package com.alipay.sdk.app;

import android.content.Intent;
import android.net.Uri;
import p110z1.C3754ao;
import p110z1.C3857aq;
import p110z1.C4745bt;

/* loaded from: classes.dex */
public class H5OpenAuthActivity extends H5PayActivity {

    /* renamed from: a */
    private boolean f263a = false;

    @Override // com.alipay.sdk.app.H5PayActivity
    /* renamed from: a */
    public void mo25332a() {
    }

    @Override // android.app.Activity, android.content.ContextWrapper, android.content.Context
    public void startActivity(Intent intent) {
        try {
            C4745bt a = C4745bt.C4746a.m9405a(intent);
            if (a == null) {
                finish();
                return;
            }
            try {
                super.startActivity(intent);
                Uri data = intent != null ? intent.getData() : null;
                if (data != null && data.toString().startsWith("alipays://platformapi/startapp")) {
                    finish();
                }
            } catch (Throwable th) {
                C3754ao.m12154a(a, C3857aq.f17251b, C3857aq.f17241af, th, (intent == null || intent.getData() == null) ? "null" : intent.getData().toString());
                this.f263a = true;
                throw th;
            }
        } catch (Throwable unused) {
            finish();
        }
    }

    @Override // com.alipay.sdk.app.H5PayActivity, android.app.Activity
    protected void onDestroy() {
        if (this.f263a) {
            try {
                C4745bt a = C4745bt.C4746a.m9405a(getIntent());
                if (a != null) {
                    C3754ao.m12152b(this, a, "", a.f20085p);
                }
            } catch (Throwable unused) {
            }
        }
        super.onDestroy();
    }
}
