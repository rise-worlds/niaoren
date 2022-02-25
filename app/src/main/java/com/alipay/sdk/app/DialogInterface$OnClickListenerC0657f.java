package com.alipay.sdk.app;

import android.content.DialogInterface;
import p110z1.C3754ao;
import p110z1.C3857aq;
import p110z1.C4745bt;

/* renamed from: com.alipay.sdk.app.f */
/* loaded from: classes.dex */
class DialogInterface$OnClickListenerC0657f implements DialogInterface.OnClickListener {

    /* renamed from: a */
    final /* synthetic */ RunnableC0656e f320a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public DialogInterface$OnClickListenerC0657f(RunnableC0656e eVar) {
        this.f320a = eVar;
    }

    @Override // android.content.DialogInterface.OnClickListener
    public void onClick(DialogInterface dialogInterface, int i) {
        C4745bt btVar;
        this.f320a.f318b.cancel();
        btVar = this.f320a.f319c.f316c;
        C3754ao.m12156a(btVar, C3857aq.f17235a, C3857aq.f17269t, "1");
        C0663l.m25285a(C0663l.m25282c());
        this.f320a.f317a.finish();
    }
}
