package com.redwas.service;

import android.accessibilityservice.AccessibilityService;
import android.accessibilityservice.GestureDescription;
import android.annotation.TargetApi;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.graphics.Path;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import android.util.Log;
import android.view.accessibility.AccessibilityNodeInfo;
import java.util.List;

/* loaded from: classes2.dex */
public abstract class BaseAccessbilityService extends AccessibilityService {
    /* JADX INFO: Access modifiers changed from: protected */
    @RequiresApi(api = 24)
    /* renamed from: a */
    public void m18145a(float f, float f2, int i, AccessibilityService.GestureResultCallback gestureResultCallback) {
        Log.e("红包", "点击了x:" + f + "---y :" + f2);
        Path path = new Path();
        path.moveTo(f, f2);
        m18144a(path, 0L, (long) i, gestureResultCallback);
    }

    @RequiresApi(api = 24)
    /* renamed from: a */
    protected void m18144a(Path path, long j, long j2, AccessibilityService.GestureResultCallback gestureResultCallback) {
        GestureDescription.Builder builder = new GestureDescription.Builder();
        builder.addStroke(new GestureDescription.StrokeDescription(path, j, j2));
        dispatchGesture(builder.build(), gestureResultCallback, null);
    }

    /* renamed from: a */
    public void m18143a(AccessibilityNodeInfo accessibilityNodeInfo) {
        if (accessibilityNodeInfo != null) {
            while (accessibilityNodeInfo != null) {
                if (accessibilityNodeInfo.isClickable()) {
                    accessibilityNodeInfo.performAction(16);
                    return;
                }
                accessibilityNodeInfo = accessibilityNodeInfo.getParent();
            }
        }
    }

