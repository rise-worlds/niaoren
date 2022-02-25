package p110z1;

import com.tencent.smtt.sdk.TbsListener;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* renamed from: z1.la */
/* loaded from: classes3.dex */
public final class RSS14Reader extends AbstractRSSReader {

    /* renamed from: g */
    private static final int[] f22292g = {1, 10, 34, 70, TbsListener.ErrorCode.PV_UPLOAD_ERROR};

    /* renamed from: h */
    private static final int[] f22293h = {4, 20, 48, 81};

    /* renamed from: i */
    private static final int[] f22294i = {0, TbsListener.ErrorCode.STARTDOWNLOAD_2, 961, 2015, 2715};

    /* renamed from: j */
    private static final int[] f22295j = {0, 336, 1036, 1516};

    /* renamed from: k */
    private static final int[] f22296k = {8, 6, 4, 3, 1};

    /* renamed from: l */
    private static final int[] f22297l = {2, 4, 6, 8};

    /* renamed from: m */
    private static final int[][] f22298m = {new int[]{3, 8, 2, 1}, new int[]{3, 5, 5, 1}, new int[]{3, 3, 7, 1}, new int[]{3, 1, 9, 1}, new int[]{2, 7, 4, 1}, new int[]{2, 5, 6, 1}, new int[]{2, 3, 8, 1}, new int[]{1, 5, 7, 1}, new int[]{1, 3, 9, 1}};

    /* renamed from: n */
    private final List<C5390kz> f22299n = new ArrayList();

    /* renamed from: o */
    private final List<C5390kz> f22300o = new ArrayList();

    @Override // p110z1.OneDReader
    /* renamed from: a */
    public final C5422ol mo2072a(int i, BitArray huVar, Map<DecodeHintType, ?> map) throws NotFoundException {
        m2134a(this.f22299n, m2130a(huVar, false, i, map));
        huVar.m2542c();
        m2134a(this.f22300o, m2130a(huVar, true, i, map));
        huVar.m2542c();
        for (C5390kz kzVar : this.f22299n) {
            if (kzVar.f22291d > 1) {
                for (C5390kz kzVar2 : this.f22300o) {
                    if (kzVar2.f22291d > 1) {
                        int i2 = (kzVar.f22286b + (kzVar2.f22286b * 16)) % 79;
                        int i3 = (kzVar.f22290c.f22287a * 9) + kzVar2.f22290c.f22287a;
                        if (i3 > 72) {
                            i3--;
                        }
                        if (i3 > 8) {
                            i3--;
                        }
                        if (i2 == i3) {
                            String valueOf = String.valueOf((kzVar.f22285a * 4537077) + kzVar2.f22285a);
                            StringBuilder sb = new StringBuilder(14);
                            for (int length = 13 - valueOf.length(); length > 0; length--) {
                                sb.append('0');
                            }
                            sb.append(valueOf);
                            int i4 = 0;
                            for (int i5 = 0; i5 < 13; i5++) {
                                int charAt = sb.charAt(i5) - '0';
                                if ((i5 & 1) == 0) {
                                    charAt *= 3;
                                }
                                i4 += charAt;
                            }
                            int i6 = 10 - (i4 % 10);
                            if (i6 == 10) {
                                i6 = 0;
                            }
                            sb.append(i6);
                            ResultPoint[] onVarArr = kzVar.f22290c.f22289c;
                            ResultPoint[] onVarArr2 = kzVar2.f22290c.f22289c;
                            return new C5422ol(sb.toString(), null, new ResultPoint[]{onVarArr[0], onVarArr[1], onVarArr2[0], onVarArr2[1]}, BarcodeFormat.RSS_14);
                        }
                    }
                }
                continue;
            }
        }
        throw NotFoundException.m1647a();
    }

