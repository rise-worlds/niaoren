package p110z1;

/* compiled from: KType.kt */
@bwy(m8750a = "1.1")
@Metadata(m8864a = 1, m8863b = {1, 1, 15}, m8862c = {1, 0, 3}, m8861d = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0087\b\u0018\u0000 \u00152\u00020\u0001:\u0001\u0015B\u0019\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\u0002\u0010\u0006J\u000b\u0010\u000b\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\f\u001a\u0004\u0018\u00010\u0005HÆ\u0003J!\u0010\r\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005HÆ\u0001J\u0013\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0011\u001a\u00020\u0012HÖ\u0001J\t\u0010\u0013\u001a\u00020\u0014HÖ\u0001R\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u0016"}, m8860e = {"Lkotlin/reflect/KTypeProjection;", "", "variance", "Lkotlin/reflect/KVariance;", "type", "Lkotlin/reflect/KType;", "(Lkotlin/reflect/KVariance;Lkotlin/reflect/KType;)V", "getType", "()Lkotlin/reflect/KType;", "getVariance", "()Lkotlin/reflect/KVariance;", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "", "Companion", "kotlin-stdlib"})
/* renamed from: z1.cnl */
/* loaded from: classes3.dex */
public final class cnl {

    /* renamed from: a */
    public static final C5012a f20877a = new C5012a(null);
    @NotNull

    /* renamed from: d */
    private static final cnl f20878d = new cnl(null, null);
    @dbs

    /* renamed from: b */
    private final KVariance f20879b;
    @dbs

    /* renamed from: c */
    private final KType f20880c;

    /* renamed from: a */
    public static /* synthetic */ cnl m4560a(cnl cnlVar, KVariance cnmVar, KType cnjVar, int i, Object obj) {
        if ((i & 1) != 0) {
            cnmVar = cnlVar.f20879b;
        }
        if ((i & 2) != 0) {
            cnjVar = cnlVar.f20880c;
        }
        return cnlVar.m4559a(cnmVar, cnjVar);
    }

    @NotNull
    /* renamed from: a */
    public final cnl m4559a(@dbs KVariance cnmVar, @dbs KType cnjVar) {
        return new cnl(cnmVar, cnjVar);
    }

    @dbs
    /* renamed from: d */
    public final KVariance m4556d() {
        return this.f20879b;
    }

    @dbs
    /* renamed from: e */
    public final KType m4555e() {
        return this.f20880c;
    }

    public boolean equals(@dbs Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof cnl)) {
            return false;
        }
        cnl cnlVar = (cnl) obj;
        return cji.m5184a(this.f20879b, cnlVar.f20879b) && cji.m5184a(this.f20880c, cnlVar.f20880c);
    }

    public int hashCode() {
        KVariance cnmVar = this.f20879b;
        int i = 0;
        int hashCode = (cnmVar != null ? cnmVar.hashCode() : 0) * 31;
        KType cnjVar = this.f20880c;
        if (cnjVar != null) {
            i = cnjVar.hashCode();
        }
        return hashCode + i;
    }

    @NotNull
    public String toString() {
        return "KTypeProjection(variance=" + this.f20879b + ", type=" + this.f20880c + ")";
    }

    public cnl(@dbs KVariance cnmVar, @dbs KType cnjVar) {
        this.f20879b = cnmVar;
        this.f20880c = cnjVar;
    }

    @dbs
    /* renamed from: a */
    public final KVariance m4561a() {
        return this.f20879b;
    }

    @dbs
    /* renamed from: b */
    public final KType m4558b() {
        return this.f20880c;
    }

    /* compiled from: KType.kt */
    @Metadata(m8864a = 1, m8863b = {1, 1, 15}, m8862c = {1, 0, 3}, m8861d = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0007\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\tJ\u000e\u0010\n\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\tJ\u000e\u0010\u000b\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\tR\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\f"}, m8860e = {"Lkotlin/reflect/KTypeProjection$Companion;", "", "()V", "STAR", "Lkotlin/reflect/KTypeProjection;", "getSTAR", "()Lkotlin/reflect/KTypeProjection;", "contravariant", "type", "Lkotlin/reflect/KType;", "covariant", "invariant", "kotlin-stdlib"})
    /* renamed from: z1.cnl$a */
    /* loaded from: classes3.dex */
    public static final class C5012a {
        private C5012a() {
        }

        public /* synthetic */ C5012a(DefaultConstructorMarker civVar) {
            this();
        }

        @NotNull
        /* renamed from: a */
        public final cnl m4554a() {
            return cnl.f20878d;
        }

        @NotNull
        /* renamed from: a */
        public final cnl m4553a(@NotNull KType cnjVar) {
            cji.m5162f(cnjVar, "type");
            return new cnl(KVariance.INVARIANT, cnjVar);
        }

        @NotNull
        /* renamed from: b */
        public final cnl m4552b(@NotNull KType cnjVar) {
            cji.m5162f(cnjVar, "type");
            return new cnl(KVariance.IN, cnjVar);
        }

        @NotNull
        /* renamed from: c */
        public final cnl m4551c(@NotNull KType cnjVar) {
            cji.m5162f(cnjVar, "type");
            return new cnl(KVariance.OUT, cnjVar);
        }
    }
}
