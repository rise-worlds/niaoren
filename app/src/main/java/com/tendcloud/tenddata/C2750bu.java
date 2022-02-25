package com.tendcloud.tenddata;

import android.annotation.SuppressLint;
import java.io.IOException;
import java.net.Socket;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ByteChannel;
import java.nio.channels.SelectableChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import javax.net.ssl.SSLEngine;
import javax.net.ssl.SSLEngineResult;
import javax.net.ssl.SSLSession;

/* compiled from: td */
@SuppressLint({"Assert"})
/* renamed from: com.tendcloud.tenddata.bu */
/* loaded from: classes2.dex */
public class C2750bu implements AbstractC2760cb, ByteChannel {

    /* renamed from: b */
    protected ExecutorService f13627b;

    /* renamed from: c */
    protected List f13628c;

    /* renamed from: d */
    protected ByteBuffer f13629d;

    /* renamed from: e */
    protected ByteBuffer f13630e;

    /* renamed from: f */
    protected ByteBuffer f13631f;

    /* renamed from: g */
    protected SocketChannel f13632g;

    /* renamed from: h */
    protected SelectionKey f13633h;

    /* renamed from: i */
    protected SSLEngine f13634i;

    /* renamed from: j */
    protected SSLEngineResult f13635j;

    /* renamed from: k */
    protected SSLEngineResult f13636k;

    /* renamed from: l */
    protected int f13637l = 0;

    /* renamed from: m */
    static final /* synthetic */ boolean f13626m = !C2750bu.class.desiredAssertionStatus();

    /* renamed from: a */
    protected static ByteBuffer f13625a = ByteBuffer.allocate(0);

    public C2750bu(SocketChannel socketChannel, SSLEngine sSLEngine, ExecutorService executorService, SelectionKey selectionKey) {
        if (socketChannel == null || sSLEngine == null || executorService == null) {
            throw new IllegalArgumentException("parameter must not be null");
        }
        this.f13632g = socketChannel;
        this.f13634i = sSLEngine;
        this.f13627b = executorService;
        SSLEngineResult sSLEngineResult = new SSLEngineResult(SSLEngineResult.Status.BUFFER_UNDERFLOW, sSLEngine.getHandshakeStatus(), 0, 0);
        this.f13636k = sSLEngineResult;
        this.f13635j = sSLEngineResult;
        this.f13628c = new ArrayList(3);
        if (selectionKey != null) {
            selectionKey.interestOps(selectionKey.interestOps() | 4);
            this.f13633h = selectionKey;
        }
        m16212a(sSLEngine.getSession());
        this.f13632g.write(m16210b(f13625a));
        m16203j();
    }

    /* renamed from: a */
    private void m16213a(Future future) {
        boolean z = false;
        while (true) {
            try {
                try {
                    future.get();
                    break;
                } catch (InterruptedException unused) {
                    z = true;
                }
            } catch (ExecutionException e) {
                throw new RuntimeException(e);
            }
        }
        if (z) {
            Thread.currentThread().interrupt();
        }
    }

    /* renamed from: j */
    private synchronized void m16203j() {
        if (this.f13634i.getHandshakeStatus() != SSLEngineResult.HandshakeStatus.NOT_HANDSHAKING) {
            if (!this.f13628c.isEmpty()) {
                Iterator it = this.f13628c.iterator();
                while (it.hasNext()) {
                    Future future = (Future) it.next();
                    if (future.isDone()) {
                        it.remove();
                    } else {
                        if (mo16180d()) {
                            m16213a(future);
                        }
                        return;
                    }
                }
            }
            if (this.f13634i.getHandshakeStatus() == SSLEngineResult.HandshakeStatus.NEED_UNWRAP) {
                if (!mo16180d() || this.f13635j.getStatus() == SSLEngineResult.Status.BUFFER_UNDERFLOW) {
                    this.f13631f.compact();
                    if (this.f13632g.read(this.f13631f) != -1) {
                        this.f13631f.flip();
                    } else {
                        throw new IOException("connection closed unexpectedly by peer");
                    }
                }
                this.f13629d.compact();
                m16202k();
                if (this.f13635j.getHandshakeStatus() == SSLEngineResult.HandshakeStatus.FINISHED) {
                    m16212a(this.f13634i.getSession());
                    return;
                }
            }
            m16208e();
            if (this.f13628c.isEmpty() || this.f13634i.getHandshakeStatus() == SSLEngineResult.HandshakeStatus.NEED_WRAP) {
                this.f13632g.write(m16210b(f13625a));
                if (this.f13636k.getHandshakeStatus() == SSLEngineResult.HandshakeStatus.FINISHED) {
                    m16212a(this.f13634i.getSession());
                    return;
                }
            }
            if (!f13626m && this.f13634i.getHandshakeStatus() == SSLEngineResult.HandshakeStatus.NOT_HANDSHAKING) {
                throw new AssertionError();
            }
            this.f13637l = 1;
        }
    }

