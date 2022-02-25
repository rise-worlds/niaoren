package com.kaopu.tiantian.Global;

import android.util.Log;

/* loaded from: classes.dex */
public class HookDisplayCutout_empty {
    public static String className = "android.view.DisplayCutout";
    public static String methodName = "isEmpty";
    public static String methodSig = "()Z";
    public static int minSDK = 28;

    public static boolean hook(Object obj) {
        return false;
    }

    public static boolean backup(Object obj) {
        try {
            Log.w("TianTian", "load should not be here");
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
