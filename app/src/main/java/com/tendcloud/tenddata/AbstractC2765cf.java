package com.tendcloud.tenddata;

import com.ctetin.expandabletextviewlibrary.ExpandableTextView;
import com.tendcloud.tenddata.AbstractC2752bw;
import com.tendcloud.tenddata.AbstractC2783cu;
import java.nio.ByteBuffer;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import org.apache.http.protocol.HTTP;
import org.apache.tools.tar.TarConstants;

/* compiled from: td */
/* renamed from: com.tendcloud.tenddata.cf */
/* loaded from: classes2.dex */
public abstract class AbstractC2765cf {

    /* renamed from: a */
    public static int f13663a = 1000;

    /* renamed from: b */
    public static int f13664b = 64;

    /* renamed from: c */
    public static final byte[] f13665c = C2806dl.m16062a("<policy-file-request/>\u0000");

    /* renamed from: d */
    protected AbstractC2752bw.EnumC2754b f13666d = null;

    /* renamed from: e */
    protected AbstractC2783cu.EnumC2784a f13667e = null;

    /* compiled from: td */
    /* renamed from: com.tendcloud.tenddata.cf$a */
    /* loaded from: classes2.dex */
    public enum EnumC2766a {
        NONE,
        ONEWAY,
        TWOWAY
    }

    /* compiled from: td */
    /* renamed from: com.tendcloud.tenddata.cf$b */
    /* loaded from: classes2.dex */
    public enum EnumC2767b {
        MATCHED,
        NOT_MATCHED
    }

    /* renamed from: a */
    public abstract EnumC2767b mo16156a(AbstractC2786cw cwVar);

    /* renamed from: a */
    public abstract EnumC2767b mo16155a(AbstractC2786cw cwVar, AbstractC2794dd ddVar);

    /* renamed from: a */
    public abstract AbstractC2787cx mo16153a(AbstractC2787cx cxVar);

    /* renamed from: a */
    public abstract AbstractC2788cy mo16154a(AbstractC2786cw cwVar, AbstractC2795de deVar);

    /* renamed from: a */
    public abstract ByteBuffer mo16157a(AbstractC2783cu cuVar);

    /* renamed from: a */
    public abstract List mo16162a(String str, boolean z);

    /* renamed from: a */
    public abstract List mo16161a(ByteBuffer byteBuffer, boolean z);

    /* renamed from: a */
    public abstract void mo16163a();

    /* renamed from: b */
    public abstract EnumC2766a mo16150b();

    /* renamed from: c */
    public abstract AbstractC2765cf mo16149c();

    /* renamed from: c */
    public abstract List mo16148c(ByteBuffer byteBuffer);

    /* renamed from: a */
    public static ByteBuffer m16173a(ByteBuffer byteBuffer) {
        ByteBuffer allocate = ByteBuffer.allocate(byteBuffer.remaining());
        byte b = TarConstants.LF_NORMAL;
        while (byteBuffer.hasRemaining()) {
            byte b2 = byteBuffer.get();
            allocate.put(b2);
            if (b == 13 && b2 == 10) {
                allocate.limit(allocate.position() - 2);
                allocate.position(0);
                return allocate;
            }
            b = b2;
        }
        byteBuffer.position(byteBuffer.position() - allocate.position());
        return null;
    }

    /* renamed from: b */
    public static String m16171b(ByteBuffer byteBuffer) {
        ByteBuffer a = m16173a(byteBuffer);
        if (a == null) {
            return null;
        }
        return C2806dl.m16059a(a.array(), 0, a.limit());
    }

