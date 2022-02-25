package p110z1;

/* renamed from: z1.bae */
/* loaded from: classes3.dex */
public final class FlowableDistinctUntilChanged<T, K> extends AbstractFlowableWithUpstream<T, T> {

    /* renamed from: c */
    final Function<? super T, K> f18021c;

    /* renamed from: d */
    final BiPredicate<? super K, ? super K> f18022d;

    public FlowableDistinctUntilChanged(Flowable<T> arvVar, Function<? super T, K> aunVar, BiPredicate<? super K, ? super K> aujVar) {
        super(arvVar);
        this.f18021c = aunVar;
        this.f18022d = aujVar;
    }

    @Override // p110z1.Flowable
    /* renamed from: d */
    protected void mo9054d(Subscriber<? super T> dbxVar) {
        if (dbxVar instanceof ConditionalSubscriber) {
            this.f17812b.m11187a((FlowableSubscriber) new C4050a((ConditionalSubscriber) dbxVar, this.f18021c, this.f18022d));
        } else {
            this.f17812b.m11187a((FlowableSubscriber) new C4051b(dbxVar, this.f18021c, this.f18022d));
        }
    }

    /* compiled from: FlowableDistinctUntilChanged.java */
    /* renamed from: z1.bae$b */
    /* loaded from: classes3.dex */
    static final class C4051b<T, K> extends BasicFuseableSubscriber<T, T> implements ConditionalSubscriber<T> {

        /* renamed from: f */
        final Function<? super T, K> f18027f;

        /* renamed from: g */
        final BiPredicate<? super K, ? super K> f18028g;

        /* renamed from: h */
        K f18029h;

        /* renamed from: i */
        boolean f18030i;

        C4051b(Subscriber<? super T> dbxVar, Function<? super T, K> aunVar, BiPredicate<? super K, ? super K> aujVar) {
            super(dbxVar);
            this.f18027f = aunVar;
            this.f18028g = aujVar;
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
                this.f19991j.onNext(t);
                return true;
            }
            try {
                K apply = this.f18027f.apply(t);
                if (this.f18030i) {
                    boolean a = this.f18028g.mo9871a((K) this.f18029h, apply);
                    this.f18029h = apply;
                    if (a) {
                        return false;
                    }
                } else {
                    this.f18030i = true;
                    this.f18029h = apply;
                }
                this.f19991j.onNext(t);
                return true;
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
            while (true) {
                T poll = this.f19993l.poll();
                if (poll == null) {
                    return null;
                }
                K apply = this.f18027f.apply(poll);
                if (!this.f18030i) {
                    this.f18030i = true;
                    this.f18029h = apply;
                    return poll;
                } else if (!this.f18028g.mo9871a((K) this.f18029h, apply)) {
                    this.f18029h = apply;
                    return poll;
                } else {
                    this.f18029h = apply;
                    if (this.f19995n != 1) {
                        this.f19992k.request(1L);
                    }
                }
            }
        }
    }

    /* compiled from: FlowableDistinctUntilChanged.java */
    /* renamed from: z1.bae$a */
    /* loaded from: classes3.dex */
    static final class C4050a<T, K> extends BasicFuseableConditionalSubscriber<T, T> {

        /* renamed from: f */
        final Function<? super T, K> f18023f;

        /* renamed from: g */
        final BiPredicate<? super K, ? super K> f18024g;

        /* renamed from: h */
        K f18025h;

        /* renamed from: i */
        boolean f18026i;

        C4050a(ConditionalSubscriber<? super T> aviVar, Function<? super T, K> aunVar, BiPredicate<? super K, ? super K> aujVar) {
            super(aviVar);
            this.f18023f = aunVar;
            this.f18024g = aujVar;
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
                return this.f19986j.tryOnNext(t);
            }
            try {
                K apply = this.f18023f.apply(t);
                if (this.f18026i) {
                    boolean a = this.f18024g.mo9871a((K) this.f18025h, apply);
                    this.f18025h = apply;
                    if (a) {
                        return false;
                    }
                } else {
                    this.f18026i = true;
                    this.f18025h = apply;
                }
                this.f19986j.onNext(t);
                return true;
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
            while (true) {
                T poll = this.f19988l.poll();
                if (poll == null) {
                    return null;
                }
                K apply = this.f18023f.apply(poll);
                if (!this.f18026i) {
                    this.f18026i = true;
                    this.f18025h = apply;
                    return poll;
                } else if (!this.f18024g.mo9871a((K) this.f18025h, apply)) {
                    this.f18025h = apply;
                    return poll;
                } else {
                    this.f18025h = apply;
                    if (this.f19990n != 1) {
                        this.f19987k.request(1L);
                    }
                }
            }
        }
    }
}
