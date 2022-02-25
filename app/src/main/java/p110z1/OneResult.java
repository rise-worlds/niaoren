package p110z1;

/* renamed from: z1.dbn */
/* loaded from: classes3.dex */
public class OneResult {

    /* renamed from: a */
    private final int f21288a;

    /* renamed from: b */
    private final Promise f21289b;

    /* renamed from: c */
    private final Object f21290c;

    public OneResult(int i, Promise dazVar, Object obj) {
        this.f21288a = i;
        this.f21289b = dazVar;
        this.f21290c = obj;
    }

    /* renamed from: a */
    public int m3253a() {
        return this.f21288a;
    }

    /* renamed from: b */
    public Promise m3252b() {
        return this.f21289b;
    }

    /* renamed from: c */
    public Object m3251c() {
        return this.f21290c;
    }

    public String toString() {
        return "OneResult [index=" + this.f21288a + ", promise=" + this.f21289b + ", result=" + this.f21290c + "]";
    }
}
