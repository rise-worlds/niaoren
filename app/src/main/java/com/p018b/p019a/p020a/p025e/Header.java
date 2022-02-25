package com.p018b.p019a.p020a.p025e;

import com.p018b.p019a.p020a.Util;
import com.p018b.p029b.ByteString;

/* renamed from: com.b.a.a.e.c */
/* loaded from: classes.dex */
public final class Header {

    /* renamed from: a */
    public static final ByteString f5896a = ByteString.m24315a(":");

    /* renamed from: b */
    public static final ByteString f5897b = ByteString.m24315a(okhttp3.internal.http2.Header.RESPONSE_STATUS_UTF8);

    /* renamed from: c */
    public static final ByteString f5898c = ByteString.m24315a(okhttp3.internal.http2.Header.TARGET_METHOD_UTF8);

    /* renamed from: d */
    public static final ByteString f5899d = ByteString.m24315a(okhttp3.internal.http2.Header.TARGET_PATH_UTF8);

    /* renamed from: e */
    public static final ByteString f5900e = ByteString.m24315a(okhttp3.internal.http2.Header.TARGET_SCHEME_UTF8);

    /* renamed from: f */
    public static final ByteString f5901f = ByteString.m24315a(okhttp3.internal.http2.Header.TARGET_AUTHORITY_UTF8);

    /* renamed from: g */
    public final ByteString f5902g;

    /* renamed from: h */
    public final ByteString f5903h;

    /* renamed from: i */
    final int f5904i;

    public Header(String str, String str2) {
        this(ByteString.m24315a(str), ByteString.m24315a(str2));
    }

    public Header(ByteString iVar, String str) {
        this(iVar, ByteString.m24315a(str));
    }

    public Header(ByteString iVar, ByteString iVar2) {
        this.f5902g = iVar;
        this.f5903h = iVar2;
        this.f5904i = iVar.mo24255g() + 32 + iVar2.mo24255g();
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof Header)) {
            return false;
        }
        Header cVar = (Header) obj;
        return this.f5902g.equals(cVar.f5902g) && this.f5903h.equals(cVar.f5903h);
    }

    public final int hashCode() {
        return ((this.f5902g.hashCode() + 527) * 31) + this.f5903h.hashCode();
    }

    public final String toString() {
        return Util.m24755a("%s: %s", this.f5902g.mo24267a(), this.f5903h.mo24267a());
    }
}
