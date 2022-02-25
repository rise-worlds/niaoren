package com.p018b.p019a;

import com.p018b.p019a.p020a.p023c.HttpMethod;

/* compiled from: Request.java */
/* renamed from: com.b.a.ap */
/* loaded from: classes.dex */
public final class C0903ap {

    /* renamed from: a */
    HttpUrl f6162a;

    /* renamed from: b */
    String f6163b;

    /* renamed from: c */
    C0896ab f6164c;

    /* renamed from: d */
    RequestBody f6165d;

    /* renamed from: e */
    Object f6166e;

    public C0903ap() {
        this.f6163b = "GET";
        this.f6164c = new C0896ab();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public C0903ap(Request aoVar) {
        this.f6162a = aoVar.f6156a;
        this.f6163b = aoVar.f6157b;
        this.f6165d = aoVar.f6159d;
        this.f6166e = aoVar.f6160e;
        this.f6164c = aoVar.f6158c.m24556b();
    }

    /* renamed from: a */
    public final C0903ap m24461a(HttpUrl acVar) {
        if (acVar != null) {
            this.f6162a = acVar;
            return this;
        }
        throw new NullPointerException("url == null");
    }

    /* renamed from: a */
    public final C0903ap m24459a(String str) {
        if (str != null) {
            if (str.regionMatches(true, 0, "ws:", 0, 3)) {
                str = "http:" + str.substring(3);
            } else if (str.regionMatches(true, 0, "wss:", 0, 4)) {
                str = "https:" + str.substring(4);
            }
            HttpUrl e = HttpUrl.m24530e(str);
            if (e != null) {
                return m24461a(e);
            }
            throw new IllegalArgumentException("unexpected url: " + str);
        }
        throw new NullPointerException("url == null");
    }

    /* renamed from: a */
    public final C0903ap m24457a(String str, String str2) {
        this.f6164c.m24550c(str, str2);
        return this;
    }

    /* renamed from: b */
    public final C0903ap m24456b(String str) {
        this.f6164c.m24553a(str);
        return this;
    }

    /* renamed from: a */
    public final C0903ap m24462a(Headers aaVar) {
        this.f6164c = aaVar.m24556b();
        return this;
    }

    /* renamed from: a */
    public final C0903ap m24460a(RequestBody aqVar) {
        return m24458a("POST", aqVar);
    }

    /* renamed from: a */
    public final C0903ap m24458a(String str, RequestBody aqVar) {
        if (str == null) {
            throw new NullPointerException("method == null");
        } else if (str.length() == 0) {
            throw new IllegalArgumentException("method.length() == 0");
        } else if (aqVar != null && !HttpMethod.m24734b(str)) {
            throw new IllegalArgumentException("method " + str + " must not have a request body.");
        } else if (aqVar != null || !HttpMethod.m24735a(str)) {
            this.f6163b = str;
            this.f6165d = aqVar;
            return this;
        } else {
            throw new IllegalArgumentException("method " + str + " must have a request body.");
        }
    }

    /* renamed from: a */
    public final Request m24463a() {
        if (this.f6162a != null) {
            return new Request(this);
        }
        throw new IllegalStateException("url == null");
    }
}
