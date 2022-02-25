package com.tendcloud.tenddata;

import java.util.Arrays;

/* compiled from: td */
/* renamed from: com.tendcloud.tenddata.jo */
/* loaded from: classes2.dex */
final class C3012jo {

    /* renamed from: a */
    final int f14333a;

    /* renamed from: b */
    final byte[] f14334b;

    C3012jo(int i, byte[] bArr) {
        this.f14333a = i;
        this.f14334b = bArr;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public int m15245a() {
        return C3000je.m15292h(this.f14333a) + 0 + this.f14334b.length;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public void m15244a(C3000je jeVar) {
        jeVar.writeRawVarint32(this.f14333a);
        jeVar.writeRawBytes(this.f14334b);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof C3012jo)) {
            return false;
        }
        C3012jo joVar = (C3012jo) obj;
        return this.f14333a == joVar.f14333a && Arrays.equals(this.f14334b, joVar.f14334b);
    }

    public int hashCode() {
        return ((527 + this.f14333a) * 31) + Arrays.hashCode(this.f14334b);
    }
}
