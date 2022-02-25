package com.p018b.p019a;

/* compiled from: ConnectionSpec.java */
/* renamed from: com.b.a.q */
/* loaded from: classes.dex */
public final class C0912q {

    /* renamed from: a */
    boolean f6372a;

    /* renamed from: b */
    String[] f6373b;

    /* renamed from: c */
    String[] f6374c;

    /* renamed from: d */
    boolean f6375d;

    /* JADX INFO: Access modifiers changed from: package-private */
    public C0912q(boolean z) {
        this.f6372a = z;
    }

    public C0912q(ConnectionSpec pVar) {
        this.f6372a = pVar.f6368d;
        this.f6373b = pVar.f6370f;
        this.f6374c = pVar.f6371g;
        this.f6375d = pVar.f6369e;
    }

    /* renamed from: a */
    public final C0912q m24387a(String... strArr) {
        if (!this.f6372a) {
            throw new IllegalStateException("no cipher suites for cleartext connections");
        } else if (strArr.length != 0) {
            this.f6373b = (String[]) strArr.clone();
            return this;
        } else {
            throw new IllegalArgumentException("At least one cipher suite is required");
        }
    }

    /* renamed from: a */
    public final C0912q m24388a(TlsVersion... axVarArr) {
        if (this.f6372a) {
            String[] strArr = new String[axVarArr.length];
            for (int i = 0; i < axVarArr.length; i++) {
                strArr[i] = axVarArr[i].f6208f;
            }
            return m24385b(strArr);
        }
        throw new IllegalStateException("no TLS versions for cleartext connections");
    }

    /* renamed from: b */
    public final C0912q m24385b(String... strArr) {
        if (!this.f6372a) {
            throw new IllegalStateException("no TLS versions for cleartext connections");
        } else if (strArr.length != 0) {
            this.f6374c = (String[]) strArr.clone();
            return this;
        } else {
            throw new IllegalArgumentException("At least one TLS version is required");
        }
    }

    /* renamed from: a */
    public final C0912q m24389a() {
        if (this.f6372a) {
            this.f6375d = true;
            return this;
        }
        throw new IllegalStateException("no TLS extensions for cleartext connections");
    }

    /* renamed from: b */
    public final ConnectionSpec m24386b() {
        return new ConnectionSpec(this);
    }
}
