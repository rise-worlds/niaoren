package com.p018b.p019a.p020a.p027g;

import com.p018b.p019a.OkHttpClient;
import com.p018b.p019a.Protocol;
import com.p018b.p019a.p020a.p028h.BasicCertificateChainCleaner;
import com.p018b.p019a.p020a.p028h.CertificateChainCleaner;
import com.p018b.p019a.p020a.p028h.TrustRootIndex;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.X509TrustManager;

/* renamed from: com.b.a.a.g.h */
/* loaded from: classes.dex */
public class Platform {

    /* renamed from: a */
    private static final Platform f6043a;

    /* renamed from: b */
    private static final Logger f6044b;

    /* renamed from: a */
    public String mo24579a(SSLSocket sSLSocket) {
        return null;
    }

    /* renamed from: a */
    public void mo24578a(SSLSocket sSLSocket, String str, List<Protocol> list) {
    }

    /* renamed from: b */
    public void mo24574b(SSLSocket sSLSocket) {
    }

    /* renamed from: b */
    public boolean mo24575b(String str) {
        return true;
    }

    /* renamed from: b */
    public static Platform m24576b() {
        return f6043a;
    }

    /* renamed from: a */
    public void mo24581a(Socket socket, InetSocketAddress inetSocketAddress, int i) {
        socket.connect(inetSocketAddress, i);
    }

    /* renamed from: a */
    public void mo24584a(int i, String str, Throwable th) {
        f6044b.log(i == 5 ? Level.WARNING : Level.INFO, str, th);
    }

    /* renamed from: a */
    public Object mo24583a(String str) {
        if (f6044b.isLoggable(Level.FINE)) {
            return new Throwable(str);
        }
        return null;
    }

    /* renamed from: a */
    public void mo24582a(String str, Object obj) {
        if (obj == null) {
            str = str + " To see where this was allocated, set the OkHttpClient logger level to FINE: Logger.getLogger(OkHttpClient.class.getName()).setLevel(Level.FINE);";
        }
        mo24584a(5, str, (Throwable) obj);
    }

    /* renamed from: a */
    public static List<String> m24580a(List<Protocol> list) {
        ArrayList arrayList = new ArrayList(list.size());
        int size = list.size();
        for (int i = 0; i < size; i++) {
            Protocol alVar = list.get(i);
            if (alVar != Protocol.HTTP_1_0) {
                arrayList.add(alVar.toString());
            }
        }
        return arrayList;
    }

    /* renamed from: a */
    public CertificateChainCleaner mo24577a(X509TrustManager x509TrustManager) {
        return new BasicCertificateChainCleaner(TrustRootIndex.m24561a(x509TrustManager));
    }

    static {
        Platform a = AndroidPlatform.m24597a();
        if (a == null && (a = Jdk9Platform.m24593a()) == null && (a = JdkWithJettyBootPlatform.m24592a()) == null) {
            a = new Platform();
        }
        f6043a = a;
        f6044b = Logger.getLogger(OkHttpClient.class.getName());
    }
}
