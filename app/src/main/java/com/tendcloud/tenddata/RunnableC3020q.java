package com.tendcloud.tenddata;

import android.os.Message;
import android.support.v4.app.NotificationCompat;

import java.util.TreeMap;
import org.apache.http.cookie.ClientCookie;

/* compiled from: td */
/* renamed from: com.tendcloud.tenddata.q */
/* loaded from: classes2.dex */
class RunnableC3020q implements Runnable {
    final /* synthetic */ C3034zz this$0;
    final /* synthetic */ String val$account;
    final /* synthetic */ int val$amount;
    final /* synthetic */ String val$currencyType;
    final /* synthetic */ String val$orderid;
    final /* synthetic */ String val$payType;
    final /* synthetic */ AbstractC2790d val$service;

    /* JADX INFO: Access modifiers changed from: package-private */
    public RunnableC3020q(C3034zz zzVar, AbstractC2790d dVar, String str, String str2, int i, String str3, String str4) {
        this.this$0 = zzVar;
        this.val$service = dVar;
        this.val$account = str;
        this.val$orderid = str2;
        this.val$amount = i;
        this.val$currencyType = str3;
        this.val$payType = str4;
    }

    @Override // java.lang.Runnable
    public void run() {
        try {
            C3034zz.C3035a aVar = new C3034zz.C3035a();
            aVar.paraMap.put("apiType", 8);
            aVar.paraMap.put(NotificationCompat.CATEGORY_SERVICE, this.val$service);
            aVar.paraMap.put(ClientCookie.DOMAIN_ATTR, "iap");
            aVar.paraMap.put("action", "recharge");
            TreeMap treeMap = new TreeMap();
            treeMap.put("accountId", this.val$account);
            treeMap.put("orderId", this.val$orderid);
            treeMap.put("amount", Integer.valueOf(this.val$amount));
            treeMap.put("currencyType", this.val$currencyType);
            treeMap.put("payType", this.val$payType);
            aVar.paraMap.put("data", treeMap);
            Message.obtain(C3034zz.m15206c(), 102, aVar).sendToTarget();
        } catch (Throwable unused) {
        }
    }
}
