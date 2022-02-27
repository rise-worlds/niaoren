package com.tendcloud.tenddata;

import android.os.Message;
import android.support.v4.app.NotificationCompat;

import java.util.HashMap;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: td */
/* renamed from: com.tendcloud.tenddata.ah */
/* loaded from: classes2.dex */
public class RunnableC2673ah implements Runnable {
    final /* synthetic */ C3034zz this$0;
    final /* synthetic */ AbstractC2790d val$service;

    /* JADX INFO: Access modifiers changed from: package-private */
    public RunnableC2673ah(C3034zz zzVar, AbstractC2790d dVar) {
        this.this$0 = zzVar;
        this.val$service = dVar;
    }

    @Override // java.lang.Runnable
    public void run() {
        String str;
        String str2;
        try {
            C2922gt.m15555a();
            C3034zz.C3035a aVar = new C3034zz.C3035a();
            aVar.paraMap.put("apiType", 1);
            HashMap hashMap = aVar.paraMap;
            str = C3034zz.f14351g;
            hashMap.put("appId", str != null ? C3034zz.f14351g : "");
            HashMap hashMap2 = aVar.paraMap;
            str2 = C3034zz.f14352h;
            hashMap2.put("channelId", str2 != null ? C3034zz.f14352h : "");
            aVar.paraMap.put(NotificationCompat.CATEGORY_SERVICE, this.val$service);
            aVar.paraMap.put("action", "init");
            Message.obtain(C3034zz.m15206c(), 101, aVar).sendToTarget();
            C2924gv.m15549a();
            C2685at.m16301a();
        } catch (Throwable unused) {
        }
    }
}
