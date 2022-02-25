package com.p018b.p019a;

import com.p018b.p019a.p020a.p021a.InternalCache;
import com.p018b.p019a.p020a.p028h.CertificateChainCleaner;
import com.p018b.p019a.p020a.p028h.OkHostnameVerifier;
import java.net.Proxy;
import java.net.ProxySelector;
import java.util.ArrayList;
import java.util.List;
import javax.net.SocketFactory;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSocketFactory;

/* compiled from: OkHttpClient.java */
/* renamed from: com.b.a.ak */
/* loaded from: classes.dex */
public final class C0901ak {

    /* renamed from: b */
    Proxy f6118b;

    /* renamed from: i */
    Cache f6125i;

    /* renamed from: j */
    InternalCache f6126j;

    /* renamed from: l */
    SSLSocketFactory f6128l;

    /* renamed from: m */
    CertificateChainCleaner f6129m;

    /* renamed from: e */
    final List<Interceptor> f6121e = new ArrayList();

    /* renamed from: f */
    final List<Interceptor> f6122f = new ArrayList();

    /* renamed from: a */
    Dispatcher f6117a = new Dispatcher();

    /* renamed from: c */
    List<Protocol> f6119c = OkHttpClient.f6089a;

    /* renamed from: d */
    List<ConnectionSpec> f6120d = OkHttpClient.f6090b;

    /* renamed from: g */
    ProxySelector f6123g = ProxySelector.getDefault();

    /* renamed from: h */
    CookieJar f6124h = CookieJar.f6389a;

    /* renamed from: k */
    SocketFactory f6127k = SocketFactory.getDefault();

    /* renamed from: n */
    HostnameVerifier f6130n = OkHostnameVerifier.f6053a;

    /* renamed from: o */
    CertificatePinner f6131o = CertificatePinner.f6233a;

    /* renamed from: p */
    Authenticator f6132p = Authenticator.f6209a;

    /* renamed from: q */
    Authenticator f6133q = Authenticator.f6209a;

    /* renamed from: r */
    ConnectionPool f6134r = new ConnectionPool();

    /* renamed from: s */
    Dns f6135s = Dns.f6397a;

    /* renamed from: t */
    boolean f6136t = true;

    /* renamed from: u */
    boolean f6137u = true;

    /* renamed from: v */
    boolean f6138v = true;

    /* renamed from: w */
    int f6139w = 10000;

    /* renamed from: x */
    int f6140x = 10000;

    /* renamed from: y */
    int f6141y = 10000;

    /* renamed from: z */
    int f6142z = 0;
}
