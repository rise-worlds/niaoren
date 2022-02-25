package p110z1;

import java.util.Map;

/* compiled from: Detector.java */
/* renamed from: z1.nt */
/* loaded from: classes3.dex */
public class C5414nt {

    /* renamed from: a */
    protected final BitMatrix f22641a;

    /* renamed from: b */
    private ResultPointCallback f22642b;

    public C5414nt(BitMatrix hyVar) {
        this.f22641a = hyVar;
    }

    /* renamed from: a */
    private BitMatrix m1787a() {
        return this.f22641a;
    }

    /* renamed from: b */
    private ResultPointCallback m1777b() {
        return this.f22642b;
    }

    /* renamed from: c */
    private DetectorResult m1775c() throws NotFoundException, FormatException {
        return m1784a((Map<DecodeHintType, ?>) null);
    }

    /* renamed from: a */
    public final DetectorResult m1784a(Map<DecodeHintType, ?> map) throws NotFoundException, FormatException {
        this.f22642b = map == null ? null : (ResultPointCallback) map.get(DecodeHintType.NEED_RESULT_POINT_CALLBACK);
        return m1782a(new FinderPatternFinder(this.f22641a, this.f22642b).m1767a(map));
    }

    /* renamed from: a */
    private static BitMatrix m1783a(BitMatrix hyVar, PerspectiveTransform imVar, int i) throws NotFoundException {
        return GridSampler.m2440a().mo2438a(hyVar, i, i, imVar);
    }

    /* renamed from: a */
    private static int m1779a(ResultPoint onVar, ResultPoint onVar2, ResultPoint onVar3, float f) throws NotFoundException {
        int a = ((MathUtils.m2531a(ResultPoint.m1624a(onVar, onVar2) / f) + MathUtils.m2531a(ResultPoint.m1624a(onVar, onVar3) / f)) / 2) + 7;
        int i = a & 3;
        if (i == 0) {
            return a + 1;
        }
        switch (i) {
            case 2:
                return a - 1;
            case 3:
                throw NotFoundException.m1647a();
            default:
                return a;
        }
    }

    /* renamed from: a */
    private float m1780a(ResultPoint onVar, ResultPoint onVar2, ResultPoint onVar3) {
        return (m1781a(onVar, onVar2) + m1781a(onVar, onVar3)) / 2.0f;
    }

    /* renamed from: a */
    private float m1785a(int i, int i2, int i3, int i4) {
        float f;
        int i5;
        float f2;
        float b = m1776b(i, i2, i3, i4);
        int i6 = i - (i3 - i);
        int i7 = 0;
        if (i6 < 0) {
            f = i / (i - i6);
            i5 = 0;
        } else if (i6 >= this.f22641a.f21920a) {
            f = ((this.f22641a.f21920a - 1) - i) / (i6 - i);
            i5 = this.f22641a.f21920a - 1;
        } else {
            i5 = i6;
            f = 1.0f;
        }
        float f3 = i2;
        int i8 = (int) (f3 - ((i4 - i2) * f));
        if (i8 < 0) {
            f2 = f3 / (i2 - i8);
        } else if (i8 >= this.f22641a.f21921b) {
            f2 = ((this.f22641a.f21921b - 1) - i2) / (i8 - i2);
            i7 = this.f22641a.f21921b - 1;
        } else {
            i7 = i8;
            f2 = 1.0f;
        }
        return (b + m1776b(i, i2, (int) (i + ((i5 - i) * f2)), i7)) - 1.0f;
    }

    /* renamed from: b */
    private float m1776b(int i, int i2, int i3, int i4) {
        int i5;
        int i6;
        int i7;
        int i8;
        int i9;
        int i10;
        C5414nt ntVar;
        boolean z;
        int i11 = 1;
        boolean z2 = Math.abs(i4 - i2) > Math.abs(i3 - i);
        if (z2) {
            i5 = i;
            i7 = i2;
            i6 = i3;
            i8 = i4;
        } else {
            i7 = i;
            i5 = i2;
            i8 = i3;
            i6 = i4;
        }
        int abs = Math.abs(i8 - i7);
        int abs2 = Math.abs(i6 - i5);
        int i12 = (-abs) / 2;
        int i13 = -1;
        int i14 = i7 < i8 ? 1 : -1;
        if (i5 < i6) {
            i13 = 1;
        }
        int i15 = i8 + i14;
        int i16 = i5;
        int i17 = i12;
        int i18 = 0;
        int i19 = i7;
        while (true) {
            if (i19 == i15) {
                i9 = i15;
                i10 = 2;
                break;
            }
            int i20 = z2 ? i16 : i19;
            int i21 = z2 ? i19 : i16;
            if (i18 == i11) {
                ntVar = this;
                z2 = z2;
                i9 = i15;
                z = true;
            } else {
                ntVar = this;
                z2 = z2;
                i9 = i15;
                z = false;
            }
            if (z == ntVar.f22641a.m2519a(i20, i21)) {
                if (i18 == 2) {
                    return MathUtils.m2529a(i19, i16, i7, i5);
                }
                i18++;
            }
            i17 += abs2;
            if (i17 > 0) {
                if (i16 == i6) {
                    i10 = 2;
                    break;
                }
                i16 += i13;
                i17 -= abs;
            }
            i19 += i14;
            i15 = i9;
            i11 = 1;
        }
        if (i18 == i10) {
            return MathUtils.m2529a(i9, i6, i7, i5);
        }
        return Float.NaN;
    }

