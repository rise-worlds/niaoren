package p110z1;

import com.tencent.smtt.utils.TbsLog;

/* renamed from: z1.fy */
/* loaded from: classes3.dex */
public final class Detector {

    /* renamed from: a */
    private static final int[] f21737a = {3808, 476, 2107, 1799};

    /* renamed from: b */
    private final BitMatrix f21738b;

    /* renamed from: c */
    private boolean f21739c;

    /* renamed from: d */
    private int f21740d;

    /* renamed from: e */
    private int f21741e;

    /* renamed from: f */
    private int f21742f;

    /* renamed from: g */
    private int f21743g;

    public Detector(BitMatrix hyVar) {
        this.f21738b = hyVar;
    }

    /* renamed from: a */
    private AztecDetectorResult m2797a() throws NotFoundException {
        return m2786a(false);
    }

    /* renamed from: a */
    public final AztecDetectorResult m2786a(boolean z) throws NotFoundException {
        long j;
        long j2;
        C5361a b = m2782b();
        int i = 1;
        this.f21742f = 1;
        C5361a aVar = b;
        C5361a aVar2 = aVar;
        C5361a aVar3 = aVar2;
        boolean z2 = true;
        while (this.f21742f < 9) {
            C5361a a = m2791a(b, z2, i, -1);
            C5361a a2 = m2791a(aVar, z2, i, i);
            C5361a a3 = m2791a(aVar2, z2, -1, i);
            C5361a a4 = m2791a(aVar3, z2, -1, -1);
            if (this.f21742f > 2) {
                double b2 = (m2781b(a4, a) * this.f21742f) / (m2781b(aVar3, b) * (this.f21742f + 2));
                if (b2 < 0.75d || b2 > 1.25d) {
                    break;
                }
                C5361a aVar4 = new C5361a(a.f21744a - 3, a.f21745b + 3);
                C5361a aVar5 = new C5361a(a2.f21744a - 3, a2.f21745b - 3);
                C5361a aVar6 = new C5361a(a3.f21744a + 3, a3.f21745b - 3);
                b = a;
                C5361a aVar7 = new C5361a(a4.f21744a + 3, a4.f21745b + 3);
                int a5 = m2793a(aVar7, aVar4);
                if (!(a5 != 0 && m2793a(aVar4, aVar5) == a5 && m2793a(aVar5, aVar6) == a5 && m2793a(aVar6, aVar7) == a5)) {
                    break;
                }
            } else {
                b = a;
            }
            z2 = !z2;
            this.f21742f++;
            aVar3 = a4;
            aVar = a2;
            aVar2 = a3;
            i = 1;
        }
        int i2 = this.f21742f;
        if (i2 == 5 || i2 == 7) {
            this.f21739c = this.f21742f == 5;
            ResultPoint[] onVarArr = {new ResultPoint(b.f21744a + 0.5f, b.f21745b - 0.5f), new ResultPoint(aVar.f21744a + 0.5f, aVar.f21745b + 0.5f), new ResultPoint(aVar2.f21744a - 0.5f, aVar2.f21745b + 0.5f), new ResultPoint(aVar3.f21744a - 0.5f, aVar3.f21745b - 0.5f)};
            int i3 = this.f21742f;
            ResultPoint[] a6 = m2783a(onVarArr, (i3 * 2) - 3, i3 * 2);
            if (z) {
                ResultPoint onVar = a6[0];
                a6[0] = a6[2];
                a6[2] = onVar;
            }
            if (!m2789a(a6[0]) || !m2789a(a6[1]) || !m2789a(a6[2]) || !m2789a(a6[3])) {
                throw NotFoundException.m1647a();
            }
            int i4 = this.f21742f * 2;
            int[] iArr = {m2787a(a6[0], a6[1], i4), m2787a(a6[1], a6[2], i4), m2787a(a6[2], a6[3], i4), m2787a(a6[3], a6[0], i4)};
            int i5 = 0;
            for (int i6 = 0; i6 < 4; i6++) {
                int i7 = iArr[i6];
                i5 = (i5 << 3) + ((i7 >> (i4 - 2)) << 1) + (i7 & 1);
            }
            int i8 = ((i5 & 1) << 11) + (i5 >> 1);
            for (int i9 = 0; i9 < 4; i9++) {
                if (Integer.bitCount(f21737a[i9] ^ i8) <= 2) {
                    this.f21743g = i9;
                    long j3 = 0;
                    for (int i10 = 0; i10 < 4; i10++) {
                        int i11 = iArr[(this.f21743g + i10) % 4];
                        if (this.f21739c) {
                            j2 = j3 << 7;
                            j = (i11 >> 1) & 127;
                        } else {
                            j2 = j3 << 10;
                            j = ((i11 >> 2) & TbsLog.TBSLOG_CODE_SDK_UNAVAIL_X5CORE) + ((i11 >> 1) & 31);
                        }
                        j3 = j2 + j;
                    }
                    int a7 = m2795a(j3, this.f21739c);
                    if (this.f21739c) {
                        this.f21740d = (a7 >> 6) + 1;
                        this.f21741e = (a7 & 63) + 1;
                    } else {
                        this.f21740d = (a7 >> 11) + 1;
                        this.f21741e = (a7 & 2047) + 1;
                    }
                    BitMatrix hyVar = this.f21738b;
                    int i12 = this.f21743g;
                    ResultPoint onVar2 = a6[i12 % 4];
                    ResultPoint onVar3 = a6[(i12 + 1) % 4];
                    ResultPoint onVar4 = a6[(i12 + 2) % 4];
                    ResultPoint onVar5 = a6[(i12 + 3) % 4];
                    GridSampler a8 = GridSampler.m2440a();
                    int c = m2779c();
                    float f = c / 2.0f;
                    int i13 = this.f21742f;
                    float f2 = f - i13;
                    float f3 = f + i13;
                    return new AztecDetectorResult(a8.mo2439a(hyVar, c, c, f2, f2, f3, f2, f3, f3, f2, f3, onVar2.f22726c, onVar2.f22727d, onVar3.f22726c, onVar3.f22727d, onVar4.f22726c, onVar4.f22727d, onVar5.f22726c, onVar5.f22727d), m2783a(a6, this.f21742f * 2, m2779c()), this.f21739c, this.f21741e, this.f21740d);
                }
            }
            throw NotFoundException.m1647a();
        }
        throw NotFoundException.m1647a();
    }

