package com.tendcloud.tenddata;

import android.annotation.SuppressLint;
import com.tendcloud.tenddata.AbstractC2752bw;
import com.tendcloud.tenddata.AbstractC2765cf;
import com.tendcloud.tenddata.AbstractC2783cu;
import com.tendcloud.tenddata.AbstractRunnableC2798dh;
import java.io.IOException;
import java.io.PrintStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.ByteChannel;
import java.nio.channels.SelectionKey;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import p110z1.C4963cj;

/* compiled from: td */
@SuppressLint({"Assert"})
/* renamed from: com.tendcloud.tenddata.bz */
/* loaded from: classes2.dex */
public class C2757bz implements AbstractC2752bw {

    /* renamed from: f */
    public SelectionKey f13644f;

    /* renamed from: g */
    public ByteChannel f13645g;

    /* renamed from: h */
    public final BlockingQueue f13646h;

    /* renamed from: i */
    public final BlockingQueue f13647i;

    /* renamed from: j */
    public volatile AbstractRunnableC2798dh.C2800b f13648j;

    /* renamed from: l */
    private volatile boolean f13649l;

    /* renamed from: m */
    private AbstractC2752bw.EnumC2753a f13650m;

    /* renamed from: n */
    private final AbstractC2759ca f13651n;

    /* renamed from: o */
    private List f13652o;

    /* renamed from: p */
    private AbstractC2765cf f13653p;

    /* renamed from: q */
    private AbstractC2752bw.EnumC2754b f13654q;

    /* renamed from: r */
    private AbstractC2783cu.EnumC2784a f13655r;

    /* renamed from: s */
    private ByteBuffer f13656s;

    /* renamed from: t */
    private AbstractC2786cw f13657t;

    /* renamed from: u */
    private String f13658u;

    /* renamed from: v */
    private Integer f13659v;

    /* renamed from: w */
    private Boolean f13660w;

    /* renamed from: x */
    private String f13661x;

    /* renamed from: k */
    static final /* synthetic */ boolean f13643k = !C2757bz.class.desiredAssertionStatus();

    /* renamed from: c */
    public static int f13640c = 16384;

    /* renamed from: d */
    public static boolean f13641d = false;

    /* renamed from: e */
    public static final List f13642e = new ArrayList(4);

    static {
        f13642e.add(new C2770ch());
        f13642e.add(new C2768cg());
        f13642e.add(new C2772cj());
        f13642e.add(new C2771ci());
    }

    public C2757bz(AbstractC2759ca caVar, List list) {
        this(caVar, (AbstractC2765cf) null);
        this.f13654q = AbstractC2752bw.EnumC2754b.SERVER;
        if (list == null || list.isEmpty()) {
            this.f13652o = f13642e;
        } else {
            this.f13652o = list;
        }
    }

    public C2757bz(AbstractC2759ca caVar, AbstractC2765cf cfVar) {
        this.f13649l = false;
        this.f13650m = AbstractC2752bw.EnumC2753a.NOT_YET_CONNECTED;
        this.f13653p = null;
        this.f13655r = null;
        this.f13656s = ByteBuffer.allocate(0);
        this.f13657t = null;
        this.f13658u = null;
        this.f13659v = null;
        this.f13660w = null;
        this.f13661x = null;
        if (caVar == null || (cfVar == null && this.f13654q == AbstractC2752bw.EnumC2754b.SERVER)) {
            throw new IllegalArgumentException("parameters must not be null");
        }
        this.f13646h = new LinkedBlockingQueue();
        this.f13647i = new LinkedBlockingQueue();
        this.f13651n = caVar;
        this.f13654q = AbstractC2752bw.EnumC2754b.CLIENT;
        if (cfVar != null) {
            this.f13653p = cfVar.mo16149c();
        }
    }

    @Deprecated
    public C2757bz(AbstractC2759ca caVar, AbstractC2765cf cfVar, Socket socket) {
        this(caVar, cfVar);
    }

    @Deprecated
    public C2757bz(AbstractC2759ca caVar, List list, Socket socket) {
        this(caVar, list);
    }

