package com.xuhao.didi.socket.client.impl.client.iothreads;

import com.xuhao.didi.socket.client.sdk.client.OkSocketOptions;

/* loaded from: classes2.dex */
/* synthetic */ class IOThreadManager$1 {

    /* renamed from: a */
    static final /* synthetic */ int[] f14420a = new int[OkSocketOptions.IOThreadMode.values().length];

    static {
        try {
            f14420a[OkSocketOptions.IOThreadMode.DUPLEX.ordinal()] = 1;
        } catch (NoSuchFieldError unused) {
        }
        try {
            f14420a[OkSocketOptions.IOThreadMode.SIMPLEX.ordinal()] = 2;
        } catch (NoSuchFieldError unused2) {
        }
    }
}
