package com.nrzs.base.webview.webview;

import android.content.Context;
import android.util.AttributeSet;
import android.webkit.WebView;

/* loaded from: classes2.dex */
public abstract class BaseWebView extends WebView implements AbstractC1956b {
    @Override // com.nrzs.base.webview.webview.AbstractC1956b
    /* renamed from: a */
    public void mo18976a() {
    }

    public BaseWebView(Context context) {
        super(context);
        m18985h();
    }

    public BaseWebView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        m18985h();
    }

    public BaseWebView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        m18985h();
    }

    /* renamed from: h */
    private void m18985h() {
        mo18976a();
        mo18975f();
        mo18974g();
        mo18973i();
    }
}
