package p110z1;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;

/* renamed from: z1.bau */
/* loaded from: classes3.dex */
public final class FlowableFlatMapSingle<T, R> extends AbstractFlowableWithUpstream<T, R> {

    /* renamed from: c */
    final Function<? super T, ? extends SingleSource<? extends R>> f18101c;

    /* renamed from: d */
    final boolean f18102d;

    /* renamed from: e */
    final int f18103e;

    public FlowableFlatMapSingle(Flowable<T> arvVar, Function<? super T, ? extends SingleSource<? extends R>> aunVar, boolean z, int i) {
        super(arvVar);
        this.f18101c = aunVar;
        this.f18102d = z;
        this.f18103e = i;
    }

    @Override // p110z1.Flowable
    /* renamed from: d */
    protected void mo9054d(Subscriber<? super R> dbxVar) {
        this.f17812b.m11187a((FlowableSubscriber) new C4072a(dbxVar, this.f18101c, this.f18102d, this.f18103e));
    }

    /* compiled from: FlowableFlatMapSingle.java */
    /* renamed from: z1.bau$a */
    /* loaded from: classes3.dex */
    static final class C4072a<T, R> extends AtomicInteger implements FlowableSubscriber<T>, dby {
        private static final long serialVersionUID = 8600231336733376951L;
        volatile boolean cancelled;
        final boolean delayErrors;
        final Subscriber<? super R> downstream;
        final Function<? super T, ? extends SingleSource<? extends R>> mapper;
        final int maxConcurrency;
        dby upstream;
        final AtomicLong requested = new AtomicLong();
        final CompositeDisposable set = new CompositeDisposable();
        final AtomicThrowable errors = new AtomicThrowable();
        final AtomicInteger active = new AtomicInteger(1);
        final AtomicReference<SpscLinkedArrayQueue<R>> queue = new AtomicReference<>();

        C4072a(Subscriber<? super R> dbxVar, Function<? super T, ? extends SingleSource<? extends R>> aunVar, boolean z, int i) {
            this.downstream = dbxVar;
            this.mapper = aunVar;
            this.delayErrors = z;
            this.maxConcurrency = i;
        }

        @Override // p110z1.FlowableSubscriber, p110z1.Subscriber
        public void onSubscribe(dby dbyVar) {
            if (SubscriptionHelper.validate(this.upstream, dbyVar)) {
                this.upstream = dbyVar;
                this.downstream.onSubscribe(this);
                int i = this.maxConcurrency;
                if (i == Integer.MAX_VALUE) {
                    dbyVar.request(cjm.f20759b);
                } else {
                    dbyVar.request(i);
                }
            }
        }

        @Override // p110z1.Subscriber
        public void onNext(T t) {
            try {
                SingleSource ataVar = (SingleSource) ObjectHelper.m9873a(this.mapper.apply(t), "The mapper returned a null SingleSource");
                this.active.getAndIncrement();
                C4073a aVar = new C4073a();
                if (!this.cancelled && this.set.mo9939a(aVar)) {
                    ataVar.mo10018a(aVar);
                }
            } catch (Throwable th) {
                Exceptions.m9983b(th);
                this.upstream.cancel();
                onError(th);
            }
        }

        @Override // p110z1.Subscriber
        public void onError(Throwable th) {
            this.active.decrementAndGet();
            if (this.errors.addThrowable(th)) {
                if (!this.delayErrors) {
                    this.set.dispose();
                }
                drain();
                return;
            }
            RxJavaPlugins.m9212a(th);
        }

        @Override // p110z1.Subscriber
        public void onComplete() {
            this.active.decrementAndGet();
            drain();
        }

        @Override // p110z1.dby
        public void cancel() {
            this.cancelled = true;
            this.upstream.cancel();
            this.set.dispose();
        }

        @Override // p110z1.dby
        public void request(long j) {
            if (SubscriptionHelper.validate(j)) {
                BackpressureHelper.m9449a(this.requested, j);
                drain();
            }
        }

