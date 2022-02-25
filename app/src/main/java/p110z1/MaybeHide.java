package p110z1;

/* renamed from: z1.bgf */
/* loaded from: classes3.dex */
public final class MaybeHide<T> extends AbstractMaybeWithUpstream<T, T> {
    public MaybeHide(MaybeSource<T> asiVar) {
        super(asiVar);
    }

    @Override // p110z1.Maybe
    /* renamed from: b */
    protected void mo8992b(MaybeObserver<? super T> asfVar) {
        this.f18529a.mo10646a(new C4304a(asfVar));
    }

    /* compiled from: MaybeHide.java */
    /* renamed from: z1.bgf$a */
    /* loaded from: classes3.dex */
    static final class C4304a<T> implements MaybeObserver<T>, Disposable {

        /* renamed from: a */
        final MaybeObserver<? super T> f18652a;

        /* renamed from: b */
        Disposable f18653b;

        C4304a(MaybeObserver<? super T> asfVar) {
            this.f18652a = asfVar;
        }

        @Override // p110z1.Disposable
        public void dispose() {
            this.f18653b.dispose();
            this.f18653b = DisposableHelper.DISPOSED;
        }

        @Override // p110z1.Disposable
        public boolean isDisposed() {
            return this.f18653b.isDisposed();
        }

        @Override // p110z1.MaybeObserver, p110z1.SingleObserver
        public void onSubscribe(Disposable atrVar) {
            if (DisposableHelper.validate(this.f18653b, atrVar)) {
                this.f18653b = atrVar;
                this.f18652a.onSubscribe(this);
            }
        }

        @Override // p110z1.MaybeObserver, p110z1.SingleObserver
        public void onSuccess(T t) {
            this.f18652a.onSuccess(t);
        }

        @Override // p110z1.MaybeObserver, p110z1.SingleObserver
        public void onError(Throwable th) {
            this.f18652a.onError(th);
        }

        @Override // p110z1.MaybeObserver
        public void onComplete() {
            this.f18652a.onComplete();
        }
    }
}
