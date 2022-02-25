package com.tendcloud.tenddata;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: td */
/* renamed from: com.tendcloud.tenddata.gy */
/* loaded from: classes2.dex */
public class HandlerC2928gy extends Handler {
    final /* synthetic */ C2926gx this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public HandlerC2928gy(C2926gx gxVar, Looper looper) {
        super(looper);
        this.this$0 = gxVar;
    }

    @Override // android.os.Handler
    public void handleMessage(Message message) {
        try {
            switch (message.what) {
                case 1:
                    this.this$0.m15531f();
                    break;
                case 2:
                    this.this$0.m15532e();
                    break;
                case 3:
                    this.this$0.m15534d();
                    break;
            }
        } catch (Throwable th) {
            C2933hb.postSDKError(th);
        }
    }
}
