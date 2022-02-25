package com.p018b.p019a;

import com.p018b.p019a.p020a.Util;
import java.net.Proxy;
import java.net.ProxySelector;
import java.util.List;
import javax.net.SocketFactory;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSocketFactory;
import org.apache.http.HttpHost;
import p110z1.C4963cj;

/* renamed from: com.b.a.a */
/* loaded from: classes.dex */
public final class Address {

    /* renamed from: a */
    final HttpUrl f5678a;

    /* renamed from: b */
    final Dns f5679b;

    /* renamed from: c */
    final SocketFactory f5680c;

    /* renamed from: d */
    final Authenticator f5681d;

    /* renamed from: e */
    final List<Protocol> f5682e;

    /* renamed from: f */
    final List<ConnectionSpec> f5683f;

    /* renamed from: g */
    final ProxySelector f5684g;

    /* renamed from: h */
    final Proxy f5685h;

    /* renamed from: i */
    final SSLSocketFactory f5686i;

    /* renamed from: j */
    final HostnameVerifier f5687j;

    /* renamed from: k */
    final CertificatePinner f5688k;

    public Address(String str, int i, Dns vVar, SocketFactory socketFactory, SSLSocketFactory sSLSocketFactory, HostnameVerifier hostnameVerifier, CertificatePinner iVar, Authenticator bVar, Proxy proxy, List<Protocol> list, List<ConnectionSpec> list2, ProxySelector proxySelector) {
        C0897ad adVar = new C0897ad();
        String str2 = sSLSocketFactory != null ? "https" : HttpHost.DEFAULT_SCHEME_NAME;
        if (str2.equalsIgnoreCase(HttpHost.DEFAULT_SCHEME_NAME)) {
            adVar.f6069a = HttpHost.DEFAULT_SCHEME_NAME;
        } else if (str2.equalsIgnoreCase("https")) {
            adVar.f6069a = "https";
        } else {
            throw new IllegalArgumentException("unexpected scheme: " + str2);
        }
        C0897ad a = adVar.m24521a(str);
        if (i <= 0 || i > 65535) {
            throw new IllegalArgumentException("unexpected port: " + i);
        }
        a.f6073e = i;
        this.f5678a = a.m24519b();
        if (vVar != null) {
            this.f5679b = vVar;
            if (socketFactory != null) {
                this.f5680c = socketFactory;
                if (bVar != null) {
                    this.f5681d = bVar;
                    if (list != null) {
                        this.f5682e = Util.m24753a(list);
                        if (list2 != null) {
                            this.f5683f = Util.m24753a(list2);
                            if (proxySelector != null) {
                                this.f5684g = proxySelector;
                                this.f5685h = proxy;
                                this.f5686i = sSLSocketFactory;
                                this.f5687j = hostnameVerifier;
                                this.f5688k = iVar;
                                return;
                            }
                            throw new NullPointerException("proxySelector == null");
                        }
                        throw new NullPointerException("connectionSpecs == null");
                    }
                    throw new NullPointerException("protocols == null");
                }
                throw new NullPointerException("proxyAuthenticator == null");
            }
            throw new NullPointerException("socketFactory == null");
        }
        throw new NullPointerException("dns == null");
    }

    /* renamed from: a */
    public final HttpUrl m24833a() {
        return this.f5678a;
    }

    /* renamed from: b */
    public final Dns m24832b() {
        return this.f5679b;
    }

    /* renamed from: c */
    public final SocketFactory m24831c() {
        return this.f5680c;
    }

    /* renamed from: d */
    public final Authenticator m24830d() {
        return this.f5681d;
    }

    /* renamed from: e */
    public final List<Protocol> m24829e() {
        return this.f5682e;
    }

    /* renamed from: f */
    public final List<ConnectionSpec> m24828f() {
        return this.f5683f;
    }

    /* renamed from: g */
    public final ProxySelector m24827g() {
        return this.f5684g;
    }

    /* renamed from: h */
    public final Proxy m24826h() {
        return this.f5685h;
    }

    /* renamed from: i */
    public final SSLSocketFactory m24825i() {
        return this.f5686i;
    }

    /* renamed from: j */
    public final HostnameVerifier m24824j() {
        return this.f5687j;
    }

    /* renamed from: k */
    public final CertificatePinner m24823k() {
        return this.f5688k;
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof Address)) {
            return false;
        }
        Address aVar = (Address) obj;
        return this.f5678a.equals(aVar.f5678a) && this.f5679b.equals(aVar.f5679b) && this.f5681d.equals(aVar.f5681d) && this.f5682e.equals(aVar.f5682e) && this.f5683f.equals(aVar.f5683f) && this.f5684g.equals(aVar.f5684g) && Util.m24761a(this.f5685h, aVar.f5685h) && Util.m24761a(this.f5686i, aVar.f5686i) && Util.m24761a(this.f5687j, aVar.f5687j) && Util.m24761a(this.f5688k, aVar.f5688k);
    }

    public final int hashCode() {
        int hashCode = (((((((((((this.f5678a.hashCode() + 527) * 31) + this.f5679b.hashCode()) * 31) + this.f5681d.hashCode()) * 31) + this.f5682e.hashCode()) * 31) + this.f5683f.hashCode()) * 31) + this.f5684g.hashCode()) * 31;
        Proxy proxy = this.f5685h;
        int i = 0;
        int hashCode2 = (hashCode + (proxy != null ? proxy.hashCode() : 0)) * 31;
        SSLSocketFactory sSLSocketFactory = this.f5686i;
        int hashCode3 = (hashCode2 + (sSLSocketFactory != null ? sSLSocketFactory.hashCode() : 0)) * 31;
        HostnameVerifier hostnameVerifier = this.f5687j;
        int hashCode4 = (hashCode3 + (hostnameVerifier != null ? hostnameVerifier.hashCode() : 0)) * 31;
        CertificatePinner iVar = this.f5688k;
        if (iVar != null) {
            i = iVar.hashCode();
        }
        return hashCode4 + i;
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("Address{");
        sb.append(this.f5678a.f6061b);
        sb.append(":");
        sb.append(this.f5678a.f6062c);
        if (this.f5685h != null) {
            sb.append(", proxy=");
            sb.append(this.f5685h);
        } else {
            sb.append(", proxySelector=");
            sb.append(this.f5684g);
        }
        sb.append(C4963cj.f20747d);
        return sb.toString();
    }
}
