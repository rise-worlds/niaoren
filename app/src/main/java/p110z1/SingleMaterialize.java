package p110z1;

@Experimental
/* renamed from: z1.bpu */
/* loaded from: classes3.dex */
public final class SingleMaterialize<T> extends Single<Notification<T>> {

    /* renamed from: a */
    final Single<T> f19813a;

    public SingleMaterialize(Single<T> asuVar) {
        this.f19813a = asuVar;
    }

    @Override // p110z1.Single
    /* renamed from: b */
    protected void mo8961b(SingleObserver<? super Notification<T>> asxVar) {
        this.f19813a.mo10018a((SingleObserver) new MaterializeSingleObserver(asxVar));
    }
}
