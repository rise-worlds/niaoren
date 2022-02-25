package p110z1;

/* renamed from: z1.qb */
/* loaded from: classes3.dex */
public final class JavaVersion {

    /* renamed from: a */
    private static final int f22856a = m1374c();

    /* renamed from: c */
    private static int m1374c() {
        return m1377a(System.getProperty("java.version"));
    }

    /* renamed from: a */
    static int m1377a(String str) {
        int b = m1375b(str);
        if (b == -1) {
            b = m1373c(str);
        }
        if (b == -1) {
            return 6;
        }
        return b;
    }

    /* renamed from: b */
    private static int m1375b(String str) {
        try {
            String[] split = str.split("[._]");
            int parseInt = Integer.parseInt(split[0]);
            return (parseInt != 1 || split.length <= 1) ? parseInt : Integer.parseInt(split[1]);
        } catch (NumberFormatException unused) {
            return -1;
        }
    }

    /* renamed from: c */
    private static int m1373c(String str) {
        try {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < str.length(); i++) {
                char charAt = str.charAt(i);
                if (!Character.isDigit(charAt)) {
                    break;
                }
                sb.append(charAt);
            }
            return Integer.parseInt(sb.toString());
        } catch (NumberFormatException unused) {
            return -1;
        }
    }

    /* renamed from: a */
    public static int m1378a() {
        return f22856a;
    }

    /* renamed from: b */
    public static boolean m1376b() {
        return f22856a >= 9;
    }

    private JavaVersion() {
    }
}
