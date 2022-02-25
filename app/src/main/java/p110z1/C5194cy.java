package p110z1;

import android.content.Context;
import android.os.Build;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import com.cyjh.ddy.media.p035a.ResultTypeConstant;
import com.cyjh.mq.p049d.C1363e;
import com.stripe.android.view.ShippingInfoWidget;
import java.io.File;

/* renamed from: z1.cy */
/* loaded from: classes3.dex */
public final class C5194cy {

    /* renamed from: a */
    private static C5194cy f21105a = new C5194cy();

    private C5194cy() {
    }

    /* renamed from: a */
    private static String m3465a(String str, String str2) {
        try {
            return (String) Class.forName("android.os.SystemProperties").getMethod("get", String.class, String.class).invoke(null, str, str2);
        } catch (Exception unused) {
            return str2;
        }
    }

    /* renamed from: a */
    public static C5194cy m3467a() {
        return f21105a;
    }

    /* renamed from: a */
    public static boolean m3466a(Context context) {
        boolean z;
        int length;
        try {
            if (!Build.HARDWARE.contains("goldfish") && !Build.PRODUCT.contains("sdk") && !Build.FINGERPRINT.contains("generic")) {
                TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(ShippingInfoWidget.f12563f);
                if (telephonyManager != null) {
                    String deviceId = telephonyManager.getDeviceId();
                    if (!(deviceId == null || (length = deviceId.length()) == 0)) {
                        for (int i = 0; i < length; i++) {
                            if (!(Character.isWhitespace(deviceId.charAt(i)) || deviceId.charAt(i) == '0')) {
                                z = false;
                                break;
                            }
                        }
                    }
                    z = true;
                    if (z) {
                        return true;
                    }
                }
                return C5097cq.m3699a(Settings.Secure.getString(context.getContentResolver(), "android_id"));
            }
            return true;
        } catch (Exception unused) {
            return false;
        }
    }

    /* renamed from: b */
    public static String m3464b() {
        return "android";
    }

    /* renamed from: c */
    public static boolean m3463c() {
        String[] strArr = {"/system/bin/", "/system/xbin/", "/system/sbin/", "/sbin/", "/vendor/bin/"};
        for (int i = 0; i < 5; i++) {
            try {
                if (new File(strArr[i] + C1363e.f8870a).exists()) {
                    return true;
                }
            } catch (Exception unused) {
            }
        }
        return false;
    }

    /* renamed from: d */
    public static String m3462d() {
        return Build.BOARD;
    }

    /* renamed from: e */
    public static String m3461e() {
        return Build.BRAND;
    }

    /* renamed from: f */
    public static String m3460f() {
        return Build.DEVICE;
    }

    /* renamed from: g */
    public static String m3459g() {
        return Build.DISPLAY;
    }

    /* renamed from: h */
    public static String m3458h() {
        return Build.VERSION.INCREMENTAL;
    }

    /* renamed from: i */
    public static String m3457i() {
        return Build.MANUFACTURER;
    }

    /* renamed from: j */
    public static String m3456j() {
        return Build.MODEL;
    }

    /* renamed from: k */
    public static String m3455k() {
        return Build.PRODUCT;
    }

    /* renamed from: l */
    public static String m3454l() {
        return Build.VERSION.RELEASE;
    }

    /* renamed from: m */
    public static String m3453m() {
        return Build.VERSION.SDK;
    }

    /* renamed from: n */
    public static String m3452n() {
        return Build.TAGS;
    }

    /* renamed from: o */
    public static String m3451o() {
        return m3465a("ro.kernel.qemu", ResultTypeConstant.f7213z);
    }
}
