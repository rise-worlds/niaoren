package p110z1;

/* compiled from: Progressions.kt */
@Metadata(m8864a = 1, m8863b = {1, 1, 15}, m8862c = {1, 0, 3}, m8861d = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u001c\n\u0002\u0010\t\n\u0002\b\u000b\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0016\u0018\u0000 \u00182\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0001\u0018B\u001f\b\u0000\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0004\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0002¢\u0006\u0002\u0010\u0006J\u0013\u0010\r\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010H\u0096\u0002J\b\u0010\u0011\u001a\u00020\u0012H\u0016J\b\u0010\u0013\u001a\u00020\u000eH\u0016J\t\u0010\u0014\u001a\u00020\u0015H\u0096\u0002J\b\u0010\u0016\u001a\u00020\u0017H\u0016R\u0011\u0010\u0007\u001a\u00020\u0002¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\n\u001a\u00020\u0002¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\tR\u0011\u0010\u0005\u001a\u00020\u0002¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\t¨\u0006\u0019"}, m8860e = {"Lkotlin/ranges/LongProgression;", "", "", "start", "endInclusive", "step", "(JJJ)V", "first", "getFirst", "()J", "last", "getLast", "getStep", "equals", "", "other", "", "hashCode", "", "isEmpty", "iterator", "Lkotlin/collections/LongIterator;", "toString", "", "Companion", "kotlin-stdlib"})
/* renamed from: z1.cmf */
/* loaded from: classes3.dex */
public class cmf implements Iterable<Long>, KMarkers {

    /* renamed from: a */
    public static final C4989a f20846a = new C4989a(null);

    /* renamed from: b */
    private final long f20847b;

    /* renamed from: c */
    private final long f20848c;

    /* renamed from: d */
    private final long f20849d;

    public cmf(long j, long j2, long j3) {
        if (j3 == 0) {
            throw new IllegalArgumentException("Step must be non-zero.");
        } else if (j3 != Long.MIN_VALUE) {
            this.f20847b = j;
            this.f20848c = progressionUtil.m5466a(j, j2, j3);
            this.f20849d = j3;
        } else {
            throw new IllegalArgumentException("Step must be greater than Long.MIN_VALUE to avoid overflow on negation.");
        }
    }

    /* renamed from: a */
    public final long m4821a() {
        return this.f20847b;
    }

    /* renamed from: b */
    public final long m4820b() {
        return this.f20848c;
    }

    /* renamed from: c */
    public final long m4819c() {
        return this.f20849d;
    }

    @NotNull
    /* renamed from: d */
    public caj iterator() {
        return new cmg(this.f20847b, this.f20848c, this.f20849d);
    }

    /* renamed from: e */
    public boolean mo4667e() {
        if (this.f20849d > 0) {
            if (this.f20847b > this.f20848c) {
                return true;
            }
        } else if (this.f20847b < this.f20848c) {
            return true;
        }
        return false;
    }

    public boolean equals(@dbs Object obj) {
        if (obj instanceof cmf) {
            if (!mo4667e() || !((cmf) obj).mo4667e()) {
                cmf cmfVar = (cmf) obj;
                if (!(this.f20847b == cmfVar.f20847b && this.f20848c == cmfVar.f20848c && this.f20849d == cmfVar.f20849d)) {
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
        long j = 31;
        long j2 = this.f20847b;
        long j3 = this.f20848c;
        long j4 = j * (((j2 ^ (j2 >>> 32)) * j) + (j3 ^ (j3 >>> 32)));
        long j5 = this.f20849d;
        return (int) (j4 + (j5 ^ (j5 >>> 32)));
    }

    @NotNull
    public String toString() {
        long j;
        StringBuilder sb;
        if (this.f20849d > 0) {
            sb = new StringBuilder();
            sb.append(this.f20847b);
            sb.append("..");
            sb.append(this.f20848c);
            sb.append(" step ");
            j = this.f20849d;
        } else {
            sb = new StringBuilder();
            sb.append(this.f20847b);
            sb.append(" downTo ");
            sb.append(this.f20848c);
            sb.append(" step ");
            j = -this.f20849d;
        }
        sb.append(j);
        return sb.toString();
    }

    /* compiled from: Progressions.kt */
    @Metadata(m8864a = 1, m8863b = {1, 1, 15}, m8862c = {1, 0, 3}, m8861d = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u001e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\u0006¨\u0006\t"}, m8860e = {"Lkotlin/ranges/LongProgression$Companion;", "", "()V", "fromClosedRange", "Lkotlin/ranges/LongProgression;", "rangeStart", "", "rangeEnd", "step", "kotlin-stdlib"})
    /* renamed from: z1.cmf$a */
    /* loaded from: classes3.dex */
    public static final class C4989a {
        private C4989a() {
        }

        public /* synthetic */ C4989a(DefaultConstructorMarker civVar) {
            this();
        }

        @NotNull
        /* renamed from: a */
        public final cmf m4817a(long j, long j2, long j3) {
            return new cmf(j, j2, j3);
        }
    }
}
