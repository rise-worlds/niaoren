package com.kaopu.tiantian.Global;

import android.content.res.Configuration;
import android.util.DisplayMetrics;
import android.util.Log;
import com.lody.virtual.helper.utils.Reflect;

/* loaded from: classes.dex */
public class HookRealMetrics {
    public static String className = "android.view.DisplayInfo";
    public static String methodName = "getLogicalMetrics";
    public static String methodSig = "(Landroid/util/DisplayMetrics;Landroid/content/res/CompatibilityInfo;Landroid/content/res/Configuration;)V";

    public static void hook(Object obj, DisplayMetrics displayMetrics, Object obj2, Configuration configuration) {
        Log.d("HookRealMetrics", "getlogicalMetrics=" + displayMetrics);
        backup2(obj, displayMetrics, obj2, configuration);
    }

    public static void backup2(Object obj, DisplayMetrics displayMetrics, Object obj2, Configuration configuration) {
        Reflect.m18998on(obj).call("getMetricsWithSize", displayMetrics, obj2, configuration, Reflect.m18998on(obj).get("appWidth"), Reflect.m18998on(obj).get("appHeight"));
        Log.d("HookRealMetrics", "logicalWidth=" + Reflect.m18998on(obj).get("logicalWidth") + "，logicalHeight=" + Reflect.m18998on(obj).get("logicalHeight"));
        Log.d("HookRealMetrics", "appWidth=" + Reflect.m18998on(obj).get("appWidth") + "，appHeight=" + Reflect.m18998on(obj).get("appHeight"));
    }

    public static void backup(Object obj, DisplayMetrics displayMetrics, Object obj2, Configuration configuration) {
        try {
            Log.w("TianTian", "load should not be here");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
