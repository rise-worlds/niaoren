package p110z1;

/* renamed from: z1.ij */
/* loaded from: classes3.dex */
public class GlobalHistogramBinarizer extends Binarizer {

    /* renamed from: b */
    private static final int f22001b = 5;

    /* renamed from: c */
    private static final int f22002c = 3;

    /* renamed from: d */
    private static final int f22003d = 32;

    /* renamed from: e */
    private static final byte[] f22004e = new byte[0];

    /* renamed from: f */
    private byte[] f22005f = f22004e;

    /* renamed from: g */
    private final int[] f22006g = new int[32];

    public GlobalHistogramBinarizer(LuminanceSource odVar) {
        super(odVar);
    }

    @Override // p110z1.Binarizer
    /* renamed from: a */
    public Binarizer mo2433a(LuminanceSource odVar) {
        return new GlobalHistogramBinarizer(odVar);
    }

    /* renamed from: a */
    private void m2443a(int i) {
        if (this.f22005f.length < i) {
            this.f22005f = new byte[i];
        }
        for (int i2 = 0; i2 < 32; i2++) {
            this.f22006g[i2] = 0;
        }
    }

    /* renamed from: a */
    private static int m2441a(int[] iArr) throws NotFoundException {
        int length = iArr.length;
        int i = 0;
        int i2 = 0;
        int i3 = 0;
        for (int i4 = 0; i4 < length; i4++) {
            if (iArr[i4] > i) {
                i = iArr[i4];
                i3 = i4;
            }
            if (iArr[i4] > i2) {
                i2 = iArr[i4];
            }
        }
        int i5 = 0;
        int i6 = 0;
        for (int i7 = 0; i7 < length; i7++) {
            int i8 = i7 - i3;
            int i9 = iArr[i7] * i8 * i8;
            if (i9 > i6) {
                i5 = i7;
                i6 = i9;
            }
        }
        if (i3 > i5) {
            i3 = i5;
            i5 = i3;
        }
        if (i5 - i3 > length / 16) {
            int i10 = i5 - 1;
            int i11 = i10;
            int i12 = -1;
            while (i10 > i3) {
                int i13 = i10 - i3;
                int i14 = i13 * i13 * (i5 - i10) * (i2 - iArr[i10]);
                if (i14 > i12) {
                    i11 = i10;
                    i12 = i14;
                }
                i10--;
            }
            return i11 << 3;
        }
        throw NotFoundException.m1647a();
    }

    @Override // p110z1.Binarizer
    /* renamed from: a */
    public final BitArray mo2442a(int i, BitArray huVar) throws NotFoundException {
        LuminanceSource odVar = this.f21780a;
        int i2 = odVar.f22688a;
        if (huVar == null || huVar.f21908b < i2) {
            huVar = new BitArray(i2);
        } else {
            huVar.m2546b();
        }
        m2443a(i2);
        byte[] a = odVar.mo1640a(i, this.f22005f);
        int[] iArr = this.f22006g;
        for (int i3 = 0; i3 < i2; i3++) {
            int i4 = (a[i3] & 255) >> 3;
            iArr[i4] = iArr[i4] + 1;
        }
        int a2 = m2441a(iArr);
        if (i2 < 3) {
            for (int i5 = 0; i5 < i2; i5++) {
                if ((a[i5] & 255) < a2) {
                    huVar.m2545b(i5);
                }
            }
        } else {
            int i6 = a[0] & 255;
            int i7 = a[1] & 255;
            int i8 = 1;
            while (i8 < i2 - 1) {
                int i9 = i8 + 1;
                int i10 = a[i9] & 255;
                if ((((i7 << 2) - i6) - i10) / 2 < a2) {
                    huVar.m2545b(i8);
                }
                i6 = i7;
                i8 = i9;
                i7 = i10;
            }
        }
        return huVar;
    }

    @Override // p110z1.Binarizer
    /* renamed from: a */
    public BitMatrix mo2435a() throws NotFoundException {
        LuminanceSource odVar = this.f21780a;
        int i = odVar.f22688a;
        int i2 = odVar.f22689b;
        BitMatrix hyVar = new BitMatrix(i, i2);
        m2443a(i);
        int[] iArr = this.f22006g;
        for (int i3 = 1; i3 < 5; i3++) {
            byte[] a = odVar.mo1640a((i2 * i3) / 5, this.f22005f);
            int i4 = (i << 2) / 5;
            for (int i5 = i / 5; i5 < i4; i5++) {
                int i6 = (a[i5] & 255) >> 3;
                iArr[i6] = iArr[i6] + 1;
            }
        }
        int a2 = m2441a(iArr);
        byte[] a3 = odVar.mo1642a();
        for (int i7 = 0; i7 < i2; i7++) {
            int i8 = i7 * i;
            for (int i9 = 0; i9 < i; i9++) {
                if ((a3[i8 + i9] & 255) < a2) {
                    hyVar.m2511b(i9, i7);
                }
            }
        }
        return hyVar;
    }
}
