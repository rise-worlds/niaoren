package p110z1;

@bwy(m8750a = "1.3")
@Unsigned
@Metadata(m8864a = 1, m8863b = {1, 1, 15}, m8862c = {1, 0, 3}, m8861d = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u001c\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\t\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0017\u0018\u0000 \u00192\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0001\u0019B\"\b\u0000\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0004\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0006ø\u0001\u0000¢\u0006\u0002\u0010\u0007J\u0013\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012H\u0096\u0002J\b\u0010\u0013\u001a\u00020\u0006H\u0016J\b\u0010\u0014\u001a\u00020\u0010H\u0016J\t\u0010\u0015\u001a\u00020\u0016H\u0096\u0002J\b\u0010\u0017\u001a\u00020\u0018H\u0016R\u0016\u0010\b\u001a\u00020\u0002ø\u0001\u0000¢\u0006\n\n\u0002\u0010\u000b\u001a\u0004\b\t\u0010\nR\u0016\u0010\f\u001a\u00020\u0002ø\u0001\u0000¢\u0006\n\n\u0002\u0010\u000b\u001a\u0004\b\r\u0010\nR\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\nø\u0001\u0000\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u001a"}, m8860e = {"Lkotlin/ranges/UIntProgression;", "", "Lkotlin/UInt;", "start", "endInclusive", "step", "", "(IIILkotlin/jvm/internal/DefaultConstructorMarker;)V", "first", "getFirst", "()I", "I", "last", "getLast", "getStep", "equals", "", "other", "", "hashCode", "isEmpty", "iterator", "Lkotlin/collections/UIntIterator;", "toString", "", "Companion", "kotlin-stdlib"})
/* renamed from: z1.cmm */
/* loaded from: classes3.dex */
public class UIntRange implements Iterable<UInt>, KMarkers {

    /* renamed from: a */
    public static final C4991a f20856a = new C4991a(null);

    /* renamed from: b */
    private final int f20857b;

    /* renamed from: c */
    private final int f20858c;

    /* renamed from: d */
    private final int f20859d;

    public /* synthetic */ UIntRange(int i, int i2, int i3, DefaultConstructorMarker civVar) {
        this(i, i2, i3);
    }

    private UIntRange(int i, int i2, int i3) {
        if (i3 == 0) {
            throw new IllegalArgumentException("Step must be non-zero.");
        } else if (i3 != Integer.MIN_VALUE) {
            this.f20857b = i;
            this.f20858c = UProgressionUtil.m5458a(i, i2, i3);
            this.f20859d = i3;
        } else {
            throw new IllegalArgumentException("Step must be greater than Int.MIN_VALUE to avoid overflow on negation.");
        }
    }

    /* renamed from: a */
    public final int m4686a() {
        return this.f20857b;
    }

    /* renamed from: b */
    public final int m4685b() {
        return this.f20858c;
    }

    /* renamed from: c */
    public final int m4684c() {
        return this.f20859d;
    }

    @NotNull
    /* renamed from: d */
    public cbl iterator() {
        return new cmn(this.f20857b, this.f20858c, this.f20859d, null);
    }

    /* renamed from: e */
    public boolean mo4667e() {
        if (this.f20859d > 0) {
            if (UnsignedUtils.m8340a(this.f20857b, this.f20858c) > 0) {
                return true;
            }
        } else if (UnsignedUtils.m8340a(this.f20857b, this.f20858c) < 0) {
            return true;
        }
        return false;
    }

    public boolean equals(@dbs Object obj) {
        if (obj instanceof UIntRange) {
            if (!mo4667e() || !((UIntRange) obj).mo4667e()) {
                UIntRange cmmVar = (UIntRange) obj;
                if (!(this.f20857b == cmmVar.f20857b && this.f20858c == cmmVar.f20858c && this.f20859d == cmmVar.f20859d)) {
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
        return (((this.f20857b * 31) + this.f20858c) * 31) + this.f20859d;
    }

    @NotNull
    public String toString() {
        int i;
        StringBuilder sb;
        if (this.f20859d > 0) {
            sb = new StringBuilder();
            sb.append(UInt.m8635a(this.f20857b));
            sb.append("..");
            sb.append(UInt.m8635a(this.f20858c));
            sb.append(" step ");
            i = this.f20859d;
        } else {
            sb = new StringBuilder();
            sb.append(UInt.m8635a(this.f20857b));
            sb.append(" downTo ");
            sb.append(UInt.m8635a(this.f20858c));
            sb.append(" step ");
            i = -this.f20859d;
        }
        sb.append(i);
        return sb.toString();
    }

    /* compiled from: UIntRange.kt */
    @Metadata(m8864a = 1, m8863b = {1, 1, 15}, m8862c = {1, 0, 3}, m8861d = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J(\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\tø\u0001\u0000¢\u0006\u0004\b\n\u0010\u000b\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\f"}, m8860e = {"Lkotlin/ranges/UIntProgression$Companion;", "", "()V", "fromClosedRange", "Lkotlin/ranges/UIntProgression;", "rangeStart", "Lkotlin/UInt;", "rangeEnd", "step", "", "fromClosedRange-Nkh28Cs", "(III)Lkotlin/ranges/UIntProgression;", "kotlin-stdlib"})
    /* renamed from: z1.cmm$a */
    /* loaded from: classes3.dex */
    public static final class C4991a {
        private C4991a() {
        }

        public /* synthetic */ C4991a(DefaultConstructorMarker civVar) {
            this();
        }

        @NotNull
        /* renamed from: a */
        public final UIntRange m4682a(int i, int i2, int i3) {
            return new UIntRange(i, i2, i3, null);
        }
    }
}
