package com.cyjh.ddy.net.utils;

import com.blankj.utilcode.util.Utils;
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

/* loaded from: classes.dex */
public class OkHttpUtils {

    /* renamed from: a */
    private static final int f7535a = 30;

    /* renamed from: b */
    private OkHttpClient f7536b;

    private OkHttpUtils() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(30L, TimeUnit.SECONDS);
        builder.sslSocketFactory(m21403c());
        builder.hostnameVerifier(new HostnameVerifier() { // from class: com.cyjh.ddy.net.utils.OkHttpUtils.1
            @Override // javax.net.ssl.HostnameVerifier
            public boolean verify(String str, SSLSession sSLSession) {
                return true;
            }
        });
        builder.dns(OkHttpDns.getInstance(Utils.m24103a()));
        this.f7536b = builder.build();
    }

    /* renamed from: a */
    public OkHttpClient m21405a() {
        return this.f7536b;
    }

    /* loaded from: classes.dex */
    private static class SingletonHolder {

        /* renamed from: a */
        private static final OkHttpUtils f7538a = new OkHttpUtils();

        private SingletonHolder() {
        }
    }

    /* renamed from: b */
    public static OkHttpUtils m21404b() {
        return SingletonHolder.f7538a;
    }

    /* renamed from: c */
    private static SSLSocketFactory m21403c() {
        try {
            SSLContext instance = SSLContext.getInstance("TLS");
            instance.init(null, new TrustManager[]{new TrustAllCerts()}, new SecureRandom());
            return instance.getSocketFactory();
        } catch (Exception unused) {
            return null;
        }
    }

    /* loaded from: classes.dex */
    public static class TrustAllCerts implements X509TrustManager {
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
}
