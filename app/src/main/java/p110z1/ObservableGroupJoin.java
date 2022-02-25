package p110z1;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

/* renamed from: z1.bkp */
/* loaded from: classes3.dex */
public final class ObservableGroupJoin<TLeft, TRight, TLeftEnd, TRightEnd, R> extends AbstractObservableWithUpstream<TLeft, R> {

    /* renamed from: b */
    final ObservableSource<? extends TRight> f19161b;

    /* renamed from: c */
    final Function<? super TLeft, ? extends ObservableSource<TLeftEnd>> f19162c;

    /* renamed from: d */
    final Function<? super TRight, ? extends ObservableSource<TRightEnd>> f19163d;

    /* renamed from: e */
    final BiFunction<? super TLeft, ? super Observable<TRight>, ? extends R> f19164e;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: ObservableGroupJoin.java */
    /* renamed from: z1.bkp$b */
    /* loaded from: classes3.dex */
    public interface AbstractC4463b {
        void innerClose(boolean z, C4464c cVar);

        void innerCloseError(Throwable th);

        void innerComplete(C4465d dVar);

        void innerError(Throwable th);

        void innerValue(boolean z, Object obj);
    }

    public ObservableGroupJoin(ObservableSource<TLeft> asqVar, ObservableSource<? extends TRight> asqVar2, Function<? super TLeft, ? extends ObservableSource<TLeftEnd>> aunVar, Function<? super TRight, ? extends ObservableSource<TRightEnd>> aunVar2, BiFunction<? super TLeft, ? super Observable<TRight>, ? extends R> auiVar) {
        super(asqVar);
        this.f19161b = asqVar2;
        this.f19162c = aunVar;
        this.f19163d = aunVar2;
        this.f19164e = auiVar;
    }

    @Override // p110z1.Observable
    /* renamed from: a */
    protected void mo34a(Observer<? super R> assVar) {
        C4462a aVar = new C4462a(assVar, this.f19162c, this.f19163d, this.f19164e);
        assVar.onSubscribe(aVar);
        C4465d dVar = new C4465d(aVar, true);
        aVar.disposables.mo9939a(dVar);
        C4465d dVar2 = new C4465d(aVar, false);
        aVar.disposables.mo9939a(dVar2);
        this.f18807a.subscribe(dVar);
        this.f19161b.subscribe(dVar2);
    }

    /* compiled from: ObservableGroupJoin.java */
    /* renamed from: z1.bkp$a */
    /* loaded from: classes3.dex */
    static final class C4462a<TLeft, TRight, TLeftEnd, TRightEnd, R> extends AtomicInteger implements Disposable, AbstractC4463b {
        private static final long serialVersionUID = -6071216598687999801L;
        volatile boolean cancelled;
        final Observer<? super R> downstream;
        final Function<? super TLeft, ? extends ObservableSource<TLeftEnd>> leftEnd;
        int leftIndex;
        final BiFunction<? super TLeft, ? super Observable<TRight>, ? extends R> resultSelector;
        final Function<? super TRight, ? extends ObservableSource<TRightEnd>> rightEnd;
        int rightIndex;
        static final Integer LEFT_VALUE = 1;
        static final Integer RIGHT_VALUE = 2;
        static final Integer LEFT_CLOSE = 3;
        static final Integer RIGHT_CLOSE = 4;
        final CompositeDisposable disposables = new CompositeDisposable();
        final SpscLinkedArrayQueue<Object> queue = new SpscLinkedArrayQueue<>(Observable.m10338d());
        final Map<Integer, UnicastSubject<TRight>> lefts = new LinkedHashMap();
        final Map<Integer, TRight> rights = new LinkedHashMap();
        final AtomicReference<Throwable> error = new AtomicReference<>();
        final AtomicInteger active = new AtomicInteger(2);

