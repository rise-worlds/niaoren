package com.kaopu.tiantian.Global;

import android.util.Log;
import com.lody.virtual.client.VClient;
import com.lody.virtual.helper.utils.VLog;
import java.lang.reflect.Array;
import java.lang.reflect.Method;

/* loaded from: classes.dex */
public class Hook_unitySendMessage {
    public static String className = "com.unity3d.player.UnityPlayer";
    public static String methodName = "UnitySendMessage";
    public static String methodSig = "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V";

    public static void hook(String str, String str2, String str3) {
        boolean z;
        VLog.m18993d("sunya", "UnitySendMessage:" + str + " send(" + str2 + ")=" + str3, new Object[0]);
        if (str2 == null || !str2.equals("SetResolutionOnChanged")) {
            try {
                Method[] declaredMethods = Class.forName(className, false, VClient.get().getClassLoader()).getDeclaredMethods();
                int length = declaredMethods.length;
                int i = 0;
                while (true) {
                    z = true;
                    if (i >= length) {
                        break;
                    }
                    Method method = declaredMethods[i];
                    if (method.getName().equals("nativeUnitySendMessage")) {
                        method.setAccessible(true);
                        Class<?>[] parameterTypes = method.getParameterTypes();
                        if (parameterTypes[2].equals(String.class)) {
                            method.invoke(null, str, str2, str3);
                        } else if (parameterTypes[2].equals(Array.newInstance(Byte.TYPE, 0).getClass())) {
                            method.invoke(null, str, str2, str3.getBytes("UTF-8"));
                        } else {
                            VLog.m18993d("sunya", "unknow arg3 type=" + parameterTypes[2].getClass(), new Object[0]);
                        }
                    } else {
                        i++;
                    }
                }
                z = false;
                if (!z) {
                    VLog.m18993d("sunya", "not found nativeUnitySendMessage", new Object[0]);
                    backup(str, str2, str3);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void backup(String str, String str2, String str3) {
        try {
            Log.w("TianTian", "load should not be here");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
