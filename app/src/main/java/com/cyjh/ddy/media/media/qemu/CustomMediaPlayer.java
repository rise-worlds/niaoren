package com.cyjh.ddy.media.media.qemu;

import android.app.Activity;
import android.view.Surface;
import com.cyjh.ddy.base.utils.CLog;
import com.cyjh.ddy.base.utils.NiceUtil;
import com.cyjh.ddy.base.utils.WSUtils;
import com.cyjh.ddy.media.bean.socket.ControlResponse;
import com.cyjh.ddy.media.beaninner.VedioPackage;
import com.cyjh.ddy.media.media.CustomTextureView;
import com.cyjh.ddy.media.media.listener.OnPreparedListener;

/* renamed from: com.cyjh.ddy.media.media.qemu.a */
/* loaded from: classes.dex */
public class CustomMediaPlayer {

    /* renamed from: a */
    public static final String f7307a = "CustomMediaPlayer";

    /* renamed from: c */
    private Surface f7309c;

    /* renamed from: d */
    private LiveVedioThread f7310d;

    /* renamed from: e */
    private LiveAudioThread f7311e;

    /* renamed from: g */
    private CustomTextureView f7313g;

    /* renamed from: h */
    private OnPreparedListener f7314h;

    /* renamed from: b */
    private volatile boolean f7308b = false;

    /* renamed from: f */
    private boolean f7312f = true;

    public CustomMediaPlayer(CustomTextureView customTextureView) {
        this.f7313g = customTextureView;
        m21624h();
    }

    /* renamed from: h */
    private void m21624h() {
        this.f7310d = new LiveVedioThread();
        this.f7311e = new LiveAudioThread();
    }

    /* renamed from: a */
    public void m21639a(Surface surface) {
        this.f7309c = surface;
    }

    /* renamed from: a */
    public int m21637a(VedioPackage bVar) {
        if (!this.f7308b) {
            this.f7310d.m21570b(bVar);
            return 1;
        }
        CLog.m21883e(f7307a, "playVideoPacket isClose==true");
        return 0;
    }

    /* renamed from: b */
    public void m21634b(VedioPackage bVar) {
        this.f7310d.m21543c(bVar);
    }

    /* renamed from: c */
    public void m21631c(VedioPackage bVar) {
        this.f7310d.m21542d(bVar);
    }

    /* renamed from: a */
    public int m21640a() {
        return this.f7310d.m21539k();
    }

    /* renamed from: b */
    public int m21635b() {
        return this.f7310d.m21558g();
    }

    /* renamed from: c */
    public int m21632c() {
        return this.f7311e.m21558g();
    }

    /* renamed from: d */
    public int m21628d(VedioPackage bVar) {
        if (this.f7308b) {
            return 0;
        }
        if (!this.f7312f) {
            return 1;
        }
        this.f7311e.m21570b(bVar);
        return 1;
    }

    /* renamed from: a */
    public void m21636a(boolean z) {
        this.f7312f = z;
    }

    /* renamed from: b */
    public void m21633b(boolean z) {
        this.f7313g.switchControllerKeyEvent(z);
    }

    /* renamed from: d */
    public void m21629d() {
        OnPreparedListener jVar = this.f7314h;
        if (jVar != null) {
            jVar.mo21648a();
        }
    }

    /* renamed from: c */
    public void m21630c(boolean z) {
        this.f7308b = false;
        if (!this.f7310d.m21565d()) {
            this.f7310d.m21579a(this.f7309c, z);
        }
        if (!this.f7311e.m21565d()) {
            this.f7311e.m21562e();
        }
    }

    /* renamed from: e */
    public void m21627e() {
        this.f7308b = true;
        LiveVedioThread eVar = this.f7310d;
        if (eVar != null) {
            eVar.m21560f();
        }
        LiveAudioThread cVar = this.f7311e;
        if (cVar != null) {
            cVar.m21560f();
        }
    }

    public void setOnPreparedListener(OnPreparedListener jVar) {
        this.f7314h = jVar;
    }

    /* renamed from: a */
    public void m21638a(ControlResponse controlResponse) {
        if (!this.f7308b) {
            final Activity a = NiceUtil.m21778a(this.f7313g.getContext());
            int b = WSUtils.m21712b(a);
            int c = WSUtils.m21711c(a);
            final int i = controlResponse.width;
            final int i2 = controlResponse.height;
            int i3 = controlResponse.rotate;
            if (b > c) {
                if (i < i2) {
                    this.f7313g.post(new Runnable() { // from class: com.cyjh.ddy.media.media.qemu.CustomMediaPlayer$1
                        @Override // java.lang.Runnable
                        public void run() {
                            CLog.m21881v("jason", "屏幕翻转-竖屏 " + i + "x" + i2);
                            a.setRequestedOrientation(1);
                        }
                    });
                }
            } else if (i > i2) {
                this.f7313g.post(new Runnable() { // from class: com.cyjh.ddy.media.media.qemu.CustomMediaPlayer$2
                    @Override // java.lang.Runnable
                    public void run() {
                        CLog.m21881v("jason", "屏幕翻转-横屏" + i + "x" + i2);
                        a.setRequestedOrientation(0);
                    }
                });
            }
            this.f7313g.setScreenRotate(i3);
        }
    }

    /* renamed from: f */
    public void m21626f() {
        this.f7313g.onParentSizeChanged();
    }

    /* renamed from: g */
    public long m21625g() {
        LiveVedioThread eVar = this.f7310d;
        if (eVar != null) {
            return eVar.m21556h();
        }
        return 0L;
    }
}
