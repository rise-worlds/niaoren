package p110z1;

/* compiled from: VersionUtils.java */
/* renamed from: z1.uo */
/* loaded from: classes3.dex */
public class C5574uo {

    /* renamed from: a */
    private static final String f23467a = "VERSION__4.46__";

    /* renamed from: b */
    private static C5570ui f23468b = null;

    /* renamed from: c */
    private static boolean f23469c = false;

    /* renamed from: d */
    private static String f23470d = "VERSION__4.46__";

    private C5574uo() {
    }

    /* renamed from: a */
    public static final void m519a(String str) {
        m518a("core", f23470d, "jdbc", str);
    }

    /* renamed from: b */
    public static final void m514b(String str) {
        m518a("core", f23470d, "android", str);
    }

    /* renamed from: a */
    public static String m520a() {
        return f23470d;
    }

    /* renamed from: a */
    static void m516a(boolean z) {
        f23469c = z;
    }

    /* renamed from: a */
    private static void m518a(String str, String str2, String str3, String str4) {
        if (str2 == null) {
            if (str4 != null) {
                m517a((Throwable) null, "Unknown version", " for {}, version for {} is '{}'", new Object[]{str, str3, str4});
            }
        } else if (str4 == null) {
            m517a((Throwable) null, "Unknown version", " for {}, version for {} is '{}'", new Object[]{str3, str, str2});
        } else if (!str2.equals(str4)) {
            m517a((Throwable) null, "Mismatched versions", ": {} is '{}', while {} is '{}'", new Object[]{str, str2, str3, str4});
        }
    }

    /* renamed from: a */
    private static void m517a(Throwable th, String str, String str2, Object[] objArr) {
        C5570ui b = m515b();
        b.m567d(th, str + str2, objArr);
        if (f23469c) {
            throw new IllegalStateException("See error log for details:" + str);
        }
    }

    /* renamed from: b */
    private static C5570ui m515b() {
        if (f23468b == null) {
            f23468b = LoggerFactory.m545a(C5574uo.class);
        }
        return f23468b;
    }
}
