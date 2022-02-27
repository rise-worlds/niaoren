package com.tendcloud.tenddata;

import android.os.Message;
import android.support.v4.app.NotificationCompat;

import org.apache.http.cookie.ClientCookie;

/* compiled from: td */
/* renamed from: com.tendcloud.tenddata.ae */
/* loaded from: classes2.dex */
class RunnableC2670ae implements Runnable {
    final /* synthetic */ C3034zz this$0;
    final /* synthetic */ String val$roleName;
    final /* synthetic */ AbstractC2790d val$service;

    /* JADX INFO: Access modifiers changed from: package-private */
    public RunnableC2670ae(C3034zz zzVar, String str, AbstractC2790d dVar) {
        this.this$0 = zzVar;
        this.val$roleName = str;
        this.val$service = dVar;
    }

    @Override // java.lang.Runnable
    public void run() {
        C3034zz.C3035a aVar = new C3034zz.C3035a();
        aVar.paraMap.put("apiType", 9);
        aVar.paraMap.put(ClientCookie.DOMAIN_ATTR, "account");
        aVar.paraMap.put("action", "roleCreate");
        aVar.paraMap.put("parameter", this.val$roleName);
        aVar.paraMap.put(NotificationCompat.CATEGORY_SERVICE, this.val$service);
        Message.obtain(C3034zz.m15206c(), 102, aVar).sendToTarget();
    }
}
