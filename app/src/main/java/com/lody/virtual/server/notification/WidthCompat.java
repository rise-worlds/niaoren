package com.lody.virtual.server.notification;

import android.content.Context;
import android.os.Build;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import com.liulishuo.filedownloader.model.ConnectionModel;
import com.lody.virtual.client.ipc.ServiceManagerNative;
import com.lody.virtual.helper.compat.BuildCompat;

/* loaded from: classes.dex */
class WidthCompat {
    private static final String TAG = "WidthCompat";
    private volatile int mWidth = 0;

    public int getNotificationWidth(Context context, int i, int i2, int i3) {
        if (this.mWidth > 0) {
            return this.mWidth;
        }
        int defaultWidth = getDefaultWidth(i, i3);
        if (BuildCompat.isEMUI()) {
            defaultWidth = getEMUINotificationWidth(context, i, i2);
        } else if (BuildCompat.isMIUI()) {
            if (Build.VERSION.SDK_INT >= 21) {
                defaultWidth = getMIUINotificationWidth(context, i - (Math.round(TypedValue.applyDimension(1, 10.0f, context.getResources().getDisplayMetrics())) * 2), i2);
            } else {
                defaultWidth = getMIUINotificationWidth(context, i - (Math.round(TypedValue.applyDimension(1, 25.0f, context.getResources().getDisplayMetrics())) * 2), i2);
            }
        }
        this.mWidth = defaultWidth;
        return defaultWidth;
    }

    private int getDefaultWidth(int i, int i2) {
        return Build.VERSION.SDK_INT >= 21 ? i - (i2 * 2) : i;
    }

    private int getMIUINotificationWidth(Context context, int i, int i2) {
        try {
            Context createPackageContext = context.createPackageContext("com.android.systemui", 3);
            int systemId = getSystemId(createPackageContext, "status_bar_notification_row", "layout");
            if (systemId != 0) {
                ViewGroup createViewGroup = createViewGroup(createPackageContext, systemId);
                int systemId2 = getSystemId(createPackageContext, "adaptive", ConnectionModel.f10389a);
                if (systemId2 == 0) {
                    systemId2 = getSystemId(createPackageContext, ServiceManagerNative.CONTENT, ConnectionModel.f10389a);
                } else {
                    View findViewById = createViewGroup.findViewById(systemId2);
                    if (findViewById != null && (findViewById instanceof ViewGroup)) {
                        ((ViewGroup) findViewById).addView(new View(createPackageContext));
                    }
                }
                layout(createViewGroup, i, i2);
                if (systemId2 != 0) {
                    View findViewById2 = createViewGroup.findViewById(systemId2);
                    if (findViewById2 != null) {
                        return ((i - findViewById2.getLeft()) - findViewById2.getPaddingLeft()) - findViewById2.getPaddingRight();
                    }
                } else {
                    int childCount = createViewGroup.getChildCount();
                    for (int i3 = 0; i3 < childCount; i3++) {
                        View childAt = createViewGroup.getChildAt(i3);
                        if (!FrameLayout.class.isInstance(childAt) && !"LatestItemView".equals(childAt.getClass().getName()) && !"SizeAdaptiveLayout".equals(childAt.getClass().getName())) {
                        }
                        return ((i - childAt.getLeft()) - childAt.getPaddingLeft()) - childAt.getPaddingRight();
                    }
                }
            }
        } catch (Exception unused) {
        }
        return i;
    }

    private int getEMUINotificationWidth(Context context, int i, int i2) {
        try {
            Context createPackageContext = context.createPackageContext("com.android.systemui", 3);
            int systemId = getSystemId(createPackageContext, "time_axis", "layout");
            if (systemId != 0) {
                ViewGroup createViewGroup = createViewGroup(createPackageContext, systemId);
                layout(createViewGroup, i, i2);
                int systemId2 = getSystemId(createPackageContext, "content_view_group", ConnectionModel.f10389a);
                if (systemId2 != 0) {
                    View findViewById = createViewGroup.findViewById(systemId2);
                    return ((i - findViewById.getLeft()) - findViewById.getPaddingLeft()) - findViewById.getPaddingRight();
                }
                int childCount = createViewGroup.getChildCount();
                for (int i3 = 0; i3 < childCount; i3++) {
                    View childAt = createViewGroup.getChildAt(i3);
                    if (LinearLayout.class.isInstance(childAt)) {
                        return ((i - childAt.getLeft()) - childAt.getPaddingLeft()) - childAt.getPaddingRight();
                    }
                }
            }
        } catch (Exception unused) {
        }
        return i;
    }

    private int getSystemId(Context context, String str, String str2) {
        return context.getResources().getIdentifier(str, str2, "com.android.systemui");
    }

    private ViewGroup createViewGroup(Context context, int i) {
        try {
            return (ViewGroup) LayoutInflater.from(context).inflate(i, (ViewGroup) null);
        } catch (Throwable unused) {
            return new FrameLayout(context);
        }
    }

    private void layout(View view, int i, int i2) {
        view.layout(0, 0, i, i2);
        view.measure(View.MeasureSpec.makeMeasureSpec(i, Integer.MIN_VALUE), View.MeasureSpec.makeMeasureSpec(i2, Integer.MIN_VALUE));
        view.layout(0, 0, i, i2);
    }
}
