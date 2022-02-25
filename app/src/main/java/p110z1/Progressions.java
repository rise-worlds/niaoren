package p110z1;

@Metadata(m8864a = 1, m8863b = {1, 1, 15}, m8862c = {1, 0, 3}, m8861d = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u001c\n\u0002\u0010\f\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\t\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0016\u0018\u0000 \u00192\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0001\u0019B\u001f\b\u0000\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0004\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007J\u0013\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012H\u0096\u0002J\b\u0010\u0013\u001a\u00020\u0006H\u0016J\b\u0010\u0014\u001a\u00020\u0010H\u0016J\t\u0010\u0015\u001a\u00020\u0016H\u0096\u0002J\b\u0010\u0017\u001a\u00020\u0018H\u0016R\u0011\u0010\b\u001a\u00020\u0002¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u000b\u001a\u00020\u0002¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\nR\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000e¨\u0006\u001a"}, m8860e = {"Lkotlin/ranges/CharProgression;", "", "", "start", "endInclusive", "step", "", "(CCI)V", "first", "getFirst", "()C", "last", "getLast", "getStep", "()I", "equals", "", "other", "", "hashCode", "isEmpty", "iterator", "Lkotlin/collections/CharIterator;", "toString", "", "Companion", "kotlin-stdlib"})
/* renamed from: z1.clu */
/* loaded from: classes3.dex */
public class Progressions implements Iterable<Character>, KMarkers {

    /* renamed from: a */
    public static final C4982a f20817a = new C4982a(null);

    /* renamed from: b */
    private final char f20818b;

    /* renamed from: c */
    private final char f20819c;

    /* renamed from: d */
    private final int f20820d;

    public Progressions(char c, char c2, int i) {
        if (i == 0) {
            throw new IllegalArgumentException("Step must be non-zero.");
        } else if (i != Integer.MIN_VALUE) {
            this.f20818b = c;
            this.f20819c = (char) progressionUtil.m5468a((int) c, (int) c2, i);
            this.f20820d = i;
        } else {
            throw new IllegalArgumentException("Step must be greater than Int.MIN_VALUE to avoid overflow on negation.");
        }
    }

    /* renamed from: a */
    public final char m4861a() {
        return this.f20818b;
    }

    /* renamed from: b */
    public final char m4860b() {
        return this.f20819c;
    }

    /* renamed from: c */
    public final int m4859c() {
        return this.f20820d;
    }

    @NotNull
    /* renamed from: d */
    public bzj iterator() {
        return new ProgressionIterators(this.f20818b, this.f20819c, this.f20820d);
    }

    /* renamed from: e */
    public boolean mo4667e() {
        if (this.f20820d > 0) {
            if (this.f20818b > this.f20819c) {
                return true;
            }
        } else if (this.f20818b < this.f20819c) {
            return true;
        }
        return false;
    }

    public boolean equals(@dbs Object obj) {
        if (obj instanceof Progressions) {
            if (!mo4667e() || !((Progressions) obj).mo4667e()) {
                Progressions cluVar = (Progressions) obj;
                if (!(this.f20818b == cluVar.f20818b && this.f20819c == cluVar.f20819c && this.f20820d == cluVar.f20820d)) {
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
        return (((this.f20818b * 31) + this.f20819c) * 31) + this.f20820d;
    }

    @NotNull
    public String toString() {
        int i;
        StringBuilder sb;
        if (this.f20820d > 0) {
            sb = new StringBuilder();
            sb.append(this.f20818b);
            sb.append("..");
            sb.append(this.f20819c);
            sb.append(" step ");
            i = this.f20820d;
        } else {
            sb = new StringBuilder();
            sb.append(this.f20818b);
            sb.append(" downTo ");
            sb.append(this.f20819c);
            sb.append(" step ");
            i = -this.f20820d;
        }
        sb.append(i);
        return sb.toString();
    }

    /* compiled from: Progressions.kt */
    @Metadata(m8864a = 1, m8863b = {1, 1, 15}, m8862c = {1, 0, 3}, m8861d = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\f\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u001e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\t¨\u0006\n"}, m8860e = {"Lkotlin/ranges/CharProgression$Companion;", "", "()V", "fromClosedRange", "Lkotlin/ranges/CharProgression;", "rangeStart", "", "rangeEnd", "step", "", "kotlin-stdlib"})
    /* renamed from: z1.clu$a */
    /* loaded from: classes3.dex */
    public static final class C4982a {
        private C4982a() {
        }

        public /* synthetic */ C4982a(DefaultConstructorMarker civVar) {
            this();
        }

        @NotNull
        /* renamed from: a */
        public final Progressions m4857a(char c, char c2, int i) {
            return new Progressions(c, c2, i);
        }
    }
}
