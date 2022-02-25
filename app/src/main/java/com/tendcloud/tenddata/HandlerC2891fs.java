package com.tendcloud.tenddata;

import android.hardware.SensorEvent;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: td */
/* renamed from: com.tendcloud.tenddata.fs */
/* loaded from: classes2.dex */
public class HandlerC2891fs extends Handler {
    final /* synthetic */ C2890fr this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public HandlerC2891fs(C2890fr frVar, Looper looper) {
        super(looper);
        this.this$0 = frVar;
    }

    @Override // android.os.Handler
    public void handleMessage(Message message) {
        try {
            int i = message.what;
            if (i != 1) {
                switch (i) {
                    case 13:
                        this.this$0.m15688f();
                        break;
                    case 14:
                        this.this$0.m15687g();
                        break;
                }
            } else {
                this.this$0.m15705a((SensorEvent) message.obj);
            }
        } catch (Throwable th) {
            C2933hb.postSDKError(th);
        }
    }
}
