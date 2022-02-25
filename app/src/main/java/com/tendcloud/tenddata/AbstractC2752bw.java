package com.tendcloud.tenddata;

import com.tendcloud.tenddata.AbstractC2783cu;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;

/* compiled from: td */
/* renamed from: com.tendcloud.tenddata.bw */
/* loaded from: classes2.dex */
public interface AbstractC2752bw {

    /* renamed from: a */
    public static final int f13638a = 80;

    /* renamed from: b */
    public static final int f13639b = 443;

    /* compiled from: td */
    /* renamed from: com.tendcloud.tenddata.bw$a */
    /* loaded from: classes2.dex */
    public enum EnumC2753a {
        NOT_YET_CONNECTED,
        CONNECTING,
        OPEN,
        CLOSING,
        CLOSED
    }

    /* compiled from: td */
    /* renamed from: com.tendcloud.tenddata.bw$b */
    /* loaded from: classes2.dex */
    public enum EnumC2754b {
        CLIENT,
        SERVER
    }

    void close();

    void close(int i);

    void close(int i, String str);

    void closeConnection(int i, String str);

    AbstractC2765cf getDraft();

    InetSocketAddress getLocalSocketAddress();

    EnumC2753a getReadyState();

    InetSocketAddress getRemoteSocketAddress();

    String getResourceDescriptor();

    boolean hasBufferedData();

    boolean isClosed();

    boolean isClosing();

    boolean isConnecting();

    boolean isFlushAndClose();

    boolean isOpen();

    void send(String str);

    void send(ByteBuffer byteBuffer);

    void send(byte[] bArr);

    void sendFragmentedFrame(AbstractC2783cu.EnumC2784a aVar, ByteBuffer byteBuffer, boolean z);

    void sendFrame(AbstractC2783cu cuVar);
}
