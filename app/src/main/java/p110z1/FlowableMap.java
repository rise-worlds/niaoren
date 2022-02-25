package p110z1;

/* renamed from: z1.bbr */
/* loaded from: classes3.dex */
public final class FlowableMap<T, U> extends AbstractFlowableWithUpstream<T, U> {

    /* renamed from: c */
    final Function<? super T, ? extends U> f18191c;

    public FlowableMap(Flowable<T> arvVar, Function<? super T, ? extends U> aunVar) {
        super(arvVar);
        this.f18191c = aunVar;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // p110z1.Flowable
    /* renamed from: d */
    public void mo9054d(Subscriber<? super U> dbxVar) {
        if (dbxVar instanceof ConditionalSubscriber) {
            this.f17812b.m11187a((FlowableSubscriber) new C4117a((ConditionalSubscriber) dbxVar, this.f18191c));
        } else {
            this.f17812b.m11187a((FlowableSubscriber) new C4118b(dbxVar, this.f18191c));
        }
    }

    /* compiled from: FlowableMap.java */
    /* renamed from: z1.bbr$b */
    /* loaded from: classes3.dex */
    static final class C4118b<T, U> extends BasicFuseableSubscriber<T, U> {

        /* renamed from: f */
        final Function<? super T, ? extends U> f18193f;

        /* JADX INFO: Access modifiers changed from: package-private */
        public C4118b(Subscriber<? super U> dbxVar, Function<? super T, ? extends U> aunVar) {
            super(dbxVar);
            this.f18193f = aunVar;
        }

        @Override // p110z1.Subscriber
        public void onNext(T t) {
            if (!this.f19994m) {
                if (this.f19995n != 0) {
                    this.f19991j.onNext(null);
                    return;
                }
                try {
                    this.f19991j.onNext(ObjectHelper.m9873a(this.f18193f.apply(t), "The mapper function returned a null value."));
                } catch (Throwable th) {
                    m9470a(th);
                }
            }
        }

        @Override // p110z1.QueueFuseable
        public int requestFusion(int i) {
            return m9471a(i);
        }

        @Override // p110z1.SimpleQueue
        @atn
        public U poll() throws Exception {
            T poll = this.f19993l.poll();
            if (poll != null) {
                return (U) ObjectHelper.m9873a(this.f18193f.apply(poll), "The mapper function returned a null value.");
            }
            return null;
        }
    }

    /* compiled from: FlowableMap.java */
    /* renamed from: z1.bbr$a */
    /* loaded from: classes3.dex */
    static final class C4117a<T, U> extends BasicFuseableConditionalSubscriber<T, U> {

        /* renamed from: f */
        final Function<? super T, ? extends U> f18192f;

        C4117a(ConditionalSubscriber<? super U> aviVar, Function<? super T, ? extends U> aunVar) {
            super(aviVar);
            this.f18192f = aunVar;
        }

        @Override // p110z1.Subscriber
        public void onNext(T t) {
            if (!this.f19989m) {
                if (this.f19990n != 0) {
                    this.f19986j.onNext(null);
                    return;
                }
                try {
                    this.f19986j.onNext(ObjectHelper.m9873a(this.f18192f.apply(t), "The mapper function returned a null value."));
                } catch (Throwable th) {
                    m9474a(th);
                }
            }
        }

        @Override // p110z1.ConditionalSubscriber
        public boolean tryOnNext(T t) {
            if (this.f19989m) {
                return false;
            }
            try {
                return this.f19986j.tryOnNext(ObjectHelper.m9873a(this.f18192f.apply(t), "The mapper function returned a null value."));
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
        public U poll() throws Exception {
            T poll = this.f19988l.poll();
            if (poll != null) {
                return (U) ObjectHelper.m9873a(this.f18192f.apply(poll), "The mapper function returned a null value.");
            }
            return null;
        }
    }
}
