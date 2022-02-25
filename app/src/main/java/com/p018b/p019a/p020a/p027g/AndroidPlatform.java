package com.p018b.p019a.p020a.p027g;

import android.util.Log;
import com.alipay.sdk.app.C0650b;
import com.p018b.p019a.Protocol;
import com.p018b.p019a.p020a.Util;
import com.p018b.p019a.p020a.p028h.CertificateChainCleaner;
import com.p018b.p029b.Buffer;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.security.cert.X509Certificate;
import java.util.List;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.X509TrustManager;

/* renamed from: com.b.a.a.g.a */
/* loaded from: classes.dex */
final class AndroidPlatform extends Platform {

    /* renamed from: a */
    private final Class<?> f6019a;

    /* renamed from: b */
    private final OptionalMethod<Socket> f6020b;

    /* renamed from: c */
    private final OptionalMethod<Socket> f6021c;

    /* renamed from: d */
    private final OptionalMethod<Socket> f6022d;

    /* renamed from: e */
    private final OptionalMethod<Socket> f6023e;

    /* renamed from: f */
    private final C0892c f6024f = C0892c.m24596a();

    private AndroidPlatform(Class<?> cls, OptionalMethod<Socket> gVar, OptionalMethod<Socket> gVar2, OptionalMethod<Socket> gVar3, OptionalMethod<Socket> gVar4) {
        this.f6019a = cls;
        this.f6020b = gVar;
        this.f6021c = gVar2;
        this.f6022d = gVar3;
        this.f6023e = gVar4;
    }

    @Override // com.p018b.p019a.p020a.p027g.Platform
    /* renamed from: a */
    public final void mo24581a(Socket socket, InetSocketAddress inetSocketAddress, int i) {
        try {
            socket.connect(inetSocketAddress, i);
        } catch (AssertionError e) {
            if (Util.m24763a(e)) {
                throw new IOException(e);
            }
            throw e;
        } catch (SecurityException e2) {
            IOException iOException = new IOException("Exception in connect");
            iOException.initCause(e2);
            throw iOException;
        }
    }

    @Override // com.p018b.p019a.p020a.p027g.Platform
    /* renamed from: a */
    public final void mo24578a(SSLSocket sSLSocket, String str, List<Protocol> list) {
        if (str != null) {
            this.f6020b.m24588a(sSLSocket, true);
            this.f6021c.m24588a(sSLSocket, str);
        }
        OptionalMethod<Socket> gVar = this.f6023e;
        if (gVar != null && gVar.m24589a((OptionalMethod<Socket>) sSLSocket)) {
            Object[] objArr = new Object[1];
            Buffer fVar = new Buffer();
            int size = list.size();
            for (int i = 0; i < size; i++) {
                Protocol alVar = list.get(i);
                if (alVar != Protocol.HTTP_1_0) {
                    fVar.mo24293h(alVar.toString().length());
                    fVar.mo24298b(alVar.toString());
                }
            }
            objArr[0] = fVar.m24318n();
            this.f6023e.m24587b(sSLSocket, objArr);
        }
    }

    @Override // com.p018b.p019a.p020a.p027g.Platform
    /* renamed from: a */
    public final String mo24579a(SSLSocket sSLSocket) {
        byte[] bArr;
        OptionalMethod<Socket> gVar = this.f6022d;
        if (gVar == null || !gVar.m24589a((OptionalMethod<Socket>) sSLSocket) || (bArr = (byte[]) this.f6022d.m24587b(sSLSocket, new Object[0])) == null) {
            return null;
        }
        return new String(bArr, Util.f5781e);
    }

    @Override // com.p018b.p019a.p020a.p027g.Platform
    /* renamed from: a */
    public final void mo24584a(int i, String str, Throwable th) {
        int min;
        int i2 = 5;
        if (i != 5) {
            i2 = 3;
        }
        if (th != null) {
            str = str + '\n' + Log.getStackTraceString(th);
        }
        int i3 = 0;
        int length = str.length();
        while (i3 < length) {
            int indexOf = str.indexOf(10, i3);
            if (indexOf == -1) {
                indexOf = length;
            }
            while (true) {
                min = Math.min(indexOf, i3 + C0650b.f301d);
                Log.println(i2, "OkHttp", str.substring(i3, min));
                if (min >= indexOf) {
                    break;
                }
                i3 = min;
            }
            i3 = min + 1;
        }
    }

    @Override // com.p018b.p019a.p020a.p027g.Platform
    /* renamed from: a */
    public final Object mo24583a(String str) {
        return this.f6024f.m24594a(str);
    }

    @Override // com.p018b.p019a.p020a.p027g.Platform
    /* renamed from: a */
    public final void mo24582a(String str, Object obj) {
        if (!this.f6024f.m24595a(obj)) {
            mo24584a(5, str, (Throwable) null);
        }
    }

    @Override // com.p018b.p019a.p020a.p027g.Platform
    /* renamed from: b */
    public final boolean mo24575b(String str) {
        try {
            Class<?> cls = Class.forName("android.security.NetworkSecurityPolicy");
            return ((Boolean) cls.getMethod("isCleartextTrafficPermitted", String.class).invoke(cls.getMethod("getInstance", new Class[0]).invoke(null, new Object[0]), str)).booleanValue();
        } catch (ClassNotFoundException | NoSuchMethodException unused) {
            return super.mo24575b(str);
        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException unused2) {
            throw new AssertionError();
        }
    }

    @Override // com.p018b.p019a.p020a.p027g.Platform
    /* renamed from: a */
    public final CertificateChainCleaner mo24577a(X509TrustManager x509TrustManager) {
        try {
            Class<?> cls = Class.forName("android.net.http.X509TrustManagerExtensions");
            return new C0891b(cls.getConstructor(X509TrustManager.class).newInstance(x509TrustManager), cls.getMethod("checkServerTrusted", X509Certificate[].class, String.class, String.class));
        } catch (Exception unused) {
            return super.mo24577a(x509TrustManager);
        }
    }

    /* renamed from: a */
    public static Platform m24597a() {
        Class<?> cls;
        OptionalMethod gVar;
        OptionalMethod gVar2;
        try {
            try {
                cls = Class.forName("com.android.org.conscrypt.SSLParametersImpl");
            } catch (ClassNotFoundException unused) {
                cls = Class.forName("org.apache.harmony.xnet.provider.jsse.SSLParametersImpl");
            }
            OptionalMethod gVar3 = new OptionalMethod(null, "setUseSessionTickets", Boolean.TYPE);
            OptionalMethod gVar4 = new OptionalMethod(null, "setHostname", String.class);
            try {
                Class.forName("android.net.Network");
                gVar2 = new OptionalMethod(byte[].class, "getAlpnSelectedProtocol", new Class[0]);
            } catch (ClassNotFoundException unused2) {
                gVar2 = null;
            }
            try {
                gVar = new OptionalMethod(null, "setAlpnProtocols", byte[].class);
                gVar2 = gVar2;
            } catch (ClassNotFoundException unused3) {
                gVar = null;
                return new AndroidPlatform(cls, gVar3, gVar4, gVar2, gVar);
            }
            return new AndroidPlatform(cls, gVar3, gVar4, gVar2, gVar);
        } catch (ClassNotFoundException unused4) {
            return null;
        }
    }
}
