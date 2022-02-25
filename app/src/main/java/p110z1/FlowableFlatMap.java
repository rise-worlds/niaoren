package p110z1;

import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;

/* renamed from: z1.bap */
/* loaded from: classes3.dex */
public final class FlowableFlatMap<T, U> extends AbstractFlowableWithUpstream<T, U> {

    /* renamed from: c */
    final Function<? super T, ? extends Publisher<? extends U>> f18081c;

    /* renamed from: d */
    final boolean f18082d;

    /* renamed from: e */
    final int f18083e;

    /* renamed from: f */
    final int f18084f;

    public FlowableFlatMap(Flowable<T> arvVar, Function<? super T, ? extends Publisher<? extends U>> aunVar, boolean z, int i, int i2) {
        super(arvVar);
        this.f18081c = aunVar;
        this.f18082d = z;
        this.f18083e = i;
        this.f18084f = i2;
    }

    @Override // p110z1.Flowable
    /* renamed from: d */
    protected void mo9054d(Subscriber<? super U> dbxVar) {
        if (!FlowableScalarXMap.m9735a(this.f17812b, dbxVar, this.f18081c)) {
            this.f17812b.m11187a((FlowableSubscriber) m9793a(dbxVar, this.f18081c, this.f18082d, this.f18083e, this.f18084f));
        }
    }

