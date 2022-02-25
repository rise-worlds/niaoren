package com.blankj.utilcode.util;

import android.os.Build;
import android.os.Environment;
import android.text.TextUtils;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Properties;
import p110z1.C4963cj;

/* renamed from: com.blankj.utilcode.util.ap */
/* loaded from: classes.dex */
public final class RomUtils {

    /* renamed from: A */
    private static final String f6705A = "ro.build.MiFavor_version";

    /* renamed from: B */
    private static final String f6706B = "ro.rom.version";

    /* renamed from: C */
    private static final String f6707C = "ro.build.rom.id";

    /* renamed from: D */
    private static final String f6708D = "unknown";

    /* renamed from: u */
    private static final String f6730u = "ro.build.version.emui";

    /* renamed from: v */
    private static final String f6731v = "ro.vivo.os.build.display.id";

    /* renamed from: w */
    private static final String f6732w = "ro.build.version.incremental";

    /* renamed from: x */
    private static final String f6733x = "ro.build.version.opporom";

    /* renamed from: y */
    private static final String f6734y = "ro.letv.release.version";

    /* renamed from: z */
    private static final String f6735z = "ro.build.uiversion";

    /* renamed from: a */
    private static final String[] f6710a = {"huawei"};

    /* renamed from: b */
    private static final String[] f6711b = {"vivo"};

    /* renamed from: c */
    private static final String[] f6712c = {"xiaomi"};

    /* renamed from: d */
    private static final String[] f6713d = {"oppo"};

    /* renamed from: e */
    private static final String[] f6714e = {"leeco", "letv"};

    /* renamed from: f */
    private static final String[] f6715f = {"360", "qiku"};

    /* renamed from: g */
    private static final String[] f6716g = {"zte"};

    /* renamed from: h */
    private static final String[] f6717h = {"oneplus"};

    /* renamed from: i */
    private static final String[] f6718i = {"nubia"};

    /* renamed from: j */
    private static final String[] f6719j = {"coolpad", "yulong"};

    /* renamed from: k */
    private static final String[] f6720k = {"lg", "lge"};

    /* renamed from: l */
    private static final String[] f6721l = {"google"};

    /* renamed from: m */
    private static final String[] f6722m = {"samsung"};

    /* renamed from: n */
    private static final String[] f6723n = {"meizu"};

    /* renamed from: o */
    private static final String[] f6724o = {"lenovo"};

    /* renamed from: p */
    private static final String[] f6725p = {"smartisan"};

    /* renamed from: q */
    private static final String[] f6726q = {"htc"};

    /* renamed from: r */
    private static final String[] f6727r = {"sony"};

    /* renamed from: s */
    private static final String[] f6728s = {"gionee", "amigo"};

    /* renamed from: t */
    private static final String[] f6729t = {"motorola"};

    /* renamed from: E */
    private static C0982a f6709E = null;

    private RomUtils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    /* renamed from: a */
    public static boolean m23443a() {
        return f6710a[0].equals(m23417u().f6736a);
    }

    /* renamed from: b */
    public static boolean m23440b() {
        return f6711b[0].equals(m23417u().f6736a);
    }

    /* renamed from: c */
    public static boolean m23438c() {
        return f6712c[0].equals(m23417u().f6736a);
    }

    /* renamed from: d */
    public static boolean m23436d() {
        return f6713d[0].equals(m23417u().f6736a);
    }

    /* renamed from: e */
    public static boolean m23434e() {
        return f6714e[0].equals(m23417u().f6736a);
    }

    /* renamed from: f */
    public static boolean m23432f() {
        return f6715f[0].equals(m23417u().f6736a);
    }

    /* renamed from: g */
    public static boolean m23431g() {
        return f6716g[0].equals(m23417u().f6736a);
    }

    /* renamed from: h */
    public static boolean m23430h() {
        return f6717h[0].equals(m23417u().f6736a);
    }

    /* renamed from: i */
    public static boolean m23429i() {
        return f6718i[0].equals(m23417u().f6736a);
    }

    /* renamed from: j */
    public static boolean m23428j() {
        return f6719j[0].equals(m23417u().f6736a);
    }

    /* renamed from: k */
    public static boolean m23427k() {
        return f6720k[0].equals(m23417u().f6736a);
    }

