package p110z1;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.DisplayCutout;
import android.view.View;
import android.view.WindowInsets;
import com.blankj.utilcode.util.SizeUtils;
import com.stripe.android.view.ShippingInfoWidget;
import java.lang.reflect.InvocationTargetException;

/* renamed from: z1.aox */
/* loaded from: classes3.dex */
public class CommonUtil {

    /* renamed from: a */
    public static final int f17107a = 32;

    /* renamed from: b */
    public static final int f17108b = 8;

    /* renamed from: a */
    public static boolean m11899a(Context context) {
        return Build.CPU_ABI.equalsIgnoreCase("x86");
    }

    /* renamed from: b */
    public static boolean m11895b(Context context) {
        return Build.CPU_ABI.equalsIgnoreCase("x86") || Build.PRODUCT.equals("android_x86");
    }

    /* renamed from: c */
    public static boolean m11892c(Context context) {
        Intent intent = new Intent();
        intent.setData(Uri.parse("tel:123456"));
        intent.setAction("android.intent.action.DIAL");
        boolean z = intent.resolveActivity(context.getPackageManager()) != null;
        if (Build.FINGERPRINT.startsWith("generic") || Build.FINGERPRINT.toLowerCase().contains("vbox") || Build.FINGERPRINT.toLowerCase().contains("test-keys") || Build.MODEL.contains("google_sdk") || Build.MODEL.contains("Emulator") || Build.SERIAL.equalsIgnoreCase("unknown") || Build.SERIAL.equalsIgnoreCase("android") || Build.MODEL.contains("Android SDK built for x86") || Build.MANUFACTURER.contains("Genymotion")) {
            return true;
        }
        return (Build.BRAND.startsWith("generic") && Build.DEVICE.startsWith("generic")) || "google_sdk".equals(Build.PRODUCT) || ((TelephonyManager) context.getSystemService(ShippingInfoWidget.f12563f)).getNetworkOperatorName().toLowerCase().equals("android") || !z;
    }

    /* renamed from: a */
    public static boolean m11901a() {
        BluetoothAdapter defaultAdapter = BluetoothAdapter.getDefaultAdapter();
        return defaultAdapter == null || TextUtils.isEmpty(defaultAdapter.getName()) || m11897b();
    }

    /* renamed from: a */
    public static boolean m11900a(Activity activity) {
        return m11898a("ro.miui.notch", activity) == 1 || m11891d(activity) || m11889f(activity) || m11890e(activity) || m11893c(activity) != null;
    }

    /* renamed from: c */
    private static DisplayCutout m11893c(Activity activity) {
        WindowInsets rootWindowInsets;
        View decorView = activity.getWindow().getDecorView();
        if (Build.VERSION.SDK_INT < 28 || (rootWindowInsets = decorView.getRootWindowInsets()) == null) {
            return null;
        }
        return rootWindowInsets.getDisplayCutout();
    }

    /* renamed from: a */
    private static int m11898a(String str, Activity activity) {
        if (!"Xiaomi".equals(Build.MANUFACTURER)) {
            return 0;
        }
        try {
            Class<?> loadClass = activity.getClassLoader().loadClass("android.os.SystemProperties");
            return ((Integer) loadClass.getMethod("getInt", String.class, Integer.TYPE).invoke(loadClass, new String(str), new Integer(0))).intValue();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return 0;
        } catch (IllegalAccessException e2) {
            e2.printStackTrace();
            return 0;
        } catch (IllegalArgumentException e3) {
            e3.printStackTrace();
            return 0;
        } catch (NoSuchMethodException e4) {
            e4.printStackTrace();
            return 0;
        } catch (InvocationTargetException e5) {
            e5.printStackTrace();
            return 0;
        }
    }

    /* renamed from: d */
    private static boolean m11891d(Context context) {
        try {
            try {
                try {
                    Class<?> loadClass = context.getClassLoader().loadClass("com.huawei.android.util.HwNotchSizeUtil");
                    return ((Boolean) loadClass.getMethod("hasNotchInScreen", new Class[0]).invoke(loadClass, new Object[0])).booleanValue();
                } catch (ClassNotFoundException unused) {
                    Log.e("LHP", "hasNotchAtHuawei ClassNotFoundExceptionn");
                    return false;
                }
            } catch (NoSuchMethodException unused2) {
                Log.e("LHP", "hasNotchAtHuawei NoSuchMethodException");
                return false;
            } catch (Exception unused3) {
                Log.e("LHP", "hasNotchAtHuawei Exception");
                return false;
            }
        } catch (Throwable unused4) {
            return false;
        }
    }

    /* renamed from: e */
    private static boolean m11890e(Context context) {
        try {
            try {
                try {
                    Class<?> loadClass = context.getClassLoader().loadClass("android.util.FtFeature");
                    return ((Boolean) loadClass.getMethod("isFeatureSupport", Integer.TYPE).invoke(loadClass, 32)).booleanValue();
                } catch (ClassNotFoundException unused) {
                    Log.e("LHP", "hasNotchAtVivo ClassNotFoundExceptionn");
                    return false;
                }
            } catch (NoSuchMethodException unused2) {
                Log.e("LHP", "hasNotchAtVivo NoSuchMethodException");
                return false;
            } catch (Exception unused3) {
                Log.e("LHP", "hasNotchAtVivo Exception");
                return false;
            }
        } catch (Throwable unused4) {
            return false;
        }
    }

    /* renamed from: f */
    private static boolean m11889f(Context context) {
        return context.getPackageManager().hasSystemFeature("com.oppo.feature.screen.heteromorphism");
    }

    /* renamed from: b */
    public static int m11896b(Activity activity) {
        int identifier;
        if (m11898a("ro.miui.notch", activity) == 1 && (identifier = activity.getResources().getIdentifier("notch_height", "dimen", "android")) > 0) {
            return activity.getResources().getDimensionPixelSize(identifier);
        }
        if (m11891d(activity)) {
            int[] iArr = {0, 0};
            try {
                try {
                    try {
                        Class<?> loadClass = activity.getClassLoader().loadClass("com.huawei.android.util.HwNotchSizeUtil");
                        int i = ((int[]) loadClass.getMethod("getNotchSize", new Class[0]).invoke(loadClass, new Object[0]))[1];
                        return 0;
                    } catch (NoSuchMethodException unused) {
                        Log.e("test", "getNotchSize NoSuchMethodException");
                        return 0;
                    }
                } catch (ClassNotFoundException unused2) {
                    Log.e("test", "getNotchSize ClassNotFoundException");
                    return 0;
                } catch (Exception unused3) {
                    Log.e("test", "getNotchSize Exception");
                    return 0;
                }
            } catch (Throwable unused4) {
                return 0;
            }
        } else if (m11889f(activity)) {
            return 80;
        } else {
            if (m11890e(activity)) {
                return SizeUtils.m23262a(27.0f);
            }
            return 0;
        }
    }

    /* renamed from: c */
    private static String[] m11894c() {
        String[] strArr = new String[0];
        return Build.VERSION.SDK_INT >= 21 ? Build.SUPPORTED_ABIS : new String[]{Build.CPU_ABI, Build.CPU_ABI2};
    }

    /* renamed from: b */
    public static boolean m11897b() {
        String[] c = m11894c();
        return c.length > 0 && c[0].equals("x86");
    }
}
