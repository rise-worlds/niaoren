package com.kaopu.tiantian.Global;

import android.util.Log;
import com.lody.virtual.helper.utils.Reflect;

/* loaded from: classes.dex */
public class HookLiuhai_systemFeature {
    public static String className = "android.app.ApplicationPackageManager";
    public static String methodName = "hasSystemFeature";
    public static String methodSig = "(Ljava/lang/String;)Z";
    public static int minSDK = 26;

    public static boolean hook(Object obj, String str) {
        if (str.equals("com.oppo.feature.screen.heteromorphism")) {
            return false;
        }
        return ((Boolean) Reflect.m18998on(obj).call("hasSystemFeature", str, 0).get()).booleanValue();
    }

    public static boolean backup(Object obj, String str) {
        try {
            Log.w("TianTian", "load should not be here");
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
