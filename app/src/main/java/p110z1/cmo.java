package p110z1;

import org.apache.tools.ant.types.selectors.SizeSelector;

/* compiled from: UIntRange.kt */
@bwy(m8750a = "1.3")
@Unsigned
@Metadata(m8864a = 1, m8863b = {1, 1, 15}, m8862c = {1, 0, 3}, m8861d = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0007\u0018\u0000 \u00172\u00020\u00012\b\u0012\u0004\u0012\u00020\u00030\u0002:\u0001\u0017B\u0018\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003ø\u0001\u0000¢\u0006\u0002\u0010\u0006J\u001b\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u0003H\u0096\u0002ø\u0001\u0000¢\u0006\u0004\b\r\u0010\u000eJ\u0013\u0010\u000f\u001a\u00020\u000b2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011H\u0096\u0002J\b\u0010\u0012\u001a\u00020\u0013H\u0016J\b\u0010\u0014\u001a\u00020\u000bH\u0016J\b\u0010\u0015\u001a\u00020\u0016H\u0016R\u0017\u0010\u0005\u001a\u00020\u00038VX\u0096\u0004ø\u0001\u0000¢\u0006\u0006\u001a\u0004\b\u0007\u0010\bR\u0017\u0010\u0004\u001a\u00020\u00038VX\u0096\u0004ø\u0001\u0000¢\u0006\u0006\u001a\u0004\b\t\u0010\bø\u0001\u0000\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0018"}, m8860e = {"Lkotlin/ranges/UIntRange;", "Lkotlin/ranges/UIntProgression;", "Lkotlin/ranges/ClosedRange;", "Lkotlin/UInt;", "start", "endInclusive", "(IILkotlin/jvm/internal/DefaultConstructorMarker;)V", "getEndInclusive", "()Lkotlin/UInt;", "getStart", "contains", "", SizeSelector.SIZE_KEY, "contains-WZ4Q5Ns", "(I)Z", "equals", "other", "", "hashCode", "", "isEmpty", "toString", "", "Companion", "kotlin-stdlib"})
/* renamed from: z1.cmo */
/* loaded from: classes3.dex */
public final class cmo extends UIntRange implements Range<UInt> {

    /* renamed from: b */
    public static final C4992a f20864b = new C4992a(null);
    @NotNull

    /* renamed from: c */
    private static final cmo f20865c = new cmo(-1, 0, null);

    private cmo(int i, int i2) {
        super(i, i2, 1, null);
    }

    public /* synthetic */ cmo(int i, int i2, DefaultConstructorMarker civVar) {
        this(i, i2);
    }

    @Override // p110z1.Range
    /* renamed from: a */
    public /* synthetic */ boolean mo4668a(UInt bxoVar) {
        return m4680a(bxoVar.m8629b());
    }

    @NotNull
    /* renamed from: f */
    public UInt mo4665g() {
        return UInt.m8623c(m4686a());
    }

    @NotNull
    /* renamed from: h */
    public UInt mo4663i() {
        return UInt.m8623c(m4685b());
    }

    /* renamed from: a */
    public boolean m4680a(int i) {
        return UnsignedUtils.m8340a(m4686a(), i) <= 0 && UnsignedUtils.m8340a(i, m4685b()) <= 0;
    }

    @Override // p110z1.UIntRange, p110z1.Range
    /* renamed from: e */
    public boolean mo4667e() {
        return UnsignedUtils.m8340a(m4686a(), m4685b()) > 0;
    }

    @Override // p110z1.UIntRange
    public boolean equals(@dbs Object obj) {
        if (obj instanceof cmo) {
            if (!mo4667e() || !((cmo) obj).mo4667e()) {
                cmo cmoVar = (cmo) obj;
                if (!(m4686a() == cmoVar.m4686a() && m4685b() == cmoVar.m4685b())) {
                }
            }
            return true;
        }
        return false;
    }

    @Override // p110z1.UIntRange
    public int hashCode() {
        if (mo4667e()) {
            return -1;
        }
        return (m4686a() * 31) + m4685b();
    }

    @Override // p110z1.UIntRange
    @NotNull
    public String toString() {
        return UInt.m8635a(m4686a()) + ".." + UInt.m8635a(m4685b());
    }

    /* compiled from: UIntRange.kt */
    @Metadata(m8864a = 1, m8863b = {1, 1, 15}, m8862c = {1, 0, 3}, m8861d = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, m8860e = {"Lkotlin/ranges/UIntRange$Companion;", "", "()V", "EMPTY", "Lkotlin/ranges/UIntRange;", "getEMPTY", "()Lkotlin/ranges/UIntRange;", "kotlin-stdlib"})
    /* renamed from: z1.cmo$a */
    /* loaded from: classes3.dex */
    public static final class C4992a {
        private C4992a() {
        }

        public /* synthetic */ C4992a(DefaultConstructorMarker civVar) {
            this();
        }

        @NotNull
        /* renamed from: a */
        public final cmo m4676a() {
            return cmo.f20865c;
        }
    }
}
