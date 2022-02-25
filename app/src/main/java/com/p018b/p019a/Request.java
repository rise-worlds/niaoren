package com.p018b.p019a;

/* renamed from: com.b.a.ao */
/* loaded from: classes.dex */
public final class Request {

    /* renamed from: a */
    final HttpUrl f6156a;

    /* renamed from: b */
    final String f6157b;

    /* renamed from: c */
    final Headers f6158c;

    /* renamed from: d */
    final RequestBody f6159d;

    /* renamed from: e */
    final Object f6160e;

    /* renamed from: f */
    private volatile CacheControl f6161f;

    /* JADX INFO: Access modifiers changed from: package-private */
    public Request(C0903ap apVar) {
        this.f6156a = apVar.f6162a;
        this.f6157b = apVar.f6163b;
        this.f6158c = apVar.f6164c.m24554a();
        this.f6159d = apVar.f6165d;
        this.f6160e = apVar.f6166e != null ? apVar.f6166e : this;
    }

    /* renamed from: a */
    public final HttpUrl m24471a() {
        return this.f6156a;
    }

    /* renamed from: b */
    public final String m24469b() {
        return this.f6157b;
    }

    /* renamed from: c */
    public final Headers m24468c() {
        return this.f6158c;
    }

    /* renamed from: a */
    public final String m24470a(String str) {
        return this.f6158c.m24557a(str);
    }

    /* renamed from: d */
    public final RequestBody m24467d() {
        return this.f6159d;
    }

    /* renamed from: e */
    public final C0903ap m24466e() {
        return new C0903ap(this);
    }

    /* renamed from: f */
    public final CacheControl m24465f() {
        CacheControl eVar = this.f6161f;
        if (eVar != null) {
            return eVar;
        }
        CacheControl a = CacheControl.m24417a(this.f6158c);
        this.f6161f = a;
        return a;
    }

    /* renamed from: g */
    public final boolean m24464g() {
        return this.f6156a.m24535c();
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("Request{method=");
        sb.append(this.f6157b);
        sb.append(", url=");
        sb.append(this.f6156a);
        sb.append(", tag=");
        Object obj = this.f6160e;
        if (obj == this) {
            obj = null;
        }
        sb.append(obj);
        sb.append('}');
        return sb.toString();
    }
}
