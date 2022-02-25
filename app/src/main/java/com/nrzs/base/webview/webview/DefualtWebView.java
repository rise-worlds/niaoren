package com.nrzs.base.webview.webview;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.net.http.SslError;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.webkit.DownloadListener;
import android.webkit.HttpAuthHandler;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.blankj.utilcode.util.LogUtils;
import com.tendcloud.tenddata.C2664ab;
import p110z1.IloadViewResult;
import p110z1.LoadViewHelper;
import p110z1.OnClickListenerWrapper;

/* loaded from: classes2.dex */
public abstract class DefualtWebView extends BaseWebView implements IloadViewResult {

    /* renamed from: a */
    public static long f10518a = 0;

    /* renamed from: f */
    private static final int f10519f = 1;

    /* renamed from: g */
    private static final int f10520g = 30000;

    /* renamed from: b */
    private LoadViewHelper f10521b;

    /* renamed from: c */
    private WebViewClient f10522c;

    /* renamed from: d */
    private boolean f10523d;

    /* renamed from: e */
    private Handler f10524e;

    /* renamed from: h */
    private String f10525h = "";

    @Override // com.nrzs.base.webview.webview.AbstractC1956b
    /* renamed from: f */
    public void mo18975f() {
    }

    public abstract LoadViewHelper.AbstractC3846a getLocalLoadHelper();

    /* renamed from: h */
    protected abstract void mo18592h();

    public DefualtWebView(Context context) {
        super(context);
    }

    public DefualtWebView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public DefualtWebView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    @Override // p110z1.IloadViewResult
    /* renamed from: k_ */
    public void mo11682k_() {
        this.f10521b.mo11682k_();
    }

    @Override // p110z1.IloadViewResult
    /* renamed from: l_ */
    public void mo11681l_() {
        this.f10521b.mo11681l_();
    }

    @Override // p110z1.IloadViewResult
    /* renamed from: m_ */
    public void mo11680m_() {
        this.f10521b.mo11680m_();
    }

    @Override // p110z1.IloadViewResult
    /* renamed from: n_ */
    public void mo11679n_() {
        this.f10521b.mo11679n_();
    }

    @Override // com.nrzs.base.webview.webview.AbstractC1956b
    /* renamed from: g */
    public void mo18974g() {
        C1955a.m18977a(getContext(), this);
        setHorizontalScrollBarEnabled(false);
        setVerticalScrollBarEnabled(false);
        setScrollBarStyle(0);
        setWebChromeClient(new WebChromeClient() { // from class: com.nrzs.base.webview.webview.DefualtWebView.1
            @Override // android.webkit.WebChromeClient
            public void onProgressChanged(WebView webView, int i) {
                super.onProgressChanged(webView, i);
                DefualtWebView.f10518a = System.currentTimeMillis();
            }
        });
    }

    @Override // com.nrzs.base.webview.webview.AbstractC1956b
    /* renamed from: i */
    public void mo18973i() {
        setWebViewClient(null);
        setDownloadListener(new C1954a());
    }

