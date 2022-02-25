package com.p018b.p019a;

import com.p018b.p019a.p020a.Util;
import com.p018b.p029b.BufferedSink;

/* renamed from: com.b.a.aq */
/* loaded from: classes.dex */
public abstract class RequestBody {
    /* renamed from: a */
    public abstract MediaType mo24366a();

    /* renamed from: a */
    public abstract void mo24365a(BufferedSink gVar);

    /* renamed from: b */
    public long mo24363b() {
        return -1L;
    }

    /* renamed from: a */
    public static RequestBody m24455a(byte[] bArr) {
        int length = bArr.length;
        if (bArr != null) {
            Util.m24769a(bArr.length, length);
            return new C0904ar(length, bArr);
        }
        throw new NullPointerException("content == null");
    }
}
