package com.cyjh.ddy.media.media.listener;

import com.xuhao.didi.socket.client.sdk.client.ConnectionInfo;

/* renamed from: com.cyjh.ddy.media.media.listener.g */
/* loaded from: classes.dex */
public interface IHwyMediaListener {
    void MediaConnectRefuse(int i, String str);

    void mediaCloseSuccess();

    void mediaConnectError(String str);

    void mediaConnectSuccess();

    void mediaFirstFrameSuccess(ConnectionInfo aVar);

    void showFPS(String str);

    void showLeftPacketLength(int i, int i2);

    void showPing(String str);

    void upFrameTime(long j);

    void upTraffic(long j, long j2);
}
