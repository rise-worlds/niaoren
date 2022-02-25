package com.kaopu.tiantian.Global;

import android.graphics.Rect;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.Window;
import com.lody.virtual.helper.utils.Reflect;

/* loaded from: classes.dex */
public class HookViewRootWresized_ANDROID9 {
    public static String className = "android.view.ViewRootImpl$W";
    public static String methodName = "resized";
    public static String methodSig = "(Landroid/graphics/Rect;Landroid/graphics/Rect;Landroid/graphics/Rect;Landroid/graphics/Rect;Landroid/graphics/Rect;Landroid/graphics/Rect;ZLandroid/util/MergedConfiguration;Landroid/graphics/Rect;ZZILandroid/view/DisplayCutout$ParcelableWrapper;)V";
    public static int minSDK = 28;

    public static void hook(Object obj, Rect rect, Rect rect2, Rect rect3, Rect rect4, Rect rect5, Rect rect6, boolean z, Object obj2, Rect rect7, boolean z2, boolean z3, int i, Object obj3) {
        Log.d("lbs333", "HookViewRootWresized_ANDROID9");
        Object obj4 = Reflect.m18998on(Reflect.m18998on(Reflect.m18998on(obj).get("mViewAncestor")).call("get").get()).get("mContext");
        if (obj4.getClass().getName().equals("com.android.internal.policy.DecorContext")) {
            Log.d("lbs333", "HookViewRootWresized_ANDROID9 --- 2");
            final Window window = (Window) Reflect.m18998on(obj4).get("mPhoneWindow");
            if (ViewUtil.isFullScreenWindow(window)) {
                if (rect4.top == 0 && rect4.left == 0) {
                    new Handler(Looper.getMainLooper()).postDelayed(new Runnable() { // from class: com.kaopu.tiantian.Global.HookViewRootWresized_ANDROID9.2
                        @Override // java.lang.Runnable
                        public void run() {
                            Log.d("lbs333", "ViewUtil.adjustWindowReal --- 2");
                            ViewUtil.adjustWindowReal(window);
                        }
                    }, 1000L);
                } else {
                    new Handler(Looper.getMainLooper()).postDelayed(new Runnable() { // from class: com.kaopu.tiantian.Global.HookViewRootWresized_ANDROID9.1
                        @Override // java.lang.Runnable
                        public void run() {
                            Log.d("lbs333", "ViewUtil.resetWindowToPadding --- 33");
                            ViewUtil.resetWindowToPadding(window);
                        }
                    }, 0L);
                }
            }
        }
        backup2(obj, rect, rect2, rect3, rect4, rect5, rect6, z, obj2, rect7, z2, z3, i, obj3);
    }

    public static void backup2(Object obj, Rect rect, Rect rect2, Rect rect3, Rect rect4, Rect rect5, Rect rect6, boolean z, Object obj2, Rect rect7, boolean z2, boolean z3, int i, Object obj3) {
        Object obj4 = Reflect.m18998on(Reflect.m18998on(obj).get("mViewAncestor")).call("get").get();
        if (obj4 != null) {
            Reflect.m18998on(obj4).call("dispatchResized", rect, rect2, rect3, rect4, rect5, rect6, Boolean.valueOf(z), obj2, rect7, Boolean.valueOf(z2), Boolean.valueOf(z3), Integer.valueOf(i), obj3);
        }
    }

    public static void backup(Object obj, Rect rect, Rect rect2, Rect rect3, Rect rect4, Rect rect5, Rect rect6, boolean z, Object obj2, Rect rect7, boolean z2, boolean z3, int i, Object obj3) {
        try {
            Log.w("TianTian", "load should not be here");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
