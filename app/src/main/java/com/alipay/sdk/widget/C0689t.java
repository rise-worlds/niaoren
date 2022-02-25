package com.alipay.sdk.widget;

import android.net.http.SslError;
import android.webkit.SslErrorHandler;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.alipay.sdk.widget.C0682p;

/* JADX INFO: Access modifiers changed from: package-private */
/* renamed from: com.alipay.sdk.widget.t */
/* loaded from: classes.dex */
public class C0689t extends WebViewClient {

    /* renamed from: a */
    final /* synthetic */ C0682p f414a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public C0689t(C0682p pVar) {
        this.f414a = pVar;
    }

    @Override // android.webkit.WebViewClient
    public boolean shouldOverrideUrlLoading(WebView webView, String str) {
        C0682p.AbstractC0684b bVar;
        bVar = this.f414a.f405h;
        if (!bVar.mo25221b(this.f414a, str)) {
            return super.shouldOverrideUrlLoading(webView, str);
        }
        return true;
    }

    @Override // android.webkit.WebViewClient
    public void onPageFinished(WebView webView, String str) {
        C0682p.AbstractC0684b bVar;
        bVar = this.f414a.f405h;
        if (!bVar.mo25220c(this.f414a, str)) {
            super.onPageFinished(webView, str);
        }
    }

    @Override // android.webkit.WebViewClient
    public void onReceivedError(WebView webView, int i, String str, String str2) {
        C0682p.AbstractC0684b bVar;
        bVar = this.f414a.f405h;
        if (!bVar.mo25223a(this.f414a, i, str, str2)) {
            super.onReceivedError(webView, i, str, str2);
        }
    }

    @Override // android.webkit.WebViewClient
    public void onReceivedSslError(WebView webView, SslErrorHandler sslErrorHandler, SslError sslError) {
        C0682p.AbstractC0684b bVar;
        bVar = this.f414a.f405h;
        if (!bVar.mo25222a(this.f414a, sslErrorHandler, sslError)) {
            super.onReceivedSslError(webView, sslErrorHandler, sslError);
        }
    }
}
