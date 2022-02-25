package p110z1;

/* renamed from: z1.ayq */
/* loaded from: classes3.dex */
abstract class AbstractFlowableWithUpstream<T, R> extends Flowable<R> implements HasUpstreamPublisher<T> {

    /* renamed from: b */
    protected final Flowable<T> f17812b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public AbstractFlowableWithUpstream(Flowable<T> arvVar) {
        this.f17812b = (Flowable) ObjectHelper.m9873a(arvVar, "source is null");
    }

    @Override // p110z1.HasUpstreamPublisher
    /* renamed from: p_ */
    public final Publisher<T> mo9741p_() {
        return this.f17812b;
    }
}
