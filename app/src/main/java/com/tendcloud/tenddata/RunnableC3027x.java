package com.tendcloud.tenddata;

import android.os.Message;
import android.support.v4.app.NotificationCompat;
import android.text.TextUtils;

import java.util.TreeMap;
import org.apache.http.cookie.ClientCookie;
import org.json.JSONArray;

/* compiled from: td */
/* renamed from: com.tendcloud.tenddata.x */
/* loaded from: classes2.dex */
class RunnableC3027x implements Runnable {
    final /* synthetic */ C3034zz this$0;
    final /* synthetic */ String val$account;
    final /* synthetic */ Order val$order;
    final /* synthetic */ AbstractC2790d val$service;

    /* JADX INFO: Access modifiers changed from: package-private */
    public RunnableC3027x(C3034zz zzVar, AbstractC2790d dVar, String str, Order order) {
        this.this$0 = zzVar;
        this.val$service = dVar;
        this.val$account = str;
        this.val$order = order;
    }

    @Override // java.lang.Runnable
    public void run() {
        JSONArray jSONArray;
        try {
            C3034zz.C3035a aVar = new C3034zz.C3035a();
            aVar.paraMap.put("apiType", 8);
            aVar.paraMap.put(NotificationCompat.CATEGORY_SERVICE, this.val$service);
            aVar.paraMap.put(ClientCookie.DOMAIN_ATTR, "iap");
            aVar.paraMap.put("action", "placeOrder");
            TreeMap treeMap = new TreeMap();
            treeMap.put("accountId", this.val$account);
            treeMap.put("orderId", this.val$order.getString(Order.keyOrderId));
            treeMap.put("amount", Integer.valueOf(this.val$order.optInt(Order.keyTotalPrice)));
            if (this.val$service.name().equals("TRACKING")) {
                treeMap.put("currencyType", this.val$order.optString(Order.keyCurrencyType));
            } else if (this.val$service.name().equals("APP") || this.val$service.name().equals("FINTECH")) {
                String optString = this.val$order.optString(Order.keyCurrencyType);
                if (TextUtils.isEmpty(optString)) {
                    optString = "CNY";
                }
                treeMap.put("currencyType", optString);
            }
            if (this.val$order.has(Order.keyOrderDetail) && (jSONArray = this.val$order.getJSONArray(Order.keyOrderDetail)) != null && jSONArray.length() > 0) {
                treeMap.put("items", jSONArray);
            }
            aVar.paraMap.put("data", treeMap);
            Message.obtain(C3034zz.m15206c(), 102, aVar).sendToTarget();
        } catch (Throwable unused) {
        }
    }
}
