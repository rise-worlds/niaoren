package com.cyjh.mobileanjian.screencap;

import android.content.Context;
import android.os.Build;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;

/* compiled from: ScreenUtils.java */
/* renamed from: com.cyjh.mobileanjian.screencap.a */
/* loaded from: classes.dex */
public final class C1350a {
    private C1350a() {
    }

    /* renamed from: a */
    public static int[] m20567a(Context context) {
        int[] iArr = new int[2];
        if (Build.VERSION.SDK_INT >= 17) {
            DisplayMetrics displayMetrics = new DisplayMetrics();
            ((WindowManager) context.getSystemService("window")).getDefaultDisplay().getRealMetrics(displayMetrics);
            iArr[0] = displayMetrics.widthPixels;
            iArr[1] = displayMetrics.heightPixels;
        } else {
            WindowManager windowManager = (WindowManager) context.getSystemService("window");
            Display defaultDisplay = windowManager != null ? windowManager.getDefaultDisplay() : null;
            DisplayMetrics displayMetrics2 = new DisplayMetrics();
            try {
                Class.forName("android.view.Display").getMethod("getRealMetrics", DisplayMetrics.class).invoke(defaultDisplay, displayMetrics2);
                iArr[0] = displayMetrics2.widthPixels;
                iArr[1] = displayMetrics2.heightPixels;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return iArr;
    }

    /* renamed from: b */
    private static int m20566b(Context context) {
        WindowManager windowManager;
        if (context == null || (windowManager = (WindowManager) context.getSystemService("window")) == null) {
            return 0;
        }
        return windowManager.getDefaultDisplay().getRotation();
    }
}