    /* renamed from: a */
    private void m2784a(ResultPoint[] onVarArr) throws NotFoundException {
        long j;
        long j2;
        if (!m2789a(onVarArr[0]) || !m2789a(onVarArr[1]) || !m2789a(onVarArr[2]) || !m2789a(onVarArr[3])) {
            throw NotFoundException.m1647a();
        }
        int i = this.f21742f * 2;
        int[] iArr = {m2787a(onVarArr[0], onVarArr[1], i), m2787a(onVarArr[1], onVarArr[2], i), m2787a(onVarArr[2], onVarArr[3], i), m2787a(onVarArr[3], onVarArr[0], i)};
        int i2 = 0;
        for (int i3 = 0; i3 < 4; i3++) {
            int i4 = iArr[i3];
            i2 = (i2 << 3) + ((i4 >> (i - 2)) << 1) + (i4 & 1);
        }
        int i5 = ((i2 & 1) << 11) + (i2 >> 1);
        for (int i6 = 0; i6 < 4; i6++) {
            if (Integer.bitCount(f21737a[i6] ^ i5) <= 2) {
                this.f21743g = i6;
                long j3 = 0;
                for (int i7 = 0; i7 < 4; i7++) {
                    int i8 = iArr[(this.f21743g + i7) % 4];
                    if (this.f21739c) {
                        j2 = j3 << 7;
                        j = (i8 >> 1) & 127;
                    } else {
                        j2 = j3 << 10;
                        j = ((i8 >> 2) & TbsLog.TBSLOG_CODE_SDK_UNAVAIL_X5CORE) + ((i8 >> 1) & 31);
                    }
                    j3 = j2 + j;
                }
                int a = m2795a(j3, this.f21739c);
                if (this.f21739c) {
                    this.f21740d = (a >> 6) + 1;
                    this.f21741e = (a & 63) + 1;
                    return;
                }
                this.f21740d = (a >> 11) + 1;
                this.f21741e = (a & 2047) + 1;
                return;
            }
        }
        throw NotFoundException.m1647a();
    }

    /* renamed from: a */
    private static int m2785a(int[] iArr, int i) throws NotFoundException {
        int i2 = 0;
        for (int i3 = 0; i3 < 4; i3++) {
            int i4 = iArr[i3];
            i2 = (i2 << 3) + ((i4 >> (i - 2)) << 1) + (i4 & 1);
        }
        int i5 = ((i2 & 1) << 11) + (i2 >> 1);
        for (int i6 = 0; i6 < 4; i6++) {
            if (Integer.bitCount(f21737a[i6] ^ i5) <= 2) {
                return i6;
            }
        }
        throw NotFoundException.m1647a();
    }

