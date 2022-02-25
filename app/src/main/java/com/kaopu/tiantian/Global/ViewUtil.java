package com.kaopu.tiantian.Global;

import android.content.res.Configuration;
import android.graphics.Rect;
import android.os.Build;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import com.kaopu.tiantian.HookInfo;
import com.lody.virtual.helper.utils.Reflect;
import com.lody.virtual.helper.utils.VLog;

/* loaded from: classes.dex */
public class ViewUtil {
    public static void fixwindowsize(Window window) {
    }

    public static boolean isDialog(Window window) {
        boolean z;
        int width = window.getDecorView().getWidth();
        int height = window.getDecorView().getHeight();
        Object obj = Reflect.m18998on(window.getWindowManager().getDefaultDisplay()).get("mDisplayInfo");
        int intValue = ((Integer) Reflect.m18998on(obj).get("logicalWidth")).intValue();
        int intValue2 = ((Integer) Reflect.m18998on(obj).get("logicalHeight")).intValue();
        View decorView = window.getDecorView();
        ((Integer) Reflect.m18998on(decorView).get("mLeft")).intValue();
        ((Integer) Reflect.m18998on(decorView).get("mTop")).intValue();
        ((Integer) Reflect.m18998on(decorView).get("mRight")).intValue();
        ((Integer) Reflect.m18998on(decorView).get("mBottom")).intValue();
        WindowManager.LayoutParams attributes = window.getAttributes();
        if (width != 0 && width != intValue && width != intValue2 && width != (intValue2 * 16) / 9 && width != (intValue * 16) / 9) {
            z = true;
        } else if (height == 0 || height == intValue || height == intValue2 || height == (intValue * 16) / 9 || height == (intValue2 * 16) / 9) {
            z = attributes.width == -2 || attributes.height == -2;
        } else {
            z = true;
        }
        int[] windowPosOnScreen = getWindowPosOnScreen(window);
        int i = windowPosOnScreen[0];
        int i2 = windowPosOnScreen[1];
        return z;
    }

    public static boolean isFullScreenWindow(Window window) {
        int i = window.getAttributes().flags;
        if (isDialog(window)) {
            return false;
        }
        if ((i & 1024) == 1024 || (window.getDecorView().getSystemUiVisibility() & 4) == 4) {
            return true;
        }
        int width = window.getDecorView().getWidth();
        int height = window.getDecorView().getHeight();
        Object obj = Reflect.m18998on(window.getWindowManager().getDefaultDisplay()).get("mDisplayInfo");
        int intValue = ((Integer) Reflect.m18998on(obj).get("logicalWidth")).intValue();
        int intValue2 = ((Integer) Reflect.m18998on(obj).get("logicalHeight")).intValue();
        if (width == (intValue2 * 16) / 9 && height == intValue2) {
            return true;
        }
        return height == (intValue * 16) / 9 && width == intValue;
    }

    public static void resetWindowToPadding(Window window) {
        if (isFullScreenWindow(window)) {
            int[] caculatePadding = caculatePadding(window);
            int i = caculatePadding[0];
            int i2 = caculatePadding[1];
            int i3 = caculatePadding[2];
            int i4 = caculatePadding[3];
            int i5 = caculatePadding[4];
            int i6 = caculatePadding[5];
            int i7 = caculatePadding[6];
            int i8 = caculatePadding[7];
            window.getDecorView().setPadding(i3, i4, i5, i6);
            WindowManager.LayoutParams attributes = window.getAttributes();
            attributes.width = -1;
            attributes.height = -1;
            window.setAttributes(attributes);
        }
    }

    public static int[] getWindowPosOnScreen(Window window) {
        int[] iArr = new int[2];
        window.getDecorView().getLocationOnScreen(iArr);
        return iArr;
    }

