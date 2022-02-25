package com.p018b.p019a;

import java.io.Closeable;

/* renamed from: com.b.a.as */
/* loaded from: classes.dex */
public final class Response implements Closeable {

    /* renamed from: a */
    final Request f6171a;

    /* renamed from: b */
    final Protocol f6172b;

    /* renamed from: c */
    final int f6173c;

    /* renamed from: d */
    final String f6174d;

    /* renamed from: e */
    final Handshake f6175e;

    /* renamed from: f */
    final Headers f6176f;

    /* renamed from: g */
    final ResponseBody f6177g;

    /* renamed from: h */
    final Response f6178h;

    /* renamed from: i */
    final Response f6179i;

    /* renamed from: j */
    final Response f6180j;

    /* renamed from: k */
    final long f6181k;

    /* renamed from: l */
    final long f6182l;

    /* renamed from: m */
    private volatile CacheControl f6183m;

    /* JADX INFO: Access modifiers changed from: package-private */
    public Response(C0905at atVar) {
        this.f6171a = atVar.f6184a;
        this.f6172b = atVar.f6185b;
        this.f6173c = atVar.f6186c;
        this.f6174d = atVar.f6187d;
        this.f6175e = atVar.f6188e;
        this.f6176f = atVar.f6189f.m24554a();
        this.f6177g = atVar.f6190g;
        this.f6178h = atVar.f6191h;
        this.f6179i = atVar.f6192i;
        this.f6180j = atVar.f6193j;
        this.f6181k = atVar.f6194k;
        this.f6182l = atVar.f6195l;
    }

    /* renamed from: a */
    public final Request m24454a() {
        return this.f6171a;
    }

    /* renamed from: b */
    public final int m24452b() {
        return this.f6173c;
    }

    /* renamed from: c */
    public final Handshake m24451c() {
        return this.f6175e;
    }

    /* renamed from: d */
    public final Headers m24450d() {
        return this.f6176f;
    }

    /* renamed from: e */
    public final ResponseBody m24449e() {
        return this.f6177g;
    }

    /* renamed from: f */
    public final C0905at m24448f() {
        return new C0905at(this);
    }

    /* renamed from: g */
    public final CacheControl m24447g() {
        CacheControl eVar = this.f6183m;
        if (eVar != null) {
            return eVar;
        }
        CacheControl a = CacheControl.m24417a(this.f6176f);
        this.f6183m = a;
        return a;
    }

    /* renamed from: h */
    public final long m24446h() {
        return this.f6181k;
    }

    /* renamed from: i */
    public final long m24445i() {
        return this.f6182l;
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
        this.f6177g.close();
    }

    public final String toString() {
        return "Response{protocol=" + this.f6172b + ", code=" + this.f6173c + ", message=" + this.f6174d + ", url=" + this.f6171a.f6156a + '}';
    }

    /* renamed from: a */
    public final String m24453a(String str) {
        String a = this.f6176f.m24557a(str);
        if (a != null) {
            return a;
        }
        return null;
    }
}