    /* renamed from: b */
    private synchronized ByteBuffer m16210b(ByteBuffer byteBuffer) {
        this.f13630e.compact();
        this.f13636k = this.f13634i.wrap(byteBuffer, this.f13630e);
        this.f13630e.flip();
        return this.f13630e;
    }

    /* renamed from: k */
    private synchronized ByteBuffer m16202k() {
        while (true) {
            int remaining = this.f13629d.remaining();
            this.f13635j = this.f13634i.unwrap(this.f13631f, this.f13629d);
            if (this.f13635j.getStatus() != SSLEngineResult.Status.OK || (remaining == this.f13629d.remaining() && this.f13634i.getHandshakeStatus() != SSLEngineResult.HandshakeStatus.NEED_UNWRAP)) {
                break;
            }
        }
        this.f13629d.flip();
        return this.f13629d;
    }

    /* renamed from: e */
    protected void m16208e() {
        while (true) {
            Runnable delegatedTask = this.f13634i.getDelegatedTask();
            if (delegatedTask != null) {
                this.f13628c.add(this.f13627b.submit(delegatedTask));
            } else {
                return;
            }
        }
    }

    /* renamed from: a */
    protected void m16212a(SSLSession sSLSession) {
        int applicationBufferSize = sSLSession.getApplicationBufferSize();
        int packetBufferSize = sSLSession.getPacketBufferSize();
        ByteBuffer byteBuffer = this.f13629d;
        if (byteBuffer == null) {
            this.f13629d = ByteBuffer.allocate(applicationBufferSize);
            this.f13630e = ByteBuffer.allocate(packetBufferSize);
            this.f13631f = ByteBuffer.allocate(packetBufferSize);
        } else {
            if (byteBuffer.capacity() != applicationBufferSize) {
                this.f13629d = ByteBuffer.allocate(applicationBufferSize);
            }
            if (this.f13630e.capacity() != packetBufferSize) {
                this.f13630e = ByteBuffer.allocate(packetBufferSize);
            }
            if (this.f13631f.capacity() != packetBufferSize) {
                this.f13631f = ByteBuffer.allocate(packetBufferSize);
            }
        }
        this.f13629d.rewind();
        this.f13629d.flip();
        this.f13631f.rewind();
        this.f13631f.flip();
        this.f13630e.rewind();
        this.f13630e.flip();
        this.f13637l++;
    }

    @Override // java.nio.channels.WritableByteChannel
    public int write(ByteBuffer byteBuffer) {
        if (!m16201l()) {
            m16203j();
            return 0;
        }
        if (this.f13637l <= 1) {
            m16212a(this.f13634i.getSession());
        }
        return this.f13632g.write(m16210b(byteBuffer));
    }

    @Override // java.nio.channels.ReadableByteChannel
    public int read(ByteBuffer byteBuffer) {
        if (!byteBuffer.hasRemaining()) {
            return 0;
        }
        if (!m16201l()) {
            if (mo16180d()) {
                while (!m16201l()) {
                    m16203j();
                }
            } else {
                m16203j();
                if (!m16201l()) {
                    return 0;
                }
            }
        }
        if (this.f13637l <= 1) {
            m16212a(this.f13634i.getSession());
        }
        int c = m16209c(byteBuffer);
        if (c != 0) {
            return c;
        }
        if (f13626m || this.f13629d.position() == 0) {
            this.f13629d.clear();
            if (!this.f13631f.hasRemaining()) {
                this.f13631f.clear();
            } else {
                this.f13631f.compact();
            }
            if ((mo16180d() || this.f13635j.getStatus() == SSLEngineResult.Status.BUFFER_UNDERFLOW) && this.f13632g.read(this.f13631f) == -1) {
                return -1;
            }
            this.f13631f.flip();
            m16202k();
            int a = m16214a(this.f13629d, byteBuffer);
            return (a != 0 || !mo16180d()) ? a : read(byteBuffer);
        }
        throw new AssertionError();
    }

