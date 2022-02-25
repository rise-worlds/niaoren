package com.p018b.p019a.p020a.p028h;

import java.security.cert.X509Certificate;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import javax.security.auth.x500.X500Principal;

/* compiled from: TrustRootIndex.java */
/* renamed from: com.b.a.a.h.g */
/* loaded from: classes.dex */
final class C0895g extends TrustRootIndex {

    /* renamed from: a */
    private final Map<X500Principal, Set<X509Certificate>> f6056a = new LinkedHashMap();

    public C0895g(X509Certificate... x509CertificateArr) {
        for (X509Certificate x509Certificate : x509CertificateArr) {
            X500Principal subjectX500Principal = x509Certificate.getSubjectX500Principal();
            Set<X509Certificate> set = this.f6056a.get(subjectX500Principal);
            if (set == null) {
                set = new LinkedHashSet<>(1);
                this.f6056a.put(subjectX500Principal, set);
            }
            set.add(x509Certificate);
        }
    }

    @Override // com.p018b.p019a.p020a.p028h.TrustRootIndex
    /* renamed from: a */
    public final X509Certificate mo24560a(X509Certificate x509Certificate) {
        Set<X509Certificate> set = this.f6056a.get(x509Certificate.getIssuerX500Principal());
        if (set == null) {
            return null;
        }
        for (X509Certificate x509Certificate2 : set) {
            try {
                x509Certificate.verify(x509Certificate2.getPublicKey());
                return x509Certificate2;
            } catch (Exception unused) {
            }
        }
        return null;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        return (obj instanceof C0895g) && ((C0895g) obj).f6056a.equals(this.f6056a);
    }

    public final int hashCode() {
        return this.f6056a.hashCode();
    }
}
