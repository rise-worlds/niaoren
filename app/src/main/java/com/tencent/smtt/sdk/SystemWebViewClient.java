package com.tencent.smtt.sdk;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.graphics.Bitmap;
import android.net.Uri;
import android.net.http.SslCertificate;
import android.net.http.SslError;
import android.os.Build;
import android.os.Message;
import android.view.KeyEvent;
import android.webkit.ClientCertRequest;
import android.webkit.HttpAuthHandler;
import android.webkit.SslErrorHandler;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.tencent.smtt.utils.ReflectionUtils;
import com.tencent.smtt.utils.TbsConfigFile;
import com.tencent.smtt.utils.TbsLog;
import java.io.InputStream;
import java.security.Principal;
import java.security.PrivateKey;
import java.security.cert.X509Certificate;
import java.util.Map;

/* JADX INFO: Access modifiers changed from: package-private */
@SuppressLint({"NewApi", "Override"})
/* loaded from: classes2.dex */
public class SystemWebViewClient extends WebViewClient {

    /* renamed from: c */
    private static String f12859c;

    /* renamed from: a */
    private WebViewClient f12860a;

    /* renamed from: b */
    private WebView f12861b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public SystemWebViewClient(WebView webView, WebViewClient webViewClient) {
        this.f12861b = webView;
        this.f12860a = webViewClient;
    }

    @Override // android.webkit.WebViewClient
    public void onLoadResource(WebView webView, String str) {
        this.f12861b.m16931a(webView);
        this.f12860a.onLoadResource(this.f12861b, str);
    }

    @Override // android.webkit.WebViewClient
    public boolean shouldOverrideUrlLoading(WebView webView, String str) {
        if (str == null || this.f12861b.showDebugView(str)) {
            return true;
        }
        this.f12861b.m16931a(webView);
        return this.f12860a.shouldOverrideUrlLoading(this.f12861b, str);
    }

    @Override // android.webkit.WebViewClient
    public boolean shouldOverrideUrlLoading(WebView webView, WebResourceRequest webResourceRequest) {
        boolean z;
        String uri = (webResourceRequest == null || webResourceRequest.getUrl() == null) ? null : webResourceRequest.getUrl().toString();
        if (uri == null || this.f12861b.showDebugView(uri)) {
            return true;
        }
        this.f12861b.m16931a(webView);
        if (Build.VERSION.SDK_INT >= 24) {
            Object a = ReflectionUtils.m16406a(webResourceRequest, "isRedirect");
            if (a instanceof Boolean) {
                z = ((Boolean) a).booleanValue();
                return this.f12860a.shouldOverrideUrlLoading(this.f12861b, new C2581e(webResourceRequest.getUrl().toString(), webResourceRequest.isForMainFrame(), z, webResourceRequest.hasGesture(), webResourceRequest.getMethod(), webResourceRequest.getRequestHeaders()));
            }
        }
        z = false;
        return this.f12860a.shouldOverrideUrlLoading(this.f12861b, new C2581e(webResourceRequest.getUrl().toString(), webResourceRequest.isForMainFrame(), z, webResourceRequest.hasGesture(), webResourceRequest.getMethod(), webResourceRequest.getRequestHeaders()));
    }

