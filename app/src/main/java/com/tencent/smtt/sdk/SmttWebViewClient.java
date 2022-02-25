package com.tencent.smtt.sdk;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Message;
import android.view.KeyEvent;
import com.tencent.smtt.export.external.interfaces.ClientCertRequest;
import com.tencent.smtt.export.external.interfaces.HttpAuthHandler;
import com.tencent.smtt.export.external.interfaces.IX5WebViewBase;
import com.tencent.smtt.export.external.interfaces.IX5WebViewClient;
import com.tencent.smtt.export.external.interfaces.SslError;
import com.tencent.smtt.export.external.interfaces.SslErrorHandler;
import com.tencent.smtt.export.external.interfaces.WebResourceError;
import com.tencent.smtt.export.external.interfaces.WebResourceRequest;
import com.tencent.smtt.export.external.interfaces.WebResourceResponse;
import com.tencent.smtt.export.external.proxy.X5ProxyWebViewClient;
import com.tencent.smtt.utils.TbsConfigFile;
import com.tencent.smtt.utils.TbsLog;

/* JADX INFO: Access modifiers changed from: package-private */
/* renamed from: com.tencent.smtt.sdk.g */
/* loaded from: classes2.dex */
public class SmttWebViewClient extends X5ProxyWebViewClient {

    /* renamed from: c */
    private static String f13161c;

    /* renamed from: a */
    private WebViewClient f13162a;

    /* renamed from: b */
    private WebView f13163b;

    public SmttWebViewClient(IX5WebViewClient iX5WebViewClient, WebView webView, WebViewClient webViewClient) {
        super(iX5WebViewClient);
        this.f13163b = webView;
        this.f13162a = webViewClient;
        this.f13162a.f13055a = this;
    }

    @Override // com.tencent.smtt.export.external.proxy.ProxyWebViewClient, com.tencent.smtt.export.external.interfaces.IX5WebViewClient
    public void doUpdateVisitedHistory(IX5WebViewBase iX5WebViewBase, String str, boolean z) {
        this.f13163b.m16930a(iX5WebViewBase);
        this.f13162a.doUpdateVisitedHistory(this.f13163b, str, z);
    }

    @Override // com.tencent.smtt.export.external.proxy.ProxyWebViewClient, com.tencent.smtt.export.external.interfaces.IX5WebViewClient
    public void onFormResubmission(IX5WebViewBase iX5WebViewBase, Message message, Message message2) {
        this.f13163b.m16930a(iX5WebViewBase);
        this.f13162a.onFormResubmission(this.f13163b, message, message2);
    }

    @Override // com.tencent.smtt.export.external.proxy.ProxyWebViewClient, com.tencent.smtt.export.external.interfaces.IX5WebViewClient
    public void onLoadResource(IX5WebViewBase iX5WebViewBase, String str) {
        this.f13163b.m16930a(iX5WebViewBase);
        this.f13162a.onLoadResource(this.f13163b, str);
    }

    @Override // com.tencent.smtt.export.external.proxy.ProxyWebViewClient, com.tencent.smtt.export.external.interfaces.IX5WebViewClient
    public void onPageFinished(IX5WebViewBase iX5WebViewBase, int i, int i2, String str) {
        TbsConfigFile a;
        if (f13161c == null && (a = TbsConfigFile.m16380a()) != null) {
            a.m16378a(false);
            f13161c = Boolean.toString(false);
        }
        this.f13163b.m16930a(iX5WebViewBase);
        this.f13163b.f13022a++;
        this.f13162a.onPageFinished(this.f13163b, str);
        if (TbsConfig.APP_QZONE.equals(iX5WebViewBase.getView().getContext().getApplicationInfo().packageName)) {
            this.f13163b.m16934a(iX5WebViewBase.getView().getContext());
        }
        TbsLog.app_extra("SmttWebViewClient", iX5WebViewBase.getView().getContext());
        try {
            super.onPageFinished(iX5WebViewBase, i, i2, str);
        } catch (Exception unused) {
        }
        WebView.m16922d();
        if (!TbsShareManager.mHasQueryed && this.f13163b.getContext() != null && TbsShareManager.isThirdPartyApp(this.f13163b.getContext())) {
            TbsShareManager.mHasQueryed = true;
            new Thread(new Runnable() { // from class: com.tencent.smtt.sdk.g.1
                @Override // java.lang.Runnable
                public void run() {
                    if (!TbsShareManager.forceLoadX5FromTBSDemo(SmttWebViewClient.this.f13163b.getContext()) && TbsDownloader.needDownload(SmttWebViewClient.this.f13163b.getContext(), false)) {
                        TbsDownloader.startDownload(SmttWebViewClient.this.f13163b.getContext());
                    }
                }
            }).start();
        }
        if (this.f13163b.getContext() != null && !TbsLogReport.getInstance(this.f13163b.getContext()).getShouldUploadEventReport()) {
            TbsLogReport.getInstance(this.f13163b.getContext()).setShouldUploadEventReport(true);
            TbsLogReport.getInstance(this.f13163b.getContext()).dailyReport();
        }
    }

