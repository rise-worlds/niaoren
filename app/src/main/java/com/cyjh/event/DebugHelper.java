package com.cyjh.event;

import com.google.protobuf.ByteString;

/* renamed from: com.cyjh.event.a */
/* loaded from: classes.dex */
public final class DebugHelper {

    /* renamed from: a */
    private static boolean f8181a = false;

    /* renamed from: b */
    private static ByteString f8182b;

    /* renamed from: a */
    public static byte[] m21096a() {
        f8182b = null;
        f8181a = true;
        ByteString byteString = f8182b;
        if (byteString == null) {
            return null;
        }
        return byteString.toByteArray();
    }

    /* renamed from: a */
    private static void m21095a(ByteString byteString) {
        f8182b = byteString;
        f8181a = false;
    }
}
