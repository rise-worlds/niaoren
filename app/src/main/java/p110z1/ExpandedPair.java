package p110z1;

/* renamed from: z1.ku */
/* loaded from: classes3.dex */
final class ExpandedPair {

    /* renamed from: a */
    final DataCharacter f22261a;

    /* renamed from: b */
    final DataCharacter f22262b;

    /* renamed from: c */
    final FinderPattern f22263c;

    /* renamed from: d */
    private final boolean f22264d = true;

    /* JADX INFO: Access modifiers changed from: package-private */
    public ExpandedPair(DataCharacter kxVar, DataCharacter kxVar2, FinderPattern kyVar) {
        this.f22261a = kxVar;
        this.f22262b = kxVar2;
        this.f22263c = kyVar;
    }

    /* renamed from: a */
    private boolean m2171a() {
        return this.f22264d;
    }

    /* renamed from: b */
    private DataCharacter m2168b() {
        return this.f22261a;
    }

    /* renamed from: c */
    private DataCharacter m2167c() {
        return this.f22262b;
    }

    /* renamed from: d */
    private FinderPattern m2166d() {
        return this.f22263c;
    }

    /* renamed from: e */
    private boolean m2165e() {
        return this.f22262b == null;
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("[ ");
        sb.append(this.f22261a);
        sb.append(" , ");
        sb.append(this.f22262b);
        sb.append(" : ");
        FinderPattern kyVar = this.f22263c;
        sb.append(kyVar == null ? "null" : Integer.valueOf(kyVar.f22287a));
        sb.append(" ]");
        return sb.toString();
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof ExpandedPair)) {
            return false;
        }
        ExpandedPair kuVar = (ExpandedPair) obj;
        return m2169a(this.f22261a, kuVar.f22261a) && m2169a(this.f22262b, kuVar.f22262b) && m2169a(this.f22263c, kuVar.f22263c);
    }

    /* renamed from: a */
    private static boolean m2169a(Object obj, Object obj2) {
        if (obj == null) {
            return obj2 == null;
        }
        return obj.equals(obj2);
    }

    public final int hashCode() {
        return (m2170a(this.f22261a) ^ m2170a(this.f22262b)) ^ m2170a(this.f22263c);
    }

    /* renamed from: a */
    private static int m2170a(Object obj) {
        if (obj == null) {
            return 0;
        }
        return obj.hashCode();
    }
}
