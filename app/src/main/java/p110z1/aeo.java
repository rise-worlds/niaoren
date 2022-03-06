package p110z1;

import android.app.Activity;
import android.app.KeyguardManager;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.os.Build;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresPermission;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import com.common.utils.log.LogUtils;
import com.lbd.xj.app.AppConfig;
import com.lbd.xj.app.XJApp;

/* compiled from: ScreenUtils.java */
/* renamed from: z1.aeo */
/* loaded from: classes3.dex */
public class aeo {
    private aeo() {
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    /* renamed from: a */
    public static float m13919a(Context context, float f) {
        return (f * context.getResources().getDisplayMetrics().density) + 0.5f;
    }

    /* renamed from: a */
    public static float m13924a(float f) {
        return m13919a(XJApp.getInstance().getApplicationContext(), f);
    }

    /* renamed from: a */
    public static int m13920a(Context context) {
        Display defaultDisplay = ((WindowManager) context.getSystemService("window")).getDefaultDisplay();
        if (Build.VERSION.SDK_INT >= 17) {
            DisplayMetrics displayMetrics = new DisplayMetrics();
            defaultDisplay.getRealMetrics(displayMetrics);
            return displayMetrics.heightPixels;
        } else if (Build.VERSION.SDK_INT < 14) {
            return 0;
        } else {
            try {
                return ((Integer) Display.class.getMethod("getRawHeight", new Class[0]).invoke(defaultDisplay, new Object[0])).intValue();
            } catch (Exception unused) {
                DisplayMetrics displayMetrics2 = new DisplayMetrics();
                defaultDisplay.getMetrics(displayMetrics2);
                return displayMetrics2.heightPixels;
            }
        }
    }

    /* renamed from: a */
    public static int m13925a() {
        return m13920a(XJApp.getInstance().getApplicationContext());
    }

    /* renamed from: b */
    public static float m13918b() {
        return Resources.getSystem().getDisplayMetrics().density;
    }

    /* renamed from: c */
    public static int m13914c() {
        return Resources.getSystem().getDisplayMetrics().densityDpi;
    }

    /* renamed from: d */
    public static int m13912d() {
        WindowManager windowManager = (WindowManager) adz.m14226a().getSystemService("window");
        Point point = new Point();
        if (Build.VERSION.SDK_INT >= 17) {
            windowManager.getDefaultDisplay().getRealSize(point);
        } else {
            windowManager.getDefaultDisplay().getSize(point);
        }
        return point.y;
    }

    /* renamed from: a */
    public static int m13922a(@NonNull Activity activity) {
        switch (activity.getWindowManager().getDefaultDisplay().getRotation()) {
            case 1:
                return 90;
            case 2:
                return 180;
            case 3:
                return 270;
            default:
                return 0;
        }
    }

    /* renamed from: b */
    public static int m13916b(Context context) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((WindowManager) context.getSystemService("window")).getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics.widthPixels;
    }

    /* renamed from: e */
    public static int m13910e() {
        return m13916b(XJApp.getInstance().getApplicationContext());
    }

    /* renamed from: f */
    public static int m13908f() {
        WindowManager windowManager = (WindowManager) adz.m14226a().getSystemService("window");
        Point point = new Point();
        if (Build.VERSION.SDK_INT >= 17) {
            windowManager.getDefaultDisplay().getRealSize(point);
        } else {
            windowManager.getDefaultDisplay().getSize(point);
        }
        return point.x;
    }

    /* renamed from: g */
    public static int m13906g() {
        int b = m13916b(XJApp.getInstance().getApplicationContext());
        if (b >= 1080) {
            b = AppConfig.f9446m;
        }
        try {
            b = ((Integer) PreferencesUtil.m13937a().m13927b("surfaceW", Integer.valueOf(b))).intValue();
            LogUtils.m22037e("ScreenUtils getWiidth:" + b);
            return b;
        } catch (Exception e) {
            e.printStackTrace();
            return b;
        }
    }

    /* renamed from: h */
    public static int m13904h() {
        int g = m13906g();
        if (g >= 720) {
            g = AppConfig.f9447n;
        }
        try {
            g = ((Integer) PreferencesUtil.m13937a().m13927b("surfaceH", Integer.valueOf(g))).intValue();
            LogUtils.m22037e("ScreenUtils getHeiht:" + g);
            return g;
        } catch (Exception e) {
            e.printStackTrace();
            return g;
        }
    }

