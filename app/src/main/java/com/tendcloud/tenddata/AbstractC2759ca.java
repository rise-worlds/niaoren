package com.tendcloud.tenddata;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;

/* compiled from: td */
/* renamed from: com.tendcloud.tenddata.ca */
/* loaded from: classes2.dex */
public interface AbstractC2759ca {
    String getFlashPolicy(AbstractC2752bw bwVar);

    InetSocketAddress getLocalSocketAddress(AbstractC2752bw bwVar);

    InetSocketAddress getRemoteSocketAddress(AbstractC2752bw bwVar);

    void onWebsocketClose(AbstractC2752bw bwVar, int i, String str, boolean z);

    void onWebsocketCloseInitiated(AbstractC2752bw bwVar, int i, String str);

    void onWebsocketClosing(AbstractC2752bw bwVar, int i, String str, boolean z);

    void onWebsocketError(AbstractC2752bw bwVar, Exception exc);

    void onWebsocketHandshakeReceivedAsClient(AbstractC2752bw bwVar, AbstractC2786cw cwVar, AbstractC2794dd ddVar);

    AbstractC2795de onWebsocketHandshakeReceivedAsServer(AbstractC2752bw bwVar, AbstractC2765cf cfVar, AbstractC2786cw cwVar);

    void onWebsocketHandshakeSentAsClient(AbstractC2752bw bwVar, AbstractC2786cw cwVar);

    void onWebsocketMessage(AbstractC2752bw bwVar, String str);

    void onWebsocketMessage(AbstractC2752bw bwVar, ByteBuffer byteBuffer);

    void onWebsocketMessageFragment(AbstractC2752bw bwVar, AbstractC2783cu cuVar);

    void onWebsocketOpen(AbstractC2752bw bwVar, AbstractC2792db dbVar);

    void onWebsocketPing(AbstractC2752bw bwVar, AbstractC2783cu cuVar);

    void onWebsocketPong(AbstractC2752bw bwVar, AbstractC2783cu cuVar);

    void onWriteDemand(AbstractC2752bw bwVar);
}
