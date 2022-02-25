package com.cyjh.ddy.media.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import com.cyjh.ddy.media.media.listener.IHwyMediaListener;
import com.cyjh.ddy.media.media.listener.IMediaServiceListener;
import com.cyjh.ddy.media.oksocket.MsgDataBean;
import java.lang.ref.WeakReference;

/* loaded from: classes.dex */
public class MediaService extends Service implements IMediaWrap {

    /* renamed from: a */
    private MediaWrap f7434a = new MediaWrap();

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        return new LocalBinder(this);
    }

    /* loaded from: classes.dex */
    public static class LocalBinder extends Binder {

        /* renamed from: a */
        private WeakReference<MediaService> f7435a;

        public LocalBinder(MediaService mediaService) {
            this.f7435a = new WeakReference<>(mediaService);
        }

        public MediaService getServerInstance() {
            return this.f7435a.get();
        }
    }

    @Override // android.app.Service
    public int onStartCommand(Intent intent, int i, int i2) {
        return super.onStartCommand(intent, i, i2);
    }

    @Override // android.app.Service
    public void onDestroy() {
        release();
        super.onDestroy();
    }

    @Override // com.cyjh.ddy.media.service.IMediaWrap
    public void connect(String str, int i, String str2, IHwyMediaListener gVar, String str3, IMediaServiceListener hVar, boolean z) {
        this.f7434a.connect(str, i, str2, gVar, str3, hVar, z);
    }

    @Override // com.cyjh.ddy.media.service.IMediaWrap
    public void send(MsgDataBean bVar) {
        this.f7434a.send(bVar);
    }

    @Override // com.cyjh.ddy.media.service.IMediaWrap
    public void release() {
        this.f7434a.release();
    }
}
