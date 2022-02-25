package p110z1;

import java.util.Map;

/* renamed from: z1.lq */
/* loaded from: classes3.dex */
public final class ITFReader extends OneDReader {

    /* renamed from: a */
    private static final float f22366a = 0.38f;

    /* renamed from: b */
    private static final float f22367b = 0.5f;

    /* renamed from: c */
    private static final int f22368c = 3;

    /* renamed from: d */
    private static final int f22369d = 2;

    /* renamed from: e */
    private static final int f22370e = 1;

    /* renamed from: f */
    private static final int[] f22371f = {6, 8, 10, 12, 14};

    /* renamed from: h */
    private static final int[] f22372h = {1, 1, 1, 1};

    /* renamed from: i */
    private static final int[][] f22373i = {new int[]{1, 1, 2}, new int[]{1, 1, 3}};

    /* renamed from: j */
    private static final int[][] f22374j = {new int[]{1, 1, 2, 2, 1}, new int[]{2, 1, 1, 1, 2}, new int[]{1, 2, 1, 1, 2}, new int[]{2, 2, 1, 1, 1}, new int[]{1, 1, 2, 1, 2}, new int[]{2, 1, 2, 1, 1}, new int[]{1, 2, 2, 1, 1}, new int[]{1, 1, 1, 2, 2}, new int[]{2, 1, 1, 2, 1}, new int[]{1, 2, 1, 2, 1}, new int[]{1, 1, 3, 3, 1}, new int[]{3, 1, 1, 1, 3}, new int[]{1, 3, 1, 1, 3}, new int[]{3, 3, 1, 1, 1}, new int[]{1, 1, 3, 1, 3}, new int[]{3, 1, 3, 1, 1}, new int[]{1, 3, 3, 1, 1}, new int[]{1, 1, 1, 3, 3}, new int[]{3, 1, 1, 3, 1}, new int[]{1, 3, 1, 3, 1}};

    /* renamed from: g */
    private int f22375g = -1;

    /* renamed from: a */
    private static void m2095a(BitArray huVar, int i, int i2, StringBuilder sb) throws NotFoundException {
        int[] iArr = new int[10];
        int[] iArr2 = new int[5];
        int[] iArr3 = new int[5];
        while (i < i2) {
            m2090a(huVar, i, iArr);
            for (int i3 = 0; i3 < 5; i3++) {
                int i4 = i3 * 2;
                iArr2[i3] = iArr[i4];
                iArr3[i3] = iArr[i4 + 1];
            }
            sb.append((char) (m2094a(iArr2) + 48));
            sb.append((char) (m2094a(iArr3) + 48));
            for (int i5 = 0; i5 < 10; i5++) {
                i += iArr[i5];
            }
        }
    }

    /* renamed from: a */
    private int[] m2097a(BitArray huVar) throws NotFoundException {
        int[] c = m2091c(huVar, m2093b(huVar), f22372h);
        this.f22375g = (c[1] - c[0]) / 4;
        m2096a(huVar, c[0]);
        return c;
    }

