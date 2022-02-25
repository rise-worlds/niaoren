package p110z1;

/* renamed from: z1.bge */
/* loaded from: classes3.dex */
public final class MaybeFromSingle<T> extends Maybe<T> implements HasUpstreamSingleSource<T> {

    /* renamed from: a */
    final SingleSource<T> f18649a;

    public MaybeFromSingle(SingleSource<T> ataVar) {
        this.f18649a = ataVar;
    }

    @Override // p110z1.HasUpstreamSingleSource
    /* renamed from: u_ */
    public SingleSource<T> mo9695u_() {
        return this.f18649a;
    }

    @Override // p110z1.Maybe
    /* renamed from: b */
    protected void mo8992b(MaybeObserver<? super T> asfVar) {
        this.f18649a.mo10018a(new C4303a(asfVar));
    }

    /* compiled from: MaybeFromSingle.java */
    /* renamed from: z1.bge$a */
    /* loaded from: classes3.dex */
    static final class C4303a<T> implements SingleObserver<T>, Disposable {

        /* renamed from: a */
        final MaybeObserver<? super T> f18650a;

        /* renamed from: b */
        Disposable f18651b;

        C4303a(MaybeObserver<? super T> asfVar) {
            this.f18650a = asfVar;
        }

        @Override // p110z1.Disposable
        public void dispose() {
            this.f18651b.dispose();
            this.f18651b = DisposableHelper.DISPOSED;
        }

        @Override // p110z1.Disposable
        public boolean isDisposed() {
            return this.f18651b.isDisposed();
        }

        @Override // p110z1.SingleObserver
        public void onSubscribe(Disposable atrVar) {
            if (DisposableHelper.validate(this.f18651b, atrVar)) {
                this.f18651b = atrVar;
                this.f18650a.onSubscribe(this);
            }
        }

        @Override // p110z1.SingleObserver
        public void onSuccess(T t) {
            this.f18651b = DisposableHelper.DISPOSED;
            this.f18650a.onSuccess(t);
        }

        @Override // p110z1.SingleObserver
        public void onError(Throwable th) {
            this.f18651b = DisposableHelper.DISPOSED;
            this.f18650a.onError(th);
        }
    }
}
