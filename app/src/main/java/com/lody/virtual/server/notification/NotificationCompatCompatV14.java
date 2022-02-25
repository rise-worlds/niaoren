package com.lody.virtual.server.notification;

import android.app.Notification;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import com.lody.virtual.client.VClient;
import com.lody.virtual.client.core.VirtualCore;

/* loaded from: classes.dex */
class NotificationCompatCompatV14 extends NotificationCompat {
    private final RemoteViewsFixer mRemoteViewsFixer = new RemoteViewsFixer(this);

    private RemoteViewsFixer getRemoteViewsFixer() {
        return this.mRemoteViewsFixer;
    }

    @Override // com.lody.virtual.server.notification.NotificationCompat
    public boolean dealNotification(int i, Notification notification, String str) {
        Context appContext = getAppContext(str);
        if (appContext == null) {
            return false;
        }
        if (!VClient.get().isAppUseOutsideAPK() || !VirtualCore.get().isOutsideInstalled(str)) {
            remakeRemoteViews(i, notification, appContext);
            if (notification.icon != 0) {
                notification.icon = getHostContext().getApplicationInfo().icon;
            }
            return true;
        }
        if (notification.icon != 0) {
            getNotificationFixer().fixIconImage(appContext.getResources(), notification.contentView, false, notification);
            if (Build.VERSION.SDK_INT >= 16) {
                getNotificationFixer().fixIconImage(appContext.getResources(), notification.bigContentView, false, notification);
            }
            notification.icon = getHostContext().getApplicationInfo().icon;
        }
        return true;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void remakeRemoteViews(int i, Notification notification, Context context) {
        if (notification.tickerView != null) {
            if (isSystemLayout(notification.tickerView)) {
                getNotificationFixer().fixRemoteViewActions(context, false, notification.tickerView);
            } else {
                RemoteViewsFixer remoteViewsFixer = getRemoteViewsFixer();
                notification.tickerView = remoteViewsFixer.makeRemoteViews(i + ":tickerView", context, notification.tickerView, false, false);
            }
        }
        if (notification.contentView != null) {
            if (isSystemLayout(notification.contentView)) {
                getNotificationFixer().fixIconImage(context.getResources(), notification.contentView, getNotificationFixer().fixRemoteViewActions(context, false, notification.contentView), notification);
            } else {
                RemoteViewsFixer remoteViewsFixer2 = getRemoteViewsFixer();
                notification.contentView = remoteViewsFixer2.makeRemoteViews(i + ":contentView", context, notification.contentView, false, true);
            }
        }
        if (Build.VERSION.SDK_INT >= 16 && notification.bigContentView != null) {
            if (isSystemLayout(notification.bigContentView)) {
                getNotificationFixer().fixRemoteViewActions(context, false, notification.bigContentView);
            } else {
                RemoteViewsFixer remoteViewsFixer3 = getRemoteViewsFixer();
                notification.bigContentView = remoteViewsFixer3.makeRemoteViews(i + ":bigContentView", context, notification.bigContentView, true, true);
            }
        }
        if (Build.VERSION.SDK_INT >= 21 && notification.headsUpContentView != null) {
            if (isSystemLayout(notification.headsUpContentView)) {
                getNotificationFixer().fixIconImage(context.getResources(), notification.contentView, getNotificationFixer().fixRemoteViewActions(context, false, notification.headsUpContentView), notification);
                return;
            }
            RemoteViewsFixer remoteViewsFixer4 = getRemoteViewsFixer();
            notification.headsUpContentView = remoteViewsFixer4.makeRemoteViews(i + ":headsUpContentView", context, notification.headsUpContentView, false, false);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Context getAppContext(String str) {
        try {
            return getHostContext().createPackageContext(str, 3);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }
}
