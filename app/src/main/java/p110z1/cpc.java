package p110z1;

import org.apache.tools.ant.types.selectors.SizeSelector;

/* compiled from: Regex.kt */
@Metadata(m8864a = 1, m8863b = {1, 1, 15}, m8862c = {1, 0, 3}, m8861d = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\t\u0010\f\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\r\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0011\u001a\u00020\u0012HÖ\u0001J\t\u0010\u0013\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u0014"}, m8860e = {"Lkotlin/text/MatchGroup;", "", SizeSelector.SIZE_KEY, "", "range", "Lkotlin/ranges/IntRange;", "(Ljava/lang/String;Lkotlin/ranges/IntRange;)V", "getRange", "()Lkotlin/ranges/IntRange;", "getValue", "()Ljava/lang/String;", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "kotlin-stdlib"})
/* renamed from: z1.cpc */
/* loaded from: classes3.dex */
public final class cpc {
    @NotNull

    /* renamed from: a */
    private final String f21016a;
    @NotNull

    /* renamed from: b */
    private final cme f21017b;

    /* renamed from: a */
    public static /* synthetic */ cpc m4201a(cpc cpcVar, String str, cme cmeVar, int i, Object obj) {
        if ((i & 1) != 0) {
            str = cpcVar.f21016a;
        }
        if ((i & 2) != 0) {
            cmeVar = cpcVar.f21017b;
        }
        return cpcVar.m4202a(str, cmeVar);
    }

    @NotNull
    /* renamed from: a */
    public final cpc m4202a(@NotNull String str, @NotNull cme cmeVar) {
        cji.m5162f(str, SizeSelector.SIZE_KEY);
        cji.m5162f(cmeVar, "range");
        return new cpc(str, cmeVar);
    }

    @NotNull
    /* renamed from: c */
    public final String m4199c() {
        return this.f21016a;
    }

    @NotNull
    /* renamed from: d */
    public final cme m4198d() {
        return this.f21017b;
    }

    public boolean equals(@dbs Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof cpc)) {
            return false;
        }
        cpc cpcVar = (cpc) obj;
        return cji.m5184a((Object) this.f21016a, (Object) cpcVar.f21016a) && cji.m5184a(this.f21017b, cpcVar.f21017b);
    }

    public int hashCode() {
        String str = this.f21016a;
        int i = 0;
        int hashCode = (str != null ? str.hashCode() : 0) * 31;
        cme cmeVar = this.f21017b;
        if (cmeVar != null) {
            i = cmeVar.hashCode();
        }
        return hashCode + i;
    }

    @NotNull
    public String toString() {
        return "MatchGroup(value=" + this.f21016a + ", range=" + this.f21017b + ")";
    }

    public cpc(@NotNull String str, @NotNull cme cmeVar) {
        cji.m5162f(str, SizeSelector.SIZE_KEY);
        cji.m5162f(cmeVar, "range");
        this.f21016a = str;
        this.f21017b = cmeVar;
    }

    @NotNull
    /* renamed from: a */
    public final String m4203a() {
        return this.f21016a;
    }

    @NotNull
    /* renamed from: b */
    public final cme m4200b() {
        return this.f21017b;
    }
}
