package com.kaopu.tiantian.Global;

import android.graphics.Rect;
import android.graphics.Region;
import android.os.Build;
import android.util.Log;
import android.view.View;
import android.view.WindowInsets;
import com.lody.virtual.helper.utils.Reflect;

/* loaded from: classes.dex */
public class HookViewRootInsets {
    public static String className = "android.view.ViewRootImpl";
    public static String methodName = "dispatchApplyInsets";
    public static String methodSig = "(Landroid/view/View;)V";
    public static int minSDK = 28;

    public static void hook(Object obj, View view) {
        Object obj2;
        Object obj3;
        if (Build.VERSION.SDK_INT >= 28 && (obj3 = Reflect.m18998on(obj).get("mAttachInfo")) != null) {
            Object obj4 = Build.VERSION.SDK_INT == 28 ? Reflect.m18997on("android.view.DisplayCutout").create(new Rect(), new Region(), false).get() : Reflect.m18997on("android.view.DisplayCutout").create(new Rect(), new Rect(), new Rect(), new Rect(), new Rect(), false).get();
            Reflect.m18998on(Reflect.m18998on(obj3).get("mDisplayCutout")).set("mInner", obj4);
            Reflect.m18998on(obj3).set("mContentInsets", new Rect());
            Reflect.m18998on(obj3).set("mStableInsets", new Rect());
            Reflect.m18998on(Reflect.m18998on(obj).get("mPendingDisplayCutout")).call("set", obj4);
            Reflect.m18998on(obj).set("mPendingContentInsets", new Rect());
        }
        WindowInsets windowInsets = (WindowInsets) Reflect.m18998on(obj).call("getWindowInsets", true).get();
        if (Build.VERSION.SDK_INT >= 28 && (obj2 = Reflect.m18998on(windowInsets).get("mDisplayCutout")) != null) {
            ((Rect) Reflect.m18998on(obj2).get("mSafeInsets")).set(0, 0, 0, 0);
            Reflect.m18998on(obj2).set("mSafeInsets", new Rect(null));
            Reflect.m18998on(obj2).set("mBounds", Build.VERSION.SDK_INT == 28 ? new Region(0, 0, 0, 0) : Reflect.m18997on("android.view.DisplayCutout$Bounds").create(new Rect(), new Rect(), new Rect(), new Rect(), false).get());
        }
        Reflect.m18998on(view).call("dispatchApplyWindowInsets", windowInsets);
    }

    public static void backup(Object obj, View view) {
        try {
            Log.w("TianTian", "load should not be here");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
