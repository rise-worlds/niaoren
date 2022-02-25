package com.alipay.sdk.widget;

import android.app.Activity;
import android.text.TextUtils;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.widget.FrameLayout;

/* renamed from: com.alipay.sdk.widget.g */
/* loaded from: classes.dex */
public abstract class AbstractC0672g extends FrameLayout {

    /* renamed from: a */
    protected Activity f360a;

    /* renamed from: a */
    public abstract void mo25255a();

    /* renamed from: a */
    public abstract void mo25252a(String str);

    /* renamed from: b */
    public abstract boolean mo25248b();

    public AbstractC0672g(Activity activity) {
        super(activity);
        this.f360a = activity;
    }

    /* renamed from: a */
    public void m25257a(String str, String str2) {
        if (!TextUtils.isEmpty(str2)) {
            CookieSyncManager.createInstance(this.f360a.getApplicationContext()).sync();
            CookieManager.getInstance().setCookie(str, str2);
            CookieSyncManager.getInstance().sync();
        }
    }
}
