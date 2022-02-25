package com.alipay.sdk.app;

import android.app.Activity;
import android.graphics.Bitmap;
import android.net.http.SslError;
import android.os.SystemClock;
import android.webkit.SslErrorHandler;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import p110z1.C3754ao;
import p110z1.C3857aq;
import p110z1.C4745bt;
import p110z1.C5019co;

/* renamed from: com.alipay.sdk.app.d */
/* loaded from: classes.dex */
public class C0655d extends WebViewClient {

    /* renamed from: a */
    private Activity f314a;

    /* renamed from: b */
    private boolean f315b;

    /* renamed from: c */
    private final C4745bt f316c;

    public C0655d(Activity activity, C4745bt btVar) {
        this.f314a = activity;
        this.f316c = btVar;
    }

    @Override // android.webkit.WebViewClient
    public void onReceivedError(WebView webView, int i, String str, String str2) {
        this.f315b = true;
        super.onReceivedError(webView, i, str, str2);
    }

    @Override // android.webkit.WebViewClient
    public void onReceivedSslError(WebView webView, SslErrorHandler sslErrorHandler, SslError sslError) {
        Activity activity = this.f314a;
        if (activity != null) {
            C4745bt btVar = this.f316c;
            C3754ao.m12156a(btVar, C3857aq.f17235a, C3857aq.f17267r, "1" + sslError);
            activity.runOnUiThread(new RunnableC0656e(this, activity, sslErrorHandler));
        }
    }

    @Override // android.webkit.WebViewClient
    public boolean shouldOverrideUrlLoading(WebView webView, String str) {
        return C5019co.m4491a(this.f316c, webView, str, this.f314a);
    }

    @Override // android.webkit.WebViewClient
    public void onPageStarted(WebView webView, String str, Bitmap bitmap) {
        C4745bt btVar = this.f316c;
        C3754ao.m12151b(btVar, C3857aq.f17251b, "h5ld", SystemClock.elapsedRealtime() + "|" + C5019co.m4474e(str));
        super.onPageStarted(webView, str, bitmap);
    }

    @Override // android.webkit.WebViewClient
    public void onPageFinished(WebView webView, String str) {
        C4745bt btVar = this.f316c;
        C3754ao.m12151b(btVar, C3857aq.f17251b, "h5ldd", SystemClock.elapsedRealtime() + "|" + C5019co.m4474e(str));
    }

    /* renamed from: a */
    public void m25293a() {
        this.f314a = null;
    }

    /* renamed from: b */
    public boolean m25291b() {
        return this.f315b;
    }
}
