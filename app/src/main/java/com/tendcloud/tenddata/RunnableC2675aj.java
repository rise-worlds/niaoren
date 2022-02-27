package com.tendcloud.tenddata;

import android.os.Message;
import android.support.v4.app.NotificationCompat;

import java.util.Map;

/* compiled from: td */
/* renamed from: com.tendcloud.tenddata.aj */
/* loaded from: classes2.dex */
class RunnableC2675aj implements Runnable {
    final /* synthetic */ C3034zz this$0;
    final /* synthetic */ String val$eventId;
    final /* synthetic */ String val$eventLabel;
    final /* synthetic */ Map val$map;
    final /* synthetic */ AbstractC2790d val$service;

    /* JADX INFO: Access modifiers changed from: package-private */
    public RunnableC2675aj(C3034zz zzVar, AbstractC2790d dVar, String str, String str2, Map map) {
        this.this$0 = zzVar;
        this.val$service = dVar;
        this.val$eventId = str;
        this.val$eventLabel = str2;
        this.val$map = map;
    }

    @Override // java.lang.Runnable
    public void run() {
        try {
            C3034zz.C3035a aVar = new C3034zz.C3035a();
            aVar.paraMap.put(NotificationCompat.CATEGORY_SERVICE, this.val$service);
            aVar.paraMap.put("apiType", 2);
            aVar.paraMap.put("eventId", C2855es.m15800a(this.val$eventId));
            aVar.paraMap.put("eventLabel", this.val$eventLabel == null ? null : C2855es.m15800a(this.val$eventLabel));
            aVar.paraMap.put("map", this.val$map);
            aVar.paraMap.put("occurTime", String.valueOf(System.currentTimeMillis()));
            Message.obtain(C3034zz.m15206c(), 102, aVar).sendToTarget();
        } catch (Throwable unused) {
        }
    }
}
