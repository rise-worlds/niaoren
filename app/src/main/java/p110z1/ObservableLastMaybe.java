package p110z1;

/* renamed from: z1.bky */
/* loaded from: classes3.dex */
public final class ObservableLastMaybe<T> extends Maybe<T> {

    /* renamed from: a */
    final ObservableSource<T> f19213a;

    public ObservableLastMaybe(ObservableSource<T> asqVar) {
        this.f19213a = asqVar;
    }

    @Override // p110z1.Maybe
    /* renamed from: b */
    protected void mo8992b(MaybeObserver<? super T> asfVar) {
        this.f19213a.subscribe(new C4488a(asfVar));
    }

    /* compiled from: ObservableLastMaybe.java */
    /* renamed from: z1.bky$a */
    /* loaded from: classes3.dex */
    static final class C4488a<T> implements Observer<T>, Disposable {

        /* renamed from: a */
        final MaybeObserver<? super T> f19214a;

        /* renamed from: b */
        Disposable f19215b;

        /* renamed from: c */
        T f19216c;

        C4488a(MaybeObserver<? super T> asfVar) {
            this.f19214a = asfVar;
        }

        @Override // p110z1.Disposable
        public void dispose() {
            this.f19215b.dispose();
            this.f19215b = DisposableHelper.DISPOSED;
        }

        @Override // p110z1.Disposable
        public boolean isDisposed() {
            return this.f19215b == DisposableHelper.DISPOSED;
        }

        @Override // p110z1.Observer
        public void onSubscribe(Disposable atrVar) {
            if (DisposableHelper.validate(this.f19215b, atrVar)) {
                this.f19215b = atrVar;
                this.f19214a.onSubscribe(this);
            }
        }

        @Override // p110z1.Observer
        public void onNext(T t) {
            this.f19216c = t;
        }

        @Override // p110z1.Observer
        public void onError(Throwable th) {
            this.f19215b = DisposableHelper.DISPOSED;
            this.f19216c = null;
            this.f19214a.onError(th);
        }

        @Override // p110z1.Observer
        public void onComplete() {
            this.f19215b = DisposableHelper.DISPOSED;
            T t = this.f19216c;
            if (t != null) {
                this.f19216c = null;
                this.f19214a.onSuccess(t);
                return;
            }
            this.f19214a.onComplete();
        }
    }
}