    /* renamed from: a */
    public static <T, U> FlowableSubscriber<T> m9793a(Subscriber<? super U> dbxVar, Function<? super T, ? extends Publisher<? extends U>> aunVar, boolean z, int i, int i2) {
        return new C4065b(dbxVar, aunVar, z, i, i2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: FlowableFlatMap.java */
    /* renamed from: z1.bap$b */
    /* loaded from: classes3.dex */
    public static final class C4065b<T, U> extends AtomicInteger implements FlowableSubscriber<T>, dby {
        private static final long serialVersionUID = -2117620485640801370L;
        final int bufferSize;
        volatile boolean cancelled;
        final boolean delayErrors;
        volatile boolean done;
        final Subscriber<? super U> downstream;
        long lastId;
        int lastIndex;
        final Function<? super T, ? extends Publisher<? extends U>> mapper;
        final int maxConcurrency;
        volatile SimplePlainQueue<U> queue;
        int scalarEmitted;
        final int scalarLimit;
        long uniqueId;
        dby upstream;
        static final C4064a<?, ?>[] EMPTY = new C4064a[0];
        static final C4064a<?, ?>[] CANCELLED = new C4064a[0];
        final AtomicThrowable errs = new AtomicThrowable();
        final AtomicReference<C4064a<?, ?>[]> subscribers = new AtomicReference<>();
        final AtomicLong requested = new AtomicLong();

        C4065b(Subscriber<? super U> dbxVar, Function<? super T, ? extends Publisher<? extends U>> aunVar, boolean z, int i, int i2) {
            this.downstream = dbxVar;
            this.mapper = aunVar;
            this.delayErrors = z;
            this.maxConcurrency = i;
            this.bufferSize = i2;
            this.scalarLimit = Math.max(1, i >> 1);
            this.subscribers.lazySet(EMPTY);
        }

        @Override // p110z1.FlowableSubscriber, p110z1.Subscriber
        public void onSubscribe(dby dbyVar) {
            if (SubscriptionHelper.validate(this.upstream, dbyVar)) {
                this.upstream = dbyVar;
                this.downstream.onSubscribe(this);
                if (!this.cancelled) {
                    int i = this.maxConcurrency;
                    if (i == Integer.MAX_VALUE) {
                        dbyVar.request(cjm.f20759b);
                    } else {
                        dbyVar.request(i);
                    }
                }
            }
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // p110z1.Subscriber
        public void onNext(T t) {
            if (!this.done) {
                try {
                    Publisher dbwVar = (Publisher) ObjectHelper.m9873a(this.mapper.apply(t), "The mapper returned a null Publisher");
                    if (dbwVar instanceof Callable) {
                        try {
                            Object call = ((Callable) dbwVar).call();
                            if (call != null) {
                                tryEmitScalar(call);
                            } else if (this.maxConcurrency != Integer.MAX_VALUE && !this.cancelled) {
                                int i = this.scalarEmitted + 1;
                                this.scalarEmitted = i;
                                int i2 = this.scalarLimit;
                                if (i == i2) {
                                    this.scalarEmitted = 0;
                                    this.upstream.request(i2);
                                }
                            }
                        } catch (Throwable th) {
                            Exceptions.m9983b(th);
                            this.errs.addThrowable(th);
                            drain();
                        }
                    } else {
                        long j = this.uniqueId;
                        this.uniqueId = 1 + j;
                        C4064a aVar = new C4064a(this, j);
                        if (addInner(aVar)) {
                            dbwVar.subscribe(aVar);
                        }
                    }
                } catch (Throwable th2) {
                    Exceptions.m9983b(th2);
                    this.upstream.cancel();
                    onError(th2);
                }
            }
        }

        /* JADX WARN: Multi-variable type inference failed */
        boolean addInner(C4064a<T, U> aVar) {
            C4064a<?, ?>[] aVarArr;
            C4064a[] aVarArr2;
            do {
                aVarArr = this.subscribers.get();
                if (aVarArr == CANCELLED) {
                    aVar.dispose();
                    return false;
                }
                int length = aVarArr.length;
                aVarArr2 = new C4064a[length + 1];
                System.arraycopy(aVarArr, 0, aVarArr2, 0, length);
                aVarArr2[length] = aVar;
            } while (!this.subscribers.compareAndSet(aVarArr, aVarArr2));
            return true;
        }

        void removeInner(C4064a<T, U> aVar) {
            C4064a<?, ?>[] aVarArr;
            C4064a<?, ?>[] aVarArr2;
            do {
                aVarArr = this.subscribers.get();
                int length = aVarArr.length;
                if (length != 0) {
                    int i = -1;
                    int i2 = 0;
                    while (true) {
                        if (i2 >= length) {
                            break;
                        } else if (aVarArr[i2] == aVar) {
                            i = i2;
                            break;
                        } else {
                            i2++;
                        }
                    }
                    if (i >= 0) {
                        if (length == 1) {
                            aVarArr2 = EMPTY;
                        } else {
                            C4064a<?, ?>[] aVarArr3 = new C4064a[length - 1];
                            System.arraycopy(aVarArr, 0, aVarArr3, 0, i);
                            System.arraycopy(aVarArr, i + 1, aVarArr3, i, (length - i) - 1);
                            aVarArr2 = aVarArr3;
                        }
                    } else {
                        return;
                    }
                } else {
                    return;
                }
            } while (!this.subscribers.compareAndSet(aVarArr, aVarArr2));
        }

        SimpleQueue<U> getMainQueue() {
            SimplePlainQueue<U> avvVar = this.queue;
            if (avvVar == null) {
                int i = this.maxConcurrency;
                if (i == Integer.MAX_VALUE) {
                    avvVar = new SpscLinkedArrayQueue<>(this.bufferSize);
                } else {
                    avvVar = new SpscArrayQueue(i);
                }
                this.queue = avvVar;
            }
            return avvVar;
        }

        void tryEmitScalar(U u) {
            if (get() == 0 && compareAndSet(0, 1)) {
                long j = this.requested.get();
                SimpleQueue<U> avwVar = this.queue;
                if (j == 0 || (avwVar != null && !avwVar.isEmpty())) {
                    if (avwVar == null) {
                        avwVar = getMainQueue();
                    }
                    if (!avwVar.offer(u)) {
                        onError(new IllegalStateException("Scalar queue full?!"));
                        return;
                    }
                } else {
                    this.downstream.onNext(u);
                    if (j != cjm.f20759b) {
                        this.requested.decrementAndGet();
                    }
                    if (this.maxConcurrency != Integer.MAX_VALUE && !this.cancelled) {
                        int i = this.scalarEmitted + 1;
                        this.scalarEmitted = i;
                        int i2 = this.scalarLimit;
                        if (i == i2) {
                            this.scalarEmitted = 0;
                            this.upstream.request(i2);
                        }
                    }
                }
                if (decrementAndGet() == 0) {
                    return;
                }
            } else if (!getMainQueue().offer(u)) {
                onError(new IllegalStateException("Scalar queue full?!"));
                return;
            } else if (getAndIncrement() != 0) {
                return;
            }
            drainLoop();
        }

        SimpleQueue<U> getInnerQueue(C4064a<T, U> aVar) {
            SimpleQueue<U> avwVar = aVar.queue;
            if (avwVar != null) {
                return avwVar;
            }
            SpscArrayQueue bqkVar = new SpscArrayQueue(this.bufferSize);
            aVar.queue = bqkVar;
            return bqkVar;
        }

        void tryEmit(U u, C4064a<T, U> aVar) {
            if (get() != 0 || !compareAndSet(0, 1)) {
                SimpleQueue avwVar = aVar.queue;
                if (avwVar == null) {
                    avwVar = new SpscArrayQueue(this.bufferSize);
                    aVar.queue = avwVar;
                }
                if (!avwVar.offer(u)) {
                    onError(new MissingBackpressureException("Inner queue full?!"));
                    return;
                } else if (getAndIncrement() != 0) {
                    return;
                }
            } else {
                long j = this.requested.get();
                SimpleQueue<U> avwVar2 = aVar.queue;
                if (j == 0 || (avwVar2 != null && !avwVar2.isEmpty())) {
                    if (avwVar2 == null) {
                        avwVar2 = getInnerQueue(aVar);
                    }
                    if (!avwVar2.offer(u)) {
                        onError(new MissingBackpressureException("Inner queue full?!"));
                        return;
                    }
                } else {
                    this.downstream.onNext(u);
                    if (j != cjm.f20759b) {
                        this.requested.decrementAndGet();
                    }
                    aVar.requestMore(1L);
                }
                if (decrementAndGet() == 0) {
                    return;
                }
            }
            drainLoop();
        }

        @Override // p110z1.Subscriber
        public void onError(Throwable th) {
            if (this.done) {
                RxJavaPlugins.m9212a(th);
            } else if (this.errs.addThrowable(th)) {
                this.done = true;
                drain();
            } else {
                RxJavaPlugins.m9212a(th);
            }
        }

        @Override // p110z1.Subscriber
        public void onComplete() {
            if (!this.done) {
                this.done = true;
                drain();
            }
        }

        @Override // p110z1.dby
        public void request(long j) {
            if (SubscriptionHelper.validate(j)) {
                BackpressureHelper.m9449a(this.requested, j);
                drain();
            }
        }

        @Override // p110z1.dby
        public void cancel() {
            SimplePlainQueue<U> avvVar;
            if (!this.cancelled) {
                this.cancelled = true;
                this.upstream.cancel();
                disposeAll();
                if (getAndIncrement() == 0 && (avvVar = this.queue) != null) {
                    avvVar.clear();
                }
            }
        }

        void drain() {
            if (getAndIncrement() == 0) {
                drainLoop();
            }
        }

        /* JADX WARN: Code restructure failed: missing block: B:120:0x019e, code lost:
            r24.lastIndex = r4;
            r24.lastId = r11[r4].f18085id;
            r5 = 0;
         */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        void drainLoop() {
            /*
                Method dump skipped, instructions count: 463
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: p110z1.FlowableFlatMap.C4065b.drainLoop():void");
        }

        boolean checkTerminate() {
            if (this.cancelled) {
                clearScalarQueue();
                return true;
            } else if (this.delayErrors || this.errs.get() == null) {
                return false;
            } else {
                clearScalarQueue();
                Throwable terminate = this.errs.terminate();
                if (terminate != ExceptionHelper.f20064a) {
                    this.downstream.onError(terminate);
                }
                return true;
            }
        }

        void clearScalarQueue() {
            SimplePlainQueue<U> avvVar = this.queue;
            if (avvVar != null) {
                avvVar.clear();
            }
        }

        void disposeAll() {
            C4064a<?, ?>[] andSet;
            C4064a<?, ?>[] aVarArr = this.subscribers.get();
            C4064a<?, ?>[] aVarArr2 = CANCELLED;
            if (!(aVarArr == aVarArr2 || (andSet = this.subscribers.getAndSet(aVarArr2)) == CANCELLED)) {
                for (C4064a<?, ?> aVar : andSet) {
                    aVar.dispose();
                }
                Throwable terminate = this.errs.terminate();
                if (!(terminate == null || terminate == ExceptionHelper.f20064a)) {
                    RxJavaPlugins.m9212a(terminate);
                }
            }
        }

        void innerError(C4064a<T, U> aVar, Throwable th) {
            if (this.errs.addThrowable(th)) {
                aVar.done = true;
                if (!this.delayErrors) {
                    this.upstream.cancel();
                    for (C4064a<?, ?> aVar2 : this.subscribers.getAndSet(CANCELLED)) {
                        aVar2.dispose();
                    }
                }
                drain();
                return;
            }
            RxJavaPlugins.m9212a(th);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: FlowableFlatMap.java */
    /* renamed from: z1.bap$a */
    /* loaded from: classes3.dex */
    public static final class C4064a<T, U> extends AtomicReference<dby> implements FlowableSubscriber<U>, Disposable {
        private static final long serialVersionUID = -4606175640614850599L;
        final int bufferSize;
        volatile boolean done;
        int fusionMode;

        /* renamed from: id */
        final long f18085id;
        final int limit;
        final C4065b<T, U> parent;
        long produced;
        volatile SimpleQueue<U> queue;

        C4064a(C4065b<T, U> bVar, long j) {
            this.f18085id = j;
            this.parent = bVar;
            this.bufferSize = bVar.bufferSize;
            this.limit = this.bufferSize >> 2;
        }

        @Override // p110z1.FlowableSubscriber, p110z1.Subscriber
        public void onSubscribe(dby dbyVar) {
            if (SubscriptionHelper.setOnce(this, dbyVar)) {
                if (dbyVar instanceof QueueSubscription) {
                    QueueSubscription avtVar = (QueueSubscription) dbyVar;
                    int requestFusion = avtVar.requestFusion(7);
                    if (requestFusion == 1) {
                        this.fusionMode = requestFusion;
                        this.queue = avtVar;
                        this.done = true;
                        this.parent.drain();
                        return;
                    } else if (requestFusion == 2) {
                        this.fusionMode = requestFusion;
                        this.queue = avtVar;
                    }
                }
                dbyVar.request(this.bufferSize);
            }
        }

        @Override // p110z1.Subscriber
        public void onNext(U u) {
            if (this.fusionMode != 2) {
                this.parent.tryEmit(u, this);
            } else {
                this.parent.drain();
            }
        }

        @Override // p110z1.Subscriber
        public void onError(Throwable th) {
            lazySet(SubscriptionHelper.CANCELLED);
            this.parent.innerError(this, th);
        }

        @Override // p110z1.Subscriber
        public void onComplete() {
            this.done = true;
            this.parent.drain();
        }

        void requestMore(long j) {
            if (this.fusionMode != 1) {
                long j2 = this.produced + j;
                if (j2 >= this.limit) {
                    this.produced = 0L;
                    get().request(j2);
                    return;
                }
                this.produced = j2;
            }
        }

        @Override // p110z1.Disposable
        public void dispose() {
            SubscriptionHelper.cancel(this);
        }

        @Override // p110z1.Disposable
        public boolean isDisposed() {
            return get() == SubscriptionHelper.CANCELLED;
        }
    }
}
