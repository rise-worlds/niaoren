package p110z1;

/* compiled from: Progressions.kt */
@Metadata(m8864a = 1, m8863b = {1, 1, 15}, m8862c = {1, 0, 3}, m8861d = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u001c\n\u0002\u0010\b\n\u0002\b\u000b\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0016\u0018\u0000 \u00172\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0001\u0017B\u001f\b\u0000\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0004\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0002¢\u0006\u0002\u0010\u0006J\u0013\u0010\r\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010H\u0096\u0002J\b\u0010\u0011\u001a\u00020\u0002H\u0016J\b\u0010\u0012\u001a\u00020\u000eH\u0016J\t\u0010\u0013\u001a\u00020\u0014H\u0096\u0002J\b\u0010\u0015\u001a\u00020\u0016H\u0016R\u0011\u0010\u0007\u001a\u00020\u0002¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\n\u001a\u00020\u0002¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\tR\u0011\u0010\u0005\u001a\u00020\u0002¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\t¨\u0006\u0018"}, m8860e = {"Lkotlin/ranges/IntProgression;", "", "", "start", "endInclusive", "step", "(III)V", "first", "getFirst", "()I", "last", "getLast", "getStep", "equals", "", "other", "", "hashCode", "isEmpty", "iterator", "Lkotlin/collections/IntIterator;", "toString", "", "Companion", "kotlin-stdlib"})
/* renamed from: z1.cmc */
/* loaded from: classes3.dex */
public class cmc implements Iterable<Integer>, KMarkers {

    /* renamed from: a */
    public static final C4987a f20836a = new C4987a(null);

    /* renamed from: b */
    private final int f20837b;

    /* renamed from: c */
    private final int f20838c;

    /* renamed from: d */
    private final int f20839d;

    public cmc(int i, int i2, int i3) {
        if (i3 == 0) {
            throw new IllegalArgumentException("Step must be non-zero.");
        } else if (i3 != Integer.MIN_VALUE) {
            this.f20837b = i;
            this.f20838c = progressionUtil.m5468a(i, i2, i3);
            this.f20839d = i3;
        } else {
            throw new IllegalArgumentException("Step must be greater than Int.MIN_VALUE to avoid overflow on negation.");
        }
    }

    /* renamed from: a */
    public final int m4833a() {
        return this.f20837b;
    }

    /* renamed from: b */
    public final int m4832b() {
        return this.f20838c;
    }

    /* renamed from: c */
    public final int m4831c() {
        return this.f20839d;
    }

    @NotNull
    /* renamed from: d */
    public cai iterator() {
        return new cmd(this.f20837b, this.f20838c, this.f20839d);
    }

    /* renamed from: e */
    public boolean mo4667e() {
        if (this.f20839d > 0) {
            if (this.f20837b > this.f20838c) {
                return true;
            }
        } else if (this.f20837b < this.f20838c) {
            return true;
        }
        return false;
    }

    public boolean equals(@dbs Object obj) {
        if (obj instanceof cmc) {
            if (!mo4667e() || !((cmc) obj).mo4667e()) {
                cmc cmcVar = (cmc) obj;
                if (!(this.f20837b == cmcVar.f20837b && this.f20838c == cmcVar.f20838c && this.f20839d == cmcVar.f20839d)) {
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
        return (((this.f20837b * 31) + this.f20838c) * 31) + this.f20839d;
    }

    @NotNull
    public String toString() {
        int i;
        StringBuilder sb;
        if (this.f20839d > 0) {
            sb = new StringBuilder();
            sb.append(this.f20837b);
            sb.append("..");
            sb.append(this.f20838c);
            sb.append(" step ");
            i = this.f20839d;
        } else {
            sb = new StringBuilder();
            sb.append(this.f20837b);
            sb.append(" downTo ");
            sb.append(this.f20838c);
            sb.append(" step ");
            i = -this.f20839d;
        }
        sb.append(i);
        return sb.toString();
    }

    /* compiled from: Progressions.kt */
    @Metadata(m8864a = 1, m8863b = {1, 1, 15}, m8862c = {1, 0, 3}, m8861d = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u001e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\u0006¨\u0006\t"}, m8860e = {"Lkotlin/ranges/IntProgression$Companion;", "", "()V", "fromClosedRange", "Lkotlin/ranges/IntProgression;", "rangeStart", "", "rangeEnd", "step", "kotlin-stdlib"})
    /* renamed from: z1.cmc$a */
    /* loaded from: classes3.dex */
    public static final class C4987a {
        private C4987a() {
        }

        public /* synthetic */ C4987a(DefaultConstructorMarker civVar) {
            this();
        }

        @NotNull
        /* renamed from: a */
        public final cmc m4829a(int i, int i2, int i3) {
            return new cmc(i, i2, i3);
        }
    }
}