    /* renamed from: a */
    private static void m2134a(Collection<C5390kz> collection, C5390kz kzVar) {
        if (kzVar != null) {
            boolean z = false;
            Iterator<C5390kz> it = collection.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                C5390kz next = it.next();
                if (next.f22285a == kzVar.f22285a) {
                    next.f22291d++;
                    z = true;
                    break;
                }
            }
            if (!z) {
                collection.add(kzVar);
            }
        }
    }

    @Override // p110z1.OneDReader, p110z1.Reader
    /* renamed from: a */
    public final void mo1638a() {
        this.f22299n.clear();
        this.f22300o.clear();
    }

    /* renamed from: a */
    private FinderPattern m2133a(BitArray huVar, int i, boolean z, int[] iArr) throws NotFoundException {
        int i2;
        int i3;
        boolean a = huVar.m2551a(iArr[0]);
        int i4 = iArr[0] - 1;
        while (i4 >= 0 && a != huVar.m2551a(i4)) {
            i4--;
        }
        int i5 = i4 + 1;
        int[] iArr2 = this.f22207a;
        System.arraycopy(iArr2, 0, iArr2, 1, iArr2.length - 1);
        iArr2[0] = iArr[0] - i5;
        int a2 = m2237a(iArr2, f22298m);
        int i6 = iArr[1];
        if (z) {
            i3 = (huVar.f21908b - 1) - i5;
            i2 = (huVar.f21908b - 1) - i6;
        } else {
            i2 = i6;
            i3 = i5;
        }
        return new FinderPattern(a2, new int[]{i5, iArr[1]}, i3, i2, i);
    }

    /* renamed from: a */
    private static C5422ol m2129a(C5390kz kzVar, C5390kz kzVar2) {
        String valueOf = String.valueOf((kzVar.f22285a * 4537077) + kzVar2.f22285a);
        StringBuilder sb = new StringBuilder(14);
        for (int length = 13 - valueOf.length(); length > 0; length--) {
            sb.append('0');
        }
        sb.append(valueOf);
        int i = 0;
        for (int i2 = 0; i2 < 13; i2++) {
            int charAt = sb.charAt(i2) - '0';
            if ((i2 & 1) == 0) {
                charAt *= 3;
            }
            i += charAt;
        }
        int i3 = 10 - (i % 10);
        if (i3 == 10) {
            i3 = 0;
        }
        sb.append(i3);
        ResultPoint[] onVarArr = kzVar.f22290c.f22289c;
        ResultPoint[] onVarArr2 = kzVar2.f22290c.f22289c;
        return new C5422ol(sb.toString(), null, new ResultPoint[]{onVarArr[0], onVarArr[1], onVarArr2[0], onVarArr2[1]}, BarcodeFormat.RSS_14);
    }

    /* renamed from: b */
    private static boolean m2127b(C5390kz kzVar, C5390kz kzVar2) {
        int i = (kzVar.f22286b + (kzVar2.f22286b * 16)) % 79;
        int i2 = (kzVar.f22290c.f22287a * 9) + kzVar2.f22290c.f22287a;
        if (i2 > 72) {
            i2--;
        }
        if (i2 > 8) {
            i2--;
        }
        return i == i2;
    }

    /* renamed from: a */
    private C5390kz m2130a(BitArray huVar, boolean z, int i, Map<DecodeHintType, ?> map) {
        int i2;
        int i3;
        try {
            int[] iArr = this.f22207a;
            iArr[0] = 0;
            iArr[1] = 0;
            iArr[2] = 0;
            iArr[3] = 0;
            int i4 = huVar.f21908b;
            int i5 = 0;
            boolean z2 = false;
            while (i5 < i4) {
                z2 = !huVar.m2551a(i5);
                if (z == z2) {
                    break;
                }
                i5++;
            }
            int i6 = i5;
            int i7 = 0;
            while (i5 < i4) {
                if (huVar.m2551a(i5) != z2) {
                    iArr[i7] = iArr[i7] + 1;
                } else {
                    if (i7 != 3) {
                        i7++;
                    } else if (m2239a(iArr)) {
                        int[] iArr2 = {i6, i5};
                        boolean a = huVar.m2551a(iArr2[0]);
                        int i8 = iArr2[0] - 1;
                        while (i8 >= 0 && a != huVar.m2551a(i8)) {
                            i8--;
                        }
                        int i9 = i8 + 1;
                        int[] iArr3 = this.f22207a;
                        System.arraycopy(iArr3, 0, iArr3, 1, iArr3.length - 1);
                        iArr3[0] = iArr2[0] - i9;
                        int a2 = m2237a(iArr3, f22298m);
                        int i10 = iArr2[1];
                        if (z) {
                            i3 = (huVar.f21908b - 1) - i9;
                            i2 = (huVar.f21908b - 1) - i10;
                        } else {
                            i2 = i10;
                            i3 = i9;
                        }
                        FinderPattern kyVar = new FinderPattern(a2, new int[]{i9, iArr2[1]}, i3, i2, i);
                        if ((map == null ? null : (ResultPointCallback) map.get(DecodeHintType.NEED_RESULT_POINT_CALLBACK)) != null) {
                            float f = (iArr2[0] + iArr2[1]) / 2.0f;
                            if (z) {
                                f = (huVar.f21908b - 1) - f;
                            }
                            new ResultPoint(f, i);
                        }
                        DataCharacter a3 = m2132a(huVar, kyVar, true);
                        DataCharacter a4 = m2132a(huVar, kyVar, false);
                        return new C5390kz((a3.f22285a * 1597) + a4.f22285a, a3.f22286b + (a4.f22286b * 4), kyVar);
                    } else {
                        i6 += iArr[0] + iArr[1];
                        iArr[0] = iArr[2];
                        iArr[1] = iArr[3];
                        iArr[2] = 0;
                        iArr[3] = 0;
                        i7--;
                    }
                    iArr[i7] = 1;
                    z2 = !z2;
                }
                i5++;
            }
            throw NotFoundException.m1647a();
        } catch (NotFoundException unused) {
            return null;
        }
    }

    /* renamed from: a */
    private DataCharacter m2132a(BitArray huVar, FinderPattern kyVar, boolean z) throws NotFoundException {
        boolean z2;
        boolean z3;
        boolean z4;
        boolean z5;
        int[] iArr = this.f22208b;
        for (int i = 0; i < iArr.length; i++) {
            iArr[i] = 0;
        }
        if (z) {
            m2087b(huVar, kyVar.f22288b[0], iArr);
        } else {
            m2090a(huVar, kyVar.f22288b[1] + 1, iArr);
            int i2 = 0;
            for (int length = iArr.length - 1; i2 < length; length--) {
                int i3 = iArr[i2];
                iArr[i2] = iArr[length];
                iArr[length] = i3;
                i2++;
            }
        }
        int i4 = z ? 16 : 15;
        float a = MathUtils.m2528a(iArr) / i4;
        int[] iArr2 = this.f22211e;
        int[] iArr3 = this.f22212f;
        float[] fArr = this.f22209c;
        float[] fArr2 = this.f22210d;
        for (int i5 = 0; i5 < iArr.length; i5++) {
            float f = iArr[i5] / a;
            int i6 = (int) (0.5f + f);
            if (i6 <= 0) {
                i6 = 1;
            } else if (i6 > 8) {
                i6 = 8;
            }
            int i7 = i5 / 2;
            if ((i5 & 1) == 0) {
                iArr2[i7] = i6;
                fArr[i7] = f - i6;
            } else {
                iArr3[i7] = i6;
                fArr2[i7] = f - i6;
            }
        }
        int a2 = MathUtils.m2528a(this.f22211e);
        int a3 = MathUtils.m2528a(this.f22212f);
        if (z) {
            if (a2 > 12) {
                z5 = false;
                z4 = true;
            } else if (a2 < 4) {
                z5 = true;
                z4 = false;
            } else {
                z5 = false;
                z4 = false;
            }
            if (a3 > 12) {
                z3 = false;
                z2 = true;
            } else if (a3 < 4) {
                z3 = true;
                z2 = false;
            } else {
                z3 = false;
                z2 = false;
            }
        } else {
            if (a2 > 11) {
                z5 = false;
                z4 = true;
            } else if (a2 < 5) {
                z5 = true;
                z4 = false;
            } else {
                z5 = false;
                z4 = false;
            }
            if (a3 > 10) {
                z3 = false;
                z2 = true;
            } else if (a3 < 4) {
                z3 = true;
                z2 = false;
            } else {
                z3 = false;
                z2 = false;
            }
        }
        int i8 = (a2 + a3) - i4;
        boolean z6 = (a2 & 1) == z;
        boolean z7 = (a3 & 1) == 1;
        if (i8 == 1) {
            if (z6) {
                if (!z7) {
                    z4 = true;
                } else {
                    throw NotFoundException.m1647a();
                }
            } else if (z7) {
                z2 = true;
            } else {
                throw NotFoundException.m1647a();
            }
        } else if (i8 == -1) {
            if (z6) {
                if (!z7) {
                    z5 = true;
                } else {
                    throw NotFoundException.m1647a();
                }
            } else if (z7) {
                z3 = true;
            } else {
                throw NotFoundException.m1647a();
            }
        } else if (i8 != 0) {
            throw NotFoundException.m1647a();
        } else if (z6) {
            if (!z7) {
                throw NotFoundException.m1647a();
            } else if (a2 < a3) {
                z5 = true;
                z2 = true;
            } else {
                z4 = true;
                z3 = true;
            }
        } else if (z7) {
            throw NotFoundException.m1647a();
        }
        if (z5) {
            if (!z4) {
                m2238a(this.f22211e, this.f22209c);
            } else {
                throw NotFoundException.m1647a();
            }
        }
        if (z4) {
            m2234b(this.f22211e, this.f22209c);
        }
        if (z3) {
            if (!z2) {
                m2238a(this.f22212f, this.f22209c);
            } else {
                throw NotFoundException.m1647a();
            }
        }
        if (z2) {
            m2234b(this.f22212f, this.f22210d);
        }
        int i9 = 0;
        int i10 = 0;
        for (int length2 = iArr2.length - 1; length2 >= 0; length2--) {
            i9 = (i9 * 9) + iArr2[length2];
            i10 += iArr2[length2];
        }
        int i11 = 0;
        int i12 = 0;
        for (int length3 = iArr3.length - 1; length3 >= 0; length3--) {
            i11 = (i11 * 9) + iArr3[length3];
            i12 += iArr3[length3];
        }
        int i13 = i9 + (i11 * 3);
        if (z) {
            if ((i10 & 1) != 0 || i10 > 12 || i10 < 4) {
                throw NotFoundException.m1647a();
            }
            int i14 = (12 - i10) / 2;
            int i15 = f22296k[i14];
            return new DataCharacter((RSSUtils.m2125a(iArr2, i15, false) * f22292g[i14]) + RSSUtils.m2125a(iArr3, 9 - i15, true) + f22294i[i14], i13);
        } else if ((i12 & 1) != 0 || i12 > 10 || i12 < 4) {
            throw NotFoundException.m1647a();
        } else {
            int i16 = (10 - i12) / 2;
            int i17 = f22297l[i16];
            return new DataCharacter((RSSUtils.m2125a(iArr3, 9 - i17, false) * f22293h[i16]) + RSSUtils.m2125a(iArr2, i17, true) + f22295j[i16], i13);
        }
    }

    /* renamed from: a */
    private int[] m2131a(BitArray huVar, boolean z) throws NotFoundException {
        int[] iArr = this.f22207a;
        iArr[0] = 0;
        iArr[1] = 0;
        iArr[2] = 0;
        iArr[3] = 0;
        int i = huVar.f21908b;
        int i2 = 0;
        boolean z2 = false;
        while (i2 < i) {
            z2 = !huVar.m2551a(i2);
            if (z == z2) {
                break;
            }
            i2++;
        }
        int i3 = i2;
        int i4 = 0;
        while (i2 < i) {
            if (huVar.m2551a(i2) != z2) {
                iArr[i4] = iArr[i4] + 1;
            } else {
                if (i4 != 3) {
                    i4++;
                } else if (m2239a(iArr)) {
                    return new int[]{i3, i2};
                } else {
                    i3 += iArr[0] + iArr[1];
                    iArr[0] = iArr[2];
                    iArr[1] = iArr[3];
                    iArr[2] = 0;
                    iArr[3] = 0;
                    i4--;
                }
                iArr[i4] = 1;
                z2 = !z2;
            }
            i2++;
        }
        throw NotFoundException.m1647a();
    }

    /* renamed from: a */
    private void m2128a(boolean z, int i) throws NotFoundException {
        boolean z2;
        boolean z3;
        boolean z4;
        boolean z5;
        boolean z6;
        boolean z7;
        int a = MathUtils.m2528a(this.f22211e);
        int a2 = MathUtils.m2528a(this.f22212f);
        boolean z8 = false;
        if (z) {
            if (a > 12) {
                z7 = true;
                z6 = false;
            } else if (a < 4) {
                z7 = false;
                z6 = true;
            } else {
                z7 = false;
                z6 = false;
            }
            if (a2 > 12) {
                z4 = z7;
                z3 = z6;
                z5 = false;
                z2 = true;
            } else if (a2 < 4) {
                z4 = z7;
                z3 = z6;
                z5 = true;
                z2 = false;
            } else {
                z4 = z7;
                z3 = z6;
                z5 = false;
                z2 = false;
            }
        } else {
            if (a > 11) {
                z4 = true;
                z3 = false;
            } else if (a < 5) {
                z4 = false;
                z3 = true;
            } else {
                z4 = false;
                z3 = false;
            }
            if (a2 > 10) {
                z5 = false;
                z2 = true;
            } else if (a2 < 4) {
                z5 = true;
                z2 = false;
            } else {
                z5 = false;
                z2 = false;
            }
        }
        int i2 = (a + a2) - i;
        boolean z9 = (a & 1) == z;
        if ((a2 & 1) == 1) {
            z8 = true;
        }
        if (i2 == 1) {
            if (z9) {
                if (!z8) {
                    z4 = true;
                } else {
                    throw NotFoundException.m1647a();
                }
            } else if (z8) {
                z2 = true;
            } else {
                throw NotFoundException.m1647a();
            }
        } else if (i2 == -1) {
            if (z9) {
                if (!z8) {
                    z3 = true;
                } else {
                    throw NotFoundException.m1647a();
                }
            } else if (z8) {
                z5 = true;
            } else {
                throw NotFoundException.m1647a();
            }
        } else if (i2 != 0) {
            throw NotFoundException.m1647a();
        } else if (z9) {
            if (!z8) {
                throw NotFoundException.m1647a();
            } else if (a < a2) {
                z3 = true;
                z2 = true;
            } else {
                z5 = true;
                z4 = true;
            }
        } else if (z8) {
            throw NotFoundException.m1647a();
        }
        if (z3) {
            if (!z4) {
                m2238a(this.f22211e, this.f22209c);
            } else {
                throw NotFoundException.m1647a();
            }
        }
        if (z4) {
            m2234b(this.f22211e, this.f22209c);
        }
        if (z5) {
            if (!z2) {
                m2238a(this.f22212f, this.f22209c);
            } else {
                throw NotFoundException.m1647a();
            }
        }
        if (z2) {
            m2234b(this.f22212f, this.f22210d);
        }
    }
}
