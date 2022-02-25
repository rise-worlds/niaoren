package com.kaopu.tiantian.Global;

import android.content.res.Configuration;
import android.util.DisplayMetrics;
import android.util.Log;
import com.lody.virtual.helper.utils.Reflect;

/* loaded from: classes.dex */
public class HookViewMetrics {
    public static String className = "android.view.DisplayInfo";
    public static String methodName = "getAppMetrics";
    public static String methodSig = "(Landroid/util/DisplayMetrics;Landroid/view/DisplayAdjustments;)V";

    public static void hook(Object obj, DisplayMetrics displayMetrics, Object obj2) {
        if (obj2 != null) {
            ViewUtil.adjustConfiguration((Configuration) Reflect.m18998on(obj2).get("mConfiguration"));
        }
        backup2(obj, displayMetrics, obj2);
    }

    public static void backup2(Object obj, DisplayMetrics displayMetrics, Object obj2) {
        Reflect.m18998on(obj).call("getMetricsWithSize", displayMetrics, Reflect.m18998on(obj2).call("getCompatibilityInfo").get(), Reflect.m18998on(obj2).call("getConfiguration").get(), Reflect.m18998on(obj).get("appWidth"), Reflect.m18998on(obj).get("appHeight"));
    }

    public static void backup(Object obj, DisplayMetrics displayMetrics, Object obj2) {
        try {
            Log.w("TianTian", "load should not be here");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
