package com.tendcloud.tenddata;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.tendcloud.tenddata.C2962ia;

/* compiled from: td */
/* renamed from: com.tendcloud.tenddata.hy */
/* loaded from: classes2.dex */
final class HandlerC2959hy extends Handler {
    /* JADX INFO: Access modifiers changed from: package-private */
    public HandlerC2959hy(Looper looper) {
        super(looper);
    }

    @Override // android.os.Handler
    public void handleMessage(Message message) {
        if (message.obj != null && (message.obj instanceof C2962ia.C2963a)) {
            try {
                C2858ev.m15778a().post((C2962ia.C2963a) message.obj);
            } catch (Throwable unused) {
            }
        }
    }
}
