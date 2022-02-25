package com.angel.nrzs.utls.x5webview;

import android.content.Context;
import android.view.View;
import p110z1.LoadViewHelper;
import p110z1.LocalLoadHelper;
import p110z1.NRZSJsCallAndroid;

/* loaded from: classes.dex */
public class NRZSX5WebView extends X5WebView {
    public NRZSX5WebView(Context context) {
        super(context);
        mo24841h();
    }

    @Override // com.angel.nrzs.utls.x5webview.X5WebView
    /* renamed from: h */
    protected void mo24841h() {
        addJavascriptInterface(new NRZSJsCallAndroid(getContext()), NRZSJsCallAndroid.f21633a);
    }

    @Override // com.angel.nrzs.utls.x5webview.X5WebView
    public LoadViewHelper.AbstractC3846a getLocalLoadHelper() {
        return new LocalLoadHelper(getContext(), this, new View.OnClickListener() { // from class: com.angel.nrzs.utls.x5webview.NRZSX5WebView.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                NRZSX5WebView.this.reload();
            }
        });
    }
}
