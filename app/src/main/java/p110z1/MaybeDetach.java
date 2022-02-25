package p110z1;

/* renamed from: z1.bfg */
/* loaded from: classes3.dex */
public final class MaybeDetach<T> extends AbstractMaybeWithUpstream<T, T> {
    public MaybeDetach(MaybeSource<T> asiVar) {
        super(asiVar);
    }

    @Override // p110z1.Maybe
    /* renamed from: b */
    protected void mo8992b(MaybeObserver<? super T> asfVar) {
        this.f18529a.mo10646a(new C4279a(asfVar));
    }

    /* compiled from: MaybeDetach.java */
    /* renamed from: z1.bfg$a */
    /* loaded from: classes3.dex */
    static final class C4279a<T> implements MaybeObserver<T>, Disposable {

        /* renamed from: a */
        MaybeObserver<? super T> f18579a;

        /* renamed from: b */
        Disposable f18580b;

        C4279a(MaybeObserver<? super T> asfVar) {
            this.f18579a = asfVar;
        }

        @Override // p110z1.Disposable
        public void dispose() {
            this.f18579a = null;
            this.f18580b.dispose();
            this.f18580b = DisposableHelper.DISPOSED;
        }

        @Override // p110z1.Disposable
        public boolean isDisposed() {
            return this.f18580b.isDisposed();
        }

        @Override // p110z1.MaybeObserver, p110z1.SingleObserver
        public void onSubscribe(Disposable atrVar) {
            if (DisposableHelper.validate(this.f18580b, atrVar)) {
                this.f18580b = atrVar;
                this.f18579a.onSubscribe(this);
            }
        }

        @Override // p110z1.MaybeObserver, p110z1.SingleObserver
        public void onSuccess(T t) {
            this.f18580b = DisposableHelper.DISPOSED;
            MaybeObserver<? super T> asfVar = this.f18579a;
            if (asfVar != null) {
                this.f18579a = null;
                asfVar.onSuccess(t);
            }
        }

        @Override // p110z1.MaybeObserver, p110z1.SingleObserver
        public void onError(Throwable th) {
            this.f18580b = DisposableHelper.DISPOSED;
            MaybeObserver<? super T> asfVar = this.f18579a;
            if (asfVar != null) {
                this.f18579a = null;
                asfVar.onError(th);
            }
        }

        @Override // p110z1.MaybeObserver
        public void onComplete() {
            this.f18580b = DisposableHelper.DISPOSED;
            MaybeObserver<? super T> asfVar = this.f18579a;
            if (asfVar != null) {
                this.f18579a = null;
                asfVar.onComplete();
            }
        }
    }
}
