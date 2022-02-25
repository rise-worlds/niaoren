package p110z1;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;

/* renamed from: z1.bbw */
/* loaded from: classes3.dex */
public final class FlowableMergeWithMaybe<T> extends AbstractFlowableWithUpstream<T, T> {

    /* renamed from: c */
    final MaybeSource<? extends T> f18200c;

    public FlowableMergeWithMaybe(Flowable<T> arvVar, MaybeSource<? extends T> asiVar) {
        super(arvVar);
        this.f18200c = asiVar;
    }

    @Override // p110z1.Flowable
    /* renamed from: d */
    protected void mo9054d(Subscriber<? super T> dbxVar) {
        C4123a aVar = new C4123a(dbxVar);
        dbxVar.onSubscribe(aVar);
        this.f17812b.m11187a((FlowableSubscriber) aVar);
        this.f18200c.mo10646a(aVar.otherObserver);
    }

    /* compiled from: FlowableMergeWithMaybe.java */
    /* renamed from: z1.bbw$a */
    /* loaded from: classes3.dex */
    static final class C4123a<T> extends AtomicInteger implements FlowableSubscriber<T>, dby {
        static final int OTHER_STATE_CONSUMED_OR_EMPTY = 2;
        static final int OTHER_STATE_HAS_VALUE = 1;
        private static final long serialVersionUID = -4592979584110982903L;
        volatile boolean cancelled;
        int consumed;
        final Subscriber<? super T> downstream;
        long emitted;
        final int limit;
        volatile boolean mainDone;
        volatile int otherState;
        volatile SimplePlainQueue<T> queue;
        T singleItem;
        final AtomicReference<dby> mainSubscription = new AtomicReference<>();
        final C4124a<T> otherObserver = new C4124a<>(this);
        final AtomicThrowable error = new AtomicThrowable();
        final AtomicLong requested = new AtomicLong();
        final int prefetch = Flowable.m11274a();

        C4123a(Subscriber<? super T> dbxVar) {
            this.downstream = dbxVar;
            int i = this.prefetch;
            this.limit = i - (i >> 2);
        }

        @Override // p110z1.FlowableSubscriber, p110z1.Subscriber
        public void onSubscribe(dby dbyVar) {
            SubscriptionHelper.setOnce(this.mainSubscription, dbyVar, this.prefetch);
        }

        @Override // p110z1.Subscriber
        public void onNext(T t) {
            if (compareAndSet(0, 1)) {
                long j = this.emitted;
                if (this.requested.get() != j) {
                    SimplePlainQueue<T> avvVar = this.queue;
                    if (avvVar == null || avvVar.isEmpty()) {
                        this.emitted = j + 1;
                        this.downstream.onNext(t);
                        int i = this.consumed + 1;
                        if (i == this.limit) {
                            this.consumed = 0;
                            this.mainSubscription.get().request(i);
                        } else {
                            this.consumed = i;
                        }
                    } else {
                        avvVar.offer(t);
                    }
                } else {
                    getOrCreateQueue().offer(t);
                }
                if (decrementAndGet() == 0) {
                    return;
                }
            } else {
                getOrCreateQueue().offer(t);
                if (getAndIncrement() != 0) {
                    return;
                }
            }
            drainLoop();
        }

        @Override // p110z1.Subscriber
        public void onError(Throwable th) {
            if (this.error.addThrowable(th)) {
                SubscriptionHelper.cancel(this.mainSubscription);
                drain();
                return;
            }
            RxJavaPlugins.m9212a(th);
        }

        @Override // p110z1.Subscriber
        public void onComplete() {
            this.mainDone = true;
            drain();
        }

        @Override // p110z1.dby
        public void request(long j) {
            BackpressureHelper.m9449a(this.requested, j);
            drain();
        }

        @Override // p110z1.dby
        public void cancel() {
            this.cancelled = true;
            SubscriptionHelper.cancel(this.mainSubscription);
            DisposableHelper.dispose(this.otherObserver);
            if (getAndIncrement() == 0) {
                this.queue = null;
                this.singleItem = null;
            }
        }

        void otherSuccess(T t) {
            if (compareAndSet(0, 1)) {
                long j = this.emitted;
                if (this.requested.get() != j) {
                    this.emitted = j + 1;
                    this.downstream.onNext(t);
                    this.otherState = 2;
                } else {
                    this.singleItem = t;
                    this.otherState = 1;
                    if (decrementAndGet() == 0) {
                        return;
                    }
                }
            } else {
                this.singleItem = t;
                this.otherState = 1;
                if (getAndIncrement() != 0) {
                    return;
                }
            }
            drainLoop();
        }

