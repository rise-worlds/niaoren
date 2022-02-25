package com.alipay.sdk.app;

import p110z1.C4943cf;

/* JADX INFO: Access modifiers changed from: package-private */
/* renamed from: com.alipay.sdk.app.j */
/* loaded from: classes.dex */
public class C0661j implements C4943cf.AbstractC4944a {

    /* renamed from: a */
    final /* synthetic */ PayTask f327a;

    @Override // p110z1.C4943cf.AbstractC4944a
    /* renamed from: a */
    public void mo5476a() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public C0661j(PayTask payTask) {
        this.f327a = payTask;
    }

    @Override // p110z1.C4943cf.AbstractC4944a
    /* renamed from: b */
    public void mo5475b() {
        this.f327a.dismissLoading();
    }
}
