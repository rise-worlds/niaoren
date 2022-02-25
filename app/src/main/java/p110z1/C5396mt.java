package p110z1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* compiled from: Detector.java */
/* renamed from: z1.mt */
/* loaded from: classes3.dex */
public final class C5396mt {

    /* renamed from: c */
    private static final float f22498c = 0.42f;

    /* renamed from: d */
    private static final float f22499d = 0.8f;

    /* renamed from: g */
    private static final int f22502g = 3;

    /* renamed from: h */
    private static final int f22503h = 5;

    /* renamed from: i */
    private static final int f22504i = 25;

    /* renamed from: j */
    private static final int f22505j = 5;

    /* renamed from: k */
    private static final int f22506k = 10;

    /* renamed from: a */
    private static final int[] f22496a = {0, 4, 1, 5};

    /* renamed from: b */
    private static final int[] f22497b = {6, 2, 7, 3};

    /* renamed from: e */
    private static final int[] f22500e = {8, 1, 1, 1, 1, 1, 1, 3};

    /* renamed from: f */
    private static final int[] f22501f = {7, 1, 1, 3, 1, 1, 1, 2, 1};

    private C5396mt() {
    }

    /* renamed from: a */
    public static PDF417DetectorResult m1927a(BinaryBitmap htVar, boolean z) throws NotFoundException {
        BitMatrix c = htVar.m2557c();
        List<ResultPoint[]> a = m1923a(z, c);
        if (a.isEmpty()) {
            c = c.clone();
            int i = c.f21920a;
            int i2 = c.f21921b;
            BitArray huVar = new BitArray(i);
            BitArray huVar2 = new BitArray(i);
            for (int i3 = 0; i3 < (i2 + 1) / 2; i3++) {
                huVar = c.m2517a(i3, huVar);
                int i4 = (i2 - 1) - i3;
                huVar2 = c.m2517a(i4, huVar2);
                huVar.m2542c();
                huVar2.m2542c();
                c.m2510b(i3, huVar2);
                c.m2510b(i4, huVar);
            }
            a = m1923a(z, c);
        }
        return new PDF417DetectorResult(c, a);
    }

    /* renamed from: a */
    private static List<ResultPoint[]> m1923a(boolean z, BitMatrix hyVar) {
        int i;
        int i2;
        ArrayList<ResultPoint[]> arrayList = new ArrayList();
        int i3 = 0;
        int i4 = 0;
        boolean z2 = false;
        while (i3 < hyVar.f21921b) {
            int i5 = hyVar.f21921b;
            int i6 = hyVar.f21920a;
            ResultPoint[] onVarArr = new ResultPoint[8];
            m1921a(onVarArr, m1925a(hyVar, i5, i6, i3, i4, f22500e), f22496a);
            if (onVarArr[4] != null) {
                i = (int) onVarArr[4].f22726c;
                i2 = (int) onVarArr[4].f22727d;
            } else {
                i2 = i3;
                i = i4;
            }
            m1921a(onVarArr, m1925a(hyVar, i5, i6, i2, i, f22501f), f22497b);
            if (onVarArr[0] == null && onVarArr[3] == null) {
                if (!z2) {
                    break;
                }
                for (ResultPoint[] onVarArr2 : arrayList) {
                    if (onVarArr2[1] != null) {
                        i3 = (int) Math.max(i3, onVarArr2[1].f22727d);
                    }
                    if (onVarArr2[3] != null) {
                        i3 = Math.max(i3, (int) onVarArr2[3].f22727d);
                    }
                }
                i3 += 5;
                i4 = 0;
                z2 = false;
            } else {
                arrayList.add(onVarArr);
                if (!z) {
                    break;
                } else if (onVarArr[2] != null) {
                    i4 = (int) onVarArr[2].f22726c;
                    i3 = (int) onVarArr[2].f22727d;
                    z2 = true;
                } else {
                    i4 = (int) onVarArr[4].f22726c;
                    i3 = (int) onVarArr[4].f22727d;
                    z2 = true;
                }
            }
        }
        return arrayList;
    }

    /* renamed from: a */
    private static void m1921a(ResultPoint[] onVarArr, ResultPoint[] onVarArr2, int[] iArr) {
        for (int i = 0; i < iArr.length; i++) {
            onVarArr[iArr[i]] = onVarArr2[i];
        }
    }