    /* renamed from: a */
    private static int m2795a(long j, boolean z) throws NotFoundException {
        int i;
        int i2;
        if (z) {
            i = 7;
            i2 = 2;
        } else {
            i = 10;
            i2 = 4;
        }
        int i3 = i - i2;
        int[] iArr = new int[i];
        for (int i4 = i - 1; i4 >= 0; i4--) {
            iArr[i4] = ((int) j) & 15;
            j >>= 4;
        }
        try {
            new ReedSolomonDecoder(GenericGF.f21927d).m2470a(iArr, i3);
            int i5 = 0;
            for (int i6 = 0; i6 < i2; i6++) {
                i5 = (i5 << 4) + iArr[i6];
            }
            return i5;
        } catch (ReedSolomonException unused) {
            throw NotFoundException.m1647a();
        }
    }

    /* renamed from: a */
    private ResultPoint[] m2794a(C5361a aVar) throws NotFoundException {
        int i = 1;
        this.f21742f = 1;
        C5361a aVar2 = aVar;
        C5361a aVar3 = aVar2;
        C5361a aVar4 = aVar3;
        C5361a aVar5 = aVar4;
        boolean z = true;
        while (this.f21742f < 9) {
            C5361a a = m2791a(aVar2, z, i, -1);
            C5361a a2 = m2791a(aVar3, z, i, i);
            C5361a a3 = m2791a(aVar4, z, -1, i);
            C5361a a4 = m2791a(aVar5, z, -1, -1);
            if (this.f21742f > 2) {
                double b = (m2781b(a4, a) * this.f21742f) / (m2781b(aVar5, aVar2) * (this.f21742f + 2));
                if (b < 0.75d || b > 1.25d) {
                    break;
                }
                C5361a aVar6 = new C5361a(a.f21744a - 3, a.f21745b + 3);
                C5361a aVar7 = new C5361a(a2.f21744a - 3, a2.f21745b - 3);
                C5361a aVar8 = new C5361a(a3.f21744a + 3, a3.f21745b - 3);
                aVar2 = a;
                C5361a aVar9 = new C5361a(a4.f21744a + 3, a4.f21745b + 3);
                int a5 = m2793a(aVar9, aVar6);
                if (!(a5 != 0 && m2793a(aVar6, aVar7) == a5 && m2793a(aVar7, aVar8) == a5 && m2793a(aVar8, aVar9) == a5)) {
                    break;
                }
            } else {
                aVar2 = a;
            }
            z = !z;
            this.f21742f++;
            aVar5 = a4;
            aVar3 = a2;
            aVar4 = a3;
            i = 1;
        }
        int i2 = this.f21742f;
        if (i2 == 5 || i2 == 7) {
            this.f21739c = this.f21742f == 5;
            ResultPoint[] onVarArr = {new ResultPoint(aVar2.f21744a + 0.5f, aVar2.f21745b - 0.5f), new ResultPoint(aVar3.f21744a + 0.5f, aVar3.f21745b + 0.5f), new ResultPoint(aVar4.f21744a - 0.5f, aVar4.f21745b + 0.5f), new ResultPoint(aVar5.f21744a - 0.5f, aVar5.f21745b - 0.5f)};
            int i3 = this.f21742f;
            return m2783a(onVarArr, (i3 * 2) - 3, i3 * 2);
        }
        throw NotFoundException.m1647a();
    }

