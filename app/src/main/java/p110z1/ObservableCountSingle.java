package p110z1;

/* renamed from: z1.bjg */
/* loaded from: classes3.dex */
public final class ObservableCountSingle<T> extends Single<Long> implements FuseToObservable<Long> {

    /* renamed from: a */
    final ObservableSource<T> f18973a;

    public ObservableCountSingle(ObservableSource<T> asqVar) {
        this.f18973a = asqVar;
    }

    @Override // p110z1.Single
    /* renamed from: b */
    public void mo8961b(SingleObserver<? super Long> asxVar) {
        this.f18973a.subscribe(new C4419a(asxVar));
    }

    @Override // p110z1.FuseToObservable
    /* renamed from: w_ */
    public Observable<Long> mo9572w_() {
        return RxJavaPlugins.m9203a(new ObservableCount(this.f18973a));
    }

    /* compiled from: ObservableCountSingle.java */
    /* renamed from: z1.bjg$a */
    /* loaded from: classes3.dex */
    static final class C4419a implements Observer<Object>, Disposable {

        /* renamed from: a */
        final SingleObserver<? super Long> f18974a;

        /* renamed from: b */
        Disposable f18975b;

        /* renamed from: c */
        long f18976c;

        C4419a(SingleObserver<? super Long> asxVar) {
            this.f18974a = asxVar;
        }

        @Override // p110z1.Observer
        public void onSubscribe(Disposable atrVar) {
            if (DisposableHelper.validate(this.f18975b, atrVar)) {
                this.f18975b = atrVar;
                this.f18974a.onSubscribe(this);
            }
        }

        @Override // p110z1.Disposable
        public void dispose() {
            this.f18975b.dispose();
            this.f18975b = DisposableHelper.DISPOSED;
        }

        @Override // p110z1.Disposable
        public boolean isDisposed() {
            return this.f18975b.isDisposed();
        }

        @Override // p110z1.Observer
        public void onNext(Object obj) {
            this.f18976c++;
        }

        @Override // p110z1.Observer
        public void onError(Throwable th) {
            this.f18975b = DisposableHelper.DISPOSED;
            this.f18974a.onError(th);
        }

        @Override // p110z1.Observer
        public void onComplete() {
            this.f18975b = DisposableHelper.DISPOSED;
            this.f18974a.onSuccess(Long.valueOf(this.f18976c));
        }
    }
}
