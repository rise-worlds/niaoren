package com.alipay.android.phone.mrpc.core;

/* renamed from: com.alipay.android.phone.mrpc.core.p */
/* loaded from: classes.dex */
public final class C0610p extends C0615u {

    /* renamed from: c */
    private int f177c;

    /* renamed from: d */
    private String f178d;

    /* renamed from: e */
    private long f179e;

    /* renamed from: f */
    private long f180f;

    /* renamed from: g */
    private String f181g;

    /* renamed from: h */
    private HttpUrlHeader f182h;

    public C0610p(HttpUrlHeader httpUrlHeader, int i, String str, byte[] bArr) {
        this.f182h = httpUrlHeader;
        this.f177c = i;
        this.f178d = str;
        this.f203a = bArr;
    }

    /* renamed from: a */
    public final HttpUrlHeader m25472a() {
        return this.f182h;
    }

    /* renamed from: a */
    public final void m25471a(long j) {
        this.f179e = j;
    }

    /* renamed from: a */
    public final void m25470a(String str) {
        this.f181g = str;
    }

    /* renamed from: b */
    public final void m25469b(long j) {
        this.f180f = j;
    }
}
