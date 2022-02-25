package com.tendcloud.tenddata;

import android.annotation.SuppressLint;
import com.tendcloud.tenddata.AbstractC2752bw;
import com.tendcloud.tenddata.AbstractC2765cf;
import com.tendcloud.tenddata.AbstractC2783cu;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import org.apache.http.protocol.HTTP;
import p110z1.cin;

/* compiled from: td */
@SuppressLint({"Assert", "UseValueOf"})
/* renamed from: com.tendcloud.tenddata.cg */
/* loaded from: classes2.dex */
public class C2768cg extends AbstractC2765cf {

    /* renamed from: f */
    static final /* synthetic */ boolean f13668f = !C2768cg.class.desiredAssertionStatus();

    /* renamed from: g */
    private ByteBuffer f13669g;

    /* renamed from: h */
    private AbstractC2783cu f13670h = null;

    /* renamed from: i */
    private final Random f13671i = new Random();

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: td */
    /* renamed from: com.tendcloud.tenddata.cg$a */
    /* loaded from: classes2.dex */
    public class C2769a extends Throwable {
        private static final long serialVersionUID = 7330519489840500997L;
        private int preferedsize;

        public C2769a(int i) {
            this.preferedsize = i;
        }

        public int getPreferedSize() {
            return this.preferedsize;
        }
    }

    /* renamed from: b */
    public static int m16165b(AbstractC2792db dbVar) {
        String a = dbVar.mo16133a("Sec-WebSocket-Version");
        if (a.length() <= 0) {
            return -1;
        }
        try {
            return new Integer(a.trim()).intValue();
        } catch (NumberFormatException unused) {
            return -1;
        }
    }

    @Override // com.tendcloud.tenddata.AbstractC2765cf
    /* renamed from: a */
    public AbstractC2765cf.EnumC2767b mo16155a(AbstractC2786cw cwVar, AbstractC2794dd ddVar) {
        if (!cwVar.mo16131b("Sec-WebSocket-Key") || !ddVar.mo16131b("Sec-WebSocket-Accept")) {
            return AbstractC2765cf.EnumC2767b.NOT_MATCHED;
        }
        if (m16166a(cwVar.mo16133a("Sec-WebSocket-Key")).equals(ddVar.mo16133a("Sec-WebSocket-Accept"))) {
            return AbstractC2765cf.EnumC2767b.MATCHED;
        }
        return AbstractC2765cf.EnumC2767b.NOT_MATCHED;
    }

    @Override // com.tendcloud.tenddata.AbstractC2765cf
    /* renamed from: a */
    public AbstractC2765cf.EnumC2767b mo16156a(AbstractC2786cw cwVar) {
        int b = m16165b(cwVar);
        if (b == 7 || b == 8) {
            return m16176a((AbstractC2792db) cwVar) ? AbstractC2765cf.EnumC2767b.MATCHED : AbstractC2765cf.EnumC2767b.NOT_MATCHED;
        }
        return AbstractC2765cf.EnumC2767b.NOT_MATCHED;
    }

    @Override // com.tendcloud.tenddata.AbstractC2765cf
    /* renamed from: a */
    public ByteBuffer mo16157a(AbstractC2783cu cuVar) {
        ByteBuffer c = cuVar.mo16138c();
        int i = 0;
        boolean z = this.f13666d == AbstractC2752bw.EnumC2754b.CLIENT;
        int i2 = c.remaining() <= 125 ? 1 : c.remaining() <= 65535 ? 2 : 8;
        ByteBuffer allocate = ByteBuffer.allocate((i2 > 1 ? i2 + 1 : i2) + 1 + (z ? 4 : 0) + c.remaining());
        byte a = m16167a(cuVar.mo16135f());
        boolean d = cuVar.mo16137d();
        byte b = cin.f20709a;
        allocate.put((byte) (((byte) (d ? -128 : 0)) | a));
        byte[] a2 = m16168a(c.remaining(), i2);
        if (f13668f || a2.length == i2) {
            if (i2 == 1) {
                byte b2 = a2[0];
                if (!z) {
                    b = 0;
                }
                allocate.put((byte) (b2 | b));
            } else if (i2 == 2) {
                if (!z) {
                    b = 0;
                }
                allocate.put((byte) (b | 126));
                allocate.put(a2);
            } else if (i2 == 8) {
                if (!z) {
                    b = 0;
                }
                allocate.put((byte) (b | cin.f20710b));
                allocate.put(a2);
            } else {
                throw new RuntimeException("Size representation not supported/specified");
            }
            if (z) {
                ByteBuffer allocate2 = ByteBuffer.allocate(4);
                allocate2.putInt(this.f13671i.nextInt());
                allocate.put(allocate2.array());
                while (c.hasRemaining()) {
                    allocate.put((byte) (c.get() ^ allocate2.get(i % 4)));
                    i++;
                }
            } else {
                allocate.put(c);
            }
            if (f13668f || allocate.remaining() == 0) {
                allocate.flip();
                return allocate;
            }
            throw new AssertionError(allocate.remaining());
        }
        throw new AssertionError();
    }

