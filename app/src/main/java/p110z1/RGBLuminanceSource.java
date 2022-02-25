package p110z1;

import com.tencent.smtt.sdk.TbsListener;

/* renamed from: z1.oi */
/* loaded from: classes3.dex */
public final class RGBLuminanceSource extends LuminanceSource {

    /* renamed from: c */
    private final byte[] f22700c;

    /* renamed from: d */
    private final int f22701d;

    /* renamed from: e */
    private final int f22702e;

    /* renamed from: f */
    private final int f22703f;

    /* renamed from: g */
    private final int f22704g;

    @Override // p110z1.LuminanceSource
    /* renamed from: b */
    public final boolean mo1639b() {
        return true;
    }

    public RGBLuminanceSource(int i, int i2, int[] iArr) {
        super(i, i2);
        this.f22701d = i;
        this.f22702e = i2;
        this.f22703f = 0;
        this.f22704g = 0;
        int i3 = i * i2;
        this.f22700c = new byte[i3];
        for (int i4 = 0; i4 < i3; i4++) {
            int i5 = iArr[i4];
            this.f22700c[i4] = (byte) (((((i5 >> 16) & 255) + ((i5 >> 7) & TbsListener.ErrorCode.INFO_CODE_FILEREADER_OPENFILEREADER_MINIQBSUCCESS)) + (i5 & 255)) / 4);
        }
    }

    private RGBLuminanceSource(byte[] bArr, int i, int i2, int i3, int i4, int i5, int i6) {
        super(i5, i6);
        if (i5 + i3 > i || i6 + i4 > i2) {
            throw new IllegalArgumentException("Crop rectangle does not fit within image data.");
        }
        this.f22700c = bArr;
        this.f22701d = i;
        this.f22702e = i2;
        this.f22703f = i3;
        this.f22704g = i4;
    }

    @Override // p110z1.LuminanceSource
    /* renamed from: a */
    public final LuminanceSource mo1641a(int i, int i2, int i3, int i4) {
        return new RGBLuminanceSource(this.f22700c, this.f22701d, this.f22702e, this.f22703f + i, this.f22704g + i2, i3, i4);
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
        System.arraycopy(this.f22700c, ((i + this.f22704g) * this.f22701d) + this.f22703f, bArr, 0, i2);
        return bArr;
    }

    @Override // p110z1.LuminanceSource
    /* renamed from: a */
    public final byte[] mo1642a() {
        int i = this.f22688a;
        int i2 = this.f22689b;
        if (i == this.f22701d && i2 == this.f22702e) {
            return this.f22700c;
        }
        int i3 = i * i2;
        byte[] bArr = new byte[i3];
        int i4 = this.f22704g;
        int i5 = this.f22701d;
        int i6 = (i4 * i5) + this.f22703f;
        if (i == i5) {
            System.arraycopy(this.f22700c, i6, bArr, 0, i3);
            return bArr;
        }
        for (int i7 = 0; i7 < i2; i7++) {
            System.arraycopy(this.f22700c, i6, bArr, i7 * i, i);
            i6 += this.f22701d;
        }
        return bArr;
    }
}
