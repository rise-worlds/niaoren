package com.tendcloud.tenddata;

import android.os.Message;
import android.support.v4.app.NotificationCompat;

/* compiled from: td */
/* renamed from: com.tendcloud.tenddata.ak */
/* loaded from: classes2.dex */
class RunnableC2676ak implements Runnable {
    final /* synthetic */ C3034zz this$0;
    final /* synthetic */ Throwable val$e;
    final /* synthetic */ AbstractC2790d val$service;

    /* JADX INFO: Access modifiers changed from: package-private */
    public RunnableC2676ak(C3034zz zzVar, Throwable th, AbstractC2790d dVar) {
        this.this$0 = zzVar;
        this.val$e = th;
        this.val$service = dVar;
    }

    @Override // java.lang.Runnable
    public void run() {
        try {
            C3034zz.C3035a aVar = new C3034zz.C3035a();
            aVar.paraMap.put("apiType", 3);
            aVar.paraMap.put("occurTime", String.valueOf(System.currentTimeMillis()));
            aVar.paraMap.put("throwable", this.val$e);
            aVar.paraMap.put(NotificationCompat.CATEGORY_SERVICE, this.val$service);
            Message.obtain(C3034zz.m15206c(), 102, aVar).sendToTarget();
        } catch (Throwable unused) {
        }
    }
}
