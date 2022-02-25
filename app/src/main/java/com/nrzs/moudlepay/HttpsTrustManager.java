package com.nrzs.moudlepay;

import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

/* renamed from: com.nrzs.moudlepay.c */
/* loaded from: classes2.dex */
public class HttpsTrustManager implements X509TrustManager {

    /* renamed from: a */
    private static TrustManager[] f11239a;

    /* renamed from: b */
    private static final X509Certificate[] f11240b = new X509Certificate[0];

    /* renamed from: a */
    public boolean m18497a(X509Certificate[] x509CertificateArr) {
        return true;
    }

    /* renamed from: b */
    public boolean m18496b(X509Certificate[] x509CertificateArr) {
        return true;
    }

    @Override // javax.net.ssl.X509TrustManager
    public void checkClientTrusted(X509Certificate[] x509CertificateArr, String str) throws CertificateException {
    }

    @Override // javax.net.ssl.X509TrustManager
    public void checkServerTrusted(X509Certificate[] x509CertificateArr, String str) throws CertificateException {
    }

    @Override // javax.net.ssl.X509TrustManager
    public X509Certificate[] getAcceptedIssuers() {
        return f11240b;
    }

    /* renamed from: a */
    public static void m18498a() {
        SSLContext sSLContext;
        NoSuchAlgorithmException e;
        KeyManagementException e2;
        HttpsURLConnection.setDefaultHostnameVerifier(new HostnameVerifier() { // from class: com.nrzs.moudlepay.c.1
            @Override // javax.net.ssl.HostnameVerifier
            public boolean verify(String str, SSLSession sSLSession) {
                return true;
            }
        });
        if (f11239a == null) {
            f11239a = new TrustManager[]{new HttpsTrustManager()};
        }
        try {
            sSLContext = SSLContext.getInstance("TLS");
        } catch (KeyManagementException e3) {
            e2 = e3;
            sSLContext = null;
        } catch (NoSuchAlgorithmException e4) {
            e = e4;
            sSLContext = null;
        }
        try {
            sSLContext.init(null, f11239a, new SecureRandom());
        } catch (KeyManagementException e5) {
            e2 = e5;
            e2.printStackTrace();
            HttpsURLConnection.setDefaultSSLSocketFactory(sSLContext.getSocketFactory());
        } catch (NoSuchAlgorithmException e6) {
            e = e6;
            e.printStackTrace();
            HttpsURLConnection.setDefaultSSLSocketFactory(sSLContext.getSocketFactory());
        }
        HttpsURLConnection.setDefaultSSLSocketFactory(sSLContext.getSocketFactory());
    }
}
