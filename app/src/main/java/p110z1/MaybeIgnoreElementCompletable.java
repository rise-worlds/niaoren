package p110z1;

/* renamed from: z1.bgh */
/* loaded from: classes3.dex */
public final class MaybeIgnoreElementCompletable<T> extends Completable implements FuseToMaybe<T> {

    /* renamed from: a */
    final MaybeSource<T> f18656a;

    public MaybeIgnoreElementCompletable(MaybeSource<T> asiVar) {
        this.f18656a = asiVar;
    }

    @Override // p110z1.Completable
    /* renamed from: b */
    protected void mo9001b(CompletableObserver arpVar) {
        this.f18656a.mo10646a(new C4306a(arpVar));
    }

    @Override // p110z1.FuseToMaybe
    /* renamed from: v_ */
    public Maybe<T> mo9694v_() {
        return RxJavaPlugins.m9205a(new MaybeIgnoreElement(this.f18656a));
    }

    /* compiled from: MaybeIgnoreElementCompletable.java */
    /* renamed from: z1.bgh$a */
    /* loaded from: classes3.dex */
    static final class C4306a<T> implements MaybeObserver<T>, Disposable {

        /* renamed from: a */
        final CompletableObserver f18657a;

        /* renamed from: b */
        Disposable f18658b;

        C4306a(CompletableObserver arpVar) {
            this.f18657a = arpVar;
        }

        @Override // p110z1.MaybeObserver, p110z1.SingleObserver
        public void onSubscribe(Disposable atrVar) {
            if (DisposableHelper.validate(this.f18658b, atrVar)) {
                this.f18658b = atrVar;
                this.f18657a.onSubscribe(this);
            }
        }

        @Override // p110z1.MaybeObserver, p110z1.SingleObserver
        public void onSuccess(T t) {
            this.f18658b = DisposableHelper.DISPOSED;
            this.f18657a.onComplete();
        }

        @Override // p110z1.MaybeObserver, p110z1.SingleObserver
        public void onError(Throwable th) {
            this.f18658b = DisposableHelper.DISPOSED;
            this.f18657a.onError(th);
        }

        @Override // p110z1.MaybeObserver
        public void onComplete() {
            this.f18658b = DisposableHelper.DISPOSED;
            this.f18657a.onComplete();
        }

        @Override // p110z1.Disposable
        public boolean isDisposed() {
            return this.f18658b.isDisposed();
        }

        @Override // p110z1.Disposable
        public void dispose() {
            this.f18658b.dispose();
            this.f18658b = DisposableHelper.DISPOSED;
        }
    }
}
