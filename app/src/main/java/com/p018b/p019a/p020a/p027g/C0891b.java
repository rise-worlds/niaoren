package com.p018b.p019a.p020a.p027g;

import com.p018b.p019a.p020a.p028h.CertificateChainCleaner;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.security.cert.Certificate;
import java.security.cert.X509Certificate;
import java.util.List;
import javax.net.ssl.SSLPeerUnverifiedException;

/* compiled from: AndroidPlatform.java */
/* renamed from: com.b.a.a.g.b */
/* loaded from: classes.dex */
final class C0891b extends CertificateChainCleaner {

    /* renamed from: a */
    private final Object f6025a;

    /* renamed from: b */
    private final Method f6026b;

    public final int hashCode() {
        return 0;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public C0891b(Object obj, Method method) {
        this.f6025a = obj;
        this.f6026b = method;
    }

    @Override // com.p018b.p019a.p020a.p028h.CertificateChainCleaner
    /* renamed from: a */
    public final List<Certificate> mo24572a(List<Certificate> list, String str) {
        try {
            return (List) this.f6026b.invoke(this.f6025a, (X509Certificate[]) list.toArray(new X509Certificate[list.size()]), "RSA", str);
        } catch (IllegalAccessException e) {
            throw new AssertionError(e);
        } catch (InvocationTargetException e2) {
            SSLPeerUnverifiedException sSLPeerUnverifiedException = new SSLPeerUnverifiedException(e2.getMessage());
            sSLPeerUnverifiedException.initCause(e2);
            throw sSLPeerUnverifiedException;
        }
    }

    public final boolean equals(Object obj) {
        return obj instanceof C0891b;
    }
}
