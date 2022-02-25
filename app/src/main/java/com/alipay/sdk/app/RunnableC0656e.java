package com.alipay.sdk.app;

import android.app.Activity;
import android.webkit.SslErrorHandler;
import com.alipay.sdk.widget.C0670e;

/* renamed from: com.alipay.sdk.app.e */
/* loaded from: classes.dex */
class RunnableC0656e implements Runnable {

    /* renamed from: a */
    final /* synthetic */ Activity f317a;

    /* renamed from: b */
    final /* synthetic */ SslErrorHandler f318b;

    /* renamed from: c */
    final /* synthetic */ C0655d f319c;

    /* JADX INFO: Access modifiers changed from: package-private */
    public RunnableC0656e(C0655d dVar, Activity activity, SslErrorHandler sslErrorHandler) {
        this.f319c = dVar;
        this.f317a = activity;
        this.f318b = sslErrorHandler;
    }

    @Override // java.lang.Runnable
    public void run() {
        C0670e.m25258a(this.f317a, "安全警告", "安全连接证书校验无效，将无法保证访问数据的安全性，请安装支付宝后重试。", "确定", new DialogInterface$OnClickListenerC0657f(this), null, null);
    }
}
