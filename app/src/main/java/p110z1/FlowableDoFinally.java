package p110z1;

/* renamed from: z1.bag */
/* loaded from: classes3.dex */
public final class FlowableDoFinally<T> extends AbstractFlowableWithUpstream<T, T> {

    /* renamed from: c */
    final Action f18034c;

    public FlowableDoFinally(Flowable<T> arvVar, Action augVar) {
        super(arvVar);
        this.f18034c = augVar;
    }

    @Override // p110z1.Flowable
    /* renamed from: d */
    protected void mo9054d(Subscriber<? super T> dbxVar) {
        if (dbxVar instanceof ConditionalSubscriber) {
            this.f17812b.m11187a((FlowableSubscriber) new C4054a((ConditionalSubscriber) dbxVar, this.f18034c));
        } else {
            this.f17812b.m11187a((FlowableSubscriber) new C4055b(dbxVar, this.f18034c));
        }
    }

    /* compiled from: FlowableDoFinally.java */
    /* renamed from: z1.bag$b */
    /* loaded from: classes3.dex */
    static final class C4055b<T> extends BasicIntQueueSubscription<T> implements FlowableSubscriber<T> {
        private static final long serialVersionUID = 4109457741734051389L;
        final Subscriber<? super T> downstream;
        final Action onFinally;

        /* renamed from: qs */
        QueueSubscription<T> f18036qs;
        boolean syncFused;
        dby upstream;

        C4055b(Subscriber<? super T> dbxVar, Action augVar) {
            this.downstream = dbxVar;
            this.onFinally = augVar;
        }

        @Override // p110z1.FlowableSubscriber, p110z1.Subscriber
        public void onSubscribe(dby dbyVar) {
            if (SubscriptionHelper.validate(this.upstream, dbyVar)) {
                this.upstream = dbyVar;
                if (dbyVar instanceof QueueSubscription) {
                    this.f18036qs = (QueueSubscription) dbyVar;
                }
                this.downstream.onSubscribe(this);
            }
        }

        @Override // p110z1.Subscriber
        public void onNext(T t) {
            this.downstream.onNext(t);
        }

        @Override // p110z1.Subscriber
        public void onError(Throwable th) {
            this.downstream.onError(th);
            runFinally();
        }

        @Override // p110z1.Subscriber
        public void onComplete() {
            this.downstream.onComplete();
            runFinally();
        }

        @Override // p110z1.dby
        public void cancel() {
            this.upstream.cancel();
            runFinally();
        }

        @Override // p110z1.dby
        public void request(long j) {
            this.upstream.request(j);
        }

        @Override // p110z1.QueueFuseable
        public int requestFusion(int i) {
            QueueSubscription<T> avtVar = this.f18036qs;
            if (avtVar == null || (i & 4) != 0) {
                return 0;
            }
            int requestFusion = avtVar.requestFusion(i);
            if (requestFusion != 0) {
                boolean z = true;
                if (requestFusion != 1) {
                    z = false;
                }
                this.syncFused = z;
            }
            return requestFusion;
        }

        @Override // p110z1.SimpleQueue
        public void clear() {
            this.f18036qs.clear();
        }

        @Override // p110z1.SimpleQueue
        public boolean isEmpty() {
            return this.f18036qs.isEmpty();
        }

        @Override // p110z1.SimpleQueue
        @atn
        public T poll() throws Exception {
            T poll = this.f18036qs.poll();
            if (poll == null && this.syncFused) {
                runFinally();
            }
            return poll;
        }

        void runFinally() {
            if (compareAndSet(0, 1)) {
                try {
                    this.onFinally.mo9442a();
                } catch (Throwable th) {
                    Exceptions.m9983b(th);
                    RxJavaPlugins.m9212a(th);
                }
            }
        }
    }

    /* compiled from: FlowableDoFinally.java */
    /* renamed from: z1.bag$a */
    /* loaded from: classes3.dex */
    static final class C4054a<T> extends BasicIntQueueSubscription<T> implements ConditionalSubscriber<T> {
        private static final long serialVersionUID = 4109457741734051389L;
        final ConditionalSubscriber<? super T> downstream;
        final Action onFinally;

        /* renamed from: qs */
        QueueSubscription<T> f18035qs;
        boolean syncFused;
        dby upstream;

        C4054a(ConditionalSubscriber<? super T> aviVar, Action augVar) {
            this.downstream = aviVar;
            this.onFinally = augVar;
        }

        @Override // p110z1.FlowableSubscriber, p110z1.Subscriber
        public void onSubscribe(dby dbyVar) {
            if (SubscriptionHelper.validate(this.upstream, dbyVar)) {
                this.upstream = dbyVar;
                if (dbyVar instanceof QueueSubscription) {
                    this.f18035qs = (QueueSubscription) dbyVar;
                }
                this.downstream.onSubscribe(this);
            }
        }

        @Override // p110z1.Subscriber
        public void onNext(T t) {
            this.downstream.onNext(t);
        }

        @Override // p110z1.ConditionalSubscriber
        public boolean tryOnNext(T t) {
            return this.downstream.tryOnNext(t);
        }

        @Override // p110z1.Subscriber
        public void onError(Throwable th) {
            this.downstream.onError(th);
            runFinally();
        }

        @Override // p110z1.Subscriber
        public void onComplete() {
            this.downstream.onComplete();
            runFinally();
        }

        @Override // p110z1.dby
        public void cancel() {
            this.upstream.cancel();
            runFinally();
        }

        @Override // p110z1.dby
        public void request(long j) {
            this.upstream.request(j);
        }

        @Override // p110z1.QueueFuseable
        public int requestFusion(int i) {
            QueueSubscription<T> avtVar = this.f18035qs;
            if (avtVar == null || (i & 4) != 0) {
                return 0;
            }
            int requestFusion = avtVar.requestFusion(i);
            if (requestFusion != 0) {
                boolean z = true;
                if (requestFusion != 1) {
                    z = false;
                }
                this.syncFused = z;
            }
            return requestFusion;
        }

        @Override // p110z1.SimpleQueue
        public void clear() {
            this.f18035qs.clear();
        }

        @Override // p110z1.SimpleQueue
        public boolean isEmpty() {
            return this.f18035qs.isEmpty();
        }

        @Override // p110z1.SimpleQueue
        @atn
        public T poll() throws Exception {
            T poll = this.f18035qs.poll();
            if (poll == null && this.syncFused) {
                runFinally();
            }
            return poll;
        }

        void runFinally() {
            if (compareAndSet(0, 1)) {
                try {
                    this.onFinally.mo9442a();
                } catch (Throwable th) {
                    Exceptions.m9983b(th);
                    RxJavaPlugins.m9212a(th);
                }
            }
        }
    }
}
