package com.tencent.smtt.sdk;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.Window;
import android.widget.FrameLayout;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;
import com.tencent.smtt.export.external.DexLoader;

/* renamed from: com.tencent.smtt.sdk.q */
/* loaded from: classes2.dex */
class TbsVideoView extends FrameLayout implements MediaPlayer.OnErrorListener {

    /* renamed from: a */
    private Object f13236a;

    /* renamed from: b */
    private VideoWizard f13237b;

    /* renamed from: c */
    private VideoView f13238c;

    /* renamed from: d */
    private Context f13239d;

    /* renamed from: e */
    private String f13240e;

    public TbsVideoView(Context context) {
        super(context.getApplicationContext());
        this.f13239d = null;
        this.f13239d = context;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public void m16637a(Bundle bundle, Object obj) {
        m16635b(bundle, obj);
    }

    /* renamed from: b */
    private void m16635b(Bundle bundle, Object obj) {
        boolean z;
        m16640a();
        if (m16636b()) {
            bundle.putInt("callMode", bundle.getInt("callMode"));
            z = this.f13237b.m16622a(this.f13236a, bundle, this, obj);
        } else {
            z = false;
        }
        if (!z) {
            VideoView videoView = this.f13238c;
            if (videoView != null) {
                videoView.stopPlayback();
            }
            if (this.f13238c == null) {
                this.f13238c = new VideoView(getContext());
            }
            this.f13240e = bundle.getString("videoUrl");
            this.f13238c.setVideoURI(Uri.parse(this.f13240e));
            this.f13238c.setOnErrorListener(this);
            Intent intent = new Intent("com.tencent.smtt.tbs.video.PLAY");
            intent.addFlags(268435456);
            Context applicationContext = getContext().getApplicationContext();
            intent.setPackage(applicationContext.getPackageName());
            applicationContext.startActivity(intent);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public void m16640a() {
        setBackgroundColor(-16777216);
        if (this.f13237b == null) {
            SDKEngine.m16828a(true).m16830a(getContext().getApplicationContext(), false, false);
            TbsWizard a = SDKEngine.m16828a(true).m16832a();
            DexLoader dexLoader = null;
            if (a != null) {
                dexLoader = a.m16629b();
            }
            if (dexLoader != null && QbSdk.canLoadVideo(getContext())) {
                this.f13237b = new VideoWizard(dexLoader);
            }
        }
        VideoWizard sVar = this.f13237b;
        if (sVar != null && this.f13236a == null) {
            this.f13236a = sVar.m16625a(getContext().getApplicationContext());
        }
    }

    /* renamed from: b */
    public boolean m16636b() {
        return (this.f13237b == null || this.f13236a == null) ? false : true;
    }

    /* renamed from: a */
    public void m16639a(Activity activity) {
        VideoView videoView;
        if (!m16636b() && (videoView = this.f13238c) != null) {
            if (videoView.getParent() == null) {
                Window window = activity.getWindow();
                FrameLayout frameLayout = (FrameLayout) window.getDecorView();
                window.addFlags(1024);
                window.addFlags(128);
                frameLayout.setBackgroundColor(-16777216);
                MediaController mediaController = new MediaController(activity);
                mediaController.setMediaPlayer(this.f13238c);
                this.f13238c.setMediaController(mediaController);
                FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-1, -1);
                layoutParams.gravity = 17;
                frameLayout.addView(this.f13238c, layoutParams);
            }
            if (Build.VERSION.SDK_INT >= 8) {
                this.f13238c.start();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public void m16638a(Activity activity, int i) {
        VideoView videoView;
        VideoView videoView2;
        if (i == 3 && !m16636b() && (videoView2 = this.f13238c) != null) {
            videoView2.pause();
        }
        if (i == 4) {
            this.f13239d = null;
            if (!m16636b() && (videoView = this.f13238c) != null) {
                videoView.stopPlayback();
                this.f13238c = null;
            }
        }
        if (i == 2 && !m16636b()) {
            this.f13239d = activity;
            m16639a(activity);
        }
        if (m16636b()) {
            this.f13237b.m16623a(this.f13236a, activity, i);
        }
    }

    @Override // android.media.MediaPlayer.OnErrorListener
    public boolean onError(MediaPlayer mediaPlayer, int i, int i2) {
        try {
            if (this.f13239d instanceof Activity) {
                Activity activity = (Activity) this.f13239d;
                if (!activity.isFinishing()) {
                    activity.finish();
                }
            }
            Context context = getContext();
            if (context != null) {
                Toast.makeText(context, "播放失败，请选择其它播放器播放", 1).show();
                Context applicationContext = context.getApplicationContext();
                Intent intent = new Intent("android.intent.action.VIEW");
                intent.addFlags(268435456);
                intent.setDataAndType(Uri.parse(this.f13240e), "video/*");
                applicationContext.startActivity(intent);
            }
            return true;
        } catch (Throwable unused) {
            return false;
        }
    }

    /* renamed from: c */
    public void m16634c() {
        if (m16636b()) {
            this.f13237b.m16624a(this.f13236a);
        }
    }
}
