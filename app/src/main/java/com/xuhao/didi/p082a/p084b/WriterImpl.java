package com.xuhao.didi.p082a.p084b;

import com.xuhao.didi.p082a.p083a.WriteException;
import com.xuhao.didi.p082a.p084b.p085a.IIOCoreOptions;
import com.xuhao.didi.p082a.p084b.p085a.IOAction;
import com.xuhao.didi.p082a.p084b.p085a.IPulseSendable;
import com.xuhao.didi.p082a.p084b.p085a.ISendable;
import com.xuhao.didi.p082a.p084b.p085a.IStateSender;
import com.xuhao.didi.p082a.p084b.p085a.IWriter;
import com.xuhao.didi.p082a.p088e.BytesUtils;
import com.xuhao.didi.p082a.p088e.SLog;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.concurrent.LinkedBlockingQueue;

/* renamed from: com.xuhao.didi.a.b.c */
/* loaded from: classes2.dex */
public class WriterImpl implements IWriter<IIOCoreOptions> {

    /* renamed from: a */
    private volatile IIOCoreOptions f14367a;

    /* renamed from: b */
    private IStateSender f14368b;

    /* renamed from: c */
    private OutputStream f14369c;

    /* renamed from: d */
    private LinkedBlockingQueue<ISendable> f14370d = new LinkedBlockingQueue<>();

    @Override // com.xuhao.didi.p082a.p084b.p085a.IWriter
    /* renamed from: a */
    public void mo15184a(OutputStream outputStream, IStateSender fVar) {
        this.f14368b = fVar;
        this.f14369c = outputStream;
    }

    @Override // com.xuhao.didi.p082a.p084b.p085a.IWriter
    /* renamed from: a */
    public boolean mo15187a() throws RuntimeException {
        ISendable eVar;
        try {
            eVar = this.f14370d.take();
        } catch (InterruptedException unused) {
            eVar = null;
        }
        int i = 0;
        if (eVar == null) {
            return false;
        }
        try {
            byte[] a = eVar.mo15191a();
            int f = this.f14367a.mo15042f();
            int length = a.length;
            ByteBuffer allocate = ByteBuffer.allocate(f);
            allocate.order(this.f14367a.mo15046d());
            while (length > 0) {
                int min = Math.min(f, length);
                allocate.clear();
                allocate.rewind();
                allocate.put(a, i, min);
                allocate.flip();
                byte[] bArr = new byte[min];
                allocate.get(bArr);
                this.f14369c.write(bArr);
                this.f14369c.flush();
                if (SLog.m15177a()) {
                    byte[] copyOfRange = Arrays.copyOfRange(a, i, i + min);
                    SLog.m15174b("write bytes: " + BytesUtils.m15178a(copyOfRange));
                    SLog.m15174b("bytes write length:" + min);
                }
                i += min;
                length -= min;
            }
            if (eVar instanceof IPulseSendable) {
                this.f14368b.mo15087a(IOAction.f14365c, eVar);
                return true;
            }
            this.f14368b.mo15087a(IOAction.f14364b, eVar);
            return true;
        } catch (Exception e) {
            throw new WriteException(e);
        }
    }

    @Override // com.xuhao.didi.p082a.p084b.p085a.IWriter
    /* renamed from: a */
    public void mo15186a(IIOCoreOptions aVar) {
        this.f14367a = aVar;
    }

    @Override // com.xuhao.didi.p082a.p084b.p085a.IWriter
    /* renamed from: a */
    public void mo15185a(ISendable eVar) {
        this.f14370d.offer(eVar);
    }

    @Override // com.xuhao.didi.p082a.p084b.p085a.IWriter
    /* renamed from: b */
    public void mo15183b() {
        OutputStream outputStream = this.f14369c;
        if (outputStream != null) {
            try {
                outputStream.close();
            } catch (IOException unused) {
            }
        }
    }
}
