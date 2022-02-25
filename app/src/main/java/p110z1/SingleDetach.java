package p110z1;

/* renamed from: z1.bou */
/* loaded from: classes3.dex */
public final class SingleDetach<T> extends Single<T> {

    /* renamed from: a */
    final SingleSource<T> f19729a;

    public SingleDetach(SingleSource<T> ataVar) {
        this.f19729a = ataVar;
    }

    @Override // p110z1.Single
    /* renamed from: b */
    protected void mo8961b(SingleObserver<? super T> asxVar) {
        this.f19729a.mo10018a(new C4664a(asxVar));
    }

    /* compiled from: SingleDetach.java */
    /* renamed from: z1.bou$a */
    /* loaded from: classes3.dex */
    static final class C4664a<T> implements SingleObserver<T>, Disposable {

        /* renamed from: a */
        SingleObserver<? super T> f19730a;

        /* renamed from: b */
        Disposable f19731b;

        C4664a(SingleObserver<? super T> asxVar) {
            this.f19730a = asxVar;
        }

        @Override // p110z1.Disposable
        public void dispose() {
            this.f19730a = null;
            this.f19731b.dispose();
            this.f19731b = DisposableHelper.DISPOSED;
        }

        @Override // p110z1.Disposable
        public boolean isDisposed() {
            return this.f19731b.isDisposed();
        }

        @Override // p110z1.SingleObserver
        public void onSubscribe(Disposable atrVar) {
            if (DisposableHelper.validate(this.f19731b, atrVar)) {
                this.f19731b = atrVar;
                this.f19730a.onSubscribe(this);
            }
        }

        @Override // p110z1.SingleObserver
        public void onSuccess(T t) {
            this.f19731b = DisposableHelper.DISPOSED;
            SingleObserver<? super T> asxVar = this.f19730a;
            if (asxVar != null) {
                this.f19730a = null;
                asxVar.onSuccess(t);
            }
        }

        @Override // p110z1.SingleObserver
        public void onError(Throwable th) {
            this.f19731b = DisposableHelper.DISPOSED;
            SingleObserver<? super T> asxVar = this.f19730a;
            if (asxVar != null) {
                this.f19730a = null;
                asxVar.onError(th);
            }
        }
    }
}
