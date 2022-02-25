package com.blankj.utilcode.util;

import android.app.Activity;
import android.app.KeyguardManager;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.os.Build;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresPermission;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

/* renamed from: com.blankj.utilcode.util.at */
/* loaded from: classes.dex */
public final class ScreenUtils {
    private ScreenUtils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    /* renamed from: a */
    public static int m23306a() {
        WindowManager windowManager = (WindowManager) Utils.m24103a().getSystemService("window");
        if (windowManager == null) {
            return -1;
        }
        Point point = new Point();
        if (Build.VERSION.SDK_INT >= 17) {
            windowManager.getDefaultDisplay().getRealSize(point);
        } else {
            windowManager.getDefaultDisplay().getSize(point);
        }
        return point.x;
    }

    /* renamed from: b */
    public static int m23302b() {
        WindowManager windowManager = (WindowManager) Utils.m24103a().getSystemService("window");
        if (windowManager == null) {
            return -1;
        }
        Point point = new Point();
        if (Build.VERSION.SDK_INT >= 17) {
            windowManager.getDefaultDisplay().getRealSize(point);
        } else {
            windowManager.getDefaultDisplay().getSize(point);
        }
        return point.y;
    }

    /* renamed from: c */
    public static int m23300c() {
        WindowManager windowManager = (WindowManager) Utils.m24103a().getSystemService("window");
        if (windowManager == null) {
            return -1;
        }
        Point point = new Point();
        windowManager.getDefaultDisplay().getSize(point);
        return point.x;
    }

    /* renamed from: d */
    public static int m23298d() {
        WindowManager windowManager = (WindowManager) Utils.m24103a().getSystemService("window");
        if (windowManager == null) {
            return -1;
        }
        Point point = new Point();
        windowManager.getDefaultDisplay().getSize(point);
        return point.y;
    }

    /* renamed from: e */
    public static float m23296e() {
        return Resources.getSystem().getDisplayMetrics().density;
    }

    /* renamed from: f */
    public static int m23294f() {
        return Resources.getSystem().getDisplayMetrics().densityDpi;
    }

    /* renamed from: a */
    public static void m23304a(@NonNull Activity activity) {
        if (activity != null) {
            activity.getWindow().addFlags(1024);
            return;
        }
        throw new NullPointerException("Argument 'activity' of type Activity (#0 out of 1, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    /* renamed from: b */
    public static void m23301b(@NonNull Activity activity) {
        if (activity != null) {
            activity.getWindow().clearFlags(1024);
            return;
        }
        throw new NullPointerException("Argument 'activity' of type Activity (#0 out of 1, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    /* renamed from: c */
    public static void m23299c(@NonNull Activity activity) {
        if (activity != null) {
            boolean d = m23297d(activity);
            Window window = activity.getWindow();
            if (d) {
                window.clearFlags(1024);
            } else {
                window.addFlags(1024);
            }
        } else {
            throw new NullPointerException("Argument 'activity' of type Activity (#0 out of 1, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
    }

    /* renamed from: d */
    public static boolean m23297d(@NonNull Activity activity) {
        if (activity != null) {
            return (activity.getWindow().getAttributes().flags & 1024) == 1024;
        }
        throw new NullPointerException("Argument 'activity' of type Activity (#0 out of 1, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    /* renamed from: e */
    public static void m23295e(@NonNull Activity activity) {
        if (activity != null) {
            activity.setRequestedOrientation(0);
            return;
        }
        throw new NullPointerException("Argument 'activity' of type Activity (#0 out of 1, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    /* renamed from: f */
    public static void m23293f(@NonNull Activity activity) {
        if (activity != null) {
            activity.setRequestedOrientation(1);
            return;
        }
        throw new NullPointerException("Argument 'activity' of type Activity (#0 out of 1, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    /* renamed from: g */
    public static boolean m23292g() {
        return Utils.m24103a().getResources().getConfiguration().orientation == 2;
    }

    /* renamed from: h */
    public static boolean m23290h() {
        return Utils.m24103a().getResources().getConfiguration().orientation == 1;
    }

    /* renamed from: g */
    public static int m23291g(@NonNull Activity activity) {
        if (activity != null) {
            switch (activity.getWindowManager().getDefaultDisplay().getRotation()) {
                case 0:
                    return 0;
                case 1:
                    return 90;
                case 2:
                    return 180;
                case 3:
                    return 270;
                default:
                    return 0;
            }
        } else {
            throw new NullPointerException("Argument 'activity' of type Activity (#0 out of 1, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
    }

    /* renamed from: h */
    public static Bitmap m23289h(@NonNull Activity activity) {
        if (activity != null) {
            return m23303a(activity, false);
        }
        throw new NullPointerException("Argument 'activity' of type Activity (#0 out of 1, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    /* renamed from: a */
    public static Bitmap m23303a(@NonNull Activity activity, boolean z) {
        Bitmap bitmap;
        if (activity != null) {
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
        throw new NullPointerException("Argument 'activity' of type Activity (#0 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    /* renamed from: i */
    public static boolean m23288i() {
        KeyguardManager keyguardManager = (KeyguardManager) Utils.m24103a().getSystemService("keyguard");
        if (keyguardManager == null) {
            return false;
        }
        return keyguardManager.inKeyguardRestrictedInputMode();
    }

    @RequiresPermission("android.permission.WRITE_SETTINGS")
    /* renamed from: a */
    public static void m23305a(int i) {
        Settings.System.putInt(Utils.m24103a().getContentResolver(), "screen_off_timeout", i);
    }

    /* renamed from: j */
    public static int m23287j() {
        try {
            return Settings.System.getInt(Utils.m24103a().getContentResolver(), "screen_off_timeout");
        } catch (Settings.SettingNotFoundException e) {
            e.printStackTrace();
            return -123;
        }
    }
}
