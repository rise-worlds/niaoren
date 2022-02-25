package com.alipay.sdk.widget;

import android.content.Context;
import android.net.http.SslError;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.webkit.JsPromptResult;
import android.webkit.SslErrorHandler;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import p110z1.C4745bt;
import p110z1.C4976cl;
import p110z1.C5019co;

/* renamed from: com.alipay.sdk.widget.p */
/* loaded from: classes.dex */
public class C0682p extends LinearLayout {

    /* renamed from: f */
    private static Handler f398f = new Handler(Looper.getMainLooper());

    /* renamed from: a */
    private ImageView f399a;

    /* renamed from: b */
    private TextView f400b;

    /* renamed from: c */
    private ImageView f401c;

    /* renamed from: d */
    private ProgressBar f402d;

    /* renamed from: e */
    private WebView f403e;

    /* renamed from: g */
    private AbstractC0683a f404g;

    /* renamed from: h */
    private AbstractC0684b f405h;

    /* renamed from: i */
    private AbstractC0685c f406i;

    /* renamed from: j */
    private final C4745bt f407j;

    /* renamed from: k */
    private View.OnClickListener f408k;

    /* renamed from: l */
    private final float f409l;

    /* renamed from: com.alipay.sdk.widget.p$a */
    /* loaded from: classes.dex */
    public interface AbstractC0683a {
        /* renamed from: a */
        void mo25225a(C0682p pVar, String str);

        /* renamed from: a */
        boolean mo25224a(C0682p pVar, String str, String str2, String str3, JsPromptResult jsPromptResult);
    }

    /* renamed from: com.alipay.sdk.widget.p$b */
    /* loaded from: classes.dex */
    public interface AbstractC0684b {
        /* renamed from: a */
        boolean mo25223a(C0682p pVar, int i, String str, String str2);

        /* renamed from: a */
        boolean mo25222a(C0682p pVar, SslErrorHandler sslErrorHandler, SslError sslError);

        /* renamed from: b */
        boolean mo25221b(C0682p pVar, String str);

        /* renamed from: c */
        boolean mo25220c(C0682p pVar, String str);
    }

    /* renamed from: com.alipay.sdk.widget.p$c */
    /* loaded from: classes.dex */
    public interface AbstractC0685c {
        /* renamed from: a */
        void mo25219a(C0682p pVar);

        /* renamed from: b */
        void mo25218b(C0682p pVar);
    }

    public C0682p(Context context, C4745bt btVar) {
        this(context, null, btVar);
    }

    public C0682p(Context context, AttributeSet attributeSet, C4745bt btVar) {
        super(context, attributeSet);
        this.f408k = new View$OnClickListenerC0686q(this);
        this.f407j = btVar;
        this.f409l = context.getResources().getDisplayMetrics().density;
        setOrientation(1);
        m25238a(context);
        m25232b(context);
        m25230c(context);
    }

