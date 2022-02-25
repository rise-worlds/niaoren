package com.tendcloud.tenddata;

import java.io.IOException;

/* compiled from: td */
/* renamed from: com.tendcloud.tenddata.jm */
/* loaded from: classes2.dex */
public abstract class AbstractC3010jm {

    /* renamed from: b */
    protected volatile int f14330b = -1;

    /* renamed from: a */
    protected int mo15260a() {
        return 0;
    }

    /* renamed from: a */
    public abstract AbstractC3010jm m15259a(C2999jd jdVar);

    public void writeTo(C3000je jeVar) {
    }

    /* renamed from: d */
    public int m15253d() {
        if (this.f14330b < 0) {
            m15252e();
        }
        return this.f14330b;
    }

    /* renamed from: e */
    public int m15252e() {
        int a = mo15260a();
        this.f14330b = a;
        return a;
    }

    /* renamed from: a */
    public static final byte[] m15258a(AbstractC3010jm jmVar) {
        byte[] bArr = new byte[jmVar.m15252e()];
        m15256a(jmVar, bArr, 0, bArr.length);
        return bArr;
    }

    /* renamed from: a */
    public static final void m15256a(AbstractC3010jm jmVar, byte[] bArr, int i, int i2) {
        try {
            C3000je a = C3000je.m15324a(bArr, i, i2);
            jmVar.writeTo(a);
            a.m15323b();
        } catch (IOException e) {
            throw new RuntimeException("Serializing to a byte array threw an IOException (should never happen).", e);
        }
    }

    /* renamed from: a */
    public static final AbstractC3010jm m15257a(AbstractC3010jm jmVar, byte[] bArr) {
        return m15255b(jmVar, bArr, 0, bArr.length);
    }

    /* renamed from: b */
    public static final AbstractC3010jm m15255b(AbstractC3010jm jmVar, byte[] bArr, int i, int i2) {
        try {
            C2999jd a = C2999jd.m15373a(bArr, i, i2);
            jmVar.m15259a(a);
            a.checkLastTagWas(0);
            return jmVar;
        } catch (C3009jl e) {
            throw e;
        } catch (IOException unused) {
            throw new RuntimeException("Reading from a byte array threw an IOException (should never happen).");
        }
    }

    public String toString() {
        return C3011jn.m15251a(this);
    }

    /* renamed from: c */
    public AbstractC3010jm clone() {
        return (AbstractC3010jm) super.clone();
    }
}