    /* renamed from: a */
    private static ResultPoint[] m1925a(BitMatrix hyVar, int i, int i2, int i3, int i4, int[] iArr) {
        boolean z;
        int i5;
        ResultPoint[] onVarArr = new ResultPoint[4];
        int[] iArr2 = new int[iArr.length];
        int i6 = i3;
        while (true) {
            if (i6 >= i) {
                z = false;
                break;
            }
            int[] a = m1924a(hyVar, i4, i6, i2, iArr, iArr2);
            if (a != null) {
                int[] iArr3 = a;
                while (true) {
                    if (i6 <= 0) {
                        break;
                    }
                    i6--;
                    int[] a2 = m1924a(hyVar, i4, i6, i2, iArr, iArr2);
                    if (a2 == null) {
                        i6++;
                        break;
                    }
                    iArr3 = a2;
                }
                float f = i6;
                onVarArr[0] = new ResultPoint(iArr3[0], f);
                onVarArr[1] = new ResultPoint(iArr3[1], f);
                z = true;
            } else {
                i6 += 5;
            }
        }
        int i7 = i6 + 1;
        if (z) {
            int[] iArr4 = {(int) onVarArr[0].f22726c, (int) onVarArr[1].f22726c};
            int i8 = i7;
            int i9 = 0;
            while (true) {
                if (i8 >= i) {
                    i5 = i9;
                    break;
                }
                i5 = i9;
                int[] a3 = m1924a(hyVar, iArr4[0], i8, i2, iArr, iArr2);
                if (a3 == null || Math.abs(iArr4[0] - a3[0]) >= 5 || Math.abs(iArr4[1] - a3[1]) >= 5) {
                    if (i5 > 25) {
                        break;
                    }
                    i9 = i5 + 1;
                } else {
                    iArr4 = a3;
                    i9 = 0;
                }
                i8++;
            }
            i7 = i8 - (i5 + 1);
            float f2 = i7;
            onVarArr[2] = new ResultPoint(iArr4[0], f2);
            onVarArr[3] = new ResultPoint(iArr4[1], f2);
        }
        if (i7 - i6 < 10) {
            Arrays.fill(onVarArr, (Object) null);
        }
        return onVarArr;
    }

    /* renamed from: a */
    private static int[] m1924a(BitMatrix hyVar, int i, int i2, int i3, int[] iArr, int[] iArr2) {
        Arrays.fill(iArr2, 0, iArr2.length, 0);
        int i4 = 0;
        while (hyVar.m2519a(i, i2) && i > 0) {
            i4++;
            if (i4 >= 3) {
                break;
            }
            i--;
        }
        int length = iArr.length;
        int i5 = i;
        int i6 = 0;
        boolean z = false;
        while (i < i3) {
            if (hyVar.m2519a(i, i2) != z) {
                iArr2[i6] = iArr2[i6] + 1;
            } else {
                if (i6 != length - 1) {
                    i6++;
                } else if (m1922a(iArr2, iArr) < f22498c) {
                    return new int[]{i5, i};
                } else {
                    i5 += iArr2[0] + iArr2[1];
                    int i7 = i6 - 1;
                    System.arraycopy(iArr2, 2, iArr2, 0, i7);
                    iArr2[i7] = 0;
                    iArr2[i6] = 0;
                    i6--;
                }
                iArr2[i6] = 1;
                z = !z;
            }
            i++;
        }
        if (i6 != length - 1 || m1922a(iArr2, iArr) >= f22498c) {
            return null;
        }
        return new int[]{i5, i - 1};
    }

    /* renamed from: a */
    private static float m1922a(int[] iArr, int[] iArr2) {
        int length = iArr.length;
        int i = 0;
        int i2 = 0;
        for (int i3 = 0; i3 < length; i3++) {
            i += iArr[i3];
            i2 += iArr2[i3];
        }
        if (i < i2) {
            return Float.POSITIVE_INFINITY;
        }
        float f = i;
        float f2 = f / i2;
        float f3 = f22499d * f2;
        float f4 = 0.0f;
        for (int i4 = 0; i4 < length; i4++) {
            float f5 = iArr2[i4] * f2;
            float f6 = iArr[i4];
            float f7 = f6 > f5 ? f6 - f5 : f5 - f6;
            if (f7 > f3) {
                return Float.POSITIVE_INFINITY;
            }
            f4 += f7;
        }
        return f4 / f;
    }

    /* renamed from: a */
    private static ResultPoint[] m1926a(BitMatrix hyVar, int i, int i2) {
        int i3;
        int i4;
        int i5 = hyVar.f21921b;
        int i6 = hyVar.f21920a;
        ResultPoint[] onVarArr = new ResultPoint[8];
        m1921a(onVarArr, m1925a(hyVar, i5, i6, i, i2, f22500e), f22496a);
        if (onVarArr[4] != null) {
            i3 = (int) onVarArr[4].f22726c;
            i4 = (int) onVarArr[4].f22727d;
        } else {
            i4 = i;
            i3 = i2;
        }
        m1921a(onVarArr, m1925a(hyVar, i5, i6, i4, i3, f22501f), f22497b);
        return onVarArr;
    }
}
