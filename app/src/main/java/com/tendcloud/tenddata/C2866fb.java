package com.tendcloud.tenddata;

/* compiled from: td */
/* renamed from: com.tendcloud.tenddata.fb */
/* loaded from: classes2.dex */
public final class C2866fb {

    /* renamed from: a */
    private static byte[] f13937a = new byte[0];

    private C2866fb() {
    }

    /* renamed from: a */
    public static byte[] m15767a(byte[] bArr) {
        try {
            C2870ff.m15743a();
            return C2868fd.m15758a(bArr);
        } catch (Throwable th) {
            C2933hb.postSDKError(th);
            return f13937a;
        }
    }

    /* renamed from: b */
    public static byte[] m15766b(byte[] bArr) {
        try {
            C2870ff.m15743a();
            return C2868fd.m15755b(bArr);
        } catch (Throwable th) {
            C2933hb.postSDKError(th);
            return f13937a;
        }
    }
}
