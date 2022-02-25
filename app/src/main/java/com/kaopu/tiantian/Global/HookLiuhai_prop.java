package com.kaopu.tiantian.Global;

import android.util.Log;
import com.lody.virtual.helper.utils.Reflect;

/* loaded from: classes.dex */
public class HookLiuhai_prop {
    public static String className = "android.os.SystemProperties";
    public static String methodName = "getInt";
    public static String methodSig = "(Ljava/lang/String;I)I";
    public static int minSDK = 26;

    public static int hook(String str, int i) {
        if (str.equals("ro.miui.notch")) {
            return 0;
        }
        return ((Integer) Reflect.m18997on("android.os.SystemProperties").call("native_get_int", str, Integer.valueOf(i)).get()).intValue();
    }

    public static int backup(String str, int i) {
        try {
            Log.w("TianTian", "load should not be here");
            return 0;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
}