    /* renamed from: a */
    public static AbstractC2788cy m16172a(ByteBuffer byteBuffer, AbstractC2752bw.EnumC2754b bVar) {
        C2791da daVar;
        String b = m16171b(byteBuffer);
        if (b != null) {
            String[] split = b.split(ExpandableTextView.f6958c, 3);
            if (split.length == 3) {
                if (bVar == AbstractC2752bw.EnumC2754b.CLIENT) {
                    C2791da daVar2 = new C2791da();
                    C2791da daVar3 = daVar2;
                    daVar3.setHttpStatus(Short.parseShort(split[1]));
                    daVar3.setHttpStatusMessage(split[2]);
                    daVar = daVar2;
                } else {
                    C2789cz czVar = new C2789cz();
                    czVar.setResourceDescriptor(split[1]);
                    daVar = czVar;
                }
                String b2 = m16171b(byteBuffer);
                while (b2 != null && b2.length() > 0) {
                    String[] split2 = b2.split(":", 2);
                    if (split2.length == 2) {
                        daVar.mo16132a(split2[0], split2[1].replaceFirst("^ +", ""));
                        b2 = m16171b(byteBuffer);
                    } else {
                        throw new C2776cn("not an http header");
                    }
                }
                if (b2 != null) {
                    return daVar;
                }
                throw new C2773ck();
            }
            throw new C2776cn();
        }
        throw new C2773ck(byteBuffer.capacity() + 128);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: a */
    public boolean m16176a(AbstractC2792db dbVar) {
        return dbVar.mo16133a("Upgrade").equalsIgnoreCase("websocket") && dbVar.mo16133a(HTTP.CONN_DIRECTIVE).toLowerCase(Locale.ENGLISH).contains("upgrade");
    }

    /* renamed from: a */
    public List m16177a(AbstractC2783cu.EnumC2784a aVar, ByteBuffer byteBuffer, boolean z) {
        if (aVar == AbstractC2783cu.EnumC2784a.BINARY || aVar == AbstractC2783cu.EnumC2784a.TEXT || aVar == AbstractC2783cu.EnumC2784a.TEXT) {
            if (this.f13667e != null) {
                this.f13667e = AbstractC2783cu.EnumC2784a.CONTINUOUS;
            } else {
                this.f13667e = aVar;
            }
            C2785cv cvVar = new C2785cv(this.f13667e);
            try {
                cvVar.setPayload(byteBuffer);
                cvVar.setFin(z);
                if (z) {
                    this.f13667e = null;
                } else {
                    this.f13667e = aVar;
                }
                return Collections.singletonList(cvVar);
            } catch (C2774cl e) {
                throw new RuntimeException(e);
            }
        } else {
            throw new IllegalArgumentException("Only Opcode.BINARY or  Opcode.TEXT are allowed");
        }
    }

    /* renamed from: a */
    public List m16175a(AbstractC2792db dbVar, AbstractC2752bw.EnumC2754b bVar) {
        return m16174a(dbVar, bVar, true);
    }

    /* renamed from: a */
    public List m16174a(AbstractC2792db dbVar, AbstractC2752bw.EnumC2754b bVar, boolean z) {
        StringBuilder sb = new StringBuilder(100);
        if (dbVar instanceof AbstractC2786cw) {
            sb.append("GET ");
            sb.append(((AbstractC2786cw) dbVar).mo16134a());
            sb.append(" HTTP/1.1");
        } else if (dbVar instanceof AbstractC2794dd) {
            sb.append("HTTP/1.1 101 " + ((AbstractC2794dd) dbVar).mo16128a());
        } else {
            throw new RuntimeException("unknow role");
        }
        sb.append("\r\n");
        Iterator c = dbVar.mo16130c();
        while (c.hasNext()) {
            String str = (String) c.next();
            String a = dbVar.mo16133a(str);
            sb.append(str);
            sb.append(": ");
            sb.append(a);
            sb.append("\r\n");
        }
        sb.append("\r\n");
        byte[] b = C2806dl.m16058b(sb.toString());
        byte[] d = z ? dbVar.mo16129d() : null;
        ByteBuffer allocate = ByteBuffer.allocate((d == null ? 0 : d.length) + b.length);
        allocate.put(b);
        if (d != null) {
            allocate.put(d);
        }
        allocate.flip();
        return Collections.singletonList(allocate);
    }

    /* renamed from: d */
    public AbstractC2792db mo16147d(ByteBuffer byteBuffer) {
        return m16172a(byteBuffer, this.f13666d);
    }

    /* renamed from: a */
    public int m16178a(int i) {
        if (i >= 0) {
            return i;
        }
        throw new C2774cl(1002, "Negative count");
    }

    public void setParseMode(AbstractC2752bw.EnumC2754b bVar) {
        this.f13666d = bVar;
    }

    /* renamed from: d */
    public AbstractC2752bw.EnumC2754b m16170d() {
        return this.f13666d;
    }
}
