package com.lody.virtual.client.stub;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import com.lody.virtual.client.core.VirtualCore;

/* loaded from: classes.dex */
public class KeepAliveService extends Service {
    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override // android.app.Service
    public int onStartCommand(Intent intent, int i, int i2) {
        return 1;
    }

    @Override // android.app.Service
    public void onCreate() {
        super.onCreate();
        if (!VirtualCore.getConfig().isHideForegroundNotification()) {
            HiddenForeNotification.bindForeground(this);
        }
    }
}
