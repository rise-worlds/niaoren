package com.kaopu.tiantian.Global;

import android.util.Log;

/* loaded from: classes.dex */
public class Hook_getkeypair {
    private static String TAG = "tiantian";
    public static String className = "com.tencent.abase.SecurityStore";
    public static String methodName = "getKeyPair";
    public static String methodSig = "()V";

    public static void hook(Object obj) {
    }

    public static void backup(Object obj) {
        try {
            Log.w(TAG, "Hook_AndroidProxySelector_getProxyUrl should not be here");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
