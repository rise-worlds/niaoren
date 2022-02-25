package com.p018b.p019a;

import com.p018b.p019a.p020a.Util;
import java.security.cert.Certificate;
import java.util.Collections;
import java.util.List;
import javax.net.ssl.SSLPeerUnverifiedException;
import javax.net.ssl.SSLSession;

/* renamed from: com.b.a.z */
/* loaded from: classes.dex */
public final class Handshake {

    /* renamed from: a */
    private final TlsVersion f6403a;

    /* renamed from: b */
    private final CipherSuite f6404b;

    /* renamed from: c */
    private final List<Certificate> f6405c;

    /* renamed from: d */
    private final List<Certificate> f6406d;

    private Handshake(TlsVersion axVar, CipherSuite lVar, List<Certificate> list, List<Certificate> list2) {
        this.f6403a = axVar;
        this.f6404b = lVar;
        this.f6405c = list;
        this.f6406d = list2;
    }

    /* renamed from: a */
    public static Handshake m24359a(SSLSession sSLSession) {
        Certificate[] certificateArr;
        List list;
        List list2;
        String cipherSuite = sSLSession.getCipherSuite();
        if (cipherSuite != null) {
            CipherSuite a = CipherSuite.m24400a(cipherSuite);
            String protocol = sSLSession.getProtocol();
            if (protocol != null) {
                TlsVersion a2 = TlsVersion.m24420a(protocol);
                try {
                    certificateArr = sSLSession.getPeerCertificates();
                } catch (SSLPeerUnverifiedException unused) {
                    certificateArr = null;
                }
                if (certificateArr != null) {
                    list = Util.m24752a(certificateArr);
                } else {
                    list = Collections.emptyList();
                }
                Certificate[] localCertificates = sSLSession.getLocalCertificates();
                if (localCertificates != null) {
                    list2 = Util.m24752a(localCertificates);
                } else {
                    list2 = Collections.emptyList();
                }
                return new Handshake(a2, a, list, list2);
            }
            throw new IllegalStateException("tlsVersion == null");
        }
        throw new IllegalStateException("cipherSuite == null");
    }

    /* renamed from: a */
    public final CipherSuite m24360a() {
        return this.f6404b;
    }

    /* renamed from: b */
    public final List<Certificate> m24358b() {
        return this.f6405c;
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof Handshake)) {
            return false;
        }
        Handshake zVar = (Handshake) obj;
        return Util.m24761a(this.f6404b, zVar.f6404b) && this.f6404b.equals(zVar.f6404b) && this.f6405c.equals(zVar.f6405c) && this.f6406d.equals(zVar.f6406d);
    }

    public final int hashCode() {
        TlsVersion axVar = this.f6403a;
        return (((((((axVar != null ? axVar.hashCode() : 0) + 527) * 31) + this.f6404b.hashCode()) * 31) + this.f6405c.hashCode()) * 31) + this.f6406d.hashCode();
    }
}
