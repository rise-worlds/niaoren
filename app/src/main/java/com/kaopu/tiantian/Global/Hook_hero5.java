package com.kaopu.tiantian.Global;

import android.util.Log;
import com.lody.virtual.client.NativeEngine;
import com.lody.virtual.helper.utils.VLog;
import java.io.File;

/* loaded from: classes.dex */
public class Hook_hero5 {
    public static String className = "r2.labs.unitypatch.UnityPatch";
    public static String methodName = "HookInit";
    public static String methodSig = "(ILjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V";

    public static void hook(int i, String str, String str2, String str3, String str4) {
        VLog.m18993d("sunya-hero", "UnityPatch HookInit in1 i=" + i + " str=" + str + " str2=" + str2 + " str3=" + str3 + " str4=" + str4, new Object[0]);
        String resverseRedirectedPath = NativeEngine.resverseRedirectedPath(str);
        String redirectedPath = NativeEngine.getRedirectedPath(str2);
        String resverseRedirectedPath2 = NativeEngine.resverseRedirectedPath(str3);
        String redirectedPath2 = NativeEngine.getRedirectedPath(str4);
        VLog.m18993d("sunya-hero", "UnityPatch HookInit in2 i=" + i + " str=" + resverseRedirectedPath + " str2=" + redirectedPath + " str3=" + resverseRedirectedPath2 + " str4=" + redirectedPath2, new Object[0]);
        if (new File(redirectedPath).exists()) {
            VLog.m18993d("sunya-hero", "UnityPatch HookInit use redirect", new Object[0]);
            NativeEngine.nativeIORedirect(resverseRedirectedPath + "/", redirectedPath + "/");
            NativeEngine.nativeIORedirect(resverseRedirectedPath2, redirectedPath2);
        }
        VLog.m18993d("sunya-hero", "UnityPatch HookInit out", new Object[0]);
    }

    public static void backup(int i, String str, String str2, String str3, String str4) {
        try {
            Log.w("TianTian", "load should not be here");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
