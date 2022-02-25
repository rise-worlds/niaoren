package com.p073ta.utdid2.p074a.p075a;

/* renamed from: com.ta.utdid2.a.a.d */
/* loaded from: classes2.dex */
public class C2516d {
    public static byte[] getBytes(int i) {
        byte[] bArr = {(byte) ((r3 >> 8) % 256), (byte) (r3 % 256), (byte) (r3 % 256), (byte) (i % 256)};
        int i2 = i >> 8;
        int i3 = i2 >> 8;
        return bArr;
    }
}
