package com.p018b.p019a.p020a.p023c;

import com.p018b.p019a.Address;
import com.p018b.p019a.CertificatePinner;
import com.p018b.p019a.HttpUrl;
import com.p018b.p019a.Interceptor;
import com.p018b.p019a.OkHttpClient;
import com.p018b.p019a.Request;
import com.p018b.p019a.Response;
import com.p018b.p019a.p020a.p022b.StreamAllocation;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.ProtocolException;
import java.net.SocketTimeoutException;
import java.security.cert.CertificateException;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLHandshakeException;
import javax.net.ssl.SSLPeerUnverifiedException;
import javax.net.ssl.SSLSocketFactory;

/* renamed from: com.b.a.a.c.k */
/* loaded from: classes.dex */
public final class RetryAndFollowUpInterceptor implements Interceptor {

    /* renamed from: a */
    private final OkHttpClient f5808a;

    /* renamed from: b */
    private final boolean f5809b;

    /* renamed from: c */
    private StreamAllocation f5810c;

    /* renamed from: d */
    private Object f5811d;

    /* renamed from: e */
    private volatile boolean f5812e;

    public RetryAndFollowUpInterceptor(OkHttpClient aiVar, boolean z) {
        this.f5808a = aiVar;
        this.f5809b = z;
    }

    /* renamed from: a */
    public final boolean m24729a() {
        return this.f5812e;
    }

    /* renamed from: a */
    public final void m24725a(Object obj) {
        this.f5811d = obj;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code restructure failed: missing block: B:32:0x00c0, code lost:
        if (r7.equals("HEAD") == false) goto L_0x0155;
     */
    /* JADX WARN: Removed duplicated region for block: B:45:0x010c  */
    /* JADX WARN: Removed duplicated region for block: B:56:0x0147  */
    @Override // com.p018b.p019a.Interceptor
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final com.p018b.p019a.Response mo24513a(com.p018b.p019a.AbstractC0899ag r11) {
        /*
            Method dump skipped, instructions count: 596
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.p018b.p019a.p020a.p023c.RetryAndFollowUpInterceptor.mo24513a(com.b.a.ag):com.b.a.as");
    }

    /* renamed from: a */
    private Address m24728a(HttpUrl acVar) {
        CertificatePinner iVar;
        HostnameVerifier hostnameVerifier;
        SSLSocketFactory sSLSocketFactory = null;
        if (acVar.m24535c()) {
            sSLSocketFactory = this.f5808a.m24498i();
            hostnameVerifier = this.f5808a.m24497j();
            iVar = this.f5808a.m24496k();
        } else {
            hostnameVerifier = null;
            iVar = null;
        }
        return new Address(acVar.m24529f(), acVar.m24528g(), this.f5808a.m24500g(), this.f5808a.m24499h(), sSLSocketFactory, hostnameVerifier, iVar, this.f5808a.m24494m(), this.f5808a.m24503d(), this.f5808a.m24489r(), this.f5808a.m24488s(), this.f5808a.m24502e());
    }

    /* renamed from: a */
    private boolean m24726a(IOException iOException, boolean z, Request aoVar) {
        boolean z2;
        this.f5810c.m24779a(iOException);
        if (!this.f5808a.m24490q()) {
            return false;
        }
        if (z && (aoVar.m24467d() instanceof UnrepeatableRequestBody)) {
            return false;
        }
        if (iOException instanceof ProtocolException) {
            z2 = false;
        } else if (iOException instanceof InterruptedIOException) {
            if (!(iOException instanceof SocketTimeoutException) || z) {
                z2 = false;
            }
            z2 = true;
        } else if (!(iOException instanceof SSLHandshakeException) || !(iOException.getCause() instanceof CertificateException)) {
            if (iOException instanceof SSLPeerUnverifiedException) {
                z2 = false;
            }
            z2 = true;
        } else {
            z2 = false;
        }
        return z2 && this.f5810c.m24771e();
    }

    /* renamed from: a */
    private static boolean m24727a(Response asVar, HttpUrl acVar) {
        HttpUrl a = asVar.m24454a().m24471a();
        return a.m24529f().equals(acVar.m24529f()) && a.m24528g() == acVar.m24528g() && a.m24538b().equals(acVar.m24538b());
    }
}
