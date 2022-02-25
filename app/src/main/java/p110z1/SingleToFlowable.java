package p110z1;

/* renamed from: z1.bqd */
/* loaded from: classes3.dex */
public final class SingleToFlowable<T> extends Flowable<T> {

    /* renamed from: b */
    final SingleSource<? extends T> f19836b;

    public SingleToFlowable(SingleSource<? extends T> ataVar) {
        this.f19836b = ataVar;
    }

    @Override // p110z1.Flowable
    /* renamed from: d */
    public void mo9054d(Subscriber<? super T> dbxVar) {
        this.f19836b.mo10018a(new C4702a(dbxVar));
    }

    /* compiled from: SingleToFlowable.java */
    /* renamed from: z1.bqd$a */
    /* loaded from: classes3.dex */
    static final class C4702a<T> extends DeferredScalarSubscription<T> implements SingleObserver<T> {
        private static final long serialVersionUID = 187782011903685568L;
        Disposable upstream;

        C4702a(Subscriber<? super T> dbxVar) {
            super(dbxVar);
        }

        @Override // p110z1.SingleObserver
        public void onSubscribe(Disposable atrVar) {
            if (DisposableHelper.validate(this.upstream, atrVar)) {
                this.upstream = atrVar;
                this.downstream.onSubscribe(this);
            }
        }

        @Override // p110z1.SingleObserver
        public void onSuccess(T t) {
            complete(t);
        }

        @Override // p110z1.SingleObserver
        public void onError(Throwable th) {
            this.downstream.onError(th);
        }

        @Override // p110z1.DeferredScalarSubscription, p110z1.dby
        public void cancel() {
            super.cancel();
            this.upstream.dispose();
        }
    }
}