    /* renamed from: l */
    public static boolean m23426l() {
        return f6721l[0].equals(m23417u().f6736a);
    }

    /* renamed from: m */
    public static boolean m23425m() {
        return f6722m[0].equals(m23417u().f6736a);
    }

    /* renamed from: n */
    public static boolean m23424n() {
        return f6723n[0].equals(m23417u().f6736a);
    }

    /* renamed from: o */
    public static boolean m23423o() {
        return f6724o[0].equals(m23417u().f6736a);
    }

    /* renamed from: p */
    public static boolean m23422p() {
        return f6725p[0].equals(m23417u().f6736a);
    }

    /* renamed from: q */
    public static boolean m23421q() {
        return f6726q[0].equals(m23417u().f6736a);
    }

    /* renamed from: r */
    public static boolean m23420r() {
        return f6727r[0].equals(m23417u().f6736a);
    }

    /* renamed from: s */
    public static boolean m23419s() {
        return f6728s[0].equals(m23417u().f6736a);
    }

    /* renamed from: t */
    public static boolean m23418t() {
        return f6729t[0].equals(m23417u().f6736a);
    }

    /* renamed from: u */
    public static C0982a m23417u() {
        C0982a aVar = f6709E;
        if (aVar != null) {
            return aVar;
        }
        f6709E = new C0982a();
        String w = m23415w();
        String v = m23416v();
        if (m23441a(w, v, f6710a)) {
            f6709E.f6736a = f6710a[0];
            String a = m23442a(f6730u);
            String[] split = a.split("_");
            if (split.length > 1) {
                f6709E.f6737b = split[1];
            } else {
                f6709E.f6737b = a;
            }
            return f6709E;
        } else if (m23441a(w, v, f6711b)) {
            f6709E.f6736a = f6711b[0];
            f6709E.f6737b = m23442a(f6731v);
            return f6709E;
        } else if (m23441a(w, v, f6712c)) {
            f6709E.f6736a = f6712c[0];
            f6709E.f6737b = m23442a(f6732w);
            return f6709E;
        } else if (m23441a(w, v, f6713d)) {
            f6709E.f6736a = f6713d[0];
            f6709E.f6737b = m23442a(f6733x);
            return f6709E;
        } else if (m23441a(w, v, f6714e)) {
            f6709E.f6736a = f6714e[0];
            f6709E.f6737b = m23442a(f6734y);
            return f6709E;
        } else if (m23441a(w, v, f6715f)) {
            f6709E.f6736a = f6715f[0];
            f6709E.f6737b = m23442a(f6735z);
            return f6709E;
        } else if (m23441a(w, v, f6716g)) {
            f6709E.f6736a = f6716g[0];
            f6709E.f6737b = m23442a(f6705A);
            return f6709E;
        } else if (m23441a(w, v, f6717h)) {
            f6709E.f6736a = f6717h[0];
            f6709E.f6737b = m23442a(f6706B);
            return f6709E;
        } else if (m23441a(w, v, f6718i)) {
            f6709E.f6736a = f6718i[0];
            f6709E.f6737b = m23442a(f6707C);
            return f6709E;
        } else {
            if (m23441a(w, v, f6719j)) {
                f6709E.f6736a = f6719j[0];
            } else if (m23441a(w, v, f6720k)) {
                f6709E.f6736a = f6720k[0];
            } else if (m23441a(w, v, f6721l)) {
                f6709E.f6736a = f6721l[0];
            } else if (m23441a(w, v, f6722m)) {
                f6709E.f6736a = f6722m[0];
            } else if (m23441a(w, v, f6723n)) {
                f6709E.f6736a = f6723n[0];
            } else if (m23441a(w, v, f6724o)) {
                f6709E.f6736a = f6724o[0];
            } else if (m23441a(w, v, f6725p)) {
                f6709E.f6736a = f6725p[0];
            } else if (m23441a(w, v, f6726q)) {
                f6709E.f6736a = f6726q[0];
            } else if (m23441a(w, v, f6727r)) {
                f6709E.f6736a = f6727r[0];
            } else if (m23441a(w, v, f6728s)) {
                f6709E.f6736a = f6728s[0];
            } else if (m23441a(w, v, f6729t)) {
                f6709E.f6736a = f6729t[0];
            } else {
                f6709E.f6736a = v;
            }
            f6709E.f6737b = m23442a("");
            return f6709E;
        }
    }

