package com.lbd.p054xj.keeplive;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;
import android.support.annotation.Nullable;
import com.common.utils.log.LogUtils;
import com.lbd.p054xj.C1467R;
import com.lody.virtual.client.ipc.ServiceManagerNative;

/* renamed from: com.lbd.xj.keeplive.DaemonService */
/* loaded from: classes.dex */
public class DaemonService extends Service {

    /* renamed from: a */
    public static final int f9456a = 100;

    /* renamed from: b */
    private static final String f9457b = "DaemonService";

    @Override // android.app.Service
    @Nullable
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override // android.app.Service
    public int onStartCommand(Intent intent, int i, int i2) {
        return 1;
    }

    @Override // android.app.Service
    public void onCreate() {
        super.onCreate();
        LogUtils.m22038d(f9457b, "DaemonService---->onCreate被调用，启动前台service");
        if (Build.VERSION.SDK_INT >= 18) {
            Notification.Builder builder = new Notification.Builder(this);
            builder.setSmallIcon(C1467R.mipmap.app_icon);
            builder.setContentTitle("");
            builder.setContentText("");
            startForeground(100, builder.build());
            startService(new Intent(this, CancelNoticeService.class));
            return;
        }
        startForeground(100, new Notification());
    }

    @Override // android.app.Service
    public void onDestroy() {
        super.onDestroy();
        if (Build.VERSION.SDK_INT >= 18) {
            ((NotificationManager) getSystemService(ServiceManagerNative.NOTIFICATION)).cancel(100);
        }
        LogUtils.m22038d(f9457b, "DaemonService---->onDestroy��ǰ̨service��ɱ��");
        startService(new Intent(getApplicationContext(), DaemonService.class));
    }
}
