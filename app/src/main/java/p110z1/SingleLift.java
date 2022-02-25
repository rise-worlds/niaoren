package p110z1;

/* renamed from: z1.bps */
/* loaded from: classes3.dex */
public final class SingleLift<T, R> extends Single<R> {

    /* renamed from: a */
    final SingleSource<T> f19807a;

    /* renamed from: b */
    final SingleOperator<? extends R, ? super T> f19808b;

    public SingleLift(SingleSource<T> ataVar, SingleOperator<? extends R, ? super T> aszVar) {
        this.f19807a = ataVar;
        this.f19808b = aszVar;
    }

    @Override // p110z1.Single
    /* renamed from: b */
    protected void mo8961b(SingleObserver<? super R> asxVar) {
        try {
            this.f19807a.mo10018a((SingleObserver) ObjectHelper.m9873a(this.f19808b.m10019a(asxVar), "The onLift returned a null SingleObserver"));
        } catch (Throwable th) {
            Exceptions.m9983b(th);
            EmptyDisposable.error(th, asxVar);
        }
    }
}
