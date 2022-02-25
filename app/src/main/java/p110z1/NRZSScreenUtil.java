package p110z1;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Point;
import android.graphics.Rect;
import android.os.Build;
import android.support.annotation.NonNull;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.DisplayCutout;
import android.view.View;
import android.view.Window;
import android.view.WindowInsets;
import android.view.WindowManager;
import com.cyjh.ddy.media.p035a.ResultTypeConstant;
import java.lang.reflect.InvocationTargetException;

/* renamed from: z1.apc */
/* loaded from: classes3.dex */
public class NRZSScreenUtil {

    /* renamed from: a */
    public static final int f17111a = 32;

    /* renamed from: b */
    public static final int f17112b = 8;

    /* renamed from: a */
    public static int m11872a(Context context) {
        DisplayMetrics e = m11863e(context);
        return m11862f(context) ? e.heightPixels : e.widthPixels;
    }

    /* renamed from: b */
    public static int m11867b(Context context) {
        return m11863e(context).widthPixels;
    }

    /* renamed from: c */
    public static int m11865c(Context context) {
        DisplayMetrics e = m11863e(context);
        return m11862f(context) ? e.widthPixels : e.heightPixels;
    }

    /* renamed from: d */
    public static int m11864d(Context context) {
        return m11863e(context).heightPixels;
    }

    /* renamed from: e */
    public static DisplayMetrics m11863e(Context context) {
        return context.getResources().getDisplayMetrics();
    }

    /* renamed from: f */
    public static boolean m11862f(Context context) {
        return context.getResources().getConfiguration().orientation == 2;
    }

    /* renamed from: a */
    public static int m11871a(Context context, float f) {
        return (int) ((f * context.getResources().getDisplayMetrics().density) + 0.5f);
    }

    /* renamed from: b */
    public static int m11866b(Context context, float f) {
        return (int) ((f / context.getResources().getDisplayMetrics().density) + 0.5f);
    }

    /* renamed from: g */
    public static float m11861g(Context context) {
        return context.getResources().getDisplayMetrics().density;
    }

    /* renamed from: h */
    public static int[] m11860h(Context context) {
        int[] iArr = new int[2];
        if (iArr[0] != 0) {
            return iArr;
        }
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((WindowManager) context.getApplicationContext().getSystemService("window")).getDefaultDisplay().getMetrics(displayMetrics);
        iArr[0] = displayMetrics.widthPixels;
        iArr[1] = displayMetrics.heightPixels;
        return iArr;
    }

