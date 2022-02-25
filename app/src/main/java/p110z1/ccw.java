package p110z1;

import p110z1.cda;

/* compiled from: CoroutineContextImpl.kt */
@bwy(m8750a = "1.1")
@Metadata(m8864a = 1, m8863b = {1, 1, 15}, m8862c = {1, 0, 3}, m8861d = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b'\u0018\u00002\u00020\u0001B\u0011\u0012\n\u0010\u0002\u001a\u0006\u0012\u0002\b\u00030\u0003¢\u0006\u0002\u0010\u0004R\u0018\u0010\u0002\u001a\u0006\u0012\u0002\b\u00030\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, m8860e = {"Lkotlin/coroutines/experimental/AbstractCoroutineContextElement;", "Lkotlin/coroutines/experimental/CoroutineContext$Element;", "key", "Lkotlin/coroutines/experimental/CoroutineContext$Key;", "(Lkotlin/coroutines/experimental/CoroutineContext$Key;)V", "getKey", "()Lkotlin/coroutines/experimental/CoroutineContext$Key;", "kotlin-stdlib-coroutines"})
/* renamed from: z1.ccw */
/* loaded from: classes3.dex */
public abstract class ccw implements cda.AbstractC4924b {
    @NotNull

    /* renamed from: a */
    private final cda.AbstractC4926c<?> f20558a;

    public ccw(@NotNull cda.AbstractC4926c<?> cVar) {
        cji.m5162f(cVar, "key");
        this.f20558a = cVar;
    }

    @Override // p110z1.cda.AbstractC4924b, p110z1.cda
    /* renamed from: a */
    public <R> R mo5551a(R r, @NotNull cho<? super R, ? super cda.AbstractC4924b, ? extends R> choVar) {
        cji.m5162f(choVar, "operation");
        return (R) cda.AbstractC4924b.C4925a.m5610a(this, r, choVar);
    }

    @Override // p110z1.cda.AbstractC4924b, p110z1.cda
    @dbs
    /* renamed from: a */
    public <E extends cda.AbstractC4924b> E mo5549a(@NotNull cda.AbstractC4926c<E> cVar) {
        cji.m5162f(cVar, "key");
        return (E) cda.AbstractC4924b.C4925a.m5609a(this, cVar);
    }

    @Override // p110z1.cda.AbstractC4924b
    @NotNull
    /* renamed from: a */
    public cda.AbstractC4926c<?> mo5552a() {
        return this.f20558a;
    }

    @Override // p110z1.cda
    @NotNull
    /* renamed from: a */
    public cda mo5548a(@NotNull cda cdaVar) {
        cji.m5162f(cdaVar, "context");
        return cda.AbstractC4924b.C4925a.m5608a(this, cdaVar);
    }

    @Override // p110z1.cda.AbstractC4924b, p110z1.cda
    @NotNull
    /* renamed from: b */
    public cda mo5546b(@NotNull cda.AbstractC4926c<?> cVar) {
        cji.m5162f(cVar, "key");
        return cda.AbstractC4924b.C4925a.m5607b(this, cVar);
    }
}
