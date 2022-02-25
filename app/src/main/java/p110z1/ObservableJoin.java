package p110z1;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import p110z1.ObservableGroupJoin;

/* renamed from: z1.bkw */
/* loaded from: classes3.dex */
public final class ObservableJoin<TLeft, TRight, TLeftEnd, TRightEnd, R> extends AbstractObservableWithUpstream<TLeft, R> {

    /* renamed from: b */
    final ObservableSource<? extends TRight> f19208b;

    /* renamed from: c */
    final Function<? super TLeft, ? extends ObservableSource<TLeftEnd>> f19209c;

    /* renamed from: d */
    final Function<? super TRight, ? extends ObservableSource<TRightEnd>> f19210d;

    /* renamed from: e */
    final BiFunction<? super TLeft, ? super TRight, ? extends R> f19211e;

    public ObservableJoin(ObservableSource<TLeft> asqVar, ObservableSource<? extends TRight> asqVar2, Function<? super TLeft, ? extends ObservableSource<TLeftEnd>> aunVar, Function<? super TRight, ? extends ObservableSource<TRightEnd>> aunVar2, BiFunction<? super TLeft, ? super TRight, ? extends R> auiVar) {
        super(asqVar);
        this.f19208b = asqVar2;
        this.f19209c = aunVar;
        this.f19210d = aunVar2;
        this.f19211e = auiVar;
    }

    @Override // p110z1.Observable
    /* renamed from: a */
    protected void mo34a(Observer<? super R> assVar) {
        C4487a aVar = new C4487a(assVar, this.f19209c, this.f19210d, this.f19211e);
        assVar.onSubscribe(aVar);
        ObservableGroupJoin.C4465d dVar = new ObservableGroupJoin.C4465d(aVar, true);
        aVar.disposables.mo9939a(dVar);
        ObservableGroupJoin.C4465d dVar2 = new ObservableGroupJoin.C4465d(aVar, false);
        aVar.disposables.mo9939a(dVar2);
        this.f18807a.subscribe(dVar);
        this.f19208b.subscribe(dVar2);
    }

    /* compiled from: ObservableJoin.java */
    /* renamed from: z1.bkw$a */
    /* loaded from: classes3.dex */
    static final class C4487a<TLeft, TRight, TLeftEnd, TRightEnd, R> extends AtomicInteger implements Disposable, ObservableGroupJoin.AbstractC4463b {
        private static final long serialVersionUID = -6071216598687999801L;
        volatile boolean cancelled;
        final Observer<? super R> downstream;
        final Function<? super TLeft, ? extends ObservableSource<TLeftEnd>> leftEnd;
        int leftIndex;
        final BiFunction<? super TLeft, ? super TRight, ? extends R> resultSelector;
        final Function<? super TRight, ? extends ObservableSource<TRightEnd>> rightEnd;
        int rightIndex;
        static final Integer LEFT_VALUE = 1;
        static final Integer RIGHT_VALUE = 2;
        static final Integer LEFT_CLOSE = 3;
        static final Integer RIGHT_CLOSE = 4;
        final CompositeDisposable disposables = new CompositeDisposable();
        final SpscLinkedArrayQueue<Object> queue = new SpscLinkedArrayQueue<>(Observable.m10338d());
        final Map<Integer, TLeft> lefts = new LinkedHashMap();
        final Map<Integer, TRight> rights = new LinkedHashMap();
        final AtomicReference<Throwable> error = new AtomicReference<>();
        final AtomicInteger active = new AtomicInteger(2);

        C4487a(Observer<? super R> assVar, Function<? super TLeft, ? extends ObservableSource<TLeftEnd>> aunVar, Function<? super TRight, ? extends ObservableSource<TRightEnd>> aunVar2, BiFunction<? super TLeft, ? super TRight, ? extends R> auiVar) {
            this.downstream = assVar;
            this.leftEnd = aunVar;
            this.rightEnd = aunVar2;
            this.resultSelector = auiVar;
        }

        @Override // p110z1.Disposable
        public void dispose() {
            if (!this.cancelled) {
                this.cancelled = true;
                cancelAll();
                if (getAndIncrement() == 0) {
                    this.queue.clear();
                }
            }
        }

        @Override // p110z1.Disposable
        public boolean isDisposed() {
            return this.cancelled;
        }

        void cancelAll() {
            this.disposables.dispose();
        }

        void errorAll(Observer<?> assVar) {
            Throwable a = ExceptionHelper.m9431a(this.error);
            this.lefts.clear();
            this.rights.clear();
            assVar.onError(a);
        }

        void fail(Throwable th, Observer<?> assVar, SpscLinkedArrayQueue<?> bqlVar) {
            Exceptions.m9983b(th);
            ExceptionHelper.m9430a(this.error, th);
            bqlVar.clear();
            cancelAll();
            errorAll(assVar);
        }

