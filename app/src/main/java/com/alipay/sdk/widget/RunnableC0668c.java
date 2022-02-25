package com.alipay.sdk.widget;

import android.os.Handler;
import com.alipay.sdk.widget.C0665a;
import p110z1.C4921cd;

/* JADX INFO: Access modifiers changed from: package-private */
/* renamed from: com.alipay.sdk.widget.c */
/* loaded from: classes.dex */
public class RunnableC0668c implements Runnable {

    /* renamed from: a */
    final /* synthetic */ C0665a f358a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public RunnableC0668c(C0665a aVar) {
        this.f358a = aVar;
    }

    @Override // java.lang.Runnable
    public void run() {
        C0665a.AlertDialogC0666a aVar;
        C0665a.AlertDialogC0666a aVar2;
        Handler handler;
        C0665a.AlertDialogC0666a aVar3;
        aVar = this.f358a.f348e;
        if (aVar != null) {
            aVar2 = this.f358a.f348e;
            if (aVar2.isShowing()) {
                try {
                    handler = this.f358a.f355l;
                    handler.removeMessages(1);
                    aVar3 = this.f358a.f348e;
                    aVar3.dismiss();
                } catch (Exception e) {
                    C4921cd.m5618a(e);
                }
            }
        }
    }
}
