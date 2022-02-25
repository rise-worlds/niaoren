package com.alipay.android.phone.mrpc.core;

import android.content.Context;

/* renamed from: com.alipay.android.phone.mrpc.core.h */
/* loaded from: classes.dex */
public final class C0601h extends AbstractC0617w {

    /* renamed from: a */
    private Context f150a;

    public C0601h(Context context) {
        this.f150a = context;
    }

    @Override // com.alipay.android.phone.mrpc.core.AbstractC0617w
    /* renamed from: a */
    public final <T> T mo25445a(Class<T> cls, C0589aa aaVar) {
        return (T) new C0618x(new C0602i(this, aaVar)).m25443a(cls);
    }
}
