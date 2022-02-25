package com.kaopu.tiantian.Global;

import android.util.Log;
import com.lody.virtual.helper.utils.Reflect;

/* loaded from: classes.dex */
public class HookLiuhai_prop_String {
    public static String className = "android.os.SystemProperties";
    public static String methodName = "get";
    public static String methodSig = "(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;";
    public static int minSDK = 26;

    public static String hook(String str, String str2) {
        String str3 = (String) Reflect.m18997on("android.os.SystemProperties").call("native_get", str, str2).get();
        if (!str3.endsWith("A6000") && !str3.endsWith("A6003")) {
            return str3;
        }
        return str3 + "1";
    }

    public static String backup(String str, String str2) {
        try {
            Log.w("TianTian", "load should not be here");
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
