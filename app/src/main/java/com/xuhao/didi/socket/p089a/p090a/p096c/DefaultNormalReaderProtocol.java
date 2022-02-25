package com.xuhao.didi.socket.p089a.p090a.p096c;

import com.xuhao.didi.p082a.p087d.IReaderProtocol;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/* renamed from: com.xuhao.didi.socket.a.a.c.a */
/* loaded from: classes2.dex */
public class DefaultNormalReaderProtocol implements IReaderProtocol {
    @Override // com.xuhao.didi.p082a.p087d.IReaderProtocol
    /* renamed from: a */
    public int mo15148a() {
        return 4;
    }

    @Override // com.xuhao.didi.p082a.p087d.IReaderProtocol
    /* renamed from: a */
    public int mo15147a(byte[] bArr, ByteOrder byteOrder) {
        if (bArr == null || bArr.length < mo15148a()) {
            return 0;
        }
        ByteBuffer wrap = ByteBuffer.wrap(bArr);
        wrap.order(byteOrder);
        return wrap.getInt();
    }
}