    /* JADX WARN: Code restructure failed: missing block: B:12:0x001b, code lost:
        return;
     */
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void m2096a(p110z1.BitArray r3, int r4) throws p110z1.NotFoundException {
        /*
            r2 = this;
            int r0 = r2.f22375g
            int r0 = r0 * 10
            if (r0 >= r4) goto L_0x0007
            goto L_0x0008
        L_0x0007:
            r0 = r4
        L_0x0008:
            int r4 = r4 + (-1)
        L_0x000a:
            if (r0 <= 0) goto L_0x0019
            if (r4 < 0) goto L_0x0019
            boolean r1 = r3.m2551a(r4)
            if (r1 != 0) goto L_0x0019
            int r0 = r0 + (-1)
            int r4 = r4 + (-1)
            goto L_0x000a
        L_0x0019:
            if (r0 != 0) goto L_0x001c
            return
        L_0x001c:
            z1.og r3 = p110z1.NotFoundException.m1647a()
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: p110z1.ITFReader.m2096a(z1.hu, int):void");
    }

    /* renamed from: c */
    private int[] m2092c(BitArray huVar) throws NotFoundException {
        int[] iArr;
        int i;
        huVar.m2542c();
        try {
            try {
                iArr = m2091c(huVar, m2093b(huVar), f22373i[0]);
            } catch (NotFoundException unused) {
                iArr = m2091c(huVar, i, f22373i[1]);
            }
            m2096a(huVar, iArr[0]);
            int i2 = iArr[0];
            iArr[0] = huVar.f21908b - iArr[1];
            iArr[1] = huVar.f21908b - i2;
            return iArr;
        } finally {
            huVar.m2542c();
        }
    }

    /* renamed from: c */
    private static int[] m2091c(BitArray huVar, int i, int[] iArr) throws NotFoundException {
        int length = iArr.length;
        int[] iArr2 = new int[length];
        int i2 = huVar.f21908b;
        int i3 = i;
        boolean z = false;
        int i4 = 0;
        while (i < i2) {
            if (huVar.m2551a(i) != z) {
                iArr2[i4] = iArr2[i4] + 1;
            } else {
                if (i4 != length - 1) {
                    i4++;
                } else if (m2089a(iArr2, iArr, (float) f22367b) < f22366a) {
                    return new int[]{i3, i};
                } else {
                    i3 += iArr2[0] + iArr2[1];
                    int i5 = i4 - 1;
                    System.arraycopy(iArr2, 2, iArr2, 0, i5);
                    iArr2[i5] = 0;
                    iArr2[i4] = 0;
                    i4--;
                }
                iArr2[i4] = 1;
                z = !z;
            }
            i++;
        }
        throw NotFoundException.m1647a();
    }

    /* renamed from: a */
    private static int m2094a(int[] iArr) throws NotFoundException {
        int length = f22374j.length;
        int i = -1;
        float f = f22366a;
        for (int i2 = 0; i2 < length; i2++) {
            float a = m2089a(iArr, f22374j[i2], (float) f22367b);
            if (a < f) {
                i = i2;
                f = a;
            } else if (a == f) {
                i = -1;
            }
        }
        if (i >= 0) {
            return i % 10;
        }
        throw NotFoundException.m1647a();
    }

    @Override // p110z1.OneDReader
    /* renamed from: a */
    public final C5422ol mo2072a(int i, BitArray huVar, Map<DecodeHintType, ?> map) throws FormatException, NotFoundException {
        boolean z;
        int[] c = m2091c(huVar, m2093b(huVar), f22372h);
        this.f22375g = (c[1] - c[0]) / 4;
        m2096a(huVar, c[0]);
        int[] c2 = m2092c(huVar);
        StringBuilder sb = new StringBuilder(20);
        m2095a(huVar, c[1], c2[0], sb);
        String sb2 = sb.toString();
        int[] iArr = map != null ? (int[]) map.get(DecodeHintType.ALLOWED_LENGTHS) : null;
        if (iArr == null) {
            iArr = f22371f;
        }
        int length = sb2.length();
        int length2 = iArr.length;
        int i2 = 0;
        int i3 = 0;
        while (true) {
            if (i2 >= length2) {
                z = false;
                break;
            }
            int i4 = iArr[i2];
            if (length == i4) {
                z = true;
                break;
            }
            if (i4 > i3) {
                i3 = i4;
            }
            i2++;
        }
        if (!z && length > i3) {
            z = true;
        }
        if (z) {
            float f = i;
            return new C5422ol(sb2, null, new ResultPoint[]{new ResultPoint(c[1], f), new ResultPoint(c2[0], f)}, BarcodeFormat.ITF);
        }
        throw FormatException.m2059a();
    }

    /* renamed from: b */
    private static int m2093b(BitArray huVar) throws NotFoundException {
        int i = huVar.f21908b;
        int c = huVar.m2541c(0);
        if (c != i) {
            return c;
        }
        throw NotFoundException.m1647a();
    }
}
