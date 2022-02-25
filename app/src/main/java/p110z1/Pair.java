package p110z1;

/* renamed from: z1.ail */
/* loaded from: classes3.dex */
final class Pair<A, B> {

    /* renamed from: a */
    private final A f15968a;

    /* renamed from: b */
    private final B f15969b;

    private Pair(A a, B b) {
        this.f15968a = a;
        this.f15969b = b;
    }

    /* renamed from: a */
    public static <A, B> Pair<A, B> m13048a(A a, B b) {
        return new Pair<>(a, b);
    }

    /* renamed from: a */
    public A m13049a() {
        return this.f15968a;
    }

    /* renamed from: b */
    public B m13047b() {
        return this.f15969b;
    }

    public int hashCode() {
        A a = this.f15968a;
        int i = 0;
        int hashCode = ((a == null ? 0 : a.hashCode()) + 31) * 31;
        B b = this.f15969b;
        if (b != null) {
            i = b.hashCode();
        }
        return hashCode + i;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Pair ailVar = (Pair) obj;
        A a = this.f15968a;
        if (a == null) {
            if (ailVar.f15968a != null) {
                return false;
            }
        } else if (!a.equals(ailVar.f15968a)) {
            return false;
        }
        B b = this.f15969b;
        if (b == null) {
            if (ailVar.f15969b != null) {
                return false;
            }
        } else if (!b.equals(ailVar.f15969b)) {
            return false;
        }
        return true;
    }
}
