package p110z1;

/* renamed from: z1.aiw */
/* loaded from: classes3.dex */
public class App64 {

    /* renamed from: a */
    private String f16034a;

    /* renamed from: b */
    private String f16035b;

    /* renamed from: a */
    public String m12975a() {
        return this.f16034a;
    }

    /* renamed from: a */
    public void m12974a(String str) {
        this.f16034a = str;
    }

    /* renamed from: b */
    public String m12973b() {
        return this.f16035b;
    }

    /* renamed from: b */
    public void m12972b(String str) {
        this.f16035b = str;
    }

    public boolean equals(Object obj) {
        if (obj instanceof App64) {
            return ((App64) obj).m12975a().equals(this.f16034a);
        }
        return super.equals(obj);
    }

    public String toString() {
        return "App64{pkg='" + this.f16034a + "', appName='" + this.f16035b + "'}";
    }
}
