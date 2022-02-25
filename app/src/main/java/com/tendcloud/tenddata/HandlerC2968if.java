package com.tendcloud.tenddata;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: td */
/* renamed from: com.tendcloud.tenddata.if */
/* loaded from: classes2.dex */
public class HandlerC2968if extends Handler {
    final /* synthetic */ C2966id this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public HandlerC2968if(C2966id idVar, Looper looper) {
        super(looper);
        this.this$0 = idVar;
    }

    @Override // android.os.Handler
    public void handleMessage(Message message) {
        try {
            if (message.obj != null && (message.obj instanceof AbstractC2790d)) {
                this.this$0.m15456a((AbstractC2790d) message.obj);
            }
            this.this$0.m15447d();
        } catch (Throwable th) {
            C2933hb.postSDKError(th);
        }
    }
}
