package com.kaopu.tiantian.Global;

import android.util.Log;
import com.lody.virtual.helper.utils.Reflect;
import java.lang.reflect.InvocationTargetException;

/* loaded from: classes.dex */
public class HookLiuhai_loadClass {
    public static String className = "java.lang.ClassLoader";
    public static String methodName = "loadClass";
    public static String methodSig = "(Ljava/lang/String;)Ljava/lang/Class;";
    public static int minSDK = 26;

    public static Object hook(Object obj, String str) throws ClassNotFoundException {
        if (str.equals("android.util.FtFeature")) {
            throw new ClassNotFoundException();
        } else if (str.equals("com.util.FtFeature")) {
            throw new ClassNotFoundException();
        } else if (str.equals("com.huawei.android.util.HwNotchSizeUtil")) {
            throw new ClassNotFoundException();
        } else if (!str.equals("com.huawei.android.view.LayoutParamsEx")) {
            try {
                return Reflect.m18998on(obj).call("loadClass", str, false).get();
            } catch (Exception e) {
                if (!(e.getCause() instanceof InvocationTargetException)) {
                    return null;
                }
                Throwable targetException = ((InvocationTargetException) e.getCause()).getTargetException();
                if (!(targetException instanceof ClassNotFoundException)) {
                    return null;
                }
                throw ((ClassNotFoundException) targetException);
            }
        } else {
            throw new ClassNotFoundException();
        }
    }

    public static Object backup(Object obj, String str) throws ClassNotFoundException {
        try {
            Log.w("TianTian", "load should not be here");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
}