        void otherError(Throwable th) {
            if (this.error.addThrowable(th)) {
                SubscriptionHelper.cancel(this.mainSubscription);
                drain();
                return;
            }
            RxJavaPlugins.m9212a(th);
        }

        void otherComplete() {
            this.otherState = 2;
            drain();
        }

        SimplePlainQueue<T> getOrCreateQueue() {
            SimplePlainQueue<T> avvVar = this.queue;
            if (avvVar != null) {
                return avvVar;
            }
            SpscArrayQueue bqkVar = new SpscArrayQueue(Flowable.m11274a());
            this.queue = bqkVar;
            return bqkVar;
        }

        void drain() {
            if (getAndIncrement() == 0) {
                drainLoop();
            }
        }

        void drainLoop() {
            int i;
            Subscriber<? super T> dbxVar = this.downstream;
            long j = this.emitted;
            int i2 = this.consumed;
            int i3 = this.limit;
            int i4 = 1;
            long j2 = j;
            int i5 = 1;
            while (true) {
                long j3 = this.requested.get();
                while (true) {
                    i = (j2 > j3 ? 1 : (j2 == j3 ? 0 : -1));
                    if (i == 0) {
                        break;
                    } else if (this.cancelled) {
                        this.singleItem = null;
                        this.queue = null;
                        return;
                    } else if (this.error.get() != null) {
                        this.singleItem = null;
                        this.queue = null;
                        dbxVar.onError(this.error.terminate());
                        return;
                    } else {
                        int i6 = this.otherState;
                        if (i6 == i4) {
                            this.singleItem = null;
                            this.otherState = 2;
                            dbxVar.onNext((T) this.singleItem);
                            j2++;
                        } else {
                            boolean z = this.mainDone;
                            SimplePlainQueue<T> avvVar = this.queue;
                            T poll = avvVar != null ? avvVar.poll() : (Object) null;
                            boolean z2 = poll == null;
                            if (z && z2 && i6 == 2) {
                                this.queue = null;
                                dbxVar.onComplete();
                                return;
                            } else if (z2) {
                                break;
                            } else {
                                dbxVar.onNext(poll);
                                j2++;
                                i2++;
                                if (i2 == i3) {
                                    this.mainSubscription.get().request(i3);
                                    i2 = 0;
                                }
                                i4 = 1;
                            }
                        }
                    }
                }
                if (i == 0) {
                    if (this.cancelled) {
                        this.singleItem = null;
                        this.queue = null;
                        return;
                    } else if (this.error.get() != null) {
                        this.singleItem = null;
                        this.queue = null;
                        dbxVar.onError(this.error.terminate());
                        return;
                    } else {
                        boolean z3 = this.mainDone;
                        SimplePlainQueue<T> avvVar2 = this.queue;
                        boolean z4 = avvVar2 == null || avvVar2.isEmpty();
                        if (z3 && z4 && this.otherState == 2) {
                            this.queue = null;
                            dbxVar.onComplete();
                            return;
                        }
                    }
                }
                this.emitted = j2;
                this.consumed = i2;
                i5 = addAndGet(-i5);
                if (i5 != 0) {
                    i4 = 1;
                } else {
                    return;
                }
            }
        }

        /* compiled from: FlowableMergeWithMaybe.java */
        /* renamed from: z1.bbw$a$a */
        /* loaded from: classes3.dex */
        static final class C4124a<T> extends AtomicReference<Disposable> implements MaybeObserver<T> {
            private static final long serialVersionUID = -2935427570954647017L;
            final C4123a<T> parent;

            C4124a(C4123a<T> aVar) {
                this.parent = aVar;
            }

            @Override // p110z1.MaybeObserver, p110z1.SingleObserver
            public void onSubscribe(Disposable atrVar) {
                DisposableHelper.setOnce(this, atrVar);
            }

            @Override // p110z1.MaybeObserver, p110z1.SingleObserver
            public void onSuccess(T t) {
                this.parent.otherSuccess(t);
            }

            @Override // p110z1.MaybeObserver, p110z1.SingleObserver
            public void onError(Throwable th) {
                this.parent.otherError(th);
            }

            @Override // p110z1.MaybeObserver
            public void onComplete() {
                this.parent.otherComplete();
            }
        }
    }
}
