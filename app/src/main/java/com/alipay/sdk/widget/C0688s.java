package com.alipay.sdk.widget;

import android.webkit.JsPromptResult;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.ProgressBar;
import com.alipay.sdk.widget.C0682p;

/* JADX INFO: Access modifiers changed from: package-private */
/* renamed from: com.alipay.sdk.widget.s */
/* loaded from: classes.dex */
public class C0688s extends WebChromeClient {

    /* renamed from: a */
    final /* synthetic */ C0682p f413a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public C0688s(C0682p pVar) {
        this.f413a = pVar;
    }

    @Override // android.webkit.WebChromeClient
    public void onProgressChanged(WebView webView, int i) {
        ProgressBar progressBar;
        ProgressBar progressBar2;
        ProgressBar progressBar3;
        ProgressBar progressBar4;
        if (i == 100) {
            progressBar4 = this.f413a.f402d;
            progressBar4.setVisibility(4);
            return;
        }
        progressBar = this.f413a.f402d;
        if (4 == progressBar.getVisibility()) {
            progressBar3 = this.f413a.f402d;
            progressBar3.setVisibility(0);
        }
        progressBar2 = this.f413a.f402d;
        progressBar2.setProgress(i);
    }

    @Override // android.webkit.WebChromeClient
    public boolean onJsPrompt(WebView webView, String str, String str2, String str3, JsPromptResult jsPromptResult) {
        C0682p.AbstractC0683a aVar;
        aVar = this.f413a.f404g;
        return aVar.mo25224a(this.f413a, str, str2, str3, jsPromptResult);
    }

    @Override // android.webkit.WebChromeClient
    public void onReceivedTitle(WebView webView, String str) {
        C0682p.AbstractC0683a aVar;
        aVar = this.f413a.f404g;
        aVar.mo25225a(this.f413a, str);
    }
}
