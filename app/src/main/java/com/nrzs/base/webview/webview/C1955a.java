package com.nrzs.base.webview.webview;

import android.content.Context;
import android.os.Build;
import android.webkit.WebSettings;
import android.webkit.WebView;

/* compiled from: DefaultWebViewSetting.java */
/* renamed from: com.nrzs.base.webview.webview.a */
/* loaded from: classes2.dex */
public class C1955a {
    /* renamed from: a */
    public static void m18977a(Context context, WebView webView) {
        WebSettings settings = webView.getSettings();
        settings.setJavaScriptCanOpenWindowsAutomatically(true);
        settings.setJavaScriptEnabled(true);
        settings.setBlockNetworkImage(false);
        settings.setBlockNetworkLoads(false);
        settings.setAllowFileAccess(true);
        if (Build.VERSION.SDK_INT >= 21) {
            settings.setMixedContentMode(0);
        }
        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        settings.setUseWideViewPort(false);
        settings.setBuiltInZoomControls(false);
        settings.setSupportZoom(false);
        settings.setAppCacheEnabled(true);
        settings.setCacheMode(2);
        settings.setAppCacheMaxSize(10240L);
        settings.setDomStorageEnabled(true);
        settings.setDatabaseEnabled(true);
    }
}
