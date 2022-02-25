package com.nrzs.http;

import android.annotation.SuppressLint;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

/* renamed from: com.nrzs.http.l */
/* loaded from: classes2.dex */
public class RxUtils {

    /* compiled from: RxUtils.java */
    /* renamed from: com.nrzs.http.l$a */
    /* loaded from: classes2.dex */
    public static class C2180a implements HostnameVerifier {
        @Override // javax.net.ssl.HostnameVerifier
        @SuppressLint({"BadHostnameVerifier"})
        public boolean verify(String str, SSLSession sSLSession) {
            return true;
        }
    }

    @SuppressLint({"TrulyRandom"})
    /* renamed from: a */
    public static SSLSocketFactory m18548a() {
        try {
            SSLContext instance = SSLContext.getInstance("TLS");
            instance.init(null, new TrustManager[]{new C2181b()}, new SecureRandom());
            return instance.getSocketFactory();
        } catch (Exception unused) {
            return null;
        }
    }

    /* compiled from: RxUtils.java */
    /* renamed from: com.nrzs.http.l$b */
    /* loaded from: classes2.dex */
    public static class C2181b implements X509TrustManager {
        @Override // javax.net.ssl.X509TrustManager
        @SuppressLint({"TrustAllX509TrustManager"})
        public void checkClientTrusted(X509Certificate[] x509CertificateArr, String str) throws CertificateException {
        }

        @Override // javax.net.ssl.X509TrustManager
        @SuppressLint({"TrustAllX509TrustManager"})
        public void checkServerTrusted(X509Certificate[] x509CertificateArr, String str) throws CertificateException {
        }

        @Override // javax.net.ssl.X509TrustManager
        public X509Certificate[] getAcceptedIssuers() {
            return new X509Certificate[0];
        }
    }
}
