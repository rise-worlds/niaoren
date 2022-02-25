package p110z1;

import org.apache.tools.ant.types.selectors.SizeSelector;

/* compiled from: Ranges.kt */
@Metadata(m8864a = 1, m8863b = {1, 1, 15}, m8862c = {1, 0, 3}, m8861d = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\t\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\u0018\u0000 \u00152\u00020\u00012\b\u0012\u0004\u0012\u00020\u00030\u0002:\u0001\u0015B\u0015\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003¢\u0006\u0002\u0010\u0006J\u0011\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u0003H\u0096\u0002J\u0013\u0010\r\u001a\u00020\u000b2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000fH\u0096\u0002J\b\u0010\u0010\u001a\u00020\u0011H\u0016J\b\u0010\u0012\u001a\u00020\u000bH\u0016J\b\u0010\u0013\u001a\u00020\u0014H\u0016R\u0014\u0010\u0005\u001a\u00020\u00038VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0007\u0010\bR\u0014\u0010\u0004\u001a\u00020\u00038VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\t\u0010\b¨\u0006\u0016"}, m8860e = {"Lkotlin/ranges/LongRange;", "Lkotlin/ranges/LongProgression;", "Lkotlin/ranges/ClosedRange;", "", "start", "endInclusive", "(JJ)V", "getEndInclusive", "()Ljava/lang/Long;", "getStart", "contains", "", SizeSelector.SIZE_KEY, "equals", "other", "", "hashCode", "", "isEmpty", "toString", "", "Companion", "kotlin-stdlib"})
/* renamed from: z1.cmh */
/* loaded from: classes3.dex */
public final class cmh extends cmf implements Range<Long> {

    /* renamed from: b */
    public static final C4990a f20854b = new C4990a(null);
    @NotNull

    /* renamed from: c */
    private static final cmh f20855c = new cmh(1, 0);

    public cmh(long j, long j2) {
        super(j, j2, 1L);
    }

    @Override // p110z1.Range
    /* renamed from: a */
    public /* synthetic */ boolean mo4668a(Long l) {
        return m4814a(l.longValue());
    }

    @NotNull
    /* renamed from: f */
    public Long mo4665g() {
        return Long.valueOf(m4821a());
    }

    @NotNull
    /* renamed from: h */
    public Long mo4663i() {
        return Long.valueOf(m4820b());
    }

    /* renamed from: a */
    public boolean m4814a(long j) {
        return m4821a() <= j && j <= m4820b();
    }

    @Override // p110z1.cmf, p110z1.Range
    /* renamed from: e */
    public boolean mo4667e() {
        return m4821a() > m4820b();
    }

    @Override // p110z1.cmf
    public boolean equals(@dbs Object obj) {
        if (obj instanceof cmh) {
            if (!mo4667e() || !((cmh) obj).mo4667e()) {
                cmh cmhVar = (cmh) obj;
                if (!(m4821a() == cmhVar.m4821a() && m4820b() == cmhVar.m4820b())) {
                }
            }
            return true;
        }
        return false;
    }

    @Override // p110z1.cmf
    public int hashCode() {
        if (mo4667e()) {
            return -1;
        }
        return (int) ((31 * (m4821a() ^ (m4821a() >>> 32))) + (m4820b() ^ (m4820b() >>> 32)));
    }

    @Override // p110z1.cmf
    @NotNull
    public String toString() {
        return m4821a() + ".." + m4820b();
    }

    /* compiled from: Ranges.kt */
    @Metadata(m8864a = 1, m8863b = {1, 1, 15}, m8862c = {1, 0, 3}, m8861d = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, m8860e = {"Lkotlin/ranges/LongRange$Companion;", "", "()V", "EMPTY", "Lkotlin/ranges/LongRange;", "getEMPTY", "()Lkotlin/ranges/LongRange;", "kotlin-stdlib"})
    /* renamed from: z1.cmh$a */
    /* loaded from: classes3.dex */
    public static final class C4990a {
        private C4990a() {
        }

        public /* synthetic */ C4990a(DefaultConstructorMarker civVar) {
            this();
        }

        @NotNull
        /* renamed from: a */
        public final cmh m4810a() {
            return cmh.f20855c;
        }
    }
}
