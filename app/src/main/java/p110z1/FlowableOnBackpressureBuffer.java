package p110z1;

import java.util.concurrent.atomic.AtomicLong;

/* renamed from: z1.bca */
/* loaded from: classes3.dex */
public final class FlowableOnBackpressureBuffer<T> extends AbstractFlowableWithUpstream<T, T> {

    /* renamed from: c */
    final int f18207c;

    /* renamed from: d */
    final boolean f18208d;

    /* renamed from: e */
    final boolean f18209e;

    /* renamed from: f */
    final Action f18210f;

    public FlowableOnBackpressureBuffer(Flowable<T> arvVar, int i, boolean z, boolean z2, Action augVar) {
        super(arvVar);
        this.f18207c = i;
        this.f18208d = z;
        this.f18209e = z2;
        this.f18210f = augVar;
    }

    @Override // p110z1.Flowable
    /* renamed from: d */
    protected void mo9054d(Subscriber<? super T> dbxVar) {
        this.f17812b.m11187a((FlowableSubscriber) new C4131a(dbxVar, this.f18207c, this.f18208d, this.f18209e, this.f18210f));
    }

    /* compiled from: FlowableOnBackpressureBuffer.java */
    /* renamed from: z1.bca$a */
    /* loaded from: classes3.dex */
    static final class C4131a<T> extends BasicIntQueueSubscription<T> implements FlowableSubscriber<T> {
        private static final long serialVersionUID = -2514538129242366402L;
        volatile boolean cancelled;
        final boolean delayError;
        volatile boolean done;
        final Subscriber<? super T> downstream;
        Throwable error;
        final Action onOverflow;
        boolean outputFused;
        final SimplePlainQueue<T> queue;
        final AtomicLong requested = new AtomicLong();
        dby upstream;

        C4131a(Subscriber<? super T> dbxVar, int i, boolean z, boolean z2, Action augVar) {
            SimplePlainQueue<T> avvVar;
            this.downstream = dbxVar;
            this.onOverflow = augVar;
            this.delayError = z2;
            if (z) {
                avvVar = new SpscLinkedArrayQueue<>(i);
            } else {
                avvVar = new SpscArrayQueue<>(i);
            }
            this.queue = avvVar;
        }

        @Override // p110z1.FlowableSubscriber, p110z1.Subscriber
        public void onSubscribe(dby dbyVar) {
            if (SubscriptionHelper.validate(this.upstream, dbyVar)) {
                this.upstream = dbyVar;
                this.downstream.onSubscribe(this);
                dbyVar.request(cjm.f20759b);
            }
        }

        @Override // p110z1.Subscriber
        public void onNext(T t) {
            if (!this.queue.offer(t)) {
                this.upstream.cancel();
                MissingBackpressureException auaVar = new MissingBackpressureException("Buffer is full");
                try {
                    this.onOverflow.mo9442a();
                } catch (Throwable th) {
                    Exceptions.m9983b(th);
                    auaVar.initCause(th);
                }
                onError(auaVar);
            } else if (this.outputFused) {
                this.downstream.onNext(null);
            } else {
                drain();
            }
        }

        @Override // p110z1.Subscriber
        public void onError(Throwable th) {
            this.error = th;
            this.done = true;
            if (this.outputFused) {
                this.downstream.onError(th);
            } else {
                drain();
            }
        }

        @Override // p110z1.Subscriber
        public void onComplete() {
            this.done = true;
            if (this.outputFused) {
                this.downstream.onComplete();
            } else {
                drain();
            }
        }

        @Override // p110z1.dby
        public void request(long j) {
            if (!this.outputFused && SubscriptionHelper.validate(j)) {
                BackpressureHelper.m9449a(this.requested, j);
                drain();
            }
        }

        @Override // p110z1.dby
        public void cancel() {
            if (!this.cancelled) {
                this.cancelled = true;
                this.upstream.cancel();
                if (getAndIncrement() == 0) {
                    this.queue.clear();
                }
            }
        }

        void drain() {
            int i;
            if (getAndIncrement() == 0) {
                SimplePlainQueue<T> avvVar = this.queue;
                Subscriber<? super T> dbxVar = this.downstream;
                int i2 = 1;
                while (!checkTerminated(this.done, avvVar.isEmpty(), dbxVar)) {
                    long j = this.requested.get();
                    long j2 = 0;
                    while (true) {
                        i = (j2 > j ? 1 : (j2 == j ? 0 : -1));
                        if (i == 0) {
                            break;
                        }
                        boolean z = this.done;
                        Object obj = (T) avvVar.poll();
                        boolean z2 = obj == null;
                        if (!checkTerminated(z, z2, dbxVar)) {
                            if (z2) {
                                break;
                            }
                            dbxVar.onNext(obj);
                            j2++;
                        } else {
                            return;
                        }
                    }
                    if (i != 0 || !checkTerminated(this.done, avvVar.isEmpty(), dbxVar)) {
                        if (!(j2 == 0 || j == cjm.f20759b)) {
                            this.requested.addAndGet(-j2);
                        }
                        i2 = addAndGet(-i2);
                        if (i2 == 0) {
                            return;
                        }
                    } else {
                        return;
                    }
                }
            }
        }

        boolean checkTerminated(boolean z, boolean z2, Subscriber<? super T> dbxVar) {
            if (this.cancelled) {
                this.queue.clear();
                return true;
            } else if (!z) {
                return false;
            } else {
                if (!this.delayError) {
                    Throwable th = this.error;
                    if (th != null) {
                        this.queue.clear();
                        dbxVar.onError(th);
                        return true;
                    } else if (!z2) {
                        return false;
                    } else {
                        dbxVar.onComplete();
                        return true;
                    }
                } else if (!z2) {
                    return false;
                } else {
                    Throwable th2 = this.error;
                    if (th2 != null) {
                        dbxVar.onError(th2);
                    } else {
                        dbxVar.onComplete();
                    }
                    return true;
                }
            }
        }

        @Override // p110z1.QueueFuseable
        public int requestFusion(int i) {
            if ((i & 2) == 0) {
                return 0;
            }
            this.outputFused = true;
            return 2;
        }

        @Override // p110z1.SimpleQueue
        @atn
        public T poll() throws Exception {
            return this.queue.poll();
        }

        @Override // p110z1.SimpleQueue
        public void clear() {
            this.queue.clear();
        }

        @Override // p110z1.SimpleQueue
        public boolean isEmpty() {
            return this.queue.isEmpty();
        }
    }
}
