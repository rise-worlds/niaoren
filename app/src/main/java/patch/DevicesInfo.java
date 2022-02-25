package patch;

import android.os.Build;

/* renamed from: patch.a */
/* loaded from: classes2.dex */
public class DevicesInfo {

    /* renamed from: a */
    private static final int f15006a = Build.VERSION.SDK_INT;

    /* renamed from: b */
    private static final String f15007b = Build.PRODUCT.toLowerCase();

    /* renamed from: c */
    private static final String f15008c = Build.MODEL.toLowerCase();

    /* renamed from: d */
    private static final String f15009d = Build.BRAND.toLowerCase();

    /* renamed from: e */
    private static final String f15010e = Build.MANUFACTURER.toLowerCase();

    /* renamed from: f */
    private static final String f15011f = Build.HOST.toLowerCase();

    /* renamed from: g */
    private static final String f15012g = Build.DISPLAY.toLowerCase();

    /* renamed from: h */
    private static final String f15013h = Build.FINGERPRINT.toLowerCase();

    /* renamed from: a */
    public static boolean m14556a() {
        return f15008c.contains("huawei") && f15008c.contains("nxt-al10");
    }

    /* renamed from: b */
    public static boolean m14555b() {
        return f15009d.contains("honor") && f15008c.contains("bln-al10");
    }
}
