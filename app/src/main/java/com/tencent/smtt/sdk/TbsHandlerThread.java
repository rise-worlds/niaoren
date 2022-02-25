package com.tencent.smtt.sdk;

import android.os.HandlerThread;

/* renamed from: com.tencent.smtt.sdk.k */
/* loaded from: classes2.dex */
class TbsHandlerThread extends HandlerThread {

    /* renamed from: a */
    private static TbsHandlerThread f13199a;

    public TbsHandlerThread(String str) {
        super(str);
    }

    /* renamed from: a */
    public static synchronized TbsHandlerThread m16748a() {
        TbsHandlerThread kVar;
        synchronized (TbsHandlerThread.class) {
            if (f13199a == null) {
                f13199a = new TbsHandlerThread("TbsHandlerThread");
                f13199a.start();
            }
            kVar = f13199a;
        }
        return kVar;
    }
}
