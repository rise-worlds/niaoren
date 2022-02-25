package com.kaopu.tiantian.Global;

import android.util.DisplayMetrics;
import com.lody.virtual.helper.utils.Reflect;

/* loaded from: classes.dex */
public class HookRealMetrics2 {
    public static String className = "android.view.Display";
    public static String methodName = "getRealMetrics";
    public static String methodSig = "(Landroid/util/DisplayMetrics;)V";

    public static void backup(Object obj, DisplayMetrics displayMetrics) {
    }

    public static void hook(Object obj, DisplayMetrics displayMetrics) {
        backup2(obj, displayMetrics);
    }

    public static void backup2(Object obj, DisplayMetrics displayMetrics) {
        synchronized (obj) {
            Reflect.m18998on(obj).call("updateDisplayInfoLocked");
            Object obj2 = Reflect.m18998on(obj).get("mDisplayInfo");
            Object obj3 = Reflect.m18997on("android.content.res.CompatibilityInfo").get("DEFAULT_COMPATIBILITY_INFO");
            int intValue = ((Integer) Reflect.m18998on(obj2).get("logicalWidth")).intValue();
            int intValue2 = ((Integer) Reflect.m18998on(obj2).get("logicalHeight")).intValue();
            int intValue3 = ((Integer) Reflect.m18998on(obj2).get("appWidth")).intValue();
            int intValue4 = ((Integer) Reflect.m18998on(obj2).get("appHeight")).intValue();
            Reflect.m18998on(obj2).set("logicalWidth", Integer.valueOf(intValue3));
            Reflect.m18998on(obj2).set("logicalHeight", Integer.valueOf(intValue4));
            Reflect.m18998on(obj2).call("getLogicalMetrics", displayMetrics, obj3, null);
            Reflect.m18998on(obj2).set("logicalWidth", Integer.valueOf(intValue));
            Reflect.m18998on(obj2).set("logicalHeight", Integer.valueOf(intValue2));
        }
    }
}
