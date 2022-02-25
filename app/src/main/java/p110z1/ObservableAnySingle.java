package p110z1;

/* renamed from: z1.bio */
/* loaded from: classes3.dex */
public final class ObservableAnySingle<T> extends Single<Boolean> implements FuseToObservable<Boolean> {

    /* renamed from: a */
    final ObservableSource<T> f18850a;

    /* renamed from: b */
    final Predicate<? super T> f18851b;

    public ObservableAnySingle(ObservableSource<T> asqVar, Predicate<? super T> auxVar) {
        this.f18850a = asqVar;
        this.f18851b = auxVar;
    }

    @Override // p110z1.Single
    /* renamed from: b */
    protected void mo8961b(SingleObserver<? super Boolean> asxVar) {
        this.f18850a.subscribe(new C4388a(asxVar, this.f18851b));
    }

    @Override // p110z1.FuseToObservable
    /* renamed from: w_ */
    public Observable<Boolean> mo9572w_() {
        return RxJavaPlugins.m9203a(new ObservableAny(this.f18850a, this.f18851b));
    }

    /* compiled from: ObservableAnySingle.java */
    /* renamed from: z1.bio$a */
    /* loaded from: classes3.dex */
    static final class C4388a<T> implements Observer<T>, Disposable {

        /* renamed from: a */
        final SingleObserver<? super Boolean> f18852a;

        /* renamed from: b */
        final Predicate<? super T> f18853b;

        /* renamed from: c */
        Disposable f18854c;

        /* renamed from: d */
        boolean f18855d;

        C4388a(SingleObserver<? super Boolean> asxVar, Predicate<? super T> auxVar) {
            this.f18852a = asxVar;
            this.f18853b = auxVar;
        }

        @Override // p110z1.Observer
        public void onSubscribe(Disposable atrVar) {
            if (DisposableHelper.validate(this.f18854c, atrVar)) {
                this.f18854c = atrVar;
                this.f18852a.onSubscribe(this);
            }
        }

        @Override // p110z1.Observer
        public void onNext(T t) {
            if (!this.f18855d) {
                try {
                    if (this.f18853b.test(t)) {
                        this.f18855d = true;
                        this.f18854c.dispose();
                        this.f18852a.onSuccess(true);
                    }
                } catch (Throwable th) {
                    Exceptions.m9983b(th);
                    this.f18854c.dispose();
                    onError(th);
                }
            }
        }

        @Override // p110z1.Observer
        public void onError(Throwable th) {
            if (this.f18855d) {
                RxJavaPlugins.m9212a(th);
                return;
            }
            this.f18855d = true;
            this.f18852a.onError(th);
        }

        @Override // p110z1.Observer
        public void onComplete() {
            if (!this.f18855d) {
                this.f18855d = true;
                this.f18852a.onSuccess(false);
            }
        }

        @Override // p110z1.Disposable
        public void dispose() {
            this.f18854c.dispose();
        }

        @Override // p110z1.Disposable
        public boolean isDisposed() {
            return this.f18854c.isDisposed();
        }
    }
}
