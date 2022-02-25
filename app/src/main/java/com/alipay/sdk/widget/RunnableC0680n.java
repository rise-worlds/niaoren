package com.alipay.sdk.widget;

import android.webkit.SslErrorHandler;

/* renamed from: com.alipay.sdk.widget.n */
/* loaded from: classes.dex */
class RunnableC0680n implements Runnable {

    /* renamed from: a */
    final /* synthetic */ SslErrorHandler f395a;

    /* renamed from: b */
    final /* synthetic */ C0675j f396b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public RunnableC0680n(C0675j jVar, SslErrorHandler sslErrorHandler) {
        this.f396b = jVar;
        this.f395a = sslErrorHandler;
    }

    @Override // java.lang.Runnable
    public void run() {
        C0670e.m25258a(this.f396b.f360a, "安全警告", "安全連接證書校驗無效，將無法保證訪問資料的安全性，請安裝支付寶 Alipay 後重試。", "確定", new DialogInterface$OnClickListenerC0681o(this), null, null);
    }
}
