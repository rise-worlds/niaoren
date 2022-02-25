package com.nrzs.p067ft;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteCallbackList;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.util.Log;
import com.nrzs.p067ft.INRZSAidlInterface;
import p110z1.EventBus;
import p110z1.FtEvent;
import p110z1.Subscribe;
import p110z1.ThreadMode;
import p110z1.apa;

/* renamed from: com.nrzs.ft.NRZSAidlService */
/* loaded from: classes2.dex */
public class NRZSAidlService extends Service {

    /* renamed from: b */
    private RemoteCallbackList<IOnNewLoginInfoListener> f10661b = new RemoteCallbackList<>();

    /* renamed from: a */
    INRZSAidlInterface.AbstractBinderC2008a f10660a = new INRZSAidlInterface.AbstractBinderC2008a() { // from class: com.nrzs.ft.NRZSAidlService.1
        @Override // com.nrzs.p067ft.INRZSAidlInterface
        public void basicTypes(int i, long j, boolean z, float f, double d, String str) throws RemoteException {
        }

        @Override // com.nrzs.p067ft.INRZSAidlInterface
        public void registListener(IOnNewLoginInfoListener dVar) throws RemoteException {
            Log.e("NRZSAidlService", "registListener");
            NRZSAidlService.this.f10661b.register(dVar);
        }

        @Override // com.nrzs.p067ft.INRZSAidlInterface
        public void unregistListener(IOnNewLoginInfoListener dVar) throws RemoteException {
            NRZSAidlService.this.f10661b.unregister(dVar);
        }
    };

    @Override // android.app.Service
    public void onDestroy() {
        EventBus.m3448a().m3430c(this);
        super.onDestroy();
    }

    @Subscribe(m3389a = ThreadMode.MAIN, m3388b = true)
    /* renamed from: a */
    public void m18917a(FtEvent.C3567e eVar) {
        Log.e("NRZSAidlService", "onMessageEvent");
        int beginBroadcast = this.f10661b.beginBroadcast();
        for (int i = 0; i < beginBroadcast; i++) {
            IOnNewLoginInfoListener broadcastItem = this.f10661b.getBroadcastItem(i);
            if (broadcastItem != null) {
                try {
                    broadcastItem.onNewLoginInfo(apa.m11879a(eVar.f16162a));
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        }
        this.f10661b.finishBroadcast();
    }

    @Subscribe(m3389a = ThreadMode.MAIN, m3388b = true)
    /* renamed from: a */
    public void m18918a(FtEvent.C3566d dVar) {
        Log.e("NRZSAidlService", "onMessageEvent");
        int beginBroadcast = this.f10661b.beginBroadcast();
        for (int i = 0; i < beginBroadcast; i++) {
            IOnNewLoginInfoListener broadcastItem = this.f10661b.getBroadcastItem(i);
            if (broadcastItem != null) {
                try {
                    broadcastItem.onNewLoginInfo(apa.m11879a(dVar.f16161a));
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        }
        this.f10661b.finishBroadcast();
    }

    @Override // android.app.Service
    @Nullable
    public IBinder onBind(Intent intent) {
        Log.e("NRZSAidlService", "onBind");
        EventBus.m3448a().m3446a(this);
        return this.f10660a;
    }
}
