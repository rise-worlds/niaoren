package com.tendcloud.tenddata;

import android.os.Handler;
import android.os.HandlerThread;

/* compiled from: td */
/* renamed from: com.tendcloud.tenddata.hx */
/* loaded from: classes2.dex */
final class C2958hx {

    /* renamed from: a */
    static final int f14219a = 101;

    /* renamed from: b */
    private static final HandlerThread f14220b = new HandlerThread("PushThreadProcess");

    /* renamed from: c */
    private static Handler f14221c;

    C2958hx() {
    }

    static {
        f14221c = null;
        f14220b.start();
        f14221c = new HandlerC2959hy(f14220b.getLooper());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public static Handler m15467a() {
        return f14221c;
    }
}
