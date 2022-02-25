package com.tendcloud.tenddata;

import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: td */
/* renamed from: com.tendcloud.tenddata.en */
/* loaded from: classes2.dex */
public class HandlerC2850en extends Handler {
    final /* synthetic */ C2848em this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public HandlerC2850en(C2848em emVar, Looper looper) {
        super(looper);
        this.this$0 = emVar;
    }

    @Override // android.os.Handler
    public void handleMessage(Message message) {
        SensorManager sensorManager;
        SensorManager sensorManager2;
        SensorEventListener sensorEventListener;
        super.handleMessage(message);
        if (message.what == 10) {
            sensorManager = this.this$0.f13900i;
            if (sensorManager != null) {
                sensorManager2 = this.this$0.f13900i;
                sensorEventListener = this.this$0.f13903l;
                sensorManager2.unregisterListener(sensorEventListener);
            }
        }
    }
}
