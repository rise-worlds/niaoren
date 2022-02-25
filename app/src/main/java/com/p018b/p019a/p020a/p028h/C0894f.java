package com.p018b.p019a.p020a.p028h;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.security.cert.TrustAnchor;
import java.security.cert.X509Certificate;
import javax.net.ssl.X509TrustManager;

/* compiled from: TrustRootIndex.java */
/* renamed from: com.b.a.a.h.f */
/* loaded from: classes.dex */
final class C0894f extends TrustRootIndex {

    /* renamed from: a */
    private final X509TrustManager f6054a;

    /* renamed from: b */
    private final Method f6055b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public C0894f(X509TrustManager x509TrustManager, Method method) {
        this.f6055b = method;
        this.f6054a = x509TrustManager;
    }

    @Override // com.p018b.p019a.p020a.p028h.TrustRootIndex
    /* renamed from: a */
    public final X509Certificate mo24560a(X509Certificate x509Certificate) {
        try {
            TrustAnchor trustAnchor = (TrustAnchor) this.f6055b.invoke(this.f6054a, x509Certificate);
            if (trustAnchor != null) {
                return trustAnchor.getTrustedCert();
            }
            return null;
        } catch (IllegalAccessException unused) {
            throw new AssertionError();
        } catch (InvocationTargetException unused2) {
            return null;
        }
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof C0894f)) {
            return false;
        }
        C0894f fVar = (C0894f) obj;
        return this.f6054a.equals(fVar.f6054a) && this.f6055b.equals(fVar.f6055b);
    }

    public final int hashCode() {
        return this.f6054a.hashCode() + (this.f6055b.hashCode() * 31);
    }
}
