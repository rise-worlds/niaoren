package com.lbd.p054xj.p056ui.activity;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.lbd.p054xj.C1467R;

/* renamed from: com.lbd.xj.ui.activity.XJWebViewActivity */
/* loaded from: classes.dex */
public class XJWebViewActivity extends AppBaseActivity {

    /* renamed from: a */
    private WebView f9663a;

    @Override // com.lbd.p054xj.base.p055ui.BaseActivity
    /* renamed from: b */
    public void mo19492b() {
    }

    @Override // com.lbd.p054xj.base.p055ui.BaseActivity
    /* renamed from: b */
    public void mo19491b(Bundle bundle) {
    }

    @Override // com.lbd.p054xj.base.p055ui.BaseActivity
    /* renamed from: c */
    public void mo19490c() {
    }

    @Override // com.lbd.p054xj.base.p055ui.BaseActivity
    /* renamed from: a */
    protected int mo19493a() {
        return C1467R.layout.activity_web;
    }

    @Override // com.lbd.p054xj.base.p055ui.BaseActivity
    /* renamed from: c */
    public void mo19489c(Bundle bundle) {
        String stringExtra = getIntent().getStringExtra("url");
        this.f9663a = (WebView) findViewById(C1467R.C1469id.webView);
        findViewById(C1467R.C1469id.iv_set_close).setOnClickListener(new View.OnClickListener() { // from class: com.lbd.xj.ui.activity.XJWebViewActivity.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                XJWebViewActivity.this.finish();
            }
        });
        this.f9663a.loadUrl(stringExtra);
        this.f9663a.setWebViewClient(new WebViewClient() { // from class: com.lbd.xj.ui.activity.XJWebViewActivity.2
            @Override // android.webkit.WebViewClient
            public boolean shouldOverrideUrlLoading(WebView webView, String str) {
                webView.loadUrl(str);
                return true;
            }
        });
    }
}
