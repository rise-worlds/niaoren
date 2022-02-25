package p110z1;

/* renamed from: z1.ie */
/* loaded from: classes3.dex */
public final class BitSource {

    /* renamed from: a */
    public int f21954a;

    /* renamed from: b */
    public int f21955b;

    /* renamed from: c */
    private final byte[] f21956c;

    public BitSource(byte[] bArr) {
        this.f21956c = bArr;
    }

    /* renamed from: b */
    private int m2465b() {
        return this.f21955b;
    }

    /* renamed from: c */
    private int m2464c() {
        return this.f21954a;
    }

    /* renamed from: a */
    public final int m2466a(int i) {
        int i2;
        if (i <= 0 || i > 32 || i > m2467a()) {
            throw new IllegalArgumentException(String.valueOf(i));
        }
        int i3 = this.f21955b;
        if (i3 > 0) {
            int i4 = 8 - i3;
            int i5 = i < i4 ? i : i4;
            int i6 = i4 - i5;
            byte[] bArr = this.f21956c;
            int i7 = this.f21954a;
            i2 = (((255 >> (8 - i5)) << i6) & bArr[i7]) >> i6;
            i -= i5;
            this.f21955b += i5;
            if (this.f21955b == 8) {
                this.f21955b = 0;
                this.f21954a = i7 + 1;
            }
        } else {
            i2 = 0;
        }
        if (i <= 0) {
            return i2;
        }
        while (i >= 8) {
            byte[] bArr2 = this.f21956c;
            int i8 = this.f21954a;
            i2 = (i2 << 8) | (bArr2[i8] & 255);
            this.f21954a = i8 + 1;
            i -= 8;
        }
        if (i <= 0) {
            return i2;
        }
        int i9 = 8 - i;
        int i10 = (i2 << i) | ((((255 >> i9) << i9) & this.f21956c[this.f21954a]) >> i9);
        this.f21955b += i;
        return i10;
    }

    /* renamed from: a */
    public final int m2467a() {
        return ((this.f21956c.length - this.f21954a) * 8) - this.f21955b;
    }
}
