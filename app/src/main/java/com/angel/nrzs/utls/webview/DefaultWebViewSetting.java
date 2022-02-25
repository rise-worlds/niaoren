package com.angel.nrzs.utls.webview;

import android.content.Context;
import android.os.Build;
import android.webkit.WebSettings;
import android.webkit.WebView;

/* renamed from: com.angel.nrzs.utls.webview.a */
/* loaded from: classes.dex */
public class DefaultWebViewSetting {
    /* renamed from: a */
    public static void m24851a(Context context, WebView webView) {
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
