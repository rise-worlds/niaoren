package p110z1;

import java.util.ArrayList;
import java.util.List;

/* renamed from: z1.kv */
/* loaded from: classes3.dex */
final class ExpandedRow {

    /* renamed from: a */
    final List<ExpandedPair> f22265a;

    /* renamed from: b */
    final int f22266b;

    /* renamed from: c */
    private final boolean f22267c = false;

    /* JADX INFO: Access modifiers changed from: package-private */
    public ExpandedRow(List<ExpandedPair> list, int i) {
        this.f22265a = new ArrayList(list);
        this.f22266b = i;
    }

    /* renamed from: a */
    private List<ExpandedPair> m2164a() {
        return this.f22265a;
    }

    /* renamed from: b */
    private int m2162b() {
        return this.f22266b;
    }

    /* renamed from: c */
    private boolean m2161c() {
        return this.f22267c;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public final boolean m2163a(List<ExpandedPair> list) {
        return this.f22265a.equals(list);
    }

    public final String toString() {
        return "{ " + this.f22265a + " }";
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof ExpandedRow)) {
            return false;
        }
        ExpandedRow kvVar = (ExpandedRow) obj;
        return this.f22265a.equals(kvVar.f22265a) && this.f22267c == kvVar.f22267c;
    }

    public final int hashCode() {
        return this.f22265a.hashCode() ^ Boolean.valueOf(this.f22267c).hashCode();
    }
}
