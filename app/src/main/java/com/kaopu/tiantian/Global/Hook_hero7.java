package com.kaopu.tiantian.Global;

import android.util.Log;
import com.lody.virtual.client.NativeEngine;
import com.lody.virtual.client.VClient;
import com.lody.virtual.helper.utils.Reflect;
import java.io.File;

/* loaded from: classes.dex */
public class Hook_hero7 {
    public static String className = "r2.labs.unitypatch.UnityPatch";
    public static String methodName = "ClearCache";
    public static String methodSig = "";

    public static void hook() {
        backup2();
        String resverseRedirectedPath = NativeEngine.resverseRedirectedPath((String) Reflect.m18996on(className, VClient.get().getClassLoader()).get("SourceSoDir"));
        String redirectedPath = NativeEngine.getRedirectedPath((String) Reflect.m18996on(className, VClient.get().getClassLoader()).get("PatchSoDir"));
        String resverseRedirectedPath2 = NativeEngine.resverseRedirectedPath((String) Reflect.m18996on(className, VClient.get().getClassLoader()).get("SourceAssetsDir"));
        String redirectedPath2 = NativeEngine.getRedirectedPath((String) Reflect.m18996on(className, VClient.get().getClassLoader()).get("PatchAssetsZip"));
        if (new File(redirectedPath).exists()) {
            NativeEngine.nativeIORedirect(resverseRedirectedPath + "/", redirectedPath + "/");
            NativeEngine.nativeIORedirect(resverseRedirectedPath2, redirectedPath2);
        }
    }

    public static void backup2() {
        String str = (String) Reflect.m18996on(className, VClient.get().getClassLoader()).get("UnityShaderCacheFileDir");
        String str2 = (String) Reflect.m18996on(className, VClient.get().getClassLoader()).get("UnityIL2CPPFileDir");
        if (new File(str).exists()) {
            Reflect.m18996on("r2.labs.ziptools.ZipTools", VClient.get().getClassLoader()).call("DeleteDirectory", str);
        }
        if (new File(str2).exists()) {
            Reflect.m18996on("r2.labs.ziptools.ZipTools", VClient.get().getClassLoader()).call("DeleteDirectory", str2);
        }
    }

    public static void backup() {
        try {
            Log.w("TianTian", "load should not be here");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
