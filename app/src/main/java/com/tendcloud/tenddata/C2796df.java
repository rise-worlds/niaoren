package com.tendcloud.tenddata;

import com.tendcloud.tenddata.AbstractRunnableC2798dh;
import java.net.Socket;
import java.nio.channels.ByteChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.SocketChannel;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLEngine;

/* compiled from: td */
/* renamed from: com.tendcloud.tenddata.df */
/* loaded from: classes2.dex */
public class C2796df implements AbstractRunnableC2798dh.AbstractC2799a {

    /* renamed from: a */
    protected SSLContext f13719a;

    /* renamed from: b */
    protected ExecutorService f13720b;

    public C2796df(SSLContext sSLContext) {
        this(sSLContext, Executors.newSingleThreadScheduledExecutor());
    }

    public C2796df(SSLContext sSLContext, ExecutorService executorService) {
        if (sSLContext == null || executorService == null) {
            throw new IllegalArgumentException();
        }
        this.f13719a = sSLContext;
        this.f13720b = executorService;
    }

    @Override // com.tendcloud.tenddata.AbstractRunnableC2798dh.AbstractC2799a
    public ByteChannel wrapChannel(SocketChannel socketChannel, SelectionKey selectionKey) {
        SSLEngine createSSLEngine = this.f13719a.createSSLEngine();
        createSSLEngine.setUseClientMode(false);
        return new C2750bu(socketChannel, createSSLEngine, this.f13720b, selectionKey);
    }

    @Override // com.tendcloud.tenddata.AbstractRunnableC2798dh.AbstractC2799a
    /* renamed from: createWebSocket */
    public C2757bz mo16126a(AbstractC2755bx bxVar, AbstractC2765cf cfVar, Socket socket) {
        return new C2757bz(bxVar, cfVar);
    }

    @Override // com.tendcloud.tenddata.AbstractRunnableC2798dh.AbstractC2799a
    /* renamed from: createWebSocket */
    public C2757bz mo16125a(AbstractC2755bx bxVar, List list, Socket socket) {
        return new C2757bz(bxVar, list);
    }
}
