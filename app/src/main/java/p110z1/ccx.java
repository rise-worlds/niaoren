package p110z1;

import p110z1.cda;

/* compiled from: CoroutineContextImpl.kt */
@Metadata(m8864a = 1, m8863b = {1, 1, 15}, m8862c = {1, 0, 3}, m8861d = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u0000\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0000\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0001\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005J\u0010\u0010\n\u001a\u00020\u000b2\u0006\u0010\u0003\u001a\u00020\u0004H\u0002J\u0010\u0010\f\u001a\u00020\u000b2\u0006\u0010\r\u001a\u00020\u0000H\u0002J\u0013\u0010\u000e\u001a\u00020\u000b2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010H\u0096\u0002J5\u0010\u0011\u001a\u0002H\u0012\"\u0004\b\u0000\u0010\u00122\u0006\u0010\u0013\u001a\u0002H\u00122\u0018\u0010\u0014\u001a\u0014\u0012\u0004\u0012\u0002H\u0012\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u0002H\u00120\u0015H\u0016¢\u0006\u0002\u0010\u0016J(\u0010\u0017\u001a\u0004\u0018\u0001H\u0018\"\b\b\u0000\u0010\u0018*\u00020\u00042\f\u0010\u0019\u001a\b\u0012\u0004\u0012\u0002H\u00180\u001aH\u0096\u0002¢\u0006\u0002\u0010\u001bJ\b\u0010\u001c\u001a\u00020\u001dH\u0016J\u0014\u0010\u001e\u001a\u00020\u00012\n\u0010\u0019\u001a\u0006\u0012\u0002\b\u00030\u001aH\u0016J\b\u0010\u001f\u001a\u00020\u001dH\u0002J\b\u0010 \u001a\u00020!H\u0016R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0011\u0010\u0002\u001a\u00020\u0001¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\t¨\u0006\""}, m8860e = {"Lkotlin/coroutines/experimental/CombinedContext;", "Lkotlin/coroutines/experimental/CoroutineContext;", "left", "element", "Lkotlin/coroutines/experimental/CoroutineContext$Element;", "(Lkotlin/coroutines/experimental/CoroutineContext;Lkotlin/coroutines/experimental/CoroutineContext$Element;)V", "getElement", "()Lkotlin/coroutines/experimental/CoroutineContext$Element;", "getLeft", "()Lkotlin/coroutines/experimental/CoroutineContext;", "contains", "", "containsAll", "context", "equals", "other", "", "fold", "R", "initial", "operation", "Lkotlin/Function2;", "(Ljava/lang/Object;Lkotlin/jvm/functions/Function2;)Ljava/lang/Object;", "get", "E", "key", "Lkotlin/coroutines/experimental/CoroutineContext$Key;", "(Lkotlin/coroutines/experimental/CoroutineContext$Key;)Lkotlin/coroutines/experimental/CoroutineContext$Element;", "hashCode", "", "minusKey", "size", "toString", "", "kotlin-stdlib-coroutines"})
/* renamed from: z1.ccx */
/* loaded from: classes3.dex */
public final class ccx implements cda {
    @NotNull

    /* renamed from: a */
    private final cda f20559a;
    @NotNull

    /* renamed from: b */
    private final cda.AbstractC4924b f20560b;

    public ccx(@NotNull cda cdaVar, @NotNull cda.AbstractC4924b bVar) {
        cji.m5162f(cdaVar, "left");
        cji.m5162f(bVar, "element");
        this.f20559a = cdaVar;
        this.f20560b = bVar;
    }

    @NotNull
    /* renamed from: a */
    public final cda m5630a() {
        return this.f20559a;
    }

    @Override // p110z1.cda
    @NotNull
    /* renamed from: a */
    public cda mo5548a(@NotNull cda cdaVar) {
        cji.m5162f(cdaVar, "context");
        return cda.C4922a.m5611a(this, cdaVar);
    }

    @NotNull
    /* renamed from: b */
    public final cda.AbstractC4924b m5627b() {
        return this.f20560b;
    }