    @Override // android.webkit.WebViewClient
    @TargetApi(11)
    public WebResourceResponse shouldInterceptRequest(WebView webView, String str) {
        com.tencent.smtt.export.external.interfaces.WebResourceResponse shouldInterceptRequest;
        if (Build.VERSION.SDK_INT >= 11 && (shouldInterceptRequest = this.f12860a.shouldInterceptRequest(this.f12861b, str)) != null) {
            return new WebResourceResponse(shouldInterceptRequest.getMimeType(), shouldInterceptRequest.getEncoding(), shouldInterceptRequest.getData());
        }
        return null;
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x004e A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:16:0x004f  */
    @Override // android.webkit.WebViewClient
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public android.webkit.WebResourceResponse shouldInterceptRequest(android.webkit.WebView r10, android.webkit.WebResourceRequest r11) {
        /*
            r9 = this;
            int r10 = android.os.Build.VERSION.SDK_INT
            r0 = 0
            r1 = 21
            if (r10 >= r1) goto L_0x0008
            return r0
        L_0x0008:
            if (r11 != 0) goto L_0x000b
            return r0
        L_0x000b:
            r10 = 0
            int r1 = android.os.Build.VERSION.SDK_INT
            r2 = 24
            if (r1 < r2) goto L_0x0024
            java.lang.String r1 = "isRedirect"
            java.lang.Object r1 = com.tencent.smtt.utils.ReflectionUtils.m16406a(r11, r1)
            boolean r2 = r1 instanceof java.lang.Boolean
            if (r2 == 0) goto L_0x0024
            java.lang.Boolean r1 = (java.lang.Boolean) r1
            boolean r10 = r1.booleanValue()
            r5 = r10
            goto L_0x0025
        L_0x0024:
            r5 = 0
        L_0x0025:
            com.tencent.smtt.sdk.SystemWebViewClient$e r10 = new com.tencent.smtt.sdk.SystemWebViewClient$e
            android.net.Uri r1 = r11.getUrl()
            java.lang.String r3 = r1.toString()
            boolean r4 = r11.isForMainFrame()
            boolean r6 = r11.hasGesture()
            java.lang.String r7 = r11.getMethod()
            java.util.Map r8 = r11.getRequestHeaders()
            r1 = r10
            r2 = r9
            r1.<init>(r3, r4, r5, r6, r7, r8)
            com.tencent.smtt.sdk.WebViewClient r11 = r9.f12860a
            com.tencent.smtt.sdk.WebView r1 = r9.f12861b
            com.tencent.smtt.export.external.interfaces.WebResourceResponse r10 = r11.shouldInterceptRequest(r1, r10)
            if (r10 != 0) goto L_0x004f
            return r0
        L_0x004f:
            android.webkit.WebResourceResponse r11 = new android.webkit.WebResourceResponse
            java.lang.String r0 = r10.getMimeType()
            java.lang.String r1 = r10.getEncoding()
            java.io.InputStream r2 = r10.getData()
            r11.<init>(r0, r1, r2)
            java.util.Map r0 = r10.getResponseHeaders()
            r11.setResponseHeaders(r0)
            int r0 = r10.getStatusCode()
            java.lang.String r10 = r10.getReasonPhrase()
            int r1 = r11.getStatusCode()
            if (r0 != r1) goto L_0x0081
            if (r10 == 0) goto L_0x0084
            java.lang.String r1 = r11.getReasonPhrase()
            boolean r1 = r10.equals(r1)
            if (r1 != 0) goto L_0x0084
        L_0x0081:
            r11.setStatusCodeAndReasonPhrase(r0, r10)
        L_0x0084:
            return r11
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.smtt.sdk.SystemWebViewClient.shouldInterceptRequest(android.webkit.WebView, android.webkit.WebResourceRequest):android.webkit.WebResourceResponse");
    }

    /* renamed from: com.tencent.smtt.sdk.SystemWebViewClient$e */
    /* loaded from: classes2.dex */
    private class C2581e implements com.tencent.smtt.export.external.interfaces.WebResourceRequest {

        /* renamed from: b */
        private String f12870b;

        /* renamed from: c */
        private boolean f12871c;

        /* renamed from: d */
        private boolean f12872d;

        /* renamed from: e */
        private boolean f12873e;

        /* renamed from: f */
        private String f12874f;

        /* renamed from: g */
        private Map<String, String> f12875g;

        public C2581e(String str, boolean z, boolean z2, boolean z3, String str2, Map<String, String> map) {
            this.f12870b = str;
            this.f12871c = z;
            this.f12872d = z2;
            this.f12873e = z3;
            this.f12874f = str2;
            this.f12875g = map;
        }

        @Override // com.tencent.smtt.export.external.interfaces.WebResourceRequest
        public Uri getUrl() {
            return Uri.parse(this.f12870b);
        }

        @Override // com.tencent.smtt.export.external.interfaces.WebResourceRequest
        public boolean isForMainFrame() {
            return this.f12871c;
        }

        @Override // com.tencent.smtt.export.external.interfaces.WebResourceRequest
        public boolean isRedirect() {
            return this.f12872d;
        }

        @Override // com.tencent.smtt.export.external.interfaces.WebResourceRequest
        public boolean hasGesture() {
            return this.f12873e;
        }

        @Override // com.tencent.smtt.export.external.interfaces.WebResourceRequest
        public String getMethod() {
            return this.f12874f;
        }

        @Override // com.tencent.smtt.export.external.interfaces.WebResourceRequest
        public Map<String, String> getRequestHeaders() {
            return this.f12875g;
        }
    }

    @Override // android.webkit.WebViewClient
    public boolean shouldOverrideKeyEvent(WebView webView, KeyEvent keyEvent) {
        this.f12861b.m16931a(webView);
        return this.f12860a.shouldOverrideKeyEvent(this.f12861b, keyEvent);
    }

    @Override // android.webkit.WebViewClient
    public void onFormResubmission(WebView webView, Message message, Message message2) {
        this.f12861b.m16931a(webView);
        this.f12860a.onFormResubmission(this.f12861b, message, message2);
    }

    @Override // android.webkit.WebViewClient
    public void onPageFinished(WebView webView, String str) {
        TbsConfigFile a;
        if (f12859c == null && (a = TbsConfigFile.m16380a()) != null) {
            a.m16378a(true);
            f12859c = Boolean.toString(true);
        }
        this.f12861b.m16931a(webView);
        this.f12861b.f13022a++;
        this.f12860a.onPageFinished(this.f12861b, str);
        if (TbsConfig.APP_QZONE.equals(webView.getContext().getApplicationInfo().packageName)) {
            this.f12861b.m16934a(webView.getContext());
        }
        TbsLog.app_extra("SystemWebViewClient", webView.getContext());
        WebView.m16922d();
        if (!TbsShareManager.mHasQueryed && this.f12861b.getContext() != null && TbsShareManager.isThirdPartyApp(this.f12861b.getContext())) {
            TbsShareManager.mHasQueryed = true;
            new Thread(new Runnable() { // from class: com.tencent.smtt.sdk.SystemWebViewClient.1
                @Override // java.lang.Runnable
                public void run() {
                    if (!TbsShareManager.forceLoadX5FromTBSDemo(SystemWebViewClient.this.f12861b.getContext()) && TbsDownloader.needDownload(SystemWebViewClient.this.f12861b.getContext(), false)) {
                        TbsDownloader.startDownload(SystemWebViewClient.this.f12861b.getContext());
                    }
                }
            }).start();
        }
        if (this.f12861b.getContext() != null && !TbsLogReport.getInstance(this.f12861b.getContext()).getShouldUploadEventReport()) {
            TbsLogReport.getInstance(this.f12861b.getContext()).setShouldUploadEventReport(true);
            TbsLogReport.getInstance(this.f12861b.getContext()).dailyReport();
        }
    }

    @Override // android.webkit.WebViewClient
    public void onPageStarted(WebView webView, String str, Bitmap bitmap) {
        this.f12861b.m16931a(webView);
        this.f12860a.onPageStarted(this.f12861b, str, bitmap);
    }

    @Override // android.webkit.WebViewClient
    public void onReceivedError(WebView webView, int i, String str, String str2) {
        this.f12861b.m16931a(webView);
        this.f12860a.onReceivedError(this.f12861b, i, str, str2);
    }

    @Override // android.webkit.WebViewClient
    public void onReceivedError(WebView webView, WebResourceRequest webResourceRequest, final WebResourceError webResourceError) {
        this.f12861b.m16931a(webView);
        com.tencent.smtt.export.external.interfaces.WebResourceError webResourceError2 = null;
        C2582f fVar = webResourceRequest != null ? new C2582f(webResourceRequest) : null;
        if (webResourceError != null) {
            webResourceError2 = new com.tencent.smtt.export.external.interfaces.WebResourceError() { // from class: com.tencent.smtt.sdk.SystemWebViewClient.2
                @Override // com.tencent.smtt.export.external.interfaces.WebResourceError
                public CharSequence getDescription() {
                    return webResourceError.getDescription();
                }

                @Override // com.tencent.smtt.export.external.interfaces.WebResourceError
                public int getErrorCode() {
                    return webResourceError.getErrorCode();
                }
            };
        }
        this.f12860a.onReceivedError(this.f12861b, fVar, webResourceError2);
    }

    @Override // android.webkit.WebViewClient
    public void onReceivedHttpError(WebView webView, WebResourceRequest webResourceRequest, WebResourceResponse webResourceResponse) {
        this.f12861b.m16931a(webView);
        this.f12860a.onReceivedHttpError(this.f12861b, new C2582f(webResourceRequest), new C2583g(webResourceResponse));
    }

    @Override // android.webkit.WebViewClient
    public void onReceivedHttpAuthRequest(WebView webView, HttpAuthHandler httpAuthHandler, String str, String str2) {
        this.f12861b.m16931a(webView);
        this.f12860a.onReceivedHttpAuthRequest(this.f12861b, new C2578b(httpAuthHandler), str, str2);
    }

    @Override // android.webkit.WebViewClient
    public void doUpdateVisitedHistory(WebView webView, String str, boolean z) {
        this.f12861b.m16931a(webView);
        this.f12860a.doUpdateVisitedHistory(this.f12861b, str, z);
    }

    @Override // android.webkit.WebViewClient
    @TargetApi(12)
    public void onReceivedLoginRequest(WebView webView, String str, String str2, String str3) {
        if (Build.VERSION.SDK_INT >= 12) {
            this.f12861b.m16931a(webView);
            this.f12860a.onReceivedLoginRequest(this.f12861b, str, str2, str3);
        }
    }

    @Override // android.webkit.WebViewClient
    @TargetApi(8)
    public void onReceivedSslError(WebView webView, SslErrorHandler sslErrorHandler, SslError sslError) {
        if (Build.VERSION.SDK_INT >= 8) {
            this.f12861b.m16931a(webView);
            this.f12860a.onReceivedSslError(this.f12861b, new C2579c(sslErrorHandler), new C2580d(sslError));
        }
    }

    @Override // android.webkit.WebViewClient
    public void onReceivedClientCertRequest(WebView webView, ClientCertRequest clientCertRequest) {
        if (Build.VERSION.SDK_INT >= 21) {
            this.f12861b.m16931a(webView);
            this.f12860a.onReceivedClientCertRequest(this.f12861b, new C2577a(clientCertRequest));
        }
    }

    /* renamed from: com.tencent.smtt.sdk.SystemWebViewClient$a */
    /* loaded from: classes2.dex */
    private static class C2577a extends com.tencent.smtt.export.external.interfaces.ClientCertRequest {

        /* renamed from: a */
        private ClientCertRequest f12865a;

        public C2577a(ClientCertRequest clientCertRequest) {
            this.f12865a = clientCertRequest;
        }

        @Override // com.tencent.smtt.export.external.interfaces.ClientCertRequest
        public void cancel() {
            this.f12865a.cancel();
        }

        @Override // com.tencent.smtt.export.external.interfaces.ClientCertRequest
        public String getHost() {
            return this.f12865a.getHost();
        }

        @Override // com.tencent.smtt.export.external.interfaces.ClientCertRequest
        public String[] getKeyTypes() {
            return this.f12865a.getKeyTypes();
        }

        @Override // com.tencent.smtt.export.external.interfaces.ClientCertRequest
        public int getPort() {
            return this.f12865a.getPort();
        }

        @Override // com.tencent.smtt.export.external.interfaces.ClientCertRequest
        public Principal[] getPrincipals() {
            return this.f12865a.getPrincipals();
        }

        @Override // com.tencent.smtt.export.external.interfaces.ClientCertRequest
        public void ignore() {
            this.f12865a.ignore();
        }

        @Override // com.tencent.smtt.export.external.interfaces.ClientCertRequest
        public void proceed(PrivateKey privateKey, X509Certificate[] x509CertificateArr) {
            this.f12865a.proceed(privateKey, x509CertificateArr);
        }
    }

    @Override // android.webkit.WebViewClient
    public void onScaleChanged(WebView webView, float f, float f2) {
        this.f12861b.m16931a(webView);
        this.f12860a.onScaleChanged(this.f12861b, f, f2);
    }

    @Override // android.webkit.WebViewClient
    public void onTooManyRedirects(WebView webView, Message message, Message message2) {
        this.f12861b.m16931a(webView);
        this.f12860a.onTooManyRedirects(this.f12861b, message, message2);
    }

    @Override // android.webkit.WebViewClient
    public void onUnhandledKeyEvent(WebView webView, KeyEvent keyEvent) {
        this.f12861b.m16931a(webView);
        this.f12860a.onUnhandledKeyEvent(this.f12861b, keyEvent);
    }

    /* renamed from: com.tencent.smtt.sdk.SystemWebViewClient$b */
    /* loaded from: classes2.dex */
    private static class C2578b implements com.tencent.smtt.export.external.interfaces.HttpAuthHandler {

        /* renamed from: a */
        private HttpAuthHandler f12866a;

        C2578b(HttpAuthHandler httpAuthHandler) {
            this.f12866a = httpAuthHandler;
        }

        @Override // com.tencent.smtt.export.external.interfaces.HttpAuthHandler
        public void proceed(String str, String str2) {
            this.f12866a.proceed(str, str2);
        }

        @Override // com.tencent.smtt.export.external.interfaces.HttpAuthHandler
        public void cancel() {
            this.f12866a.cancel();
        }

        @Override // com.tencent.smtt.export.external.interfaces.HttpAuthHandler
        public boolean useHttpAuthUsernamePassword() {
            return this.f12866a.useHttpAuthUsernamePassword();
        }
    }

    /* renamed from: com.tencent.smtt.sdk.SystemWebViewClient$c */
    /* loaded from: classes2.dex */
    private static class C2579c implements com.tencent.smtt.export.external.interfaces.SslErrorHandler {

        /* renamed from: a */
        SslErrorHandler f12867a;

        C2579c(SslErrorHandler sslErrorHandler) {
            this.f12867a = sslErrorHandler;
        }

        @Override // com.tencent.smtt.export.external.interfaces.SslErrorHandler
        public void proceed() {
            this.f12867a.proceed();
        }

        @Override // com.tencent.smtt.export.external.interfaces.SslErrorHandler
        public void cancel() {
            this.f12867a.cancel();
        }
    }

    /* renamed from: com.tencent.smtt.sdk.SystemWebViewClient$d */
    /* loaded from: classes2.dex */
    private static class C2580d implements com.tencent.smtt.export.external.interfaces.SslError {

        /* renamed from: a */
        SslError f12868a;

        C2580d(SslError sslError) {
            this.f12868a = sslError;
        }

        @Override // com.tencent.smtt.export.external.interfaces.SslError
        public SslCertificate getCertificate() {
            return this.f12868a.getCertificate();
        }

        @Override // com.tencent.smtt.export.external.interfaces.SslError
        public boolean addError(int i) {
            return this.f12868a.addError(i);
        }

        @Override // com.tencent.smtt.export.external.interfaces.SslError
        public boolean hasError(int i) {
            return this.f12868a.hasError(i);
        }

        @Override // com.tencent.smtt.export.external.interfaces.SslError
        public int getPrimaryError() {
            return this.f12868a.getPrimaryError();
        }

        @Override // com.tencent.smtt.export.external.interfaces.SslError
        public String getUrl() {
            return this.f12868a.getUrl();
        }
    }

    /* renamed from: com.tencent.smtt.sdk.SystemWebViewClient$f */
    /* loaded from: classes2.dex */
    private static class C2582f implements com.tencent.smtt.export.external.interfaces.WebResourceRequest {

        /* renamed from: a */
        WebResourceRequest f12876a;

        C2582f(WebResourceRequest webResourceRequest) {
            this.f12876a = webResourceRequest;
        }

        @Override // com.tencent.smtt.export.external.interfaces.WebResourceRequest
        public String getMethod() {
            return this.f12876a.getMethod();
        }

        @Override // com.tencent.smtt.export.external.interfaces.WebResourceRequest
        public Map<String, String> getRequestHeaders() {
            return this.f12876a.getRequestHeaders();
        }

        @Override // com.tencent.smtt.export.external.interfaces.WebResourceRequest
        public Uri getUrl() {
            return this.f12876a.getUrl();
        }

        @Override // com.tencent.smtt.export.external.interfaces.WebResourceRequest
        public boolean hasGesture() {
            return this.f12876a.hasGesture();
        }

        @Override // com.tencent.smtt.export.external.interfaces.WebResourceRequest
        public boolean isForMainFrame() {
            return this.f12876a.isForMainFrame();
        }

        @Override // com.tencent.smtt.export.external.interfaces.WebResourceRequest
        public boolean isRedirect() {
            if (Build.VERSION.SDK_INT >= 24) {
                Object a = ReflectionUtils.m16406a(this.f12876a, "isRedirect");
                if (a instanceof Boolean) {
                    return ((Boolean) a).booleanValue();
                }
            }
            return false;
        }
    }

    /* renamed from: com.tencent.smtt.sdk.SystemWebViewClient$g */
    /* loaded from: classes2.dex */
    private static class C2583g extends com.tencent.smtt.export.external.interfaces.WebResourceResponse {

        /* renamed from: a */
        WebResourceResponse f12877a;

        public C2583g(WebResourceResponse webResourceResponse) {
            this.f12877a = webResourceResponse;
        }

        @Override // com.tencent.smtt.export.external.interfaces.WebResourceResponse
        public InputStream getData() {
            return this.f12877a.getData();
        }

        @Override // com.tencent.smtt.export.external.interfaces.WebResourceResponse
        public String getEncoding() {
            return this.f12877a.getEncoding();
        }

        @Override // com.tencent.smtt.export.external.interfaces.WebResourceResponse
        public String getMimeType() {
            return this.f12877a.getMimeType();
        }

        @Override // com.tencent.smtt.export.external.interfaces.WebResourceResponse
        public String getReasonPhrase() {
            return this.f12877a.getReasonPhrase();
        }

        @Override // com.tencent.smtt.export.external.interfaces.WebResourceResponse
        public Map<String, String> getResponseHeaders() {
            return this.f12877a.getResponseHeaders();
        }

        @Override // com.tencent.smtt.export.external.interfaces.WebResourceResponse
        public int getStatusCode() {
            return this.f12877a.getStatusCode();
        }

        @Override // com.tencent.smtt.export.external.interfaces.WebResourceResponse
        public void setData(InputStream inputStream) {
            this.f12877a.setData(inputStream);
        }

        @Override // com.tencent.smtt.export.external.interfaces.WebResourceResponse
        public void setEncoding(String str) {
            this.f12877a.setEncoding(str);
        }

        @Override // com.tencent.smtt.export.external.interfaces.WebResourceResponse
        public void setMimeType(String str) {
            this.f12877a.setMimeType(str);
        }

        @Override // com.tencent.smtt.export.external.interfaces.WebResourceResponse
        public void setResponseHeaders(Map<String, String> map) {
            this.f12877a.setResponseHeaders(map);
        }

        @Override // com.tencent.smtt.export.external.interfaces.WebResourceResponse
        public void setStatusCodeAndReasonPhrase(int i, String str) {
            this.f12877a.setStatusCodeAndReasonPhrase(i, str);
        }
    }

    @Override // android.webkit.WebViewClient
    public void onPageCommitVisible(WebView webView, String str) {
        this.f12861b.m16931a(webView);
        this.f12860a.onPageCommitVisible(this.f12861b, str);
    }
}
