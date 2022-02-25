package com.kaopu.tiantian.Global;

import android.util.Log;
import com.lody.virtual.helper.utils.VLog;

/* loaded from: classes.dex */
public class HookViewWindowInset_ANDROID9 {
    public static String className = "android.view.View";
    public static String methodName = "getRootWindowInsets";
    public static String methodSig = "()Landroid/view/WindowInsets;";
    public static int minSDK = 28;

    public static Object hook(Object obj) {
        VLog.m18993d("sunya", "HookViewWindowInset_ANDROID9 getRootWindowInsets in", new Object[0]);
        return null;
    }

    public static Object backup(Object obj) {
        try {
            Log.w("TianTian", "load should not be here");
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
