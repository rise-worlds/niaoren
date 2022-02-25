package p110z1;

/* renamed from: z1.dbm */
/* loaded from: classes3.dex */
public class OneReject {

    /* renamed from: a */
    private final int f21285a;

    /* renamed from: b */
    private final Promise f21286b;

    /* renamed from: c */
    private final Object f21287c;

    public OneReject(int i, Promise dazVar, Object obj) {
        this.f21285a = i;
        this.f21286b = dazVar;
        this.f21287c = obj;
    }

    /* renamed from: a */
    public int m3256a() {
        return this.f21285a;
    }

    /* renamed from: b */
    public Promise m3255b() {
        return this.f21286b;
    }

    /* renamed from: c */
    public Object m3254c() {
        return this.f21287c;
    }

    public String toString() {
        return "OneReject [index=" + this.f21285a + ", promise=" + this.f21286b + ", reject=" + this.f21287c + "]";
    }
}
