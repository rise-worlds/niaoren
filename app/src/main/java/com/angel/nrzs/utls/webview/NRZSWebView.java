package com.angel.nrzs.utls.webview;

import android.content.Context;
import android.view.View;
import p110z1.LoadViewHelper;
import p110z1.LocalLoadHelper;
import p110z1.NRZSJsCallAndroid;

/* loaded from: classes.dex */
public class NRZSWebView extends DefualtWebView {
    public NRZSWebView(Context context) {
        super(context);
        mo24852h();
    }

    @Override // com.angel.nrzs.utls.webview.DefualtWebView
    /* renamed from: h */
    protected void mo24852h() {
        addJavascriptInterface(new NRZSJsCallAndroid(getContext()), NRZSJsCallAndroid.f21633a);
    }

    @Override // com.angel.nrzs.utls.webview.DefualtWebView
    public LoadViewHelper.AbstractC3846a getLocalLoadHelper() {
        return new LocalLoadHelper(getContext(), this, new View.OnClickListener() { // from class: com.angel.nrzs.utls.webview.NRZSWebView.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                NRZSWebView.this.reload();
            }
        });
    }
}
