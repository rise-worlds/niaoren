package p110z1;

import org.apache.tools.ant.types.selectors.SizeSelector;

/* compiled from: ULongRange.kt */
@bwy(m8750a = "1.3")
@Unsigned
@Metadata(m8864a = 1, m8863b = {1, 1, 15}, m8862c = {1, 0, 3}, m8861d = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0007\u0018\u0000 \u00172\u00020\u00012\b\u0012\u0004\u0012\u00020\u00030\u0002:\u0001\u0017B\u0018\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003ø\u0001\u0000¢\u0006\u0002\u0010\u0006J\u001b\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u0003H\u0096\u0002ø\u0001\u0000¢\u0006\u0004\b\r\u0010\u000eJ\u0013\u0010\u000f\u001a\u00020\u000b2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011H\u0096\u0002J\b\u0010\u0012\u001a\u00020\u0013H\u0016J\b\u0010\u0014\u001a\u00020\u000bH\u0016J\b\u0010\u0015\u001a\u00020\u0016H\u0016R\u0017\u0010\u0005\u001a\u00020\u00038VX\u0096\u0004ø\u0001\u0000¢\u0006\u0006\u001a\u0004\b\u0007\u0010\bR\u0017\u0010\u0004\u001a\u00020\u00038VX\u0096\u0004ø\u0001\u0000¢\u0006\u0006\u001a\u0004\b\t\u0010\bø\u0001\u0000\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0018"}, m8860e = {"Lkotlin/ranges/ULongRange;", "Lkotlin/ranges/ULongProgression;", "Lkotlin/ranges/ClosedRange;", "Lkotlin/ULong;", "start", "endInclusive", "(JJLkotlin/jvm/internal/DefaultConstructorMarker;)V", "getEndInclusive", "()Lkotlin/ULong;", "getStart", "contains", "", SizeSelector.SIZE_KEY, "contains-VKZWuLQ", "(J)Z", "equals", "other", "", "hashCode", "", "isEmpty", "toString", "", "Companion", "kotlin-stdlib"})
/* renamed from: z1.cmr */
/* loaded from: classes3.dex */
public final class cmr extends ULongRange implements Range<ULong> {

    /* renamed from: b */
    public static final C4994a f20874b = new C4994a(null);
    @NotNull

    /* renamed from: c */
    private static final cmr f20875c = new cmr(-1, 0, null);

    private cmr(long j, long j2) {
        super(j, j2, 1L, null);
    }

    public /* synthetic */ cmr(long j, long j2, DefaultConstructorMarker civVar) {
        this(j, j2);
    }

    @Override // p110z1.Range
    /* renamed from: a */
    public /* synthetic */ boolean mo4668a(ULong bxsVar) {
        return m4669a(bxsVar.m8549b());
    }

    @NotNull
    /* renamed from: f */
    public ULong mo4665g() {
        return ULong.m8543c(m4675a());
    }

    @NotNull
    /* renamed from: h */
    public ULong mo4663i() {
        return ULong.m8543c(m4674b());
    }

    /* renamed from: a */
    public boolean m4669a(long j) {
        return UnsignedUtils.m8337a(m4675a(), j) <= 0 && UnsignedUtils.m8337a(j, m4674b()) <= 0;
    }

    @Override // p110z1.ULongRange, p110z1.Range
    /* renamed from: e */
    public boolean mo4667e() {
        return UnsignedUtils.m8337a(m4675a(), m4674b()) > 0;
    }

    @Override // p110z1.ULongRange
    public boolean equals(@dbs Object obj) {
        if (obj instanceof cmr) {
            if (!mo4667e() || !((cmr) obj).mo4667e()) {
                cmr cmrVar = (cmr) obj;
                if (!(m4675a() == cmrVar.m4675a() && m4674b() == cmrVar.m4674b())) {
                }
            }
            return true;
        }
        return false;
    }

    @Override // p110z1.ULongRange
    public int hashCode() {
        if (mo4667e()) {
            return -1;
        }
        return (((int) ULong.m8548b(m4675a() ^ ULong.m8548b(m4675a() >>> 32))) * 31) + ((int) ULong.m8548b(m4674b() ^ ULong.m8548b(m4674b() >>> 32)));
    }

    @Override // p110z1.ULongRange
    @NotNull
    public String toString() {
        return ULong.m8555a(m4675a()) + ".." + ULong.m8555a(m4674b());
    }

    /* compiled from: ULongRange.kt */
    @Metadata(m8864a = 1, m8863b = {1, 1, 15}, m8862c = {1, 0, 3}, m8861d = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, m8860e = {"Lkotlin/ranges/ULongRange$Companion;", "", "()V", "EMPTY", "Lkotlin/ranges/ULongRange;", "getEMPTY", "()Lkotlin/ranges/ULongRange;", "kotlin-stdlib"})
    /* renamed from: z1.cmr$a */
    /* loaded from: classes3.dex */
    public static final class C4994a {
        private C4994a() {
        }

        public /* synthetic */ C4994a(DefaultConstructorMarker civVar) {
            this();
        }

        @NotNull
        /* renamed from: a */
        public final cmr m4661a() {
            return cmr.f20875c;
        }
    }
}