    /* renamed from: a */
    private void m25238a(Context context) {
        LinearLayout linearLayout = new LinearLayout(context);
        linearLayout.setBackgroundColor(-218103809);
        linearLayout.setOrientation(0);
        linearLayout.setGravity(16);
        this.f399a = new ImageView(context);
        this.f399a.setOnClickListener(this.f408k);
        this.f399a.setScaleType(ImageView.ScaleType.CENTER);
        this.f399a.setImageDrawable(C4976cl.m5031a(C4976cl.f20793a, context));
        this.f399a.setPadding(m25239a(12), 0, m25239a(12), 0);
        linearLayout.addView(this.f399a, new LinearLayout.LayoutParams(-2, -2));
        View view = new View(context);
        view.setBackgroundColor(-2500135);
        linearLayout.addView(view, new LinearLayout.LayoutParams(m25239a(1), m25239a(25)));
        this.f400b = new TextView(context);
        this.f400b.setTextColor(-15658735);
        this.f400b.setTextSize(17.0f);
        this.f400b.setMaxLines(1);
        this.f400b.setEllipsize(TextUtils.TruncateAt.END);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, -2);
        layoutParams.setMargins(m25239a(17), 0, 0, 0);
        layoutParams.weight = 1.0f;
        linearLayout.addView(this.f400b, layoutParams);
        this.f401c = new ImageView(context);
        this.f401c.setOnClickListener(this.f408k);
        this.f401c.setScaleType(ImageView.ScaleType.CENTER);
        this.f401c.setImageDrawable(C4976cl.m5031a(C4976cl.f20794b, context));
        this.f401c.setPadding(m25239a(12), 0, m25239a(12), 0);
        linearLayout.addView(this.f401c, new LinearLayout.LayoutParams(-2, -2));
        addView(linearLayout, new LinearLayout.LayoutParams(-1, m25239a(48)));
    }

    /* renamed from: b */
    private void m25232b(Context context) {
        this.f402d = new ProgressBar(context, null, 16973855);
        this.f402d.setProgressDrawable(context.getResources().getDrawable(17301612));
        this.f402d.setMax(100);
        this.f402d.setBackgroundColor(-218103809);
        addView(this.f402d, new LinearLayout.LayoutParams(-1, m25239a(2)));
    }

    /* renamed from: c */
    private void m25230c(Context context) {
        this.f403e = new WebView(context);
        this.f403e.setVerticalScrollbarOverlay(true);
        m25237a(this.f403e, context);
        WebSettings settings = this.f403e.getSettings();
        settings.setUseWideViewPort(true);
        settings.setAppCacheMaxSize(5242880L);
        settings.setAppCachePath(context.getCacheDir().getAbsolutePath());
        settings.setAllowFileAccess(true);
        settings.setAppCacheEnabled(true);
        settings.setJavaScriptEnabled(true);
        settings.setCacheMode(-1);
        settings.setSupportMultipleWindows(true);
        settings.setJavaScriptEnabled(true);
        settings.setSavePassword(false);
        settings.setJavaScriptCanOpenWindowsAutomatically(true);
        settings.setDomStorageEnabled(true);
        try {
            this.f403e.removeJavascriptInterface("searchBoxJavaBridge_");
            this.f403e.removeJavascriptInterface("accessibility");
            this.f403e.removeJavascriptInterface("accessibilityTraversal");
        } catch (Exception unused) {
        }
        addView(this.f403e, new LinearLayout.LayoutParams(-1, -1));
    }

    /* renamed from: a */
    protected void m25237a(WebView webView, Context context) {
        String userAgentString = webView.getSettings().getUserAgentString();
        String packageName = context.getPackageName();
        String a = C5019co.m4496a(this.f407j, context);
        webView.getSettings().setUserAgentString(userAgentString + " AlipaySDK(" + packageName + "/" + a + "/15.7.3)");
    }

    public void setChromeProxy(AbstractC0683a aVar) {
        this.f404g = aVar;
        if (aVar == null) {
            this.f403e.setWebChromeClient(null);
        } else {
            this.f403e.setWebChromeClient(new C0688s(this));
        }
    }

    public void setWebClientProxy(AbstractC0684b bVar) {
        this.f405h = bVar;
        if (bVar == null) {
            this.f403e.setWebViewClient(null);
        } else {
            this.f403e.setWebViewClient(new C0689t(this));
        }
    }

    public void setWebEventProxy(AbstractC0685c cVar) {
        this.f406i = cVar;
    }

    public String getUrl() {
        return this.f403e.getUrl();
    }

    /* renamed from: a */
    public void m25235a(String str) {
        this.f403e.loadUrl(str);
    }

    /* renamed from: a */
    public void m25234a(String str, byte[] bArr) {
        this.f403e.postUrl(str, bArr);
    }

    public ImageView getBackButton() {
        return this.f399a;
    }

    public TextView getTitle() {
        return this.f400b;
    }

    public ImageView getRefreshButton() {
        return this.f401c;
    }

    public ProgressBar getProgressbar() {
        return this.f402d;
    }

    public WebView getWebView() {
        return this.f403e;
    }

    /* renamed from: a */
    public void m25240a() {
        removeAllViews();
        this.f403e.removeAllViews();
        this.f403e.setWebViewClient(null);
        this.f403e.setWebChromeClient(null);
        this.f403e.destroy();
    }

    /* renamed from: a */
    private int m25239a(int i) {
        return (int) (i * this.f409l);
    }
}
