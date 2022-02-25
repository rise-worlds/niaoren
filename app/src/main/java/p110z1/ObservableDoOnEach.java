package p110z1;

/* renamed from: z1.bjt */
/* loaded from: classes3.dex */
public final class ObservableDoOnEach<T> extends AbstractObservableWithUpstream<T, T> {

    /* renamed from: b */
    final Consumer<? super T> f19045b;

    /* renamed from: c */
    final Consumer<? super Throwable> f19046c;

    /* renamed from: d */
    final Action f19047d;

    /* renamed from: e */
    final Action f19048e;

    public ObservableDoOnEach(ObservableSource<T> asqVar, Consumer<? super T> aumVar, Consumer<? super Throwable> aumVar2, Action augVar, Action augVar2) {
        super(asqVar);
        this.f19045b = aumVar;
        this.f19046c = aumVar2;
        this.f19047d = augVar;
        this.f19048e = augVar2;
    }

    @Override // p110z1.Observable
    /* renamed from: a */
    public void mo34a(Observer<? super T> assVar) {
        this.f18807a.subscribe(new C4438a(assVar, this.f19045b, this.f19046c, this.f19047d, this.f19048e));
    }

    /* compiled from: ObservableDoOnEach.java */
    /* renamed from: z1.bjt$a */
    /* loaded from: classes3.dex */
    static final class C4438a<T> implements Observer<T>, Disposable {

        /* renamed from: a */
        final Observer<? super T> f19049a;

        /* renamed from: b */
        final Consumer<? super T> f19050b;

        /* renamed from: c */
        final Consumer<? super Throwable> f19051c;

        /* renamed from: d */
        final Action f19052d;

        /* renamed from: e */
        final Action f19053e;

        /* renamed from: f */
        Disposable f19054f;

        /* renamed from: g */
        boolean f19055g;

        C4438a(Observer<? super T> assVar, Consumer<? super T> aumVar, Consumer<? super Throwable> aumVar2, Action augVar, Action augVar2) {
            this.f19049a = assVar;
            this.f19050b = aumVar;
            this.f19051c = aumVar2;
            this.f19052d = augVar;
            this.f19053e = augVar2;
        }

        @Override // p110z1.Observer
        public void onSubscribe(Disposable atrVar) {
            if (DisposableHelper.validate(this.f19054f, atrVar)) {
                this.f19054f = atrVar;
                this.f19049a.onSubscribe(this);
            }
        }

        @Override // p110z1.Disposable
        public void dispose() {
            this.f19054f.dispose();
        }

        @Override // p110z1.Disposable
        public boolean isDisposed() {
            return this.f19054f.isDisposed();
        }

        @Override // p110z1.Observer
        public void onNext(T t) {
            if (!this.f19055g) {
                try {
                    this.f19050b.accept(t);
                    this.f19049a.onNext(t);
                } catch (Throwable th) {
                    Exceptions.m9983b(th);
                    this.f19054f.dispose();
                    onError(th);
                }
            }
        }

        @Override // p110z1.Observer
        public void onError(Throwable th) {
            if (this.f19055g) {
                RxJavaPlugins.m9212a(th);
                return;
            }
            this.f19055g = true;
            try {
                this.f19051c.accept(th);
            } catch (Throwable th2) {
                Exceptions.m9983b(th2);
                th = new CompositeException(th, th2);
            }
            this.f19049a.onError(th);
            try {
                this.f19053e.mo9442a();
            } catch (Throwable th3) {
                Exceptions.m9983b(th3);
                RxJavaPlugins.m9212a(th3);
            }
        }

        @Override // p110z1.Observer
        public void onComplete() {
            if (!this.f19055g) {
                try {
                    this.f19052d.mo9442a();
                    this.f19055g = true;
                    this.f19049a.onComplete();
                    try {
                        this.f19053e.mo9442a();
                    } catch (Throwable th) {
                        Exceptions.m9983b(th);
                        RxJavaPlugins.m9212a(th);
                    }
                } catch (Throwable th2) {
                    Exceptions.m9983b(th2);
                    onError(th2);
                }
            }
        }
    }
}
