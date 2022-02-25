package com.xuhao.didi.socket.client.sdk.client.p100a;

import com.xuhao.didi.p082a.p084b.p085a.IPulseSendable;
import com.xuhao.didi.p082a.p084b.p085a.ISendable;
import com.xuhao.didi.p082a.p086c.OriginalData;
import com.xuhao.didi.socket.client.sdk.client.ConnectionInfo;

/* renamed from: com.xuhao.didi.socket.client.sdk.client.a.b */
/* loaded from: classes2.dex */
public interface ISocketActionListener {
    void onPulseSend(ConnectionInfo aVar, IPulseSendable cVar);

    void onSocketConnectionFailed(ConnectionInfo aVar, String str, Exception exc);

    void onSocketConnectionSuccess(ConnectionInfo aVar, String str);

    void onSocketDisconnection(ConnectionInfo aVar, String str, Exception exc);

    void onSocketIOThreadShutdown(String str, Exception exc);

    void onSocketIOThreadStart(String str);

    void onSocketReadResponse(ConnectionInfo aVar, String str, OriginalData aVar2);

    void onSocketWriteResponse(ConnectionInfo aVar, String str, ISendable eVar);
}
