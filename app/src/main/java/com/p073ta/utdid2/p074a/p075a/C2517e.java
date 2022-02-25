package com.p073ta.utdid2.p074a.p075a;

import android.content.Context;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import com.stripe.android.view.ShippingInfoWidget;
import java.util.Random;

/* renamed from: com.ta.utdid2.a.a.e */
/* loaded from: classes2.dex */
public class C2517e {
    /* renamed from: a */
    public static String m17176a() {
        int nextInt = new Random().nextInt();
        int nextInt2 = new Random().nextInt();
        byte[] bytes = C2516d.getBytes((int) (System.currentTimeMillis() / 1000));
        byte[] bytes2 = C2516d.getBytes((int) System.nanoTime());
        byte[] bytes3 = C2516d.getBytes(nextInt);
        byte[] bytes4 = C2516d.getBytes(nextInt2);
        byte[] bArr = new byte[16];
        System.arraycopy(bytes, 0, bArr, 0, 4);
        System.arraycopy(bytes2, 0, bArr, 4, 4);
        System.arraycopy(bytes3, 0, bArr, 8, 4);
        System.arraycopy(bytes4, 0, bArr, 12, 4);
        return C2511b.encodeToString(bArr, 2);
    }

    /* renamed from: a */
    public static String m17175a(Context context) {
        String str = null;
        if (!C2515c.m17177a() && context != null) {
            try {
                TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(ShippingInfoWidget.f12563f);
                if (telephonyManager != null) {
                    str = telephonyManager.getDeviceId();
                }
            } catch (Exception unused) {
            }
        }
        if (C2521g.m17166a(str)) {
            str = m17174b();
        }
        if (C2521g.m17166a(str)) {
            str = m17173b(context);
        }
        return C2521g.m17166a(str) ? m17176a() : str;
    }

    /* renamed from: b */
    private static String m17173b(Context context) {
        String str = "";
        try {
            str = Settings.Secure.getString(context.getContentResolver(), "android_id");
        } catch (Throwable unused) {
        }
        return (TextUtils.isEmpty(str) || str.equalsIgnoreCase("a5f5faddde9e9f02") || str.equalsIgnoreCase("8e17f7422b35fbea")) ? "" : str.equalsIgnoreCase("0000000000000000") ? "" : str;
    }

    /* renamed from: b */
    private static String m17174b() {
        String str = C2522h.get("ro.aliyun.clouduuid", "");
        if (TextUtils.isEmpty(str)) {
            str = C2522h.get("ro.sys.aliyun.clouduuid", "");
        }
        return TextUtils.isEmpty(str) ? m17172c() : str;
    }

    /* renamed from: c */
    private static String m17172c() {
        try {
            return (String) Class.forName("com.yunos.baseservice.clouduuid.CloudUUID").getMethod("getCloudUUID", new Class[0]).invoke(null, new Object[0]);
        } catch (Exception unused) {
            return "";
        }
    }

    /* renamed from: c */
    public static String m17171c(Context context) {
        String str = null;
        if (context != null) {
            try {
                TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(ShippingInfoWidget.f12563f);
                if (telephonyManager != null) {
                    str = telephonyManager.getSubscriberId();
                }
            } catch (Exception unused) {
            }
        }
        return C2521g.m17166a(str) ? m17176a() : str;
    }
}
