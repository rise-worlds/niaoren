package com.lbd.xj.keeplive;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import com.common.utils.log.LogUtils;
import com.lbd.xj.C1467R;
import com.lody.virtual.client.ipc.ServiceManagerNative;

/* renamed from: com.lbd.xj.keeplive.CancelNoticeService */
/* loaded from: classes.dex */
public class CancelNoticeService extends Service {
    @Override // android.app.Service
    @Nullable
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override // android.app.Service
    public void onCreate() {
        super.onCreate();
        LogUtils.m22037e("CancelNoticeService onCreate");
    }

    @Override // android.app.Service
    public void onDestroy() {
        super.onDestroy();
    }

    @Override // android.app.Service
    public int onStartCommand(Intent intent, int i, int i2) {
        LogUtils.m22037e("CancelNoticeService onStartCommand");
        if (Build.VERSION.SDK_INT > 18) {
            Notification.Builder builder = new Notification.Builder(this);
            builder.setSmallIcon(C1467R.mipmap.app_icon);
            startForeground(100, builder.build());
            new Thread(new Runnable() { // from class: com.lbd.xj.keeplive.CancelNoticeService.1
                @Override // java.lang.Runnable
                public void run() {
                    SystemClock.sleep(1000L);
                    CancelNoticeService.this.stopForeground(true);
                    ((NotificationManager) CancelNoticeService.this.getSystemService(ServiceManagerNative.NOTIFICATION)).cancel(100);
                    CancelNoticeService.this.stopSelf();
                }
            }).start();
            LogUtils.m22037e("CancelNoticeService onStartCommand");
        }
        return super.onStartCommand(intent, i, i2);
    }
}
