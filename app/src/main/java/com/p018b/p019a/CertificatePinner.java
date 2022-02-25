package com.p018b.p019a;

import com.p018b.p019a.p020a.Util;
import com.p018b.p019a.p020a.p028h.CertificateChainCleaner;
import com.p018b.p029b.ByteString;
import java.security.cert.Certificate;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import javax.net.ssl.SSLPeerUnverifiedException;

/* renamed from: com.b.a.i */
/* loaded from: classes.dex */
public final class CertificatePinner {

    /* renamed from: a */
    public static final CertificatePinner f6233a = new C0909j().m24401a();

    /* renamed from: b */
    private final Set<C0910k> f6234b;

    /* renamed from: c */
    private final CertificateChainCleaner f6235c;

    /* JADX INFO: Access modifiers changed from: package-private */
    public CertificatePinner(Set<C0910k> set, CertificateChainCleaner bVar) {
        this.f6234b = set;
        this.f6235c = bVar;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof CertificatePinner)) {
            return false;
        }
        CertificatePinner iVar = (CertificatePinner) obj;
        return Util.m24761a(this.f6235c, iVar.f6235c) && this.f6234b.equals(iVar.f6234b);
    }

    public final int hashCode() {
        CertificateChainCleaner bVar = this.f6235c;
        return ((bVar != null ? bVar.hashCode() : 0) * 31) + this.f6234b.hashCode();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public final CertificatePinner m24405a(CertificateChainCleaner bVar) {
        return Util.m24761a(this.f6235c, bVar) ? this : new CertificatePinner(this.f6234b, bVar);
    }

    /* renamed from: a */
    public static String m24403a(Certificate certificate) {
        if (certificate instanceof X509Certificate) {
            return "sha256/" + m24402a((X509Certificate) certificate).mo24261b();
        }
        throw new IllegalArgumentException("Certificate pinning requires X509 certificates");
    }

    /* renamed from: a */
    private static ByteString m24402a(X509Certificate x509Certificate) {
        return ByteString.m24314a(x509Certificate.getPublicKey().getEncoded()).mo24258d();
    }

    /* renamed from: a */
    public final void m24404a(String str, List<Certificate> list) {
        boolean z;
        List emptyList = Collections.emptyList();
        for (C0910k kVar : this.f6234b) {
            if (kVar.f6237a.startsWith("*.")) {
                z = str.regionMatches(false, str.indexOf(46) + 1, kVar.f6238b, 0, kVar.f6238b.length());
            } else {
                z = str.equals(kVar.f6238b);
            }
            if (z) {
                if (emptyList.isEmpty()) {
                    emptyList = new ArrayList();
                }
                emptyList.add(kVar);
            }
        }
        if (!emptyList.isEmpty()) {
            CertificateChainCleaner bVar = this.f6235c;
            if (bVar != null) {
                list = bVar.mo24572a(list, str);
            }
            int size = list.size();
            for (int i = 0; i < size; i++) {
                X509Certificate x509Certificate = (X509Certificate) list.get(i);
                int size2 = emptyList.size();
                ByteString iVar = null;
                ByteString iVar2 = null;
                for (int i2 = 0; i2 < size2; i2++) {
                    C0910k kVar2 = (C0910k) emptyList.get(i2);
                    if (kVar2.f6239c.equals("sha256/")) {
                        if (iVar == null) {
                            iVar = m24402a(x509Certificate);
                        }
                        if (kVar2.f6240d.equals(iVar)) {
                            return;
                        }
                    } else if (kVar2.f6239c.equals("sha1/")) {
                        if (iVar2 == null) {
                            iVar2 = ByteString.m24314a(x509Certificate.getPublicKey().getEncoded()).mo24259c();
                        }
                        if (kVar2.f6240d.equals(iVar2)) {
                            return;
                        }
                    } else {
                        throw new AssertionError();
                    }
                }
            }
            StringBuilder sb = new StringBuilder("Certificate pinning failure!\n  Peer certificate chain:");
            int size3 = list.size();
            for (int i3 = 0; i3 < size3; i3++) {
                X509Certificate x509Certificate2 = (X509Certificate) list.get(i3);
                sb.append("\n    ");
                sb.append(m24403a((Certificate) x509Certificate2));
                sb.append(": ");
                sb.append(x509Certificate2.getSubjectDN().getName());
            }
            sb.append("\n  Pinned certificates for ");
            sb.append(str);
            sb.append(":");
            int size4 = emptyList.size();
            for (int i4 = 0; i4 < size4; i4++) {
                sb.append("\n    ");
                sb.append((C0910k) emptyList.get(i4));
            }
            throw new SSLPeerUnverifiedException(sb.toString());
        }
    }
}