    @Override // android.webkit.WebView
    public void setWebViewClient(WebViewClient webViewClient) {
        this.f10522c = webViewClient;
        super.setWebViewClient(new WebViewClient() { // from class: com.nrzs.base.webview.webview.DefualtWebView.2
            @Override // android.webkit.WebViewClient
            public void onPageStarted(WebView webView, String str, Bitmap bitmap) {
                LogUtils.m23734c("LBSWebView", "onPageStarted:" + str);
                if (DefualtWebView.this.f10522c != null) {
                    DefualtWebView.this.f10522c.onPageStarted(webView, str, bitmap);
                }
                DefualtWebView.this.f10523d = false;
                DefualtWebView.this.m18978j();
                if (DefualtWebView.this.f10524e != null && !str.equals(DefualtWebView.this.getNonErrorUrl())) {
                    DefualtWebView.this.f10524e.sendEmptyMessageDelayed(1, C2664ab.f13498O);
                }
                DefualtWebView.f10518a = System.currentTimeMillis();
                DefualtWebView.this.mo11682k_();
            }

            @Override // android.webkit.WebViewClient
            public void onPageFinished(WebView webView, String str) {
                LogUtils.m23734c("LBSWebView", "onPageFinished:" + str);
                if (!DefualtWebView.this.getSettings().getLoadsImagesAutomatically()) {
                    DefualtWebView.this.getSettings().setLoadsImagesAutomatically(true);
                }
                if (DefualtWebView.this.f10522c != null) {
                    DefualtWebView.this.f10522c.onPageFinished(webView, str);
                }
                if (!DefualtWebView.this.f10523d) {
                    DefualtWebView.this.mo11679n_();
                }
                DefualtWebView.this.m18978j();
            }

            @Override // android.webkit.WebViewClient
            public void onReceivedError(WebView webView, int i, String str, String str2) {
                LogUtils.m23734c("LBSWebView", "onReceivedError:" + str2);
                if (DefualtWebView.this.f10522c != null) {
                    DefualtWebView.this.f10522c.onReceivedError(webView, i, str, str2);
                }
                if (OnClickListenerWrapper.m11697a(DefualtWebView.this.getContext())) {
                    DefualtWebView.this.mo11680m_();
                } else {
                    DefualtWebView.this.mo11681l_();
                }
                DefualtWebView.this.m18978j();
                DefualtWebView.this.f10523d = true;
            }

            @Override // android.webkit.WebViewClient
            public boolean shouldOverrideUrlLoading(WebView webView, String str) {
                LogUtils.m23734c("LBSWebView", "shouldOverrideUrlLoading:" + str);
                if (DefualtWebView.this.f10522c != null) {
                    DefualtWebView.this.f10522c.shouldOverrideUrlLoading(webView, str);
                }
                if (str.startsWith("http:") || str.startsWith("https:")) {
                    webView.loadUrl(str);
                    return false;
                }
                try {
                    DefualtWebView.this.getContext().startActivity(new Intent("android.intent.action.VIEW", Uri.parse(str)));
                } catch (Exception unused) {
                }
                return true;
            }

            @Override // android.webkit.WebViewClient
            public void onLoadResource(WebView webView, String str) {
                LogUtils.m23734c("LBSWebView", "onLoadResource:" + str);
                if (DefualtWebView.this.f10522c != null) {
                    LogUtils.m23734c("LBSWebView", "2222 - onLoadResource:" + str);
                    DefualtWebView.this.f10522c.onLoadResource(webView, str);
                }
                if (!str.contains("www.google-analytics.com") && !str.contains("twca_logo.png")) {
                    super.onLoadResource(webView, str);
                }
            }

            @Override // android.webkit.WebViewClient
            public void onFormResubmission(WebView webView, Message message, Message message2) {
                LogUtils.m23734c("LBSWebView", "onFormResubmission:");
                super.onFormResubmission(webView, message, message2);
                if (DefualtWebView.this.f10522c != null) {
                    DefualtWebView.this.f10522c.onFormResubmission(webView, message, message2);
                }
            }

            @Override // android.webkit.WebViewClient
            public void doUpdateVisitedHistory(WebView webView, String str, boolean z) {
                LogUtils.m23734c("LBSWebView", "doUpdateVisitedHistory:" + str);
                super.doUpdateVisitedHistory(webView, str, z);
                if (DefualtWebView.this.f10522c != null) {
                    DefualtWebView.this.f10522c.doUpdateVisitedHistory(webView, str, z);
                }
            }

            @Override // android.webkit.WebViewClient
            public void onReceivedSslError(WebView webView, SslErrorHandler sslErrorHandler, SslError sslError) {
                LogUtils.m23734c("LBSWebView", "onReceivedSslError:");
                sslErrorHandler.proceed();
                if (DefualtWebView.this.f10522c != null) {
                    DefualtWebView.this.f10522c.onReceivedSslError(webView, sslErrorHandler, sslError);
                }
            }

            @Override // android.webkit.WebViewClient
            public void onReceivedHttpAuthRequest(WebView webView, HttpAuthHandler httpAuthHandler, String str, String str2) {
                super.onReceivedHttpAuthRequest(webView, httpAuthHandler, str, str2);
                LogUtils.m23734c("LBSWebView", "onReceivedHttpAuthRequest:");
                if (DefualtWebView.this.f10522c != null) {
                    DefualtWebView.this.f10522c.onReceivedHttpAuthRequest(webView, httpAuthHandler, str, str2);
                }
            }

            @Override // android.webkit.WebViewClient
            public boolean shouldOverrideKeyEvent(WebView webView, KeyEvent keyEvent) {
                LogUtils.m23734c("LBSWebView", "shouldOverrideKeyEvent:");
                return DefualtWebView.this.f10522c != null ? DefualtWebView.this.f10522c.shouldOverrideKeyEvent(webView, keyEvent) : super.shouldOverrideKeyEvent(webView, keyEvent);
            }

            @Override // android.webkit.WebViewClient
            public void onScaleChanged(WebView webView, float f, float f2) {
                super.onScaleChanged(webView, f, f2);
                LogUtils.m23734c("LBSWebView", "onScaleChanged:");
                if (DefualtWebView.this.f10522c != null) {
                    DefualtWebView.this.f10522c.onScaleChanged(webView, f, f2);
                }
            }

            @Override // android.webkit.WebViewClient
            public void onReceivedLoginRequest(WebView webView, String str, String str2, String str3) {
                super.onReceivedLoginRequest(webView, str, str2, str3);
                LogUtils.m23734c("LBSWebView", "onReceivedLoginRequest:");
                if (DefualtWebView.this.f10522c != null) {
                    DefualtWebView.this.f10522c.onReceivedLoginRequest(webView, str, str2, str3);
                }
            }
        });
    }

    /* renamed from: a */
    public void m18982a(String str, String str2, LoadViewHelper.AbstractC3846a aVar) {
        this.f10525h = str2;
        if (aVar == null) {
            aVar = getLocalLoadHelper();
        }
        this.f10521b = new LoadViewHelper(aVar);
        loadUrl(str);
    }

    @Override // android.webkit.WebView, android.view.ViewGroup, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (!TextUtils.isEmpty(getNonErrorUrl())) {
            this.f10524e = getNonErrorHandler();
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        m18978j();
        this.f10524e = null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: j */
    public void m18978j() {
        Handler handler = this.f10524e;
        if (handler != null) {
            handler.removeMessages(1);
        }
    }

    public Handler getNonErrorHandler() {
        return new Handler() { // from class: com.nrzs.base.webview.webview.DefualtWebView.3
            @Override // android.os.Handler
            public void handleMessage(Message message) {
                super.handleMessage(message);
                DefualtWebView defualtWebView = DefualtWebView.this;
                defualtWebView.loadUrl(defualtWebView.getNonErrorUrl());
            }
        };
    }

    public String getNonErrorUrl() {
        return this.f10525h;
    }

    /* renamed from: com.nrzs.base.webview.webview.DefualtWebView$a */
    /* loaded from: classes2.dex */
    private class C1954a implements DownloadListener {
        private C1954a() {
        }

        @Override // android.webkit.DownloadListener
        public void onDownloadStart(String str, String str2, String str3, String str4, long j) {
            DefualtWebView.this.getContext().startActivity(new Intent("android.intent.action.VIEW", Uri.parse(str)));
        }
    }
}
