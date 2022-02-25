package p110z1;

/* renamed from: z1.bgg */
/* loaded from: classes3.dex */
public final class MaybeIgnoreElement<T> extends AbstractMaybeWithUpstream<T, T> {
    public MaybeIgnoreElement(MaybeSource<T> asiVar) {
        super(asiVar);
    }

    @Override // p110z1.Maybe
    /* renamed from: b */
    protected void mo8992b(MaybeObserver<? super T> asfVar) {
        this.f18529a.mo10646a(new C4305a(asfVar));
    }

    /* compiled from: MaybeIgnoreElement.java */
    /* renamed from: z1.bgg$a */
    /* loaded from: classes3.dex */
    static final class C4305a<T> implements MaybeObserver<T>, Disposable {

        /* renamed from: a */
        final MaybeObserver<? super T> f18654a;

        /* renamed from: b */
        Disposable f18655b;

        C4305a(MaybeObserver<? super T> asfVar) {
            this.f18654a = asfVar;
        }

        @Override // p110z1.MaybeObserver, p110z1.SingleObserver
        public void onSubscribe(Disposable atrVar) {
            if (DisposableHelper.validate(this.f18655b, atrVar)) {
                this.f18655b = atrVar;
                this.f18654a.onSubscribe(this);
            }
        }

        @Override // p110z1.MaybeObserver, p110z1.SingleObserver
        public void onSuccess(T t) {
            this.f18655b = DisposableHelper.DISPOSED;
            this.f18654a.onComplete();
        }

        @Override // p110z1.MaybeObserver, p110z1.SingleObserver
        public void onError(Throwable th) {
            this.f18655b = DisposableHelper.DISPOSED;
            this.f18654a.onError(th);
        }

        @Override // p110z1.MaybeObserver
        public void onComplete() {
            this.f18655b = DisposableHelper.DISPOSED;
            this.f18654a.onComplete();
        }

        @Override // p110z1.Disposable
        public boolean isDisposed() {
            return this.f18655b.isDisposed();
        }

        @Override // p110z1.Disposable
        public void dispose() {
            this.f18655b.dispose();
            this.f18655b = DisposableHelper.DISPOSED;
        }
    }
}
