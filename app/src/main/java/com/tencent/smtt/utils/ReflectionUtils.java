package com.tencent.smtt.utils;

import android.os.Build;
import android.util.Log;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.Method;

/* renamed from: com.tencent.smtt.utils.k */
/* loaded from: classes2.dex */
public class ReflectionUtils {
    /* renamed from: a */
    public static Object m16403a(String str, String str2) {
        try {
            return Class.forName(str).getMethod(str2, new Class[0]).invoke(null, new Object[0]);
        } catch (Throwable th) {
            TbsLog.addLog(TbsLog.TBSLOG_CODE_SDK_INVOKE_ERROR, String.valueOf(th), new Object[0]);
            return null;
        }
    }

    /* renamed from: a */
    public static Object m16407a(Class<?> cls, String str, Class<?>[] clsArr, Object... objArr) {
        try {
            Method method = cls.getMethod(str, clsArr);
            method.setAccessible(true);
            return method.invoke(null, objArr);
        } catch (Throwable th) {
            TbsLog.addLog(TbsLog.TBSLOG_CODE_SDK_INVOKE_ERROR, String.valueOf(th), new Object[0]);
            th.printStackTrace();
            return null;
        }
    }

    /* renamed from: a */
    public static Object m16406a(Object obj, String str) {
        return m16404a(obj, str, (Class<?>[]) null, new Object[0]);
    }

    /* renamed from: a */
    public static Object m16404a(Object obj, String str, Class<?>[] clsArr, Object... objArr) {
        StringWriter stringWriter;
        Method method;
        if (obj == null) {
            return null;
        }
        try {
            Class<?> cls = obj.getClass();
            if (Build.VERSION.SDK_INT > 10) {
                method = cls.getMethod(str, clsArr);
            } else {
                method = cls.getDeclaredMethod(str, clsArr);
            }
            method.setAccessible(true);
            if (objArr.length == 0) {
                objArr = null;
            }
            return method.invoke(obj, objArr);
        } catch (Throwable th) {
            TbsLog.addLog(TbsLog.TBSLOG_CODE_SDK_INVOKE_ERROR, String.valueOf(th), new Object[0]);
            if (th.getCause() != null && th.getCause().toString().contains("AuthenticationFail")) {
                return new String("AuthenticationFail");
            }
            if (str != null && (str.equalsIgnoreCase("canLoadX5Core") || str.equalsIgnoreCase("initTesRuntimeEnvironment"))) {
                return null;
            }
            th.printStackTrace(new PrintWriter(new StringWriter()));
            Log.e("ReflectionUtils", "invokeInstance -- exceptions:" + stringWriter.toString());
            return null;
        }
    }

    /* renamed from: a */
    public static Method m16405a(Object obj, String str, Class<?>... clsArr) {
        for (Class<?> cls = obj.getClass(); cls != Object.class && cls != null; cls = cls.getSuperclass()) {
            try {
                return cls.getDeclaredMethod(str, clsArr);
            } catch (Exception unused) {
            }
        }
        return null;
    }
}
