package com.kaopu.tiantian.Global;

import android.os.Build;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import com.kaopu.tiantian.HookInfo;
import com.lody.virtual.client.core.VirtualCore;
import com.lody.virtual.helper.utils.Reflect;
import com.lody.virtual.helper.utils.VLog;

/* loaded from: classes.dex */
public class HookView {
    public static String className = "android.view.WindowManagerImpl";
    public static String methodName = "addView";
    public static String methodSig = "(Landroid/view/View;Landroid/view/ViewGroup$LayoutParams;)V";

    public static void hook(Object obj, Object obj2, Object obj3) {
        Window window;
        WindowManager windowManager = (WindowManager) obj;
        View view = (View) obj2;
        WindowManager.LayoutParams layoutParams = (WindowManager.LayoutParams) obj3;
        VLog.m18993d("lbsxxxx", "dview->lpsiz" + obj2.getClass().getName(), new Object[0]);
        if (obj2.getClass().getName().equals("com.android.internal.policy.DecorView") || obj2.getClass().getName().equals("com.android.internal.policy.impl.PhoneWindow$DecorView")) {
            if (Build.VERSION.SDK_INT >= 28 && (window = (Window) Reflect.m18998on(obj).get("mParentWindow")) != null) {
                WindowManager.LayoutParams attributes = window.getAttributes();
                if (((Integer) Reflect.m18998on(attributes).get("layoutInDisplayCutoutMode")).intValue() != 1) {
                    Reflect.m18998on(attributes).set("layoutInDisplayCutoutMode", 1);
                    window.setAttributes(attributes);
                }
            }
            int i = VirtualCore.get().getContext().getResources().getConfiguration().orientation;
            if (i == 2) {
                VLog.m18993d("lbsxxxx", "方向=横屏", new Object[0]);
            } else if (i == 1) {
                VLog.m18993d("lbsxxxx", "方向=竖屏", new Object[0]);
            }
            Window window2 = (Window) Reflect.m18998on(obj).get("mParentWindow");
            VLog.m18993d("lbsxxxx", "addview->lpsize=" + layoutParams.width + "," + layoutParams.height + " lp.flags=" + Integer.toHexString(layoutParams.flags) + " view=" + view + " window=" + window2 + " context=" + window2.getContext(), new Object[0]);
            StringBuilder sb = new StringBuilder();
            sb.append("方向=");
            sb.append(layoutParams.screenOrientation);
            VLog.m18993d("lbsxxxx", sb.toString(), new Object[0]);
            Object obj4 = Reflect.m18998on(windowManager.getDefaultDisplay()).get("mDisplayInfo");
            int intValue = ((Integer) Reflect.m18998on(obj4).get("logicalWidth")).intValue();
            int intValue2 = ((Integer) Reflect.m18998on(obj4).get("logicalHeight")).intValue();
            StringBuilder sb2 = new StringBuilder();
            sb2.append("窗口调整过  logicalWidth:");
            sb2.append(intValue);
            VLog.m18993d("lbsxxxx", sb2.toString(), new Object[0]);
            VLog.m18993d("lbsxxxx", "窗口调整过  logicalHeight:" + intValue2, new Object[0]);
            int intValue3 = ((Integer) Reflect.m18998on(obj4).get("appWidth")).intValue();
            int intValue4 = ((Integer) Reflect.m18998on(obj4).get("appHeight")).intValue();
            VLog.m18993d("lbsxxxx", "窗口调整过  appWidth:" + intValue3, new Object[0]);
            VLog.m18993d("lbsxxxx", "窗口调整过  appHeight:" + intValue4, new Object[0]);
            if (layoutParams.x != 0 || layoutParams.y != 0) {
                VLog.m18993d("lbsxxxx", "窗口调整过  不再做屏幕适配", new Object[0]);
            } else if (layoutParams.height == -2 && intValue2 > intValue) {
                VLog.m18993d("lbsxxxx", "wxxx -- 1", new Object[0]);
                if (HookInfo.isCenter) {
                    VLog.m18993d("lbsxxxx", "wxxx -- 2", new Object[0]);
                } else {
                    VLog.m18993d("lbsxxxx", "wxxx -- 3", new Object[0]);
                    layoutParams.y = ((intValue2 - ((intValue * 16) / 9)) * (-1)) / 2;
                }
            } else if (layoutParams.width == -2 && intValue > intValue2) {
                VLog.m18993d("lbsxxxx", "wxxx -- 4", new Object[0]);
                if (HookInfo.isCenter) {
                    VLog.m18993d("lbsxxxx", "wxxx -- 5", new Object[0]);
                } else {
                    VLog.m18993d("lbsxxxx", "wxxx -- 6", new Object[0]);
                    layoutParams.x = ((intValue - ((intValue2 * 16) / 9)) * (-1)) / 2;
                }
            } else if (layoutParams.width == -1 && intValue > intValue2) {
                int i2 = (intValue2 * 16) / 9;
                if (intValue > i2) {
                    layoutParams.width = i2;
                }
                if (!HookInfo.isCenter) {
                    layoutParams.gravity = 3;
                }
            } else if (layoutParams.height == -1 && intValue2 > intValue) {
                int i3 = (intValue * 16) / 9;
                if (intValue2 > i3) {
                    layoutParams.height = i3;
                }
                if (!HookInfo.isCenter) {
                    layoutParams.gravity = 48;
                }
            }
        } else if (obj2.getClass().getName().contains("com.tencent.ysdk.module.icon") || obj2.getClass().getName().contains("standout.ui.Window") || obj2.getClass().getName().contains("cn.uc.gamesdk.lib.ui.view.floater.FloatWindowView$a") || obj2.getClass().getName().contains("com.huawei.appmarket.component.buoycircle.impl.view.FloatWindowSmallView")) {
            return;
        }
        int intValue5 = ((Integer) Reflect.m18998on(windowManager.getDefaultDisplay()).field("mDisplayInfo").get("rotation")).intValue();
        backup2(obj, obj2, obj3);
        int intValue6 = ((Integer) Reflect.m18998on(windowManager.getDefaultDisplay()).field("mDisplayInfo").get("rotation")).intValue();
        if (intValue5 == intValue6) {
            return;
        }
        if (obj2.getClass().getName().equals("com.android.internal.policy.DecorView") || obj2.getClass().getName().equals("com.android.internal.policy.impl.PhoneWindow$DecorView")) {
            Window window3 = (Window) Reflect.m18998on(obj).get("mParentWindow");
            WindowManager.LayoutParams attributes2 = window3.getAttributes();
            Object obj5 = Reflect.m18998on(windowManager.getDefaultDisplay()).get("mDisplayInfo");
            int intValue7 = ((Integer) Reflect.m18998on(obj5).get("logicalWidth")).intValue();
            int intValue8 = ((Integer) Reflect.m18998on(obj5).get("logicalHeight")).intValue();
            if (intValue7 > intValue8) {
                attributes2.width = (intValue8 * 16) / 9;
                attributes2.height = -1;
            } else {
                attributes2.width = -1;
                attributes2.height = (intValue7 * 16) / 9;
            }
            VLog.m18993d("sunya-addview", "屏幕发生了翻转 重设大小  oration=" + intValue5 + "->" + intValue6 + " wh=" + attributes2.width + "," + attributes2.height + " logical=" + intValue7 + "," + intValue8, new Object[0]);
            window3.setAttributes(attributes2);
        }
    }

