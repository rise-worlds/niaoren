package com.tendcloud.tenddata;

import com.tendcloud.tenddata.AbstractRunnableC2798dh;
import java.net.Socket;
import java.nio.channels.SelectionKey;
import java.nio.channels.SocketChannel;
import java.util.List;

/* compiled from: td */
/* renamed from: com.tendcloud.tenddata.dg */
/* loaded from: classes2.dex */
public class C2797dg implements AbstractRunnableC2798dh.AbstractC2799a {
    /* renamed from: a */
    public SocketChannel wrapChannel(SocketChannel socketChannel, SelectionKey selectionKey) {
        return socketChannel;
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
