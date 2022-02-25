package p110z1;

/* renamed from: z1.bao */
/* loaded from: classes3.dex */
public final class FlowableFilter<T> extends AbstractFlowableWithUpstream<T, T> {

    /* renamed from: c */
    final Predicate<? super T> f18078c;

    public FlowableFilter(Flowable<T> arvVar, Predicate<? super T> auxVar) {
        super(arvVar);
        this.f18078c = auxVar;
    }

    @Override // p110z1.Flowable
    /* renamed from: d */
    protected void mo9054d(Subscriber<? super T> dbxVar) {
        if (dbxVar instanceof ConditionalSubscriber) {
            this.f17812b.m11187a((FlowableSubscriber) new C4062a((ConditionalSubscriber) dbxVar, this.f18078c));
        } else {
            this.f17812b.m11187a((FlowableSubscriber) new C4063b(dbxVar, this.f18078c));
        }
    }

    /* compiled from: FlowableFilter.java */
    /* renamed from: z1.bao$b */
    /* loaded from: classes3.dex */
    static final class C4063b<T> extends BasicFuseableSubscriber<T, T> implements ConditionalSubscriber<T> {

        /* renamed from: f */
        final Predicate<? super T> f18080f;

        C4063b(Subscriber<? super T> dbxVar, Predicate<? super T> auxVar) {
            super(dbxVar);
            this.f18080f = auxVar;
        }

        @Override // p110z1.Subscriber
        public void onNext(T t) {
            if (!tryOnNext(t)) {
                this.f19992k.request(1L);
            }
        }

        @Override // p110z1.ConditionalSubscriber
        public boolean tryOnNext(T t) {
            if (this.f19994m) {
                return false;
            }
            if (this.f19995n != 0) {
                this.f19991j.onNext(null);
                return true;
            }
            try {
                boolean test = this.f18080f.test(t);
                if (test) {
                    this.f19991j.onNext(t);
                }
                return test;
            } catch (Throwable th) {
                m9470a(th);
                return true;
            }
        }

        @Override // p110z1.QueueFuseable
        public int requestFusion(int i) {
            return m9471a(i);
        }

        @Override // p110z1.SimpleQueue
        @atn
        public T poll() throws Exception {
            QueueSubscription<T> avtVar = this.f19993l;
            Predicate<? super T> auxVar = this.f18080f;
            while (true) {
                T poll = avtVar.poll();
                if (poll == null) {
                    return null;
                }
                if (auxVar.test(poll)) {
                    return poll;
                }
                if (this.f19995n == 2) {
                    avtVar.request(1L);
                }
            }
        }
    }

    /* compiled from: FlowableFilter.java */
    /* renamed from: z1.bao$a */
    /* loaded from: classes3.dex */
    static final class C4062a<T> extends BasicFuseableConditionalSubscriber<T, T> {

        /* renamed from: f */
        final Predicate<? super T> f18079f;

        C4062a(ConditionalSubscriber<? super T> aviVar, Predicate<? super T> auxVar) {
            super(aviVar);
            this.f18079f = auxVar;
        }

        @Override // p110z1.Subscriber
        public void onNext(T t) {
            if (!tryOnNext(t)) {
                this.f19987k.request(1L);
            }
        }

        @Override // p110z1.ConditionalSubscriber
        public boolean tryOnNext(T t) {
            if (this.f19989m) {
                return false;
            }
            if (this.f19990n != 0) {
                return this.f19986j.tryOnNext(null);
            }
            try {
                return this.f18079f.test(t) && this.f19986j.tryOnNext(t);
            } catch (Throwable th) {
                m9474a(th);
                return true;
            }
        }

        @Override // p110z1.QueueFuseable
        public int requestFusion(int i) {
            return m9475a(i);
        }

        @Override // p110z1.SimpleQueue
        @atn
        public T poll() throws Exception {
            QueueSubscription<T> avtVar = this.f19988l;
            Predicate<? super T> auxVar = this.f18079f;
            while (true) {
                T poll = avtVar.poll();
                if (poll == null) {
                    return null;
                }
                if (auxVar.test(poll)) {
                    return poll;
                }
                if (this.f19990n == 2) {
                    avtVar.request(1L);
                }
            }
        }
    }
}
