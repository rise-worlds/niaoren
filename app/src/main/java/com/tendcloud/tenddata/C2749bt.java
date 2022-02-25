package com.tendcloud.tenddata;

import java.nio.ByteBuffer;
import java.nio.channels.ByteChannel;
import java.nio.channels.SocketChannel;

/* compiled from: td */
/* renamed from: com.tendcloud.tenddata.bt */
/* loaded from: classes2.dex */
public class C2749bt implements AbstractC2760cb {

    /* renamed from: a */
    private final ByteChannel f13624a;

    public C2749bt(ByteChannel byteChannel) {
        this.f13624a = byteChannel;
    }

    public C2749bt(AbstractC2760cb cbVar) {
        this.f13624a = cbVar;
    }

    @Override // java.nio.channels.ReadableByteChannel
    public int read(ByteBuffer byteBuffer) {
        return this.f13624a.read(byteBuffer);
    }

    @Override // java.nio.channels.Channel
    public boolean isOpen() {
        return this.f13624a.isOpen();
    }

    @Override // java.nio.channels.Channel, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        this.f13624a.close();
    }

    @Override // java.nio.channels.WritableByteChannel
    public int write(ByteBuffer byteBuffer) {
        return this.f13624a.write(byteBuffer);
    }

    @Override // com.tendcloud.tenddata.AbstractC2760cb
    /* renamed from: a */
    public boolean mo16184a() {
        ByteChannel byteChannel = this.f13624a;
        if (byteChannel instanceof AbstractC2760cb) {
            return ((AbstractC2760cb) byteChannel).mo16184a();
        }
        return false;
    }

    @Override // com.tendcloud.tenddata.AbstractC2760cb
    /* renamed from: b */
    public void mo16182b() {
        ByteChannel byteChannel = this.f13624a;
        if (byteChannel instanceof AbstractC2760cb) {
            ((AbstractC2760cb) byteChannel).mo16182b();
        }
    }

    @Override // com.tendcloud.tenddata.AbstractC2760cb
    /* renamed from: c */
    public boolean mo16181c() {
        ByteChannel byteChannel = this.f13624a;
        if (byteChannel instanceof AbstractC2760cb) {
            return ((AbstractC2760cb) byteChannel).mo16181c();
        }
        return false;
    }

    @Override // com.tendcloud.tenddata.AbstractC2760cb
    /* renamed from: a */
    public int mo16183a(ByteBuffer byteBuffer) {
        ByteChannel byteChannel = this.f13624a;
        if (byteChannel instanceof AbstractC2760cb) {
            return ((AbstractC2760cb) byteChannel).mo16183a(byteBuffer);
        }
        return 0;
    }

    @Override // com.tendcloud.tenddata.AbstractC2760cb
    /* renamed from: d */
    public boolean mo16180d() {
        ByteChannel byteChannel = this.f13624a;
        if (byteChannel instanceof SocketChannel) {
            return ((SocketChannel) byteChannel).isBlocking();
        }
        if (byteChannel instanceof AbstractC2760cb) {
            return ((AbstractC2760cb) byteChannel).mo16180d();
        }
        return false;
    }
}
