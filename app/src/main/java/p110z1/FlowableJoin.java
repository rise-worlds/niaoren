package p110z1;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import p110z1.FlowableGroupJoin;

/* renamed from: z1.bbl */
/* loaded from: classes3.dex */
public final class FlowableJoin<TLeft, TRight, TLeftEnd, TRightEnd, R> extends AbstractFlowableWithUpstream<TLeft, R> {

    /* renamed from: c */
    final Publisher<? extends TRight> f18174c;

    /* renamed from: d */
    final Function<? super TLeft, ? extends Publisher<TLeftEnd>> f18175d;

    /* renamed from: e */
    final Function<? super TRight, ? extends Publisher<TRightEnd>> f18176e;

    /* renamed from: f */
    final BiFunction<? super TLeft, ? super TRight, ? extends R> f18177f;

    public FlowableJoin(Flowable<TLeft> arvVar, Publisher<? extends TRight> dbwVar, Function<? super TLeft, ? extends Publisher<TLeftEnd>> aunVar, Function<? super TRight, ? extends Publisher<TRightEnd>> aunVar2, BiFunction<? super TLeft, ? super TRight, ? extends R> auiVar) {
        super(arvVar);
        this.f18174c = dbwVar;
        this.f18175d = aunVar;
        this.f18176e = aunVar2;
        this.f18177f = auiVar;
    }

    @Override // p110z1.Flowable
    /* renamed from: d */
    protected void mo9054d(Subscriber<? super R> dbxVar) {
        C4113a aVar = new C4113a(dbxVar, this.f18175d, this.f18176e, this.f18177f);
        dbxVar.onSubscribe(aVar);
        FlowableGroupJoin.C4091d dVar = new FlowableGroupJoin.C4091d(aVar, true);
        aVar.disposables.mo9939a(dVar);
        FlowableGroupJoin.C4091d dVar2 = new FlowableGroupJoin.C4091d(aVar, false);
        aVar.disposables.mo9939a(dVar2);
        this.f17812b.m11187a((FlowableSubscriber) dVar);
        this.f18174c.subscribe(dVar2);
    }

    /* compiled from: FlowableJoin.java */
    /* renamed from: z1.bbl$a */
    /* loaded from: classes3.dex */
    static final class C4113a<TLeft, TRight, TLeftEnd, TRightEnd, R> extends AtomicInteger implements FlowableGroupJoin.AbstractC4089b, dby {
        private static final long serialVersionUID = -6071216598687999801L;
        volatile boolean cancelled;
        final Subscriber<? super R> downstream;
        final Function<? super TLeft, ? extends Publisher<TLeftEnd>> leftEnd;
        int leftIndex;
        final BiFunction<? super TLeft, ? super TRight, ? extends R> resultSelector;
        final Function<? super TRight, ? extends Publisher<TRightEnd>> rightEnd;
        int rightIndex;
        static final Integer LEFT_VALUE = 1;
        static final Integer RIGHT_VALUE = 2;
        static final Integer LEFT_CLOSE = 3;
        static final Integer RIGHT_CLOSE = 4;
        final AtomicLong requested = new AtomicLong();
        final CompositeDisposable disposables = new CompositeDisposable();
        final SpscLinkedArrayQueue<Object> queue = new SpscLinkedArrayQueue<>(Flowable.m11274a());
        final Map<Integer, TLeft> lefts = new LinkedHashMap();
        final Map<Integer, TRight> rights = new LinkedHashMap();
        final AtomicReference<Throwable> error = new AtomicReference<>();
        final AtomicInteger active = new AtomicInteger(2);

        C4113a(Subscriber<? super R> dbxVar, Function<? super TLeft, ? extends Publisher<TLeftEnd>> aunVar, Function<? super TRight, ? extends Publisher<TRightEnd>> aunVar2, BiFunction<? super TLeft, ? super TRight, ? extends R> auiVar) {
            this.downstream = dbxVar;
            this.leftEnd = aunVar;
            this.rightEnd = aunVar2;
            this.resultSelector = auiVar;
        }

        @Override // p110z1.dby
        public void request(long j) {
            if (SubscriptionHelper.validate(j)) {
                BackpressureHelper.m9449a(this.requested, j);
            }
        }

        @Override // p110z1.dby
        public void cancel() {
            if (!this.cancelled) {
                this.cancelled = true;
                cancelAll();
                if (getAndIncrement() == 0) {
                    this.queue.clear();
                }
            }
        }

        void cancelAll() {
            this.disposables.dispose();
        }

        void errorAll(Subscriber<?> dbxVar) {
            Throwable a = ExceptionHelper.m9431a(this.error);
            this.lefts.clear();
            this.rights.clear();
            dbxVar.onError(a);
        }

        void fail(Throwable th, Subscriber<?> dbxVar, SimpleQueue<?> avwVar) {
            Exceptions.m9983b(th);
            ExceptionHelper.m9430a(this.error, th);
            avwVar.clear();
            cancelAll();
            errorAll(dbxVar);
        }

