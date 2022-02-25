package p110z1;

/* renamed from: z1.oh */
/* loaded from: classes3.dex */
public final class PlanarYUVLuminanceSource extends LuminanceSource {

    /* renamed from: c */
    private static final int f22694c = 2;

    /* renamed from: d */
    private final byte[] f22695d;

    /* renamed from: e */
    private final int f22696e;

    /* renamed from: f */
    private final int f22697f;

    /* renamed from: g */
    private final int f22698g;

    /* renamed from: h */
    private final int f22699h;

    @Override // p110z1.LuminanceSource
    /* renamed from: b */
    public final boolean mo1639b() {
        return true;
    }

    private PlanarYUVLuminanceSource(byte[] bArr, int i, int i2, int i3, int i4, int i5, int i6) {
        super(i5, i6);
        if (i5 + i3 > i || i6 + i4 > i2) {
            throw new IllegalArgumentException("Crop rectangle does not fit within image data.");
        }
        this.f22695d = bArr;
        this.f22696e = i;
        this.f22697f = i2;
        this.f22698g = i3;
        this.f22699h = i4;
    }

    @Override // p110z1.LuminanceSource
    /* renamed from: a */
    public final LuminanceSource mo1641a(int i, int i2, int i3, int i4) {
        return new PlanarYUVLuminanceSource(this.f22695d, this.f22696e, this.f22697f, this.f22698g + i, this.f22699h + i2, i3, i4);
    }

    /* renamed from: a */
    private void m1646a(int i, int i2) {
        byte[] bArr = this.f22695d;
        int i3 = (this.f22699h * this.f22696e) + this.f22698g;
        int i4 = 0;
        while (i4 < i2) {
            int i5 = (i / 2) + i3;
            int i6 = (i3 + i) - 1;
            int i7 = i3;
            while (i7 < i5) {
                byte b = bArr[i7];
                bArr[i7] = bArr[i6];
                bArr[i6] = b;
                i7++;
                i6--;
            }
            i4++;
            i3 += this.f22696e;
        }
    }

    @Override // p110z1.LuminanceSource
    /* renamed from: a */
    public final byte[] mo1640a(int i, byte[] bArr) {
        if (i < 0 || i >= this.f22689b) {
            throw new IllegalArgumentException("Requested row is outside the image: ".concat(String.valueOf(i)));
        }
        int i2 = this.f22688a;
        if (bArr == null || bArr.length < i2) {
            bArr = new byte[i2];
        }
        System.arraycopy(this.f22695d, ((i + this.f22699h) * this.f22696e) + this.f22698g, bArr, 0, i2);
        return bArr;
    }

    @Override // p110z1.LuminanceSource
    /* renamed from: a */
    public final byte[] mo1642a() {
        int i = this.f22688a;
        int i2 = this.f22689b;
        if (i == this.f22696e && i2 == this.f22697f) {
            return this.f22695d;
        }
        int i3 = i * i2;
        byte[] bArr = new byte[i3];
        int i4 = this.f22699h;
        int i5 = this.f22696e;
        int i6 = (i4 * i5) + this.f22698g;
        if (i == i5) {
            System.arraycopy(this.f22695d, i6, bArr, 0, i3);
            return bArr;
        }
        for (int i7 = 0; i7 < i2; i7++) {
            System.arraycopy(this.f22695d, i6, bArr, i7 * i, i);
            i6 += this.f22696e;
        }
        return bArr;
    }

    /* renamed from: g */
    private int[] m1645g() {
        int i = this.f22688a / 2;
        int i2 = this.f22689b / 2;
        int[] iArr = new int[i * i2];
        byte[] bArr = this.f22695d;
        int i3 = (this.f22699h * this.f22696e) + this.f22698g;
        for (int i4 = 0; i4 < i2; i4++) {
            int i5 = i4 * i;
            for (int i6 = 0; i6 < i; i6++) {
                iArr[i5 + i6] = ((bArr[(i6 << 1) + i3] & 255) * 65793) | (-16777216);
            }
            i3 += this.f22696e << 1;
        }
        return iArr;
    }

    /* renamed from: h */
    private int m1644h() {
        return this.f22688a / 2;
    }

    /* renamed from: i */
    private int m1643i() {
        return this.f22689b / 2;
    }
}
