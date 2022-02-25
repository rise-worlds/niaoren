package com.tendcloud.tenddata;

import android.os.Message;
import android.support.p003v4.app.NotificationCompat;
import com.tendcloud.tenddata.C3034zz;
import java.util.TreeMap;
import org.apache.http.cookie.ClientCookie;

/* compiled from: td */
/* renamed from: com.tendcloud.tenddata.t */
/* loaded from: classes2.dex */
class RunnableC3023t implements Runnable {
    final /* synthetic */ C3034zz this$0;
    final /* synthetic */ String val$account;
    final /* synthetic */ AbstractC2790d val$service;

    /* JADX INFO: Access modifiers changed from: package-private */
    public RunnableC3023t(C3034zz zzVar, AbstractC2790d dVar, String str) {
        this.this$0 = zzVar;
        this.val$service = dVar;
        this.val$account = str;
    }

    @Override // java.lang.Runnable
    public void run() {
        try {
            C3034zz.C3035a aVar = new C3034zz.C3035a();
            aVar.paraMap.put("apiType", 8);
            aVar.paraMap.put(NotificationCompat.CATEGORY_SERVICE, this.val$service);
            aVar.paraMap.put(ClientCookie.DOMAIN_ATTR, "iap");
            aVar.paraMap.put("action", "pay");
            TreeMap treeMap = new TreeMap();
            treeMap.put("accountId", this.val$account);
            aVar.paraMap.put("data", treeMap);
            Message.obtain(C3034zz.m15206c(), 102, aVar).sendToTarget();
        } catch (Throwable unused) {
        }
    }
}