    public static int[] caculatePadding(Window window) {
        int i;
        int i2;
        int i3;
        int i4;
        int i5;
        int i6;
        int i7;
        int i8;
        int[] iArr = {0, 0, 0, 0, 0, 0, 0, 0};
        if (isDialog(window)) {
            return iArr;
        }
        window.getDecorView().getLocationOnScreen(new int[2]);
        WindowManager.LayoutParams attributes = window.getAttributes();
        Object obj = Reflect.m18998on(window.getWindowManager().getDefaultDisplay()).get("mDisplayInfo");
        int intValue = ((Integer) Reflect.m18998on(obj).get("logicalWidth")).intValue();
        int intValue2 = ((Integer) Reflect.m18998on(obj).get("logicalHeight")).intValue();
        int[] windowPosOnScreen = getWindowPosOnScreen(window);
        int i9 = windowPosOnScreen[0];
        int i10 = windowPosOnScreen[1];
        window.getDecorView().getWidth();
        window.getDecorView().getHeight();
        boolean z = attributes.width == -1 && attributes.height == (intValue2 * 16) / 9;
        if (attributes.height == -1 && attributes.width == (intValue * 16) / 9) {
            z = true;
        }
        if (attributes.width == intValue2) {
            z = true;
        }
        if (attributes.height == intValue) {
            z = true;
        }
        if (!z) {
            intValue = intValue2;
            intValue2 = intValue;
        }
        if (attributes.width != -1) {
            i9 -= (intValue2 - attributes.width) - i9;
        }
        if (attributes.height != -1) {
            i10 -= (intValue - attributes.height) - i10;
        }
        if (intValue2 > intValue) {
            i = (intValue * 16) / 9;
            i2 = intValue;
        } else {
            i2 = (intValue2 * 16) / 9;
            i = intValue2;
        }
        if (HookInfo.isCenter) {
            i3 = ((intValue2 - i) - i9) / 2;
            i6 = ((intValue - i2) - i10) / 2;
            i5 = i3;
            i4 = i6;
        } else {
            i5 = (intValue2 - i) - i9;
            i4 = (intValue - i2) - i10;
            i3 = 0;
            i6 = 0;
        }
        return (i9 < 0 || i10 < 0 || (i7 = i9 + i3) < 0 || (i8 = i10 + i6) < 0 || (i7 > 0 && i8 > 0) || (i9 > 0 && i10 > 0)) ? new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0} : new int[]{i7, i8, i3, i6, i5, i4, i9, i10};
    }

    public static void adjustWindowReal(Window window) {
        if (isFullScreenWindow(window)) {
            View decorView = window.getDecorView();
            WindowManager windowManager = window.getWindowManager();
            WindowManager.LayoutParams attributes = window.getAttributes();
            Object obj = Reflect.m18998on(windowManager.getDefaultDisplay()).get("mDisplayInfo");
            int intValue = ((Integer) Reflect.m18998on(obj).get("logicalWidth")).intValue();
            int intValue2 = ((Integer) Reflect.m18998on(obj).get("logicalHeight")).intValue();
            boolean z = attributes.width == -1 && decorView.getWidth() == intValue2;
            if (attributes.height == -1 && decorView.getHeight() == intValue) {
                z = true;
            }
            if (intValue > intValue2) {
                decorView.setPadding(0, 0, 0, 0);
                int i = (intValue2 * 16) / 9;
                if (z) {
                    attributes.height = i;
                    attributes.width = -1;
                } else {
                    attributes.width = i;
                    attributes.height = -1;
                }
                if (!HookInfo.isCenter) {
                    attributes.gravity = 3;
                }
                window.setAttributes(attributes);
            } else {
                decorView.setPadding(0, 0, 0, 0);
                int i2 = (intValue * 16) / 9;
                if (z) {
                    attributes.height = -1;
                    attributes.width = i2;
                } else {
                    attributes.width = -1;
                    attributes.height = i2;
                }
                if (!HookInfo.isCenter) {
                    attributes.gravity = 48;
                }
                window.setAttributes(attributes);
            }
            hideNavigation(window);
        }
    }

    public static void hideNavigation(Window window) {
        View decorView = window.getDecorView();
        VLog.m18993d("sunya-viewUtil", "hideNavigation v=" + decorView, new Object[0]);
        decorView.setSystemUiVisibility(decorView.getSystemUiVisibility() | 2 | 512 | 4 | 1024 | 2048);
    }

    public static void adjustConfiguration(Configuration configuration) {
        Rect rect;
        if (Build.VERSION.SDK_INT >= 28) {
            Object obj = Reflect.m18998on(configuration).get("windowConfiguration");
            Rect rect2 = (Rect) Reflect.m18998on(obj).get("mBounds");
            Rect rect3 = (Rect) Reflect.m18998on(obj).get("mAppBounds");
            if (rect2 != null) {
                if (rect2.right > rect2.bottom) {
                    rect2.set(0, 0, (rect2.bottom * 16) / 9, rect2.bottom);
                } else if (rect2.bottom > rect2.right) {
                    rect2.set(0, 0, rect2.right, (rect2.right * 16) / 9);
                }
            }
            if (rect3 == null) {
                return;
            }
            if (rect3.right > rect3.bottom) {
                rect3.set(0, 0, (rect3.bottom * 16) / 9, rect3.bottom);
            } else if (rect3.bottom > rect3.right) {
                rect3.set(0, 0, rect3.right, (rect3.right * 16) / 9);
            }
        } else if (Build.VERSION.SDK_INT >= 26 && (rect = (Rect) Reflect.m18998on(configuration).get("appBounds")) != null) {
            if (rect.right > rect.bottom) {
                rect.set(0, 0, (rect.bottom * 16) / 9, rect.bottom);
            } else if (rect.bottom > rect.right) {
                rect.set(0, 0, rect.right, (rect.right * 16) / 9);
            }
        }
    }
}
