package com.stripe.android;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.HashSet;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;

/* renamed from: com.stripe.android.v */
/* loaded from: classes2.dex */
class StripeSSLSocketFactory extends SSLSocketFactory {

    /* renamed from: d */
    private static final String f12358d = "TLSv1.1";

    /* renamed from: e */
    private static final String f12359e = "TLSv1.2";

    /* renamed from: a */
    private final SSLSocketFactory f12360a = HttpsURLConnection.getDefaultSSLSocketFactory();

    /* renamed from: b */
    private final boolean f12361b;

    /* renamed from: c */
    private final boolean f12362c;

    /* JADX INFO: Access modifiers changed from: package-private */
    public StripeSSLSocketFactory() {
        String[] strArr;
        try {
            strArr = SSLContext.getDefault().getSupportedSSLParameters().getProtocols();
        } catch (NoSuchAlgorithmException unused) {
            strArr = new String[0];
        }
        boolean z = false;
        boolean z2 = false;
        for (String str : strArr) {
            if (str.equals(f12358d)) {
                z = true;
            } else if (str.equals(f12359e)) {
                z2 = true;
            }
        }
        this.f12361b = z;
        this.f12362c = z2;
    }

    @Override // javax.net.ssl.SSLSocketFactory
    public String[] getDefaultCipherSuites() {
        return this.f12360a.getDefaultCipherSuites();
    }

    @Override // javax.net.ssl.SSLSocketFactory
    public String[] getSupportedCipherSuites() {
        return this.f12360a.getSupportedCipherSuites();
    }

    @Override // javax.net.ssl.SSLSocketFactory
    public Socket createSocket(Socket socket, String str, int i, boolean z) throws IOException {
        return m17464a(this.f12360a.createSocket(socket, str, i, z));
    }

    @Override // javax.net.SocketFactory
    public Socket createSocket(String str, int i) throws IOException {
        return m17464a(this.f12360a.createSocket(str, i));
    }

    @Override // javax.net.SocketFactory
    public Socket createSocket(String str, int i, InetAddress inetAddress, int i2) throws IOException {
        return m17464a(this.f12360a.createSocket(str, i, inetAddress, i2));
    }

    @Override // javax.net.SocketFactory
    public Socket createSocket(InetAddress inetAddress, int i) throws IOException {
        return m17464a(this.f12360a.createSocket(inetAddress, i));
    }

    @Override // javax.net.SocketFactory
    public Socket createSocket(InetAddress inetAddress, int i, InetAddress inetAddress2, int i2) throws IOException {
        return m17464a(this.f12360a.createSocket(inetAddress, i, inetAddress2, i2));
    }

    /* renamed from: a */
    private Socket m17464a(Socket socket) {
        if (!(socket instanceof SSLSocket)) {
            return socket;
        }
        SSLSocket sSLSocket = (SSLSocket) socket;
        HashSet hashSet = new HashSet(Arrays.asList(sSLSocket.getEnabledProtocols()));
        if (this.f12361b) {
            hashSet.add(f12358d);
        }
        if (this.f12362c) {
            hashSet.add(f12359e);
        }
        sSLSocket.setEnabledProtocols((String[]) hashSet.toArray(new String[0]));
        return sSLSocket;
    }
}
