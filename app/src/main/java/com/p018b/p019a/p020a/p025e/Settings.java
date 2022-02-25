package com.p018b.p019a.p020a.p025e;

/* renamed from: com.b.a.a.e.al */
/* loaded from: classes.dex */
public final class Settings {

    /* renamed from: a */
    private int f5885a;

    /* renamed from: b */
    private final int[] f5886b = new int[10];

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public final Settings m24671a(int i, int i2) {
        int[] iArr = this.f5886b;
        if (i >= iArr.length) {
            return this;
        }
        this.f5885a = (1 << i) | this.f5885a;
        iArr[i] = i2;
        return this;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public final boolean m24672a(int i) {
        return ((1 << i) & this.f5885a) != 0;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: b */
    public final int m24669b(int i) {
        return this.f5886b[i];
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public final int m24673a() {
        return Integer.bitCount(this.f5885a);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: b */
    public final int m24670b() {
        if ((this.f5885a & 2) != 0) {
            return this.f5886b[1];
        }
        return -1;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: c */
    public final int m24668c() {
        if ((this.f5885a & 16) != 0) {
            return this.f5886b[4];
        }
        return Integer.MAX_VALUE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: c */
    public final int m24667c(int i) {
        return (this.f5885a & 32) != 0 ? this.f5886b[5] : i;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: d */
    public final int m24666d() {
        if ((this.f5885a & 128) != 0) {
            return this.f5886b[7];
        }
        return 65535;
    }
}
