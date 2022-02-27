package com.lbd.p054xj.p056ui.activity;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Message;
import android.support.v4.app.FragmentActivity;
import com.common.utils.log.LogUtils;
import com.lbd.p054xj.keeplive.PlayerMusicService;
import p110z1.EventBus;

/* renamed from: com.lbd.xj.ui.activity.XJBaseANativityctivity */
/* loaded from: classes.dex */
public class XJBaseANativityctivity extends FragmentActivity {

    /* renamed from: a */
    public PlayerMusicService f9606a;

    /* renamed from: b */
    protected Handler f9607b;

    /* renamed from: d */
    public Handler f9609d;

    /* renamed from: f */
    private HandlerThread f9611f;

    /* renamed from: g */
    private Intent f9612g;

    /* renamed from: e */
    private ServiceConnection f9610e = new ServiceConnection() { // from class: com.lbd.xj.ui.activity.XJBaseANativityctivity.1
        @Override // android.content.ServiceConnection
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            XJBaseANativityctivity.this.f9606a = ((PlayerMusicService.BinderC1497a) iBinder).getService();
            XJBaseANativityctivity.this.f9606a.m19772b();
        }

        @Override // android.content.ServiceConnection
        public void onServiceDisconnected(ComponentName componentName) {
            XJBaseANativityctivity.this.f9606a = null;
        }
    };

    /* renamed from: c */
    public Handler f9608c = new Handler(new Handler.Callback() { // from class: com.lbd.xj.ui.activity.XJBaseANativityctivity.2
        @Override // android.os.Handler.Callback
        public final boolean handleMessage(Message message) {
            return XJBaseANativityctivity.this.m19537c(message);
        }
    });

    /* renamed from: a */
    public void m19539a(Message message) {
    }

    /* renamed from: b */
    public void mo19525b() {
    }

    /* renamed from: b */
    public void mo19524b(Message message) {
    }

    /* renamed from: c */
    public boolean m19537c(Message message) {
        m19539a(message);
        return false;
    }

    @Override // android.support.v4.app.FragmentActivity, android.support.v4.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        EventBus.m3448a().m3446a(this);
        getWindow().setFlags(128, 128);
        super.onCreate(bundle);
        mo19525b();
        m19540a();
    }

    @Override // android.support.v4.app.FragmentActivity, android.app.Activity
    public void onPause() {
        super.onPause();
    }

    @Override // android.support.v4.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
    }

    @Override // android.support.v4.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        LogUtils.m22037e("Full onDestroy555555555555555555555555555555555555");
        super.onDestroy();
    }

    /* renamed from: a */
    private void m19540a() {
        this.f9611f = new HandlerThread("BaseNativeActivity");
        this.f9611f.start();
        this.f9609d = new Handler(this.f9611f.getLooper(), new Handler.Callback() { // from class: com.lbd.xj.ui.activity.XJBaseANativityctivity.3
            @Override // android.os.Handler.Callback
            public boolean handleMessage(Message message) {
                XJBaseANativityctivity.this.mo19524b(message);
                return false;
            }
        });
        HandlerThread handlerThread = new HandlerThread("keyEventHandler");
        handlerThread.start();
        this.f9607b = new Handler(handlerThread.getLooper());
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public void onWindowFocusChanged(boolean z) {
        super.onWindowFocusChanged(z);
        if (z) {
            getWindow().getDecorView().setSystemUiVisibility(7942);
        }
    }

    /* renamed from: c */
    public void m19538c() {
        this.f9612g = new Intent(this, PlayerMusicService.class);
        if (Build.VERSION.SDK_INT >= 26) {
            startForegroundService(this.f9612g);
        } else {
            startService(this.f9612g);
        }
        bindService(this.f9612g, this.f9610e, 1);
    }
}
