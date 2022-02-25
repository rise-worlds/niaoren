package com.cyjh.ddy.media.media.qemu;

import android.media.MediaFormat;
import com.cyjh.ddy.base.utils.CLog;
import com.cyjh.ddy.media.beaninner.VedioPackage;
import java.nio.ByteBuffer;

/* renamed from: com.cyjh.ddy.media.media.qemu.e */
/* loaded from: classes.dex */
public class LiveVedioThread extends LiveMediaCodecThread {

    /* renamed from: b */
    private boolean f7377b;

    /* renamed from: c */
    private int f7378c;

    /* renamed from: d */
    private int f7379d;

    /* renamed from: e */
    private VedioPackage f7380e;

    /* renamed from: f */
    private VedioPackage f7381f;

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.cyjh.ddy.media.media.qemu.LiveMediaCodecThread
    /* renamed from: c */
    public boolean mo21544c() {
        return true;
    }

    public LiveVedioThread() {
        this(960, 540);
    }

    public LiveVedioThread(int i, int i2) {
        super("LiveVedioThread");
        this.f7379d = i;
        this.f7378c = i2;
    }

    @Override // com.cyjh.ddy.media.media.qemu.LiveMediaCodecThread
    /* renamed from: a */
    protected MediaFormat mo21546a(String str) {
        MediaFormat createVideoFormat = MediaFormat.createVideoFormat(str, this.f7379d, this.f7378c);
        VedioPackage bVar = this.f7380e;
        if (bVar == null || this.f7381f == null) {
            CLog.m21882i("LiveVedioThread", "createMediaFormat sps/pps uninit");
        } else {
            createVideoFormat.setByteBuffer("csd-1", ByteBuffer.wrap(bVar.f7240a));
            createVideoFormat.setByteBuffer("csd-0", ByteBuffer.wrap(this.f7381f.f7240a));
            CLog.m21882i("LiveVedioThread", "createMediaFormat sps/pps init");
        }
        return createVideoFormat;
    }

    /* renamed from: i */
    public int m21541i() {
        return this.f7378c;
    }

    /* renamed from: j */
    public int m21540j() {
        return this.f7379d;
    }

    @Override // com.cyjh.ddy.media.media.qemu.LiveMediaCodecThread
    /* renamed from: a */
    protected String mo21545a(boolean z) {
        String str = z ? "video/hevc" : "video/avc";
        CLog.m21882i("LiveVedioThread", "mimeType: " + str);
        return str;
    }

    /* renamed from: c */
    public void m21543c(VedioPackage bVar) {
        this.f7380e = bVar;
    }

    /* renamed from: d */
    public void m21542d(VedioPackage bVar) {
        this.f7381f = bVar;
    }

    /* renamed from: k */
    public int m21539k() {
        VedioPackage bVar = this.f7381f;
        if (bVar == null || this.f7380e == null) {
            return 0;
        }
        return bVar.f7242c + this.f7380e.f7242c;
    }
}
