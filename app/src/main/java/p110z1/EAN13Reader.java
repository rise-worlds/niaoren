package p110z1;

/* renamed from: z1.ll */
/* loaded from: classes3.dex */
public final class EAN13Reader extends UPCEANReader {

    /* renamed from: a */
    static final int[] f22359a = {0, 11, 13, 14, 19, 25, 28, 21, 22, 26};

    /* renamed from: g */
    private final int[] f22360g = new int[4];

    @Override // p110z1.UPCEANReader
    /* renamed from: a */
    protected final int mo2063a(BitArray huVar, int[] iArr, StringBuilder sb) throws NotFoundException {
        int[] iArr2 = this.f22360g;
        iArr2[0] = 0;
        iArr2[1] = 0;
        iArr2[2] = 0;
        iArr2[3] = 0;
        int i = huVar.f21908b;
        int i2 = iArr[1];
        int i3 = 0;
        int i4 = 0;
        while (i3 < 6 && i2 < i) {
            int a = m2064a(huVar, iArr2, i2, f22398f);
            sb.append((char) ((a % 10) + 48));
            int i5 = i2;
            for (int i6 : iArr2) {
                i5 += i6;
            }
            if (a >= 10) {
                i4 = (1 << (5 - i3)) | i4;
            }
            i3++;
            i2 = i5;
        }
        for (int i7 = 0; i7 < 10; i7++) {
            if (i4 == f22359a[i7]) {
                sb.insert(0, (char) (i7 + 48));
                int i8 = m2066a(huVar, i2, true, f22395c)[1];
                int i9 = 0;
                while (i9 < 6 && i8 < i) {
                    sb.append((char) (m2064a(huVar, iArr2, i8, f22397e) + 48));
                    int i10 = i8;
                    for (int i11 : iArr2) {
                        i10 += i11;
                    }
                    i9++;
                    i8 = i10;
                }
                return i8;
            }
        }
        throw NotFoundException.m1647a();
    }

    @Override // p110z1.UPCEANReader
    /* renamed from: b */
    final BarcodeFormat mo2062b() {
        return BarcodeFormat.EAN_13;
    }

    /* renamed from: a */
    private static void m2101a(StringBuilder sb, int i) throws NotFoundException {
        for (int i2 = 0; i2 < 10; i2++) {
            if (i == f22359a[i2]) {
                sb.insert(0, (char) (i2 + 48));
                return;
            }
        }
        throw NotFoundException.m1647a();
    }
}
