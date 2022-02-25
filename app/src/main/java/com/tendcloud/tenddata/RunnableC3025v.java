package com.tendcloud.tenddata;

import android.os.Message;
import android.support.p003v4.app.NotificationCompat;
import com.tendcloud.tenddata.C3034zz;
import java.util.TreeMap;
import org.apache.http.cookie.ClientCookie;
import org.json.JSONArray;
import org.json.JSONObject;

/* compiled from: td */
/* renamed from: com.tendcloud.tenddata.v */
/* loaded from: classes2.dex */
class RunnableC3025v implements Runnable {
    final /* synthetic */ C3034zz this$0;
    final /* synthetic */ String val$account;
    final /* synthetic */ int val$amount;
    final /* synthetic */ String val$currencyType;
    final /* synthetic */ Order val$order;
    final /* synthetic */ String val$orderid;
    final /* synthetic */ String val$payType;
    final /* synthetic */ AbstractC2790d val$service;

    /* JADX INFO: Access modifiers changed from: package-private */
    public RunnableC3025v(C3034zz zzVar, AbstractC2790d dVar, String str, String str2, int i, String str3, String str4, Order order) {
        this.this$0 = zzVar;
        this.val$service = dVar;
        this.val$account = str;
        this.val$orderid = str2;
        this.val$amount = i;
        this.val$currencyType = str3;
        this.val$payType = str4;
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
            aVar.paraMap.put("action", "recharge");
            TreeMap treeMap = new TreeMap();
            treeMap.put("accountId", this.val$account);
            treeMap.put("orderId", this.val$orderid);
            treeMap.put("amount", Integer.valueOf(this.val$amount));
            treeMap.put("currencyType", this.val$currencyType);
            treeMap.put("payType", this.val$payType);
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("orderId", this.val$order.getString(Order.keyOrderId));
            jSONObject.put("amount", this.val$order.getInt(Order.keyTotalPrice));
            jSONObject.put("currencyType", this.val$order.getString(Order.keyCurrencyType));
            if (this.val$order.has(Order.keyOrderDetail) && (jSONArray = this.val$order.getJSONArray(Order.keyOrderDetail)) != null && jSONArray.length() > 0) {
                jSONObject.put("items", jSONArray);
            }
            treeMap.put("order", jSONObject);
            aVar.paraMap.put("data", treeMap);
            Message.obtain(C3034zz.m15206c(), 102, aVar).sendToTarget();
        } catch (Throwable unused) {
        }
    }
}