        void innerSuccess(C4072a<T, R>.C4073a aVar, R r) {
            this.set.mo9936c(aVar);
            if (get() == 0) {
                boolean z = true;
                if (compareAndSet(0, 1)) {
                    if (this.active.decrementAndGet() != 0) {
                        z = false;
                    }
                    if (this.requested.get() != 0) {
                        this.downstream.onNext(r);
                        SpscLinkedArrayQueue<R> bqlVar = this.queue.get();
                        if (!z || (bqlVar != null && !bqlVar.isEmpty())) {
                            BackpressureHelper.m9446c(this.requested, 1L);
                            if (this.maxConcurrency != Integer.MAX_VALUE) {
                                this.upstream.request(1L);
                            }
                        } else {
                            Throwable terminate = this.errors.terminate();
                            if (terminate != null) {
                                this.downstream.onError(terminate);
                                return;
                            } else {
                                this.downstream.onComplete();
                                return;
                            }
                        }
                    } else {
                        SpscLinkedArrayQueue<R> orCreateQueue = getOrCreateQueue();
                        synchronized (orCreateQueue) {
                            orCreateQueue.offer(r);
                        }
                    }
                    if (decrementAndGet() == 0) {
                        return;
                    }
                    drainLoop();
                }
            }
            SpscLinkedArrayQueue<R> orCreateQueue2 = getOrCreateQueue();
            synchronized (orCreateQueue2) {
                orCreateQueue2.offer(r);
            }
            this.active.decrementAndGet();
            if (getAndIncrement() != 0) {
                return;
            }
            drainLoop();
        }

        SpscLinkedArrayQueue<R> getOrCreateQueue() {
            SpscLinkedArrayQueue<R> bqlVar;
            do {
                SpscLinkedArrayQueue<R> bqlVar2 = this.queue.get();
                if (bqlVar2 != null) {
                    return bqlVar2;
                }
                bqlVar = new SpscLinkedArrayQueue<>(Flowable.m11274a());
            } while (!this.queue.compareAndSet(null, bqlVar));
            return bqlVar;
        }

        void innerError(C4072a<T, R>.C4073a aVar, Throwable th) {
            this.set.mo9936c(aVar);
            if (this.errors.addThrowable(th)) {
                if (!this.delayErrors) {
                    this.upstream.cancel();
                    this.set.dispose();
                } else if (this.maxConcurrency != Integer.MAX_VALUE) {
                    this.upstream.request(1L);
                }
                this.active.decrementAndGet();
                drain();
                return;
            }
            RxJavaPlugins.m9212a(th);
        }

        void drain() {
            if (getAndIncrement() == 0) {
                drainLoop();
            }
        }

        void clear() {
            SpscLinkedArrayQueue<R> bqlVar = this.queue.get();
            if (bqlVar != null) {
                bqlVar.clear();
            }
        }

