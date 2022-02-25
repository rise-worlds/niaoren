package com.kaopu.tiantian.Global;

import android.graphics.Point;
import android.util.Log;
import com.lody.virtual.helper.utils.Reflect;

/* loaded from: classes.dex */
public class Hookgetdisplay {
    public static String className = "android.view.Display";
    public static String methodName = "getRealSize";
    public static String methodSig = "(Landroid/graphics/Point;)V";

    public static void hook(Object obj, Point point) {
        Object obj2 = Reflect.m18998on(obj).get("mDisplayInfo");
        point.x = ((Integer) Reflect.m18998on(obj2).get("appWidth")).intValue();
        point.y = ((Integer) Reflect.m18998on(obj2).get("appHeight")).intValue();
    }

    public static void backup(Object obj, Point point) {
        try {
            Log.w("TianTian", "load should not be here");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
