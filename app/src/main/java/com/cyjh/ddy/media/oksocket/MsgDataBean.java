package com.cyjh.ddy.media.oksocket;

import com.xuhao.didi.p082a.p084b.p085a.ISendable;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.charset.Charset;

/* renamed from: com.cyjh.ddy.media.oksocket.b */
/* loaded from: classes.dex */
public class MsgDataBean implements ISendable {

    /* renamed from: a */
    private String f7396a;

    public MsgDataBean(String str) {
        this.f7396a = "";
        this.f7396a = str;
    }

    @Override // com.xuhao.didi.p082a.p084b.p085a.ISendable
    /* renamed from: a */
    public byte[] mo15191a() {
        byte[] bytes = this.f7396a.getBytes(Charset.defaultCharset());
        ByteBuffer allocate = ByteBuffer.allocate(bytes.length + 4);
        allocate.order(ByteOrder.LITTLE_ENDIAN);
        allocate.putInt(bytes.length);
        allocate.put(bytes);
        return allocate.array();
    }
}
