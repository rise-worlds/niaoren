package p110z1;

import org.apache.tools.ant.types.selectors.SizeSelector;

/* compiled from: Ranges.kt */
@Metadata(m8864a = 1, m8863b = {1, 1, 15}, m8862c = {1, 0, 3}, m8861d = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\u0018\u0000 \u00142\u00020\u00012\b\u0012\u0004\u0012\u00020\u00030\u0002:\u0001\u0014B\u0015\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003¢\u0006\u0002\u0010\u0006J\u0011\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u0003H\u0096\u0002J\u0013\u0010\r\u001a\u00020\u000b2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000fH\u0096\u0002J\b\u0010\u0010\u001a\u00020\u0003H\u0016J\b\u0010\u0011\u001a\u00020\u000bH\u0016J\b\u0010\u0012\u001a\u00020\u0013H\u0016R\u0014\u0010\u0005\u001a\u00020\u00038VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0007\u0010\bR\u0014\u0010\u0004\u001a\u00020\u00038VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\t\u0010\b¨\u0006\u0015"}, m8860e = {"Lkotlin/ranges/IntRange;", "Lkotlin/ranges/IntProgression;", "Lkotlin/ranges/ClosedRange;", "", "start", "endInclusive", "(II)V", "getEndInclusive", "()Ljava/lang/Integer;", "getStart", "contains", "", SizeSelector.SIZE_KEY, "equals", "other", "", "hashCode", "isEmpty", "toString", "", "Companion", "kotlin-stdlib"})
/* renamed from: z1.cme */
/* loaded from: classes3.dex */
public final class cme extends cmc implements Range<Integer> {

    /* renamed from: b */
    public static final C4988a f20844b = new C4988a(null);
    @NotNull

    /* renamed from: c */
    private static final cme f20845c = new cme(1, 0);

    public cme(int i, int i2) {
        super(i, i2, 1);
    }

    @Override // p110z1.Range
    /* renamed from: a */
    public /* synthetic */ boolean mo4668a(Integer num) {
        return m4826a(num.intValue());
    }

    @NotNull
    /* renamed from: f */
    public Integer mo4665g() {
        return Integer.valueOf(m4833a());
    }

    @NotNull
    /* renamed from: h */
    public Integer mo4663i() {
        return Integer.valueOf(m4832b());
    }

    /* renamed from: a */
    public boolean m4826a(int i) {
        return m4833a() <= i && i <= m4832b();
    }

    @Override // p110z1.cmc, p110z1.Range
    /* renamed from: e */
    public boolean mo4667e() {
        return m4833a() > m4832b();
    }

    @Override // p110z1.cmc
    public boolean equals(@dbs Object obj) {
        if (obj instanceof cme) {
            if (!mo4667e() || !((cme) obj).mo4667e()) {
                cme cmeVar = (cme) obj;
                if (!(m4833a() == cmeVar.m4833a() && m4832b() == cmeVar.m4832b())) {
                }
            }
            return true;
        }
        return false;
    }

    @Override // p110z1.cmc
    public int hashCode() {
        if (mo4667e()) {
            return -1;
        }
        return (m4833a() * 31) + m4832b();
    }

    @Override // p110z1.cmc
    @NotNull
    public String toString() {
        return m4833a() + ".." + m4832b();
    }

    /* compiled from: Ranges.kt */
    @Metadata(m8864a = 1, m8863b = {1, 1, 15}, m8862c = {1, 0, 3}, m8861d = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, m8860e = {"Lkotlin/ranges/IntRange$Companion;", "", "()V", "EMPTY", "Lkotlin/ranges/IntRange;", "getEMPTY", "()Lkotlin/ranges/IntRange;", "kotlin-stdlib"})
    /* renamed from: z1.cme$a */
    /* loaded from: classes3.dex */
    public static final class C4988a {
        private C4988a() {
        }

        public /* synthetic */ C4988a(DefaultConstructorMarker civVar) {
            this();
        }

        @NotNull
        /* renamed from: a */
        public final cme m4822a() {
            return cme.f20845c;
        }
    }
}
