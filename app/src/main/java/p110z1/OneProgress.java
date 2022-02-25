package p110z1;

/* renamed from: z1.dbl */
/* loaded from: classes3.dex */
public class OneProgress extends MasterProgress {

    /* renamed from: a */
    private final int f21282a;

    /* renamed from: b */
    private final Promise f21283b;

    /* renamed from: c */
    private final Object f21284c;

    public OneProgress(int i, int i2, int i3, int i4, Promise dazVar, Object obj) {
        super(i, i2, i3);
        this.f21282a = i4;
        this.f21283b = dazVar;
        this.f21284c = obj;
    }

    /* renamed from: d */
    public int m3259d() {
        return this.f21282a;
    }

    /* renamed from: e */
    public Promise m3258e() {
        return this.f21283b;
    }

    /* renamed from: f */
    public Object m3257f() {
        return this.f21284c;
    }

    @Override // p110z1.MasterProgress
    public String toString() {
        return "OneProgress [index=" + this.f21282a + ", promise=" + this.f21283b + ", progress=" + this.f21284c + ", getDone()=" + m3265a() + ", getFail()=" + m3264b() + ", getTotal()=" + m3263c() + "]";
    }
}
