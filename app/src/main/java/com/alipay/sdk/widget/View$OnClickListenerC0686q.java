package com.alipay.sdk.widget;

import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import com.alipay.sdk.widget.C0682p;

/* JADX INFO: Access modifiers changed from: package-private */
/* renamed from: com.alipay.sdk.widget.q */
/* loaded from: classes.dex */
public class View$OnClickListenerC0686q implements View.OnClickListener {

    /* renamed from: a */
    final /* synthetic */ C0682p f410a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public View$OnClickListenerC0686q(C0682p pVar) {
        this.f410a = pVar;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        C0682p.AbstractC0685c cVar;
        Handler handler;
        ImageView imageView;
        ImageView imageView2;
        cVar = this.f410a.f406i;
        if (cVar != null) {
            view.setEnabled(false);
            handler = C0682p.f398f;
            handler.postDelayed(new RunnableC0687r(this, view), 256L);
            imageView = this.f410a.f399a;
            if (view == imageView) {
                cVar.mo25219a(this.f410a);
                return;
            }
            imageView2 = this.f410a.f401c;
            if (view == imageView2) {
                cVar.mo25218b(this.f410a);
            }
        }
    }
}
