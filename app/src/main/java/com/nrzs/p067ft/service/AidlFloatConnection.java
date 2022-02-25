package com.nrzs.p067ft.service;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.IBinder;
import com.nrzs.p067ft.INRZSAidlInterface;

/* renamed from: com.nrzs.ft.service.a */
/* loaded from: classes2.dex */
public class AidlFloatConnection implements ServiceConnection {

    /* renamed from: a */
    private INRZSAidlInterface f10733a;

    /* renamed from: a */
    public void m18887a(String str) {
    }

    @Override // android.content.ServiceConnection
    public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        this.f10733a = INRZSAidlInterface.AbstractBinderC2008a.asInterface(iBinder);
    }

    @Override // android.content.ServiceConnection
    public void onServiceDisconnected(ComponentName componentName) {
        this.f10733a = null;
    }
}
