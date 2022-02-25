package p110z1;

/* renamed from: z1.bil */
/* loaded from: classes3.dex */
public final class ObservableAllSingle<T> extends Single<Boolean> implements FuseToObservable<Boolean> {

    /* renamed from: a */
    final ObservableSource<T> f18834a;

    /* renamed from: b */
    final Predicate<? super T> f18835b;

    public ObservableAllSingle(ObservableSource<T> asqVar, Predicate<? super T> auxVar) {
        this.f18834a = asqVar;
        this.f18835b = auxVar;
    }

    @Override // p110z1.Single
    /* renamed from: b */
    protected void mo8961b(SingleObserver<? super Boolean> asxVar) {
        this.f18834a.subscribe(new C4384a(asxVar, this.f18835b));
    }

    @Override // p110z1.FuseToObservable
    /* renamed from: w_ */
    public Observable<Boolean> mo9572w_() {
        return RxJavaPlugins.m9203a(new ObservableAll(this.f18834a, this.f18835b));
    }

    /* compiled from: ObservableAllSingle.java */
    /* renamed from: z1.bil$a */
    /* loaded from: classes3.dex */
    static final class C4384a<T> implements Observer<T>, Disposable {

        /* renamed from: a */
        final SingleObserver<? super Boolean> f18836a;

        /* renamed from: b */
        final Predicate<? super T> f18837b;

        /* renamed from: c */
        Disposable f18838c;

        /* renamed from: d */
        boolean f18839d;

        C4384a(SingleObserver<? super Boolean> asxVar, Predicate<? super T> auxVar) {
            this.f18836a = asxVar;
            this.f18837b = auxVar;
        }

        @Override // p110z1.Observer
        public void onSubscribe(Disposable atrVar) {
            if (DisposableHelper.validate(this.f18838c, atrVar)) {
                this.f18838c = atrVar;
                this.f18836a.onSubscribe(this);
            }
        }

        @Override // p110z1.Observer
        public void onNext(T t) {
            if (!this.f18839d) {
                try {
                    if (!this.f18837b.test(t)) {
                        this.f18839d = true;
                        this.f18838c.dispose();
                        this.f18836a.onSuccess(false);
                    }
                } catch (Throwable th) {
                    Exceptions.m9983b(th);
                    this.f18838c.dispose();
                    onError(th);
                }
            }
        }

        @Override // p110z1.Observer
        public void onError(Throwable th) {
            if (this.f18839d) {
                RxJavaPlugins.m9212a(th);
                return;
            }
            this.f18839d = true;
            this.f18836a.onError(th);
        }

        @Override // p110z1.Observer
        public void onComplete() {
            if (!this.f18839d) {
                this.f18839d = true;
                this.f18836a.onSuccess(true);
            }
        }

        @Override // p110z1.Disposable
        public void dispose() {
            this.f18838c.dispose();
        }

        @Override // p110z1.Disposable
        public boolean isDisposed() {
            return this.f18838c.isDisposed();
        }
    }
}