    /* renamed from: b */
    private C5361a m2782b() {
        ResultPoint onVar;
        ResultPoint onVar2;
        ResultPoint onVar3;
        ResultPoint onVar4;
        ResultPoint onVar5;
        ResultPoint onVar6;
        ResultPoint onVar7;
        ResultPoint onVar8;
        try {
            ResultPoint[] a = new WhiteRectangleDetector(this.f21738b).m2524a();
            onVar3 = a[0];
            onVar2 = a[1];
            onVar = a[2];
            onVar4 = a[3];
        } catch (NotFoundException unused) {
            int i = this.f21738b.f21920a / 2;
            int i2 = this.f21738b.f21921b / 2;
            int i3 = i + 7;
            int i4 = i2 - 7;
            onVar3 = m2791a(new C5361a(i3, i4), false, 1, -1).m2778a();
            int i5 = i2 + 7;
            onVar2 = m2791a(new C5361a(i3, i5), false, 1, 1).m2778a();
            int i6 = i - 7;
            onVar = m2791a(new C5361a(i6, i5), false, -1, 1).m2778a();
            onVar4 = m2791a(new C5361a(i6, i4), false, -1, -1).m2778a();
        }
        int a2 = MathUtils.m2531a((((onVar3.f22726c + onVar4.f22726c) + onVar2.f22726c) + onVar.f22726c) / 4.0f);
        int a3 = MathUtils.m2531a((((onVar3.f22727d + onVar4.f22727d) + onVar2.f22727d) + onVar.f22727d) / 4.0f);
        try {
            ResultPoint[] a4 = new WhiteRectangleDetector(this.f21738b, 15, a2, a3).m2524a();
            onVar6 = a4[0];
            onVar5 = a4[1];
            onVar7 = a4[2];
            onVar8 = a4[3];
        } catch (NotFoundException unused2) {
            int i7 = a2 + 7;
            int i8 = a3 - 7;
            onVar6 = m2791a(new C5361a(i7, i8), false, 1, -1).m2778a();
            int i9 = a3 + 7;
            onVar5 = m2791a(new C5361a(i7, i9), false, 1, 1).m2778a();
            int i10 = a2 - 7;
            onVar7 = m2791a(new C5361a(i10, i9), false, -1, 1).m2778a();
            onVar8 = m2791a(new C5361a(i10, i8), false, -1, -1).m2778a();
        }
        return new C5361a(MathUtils.m2531a((((onVar6.f22726c + onVar8.f22726c) + onVar5.f22726c) + onVar7.f22726c) / 4.0f), MathUtils.m2531a((((onVar6.f22727d + onVar8.f22727d) + onVar5.f22727d) + onVar7.f22727d) / 4.0f));
    }

    /* renamed from: b */
    private ResultPoint[] m2780b(ResultPoint[] onVarArr) {
        return m2783a(onVarArr, this.f21742f * 2, m2779c());
    }

    /* renamed from: a */
    private BitMatrix m2790a(BitMatrix hyVar, ResultPoint onVar, ResultPoint onVar2, ResultPoint onVar3, ResultPoint onVar4) throws NotFoundException {
        GridSampler a = GridSampler.m2440a();
        int c = m2779c();
        float f = c / 2.0f;
        int i = this.f21742f;
        float f2 = f - i;
        float f3 = f + i;
        return a.mo2439a(hyVar, c, c, f2, f2, f3, f2, f3, f3, f2, f3, onVar.f22726c, onVar.f22727d, onVar2.f22726c, onVar2.f22727d, onVar3.f22726c, onVar3.f22727d, onVar4.f22726c, onVar4.f22727d);
    }

    /* renamed from: a */
    private boolean m2792a(C5361a aVar, C5361a aVar2, C5361a aVar3, C5361a aVar4) {
        C5361a aVar5 = new C5361a(aVar.f21744a - 3, aVar.f21745b + 3);
        C5361a aVar6 = new C5361a(aVar2.f21744a - 3, aVar2.f21745b - 3);
        C5361a aVar7 = new C5361a(aVar3.f21744a + 3, aVar3.f21745b - 3);
        C5361a aVar8 = new C5361a(aVar4.f21744a + 3, aVar4.f21745b + 3);
        int a = m2793a(aVar8, aVar5);
        return a != 0 && m2793a(aVar5, aVar6) == a && m2793a(aVar6, aVar7) == a && m2793a(aVar7, aVar8) == a;
    }

    /* renamed from: a */
    private int m2793a(C5361a aVar, C5361a aVar2) {
        float b = m2781b(aVar, aVar2);
        float f = (aVar2.f21744a - aVar.f21744a) / b;
        float f2 = (aVar2.f21745b - aVar.f21745b) / b;
        float f3 = aVar.f21744a;
        float f4 = aVar.f21745b;
        boolean a = this.f21738b.m2519a(aVar.f21744a, aVar.f21745b);
        int ceil = (int) Math.ceil(b);
        boolean z = false;
        int i = 0;
        for (int i2 = 0; i2 < ceil; i2++) {
            f3 += f;
            f4 += f2;
            if (this.f21738b.m2519a(MathUtils.m2531a(f3), MathUtils.m2531a(f4)) != a) {
                i++;
            }
        }
        float f5 = i / b;
        if (f5 > 0.1f && f5 < 0.9f) {
            return 0;
        }
        if (f5 <= 0.1f) {
            z = true;
        }
        return z == a ? 1 : -1;
    }

