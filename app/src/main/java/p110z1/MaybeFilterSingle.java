package p110z1;

/* renamed from: z1.bfq */
/* loaded from: classes3.dex */
public final class MaybeFilterSingle<T> extends Maybe<T> {

    /* renamed from: a */
    final SingleSource<T> f18604a;

    /* renamed from: b */
    final Predicate<? super T> f18605b;

    public MaybeFilterSingle(SingleSource<T> ataVar, Predicate<? super T> auxVar) {
        this.f18604a = ataVar;
        this.f18605b = auxVar;
    }

    @Override // p110z1.Maybe
    /* renamed from: b */
    protected void mo8992b(MaybeObserver<? super T> asfVar) {
        this.f18604a.mo10018a(new C4287a(asfVar, this.f18605b));
    }

    /* compiled from: MaybeFilterSingle.java */
    /* renamed from: z1.bfq$a */
    /* loaded from: classes3.dex */
    static final class C4287a<T> implements SingleObserver<T>, Disposable {

        /* renamed from: a */
        final MaybeObserver<? super T> f18606a;

        /* renamed from: b */
        final Predicate<? super T> f18607b;

        /* renamed from: c */
        Disposable f18608c;

        C4287a(MaybeObserver<? super T> asfVar, Predicate<? super T> auxVar) {
            this.f18606a = asfVar;
            this.f18607b = auxVar;
        }

        @Override // p110z1.Disposable
        public void dispose() {
            Disposable atrVar = this.f18608c;
            this.f18608c = DisposableHelper.DISPOSED;
            atrVar.dispose();
        }

        @Override // p110z1.Disposable
        public boolean isDisposed() {
            return this.f18608c.isDisposed();
        }

        @Override // p110z1.SingleObserver
        public void onSubscribe(Disposable atrVar) {
            if (DisposableHelper.validate(this.f18608c, atrVar)) {
                this.f18608c = atrVar;
                this.f18606a.onSubscribe(this);
            }
        }

        @Override // p110z1.SingleObserver
        public void onSuccess(T t) {
            try {
                if (this.f18607b.test(t)) {
                    this.f18606a.onSuccess(t);
                } else {
                    this.f18606a.onComplete();
                }
            } catch (Throwable th) {
                Exceptions.m9983b(th);
                this.f18606a.onError(th);
            }
        }

        @Override // p110z1.SingleObserver
        public void onError(Throwable th) {
            this.f18606a.onError(th);
        }
    }
}
