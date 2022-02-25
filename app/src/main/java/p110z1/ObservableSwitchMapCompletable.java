package p110z1;

import java.util.concurrent.atomic.AtomicReference;

/* renamed from: z1.bia */
/* loaded from: classes3.dex */
public final class ObservableSwitchMapCompletable<T> extends Completable {

    /* renamed from: a */
    final Observable<T> f18788a;

    /* renamed from: b */
    final Function<? super T, ? extends CompletableSource> f18789b;

    /* renamed from: c */
    final boolean f18790c;

    public ObservableSwitchMapCompletable(Observable<T> aslVar, Function<? super T, ? extends CompletableSource> aunVar, boolean z) {
        this.f18788a = aslVar;
        this.f18789b = aunVar;
        this.f18790c = z;
    }

    @Override // p110z1.Completable
    /* renamed from: b */
    protected void mo9001b(CompletableObserver arpVar) {
        if (!ScalarXMapZHelper.m9672a(this.f18788a, this.f18789b, arpVar)) {
            this.f18788a.subscribe(new C4370a(arpVar, this.f18789b, this.f18790c));
        }
    }

    /* compiled from: ObservableSwitchMapCompletable.java */
    /* renamed from: z1.bia$a */
    /* loaded from: classes3.dex */
    static final class C4370a<T> implements Observer<T>, Disposable {

        /* renamed from: f */
        static final C4371a f18791f = new C4371a(null);

        /* renamed from: a */
        final CompletableObserver f18792a;

        /* renamed from: b */
        final Function<? super T, ? extends CompletableSource> f18793b;

        /* renamed from: c */
        final boolean f18794c;

        /* renamed from: d */
        final AtomicThrowable f18795d = new AtomicThrowable();

        /* renamed from: e */
        final AtomicReference<C4371a> f18796e = new AtomicReference<>();

        /* renamed from: g */
        volatile boolean f18797g;

        /* renamed from: h */
        Disposable f18798h;

        C4370a(CompletableObserver arpVar, Function<? super T, ? extends CompletableSource> aunVar, boolean z) {
            this.f18792a = arpVar;
            this.f18793b = aunVar;
            this.f18794c = z;
        }

        @Override // p110z1.Observer
        public void onSubscribe(Disposable atrVar) {
            if (DisposableHelper.validate(this.f18798h, atrVar)) {
                this.f18798h = atrVar;
                this.f18792a.onSubscribe(this);
            }
        }

        @Override // p110z1.Observer
        public void onNext(T t) {
            C4371a aVar;
            try {
                CompletableSource arsVar = (CompletableSource) ObjectHelper.m9873a(this.f18793b.apply(t), "The mapper returned a null CompletableSource");
                C4371a aVar2 = new C4371a(this);
                do {
                    aVar = this.f18796e.get();
                    if (aVar == f18791f) {
                        return;
                    }
                } while (!this.f18796e.compareAndSet(aVar, aVar2));
                if (aVar != null) {
                    aVar.dispose();
                }
                arsVar.mo11309a(aVar2);
            } catch (Throwable th) {
                Exceptions.m9983b(th);
                this.f18798h.dispose();
                onError(th);
            }
        }

        @Override // p110z1.Observer
        public void onError(Throwable th) {
            if (!this.f18795d.addThrowable(th)) {
                RxJavaPlugins.m9212a(th);
            } else if (this.f18794c) {
                onComplete();
            } else {
                m9675a();
                Throwable terminate = this.f18795d.terminate();
                if (terminate != ExceptionHelper.f20064a) {
                    this.f18792a.onError(terminate);
                }
            }
        }

        @Override // p110z1.Observer
        public void onComplete() {
            this.f18797g = true;
            if (this.f18796e.get() == null) {
                Throwable terminate = this.f18795d.terminate();
                if (terminate == null) {
                    this.f18792a.onComplete();
                } else {
                    this.f18792a.onError(terminate);
                }
            }
        }

        /* renamed from: a */
        void m9675a() {
            C4371a andSet = this.f18796e.getAndSet(f18791f);
            if (andSet != null && andSet != f18791f) {
                andSet.dispose();
            }
        }

        @Override // p110z1.Disposable
        public void dispose() {
            this.f18798h.dispose();
            m9675a();
        }

        @Override // p110z1.Disposable
        public boolean isDisposed() {
            return this.f18796e.get() == f18791f;
        }

        /* renamed from: a */
        void m9673a(C4371a aVar, Throwable th) {
            if (!this.f18796e.compareAndSet(aVar, null) || !this.f18795d.addThrowable(th)) {
                RxJavaPlugins.m9212a(th);
            } else if (!this.f18794c) {
                dispose();
                Throwable terminate = this.f18795d.terminate();
                if (terminate != ExceptionHelper.f20064a) {
                    this.f18792a.onError(terminate);
                }
            } else if (this.f18797g) {
                this.f18792a.onError(this.f18795d.terminate());
            }
        }

        /* renamed from: a */
        void m9674a(C4371a aVar) {
            if (this.f18796e.compareAndSet(aVar, null) && this.f18797g) {
                Throwable terminate = this.f18795d.terminate();
                if (terminate == null) {
                    this.f18792a.onComplete();
                } else {
                    this.f18792a.onError(terminate);
                }
            }
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* compiled from: ObservableSwitchMapCompletable.java */
        /* renamed from: z1.bia$a$a */
        /* loaded from: classes3.dex */
        public static final class C4371a extends AtomicReference<Disposable> implements CompletableObserver {
            private static final long serialVersionUID = -8003404460084760287L;
            final C4370a<?> parent;

            C4371a(C4370a<?> aVar) {
                this.parent = aVar;
            }

            @Override // p110z1.CompletableObserver, p110z1.MaybeObserver, p110z1.SingleObserver
            public void onSubscribe(Disposable atrVar) {
                DisposableHelper.setOnce(this, atrVar);
            }

            @Override // p110z1.CompletableObserver, p110z1.MaybeObserver, p110z1.SingleObserver
            public void onError(Throwable th) {
                this.parent.m9673a(this, th);
            }

            @Override // p110z1.CompletableObserver, p110z1.MaybeObserver
            public void onComplete() {
                this.parent.m9674a(this);
            }

            void dispose() {
                DisposableHelper.dispose(this);
            }
        }
    }
}
