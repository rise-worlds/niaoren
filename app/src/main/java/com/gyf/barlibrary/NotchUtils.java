package com.gyf.barlibrary;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.view.DisplayCutout;
import android.view.View;
import android.view.Window;
import android.view.WindowInsets;

/* renamed from: com.gyf.barlibrary.j */
/* loaded from: classes.dex */
public class NotchUtils {

    /* renamed from: a */
    private static final String f9363a = "android.os.SystemProperties";

    /* renamed from: b */
    private static final String f9364b = "ro.miui.notch";

    /* renamed from: c */
    private static final String f9365c = "com.huawei.android.util.HwNotchSizeUtil";

    /* renamed from: d */
    private static final String f9366d = "android.util.FtFeature";

    /* renamed from: e */
    private static final String f9367e = "com.oppo.feature.screen.heteromorphism";

    /* renamed from: a */
    public static boolean m19919a(Activity activity) {
        return activity != null && (m19918a((Context) activity) || m19915b((Context) activity) || m19910d(activity) || m19912c((Context) activity) || m19916b(activity));
    }

    /* renamed from: a */
    public static boolean m19917a(View view) {
        return view != null && (m19918a(view.getContext()) || m19915b(view.getContext()) || m19910d(view.getContext()) || m19912c(view.getContext()) || m19914b(view));
    }

    /* renamed from: b */
    private static boolean m19914b(View view) {
        return m19911c(view) != null;
    }

    /* renamed from: b */
    private static boolean m19916b(Activity activity) {
        return m19913c(activity) != null;
    }

    /* renamed from: c */
    private static DisplayCutout m19913c(Activity activity) {
        Window window;
        WindowInsets rootWindowInsets;
        if (Build.VERSION.SDK_INT < 28 || activity == null || (window = activity.getWindow()) == null || (rootWindowInsets = window.getDecorView().getRootWindowInsets()) == null) {
            return null;
        }
        return rootWindowInsets.getDisplayCutout();
    }

    /* renamed from: c */
    private static DisplayCutout m19911c(View view) {
        WindowInsets rootWindowInsets;
        if (Build.VERSION.SDK_INT < 28 || view == null || (rootWindowInsets = view.getRootWindowInsets()) == null) {
            return null;
        }
        return rootWindowInsets.getDisplayCutout();
    }

    /* JADX WARN: Removed duplicated region for block: B:11:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0042 A[ORIG_RETURN, RETURN] */
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static boolean m19918a(android.content.Context r6) {
        /*
            java.lang.String r0 = "Xiaomi"
            java.lang.String r1 = android.os.Build.MANUFACTURER
            boolean r0 = r0.equals(r1)
            r1 = 1
            r2 = 0
            if (r0 == 0) goto L_0x003e
            java.lang.ClassLoader r6 = r6.getClassLoader()     // Catch: NoSuchMethodException | IllegalAccessException | InvocationTargetException | ClassNotFoundException -> 0x003e
            java.lang.String r0 = "android.os.SystemProperties"
            java.lang.Class r6 = r6.loadClass(r0)     // Catch: NoSuchMethodException | IllegalAccessException | InvocationTargetException | ClassNotFoundException -> 0x003e
            java.lang.String r0 = "getInt"
            r3 = 2
            java.lang.Class[] r4 = new java.lang.Class[r3]     // Catch: NoSuchMethodException | IllegalAccessException | InvocationTargetException | ClassNotFoundException -> 0x003e
            java.lang.Class<java.lang.String> r5 = java.lang.String.class
            r4[r2] = r5     // Catch: NoSuchMethodException | IllegalAccessException | InvocationTargetException | ClassNotFoundException -> 0x003e
            java.lang.Class r5 = java.lang.Integer.TYPE     // Catch: NoSuchMethodException | IllegalAccessException | InvocationTargetException | ClassNotFoundException -> 0x003e
            r4[r1] = r5     // Catch: NoSuchMethodException | IllegalAccessException | InvocationTargetException | ClassNotFoundException -> 0x003e
            java.lang.reflect.Method r0 = r6.getMethod(r0, r4)     // Catch: NoSuchMethodException | IllegalAccessException | InvocationTargetException | ClassNotFoundException -> 0x003e
            java.lang.Object[] r3 = new java.lang.Object[r3]     // Catch: NoSuchMethodException | IllegalAccessException | InvocationTargetException | ClassNotFoundException -> 0x003e
            java.lang.String r4 = "ro.miui.notch"
            r3[r2] = r4     // Catch: NoSuchMethodException | IllegalAccessException | InvocationTargetException | ClassNotFoundException -> 0x003e
            java.lang.Integer r4 = java.lang.Integer.valueOf(r2)     // Catch: NoSuchMethodException | IllegalAccessException | InvocationTargetException | ClassNotFoundException -> 0x003e
            r3[r1] = r4     // Catch: NoSuchMethodException | IllegalAccessException | InvocationTargetException | ClassNotFoundException -> 0x003e
            java.lang.Object r6 = r0.invoke(r6, r3)     // Catch: NoSuchMethodException | IllegalAccessException | InvocationTargetException | ClassNotFoundException -> 0x003e
            java.lang.Integer r6 = (java.lang.Integer) r6     // Catch: NoSuchMethodException | IllegalAccessException | InvocationTargetException | ClassNotFoundException -> 0x003e
            int r6 = r6.intValue()     // Catch: NoSuchMethodException | IllegalAccessException | InvocationTargetException | ClassNotFoundException -> 0x003e
            goto L_0x003f
        L_0x003e:
            r6 = 0
        L_0x003f:
            if (r6 != r1) goto L_0x0042
            goto L_0x0043
        L_0x0042:
            r1 = 0
        L_0x0043:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.gyf.barlibrary.NotchUtils.m19918a(android.content.Context):boolean");
    }

    /* renamed from: b */
    private static boolean m19915b(Context context) {
        try {
            Class<?> loadClass = context.getClassLoader().loadClass(f9365c);
            return ((Boolean) loadClass.getMethod("hasNotchInScreen", new Class[0]).invoke(loadClass, new Object[0])).booleanValue();
        } catch (ClassNotFoundException | NoSuchMethodException | Exception unused) {
            return false;
        }
    }

    /* renamed from: c */
    private static boolean m19912c(Context context) {
        try {
            Class<?> loadClass = context.getClassLoader().loadClass(f9366d);
            return ((Boolean) loadClass.getMethod("isFeatureSupport", Integer.TYPE).invoke(loadClass, 32)).booleanValue();
        } catch (ClassNotFoundException | NoSuchMethodException | Exception unused) {
            return false;
        }
    }

    /* renamed from: d */
    private static boolean m19910d(Context context) {
        try {
            return context.getPackageManager().hasSystemFeature(f9367e);
        } catch (Exception unused) {
            return false;
        }
    }
}
