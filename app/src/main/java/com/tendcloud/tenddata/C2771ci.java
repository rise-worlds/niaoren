package com.tendcloud.tenddata;

import com.tendcloud.tenddata.AbstractC2765cf;
import com.tendcloud.tenddata.AbstractC2783cu;
import java.nio.ByteBuffer;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import org.apache.http.protocol.HTTP;

/* compiled from: td */
/* renamed from: com.tendcloud.tenddata.ci */
/* loaded from: classes2.dex */
public class C2771ci extends AbstractC2765cf {

    /* renamed from: f */
    public static final byte f13672f = 13;

    /* renamed from: g */
    public static final byte f13673g = 10;

    /* renamed from: h */
    public static final byte f13674h = 0;

    /* renamed from: i */
    public static final byte f13675i = -1;

    /* renamed from: l */
    protected ByteBuffer f13678l;

    /* renamed from: j */
    protected boolean f13676j = false;

    /* renamed from: k */
    protected List f13677k = new LinkedList();

    /* renamed from: m */
    private final Random f13679m = new Random();

    @Override // com.tendcloud.tenddata.AbstractC2765cf
    /* renamed from: a */
    public AbstractC2765cf.EnumC2767b mo16155a(AbstractC2786cw cwVar, AbstractC2794dd ddVar) {
        return (!cwVar.mo16133a("WebSocket-Origin").equals(ddVar.mo16133a("Origin")) || !m16176a(ddVar)) ? AbstractC2765cf.EnumC2767b.NOT_MATCHED : AbstractC2765cf.EnumC2767b.MATCHED;
    }

    @Override // com.tendcloud.tenddata.AbstractC2765cf
    /* renamed from: a */
    public AbstractC2765cf.EnumC2767b mo16156a(AbstractC2786cw cwVar) {
        if (!cwVar.mo16131b("Origin") || !m16176a((AbstractC2792db) cwVar)) {
            return AbstractC2765cf.EnumC2767b.NOT_MATCHED;
        }
        return AbstractC2765cf.EnumC2767b.MATCHED;
    }

    @Override // com.tendcloud.tenddata.AbstractC2765cf
    /* renamed from: a */
    public ByteBuffer mo16157a(AbstractC2783cu cuVar) {
        if (cuVar.mo16135f() == AbstractC2783cu.EnumC2784a.TEXT) {
            ByteBuffer c = cuVar.mo16138c();
            ByteBuffer allocate = ByteBuffer.allocate(c.remaining() + 2);
            allocate.put((byte) 0);
            c.mark();
            allocate.put(c);
            c.reset();
            allocate.put((byte) -1);
            allocate.flip();
            return allocate;
        }
        throw new RuntimeException("only text frames supported");
    }

    @Override // com.tendcloud.tenddata.AbstractC2765cf
    /* renamed from: a */
    public List mo16161a(ByteBuffer byteBuffer, boolean z) {
        throw new RuntimeException("not yet implemented");
    }

    @Override // com.tendcloud.tenddata.AbstractC2765cf
    /* renamed from: a */
    public List mo16162a(String str, boolean z) {
        C2785cv cvVar = new C2785cv();
        try {
            cvVar.setPayload(ByteBuffer.wrap(C2806dl.m16062a(str)));
            cvVar.setFin(true);
            cvVar.setOptcode(AbstractC2783cu.EnumC2784a.TEXT);
            cvVar.setTransferemasked(z);
            return Collections.singletonList(cvVar);
        } catch (C2774cl e) {
            throw new C2778cp(e);
        }
    }

    @Override // com.tendcloud.tenddata.AbstractC2765cf
    /* renamed from: a */
    public AbstractC2787cx mo16153a(AbstractC2787cx cxVar) {
        cxVar.mo16132a("Upgrade", "WebSocket");
        cxVar.mo16132a(HTTP.CONN_DIRECTIVE, "Upgrade");
        if (!cxVar.mo16131b("Origin")) {
            cxVar.mo16132a("Origin", "random" + this.f13679m.nextInt());
        }
        return cxVar;
    }

    @Override // com.tendcloud.tenddata.AbstractC2765cf
    /* renamed from: a */
    public AbstractC2788cy mo16154a(AbstractC2786cw cwVar, AbstractC2795de deVar) {
        deVar.setHttpStatusMessage("Web Socket Protocol Handshake");
        deVar.mo16132a("Upgrade", "WebSocket");
        deVar.mo16132a(HTTP.CONN_DIRECTIVE, cwVar.mo16133a(HTTP.CONN_DIRECTIVE));
        deVar.mo16132a("WebSocket-Origin", cwVar.mo16133a("Origin"));
        deVar.mo16132a("WebSocket-Location", "ws://" + cwVar.mo16133a(HTTP.TARGET_HOST) + cwVar.mo16134a());
        return deVar;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: e */
    public List m16159e(ByteBuffer byteBuffer) {
        while (byteBuffer.hasRemaining()) {
            byte b = byteBuffer.get();
            if (b == 0) {
                if (!this.f13676j) {
                    this.f13676j = true;
                } else {
                    throw new C2775cm("unexpected START_OF_FRAME");
                }
            } else if (b == -1) {
                if (this.f13676j) {
                    ByteBuffer byteBuffer2 = this.f13678l;
                    if (byteBuffer2 != null) {
                        byteBuffer2.flip();
                        C2785cv cvVar = new C2785cv();
                        cvVar.setPayload(this.f13678l);
                        cvVar.setFin(true);
                        cvVar.setOptcode(AbstractC2783cu.EnumC2784a.TEXT);
                        this.f13677k.add(cvVar);
                        this.f13678l = null;
                        byteBuffer.mark();
                    }
                    this.f13676j = false;
                } else {
                    throw new C2775cm("unexpected END_OF_FRAME");
                }
            } else if (!this.f13676j) {
                return null;
            } else {
                ByteBuffer byteBuffer3 = this.f13678l;
                if (byteBuffer3 == null) {
                    this.f13678l = m16160e();
                } else if (!byteBuffer3.hasRemaining()) {
                    this.f13678l = m16158f(this.f13678l);
                }
                this.f13678l.put(b);
            }
        }
        List list = this.f13677k;
        this.f13677k = new LinkedList();
        return list;
    }

    @Override // com.tendcloud.tenddata.AbstractC2765cf
    /* renamed from: c */
    public List mo16148c(ByteBuffer byteBuffer) {
        List e = m16159e(byteBuffer);
        if (e != null) {
            return e;
        }
        throw new C2774cl(1002);
    }

    @Override // com.tendcloud.tenddata.AbstractC2765cf
    /* renamed from: a */
    public void mo16163a() {
        this.f13676j = false;
        this.f13678l = null;
    }

    @Override // com.tendcloud.tenddata.AbstractC2765cf
    /* renamed from: b */
    public AbstractC2765cf.EnumC2766a mo16150b() {
        return AbstractC2765cf.EnumC2766a.NONE;
    }

    /* renamed from: e */
    public ByteBuffer m16160e() {
        return ByteBuffer.allocate(f13664b);
    }

    /* renamed from: f */
    public ByteBuffer m16158f(ByteBuffer byteBuffer) {
        byteBuffer.flip();
        ByteBuffer allocate = ByteBuffer.allocate(m16178a(byteBuffer.capacity() * 2));
        allocate.put(byteBuffer);
        return allocate;
    }

    @Override // com.tendcloud.tenddata.AbstractC2765cf
    /* renamed from: c */
    public AbstractC2765cf mo16149c() {
        return new C2771ci();
    }
}
