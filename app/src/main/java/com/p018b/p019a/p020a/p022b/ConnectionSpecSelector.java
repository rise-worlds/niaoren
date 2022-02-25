package com.p018b.p019a.p020a.p022b;

import com.p018b.p019a.ConnectionSpec;
import com.p018b.p019a.p020a.Internal;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.ProtocolException;
import java.net.UnknownServiceException;
import java.security.cert.CertificateException;
import java.util.Arrays;
import java.util.List;
import javax.net.ssl.SSLHandshakeException;
import javax.net.ssl.SSLPeerUnverifiedException;
import javax.net.ssl.SSLProtocolException;
import javax.net.ssl.SSLSocket;

/* renamed from: com.b.a.a.b.b */
/* loaded from: classes.dex */
public final class ConnectionSpecSelector {

    /* renamed from: a */
    private final List<ConnectionSpec> f5735a;

    /* renamed from: b */
    private int f5736b = 0;

    /* renamed from: c */
    private boolean f5737c;

    /* renamed from: d */
    private boolean f5738d;

    public ConnectionSpecSelector(List<ConnectionSpec> list) {
        this.f5735a = list;
    }

    /* renamed from: a */
    public final ConnectionSpec m24806a(SSLSocket sSLSocket) {
        ConnectionSpec pVar;
        int i = this.f5736b;
        int size = this.f5735a.size();
        while (true) {
            if (i >= size) {
                pVar = null;
                break;
            }
            pVar = this.f5735a.get(i);
            if (pVar.m24392a(sSLSocket)) {
                this.f5736b = i + 1;
                break;
            }
            i++;
        }
        if (pVar != null) {
            this.f5737c = m24805b(sSLSocket);
            Internal.f5689a.mo24480a(pVar, sSLSocket, this.f5738d);
            return pVar;
        }
        throw new UnknownServiceException("Unable to find acceptable protocols. isFallback=" + this.f5738d + ", modes=" + this.f5735a + ", supported protocols=" + Arrays.toString(sSLSocket.getEnabledProtocols()));
    }

    /* renamed from: a */
    public final boolean m24807a(IOException iOException) {
        this.f5738d = true;
        if (!this.f5737c || (iOException instanceof ProtocolException) || (iOException instanceof InterruptedIOException)) {
            return false;
        }
        boolean z = iOException instanceof SSLHandshakeException;
        if ((!z || !(iOException.getCause() instanceof CertificateException)) && !(iOException instanceof SSLPeerUnverifiedException)) {
            return z || (iOException instanceof SSLProtocolException);
        }
        return false;
    }

    /* renamed from: b */
    private boolean m24805b(SSLSocket sSLSocket) {
        for (int i = this.f5736b; i < this.f5735a.size(); i++) {
            if (this.f5735a.get(i).m24392a(sSLSocket)) {
                return true;
            }
        }
        return false;
    }
}
