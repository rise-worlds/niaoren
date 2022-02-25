package com.tendcloud.tenddata;

import java.nio.ByteBuffer;

/* compiled from: td */
/* renamed from: com.tendcloud.tenddata.cu */
/* loaded from: classes2.dex */
public interface AbstractC2783cu {

    /* compiled from: td */
    /* renamed from: com.tendcloud.tenddata.cu$a */
    /* loaded from: classes2.dex */
    public enum EnumC2784a {
        CONTINUOUS,
        TEXT,
        BINARY,
        PING,
        PONG,
        CLOSING
    }

    void append(AbstractC2783cu cuVar);

    /* renamed from: c */
    ByteBuffer mo16138c();

    /* renamed from: d */
    boolean mo16137d();

    /* renamed from: e */
    boolean mo16136e();

    /* renamed from: f */
    EnumC2784a mo16135f();
}
