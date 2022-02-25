package com.tendcloud.tenddata;

import android.annotation.SuppressLint;
import com.tendcloud.tenddata.AbstractC2752bw;
import com.tendcloud.tenddata.AbstractC2783cu;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.Socket;
import java.net.URI;
import java.nio.ByteBuffer;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import org.apache.http.protocol.HTTP;

/* compiled from: td */
@SuppressLint({"Assert"})
/* renamed from: com.tendcloud.tenddata.cd */
/* loaded from: classes2.dex */
public abstract class AbstractRunnableC2762cd extends AbstractC2755bx implements AbstractC2752bw, Runnable {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private CountDownLatch closeLatch;
    private CountDownLatch connectLatch;
    private int connectTimeout;
    private AbstractC2765cf draft;
    private C2757bz engine;
    private Map headers;
    private InputStream istream;
    private OutputStream ostream;
    private Proxy proxy;
    private Socket socket;
    protected URI uri;
    private Thread writeThread;

    public abstract void onClose(int i, String str, boolean z);

    public void onCloseInitiated(int i, String str) {
    }

    public void onClosing(int i, String str, boolean z) {
    }

    public abstract void onError(Exception exc);

    public void onFragment(AbstractC2783cu cuVar) {
    }

    public abstract void onMessage(String str);

    public void onMessage(ByteBuffer byteBuffer) {
    }

    public abstract void onOpen(AbstractC2794dd ddVar);

    @Override // com.tendcloud.tenddata.AbstractC2759ca
    public final void onWriteDemand(AbstractC2752bw bwVar) {
    }

    public AbstractRunnableC2762cd(URI uri) {
        this(uri, new C2770ch());
    }

    public AbstractRunnableC2762cd(URI uri, AbstractC2765cf cfVar) {
        this(uri, cfVar, null, 0);
    }

    public AbstractRunnableC2762cd(URI uri, AbstractC2765cf cfVar, Map map, int i) {
        this.uri = null;
        this.engine = null;
        this.socket = null;
        this.proxy = Proxy.NO_PROXY;
        this.connectLatch = new CountDownLatch(1);
        this.closeLatch = new CountDownLatch(1);
        this.connectTimeout = 0;
        if (uri == null) {
            throw new IllegalArgumentException();
        } else if (cfVar != null) {
            this.uri = uri;
            this.draft = cfVar;
            this.headers = map;
            this.connectTimeout = i;
            this.engine = new C2757bz(this, cfVar);
        } else {
            throw new IllegalArgumentException("null as draft is permitted for `WebSocketServer` only!");
        }
    }

    public URI getURI() {
        return this.uri;
    }

    @Override // com.tendcloud.tenddata.AbstractC2752bw
    public AbstractC2765cf getDraft() {
        return this.draft;
    }

    public void connect() {
        if (this.writeThread == null) {
            this.writeThread = new Thread(this);
            this.writeThread.start();
            return;
        }
        throw new IllegalStateException("WebSocketClient objects are not reuseable");
    }

    public boolean connectBlocking() {
        connect();
        this.connectLatch.await();
        return this.engine.isOpen();
    }

    @Override // com.tendcloud.tenddata.AbstractC2752bw
    public void close() {
        if (this.writeThread != null) {
            this.engine.close(1000);
        }
    }

    public void closeBlocking() {
        close();
        this.closeLatch.await();
    }

    @Override // com.tendcloud.tenddata.AbstractC2752bw
    public void send(String str) {
        this.engine.send(str);
    }

    @Override // com.tendcloud.tenddata.AbstractC2752bw
    public void send(byte[] bArr) {
        this.engine.send(bArr);
    }

