package com.tendcloud.tenddata;

import android.app.Activity;
import android.os.Message;
import android.support.v4.app.NotificationCompat;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: td */
/* renamed from: com.tendcloud.tenddata.ar */
/* loaded from: classes2.dex */
public final class RunnableC2683ar implements Runnable {
    final /* synthetic */ Activity val$act;
    final /* synthetic */ AbstractC2790d val$features;

    /* JADX INFO: Access modifiers changed from: package-private */
    public RunnableC2683ar(AbstractC2790d dVar, Activity activity) {
        this.val$features = dVar;
        this.val$act = activity;
    }

    @Override // java.lang.Runnable
    public void run() {
        try {
            C3034zz.C3035a aVar = new C3034zz.C3035a();
            aVar.paraMap.put("apiType", 11);
            aVar.paraMap.put(NotificationCompat.CATEGORY_SERVICE, this.val$features);
            aVar.paraMap.put("pageName", this.val$act != null ? this.val$act.getLocalClassName() : "");
            aVar.paraMap.put("occurTime", String.valueOf(System.currentTimeMillis()));
            Message.obtain(C3034zz.m15206c(), 102, aVar).sendToTarget();
        } catch (Throwable unused) {
        }
    }
}