    @Override // com.tencent.smtt.export.external.proxy.ProxyWebViewClient, com.tencent.smtt.export.external.interfaces.IX5WebViewClient
    public void onPageStarted(IX5WebViewBase iX5WebViewBase, int i, int i2, String str, Bitmap bitmap) {
        this.f13163b.m16930a(iX5WebViewBase);
        this.f13162a.onPageStarted(this.f13163b, str, bitmap);
    }

    @Override // com.tencent.smtt.export.external.proxy.ProxyWebViewClient, com.tencent.smtt.export.external.interfaces.IX5WebViewClient
    public void onReceivedError(IX5WebViewBase iX5WebViewBase, WebResourceRequest webResourceRequest, WebResourceError webResourceError) {
        this.f13163b.m16930a(iX5WebViewBase);
        this.f13162a.onReceivedError(this.f13163b, webResourceRequest, webResourceError);
    }

    @Override // com.tencent.smtt.export.external.proxy.ProxyWebViewClient, com.tencent.smtt.export.external.interfaces.IX5WebViewClient
    public void onReceivedError(IX5WebViewBase iX5WebViewBase, int i, String str, String str2) {
        if (i < -15) {
            if (i == -17) {
                i = -1;
            } else {
                return;
            }
        }
        this.f13163b.m16930a(iX5WebViewBase);
        this.f13162a.onReceivedError(this.f13163b, i, str, str2);
    }

    @Override // com.tencent.smtt.export.external.proxy.ProxyWebViewClient, com.tencent.smtt.export.external.interfaces.IX5WebViewClient
    public void onReceivedHttpError(IX5WebViewBase iX5WebViewBase, WebResourceRequest webResourceRequest, WebResourceResponse webResourceResponse) {
        this.f13163b.m16930a(iX5WebViewBase);
        this.f13162a.onReceivedHttpError(this.f13163b, webResourceRequest, webResourceResponse);
    }

    @Override // com.tencent.smtt.export.external.proxy.ProxyWebViewClient, com.tencent.smtt.export.external.interfaces.IX5WebViewClient
    public void onReceivedHttpAuthRequest(IX5WebViewBase iX5WebViewBase, HttpAuthHandler httpAuthHandler, String str, String str2) {
        this.f13163b.m16930a(iX5WebViewBase);
        this.f13162a.onReceivedHttpAuthRequest(this.f13163b, httpAuthHandler, str, str2);
    }

    @Override // com.tencent.smtt.export.external.proxy.ProxyWebViewClient, com.tencent.smtt.export.external.interfaces.IX5WebViewClient
    public void onReceivedSslError(IX5WebViewBase iX5WebViewBase, SslErrorHandler sslErrorHandler, SslError sslError) {
        this.f13163b.m16930a(iX5WebViewBase);
        this.f13162a.onReceivedSslError(this.f13163b, sslErrorHandler, sslError);
    }