    @Override // p110z1.cda
    @dbs
    /* renamed from: a */
    public <E extends cda.AbstractC4924b> E mo5549a(@NotNull cda.AbstractC4926c<E> cVar) {
        cji.m5162f(cVar, "key");
        cda cdaVar = this;
        while (true) {
            ccx ccxVar = (ccx) cdaVar;
            E e = (E) ccxVar.f20560b.mo5549a(cVar);
            if (e != null) {
                return e;
            }
            cdaVar = ccxVar.f20559a;
            if (!(cdaVar instanceof ccx)) {
                return (E) cdaVar.mo5549a(cVar);
            }
        }
    }

    @Override // p110z1.cda
    /* renamed from: a */
    public <R> R mo5551a(R r, @NotNull cho<? super R, ? super cda.AbstractC4924b, ? extends R> choVar) {
        cji.m5162f(choVar, "operation");
        return (R) choVar.invoke((Object) this.f20559a.mo5551a(r, choVar), this.f20560b);
    }

    @Override // p110z1.cda
    @NotNull
    /* renamed from: b */
    public cda mo5546b(@NotNull cda.AbstractC4926c<?> cVar) {
        cji.m5162f(cVar, "key");
        if (this.f20560b.mo5549a((cda.AbstractC4926c<cda.AbstractC4924b>) cVar) != null) {
            return this.f20559a;
        }
        cda b = this.f20559a.mo5546b(cVar);
        return b == this.f20559a ? this : b == cdc.f20565a ? this.f20560b : new ccx(b, this.f20560b);
    }

    /* renamed from: c */
    private final int m5626c() {
        cda cdaVar = this.f20559a;
        if (cdaVar instanceof ccx) {
            return ((ccx) cdaVar).m5626c() + 1;
        }
        return 2;
    }

    /* renamed from: a */
    private final boolean m5628a(cda.AbstractC4924b bVar) {
        return cji.m5184a(mo5549a((cda.AbstractC4926c<cda.AbstractC4924b>) bVar.mo5552a()), bVar);
    }

    /* renamed from: a */
    private final boolean m5629a(ccx ccxVar) {
        while (m5628a(ccxVar.f20560b)) {
            cda cdaVar = ccxVar.f20559a;
            if (cdaVar instanceof ccx) {
                ccxVar = (ccx) cdaVar;
            } else if (cdaVar != null) {
                return m5628a((cda.AbstractC4924b) cdaVar);
            } else {
                throw new TypeCastException("null cannot be cast to non-null type kotlin.coroutines.experimental.CoroutineContext.Element");
            }
        }
        return false;
    }

    public boolean equals(@dbs Object obj) {
        if (this != obj) {
            if (obj instanceof ccx) {
                ccx ccxVar = (ccx) obj;
                if (ccxVar.m5626c() != m5626c() || !ccxVar.m5629a(this)) {
                }
            }
            return false;
        }
        return true;
    }

    public int hashCode() {
        return this.f20559a.hashCode() + this.f20560b.hashCode();
    }

    /* compiled from: CoroutineContextImpl.kt */
    @Metadata(m8864a = 3, m8863b = {1, 1, 15}, m8862c = {1, 0, 3}, m8861d = {"\u0000\u0010\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u0004H\n¢\u0006\u0002\b\u0005"}, m8860e = {"<anonymous>", "", "acc", "element", "Lkotlin/coroutines/experimental/CoroutineContext$Element;", "invoke"})
    /* renamed from: z1.ccx$a */
    /* loaded from: classes3.dex */
    static final class C4918a extends Lambda implements cho<String, cda.AbstractC4924b, String> {
        public static final C4918a INSTANCE = new C4918a();

        C4918a() {
            super(2);
        }

        @NotNull
        public final String invoke(@NotNull String str, @NotNull cda.AbstractC4924b bVar) {
            cji.m5162f(str, "acc");
            cji.m5162f(bVar, "element");
            if (str.length() == 0) {
                return bVar.toString();
            }
            return str + ", " + bVar;
        }
    }

    @NotNull
    public String toString() {
        return "[" + ((String) mo5551a("", C4918a.INSTANCE)) + "]";
    }
}
