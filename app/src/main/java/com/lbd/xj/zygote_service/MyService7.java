package com.lbd.xj.zygote_service;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.IBinder;
import android.os.Process;
import android.support.annotation.RequiresApi;
import android.support.v4.app.NotificationCompat;
import com.common.utils.log.LogUtils;
import com.lbd.xj.C1467R;
import com.lbd.xj.app.AppConfig;
import com.lody.virtual.client.ipc.ServiceManagerNative;

/* renamed from: com.lbd.xj.zygote_service.MyService7 */
/* loaded from: classes.dex */
public class MyService7 extends Service {

    /* renamed from: a */
    static final boolean f10206a = false;

    /* renamed from: b */
    String f10207b = "SHENG_myservice";

    /* renamed from: d */
    private boolean f10209d = true;

    /* renamed from: c */
    MyReceiver f10208c = null;

    public native int killuid(int i);

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override // android.app.Service
    public int onStartCommand(Intent intent, int i, int i2) {
        return 2;
    }

    public native int startzygote_d(String str);

    /* renamed from: com.lbd.xj.zygote_service.MyService7$MyReceiver */
    /* loaded from: classes.dex */
    public class MyReceiver extends BroadcastReceiver {

        /* renamed from: a */
        public static final String f10210a = "com.lbd.xj.system_exit";

        public MyReceiver() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            LogUtils.m22038d(MyService7.this.f10207b, "on Recv");
            MyService7.this.killuid(Process.myUid());
            System.exit(0);
        }
    }

    static {
        System.loadLibrary("native-lib");
        if (AppConfig.f9445l) {
            System.loadLibrary("vmtools");
        } else {
            System.loadLibrary("native-activity");
        }
    }

    @Override // android.app.Service
    public void onCreate() {
        super.onCreate();
        this.f10208c = new MyReceiver();
        registerReceiver(this.f10208c, new IntentFilter("com.lbd.xj.system_exit"));
        if (Build.VERSION.SDK_INT >= 26) {
            m19236a(10001);
        } else {
            startForeground(10001, new Notification());
        }
        if (this.f10209d) {
            startzygote_d("zygote_d");
            this.f10209d = false;
        }
    }

    @RequiresApi(api = 26)
    /* renamed from: a */
    private void m19236a(int i) {
        String str = AppConfig.f9441h;
        NotificationChannel notificationChannel = new NotificationChannel(str, "My Background Service", 0);
        notificationChannel.setLightColor(-16776961);
        notificationChannel.setLockscreenVisibility(0);
        ((NotificationManager) getSystemService(ServiceManagerNative.NOTIFICATION)).createNotificationChannel(notificationChannel);
        startForeground(i, new NotificationCompat.Builder(this, str).setOngoing(true).setSmallIcon(C1467R.mipmap.app_icon).setContentTitle("App is running in background").setPriority(0).setCategory(NotificationCompat.CATEGORY_SERVICE).build());
    }

    @Override // android.app.Service
    public void onDestroy() {
        super.onDestroy();
        MyReceiver myReceiver = this.f10208c;
        if (myReceiver != null) {
            unregisterReceiver(myReceiver);
        }
        stopForeground(true);
    }
}
