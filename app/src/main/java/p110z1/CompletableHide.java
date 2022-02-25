package p110z1;

/* renamed from: z1.axv */
/* loaded from: classes3.dex */
public final class CompletableHide extends Completable {

    /* renamed from: a */
    final CompletableSource f17733a;

    public CompletableHide(CompletableSource arsVar) {
        this.f17733a = arsVar;
    }

    @Override // p110z1.Completable
    /* renamed from: b */
    protected void mo9001b(CompletableObserver arpVar) {
        this.f17733a.mo11309a(new C3957a(arpVar));
    }

    /* compiled from: CompletableHide.java */
    /* renamed from: z1.axv$a */
    /* loaded from: classes3.dex */
    static final class C3957a implements CompletableObserver, Disposable {

        /* renamed from: a */
        final CompletableObserver f17734a;

        /* renamed from: b */
        Disposable f17735b;

        C3957a(CompletableObserver arpVar) {
            this.f17734a = arpVar;
        }

        @Override // p110z1.Disposable
        public void dispose() {
            this.f17735b.dispose();
            this.f17735b = DisposableHelper.DISPOSED;
        }

        @Override // p110z1.Disposable
        public boolean isDisposed() {
            return this.f17735b.isDisposed();
        }

        @Override // p110z1.CompletableObserver, p110z1.MaybeObserver, p110z1.SingleObserver
        public void onSubscribe(Disposable atrVar) {
            if (DisposableHelper.validate(this.f17735b, atrVar)) {
                this.f17735b = atrVar;
                this.f17734a.onSubscribe(this);
            }
        }

        @Override // p110z1.CompletableObserver, p110z1.MaybeObserver, p110z1.SingleObserver
        public void onError(Throwable th) {
            this.f17734a.onError(th);
        }

        @Override // p110z1.CompletableObserver, p110z1.MaybeObserver
        public void onComplete() {
            this.f17734a.onComplete();
        }
    }
}
