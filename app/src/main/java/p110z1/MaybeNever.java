package p110z1;

/* renamed from: z1.bgp */
/* loaded from: classes3.dex */
public final class MaybeNever extends Maybe<Object> {

    /* renamed from: a */
    public static final MaybeNever f18672a = new MaybeNever();

    @Override // p110z1.Maybe
    /* renamed from: b */
    protected void mo8992b(MaybeObserver<? super Object> asfVar) {
        asfVar.onSubscribe(EmptyDisposable.NEVER);
    }
}
