package com.tendcloud.tenddata;

import android.annotation.SuppressLint;
import com.ctetin.expandabletextviewlibrary.ExpandableTextView;
import com.tendcloud.tenddata.AbstractC2752bw;
import com.tendcloud.tenddata.AbstractC2765cf;
import com.tendcloud.tenddata.AbstractC2783cu;
import java.nio.BufferUnderflowException;
import java.nio.ByteBuffer;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import org.apache.http.protocol.HTTP;

/* compiled from: td */
@SuppressLint({"UseValueOf"})
/* renamed from: com.tendcloud.tenddata.cj */
/* loaded from: classes2.dex */
public class C2772cj extends C2771ci {

    /* renamed from: n */
    private static final byte[] f13680n = {-1, 0};

    /* renamed from: m */
    private boolean f13681m = false;

    /* renamed from: o */
    private final Random f13682o = new Random();

    /* renamed from: a */
    public static byte[] m16151a(String str, String str2, byte[] bArr) {
        byte[] a = m16152a(str);
        byte[] a2 = m16152a(str2);
        try {
            return MessageDigest.getInstance("MD5").digest(new byte[]{a[0], a[1], a[2], a[3], a2[0], a2[1], a2[2], a2[3], bArr[0], bArr[1], bArr[2], bArr[3], bArr[4], bArr[5], bArr[6], bArr[7]});
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    /* renamed from: f */
    private static String m16146f() {
        Random random = new Random();
        long nextInt = random.nextInt(12) + 1;
        String l = Long.toString((random.nextInt(Math.abs(new Long(4294967295L / nextInt).intValue())) + 1) * nextInt);
        int nextInt2 = random.nextInt(12) + 1;
        String str = l;
        for (int i = 0; i < nextInt2; i++) {
            int abs = Math.abs(random.nextInt(str.length()));
            char nextInt3 = (char) (random.nextInt(95) + 33);
            if (nextInt3 >= '0' && nextInt3 <= '9') {
                nextInt3 = (char) (nextInt3 - 15);
            }
            str = new StringBuilder(str).insert(abs, nextInt3).toString();
        }
        for (int i2 = 0; i2 < nextInt; i2++) {
            str = new StringBuilder(str).insert(Math.abs(random.nextInt(str.length() - 1) + 1), ExpandableTextView.f6958c).toString();
        }
        return str;
    }

    /* renamed from: a */
    private static byte[] m16152a(String str) {
        try {
            long parseLong = Long.parseLong(str.replaceAll("[^0-9]", ""));
            long length = str.split(ExpandableTextView.f6958c).length - 1;
            if (length != 0) {
                long longValue = new Long(parseLong / length).longValue();
                return new byte[]{(byte) (longValue >> 24), (byte) ((longValue << 8) >> 24), (byte) ((longValue << 16) >> 24), (byte) ((longValue << 24) >> 24)};
            }
            throw new C2776cn("invalid Sec-WebSocket-Key (/key2/)");
        } catch (NumberFormatException unused) {
            throw new C2776cn("invalid Sec-WebSocket-Key (/key1/ or /key2/)");
        }
    }

    @Override // com.tendcloud.tenddata.C2771ci, com.tendcloud.tenddata.AbstractC2765cf
    /* renamed from: a */
    public AbstractC2765cf.EnumC2767b mo16155a(AbstractC2786cw cwVar, AbstractC2794dd ddVar) {
        if (this.f13681m) {
            return AbstractC2765cf.EnumC2767b.NOT_MATCHED;
        }
        try {
            if (ddVar.mo16133a("Sec-WebSocket-Origin").equals(cwVar.mo16133a("Origin")) && m16176a(ddVar)) {
                byte[] d = ddVar.mo16129d();
                if (d == null || d.length == 0) {
                    throw new C2773ck();
                } else if (Arrays.equals(d, m16151a(cwVar.mo16133a("Sec-WebSocket-Key1"), cwVar.mo16133a("Sec-WebSocket-Key2"), cwVar.mo16129d()))) {
                    return AbstractC2765cf.EnumC2767b.MATCHED;
                } else {
                    return AbstractC2765cf.EnumC2767b.NOT_MATCHED;
                }
            }
            return AbstractC2765cf.EnumC2767b.NOT_MATCHED;
        } catch (C2776cn e) {
            throw new RuntimeException("bad handshakerequest", e);
        }
    }

    @Override // com.tendcloud.tenddata.C2771ci, com.tendcloud.tenddata.AbstractC2765cf
    /* renamed from: a */
    public AbstractC2765cf.EnumC2767b mo16156a(AbstractC2786cw cwVar) {
        if (!cwVar.mo16133a("Upgrade").equals("WebSocket") || !cwVar.mo16133a(HTTP.CONN_DIRECTIVE).contains("Upgrade") || cwVar.mo16133a("Sec-WebSocket-Key1").length() <= 0 || cwVar.mo16133a("Sec-WebSocket-Key2").isEmpty() || !cwVar.mo16131b("Origin")) {
            return AbstractC2765cf.EnumC2767b.NOT_MATCHED;
        }
        return AbstractC2765cf.EnumC2767b.MATCHED;
    }

    @Override // com.tendcloud.tenddata.C2771ci, com.tendcloud.tenddata.AbstractC2765cf
    /* renamed from: a */
    public AbstractC2787cx mo16153a(AbstractC2787cx cxVar) {
        cxVar.mo16132a("Upgrade", "WebSocket");
        cxVar.mo16132a(HTTP.CONN_DIRECTIVE, "Upgrade");
        cxVar.mo16132a("Sec-WebSocket-Key1", m16146f());
        cxVar.mo16132a("Sec-WebSocket-Key2", m16146f());
        if (!cxVar.mo16131b("Origin")) {
            cxVar.mo16132a("Origin", "random" + this.f13682o.nextInt());
        }
        byte[] bArr = new byte[8];
        this.f13682o.nextBytes(bArr);
        cxVar.setContent(bArr);
        return cxVar;
    }

    @Override // com.tendcloud.tenddata.C2771ci, com.tendcloud.tenddata.AbstractC2765cf
    /* renamed from: a */
    public AbstractC2788cy mo16154a(AbstractC2786cw cwVar, AbstractC2795de deVar) {
        deVar.setHttpStatusMessage("WebSocket Protocol Handshake");
        deVar.mo16132a("Upgrade", "WebSocket");
        deVar.mo16132a(HTTP.CONN_DIRECTIVE, cwVar.mo16133a(HTTP.CONN_DIRECTIVE));
        deVar.mo16132a("Sec-WebSocket-Origin", cwVar.mo16133a("Origin"));
        deVar.mo16132a("Sec-WebSocket-Location", "ws://" + cwVar.mo16133a(HTTP.TARGET_HOST) + cwVar.mo16134a());
        String a = cwVar.mo16133a("Sec-WebSocket-Key1");
        String a2 = cwVar.mo16133a("Sec-WebSocket-Key2");
        byte[] d = cwVar.mo16129d();
        if (a == null || a2 == null || d == null || d.length != 8) {
            throw new C2776cn("Bad keys");
        }
        deVar.setContent(m16151a(a, a2, d));
        return deVar;
    }

    @Override // com.tendcloud.tenddata.AbstractC2765cf
    /* renamed from: d */
    public AbstractC2792db mo16147d(ByteBuffer byteBuffer) {
        AbstractC2788cy a = m16172a(byteBuffer, this.f13666d);
        if ((a.mo16131b("Sec-WebSocket-Key1") || this.f13666d == AbstractC2752bw.EnumC2754b.CLIENT) && !a.mo16131b("Sec-WebSocket-Version")) {
            byte[] bArr = new byte[this.f13666d == AbstractC2752bw.EnumC2754b.SERVER ? 8 : 16];
            try {
                byteBuffer.get(bArr);
                a.setContent(bArr);
            } catch (BufferUnderflowException unused) {
                throw new C2773ck(byteBuffer.capacity() + 16);
            }
        }
        return a;
    }

    @Override // com.tendcloud.tenddata.C2771ci, com.tendcloud.tenddata.AbstractC2765cf
    /* renamed from: c */
    public List mo16148c(ByteBuffer byteBuffer) {
        byteBuffer.mark();
        List e = super.m16159e(byteBuffer);
        if (e != null) {
            return e;
        }
        byteBuffer.reset();
        List list = this.f13677k;
        this.f13676j = true;
        if (this.f13678l == null) {
            this.f13678l = ByteBuffer.allocate(2);
            if (byteBuffer.remaining() <= this.f13678l.remaining()) {
                this.f13678l.put(byteBuffer);
                if (this.f13678l.hasRemaining()) {
                    this.f13677k = new LinkedList();
                    return list;
                } else if (Arrays.equals(this.f13678l.array(), f13680n)) {
                    list.add(new C2781cs(1000));
                    return list;
                } else {
                    throw new C2775cm();
                }
            } else {
                throw new C2775cm();
            }
        } else {
            throw new C2775cm();
        }
    }

    @Override // com.tendcloud.tenddata.C2771ci, com.tendcloud.tenddata.AbstractC2765cf
    /* renamed from: a */
    public ByteBuffer mo16157a(AbstractC2783cu cuVar) {
        if (cuVar.mo16135f() == AbstractC2783cu.EnumC2784a.CLOSING) {
            return ByteBuffer.wrap(f13680n);
        }
        return super.mo16157a(cuVar);
    }

    @Override // com.tendcloud.tenddata.C2771ci, com.tendcloud.tenddata.AbstractC2765cf
    /* renamed from: b */
    public AbstractC2765cf.EnumC2766a mo16150b() {
        return AbstractC2765cf.EnumC2766a.ONEWAY;
    }

    @Override // com.tendcloud.tenddata.C2771ci, com.tendcloud.tenddata.AbstractC2765cf
    /* renamed from: c */
    public AbstractC2765cf mo16149c() {
        return new C2772cj();
    }
}
