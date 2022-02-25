package com.tendcloud.tenddata;

import android.annotation.SuppressLint;
import java.io.IOException;
import java.io.PrintStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.ByteChannel;
import java.nio.channels.CancelledKeyException;
import java.nio.channels.SelectableChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

/* compiled from: td */
@SuppressLint({"Assert"})
/* renamed from: com.tendcloud.tenddata.dh */
/* loaded from: classes2.dex */
public abstract class AbstractRunnableC2798dh extends AbstractC2755bx implements Runnable {

    /* renamed from: c */
    private final Collection f13723c;

    /* renamed from: d */
    private final InetSocketAddress f13724d;

    /* renamed from: e */
    private ServerSocketChannel f13725e;

    /* renamed from: f */
    private Selector f13726f;

    /* renamed from: g */
    private List f13727g;

    /* renamed from: h */
    private Thread f13728h;

    /* renamed from: i */
    private volatile AtomicBoolean f13729i;

    /* renamed from: j */
    private List f13730j;

    /* renamed from: k */
    private List f13731k;

    /* renamed from: l */
    private BlockingQueue f13732l;

    /* renamed from: m */
    private int f13733m;

    /* renamed from: n */
    private AtomicInteger f13734n;

    /* renamed from: o */
    private AbstractC2799a f13735o;

    /* renamed from: b */
    static final /* synthetic */ boolean f13722b = !AbstractRunnableC2798dh.class.desiredAssertionStatus();

    /* renamed from: a */
    public static int f13721a = Runtime.getRuntime().availableProcessors();

    /* compiled from: td */
    /* renamed from: com.tendcloud.tenddata.dh$a */
    /* loaded from: classes2.dex */
    public interface AbstractC2799a extends AbstractC2756by {
        C2757bz createWebSocket(AbstractC2755bx bxVar, AbstractC2765cf cfVar, Socket socket);

        C2757bz createWebSocket(AbstractC2755bx bxVar, List list, Socket socket);

        ByteChannel wrapChannel(SocketChannel socketChannel, SelectionKey selectionKey);
    }

    /* renamed from: a */
    public void m16121a(AbstractC2752bw bwVar, int i, String str) {
    }

    /* renamed from: a */
    public void m16120a(AbstractC2752bw bwVar, int i, String str, boolean z) {
    }

    /* renamed from: a */
    public void m16119a(AbstractC2752bw bwVar, AbstractC2783cu cuVar) {
    }

    /* renamed from: a */
    public abstract void m16118a(AbstractC2752bw bwVar, AbstractC2786cw cwVar);

    /* renamed from: a */
    public abstract void m16117a(AbstractC2752bw bwVar, Exception exc);

    /* renamed from: a */
    public abstract void m16116a(AbstractC2752bw bwVar, String str);

    /* renamed from: a */
    public void m16115a(AbstractC2752bw bwVar, ByteBuffer byteBuffer) {
    }

    /* renamed from: a */
    protected boolean m16110a(SelectionKey selectionKey) {
        return true;
    }

    /* renamed from: b */
    protected void m16107b(AbstractC2752bw bwVar) {
    }

    /* renamed from: b */
    public abstract void m16106b(AbstractC2752bw bwVar, int i, String str, boolean z);

    public AbstractRunnableC2798dh() {
        this(new InetSocketAddress(80), f13721a, null);
    }

    public AbstractRunnableC2798dh(InetSocketAddress inetSocketAddress) {
        this(inetSocketAddress, f13721a, null);
    }

    public AbstractRunnableC2798dh(InetSocketAddress inetSocketAddress, int i) {
        this(inetSocketAddress, i, null);
    }

    public AbstractRunnableC2798dh(InetSocketAddress inetSocketAddress, List list) {
        this(inetSocketAddress, f13721a, list);
    }

    public AbstractRunnableC2798dh(InetSocketAddress inetSocketAddress, int i, List list) {
        this(inetSocketAddress, i, list, new HashSet());
    }