    @Override // java.lang.Runnable
    public void run() {
        int read;
        try {
            C2813ds.f13808c.put(Long.valueOf(Thread.currentThread().getId()), getURI().getHost());
            try {
                if (this.socket == null) {
                    this.socket = new Socket(this.proxy);
                } else if (this.socket.isClosed()) {
                    throw new IOException();
                }
                if (!this.socket.isBound()) {
                    this.socket.connect(new InetSocketAddress(this.uri.getHost(), getPort()), this.connectTimeout);
                }
                this.istream = this.socket.getInputStream();
                this.ostream = this.socket.getOutputStream();
                sendHandshake();
                this.writeThread = new Thread(new RunnableC2763a());
                this.writeThread.start();
                byte[] bArr = new byte[C2757bz.f13640c];
                while (!isClosed() && (read = this.istream.read(bArr)) != -1) {
                    try {
                        this.engine.decode(ByteBuffer.wrap(bArr, 0, read));
                    } catch (IOException unused) {
                        this.engine.m16190b();
                        return;
                    } catch (RuntimeException e) {
                        onError(e);
                        this.engine.closeConnection(1006, e.getMessage());
                        return;
                    }
                }
                this.engine.m16190b();
            } catch (Exception e2) {
                onWebsocketError(this.engine, e2);
                this.engine.closeConnection(-1, e2.getMessage());
            }
        } catch (Throwable unused2) {
        }
    }

    private int getPort() {
        int port = this.uri.getPort();
        if (port != -1) {
            return port;
        }
        String scheme = this.uri.getScheme();
        if (scheme.equals(C2663aa.f13480w)) {
            return AbstractC2752bw.f13639b;
        }
        if (scheme.equals("ws")) {
            return 80;
        }
        throw new RuntimeException("unkonow scheme" + scheme);
    }

    private void sendHandshake() {
        String path = this.uri.getPath();
        String query = this.uri.getQuery();
        if (path == null || path.length() == 0) {
            path = "/";
        }
        if (query != null) {
            path = path + "?" + query;
        }
        int port = getPort();
        StringBuilder sb = new StringBuilder();
        sb.append(this.uri.getHost());
        sb.append(port != 80 ? ":" + port : "");
        String sb2 = sb.toString();
        C2789cz czVar = new C2789cz();
        czVar.setResourceDescriptor(path);
        czVar.mo16132a(HTTP.TARGET_HOST, sb2);
        Map map = this.headers;
        if (map != null) {
            for (Map.Entry entry : map.entrySet()) {
                czVar.mo16132a((String) entry.getKey(), (String) entry.getValue());
            }
        }
        this.engine.startHandshake(czVar);
    }

    @Override // com.tendcloud.tenddata.AbstractC2752bw
    public AbstractC2752bw.EnumC2753a getReadyState() {
        return this.engine.getReadyState();
    }

    @Override // com.tendcloud.tenddata.AbstractC2759ca
    public final void onWebsocketMessage(AbstractC2752bw bwVar, String str) {
        onMessage(str);
    }

    @Override // com.tendcloud.tenddata.AbstractC2759ca
    public final void onWebsocketMessage(AbstractC2752bw bwVar, ByteBuffer byteBuffer) {
        onMessage(byteBuffer);
    }

    @Override // com.tendcloud.tenddata.AbstractC2755bx, com.tendcloud.tenddata.AbstractC2759ca
    public void onWebsocketMessageFragment(AbstractC2752bw bwVar, AbstractC2783cu cuVar) {
        onFragment(cuVar);
    }

    @Override // com.tendcloud.tenddata.AbstractC2759ca
    public final void onWebsocketOpen(AbstractC2752bw bwVar, AbstractC2792db dbVar) {
        this.connectLatch.countDown();
        onOpen((AbstractC2794dd) dbVar);
    }

    @Override // com.tendcloud.tenddata.AbstractC2759ca
    public final void onWebsocketClose(AbstractC2752bw bwVar, int i, String str, boolean z) {
        this.connectLatch.countDown();
        this.closeLatch.countDown();
        Thread thread = this.writeThread;
        if (thread != null) {
            thread.interrupt();
        }
        try {
            if (this.socket != null) {
                this.socket.close();
            }
        } catch (IOException e) {
            onWebsocketError(this, e);
        }
        onClose(i, str, z);
    }

    @Override // com.tendcloud.tenddata.AbstractC2759ca
    public final void onWebsocketError(AbstractC2752bw bwVar, Exception exc) {
        onError(exc);
    }

    @Override // com.tendcloud.tenddata.AbstractC2759ca
    public void onWebsocketCloseInitiated(AbstractC2752bw bwVar, int i, String str) {
        onCloseInitiated(i, str);
    }

    @Override // com.tendcloud.tenddata.AbstractC2759ca
    public void onWebsocketClosing(AbstractC2752bw bwVar, int i, String str, boolean z) {
        onClosing(i, str, z);
    }

    public AbstractC2752bw getConnection() {
        return this.engine;
    }

