package com.cyjh.ddy.base.utils;

import android.annotation.SuppressLint;
import android.content.res.Resources;
import android.graphics.Point;
import android.os.Build;
import android.view.DisplayCutout;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowInsets;
import android.view.WindowManager;
import java.lang.reflect.InvocationTargetException;

/* renamed from: com.cyjh.ddy.base.utils.e */
/* loaded from: classes.dex */
public class DisplayUtils {

    /* renamed from: a */
    private static int f7121a;

    /* renamed from: a */
    public static int m21864a(Window window) {
        Point point = new Point();
        m21863a(window, point);
        return point.x;
    }

    /* renamed from: b */
    public static int m21862b(Window window) {
        Point point = new Point();
        m21863a(window, point);
        return point.y;
    }

    /* renamed from: a */
    private static void m21863a(Window window, Point point) {
        WindowManager windowManager = (WindowManager) window.getContext().getSystemService("window");
        if (Build.VERSION.SDK_INT >= 17) {
            windowManager.getDefaultDisplay().getRealSize(point);
        } else {
            windowManager.getDefaultDisplay().getSize(point);
        }
        int o = m21850n(window) ? m21849o(window) : 0;
        if (point.x > point.y) {
            if (m21853k(window)) {
                o = m21849o(window);
            }
            point.x -= m21861c(window) + o;
            return;
        }
        point.y -= m21861c(window) + o;
    }

    @SuppressLint({"NewApi"})
    /* renamed from: c */
    public static int m21861c(Window window) {
        DisplayCutout displayCutout;
        int i = f7121a;
        if (i != 0) {
            return i;
        }
        if (m21859e(window)) {
            f7121a = m21858f(window);
        } else if (m21857g(window)) {
            f7121a = m21856h(window);
        } else if (m21855i(window)) {
            f7121a = m21854j(window);
        } else if (m21853k(window)) {
            f7121a = m21852l(window);
        } else if (Build.VERSION.SDK_INT >= 28) {
            WindowInsets rootWindowInsets = window.getDecorView().getRootWindowInsets();
            if (rootWindowInsets == null || (displayCutout = rootWindowInsets.getDisplayCutout()) == null || displayCutout.getBoundingRects() == null) {
                return 0;
            }
            f7121a = displayCutout.getSafeInsetTop();
        }
        return f7121a;
    }

    @SuppressLint({"NewApi"})
    /* renamed from: d */
    public static boolean m21860d(Window window) {
        WindowInsets rootWindowInsets;
        DisplayCutout displayCutout;
        return (Build.VERSION.SDK_INT < 28 || (rootWindowInsets = window.getDecorView().getRootWindowInsets()) == null || (displayCutout = rootWindowInsets.getDisplayCutout()) == null || displayCutout.getBoundingRects() == null) ? false : true;
    }

