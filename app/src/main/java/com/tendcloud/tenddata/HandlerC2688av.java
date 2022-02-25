package com.tendcloud.tenddata;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import org.apache.tools.ant.taskdefs.WaitFor;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: td */
/* renamed from: com.tendcloud.tenddata.av */
/* loaded from: classes2.dex */
public final class HandlerC2688av extends Handler {
    /* JADX INFO: Access modifiers changed from: package-private */
    public HandlerC2688av(Looper looper) {
        super(looper);
    }

    @Override // android.os.Handler
    public void handleMessage(Message message) {
        Handler handler;
        C2685at.m16301a();
        handler = C2685at.f13550j;
        handler.sendEmptyMessageDelayed(0, WaitFor.ONE_HOUR);
    }
}
