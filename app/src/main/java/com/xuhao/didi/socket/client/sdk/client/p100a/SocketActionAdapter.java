package com.xuhao.didi.socket.client.sdk.client.p100a;

import com.xuhao.didi.p082a.p084b.p085a.IPulseSendable;
import com.xuhao.didi.p082a.p084b.p085a.ISendable;
import com.xuhao.didi.p082a.p086c.OriginalData;
import com.xuhao.didi.socket.client.sdk.client.ConnectionInfo;

/* renamed from: com.xuhao.didi.socket.client.sdk.client.a.c */
/* loaded from: classes2.dex */
public abstract class SocketActionAdapter implements ISocketActionListener {
    @Override // com.xuhao.didi.socket.client.sdk.client.p100a.ISocketActionListener
    public void onPulseSend(ConnectionInfo aVar, IPulseSendable cVar) {
    }

    @Override // com.xuhao.didi.socket.client.sdk.client.p100a.ISocketActionListener
    public void onSocketConnectionFailed(ConnectionInfo aVar, String str, Exception exc) {
    }

    @Override // com.xuhao.didi.socket.client.sdk.client.p100a.ISocketActionListener
    public void onSocketConnectionSuccess(ConnectionInfo aVar, String str) {
    }

    @Override // com.xuhao.didi.socket.client.sdk.client.p100a.ISocketActionListener
    public void onSocketDisconnection(ConnectionInfo aVar, String str, Exception exc) {
    }

    @Override // com.xuhao.didi.socket.client.sdk.client.p100a.ISocketActionListener
    public void onSocketIOThreadShutdown(String str, Exception exc) {
    }

    @Override // com.xuhao.didi.socket.client.sdk.client.p100a.ISocketActionListener
    public void onSocketIOThreadStart(String str) {
    }

    @Override // com.xuhao.didi.socket.client.sdk.client.p100a.ISocketActionListener
    public void onSocketReadResponse(ConnectionInfo aVar, String str, OriginalData aVar2) {
    }

    @Override // com.xuhao.didi.socket.client.sdk.client.p100a.ISocketActionListener
    public void onSocketWriteResponse(ConnectionInfo aVar, String str, ISendable eVar) {
    }
}
