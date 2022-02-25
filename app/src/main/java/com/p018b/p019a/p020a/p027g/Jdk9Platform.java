package com.p018b.p019a.p020a.p027g;

import com.p018b.p019a.Protocol;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import javax.net.ssl.SSLParameters;
import javax.net.ssl.SSLSocket;

/* renamed from: com.b.a.a.g.d */
/* loaded from: classes.dex */
final class Jdk9Platform extends Platform {

    /* renamed from: a */
    final Method f6030a;

    /* renamed from: b */
    final Method f6031b;

    private Jdk9Platform(Method method, Method method2) {
        this.f6030a = method;
        this.f6031b = method2;
    }

    @Override // com.p018b.p019a.p020a.p027g.Platform
    /* renamed from: a */
    public final void mo24578a(SSLSocket sSLSocket, String str, List<Protocol> list) {
        try {
            SSLParameters sSLParameters = sSLSocket.getSSLParameters();
            List<String> a = m24580a(list);
            this.f6030a.invoke(sSLParameters, a.toArray(new String[a.size()]));
            sSLSocket.setSSLParameters(sSLParameters);
        } catch (IllegalAccessException | InvocationTargetException unused) {
            throw new AssertionError();
        }
    }

    @Override // com.p018b.p019a.p020a.p027g.Platform
    /* renamed from: a */
    public final String mo24579a(SSLSocket sSLSocket) {
        try {
            String str = (String) this.f6031b.invoke(sSLSocket, new Object[0]);
            if (str == null) {
                return null;
            }
            if (str.equals("")) {
                return null;
            }
            return str;
        } catch (IllegalAccessException | InvocationTargetException unused) {
            throw new AssertionError();
        }
    }

    /* renamed from: a */
    public static Jdk9Platform m24593a() {
        try {
            return new Jdk9Platform(SSLParameters.class.getMethod("setApplicationProtocols", String[].class), SSLSocket.class.getMethod("getApplicationProtocol", new Class[0]));
        } catch (NoSuchMethodException unused) {
            return null;
        }
    }
}
