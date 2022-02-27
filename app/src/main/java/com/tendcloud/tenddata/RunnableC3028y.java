package com.tendcloud.tenddata;

import android.os.Message;
import android.support.v4.app.NotificationCompat;
import android.text.TextUtils;
import com.liulishuo.filedownloader.model.ConnectionModel;

import java.util.TreeMap;
import org.apache.http.cookie.ClientCookie;

/* compiled from: td */
/* renamed from: com.tendcloud.tenddata.y */
/* loaded from: classes2.dex */
class RunnableC3028y implements Runnable {
    final /* synthetic */ C3034zz this$0;
    final /* synthetic */ String val$category;
    final /* synthetic */ String val$itemId;
    final /* synthetic */ String val$name;
    final /* synthetic */ AbstractC2790d val$service;
    final /* synthetic */ int val$unitPrice;

    /* JADX INFO: Access modifiers changed from: package-private */
    public RunnableC3028y(C3034zz zzVar, AbstractC2790d dVar, String str, String str2, String str3, int i) {
        this.this$0 = zzVar;
        this.val$service = dVar;
        this.val$itemId = str;
        this.val$category = str2;
        this.val$name = str3;
        this.val$unitPrice = i;
    }

    @Override // java.lang.Runnable
    public void run() {
        try {
            C3034zz.C3035a aVar = new C3034zz.C3035a();
            aVar.paraMap.put("apiType", 8);
            aVar.paraMap.put(NotificationCompat.CATEGORY_SERVICE, this.val$service);
            aVar.paraMap.put(ClientCookie.DOMAIN_ATTR, "iap");
            aVar.paraMap.put("action", "viewItem");
            TreeMap treeMap = new TreeMap();
            if (!this.val$service.name().equals("APP") && !this.val$service.name().equals("FINTECH")) {
                treeMap.put(ConnectionModel.f10389a, this.val$itemId);
                treeMap.put("category", this.val$category);
                treeMap.put("name", this.val$name);
                treeMap.put("unitPrice", Integer.valueOf(this.val$unitPrice));
                aVar.paraMap.put("data", treeMap);
                Message.obtain(C3034zz.m15206c(), 102, aVar).sendToTarget();
            }
            if (!TextUtils.isEmpty(this.val$itemId)) {
                treeMap.put(ConnectionModel.f10389a, this.val$itemId);
            }
            if (!TextUtils.isEmpty(this.val$category)) {
                treeMap.put("category", this.val$category);
            }
            if (!TextUtils.isEmpty(this.val$name)) {
                treeMap.put("name", this.val$name);
            }
            treeMap.put("unitPrice", Integer.valueOf(this.val$unitPrice));
            aVar.paraMap.put("data", treeMap);
            Message.obtain(C3034zz.m15206c(), 102, aVar).sendToTarget();
        } catch (Throwable unused) {
        }
    }
}
