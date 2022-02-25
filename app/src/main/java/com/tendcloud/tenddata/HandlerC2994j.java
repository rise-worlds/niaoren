package com.tendcloud.tenddata;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.tendcloud.tenddata.C3034zz;

/* compiled from: td */
/* renamed from: com.tendcloud.tenddata.j */
/* loaded from: classes2.dex */
final class HandlerC2994j extends Handler {
    /* JADX INFO: Access modifiers changed from: package-private */
    public HandlerC2994j(Looper looper) {
        super(looper);
    }

    @Override // android.os.Handler
    public void handleMessage(Message message) {
        C2969ig.m15443a();
        C2966id.m15457a();
        if (message.obj != null && (message.obj instanceof C3034zz.C3035a)) {
            try {
                C2858ev.m15778a().post((C3034zz.C3035a) message.obj);
            } catch (Throwable unused) {
            }
        }
    }
}
