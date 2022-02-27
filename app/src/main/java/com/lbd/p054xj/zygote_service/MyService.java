package com.lbd.p054xj.zygote_service;

import android.annotation.TargetApi;
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
import android.support.v4.app.NotificationCompat;
import com.common.utils.log.LogUtils;
import com.lbd.p054xj.C1467R;
import com.lbd.p054xj.app.AppConfig;
import com.lody.virtual.client.ipc.ServiceManagerNative;

/* renamed from: com.lbd.xj.zygote_service.MyService */
/* loaded from: classes.dex */
public class MyService extends Service {

    /* renamed from: a */
    static final boolean f9977a = false;

    /* renamed from: b */
    String f9978b = "SHENG_myservice";

    /* renamed from: d */
    private boolean f9980d = true;

    /* renamed from: e */
    private boolean f9981e = false;

    /* renamed from: c */
    MyReceiver f9979c = null;

    public native int killuid(int i);

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        return null;
    }

    public native int startzygote(String str, boolean z);

    /* renamed from: com.lbd.xj.zygote_service.MyService$MyReceiver */
    /* loaded from: classes.dex */
    public class MyReceiver extends BroadcastReceiver {

        /* renamed from: a */
        public static final String f9982a = "com.lbd.xj.system_exit";

        public MyReceiver() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            LogUtils.m22038d(MyService.this.f9978b, "on Recv");
            MyService.this.killuid(Process.myUid());
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
    public int onStartCommand(Intent intent, int i, int i2) {
        LogUtils.m22036e(this.f9978b, "MyService onStartCommand");
        return 2;
    }

    @Override // android.app.Service
    public void onCreate() {
        super.onCreate();
        this.f9979c = new MyReceiver();
        registerReceiver(this.f9979c, new IntentFilter("com.lbd.xj.system_exit"));
        if (Build.VERSION.SDK_INT >= 26) {
            m19274b();
        } else {
            startForeground(10001, new Notification());
        }
        String str = Build.BRAND;
        this.f9981e = str.toLowerCase().equals("huawei") || str.toLowerCase().equals("honor");
        String str2 = this.f9978b;
        LogUtils.m22036e(str2, "isHuawei" + this.f9981e + "x" + this.f9980d);
        if (this.f9980d) {
            startzygote("zygote", this.f9981e);
            this.f9980d = false;
        }
    }

    @TargetApi(26)
    /* renamed from: b */
    private void m19274b() {
        String str = AppConfig.f9441h;
        NotificationChannel notificationChannel = new NotificationChannel(str, "My Background Service", 0);
        notificationChannel.setLightColor(-16776961);
        notificationChannel.setLockscreenVisibility(0);
        ((NotificationManager) getSystemService(ServiceManagerNative.NOTIFICATION)).createNotificationChannel(notificationChannel);
        startForeground(10001, new NotificationCompat.Builder(this, str).setOngoing(true).setSmallIcon(C1467R.mipmap.app_icon).setContentTitle("App is running in background").setPriority(0).setCategory(NotificationCompat.CATEGORY_SERVICE).build());
    }

    /* renamed from: a */
    public Boolean m19275a() {
        return Boolean.valueOf(this.f9981e);
    }

    @Override // android.app.Service
    public void onDestroy() {
        super.onDestroy();
        MyReceiver myReceiver = this.f9979c;
        if (myReceiver != null) {
            unregisterReceiver(myReceiver);
        }
        stopForeground(true);
    }
}
