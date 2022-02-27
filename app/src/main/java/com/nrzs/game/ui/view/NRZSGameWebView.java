package com.nrzs.game.ui.view;

import android.content.Context;
import android.view.View;
import com.nrzs.base.webview.webview.DefualtWebView;
import p110z1.LoadViewHelper;
import p110z1.LocalLoadHelper;

/* renamed from: com.nrzs.game.ui.view.NRZSGameWebView */
/* loaded from: classes2.dex */
public class NRZSGameWebView extends DefualtWebView {
    @Override // com.nrzs.base.webview.webview.DefualtWebView
    /* renamed from: h */
    protected void mo18592h() {
    }

    public NRZSGameWebView(Context context) {
        super(context);
        mo18592h();
    }

    @Override // com.nrzs.base.webview.webview.DefualtWebView
    public LoadViewHelper.AbstractC3846a getLocalLoadHelper() {
        return new LocalLoadHelper(getContext(), this, new View.OnClickListener() { // from class: com.nrzs.game.ui.view.NRZSGameWebView.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                NRZSGameWebView.this.reload();
            }
        });
    }
}
