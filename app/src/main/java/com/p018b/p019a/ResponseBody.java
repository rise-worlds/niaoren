package com.p018b.p019a;

import com.p018b.p019a.p020a.Util;
import com.p018b.p029b.Buffer;
import com.p018b.p029b.BufferedSource;
import java.io.Closeable;

/* renamed from: com.b.a.au */
/* loaded from: classes.dex */
public abstract class ResponseBody implements Closeable {
    /* renamed from: a */
    public abstract MediaType mo24427a();

    /* renamed from: b */
    public abstract long mo24426b();

    /* renamed from: c */
    public abstract BufferedSource mo24425c();

    /* renamed from: d */
    public final String m24428d() {
        BufferedSource c = mo24425c();
        try {
            MediaType a = mo24427a();
            return c.mo24287a(Util.m24767a(c, a != null ? a.m24509a(Util.f5781e) : Util.f5781e));
        } finally {
            Util.m24764a(c);
        }
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        Util.m24764a(mo24425c());
    }

    /* renamed from: a */
    public static ResponseBody m24429a(byte[] bArr) {
        Buffer a = new Buffer().mo24297b(bArr);
        long length = bArr.length;
        if (a != null) {
            return new C0906av(length, a);
        }
        throw new NullPointerException("source == null");
    }
}
