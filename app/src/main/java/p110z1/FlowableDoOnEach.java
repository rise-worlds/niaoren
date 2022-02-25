package p110z1;

/* renamed from: z1.bah */
/* loaded from: classes3.dex */
public final class FlowableDoOnEach<T> extends AbstractFlowableWithUpstream<T, T> {

    /* renamed from: c */
    final Consumer<? super T> f18037c;

    /* renamed from: d */
    final Consumer<? super Throwable> f18038d;

    /* renamed from: e */
    final Action f18039e;

    /* renamed from: f */
    final Action f18040f;

    public FlowableDoOnEach(Flowable<T> arvVar, Consumer<? super T> aumVar, Consumer<? super Throwable> aumVar2, Action augVar, Action augVar2) {
        super(arvVar);
        this.f18037c = aumVar;
        this.f18038d = aumVar2;
        this.f18039e = augVar;
        this.f18040f = augVar2;
    }

    @Override // p110z1.Flowable
    /* renamed from: d */
    protected void mo9054d(Subscriber<? super T> dbxVar) {
        if (dbxVar instanceof ConditionalSubscriber) {
            this.f17812b.m11187a((FlowableSubscriber) new C4056a((ConditionalSubscriber) dbxVar, this.f18037c, this.f18038d, this.f18039e, this.f18040f));
        } else {
            this.f17812b.m11187a((FlowableSubscriber) new C4057b(dbxVar, this.f18037c, this.f18038d, this.f18039e, this.f18040f));
        }
    }

    /* compiled from: FlowableDoOnEach.java */
    /* renamed from: z1.bah$b */
    /* loaded from: classes3.dex */
    static final class C4057b<T> extends BasicFuseableSubscriber<T, T> {

        /* renamed from: f */
        final Consumer<? super T> f18045f;

        /* renamed from: g */
        final Consumer<? super Throwable> f18046g;

        /* renamed from: h */
        final Action f18047h;

        /* renamed from: i */
        final Action f18048i;

        C4057b(Subscriber<? super T> dbxVar, Consumer<? super T> aumVar, Consumer<? super Throwable> aumVar2, Action augVar, Action augVar2) {
            super(dbxVar);
            this.f18045f = aumVar;
            this.f18046g = aumVar2;
            this.f18047h = augVar;
            this.f18048i = augVar2;
        }

        @Override // p110z1.Subscriber
        public void onNext(T t) {
            if (!this.f19994m) {
                if (this.f19995n != 0) {
                    this.f19991j.onNext(null);
                    return;
                }
                try {
                    this.f18045f.accept(t);
                    this.f19991j.onNext(t);
                } catch (Throwable th) {
                    m9470a(th);
                }
            }
        }

        @Override // p110z1.BasicFuseableSubscriber, p110z1.Subscriber
        public void onError(Throwable th) {
            if (this.f19994m) {
                RxJavaPlugins.m9212a(th);
                return;
            }
            boolean z = true;
            this.f19994m = true;
            try {
                this.f18046g.accept(th);
            } catch (Throwable th2) {
                Exceptions.m9983b(th2);
                this.f19991j.onError(new CompositeException(th, th2));
                z = false;
            }
            if (z) {
                this.f19991j.onError(th);
            }
            try {
                this.f18048i.mo9442a();
            } catch (Throwable th3) {
                Exceptions.m9983b(th3);
                RxJavaPlugins.m9212a(th3);
            }
        }

