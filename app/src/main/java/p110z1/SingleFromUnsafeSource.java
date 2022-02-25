package p110z1;

/* renamed from: z1.bpo */
/* loaded from: classes3.dex */
public final class SingleFromUnsafeSource<T> extends Single<T> {

    /* renamed from: a */
    final SingleSource<T> f19800a;

    public SingleFromUnsafeSource(SingleSource<T> ataVar) {
        this.f19800a = ataVar;
    }

    @Override // p110z1.Single
    /* renamed from: b */
    protected void mo8961b(SingleObserver<? super T> asxVar) {
        this.f19800a.mo10018a(asxVar);
    }
}
