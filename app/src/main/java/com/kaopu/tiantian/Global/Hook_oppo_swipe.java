package com.kaopu.tiantian.Global;

import android.os.Build;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import com.lody.virtual.helper.utils.Reflect;

/* loaded from: classes.dex */
public class Hook_oppo_swipe {
    public static String className = "android.view.ViewRootImpl$EarlyPostImeInputStage";
    public static String methodName = "onProcess";
    public static String methodSig = "(Landroid/view/ViewRootImpl$QueuedInputEvent;)I";

    public static int hook(Object obj, Object obj2) {
        Object obj3 = Reflect.m18998on(obj2).get("mEvent");
        if (Build.VERSION.SDK_INT <= 28) {
            if (obj3 instanceof KeyEvent) {
                return ((Integer) Reflect.m18998on(obj).call("processKeyEvent", obj2).get()).intValue();
            }
            if ((((Integer) Reflect.m18998on(obj3).call("getSource").get()).intValue() & 2) != 0) {
                return ((Integer) Reflect.m18998on(obj).call("processPointerEvent", obj2).get()).intValue();
            }
            return 0;
        } else if (obj3 instanceof KeyEvent) {
            return ((Integer) Reflect.m18998on(obj).call("processKeyEvent", obj2).get()).intValue();
        } else {
            if (obj3 instanceof MotionEvent) {
                return ((Integer) Reflect.m18998on(obj).call("processMotionEvent", obj2).get()).intValue();
            }
            return 0;
        }
    }

    public static int backup(Object obj, Object obj2) {
        try {
            Log.w("TianTian", "load should not be here");
            return 0;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
}
