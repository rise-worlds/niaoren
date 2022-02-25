package com.cyjh.ddy.media.media.listener;

/* renamed from: com.cyjh.ddy.media.media.listener.e */
/* loaded from: classes.dex */
public interface IHwyManagerListener extends IHwySDKListener {
    void upConnTimes(long j);

    void upLeftPacketLength(int i, int i2);

    void upMsgBroadcast(String str, String str2);
}
