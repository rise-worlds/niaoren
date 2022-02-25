package com.gyf.barlibrary;

import android.app.Activity;
import android.os.Build;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/* renamed from: com.gyf.barlibrary.f */
/* loaded from: classes.dex */
public class FlymeOSStatusBarFontUtils {

    /* renamed from: a */
    private static Method f9313a;

    /* renamed from: b */
    private static Method f9314b;

    /* renamed from: c */
    private static Field f9315c;

    /* renamed from: d */
    private static int f9316d;

    /* renamed from: a */
    public static int m20102a(int i) {
        return (((((i & 16711680) >> 16) * 38) + (((65280 & i) >> 8) * 75)) + ((i & 255) * 15)) >> 7;
    }

    static {
        try {
            f9313a = Activity.class.getMethod("setStatusBarDarkIcon", Integer.TYPE);
        } catch (NoSuchMethodException unused) {
        }
        try {
            f9314b = Activity.class.getMethod("setStatusBarDarkIcon", Boolean.TYPE);
        } catch (NoSuchMethodException unused2) {
        }
        try {
            f9315c = WindowManager.LayoutParams.class.getField("statusBarColor");
        } catch (NoSuchFieldException unused3) {
        }
        try {
            f9316d = View.class.getField("SYSTEM_UI_FLAG_LIGHT_STATUS_BAR").getInt(null);
        } catch (IllegalAccessException | NoSuchFieldException unused4) {
        }
    }

    /* renamed from: a */
    public static boolean m20101a(int i, int i2) {
        return m20102a(i) < i2;
    }

    /* renamed from: a */
    public static void m20100a(Activity activity, int i) {
        Method method = f9313a;
        if (method != null) {
            try {
                method.invoke(activity, Integer.valueOf(i));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e2) {
                e2.printStackTrace();
            }
        } else {
            boolean a = m20101a(i, 50);
            if (f9315c != null) {
                m20098a(activity, a, a);
                m20096a(activity.getWindow(), i);
                return;
            }
            m20099a(activity, a);
        }
    }

    /* renamed from: a */
    public static void m20096a(Window window, int i) {
        try {
            m20093b(window, i);
            if (Build.VERSION.SDK_INT > 22) {
                m20097a(window.getDecorView(), true);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* renamed from: a */
    public static void m20099a(Activity activity, boolean z) {
        m20098a(activity, z, true);
    }

    /* renamed from: a */
    private static boolean m20094a(WindowManager.LayoutParams layoutParams, String str, boolean z) {
        try {
            Field declaredField = layoutParams.getClass().getDeclaredField(str);
            declaredField.setAccessible(true);
            int i = declaredField.getInt(layoutParams);
            Field declaredField2 = layoutParams.getClass().getDeclaredField("meizuFlags");
            declaredField2.setAccessible(true);
            int i2 = declaredField2.getInt(layoutParams);
            int i3 = z ? i | i2 : (~i) & i2;
            if (i2 == i3) {
                return false;
            }
            declaredField2.setInt(layoutParams, i3);
            return true;
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            return false;
        } catch (IllegalArgumentException e2) {
            e2.printStackTrace();
            return false;
        } catch (NoSuchFieldException e3) {
            e3.printStackTrace();
            return false;
        } catch (Throwable th) {
            th.printStackTrace();
            return false;
        }
    }

    /* renamed from: a */
    private static void m20097a(View view, boolean z) {
        int i;
        int systemUiVisibility = view.getSystemUiVisibility();
        if (z) {
            i = f9316d | systemUiVisibility;
        } else {
            i = (~f9316d) & systemUiVisibility;
        }
        if (i != systemUiVisibility) {
            view.setSystemUiVisibility(i);
        }
    }

    /* renamed from: b */
    private static void m20093b(Window window, int i) {
        WindowManager.LayoutParams attributes = window.getAttributes();
        Field field = f9315c;
        if (field != null) {
            try {
                if (field.getInt(attributes) != i) {
                    f9315c.set(attributes, Integer.valueOf(i));
                    window.setAttributes(attributes);
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }

    /* renamed from: a */
    public static void m20095a(Window window, boolean z) {
        if (Build.VERSION.SDK_INT < 23) {
            m20094a(window.getAttributes(), "MEIZU_FLAG_DARK_STATUS_BAR_ICON", z);
            return;
        }
        m20097a(window.getDecorView(), z);
        m20093b(window, 0);
    }

    /* renamed from: a */
    private static void m20098a(Activity activity, boolean z, boolean z2) {
        Method method = f9314b;
        if (method != null) {
            try {
                method.invoke(activity, Boolean.valueOf(z));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e2) {
                e2.printStackTrace();
            }
        } else if (z2) {
            m20095a(activity.getWindow(), z);
        }
    }
}
