package com.xuhao.didi.socket.client.sdk.client.connection;

import com.xuhao.didi.socket.client.sdk.client.ConnectionInfo;

/* renamed from: com.xuhao.didi.socket.client.sdk.client.connection.c */
/* loaded from: classes2.dex */
public class NoneReconnect extends AbsReconnectionManager {
    @Override // com.xuhao.didi.socket.client.sdk.client.p100a.ISocketActionListener
    public void onSocketConnectionFailed(ConnectionInfo aVar, String str, Exception exc) {
    }

    @Override // com.xuhao.didi.socket.client.sdk.client.p100a.ISocketActionListener
    public void onSocketConnectionSuccess(ConnectionInfo aVar, String str) {
    }

    @Override // com.xuhao.didi.socket.client.sdk.client.p100a.ISocketActionListener
    public void onSocketDisconnection(ConnectionInfo aVar, String str, Exception exc) {
    }
}
