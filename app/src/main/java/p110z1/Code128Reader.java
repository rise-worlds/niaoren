package p110z1;

/* renamed from: z1.lf */
/* loaded from: classes3.dex */
public final class Code128Reader extends OneDReader {

    /* renamed from: a */
    static final int[][] f22309a = {new int[]{2, 1, 2, 2, 2, 2}, new int[]{2, 2, 2, 1, 2, 2}, new int[]{2, 2, 2, 2, 2, 1}, new int[]{1, 2, 1, 2, 2, 3}, new int[]{1, 2, 1, 3, 2, 2}, new int[]{1, 3, 1, 2, 2, 2}, new int[]{1, 2, 2, 2, 1, 3}, new int[]{1, 2, 2, 3, 1, 2}, new int[]{1, 3, 2, 2, 1, 2}, new int[]{2, 2, 1, 2, 1, 3}, new int[]{2, 2, 1, 3, 1, 2}, new int[]{2, 3, 1, 2, 1, 2}, new int[]{1, 1, 2, 2, 3, 2}, new int[]{1, 2, 2, 1, 3, 2}, new int[]{1, 2, 2, 2, 3, 1}, new int[]{1, 1, 3, 2, 2, 2}, new int[]{1, 2, 3, 1, 2, 2}, new int[]{1, 2, 3, 2, 2, 1}, new int[]{2, 2, 3, 2, 1, 1}, new int[]{2, 2, 1, 1, 3, 2}, new int[]{2, 2, 1, 2, 3, 1}, new int[]{2, 1, 3, 2, 1, 2}, new int[]{2, 2, 3, 1, 1, 2}, new int[]{3, 1, 2, 1, 3, 1}, new int[]{3, 1, 1, 2, 2, 2}, new int[]{3, 2, 1, 1, 2, 2}, new int[]{3, 2, 1, 2, 2, 1}, new int[]{3, 1, 2, 2, 1, 2}, new int[]{3, 2, 2, 1, 1, 2}, new int[]{3, 2, 2, 2, 1, 1}, new int[]{2, 1, 2, 1, 2, 3}, new int[]{2, 1, 2, 3, 2, 1}, new int[]{2, 3, 2, 1, 2, 1}, new int[]{1, 1, 1, 3, 2, 3}, new int[]{1, 3, 1, 1, 2, 3}, new int[]{1, 3, 1, 3, 2, 1}, new int[]{1, 1, 2, 3, 1, 3}, new int[]{1, 3, 2, 1, 1, 3}, new int[]{1, 3, 2, 3, 1, 1}, new int[]{2, 1, 1, 3, 1, 3}, new int[]{2, 3, 1, 1, 1, 3}, new int[]{2, 3, 1, 3, 1, 1}, new int[]{1, 1, 2, 1, 3, 3}, new int[]{1, 1, 2, 3, 3, 1}, new int[]{1, 3, 2, 1, 3, 1}, new int[]{1, 1, 3, 1, 2, 3}, new int[]{1, 1, 3, 3, 2, 1}, new int[]{1, 3, 3, 1, 2, 1}, new int[]{3, 1, 3, 1, 2, 1}, new int[]{2, 1, 1, 3, 3, 1}, new int[]{2, 3, 1, 1, 3, 1}, new int[]{2, 1, 3, 1, 1, 3}, new int[]{2, 1, 3, 3, 1, 1}, new int[]{2, 1, 3, 1, 3, 1}, new int[]{3, 1, 1, 1, 2, 3}, new int[]{3, 1, 1, 3, 2, 1}, new int[]{3, 3, 1, 1, 2, 1}, new int[]{3, 1, 2, 1, 1, 3}, new int[]{3, 1, 2, 3, 1, 1}, new int[]{3, 3, 2, 1, 1, 1}, new int[]{3, 1, 4, 1, 1, 1}, new int[]{2, 2, 1, 4, 1, 1}, new int[]{4, 3, 1, 1, 1, 1}, new int[]{1, 1, 1, 2, 2, 4}, new int[]{1, 1, 1, 4, 2, 2}, new int[]{1, 2, 1, 1, 2, 4}, new int[]{1, 2, 1, 4, 2, 1}, new int[]{1, 4, 1, 1, 2, 2}, new int[]{1, 4, 1, 2, 2, 1}, new int[]{1, 1, 2, 2, 1, 4}, new int[]{1, 1, 2, 4, 1, 2}, new int[]{1, 2, 2, 1, 1, 4}, new int[]{1, 2, 2, 4, 1, 1}, new int[]{1, 4, 2, 1, 1, 2}, new int[]{1, 4, 2, 2, 1, 1}, new int[]{2, 4, 1, 2, 1, 1}, new int[]{2, 2, 1, 1, 1, 4}, new int[]{4, 1, 3, 1, 1, 1}, new int[]{2, 4, 1, 1, 1, 2}, new int[]{1, 3, 4, 1, 1, 1}, new int[]{1, 1, 1, 2, 4, 2}, new int[]{1, 2, 1, 1, 4, 2}, new int[]{1, 2, 1, 2, 4, 1}, new int[]{1, 1, 4, 2, 1, 2}, new int[]{1, 2, 4, 1, 1, 2}, new int[]{1, 2, 4, 2, 1, 1}, new int[]{4, 1, 1, 2, 1, 2}, new int[]{4, 2, 1, 1, 1, 2}, new int[]{4, 2, 1, 2, 1, 1}, new int[]{2, 1, 2, 1, 4, 1}, new int[]{2, 1, 4, 1, 2, 1}, new int[]{4, 1, 2, 1, 2, 1}, new int[]{1, 1, 1, 1, 4, 3}, new int[]{1, 1, 1, 3, 4, 1}, new int[]{1, 3, 1, 1, 4, 1}, new int[]{1, 1, 4, 1, 1, 3}, new int[]{1, 1, 4, 3, 1, 1}, new int[]{4, 1, 1, 1, 1, 3}, new int[]{4, 1, 1, 3, 1, 1}, new int[]{1, 1, 3, 1, 4, 1}, new int[]{1, 1, 4, 1, 3, 1}, new int[]{3, 1, 1, 1, 4, 1}, new int[]{4, 1, 1, 1, 3, 1}, new int[]{2, 1, 1, 4, 1, 2}, new int[]{2, 1, 1, 2, 1, 4}, new int[]{2, 1, 1, 2, 3, 2}, new int[]{2, 3, 3, 1, 1, 1, 2}};