    @Override // com.tencent.smtt.export.external.proxy.ProxyWebViewClient, com.tencent.smtt.export.external.interfaces.IX5WebViewClient
    public void onReceivedClientCertRequest(IX5WebViewBase iX5WebViewBase, ClientCertRequest clientCertRequest) {
        this.f13163b.m16930a(iX5WebViewBase);
        this.f13162a.onReceivedClientCertRequest(this.f13163b, clientCertRequest);
    }

    @Override // com.tencent.smtt.export.external.proxy.ProxyWebViewClient, com.tencent.smtt.export.external.interfaces.IX5WebViewClient
    public void onScaleChanged(IX5WebViewBase iX5WebViewBase, float f, float f2) {
        this.f13163b.m16930a(iX5WebViewBase);
        this.f13162a.onScaleChanged(this.f13163b, f, f2);
    }

    @Override // com.tencent.smtt.export.external.proxy.ProxyWebViewClient, com.tencent.smtt.export.external.interfaces.IX5WebViewClient
    public void onUnhandledKeyEvent(IX5WebViewBase iX5WebViewBase, KeyEvent keyEvent) {
        this.f13163b.m16930a(iX5WebViewBase);
        this.f13162a.onUnhandledKeyEvent(this.f13163b, keyEvent);
    }

    @Override // com.tencent.smtt.export.external.proxy.ProxyWebViewClient, com.tencent.smtt.export.external.interfaces.IX5WebViewClient
    public boolean shouldOverrideKeyEvent(IX5WebViewBase iX5WebViewBase, KeyEvent keyEvent) {
        this.f13163b.m16930a(iX5WebViewBase);
        return this.f13162a.shouldOverrideKeyEvent(this.f13163b, keyEvent);
    }

