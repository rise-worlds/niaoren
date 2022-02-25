package com.p018b.p029b;

/* renamed from: com.b.b.t */
/* loaded from: classes.dex */
final class Segment {

    /* renamed from: a */
    final byte[] f6451a;

    /* renamed from: b */
    int f6452b;

    /* renamed from: c */
    int f6453c;

    /* renamed from: d */
    boolean f6454d;

    /* renamed from: e */
    boolean f6455e;

    /* renamed from: f */
    Segment f6456f;

    /* renamed from: g */
    Segment f6457g;

    /* JADX INFO: Access modifiers changed from: package-private */
    public Segment() {
        this.f6451a = new byte[8192];
        this.f6455e = true;
        this.f6454d = false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Segment(Segment tVar) {
        this(tVar.f6451a, tVar.f6452b, tVar.f6453c);
        tVar.f6454d = true;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Segment(byte[] bArr, int i, int i2) {
        this.f6451a = bArr;
        this.f6452b = i;
        this.f6453c = i2;
        this.f6455e = false;
        this.f6454d = true;
    }

    /* renamed from: a */
    public final Segment m24272a() {
        Segment tVar = this.f6456f;
        if (tVar == this) {
            tVar = null;
        }
        Segment tVar2 = this.f6457g;
        tVar2.f6456f = this.f6456f;
        this.f6456f.f6457g = tVar2;
        this.f6456f = null;
        this.f6457g = null;
        return tVar;
    }

    /* renamed from: a */
    public final Segment m24271a(Segment tVar) {
        tVar.f6457g = this;
        tVar.f6456f = this.f6456f;
        this.f6456f.f6457g = tVar;
        this.f6456f = tVar;
        return tVar;
    }

    /* renamed from: a */
    public final void m24270a(Segment tVar, int i) {
        if (tVar.f6455e) {
            int i2 = tVar.f6453c;
            if (i2 + i > 8192) {
                if (!tVar.f6454d) {
                    int i3 = tVar.f6452b;
                    if ((i2 + i) - i3 <= 8192) {
                        byte[] bArr = tVar.f6451a;
                        System.arraycopy(bArr, i3, bArr, 0, i2 - i3);
                        tVar.f6453c -= tVar.f6452b;
                        tVar.f6452b = 0;
                    } else {
                        throw new IllegalArgumentException();
                    }
                } else {
                    throw new IllegalArgumentException();
                }
            }
            System.arraycopy(this.f6451a, this.f6452b, tVar.f6451a, tVar.f6453c, i);
            tVar.f6453c += i;
            this.f6452b += i;
            return;
        }
        throw new IllegalArgumentException();
    }
}
