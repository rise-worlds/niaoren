package p110z1;

/* renamed from: z1.bgi */
/* loaded from: classes3.dex */
public final class MaybeIsEmpty<T> extends AbstractMaybeWithUpstream<T, Boolean> {
    public MaybeIsEmpty(MaybeSource<T> asiVar) {
        super(asiVar);
    }

    @Override // p110z1.Maybe
    /* renamed from: b */
    protected void mo8992b(MaybeObserver<? super Boolean> asfVar) {
        this.f18529a.mo10646a(new C4307a(asfVar));
    }

    /* compiled from: MaybeIsEmpty.java */
    /* renamed from: z1.bgi$a */
    /* loaded from: classes3.dex */
    static final class C4307a<T> implements MaybeObserver<T>, Disposable {

        /* renamed from: a */
        final MaybeObserver<? super Boolean> f18659a;

        /* renamed from: b */
        Disposable f18660b;

        C4307a(MaybeObserver<? super Boolean> asfVar) {
            this.f18659a = asfVar;
        }

        @Override // p110z1.Disposable
        public void dispose() {
            this.f18660b.dispose();
        }

        @Override // p110z1.Disposable
        public boolean isDisposed() {
            return this.f18660b.isDisposed();
        }

        @Override // p110z1.MaybeObserver, p110z1.SingleObserver
        public void onSubscribe(Disposable atrVar) {
            if (DisposableHelper.validate(this.f18660b, atrVar)) {
                this.f18660b = atrVar;
                this.f18659a.onSubscribe(this);
            }
        }

        @Override // p110z1.MaybeObserver, p110z1.SingleObserver
        public void onSuccess(T t) {
            this.f18659a.onSuccess(false);
        }

        @Override // p110z1.MaybeObserver, p110z1.SingleObserver
        public void onError(Throwable th) {
            this.f18659a.onError(th);
        }

        @Override // p110z1.MaybeObserver
        public void onComplete() {
            this.f18659a.onSuccess(true);
        }
    }
}