    /* renamed from: a */
    public void m18146a() {
        try {
            Thread.sleep(200L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        performGlobalAction(1);
    }

    /* renamed from: b */
    public void m18138b() {
        try {
            Thread.sleep(500L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        performGlobalAction(8192);
    }

    /* renamed from: c */
    public void m18135c() {
        try {
            Thread.sleep(500L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        performGlobalAction(4096);
    }

    /* renamed from: a */
    public AccessibilityNodeInfo m18140a(String str) {
        return m18139a(str, false);
    }

    /* renamed from: a */
    public AccessibilityNodeInfo m18139a(String str, boolean z) {
        List<AccessibilityNodeInfo> findAccessibilityNodeInfosByText;
        AccessibilityNodeInfo rootInActiveWindow = getRootInActiveWindow();
        if (!(rootInActiveWindow == null || (findAccessibilityNodeInfosByText = rootInActiveWindow.findAccessibilityNodeInfosByText(str)) == null || findAccessibilityNodeInfosByText.isEmpty())) {
            for (AccessibilityNodeInfo accessibilityNodeInfo : findAccessibilityNodeInfosByText) {
                if (accessibilityNodeInfo != null && accessibilityNodeInfo.isClickable() == z) {
                    return accessibilityNodeInfo;
                }
            }
        }
        return null;
    }

    /* renamed from: a */
    public AccessibilityNodeInfo m18141a(AccessibilityNodeInfo accessibilityNodeInfo, String str, boolean z) {
        List<AccessibilityNodeInfo> findAccessibilityNodeInfosByText;
        if (!(accessibilityNodeInfo == null || (findAccessibilityNodeInfosByText = accessibilityNodeInfo.findAccessibilityNodeInfosByText(str)) == null || findAccessibilityNodeInfosByText.isEmpty())) {
            for (AccessibilityNodeInfo accessibilityNodeInfo2 : findAccessibilityNodeInfosByText) {
                if (accessibilityNodeInfo2 != null && accessibilityNodeInfo2.isClickable() == z) {
                    return accessibilityNodeInfo2;
                }
            }
        }
        return null;
    }

    @TargetApi(18)
    /* renamed from: b */
    public AccessibilityNodeInfo m18136b(String str) {
        List<AccessibilityNodeInfo> findAccessibilityNodeInfosByViewId;
        AccessibilityNodeInfo rootInActiveWindow = getRootInActiveWindow();
        if (!(rootInActiveWindow == null || (findAccessibilityNodeInfosByViewId = rootInActiveWindow.findAccessibilityNodeInfosByViewId(str)) == null || findAccessibilityNodeInfosByViewId.isEmpty())) {
            for (AccessibilityNodeInfo accessibilityNodeInfo : findAccessibilityNodeInfosByViewId) {
                if (accessibilityNodeInfo != null) {
                    return accessibilityNodeInfo;
                }
            }
        }
        return null;
    }

    @TargetApi(18)
    /* renamed from: a */
    public AccessibilityNodeInfo m18142a(AccessibilityNodeInfo accessibilityNodeInfo, String str) {
        List<AccessibilityNodeInfo> findAccessibilityNodeInfosByViewId;
        if (!(accessibilityNodeInfo == null || (findAccessibilityNodeInfosByViewId = accessibilityNodeInfo.findAccessibilityNodeInfosByViewId(str)) == null || findAccessibilityNodeInfosByViewId.isEmpty())) {
            for (AccessibilityNodeInfo accessibilityNodeInfo2 : findAccessibilityNodeInfosByViewId) {
                if (accessibilityNodeInfo2 != null) {
                    return accessibilityNodeInfo2;
                }
            }
        }
        return null;
    }

    @TargetApi(18)
    /* renamed from: c */
    public List<AccessibilityNodeInfo> m18134c(String str) {
        AccessibilityNodeInfo rootInActiveWindow = getRootInActiveWindow();
        if (rootInActiveWindow == null) {
            return null;
        }
        return rootInActiveWindow.findAccessibilityNodeInfosByViewId(str);
    }

    /* renamed from: d */
    public void m18133d(String str) {
        List<AccessibilityNodeInfo> findAccessibilityNodeInfosByText;
        AccessibilityNodeInfo rootInActiveWindow = getRootInActiveWindow();
        if (!(rootInActiveWindow == null || (findAccessibilityNodeInfosByText = rootInActiveWindow.findAccessibilityNodeInfosByText(str)) == null || findAccessibilityNodeInfosByText.isEmpty())) {
            for (AccessibilityNodeInfo accessibilityNodeInfo : findAccessibilityNodeInfosByText) {
                if (accessibilityNodeInfo != null) {
                    m18143a(accessibilityNodeInfo);
                    return;
                }
            }
        }
    }

    @TargetApi(18)
    /* renamed from: e */
    public void m18132e(String str) {
        List<AccessibilityNodeInfo> findAccessibilityNodeInfosByViewId;
        AccessibilityNodeInfo rootInActiveWindow = getRootInActiveWindow();
        if (!(rootInActiveWindow == null || (findAccessibilityNodeInfosByViewId = rootInActiveWindow.findAccessibilityNodeInfosByViewId(str)) == null || findAccessibilityNodeInfosByViewId.isEmpty())) {
            for (AccessibilityNodeInfo accessibilityNodeInfo : findAccessibilityNodeInfosByViewId) {
                if (accessibilityNodeInfo != null) {
                    m18143a(accessibilityNodeInfo);
                    return;
                }
            }
        }
    }

    /* renamed from: b */
    public void m18137b(AccessibilityNodeInfo accessibilityNodeInfo, String str) {
        if (Build.VERSION.SDK_INT >= 21) {
            Bundle bundle = new Bundle();
            bundle.putCharSequence(AccessibilityNodeInfoCompat.ACTION_ARGUMENT_SET_TEXT_CHARSEQUENCE, str);
            accessibilityNodeInfo.performAction(2097152, bundle);
        } else if (Build.VERSION.SDK_INT >= 18) {
            ((ClipboardManager) getSystemService("clipboard")).setPrimaryClip(ClipData.newPlainText("label", str));
            accessibilityNodeInfo.performAction(1);
            accessibilityNodeInfo.performAction(32768);
        }
    }
}
