package p110z1;

import p110z1.cda;

/* compiled from: CoroutineContextImpl.kt */
@bwy(m8750a = "1.1")
@Metadata(m8864a = 1, m8863b = {1, 1, 15}, m8862c = {1, 0, 3}, m8861d = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0000\bÇ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J5\u0010\u0003\u001a\u0002H\u0004\"\u0004\b\u0000\u0010\u00042\u0006\u0010\u0005\u001a\u0002H\u00042\u0018\u0010\u0006\u001a\u0014\u0012\u0004\u0012\u0002H\u0004\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u0002H\u00040\u0007H\u0016¢\u0006\u0002\u0010\tJ(\u0010\n\u001a\u0004\u0018\u0001H\u000b\"\b\b\u0000\u0010\u000b*\u00020\b2\f\u0010\f\u001a\b\u0012\u0004\u0012\u0002H\u000b0\rH\u0096\u0002¢\u0006\u0002\u0010\u000eJ\b\u0010\u000f\u001a\u00020\u0010H\u0016J\u0014\u0010\u0011\u001a\u00020\u00012\n\u0010\f\u001a\u0006\u0012\u0002\b\u00030\rH\u0016J\u0011\u0010\u0012\u001a\u00020\u00012\u0006\u0010\u0013\u001a\u00020\u0001H\u0096\u0002J\b\u0010\u0014\u001a\u00020\u0015H\u0016¨\u0006\u0016"}, m8860e = {"Lkotlin/coroutines/experimental/EmptyCoroutineContext;", "Lkotlin/coroutines/experimental/CoroutineContext;", "()V", "fold", "R", "initial", "operation", "Lkotlin/Function2;", "Lkotlin/coroutines/experimental/CoroutineContext$Element;", "(Ljava/lang/Object;Lkotlin/jvm/functions/Function2;)Ljava/lang/Object;", "get", "E", "key", "Lkotlin/coroutines/experimental/CoroutineContext$Key;", "(Lkotlin/coroutines/experimental/CoroutineContext$Key;)Lkotlin/coroutines/experimental/CoroutineContext$Element;", "hashCode", "", "minusKey", "plus", "context", "toString", "", "kotlin-stdlib-coroutines"})
/* renamed from: z1.cdc */
/* loaded from: classes3.dex */
public final class cdc implements cda {

    /* renamed from: a */
    public static final cdc f20565a = new cdc();

    @Override // p110z1.cda
    /* renamed from: a */
    public <R> R mo5551a(R r, @NotNull cho<? super R, ? super cda.AbstractC4924b, ? extends R> choVar) {
        cji.m5162f(choVar, "operation");
        return r;
    }

    @Override // p110z1.cda
    @dbs
    /* renamed from: a */
    public <E extends cda.AbstractC4924b> E mo5549a(@NotNull cda.AbstractC4926c<E> cVar) {
        cji.m5162f(cVar, "key");
        return null;
    }

    @Override // p110z1.cda
    @NotNull
    /* renamed from: a */
    public cda mo5548a(@NotNull cda cdaVar) {
        cji.m5162f(cdaVar, "context");
        return cdaVar;
    }

    public int hashCode() {
        return 0;
    }

    @NotNull
    public String toString() {
        return "EmptyCoroutineContext";
    }

    private cdc() {
    }

    @Override // p110z1.cda
    @NotNull
    /* renamed from: b */
    public cda mo5546b(@NotNull cda.AbstractC4926c<?> cVar) {
        cji.m5162f(cVar, "key");
        return this;
    }
}
