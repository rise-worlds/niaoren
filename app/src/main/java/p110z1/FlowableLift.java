package p110z1;

/* renamed from: z1.bbp */
/* loaded from: classes3.dex */
public final class FlowableLift<R, T> extends AbstractFlowableWithUpstream<T, R> {

    /* renamed from: c */
    final FlowableOperator<? extends R, ? super T> f18189c;

    public FlowableLift(Flowable<T> arvVar, FlowableOperator<? extends R, ? super T> arzVar) {
        super(arvVar);
        this.f18189c = arzVar;
    }

    @Override // p110z1.Flowable
    /* renamed from: d */
    public void mo9054d(Subscriber<? super R> dbxVar) {
        try {
            Subscriber<? super Object> a = this.f18189c.m10806a(dbxVar);
            if (a != null) {
                this.f17812b.subscribe(a);
                return;
            }
            throw new NullPointerException("Operator " + this.f18189c + " returned a null Subscriber");
        } catch (NullPointerException e) {
            throw e;
        } catch (Throwable th) {
            Exceptions.m9983b(th);
            RxJavaPlugins.m9212a(th);
            NullPointerException nullPointerException = new NullPointerException("Actually not, but can't throw other exceptions due to RS");
            nullPointerException.initCause(th);
            throw nullPointerException;
        }
    }
}
