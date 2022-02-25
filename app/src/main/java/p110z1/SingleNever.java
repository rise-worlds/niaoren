package p110z1;

/* renamed from: z1.bpv */
/* loaded from: classes3.dex */
public final class SingleNever extends Single<Object> {

    /* renamed from: a */
    public static final Single<Object> f19814a = new SingleNever();

    private SingleNever() {
    }

    @Override // p110z1.Single
    /* renamed from: b */
    protected void mo8961b(SingleObserver<? super Object> asxVar) {
        asxVar.onSubscribe(EmptyDisposable.NEVER);
    }
}
