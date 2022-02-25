package p110z1;

/* renamed from: z1.bam */
/* loaded from: classes3.dex */
public final class FlowableEmpty extends Flowable<Object> implements ScalarCallable<Object> {

    /* renamed from: b */
    public static final Flowable<Object> f18076b = new FlowableEmpty();

    @Override // p110z1.ScalarCallable, java.util.concurrent.Callable
    public Object call() {
        return null;
    }

    private FlowableEmpty() {
    }

    @Override // p110z1.Flowable
    /* renamed from: d */
    public void mo9054d(Subscriber<? super Object> dbxVar) {
        EmptySubscription.complete(dbxVar);
    }
}