    /*  JADX ERROR: JadxRuntimeException in pass: AttachTryCatchVisitor
        jadx.core.utils.exceptions.JadxRuntimeException: Null type added to not empty exception handler: ClassNotFoundException | NoSuchMethodException | Exception -> 0x0024
        	at jadx.core.dex.trycatch.ExceptionHandler.addCatchType(ExceptionHandler.java:54)
        	at jadx.core.dex.visitors.AttachTryCatchVisitor.createHandler(AttachTryCatchVisitor.java:136)
        	at jadx.core.dex.visitors.AttachTryCatchVisitor.convertToHandlers(AttachTryCatchVisitor.java:123)
        	at jadx.core.dex.visitors.AttachTryCatchVisitor.initTryCatches(AttachTryCatchVisitor.java:59)
        	at jadx.core.dex.visitors.AttachTryCatchVisitor.visit(AttachTryCatchVisitor.java:47)
        */
    /* renamed from: e */
    public static boolean m21859e(android.view.Window r3) {
        /*
            r0 = 0
            r3.getContext()
            r3 = move-result
            r3.getClassLoader()
            r3 = move-result
            java.lang.String r1 = "com.huawei.android.util.HwNotchSizeUtil"
            r3.loadClass(r1)
            r3 = move-result
            java.lang.String r1 = "hasNotchInScreen"
            java.lang.Class[] r2 = new java.lang.Class[r0]
            r3.getMethod(r1, r2)
            r1 = move-result
            java.lang.Object[] r2 = new java.lang.Object[r0]
            r1.invoke(r3, r2)
            r3 = move-result
            java.lang.Boolean r3 = (java.lang.Boolean) r3
            r3.booleanValue()
            r3 = move-result
            return r3
        L_0x0024:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.cyjh.ddy.base.utils.DisplayUtils.m21859e(android.view.Window):boolean");
    }

    /* renamed from: f */
    public static int m21858f(Window window) {
        int[] iArr = {0, 0};
        try {
            Class<?> loadClass = window.getContext().getClassLoader().loadClass("com.huawei.android.util.HwNotchSizeUtil");
            return ((int[]) loadClass.getMethod("getNotchSize", new Class[0]).invoke(loadClass, new Object[0]))[1];
        } catch (ClassNotFoundException unused) {
            return iArr[1];
        } catch (NoSuchMethodException unused2) {
            return iArr[1];
        } catch (Exception unused3) {
            return iArr[1];
        } catch (Throwable unused4) {
            return iArr[1];
        }
    }

    /* renamed from: g */
    public static boolean m21857g(Window window) {
        return "1".equals(SystemProperties.m21736a().m21734a("ro.miui.notch"));
    }

    /* renamed from: h */
    public static int m21856h(Window window) {
        return m21851m(window);
    }

    /* renamed from: i */
    public static boolean m21855i(Window window) {
        if (window == null) {
            return false;
        }
        return window.getContext().getPackageManager().hasSystemFeature("com.oppo.feature.screen.heteromorphism");
    }

    /* renamed from: j */
    public static int m21854j(Window window) {
        return m21851m(window);
    }

    /* renamed from: k */
    public static boolean m21853k(Window window) {
        try {
            Class<?> loadClass = window.getContext().getClassLoader().loadClass("android.util.FtFeature");
            return ((Boolean) loadClass.getMethod("isFeatureSupport", Integer.TYPE).invoke(loadClass, 32)).booleanValue();
        } catch (ClassNotFoundException | IllegalAccessException | NoSuchMethodException | InvocationTargetException unused) {
            return false;
        }
    }

    /* renamed from: l */
    public static int m21852l(Window window) {
        return m21851m(window);
    }

    /* renamed from: m */
    public static int m21851m(Window window) {
        Resources resources = window.getContext().getResources();
        int identifier = resources.getIdentifier("status_bar_height", "dimen", "android");
        if (identifier != 0) {
            return resources.getDimensionPixelSize(identifier);
        }
        return 0;
    }

    /* renamed from: n */
    public static boolean m21850n(Window window) {
        boolean z;
        ViewGroup viewGroup = (ViewGroup) window.getDecorView();
        int childCount = viewGroup.getChildCount();
        int i = 0;
        while (true) {
            if (i >= childCount) {
                z = false;
                break;
            }
            View childAt = viewGroup.getChildAt(i);
            int id = childAt.getId();
            if (id != -1 && "navigationBarBackground".equals(window.getContext().getResources().getResourceEntryName(id)) && childAt.getVisibility() == 0) {
                z = true;
                break;
            }
            i++;
        }
        return z ? (viewGroup.getSystemUiVisibility() & 2) == 0 : z;
    }

    /* renamed from: o */
    public static int m21849o(Window window) {
        Resources resources = window.getContext().getResources();
        int identifier = resources.getIdentifier("navigation_bar_height", "dimen", "android");
        if (identifier != 0) {
            return resources.getDimensionPixelSize(identifier);
        }
        return 0;
    }
}
