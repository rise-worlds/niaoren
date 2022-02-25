package com.alipay.sdk.widget;

import android.os.Handler;
import com.alipay.sdk.widget.C0665a;
import p110z1.C4921cd;

/* JADX INFO: Access modifiers changed from: package-private */
/* renamed from: com.alipay.sdk.widget.b */
/* loaded from: classes.dex */
public class RunnableC0667b implements Runnable {

    /* renamed from: a */
    final /* synthetic */ C0665a f357a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public RunnableC0667b(C0665a aVar) {
        this.f357a = aVar;
    }

    @Override // java.lang.Runnable
    public void run() {
        C0665a.AlertDialogC0666a aVar;
        C0665a.AlertDialogC0666a aVar2;
        C0665a.AlertDialogC0666a aVar3;
        Handler handler;
        C0665a.AlertDialogC0666a aVar4;
        boolean z;
        aVar = this.f357a.f348e;
        if (aVar == null) {
            C0665a aVar5 = this.f357a;
            aVar5.f348e = new C0665a.AlertDialogC0666a(aVar5.f349f);
            aVar4 = this.f357a.f348e;
            z = this.f357a.f354k;
            aVar4.setCancelable(z);
        }
        try {
            aVar2 = this.f357a.f348e;
            if (!aVar2.isShowing()) {
                aVar3 = this.f357a.f348e;
                aVar3.show();
                handler = this.f357a.f355l;
                handler.sendEmptyMessageDelayed(1, 15000L);
            }
        } catch (Exception e) {
            C4921cd.m5618a(e);
        }
    }
}
