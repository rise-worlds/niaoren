package com.tendcloud.tenddata;

import android.os.Message;
import android.support.p003v4.app.NotificationCompat;
import com.tendcloud.tenddata.C3034zz;
import java.net.URLEncoder;
import java.util.TreeMap;
import org.apache.commons.mail.EmailConstants;
import org.apache.http.cookie.ClientCookie;

/* compiled from: td */
/* renamed from: com.tendcloud.tenddata.ad */
/* loaded from: classes2.dex */
class RunnableC2669ad implements Runnable {
    final /* synthetic */ C3034zz this$0;
    final /* synthetic */ String val$link;
    final /* synthetic */ AbstractC2790d val$service;

    /* JADX INFO: Access modifiers changed from: package-private */
    public RunnableC2669ad(C3034zz zzVar, String str, AbstractC2790d dVar) {
        this.this$0 = zzVar;
        this.val$link = str;
        this.val$service = dVar;
    }

    @Override // java.lang.Runnable
    public void run() {
        try {
            C2979il.m15417a().setDeepLink(URLEncoder.encode(this.val$link, EmailConstants.UTF_8));
            C3034zz.C3035a aVar = new C3034zz.C3035a();
            aVar.paraMap.put("apiType", 8);
            aVar.paraMap.put(NotificationCompat.CATEGORY_SERVICE, this.val$service);
            aVar.paraMap.put(ClientCookie.DOMAIN_ATTR, "app");
            aVar.paraMap.put("action", "deeplink");
            TreeMap treeMap = new TreeMap();
            treeMap.put("link", this.val$link);
            aVar.paraMap.put("data", treeMap);
            Message.obtain(C3034zz.m15206c(), 102, aVar).sendToTarget();
        } catch (Throwable unused) {
        }
    }
}
