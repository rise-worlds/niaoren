package com.kaopu.tiantian.Global;

import android.util.Log;
import com.lody.virtual.helper.utils.Reflect;
import com.lody.virtual.helper.utils.VLog;

/* loaded from: classes.dex */
public class HookViewInvalidate {
    public static String className = "android.view.View";
    public static String methodName = "invalidate";
    public static String methodSig = "()V";

    public static void hook(Object obj) {
        VLog.m18993d("sunya", "invalidate view=" + obj, new Object[0]);
        if (!obj.getClass().getName().equals("com.android.internal.policy.DecorView")) {
            obj.getClass().getName().equals("com.android.internal.policy.impl.PhoneWindow$DecorView");
        }
        Reflect.m18998on(obj).call("invalidate", true);
    }

    public static void backup(Object obj) {
        try {
            Log.w("TianTian", "load should not be here");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
