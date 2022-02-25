package com.lbd.p054xj.service;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.Handler;
import android.os.IBinder;
import android.os.Process;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.p003v4.app.NotificationCompat;
import android.support.p006v7.app.AppCompatDelegate;
import android.util.Log;
import com.blankj.utilcode.util.LogUtils;
import com.lbd.p054xj.C1467R;
import com.lbd.p054xj.C1471a;
import com.lbd.p054xj.app.AppConfig;
import com.lbd.p054xj.manager.launch.Nativebridge;
import com.lbd.p054xj.p056ui.activity.XJFullActivity;
import com.lbd.p054xj.socket.SocketManagerServer;
import com.lody.virtual.client.ipc.ServiceManagerNative;
import p110z1.FtXnkjSdk;
import p110z1.FwManager;
import p110z1.GeneralUtil;
import p110z1.PreferencesUtil;
import p110z1.SharepreferenceUtil;
import p110z1.ToastUtil;
import p110z1.ValReceiver;
import p110z1.ValShare;
import p110z1.acf;

/* renamed from: com.lbd.xj.service.XJFloatService */
/* loaded from: classes.dex */
public class XJFloatService extends Service {

    /* renamed from: a */
    private Handler f9489a;

    /* renamed from: b */
    private XJFloatServiceReceiver f9490b;

    /* renamed from: com.lbd.xj.service.XJFloatService$a */
    /* loaded from: classes.dex */
    enum EnumC1517a {
        CMD_INSTALL_APP_ADD,
        CMD_GET_CMD,
        CMD_GETPROP,
        CMD_KEY_AUDIO_ADD,
        CMD_KEY_AUDIO_SUB,
        CMD_KEY_AUDIO_MUTE,
        CMD_KEY_HOME,
        CMD_KEY_BACK,
        CMD_KEY_APP_SWITCH
    }

    public native int killuid(int i);

    @Override // android.app.Service
    @Nullable
    public IBinder onBind(Intent intent) {
        return null;
    }

    public native void sendeventkey(int i);

    public native void setRotation(float f);

