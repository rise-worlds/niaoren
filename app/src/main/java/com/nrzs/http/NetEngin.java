package com.nrzs.http;

import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import java.util.concurrent.TimeUnit;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import okhttp3.OkHttpClient;
import p110z1.RxJava2CallAdapterFactory;
import retrofit2.Retrofit;

/* JADX INFO: Access modifiers changed from: package-private */
/* renamed from: com.nrzs.http.j */
/* loaded from: classes2.dex */
public final class NetEngin {

    /* renamed from: a */
    private Retrofit f11160a;

    /* renamed from: b */
    private volatile NetEngin f11161b;

    /* renamed from: a */
    public Retrofit m18554a() {
        return this.f11160a;
    }

    private NetEngin() {
        this.f11160a = new Retrofit.Builder().client(m18552c()).baseUrl("http://app.niaoren001.com/").addCallAdapterFactory(RxJava2CallAdapterFactory.m130a()).build();
    }

    /* renamed from: c */
    private OkHttpClient m18552c() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(60L, TimeUnit.SECONDS);
        builder.writeTimeout(60L, TimeUnit.SECONDS);
        builder.readTimeout(60L, TimeUnit.SECONDS);
        builder.sslSocketFactory(m18551d());
        builder.hostnameVerifier(new HostnameVerifier() { // from class: com.nrzs.http.j.1
            @Override // javax.net.ssl.HostnameVerifier
            public boolean verify(String str, SSLSession sSLSession) {
                return true;
            }
        });
        builder.retryOnConnectionFailure(true);
        return builder.build();
    }

    /* renamed from: d */
    private static SSLSocketFactory m18551d() {
        try {
            SSLContext instance = SSLContext.getInstance("TLS");
            instance.init(null, new TrustManager[]{new C2179b()}, new SecureRandom());
            return instance.getSocketFactory();
        } catch (Exception unused) {
            return null;
        }
    }

    /* compiled from: NetEngin.java */
    /* renamed from: com.nrzs.http.j$b */
    /* loaded from: classes2.dex */
    public static class C2179b implements X509TrustManager {
        @Override // javax.net.ssl.X509TrustManager
        public void checkClientTrusted(X509Certificate[] x509CertificateArr, String str) {
        }

        @Override // javax.net.ssl.X509TrustManager
        public void checkServerTrusted(X509Certificate[] x509CertificateArr, String str) {
        }

        @Override // javax.net.ssl.X509TrustManager
        public X509Certificate[] getAcceptedIssuers() {
            return new X509Certificate[0];
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: NetEngin.java */
    /* renamed from: com.nrzs.http.j$a */
    /* loaded from: classes2.dex */
    public static class C2178a {

        /* renamed from: a */
        private static final NetEngin f11163a = new NetEngin();

        private C2178a() {
        }
    }

    /* renamed from: b */
    public static NetEngin m18553b() {
        return C2178a.f11163a;
    }
}