    /* renamed from: a */
    private AlignmentPattern m1786a(float f, int i, int i2, float f2) throws NotFoundException {
        AlignmentPattern a;
        AlignmentPattern a2;
        int i3 = (int) (f2 * f);
        int max = Math.max(0, i - i3);
        int min = Math.min(this.f22641a.f21920a - 1, i + i3) - max;
        float f3 = 3.0f * f;
        if (min >= f3) {
            int max2 = Math.max(0, i2 - i3);
            int min2 = Math.min(this.f22641a.f21921b - 1, i2 + i3) - max2;
            if (min2 >= f3) {
                AlignmentPatternFinder nsVar = new AlignmentPatternFinder(this.f22641a, max, max2, min, min2, f, this.f22642b);
                int i4 = nsVar.f22634c;
                int i5 = nsVar.f22637f;
                int i6 = nsVar.f22636e + i4;
                int i7 = nsVar.f22635d + (i5 / 2);
                int[] iArr = new int[3];
                for (int i8 = 0; i8 < i5; i8++) {
                    int i9 = ((i8 & 1) == 0 ? (i8 + 1) / 2 : -((i8 + 1) / 2)) + i7;
                    iArr[0] = 0;
                    iArr[1] = 0;
                    iArr[2] = 0;
                    int i10 = i4;
                    while (i10 < i6 && !nsVar.f22632a.m2519a(i10, i9)) {
                        i10++;
                    }
                    int i11 = 0;
                    while (i10 < i6) {
                        if (!nsVar.f22632a.m2519a(i10, i9)) {
                            if (i11 == 1) {
                                i11++;
                            }
                            iArr[i11] = iArr[i11] + 1;
                        } else if (i11 == 1) {
                            iArr[1] = iArr[1] + 1;
                        } else if (i11 != 2) {
                            i11++;
                            iArr[i11] = iArr[i11] + 1;
                        } else if (nsVar.m1790a(iArr) && (a2 = nsVar.m1788a(iArr, i9, i10)) != null) {
                            return a2;
                        } else {
                            iArr[0] = iArr[2];
                            iArr[1] = 1;
                            iArr[2] = 0;
                            i11 = 1;
                        }
                        i10++;
                    }
                    if (nsVar.m1790a(iArr) && (a = nsVar.m1788a(iArr, i9, i6)) != null) {
                        return a;
                    }
                }
                if (!nsVar.f22633b.isEmpty()) {
                    return nsVar.f22633b.get(0);
                }
                throw NotFoundException.m1647a();
            }
            throw NotFoundException.m1647a();
        }
        throw NotFoundException.m1647a();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:82:0x01eb  */
    /* JADX WARN: Removed duplicated region for block: B:83:0x01f8  */
    /* JADX WARN: Removed duplicated region for block: B:86:0x0242  */
    /* JADX WARN: Removed duplicated region for block: B:87:0x024f  */
    /* JADX WARN: Type inference failed for: r19v5 */
    /* JADX WARN: Type inference failed for: r5v17 */
    /* JADX WARN: Type inference failed for: r5v18 */
    /* JADX WARN: Type inference failed for: r5v21 */
    /* JADX WARN: Type inference failed for: r5v33 */
    /* JADX WARN: Type inference failed for: r5v34 */
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final p110z1.DetectorResult m1782a(p110z1.FinderPatternInfo r42) throws p110z1.NotFoundException, p110z1.FormatException {
        /*
            Method dump skipped, instructions count: 626
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: p110z1.C5414nt.m1782a(z1.nw):z1.ii");
    }

    /* renamed from: a */
    private static PerspectiveTransform m1778a(ResultPoint onVar, ResultPoint onVar2, ResultPoint onVar3, ResultPoint onVar4, int i) {
        float f;
        float f2;
        float f3;
        float f4 = i - 3.5f;
        if (onVar4 != null) {
            float f5 = onVar4.f22726c;
            f3 = f4 - 3.0f;
            f = onVar4.f22727d;
            f2 = f5;
        } else {
            f2 = (onVar2.f22726c - onVar.f22726c) + onVar3.f22726c;
            f = (onVar2.f22727d - onVar.f22727d) + onVar3.f22727d;
            f3 = f4;
        }
        return PerspectiveTransform.m2427a(3.5f, 3.5f, f4, 3.5f, f3, f3, 3.5f, f4, onVar.f22726c, onVar.f22727d, onVar2.f22726c, onVar2.f22727d, f2, f, onVar3.f22726c, onVar3.f22727d);
    }

    /* renamed from: a */
    private float m1781a(ResultPoint onVar, ResultPoint onVar2) {
        float a = m1785a((int) onVar.f22726c, (int) onVar.f22727d, (int) onVar2.f22726c, (int) onVar2.f22727d);
        float a2 = m1785a((int) onVar2.f22726c, (int) onVar2.f22727d, (int) onVar.f22726c, (int) onVar.f22727d);
        return Float.isNaN(a) ? a2 / 7.0f : Float.isNaN(a2) ? a / 7.0f : (a + a2) / 14.0f;
    }
}
