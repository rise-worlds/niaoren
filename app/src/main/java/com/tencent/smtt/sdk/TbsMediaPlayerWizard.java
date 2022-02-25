package com.tencent.smtt.sdk;

import android.content.Context;
import android.graphics.SurfaceTexture;
import android.os.Bundle;
import com.tencent.smtt.export.external.DexLoader;
import com.tencent.smtt.sdk.TbsMediaPlayer;

/* JADX INFO: Access modifiers changed from: package-private */
/* renamed from: com.tencent.smtt.sdk.m */
/* loaded from: classes2.dex */
public class TbsMediaPlayerWizard {

    /* renamed from: a */
    private DexLoader f13225a;

    /* renamed from: b */
    private Object f13226b;

    public TbsMediaPlayerWizard(DexLoader dexLoader, Context context) {
        this.f13225a = null;
        this.f13226b = null;
        this.f13225a = dexLoader;
        this.f13226b = this.f13225a.newInstance("com.tencent.tbs.player.TbsMediaPlayerProxy", new Class[]{Context.class}, context);
    }

    /* renamed from: a */
    public boolean m16667a() {
        return this.f13226b != null;
    }

    /* renamed from: a */
    public void m16663a(SurfaceTexture surfaceTexture) {
        this.f13225a.invokeMethod(this.f13226b, "com.tencent.tbs.player.TbsMediaPlayerProxy", "setSurfaceTexture", new Class[]{SurfaceTexture.class}, surfaceTexture);
    }

    /* renamed from: a */
    public void m16662a(TbsMediaPlayer.TbsMediaPlayerListener tbsMediaPlayerListener) {
        this.f13225a.invokeMethod(this.f13226b, "com.tencent.tbs.player.TbsMediaPlayerProxy", "setPlayerListener", new Class[]{Object.class}, tbsMediaPlayerListener);
    }

    /* renamed from: b */
    public float m16660b() {
        Float f = (Float) this.f13225a.invokeMethod(this.f13226b, "com.tencent.tbs.player.TbsMediaPlayerProxy", "getVolume", new Class[0], new Object[0]);
        if (f != null) {
            return f.floatValue();
        }
        return 0.0f;
    }

    /* renamed from: a */
    public void m16666a(float f) {
        this.f13225a.invokeMethod(this.f13226b, "com.tencent.tbs.player.TbsMediaPlayerProxy", "setVolume", new Class[]{Float.TYPE}, Float.valueOf(f));
    }

    /* renamed from: a */
    public void m16661a(String str, Bundle bundle) {
        this.f13225a.invokeMethod(this.f13226b, "com.tencent.tbs.player.TbsMediaPlayerProxy", "startPlay", new Class[]{String.class, Bundle.class}, str, bundle);
    }

    /* renamed from: a */
    public void m16665a(int i) {
        this.f13225a.invokeMethod(this.f13226b, "com.tencent.tbs.player.TbsMediaPlayerProxy", "subtitle", new Class[]{Integer.TYPE}, Integer.valueOf(i));
    }

    /* renamed from: b */
    public void m16659b(int i) {
        this.f13225a.invokeMethod(this.f13226b, "com.tencent.tbs.player.TbsMediaPlayerProxy", "audio", new Class[]{Integer.TYPE}, Integer.valueOf(i));
    }

    /* renamed from: c */
    public void m16658c() {
        this.f13225a.invokeMethod(this.f13226b, "com.tencent.tbs.player.TbsMediaPlayerProxy", "pause", new Class[0], new Object[0]);
    }

    /* renamed from: d */
    public void m16657d() {
        this.f13225a.invokeMethod(this.f13226b, "com.tencent.tbs.player.TbsMediaPlayerProxy", "play", new Class[0], new Object[0]);
    }

    /* renamed from: a */
    public void m16664a(long j) {
        this.f13225a.invokeMethod(this.f13226b, "com.tencent.tbs.player.TbsMediaPlayerProxy", "seek", new Class[]{Long.TYPE}, Long.valueOf(j));
    }

    /* renamed from: e */
    public void m16656e() {
        this.f13225a.invokeMethod(this.f13226b, "com.tencent.tbs.player.TbsMediaPlayerProxy", "close", new Class[0], new Object[0]);
    }
}
