package com.p018b.p019a.p020a.p028h;

import java.lang.reflect.Method;
import java.security.cert.X509Certificate;
import javax.net.ssl.X509TrustManager;

/* renamed from: com.b.a.a.h.e */
/* loaded from: classes.dex */
public abstract class TrustRootIndex {
    /* renamed from: a */
    public abstract X509Certificate mo24560a(X509Certificate x509Certificate);

    /* renamed from: a */
    public static TrustRootIndex m24561a(X509TrustManager x509TrustManager) {
        try {
            Method declaredMethod = x509TrustManager.getClass().getDeclaredMethod("findTrustAnchorByIssuerAndSignature", X509Certificate.class);
            declaredMethod.setAccessible(true);
            return new C0894f(x509TrustManager, declaredMethod);
        } catch (NoSuchMethodException unused) {
            return new C0895g(x509TrustManager.getAcceptedIssuers());
        }
    }
}
