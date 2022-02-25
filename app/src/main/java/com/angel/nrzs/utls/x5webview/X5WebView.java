package com.angel.nrzs.utls.x5webview;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.View;
import com.angel.nrzs.utls.webview.DefualtWebView;
import com.blankj.utilcode.util.LogUtils;
import com.tencent.smtt.export.external.interfaces.HttpAuthHandler;
import com.tencent.smtt.export.external.interfaces.SslError;
import com.tencent.smtt.export.external.interfaces.SslErrorHandler;
import com.tencent.smtt.sdk.DownloadListener;
import com.tencent.smtt.sdk.WebChromeClient;
import com.tencent.smtt.sdk.WebView;
import com.tencent.smtt.sdk.WebViewClient;
import com.tendcloud.tenddata.C2664ab;
import p110z1.IloadViewResult;
import p110z1.LoadViewHelper;
import p110z1.LoadstatueViewFactory;
import p110z1.LocalLoadHelper;
import p110z1.OnClickListenerWrapper;

/* loaded from: classes.dex */
public abstract class X5WebView extends BaseX5WebView implements IloadViewResult {

    /* renamed from: b */
    public static long f5660b = 0;

    /* renamed from: h */
    private static final int f5661h = 1;

    /* renamed from: i */
    private static final int f5662i = 30000;

    /* renamed from: d */
    private LoadViewHelper f5664d;

    /* renamed from: e */
    private WebViewClient f5665e;

    /* renamed from: f */
    private boolean f5666f;

    /* renamed from: g */
    private Handler f5667g;

    /* renamed from: c */
    private WebViewClient f5663c = new WebViewClient() { // from class: com.angel.nrzs.utls.x5webview.X5WebView.1
        @Override // com.tencent.smtt.sdk.WebViewClient
        public boolean shouldOverrideUrlLoading(WebView webView, String str) {
            webView.loadUrl(str);
            return true;
        }
    };

    /* renamed from: j */
    private String f5668j = "";

    public abstract LoadViewHelper.AbstractC3846a getLocalLoadHelper();

    /* renamed from: h */
    protected abstract void mo24841h();

    @Override // com.angel.nrzs.utls.webview.IInitView
    /* renamed from: i_ */
    public void mo24839i_() {
    }

    public X5WebView(Context context) {
        super(context);
    }

