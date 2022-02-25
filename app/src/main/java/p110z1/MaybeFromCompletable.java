package p110z1;

/* renamed from: z1.bgb */
/* loaded from: classes3.dex */
public final class MaybeFromCompletable<T> extends Maybe<T> implements HasUpstreamCompletableSource {

    /* renamed from: a */
    final CompletableSource f18642a;

    public MaybeFromCompletable(CompletableSource arsVar) {
        this.f18642a = arsVar;
    }

    @Override // p110z1.HasUpstreamCompletableSource
    /* renamed from: t_ */
    public CompletableSource mo9696t_() {
        return this.f18642a;
    }

    @Override // p110z1.Maybe
    /* renamed from: b */
    protected void mo8992b(MaybeObserver<? super T> asfVar) {
        this.f18642a.mo11309a(new C4302a(asfVar));
    }

    /* compiled from: MaybeFromCompletable.java */
    /* renamed from: z1.bgb$a */
    /* loaded from: classes3.dex */
    static final class C4302a<T> implements CompletableObserver, Disposable {

        /* renamed from: a */
        final MaybeObserver<? super T> f18643a;

        /* renamed from: b */
        Disposable f18644b;

        C4302a(MaybeObserver<? super T> asfVar) {
            this.f18643a = asfVar;
        }

        @Override // p110z1.Disposable
        public void dispose() {
            this.f18644b.dispose();
            this.f18644b = DisposableHelper.DISPOSED;
        }

        @Override // p110z1.Disposable
        public boolean isDisposed() {
            return this.f18644b.isDisposed();
        }

        @Override // p110z1.CompletableObserver, p110z1.MaybeObserver, p110z1.SingleObserver
        public void onSubscribe(Disposable atrVar) {
            if (DisposableHelper.validate(this.f18644b, atrVar)) {
                this.f18644b = atrVar;
                this.f18643a.onSubscribe(this);
            }
        }

        @Override // p110z1.CompletableObserver, p110z1.MaybeObserver
        public void onComplete() {
            this.f18644b = DisposableHelper.DISPOSED;
            this.f18643a.onComplete();
        }

        @Override // p110z1.CompletableObserver, p110z1.MaybeObserver, p110z1.SingleObserver
        public void onError(Throwable th) {
            this.f18644b = DisposableHelper.DISPOSED;
            this.f18643a.onError(th);
        }
    }
}