    /* renamed from: i */
    public static int m13902i() {
        try {
            return Settings.System.getInt(adz.m14226a().getContentResolver(), "screen_off_timeout");
        } catch (Settings.SettingNotFoundException e) {
            e.printStackTrace();
            return -123;
        }
    }

    /* renamed from: j */
    public static int m13901j() {
        int intValue = ((Integer) PreferencesUtil.m13937a().m13927b(acf.f15170I, 0)).intValue();
        if (intValue == 1) {
            return 1280;
        }
        if (intValue == 2) {
            return 1920;
        }
        return m13920a(XJApp.getInstance().getApplicationContext());
    }

    /* renamed from: k */
    public static int m13900k() {
        int intValue = ((Integer) PreferencesUtil.m13937a().m13927b(acf.f15170I, 0)).intValue();
        if (intValue == 1) {
            return 720;
        }
        if (intValue == 2) {
            return 1080;
        }
        return m13916b(XJApp.getInstance().getApplicationContext());
    }

    /* renamed from: b */
    public static boolean m13917b(@NonNull Activity activity) {
        return (activity.getWindow().getAttributes().flags & 1024) == 1024;
    }

    /* renamed from: l */
    public static boolean m13899l() {
        return adz.m14226a().getResources().getConfiguration().orientation == 0;
    }

    /* renamed from: m */
    public static boolean m13898m() {
        return adz.m14226a().getResources().getConfiguration().orientation == 1;
    }

    /* renamed from: n */
    public static boolean m13897n() {
        return ((KeyguardManager) adz.m14226a().getSystemService("keyguard")).inKeyguardRestrictedInputMode();
    }

    /* renamed from: o */
    public static boolean m13896o() {
        return (adz.m14226a().getResources().getConfiguration().screenLayout & 15) >= 3;
    }

    /* renamed from: c */
    public static Bitmap m13913c(@NonNull Activity activity) {
        return m13921a(activity, false);
    }

    /* renamed from: a */
    public static Bitmap m13921a(@NonNull Activity activity, boolean z) {
        Bitmap bitmap;
        View decorView = activity.getWindow().getDecorView();
        decorView.setDrawingCacheEnabled(true);
        decorView.setWillNotCacheDrawing(false);
        Bitmap drawingCache = decorView.getDrawingCache();
        if (drawingCache == null) {
            return null;
        }
        DisplayMetrics displayMetrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        if (z) {
            Resources resources = activity.getResources();
            int dimensionPixelSize = resources.getDimensionPixelSize(resources.getIdentifier("status_bar_height", "dimen", "android"));
            bitmap = Bitmap.createBitmap(drawingCache, 0, dimensionPixelSize, displayMetrics.widthPixels, displayMetrics.heightPixels - dimensionPixelSize);
        } else {
            bitmap = Bitmap.createBitmap(drawingCache, 0, 0, displayMetrics.widthPixels, displayMetrics.heightPixels);
        }
        decorView.destroyDrawingCache();
        return bitmap;
    }

    /* renamed from: d */
    public static void m13911d(@NonNull Activity activity) {
        activity.getWindow().addFlags(1024);
    }

    /* renamed from: e */
    public static void m13909e(@NonNull Activity activity) {
        activity.setRequestedOrientation(0);
    }

    /* renamed from: f */
    public static void m13907f(@NonNull Activity activity) {
        activity.getWindow().clearFlags(1024);
    }

    /* renamed from: g */
    public static void m13905g(@NonNull Activity activity) {
        activity.setRequestedOrientation(1);
    }

    @RequiresPermission("android.permission.WRITE_SETTINGS")
    /* renamed from: a */
    public static void m13923a(int i) {
        Settings.System.putInt(adz.m14226a().getContentResolver(), "screen_off_timeout", i);
    }

    /* renamed from: b */
    public static float m13915b(Context context, float f) {
        return f * context.getResources().getDisplayMetrics().scaledDensity;
    }

    /* renamed from: h */
    public static void m13903h(@NonNull Activity activity) {
        Window window = activity.getWindow();
        if ((window.getAttributes().flags & 1024) == 1024) {
            window.clearFlags(1536);
        } else {
            window.addFlags(1536);
        }
    }
}
