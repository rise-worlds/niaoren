package com.cyjh.ddy.media.oksocket;

import com.cyjh.ddy.media.bean.CommandRequestInfo;
import com.xuhao.didi.p082a.p084b.p085a.IPulseSendable;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.charset.Charset;

/* renamed from: com.cyjh.ddy.media.oksocket.c */
/* loaded from: classes.dex */
public class PulseDataBean implements IPulseSendable {

    /* renamed from: a */
    private String f7397a;

    public PulseDataBean(String str) {
        this.f7397a = "";
        this.f7397a = SocketHelper.m21523a(new CommandRequestInfo(), 999, str);
    }

    @Override // com.xuhao.didi.p082a.p084b.p085a.ISendable
    /* renamed from: a */
    public byte[] mo15191a() {
        byte[] bytes = this.f7397a.getBytes(Charset.defaultCharset());
        ByteBuffer allocate = ByteBuffer.allocate(bytes.length + 4);
        allocate.order(ByteOrder.LITTLE_ENDIAN);
        allocate.putInt(bytes.length);
        allocate.put(bytes);
        return allocate.array();
    }
}