    /* renamed from: b */
    private static final float f22310b = 0.25f;

    /* renamed from: c */
    private static final float f22311c = 0.7f;

    /* renamed from: d */
    private static final int f22312d = 98;

    /* renamed from: e */
    private static final int f22313e = 99;

    /* renamed from: f */
    private static final int f22314f = 100;

    /* renamed from: g */
    private static final int f22315g = 101;

    /* renamed from: h */
    private static final int f22316h = 102;

    /* renamed from: i */
    private static final int f22317i = 97;

    /* renamed from: j */
    private static final int f22318j = 96;

    /* renamed from: k */
    private static final int f22319k = 101;

    /* renamed from: l */
    private static final int f22320l = 100;

    /* renamed from: m */
    private static final int f22321m = 103;

    /* renamed from: n */
    private static final int f22322n = 104;

    /* renamed from: o */
    private static final int f22323o = 105;

    /* renamed from: p */
    private static final int f22324p = 106;

    /* renamed from: a */
    private static int m2121a(BitArray huVar, int[] iArr, int i) throws NotFoundException {
        m2090a(huVar, i, iArr);
        float f = f22310b;
        int i2 = -1;
        int i3 = 0;
        while (true) {
            int[][] iArr2 = f22309a;
            if (i3 >= iArr2.length) {
                break;
            }
            float a = m2089a(iArr, iArr2[i3], (float) f22311c);
            if (a < f) {
                i2 = i3;
                f = a;
            }
            i3++;
        }
        if (i2 >= 0) {
            return i2;
        }
        throw NotFoundException.m1647a();
    }

    /* JADX WARN: Removed duplicated region for block: B:107:0x01d1 A[PHI: r19 
      PHI: (r19v7 boolean) = (r19v8 boolean), (r19v8 boolean), (r19v8 boolean), (r19v10 boolean), (r19v10 boolean), (r19v10 boolean) binds: [B:91:0x018e, B:96:0x01a0, B:95:0x019a, B:64:0x011e, B:69:0x0131, B:68:0x012a] A[DONT_GENERATE, DONT_INLINE]] */
    @Override // p110z1.OneDReader
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final p110z1.C5422ol mo2072a(int r25, p110z1.BitArray r26, java.util.Map<p110z1.DecodeHintType, ?> r27) throws p110z1.NotFoundException, p110z1.FormatException, p110z1.ChecksumException {
        /*
            Method dump skipped, instructions count: 834
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: p110z1.Code128Reader.mo2072a(int, z1.hu, java.util.Map):z1.ol");
    }

    /* renamed from: a */
    private static int[] m2122a(BitArray huVar) throws NotFoundException {
        int i = huVar.f21908b;
        int c = huVar.m2541c(0);
        int[] iArr = new int[6];
        int i2 = c;
        boolean z = false;
        int i3 = 0;
        while (c < i) {
            if (huVar.m2551a(c) != z) {
                iArr[i3] = iArr[i3] + 1;
            } else {
                if (i3 == 5) {
                    float f = f22310b;
                    int i4 = -1;
                    for (int i5 = 103; i5 <= 105; i5++) {
                        float a = m2089a(iArr, f22309a[i5], (float) f22311c);
                        if (a < f) {
                            i4 = i5;
                            f = a;
                        }
                    }
                    if (i4 >= 0 && huVar.m2550a(Math.max(0, i2 - ((c - i2) / 2)), i2)) {
                        return new int[]{i2, c, i4};
                    }
                    i2 += iArr[0] + iArr[1];
                    int i6 = i3 - 1;
                    System.arraycopy(iArr, 2, iArr, 0, i6);
                    iArr[i6] = 0;
                    iArr[i3] = 0;
                    i3--;
                } else {
                    i3++;
                }
                iArr[i3] = 1;
                z = !z;
            }
            c++;
        }
        throw NotFoundException.m1647a();
    }
}
