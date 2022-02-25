package p110z1;

/* renamed from: z1.axh */
/* loaded from: classes3.dex */
public final class CompletableDetach extends Completable {

    /* renamed from: a */
    final CompletableSource f17704a;

    public CompletableDetach(CompletableSource arsVar) {
        this.f17704a = arsVar;
    }

    @Override // p110z1.Completable
    /* renamed from: b */
    protected void mo9001b(CompletableObserver arpVar) {
        this.f17704a.mo11309a(new C3950a(arpVar));
    }

    /* compiled from: CompletableDetach.java */
    /* renamed from: z1.axh$a */
    /* loaded from: classes3.dex */
    static final class C3950a implements CompletableObserver, Disposable {

        /* renamed from: a */
        CompletableObserver f17705a;

        /* renamed from: b */
        Disposable f17706b;

        C3950a(CompletableObserver arpVar) {
            this.f17705a = arpVar;
        }

        @Override // p110z1.Disposable
        public void dispose() {
            this.f17705a = null;
            this.f17706b.dispose();
            this.f17706b = DisposableHelper.DISPOSED;
        }

        @Override // p110z1.Disposable
        public boolean isDisposed() {
            return this.f17706b.isDisposed();
        }

        @Override // p110z1.CompletableObserver, p110z1.MaybeObserver, p110z1.SingleObserver
        public void onSubscribe(Disposable atrVar) {
            if (DisposableHelper.validate(this.f17706b, atrVar)) {
                this.f17706b = atrVar;
                this.f17705a.onSubscribe(this);
            }
        }

        @Override // p110z1.CompletableObserver, p110z1.MaybeObserver, p110z1.SingleObserver
        public void onError(Throwable th) {
            this.f17706b = DisposableHelper.DISPOSED;
            CompletableObserver arpVar = this.f17705a;
            if (arpVar != null) {
                this.f17705a = null;
                arpVar.onError(th);
            }
        }

        @Override // p110z1.CompletableObserver, p110z1.MaybeObserver
        public void onComplete() {
            this.f17706b = DisposableHelper.DISPOSED;
            CompletableObserver arpVar = this.f17705a;
            if (arpVar != null) {
                this.f17705a = null;
                arpVar.onComplete();
            }
        }
    }
}
