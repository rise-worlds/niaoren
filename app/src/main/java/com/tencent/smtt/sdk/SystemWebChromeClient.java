package com.tencent.smtt.sdk;

import android.annotation.TargetApi;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Message;
import android.view.View;
import android.webkit.ConsoleMessage;
import android.webkit.GeolocationPermissions;
import android.webkit.JsPromptResult;
import android.webkit.JsResult;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebStorage;
import android.webkit.WebView;
import com.tencent.smtt.export.external.interfaces.ConsoleMessage;
import com.tencent.smtt.export.external.interfaces.GeolocationPermissionsCallback;
import com.tencent.smtt.export.external.interfaces.IX5WebChromeClient;
import com.tencent.smtt.sdk.WebChromeClient;
import com.tencent.smtt.sdk.WebStorage;
import com.tencent.smtt.sdk.WebView;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public class SystemWebChromeClient extends WebChromeClient {

    /* renamed from: a */
    private WebView f12832a;

    /* renamed from: b */
    private WebChromeClient f12833b;

    public void setupAutoFill(Message message) {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public SystemWebChromeClient(WebView webView, WebChromeClient webChromeClient) {
        this.f12832a = webView;
        this.f12833b = webChromeClient;
    }

    @Override // android.webkit.WebChromeClient
    @TargetApi(7)
    public Bitmap getDefaultVideoPoster() {
        Bitmap defaultVideoPoster = this.f12833b.getDefaultVideoPoster();
        if (defaultVideoPoster == null) {
            try {
                if (Build.VERSION.SDK_INT >= 24) {
                    return BitmapFactory.decodeResource(this.f12832a.getResources(), 17301540);
                }
            } catch (Exception unused) {
            }
        }
        return defaultVideoPoster;
    }

    @Override // android.webkit.WebChromeClient
    @TargetApi(7)
    public View getVideoLoadingProgressView() {
        return this.f12833b.getVideoLoadingProgressView();
    }

    @Override // android.webkit.WebChromeClient
    public void getVisitedHistory(final ValueCallback<String[]> valueCallback) {
        this.f12833b.getVisitedHistory(new ValueCallback<String[]>() { // from class: com.tencent.smtt.sdk.SystemWebChromeClient.1
            /* renamed from: a */
            public void onReceiveValue(String[] strArr) {
                valueCallback.onReceiveValue(strArr);
            }
        });
    }

    @Override // android.webkit.WebChromeClient
    public void onCloseWindow(WebView webView) {
        this.f12832a.m16931a(webView);
        this.f12833b.onCloseWindow(this.f12832a);
    }

    @Override // android.webkit.WebChromeClient
    public boolean onConsoleMessage(ConsoleMessage consoleMessage) {
        return this.f12833b.onConsoleMessage(new C2569a(consoleMessage));
    }

    @Override // android.webkit.WebChromeClient
    public void onConsoleMessage(String str, int i, String str2) {
        this.f12833b.onConsoleMessage(new C2569a(str, str2, i));
    }

    @Override // android.webkit.WebChromeClient
    public boolean onCreateWindow(WebView webView, boolean z, boolean z2, final Message message) {
        WebView webView2 = this.f12832a;
        webView2.getClass();
        final WebView.WebViewTransport webViewTransport = new WebView.WebViewTransport();
        Message obtain = Message.obtain(message.getTarget(), new Runnable() { // from class: com.tencent.smtt.sdk.SystemWebChromeClient.2
            @Override // java.lang.Runnable
            public void run() {
                WebView webView3 = webViewTransport.getWebView();
                if (webView3 != null) {
                    ((WebView.WebViewTransport) message.obj).setWebView(webView3.m16927b());
                }
                message.sendToTarget();
            }
        });
        obtain.obj = webViewTransport;
        return this.f12833b.onCreateWindow(this.f12832a, z, z2, obtain);
    }

    @Override // android.webkit.WebChromeClient
    @TargetApi(5)
    @Deprecated
    public void onExceededDatabaseQuota(String str, String str2, long j, long j2, long j3, WebStorage.QuotaUpdater quotaUpdater) {
        this.f12833b.onExceededDatabaseQuota(str, str2, j, j2, j3, new C2574f(quotaUpdater));
    }

    @Override // android.webkit.WebChromeClient
    @TargetApi(5)
    public void onGeolocationPermissionsHidePrompt() {
        this.f12833b.onGeolocationPermissionsHidePrompt();
    }

    @Override // android.webkit.WebChromeClient
    @TargetApi(5)
    public void onGeolocationPermissionsShowPrompt(String str, GeolocationPermissions.Callback callback) {
        this.f12833b.onGeolocationPermissionsShowPrompt(str, new C2571c(callback));
    }

    @Override // android.webkit.WebChromeClient
    @TargetApi(7)
    public void onHideCustomView() {
        this.f12833b.onHideCustomView();
    }

    @Override // android.webkit.WebChromeClient
    public boolean onJsAlert(android.webkit.WebView webView, String str, String str2, JsResult jsResult) {
        this.f12832a.m16931a(webView);
        return this.f12833b.onJsAlert(this.f12832a, str, str2, new C2573e(jsResult));
    }

    @Override // android.webkit.WebChromeClient
    public boolean onJsBeforeUnload(android.webkit.WebView webView, String str, String str2, JsResult jsResult) {
        this.f12832a.m16931a(webView);
        return this.f12833b.onJsBeforeUnload(this.f12832a, str, str2, new C2573e(jsResult));
    }

    @Override // android.webkit.WebChromeClient
    public boolean onJsConfirm(android.webkit.WebView webView, String str, String str2, JsResult jsResult) {
        this.f12832a.m16931a(webView);
        return this.f12833b.onJsConfirm(this.f12832a, str, str2, new C2573e(jsResult));
    }

    @Override // android.webkit.WebChromeClient
    public boolean onJsPrompt(android.webkit.WebView webView, String str, String str2, String str3, JsPromptResult jsPromptResult) {
        this.f12832a.m16931a(webView);
        return this.f12833b.onJsPrompt(this.f12832a, str, str2, str3, new C2572d(jsPromptResult));
    }

    @Override // android.webkit.WebChromeClient
    @TargetApi(7)
    public boolean onJsTimeout() {
        return this.f12833b.onJsTimeout();
    }

    @Override // android.webkit.WebChromeClient
    public void onProgressChanged(android.webkit.WebView webView, int i) {
        this.f12832a.m16931a(webView);
        this.f12833b.onProgressChanged(this.f12832a, i);
    }

    @Override // android.webkit.WebChromeClient
    @TargetApi(7)
    @Deprecated
    public void onReachedMaxAppCacheSize(long j, long j2, WebStorage.QuotaUpdater quotaUpdater) {
        this.f12833b.onReachedMaxAppCacheSize(j, j2, new C2574f(quotaUpdater));
    }

    @Override // android.webkit.WebChromeClient
    public void onReceivedIcon(android.webkit.WebView webView, Bitmap bitmap) {
        this.f12832a.m16931a(webView);
        this.f12833b.onReceivedIcon(this.f12832a, bitmap);
    }

    @Override // android.webkit.WebChromeClient
    public void onReceivedTitle(android.webkit.WebView webView, String str) {
        this.f12832a.m16931a(webView);
        this.f12833b.onReceivedTitle(this.f12832a, str);
    }

    @Override // android.webkit.WebChromeClient
    @TargetApi(7)
    public void onReceivedTouchIconUrl(android.webkit.WebView webView, String str, boolean z) {
        this.f12832a.m16931a(webView);
        this.f12833b.onReceivedTouchIconUrl(this.f12832a, str, z);
    }

    @Override // android.webkit.WebChromeClient
    public void onRequestFocus(android.webkit.WebView webView) {
        this.f12832a.m16931a(webView);
        this.f12833b.onRequestFocus(this.f12832a);
    }

    @Override // android.webkit.WebChromeClient
    @TargetApi(7)
    public void onShowCustomView(View view, WebChromeClient.CustomViewCallback customViewCallback) {
        this.f12833b.onShowCustomView(view, new C2570b(customViewCallback));
    }

    @Override // android.webkit.WebChromeClient
    @TargetApi(14)
    @Deprecated
    public void onShowCustomView(View view, int i, WebChromeClient.CustomViewCallback customViewCallback) {
        this.f12833b.onShowCustomView(view, i, new C2570b(customViewCallback));
    }

    public void openFileChooser(ValueCallback<Uri> valueCallback) {
        openFileChooser(valueCallback, null, null);
    }

    public void openFileChooser(ValueCallback<Uri> valueCallback, String str) {
        openFileChooser(valueCallback, str, null);
    }

    public void openFileChooser(final ValueCallback<Uri> valueCallback, String str, String str2) {
        this.f12833b.openFileChooser(new ValueCallback<Uri>() { // from class: com.tencent.smtt.sdk.SystemWebChromeClient.3
            /* renamed from: a */
            public void onReceiveValue(Uri uri) {
                valueCallback.onReceiveValue(uri);
            }
        }, str, str2);
    }

    @Override // android.webkit.WebChromeClient
    public boolean onShowFileChooser(android.webkit.WebView webView, final ValueCallback<Uri[]> valueCallback, final WebChromeClient.FileChooserParams fileChooserParams) {
        ValueCallback<Uri[]> valueCallback2 = new ValueCallback<Uri[]>() { // from class: com.tencent.smtt.sdk.SystemWebChromeClient.4
            /* renamed from: a */
            public void onReceiveValue(Uri[] uriArr) {
                valueCallback.onReceiveValue(uriArr);
            }
        };
        WebChromeClient.FileChooserParams fileChooserParams2 = new WebChromeClient.FileChooserParams() { // from class: com.tencent.smtt.sdk.SystemWebChromeClient.5
            @Override // com.tencent.smtt.sdk.WebChromeClient.FileChooserParams
            public int getMode() {
                return fileChooserParams.getMode();
            }

            @Override // com.tencent.smtt.sdk.WebChromeClient.FileChooserParams
            public String[] getAcceptTypes() {
                return fileChooserParams.getAcceptTypes();
            }

            @Override // com.tencent.smtt.sdk.WebChromeClient.FileChooserParams
            public boolean isCaptureEnabled() {
                return fileChooserParams.isCaptureEnabled();
            }

            @Override // com.tencent.smtt.sdk.WebChromeClient.FileChooserParams
            public CharSequence getTitle() {
                return fileChooserParams.getTitle();
            }

            @Override // com.tencent.smtt.sdk.WebChromeClient.FileChooserParams
            public String getFilenameHint() {
                return fileChooserParams.getFilenameHint();
            }

            @Override // com.tencent.smtt.sdk.WebChromeClient.FileChooserParams
            public Intent createIntent() {
                return fileChooserParams.createIntent();
            }
        };
        this.f12832a.m16931a(webView);
        return this.f12833b.onShowFileChooser(this.f12832a, valueCallback2, fileChooserParams2);
    }

    /* renamed from: com.tencent.smtt.sdk.SystemWebChromeClient$e */
    /* loaded from: classes2.dex */
    private class C2573e implements com.tencent.smtt.export.external.interfaces.JsResult {

        /* renamed from: a */
        JsResult f12855a;

        C2573e(JsResult jsResult) {
            this.f12855a = jsResult;
        }

        @Override // com.tencent.smtt.export.external.interfaces.JsResult
        public void cancel() {
            this.f12855a.cancel();
        }

        @Override // com.tencent.smtt.export.external.interfaces.JsResult
        public void confirm() {
            this.f12855a.confirm();
        }
    }

    /* renamed from: com.tencent.smtt.sdk.SystemWebChromeClient$d */
    /* loaded from: classes2.dex */
    private class C2572d implements com.tencent.smtt.export.external.interfaces.JsPromptResult {

        /* renamed from: a */
        JsPromptResult f12853a;

        C2572d(JsPromptResult jsPromptResult) {
            this.f12853a = jsPromptResult;
        }

        @Override // com.tencent.smtt.export.external.interfaces.JsResult
        public void cancel() {
            this.f12853a.cancel();
        }

        @Override // com.tencent.smtt.export.external.interfaces.JsResult
        public void confirm() {
            this.f12853a.confirm();
        }

        @Override // com.tencent.smtt.export.external.interfaces.JsPromptResult
        public void confirm(String str) {
            this.f12853a.confirm(str);
        }
    }

    /* renamed from: com.tencent.smtt.sdk.SystemWebChromeClient$a */
    /* loaded from: classes2.dex */
    private static class C2569a implements com.tencent.smtt.export.external.interfaces.ConsoleMessage {

        /* renamed from: a */
        private ConsoleMessage.MessageLevel f12845a;

        /* renamed from: b */
        private String f12846b;

        /* renamed from: c */
        private String f12847c;

        /* renamed from: d */
        private int f12848d;

        C2569a(android.webkit.ConsoleMessage consoleMessage) {
            this.f12845a = ConsoleMessage.MessageLevel.valueOf(consoleMessage.messageLevel().name());
            this.f12846b = consoleMessage.message();
            this.f12847c = consoleMessage.sourceId();
            this.f12848d = consoleMessage.lineNumber();
        }

        C2569a(String str, String str2, int i) {
            this.f12845a = ConsoleMessage.MessageLevel.LOG;
            this.f12846b = str;
            this.f12847c = str2;
            this.f12848d = i;
        }

        @Override // com.tencent.smtt.export.external.interfaces.ConsoleMessage
        public ConsoleMessage.MessageLevel messageLevel() {
            return this.f12845a;
        }

        @Override // com.tencent.smtt.export.external.interfaces.ConsoleMessage
        public String message() {
            return this.f12846b;
        }

        @Override // com.tencent.smtt.export.external.interfaces.ConsoleMessage
        public String sourceId() {
            return this.f12847c;
        }

        @Override // com.tencent.smtt.export.external.interfaces.ConsoleMessage
        public int lineNumber() {
            return this.f12848d;
        }
    }

    /* renamed from: com.tencent.smtt.sdk.SystemWebChromeClient$f */
    /* loaded from: classes2.dex */
    class C2574f implements WebStorage.QuotaUpdater {

        /* renamed from: a */
        WebStorage.QuotaUpdater f12857a;

        C2574f(WebStorage.QuotaUpdater quotaUpdater) {
            this.f12857a = quotaUpdater;
        }

        @Override // com.tencent.smtt.sdk.WebStorage.QuotaUpdater
        public void updateQuota(long j) {
            this.f12857a.updateQuota(j);
        }
    }

    /* renamed from: com.tencent.smtt.sdk.SystemWebChromeClient$b */
    /* loaded from: classes2.dex */
    class C2570b implements IX5WebChromeClient.CustomViewCallback {

        /* renamed from: a */
        WebChromeClient.CustomViewCallback f12849a;

        C2570b(WebChromeClient.CustomViewCallback customViewCallback) {
            this.f12849a = customViewCallback;
        }

        @Override // com.tencent.smtt.export.external.interfaces.IX5WebChromeClient.CustomViewCallback
        public void onCustomViewHidden() {
            this.f12849a.onCustomViewHidden();
        }
    }

    /* renamed from: com.tencent.smtt.sdk.SystemWebChromeClient$c */
    /* loaded from: classes2.dex */
    class C2571c implements GeolocationPermissionsCallback {

        /* renamed from: a */
        GeolocationPermissions.Callback f12851a;

        C2571c(GeolocationPermissions.Callback callback) {
            this.f12851a = callback;
        }

        @Override // com.tencent.smtt.export.external.interfaces.GeolocationPermissionsCallback
        public void invoke(String str, boolean z, boolean z2) {
            this.f12851a.invoke(str, z, z2);
        }
    }
}