        C4462a(Observer<? super R> assVar, Function<? super TLeft, ? extends ObservableSource<TLeftEnd>> aunVar, Function<? super TRight, ? extends ObservableSource<TRightEnd>> aunVar2, BiFunction<? super TLeft, ? super Observable<TRight>, ? extends R> auiVar) {
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
            for (UnicastSubject<TRight> bvaVar : this.lefts.values()) {
                bvaVar.onError(a);
            }
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
                        for (UnicastSubject<TRight> bvaVar : this.lefts.values()) {
                            bvaVar.onComplete();
                        }
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
                            UnicastSubject<TRight> a = UnicastSubject.m8943a();
                            int i2 = this.leftIndex;
                            this.leftIndex = i2 + 1;
                            this.lefts.put(Integer.valueOf(i2), a);
                            try {
                                ObservableSource asqVar = (ObservableSource) ObjectHelper.m9873a(this.leftEnd.apply(poll), "The leftEnd returned a null ObservableSource");
                                C4464c cVar = new C4464c(this, true, i2);
                                this.disposables.mo9939a(cVar);
                                asqVar.subscribe(cVar);
                                if (this.error.get() != null) {
                                    bqlVar.clear();
                                    cancelAll();
                                    errorAll(assVar);
                                    return;
                                }
                                try {
                                    assVar.onNext((Object) ObjectHelper.m9873a(this.resultSelector.apply(poll, a), "The resultSelector returned a null value"));
                                    for (TRight tright : this.rights.values()) {
                                        a.onNext(tright);
                                    }
                                } catch (Throwable th) {
                                    fail(th, assVar, bqlVar);
                                    return;
                                }
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
                                C4464c cVar2 = new C4464c(this, false, i3);
                                this.disposables.mo9939a(cVar2);
                                asqVar2.subscribe(cVar2);
                                if (this.error.get() != null) {
                                    bqlVar.clear();
                                    cancelAll();
                                    errorAll(assVar);
                                    return;
                                }
                                for (UnicastSubject<TRight> bvaVar2 : this.lefts.values()) {
                                    bvaVar2.onNext(poll);
                                }
                            } catch (Throwable th3) {
                                fail(th3, assVar, bqlVar);
                                return;
                            }
                        } else if (num == LEFT_CLOSE) {
                            C4464c cVar3 = (C4464c) poll;
                            UnicastSubject<TRight> remove = this.lefts.remove(Integer.valueOf(cVar3.index));
                            this.disposables.mo9937b(cVar3);
                            if (remove != null) {
                                remove.onComplete();
                            }
                        } else if (num == RIGHT_CLOSE) {
                            C4464c cVar4 = (C4464c) poll;
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
        public void innerComplete(C4465d dVar) {
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
        public void innerClose(boolean z, C4464c cVar) {
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

    /* compiled from: ObservableGroupJoin.java */
    /* renamed from: z1.bkp$d */
    /* loaded from: classes3.dex */
    static final class C4465d extends AtomicReference<Disposable> implements Observer<Object>, Disposable {
        private static final long serialVersionUID = 1883890389173668373L;
        final boolean isLeft;
        final AbstractC4463b parent;

        /* JADX INFO: Access modifiers changed from: package-private */
        public C4465d(AbstractC4463b bVar, boolean z) {
            this.parent = bVar;
            this.isLeft = z;
        }

        @Override // p110z1.Disposable
        public void dispose() {
            DisposableHelper.dispose(this);
        }

        @Override // p110z1.Disposable
        public boolean isDisposed() {
            return DisposableHelper.isDisposed(get());
        }

        @Override // p110z1.Observer
        public void onSubscribe(Disposable atrVar) {
            DisposableHelper.setOnce(this, atrVar);
        }

        @Override // p110z1.Observer
        public void onNext(Object obj) {
            this.parent.innerValue(this.isLeft, obj);
        }

        @Override // p110z1.Observer
        public void onError(Throwable th) {
            this.parent.innerError(th);
        }

        @Override // p110z1.Observer
        public void onComplete() {
            this.parent.innerComplete(this);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: ObservableGroupJoin.java */
    /* renamed from: z1.bkp$c */
    /* loaded from: classes3.dex */
    public static final class C4464c extends AtomicReference<Disposable> implements Observer<Object>, Disposable {
        private static final long serialVersionUID = 1883890389173668373L;
        final int index;
        final boolean isLeft;
        final AbstractC4463b parent;

        /* JADX INFO: Access modifiers changed from: package-private */
        public C4464c(AbstractC4463b bVar, boolean z, int i) {
            this.parent = bVar;
            this.isLeft = z;
            this.index = i;
        }

        @Override // p110z1.Disposable
        public void dispose() {
            DisposableHelper.dispose(this);
        }

        @Override // p110z1.Disposable
        public boolean isDisposed() {
            return DisposableHelper.isDisposed(get());
        }

        @Override // p110z1.Observer
        public void onSubscribe(Disposable atrVar) {
            DisposableHelper.setOnce(this, atrVar);
        }

        @Override // p110z1.Observer
        public void onNext(Object obj) {
            if (DisposableHelper.dispose(this)) {
                this.parent.innerClose(this.isLeft, this);
            }
        }

        @Override // p110z1.Observer
        public void onError(Throwable th) {
            this.parent.innerCloseError(th);
        }

        @Override // p110z1.Observer
        public void onComplete() {
            this.parent.innerClose(this.isLeft, this);
        }
    }
}
