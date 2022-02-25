package com.p018b.p019a;

import com.p018b.p019a.p020a.Internal;
import com.p018b.p019a.p020a.Util;
import com.p018b.p019a.p020a.p021a.InternalCache;
import com.p018b.p019a.p020a.p027g.Platform;
import com.p018b.p019a.p020a.p028h.CertificateChainCleaner;
import java.net.Proxy;
import java.net.ProxySelector;
import java.security.GeneralSecurityException;
import java.security.KeyStore;
import java.util.Arrays;
import java.util.List;
import javax.net.SocketFactory;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;

/* renamed from: com.b.a.ai */
/* loaded from: classes.dex */
public class OkHttpClient implements Cloneable {

    /* renamed from: a */
    static final List<Protocol> f6089a = Util.m24752a(Protocol.HTTP_2, Protocol.HTTP_1_1);

    /* renamed from: b */
    static final List<ConnectionSpec> f6090b = Util.m24752a(ConnectionSpec.f6364a, ConnectionSpec.f6365b, ConnectionSpec.f6366c);

    /* renamed from: A */
    final int f6091A;

    /* renamed from: B */
    final int f6092B;

    /* renamed from: c */
    final Dispatcher f6093c;

    /* renamed from: d */
    final Proxy f6094d;

    /* renamed from: e */
    final List<Protocol> f6095e;

    /* renamed from: f */
    final List<ConnectionSpec> f6096f;

    /* renamed from: g */
    final List<Interceptor> f6097g;

    /* renamed from: h */
    final List<Interceptor> f6098h;

    /* renamed from: i */
    final ProxySelector f6099i;

    /* renamed from: j */
    final CookieJar f6100j;

    /* renamed from: k */
    final Cache f6101k;

    /* renamed from: l */
    final InternalCache f6102l;

    /* renamed from: m */
    final SocketFactory f6103m;

    /* renamed from: n */
    final SSLSocketFactory f6104n;

    /* renamed from: o */
    final CertificateChainCleaner f6105o;

    /* renamed from: p */
    final HostnameVerifier f6106p;

    /* renamed from: q */
    final CertificatePinner f6107q;

    /* renamed from: r */
    final Authenticator f6108r;

    /* renamed from: s */
    final Authenticator f6109s;

    /* renamed from: t */
    final ConnectionPool f6110t;

    /* renamed from: u */
    final Dns f6111u;

    /* renamed from: v */
    final boolean f6112v;

    /* renamed from: w */
    final boolean f6113w;

    /* renamed from: x */
    final boolean f6114x;

    /* renamed from: y */
    final int f6115y;

    /* renamed from: z */
    final int f6116z;

    static {
        Internal.f5689a = new C0900aj();
    }

    public OkHttpClient() {
        this(new C0901ak());
    }

    private OkHttpClient(C0901ak akVar) {
        this.f6093c = akVar.f6117a;
        this.f6094d = akVar.f6118b;
        this.f6095e = akVar.f6119c;
        this.f6096f = akVar.f6120d;
        this.f6097g = Util.m24753a(akVar.f6121e);
        this.f6098h = Util.m24753a(akVar.f6122f);
        this.f6099i = akVar.f6123g;
        this.f6100j = akVar.f6124h;
        this.f6101k = akVar.f6125i;
        this.f6102l = akVar.f6126j;
        this.f6103m = akVar.f6127k;
        boolean z = false;
        for (ConnectionSpec pVar : this.f6096f) {
            z = z || pVar.f6368d;
        }
        if (akVar.f6128l != null || !z) {
            this.f6104n = akVar.f6128l;
            this.f6105o = akVar.f6129m;
        } else {
            X509TrustManager t = m24487t();
            this.f6104n = m24506a(t);
            this.f6105o = Platform.m24576b().mo24577a(t);
        }
        this.f6106p = akVar.f6130n;
        this.f6107q = akVar.f6131o.m24405a(this.f6105o);
        this.f6108r = akVar.f6132p;
        this.f6109s = akVar.f6133q;
        this.f6110t = akVar.f6134r;
        this.f6111u = akVar.f6135s;
        this.f6112v = akVar.f6136t;
        this.f6113w = akVar.f6137u;
        this.f6114x = akVar.f6138v;
        this.f6115y = akVar.f6139w;
        this.f6116z = akVar.f6140x;
        this.f6091A = akVar.f6141y;
        this.f6092B = akVar.f6142z;
    }

    /* renamed from: t */
    private static X509TrustManager m24487t() {
        try {
            TrustManagerFactory instance = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
            instance.init((KeyStore) null);
            TrustManager[] trustManagers = instance.getTrustManagers();
            if (trustManagers.length == 1 && (trustManagers[0] instanceof X509TrustManager)) {
                return (X509TrustManager) trustManagers[0];
            }
            throw new IllegalStateException("Unexpected default trust managers:" + Arrays.toString(trustManagers));
        } catch (GeneralSecurityException unused) {
            throw new AssertionError();
        }
    }

    /* renamed from: a */
    private static SSLSocketFactory m24506a(X509TrustManager x509TrustManager) {
        try {
            SSLContext instance = SSLContext.getInstance("TLS");
            instance.init(null, new TrustManager[]{x509TrustManager}, null);
            return instance.getSocketFactory();
        } catch (GeneralSecurityException unused) {
            throw new AssertionError();
        }
    }

    /* renamed from: a */
    public final int m24508a() {
        return this.f6115y;
    }

    /* renamed from: b */
    public final int m24505b() {
        return this.f6116z;
    }

    /* renamed from: c */
    public final int m24504c() {
        return this.f6091A;
    }

    /* renamed from: d */
    public final Proxy m24503d() {
        return this.f6094d;
    }

    /* renamed from: e */
    public final ProxySelector m24502e() {
        return this.f6099i;
    }

    /* renamed from: f */
    public final CookieJar m24501f() {
        return this.f6100j;
    }

    /* renamed from: g */
    public final Dns m24500g() {
        return this.f6111u;
    }

    /* renamed from: h */
    public final SocketFactory m24499h() {
        return this.f6103m;
    }

    /* renamed from: i */
    public final SSLSocketFactory m24498i() {
        return this.f6104n;
    }

    /* renamed from: j */
    public final HostnameVerifier m24497j() {
        return this.f6106p;
    }

    /* renamed from: k */
    public final CertificatePinner m24496k() {
        return this.f6107q;
    }

    /* renamed from: l */
    public final Authenticator m24495l() {
        return this.f6109s;
    }

    /* renamed from: m */
    public final Authenticator m24494m() {
        return this.f6108r;
    }

    /* renamed from: n */
    public final ConnectionPool m24493n() {
        return this.f6110t;
    }

    /* renamed from: o */
    public final boolean m24492o() {
        return this.f6112v;
    }

    /* renamed from: p */
    public final boolean m24491p() {
        return this.f6113w;
    }

    /* renamed from: q */
    public final boolean m24490q() {
        return this.f6114x;
    }

    /* renamed from: r */
    public final List<Protocol> m24489r() {
        return this.f6095e;
    }

    /* renamed from: s */
    public final List<ConnectionSpec> m24488s() {
        return this.f6096f;
    }

    /* renamed from: a */
    public final Call m24507a(Request aoVar) {
        return new RealCall(this, aoVar, false);
    }
}