    /* renamed from: a */
    private static boolean m23441a(String str, String str2, String... strArr) {
        for (String str3 : strArr) {
            if (str.contains(str3) || str2.contains(str3)) {
                return true;
            }
        }
        return false;
    }

    /* renamed from: v */
    private static String m23416v() {
        try {
            String str = Build.MANUFACTURER;
            return !TextUtils.isEmpty(str) ? str.toLowerCase() : "unknown";
        } catch (Throwable unused) {
            return "unknown";
        }
    }

    /* renamed from: w */
    private static String m23415w() {
        try {
            String str = Build.BRAND;
            return !TextUtils.isEmpty(str) ? str.toLowerCase() : "unknown";
        } catch (Throwable unused) {
            return "unknown";
        }
    }

    /* renamed from: a */
    private static String m23442a(String str) {
        String str2 = "";
        if (!TextUtils.isEmpty(str)) {
            str2 = m23439b(str);
        }
        if (TextUtils.isEmpty(str2) || str2.equals("unknown")) {
            try {
                String str3 = Build.DISPLAY;
                if (!TextUtils.isEmpty(str3)) {
                    str2 = str3.toLowerCase();
                }
            } catch (Throwable unused) {
            }
        }
        return TextUtils.isEmpty(str2) ? "unknown" : str2;
    }

    /* renamed from: b */
    private static String m23439b(String str) {
        String c = m23437c(str);
        if (!TextUtils.isEmpty(c)) {
            return c;
        }
        String d = m23435d(str);
        return (TextUtils.isEmpty(d) && Build.VERSION.SDK_INT < 28) ? m23433e(str) : d;
    }

    /* renamed from: c */
    private static String m23437c(String str) {
        Throwable th;
        BufferedReader bufferedReader;
        String readLine;
        BufferedReader bufferedReader2 = null;
        try {
            try {
                Runtime runtime = Runtime.getRuntime();
                bufferedReader = new BufferedReader(new InputStreamReader(runtime.exec("getprop " + str).getInputStream()), 1024);
            } catch (IOException unused) {
                return "";
            }
        } catch (IOException unused2) {
        } catch (Throwable th2) {
            th = th2;
        }
        try {
            readLine = bufferedReader.readLine();
        } catch (IOException unused3) {
            bufferedReader2 = bufferedReader;
            if (bufferedReader2 == null) {
                return "";
            }
            bufferedReader2.close();
            return "";
        } catch (Throwable th3) {
            th = th3;
            bufferedReader2 = bufferedReader;
            if (bufferedReader2 != null) {
                try {
                    bufferedReader2.close();
                } catch (IOException unused4) {
                }
            }
            throw th;
        }
        if (readLine != null) {
            try {
                bufferedReader.close();
            } catch (IOException unused5) {
            }
            return readLine;
        }
        bufferedReader.close();
        return "";
    }

    /* renamed from: d */
    private static String m23435d(String str) {
        try {
            Properties properties = new Properties();
            properties.load(new FileInputStream(new File(Environment.getRootDirectory(), "build.prop")));
            return properties.getProperty(str, "");
        } catch (Exception unused) {
            return "";
        }
    }

    /* renamed from: e */
    private static String m23433e(String str) {
        try {
            Class<?> cls = Class.forName("android.os.SystemProperties");
            return (String) cls.getMethod("get", String.class, String.class).invoke(cls, str, "");
        } catch (Exception unused) {
            return "";
        }
    }

    /* compiled from: RomUtils.java */
    /* renamed from: com.blankj.utilcode.util.ap$a */
    /* loaded from: classes.dex */
    public static class C0982a {

        /* renamed from: a */
        private String f6736a;

        /* renamed from: b */
        private String f6737b;

        /* renamed from: a */
        public String m23414a() {
            return this.f6736a;
        }

        /* renamed from: b */
        public String m23411b() {
            return this.f6737b;
        }

        public String toString() {
            return "RomInfo{name=" + this.f6736a + ", version=" + this.f6737b + C4963cj.f20747d;
        }
    }
}
