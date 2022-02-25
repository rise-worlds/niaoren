package com.alipay.sdk.widget;

import android.app.Activity;
import android.content.Context;
import android.webkit.WebSettings;
import android.webkit.WebView;
import com.alipay.sdk.app.C0655d;
import com.alipay.sdk.app.C0663l;
import com.alipay.sdk.app.EnumC0664m;
import java.lang.reflect.Method;
import p110z1.C4745bt;
import p110z1.C5019co;

/* renamed from: com.alipay.sdk.widget.h */
/* loaded from: classes.dex */
public class C0673h extends AbstractC0672g {

    /* renamed from: b */
    private C0655d f361b;

    /* renamed from: c */
    private WebView f362c;

    public C0673h(Activity activity, C4745bt btVar) {
        super(activity);
        this.f362c = new WebView(activity);
        m25256a(activity);
        addView(this.f362c);
        this.f361b = new C0655d(activity, btVar);
        this.f362c.setWebViewClient(this.f361b);
    }

    @Override // com.alipay.sdk.widget.AbstractC0672g
    /* renamed from: b */
    public boolean mo25248b() {
        if (!this.f362c.canGoBack()) {
            C0663l.m25285a(C0663l.m25282c());
            this.f360a.finish();
            return true;
        } else if (!this.f361b.m25291b()) {
            return true;
        } else {
            EnumC0664m b = EnumC0664m.m25275b(EnumC0664m.NETWORK_ERROR.m25279a());
            C0663l.m25285a(C0663l.m25286a(b.m25279a(), b.m25276b(), ""));
            this.f360a.finish();
            return true;
        }
    }

    @Override // com.alipay.sdk.widget.AbstractC0672g
    /* renamed from: a */
    public void mo25255a() {
        this.f361b.m25293a();
        removeAllViews();
    }

    /* renamed from: a */
    private void m25256a(Context context) {
        WebSettings settings = this.f362c.getSettings();
        settings.setUserAgentString(settings.getUserAgentString() + C5019co.m4480c(context));
        settings.setRenderPriority(WebSettings.RenderPriority.HIGH);
        settings.setSupportMultipleWindows(true);
        settings.setJavaScriptEnabled(true);
        settings.setSavePassword(false);
        settings.setJavaScriptCanOpenWindowsAutomatically(true);
        settings.setMinimumFontSize(settings.getMinimumFontSize() + 8);
        settings.setAllowFileAccess(false);
        settings.setTextSize(WebSettings.TextSize.NORMAL);
        settings.setDomStorageEnabled(true);
        settings.setCacheMode(1);
        this.f362c.resumeTimers();
        this.f362c.setVerticalScrollbarOverlay(true);
        this.f362c.setDownloadListener(new C0674i(this));
        try {
            try {
                this.f362c.removeJavascriptInterface("searchBoxJavaBridge_");
                this.f362c.removeJavascriptInterface("accessibility");
                this.f362c.removeJavascriptInterface("accessibilityTraversal");
            } catch (Throwable unused) {
            }
        } catch (Throwable unused2) {
            Method method = this.f362c.getClass().getMethod("removeJavascriptInterface", new Class[0]);
            if (method != null) {
                method.invoke(this.f362c, "searchBoxJavaBridge_");
                method.invoke(this.f362c, "accessibility");
                method.invoke(this.f362c, "accessibilityTraversal");
            }
        }
    }

    @Override // com.alipay.sdk.widget.AbstractC0672g
    /* renamed from: a */
    public void mo25252a(String str) {
        this.f362c.loadUrl(str);
    }
}
