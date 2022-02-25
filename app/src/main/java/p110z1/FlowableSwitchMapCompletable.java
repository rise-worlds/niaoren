package p110z1;

import java.util.concurrent.atomic.AtomicReference;

/* renamed from: z1.bhr */
/* loaded from: classes3.dex */
public final class FlowableSwitchMapCompletable<T> extends Completable {

    /* renamed from: a */
    final Flowable<T> f18751a;

    /* renamed from: b */
    final Function<? super T, ? extends CompletableSource> f18752b;

    /* renamed from: c */
    final boolean f18753c;

    public FlowableSwitchMapCompletable(Flowable<T> arvVar, Function<? super T, ? extends CompletableSource> aunVar, boolean z) {
        this.f18751a = arvVar;
        this.f18752b = aunVar;
        this.f18753c = z;
    }

    @Override // p110z1.Completable
    /* renamed from: b */
    protected void mo9001b(CompletableObserver arpVar) {
        this.f18751a.m11187a((FlowableSubscriber) new C4355a(arpVar, this.f18752b, this.f18753c));
    }

    /* compiled from: FlowableSwitchMapCompletable.java */
    /* renamed from: z1.bhr$a */
    /* loaded from: classes3.dex */
    static final class C4355a<T> implements FlowableSubscriber<T>, Disposable {

        /* renamed from: f */
        static final C4356a f18754f = new C4356a(null);

        /* renamed from: a */
        final CompletableObserver f18755a;

        /* renamed from: b */
        final Function<? super T, ? extends CompletableSource> f18756b;

        /* renamed from: c */
        final boolean f18757c;

        /* renamed from: d */
        final AtomicThrowable f18758d = new AtomicThrowable();

        /* renamed from: e */
        final AtomicReference<C4356a> f18759e = new AtomicReference<>();

        /* renamed from: g */
        volatile boolean f18760g;

        /* renamed from: h */
        dby f18761h;

        C4355a(CompletableObserver arpVar, Function<? super T, ? extends CompletableSource> aunVar, boolean z) {
            this.f18755a = arpVar;
            this.f18756b = aunVar;
            this.f18757c = z;
        }

        @Override // p110z1.FlowableSubscriber, p110z1.Subscriber
        public void onSubscribe(dby dbyVar) {
            if (SubscriptionHelper.validate(this.f18761h, dbyVar)) {
                this.f18761h = dbyVar;
                this.f18755a.onSubscribe(this);
                dbyVar.request(cjm.f20759b);
            }
        }

        @Override // p110z1.Subscriber
        public void onNext(T t) {
            C4356a aVar;
            try {
                CompletableSource arsVar = (CompletableSource) ObjectHelper.m9873a(this.f18756b.apply(t), "The mapper returned a null CompletableSource");
                C4356a aVar2 = new C4356a(this);
                do {
                    aVar = this.f18759e.get();
                    if (aVar == f18754f) {
                        return;
                    }
                } while (!this.f18759e.compareAndSet(aVar, aVar2));
                if (aVar != null) {
                    aVar.dispose();
                }
                arsVar.mo11309a(aVar2);
            } catch (Throwable th) {
                Exceptions.m9983b(th);
                this.f18761h.cancel();
                onError(th);
            }
        }

        @Override // p110z1.Subscriber
        public void onError(Throwable th) {
            if (!this.f18758d.addThrowable(th)) {
                RxJavaPlugins.m9212a(th);
            } else if (this.f18757c) {
                onComplete();
            } else {
                m9686a();
                Throwable terminate = this.f18758d.terminate();
                if (terminate != ExceptionHelper.f20064a) {
                    this.f18755a.onError(terminate);
                }
            }
        }

        @Override // p110z1.Subscriber
        public void onComplete() {
            this.f18760g = true;
            if (this.f18759e.get() == null) {
                Throwable terminate = this.f18758d.terminate();
                if (terminate == null) {
                    this.f18755a.onComplete();
                } else {
                    this.f18755a.onError(terminate);
                }
            }
        }

        /* renamed from: a */
        void m9686a() {
            C4356a andSet = this.f18759e.getAndSet(f18754f);
            if (andSet != null && andSet != f18754f) {
                andSet.dispose();
            }
        }

        @Override // p110z1.Disposable
        public void dispose() {
            this.f18761h.cancel();
            m9686a();
        }

        @Override // p110z1.Disposable
        public boolean isDisposed() {
            return this.f18759e.get() == f18754f;
        }

        /* renamed from: a */
        void m9684a(C4356a aVar, Throwable th) {
            if (!this.f18759e.compareAndSet(aVar, null) || !this.f18758d.addThrowable(th)) {
                RxJavaPlugins.m9212a(th);
            } else if (!this.f18757c) {
                dispose();
                Throwable terminate = this.f18758d.terminate();
                if (terminate != ExceptionHelper.f20064a) {
                    this.f18755a.onError(terminate);
                }
            } else if (this.f18760g) {
                this.f18755a.onError(this.f18758d.terminate());
            }
        }

        /* renamed from: a */
        void m9685a(C4356a aVar) {
            if (this.f18759e.compareAndSet(aVar, null) && this.f18760g) {
                Throwable terminate = this.f18758d.terminate();
                if (terminate == null) {
                    this.f18755a.onComplete();
                } else {
                    this.f18755a.onError(terminate);
                }
            }
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* compiled from: FlowableSwitchMapCompletable.java */
        /* renamed from: z1.bhr$a$a */
        /* loaded from: classes3.dex */
        public static final class C4356a extends AtomicReference<Disposable> implements CompletableObserver {
            private static final long serialVersionUID = -8003404460084760287L;
            final C4355a<?> parent;

            C4356a(C4355a<?> aVar) {
                this.parent = aVar;
            }

            @Override // p110z1.CompletableObserver, p110z1.MaybeObserver, p110z1.SingleObserver
            public void onSubscribe(Disposable atrVar) {
                DisposableHelper.setOnce(this, atrVar);
            }

            @Override // p110z1.CompletableObserver, p110z1.MaybeObserver, p110z1.SingleObserver
            public void onError(Throwable th) {
                this.parent.m9684a(this, th);
            }

            @Override // p110z1.CompletableObserver, p110z1.MaybeObserver
            public void onComplete() {
                this.parent.m9685a(this);
            }

            void dispose() {
                DisposableHelper.dispose(this);
            }
        }
    }
}
