package com.tendcloud.tenddata;

import android.os.Message;
import android.support.v4.app.NotificationCompat;

import org.apache.http.cookie.ClientCookie;

/* compiled from: td */
/* renamed from: com.tendcloud.tenddata.am */
/* loaded from: classes2.dex */
class RunnableC2678am implements Runnable {
    final /* synthetic */ C3034zz this$0;
    final /* synthetic */ String val$account;
    final /* synthetic */ String val$name;
    final /* synthetic */ AbstractC2790d val$service;
    final /* synthetic */ TDAccount.AccountType val$type;

    /* JADX INFO: Access modifiers changed from: package-private */
    public RunnableC2678am(C3034zz zzVar, AbstractC2790d dVar, String str, TDAccount.AccountType accountType, String str2) {
        this.this$0 = zzVar;
        this.val$service = dVar;
        this.val$account = str;
        this.val$type = accountType;
        this.val$name = str2;
    }

    @Override // java.lang.Runnable
    public void run() {
        try {
            C3034zz.C3035a aVar = new C3034zz.C3035a();
            aVar.paraMap.put("apiType", 9);
            aVar.paraMap.put(ClientCookie.DOMAIN_ATTR, "account");
            aVar.paraMap.put(NotificationCompat.CATEGORY_SERVICE, this.val$service);
            aVar.paraMap.put("action", "register");
            aVar.paraMap.put("accountId", this.val$account);
            if (this.val$type != null) {
                aVar.paraMap.put("type", this.val$type.name());
            }
            if (this.val$name != null) {
                aVar.paraMap.put("name", this.val$name);
            }
            Message.obtain(C3034zz.m15206c(), 102, aVar).sendToTarget();
        } catch (Throwable unused) {
        }
    }
}