    /* renamed from: com.lbd.xj.service.XJFloatService$XJFloatServiceReceiver */
    /* loaded from: classes.dex */
    public static class XJFloatServiceReceiver extends BroadcastReceiver {
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if (ValReceiver.f15490a.equals(intent.getAction())) {
                String stringExtra = intent.getStringExtra("key");
                if ("onResume".equals(stringExtra)) {
                    LogUtils.m23742b("isNav3:" + FwManager.getInstance().isNav);
                    if (FwManager.getInstance().isNav) {
                        SocketManagerServer.m19692b().m19693a(false);
                    }
                    FwManager.getInstance().initXJFloatView(true);
                } else if ("onPause".equals(stringExtra)) {
                    LogUtils.m23742b("onPause");
                    if (FwManager.getInstance().isNav) {
                        SocketManagerServer.m19692b().m19693a(true);
                    }
                    FwManager.getInstance().initXJFloatView(false);
                }
            }
        }
    }

    public XJFloatService() {
        FwManager.getInstance().initData();
    }

    @Override // android.app.Service
    public void onCreate() {
        super.onCreate();
        Log.d(AppConfig.f9434a, "XJFloatService - onCreate");
        FtXnkjSdk.INSTANCE.executeXNKJ();
        if (Build.VERSION.SDK_INT >= 26) {
            m19706a(100);
        } else {
            startForeground(100, new Notification());
        }
        this.f9489a = new Handler();
        FwManager.getInstance().initOnCreate(this);
        this.f9490b = new XJFloatServiceReceiver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(ValReceiver.f15490a);
        registerReceiver(this.f9490b, intentFilter);
        FwManager.getInstance().initXJFloatView(XJFullActivity.class.getName().equals(GeneralUtil.m13996h(this)));
    }

    @Override // android.app.Service
    public int onStartCommand(Intent intent, int i, int i2) {
        Log.d(AppConfig.f9434a, "XJFloatService - onStartCommand");
        if (intent == null) {
            return super.onStartCommand(intent, i, i2);
        }
        int intExtra = intent.getIntExtra("key", 0);
        if (intExtra == 1) {
            setRotation(intent.getFloatExtra("Rotation", 0.0f));
        } else if (intExtra == 2) {
            sendeventkey(intent.getIntExtra("Action", 0));
        } else if (intExtra == 3) {
            com.common.utils.log.LogUtils.m22039d("sendeventkey:3");
            if (AppConfig.f9445l) {
                Nativebridge.m19712a().sendeventkey(8);
            } else {
                sendeventkey(EnumC1517a.CMD_KEY_APP_SWITCH.ordinal());
            }
        } else if (intExtra == 4) {
            SocketManagerServer.m19692b().m19699a("EXPANDNOTIFICATION");
            FwManager.getInstance().removeXJInnerFloatView();
            FwManager.getInstance().initXJFloatView(true);
        } else if (intExtra == 5) {
            ToastUtil.m13876a(getApplicationContext(), "虚拟空间正在关机");
            this.f9489a.postDelayed(new Runnable() { // from class: com.lbd.xj.service.XJFloatService.1
                @Override // java.lang.Runnable
                public void run() {
                    GeneralUtil.m14003f();
                    GeneralUtil.m14015d();
                    XJFloatService.this.killuid(Process.myUid());
                    GeneralUtil.m14037b();
                }
            }, 1000L);
        } else if (intExtra == 6) {
            FwManager.getInstance().removeXJInnerFloatView();
            FwManager.getInstance().initXJFloatView(true);
            int intExtra2 = intent.getIntExtra("rw", 1);
            int intExtra3 = intent.getIntExtra("rh", 1);
            int intExtra4 = intent.getIntExtra("dpi", 1);
            PreferencesUtil.m13937a().m13931a("surfaceW", Integer.valueOf(intExtra2));
            PreferencesUtil.m13937a().m13931a("surfaceH", Integer.valueOf(intExtra3));
            PreferencesUtil.m13937a().m13931a("surfaceDPI", Integer.valueOf(intExtra4));
            PreferencesUtil.m13937a().m13931a(acf.f15170I, (Object) 1);
            ToastUtil.m13876a(getApplicationContext(), "设置分辨率中正在重启！");
            this.f9489a.postDelayed(new Runnable() { // from class: com.lbd.xj.service.XJFloatService.2
                @Override // java.lang.Runnable
                public void run() {
                    GeneralUtil.m14003f();
                    GeneralUtil.m14015d();
                    XJFloatService.this.killuid(Process.myUid());
                    GeneralUtil.m14025c();
                }
            }, 1000L);
        } else if (intExtra == 7) {
            int intExtra5 = intent.getIntExtra(ValShare.f16644c, 0);
            SharepreferenceUtil.m13894a(ValShare.f16644c, intExtra5);
            FwManager.getInstance().setVisible(intExtra5);
        } else if (intExtra == 8) {
            com.common.utils.log.LogUtils.m22039d("sendeventkey:7");
            if (AppConfig.f9445l) {
                Nativebridge.m19712a().sendeventkey(7);
            } else {
                sendeventkey(EnumC1517a.CMD_KEY_APP_SWITCH.ordinal());
            }
        } else if (intExtra == 9) {
            com.common.utils.log.LogUtils.m22039d("sendeventkey:8");
            if (AppConfig.f9445l) {
                Nativebridge.m19712a().sendeventkey(6);
            } else {
                sendeventkey(EnumC1517a.CMD_KEY_HOME.ordinal());
            }
        } else if (intExtra == 10) {
            if (AppConfig.f9445l) {
                Nativebridge.m19712a().sendeventkey(5);
            } else {
                sendeventkey(EnumC1517a.CMD_KEY_HOME.ordinal());
            }
        }
        return super.onStartCommand(intent, i, i2);
    }

    @Override // android.app.Service
    public void onDestroy() {
        super.onDestroy();
        unregisterReceiver(this.f9490b);
    }

    @RequiresApi(api = 26)
    /* renamed from: a */
    private void m19706a(int i) {
        NotificationChannel notificationChannel = new NotificationChannel(C1471a.f9420b, "My Background Service", 0);
        notificationChannel.setLightColor(-16776961);
        notificationChannel.setLockscreenVisibility(0);
        ((NotificationManager) getSystemService(ServiceManagerNative.NOTIFICATION)).createNotificationChannel(notificationChannel);
        startForeground(i, new NotificationCompat.Builder(this, C1471a.f9420b).setOngoing(true).setSmallIcon(C1467R.mipmap.app_icon).setContentTitle("App is running in background").setPriority(1).setCategory(NotificationCompat.CATEGORY_SERVICE).build());
    }

    static {
        System.loadLibrary("native-lib");
        if (AppConfig.f9445l) {
            System.loadLibrary("vmtools");
        } else {
            System.loadLibrary("native-activity");
        }
        System.loadLibrary("rfbox");
        System.loadLibrary(AppConfig.f9439f);
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
    }
}