    /* renamed from: a */
    private static ResultPoint[] m2783a(ResultPoint[] onVarArr, int i, int i2) {
        float f = i2 / (i * 2.0f);
        float f2 = onVarArr[0].f22726c - onVarArr[2].f22726c;
        float f3 = onVarArr[0].f22727d - onVarArr[2].f22727d;
        float f4 = (onVarArr[0].f22726c + onVarArr[2].f22726c) / 2.0f;
        float f5 = (onVarArr[0].f22727d + onVarArr[2].f22727d) / 2.0f;
        float f6 = f2 * f;
        float f7 = f3 * f;
        ResultPoint onVar = new ResultPoint(f4 + f6, f5 + f7);
        ResultPoint onVar2 = new ResultPoint(f4 - f6, f5 - f7);
        float f8 = onVarArr[1].f22726c - onVarArr[3].f22726c;
        float f9 = onVarArr[1].f22727d - onVarArr[3].f22727d;
        float f10 = (onVarArr[1].f22726c + onVarArr[3].f22726c) / 2.0f;
        float f11 = (onVarArr[1].f22727d + onVarArr[3].f22727d) / 2.0f;
        float f12 = f8 * f;
        float f13 = f * f9;
        return new ResultPoint[]{onVar, new ResultPoint(f10 + f12, f11 + f13), onVar2, new ResultPoint(f10 - f12, f11 - f13)};
    }

    /* renamed from: a */
    private boolean m2796a(int i, int i2) {
        return i >= 0 && i < this.f21738b.f21920a && i2 > 0 && i2 < this.f21738b.f21921b;
    }

    /* renamed from: c */
    private int m2779c() {
        if (this.f21739c) {
            return (this.f21740d * 4) + 11;
        }
        int i = this.f21740d;
        return i <= 4 ? (i * 4) + 15 : (i * 4) + ((((i - 4) / 8) + 1) * 2) + 15;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: Detector.java */
    /* renamed from: z1.fy$a */
    /* loaded from: classes3.dex */
    public static final class C5361a {

        /* renamed from: a */
        final int f21744a;

        /* renamed from: b */
        final int f21745b;

        /* renamed from: a */
        final ResultPoint m2778a() {
            return new ResultPoint(this.f21744a, this.f21745b);
        }

        C5361a(int i, int i2) {
            this.f21744a = i;
            this.f21745b = i2;
        }

        /* renamed from: b */
        private int m2777b() {
            return this.f21744a;
        }

        /* renamed from: c */
        private int m2776c() {
            return this.f21745b;
        }

        public final String toString() {
            return SimpleComparison.f23612f + this.f21744a + ' ' + this.f21745b + Typography.f21053e;
        }
    }

    /* renamed from: a */
    private int m2787a(ResultPoint onVar, ResultPoint onVar2, int i) {
        float a = MathUtils.m2530a(onVar.f22726c, onVar.f22727d, onVar2.f22726c, onVar2.f22727d);
        float f = a / i;
        float f2 = onVar.f22726c;
        float f3 = onVar.f22727d;
        float f4 = ((onVar2.f22726c - onVar.f22726c) * f) / a;
        float f5 = (f * (onVar2.f22727d - onVar.f22727d)) / a;
        int i2 = 0;
        for (int i3 = 0; i3 < i; i3++) {
            float f6 = i3;
            if (this.f21738b.m2519a(MathUtils.m2531a((f6 * f4) + f2), MathUtils.m2531a((f6 * f5) + f3))) {
                i2 |= 1 << ((i - i3) - 1);
            }
        }
        return i2;
    }

    /* renamed from: a */
    private C5361a m2791a(C5361a aVar, boolean z, int i, int i2) {
        int i3 = aVar.f21744a + i;
        int i4 = aVar.f21745b;
        while (true) {
            i4 += i2;
            if (!m2796a(i3, i4) || this.f21738b.m2519a(i3, i4) != z) {
                break;
            }
            i3 += i;
        }
        int i5 = i3 - i;
        int i6 = i4 - i2;
        while (m2796a(i5, i6) && this.f21738b.m2519a(i5, i6) == z) {
            i5 += i;
        }
        int i7 = i5 - i;
        while (m2796a(i7, i6) && this.f21738b.m2519a(i7, i6) == z) {
            i6 += i2;
        }
        return new C5361a(i7, i6 - i2);
    }

    /* renamed from: a */
    private boolean m2789a(ResultPoint onVar) {
        return m2796a(MathUtils.m2531a(onVar.f22726c), MathUtils.m2531a(onVar.f22727d));
    }

    /* renamed from: b */
    private static float m2781b(C5361a aVar, C5361a aVar2) {
        return MathUtils.m2529a(aVar.f21744a, aVar.f21745b, aVar2.f21744a, aVar2.f21745b);
    }

    /* renamed from: a */
    private static float m2788a(ResultPoint onVar, ResultPoint onVar2) {
        return MathUtils.m2530a(onVar.f22726c, onVar.f22727d, onVar2.f22726c, onVar2.f22727d);
    }
}