    public void decode(ByteBuffer byteBuffer) {
        try {
            if (!f13643k && !byteBuffer.hasRemaining()) {
                throw new AssertionError();
            }
            if (f13641d) {
                PrintStream printStream = System.out;
                StringBuilder sb = new StringBuilder();
                sb.append("process(");
                sb.append(byteBuffer.remaining());
                sb.append("): {");
                sb.append(byteBuffer.remaining() > 1000 ? "too big to display" : new String(byteBuffer.array(), byteBuffer.position(), byteBuffer.remaining()));
                sb.append(C4963cj.f20747d);
                printStream.println(sb.toString());
            }
            if (this.f13650m != AbstractC2752bw.EnumC2753a.NOT_YET_CONNECTED) {
                m16188b(byteBuffer);
            } else if (m16193a(byteBuffer)) {
                if (!f13643k && this.f13656s.hasRemaining() == byteBuffer.hasRemaining() && byteBuffer.hasRemaining()) {
                    throw new AssertionError();
                }
                if (byteBuffer.hasRemaining()) {
                    m16188b(byteBuffer);
                } else if (this.f13656s.hasRemaining()) {
                    m16188b(this.f13656s);
                }
            }
            if (!f13643k && !isClosing() && !isFlushAndClose() && byteBuffer.hasRemaining()) {
                throw new AssertionError();
            }
        } catch (Throwable unused) {
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:79:0x0196  */
    /* JADX WARN: Removed duplicated region for block: B:89:0x01c7  */
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private boolean m16193a(java.nio.ByteBuffer r11) {
        /*
            Method dump skipped, instructions count: 474
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tendcloud.tenddata.C2757bz.m16193a(java.nio.ByteBuffer):boolean");
    }

    /* renamed from: b */
    private void m16188b(ByteBuffer byteBuffer) {
        try {
        } catch (C2774cl e) {
            this.f13651n.onWebsocketError(this, e);
            close(e);
            return;
        }
        for (AbstractC2783cu cuVar : this.f13653p.mo16148c(byteBuffer)) {
            if (f13641d) {
                PrintStream printStream = System.out;
                printStream.println("matched frame: " + cuVar);
            }
            AbstractC2783cu.EnumC2784a f = cuVar.mo16135f();
            boolean d = cuVar.mo16137d();
            if (f == AbstractC2783cu.EnumC2784a.CLOSING) {
                int i = AbstractC2780cr.f13695e;
                String str = "";
                if (cuVar instanceof AbstractC2780cr) {
                    AbstractC2780cr crVar = (AbstractC2780cr) cuVar;
                    i = crVar.mo16143a();
                    str = crVar.mo16141b();
                }
                if (this.f13650m == AbstractC2752bw.EnumC2753a.CLOSING) {
                    m16196a(i, str, true);
                } else if (this.f13653p.mo16150b() == AbstractC2765cf.EnumC2766a.TWOWAY) {
                    m16187c(i, str, true);
                } else {
                    m16189b(i, str, false);
                }
            } else if (f == AbstractC2783cu.EnumC2784a.PING) {
                this.f13651n.onWebsocketPing(this, cuVar);
            } else if (f == AbstractC2783cu.EnumC2784a.PONG) {
                this.f13651n.onWebsocketPong(this, cuVar);
            } else {
                if (d && f != AbstractC2783cu.EnumC2784a.CONTINUOUS) {
                    if (this.f13655r == null) {
                        if (f == AbstractC2783cu.EnumC2784a.TEXT) {
                            try {
                                this.f13651n.onWebsocketMessage(this, C2806dl.m16061a(cuVar.mo16138c()));
                            } catch (RuntimeException e2) {
                                this.f13651n.onWebsocketError(this, e2);
                            }
                        } else if (f == AbstractC2783cu.EnumC2784a.BINARY) {
                            try {
                                this.f13651n.onWebsocketMessage(this, cuVar.mo16138c());
                            } catch (RuntimeException e3) {
                                this.f13651n.onWebsocketError(this, e3);
                            }
                        } else {
                            throw new C2774cl(1002, "non control or continious frame expected");
                        }
                        this.f13651n.onWebsocketError(this, e);
                        close(e);
                        return;
                    }
                    throw new C2774cl(1002, "Continuous frame sequence not completed.");
                }
                if (f != AbstractC2783cu.EnumC2784a.CONTINUOUS) {
                    if (this.f13655r == null) {
                        this.f13655r = f;
                    } else {
                        throw new C2774cl(1002, "Previous continuous frame sequence not completed.");
                    }
                } else if (d) {
                    if (this.f13655r != null) {
                        this.f13655r = null;
                    } else {
                        throw new C2774cl(1002, "Continuous frame sequence was not started.");
                    }
                } else if (this.f13655r == null) {
                    throw new C2774cl(1002, "Continuous frame sequence was not started.");
                }
                try {
                    this.f13651n.onWebsocketMessageFragment(this, cuVar);
                } catch (RuntimeException e4) {
                    this.f13651n.onWebsocketError(this, e4);
                }
            }
        }
    }

    /* renamed from: c */
    private void m16187c(int i, String str, boolean z) {
        if (this.f13650m != AbstractC2752bw.EnumC2753a.CLOSING && this.f13650m != AbstractC2752bw.EnumC2753a.CLOSED) {
            if (this.f13650m == AbstractC2752bw.EnumC2753a.OPEN) {
                if (i != 1006) {
                    if (this.f13653p.mo16150b() != AbstractC2765cf.EnumC2766a.NONE) {
                        try {
                            if (!z) {
                                try {
                                    this.f13651n.onWebsocketCloseInitiated(this, i, str);
                                } catch (RuntimeException e) {
                                    this.f13651n.onWebsocketError(this, e);
                                }
                            }
                            sendFrame(new C2781cs(i, str));
                        } catch (C2774cl e2) {
                            this.f13651n.onWebsocketError(this, e2);
                            m16189b(1006, "generated frame is invalid", false);
                        }
                    }
                    m16189b(i, str, z);
                } else if (f13643k || !z) {
                    this.f13650m = AbstractC2752bw.EnumC2753a.CLOSING;
                    m16189b(i, str, false);
                    return;
                } else {
                    throw new AssertionError();
                }
            } else if (i != -3) {
                m16189b(-1, str, false);
            } else if (f13643k || z) {
                m16189b(-3, str, true);
            } else {
                throw new AssertionError();
            }
            if (i == 1002) {
                m16189b(i, str, z);
            }
            this.f13650m = AbstractC2752bw.EnumC2753a.CLOSING;
            this.f13656s = null;
        }
    }

    @Override // com.tendcloud.tenddata.AbstractC2752bw
    public void close(int i, String str) {
        m16187c(i, str, false);
    }

    /* renamed from: a */
    protected synchronized void m16196a(int i, String str, boolean z) {
        if (this.f13650m != AbstractC2752bw.EnumC2753a.CLOSED) {
            if (this.f13644f != null) {
                this.f13644f.cancel();
            }
            if (this.f13645g != null) {
                try {
                    this.f13645g.close();
                } catch (IOException e) {
                    this.f13651n.onWebsocketError(this, e);
                }
            }
            try {
                this.f13651n.onWebsocketClose(this, i, str, z);
            } catch (RuntimeException e2) {
                this.f13651n.onWebsocketError(this, e2);
            }
            if (this.f13653p != null) {
                this.f13653p.mo16163a();
            }
            this.f13657t = null;
            this.f13650m = AbstractC2752bw.EnumC2753a.CLOSED;
            this.f13646h.clear();
        }
    }

    /* renamed from: a */
    protected void m16195a(int i, boolean z) {
        m16196a(i, "", z);
    }

    /* renamed from: a */
    public void m16197a() {
        if (this.f13660w != null) {
            m16196a(this.f13659v.intValue(), this.f13658u, this.f13660w.booleanValue());
            return;
        }
        throw new IllegalStateException("this method must be used in conjuction with flushAndClose");
    }

    @Override // com.tendcloud.tenddata.AbstractC2752bw
    public void closeConnection(int i, String str) {
        m16196a(i, str, false);
    }

    /* renamed from: b */
    protected synchronized void m16189b(int i, String str, boolean z) {
        if (!this.f13649l) {
            this.f13659v = Integer.valueOf(i);
            this.f13658u = str;
            this.f13660w = Boolean.valueOf(z);
            this.f13649l = true;
            this.f13651n.onWriteDemand(this);
            try {
                this.f13651n.onWebsocketClosing(this, i, str, z);
            } catch (RuntimeException e) {
                this.f13651n.onWebsocketError(this, e);
            }
            if (this.f13653p != null) {
                this.f13653p.mo16163a();
            }
            this.f13657t = null;
        }
    }

    /* renamed from: b */
    public void m16190b() {
        try {
            if (getReadyState() == AbstractC2752bw.EnumC2753a.NOT_YET_CONNECTED) {
                m16195a(-1, true);
            } else if (this.f13649l) {
                m16196a(this.f13659v.intValue(), this.f13658u, this.f13660w.booleanValue());
            } else if (this.f13653p.mo16150b() == AbstractC2765cf.EnumC2766a.NONE) {
                m16195a(1000, true);
            } else if (this.f13653p.mo16150b() != AbstractC2765cf.EnumC2766a.ONEWAY) {
                m16195a(1006, true);
            } else if (this.f13654q == AbstractC2752bw.EnumC2754b.SERVER) {
                m16195a(1006, true);
            } else {
                m16195a(1000, true);
            }
        } catch (Throwable unused) {
        }
    }

    @Override // com.tendcloud.tenddata.AbstractC2752bw
    public void close(int i) {
        m16187c(i, "", false);
    }

    public void close(C2774cl clVar) {
        m16187c(clVar.m16144a(), clVar.getMessage(), false);
    }

    @Override // com.tendcloud.tenddata.AbstractC2752bw
    public void send(String str) {
        if (str != null) {
            m16192a((Collection) this.f13653p.mo16162a(str, this.f13654q == AbstractC2752bw.EnumC2754b.CLIENT));
            return;
        }
        throw new IllegalArgumentException("Cannot send 'null' data to a WebSocketImpl.");
    }

    @Override // com.tendcloud.tenddata.AbstractC2752bw
    public void send(ByteBuffer byteBuffer) {
        if (byteBuffer != null) {
            m16192a((Collection) this.f13653p.mo16161a(byteBuffer, this.f13654q == AbstractC2752bw.EnumC2754b.CLIENT));
            return;
        }
        throw new IllegalArgumentException("Cannot send 'null' data to a WebSocketImpl.");
    }

    @Override // com.tendcloud.tenddata.AbstractC2752bw
    public void send(byte[] bArr) {
        send(ByteBuffer.wrap(bArr));
    }

    /* renamed from: a */
    private void m16192a(Collection collection) {
        if (isOpen()) {
            Iterator it = collection.iterator();
            while (it.hasNext()) {
                sendFrame((AbstractC2783cu) it.next());
            }
            return;
        }
        throw new C2779cq();
    }

    @Override // com.tendcloud.tenddata.AbstractC2752bw
    public void sendFragmentedFrame(AbstractC2783cu.EnumC2784a aVar, ByteBuffer byteBuffer, boolean z) {
        m16192a((Collection) this.f13653p.m16177a(aVar, byteBuffer, z));
    }

    @Override // com.tendcloud.tenddata.AbstractC2752bw
    public void sendFrame(AbstractC2783cu cuVar) {
        if (f13641d) {
            PrintStream printStream = System.out;
            printStream.println("send frame: " + cuVar);
        }
        m16185d(this.f13653p.mo16157a(cuVar));
    }

    @Override // com.tendcloud.tenddata.AbstractC2752bw
    public boolean hasBufferedData() {
        return !this.f13646h.isEmpty();
    }

    /* renamed from: c */
    private AbstractC2765cf.EnumC2767b m16186c(ByteBuffer byteBuffer) {
        byteBuffer.mark();
        if (byteBuffer.limit() > AbstractC2765cf.f13665c.length) {
            return AbstractC2765cf.EnumC2767b.NOT_MATCHED;
        }
        if (byteBuffer.limit() >= AbstractC2765cf.f13665c.length) {
            int i = 0;
            while (byteBuffer.hasRemaining()) {
                if (AbstractC2765cf.f13665c[i] != byteBuffer.get()) {
                    byteBuffer.reset();
                    return AbstractC2765cf.EnumC2767b.NOT_MATCHED;
                }
                i++;
            }
            return AbstractC2765cf.EnumC2767b.MATCHED;
        }
        throw new C2773ck(AbstractC2765cf.f13665c.length);
    }

    public void startHandshake(AbstractC2787cx cxVar) {
        if (f13643k || this.f13650m != AbstractC2752bw.EnumC2753a.CONNECTING) {
            this.f13657t = this.f13653p.mo16153a(cxVar);
            this.f13661x = cxVar.mo16134a();
            if (f13643k || this.f13661x != null) {
                try {
                    this.f13651n.onWebsocketHandshakeSentAsClient(this, this.f13657t);
                    m16191a(this.f13653p.m16175a(this.f13657t, this.f13654q));
                } catch (C2774cl unused) {
                    throw new C2776cn("Handshake data rejected by client.");
                } catch (RuntimeException e) {
                    this.f13651n.onWebsocketError(this, e);
                    throw new C2776cn("rejected because of" + e);
                }
            } else {
                throw new AssertionError();
            }
        } else {
            throw new AssertionError("shall only be called once");
        }
    }

    /* renamed from: d */
    private void m16185d(ByteBuffer byteBuffer) {
        if (f13641d) {
            PrintStream printStream = System.out;
            StringBuilder sb = new StringBuilder();
            sb.append("write(");
            sb.append(byteBuffer.remaining());
            sb.append("): {");
            sb.append(byteBuffer.remaining() > 1000 ? "too big to display" : new String(byteBuffer.array()));
            sb.append(C4963cj.f20747d);
            printStream.println(sb.toString());
        }
        this.f13646h.add(byteBuffer);
        this.f13651n.onWriteDemand(this);
    }

    /* renamed from: a */
    private void m16191a(List list) {
        Iterator it = list.iterator();
        while (it.hasNext()) {
            m16185d((ByteBuffer) it.next());
        }
    }

    /* renamed from: a */
    private void m16194a(AbstractC2792db dbVar) {
        if (f13641d) {
            PrintStream printStream = System.out;
            printStream.println("open using draft: " + this.f13653p.getClass().getSimpleName());
        }
        this.f13650m = AbstractC2752bw.EnumC2753a.OPEN;
        try {
            this.f13651n.onWebsocketOpen(this, dbVar);
        } catch (RuntimeException e) {
            this.f13651n.onWebsocketError(this, e);
        }
    }

    @Override // com.tendcloud.tenddata.AbstractC2752bw
    public boolean isConnecting() {
        if (f13643k || !this.f13649l || this.f13650m == AbstractC2752bw.EnumC2753a.CONNECTING) {
            return this.f13650m == AbstractC2752bw.EnumC2753a.CONNECTING;
        }
        throw new AssertionError();
    }

    @Override // com.tendcloud.tenddata.AbstractC2752bw
    public boolean isOpen() {
        if (f13643k || this.f13650m != AbstractC2752bw.EnumC2753a.OPEN || !this.f13649l) {
            return this.f13650m == AbstractC2752bw.EnumC2753a.OPEN;
        }
        throw new AssertionError();
    }

    @Override // com.tendcloud.tenddata.AbstractC2752bw
    public boolean isClosing() {
        return this.f13650m == AbstractC2752bw.EnumC2753a.CLOSING;
    }

    @Override // com.tendcloud.tenddata.AbstractC2752bw
    public boolean isFlushAndClose() {
        return this.f13649l;
    }

    @Override // com.tendcloud.tenddata.AbstractC2752bw
    public boolean isClosed() {
        return this.f13650m == AbstractC2752bw.EnumC2753a.CLOSED;
    }

    @Override // com.tendcloud.tenddata.AbstractC2752bw
    public AbstractC2752bw.EnumC2753a getReadyState() {
        return this.f13650m;
    }

    public int hashCode() {
        return super.hashCode();
    }

    public String toString() {
        return super.toString();
    }

    @Override // com.tendcloud.tenddata.AbstractC2752bw
    public InetSocketAddress getRemoteSocketAddress() {
        return this.f13651n.getRemoteSocketAddress(this);
    }

    @Override // com.tendcloud.tenddata.AbstractC2752bw
    public InetSocketAddress getLocalSocketAddress() {
        return this.f13651n.getLocalSocketAddress(this);
    }

    @Override // com.tendcloud.tenddata.AbstractC2752bw
    public AbstractC2765cf getDraft() {
        return this.f13653p;
    }

    @Override // com.tendcloud.tenddata.AbstractC2752bw
    public void close() {
        close(1000);
    }

    @Override // com.tendcloud.tenddata.AbstractC2752bw
    public String getResourceDescriptor() {
        return this.f13661x;
    }
}
