package p110z1;

import android.os.Build;
import android.text.TextUtils;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.HashMap;
import java.util.Map;

/* compiled from: RomUtils.java */
/* renamed from: z1.ann */
/* loaded from: classes3.dex */
public class ann {

    /* renamed from: a */
    public static final String f16787a = "MIUI";

    /* renamed from: b */
    public static final String f16788b = "EMUI";

    /* renamed from: c */
    public static final String f16789c = "VIVO";

    /* renamed from: d */
    public static final String f16790d = "OPPO";

    /* renamed from: e */
    static final String f16791e = "FLYME";

    /* renamed from: f */
    public static final String f16792f = "SMARTISAN";

    /* renamed from: g */
    public static final String f16793g = "QIKU";

    /* renamed from: h */
    public static final String f16794h = "LETV";

    /* renamed from: i */
    public static final String f16795i = "LENOVO";

    /* renamed from: j */
    static final String f16796j = "NUBIA";

    /* renamed from: k */
    public static final String f16797k = "ZTE";

    /* renamed from: l */
    public static final String f16798l = "COOLPAD";

    /* renamed from: m */
    static final String f16799m = "UNKNOWN";

    /* renamed from: n */
    private static final String f16800n = "RomUtils";

    /* renamed from: o */
    private static Map<String, String> f16801o = new HashMap();

    /* renamed from: p */
    private static final String f16802p = "ro.miui.ui.version.name";

    /* renamed from: q */
    private static final String f16803q = "ro.build.version.emui";

    /* renamed from: r */
    private static final String f16804r = "ro.vivo.os.version";

    /* renamed from: s */
    private static final String f16805s = "ro.build.version.opporom";

    /* renamed from: t */
    private static final String f16806t = "ro.build.display.id";

    /* renamed from: u */
    private static final String f16807u = "ro.smartisan.version";

    /* renamed from: v */
    private static final String f16808v = "ro.letv.eui";

    /* renamed from: w */
    private static final String f16809w = "ro.lenovo.lvp.version";

    /* compiled from: RomUtils.java */
    @Target({ElementType.METHOD})
    @Retention(RetentionPolicy.SOURCE)
    /* renamed from: z1.ann$a */
    /* loaded from: classes3.dex */
    public @interface AbstractC3718a {
    }

    /* renamed from: a */
    public static void m12275a() {
        f16801o.put(f16787a, "小米");
        f16801o.put(f16788b, "华为");
        f16801o.put(f16789c, f16789c);
        f16801o.put(f16790d, f16790d);
        f16801o.put(f16791e, "魅族");
        f16801o.put(f16792f, "锤子");
        f16801o.put(f16793g, "奇酷");
        f16801o.put(f16794h, "乐视");
        f16801o.put(f16795i, "联想");
        f16801o.put(f16796j, "努比亚");
        f16801o.put(f16797k, "中兴");
        f16801o.put(f16798l, "酷派");
    }

    /* renamed from: b */
    public static Map<String, String> m12273b() {
        return f16801o;
    }

