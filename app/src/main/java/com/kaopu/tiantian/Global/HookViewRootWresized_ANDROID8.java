package com.kaopu.tiantian.Global;

import android.graphics.Rect;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.Window;
import com.lody.virtual.helper.utils.Reflect;

/* loaded from: classes.dex */
public class HookViewRootWresized_ANDROID8 {
    public static String className = "android.view.ViewRootImpl$W";
    public static int maxSDK = 27;
    public static String methodName = "resized";
    public static String methodSig = "(Landroid/graphics/Rect;Landroid/graphics/Rect;Landroid/graphics/Rect;Landroid/graphics/Rect;Landroid/graphics/Rect;Landroid/graphics/Rect;ZLandroid/util/MergedConfiguration;Landroid/graphics/Rect;ZZI)V";
    public static int minSDK = 26;

    public static void hook(Object obj, Rect rect, Rect rect2, Rect rect3, Rect rect4, Rect rect5, Rect rect6, boolean z, Object obj2, Rect rect7, boolean z2, boolean z3, int i) {
        Object obj3 = Reflect.m18998on(Reflect.m18998on(Reflect.m18998on(obj).get("mViewAncestor")).call("get").get()).get("mContext");
        if (obj3.getClass().getName().equals("com.android.internal.policy.DecorContext")) {
            final Window window = (Window) Reflect.m18998on(obj3).get("mPhoneWindow");
            if (ViewUtil.isFullScreenWindow(window)) {
                if (rect4.top == 0 && rect4.left == 0) {
                    new Handler(Looper.getMainLooper()).postDelayed(new Runnable() { // from class: com.kaopu.tiantian.Global.HookViewRootWresized_ANDROID8.2
                        @Override // java.lang.Runnable
                        public void run() {
                            ViewUtil.adjustWindowReal(window);
                        }
                    }, 500L);
                } else {
                    new Handler(Looper.getMainLooper()).postDelayed(new Runnable() { // from class: com.kaopu.tiantian.Global.HookViewRootWresized_ANDROID8.1
                        @Override // java.lang.Runnable
                        public void run() {
                            ViewUtil.resetWindowToPadding(window);
                        }
                    }, 0L);
                }
            }
        }
        backup2(obj, rect, rect2, rect3, rect4, rect5, rect6, z, obj2, rect7, z2, z3, i);
    }

    public static void backup2(Object obj, Rect rect, Rect rect2, Rect rect3, Rect rect4, Rect rect5, Rect rect6, boolean z, Object obj2, Rect rect7, boolean z2, boolean z3, int i) {
        Object obj3 = Reflect.m18998on(Reflect.m18998on(obj).get("mViewAncestor")).call("get").get();
        if (obj3 != null) {
            Reflect.m18998on(obj3).call("dispatchResized", rect, rect2, rect3, rect4, rect5, rect6, Boolean.valueOf(z), obj2, rect7, Boolean.valueOf(z2), Boolean.valueOf(z3), Integer.valueOf(i));
        }
    }

    public static void backup(Object obj, Rect rect, Rect rect2, Rect rect3, Rect rect4, Rect rect5, Rect rect6, boolean z, Object obj2, Rect rect7, boolean z2, boolean z3, int i) {
        try {
            Log.w("TianTian", "load should not be here");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
