package com.alipay.sdk.widget;

import android.view.View;

/* renamed from: com.alipay.sdk.widget.r */
/* loaded from: classes.dex */
class RunnableC0687r implements Runnable {

    /* renamed from: a */
    final /* synthetic */ View f411a;

    /* renamed from: b */
    final /* synthetic */ View$OnClickListenerC0686q f412b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public RunnableC0687r(View$OnClickListenerC0686q qVar, View view) {
        this.f412b = qVar;
        this.f411a = view;
    }

    @Override // java.lang.Runnable
    public void run() {
        this.f411a.setEnabled(true);
    }
}
