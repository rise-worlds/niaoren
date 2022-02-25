package p110z1;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import com.lbd.p054xj.app.XJApp;
import com.stripe.android.view.ShippingInfoWidget;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/* renamed from: z1.ael */
/* loaded from: classes3.dex */
public class PhoneInfoUtils {
    /* renamed from: a */
    public static String m13949a() {
        return Build.BRAND;
    }

    /* renamed from: a */
    public static String m13948a(Context context) {
        if (Build.VERSION.SDK_INT < 23) {
            return m13941d(context);
        }
        if (Build.VERSION.SDK_INT < 26) {
            return m13947a(m13943c(context));
        }
        return m13947a(m13945b(context));
    }

    /* renamed from: b */
    public static String m13946b() {
        return m13948a(XJApp.getInstance().getApplicationContext());
    }

    @TargetApi(26)
    /* renamed from: b */
    public static Map m13945b(Context context) {
        HashMap hashMap = new HashMap();
        try {
            TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(ShippingInfoWidget.f12563f);
            String imei = telephonyManager.getImei(0);
            String imei2 = telephonyManager.getImei(1);
            if (!TextUtils.isEmpty(imei) || !TextUtils.isEmpty(imei2)) {
                hashMap.put("imei1", imei);
                hashMap.put("imei2", imei2);
            } else {
                hashMap.put("imei1", telephonyManager.getMeid());
            }
        } catch (SecurityException e) {
            e.printStackTrace();
        }
        return hashMap;
    }

    @TargetApi(23)
    /* renamed from: c */
    public static Map m13943c(Context context) {
        HashMap hashMap = new HashMap();
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(ShippingInfoWidget.f12563f);
        try {
            Method method = Class.forName("android.os.SystemProperties").getMethod("get", String.class, String.class);
            String str = (String) method.invoke(null, "ril.gsm.imei", "");
            hashMap.put("meid", (String) method.invoke(null, "ril.cdma.meid", ""));
            if (!TextUtils.isEmpty(str)) {
                String[] split = str.split(",");
                if (split == null || split.length <= 0) {
                    hashMap.put("imei1", telephonyManager.getDeviceId(0));
                    hashMap.put("imei2", telephonyManager.getDeviceId(1));
                } else {
                    hashMap.put("imei1", split[0]);
                    if (split.length > 1) {
                        hashMap.put("imei2", split[1]);
                    } else {
                        hashMap.put("imei2", telephonyManager.getDeviceId(1));
                    }
                }
            } else {
                hashMap.put("imei1", telephonyManager.getDeviceId(0));
                hashMap.put("imei2", telephonyManager.getDeviceId(1));
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e2) {
            e2.printStackTrace();
        } catch (NoSuchMethodException e3) {
            e3.printStackTrace();
        } catch (SecurityException e4) {
            e4.printStackTrace();
        } catch (InvocationTargetException e5) {
            e5.printStackTrace();
        }
        return hashMap;
    }

    /* renamed from: d */
    public static String m13941d(Context context) {
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(ShippingInfoWidget.f12563f);
        if (telephonyManager == null) {
            return null;
        }
        try {
            return telephonyManager.getDeviceId();
        } catch (SecurityException e) {
            e.printStackTrace();
            return null;
        }
    }

    /* renamed from: e */
    public static int m13940e(Context context) {
        return m13941d(context).trim().length();
    }

    /* renamed from: f */
    public static String m13939f(Context context) {
        try {
            Class<?> cls = Class.forName("android.os.SystemProperties");
            return (String) cls.getMethod("get", String.class).invoke(cls, "ro.serialno");
        } catch (Exception unused) {
            return null;
        }
    }

    /* renamed from: c */
    public static String m13944c() {
        return Build.MODEL;
    }

    /* renamed from: d */
    public static String m13942d() {
        return Build.DISPLAY;
    }

    /* renamed from: a */
    private static String m13947a(Map<String, String> map) {
        if (map == null) {
            return "";
        }
        String str = map.get("imei1");
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        String str2 = map.get("imei2");
        return str2 == null ? str : (str.trim().length() == 15 && str2.trim().length() == 15) ? Long.parseLong(str.trim()) > Long.parseLong(str2.trim()) ? str : str2 : (str.trim().length() == 15 || str2.trim().length() != 15) ? str : str2;
    }

    /* renamed from: g */
    public static int m13938g(Context context) {
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return 0;
        }
    }
}