    /* renamed from: c */
    private int m16209c(ByteBuffer byteBuffer) {
        if (this.f13629d.hasRemaining()) {
            return m16214a(this.f13629d, byteBuffer);
        }
        if (!this.f13629d.hasRemaining()) {
            this.f13629d.clear();
        }
        if (!this.f13631f.hasRemaining()) {
            return 0;
        }
        m16202k();
        int a = m16214a(this.f13629d, byteBuffer);
        if (a > 0) {
            return a;
        }
        return 0;
    }

    /* renamed from: f */
    public boolean m16207f() {
        return this.f13632g.isConnected();
    }

    @Override // java.nio.channels.Channel, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        this.f13634i.closeOutbound();
        this.f13634i.getSession().invalidate();
        if (this.f13632g.isOpen()) {
            this.f13632g.write(m16210b(f13625a));
        }
        this.f13632g.close();
        this.f13627b.shutdownNow();
    }

    /* renamed from: l */
    private boolean m16201l() {
        SSLEngineResult.HandshakeStatus handshakeStatus = this.f13634i.getHandshakeStatus();
        return handshakeStatus == SSLEngineResult.HandshakeStatus.FINISHED || handshakeStatus == SSLEngineResult.HandshakeStatus.NOT_HANDSHAKING;
    }

    /* renamed from: a */
    public SelectableChannel m16211a(boolean z) {
        return this.f13632g.configureBlocking(z);
    }

    /* renamed from: a */
    public boolean m16215a(SocketAddress socketAddress) {
        return this.f13632g.connect(socketAddress);
    }

    /* renamed from: g */
    public boolean m16206g() {
        return this.f13632g.finishConnect();
    }

    /* renamed from: h */
    public Socket m16205h() {
        return this.f13632g.socket();
    }

    /* renamed from: i */
    public boolean m16204i() {
        return this.f13634i.isInboundDone();
    }

    @Override // java.nio.channels.Channel
    public boolean isOpen() {
        return this.f13632g.isOpen();
    }

    @Override // com.tendcloud.tenddata.AbstractC2760cb
    /* renamed from: a */
    public boolean mo16184a() {
        return this.f13630e.hasRemaining() || !m16201l();
    }

    @Override // com.tendcloud.tenddata.AbstractC2760cb
    /* renamed from: b */
    public void mo16182b() {
        write(this.f13630e);
    }

    @Override // com.tendcloud.tenddata.AbstractC2760cb
    /* renamed from: c */
    public boolean mo16181c() {
        return this.f13629d.hasRemaining() || !(!this.f13631f.hasRemaining() || this.f13635j.getStatus() == SSLEngineResult.Status.BUFFER_UNDERFLOW || this.f13635j.getStatus() == SSLEngineResult.Status.CLOSED);
    }

    @Override // com.tendcloud.tenddata.AbstractC2760cb
    /* renamed from: a */
    public int mo16183a(ByteBuffer byteBuffer) {
        return m16209c(byteBuffer);
    }

    /* renamed from: a */
    private int m16214a(ByteBuffer byteBuffer, ByteBuffer byteBuffer2) {
        int remaining = byteBuffer.remaining();
        int remaining2 = byteBuffer2.remaining();
        if (remaining > remaining2) {
            int min = Math.min(remaining, remaining2);
            for (int i = 0; i < min; i++) {
                byteBuffer2.put(byteBuffer.get());
            }
            return min;
        }
        byteBuffer2.put(byteBuffer);
        return remaining;
    }

    @Override // com.tendcloud.tenddata.AbstractC2760cb
    /* renamed from: d */
    public boolean mo16180d() {
        return this.f13632g.isBlocking();
    }
}
