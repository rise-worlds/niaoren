package p110z1;

/* renamed from: z1.mx */
/* loaded from: classes3.dex */
final class BarcodeRow {

    /* renamed from: a */
    final byte[] f22524a;

    /* renamed from: b */
    private int f22525b = 0;

    /* JADX INFO: Access modifiers changed from: package-private */
    public BarcodeRow(int i) {
        this.f22524a = new byte[i];
    }

    /* renamed from: a */
    private void m1890a(int i, byte b) {
        this.f22524a[i] = b;
    }

    /* renamed from: a */
    private void m1889a(int i, boolean z) {
        this.f22524a[i] = z ? (byte) 1 : (byte) 0;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public final void m1888a(boolean z, int i) {
        for (int i2 = 0; i2 < i; i2++) {
            int i3 = this.f22525b;
            this.f22525b = i3 + 1;
            this.f22524a[i3] = z ? (byte) 1 : (byte) 0;
        }
    }

    /* renamed from: a */
    private byte[] m1891a(int i) {
        byte[] bArr = new byte[this.f22524a.length * i];
        for (int i2 = 0; i2 < bArr.length; i2++) {
            bArr[i2] = this.f22524a[i2 / i];
        }
        return bArr;
    }
}
