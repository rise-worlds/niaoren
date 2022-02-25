package com.p018b.p019a.p020a.p025e;

/* compiled from: Huffman.java */
/* renamed from: com.b.a.a.e.ah */
/* loaded from: classes.dex */
final class C0869ah {

    /* renamed from: a */
    final C0869ah[] f5878a;

    /* renamed from: b */
    final int f5879b;

    /* renamed from: c */
    final int f5880c;

    /* JADX INFO: Access modifiers changed from: package-private */
    public C0869ah() {
        this.f5878a = new C0869ah[256];
        this.f5879b = 0;
        this.f5880c = 0;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public C0869ah(int i, int i2) {
        this.f5878a = null;
        this.f5879b = i;
        int i3 = i2 & 7;
        this.f5880c = i3 == 0 ? 8 : i3;
    }
}
