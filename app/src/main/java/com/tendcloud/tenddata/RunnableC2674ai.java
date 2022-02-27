package com.tendcloud.tenddata;

import android.os.Message;
import android.support.v4.app.NotificationCompat;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: td */
/* renamed from: com.tendcloud.tenddata.ai */
/* loaded from: classes2.dex */
public class RunnableC2674ai implements Runnable {
    final /* synthetic */ C3034zz this$0;
    final /* synthetic */ int val$apiType;
    final /* synthetic */ AbstractC2790d val$features;
    final /* synthetic */ String val$pageName;

    /* JADX INFO: Access modifiers changed from: package-private */
    public RunnableC2674ai(C3034zz zzVar, int i, String str, AbstractC2790d dVar) {
        this.this$0 = zzVar;
        this.val$apiType = i;
        this.val$pageName = str;
        this.val$features = dVar;
    }

    @Override // java.lang.Runnable
    public void run() {
        try {
            if (C3034zz.f14345a) {
                C3034zz.C3035a aVar = new C3034zz.C3035a();
                aVar.paraMap.put("apiType", Integer.valueOf(this.val$apiType));
                aVar.paraMap.put("occurTime", String.valueOf(System.currentTimeMillis()));
                aVar.paraMap.put("pageName", this.val$pageName == null ? "" : C2855es.m15800a(this.val$pageName));
                aVar.paraMap.put(NotificationCompat.CATEGORY_SERVICE, this.val$features);
                Message.obtain(C3034zz.m15206c(), 102, aVar).sendToTarget();
            }
        } catch (Throwable unused) {
        }
    }
}
