package com.angel.nrzs.utls.x5webview;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;
import com.tencent.smtt.sdk.WebSettings;
import com.tencent.smtt.sdk.WebView;
import com.tencent.smtt.sdk.WebViewClient;
import p110z1.NRZSJsCallAndroid;
import p110z1.cjm;

/* loaded from: classes.dex */
public class X5WebViewTest extends WebView {

    /* renamed from: b */
    TextView f5675b;

    /* renamed from: c */
    private WebViewClient f5676c = new WebViewClient() { // from class: com.angel.nrzs.utls.x5webview.X5WebViewTest.1
        @Override // com.tencent.smtt.sdk.WebViewClient
        public boolean shouldOverrideUrlLoading(WebView webView, String str) {
            webView.loadUrl(str);
            return true;
        }
    };

    @SuppressLint({"SetJavaScriptEnabled"})
    public X5WebViewTest(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        setWebViewClient(this.f5676c);
        m24836h();
        getView().setClickable(true);
        addJavascriptInterface(new NRZSJsCallAndroid(getContext()), NRZSJsCallAndroid.f21633a);
    }

    /* renamed from: h */
    private void m24836h() {
        WebSettings settings = getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setJavaScriptCanOpenWindowsAutomatically(true);
        settings.setAllowFileAccess(true);
        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS);
        settings.setSupportZoom(true);
        settings.setBuiltInZoomControls(true);
        settings.setUseWideViewPort(true);
        settings.setSupportMultipleWindows(true);
        settings.setAppCacheEnabled(true);
        settings.setDomStorageEnabled(true);
        settings.setGeolocationEnabled(true);
        settings.setAppCacheMaxSize(cjm.f20759b);
        settings.setPluginState(WebSettings.PluginState.ON_DEMAND);
        settings.setCacheMode(2);
    }

    public X5WebViewTest(Context context) {
        super(context);
        setBackgroundColor(85621);
    }
}
