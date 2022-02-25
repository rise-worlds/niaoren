package p110z1;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;

/* renamed from: z1.bob */
/* loaded from: classes3.dex */
public final class ParallelJoin<T> extends Flowable<T> {

    /* renamed from: b */
    final ParallelFlowable<? extends T> f19631b;

    /* renamed from: c */
    final int f19632c;

    /* renamed from: d */
    final boolean f19633d;

    public ParallelJoin(ParallelFlowable<? extends T> bubVar, int i, boolean z) {
        this.f19631b = bubVar;
        this.f19632c = i;
        this.f19633d = z;
    }

    @Override // p110z1.Flowable
    /* renamed from: d */
    protected void mo9054d(Subscriber<? super T> dbxVar) {
        AbstractC4634c cVar;
        if (this.f19633d) {
            cVar = new C4635d(dbxVar, this.f19631b.mo9267a(), this.f19632c);
        } else {
            cVar = new C4633b(dbxVar, this.f19631b.mo9267a(), this.f19632c);
        }
        dbxVar.onSubscribe(cVar);
        this.f19631b.mo9236a(cVar.subscribers);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: ParallelJoin.java */
    /* renamed from: z1.bob$c */
    /* loaded from: classes3.dex */
    public static abstract class AbstractC4634c<T> extends AtomicInteger implements dby {
        private static final long serialVersionUID = 3100232009247827843L;
        volatile boolean cancelled;
        final Subscriber<? super T> downstream;
        final C4632a<T>[] subscribers;
        final AtomicThrowable errors = new AtomicThrowable();
        final AtomicLong requested = new AtomicLong();
        final AtomicInteger done = new AtomicInteger();

        abstract void drain();

        abstract void onComplete();

        abstract void onError(Throwable th);

        abstract void onNext(C4632a<T> aVar, T t);

        AbstractC4634c(Subscriber<? super T> dbxVar, int i, int i2) {
            this.downstream = dbxVar;
            C4632a<T>[] aVarArr = new C4632a[i];
            for (int i3 = 0; i3 < i; i3++) {
                aVarArr[i3] = new C4632a<>(this, i2);
            }
            this.subscribers = aVarArr;
            this.done.lazySet(i);
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
            if (!this.cancelled) {
                this.cancelled = true;
                cancelAll();
                if (getAndIncrement() == 0) {
                    cleanup();
                }
            }
        }

        void cancelAll() {
            for (C4632a<T> aVar : this.subscribers) {
                aVar.cancel();
            }
        }

        void cleanup() {
            for (C4632a<T> aVar : this.subscribers) {
                aVar.queue = null;
            }
        }
    }

    /* compiled from: ParallelJoin.java */
    /* renamed from: z1.bob$b */
    /* loaded from: classes3.dex */
    static final class C4633b<T> extends AbstractC4634c<T> {
        private static final long serialVersionUID = 6312374661811000451L;

        C4633b(Subscriber<? super T> dbxVar, int i, int i2) {
            super(dbxVar, i, i2);
        }

        @Override // p110z1.ParallelJoin.AbstractC4634c
        public void onNext(C4632a<T> aVar, T t) {
            if (get() == 0 && compareAndSet(0, 1)) {
                if (this.requested.get() != 0) {
                    this.downstream.onNext(t);
                    if (this.requested.get() != cjm.f20759b) {
                        this.requested.decrementAndGet();
                    }
                    aVar.request(1L);
                } else if (!aVar.getQueue().offer(t)) {
                    cancelAll();
                    MissingBackpressureException auaVar = new MissingBackpressureException("Queue full?!");
                    if (this.errors.compareAndSet(null, auaVar)) {
                        this.downstream.onError(auaVar);
                        return;
                    } else {
                        RxJavaPlugins.m9212a(auaVar);
                        return;
                    }
                }
                if (decrementAndGet() == 0) {
                    return;
                }
            } else if (!aVar.getQueue().offer(t)) {
                cancelAll();
                onError(new MissingBackpressureException("Queue full?!"));
                return;
            } else if (getAndIncrement() != 0) {
                return;
            }
            drainLoop();
        }

        @Override // p110z1.ParallelJoin.AbstractC4634c
        public void onError(Throwable th) {
            if (this.errors.compareAndSet(null, th)) {
                cancelAll();
                drain();
            } else if (th != this.errors.get()) {
                RxJavaPlugins.m9212a(th);
            }
        }

        @Override // p110z1.ParallelJoin.AbstractC4634c
        public void onComplete() {
            this.done.decrementAndGet();
            drain();
        }

        @Override // p110z1.ParallelJoin.AbstractC4634c
        void drain() {
            if (getAndIncrement() == 0) {
                drainLoop();
            }
        }

        /* JADX WARN: Code restructure failed: missing block: B:30:0x005f, code lost:
            if (r13 == false) goto L_0x0067;
         */
        /* JADX WARN: Code restructure failed: missing block: B:31:0x0061, code lost:
            if (r11 == false) goto L_0x0067;
         */
        /* JADX WARN: Code restructure failed: missing block: B:32:0x0063, code lost:
            r3.onComplete();
         */
        /* JADX WARN: Code restructure failed: missing block: B:33:0x0066, code lost:
            return;
         */
        /* JADX WARN: Code restructure failed: missing block: B:34:0x0067, code lost:
            if (r11 == false) goto L_0x006b;
         */
        /* JADX WARN: Code restructure failed: missing block: B:35:0x0069, code lost:
            r10 = r14;
         */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        void drainLoop() {
            /*
                Method dump skipped, instructions count: 219
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: p110z1.ParallelJoin.C4633b.drainLoop():void");
        }
    }

    /* compiled from: ParallelJoin.java */
    /* renamed from: z1.bob$d */
    /* loaded from: classes3.dex */
    static final class C4635d<T> extends AbstractC4634c<T> {
        private static final long serialVersionUID = -5737965195918321883L;

        C4635d(Subscriber<? super T> dbxVar, int i, int i2) {
            super(dbxVar, i, i2);
        }

        @Override // p110z1.ParallelJoin.AbstractC4634c
        void onNext(C4632a<T> aVar, T t) {
            if (get() != 0 || !compareAndSet(0, 1)) {
                if (!aVar.getQueue().offer(t) && aVar.cancel()) {
                    this.errors.addThrowable(new MissingBackpressureException("Queue full?!"));
                    this.done.decrementAndGet();
                }
                if (getAndIncrement() != 0) {
                    return;
                }
            } else {
                if (this.requested.get() != 0) {
                    this.downstream.onNext(t);
                    if (this.requested.get() != cjm.f20759b) {
                        this.requested.decrementAndGet();
                    }
                    aVar.request(1L);
                } else if (!aVar.getQueue().offer(t)) {
                    aVar.cancel();
                    this.errors.addThrowable(new MissingBackpressureException("Queue full?!"));
                    this.done.decrementAndGet();
                    drainLoop();
                    return;
                }
                if (decrementAndGet() == 0) {
                    return;
                }
            }
            drainLoop();
        }

        @Override // p110z1.ParallelJoin.AbstractC4634c
        void onError(Throwable th) {
            this.errors.addThrowable(th);
            this.done.decrementAndGet();
            drain();
        }

        @Override // p110z1.ParallelJoin.AbstractC4634c
        void onComplete() {
            this.done.decrementAndGet();
            drain();
        }

        @Override // p110z1.ParallelJoin.AbstractC4634c
        void drain() {
            if (getAndIncrement() == 0) {
                drainLoop();
            }
        }

        /* JADX WARN: Code restructure failed: missing block: B:25:0x004d, code lost:
            if (r13 == false) goto L_0x0069;
         */
        /* JADX WARN: Code restructure failed: missing block: B:26:0x004f, code lost:
            if (r11 == false) goto L_0x0069;
         */
        /* JADX WARN: Code restructure failed: missing block: B:28:0x0059, code lost:
            if (r17.errors.get() == null) goto L_0x0065;
         */
        /* JADX WARN: Code restructure failed: missing block: B:29:0x005b, code lost:
            r3.onError(r17.errors.terminate());
         */
        /* JADX WARN: Code restructure failed: missing block: B:30:0x0065, code lost:
            r3.onComplete();
         */
        /* JADX WARN: Code restructure failed: missing block: B:31:0x0068, code lost:
            return;
         */
        /* JADX WARN: Code restructure failed: missing block: B:32:0x0069, code lost:
            if (r11 == false) goto L_0x006d;
         */
        /* JADX WARN: Code restructure failed: missing block: B:33:0x006b, code lost:
            r10 = r14;
         */
        /* JADX WARN: Code restructure failed: missing block: B:94:?, code lost:
            return;
         */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        void drainLoop() {
            /*
                Method dump skipped, instructions count: 224
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: p110z1.ParallelJoin.C4635d.drainLoop():void");
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: ParallelJoin.java */
    /* renamed from: z1.bob$a */
    /* loaded from: classes3.dex */
    public static final class C4632a<T> extends AtomicReference<dby> implements FlowableSubscriber<T> {
        private static final long serialVersionUID = 8410034718427740355L;
        final int limit;
        final AbstractC4634c<T> parent;
        final int prefetch;
        long produced;
        volatile SimplePlainQueue<T> queue;

        C4632a(AbstractC4634c<T> cVar, int i) {
            this.parent = cVar;
            this.prefetch = i;
            this.limit = i - (i >> 2);
        }

        @Override // p110z1.FlowableSubscriber, p110z1.Subscriber
        public void onSubscribe(dby dbyVar) {
            SubscriptionHelper.setOnce(this, dbyVar, this.prefetch);
        }

        @Override // p110z1.Subscriber
        public void onNext(T t) {
            this.parent.onNext(this, t);
        }

        @Override // p110z1.Subscriber
        public void onError(Throwable th) {
            this.parent.onError(th);
        }

        @Override // p110z1.Subscriber
        public void onComplete() {
            this.parent.onComplete();
        }

        public void requestOne() {
            long j = this.produced + 1;
            if (j == this.limit) {
                this.produced = 0L;
                get().request(j);
                return;
            }
            this.produced = j;
        }

        public void request(long j) {
            long j2 = this.produced + j;
            if (j2 >= this.limit) {
                this.produced = 0L;
                get().request(j2);
                return;
            }
            this.produced = j2;
        }

        public boolean cancel() {
            return SubscriptionHelper.cancel(this);
        }

        SimplePlainQueue<T> getQueue() {
            SimplePlainQueue<T> avvVar = this.queue;
            if (avvVar != null) {
                return avvVar;
            }
            SpscArrayQueue bqkVar = new SpscArrayQueue(this.prefetch);
            this.queue = bqkVar;
            return bqkVar;
        }
    }
}
