package com.kaopu.tiantian.Global;

import android.util.Log;
import com.lody.virtual.helper.utils.Reflect;

/* loaded from: classes.dex */
public class HookLiuhai_Resources {
    public static String className = "android.content.res.Resources";
    public static String methodName = "getIdentifier";
    public static String methodSig = "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)I";
    public static int minSDK = 28;

    public static int hook(Object obj, String str, String str2, String str3) {
        int intValue = ((Integer) Reflect.m18998on(Reflect.m18998on(obj).get("mResourcesImpl")).call("getIdentifier", str, str2, str3).get()).intValue();
        if (str == null || !str.equals("config_mainBuiltInDisplayCutout")) {
            return intValue;
        }
        return 0;
    }

    public static int backup(Object obj, String str, String str2, String str3) {
        try {
            Log.w("TianTian", "load should not be here");
            return 0;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
}