        @Override // p110z1.BasicFuseableSubscriber, p110z1.Subscriber
        public void onComplete() {
            if (!this.f19994m) {
                try {
                    this.f18047h.mo9442a();
                    this.f19994m = true;
                    this.f19991j.onComplete();
                    try {
                        this.f18048i.mo9442a();
                    } catch (Throwable th) {
                        Exceptions.m9983b(th);
                        RxJavaPlugins.m9212a(th);
                    }
                } catch (Throwable th2) {
                    m9470a(th2);
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
            try {
                T poll = this.f19993l.poll();
                if (poll != null) {
                    try {
                        this.f18045f.accept(poll);
                    } catch (Throwable th) {
                        try {
                            Exceptions.m9983b(th);
                            this.f18046g.accept(th);
                            throw ExceptionHelper.m9428c(th);
                        } finally {
                            this.f18048i.mo9442a();
                        }
                    }
                } else if (this.f19995n == 1) {
                    this.f18047h.mo9442a();
                }
                return poll;
            } catch (Throwable th2) {
                Exceptions.m9983b(th2);
                try {
                    this.f18046g.accept(th2);
                    throw ExceptionHelper.m9428c(th2);
                } catch (Throwable th3) {
                    throw new CompositeException(th2, th3);
                }
            }
        }
    }

    /* compiled from: FlowableDoOnEach.java */
    /* renamed from: z1.bah$a */
    /* loaded from: classes3.dex */
    static final class C4056a<T> extends BasicFuseableConditionalSubscriber<T, T> {

        /* renamed from: f */
        final Consumer<? super T> f18041f;

        /* renamed from: g */
        final Consumer<? super Throwable> f18042g;

        /* renamed from: h */
        final Action f18043h;

        /* renamed from: i */
        final Action f18044i;

        C4056a(ConditionalSubscriber<? super T> aviVar, Consumer<? super T> aumVar, Consumer<? super Throwable> aumVar2, Action augVar, Action augVar2) {
            super(aviVar);
            this.f18041f = aumVar;
            this.f18042g = aumVar2;
            this.f18043h = augVar;
            this.f18044i = augVar2;
        }

        @Override // p110z1.Subscriber
        public void onNext(T t) {
            if (!this.f19989m) {
                if (this.f19990n != 0) {
                    this.f19986j.onNext(null);
                    return;
                }
                try {
                    this.f18041f.accept(t);
                    this.f19986j.onNext(t);
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
                this.f18041f.accept(t);
                return this.f19986j.tryOnNext(t);
            } catch (Throwable th) {
                m9474a(th);
                return false;
            }
        }

        @Override // p110z1.BasicFuseableConditionalSubscriber, p110z1.Subscriber
        public void onError(Throwable th) {
            if (this.f19989m) {
                RxJavaPlugins.m9212a(th);
                return;
            }
            boolean z = true;
            this.f19989m = true;
            try {
                this.f18042g.accept(th);
            } catch (Throwable th2) {
                Exceptions.m9983b(th2);
                this.f19986j.onError(new CompositeException(th, th2));
                z = false;
            }
            if (z) {
                this.f19986j.onError(th);
            }
            try {
                this.f18044i.mo9442a();
            } catch (Throwable th3) {
                Exceptions.m9983b(th3);
                RxJavaPlugins.m9212a(th3);
            }
        }

        @Override // p110z1.BasicFuseableConditionalSubscriber, p110z1.Subscriber
        public void onComplete() {
            if (!this.f19989m) {
                try {
                    this.f18043h.mo9442a();
                    this.f19989m = true;
                    this.f19986j.onComplete();
                    try {
                        this.f18044i.mo9442a();
                    } catch (Throwable th) {
                        Exceptions.m9983b(th);
                        RxJavaPlugins.m9212a(th);
                    }
                } catch (Throwable th2) {
                    m9474a(th2);
                }
            }
        }

        @Override // p110z1.QueueFuseable
        public int requestFusion(int i) {
            return m9475a(i);
        }

        @Override // p110z1.SimpleQueue
        @atn
        public T poll() throws Exception {
            try {
                T poll = this.f19988l.poll();
                if (poll != null) {
                    try {
                        this.f18041f.accept(poll);
                    } catch (Throwable th) {
                        try {
                            Exceptions.m9983b(th);
                            this.f18042g.accept(th);
                            throw ExceptionHelper.m9428c(th);
                        } finally {
                            this.f18044i.mo9442a();
                        }
                    }
                } else if (this.f19990n == 1) {
                    this.f18043h.mo9442a();
                }
                return poll;
            } catch (Throwable th2) {
                Exceptions.m9983b(th2);
                try {
                    this.f18042g.accept(th2);
                    throw ExceptionHelper.m9428c(th2);
                } catch (Throwable th3) {
                    throw new CompositeException(th2, th3);
                }
            }
        }
    }
}
