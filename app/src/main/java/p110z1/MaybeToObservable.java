package p110z1;

/* renamed from: z1.bhe */
/* loaded from: classes3.dex */
public final class MaybeToObservable<T> extends Observable<T> implements HasUpstreamMaybeSource<T> {

    /* renamed from: a */
    final MaybeSource<T> f18717a;

    public MaybeToObservable(MaybeSource<T> asiVar) {
        this.f18717a = asiVar;
    }

    @Override // p110z1.HasUpstreamMaybeSource
    /* renamed from: s_ */
    public MaybeSource<T> mo9687s_() {
        return this.f18717a;
    }

    @Override // p110z1.Observable
    /* renamed from: a */
    protected void mo34a(Observer<? super T> assVar) {
        this.f18717a.mo10646a(m9688b((Observer) assVar));
    }

    /* renamed from: b */
    public static <T> MaybeObserver<T> m9688b(Observer<? super T> assVar) {
        return new C4339a(assVar);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: MaybeToObservable.java */
    /* renamed from: z1.bhe$a */
    /* loaded from: classes3.dex */
    public static final class C4339a<T> extends DeferredScalarDisposable<T> implements MaybeObserver<T> {
        private static final long serialVersionUID = 7603343402964826922L;
        Disposable upstream;

        C4339a(Observer<? super T> assVar) {
            super(assVar);
        }

        @Override // p110z1.MaybeObserver, p110z1.SingleObserver
        public void onSubscribe(Disposable atrVar) {
            if (DisposableHelper.validate(this.upstream, atrVar)) {
                this.upstream = atrVar;
                this.downstream.onSubscribe(this);
            }
        }

        @Override // p110z1.MaybeObserver, p110z1.SingleObserver
        public void onSuccess(T t) {
            complete(t);
        }

        @Override // p110z1.MaybeObserver, p110z1.SingleObserver
        public void onError(Throwable th) {
            error(th);
        }

        @Override // p110z1.MaybeObserver
        public void onComplete() {
            complete();
        }

        @Override // p110z1.DeferredScalarDisposable, p110z1.Disposable
        public void dispose() {
            super.dispose();
            this.upstream.dispose();
        }
    }
}