    @Override // com.tendcloud.tenddata.AbstractC2759ca
    public InetSocketAddress getLocalSocketAddress(AbstractC2752bw bwVar) {
        Socket socket = this.socket;
        if (socket != null) {
            return (InetSocketAddress) socket.getLocalSocketAddress();
        }
        return null;
    }

    @Override // com.tendcloud.tenddata.AbstractC2759ca
    public InetSocketAddress getRemoteSocketAddress(AbstractC2752bw bwVar) {
        Socket socket = this.socket;
        if (socket != null) {
            return (InetSocketAddress) socket.getRemoteSocketAddress();
        }
        return null;
    }

    /* compiled from: td */
    /* renamed from: com.tendcloud.tenddata.cd$a */
    /* loaded from: classes2.dex */
    class RunnableC2763a implements Runnable {
        private RunnableC2763a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            Thread.currentThread().setName("WebsocketWriteThread");
            while (!Thread.interrupted()) {
                try {
                    ByteBuffer byteBuffer = (ByteBuffer) AbstractRunnableC2762cd.this.engine.f13646h.take();
                    AbstractRunnableC2762cd.this.ostream.write(byteBuffer.array(), 0, byteBuffer.limit());
                    AbstractRunnableC2762cd.this.ostream.flush();
                } catch (IOException unused) {
                    AbstractRunnableC2762cd.this.engine.m16190b();
                    return;
                } catch (Throwable unused2) {
                    return;
                }
            }
        }
    }

    public void setProxy(Proxy proxy) {
        if (proxy != null) {
            this.proxy = proxy;
            return;
        }
        throw new IllegalArgumentException();
    }

    public void setSocket(Socket socket) {
        if (this.socket == null) {
            this.socket = socket;
            return;
        }
        throw new IllegalStateException("socket has already been set");
    }

    @Override // com.tendcloud.tenddata.AbstractC2752bw
    public void sendFragmentedFrame(AbstractC2783cu.EnumC2784a aVar, ByteBuffer byteBuffer, boolean z) {
        this.engine.sendFragmentedFrame(aVar, byteBuffer, z);
    }

    @Override // com.tendcloud.tenddata.AbstractC2752bw
    public boolean isOpen() {
        return this.engine.isOpen();
    }

    @Override // com.tendcloud.tenddata.AbstractC2752bw
    public boolean isFlushAndClose() {
        return this.engine.isFlushAndClose();
    }

    @Override // com.tendcloud.tenddata.AbstractC2752bw
    public boolean isClosed() {
        return this.engine.isClosed();
    }

    @Override // com.tendcloud.tenddata.AbstractC2752bw
    public boolean isClosing() {
        return this.engine.isClosing();
    }

    @Override // com.tendcloud.tenddata.AbstractC2752bw
    public boolean isConnecting() {
        return this.engine.isConnecting();
    }

    @Override // com.tendcloud.tenddata.AbstractC2752bw
    public boolean hasBufferedData() {
        return this.engine.hasBufferedData();
    }

    @Override // com.tendcloud.tenddata.AbstractC2752bw
    public void close(int i) {
        this.engine.close();
    }

    @Override // com.tendcloud.tenddata.AbstractC2752bw
    public void close(int i, String str) {
        this.engine.close(i, str);
    }

    @Override // com.tendcloud.tenddata.AbstractC2752bw
    public void closeConnection(int i, String str) {
        this.engine.closeConnection(i, str);
    }

    @Override // com.tendcloud.tenddata.AbstractC2752bw
    public void send(ByteBuffer byteBuffer) {
        this.engine.send(byteBuffer);
    }

    @Override // com.tendcloud.tenddata.AbstractC2752bw
    public void sendFrame(AbstractC2783cu cuVar) {
        this.engine.sendFrame(cuVar);
    }

    @Override // com.tendcloud.tenddata.AbstractC2752bw
    public InetSocketAddress getLocalSocketAddress() {
        return this.engine.getLocalSocketAddress();
    }

    @Override // com.tendcloud.tenddata.AbstractC2752bw
    public InetSocketAddress getRemoteSocketAddress() {
        return this.engine.getRemoteSocketAddress();
    }

    @Override // com.tendcloud.tenddata.AbstractC2752bw
    public String getResourceDescriptor() {
        return this.uri.getPath();
    }
}
