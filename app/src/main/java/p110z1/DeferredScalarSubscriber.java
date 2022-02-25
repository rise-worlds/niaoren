package p110z1;

/* renamed from: z1.brm */
/* loaded from: classes3.dex */
public abstract class DeferredScalarSubscriber<T, R> extends DeferredScalarSubscription<R> implements FlowableSubscriber<T> {
    private static final long serialVersionUID = 2984505488220891551L;
    protected boolean hasValue;
    protected dby upstream;

    public DeferredScalarSubscriber(Subscriber<? super R> dbxVar) {
        super(dbxVar);
    }

    public void onSubscribe(dby dbyVar) {
        if (SubscriptionHelper.validate(this.upstream, dbyVar)) {
            this.upstream = dbyVar;
            this.downstream.onSubscribe(this);
            dbyVar.request(cjm.f20759b);
        }
    }

    public void onError(Throwable th) {
        this.value = null;
        this.downstream.onError(th);
    }

    public void onComplete() {
        if (this.hasValue) {
            complete(this.value);
        } else {
            this.downstream.onComplete();
        }
    }

    @Override // p110z1.DeferredScalarSubscription, p110z1.dby
    public void cancel() {
        super.cancel();
        this.upstream.cancel();
    }
}
