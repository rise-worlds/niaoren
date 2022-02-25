package p110z1;

import p110z1.CoroutineContext;

@bwy(m8750a = "1.3")
@Metadata(m8864a = 1, m8863b = {1, 1, 15}, m8862c = {1, 0, 3}, m8861d = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b'\u0018\u00002\u00020\u0001B\u0011\u0012\n\u0010\u0002\u001a\u0006\u0012\u0002\b\u00030\u0003¢\u0006\u0002\u0010\u0004R\u0018\u0010\u0002\u001a\u0006\u0012\u0002\b\u00030\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, m8860e = {"Lkotlin/coroutines/AbstractCoroutineContextElement;", "Lkotlin/coroutines/CoroutineContext$Element;", "key", "Lkotlin/coroutines/CoroutineContext$Key;", "(Lkotlin/coroutines/CoroutineContext$Key;)V", "getKey", "()Lkotlin/coroutines/CoroutineContext$Key;", "kotlin-stdlib"})
/* renamed from: z1.ccn */
/* loaded from: classes3.dex */
public abstract class CoroutineContextImpl implements CoroutineContext.AbstractC4914b {
    @NotNull

    /* renamed from: a */
    private final CoroutineContext.AbstractC4916c<?> f20549a;

    public CoroutineContextImpl(@NotNull CoroutineContext.AbstractC4916c<?> cVar) {
        cji.m5162f(cVar, "key");
        this.f20549a = cVar;
    }

    @Override // p110z1.CoroutineContext.AbstractC4914b
    @NotNull
    /* renamed from: a */
    public CoroutineContext.AbstractC4916c<?> mo5567a() {
        return this.f20549a;
    }

    @Override // p110z1.CoroutineContext.AbstractC4914b, p110z1.CoroutineContext
    public <R> R fold(R r, @NotNull cho<? super R, ? super CoroutineContext.AbstractC4914b, ? extends R> choVar) {
        cji.m5162f(choVar, "operation");
        return (R) CoroutineContext.AbstractC4914b.C4915a.m5636a(this, r, choVar);
    }

    @Override // p110z1.CoroutineContext.AbstractC4914b, p110z1.CoroutineContext
    @dbs
    public <E extends CoroutineContext.AbstractC4914b> E get(@NotNull CoroutineContext.AbstractC4916c<E> cVar) {
        cji.m5162f(cVar, "key");
        return (E) CoroutineContext.AbstractC4914b.C4915a.m5635a(this, cVar);
    }

    @Override // p110z1.CoroutineContext.AbstractC4914b, p110z1.CoroutineContext
    @NotNull
    public CoroutineContext minusKey(@NotNull CoroutineContext.AbstractC4916c<?> cVar) {
        cji.m5162f(cVar, "key");
        return CoroutineContext.AbstractC4914b.C4915a.m5633b(this, cVar);
    }

    @Override // p110z1.CoroutineContext
    @NotNull
    public CoroutineContext plus(@NotNull CoroutineContext ccsVar) {
        cji.m5162f(ccsVar, "context");
        return CoroutineContext.AbstractC4914b.C4915a.m5634a(this, ccsVar);
    }
}