    public AbstractRunnableC2798dh(InetSocketAddress inetSocketAddress, int i, List list, Collection collection) {
        this.f13729i = new AtomicBoolean(false);
        this.f13733m = 0;
        this.f13734n = new AtomicInteger(0);
        this.f13735o = new C2797dg();
        if (inetSocketAddress == null || i < 1 || collection == null) {
            throw new IllegalArgumentException("address and connectionscontainer must not be null and you need at least 1 decoder");
        }
        if (list == null) {
            this.f13727g = Collections.emptyList();
        } else {
            this.f13727g = list;
        }
        this.f13724d = inetSocketAddress;
        this.f13723c = collection;
        this.f13731k = new LinkedList();
        this.f13730j = new ArrayList(i);
        this.f13732l = new LinkedBlockingQueue();
        for (int i2 = 0; i2 < i; i2++) {
            C2800b bVar = new C2800b();
            this.f13730j.add(bVar);
            bVar.start();
        }
    }

    /* renamed from: a */
    public void m16123a() {
        if (this.f13728h == null) {
            new Thread(this).start();
            return;
        }
        throw new IllegalStateException(getClass().getName() + " can only be started once.");
    }

    public void stop(int i) {
        ArrayList<AbstractC2752bw> arrayList;
        if (this.f13729i.compareAndSet(false, true)) {
            synchronized (this.f13723c) {
                arrayList = new ArrayList(this.f13723c);
            }
            for (AbstractC2752bw bwVar : arrayList) {
                bwVar.close(1001);
            }
            synchronized (this) {
                if (this.f13728h != null) {
                    Thread.currentThread();
                    Thread thread = this.f13728h;
                    if (this.f13728h != Thread.currentThread()) {
                        if (arrayList.size() > 0) {
                            this.f13728h.join(i);
                        }
                        this.f13728h.interrupt();
                        this.f13728h.join();
                    }
                }
            }
        }
    }

    /* renamed from: b */
    public void m16108b() {
        stop(0);
    }

    /* renamed from: c */
    public Collection m16104c() {
        return this.f13723c;
    }

    /* renamed from: d */
    public InetSocketAddress m16102d() {
        return this.f13724d;
    }

    /* renamed from: e */
    public int m16100e() {
        ServerSocketChannel serverSocketChannel;
        int port = m16102d().getPort();
        return (port != 0 || (serverSocketChannel = this.f13725e) == null) ? port : serverSocketChannel.socket().getLocalPort();
    }

    /* renamed from: f */
    public List m16098f() {
        return Collections.unmodifiableList(this.f13727g);
    }

    /* JADX WARN: Removed duplicated region for block: B:95:0x01b4 A[Catch: all -> 0x0202, RuntimeException -> 0x0204, TRY_ENTER, TryCatch #10 {RuntimeException -> 0x0204, blocks: (B:12:0x0062, B:14:0x006a, B:16:0x007b, B:18:0x0081, B:19:0x0087, B:22:0x008e, B:24:0x0095, B:26:0x009b, B:27:0x009f, B:29:0x00ce, B:31:0x00d4, B:32:0x00da, B:33:0x00de, B:35:0x00e6, B:37:0x00ec, B:39:0x00fd, B:41:0x0107, B:42:0x010d, B:43:0x0111, B:46:0x0117, B:47:0x011a, B:50:0x011f, B:52:0x0125, B:53:0x012b, B:55:0x0133, B:57:0x0139, B:61:0x0144, B:63:0x014c, B:64:0x0154, B:65:0x015c, B:67:0x0162, B:68:0x0167, B:70:0x016d, B:71:0x0176, B:74:0x017c, B:75:0x017f, B:95:0x01b4, B:96:0x01b7), top: B:154:0x0062, outer: #0 }] */
    @Override // java.lang.Runnable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void run() {
        /*
            Method dump skipped, instructions count: 633
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tendcloud.tenddata.AbstractRunnableC2798dh.run():void");
    }

    /* renamed from: a */
    protected void m16122a(AbstractC2752bw bwVar) {
        if (this.f13734n.get() < (this.f13730j.size() * 2) + 1) {
            this.f13734n.incrementAndGet();
            this.f13732l.put(m16097g());
        }
    }

