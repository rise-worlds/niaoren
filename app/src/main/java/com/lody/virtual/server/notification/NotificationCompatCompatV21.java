package com.lody.virtual.server.notification;

import android.annotation.TargetApi;
import android.app.Notification;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.RemoteViews;
import com.lody.virtual.client.core.VirtualCore;
import com.lody.virtual.client.ipc.VPackageManager;
import com.lody.virtual.helper.compat.NotificationChannelCompat;
import com.lody.virtual.helper.utils.Reflect;
import com.lody.virtual.helper.utils.VLog;
import p110z1.NotificationO;

@TargetApi(21)
/* loaded from: classes.dex */
class NotificationCompatCompatV21 extends NotificationCompatCompatV14 {
    private static final String TAG = "NotificationCompatCompatV21";

    @Override // com.lody.virtual.server.notification.NotificationCompatCompatV14, com.lody.virtual.server.notification.NotificationCompat
    public boolean dealNotification(int i, Notification notification, String str) {
        Context appContext = getAppContext(str);
        if (Build.VERSION.SDK_INT >= 26 && VirtualCore.get().getTargetSdkVersion() >= 26 && TextUtils.isEmpty(notification.getChannelId())) {
            NotificationO.mChannelId.set(notification, NotificationChannelCompat.DEFAULT_ID);
        }
        try {
            if (!resolveRemoteViews(appContext, i, str, notification)) {
                if (!resolveRemoteViews(appContext, i, str, notification.publicVersion)) {
                    return false;
                }
            }
            return true;
        } catch (Throwable unused) {
            VLog.m18992e(TAG, "error deal Notification!");
            return false;
        }
    }

    private PackageInfo getOutSidePackageInfo(String str) {
        try {
            return VirtualCore.get().getUnHookPackageManager().getPackageInfo(str, 1024);
        } catch (Throwable unused) {
            return null;
        }
    }

    private boolean resolveRemoteViews(Context context, int i, String str, Notification notification) {
        ApplicationInfo applicationInfo;
        if (notification == null) {
            return false;
        }
        ApplicationInfo applicationInfo2 = getHostContext().getApplicationInfo();
        PackageInfo outSidePackageInfo = getOutSidePackageInfo(str);
        PackageInfo packageInfo = VPackageManager.get().getPackageInfo(str, 1024, 0);
        boolean z = outSidePackageInfo != null && outSidePackageInfo.versionCode == packageInfo.versionCode;
        getNotificationFixer().fixNotificationRemoteViews(context, notification);
        if (Build.VERSION.SDK_INT >= 23) {
            getNotificationFixer().fixIcon(notification.getSmallIcon(), context, z);
            getNotificationFixer().fixIcon(notification.getLargeIcon(), context, z);
        } else {
            getNotificationFixer().fixIconImage(context.getResources(), notification.contentView, false, notification);
        }
        notification.icon = applicationInfo2.icon;
        if (z) {
            applicationInfo = outSidePackageInfo.applicationInfo;
        } else {
            applicationInfo = packageInfo.applicationInfo;
        }
        applicationInfo.targetSdkVersion = 22;
        fixApplicationInfo(notification.tickerView, applicationInfo);
        fixApplicationInfo(notification.contentView, applicationInfo);
        fixApplicationInfo(notification.bigContentView, applicationInfo);
        fixApplicationInfo(notification.headsUpContentView, applicationInfo);
        Bundle bundle = (Bundle) Reflect.m18998on(notification).get("extras");
        if (bundle != null) {
            bundle.putParcelable(NotificationCompat.EXTRA_BUILDER_APPLICATION_INFO, applicationInfo);
        }
        if (Build.VERSION.SDK_INT >= 26 && !z) {
            remakeRemoteViews(i, notification, context);
        }
        return true;
    }

    private ApplicationInfo getApplicationInfo(Notification notification) {
        ApplicationInfo applicationInfo;
        ApplicationInfo applicationInfo2;
        ApplicationInfo applicationInfo3 = getApplicationInfo(notification.tickerView);
        if (applicationInfo3 != null) {
            return applicationInfo3;
        }
        ApplicationInfo applicationInfo4 = getApplicationInfo(notification.contentView);
        if (applicationInfo4 != null) {
            return applicationInfo4;
        }
        if (Build.VERSION.SDK_INT >= 16 && (applicationInfo2 = getApplicationInfo(notification.bigContentView)) != null) {
            return applicationInfo2;
        }
        if (Build.VERSION.SDK_INT < 21 || (applicationInfo = getApplicationInfo(notification.headsUpContentView)) == null) {
            return null;
        }
        return applicationInfo;
    }

    private ApplicationInfo getApplicationInfo(RemoteViews remoteViews) {
        if (remoteViews != null) {
            return p110z1.RemoteViews.mApplication.get(remoteViews);
        }
        return null;
    }

    private void fixApplicationInfo(RemoteViews remoteViews, ApplicationInfo applicationInfo) {
        if (remoteViews != null) {
            p110z1.RemoteViews.mApplication.set(remoteViews, applicationInfo);
        }
    }
}
