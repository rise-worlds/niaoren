package com.tendcloud.tenddata;

import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.channels.ByteChannel;
import org.apache.http.protocol.HTTP;

/* compiled from: td */
/* renamed from: com.tendcloud.tenddata.cc */
/* loaded from: classes2.dex */
public abstract class AbstractC2761cc extends C2749bt {

    /* renamed from: a */
    protected final ByteBuffer f13662a;

    /* renamed from: e */
    public abstract String m16179e();

    public AbstractC2761cc(ByteChannel byteChannel) {
        super(byteChannel);
        try {
            this.f13662a = ByteBuffer.wrap(m16179e().getBytes(HTTP.ASCII));
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

    @Override // com.tendcloud.tenddata.C2749bt, java.nio.channels.WritableByteChannel
    public int write(ByteBuffer byteBuffer) {
        if (!this.f13662a.hasRemaining()) {
            return super.write(byteBuffer);
        }
        return super.write(this.f13662a);
    }
}
