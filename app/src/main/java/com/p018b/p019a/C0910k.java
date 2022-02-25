package com.p018b.p019a;

import com.p018b.p029b.ByteString;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: CertificatePinner.java */
/* renamed from: com.b.a.k */
/* loaded from: classes.dex */
public final class C0910k {

    /* renamed from: a */
    final String f6237a;

    /* renamed from: b */
    final String f6238b;

    /* renamed from: c */
    final String f6239c;

    /* renamed from: d */
    final ByteString f6240d;

    public final boolean equals(Object obj) {
        if (!(obj instanceof C0910k)) {
            return false;
        }
        C0910k kVar = (C0910k) obj;
        return this.f6237a.equals(kVar.f6237a) && this.f6239c.equals(kVar.f6239c) && this.f6240d.equals(kVar.f6240d);
    }

    public final int hashCode() {
        return ((((this.f6237a.hashCode() + 527) * 31) + this.f6239c.hashCode()) * 31) + this.f6240d.hashCode();
    }

    public final String toString() {
        return this.f6239c + this.f6240d.mo24261b();
    }
}
