package com.cyjh.mobileanjian.ipc.share.proto;

import java.nio.ByteBuffer;

/* renamed from: com.cyjh.mobileanjian.ipc.share.proto.c */
/* loaded from: classes.dex */
public abstract class ProtoBufDataWraper {
    /* renamed from: a */
    public abstract byte[] mo20932a();

    /* renamed from: b */
    private ByteBuffer m20931b() {
        int length = mo20932a().length;
        ByteBuffer wrap = ByteBuffer.wrap(new byte[length + 4]);
        wrap.putInt(length);
        wrap.put(mo20932a());
        wrap.flip();
        return wrap;
    }
}