        /* JADX WARN: Code restructure failed: missing block: B:36:0x0077, code lost:
            if (r13 != 0) goto L_0x00c8;
         */
        /* JADX WARN: Code restructure failed: missing block: B:38:0x007b, code lost:
            if (r17.cancelled == false) goto L_0x0081;
         */
        /* JADX WARN: Code restructure failed: missing block: B:39:0x007d, code lost:
            clear();
         */
        /* JADX WARN: Code restructure failed: missing block: B:40:0x0080, code lost:
            return;
         */
        /* JADX WARN: Code restructure failed: missing block: B:42:0x0083, code lost:
            if (r17.delayErrors != false) goto L_0x009c;
         */
        /* JADX WARN: Code restructure failed: missing block: B:44:0x008d, code lost:
            if (r17.errors.get() == null) goto L_0x009c;
         */
        /* JADX WARN: Code restructure failed: missing block: B:45:0x008f, code lost:
            r2 = r17.errors.terminate();
            clear();
            r1.onError(r2);
         */
        /* JADX WARN: Code restructure failed: missing block: B:46:0x009b, code lost:
            return;
         */
        /* JADX WARN: Code restructure failed: missing block: B:48:0x00a0, code lost:
            if (r2.get() != 0) goto L_0x00a4;
         */
        /* JADX WARN: Code restructure failed: missing block: B:49:0x00a2, code lost:
            r6 = true;
         */
        /* JADX WARN: Code restructure failed: missing block: B:50:0x00a4, code lost:
            r6 = false;
         */
        /* JADX WARN: Code restructure failed: missing block: B:51:0x00a5, code lost:
            r7 = r3.get();
         */
        /* JADX WARN: Code restructure failed: missing block: B:52:0x00ab, code lost:
            if (r7 == null) goto L_0x00b3;
         */
        /* JADX WARN: Code restructure failed: missing block: B:54:0x00b1, code lost:
            if (r7.isEmpty() == false) goto L_0x00b4;
         */
        /* JADX WARN: Code restructure failed: missing block: B:55:0x00b3, code lost:
            r12 = true;
         */
        /* JADX WARN: Code restructure failed: missing block: B:56:0x00b4, code lost:
            if (r6 == false) goto L_0x00c8;
         */
        /* JADX WARN: Code restructure failed: missing block: B:57:0x00b6, code lost:
            if (r12 == false) goto L_0x00c8;
         */
        /* JADX WARN: Code restructure failed: missing block: B:58:0x00b8, code lost:
            r2 = r17.errors.terminate();
         */
        /* JADX WARN: Code restructure failed: missing block: B:59:0x00be, code lost:
            if (r2 == null) goto L_0x00c4;
         */
        /* JADX WARN: Code restructure failed: missing block: B:60:0x00c0, code lost:
            r1.onError(r2);
         */
        /* JADX WARN: Code restructure failed: missing block: B:61:0x00c4, code lost:
            r1.onComplete();
         */
        /* JADX WARN: Code restructure failed: missing block: B:62:0x00c7, code lost:
            return;
         */
        /* JADX WARN: Code restructure failed: missing block: B:64:0x00ca, code lost:
            if (r10 == 0) goto L_0x00dd;
         */
        /* JADX WARN: Code restructure failed: missing block: B:65:0x00cc, code lost:
            p110z1.BackpressureHelper.m9446c(r17.requested, r10);
         */
        /* JADX WARN: Code restructure failed: missing block: B:66:0x00d6, code lost:
            if (r17.maxConcurrency == Integer.MAX_VALUE) goto L_0x00dd;
         */
        /* JADX WARN: Code restructure failed: missing block: B:67:0x00d8, code lost:
            r17.upstream.request(r10);
         */
        /* JADX WARN: Code restructure failed: missing block: B:68:0x00dd, code lost:
            r5 = addAndGet(-r5);
         */
        /* JADX WARN: Code restructure failed: missing block: B:81:?, code lost:
            return;
         */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        void drainLoop() {
            /*
                Method dump skipped, instructions count: 229
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: p110z1.FlowableFlatMapSingle.C4072a.drainLoop():void");
        }

        /* compiled from: FlowableFlatMapSingle.java */
        /* renamed from: z1.bau$a$a */
        /* loaded from: classes3.dex */
        final class C4073a extends AtomicReference<Disposable> implements SingleObserver<R>, Disposable {
            private static final long serialVersionUID = -502562646270949838L;

            C4073a() {
            }

            @Override // p110z1.SingleObserver
            public void onSubscribe(Disposable atrVar) {
                DisposableHelper.setOnce(this, atrVar);
            }

            @Override // p110z1.SingleObserver
            public void onSuccess(R r) {
                C4072a.this.innerSuccess(this, r);
            }

            @Override // p110z1.SingleObserver
            public void onError(Throwable th) {
                C4072a.this.innerError(this, th);
            }

            @Override // p110z1.Disposable
            public boolean isDisposed() {
                return DisposableHelper.isDisposed(get());
            }

            @Override // p110z1.Disposable
            public void dispose() {
                DisposableHelper.dispose(this);
            }
        }
    }
}
