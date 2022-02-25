package com.alipay.sdk.app;

/* loaded from: classes.dex */
public class H5AuthActivity extends H5PayActivity {
    @Override // com.alipay.sdk.app.H5PayActivity
    /* renamed from: a */
    public void mo25332a() {
        Object obj = AuthTask.f260a;
        synchronized (obj) {
            try {
                obj.notify();
            } catch (Exception unused) {
            }
        }
    }
}