    /* renamed from: g */
    public ByteBuffer m16097g() {
        return ByteBuffer.allocate(C2757bz.f13640c);
    }

    /* renamed from: a */
    private void m16114a(C2757bz bzVar) {
        if (bzVar.f13648j == null) {
            List list = this.f13730j;
            bzVar.f13648j = (C2800b) list.get(this.f13733m % list.size());
            this.f13733m++;
        }
        bzVar.f13648j.put(bzVar);
    }

    /* renamed from: j */
    private ByteBuffer m16094j() {
        return (ByteBuffer) this.f13732l.take();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a */
    public void m16111a(ByteBuffer byteBuffer) {
        if (this.f13732l.size() <= this.f13734n.intValue()) {
            this.f13732l.put(byteBuffer);
        }
    }

    /* renamed from: a */
    private void m16109a(SelectionKey selectionKey, AbstractC2752bw bwVar, IOException iOException) {
        SelectableChannel channel;
        if (bwVar != null) {
            bwVar.closeConnection(1006, iOException.getMessage());
        } else if (selectionKey != null && (channel = selectionKey.channel()) != null && channel.isOpen()) {
            try {
                channel.close();
            } catch (IOException unused) {
            }
            if (C2757bz.f13641d) {
                PrintStream printStream = System.out;
                printStream.println("Connection closed because of" + iOException);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: b */
    public void m16105b(AbstractC2752bw bwVar, Exception exc) {
        m16117a(bwVar, exc);
        try {
            m16108b();
        } catch (IOException e) {
            m16117a((AbstractC2752bw) null, e);
        } catch (InterruptedException e2) {
            Thread.currentThread().interrupt();
            m16117a((AbstractC2752bw) null, e2);
        }
    }

    /* renamed from: h */
    protected String m16096h() {
        return "<cross-domain-policy><allow-access-from domain=\"*\" to-ports=\"" + m16100e() + "\" /></cross-domain-policy>";
    }

    @Override // com.tendcloud.tenddata.AbstractC2759ca
    public final void onWebsocketMessage(AbstractC2752bw bwVar, String str) {
        m16116a(bwVar, str);
    }

    @Override // com.tendcloud.tenddata.AbstractC2755bx, com.tendcloud.tenddata.AbstractC2759ca
    @Deprecated
    public void onWebsocketMessageFragment(AbstractC2752bw bwVar, AbstractC2783cu cuVar) {
        m16119a(bwVar, cuVar);
    }

    @Override // com.tendcloud.tenddata.AbstractC2759ca
    public final void onWebsocketMessage(AbstractC2752bw bwVar, ByteBuffer byteBuffer) {
        m16115a(bwVar, byteBuffer);
    }

    @Override // com.tendcloud.tenddata.AbstractC2759ca
    public final void onWebsocketOpen(AbstractC2752bw bwVar, AbstractC2792db dbVar) {
        if (m16101d(bwVar)) {
            m16118a(bwVar, (AbstractC2786cw) dbVar);
        }
    }

    @Override // com.tendcloud.tenddata.AbstractC2759ca
    public final void onWebsocketClose(AbstractC2752bw bwVar, int i, String str, boolean z) {
        this.f13726f.wakeup();
        try {
            if (m16103c(bwVar)) {
                m16106b(bwVar, i, str, z);
            }
            try {
                m16107b(bwVar);
            } catch (InterruptedException unused) {
                Thread.currentThread().interrupt();
            }
        } catch (Throwable th) {
            try {
                m16107b(bwVar);
            } catch (InterruptedException unused2) {
                Thread.currentThread().interrupt();
            }
            throw th;
        }
    }

    /* renamed from: c */
    protected boolean m16103c(AbstractC2752bw bwVar) {
        boolean remove;
        synchronized (this.f13723c) {
            remove = this.f13723c.remove(bwVar);
            if (!f13722b && !remove) {
                throw new AssertionError();
            }
        }
        if (this.f13729i.get() && this.f13723c.size() == 0) {
            this.f13728h.interrupt();
        }
        return remove;
    }

    @Override // com.tendcloud.tenddata.AbstractC2755bx, com.tendcloud.tenddata.AbstractC2759ca
    public AbstractC2795de onWebsocketHandshakeReceivedAsServer(AbstractC2752bw bwVar, AbstractC2765cf cfVar, AbstractC2786cw cwVar) {
        return super.onWebsocketHandshakeReceivedAsServer(bwVar, cfVar, cwVar);
    }

    /* renamed from: d */
    protected boolean m16101d(AbstractC2752bw bwVar) {
        boolean add;
        if (!this.f13729i.get()) {
            synchronized (this.f13723c) {
                add = this.f13723c.add(bwVar);
                if (!f13722b && !add) {
                    throw new AssertionError();
                }
            }
            return add;
        }
        bwVar.close(1001);
        return true;
    }

    @Override // com.tendcloud.tenddata.AbstractC2759ca
    public final void onWebsocketError(AbstractC2752bw bwVar, Exception exc) {
        m16117a(bwVar, exc);
    }

    @Override // com.tendcloud.tenddata.AbstractC2759ca
    public final void onWriteDemand(AbstractC2752bw bwVar) {
        C2757bz bzVar = (C2757bz) bwVar;
        try {
            bzVar.f13644f.interestOps(5);
        } catch (CancelledKeyException unused) {
            bzVar.f13646h.clear();
        }
        this.f13726f.wakeup();
    }

    @Override // com.tendcloud.tenddata.AbstractC2759ca
    public void onWebsocketCloseInitiated(AbstractC2752bw bwVar, int i, String str) {
        m16121a(bwVar, i, str);
    }

    @Override // com.tendcloud.tenddata.AbstractC2759ca
    public void onWebsocketClosing(AbstractC2752bw bwVar, int i, String str, boolean z) {
        m16120a(bwVar, i, str, z);
    }

    public final void setWebSocketFactory(AbstractC2799a aVar) {
        this.f13735o = aVar;
    }

    /* renamed from: i */
    public final AbstractC2756by m16095i() {
        return this.f13735o;
    }

    /* renamed from: e */
    private Socket m16099e(AbstractC2752bw bwVar) {
        return ((SocketChannel) ((C2757bz) bwVar).f13644f.channel()).socket();
    }

    @Override // com.tendcloud.tenddata.AbstractC2759ca
    public InetSocketAddress getLocalSocketAddress(AbstractC2752bw bwVar) {
        return (InetSocketAddress) m16099e(bwVar).getLocalSocketAddress();
    }

    @Override // com.tendcloud.tenddata.AbstractC2759ca
    public InetSocketAddress getRemoteSocketAddress(AbstractC2752bw bwVar) {
        return (InetSocketAddress) m16099e(bwVar).getRemoteSocketAddress();
    }

    /* compiled from: td */
    /* renamed from: com.tendcloud.tenddata.dh$b */
    /* loaded from: classes2.dex */
    public class C2800b extends Thread {
        static final /* synthetic */ boolean $assertionsDisabled = false;
        private BlockingQueue iqueue = new LinkedBlockingQueue();

        public C2800b() {
            setName("WebSocketWorker-" + getId());
            setUncaughtExceptionHandler(new C2801di(this, AbstractRunnableC2798dh.this));
        }

        public void put(C2757bz bzVar) {
            this.iqueue.put(bzVar);
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            RuntimeException e;
            C2757bz bzVar = null;
            while (true) {
                try {
                    try {
                        bzVar = (C2757bz) this.iqueue.take();
                        try {
                            ByteBuffer byteBuffer = (ByteBuffer) bzVar.f13647i.poll();
                            try {
                                bzVar.decode(byteBuffer);
                                AbstractRunnableC2798dh.this.m16111a(byteBuffer);
                                bzVar = bzVar;
                            } catch (Throwable th) {
                                AbstractRunnableC2798dh.this.m16111a(byteBuffer);
                                throw th;
                            }
                        } catch (RuntimeException e2) {
                            e = e2;
                            AbstractRunnableC2798dh.this.m16105b(bzVar, e);
                            return;
                        }
                    } catch (RuntimeException e3) {
                        e = e3;
                    }
                } catch (InterruptedException unused) {
                    return;
                }
            }
        }
    }
}
