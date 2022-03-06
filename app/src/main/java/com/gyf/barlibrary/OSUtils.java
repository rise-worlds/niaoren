package com.gyf.barlibrary;

import android.text.TextUtils;

/* renamed from: com.gyf.barlibrary.k */
/* loaded from: classes.dex */
public class OSUtils {

    /* renamed from: a */
    private static final String f9368a = "ro.miui.ui.version.name";

    /* renamed from: b */
    private static final String f9369b = "ro.build.version.emui";

    /* renamed from: c */
    private static final String f9370c = "ro.build.display.id";

    /* renamed from: a */
    public static boolean m19909a() {
        return !TextUtils.isEmpty(m19908a(f9368a, ""));
    }

    /* renamed from: b */
    public static boolean m19907b() {
        String c = m19906c();
        if (c.isEmpty()) {
            return false;
        }
        try {
            return Integer.valueOf(c.substring(1)).intValue() >= 6;
        } catch (NumberFormatException unused) {
            return false;
        }
    }

    /* renamed from: c */
    public static String m19906c() {
        return m19909a() ? m19908a(f9368a, "") : "";
    }

    /* renamed from: d */
    public static boolean m19905d() {
        return !TextUtils.isEmpty(m19908a(f9369b, ""));
    }

    /* renamed from: e */
    public static String m19904e() {
        return m19905d() ? m19908a(f9369b, "") : "";
    }

    /* renamed from: f */
    public static boolean m19903f() {
        String e = m19904e();
        return "EmotionUI 3".equals(e) || e.contains("EmotionUI_3.1");
    }

    /* renamed from: g */
    public static boolean m19902g() {
        return m19904e().contains("EmotionUI_3.0");
    }

    /* renamed from: h */
    public static boolean m19901h() {
        return m19902g() || m19903f();
    }

    /* renamed from: i */
    public static boolean m19900i() {
        return m19896m().toLowerCase().contains("flyme");
    }

    /* renamed from: j */
    public static boolean m19899j() {
        int i;
        String l = m19897l();
        if (l.isEmpty()) {
            return false;
        }
        try {
            if (l.toLowerCase().contains("os")) {
                i = Integer.valueOf(l.substring(9, 10)).intValue();
            } else {
                i = Integer.valueOf(l.substring(6, 7)).intValue();
            }
            return i >= 4;
        } catch (NumberFormatException unused) {
            return false;
        }
    }

    /* renamed from: k */
    public static boolean m19898k() {
        int i;
        String l = m19897l();
        if (l.isEmpty()) {
            return false;
        }
        try {
            if (l.toLowerCase().contains("os")) {
                i = Integer.valueOf(l.substring(9, 10)).intValue();
            } else {
                i = Integer.valueOf(l.substring(6, 7)).intValue();
            }
            return i == 5;
        } catch (NumberFormatException unused) {
            return false;
        }
    }

    /* renamed from: l */
    public static String m19897l() {
        return m19900i() ? m19908a(f9370c, "") : "";
    }

    /* renamed from: m */
    private static String m19896m() {
        return m19908a(f9370c, "");
    }

    /* renamed from: a */
    private static String m19908a(String str, String str2) {
        try {
            Class<?> cls = Class.forName("android.os.SystemProperties");
            return (String) cls.getMethod("get", String.class, String.class).invoke(cls, str, str2);
        } catch (Exception e) {
            e.printStackTrace();
            return str2;
        }
    }
}
