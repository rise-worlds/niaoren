package com.tendcloud.tenddata;

import android.os.Message;
import android.support.v4.app.NotificationCompat;

import java.util.TreeMap;
import org.apache.http.cookie.ClientCookie;

/* compiled from: td */
/* renamed from: com.tendcloud.tenddata.z */
/* loaded from: classes2.dex */
class RunnableC3029z implements Runnable {
    final /* synthetic */ C3034zz this$0;
    final /* synthetic */ AbstractC2790d val$service;
    final /* synthetic */ ShoppingCart val$shoppingCart;

    /* JADX INFO: Access modifiers changed from: package-private */
    public RunnableC3029z(C3034zz zzVar, AbstractC2790d dVar, ShoppingCart shoppingCart) {
        this.this$0 = zzVar;
        this.val$service = dVar;
        this.val$shoppingCart = shoppingCart;
    }

    @Override // java.lang.Runnable
    public void run() {
        try {
            C3034zz.C3035a aVar = new C3034zz.C3035a();
            aVar.paraMap.put("apiType", 8);
            aVar.paraMap.put(NotificationCompat.CATEGORY_SERVICE, this.val$service);
            aVar.paraMap.put(ClientCookie.DOMAIN_ATTR, "iap");
            aVar.paraMap.put("action", "viewItems");
            TreeMap treeMap = new TreeMap();
            treeMap.put("items", this.val$shoppingCart);
            aVar.paraMap.put("data", treeMap);
            Message.obtain(C3034zz.m15206c(), 102, aVar).sendToTarget();
        } catch (Throwable unused) {
        }
    }
}
