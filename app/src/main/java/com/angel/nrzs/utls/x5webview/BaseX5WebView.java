package com.angel.nrzs.utls.x5webview;

import android.content.Context;
import android.util.AttributeSet;
import com.angel.nrzs.utls.webview.IInitView;
import com.tencent.smtt.sdk.WebView;

/* loaded from: classes.dex */
public abstract class BaseX5WebView extends WebView implements IInitView {
    @Override // com.angel.nrzs.utls.webview.IInitView
    /* renamed from: h_ */
    public void mo24849h_() {
    }

    public BaseX5WebView(Context context) {
        super(context);
        m24850h();
    }

    public BaseX5WebView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        m24850h();
    }

    public BaseX5WebView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        m24850h();
    }

    /* renamed from: h */
    private void m24850h() {
        mo24849h_();
        mo24839i_();
        mo24837j_();
        mo24840i();
    }
}
