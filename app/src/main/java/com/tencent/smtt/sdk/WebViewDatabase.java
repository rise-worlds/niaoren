package com.tencent.smtt.sdk;

import android.content.Context;

/* loaded from: classes2.dex */
public class WebViewDatabase {

    /* renamed from: a */
    private static WebViewDatabase f13056a;

    /* renamed from: b */
    private Context f13057b;

    protected WebViewDatabase(Context context) {
        this.f13057b = context;
    }

    public static WebViewDatabase getInstance(Context context) {
        return m16908a(context);
    }

    /* renamed from: a */
    private static synchronized WebViewDatabase m16908a(Context context) {
        WebViewDatabase webViewDatabase;
        synchronized (WebViewDatabase.class) {
            if (f13056a == null) {
                f13056a = new WebViewDatabase(context);
            }
            webViewDatabase = f13056a;
        }
        return webViewDatabase;
    }

    @Deprecated
    public boolean hasUsernamePassword() {
        X5CoreEngine a = X5CoreEngine.m16621a();
        if (a == null || !a.m16618b()) {
            return android.webkit.WebViewDatabase.getInstance(this.f13057b).hasUsernamePassword();
        }
        return a.m16616c().m16597b(this.f13057b);
    }

    @Deprecated
    public void clearUsernamePassword() {
        X5CoreEngine a = X5CoreEngine.m16621a();
        if (a == null || !a.m16618b()) {
            android.webkit.WebViewDatabase.getInstance(this.f13057b).clearUsernamePassword();
        } else {
            a.m16616c().m16591c(this.f13057b);
        }
    }

    public boolean hasHttpAuthUsernamePassword() {
        X5CoreEngine a = X5CoreEngine.m16621a();
        if (a == null || !a.m16618b()) {
            return android.webkit.WebViewDatabase.getInstance(this.f13057b).hasHttpAuthUsernamePassword();
        }
        return a.m16616c().m16587d(this.f13057b);
    }

    public void clearHttpAuthUsernamePassword() {
        X5CoreEngine a = X5CoreEngine.m16621a();
        if (a == null || !a.m16618b()) {
            android.webkit.WebViewDatabase.getInstance(this.f13057b).clearHttpAuthUsernamePassword();
        } else {
            a.m16616c().m16584e(this.f13057b);
        }
    }

    public boolean hasFormData() {
        X5CoreEngine a = X5CoreEngine.m16621a();
        if (a == null || !a.m16618b()) {
            return android.webkit.WebViewDatabase.getInstance(this.f13057b).hasFormData();
        }
        return a.m16616c().m16581f(this.f13057b);
    }

    public void clearFormData() {
        X5CoreEngine a = X5CoreEngine.m16621a();
        if (a == null || !a.m16618b()) {
            android.webkit.WebViewDatabase.getInstance(this.f13057b).clearFormData();
        } else {
            a.m16616c().m16578g(this.f13057b);
        }
    }
}
