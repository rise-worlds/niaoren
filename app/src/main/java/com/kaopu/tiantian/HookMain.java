package com.kaopu.tiantian;

import android.os.Build;
import android.util.Log;
import com.lody.virtual.client.VClient;
import com.lody.virtual.client.env.VirtualRuntime;
import com.lody.virtual.helper.utils.VLog;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/* loaded from: classes.dex */
public class HookMain {
    private static final String LIB_NAME = "v++";
    private static final String LIB_NAME_64 = "v++_64";
    private static final String TAG = "YAHFA";
    private static List<Class<?>> hookInfoClasses = new LinkedList();
    private static ClassLoader ttartClassLoader = null;

    private static native boolean backupAndHookNative(Method method, Method method2, Method method3);

    public static native Method findMethodNative(Class cls, String str, String str2);

    private static native void init(int i);

    static {
        try {
            if (VirtualRuntime.is64bit()) {
                System.loadLibrary(LIB_NAME_64);
            } else {
                System.loadLibrary(LIB_NAME);
            }
        } catch (Throwable unused) {
        }
        init(Build.VERSION.SDK_INT);
    }

    public static void doHookLater_va(ClassLoader classLoader) {
        String[] strArr;
        try {
            Class<?> cls = Class.forName("com.kaopu.tiantian.HookInfo", true, ttartClassLoader);
            for (String str : (String[]) cls.getField("hookItemNamesLater").get(null)) {
                if (str.contains(VClient.get().getCurrentPackage()) || str.startsWith("com.kaopu.tiantian.Global")) {
                    doHookItemDefault(ttartClassLoader, str, classLoader);
                }
            }
            hookInfoClasses.add(cls);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void doHookDefault_va(ClassLoader classLoader, ClassLoader classLoader2, int i) {
        String[] strArr;
        try {
            Log.d("HookRealMetrics", "doHookDefault_va=1");
            ttartClassLoader = classLoader;
            Class<?> cls = Class.forName("com.kaopu.tiantian.HookInfo", true, classLoader);
            if (i == 0) {
                strArr = (String[]) cls.getField("hookItemNames").get(null);
            } else {
                strArr = (String[]) cls.getField("hookItemNamesLater").get(null);
            }
            for (String str : strArr) {
                if ((str.contains(VClient.get().getCurrentPackage()) || str.startsWith("com.kaopu.tiantian.Global")) && (!str.contains("HookLiuhai") || Build.VERSION.SDK_INT >= 26)) {
                    Class<?> cls2 = Class.forName(str, true, classLoader);
                    try {
                        if (Build.VERSION.SDK_INT > ((Integer) cls2.getField("maxSDK").get(null)).intValue()) {
                        }
                    } catch (NoSuchFieldException unused) {
                    }
                    try {
                        if (Build.VERSION.SDK_INT < ((Integer) cls2.getField("minSDK").get(null)).intValue()) {
                        }
                    } catch (NoSuchFieldException unused2) {
                    }
                    doHookItemDefault(classLoader, str, classLoader2);
                }
            }
            hookInfoClasses.add(cls);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void doHookDefault(ClassLoader classLoader, ClassLoader classLoader2) {
        try {
            ttartClassLoader = classLoader;
            Class<?> cls = Class.forName("com.kaopu.tiantian.HookInfo", true, classLoader);
            for (String str : (String[]) cls.getField("hookItemNames").get(null)) {
                doHookItemDefault(classLoader, str, classLoader2);
            }
            hookInfoClasses.add(cls);
            VLog.m18993d("sunya-yafa", " doHookDefault finish", new Object[0]);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void doHookItemDefault(ClassLoader classLoader, String str, ClassLoader classLoader2) {
        Method[] declaredMethods;
        try {
            Log.d("HookRealMetrics", "doHookItemDefault=1");
            Class<?> cls = Class.forName(str, true, classLoader);
            Method method = null;
            String str2 = (String) cls.getField("className").get(null);
            String str3 = (String) cls.getField("methodName").get(null);
            String str4 = (String) cls.getField("methodSig").get(null);
            if (str2 != null && !str2.equals("")) {
                Class<?> cls2 = Class.forName(str2, true, classLoader2);
                Modifier.isAbstract(cls2.getModifiers());
                Method method2 = null;
                for (Method method3 : cls.getDeclaredMethods()) {
                    if (method3.getName().equals("hook") && Modifier.isStatic(method3.getModifiers())) {
                        method = method3;
                    } else if (method3.getName().equals("backup") && Modifier.isStatic(method3.getModifiers())) {
                        method2 = method3;
                    }
                }
                if (method == null) {
                    VLog.m18992e("HookRealMetrics", "Cannot find hook for " + str3);
                } else if (HookUtil.checkSig(cls2, method.getParameterTypes().length, str3)) {
                    if (str4 == null || str4.equals("")) {
                        str4 = HookUtil.autoFillSig(cls2, str3, method.getParameterTypes().length);
                    }
                    findAndBackupAndHook(cls2, str3, str4, method, method2);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void findAndHook(Class cls, String str, String str2, Method method) {
        hook(findMethod(cls, str, str2), method);
    }

    public static void findAndBackupAndHook(Class cls, String str, String str2, Method method, Method method2) {
        Log.d("HookRealMetrics", "findAndBackupAndHook=1：" + str);
        backupAndHook(findMethod(cls, str, str2), method, method2);
    }

    public static void hook(Method method, Method method2) {
        backupAndHook(method, method2, null);
    }

    public static void backupAndHook(Method method, Method method2, Method method3) {
        if (method == null) {
            throw new IllegalArgumentException("null target method");
        } else if (method2 == null) {
            throw new IllegalArgumentException("null hook method");
        } else if (Modifier.isStatic(method2.getModifiers())) {
            checkCompatibleMethods(method, method2, "Original", "Hook");
            if (method3 != null) {
                if (Modifier.isStatic(method3.getModifiers())) {
                    checkCompatibleMethods(method3, method, "Backup", "Original");
                } else {
                    throw new IllegalArgumentException("Backup must be a static method: " + method2);
                }
            }
            if (!backupAndHookNative(method, method2, method3)) {
                throw new RuntimeException("Failed to hook " + method + " with " + method2);
            }
        } else {
            throw new IllegalArgumentException("Hook must be a static method: " + method2);
        }
    }

    public static String MethodParamString(Method method) {
        Class<?>[] parameterTypes = method.getParameterTypes();
        String str = "(";
        for (int i = 0; i < parameterTypes.length; i++) {
            str = i >= 1 ? str + "," + parameterTypes[i].getName() : str + parameterTypes[i].getName();
        }
        return (str + ")") + method.getReturnType();
    }

    private static Method findMethod(Class cls, String str, String str2) {
        VLog.m18993d("sunya-yafa", "findMethod" + str + str2 + " in class" + cls, new Object[0]);
        if (cls == null) {
            throw new IllegalArgumentException("null class");
        } else if (str == null) {
            throw new IllegalArgumentException("null method name");
        } else if (str2 != null) {
            return findMethodNative(cls, str, str2);
        } else {
            throw new IllegalArgumentException("null method signature");
        }
    }

    private static void checkCompatibleMethods(Method method, Method method2, String str, String str2) {
        ArrayList arrayList = new ArrayList(Arrays.asList(method.getParameterTypes()));
        ArrayList arrayList2 = new ArrayList(Arrays.asList(method2.getParameterTypes()));
        if (!Modifier.isStatic(method.getModifiers())) {
            arrayList.add(0, method.getDeclaringClass());
        }
        if (!Modifier.isStatic(method2.getModifiers())) {
            arrayList2.add(0, method2.getDeclaringClass());
        }
        if (!method.getReturnType().isAssignableFrom(method2.getReturnType())) {
            VLog.m18993d("yafaerr", "Incompatible return types. " + str + ": " + method.getReturnType() + ", " + str2 + ": " + method2.getReturnType(), new Object[0]);
        }
        if (arrayList.size() == arrayList2.size()) {
            for (int i = 0; i < arrayList.size(); i++) {
                if (!((Class) arrayList.get(i)).isAssignableFrom((Class) arrayList2.get(i))) {
                    VLog.m18993d("yafaerr", "Incompatible argument #" + i + ": " + str + ": " + arrayList.get(i) + ", " + str2 + ": " + arrayList2.get(i), new Object[0]);
                }
            }
            return;
        }
        throw new IllegalArgumentException("Number of arguments don't match. " + str + ": " + arrayList.size() + ", " + str2 + ": " + arrayList2.size());
    }

    public static void doHookLater(ClassLoader classLoader) {
        try {
            Class<?> cls = Class.forName("com.kaopu.tiantian.HookInfo", true, ttartClassLoader);
            for (String str : (String[]) cls.getField("hookItemNamesLater").get(null)) {
                doHookItemDefault(ttartClassLoader, str, classLoader);
            }
            hookInfoClasses.add(cls);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void doHookDefault_host(ClassLoader classLoader, ClassLoader classLoader2) {
        doHookItemDefault(classLoader, "com.nrzs.core.util.Hook_getScreenSize", classLoader);
    }

    public static void doHookDefault_list(ClassLoader classLoader, ClassLoader classLoader2, String[] strArr) {
        if (classLoader == null) {
            try {
                classLoader = ttartClassLoader;
            } catch (Exception e) {
                e.printStackTrace();
                return;
            }
        }
        if (classLoader2 == null) {
            classLoader2 = Thread.currentThread().getContextClassLoader();
        }
        for (String str : strArr) {
            doHookItemDefault(classLoader, str, classLoader2);
        }
    }
}
