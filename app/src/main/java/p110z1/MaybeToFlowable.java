package p110z1;

/* renamed from: z1.bhd */
/* loaded from: classes3.dex */
public final class MaybeToFlowable<T> extends Flowable<T> implements HasUpstreamMaybeSource<T> {

    /* renamed from: b */
    final MaybeSource<T> f18716b;

    public MaybeToFlowable(MaybeSource<T> asiVar) {
        this.f18716b = asiVar;
    }

    @Override // p110z1.HasUpstreamMaybeSource
    /* renamed from: s_ */
    public MaybeSource<T> mo9687s_() {
        return this.f18716b;
    }

    @Override // p110z1.Flowable
    /* renamed from: d */
    protected void mo9054d(Subscriber<? super T> dbxVar) {
        this.f18716b.mo10646a(new C4338a(dbxVar));
    }

    /* compiled from: MaybeToFlowable.java */
    /* renamed from: z1.bhd$a */
    /* loaded from: classes3.dex */
    static final class C4338a<T> extends DeferredScalarSubscription<T> implements MaybeObserver<T> {
        private static final long serialVersionUID = 7603343402964826922L;
        Disposable upstream;

        C4338a(Subscriber<? super T> dbxVar) {
            super(dbxVar);
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
            this.downstream.onError(th);
        }

        @Override // p110z1.MaybeObserver
        public void onComplete() {
            this.downstream.onComplete();
        }

        @Override // p110z1.DeferredScalarSubscription, p110z1.dby
        public void cancel() {
            super.cancel();
            this.upstream.dispose();
        }
    }
}