    /* JADX WARN: Not initialized variable reg: 2, insn: 0x006a: MOVE  (r0 I:??[OBJECT, ARRAY]) = (r2 I:??[OBJECT, ARRAY]), block:B:20:0x006a */
    /* JADX WARN: Removed duplicated region for block: B:28:0x006d A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static java.lang.String m12274a(java.lang.String r6) {
        /*
            r0 = 0
            java.lang.Runtime r1 = java.lang.Runtime.getRuntime()     // Catch: all -> 0x0040, IOException -> 0x0042
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch: all -> 0x0040, IOException -> 0x0042
            r2.<init>()     // Catch: all -> 0x0040, IOException -> 0x0042
            java.lang.String r3 = "getprop "
            r2.append(r3)     // Catch: all -> 0x0040, IOException -> 0x0042
            r2.append(r6)     // Catch: all -> 0x0040, IOException -> 0x0042
            java.lang.String r2 = r2.toString()     // Catch: all -> 0x0040, IOException -> 0x0042
            java.lang.Process r1 = r1.exec(r2)     // Catch: all -> 0x0040, IOException -> 0x0042
            java.io.BufferedReader r2 = new java.io.BufferedReader     // Catch: all -> 0x0040, IOException -> 0x0042
            java.io.InputStreamReader r3 = new java.io.InputStreamReader     // Catch: all -> 0x0040, IOException -> 0x0042
            java.io.InputStream r1 = r1.getInputStream()     // Catch: all -> 0x0040, IOException -> 0x0042
            r3.<init>(r1)     // Catch: all -> 0x0040, IOException -> 0x0042
            r1 = 1024(0x400, float:1.435E-42)
            r2.<init>(r3, r1)     // Catch: all -> 0x0040, IOException -> 0x0042
            java.lang.String r1 = r2.readLine()     // Catch: IOException -> 0x003e, all -> 0x0069
            r2.close()     // Catch: IOException -> 0x003e, all -> 0x0069
            r2.close()     // Catch: IOException -> 0x0035
            goto L_0x003d
        L_0x0035:
            r6 = move-exception
            java.lang.String r0 = "RomUtils"
            java.lang.String r2 = "Exception while closing InputStream"
            android.util.Log.e(r0, r2, r6)
        L_0x003d:
            return r1
        L_0x003e:
            r1 = move-exception
            goto L_0x0044
        L_0x0040:
            r6 = move-exception
            goto L_0x006b
        L_0x0042:
            r1 = move-exception
            r2 = r0
        L_0x0044:
            java.lang.String r3 = "RomUtils"
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch: all -> 0x0069
            r4.<init>()     // Catch: all -> 0x0069
            java.lang.String r5 = "Unable to read sysprop "
            r4.append(r5)     // Catch: all -> 0x0069
            r4.append(r6)     // Catch: all -> 0x0069
            java.lang.String r6 = r4.toString()     // Catch: all -> 0x0069
            android.util.Log.e(r3, r6, r1)     // Catch: all -> 0x0069
            if (r2 == 0) goto L_0x0068
            r2.close()     // Catch: IOException -> 0x0060
            goto L_0x0068
        L_0x0060:
            r6 = move-exception
            java.lang.String r1 = "RomUtils"
            java.lang.String r2 = "Exception while closing InputStream"
            android.util.Log.e(r1, r2, r6)
        L_0x0068:
            return r0
        L_0x0069:
            r6 = move-exception
            r0 = r2
        L_0x006b:
            if (r0 == 0) goto L_0x0079
            r0.close()     // Catch: IOException -> 0x0071
            goto L_0x0079
        L_0x0071:
            r0 = move-exception
            java.lang.String r1 = "RomUtils"
            java.lang.String r2 = "Exception while closing InputStream"
            android.util.Log.e(r1, r2, r0)
        L_0x0079:
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: p110z1.ann.m12274a(java.lang.String):java.lang.String");
    }

    /* renamed from: c */
    public static String m12272c() {
        return m12271d() ? f16787a : m12270e() ? f16788b : m12269f() ? f16789c : m12268g() ? f16790d : m12267h() ? f16791e : m12266i() ? f16792f : m12265j() ? f16793g : m12264k() ? f16794h : m12263l() ? f16795i : m12261n() ? f16797k : m12262m() ? f16798l : f16799m;
    }

    /* renamed from: d */
    public static boolean m12271d() {
        return !TextUtils.isEmpty(m12274a(f16802p));
    }

    /* renamed from: e */
    public static boolean m12270e() {
        return !TextUtils.isEmpty(m12274a(f16803q));
    }

    /* renamed from: f */
    public static boolean m12269f() {
        return !TextUtils.isEmpty(m12274a(f16804r));
    }

    /* renamed from: g */
    public static boolean m12268g() {
        return !TextUtils.isEmpty(m12274a(f16805s));
    }

    /* renamed from: h */
    public static boolean m12267h() {
        String a = m12274a(f16806t);
        return !TextUtils.isEmpty(a) && a.toUpperCase().contains(f16791e);
    }

    /* renamed from: i */
    public static boolean m12266i() {
        return !TextUtils.isEmpty(m12274a(f16807u));
    }

    /* renamed from: j */
    public static boolean m12265j() {
        String str = Build.MANUFACTURER;
        return !TextUtils.isEmpty(str) && str.toUpperCase().contains(f16793g);
    }

    /* renamed from: k */
    public static boolean m12264k() {
        return !TextUtils.isEmpty(m12274a(f16808v));
    }

    /* renamed from: l */
    public static boolean m12263l() {
        return !TextUtils.isEmpty(m12274a(f16809w));
    }

    /* renamed from: m */
    public static boolean m12262m() {
        String str = Build.MODEL;
        String str2 = Build.FINGERPRINT;
        return (!TextUtils.isEmpty(str) && str.toLowerCase().contains(f16798l)) || (!TextUtils.isEmpty(str2) && str2.toLowerCase().contains(f16798l));
    }

    /* renamed from: n */
    public static boolean m12261n() {
        String str = Build.MANUFACTURER;
        String str2 = Build.FINGERPRINT;
        return (!TextUtils.isEmpty(str) && (str2.toLowerCase().contains(f16796j) || str2.toLowerCase().contains(f16797k))) || (!TextUtils.isEmpty(str2) && (str2.toLowerCase().contains(f16796j) || str2.toLowerCase().contains(f16797k)));
    }

    /* renamed from: o */
    public static boolean m12260o() {
        return m12271d() || m12270e() || m12267h() || m12265j() || m12268g() || m12269f() || m12264k() || m12261n() || m12263l() || m12262m();
    }
}
