package com.xuhao.didi.p082a.p084b;

import com.xuhao.didi.p082a.p083a.ReadException;
import com.xuhao.didi.p082a.p084b.p085a.IOAction;
import com.xuhao.didi.p082a.p086c.OriginalData;
import com.xuhao.didi.p082a.p087d.IReaderProtocol;
import com.xuhao.didi.p082a.p088e.BytesUtils;
import com.xuhao.didi.p082a.p088e.SLog;
import java.io.IOException;
import java.nio.ByteBuffer;

/* renamed from: com.xuhao.didi.a.b.b */
/* loaded from: classes2.dex */
public class ReaderImpl extends AbsReader {

    /* renamed from: d */
    private ByteBuffer f14366d;

    @Override // com.xuhao.didi.p082a.p084b.p085a.IReader
    /* renamed from: b */
    public void mo15188b() throws RuntimeException {
        OriginalData aVar = new OriginalData();
        IReaderProtocol c = this.f14360a.mo15048c();
        int a = c.mo15148a();
        ByteBuffer allocate = ByteBuffer.allocate(a);
        allocate.order(this.f14360a.mo15064a());
        try {
            if (this.f14366d != null) {
                this.f14366d.flip();
                int min = Math.min(this.f14366d.remaining(), a);
                allocate.put(this.f14366d.array(), 0, min);
                if (min < a) {
                    this.f14366d = null;
                    m15189a(allocate, a - min);
                } else {
                    this.f14366d.position(a);
                }
            } else {
                m15189a(allocate, allocate.capacity());
            }
            aVar.m15181a(allocate.array());
            if (SLog.m15177a()) {
                SLog.m15174b("read head: " + BytesUtils.m15178a(allocate.array()));
            }
            int a2 = c.mo15147a(aVar.m15182a(), this.f14360a.mo15064a());
            if (SLog.m15177a()) {
                SLog.m15174b("need read body length: " + a2);
            }
            if (a2 > 0) {
                if (a2 <= this.f14360a.mo15052b() * 1024 * 1024) {
                    ByteBuffer allocate2 = ByteBuffer.allocate(a2);
                    allocate2.order(this.f14360a.mo15064a());
                    if (this.f14366d != null) {
                        int position = this.f14366d.position();
                        int min2 = Math.min(this.f14366d.remaining(), a2);
                        allocate2.put(this.f14366d.array(), position, min2);
                        this.f14366d.position(position + min2);
                        if (min2 == a2) {
                            if (this.f14366d.remaining() > 0) {
                                ByteBuffer allocate3 = ByteBuffer.allocate(this.f14366d.remaining());
                                allocate3.order(this.f14360a.mo15064a());
                                allocate3.put(this.f14366d.array(), this.f14366d.position(), this.f14366d.remaining());
                                this.f14366d = allocate3;
                            } else {
                                this.f14366d = null;
                            }
                            aVar.m15179b(allocate2.array());
                            this.f14361b.mo15087a(IOAction.f14363a, aVar);
                            return;
                        }
                        this.f14366d = null;
                    }
                    m15190a(allocate2);
                    aVar.m15179b(allocate2.array());
                } else {
                    throw new ReadException("Need to follow the transmission protocol.\r\nPlease check the client/server code.\r\nAccording to the packet header data in the transport protocol, the package length is " + a2 + " Bytes.\r\nYou need check your <ReaderProtocol> definition");
                }
            } else if (a2 == 0) {
                aVar.m15179b(new byte[0]);
                if (this.f14366d != null) {
                    if (this.f14366d.hasRemaining()) {
                        ByteBuffer allocate4 = ByteBuffer.allocate(this.f14366d.remaining());
                        allocate4.order(this.f14360a.mo15064a());
                        allocate4.put(this.f14366d.array(), this.f14366d.position(), this.f14366d.remaining());
                        this.f14366d = allocate4;
                    } else {
                        this.f14366d = null;
                    }
                }
            } else if (a2 < 0) {
                throw new ReadException("read body is wrong,this socket input stream is end of file read " + a2 + " ,that mean this socket is disconnected by server");
            }
            this.f14361b.mo15087a(IOAction.f14363a, aVar);
        } catch (Exception e) {
            throw new ReadException(e);
        }
    }

    /* renamed from: a */
    private void m15189a(ByteBuffer byteBuffer, int i) throws IOException {
        for (int i2 = 0; i2 < i; i2++) {
            byte[] bArr = new byte[1];
            int read = this.f14362c.read(bArr);
            if (read != -1) {
                byteBuffer.put(bArr);
            } else {
                throw new ReadException("read head is wrong, this socket input stream is end of file read " + read + " ,that mean this socket is disconnected by server");
            }
        }
    }

    /* renamed from: a */
    private void m15190a(ByteBuffer byteBuffer) throws IOException {
        while (byteBuffer.hasRemaining()) {
            try {
                byte[] bArr = new byte[this.f14360a.mo15044e()];
                int read = this.f14362c.read(bArr);
                if (read == -1) {
                    break;
                }
                int remaining = byteBuffer.remaining();
                if (read > remaining) {
                    byteBuffer.put(bArr, 0, remaining);
                    int i = read - remaining;
                    this.f14366d = ByteBuffer.allocate(i);
                    this.f14366d.order(this.f14360a.mo15064a());
                    this.f14366d.put(bArr, remaining, i);
                } else {
                    byteBuffer.put(bArr, 0, read);
                }
            } catch (Exception e) {
                throw e;
            }
        }
        if (SLog.m15177a()) {
            SLog.m15174b("read total bytes: " + BytesUtils.m15178a(byteBuffer.array()));
            SLog.m15174b("read total length:" + (byteBuffer.capacity() - byteBuffer.remaining()));
        }
    }
}
