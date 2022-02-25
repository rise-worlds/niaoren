package com.kaopu.tiantian.Global;

import android.os.Build;
import android.os.SystemClock;
import android.util.DisplayMetrics;
import android.util.Log;
import com.lody.virtual.helper.utils.Reflect;
import p110z1.Display;

/* loaded from: classes.dex */
public class HookUpdateCacheedAppSize {
    public static String className = "android.view.Display";
    public static String methodName = "updateCachedAppSizeIfNeededLocked";
    public static String methodSig = "()V";

    public static void hook(Display cxjVar) {
        backup2(cxjVar);
    }

    public static void backup2(Object obj) {
        Object obj2;
        long longValue = ((Long) Reflect.m18998on(obj).get("mLastCachedAppSizeUpdate")).longValue();
        int intValue = ((Integer) Reflect.m18998on(obj).get("CACHED_APP_SIZE_DURATION_MILLIS")).intValue();
        Object obj3 = Reflect.m18998on(obj).get("mDisplayInfo");
        if (Build.VERSION.SDK_INT >= 26) {
            obj2 = Reflect.m18998on(obj).call("getDisplayAdjustments").get();
        } else {
            obj2 = Reflect.m18998on(obj).get("mDisplayAdjustments");
        }
        DisplayMetrics displayMetrics = new DisplayMetrics();
        long uptimeMillis = SystemClock.uptimeMillis();
        if (uptimeMillis > longValue + intValue) {
            Reflect.m18998on(obj).call("updateDisplayInfoLocked");
            Reflect.m18998on(obj3).call("getAppMetrics", displayMetrics, obj2);
            Reflect.m18998on(obj).set("mCachedAppWidthCompat", Integer.valueOf(displayMetrics.widthPixels));
            Reflect.m18998on(obj).set("mCachedAppHeightCompat", Integer.valueOf(displayMetrics.heightPixels));
            Reflect.m18998on(obj).set("mLastCachedAppSizeUpdate", Long.valueOf(uptimeMillis));
        }
    }

    public static void backup(Object obj) {
        try {
            Log.w("TianTian", "load should not be here");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
