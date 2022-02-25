package com.tendcloud.tenddata;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: td */
/* renamed from: com.tendcloud.tenddata.gw */
/* loaded from: classes2.dex */
public class HandlerC2925gw extends Handler {
    final /* synthetic */ C2924gv this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public HandlerC2925gw(C2924gv gvVar, Looper looper) {
        super(looper);
        this.this$0 = gvVar;
    }

    @Override // android.os.Handler
    public void handleMessage(Message message) {
        switch (message.what) {
            case 1:
                this.this$0.m15545b();
                return;
            case 2:
                this.this$0.m15543c();
                return;
            case 3:
                this.this$0.m15541d();
                return;
            case 4:
                this.this$0.m15541d();
                this.this$0.m15545b();
                this.this$0.m15548a(2, 600000L);
                return;
            default:
                return;
        }
    }
}