    /* renamed from: a */
    public void m16813a(String str) {
        Intent intent = new Intent("android.intent.action.DIAL", Uri.parse(str));
        intent.addFlags(268435456);
        try {
            if (this.f13163b.getContext() != null) {
                this.f13163b.getContext().startActivity(intent);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // com.tencent.smtt.export.external.proxy.ProxyWebViewClient, com.tencent.smtt.export.external.interfaces.IX5WebViewClient
    public boolean shouldOverrideUrlLoading(IX5WebViewBase iX5WebViewBase, String str) {
        if (str == null || this.f13163b.showDebugView(str)) {
            return true;
        }
        this.f13163b.m16930a(iX5WebViewBase);
        boolean shouldOverrideUrlLoading = this.f13162a.shouldOverrideUrlLoading(this.f13163b, str);
        if (!shouldOverrideUrlLoading) {
            if (str.startsWith("wtai://wp/mc;")) {
                this.f13163b.getContext().startActivity(new Intent("android.intent.action.VIEW", Uri.parse(WebView.SCHEME_TEL + str.substring(13))));
                return true;
            } else if (str.startsWith(WebView.SCHEME_TEL)) {
                m16813a(str);
                return true;
            }
        }
        return shouldOverrideUrlLoading;
    }

    @Override // com.tencent.smtt.export.external.proxy.ProxyWebViewClient, com.tencent.smtt.export.external.interfaces.IX5WebViewClient
    public void onTooManyRedirects(IX5WebViewBase iX5WebViewBase, Message message, Message message2) {
        this.f13163b.m16930a(iX5WebViewBase);
        this.f13162a.onTooManyRedirects(this.f13163b, message, message2);
    }

    @Override // com.tencent.smtt.export.external.proxy.ProxyWebViewClient, com.tencent.smtt.export.external.interfaces.IX5WebViewClient
    public boolean shouldOverrideUrlLoading(IX5WebViewBase iX5WebViewBase, WebResourceRequest webResourceRequest) {
        String uri = (webResourceRequest == null || webResourceRequest.getUrl() == null) ? null : webResourceRequest.getUrl().toString();
        if (uri == null || this.f13163b.showDebugView(uri)) {
            return true;
        }
        this.f13163b.m16930a(iX5WebViewBase);
        boolean shouldOverrideUrlLoading = this.f13162a.shouldOverrideUrlLoading(this.f13163b, webResourceRequest);
        if (!shouldOverrideUrlLoading) {
            if (uri.startsWith("wtai://wp/mc;")) {
                this.f13163b.getContext().startActivity(new Intent("android.intent.action.VIEW", Uri.parse(WebView.SCHEME_TEL + uri.substring(13))));
                return true;
            } else if (uri.startsWith(WebView.SCHEME_TEL)) {
                m16813a(uri);
                return true;
            }
        }
        return shouldOverrideUrlLoading;
    }

    @Override // com.tencent.smtt.export.external.proxy.ProxyWebViewClient, com.tencent.smtt.export.external.interfaces.IX5WebViewClient
    public WebResourceResponse shouldInterceptRequest(IX5WebViewBase iX5WebViewBase, String str) {
        this.f13163b.m16930a(iX5WebViewBase);
        return this.f13162a.shouldInterceptRequest(this.f13163b, str);
    }

    @Override // com.tencent.smtt.export.external.proxy.ProxyWebViewClient, com.tencent.smtt.export.external.interfaces.IX5WebViewClient
    public WebResourceResponse shouldInterceptRequest(IX5WebViewBase iX5WebViewBase, WebResourceRequest webResourceRequest) {
        this.f13163b.m16930a(iX5WebViewBase);
        return this.f13162a.shouldInterceptRequest(this.f13163b, webResourceRequest);
    }

    @Override // com.tencent.smtt.export.external.proxy.ProxyWebViewClient, com.tencent.smtt.export.external.interfaces.IX5WebViewClient
    public WebResourceResponse shouldInterceptRequest(IX5WebViewBase iX5WebViewBase, WebResourceRequest webResourceRequest, Bundle bundle) {
        this.f13163b.m16930a(iX5WebViewBase);
        return this.f13162a.shouldInterceptRequest(this.f13163b, webResourceRequest, bundle);
    }

    @Override // com.tencent.smtt.export.external.proxy.ProxyWebViewClient, com.tencent.smtt.export.external.interfaces.IX5WebViewClient
    public void onReceivedLoginRequest(IX5WebViewBase iX5WebViewBase, String str, String str2, String str3) {
        this.f13163b.m16930a(iX5WebViewBase);
        this.f13162a.onReceivedLoginRequest(this.f13163b, str, str2, str3);
    }

    /* renamed from: a */
    public void m16815a(WebView webView, String str, Bitmap bitmap) {
        super.onPageStarted(this.f13163b.m16924c(), 0, 0, str, bitmap);
    }

    @Override // com.tencent.smtt.export.external.proxy.ProxyWebViewClient, com.tencent.smtt.export.external.interfaces.IX5WebViewClient
    public void onDetectedBlankScreen(IX5WebViewBase iX5WebViewBase, String str, int i) {
        this.f13163b.m16930a(iX5WebViewBase);
        this.f13162a.onDetectedBlankScreen(str, i);
    }

    @Override // com.tencent.smtt.export.external.proxy.ProxyWebViewClient, com.tencent.smtt.export.external.interfaces.IX5WebViewClient
    public void onPageFinished(IX5WebViewBase iX5WebViewBase, String str) {
        onPageFinished(iX5WebViewBase, 0, 0, str);
    }

    @Override // com.tencent.smtt.export.external.proxy.ProxyWebViewClient, com.tencent.smtt.export.external.interfaces.IX5WebViewClient
    public void onPageStarted(IX5WebViewBase iX5WebViewBase, String str, Bitmap bitmap) {
        onPageStarted(iX5WebViewBase, 0, 0, str, bitmap);
    }

    @Override // com.tencent.smtt.export.external.proxy.ProxyWebViewClient
    public void countPVContentCacheCallBack(String str) {
        this.f13163b.f13022a++;
    }

    @Override // com.tencent.smtt.export.external.proxy.ProxyWebViewClient, com.tencent.smtt.export.external.interfaces.IX5WebViewClient
    public void onPageCommitVisible(IX5WebViewBase iX5WebViewBase, String str) {
        this.f13163b.m16930a(iX5WebViewBase);
        this.f13162a.onPageCommitVisible(this.f13163b, str);
    }
}
