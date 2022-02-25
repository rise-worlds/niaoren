package p110z1;

/* renamed from: z1.baf */
/* loaded from: classes3.dex */
public final class FlowableDoAfterNext<T> extends AbstractFlowableWithUpstream<T, T> {

    /* renamed from: c */
    final Consumer<? super T> f18031c;

    public FlowableDoAfterNext(Flowable<T> arvVar, Consumer<? super T> aumVar) {
        super(arvVar);
        this.f18031c = aumVar;
    }

    @Override // p110z1.Flowable
    /* renamed from: d */
    protected void mo9054d(Subscriber<? super T> dbxVar) {
        if (dbxVar instanceof ConditionalSubscriber) {
            this.f17812b.m11187a((FlowableSubscriber) new C4052a((ConditionalSubscriber) dbxVar, this.f18031c));
        } else {
            this.f17812b.m11187a((FlowableSubscriber) new C4053b(dbxVar, this.f18031c));
        }
    }

    /* compiled from: FlowableDoAfterNext.java */
    /* renamed from: z1.baf$b */
    /* loaded from: classes3.dex */
    static final class C4053b<T> extends BasicFuseableSubscriber<T, T> {

        /* renamed from: f */
        final Consumer<? super T> f18033f;

        C4053b(Subscriber<? super T> dbxVar, Consumer<? super T> aumVar) {
            super(dbxVar);
            this.f18033f = aumVar;
        }

        @Override // p110z1.Subscriber
        public void onNext(T t) {
            if (!this.f19994m) {
                this.f19991j.onNext(t);
                if (this.f19995n == 0) {
                    try {
                        this.f18033f.accept(t);
                    } catch (Throwable th) {
                        m9470a(th);
                    }
                }
            }
        }

        @Override // p110z1.QueueFuseable
        public int requestFusion(int i) {
            return m9471a(i);
        }

        @Override // p110z1.SimpleQueue
        @atn
        public T poll() throws Exception {
            T poll = this.f19993l.poll();
            if (poll != null) {
                this.f18033f.accept(poll);
            }
            return poll;
        }
    }

    /* compiled from: FlowableDoAfterNext.java */
    /* renamed from: z1.baf$a */
    /* loaded from: classes3.dex */
    static final class C4052a<T> extends BasicFuseableConditionalSubscriber<T, T> {

        /* renamed from: f */
        final Consumer<? super T> f18032f;

        C4052a(ConditionalSubscriber<? super T> aviVar, Consumer<? super T> aumVar) {
            super(aviVar);
            this.f18032f = aumVar;
        }

        @Override // p110z1.Subscriber
        public void onNext(T t) {
            this.f19986j.onNext(t);
            if (this.f19990n == 0) {
                try {
                    this.f18032f.accept(t);
                } catch (Throwable th) {
                    m9474a(th);
                }
            }
        }

        @Override // p110z1.ConditionalSubscriber
        public boolean tryOnNext(T t) {
            boolean tryOnNext = this.f19986j.tryOnNext(t);
            try {
                this.f18032f.accept(t);
            } catch (Throwable th) {
                m9474a(th);
            }
            return tryOnNext;
        }

        @Override // p110z1.QueueFuseable
        public int requestFusion(int i) {
            return m9475a(i);
        }

        @Override // p110z1.SimpleQueue
        @atn
        public T poll() throws Exception {
            T poll = this.f19988l.poll();
            if (poll != null) {
                this.f18032f.accept(poll);
            }
            return poll;
        }
    }
}
