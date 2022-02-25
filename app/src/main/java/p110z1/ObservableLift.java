package p110z1;

/* renamed from: z1.bla */
/* loaded from: classes3.dex */
public final class ObservableLift<R, T> extends AbstractObservableWithUpstream<T, R> {

    /* renamed from: b */
    final ObservableOperator<? extends R, ? super T> f19223b;

    public ObservableLift(ObservableSource<T> asqVar, ObservableOperator<? extends R, ? super T> aspVar) {
        super(asqVar);
        this.f19223b = aspVar;
    }

    @Override // p110z1.Observable
    /* renamed from: a */
    public void mo34a(Observer<? super R> assVar) {
        try {
            Observer<? super Object> a = this.f19223b.m10163a(assVar);
            this.f18807a.subscribe((Observer) ObjectHelper.m9873a(a, "Operator " + this.f19223b + " returned a null Observer"));
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
