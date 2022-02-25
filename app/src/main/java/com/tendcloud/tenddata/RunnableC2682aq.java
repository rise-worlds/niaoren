package com.tendcloud.tenddata;

import android.os.Message;
import android.support.p003v4.app.NotificationCompat;
import com.tendcloud.tenddata.C3034zz;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: td */
/* renamed from: com.tendcloud.tenddata.aq */
/* loaded from: classes2.dex */
public final class RunnableC2682aq implements Runnable {
    final /* synthetic */ AbstractC2790d val$features;

    /* JADX INFO: Access modifiers changed from: package-private */
    public RunnableC2682aq(AbstractC2790d dVar) {
        this.val$features = dVar;
    }

    @Override // java.lang.Runnable
    public void run() {
        try {
            C3034zz.C3035a aVar = new C3034zz.C3035a();
            aVar.paraMap.put("apiType", 10);
            aVar.paraMap.put("occurTime", Long.valueOf(System.currentTimeMillis()));
            aVar.paraMap.put(NotificationCompat.CATEGORY_SERVICE, this.val$features);
            Message.obtain(C3034zz.m15206c(), 102, aVar).sendToTarget();
        } catch (Throwable unused) {
        }
    }
}
