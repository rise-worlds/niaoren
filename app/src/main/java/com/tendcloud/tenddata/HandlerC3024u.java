package com.tendcloud.tenddata;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.p003v4.app.NotificationCompat;
import com.tendcloud.tenddata.C3034zz;

/* compiled from: td */
/* renamed from: com.tendcloud.tenddata.u */
/* loaded from: classes2.dex */
final class HandlerC3024u extends Handler {
    /* JADX INFO: Access modifiers changed from: package-private */
    public HandlerC3024u(Looper looper) {
        super(looper);
    }

    @Override // android.os.Handler
    public void handleMessage(Message message) {
        try {
            AbstractC2790d dVar = (AbstractC2790d) message.obj;
            if (C2664ab.f13520n == 1) {
                C3034zz.C3035a aVar = new C3034zz.C3035a();
                aVar.paraMap.put("apiType", 11);
                aVar.paraMap.put("occurTime", String.valueOf(System.currentTimeMillis()));
                aVar.paraMap.put("sessionEnd", 1);
                aVar.paraMap.put(NotificationCompat.CATEGORY_SERVICE, dVar);
                Message.obtain(C3034zz.m15206c(), 102, aVar).sendToTarget();
                C2664ab.f13497N.set(true);
                C2664ab.f13520n = 2;
            }
        } catch (Exception e) {
            C2933hb.postSDKError(e);
        }
    }
}