        /* JADX WARN: Multi-variable type inference failed */
        void drain() {
            if (getAndIncrement() == 0) {
                SpscLinkedArrayQueue<Object> bqlVar = this.queue;
                Subscriber<? super R> dbxVar = this.downstream;
                boolean z = true;
                int i = 1;
                while (!this.cancelled) {
                    if (this.error.get() != null) {
                        bqlVar.clear();
                        cancelAll();
                        errorAll(dbxVar);
                        return;
                    }
                    boolean z2 = this.active.get() == 0;
                    Integer num = (Integer) bqlVar.poll();
                    boolean z3 = num == null;
                    if (z2 && z3) {
                        this.lefts.clear();
                        this.rights.clear();
                        this.disposables.dispose();
                        dbxVar.onComplete();
                        return;
                    } else if (z3) {
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
                                Publisher dbwVar = (Publisher) ObjectHelper.m9873a(this.leftEnd.apply(poll), "The leftEnd returned a null Publisher");
                                FlowableGroupJoin.C4090c cVar = new FlowableGroupJoin.C4090c(this, z, i2);
                                this.disposables.mo9939a(cVar);
                                dbwVar.subscribe(cVar);
                                if (this.error.get() != null) {
                                    bqlVar.clear();
                                    cancelAll();
                                    errorAll(dbxVar);
                                    return;
                                }
                                long j = this.requested.get();
                                long j2 = 0;
                                for (TRight tright : this.rights.values()) {
                                    try {
                                        Object obj = (Object) ObjectHelper.m9873a(this.resultSelector.apply(poll, tright), "The resultSelector returned a null value");
                                        if (j2 != j) {
                                            dbxVar.onNext(obj);
                                            j2++;
                                        } else {
                                            ExceptionHelper.m9430a(this.error, new MissingBackpressureException("Could not emit value due to lack of requests"));
                                            bqlVar.clear();
                                            cancelAll();
                                            errorAll(dbxVar);
                                            return;
                                        }
                                    } catch (Throwable th) {
                                        fail(th, dbxVar, bqlVar);
                                        return;
                                    }
                                }
                                if (j2 != 0) {
                                    BackpressureHelper.m9446c(this.requested, j2);
                                }
                            } catch (Throwable th2) {
                                fail(th2, dbxVar, bqlVar);
                                return;
                            }
                        } else if (num == RIGHT_VALUE) {
                            int i3 = this.rightIndex;
                            this.rightIndex = i3 + 1;
                            this.rights.put(Integer.valueOf(i3), poll);
                            try {
                                Publisher dbwVar2 = (Publisher) ObjectHelper.m9873a(this.rightEnd.apply(poll), "The rightEnd returned a null Publisher");
                                FlowableGroupJoin.C4090c cVar2 = new FlowableGroupJoin.C4090c(this, false, i3);
                                this.disposables.mo9939a(cVar2);
                                dbwVar2.subscribe(cVar2);
                                if (this.error.get() != null) {
                                    bqlVar.clear();
                                    cancelAll();
                                    errorAll(dbxVar);
                                    return;
                                }
                                long j3 = this.requested.get();
                                long j4 = 0;
                                for (TLeft tleft : this.lefts.values()) {
                                    try {
                                        Object obj2 = (Object) ObjectHelper.m9873a(this.resultSelector.apply(tleft, poll), "The resultSelector returned a null value");
                                        if (j4 != j3) {
                                            dbxVar.onNext(obj2);
                                            j4++;
                                        } else {
                                            ExceptionHelper.m9430a(this.error, new MissingBackpressureException("Could not emit value due to lack of requests"));
                                            bqlVar.clear();
                                            cancelAll();
                                            errorAll(dbxVar);
                                            return;
                                        }
                                    } catch (Throwable th3) {
                                        fail(th3, dbxVar, bqlVar);
                                        return;
                                    }
                                }
                                if (j4 != 0) {
                                    BackpressureHelper.m9446c(this.requested, j4);
                                }
                            } catch (Throwable th4) {
                                fail(th4, dbxVar, bqlVar);
                                return;
                            }
                        } else if (num == LEFT_CLOSE) {
                            FlowableGroupJoin.C4090c cVar3 = (FlowableGroupJoin.C4090c) poll;
                            this.lefts.remove(Integer.valueOf(cVar3.index));
                            this.disposables.mo9937b(cVar3);
                        } else if (num == RIGHT_CLOSE) {
                            FlowableGroupJoin.C4090c cVar4 = (FlowableGroupJoin.C4090c) poll;
                            this.rights.remove(Integer.valueOf(cVar4.index));
                            this.disposables.mo9937b(cVar4);
                        }
                        z = true;
                    }
                }
                bqlVar.clear();
            }
        }

        @Override // p110z1.FlowableGroupJoin.AbstractC4089b
        public void innerError(Throwable th) {
            if (ExceptionHelper.m9430a(this.error, th)) {
                this.active.decrementAndGet();
                drain();
                return;
            }
            RxJavaPlugins.m9212a(th);
        }

        @Override // p110z1.FlowableGroupJoin.AbstractC4089b
        public void innerComplete(FlowableGroupJoin.C4091d dVar) {
            this.disposables.mo9936c(dVar);
            this.active.decrementAndGet();
            drain();
        }

        @Override // p110z1.FlowableGroupJoin.AbstractC4089b
        public void innerValue(boolean z, Object obj) {
            synchronized (this) {
                this.queue.offer(z ? LEFT_VALUE : RIGHT_VALUE, obj);
            }
            drain();
        }

        @Override // p110z1.FlowableGroupJoin.AbstractC4089b
        public void innerClose(boolean z, FlowableGroupJoin.C4090c cVar) {
            synchronized (this) {
                this.queue.offer(z ? LEFT_CLOSE : RIGHT_CLOSE, cVar);
            }
            drain();
        }

        @Override // p110z1.FlowableGroupJoin.AbstractC4089b
        public void innerCloseError(Throwable th) {
            if (ExceptionHelper.m9430a(this.error, th)) {
                drain();
            } else {
                RxJavaPlugins.m9212a(th);
            }
        }
    }
}