    public X5WebView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    @SuppressLint({"SetJavaScriptEnabled"})
    public X5WebView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        getView().setClickable(true);
    }

    @Override // com.angel.nrzs.utls.webview.IInitView
    /* renamed from: j_ */
    public void mo24837j_() {
        X5WebViewSetting.m24834a(getContext(), this);
        setHorizontalScrollBarEnabled(false);
        setVerticalScrollBarEnabled(false);
        setScrollBarStyle(0);
        setWebChromeClient(new WebChromeClient() { // from class: com.angel.nrzs.utls.x5webview.X5WebView.2
            @Override // com.tencent.smtt.sdk.WebChromeClient
            public void onProgressChanged(WebView webView, int i) {
                super.onProgressChanged(webView, i);
                DefualtWebView.f5646a = System.currentTimeMillis();
            }
        });
    }

    @Override // com.angel.nrzs.utls.webview.IInitView
    /* renamed from: i */
    public void mo24840i() {
        setWebViewClient(null);
        setDownloadListener(new C0851a());
    }

    @Override // p110z1.IloadViewResult
    /* renamed from: k_ */
    public void mo11682k_() {
        this.f5664d.mo11682k_();
    }

    @Override // p110z1.IloadViewResult
    /* renamed from: l_ */
    public void mo11681l_() {
        this.f5664d.mo11681l_();
    }

    @Override // p110z1.IloadViewResult
    /* renamed from: m_ */
    public void mo11680m_() {
        this.f5664d.mo11680m_();
    }

    @Override // p110z1.IloadViewResult
    /* renamed from: n_ */
    public void mo11679n_() {
        this.f5664d.mo11679n_();
    }

    @Override // com.tencent.smtt.sdk.WebView
    public void setWebViewClient(WebViewClient webViewClient) {
        this.f5665e = webViewClient;
        super.setWebViewClient(new WebViewClient() { // from class: com.angel.nrzs.utls.x5webview.X5WebView.3
            @Override // com.tencent.smtt.sdk.WebViewClient
            public void onPageStarted(WebView webView, String str, Bitmap bitmap) {
                LogUtils.m23734c("LBSWebView", "onPageStarted:" + str);
                if (X5WebView.this.f5665e != null) {
                    X5WebView.this.f5665e.onPageStarted(webView, str, bitmap);
                }
                X5WebView.this.f5666f = false;
                X5WebView.this.m24838j();
                if (X5WebView.this.f5667g != null && !str.equals(X5WebView.this.getNonErrorUrl())) {
                    X5WebView.this.f5667g.sendEmptyMessageDelayed(1, C2664ab.f13498O);
                }
                X5WebView.f5660b = System.currentTimeMillis();
                if (str.contains("opay")) {
                    X5WebView x5WebView = X5WebView.this;
                    Context context = x5WebView.getContext();
                    X5WebView x5WebView2 = X5WebView.this;
                    x5WebView.f5664d = new LoadViewHelper(new LocalLoadHelper(context, x5WebView2, LoadstatueViewFactory.m11675b(x5WebView2.getContext(), X5WebView.this), null, null, new View.OnClickListener() { // from class: com.angel.nrzs.utls.x5webview.X5WebView.3.1
                        @Override // android.view.View.OnClickListener
                        public void onClick(View view) {
                            X5WebView.this.reload();
                        }
                    }));
                    X5WebView.this.mo11682k_();
                    return;
                }
                X5WebView.this.mo11682k_();
            }

            @Override // com.tencent.smtt.sdk.WebViewClient
            public void onPageFinished(WebView webView, String str) {
                LogUtils.m23734c("LBSWebView", "onPageFinished:" + str);
                if (!X5WebView.this.getSettings().getLoadsImagesAutomatically()) {
                    X5WebView.this.getSettings().setLoadsImagesAutomatically(true);
                }
                if (X5WebView.this.f5665e != null) {
                    X5WebView.this.f5665e.onPageFinished(webView, str);
                }
                if (!X5WebView.this.f5666f) {
                    X5WebView.this.mo11679n_();
                }
                X5WebView.this.m24838j();
            }

            @Override // com.tencent.smtt.sdk.WebViewClient
            public void onReceivedError(WebView webView, int i, String str, String str2) {
                LogUtils.m23734c("LBSWebView", "onReceivedError:" + str2);
                if (X5WebView.this.f5665e != null) {
                    X5WebView.this.f5665e.onReceivedError(webView, i, str, str2);
                }
                if (OnClickListenerWrapper.m11697a(X5WebView.this.getContext())) {
                    X5WebView.this.mo11680m_();
                } else {
                    X5WebView.this.mo11681l_();
                }
                X5WebView.this.m24838j();
                X5WebView.this.f5666f = true;
            }

            @Override // com.tencent.smtt.sdk.WebViewClient
            public boolean shouldOverrideUrlLoading(WebView webView, String str) {
                LogUtils.m23734c("LBSWebView", "shouldOverrideUrlLoading:" + str);
                if (X5WebView.this.f5665e != null) {
                    X5WebView.this.f5665e.shouldOverrideUrlLoading(webView, str);
                }
                if (str.startsWith("http:") || str.startsWith("https:")) {
                    webView.loadUrl(str);
                    return true;
                }
                try {
                    X5WebView.this.getContext().startActivity(new Intent("android.intent.action.VIEW", Uri.parse(str)));
                } catch (Exception unused) {
                }
                return true;
            }

            @Override // com.tencent.smtt.sdk.WebViewClient
            public void onLoadResource(WebView webView, String str) {
                super.onLoadResource(webView, str);
            }

            @Override // com.tencent.smtt.sdk.WebViewClient
            public void onFormResubmission(WebView webView, Message message, Message message2) {
                super.onFormResubmission(webView, message, message2);
                if (X5WebView.this.f5665e != null) {
                    X5WebView.this.f5665e.onFormResubmission(webView, message, message2);
                }
            }

            @Override // com.tencent.smtt.sdk.WebViewClient
            public void doUpdateVisitedHistory(WebView webView, String str, boolean z) {
                super.doUpdateVisitedHistory(webView, str, z);
            }

            @Override // com.tencent.smtt.sdk.WebViewClient
            public void onReceivedSslError(WebView webView, SslErrorHandler sslErrorHandler, SslError sslError) {
                sslErrorHandler.proceed();
                if (X5WebView.this.f5665e != null) {
                    X5WebView.this.f5665e.onReceivedSslError(webView, sslErrorHandler, sslError);
                }
            }

            @Override // com.tencent.smtt.sdk.WebViewClient
            public void onReceivedHttpAuthRequest(WebView webView, HttpAuthHandler httpAuthHandler, String str, String str2) {
                super.onReceivedHttpAuthRequest(webView, httpAuthHandler, str, str2);
                LogUtils.m23734c("LBSWebView", "onReceivedHttpAuthRequest:");
                if (X5WebView.this.f5665e != null) {
                    X5WebView.this.f5665e.onReceivedHttpAuthRequest(webView, httpAuthHandler, str, str2);
                }
            }

            @Override // com.tencent.smtt.sdk.WebViewClient
            public boolean shouldOverrideKeyEvent(WebView webView, KeyEvent keyEvent) {
                LogUtils.m23734c("LBSWebView", "shouldOverrideKeyEvent:");
                if (X5WebView.this.f5665e != null) {
                    return X5WebView.this.f5665e.shouldOverrideKeyEvent(webView, keyEvent);
                }
                return super.shouldOverrideKeyEvent(webView, keyEvent);
            }

            @Override // com.tencent.smtt.sdk.WebViewClient
            public void onScaleChanged(WebView webView, float f, float f2) {
                super.onScaleChanged(webView, f, f2);
            }

            @Override // com.tencent.smtt.sdk.WebViewClient
            public void onReceivedLoginRequest(WebView webView, String str, String str2, String str3) {
                super.onReceivedLoginRequest(webView, str, str2, str3);
            }
        });
    }

    /* renamed from: a */
    public void m24845a(String str, String str2, LoadViewHelper.AbstractC3846a aVar) {
        this.f5668j = str2;
        if (aVar == null) {
            aVar = getLocalLoadHelper();
        }
        this.f5664d = new LoadViewHelper(aVar);
        loadUrl(str);
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (!TextUtils.isEmpty(getNonErrorUrl())) {
            this.f5667g = getNonErrorHandler();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.tencent.smtt.sdk.WebView, android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        m24838j();
        this.f5667g = null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: j */
    public void m24838j() {
        Handler handler = this.f5667g;
        if (handler != null) {
            handler.removeMessages(1);
        }
    }

    public Handler getNonErrorHandler() {
        return new Handler() { // from class: com.angel.nrzs.utls.x5webview.X5WebView.4
            @Override // android.os.Handler
            public void handleMessage(Message message) {
                super.handleMessage(message);
                X5WebView x5WebView = X5WebView.this;
                x5WebView.loadUrl(x5WebView.getNonErrorUrl());
            }
        };
    }

    public String getNonErrorUrl() {
        return this.f5668j;
    }

    /* renamed from: com.angel.nrzs.utls.x5webview.X5WebView$a */
    /* loaded from: classes.dex */
    private class C0851a implements DownloadListener {
        private C0851a() {
        }

        @Override // com.tencent.smtt.sdk.DownloadListener
        public void onDownloadStart(String str, String str2, String str3, String str4, long j) {
            X5WebView.this.getContext().startActivity(new Intent("android.intent.action.VIEW", Uri.parse(str)));
        }
    }
}
