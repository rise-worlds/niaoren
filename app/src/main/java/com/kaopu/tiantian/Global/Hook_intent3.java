package com.kaopu.tiantian.Global;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import com.lody.virtual.client.VClient;
import com.lody.virtual.helper.utils.Reflect;
import java.lang.reflect.Field;

/* loaded from: classes.dex */
public class Hook_intent3 {
    private static String TAG = "tiantian";
    public static String className = "android.app.Activity";
    public static String methodName = "setResult";
    public static String methodSig = "(ILandroid/content/Intent;)V";

    public static void hook(Activity activity, int i, Intent intent) {
        if (i != -1 || !"com.tencent.tmgp.tmsk.qj2".equals(VClient.get().getCurrentPackage()) || !"com.tencent.connect.common.AssistActivity".equals(activity.getClass().getName())) {
            backup2(activity, i, intent);
        } else {
            Reflect.m18998on(Reflect.m18996on("com.tencent.msdk.framework.MSDKEnv", activity.getClassLoader()).call("getInstance").get("currentActivity")).call("onActivityResult", 11101, Integer.valueOf(i), intent);
        }
    }

    public static void backup2(Activity activity, int i, Intent intent) {
        try {
            Class<?> cls = Class.forName("android.app.Activity");
            Field declaredField = cls.getDeclaredField("mResultCode");
            Field declaredField2 = cls.getDeclaredField("mResultData");
            declaredField.setAccessible(true);
            declaredField2.setAccessible(true);
            declaredField.set(activity, Integer.valueOf(i));
            declaredField2.set(activity, intent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void backup(Activity activity, int i, Intent intent) {
        try {
            Log.w(TAG, "Hook_AndroidProxySelector_getProxyUrl should not be here");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