    public static void backup(Object obj, Object obj2, Object obj3) {
        try {
            Log.w("TianTian", "load should not be here");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void backup2(Object obj, Object obj2, Object obj3) {
        VLog.m18993d("lbsxxxx", "addview sdk=" + Build.VERSION.SDK_INT, new Object[0]);
        switch (Build.VERSION.SDK_INT) {
            case 21:
                Reflect.m18998on(Reflect.m18998on(obj).get("mGlobal")).call("addView", obj2, obj3, Reflect.m18998on(obj).get("mDisplay"), Reflect.m18998on(obj).get("mParentWindow"));
                return;
            case 22:
            case 23:
                Reflect.m18998on(obj).call("applyDefaultToken", obj3);
                Reflect.m18998on(Reflect.m18998on(obj).get("mGlobal")).call("addView", obj2, obj3, Reflect.m18998on(obj).get("mDisplay"), Reflect.m18998on(obj).get("mParentWindow"));
                return;
            default:
                Reflect.m18998on(obj).call("applyDefaultToken", obj3);
                Reflect.m18998on(Reflect.m18998on(obj).get("mGlobal")).call("addView", obj2, obj3, Reflect.m18998on(Reflect.m18998on(obj).get("mContext")).call("getDisplay").get(), Reflect.m18998on(obj).get("mParentWindow"));
                return;
        }
    }
}
