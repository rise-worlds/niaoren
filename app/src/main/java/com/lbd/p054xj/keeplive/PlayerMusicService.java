package com.lbd.p054xj.keeplive;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.Build;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.NotificationCompat;
import com.common.utils.log.LogUtils;
import com.lbd.p054xj.C1467R;
import com.lody.virtual.client.ipc.ServiceManagerNative;
import p110z1.acf;

/* renamed from: com.lbd.xj.keeplive.PlayerMusicService */
/* loaded from: classes.dex */
public class PlayerMusicService extends Service {

    /* renamed from: a */
    private static final String f9460a = "PlayerMusicService";

    /* renamed from: b */
    private Handler f9461b = new Handler(Looper.getMainLooper());

    /* renamed from: c */
    private MediaPlayer f9462c;

    /* renamed from: d */
    private BinderC1497a f9463d;

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: b */
    public void m19771b(int i) {
    }

    /* renamed from: com.lbd.xj.keeplive.PlayerMusicService$a */
    /* loaded from: classes.dex */
    public class BinderC1497a extends Binder {
        public BinderC1497a() {
        }

        public PlayerMusicService getService() {
            return PlayerMusicService.this;
        }
    }

    /* renamed from: a */
    static MediaPlayer m19774a(PlayerMusicService playerMusicService) {
        return playerMusicService.f9462c;
    }

    /* renamed from: b */
    static void m19770b(PlayerMusicService playerMusicService) {
        playerMusicService.m19768c();
    }

    /* renamed from: a */
    static void m19773a(PlayerMusicService playerMusicService, int i) {
        playerMusicService.m19771b(i);
    }

    /* renamed from: b */
    static void m19769b(PlayerMusicService playerMusicService, int i) {
        playerMusicService.m19775a(i);
    }

    /* renamed from: c */
    static Handler m19767c(PlayerMusicService playerMusicService) {
        return playerMusicService.f9461b;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a */
    public void m19775a(int i) {
        if (Build.VERSION.SDK_INT < 18) {
            startForeground(102, new Notification());
        } else if (Build.VERSION.SDK_INT >= 26) {
            ((NotificationManager) getSystemService(ServiceManagerNative.NOTIFICATION)).createNotificationChannel(new NotificationChannel(Integer.toString(102), "xxxx", 1));
            startForeground(102, new NotificationCompat.Builder(this, Integer.toString(102)).setSmallIcon(C1467R.mipmap.app_icon).build());
        } else {
            Notification.Builder builder = new Notification.Builder(this);
            builder.setSmallIcon(C1467R.mipmap.app_icon);
            startForeground(102, builder.build());
        }
    }

    /* renamed from: a */
    public int m19776a() {
        MediaPlayer mediaPlayer = this.f9462c;
        if (mediaPlayer != null) {
            return mediaPlayer.getCurrentPosition();
        }
        return 0;
    }

    @Override // android.app.Service
    @Nullable
    public IBinder onBind(Intent intent) {
        this.f9463d = new BinderC1497a();
        return this.f9463d;
    }

    @Override // android.app.Service
    @RequiresApi(api = 26)
    public void onCreate() {
        super.onCreate();
        LogUtils.m22038d(f9460a, "PlayerMusicService---->onCreate,��������");
        this.f9462c = MediaPlayer.create(getApplicationContext(), C1467R.raw.f9416t);
        MediaPlayer mediaPlayer = this.f9462c;
        if (mediaPlayer != null) {
            mediaPlayer.setLooping(true);
        }
        if (Build.VERSION.SDK_INT >= 18) {
            LogUtils.m22038d("SHENG", "onCreate startForeground");
            if (Build.VERSION.SDK_INT >= 26) {
                ((NotificationManager) getSystemService(ServiceManagerNative.NOTIFICATION)).createNotificationChannel(new NotificationChannel(Integer.toString(102), "xxxx", 1));
                startForeground(102, new NotificationCompat.Builder(this, Integer.toString(102)).setSmallIcon(C1467R.mipmap.app_icon).setAutoCancel(true).build());
                return;
            }
            Notification.Builder builder = new Notification.Builder(this);
            builder.setSmallIcon(C1467R.mipmap.app_icon);
            builder.setAutoCancel(true);
            startForeground(102, builder.build());
            return;
        }
        startForeground(102, new Notification());
    }

    @Override // android.app.Service
    public void onDestroy() {
        super.onDestroy();
        m19765d();
        LogUtils.m22038d(f9460a, "PlayerMusicService---->onCreate,停止服务");
        stopForeground(true);
        this.f9461b.removeCallbacksAndMessages(null);
    }

    @Override // android.app.Service
    public int onStartCommand(Intent intent, int i, int i2) {
        LogUtils.m22038d("onStartCommand", System.currentTimeMillis() + "onStartCommand");
        new Thread(new Runnable() { // from class: com.lbd.xj.keeplive.PlayerMusicService.1
            @Override // java.lang.Runnable
            public void run() {
                PlayerMusicService.this.m19772b();
            }
        }).start();
        return 1;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: c */
    public void m19768c() {
        this.f9461b.postDelayed(new Runnable() { // from class: com.lbd.xj.keeplive.PlayerMusicService.2
            @Override // java.lang.Runnable
            public void run() {
                if (PlayerMusicService.this.f9462c != null) {
                    PlayerMusicService.this.m19768c();
                    PlayerMusicService playerMusicService = PlayerMusicService.this;
                    playerMusicService.m19771b(playerMusicService.m19776a());
                    PlayerMusicService playerMusicService2 = PlayerMusicService.this;
                    playerMusicService2.m19775a(playerMusicService2.m19776a());
                    return;
                }
                PlayerMusicService.this.f9461b.removeCallbacks(this);
            }
        }, acf.f15175N);
    }

    /* renamed from: b */
    public void m19772b() {
        if (this.f9462c != null) {
            LogUtils.m22038d(f9460a, "启动后台播放音乐");
            m19768c();
        }
    }

    /* renamed from: d */
    private void m19765d() {
        if (this.f9462c != null) {
            LogUtils.m22038d(f9460a, "关闭后台播放音乐");
            this.f9462c.stop();
        }
    }
}
