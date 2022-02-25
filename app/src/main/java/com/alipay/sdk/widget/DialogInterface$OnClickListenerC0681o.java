package com.alipay.sdk.widget;

import android.content.DialogInterface;
import com.alipay.sdk.app.C0663l;
import p110z1.C3754ao;
import p110z1.C3857aq;
import p110z1.C4745bt;

/* renamed from: com.alipay.sdk.widget.o */
/* loaded from: classes.dex */
class DialogInterface$OnClickListenerC0681o implements DialogInterface.OnClickListener {

    /* renamed from: a */
    final /* synthetic */ RunnableC0680n f397a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public DialogInterface$OnClickListenerC0681o(RunnableC0680n nVar) {
        this.f397a = nVar;
    }

    @Override // android.content.DialogInterface.OnClickListener
    public void onClick(DialogInterface dialogInterface, int i) {
        C4745bt btVar;
        this.f397a.f395a.cancel();
        btVar = this.f397a.f396b.f385w;
        C3754ao.m12156a(btVar, C3857aq.f17235a, C3857aq.f17269t, "2");
        C0663l.m25285a(C0663l.m25282c());
        this.f397a.f396b.f360a.finish();
    }
}
