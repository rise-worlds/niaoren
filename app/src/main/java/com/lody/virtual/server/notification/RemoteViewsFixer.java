package com.lody.virtual.server.notification;

import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.RemoteViews;
import android.widget.TextView;
import com.lody.virtual.C1713R;
import com.lody.virtual.helper.utils.Reflect;
import com.lody.virtual.helper.utils.VLog;
import p110z1.MemoryConstants;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class RemoteViewsFixer {
    private static final boolean DEBUG = false;
    private static final String TAG = NotificationCompat.TAG;
    private NotificationCompat mNotificationCompat;
    private int notification_max_height;
    private int notification_mid_height;
    private int notification_min_height;
    private int notification_padding;
    private int notification_panel_width;
    private int notification_side_padding;
    private boolean init = false;
    private final WidthCompat mWidthCompat = new WidthCompat();

    /* JADX INFO: Access modifiers changed from: package-private */
    public RemoteViewsFixer(NotificationCompat notificationCompat) {
        this.mNotificationCompat = notificationCompat;
    }

    View toView(Context context, RemoteViews remoteViews, boolean z) {
        try {
            try {
                return createView(context, remoteViews, z);
            } catch (Throwable unused) {
                return null;
            }
        } catch (Throwable unused2) {
            return LayoutInflater.from(context).inflate(remoteViews.getLayoutId(), (ViewGroup) null);
        }
    }

    Bitmap createBitmap(View view) {
        if (view == null) {
            return null;
        }
        view.setDrawingCacheEnabled(true);
        view.buildDrawingCache();
        return view.getDrawingCache();
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x0086  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private android.view.View apply(android.content.Context r10, android.widget.RemoteViews r11) {
        /*
            r9 = this;
            r0 = 2
            r1 = 0
            r2 = 1
            r3 = 0
            android.view.LayoutInflater r10 = android.view.LayoutInflater.from(r10)     // Catch: Exception -> 0x0043
            int r4 = r11.getLayoutId()     // Catch: Exception -> 0x0043
            android.view.View r10 = r10.inflate(r4, r1, r3)     // Catch: Exception -> 0x0043
            com.lody.virtual.helper.utils.Reflect r4 = com.lody.virtual.helper.utils.Reflect.m18998on(r10)     // Catch: Exception -> 0x0034
            java.lang.String r5 = "setTagInternal"
            java.lang.Object[] r6 = new java.lang.Object[r0]     // Catch: Exception -> 0x0034
            java.lang.String r7 = "com.android.internal.R$id"
            com.lody.virtual.helper.utils.Reflect r7 = com.lody.virtual.helper.utils.Reflect.m18997on(r7)     // Catch: Exception -> 0x0034
            java.lang.String r8 = "widget_frame"
            java.lang.Object r7 = r7.get(r8)     // Catch: Exception -> 0x0034
            r6[r3] = r7     // Catch: Exception -> 0x0034
            int r7 = r11.getLayoutId()     // Catch: Exception -> 0x0034
            java.lang.Integer r7 = java.lang.Integer.valueOf(r7)     // Catch: Exception -> 0x0034
            r6[r2] = r7     // Catch: Exception -> 0x0034
            r4.call(r5, r6)     // Catch: Exception -> 0x0034
            goto L_0x0050
        L_0x0034:
            r4 = move-exception
            java.lang.String r5 = com.lody.virtual.server.notification.RemoteViewsFixer.TAG     // Catch: Exception -> 0x0041
            java.lang.String r6 = "setTagInternal"
            java.lang.Object[] r7 = new java.lang.Object[r2]     // Catch: Exception -> 0x0041
            r7[r3] = r4     // Catch: Exception -> 0x0041
            com.lody.virtual.helper.utils.VLog.m18986w(r5, r6, r7)     // Catch: Exception -> 0x0041
            goto L_0x0050
        L_0x0041:
            r4 = move-exception
            goto L_0x0045
        L_0x0043:
            r4 = move-exception
            r10 = r1
        L_0x0045:
            java.lang.String r5 = com.lody.virtual.server.notification.RemoteViewsFixer.TAG
            java.lang.String r6 = "inflate"
            java.lang.Object[] r7 = new java.lang.Object[r2]
            r7[r3] = r4
            com.lody.virtual.helper.utils.VLog.m18986w(r5, r6, r7)
        L_0x0050:
            if (r10 == 0) goto L_0x00aa
            com.lody.virtual.helper.utils.Reflect r11 = com.lody.virtual.helper.utils.Reflect.m18998on(r11)
            java.lang.String r4 = "mActions"
            java.lang.Object r11 = r11.get(r4)
            java.util.ArrayList r11 = (java.util.ArrayList) r11
            if (r11 == 0) goto L_0x00aa
            java.lang.String r4 = com.lody.virtual.server.notification.RemoteViewsFixer.TAG
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            java.lang.String r6 = "apply actions:"
            r5.append(r6)
            int r6 = r11.size()
            r5.append(r6)
            java.lang.String r5 = r5.toString()
            java.lang.Object[] r6 = new java.lang.Object[r3]
            com.lody.virtual.helper.utils.VLog.m18993d(r4, r5, r6)
            java.util.Iterator r11 = r11.iterator()
        L_0x0080:
            boolean r4 = r11.hasNext()
            if (r4 == 0) goto L_0x00aa
            java.lang.Object r4 = r11.next()
            com.lody.virtual.helper.utils.Reflect r4 = com.lody.virtual.helper.utils.Reflect.m18998on(r4)     // Catch: Exception -> 0x009d
            java.lang.String r5 = "apply"
            r6 = 3
            java.lang.Object[] r6 = new java.lang.Object[r6]     // Catch: Exception -> 0x009d
            r6[r3] = r10     // Catch: Exception -> 0x009d
            r6[r2] = r1     // Catch: Exception -> 0x009d
            r6[r0] = r1     // Catch: Exception -> 0x009d
            r4.call(r5, r6)     // Catch: Exception -> 0x009d
            goto L_0x0080
        L_0x009d:
            r4 = move-exception
            java.lang.String r5 = com.lody.virtual.server.notification.RemoteViewsFixer.TAG
            java.lang.String r6 = "apply action"
            java.lang.Object[] r7 = new java.lang.Object[r2]
            r7[r3] = r4
            com.lody.virtual.helper.utils.VLog.m18986w(r5, r6, r7)
            goto L_0x0080
        L_0x00aa:
            return r10
        */
        throw new UnsupportedOperationException("Method not decompiled: com.lody.virtual.server.notification.RemoteViewsFixer.apply(android.content.Context, android.widget.RemoteViews):android.view.View");
    }

    private View createView(Context context, RemoteViews remoteViews, boolean z) {
        if (remoteViews == null) {
            return null;
        }
        Context hostContext = this.mNotificationCompat.getHostContext();
        init(hostContext);
        int i = z ? this.notification_max_height : this.notification_min_height;
        int notificationWidth = this.mWidthCompat.getNotificationWidth(hostContext, this.notification_panel_width, i, this.notification_side_padding);
        FrameLayout frameLayout = new FrameLayout(context);
        View apply = apply(context, remoteViews);
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-1, -1);
        layoutParams.gravity = 16;
        frameLayout.addView(apply, layoutParams);
        if (apply instanceof ViewGroup) {
            VLog.m18988v(TAG, "createView:fixTextView");
            fixTextView((ViewGroup) apply);
        }
        frameLayout.layout(0, 0, notificationWidth, i);
        frameLayout.measure(View.MeasureSpec.makeMeasureSpec(notificationWidth, MemoryConstants.f21646d), View.MeasureSpec.makeMeasureSpec(i, Integer.MIN_VALUE));
        frameLayout.layout(0, 0, notificationWidth, frameLayout.getMeasuredHeight());
        return frameLayout;
    }

    private void fixTextView(ViewGroup viewGroup) {
        int childCount = viewGroup.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childAt = viewGroup.getChildAt(i);
            if (childAt instanceof TextView) {
                TextView textView = (TextView) childAt;
                if (isSingleLine(textView)) {
                    textView.setSingleLine(false);
                    textView.setMaxLines(1);
                }
            } else if (childAt instanceof ViewGroup) {
                fixTextView((ViewGroup) childAt);
            }
        }
    }

    private boolean isSingleLine(TextView textView) {
        try {
            return ((Boolean) Reflect.m18998on(textView).get("mSingleLine")).booleanValue();
        } catch (Exception unused) {
            return (textView.getInputType() & 131072) != 0;
        }
    }

    public RemoteViews makeRemoteViews(String str, Context context, RemoteViews remoteViews, boolean z, boolean z2) {
        int i;
        RemoteViews remoteViews2;
        if (remoteViews == null) {
            return null;
        }
        PendIntentCompat pendIntentCompat = new PendIntentCompat(remoteViews);
        if (!z2 || pendIntentCompat.findPendIntents() <= 0) {
            i = C1713R.layout.custom_notification_lite;
        } else {
            i = C1713R.layout.custom_notification;
        }
        if (Build.VERSION.SDK_INT <= 19 || p110z1.RemoteViews.ctor == null) {
            remoteViews2 = new RemoteViews(this.mNotificationCompat.getHostContext().getPackageName(), i);
        } else {
            remoteViews2 = p110z1.RemoteViews.ctor.newInstance(this.mNotificationCompat.getHostContext().getApplicationInfo(), Integer.valueOf(i));
        }
        View view = toView(context, remoteViews, z);
        remoteViews2.setImageViewBitmap(C1713R.C1715id.im_main, createBitmap(view));
        if (z2 && i == C1713R.layout.custom_notification) {
            try {
                pendIntentCompat.setPendIntent(remoteViews2, toView(this.mNotificationCompat.getHostContext(), remoteViews2, z), view);
            } catch (Exception e) {
                VLog.m18991e(TAG, "setPendIntent error", e);
            }
        }
        return remoteViews2;
    }

    private void init(Context context) {
        if (!this.init) {
            this.init = true;
            if (this.notification_panel_width == 0) {
                Context context2 = null;
                try {
                    context2 = context.createPackageContext("com.android.systemui", 2);
                } catch (PackageManager.NameNotFoundException unused) {
                }
                if (Build.VERSION.SDK_INT <= 19) {
                    this.notification_side_padding = 0;
                } else {
                    this.notification_side_padding = getDimem(context, context2, "notification_side_padding", C1713R.dimen.notification_side_padding);
                }
                this.notification_panel_width = getDimem(context, context2, "notification_panel_width", C1713R.dimen.notification_panel_width);
                if (this.notification_panel_width <= 0) {
                    this.notification_panel_width = context.getResources().getDisplayMetrics().widthPixels;
                }
                this.notification_min_height = getDimem(context, context2, "notification_min_height", C1713R.dimen.notification_min_height);
                this.notification_max_height = getDimem(context, context2, "notification_max_height", C1713R.dimen.notification_max_height);
                this.notification_mid_height = getDimem(context, context2, "notification_mid_height", C1713R.dimen.notification_mid_height);
                this.notification_padding = getDimem(context, context2, "notification_padding", C1713R.dimen.notification_padding);
            }
        }
    }

    private int getDimem(Context context, Context context2, String str, int i) {
        int identifier;
        if (!(context2 == null || (identifier = context2.getResources().getIdentifier(str, "dimen", "com.android.systemui")) == 0)) {
            try {
                return Math.round(context2.getResources().getDimension(identifier));
            } catch (Exception unused) {
            }
        }
        if (i == 0) {
            return 0;
        }
        return Math.round(context.getResources().getDimension(i));
    }
}
