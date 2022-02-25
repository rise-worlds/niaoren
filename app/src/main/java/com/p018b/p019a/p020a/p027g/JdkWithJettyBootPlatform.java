package com.p018b.p019a.p020a.p027g;

import com.p018b.p019a.Protocol;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.List;
import javax.net.ssl.SSLSocket;

/* renamed from: com.b.a.a.g.e */
/* loaded from: classes.dex */
final class JdkWithJettyBootPlatform extends Platform {

    /* renamed from: a */
    private final Method f6032a;

    /* renamed from: b */
    private final Method f6033b;

    /* renamed from: c */
    private final Method f6034c;

    /* renamed from: d */
    private final Class<?> f6035d;

    /* renamed from: e */
    private final Class<?> f6036e;

    private JdkWithJettyBootPlatform(Method method, Method method2, Method method3, Class<?> cls, Class<?> cls2) {
        this.f6032a = method;
        this.f6033b = method2;
        this.f6034c = method3;
        this.f6035d = cls;
        this.f6036e = cls2;
    }

    @Override // com.p018b.p019a.p020a.p027g.Platform
    /* renamed from: a */
    public final void mo24578a(SSLSocket sSLSocket, String str, List<Protocol> list) {
        try {
            this.f6032a.invoke(null, sSLSocket, Proxy.newProxyInstance(Platform.class.getClassLoader(), new Class[]{this.f6035d, this.f6036e}, new C0893f(m24580a(list))));
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new AssertionError(e);
        }
    }

    @Override // com.p018b.p019a.p020a.p027g.Platform
    /* renamed from: b */
    public final void mo24574b(SSLSocket sSLSocket) {
        try {
            this.f6034c.invoke(null, sSLSocket);
        } catch (IllegalAccessException | InvocationTargetException unused) {
            throw new AssertionError();
        }
    }

    @Override // com.p018b.p019a.p020a.p027g.Platform
    /* renamed from: a */
    public final String mo24579a(SSLSocket sSLSocket) {
        try {
            C0893f fVar = (C0893f) Proxy.getInvocationHandler(this.f6033b.invoke(null, sSLSocket));
            if (!fVar.f6037a && fVar.f6038b == null) {
                Platform.m24576b().mo24584a(4, "ALPN callback dropped: HTTP/2 is disabled. Is alpn-boot on the boot class path?", (Throwable) null);
                return null;
            } else if (fVar.f6037a) {
                return null;
            } else {
                return fVar.f6038b;
            }
        } catch (IllegalAccessException | InvocationTargetException unused) {
            throw new AssertionError();
        }
    }

    /* renamed from: a */
    public static Platform m24592a() {
        try {
            Class<?> cls = Class.forName("org.eclipse.jetty.alpn.ALPN");
            Class<?> cls2 = Class.forName("org.eclipse.jetty.alpn.ALPN$Provider");
            Class<?> cls3 = Class.forName("org.eclipse.jetty.alpn.ALPN$ClientProvider");
            return new JdkWithJettyBootPlatform(cls.getMethod("put", SSLSocket.class, cls2), cls.getMethod("get", SSLSocket.class), cls.getMethod("remove", SSLSocket.class), cls3, Class.forName("org.eclipse.jetty.alpn.ALPN$ServerProvider"));
        } catch (ClassNotFoundException | NoSuchMethodException unused) {
            return null;
        }
    }
}
