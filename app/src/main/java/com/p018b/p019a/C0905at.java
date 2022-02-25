package com.p018b.p019a;

/* compiled from: Response.java */
/* renamed from: com.b.a.at */
/* loaded from: classes.dex */
public final class C0905at {

    /* renamed from: a */
    Request f6184a;

    /* renamed from: b */
    Protocol f6185b;

    /* renamed from: c */
    int f6186c;

    /* renamed from: d */
    String f6187d;

    /* renamed from: e */
    Handshake f6188e;

    /* renamed from: f */
    C0896ab f6189f;

    /* renamed from: g */
    ResponseBody f6190g;

    /* renamed from: h */
    Response f6191h;

    /* renamed from: i */
    Response f6192i;

    /* renamed from: j */
    Response f6193j;

    /* renamed from: k */
    long f6194k;

    /* renamed from: l */
    long f6195l;

    public C0905at() {
        this.f6186c = -1;
        this.f6189f = new C0896ab();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public C0905at(Response asVar) {
        this.f6186c = -1;
        this.f6184a = asVar.f6171a;
        this.f6185b = asVar.f6172b;
        this.f6186c = asVar.f6173c;
        this.f6187d = asVar.f6174d;
        this.f6188e = asVar.f6175e;
        this.f6189f = asVar.f6176f.m24556b();
        this.f6190g = asVar.f6177g;
        this.f6191h = asVar.f6178h;
        this.f6192i = asVar.f6179i;
        this.f6193j = asVar.f6180j;
        this.f6194k = asVar.f6181k;
        this.f6195l = asVar.f6182l;
    }

    /* renamed from: a */
    public final C0905at m24439a(Request aoVar) {
        this.f6184a = aoVar;
        return this;
    }

    /* renamed from: a */
    public final C0905at m24440a(Protocol alVar) {
        this.f6185b = alVar;
        return this;
    }

    /* renamed from: a */
    public final C0905at m24443a(int i) {
        this.f6186c = i;
        return this;
    }

    /* renamed from: a */
    public final C0905at m24435a(String str) {
        this.f6187d = str;
        return this;
    }

    /* renamed from: a */
    public final C0905at m24436a(Handshake zVar) {
        this.f6188e = zVar;
        return this;
    }

    /* renamed from: a */
    public final C0905at m24433a(String str, String str2) {
        this.f6189f.m24552a(str, str2);
        return this;
    }

    /* renamed from: a */
    public final C0905at m24441a(Headers aaVar) {
        this.f6189f = aaVar.m24556b();
        return this;
    }

    /* renamed from: a */
    public final C0905at m24437a(ResponseBody auVar) {
        this.f6190g = auVar;
        return this;
    }

    /* renamed from: a */
    public final C0905at m24438a(Response asVar) {
        if (asVar != null) {
            m24434a("networkResponse", asVar);
        }
        this.f6191h = asVar;
        return this;
    }

    /* renamed from: b */
    public final C0905at m24431b(Response asVar) {
        if (asVar != null) {
            m24434a("cacheResponse", asVar);
        }
        this.f6192i = asVar;
        return this;
    }

    /* renamed from: a */
    private static void m24434a(String str, Response asVar) {
        if (asVar.f6177g != null) {
            throw new IllegalArgumentException(str + ".body != null");
        } else if (asVar.f6178h != null) {
            throw new IllegalArgumentException(str + ".networkResponse != null");
        } else if (asVar.f6179i != null) {
            throw new IllegalArgumentException(str + ".cacheResponse != null");
        } else if (asVar.f6180j != null) {
            throw new IllegalArgumentException(str + ".priorResponse != null");
        }
    }

    /* renamed from: a */
    public final C0905at m24442a(long j) {
        this.f6194k = j;
        return this;
    }

    /* renamed from: b */
    public final C0905at m24432b(long j) {
        this.f6195l = j;
        return this;
    }

    /* renamed from: a */
    public final Response m24444a() {
        if (this.f6184a == null) {
            throw new IllegalStateException("request == null");
        } else if (this.f6185b == null) {
            throw new IllegalStateException("protocol == null");
        } else if (this.f6186c >= 0) {
            return new Response(this);
        } else {
            throw new IllegalStateException("code < 0: " + this.f6186c);
        }
    }

    /* renamed from: c */
    public final C0905at m24430c(Response asVar) {
        if (asVar == null || asVar.f6177g == null) {
            this.f6193j = asVar;
            return this;
        }
        throw new IllegalArgumentException("priorResponse.body != null");
    }
}
