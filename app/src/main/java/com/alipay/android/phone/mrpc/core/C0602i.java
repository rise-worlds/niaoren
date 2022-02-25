package com.alipay.android.phone.mrpc.core;

import android.content.Context;

/* renamed from: com.alipay.android.phone.mrpc.core.i */
/* loaded from: classes.dex */
final class C0602i implements AbstractC0600g {

    /* renamed from: a */
    final /* synthetic */ C0589aa f151a;

    /* renamed from: b */
    final /* synthetic */ C0601h f152b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public C0602i(C0601h hVar, C0589aa aaVar) {
        this.f152b = hVar;
        this.f151a = aaVar;
    }

    @Override // com.alipay.android.phone.mrpc.core.AbstractC0600g
    /* renamed from: a */
    public final String mo25499a() {
        return this.f151a.m25517a();
    }

    @Override // com.alipay.android.phone.mrpc.core.AbstractC0600g
    /* renamed from: b */
    public final AbstractC0590ab mo25498b() {
        Context context;
        context = this.f152b.f150a;
        return C0606l.m25488a(context.getApplicationContext());
    }

    @Override // com.alipay.android.phone.mrpc.core.AbstractC0600g
    /* renamed from: c */
    public final C0589aa mo25497c() {
        return this.f151a;
    }

    @Override // com.alipay.android.phone.mrpc.core.AbstractC0600g
    /* renamed from: d */
    public final boolean mo25496d() {
        return this.f151a.m25514c();
    }
}
