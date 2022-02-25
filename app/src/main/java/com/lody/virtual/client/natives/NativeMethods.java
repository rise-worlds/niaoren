package com.lody.virtual.client.natives;

import android.annotation.SuppressLint;
import android.hardware.Camera;
import android.media.AudioRecord;
import android.media.MediaRecorder;
import android.os.Build;
import com.lody.virtual.client.hook.utils.MethodParameterUtils;
import dalvik.system.DexFile;
import java.lang.reflect.Method;

/* loaded from: classes.dex */
public class NativeMethods {
    public static int gAudioRecordMethodType;
    public static Method gAudioRecordNativeCheckPermission;
    public static Method gAudioRecordNativeSetup;
    public static int gCameraMethodType;
    public static Method gCameraNativeSetup;
    public static Method gMediaRecorderNativeSetup;
    public static Method gOpenDexFileNative;

    static {
        try {
            init();
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    @SuppressLint({"PrivateApi"})
    private static void init() {
        Method[] declaredMethods;
        gMediaRecorderNativeSetup = getMediaRecorderNativeSetup();
        gAudioRecordNativeSetup = getAudioRecordNativeSetup();
        Method method = gAudioRecordNativeSetup;
        if (method == null || method.getParameterTypes().length != 10) {
            gAudioRecordMethodType = 1;
        } else {
            gAudioRecordMethodType = 2;
        }
        String str = Build.VERSION.SDK_INT >= 19 ? "openDexFileNative" : "openDexFile";
        Method[] declaredMethods2 = DexFile.class.getDeclaredMethods();
        int length = declaredMethods2.length;
        int i = 0;
        while (true) {
            if (i >= length) {
                break;
            }
            Method method2 = declaredMethods2[i];
            if (method2.getName().equals(str)) {
                gOpenDexFileNative = method2;
                break;
            }
            i++;
        }
        Method method3 = gOpenDexFileNative;
        if (method3 != null) {
            method3.setAccessible(true);
            gCameraMethodType = -1;
            Method cameraNativeSetup = getCameraNativeSetup();
            if (cameraNativeSetup != null) {
                int paramsIndex = MethodParameterUtils.getParamsIndex(cameraNativeSetup.getParameterTypes(), String.class);
                gCameraNativeSetup = cameraNativeSetup;
                gCameraMethodType = paramsIndex + 16;
            }
            for (Method method4 : AudioRecord.class.getDeclaredMethods()) {
                if (method4.getName().equals("native_check_permission") && method4.getParameterTypes().length == 1 && method4.getParameterTypes()[0] == String.class) {
                    gAudioRecordNativeCheckPermission = method4;
                    method4.setAccessible(true);
                    return;
                }
            }
            return;
        }
        throw new RuntimeException("Unable to find method : " + str);
    }

    private static Method getCameraNativeSetup() {
        Method[] declaredMethods = Camera.class.getDeclaredMethods();
        if (declaredMethods == null) {
            return null;
        }
        for (Method method : declaredMethods) {
            if ("native_setup".equals(method.getName())) {
                return method;
            }
        }
        return null;
    }

    @SuppressLint({"PrivateApi"})
    private static Method getMediaRecorderNativeSetup() {
        Method method;
        try {
            method = MediaRecorder.class.getDeclaredMethod("native_setup", Object.class, String.class, String.class);
        } catch (NoSuchMethodException unused) {
            method = null;
        }
        if (method != null) {
            return method;
        }
        try {
            return MediaRecorder.class.getDeclaredMethod("native_setup", Object.class, String.class);
        } catch (NoSuchMethodException unused2) {
            return method;
        }
    }

    @SuppressLint({"PrivateApi"})
    private static Method getAudioRecordNativeSetup() {
        Method method;
        try {
            method = AudioRecord.class.getDeclaredMethod("native_setup", Object.class, Object.class, int[].class, Integer.TYPE, Integer.TYPE, Integer.TYPE, Integer.TYPE, int[].class, String.class, Long.TYPE);
        } catch (NoSuchMethodException unused) {
            method = null;
        }
        if (method != null) {
            return method;
        }
        try {
            return AudioRecord.class.getDeclaredMethod("native_setup", Object.class, Object.class, Integer.TYPE, Integer.TYPE, Integer.TYPE, Integer.TYPE, Integer.TYPE, int[].class, String.class);
        } catch (NoSuchMethodException unused2) {
            return method;
        }
    }
}