    @Override // com.tendcloud.tenddata.AbstractC2765cf
    /* renamed from: a */
    public List mo16161a(ByteBuffer byteBuffer, boolean z) {
        C2785cv cvVar = new C2785cv();
        try {
            cvVar.setPayload(byteBuffer);
            cvVar.setFin(true);
            cvVar.setOptcode(AbstractC2783cu.EnumC2784a.BINARY);
            cvVar.setTransferemasked(z);
            return Collections.singletonList(cvVar);
        } catch (C2774cl e) {
            throw new C2778cp(e);
        }
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

    /* renamed from: a */
    private byte m16167a(AbstractC2783cu.EnumC2784a aVar) {
        if (aVar == AbstractC2783cu.EnumC2784a.CONTINUOUS) {
            return (byte) 0;
        }
        if (aVar == AbstractC2783cu.EnumC2784a.TEXT) {
            return (byte) 1;
        }
        if (aVar == AbstractC2783cu.EnumC2784a.BINARY) {
            return (byte) 2;
        }
        if (aVar == AbstractC2783cu.EnumC2784a.CLOSING) {
            return (byte) 8;
        }
        if (aVar == AbstractC2783cu.EnumC2784a.PING) {
            return (byte) 9;
        }
        if (aVar == AbstractC2783cu.EnumC2784a.PONG) {
            return (byte) 10;
        }
        throw new RuntimeException("Don't know how to handle " + aVar.toString());
    }

    /* renamed from: a */
    private String m16166a(String str) {
        try {
            return C2802dj.m16084a(MessageDigest.getInstance("SHA1").digest((str.trim() + "258EAFA5-E914-47DA-95CA-C5AB0DC85B11").getBytes()));
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    @Override // com.tendcloud.tenddata.AbstractC2765cf
    /* renamed from: a */
    public AbstractC2787cx mo16153a(AbstractC2787cx cxVar) {
        cxVar.mo16132a("Upgrade", "websocket");
        cxVar.mo16132a(HTTP.CONN_DIRECTIVE, "Upgrade");
        cxVar.mo16132a("Sec-WebSocket-Version", "8");
        byte[] bArr = new byte[16];
        this.f13671i.nextBytes(bArr);
        cxVar.mo16132a("Sec-WebSocket-Key", C2802dj.m16084a(bArr));
        return cxVar;
    }

    @Override // com.tendcloud.tenddata.AbstractC2765cf
    /* renamed from: a */
    public AbstractC2788cy mo16154a(AbstractC2786cw cwVar, AbstractC2795de deVar) {
        deVar.mo16132a("Upgrade", "websocket");
        deVar.mo16132a(HTTP.CONN_DIRECTIVE, cwVar.mo16133a(HTTP.CONN_DIRECTIVE));
        deVar.setHttpStatusMessage("Switching Protocols");
        String a = cwVar.mo16133a("Sec-WebSocket-Key");
        if (a != null) {
            deVar.mo16132a("Sec-WebSocket-Accept", m16166a(a));
            return deVar;
        }
        throw new C2776cn("missing Sec-WebSocket-Key");
    }

    /* renamed from: a */
    private byte[] m16168a(long j, int i) {
        byte[] bArr = new byte[i];
        int i2 = (i * 8) - 8;
        for (int i3 = 0; i3 < i; i3++) {
            bArr[i3] = (byte) (j >>> (i2 - (i3 * 8)));
        }
        return bArr;
    }

    /* renamed from: a */
    private AbstractC2783cu.EnumC2784a m16169a(byte b) {
        switch (b) {
            case 0:
                return AbstractC2783cu.EnumC2784a.CONTINUOUS;
            case 1:
                return AbstractC2783cu.EnumC2784a.TEXT;
            case 2:
                return AbstractC2783cu.EnumC2784a.BINARY;
            default:
                switch (b) {
                    case 8:
                        return AbstractC2783cu.EnumC2784a.CLOSING;
                    case 9:
                        return AbstractC2783cu.EnumC2784a.PING;
                    case 10:
                        return AbstractC2783cu.EnumC2784a.PONG;
                    default:
                        throw new C2775cm("unknow optcode " + ((int) b));
                }
        }
    }

    @Override // com.tendcloud.tenddata.AbstractC2765cf
    /* renamed from: c */
    public List mo16148c(ByteBuffer byteBuffer) {
        LinkedList linkedList = new LinkedList();
        if (this.f13669g != null) {
            try {
                byteBuffer.mark();
                int remaining = byteBuffer.remaining();
                int remaining2 = this.f13669g.remaining();
                if (remaining2 > remaining) {
                    this.f13669g.put(byteBuffer.array(), byteBuffer.position(), remaining);
                    byteBuffer.position(byteBuffer.position() + remaining);
                    return Collections.emptyList();
                }
                this.f13669g.put(byteBuffer.array(), byteBuffer.position(), remaining2);
                byteBuffer.position(byteBuffer.position() + remaining2);
                linkedList.add(m16164e((ByteBuffer) this.f13669g.duplicate().position(0)));
                this.f13669g = null;
            } catch (C2769a e) {
                this.f13669g.limit();
                ByteBuffer allocate = ByteBuffer.allocate(m16178a(e.getPreferedSize()));
                if (f13668f || allocate.limit() > this.f13669g.limit()) {
                    this.f13669g.rewind();
                    allocate.put(this.f13669g);
                    this.f13669g = allocate;
                    return mo16148c(byteBuffer);
                }
                throw new AssertionError();
            }
        }
        while (byteBuffer.hasRemaining()) {
            byteBuffer.mark();
            try {
                linkedList.add(m16164e(byteBuffer));
            } catch (C2769a e2) {
                byteBuffer.reset();
                this.f13669g = ByteBuffer.allocate(m16178a(e2.getPreferedSize()));
                this.f13669g.put(byteBuffer);
            }
        }
        return linkedList;
    }

    /* renamed from: e */
    public AbstractC2783cu m16164e(ByteBuffer byteBuffer) {
        AbstractC2782ct ctVar;
        int remaining = byteBuffer.remaining();
        int i = 2;
        if (remaining >= 2) {
            byte b = byteBuffer.get();
            boolean z = (b >> 8) != 0;
            byte b2 = (byte) ((b & cin.f20710b) >> 4);
            if (b2 == 0) {
                byte b3 = byteBuffer.get();
                boolean z2 = (b3 & cin.f20709a) != 0;
                int i2 = (byte) (b3 & cin.f20710b);
                AbstractC2783cu.EnumC2784a a = m16169a((byte) (b & 15));
                if (z || !(a == AbstractC2783cu.EnumC2784a.PING || a == AbstractC2783cu.EnumC2784a.PONG || a == AbstractC2783cu.EnumC2784a.CLOSING)) {
                    if (i2 < 0 || i2 > 125) {
                        if (a == AbstractC2783cu.EnumC2784a.PING || a == AbstractC2783cu.EnumC2784a.PONG || a == AbstractC2783cu.EnumC2784a.CLOSING) {
                            throw new C2775cm("more than 125 octets");
                        } else if (i2 != 126) {
                            i = 10;
                            if (remaining >= 10) {
                                byte[] bArr = new byte[8];
                                for (int i3 = 0; i3 < 8; i3++) {
                                    bArr[i3] = byteBuffer.get();
                                }
                                long longValue = new BigInteger(bArr).longValue();
                                if (longValue <= 2147483647L) {
                                    i2 = (int) longValue;
                                } else {
                                    throw new C2777co("Payloadsize is to big...");
                                }
                            } else {
                                throw new C2769a(10);
                            }
                        } else if (remaining >= 4) {
                            byte[] bArr2 = new byte[3];
                            bArr2[1] = byteBuffer.get();
                            bArr2[2] = byteBuffer.get();
                            i2 = new BigInteger(bArr2).intValue();
                            i = 4;
                        } else {
                            throw new C2769a(4);
                        }
                    }
                    int i4 = i + (z2 ? 4 : 0) + i2;
                    if (remaining >= i4) {
                        ByteBuffer allocate = ByteBuffer.allocate(m16178a(i2));
                        if (z2) {
                            byte[] bArr3 = new byte[4];
                            byteBuffer.get(bArr3);
                            for (int i5 = 0; i5 < i2; i5++) {
                                allocate.put((byte) (byteBuffer.get() ^ bArr3[i5 % 4]));
                            }
                        } else {
                            allocate.put(byteBuffer.array(), byteBuffer.position(), allocate.limit());
                            byteBuffer.position(byteBuffer.position() + allocate.limit());
                        }
                        if (a == AbstractC2783cu.EnumC2784a.CLOSING) {
                            ctVar = new C2781cs();
                        } else {
                            ctVar = new C2785cv();
                            ctVar.setFin(z);
                            ctVar.setOptcode(a);
                        }
                        allocate.flip();
                        ctVar.setPayload(allocate);
                        return ctVar;
                    }
                    throw new C2769a(i4);
                }
                throw new C2775cm("control frames may no be fragmented");
            }
            throw new C2775cm("bad rsv " + ((int) b2));
        }
        throw new C2769a(2);
    }

    @Override // com.tendcloud.tenddata.AbstractC2765cf
    /* renamed from: a */
    public void mo16163a() {
        this.f13669g = null;
    }

    @Override // com.tendcloud.tenddata.AbstractC2765cf
    /* renamed from: c */
    public AbstractC2765cf mo16149c() {
        return new C2768cg();
    }

    @Override // com.tendcloud.tenddata.AbstractC2765cf
    /* renamed from: b */
    public AbstractC2765cf.EnumC2766a mo16150b() {
        return AbstractC2765cf.EnumC2766a.TWOWAY;
    }
}
