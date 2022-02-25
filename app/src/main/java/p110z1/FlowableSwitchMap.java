package p110z1;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;

/* renamed from: z1.bdp */
/* loaded from: classes3.dex */
public final class FlowableSwitchMap<T, R> extends AbstractFlowableWithUpstream<T, R> {

    /* renamed from: c */
    final Function<? super T, ? extends Publisher<? extends R>> f18372c;

    /* renamed from: d */
    final int f18373d;

    /* renamed from: e */
    final boolean f18374e;

    public FlowableSwitchMap(Flowable<T> arvVar, Function<? super T, ? extends Publisher<? extends R>> aunVar, int i, boolean z) {
        super(arvVar);
        this.f18372c = aunVar;
        this.f18373d = i;
        this.f18374e = z;
    }

    @Override // p110z1.Flowable
    /* renamed from: d */
    protected void mo9054d(Subscriber<? super R> dbxVar) {
        if (!FlowableScalarXMap.m9735a(this.f17812b, dbxVar, this.f18372c)) {
            this.f17812b.m11187a((FlowableSubscriber) new C4207b(dbxVar, this.f18372c, this.f18373d, this.f18374e));
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: FlowableSwitchMap.java */
    /* renamed from: z1.bdp$b */
    /* loaded from: classes3.dex */
    public static final class C4207b<T, R> extends AtomicInteger implements FlowableSubscriber<T>, dby {
        static final C4206a<Object, Object> CANCELLED = new C4206a<>(null, -1, 1);
        private static final long serialVersionUID = -3491074160481096299L;
        final int bufferSize;
        volatile boolean cancelled;
        final boolean delayErrors;
        volatile boolean done;
        final Subscriber<? super R> downstream;
        final Function<? super T, ? extends Publisher<? extends R>> mapper;
        volatile long unique;
        dby upstream;
        final AtomicReference<C4206a<T, R>> active = new AtomicReference<>();
        final AtomicLong requested = new AtomicLong();
        final AtomicThrowable error = new AtomicThrowable();

        static {
            CANCELLED.cancel();
        }

        C4207b(Subscriber<? super R> dbxVar, Function<? super T, ? extends Publisher<? extends R>> aunVar, int i, boolean z) {
            this.downstream = dbxVar;
            this.mapper = aunVar;
            this.bufferSize = i;
            this.delayErrors = z;
        }

        @Override // p110z1.FlowableSubscriber, p110z1.Subscriber
        public void onSubscribe(dby dbyVar) {
            if (SubscriptionHelper.validate(this.upstream, dbyVar)) {
                this.upstream = dbyVar;
                this.downstream.onSubscribe(this);
            }
        }

        @Override // p110z1.Subscriber
        public void onNext(T t) {
            C4206a<T, R> aVar;
            if (!this.done) {
                long j = this.unique + 1;
                this.unique = j;
                C4206a<T, R> aVar2 = this.active.get();
                if (aVar2 != null) {
                    aVar2.cancel();
                }
                try {
                    Publisher dbwVar = (Publisher) ObjectHelper.m9873a(this.mapper.apply(t), "The publisher returned is null");
                    C4206a<T, R> aVar3 = new C4206a<>(this, j, this.bufferSize);
                    do {
                        aVar = this.active.get();
                        if (aVar == CANCELLED) {
                            return;
                        }
                    } while (!this.active.compareAndSet(aVar, aVar3));
                    dbwVar.subscribe(aVar3);
                } catch (Throwable th) {
                    Exceptions.m9983b(th);
                    this.upstream.cancel();
                    onError(th);
                }
            }
        }

        @Override // p110z1.Subscriber
        public void onError(Throwable th) {
            if (this.done || !this.error.addThrowable(th)) {
                RxJavaPlugins.m9212a(th);
                return;
            }
            if (!this.delayErrors) {
                disposeInner();
            }
            this.done = true;
            drain();
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
                if (this.unique == 0) {
                    this.upstream.request(cjm.f20759b);
                } else {
                    drain();
                }
            }
        }

        @Override // p110z1.dby
        public void cancel() {
            if (!this.cancelled) {
                this.cancelled = true;
                this.upstream.cancel();
                disposeInner();
            }
        }

        /* JADX WARN: Multi-variable type inference failed */
        void disposeInner() {
            C4206a<Object, Object> aVar;
            C4206a<T, R> aVar2 = this.active.get();
            C4206a<Object, Object> aVar3 = CANCELLED;
            if (aVar2 != aVar3 && (aVar = (C4206a) this.active.getAndSet(aVar3)) != CANCELLED && aVar != null) {
                aVar.cancel();
            }
        }

        /* JADX WARN: Code restructure failed: missing block: B:111:0x000c, code lost:
            continue;
         */
        /* JADX WARN: Code restructure failed: missing block: B:78:0x011f, code lost:
            if (r12 == 0) goto L_0x013d;
         */
        /* JADX WARN: Code restructure failed: missing block: B:80:0x0123, code lost:
            if (r17.cancelled != false) goto L_0x013d;
         */
        /* JADX WARN: Code restructure failed: missing block: B:82:0x012c, code lost:
            if (r8 == p110z1.cjm.f20759b) goto L_0x0134;
         */
        /* JADX WARN: Code restructure failed: missing block: B:83:0x012e, code lost:
            r17.requested.addAndGet(-r12);
         */
        /* JADX WARN: Code restructure failed: missing block: B:84:0x0134, code lost:
            r6.get().request(r12);
         */
        /* JADX WARN: Code restructure failed: missing block: B:85:0x013d, code lost:
            if (r14 == false) goto L_0x0141;
         */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        void drain() {
            /*
                Method dump skipped, instructions count: 329
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: p110z1.FlowableSwitchMap.C4207b.drain():void");
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: FlowableSwitchMap.java */
    /* renamed from: z1.bdp$a */
    /* loaded from: classes3.dex */
    public static final class C4206a<T, R> extends AtomicReference<dby> implements FlowableSubscriber<R> {
        private static final long serialVersionUID = 3837284832786408377L;
        final int bufferSize;
        volatile boolean done;
        int fusionMode;
        final long index;
        final C4207b<T, R> parent;
        volatile SimpleQueue<R> queue;

        C4206a(C4207b<T, R> bVar, long j, int i) {
            this.parent = bVar;
            this.index = j;
            this.bufferSize = i;
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
                        dbyVar.request(this.bufferSize);
                        return;
                    }
                }
                this.queue = new SpscArrayQueue(this.bufferSize);
                dbyVar.request(this.bufferSize);
            }
        }

        @Override // p110z1.Subscriber
        public void onNext(R r) {
            C4207b<T, R> bVar = this.parent;
            if (this.index != bVar.unique) {
                return;
            }
            if (this.fusionMode != 0 || this.queue.offer(r)) {
                bVar.drain();
            } else {
                onError(new MissingBackpressureException("Queue full?!"));
            }
        }

        @Override // p110z1.Subscriber
        public void onError(Throwable th) {
            C4207b<T, R> bVar = this.parent;
            if (this.index != bVar.unique || !bVar.error.addThrowable(th)) {
                RxJavaPlugins.m9212a(th);
                return;
            }
            if (!bVar.delayErrors) {
                bVar.upstream.cancel();
            }
            this.done = true;
            bVar.drain();
        }

        @Override // p110z1.Subscriber
        public void onComplete() {
            C4207b<T, R> bVar = this.parent;
            if (this.index == bVar.unique) {
                this.done = true;
                bVar.drain();
            }
        }

        public void cancel() {
            SubscriptionHelper.cancel(this);
        }
    }
}
