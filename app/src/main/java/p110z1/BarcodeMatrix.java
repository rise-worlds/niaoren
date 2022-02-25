package p110z1;

import java.lang.reflect.Array;

/* renamed from: z1.mw */
/* loaded from: classes3.dex */
public final class BarcodeMatrix {

    /* renamed from: a */
    int f22520a;

    /* renamed from: b */
    private final BarcodeRow[] f22521b;

    /* renamed from: c */
    private final int f22522c;

    /* renamed from: d */
    private final int f22523d;

    public BarcodeMatrix(int i, int i2) {
        this.f22521b = new BarcodeRow[i];
        int length = this.f22521b.length;
        for (int i3 = 0; i3 < length; i3++) {
            this.f22521b[i3] = new BarcodeRow(((i2 + 4) * 17) + 1);
        }
        this.f22523d = i2 * 17;
        this.f22522c = i;
        this.f22520a = -1;
    }

    /* renamed from: a */
    private void m1894a(int i, int i2, byte b) {
        this.f22521b[i2].f22524a[i] = b;
    }

    /* renamed from: b */
    private void m1893b() {
        this.f22520a++;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public final BarcodeRow m1896a() {
        return this.f22521b[this.f22520a];
    }

    /* renamed from: c */
    private byte[][] m1892c() {
        return m1895a(1, 1);
    }

    /* renamed from: a */
    public final byte[][] m1895a(int i, int i2) {
        byte[][] bArr = (byte[][]) Array.newInstance(byte.class, this.f22522c * i2, this.f22523d * i);
        int i3 = this.f22522c * i2;
        for (int i4 = 0; i4 < i3; i4++) {
            int i5 = (i3 - i4) - 1;
            BarcodeRow mxVar = this.f22521b[i4 / i2];
            byte[] bArr2 = new byte[mxVar.f22524a.length * i];
            for (int i6 = 0; i6 < bArr2.length; i6++) {
                bArr2[i6] = mxVar.f22524a[i6 / i];
            }
            bArr[i5] = bArr2;
        }
        return bArr;
    }
}
