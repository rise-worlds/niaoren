package p110z1;

@bwy(m8750a = "1.3")
@Unsigned
@Metadata(m8864a = 1, m8863b = {1, 1, 15}, m8862c = {1, 0, 3}, m8861d = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u001c\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\t\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0017\u0018\u0000 \u001a2\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0001\u001aB\"\b\u0000\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0004\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0006ø\u0001\u0000¢\u0006\u0002\u0010\u0007J\u0013\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012H\u0096\u0002J\b\u0010\u0013\u001a\u00020\u0014H\u0016J\b\u0010\u0015\u001a\u00020\u0010H\u0016J\t\u0010\u0016\u001a\u00020\u0017H\u0096\u0002J\b\u0010\u0018\u001a\u00020\u0019H\u0016R\u0016\u0010\b\u001a\u00020\u0002ø\u0001\u0000¢\u0006\n\n\u0002\u0010\u000b\u001a\u0004\b\t\u0010\nR\u0016\u0010\f\u001a\u00020\u0002ø\u0001\u0000¢\u0006\n\n\u0002\u0010\u000b\u001a\u0004\b\r\u0010\nR\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\nø\u0001\u0000\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u001b"}, m8860e = {"Lkotlin/ranges/ULongProgression;", "", "Lkotlin/ULong;", "start", "endInclusive", "step", "", "(JJJLkotlin/jvm/internal/DefaultConstructorMarker;)V", "first", "getFirst", "()J", "J", "last", "getLast", "getStep", "equals", "", "other", "", "hashCode", "", "isEmpty", "iterator", "Lkotlin/collections/ULongIterator;", "toString", "", "Companion", "kotlin-stdlib"})
/* renamed from: z1.cmp */
/* loaded from: classes3.dex */
public class ULongRange implements Iterable<ULong>, KMarkers {

    /* renamed from: a */
    public static final C4993a f20866a = new C4993a(null);

    /* renamed from: b */
    private final long f20867b;

    /* renamed from: c */
    private final long f20868c;

    /* renamed from: d */
    private final long f20869d;

    public /* synthetic */ ULongRange(long j, long j2, long j3, DefaultConstructorMarker civVar) {
        this(j, j2, j3);
    }

    private ULongRange(long j, long j2, long j3) {
        if (j3 == 0) {
            throw new IllegalArgumentException("Step must be non-zero.");
        } else if (j3 != Long.MIN_VALUE) {
            this.f20867b = j;
            this.f20868c = UProgressionUtil.m5457a(j, j2, j3);
            this.f20869d = j3;
        } else {
            throw new IllegalArgumentException("Step must be greater than Long.MIN_VALUE to avoid overflow on negation.");
        }
    }

    /* renamed from: a */
    public final long m4675a() {
        return this.f20867b;
    }

    /* renamed from: b */
    public final long m4674b() {
        return this.f20868c;
    }

    /* renamed from: c */
    public final long m4673c() {
        return this.f20869d;
    }

    @NotNull
    /* renamed from: d */
    public cbm iterator() {
        return new cmq(this.f20867b, this.f20868c, this.f20869d, null);
    }

    /* renamed from: e */
    public boolean mo4667e() {
        if (this.f20869d > 0) {
            if (UnsignedUtils.m8337a(this.f20867b, this.f20868c) > 0) {
                return true;
            }
        } else if (UnsignedUtils.m8337a(this.f20867b, this.f20868c) < 0) {
            return true;
        }
        return false;
    }

    public boolean equals(@dbs Object obj) {
        if (obj instanceof ULongRange) {
            if (!mo4667e() || !((ULongRange) obj).mo4667e()) {
                ULongRange cmpVar = (ULongRange) obj;
                if (!(this.f20867b == cmpVar.f20867b && this.f20868c == cmpVar.f20868c && this.f20869d == cmpVar.f20869d)) {
                }
            }
            return true;
        }
        return false;
    }

    public int hashCode() {
        if (mo4667e()) {
            return -1;
        }
        long j = this.f20867b;
        long j2 = this.f20868c;
        long j3 = this.f20869d;
        return (((((int) ULong.m8548b(j ^ ULong.m8548b(j >>> 32))) * 31) + ((int) ULong.m8548b(j2 ^ ULong.m8548b(j2 >>> 32)))) * 31) + ((int) ((j3 >>> 32) ^ j3));
    }

    @NotNull
    public String toString() {
        long j;
        StringBuilder sb;
        if (this.f20869d > 0) {
            sb = new StringBuilder();
            sb.append(ULong.m8555a(this.f20867b));
            sb.append("..");
            sb.append(ULong.m8555a(this.f20868c));
            sb.append(" step ");
            j = this.f20869d;
        } else {
            sb = new StringBuilder();
            sb.append(ULong.m8555a(this.f20867b));
            sb.append(" downTo ");
            sb.append(ULong.m8555a(this.f20868c));
            sb.append(" step ");
            j = -this.f20869d;
        }
        sb.append(j);
        return sb.toString();
    }

    /* compiled from: ULongRange.kt */
    @Metadata(m8864a = 1, m8863b = {1, 1, 15}, m8862c = {1, 0, 3}, m8861d = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J(\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\tø\u0001\u0000¢\u0006\u0004\b\n\u0010\u000b\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\f"}, m8860e = {"Lkotlin/ranges/ULongProgression$Companion;", "", "()V", "fromClosedRange", "Lkotlin/ranges/ULongProgression;", "rangeStart", "Lkotlin/ULong;", "rangeEnd", "step", "", "fromClosedRange-7ftBX0g", "(JJJ)Lkotlin/ranges/ULongProgression;", "kotlin-stdlib"})
    /* renamed from: z1.cmp$a */
    /* loaded from: classes3.dex */
    public static final class C4993a {
        private C4993a() {
        }

        public /* synthetic */ C4993a(DefaultConstructorMarker civVar) {
            this();
        }

        @NotNull
        /* renamed from: a */
        public final ULongRange m4671a(long j, long j2, long j3) {
            return new ULongRange(j, j2, j3, null);
        }
    }
}