    /* renamed from: i */
    public static String m11859i(Context context) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((WindowManager) context.getApplicationContext().getSystemService("window")).getDefaultDisplay().getMetrics(displayMetrics);
        int i = displayMetrics.widthPixels;
        int i2 = displayMetrics.heightPixels;
        return i + "x" + i2;
    }

    /* renamed from: j */
    public static int m11858j(Context context) {
        try {
            Class<?> cls = Class.forName("com.android.internal.R$dimen");
            return context.getResources().getDimensionPixelSize(Integer.parseInt(cls.getField("status_bar_height").get(cls.newInstance()).toString()));
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    @TargetApi(17)
    /* renamed from: k */
    public static int m11857k(Context context) {
        if (Build.VERSION.SDK_INT < 17) {
            return 0;
        }
        DisplayMetrics displayMetrics = new DisplayMetrics();
        WindowManager windowManager = (WindowManager) context.getApplicationContext().getSystemService("window");
        windowManager.getDefaultDisplay().getMetrics(displayMetrics);
        int i = displayMetrics.heightPixels;
        windowManager.getDefaultDisplay().getRealMetrics(displayMetrics);
        int i2 = displayMetrics.heightPixels;
        if (i2 > i) {
            return i2 - i;
        }
        return 0;
    }

    /* renamed from: a */
    public static boolean m11873a(Activity activity) {
        return m11869a("ro.miui.notch", activity) == 1 || m11854n(activity) || m11852p(activity) || m11853o(activity) || m11868b(activity) != null;
    }

    /* renamed from: b */
    private static DisplayCutout m11868b(Activity activity) {
        WindowInsets rootWindowInsets;
        View decorView = activity.getWindow().getDecorView();
        if (Build.VERSION.SDK_INT < 28 || (rootWindowInsets = decorView.getRootWindowInsets()) == null) {
            return null;
        }
        return rootWindowInsets.getDisplayCutout();
    }

    /* renamed from: a */
    private static int m11869a(String str, Activity activity) {
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

    /* renamed from: n */
    private static boolean m11854n(Context context) {
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

    /* renamed from: o */
    private static boolean m11853o(Context context) {
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

    /* renamed from: p */
    private static boolean m11852p(Context context) {
        return context.getPackageManager().hasSystemFeature("com.oppo.feature.screen.heteromorphism");
    }

    /* renamed from: l */
    public static boolean m11856l(Context context) {
        try {
            Resources resources = context.getResources();
            int identifier = resources.getIdentifier("config_showNavigationBar", "bool", "android");
            boolean z = identifier > 0 ? resources.getBoolean(identifier) : false;
            try {
                Class<?> cls = Class.forName("android.os.SystemProperties");
                String str = (String) cls.getMethod("get", String.class).invoke(cls, "qemu.hw.mainkeys");
                if ("1".equals(str)) {
                    return false;
                }
                if (ResultTypeConstant.f7213z.equals(str)) {
                    return true;
                }
                return z;
            } catch (Exception unused) {
                return z;
            }
        } catch (Exception unused2) {
            return false;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:32:0x0060 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:33:0x0061  */
    /* renamed from: m */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static boolean m11855m(@android.support.annotation.NonNull android.content.Context r7) {
        /*
            int r0 = android.os.Build.VERSION.SDK_INT
            r1 = 0
            r2 = 17
            if (r0 < r2) goto L_0x00aa
            r0 = 0
            r2 = 1
            java.lang.String r3 = "com.android.internal.policy.PhoneWindow"
            java.lang.Class r3 = java.lang.Class.forName(r3)     // Catch: InvocationTargetException -> 0x0046, InstantiationException -> 0x004b, IllegalAccessException -> 0x0050, NoSuchMethodException -> 0x0055, ClassNotFoundException -> 0x005a
            java.lang.Class[] r4 = new java.lang.Class[r2]     // Catch: InvocationTargetException -> 0x0046, InstantiationException -> 0x004b, IllegalAccessException -> 0x0050, NoSuchMethodException -> 0x0055, ClassNotFoundException -> 0x005a
            java.lang.Class<android.content.Context> r5 = android.content.Context.class
            r4[r1] = r5     // Catch: InvocationTargetException -> 0x0046, InstantiationException -> 0x004b, IllegalAccessException -> 0x0050, NoSuchMethodException -> 0x0055, ClassNotFoundException -> 0x005a
            java.lang.reflect.Constructor r3 = r3.getConstructor(r4)     // Catch: InvocationTargetException -> 0x0046, InstantiationException -> 0x004b, IllegalAccessException -> 0x0050, NoSuchMethodException -> 0x0055, ClassNotFoundException -> 0x005a
            java.lang.Object[] r4 = new java.lang.Object[r2]     // Catch: InvocationTargetException -> 0x0046, InstantiationException -> 0x004b, IllegalAccessException -> 0x0050, NoSuchMethodException -> 0x0055, ClassNotFoundException -> 0x005a
            r4[r1] = r7     // Catch: InvocationTargetException -> 0x0046, InstantiationException -> 0x004b, IllegalAccessException -> 0x0050, NoSuchMethodException -> 0x0055, ClassNotFoundException -> 0x005a
            java.lang.Object r3 = r3.newInstance(r4)     // Catch: InvocationTargetException -> 0x0046, InstantiationException -> 0x004b, IllegalAccessException -> 0x0050, NoSuchMethodException -> 0x0055, ClassNotFoundException -> 0x005a
            boolean r4 = r3 instanceof android.view.Window     // Catch: InvocationTargetException -> 0x0046, InstantiationException -> 0x004b, IllegalAccessException -> 0x0050, NoSuchMethodException -> 0x0055, ClassNotFoundException -> 0x005a
            if (r4 == 0) goto L_0x005e
            android.view.Window r3 = (android.view.Window) r3     // Catch: InvocationTargetException -> 0x0046, InstantiationException -> 0x004b, IllegalAccessException -> 0x0050, NoSuchMethodException -> 0x0055, ClassNotFoundException -> 0x005a
            r0 = -1
            r3.setLayout(r0, r0)     // Catch: InvocationTargetException -> 0x002d, InstantiationException -> 0x0032, IllegalAccessException -> 0x0037, NoSuchMethodException -> 0x003c, ClassNotFoundException -> 0x0041
            r0 = r3
            goto L_0x005e
        L_0x002d:
            r0 = move-exception
            r6 = r3
            r3 = r0
            r0 = r6
            goto L_0x0047
        L_0x0032:
            r0 = move-exception
            r6 = r3
            r3 = r0
            r0 = r6
            goto L_0x004c
        L_0x0037:
            r0 = move-exception
            r6 = r3
            r3 = r0
            r0 = r6
            goto L_0x0051
        L_0x003c:
            r0 = move-exception
            r6 = r3
            r3 = r0
            r0 = r6
            goto L_0x0056
        L_0x0041:
            r0 = move-exception
            r6 = r3
            r3 = r0
            r0 = r6
            goto L_0x005b
        L_0x0046:
            r3 = move-exception
        L_0x0047:
            r3.printStackTrace()
            goto L_0x005e
        L_0x004b:
            r3 = move-exception
        L_0x004c:
            r3.printStackTrace()
            goto L_0x005e
        L_0x0050:
            r3 = move-exception
        L_0x0051:
            r3.printStackTrace()
            goto L_0x005e
        L_0x0055:
            r3 = move-exception
        L_0x0056:
            r3.printStackTrace()
            goto L_0x005e
        L_0x005a:
            r3 = move-exception
        L_0x005b:
            r3.printStackTrace()
        L_0x005e:
            if (r0 != 0) goto L_0x0061
            return r1
        L_0x0061:
            android.content.Context r3 = r7.getApplicationContext()
            java.lang.String r4 = "window"
            java.lang.Object r3 = r3.getSystemService(r4)
            android.view.WindowManager r3 = (android.view.WindowManager) r3
            android.view.Display r3 = r3.getDefaultDisplay()
            android.graphics.Point r4 = new android.graphics.Point
            r4.<init>()
            r3.getRealSize(r4)
            android.view.View r0 = r0.getDecorView()
            android.content.res.Resources r7 = r7.getResources()
            android.content.res.Configuration r7 = r7.getConfiguration()
            r3 = 2
            int r7 = r7.orientation
            if (r3 != r7) goto L_0x009b
            r7 = 16908290(0x1020002, float:2.3877235E-38)
            android.view.View r7 = r0.findViewById(r7)
            int r0 = r4.x
            int r7 = r7.getWidth()
            if (r0 == r7) goto L_0x00aa
            r1 = 1
            goto L_0x00aa
        L_0x009b:
            android.graphics.Rect r7 = new android.graphics.Rect
            r7.<init>()
            r0.getWindowVisibleDisplayFrame(r7)
            int r7 = r7.bottom
            int r0 = r4.y
            if (r7 == r0) goto L_0x00aa
            r1 = 1
        L_0x00aa:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: p110z1.NRZSScreenUtil.m11855m(android.content.Context):boolean");
    }

    @TargetApi(17)
    /* renamed from: a */
    public static boolean m11870a(@NonNull Context context, @NonNull Window window) {
        Display defaultDisplay = window.getWindowManager().getDefaultDisplay();
        Point point = new Point();
        defaultDisplay.getRealSize(point);
        View decorView = window.getDecorView();
        if (2 == context.getResources().getConfiguration().orientation) {
            return point.x != decorView.findViewById(16908290).getWidth();
        }
        Rect rect = new Rect();
        decorView.getWindowVisibleDisplayFrame(rect);
        return rect.bottom != point.y;
    }
}