        /* JADX WARN: Multi-variable type inference failed */
        void drain() {
            if (getAndIncrement() == 0) {
                SpscLinkedArrayQueue<?> bqlVar = this.queue;
                Observer<? super R> assVar = this.downstream;
                int i = 1;
                while (!this.cancelled) {
                    if (this.error.get() != null) {
                        bqlVar.clear();
                        cancelAll();
                        errorAll(assVar);
                        return;
                    }
                    boolean z = this.active.get() == 0;
                    Integer num = (Integer) bqlVar.poll();
                    boolean z2 = num == null;
                    if (z && z2) {
                        this.lefts.clear();
                        this.rights.clear();
                        this.disposables.dispose();
                        assVar.onComplete();
                        return;
                    } else if (z2) {
                        i = addAndGet(-i);
                        if (i == 0) {
                            return;
                        }
                    } else {
                        Object poll = bqlVar.poll();
                        if (num == LEFT_VALUE) {
                            int i2 = this.leftIndex;
                            this.leftIndex = i2 + 1;
                            this.lefts.put(Integer.valueOf(i2), poll);
                            try {
                                ObservableSource asqVar = (ObservableSource) ObjectHelper.m9873a(this.leftEnd.apply(poll), "The leftEnd returned a null ObservableSource");
                                ObservableGroupJoin.C4464c cVar = new ObservableGroupJoin.C4464c(this, true, i2);
                                this.disposables.mo9939a(cVar);
                                asqVar.subscribe(cVar);
                                if (this.error.get() != null) {
                                    bqlVar.clear();
                                    cancelAll();
                                    errorAll(assVar);
                                    return;
                                }
                                for (TRight tright : this.rights.values()) {
                                    try {
                                        assVar.onNext((Object) ObjectHelper.m9873a(this.resultSelector.apply(poll, tright), "The resultSelector returned a null value"));
                                    } catch (Throwable th) {
                                        fail(th, assVar, bqlVar);
                                        return;
                                    }
                                }
                                continue;
                            } catch (Throwable th2) {
                                fail(th2, assVar, bqlVar);
                                return;
                            }
                        } else if (num == RIGHT_VALUE) {
                            int i3 = this.rightIndex;
                            this.rightIndex = i3 + 1;
                            this.rights.put(Integer.valueOf(i3), poll);
                            try {
                                ObservableSource asqVar2 = (ObservableSource) ObjectHelper.m9873a(this.rightEnd.apply(poll), "The rightEnd returned a null ObservableSource");
                                ObservableGroupJoin.C4464c cVar2 = new ObservableGroupJoin.C4464c(this, false, i3);
                                this.disposables.mo9939a(cVar2);
                                asqVar2.subscribe(cVar2);
                                if (this.error.get() != null) {
                                    bqlVar.clear();
                                    cancelAll();
                                    errorAll(assVar);
                                    return;
                                }
                                for (TLeft tleft : this.lefts.values()) {
                                    try {
                                        assVar.onNext((Object) ObjectHelper.m9873a(this.resultSelector.apply(tleft, poll), "The resultSelector returned a null value"));
                                    } catch (Throwable th3) {
                                        fail(th3, assVar, bqlVar);
                                        return;
                                    }
                                }
                                continue;
                            } catch (Throwable th4) {
                                fail(th4, assVar, bqlVar);
                                return;
                            }
                        } else if (num == LEFT_CLOSE) {
                            ObservableGroupJoin.C4464c cVar3 = (ObservableGroupJoin.C4464c) poll;
                            this.lefts.remove(Integer.valueOf(cVar3.index));
                            this.disposables.mo9937b(cVar3);
                        } else {
                            ObservableGroupJoin.C4464c cVar4 = (ObservableGroupJoin.C4464c) poll;
                            this.rights.remove(Integer.valueOf(cVar4.index));
                            this.disposables.mo9937b(cVar4);
                        }
                    }
                }
                bqlVar.clear();
            }
        }

        @Override // p110z1.ObservableGroupJoin.AbstractC4463b
        public void innerError(Throwable th) {
            if (ExceptionHelper.m9430a(this.error, th)) {
                this.active.decrementAndGet();
                drain();
                return;
            }
            RxJavaPlugins.m9212a(th);
        }

        @Override // p110z1.ObservableGroupJoin.AbstractC4463b
        public void innerComplete(ObservableGroupJoin.C4465d dVar) {
            this.disposables.mo9936c(dVar);
            this.active.decrementAndGet();
            drain();
        }

        @Override // p110z1.ObservableGroupJoin.AbstractC4463b
        public void innerValue(boolean z, Object obj) {
            synchronized (this) {
                this.queue.offer(z ? LEFT_VALUE : RIGHT_VALUE, obj);
            }
            drain();
        }

        @Override // p110z1.ObservableGroupJoin.AbstractC4463b
        public void innerClose(boolean z, ObservableGroupJoin.C4464c cVar) {
            synchronized (this) {
                this.queue.offer(z ? LEFT_CLOSE : RIGHT_CLOSE, cVar);
            }
            drain();
        }

        @Override // p110z1.ObservableGroupJoin.AbstractC4463b
        public void innerCloseError(Throwable th) {
            if (ExceptionHelper.m9430a(this.error, th)) {
                drain();
            } else {
                RxJavaPlugins.m9212a(th);
            }
        }
    }
}
