package com.angel.nrzs.utls.webview;

import android.content.Context;
import android.util.AttributeSet;
import android.webkit.WebView;

/* loaded from: classes.dex */
public abstract class BaseWebView extends WebView implements IInitView {
    @Override // com.angel.nrzs.utls.webview.IInitView
    /* renamed from: h_ */
    public void mo24849h_() {
    }

    public BaseWebView(Context context) {
        super(context);
        m24860h();
    }

    public BaseWebView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        m24860h();
    }

    public BaseWebView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        m24860h();
    }

    /* renamed from: h */
    private void m24860h() {
        mo24849h_();
        mo24839i_();
        mo24837j_();
        mo24840i();
    }
}
