package com.lbd.xj.utils.vmlog;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

/* renamed from: com.lbd.xj.utils.vmlog.VMLogService */
/* loaded from: classes.dex */
public class VMLogService extends Service {

    /* renamed from: a */
    private VMLogListener f9971a;

    @Override // android.app.Service
    @Nullable
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override // android.app.Service
    public void onDestroy() {
        super.onDestroy();
        VMLogListener aVar = this.f9971a;
        if (aVar != null) {
            aVar.stopWatching();
        }
    }

    @Override // android.app.Service
    public int onStartCommand(Intent intent, int i, int i2) {
        super.onStartCommand(intent, i, i2);
        if (intent == null) {
            return 1;
        }
        if (this.f9971a == null) {
            this.f9971a = new VMLogListener(intent.getStringExtra("path"));
        }
        this.f9971a.startWatching();
        return 1;
    }

    /* renamed from: a */
    public static void m19281a(Context context, String str) {
        Intent intent = new Intent(context, VMLogService.class);
        intent.putExtra("path", str);
        context.startService(intent);
    }

    /* renamed from: a */
    public void m19282a() {
        VMLogListener aVar = this.f9971a;
        if (aVar != null) {
            aVar.startWatching();
        }
    }

    /* renamed from: b */
    public void m19280b() {
        VMLogListener aVar = this.f9971a;
        if (aVar != null) {
            aVar.stopWatching();
        }
    }
}
