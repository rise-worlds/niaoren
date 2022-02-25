package com.lody.virtual.client.stub;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;
import com.lody.virtual.helper.compat.NotificationChannelCompat;

/* loaded from: classes.dex */
public class HiddenForeNotification extends Service {

    /* renamed from: ID */
    private static final int f10498ID = 2781;

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        return null;
    }

    public static void bindForeground(Service service) {
        Notification.Builder createBuilder = NotificationChannelCompat.createBuilder(service.getApplicationContext(), NotificationChannelCompat.DAEMON_ID);
        createBuilder.setSmallIcon(17301544);
        if (Build.VERSION.SDK_INT > 24) {
            createBuilder.setContentTitle("鸟人助手");
            createBuilder.setContentText("防止鸟人助手被关闭，请保留该权限");
        } else {
            createBuilder.setContentTitle("鸟人助手");
            createBuilder.setContentText("防止鸟人助手被关闭，请保留该权限");
            createBuilder.setContentIntent(PendingIntent.getService(service, 0, new Intent(service, HiddenForeNotification.class), 0));
        }
        createBuilder.setSound(null);
        service.startForeground(f10498ID, createBuilder.getNotification());
        if (Build.VERSION.SDK_INT <= 24) {
            service.startService(new Intent(service, HiddenForeNotification.class));
        }
    }

    @Override // android.app.Service
    public int onStartCommand(Intent intent, int i, int i2) {
        try {
            Notification.Builder createBuilder = NotificationChannelCompat.createBuilder(getBaseContext(), NotificationChannelCompat.DAEMON_ID);
            createBuilder.setSmallIcon(17301544);
            createBuilder.setContentTitle("鸟人助手");
            createBuilder.setContentText("防止鸟人助手被关闭，请保留该权限");
            createBuilder.setSound(null);
            startForeground(f10498ID, createBuilder.getNotification());
            stopForeground(true);
            stopSelf();
            return 2;
        } catch (Exception e) {
            e.printStackTrace();
            return 2;
        }
    }
}
