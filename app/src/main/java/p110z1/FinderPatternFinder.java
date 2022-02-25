package p110z1;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

/* renamed from: z1.nv */
/* loaded from: classes3.dex */
public class FinderPatternFinder {

    /* renamed from: a */
    protected static final int f22645a = 3;

    /* renamed from: b */
    protected static final int f22646b = 97;

    /* renamed from: e */
    private static final int f22647e = 2;

    /* renamed from: c */
    protected final BitMatrix f22648c;

    /* renamed from: d */
    protected final List<C5415nu> f22649d;

    /* renamed from: f */
    private boolean f22650f;

    /* renamed from: g */
    private final int[] f22651g;

    /* renamed from: h */
    private final ResultPointCallback f22652h;

    public FinderPatternFinder(BitMatrix hyVar) {
        this(hyVar, null);
    }

    public FinderPatternFinder(BitMatrix hyVar, ResultPointCallback ooVar) {
        this.f22648c = hyVar;
        this.f22649d = new ArrayList();
        this.f22651g = new int[5];
        this.f22652h = ooVar;
    }

    /* renamed from: a */
    private BitMatrix m1770a() {
        return this.f22648c;
    }

    /* renamed from: b */
    private List<C5415nu> m1763b() {
        return this.f22649d;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public final FinderPatternInfo m1767a(Map<DecodeHintType, ?> map) throws NotFoundException {
        float f;
        int i;
        boolean z = map != null && map.containsKey(DecodeHintType.TRY_HARDER);
        int i2 = this.f22648c.f21921b;
        int i3 = this.f22648c.f21920a;
        int i4 = (i2 * 3) / 388;
        if (i4 < 3 || z) {
            i4 = 3;
        }
        int[] iArr = new int[5];
        int i5 = i4 - 1;
        int i6 = i4;
        boolean z2 = false;
        while (i5 < i2 && !z2) {
            m1761b(iArr);
            boolean z3 = z2;
            int i7 = 0;
            int i8 = 0;
            while (i7 < i3) {
                if (this.f22648c.m2519a(i7, i5)) {
                    if ((i8 & 1) == 1) {
                        i8++;
                    }
                    iArr[i8] = iArr[i8] + 1;
                } else if ((i8 & 1) != 0) {
                    iArr[i8] = iArr[i8] + 1;
                } else if (i8 != 4) {
                    i8++;
                    iArr[i8] = iArr[i8] + 1;
                } else if (!m1766a(iArr)) {
                    m1758c(iArr);
                    i8 = 3;
                } else if (m1764a(iArr, i5, i7)) {
                    if (this.f22650f) {
                        z3 = m1755e();
                    } else {
                        if (this.f22649d.size() > 1) {
                            C5415nu nuVar = null;
                            for (C5415nu nuVar2 : this.f22649d) {
                                if (nuVar2.f22644b >= 2) {
                                    if (nuVar != null) {
                                        this.f22650f = true;
                                        i = ((int) (Math.abs(nuVar.f22726c - nuVar2.f22726c) - Math.abs(nuVar.f22727d - nuVar2.f22727d))) / 2;
                                        break;
                                    }
                                    nuVar = nuVar2;
                                }
                            }
                        }
                        i = 0;
                        if (i > iArr[2]) {
                            i5 += (i - iArr[2]) - 2;
                            i7 = i3 - 1;
                        }
                    }
                    m1761b(iArr);
                    i8 = 0;
                    i6 = 2;
                } else {
                    m1758c(iArr);
                    i8 = 3;
                }
                i7++;
            }
            if (!m1766a(iArr) || !m1764a(iArr, i5, i3)) {
                i6 = i6;
                z2 = z3;
            } else {
                int i9 = iArr[0];
                if (this.f22650f) {
                    i6 = i9;
                    z2 = m1755e();
                } else {
                    i6 = i9;
                    z2 = z3;
                }
            }
            i5 += i6;
        }
        int size = this.f22649d.size();
        if (size >= 3) {
            float f2 = 0.0f;
            if (size > 3) {
                float f3 = 0.0f;
                float f4 = 0.0f;
                for (C5415nu nuVar3 : this.f22649d) {
                    float f5 = nuVar3.f22643a;
                    f3 += f5;
                    f4 += f5 * f5;
                }
                float f6 = f3 / size;
                float sqrt = (float) Math.sqrt((f4 / f) - (f6 * f6));
                Collections.sort(this.f22649d, new C5418b(f6, (byte) 0));
                float max = Math.max(0.2f * f6, sqrt);
                int i10 = 0;
                while (i10 < this.f22649d.size() && this.f22649d.size() > 3) {
                    if (Math.abs(this.f22649d.get(i10).f22643a - f6) > max) {
                        this.f22649d.remove(i10);
                        i10--;
                    }
                    i10++;
                }
            }
            if (this.f22649d.size() > 3) {
                for (C5415nu nuVar4 : this.f22649d) {
                    f2 += nuVar4.f22643a;
                }
                Collections.sort(this.f22649d, new C5417a(f2 / this.f22649d.size(), (byte) 0));
                List<C5415nu> list = this.f22649d;
                list.subList(3, list.size()).clear();
            }
            C5415nu[] nuVarArr = {this.f22649d.get(0), this.f22649d.get(1), this.f22649d.get(2)};
            ResultPoint.m1622a(nuVarArr);
            return new FinderPatternInfo(nuVarArr);
        }
        throw NotFoundException.m1647a();
    }

    /* renamed from: a */
    private static float m1765a(int[] iArr, int i) {
        return ((i - iArr[4]) - iArr[3]) - (iArr[2] / 2.0f);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: a */
    public static boolean m1766a(int[] iArr) {
        int i = 0;
        for (int i2 = 0; i2 < 5; i2++) {
            int i3 = iArr[i2];
            if (i3 == 0) {
                return false;
            }
            i += i3;
        }
        if (i < 7) {
            return false;
        }
        float f = i / 7.0f;
        float f2 = f / 2.0f;
        return Math.abs(f - ((float) iArr[0])) < f2 && Math.abs(f - ((float) iArr[1])) < f2 && Math.abs((f * 3.0f) - ((float) iArr[2])) < 3.0f * f2 && Math.abs(f - ((float) iArr[3])) < f2 && Math.abs(f - ((float) iArr[4])) < f2;
    }

    /* renamed from: d */
    private static boolean m1756d(int[] iArr) {
        int i = 0;
        for (int i2 = 0; i2 < 5; i2++) {
            int i3 = iArr[i2];
            if (i3 == 0) {
                return false;
            }
            i += i3;
        }
        if (i < 7) {
            return false;
        }
        float f = i / 7.0f;
        float f2 = f / 1.333f;
        return Math.abs(f - ((float) iArr[0])) < f2 && Math.abs(f - ((float) iArr[1])) < f2 && Math.abs((f * 3.0f) - ((float) iArr[2])) < 3.0f * f2 && Math.abs(f - ((float) iArr[3])) < f2 && Math.abs(f - ((float) iArr[4])) < f2;
    }

    /* renamed from: c */
    private int[] m1759c() {
        m1761b(this.f22651g);
        return this.f22651g;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: b */
    public static void m1761b(int[] iArr) {
        for (int i = 0; i < iArr.length; i++) {
            iArr[i] = 0;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: c */
    public static void m1758c(int[] iArr) {
        iArr[0] = iArr[2];
        iArr[1] = iArr[3];
        iArr[2] = iArr[4];
        iArr[3] = 1;
        iArr[4] = 0;
    }

    /* renamed from: a */
    private boolean m1769a(int i, int i2) {
        int i3;
        int i4;
        int i5;
        int[] c = m1759c();
        int i6 = 0;
        while (i >= i6 && i2 >= i6 && this.f22648c.m2519a(i2 - i6, i - i6)) {
            c[2] = c[2] + 1;
            i6++;
        }
        if (c[2] == 0) {
            return false;
        }
        while (i >= i6 && i2 >= i6 && !this.f22648c.m2519a(i2 - i6, i - i6)) {
            c[1] = c[1] + 1;
            i6++;
        }
        if (c[1] == 0) {
            return false;
        }
        while (i >= i6 && i2 >= i6 && this.f22648c.m2519a(i2 - i6, i - i6)) {
            c[0] = c[0] + 1;
            i6++;
        }
        if (c[0] == 0) {
            return false;
        }
        int i7 = this.f22648c.f21921b;
        int i8 = this.f22648c.f21920a;
        int i9 = 1;
        while (true) {
            int i10 = i + i9;
            if (i10 >= i7 || (i5 = i2 + i9) >= i8 || !this.f22648c.m2519a(i5, i10)) {
                break;
            }
            c[2] = c[2] + 1;
            i9++;
        }
        while (true) {
            int i11 = i + i9;
            if (i11 >= i7 || (i4 = i2 + i9) >= i8 || this.f22648c.m2519a(i4, i11)) {
                break;
            }
            c[3] = c[3] + 1;
            i9++;
        }
        if (c[3] == 0) {
            return false;
        }
        while (true) {
            int i12 = i + i9;
            if (i12 >= i7 || (i3 = i2 + i9) >= i8 || !this.f22648c.m2519a(i3, i12)) {
                break;
            }
            c[4] = c[4] + 1;
            i9++;
        }
        if (c[4] == 0) {
            return false;
        }
        int i13 = 0;
        int i14 = 0;
        while (true) {
            if (i13 < 5) {
                int i15 = c[i13];
                if (i15 == 0) {
                    break;
                }
                i14 += i15;
                i13++;
            } else if (i14 >= 7) {
                float f = i14 / 7.0f;
                float f2 = f / 1.333f;
                if (Math.abs(f - c[0]) >= f2 || Math.abs(f - c[1]) >= f2 || Math.abs((f * 3.0f) - c[2]) >= 3.0f * f2 || Math.abs(f - c[3]) >= f2 || Math.abs(f - c[4]) >= f2) {
                    break;
                }
                return true;
            }
        }
        return false;
    }

    /* JADX WARN: Code restructure failed: missing block: B:19:0x0038, code lost:
        if (r2[1] <= r13) goto L_0x003c;
     */
    /* JADX WARN: Code restructure failed: missing block: B:21:0x003d, code lost:
        if (r3 < 0) goto L_0x0051;
     */
    /* JADX WARN: Code restructure failed: missing block: B:23:0x0043, code lost:
        if (r0.m2519a(r12, r3) == false) goto L_0x0051;
     */
    /* JADX WARN: Code restructure failed: missing block: B:25:0x0047, code lost:
        if (r2[0] > r13) goto L_0x0051;
     */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x0049, code lost:
        r2[0] = r2[0] + 1;
        r3 = r3 - 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:28:0x0053, code lost:
        if (r2[0] <= r13) goto L_0x0056;
     */
    /* JADX WARN: Code restructure failed: missing block: B:29:0x0055, code lost:
        return Float.NaN;
     */
    /* JADX WARN: Code restructure failed: missing block: B:30:0x0056, code lost:
        r11 = r11 + 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:31:0x0057, code lost:
        if (r11 >= r1) goto L_0x0067;
     */
    /* JADX WARN: Code restructure failed: missing block: B:33:0x005d, code lost:
        if (r0.m2519a(r12, r11) == false) goto L_0x0067;
     */
    /* JADX WARN: Code restructure failed: missing block: B:34:0x005f, code lost:
        r2[2] = r2[2] + 1;
        r11 = r11 + 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:35:0x0067, code lost:
        if (r11 != r1) goto L_0x006a;
     */
    /* JADX WARN: Code restructure failed: missing block: B:36:0x0069, code lost:
        return Float.NaN;
     */
    /* JADX WARN: Code restructure failed: missing block: B:38:0x006b, code lost:
        if (r11 >= r1) goto L_0x007f;
     */
    /* JADX WARN: Code restructure failed: missing block: B:40:0x0071, code lost:
        if (r0.m2519a(r12, r11) != false) goto L_0x007f;
     */
    /* JADX WARN: Code restructure failed: missing block: B:42:0x0075, code lost:
        if (r2[3] >= r13) goto L_0x007f;
     */
    /* JADX WARN: Code restructure failed: missing block: B:43:0x0077, code lost:
        r2[3] = r2[3] + 1;
        r11 = r11 + 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:44:0x007f, code lost:
        if (r11 == r1) goto L_0x00c6;
     */
    /* JADX WARN: Code restructure failed: missing block: B:46:0x0083, code lost:
        if (r2[3] < r13) goto L_0x0086;
     */
    /* JADX WARN: Code restructure failed: missing block: B:48:0x0087, code lost:
        if (r11 >= r1) goto L_0x009b;
     */
    /* JADX WARN: Code restructure failed: missing block: B:50:0x008d, code lost:
        if (r0.m2519a(r12, r11) == false) goto L_0x009b;
     */
    /* JADX WARN: Code restructure failed: missing block: B:52:0x0091, code lost:
        if (r2[4] >= r13) goto L_0x009b;
     */
    /* JADX WARN: Code restructure failed: missing block: B:53:0x0093, code lost:
        r2[4] = r2[4] + 1;
        r11 = r11 + 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:55:0x009d, code lost:
        if (r2[4] < r13) goto L_0x00a0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:56:0x009f, code lost:
        return Float.NaN;
     */
    /* JADX WARN: Code restructure failed: missing block: B:58:0x00b7, code lost:
        if ((java.lang.Math.abs(((((r2[0] + r2[1]) + r2[2]) + r2[3]) + r2[4]) - r14) * 5) < (r14 * 2)) goto L_0x00ba;
     */
    /* JADX WARN: Code restructure failed: missing block: B:59:0x00b9, code lost:
        return Float.NaN;
     */
    /* JADX WARN: Code restructure failed: missing block: B:61:0x00be, code lost:
        if (m1766a(r2) == false) goto L_0x00c5;
     */
    /* JADX WARN: Code restructure failed: missing block: B:63:0x00c4, code lost:
        return m1765a(r2, r11);
     */
    /* JADX WARN: Code restructure failed: missing block: B:64:0x00c5, code lost:
        return Float.NaN;
     */
    /* JADX WARN: Code restructure failed: missing block: B:65:0x00c6, code lost:
        return Float.NaN;
     */
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private float m1768a(int r11, int r12, int r13, int r14) {
        /*
            Method dump skipped, instructions count: 200
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: p110z1.FinderPatternFinder.m1768a(int, int, int, int):float");
    }

    /* JADX WARN: Code restructure failed: missing block: B:19:0x0038, code lost:
        if (r2[1] <= r13) goto L_0x003c;
     */
    /* JADX WARN: Code restructure failed: missing block: B:21:0x003d, code lost:
        if (r3 < 0) goto L_0x0051;
     */
    /* JADX WARN: Code restructure failed: missing block: B:23:0x0043, code lost:
        if (r0.m2519a(r3, r12) == false) goto L_0x0051;
     */
    /* JADX WARN: Code restructure failed: missing block: B:25:0x0047, code lost:
        if (r2[0] > r13) goto L_0x0051;
     */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x0049, code lost:
        r2[0] = r2[0] + 1;
        r3 = r3 - 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:28:0x0053, code lost:
        if (r2[0] <= r13) goto L_0x0056;
     */
    /* JADX WARN: Code restructure failed: missing block: B:29:0x0055, code lost:
        return Float.NaN;
     */
    /* JADX WARN: Code restructure failed: missing block: B:30:0x0056, code lost:
        r11 = r11 + 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:31:0x0057, code lost:
        if (r11 >= r1) goto L_0x0067;
     */
    /* JADX WARN: Code restructure failed: missing block: B:33:0x005d, code lost:
        if (r0.m2519a(r11, r12) == false) goto L_0x0067;
     */
    /* JADX WARN: Code restructure failed: missing block: B:34:0x005f, code lost:
        r2[2] = r2[2] + 1;
        r11 = r11 + 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:35:0x0067, code lost:
        if (r11 != r1) goto L_0x006a;
     */
    /* JADX WARN: Code restructure failed: missing block: B:36:0x0069, code lost:
        return Float.NaN;
     */
    /* JADX WARN: Code restructure failed: missing block: B:38:0x006b, code lost:
        if (r11 >= r1) goto L_0x007f;
     */
    /* JADX WARN: Code restructure failed: missing block: B:40:0x0071, code lost:
        if (r0.m2519a(r11, r12) != false) goto L_0x007f;
     */
    /* JADX WARN: Code restructure failed: missing block: B:42:0x0075, code lost:
        if (r2[3] >= r13) goto L_0x007f;
     */
    /* JADX WARN: Code restructure failed: missing block: B:43:0x0077, code lost:
        r2[3] = r2[3] + 1;
        r11 = r11 + 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:44:0x007f, code lost:
        if (r11 == r1) goto L_0x00c4;
     */
    /* JADX WARN: Code restructure failed: missing block: B:46:0x0083, code lost:
        if (r2[3] < r13) goto L_0x0086;
     */
    /* JADX WARN: Code restructure failed: missing block: B:48:0x0087, code lost:
        if (r11 >= r1) goto L_0x009b;
     */
    /* JADX WARN: Code restructure failed: missing block: B:50:0x008d, code lost:
        if (r0.m2519a(r11, r12) == false) goto L_0x009b;
     */
    /* JADX WARN: Code restructure failed: missing block: B:52:0x0091, code lost:
        if (r2[4] >= r13) goto L_0x009b;
     */
    /* JADX WARN: Code restructure failed: missing block: B:53:0x0093, code lost:
        r2[4] = r2[4] + 1;
        r11 = r11 + 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:55:0x009d, code lost:
        if (r2[4] < r13) goto L_0x00a0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:56:0x009f, code lost:
        return Float.NaN;
     */
    /* JADX WARN: Code restructure failed: missing block: B:58:0x00b5, code lost:
        if ((java.lang.Math.abs(((((r2[0] + r2[1]) + r2[2]) + r2[3]) + r2[4]) - r14) * 5) < r14) goto L_0x00b8;
     */
    /* JADX WARN: Code restructure failed: missing block: B:59:0x00b7, code lost:
        return Float.NaN;
     */
    /* JADX WARN: Code restructure failed: missing block: B:61:0x00bc, code lost:
        if (m1766a(r2) == false) goto L_0x00c3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:63:0x00c2, code lost:
        return m1765a(r2, r11);
     */
    /* JADX WARN: Code restructure failed: missing block: B:64:0x00c3, code lost:
        return Float.NaN;
     */
    /* JADX WARN: Code restructure failed: missing block: B:65:0x00c4, code lost:
        return Float.NaN;
     */
    /* renamed from: b */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private float m1762b(int r11, int r12, int r13, int r14) {
        /*
            Method dump skipped, instructions count: 198
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: p110z1.FinderPatternFinder.m1762b(int, int, int, int):float");
    }

    @Deprecated
    /* renamed from: b */
    private boolean m1760b(int[] iArr, int i, int i2) {
        return m1764a(iArr, i, i2);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Code restructure failed: missing block: B:100:0x0179, code lost:
        if (r12[3] >= r1) goto L_0x0183;
     */
    /* JADX WARN: Code restructure failed: missing block: B:101:0x017b, code lost:
        r12[3] = r12[3] + 1;
        r8 = r8 + 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:102:0x0183, code lost:
        if (r8 == r11) goto L_0x01ce;
     */
    /* JADX WARN: Code restructure failed: missing block: B:104:0x0187, code lost:
        if (r12[3] < r1) goto L_0x018a;
     */
    /* JADX WARN: Code restructure failed: missing block: B:105:0x018a, code lost:
        if (r8 >= r11) goto L_0x019e;
     */
    /* JADX WARN: Code restructure failed: missing block: B:107:0x0190, code lost:
        if (r10.m2519a(r8, r9) == false) goto L_0x019e;
     */
    /* JADX WARN: Code restructure failed: missing block: B:109:0x0194, code lost:
        if (r12[4] >= r1) goto L_0x019e;
     */
    /* JADX WARN: Code restructure failed: missing block: B:110:0x0196, code lost:
        r12[4] = r12[4] + 1;
        r8 = r8 + 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:112:0x01a0, code lost:
        if (r12[4] < r1) goto L_0x01a5;
     */
    /* JADX WARN: Code restructure failed: missing block: B:113:0x01a2, code lost:
        r1 = Float.NaN;
     */
    /* JADX WARN: Code restructure failed: missing block: B:115:0x01bb, code lost:
        if ((java.lang.Math.abs(((((r12[0] + r12[1]) + r12[2]) + r12[3]) + r12[4]) - r3) * 5) < r3) goto L_0x01c0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:116:0x01bd, code lost:
        r1 = Float.NaN;
     */
    /* JADX WARN: Code restructure failed: missing block: B:118:0x01c4, code lost:
        if (m1766a(r12) == false) goto L_0x01cb;
     */
    /* JADX WARN: Code restructure failed: missing block: B:119:0x01c6, code lost:
        r1 = m1765a(r12, r8);
     */
    /* JADX WARN: Code restructure failed: missing block: B:120:0x01cb, code lost:
        r1 = Float.NaN;
     */
    /* JADX WARN: Code restructure failed: missing block: B:121:0x01ce, code lost:
        r1 = Float.NaN;
     */
    /* JADX WARN: Code restructure failed: missing block: B:18:0x0059, code lost:
        if (r12[1] <= r9) goto L_0x005d;
     */
    /* JADX WARN: Code restructure failed: missing block: B:19:0x005d, code lost:
        if (r13 < 0) goto L_0x0071;
     */
    /* JADX WARN: Code restructure failed: missing block: B:21:0x0063, code lost:
        if (r10.m2519a(r8, r13) == false) goto L_0x0071;
     */
    /* JADX WARN: Code restructure failed: missing block: B:23:0x0067, code lost:
        if (r12[0] > r9) goto L_0x0071;
     */
    /* JADX WARN: Code restructure failed: missing block: B:24:0x0069, code lost:
        r12[0] = r12[0] + 1;
        r13 = r13 - 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x0073, code lost:
        if (r12[0] <= r9) goto L_0x0079;
     */
    /* JADX WARN: Code restructure failed: missing block: B:27:0x0075, code lost:
        r15 = Float.NaN;
     */
    /* JADX WARN: Code restructure failed: missing block: B:28:0x0079, code lost:
        r13 = r20 + 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:29:0x007b, code lost:
        if (r13 >= r11) goto L_0x008b;
     */
    /* JADX WARN: Code restructure failed: missing block: B:31:0x0081, code lost:
        if (r10.m2519a(r8, r13) == false) goto L_0x008b;
     */
    /* JADX WARN: Code restructure failed: missing block: B:32:0x0083, code lost:
        r12[2] = r12[2] + 1;
        r13 = r13 + 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:33:0x008b, code lost:
        if (r13 != r11) goto L_0x0091;
     */
    /* JADX WARN: Code restructure failed: missing block: B:34:0x008d, code lost:
        r15 = Float.NaN;
     */
    /* JADX WARN: Code restructure failed: missing block: B:35:0x0091, code lost:
        if (r13 >= r11) goto L_0x00a5;
     */
    /* JADX WARN: Code restructure failed: missing block: B:37:0x0097, code lost:
        if (r10.m2519a(r8, r13) != false) goto L_0x00a5;
     */
    /* JADX WARN: Code restructure failed: missing block: B:39:0x009b, code lost:
        if (r12[3] >= r9) goto L_0x00a5;
     */
    /* JADX WARN: Code restructure failed: missing block: B:40:0x009d, code lost:
        r12[3] = r12[3] + 1;
        r13 = r13 + 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:41:0x00a5, code lost:
        if (r13 == r11) goto L_0x00f1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:43:0x00a9, code lost:
        if (r12[3] < r9) goto L_0x00ac;
     */
    /* JADX WARN: Code restructure failed: missing block: B:44:0x00ac, code lost:
        if (r13 >= r11) goto L_0x00c0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:46:0x00b2, code lost:
        if (r10.m2519a(r8, r13) == false) goto L_0x00c0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:48:0x00b6, code lost:
        if (r12[4] >= r9) goto L_0x00c0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:49:0x00b8, code lost:
        r12[4] = r12[4] + 1;
        r13 = r13 + 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:51:0x00c2, code lost:
        if (r12[4] < r9) goto L_0x00c7;
     */
    /* JADX WARN: Code restructure failed: missing block: B:52:0x00c4, code lost:
        r15 = Float.NaN;
     */
    /* JADX WARN: Code restructure failed: missing block: B:54:0x00de, code lost:
        if ((java.lang.Math.abs(((((r12[0] + r12[1]) + r12[2]) + r12[3]) + r12[4]) - r3) * 5) < (r3 * 2)) goto L_0x00e3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:55:0x00e0, code lost:
        r15 = Float.NaN;
     */
    /* JADX WARN: Code restructure failed: missing block: B:57:0x00e7, code lost:
        if (m1766a(r12) == false) goto L_0x00ee;
     */
    /* JADX WARN: Code restructure failed: missing block: B:58:0x00e9, code lost:
        r15 = m1765a(r12, r13);
     */
    /* JADX WARN: Code restructure failed: missing block: B:59:0x00ee, code lost:
        r15 = Float.NaN;
     */
    /* JADX WARN: Code restructure failed: missing block: B:60:0x00f1, code lost:
        r15 = Float.NaN;
     */
    /* JADX WARN: Code restructure failed: missing block: B:79:0x0138, code lost:
        if (r12[1] <= r1) goto L_0x013c;
     */
    /* JADX WARN: Code restructure failed: missing block: B:80:0x013c, code lost:
        if (r13 < 0) goto L_0x0150;
     */
    /* JADX WARN: Code restructure failed: missing block: B:82:0x0142, code lost:
        if (r10.m2519a(r13, r9) == false) goto L_0x0150;
     */
    /* JADX WARN: Code restructure failed: missing block: B:84:0x0146, code lost:
        if (r12[0] > r1) goto L_0x0150;
     */
    /* JADX WARN: Code restructure failed: missing block: B:85:0x0148, code lost:
        r12[0] = r12[0] + 1;
        r13 = r13 - 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:87:0x0152, code lost:
        if (r12[0] <= r1) goto L_0x0158;
     */
    /* JADX WARN: Code restructure failed: missing block: B:88:0x0154, code lost:
        r1 = Float.NaN;
     */
    /* JADX WARN: Code restructure failed: missing block: B:89:0x0158, code lost:
        r8 = r8 + 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:90:0x0159, code lost:
        if (r8 >= r11) goto L_0x0169;
     */
    /* JADX WARN: Code restructure failed: missing block: B:92:0x015f, code lost:
        if (r10.m2519a(r8, r9) == false) goto L_0x0169;
     */
    /* JADX WARN: Code restructure failed: missing block: B:93:0x0161, code lost:
        r12[2] = r12[2] + 1;
        r8 = r8 + 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:94:0x0169, code lost:
        if (r8 != r11) goto L_0x016f;
     */
    /* JADX WARN: Code restructure failed: missing block: B:95:0x016b, code lost:
        r1 = Float.NaN;
     */
    /* JADX WARN: Code restructure failed: missing block: B:96:0x016f, code lost:
        if (r8 >= r11) goto L_0x0183;
     */
    /* JADX WARN: Code restructure failed: missing block: B:98:0x0175, code lost:
        if (r10.m2519a(r8, r9) != false) goto L_0x0183;
     */
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final boolean m1764a(int[] r19, int r20, int r21) {
        /*
            Method dump skipped, instructions count: 896
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: p110z1.FinderPatternFinder.m1764a(int[], int, int):boolean");
    }

    /* renamed from: d */
    private int m1757d() {
        if (this.f22649d.size() <= 1) {
            return 0;
        }
        C5415nu nuVar = null;
        for (C5415nu nuVar2 : this.f22649d) {
            if (nuVar2.f22644b >= 2) {
                if (nuVar == null) {
                    nuVar = nuVar2;
                } else {
                    this.f22650f = true;
                    return ((int) (Math.abs(nuVar.f22726c - nuVar2.f22726c) - Math.abs(nuVar.f22727d - nuVar2.f22727d))) / 2;
                }
            }
        }
        return 0;
    }

    /* renamed from: e */
    private boolean m1755e() {
        int size = this.f22649d.size();
        float f = 0.0f;
        int i = 0;
        float f2 = 0.0f;
        for (C5415nu nuVar : this.f22649d) {
            if (nuVar.f22644b >= 2) {
                i++;
                f2 += nuVar.f22643a;
            }
        }
        if (i < 3) {
            return false;
        }
        float f3 = f2 / size;
        for (C5415nu nuVar2 : this.f22649d) {
            f += Math.abs(nuVar2.f22643a - f3);
        }
        return f <= f2 * 0.05f;
    }

    /* renamed from: f */
    private C5415nu[] m1754f() throws NotFoundException {
        float f;
        int size = this.f22649d.size();
        if (size >= 3) {
            float f2 = 0.0f;
            if (size > 3) {
                float f3 = 0.0f;
                float f4 = 0.0f;
                for (C5415nu nuVar : this.f22649d) {
                    float f5 = nuVar.f22643a;
                    f3 += f5;
                    f4 += f5 * f5;
                }
                float f6 = f3 / size;
                float sqrt = (float) Math.sqrt((f4 / f) - (f6 * f6));
                Collections.sort(this.f22649d, new C5418b(f6, (byte) 0));
                float max = Math.max(0.2f * f6, sqrt);
                int i = 0;
                while (i < this.f22649d.size() && this.f22649d.size() > 3) {
                    if (Math.abs(this.f22649d.get(i).f22643a - f6) > max) {
                        this.f22649d.remove(i);
                        i--;
                    }
                    i++;
                }
            }
            if (this.f22649d.size() > 3) {
                for (C5415nu nuVar2 : this.f22649d) {
                    f2 += nuVar2.f22643a;
                }
                Collections.sort(this.f22649d, new C5417a(f2 / this.f22649d.size(), (byte) 0));
                List<C5415nu> list = this.f22649d;
                list.subList(3, list.size()).clear();
            }
            return new C5415nu[]{this.f22649d.get(0), this.f22649d.get(1), this.f22649d.get(2)};
        }
        throw NotFoundException.m1647a();
    }

    /* compiled from: FinderPatternFinder.java */
    /* renamed from: z1.nv$b */
    /* loaded from: classes3.dex */
    private static final class C5418b implements Serializable, Comparator<C5415nu> {

        /* renamed from: a */
        private final float f22654a;

        /* synthetic */ C5418b(float f, byte b) {
            this(f);
        }

        @Override // java.util.Comparator
        public final /* synthetic */ int compare(C5415nu nuVar, C5415nu nuVar2) {
            return Float.compare(Math.abs(nuVar2.f22643a - this.f22654a), Math.abs(nuVar.f22643a - this.f22654a));
        }

        private C5418b(float f) {
            this.f22654a = f;
        }

        /* renamed from: a */
        private int m1752a(C5415nu nuVar, C5415nu nuVar2) {
            return Float.compare(Math.abs(nuVar2.f22643a - this.f22654a), Math.abs(nuVar.f22643a - this.f22654a));
        }
    }

    /* compiled from: FinderPatternFinder.java */
    /* renamed from: z1.nv$a */
    /* loaded from: classes3.dex */
    private static final class C5417a implements Serializable, Comparator<C5415nu> {

        /* renamed from: a */
        private final float f22653a;

        /* synthetic */ C5417a(float f, byte b) {
            this(f);
        }

        @Override // java.util.Comparator
        public final /* synthetic */ int compare(C5415nu nuVar, C5415nu nuVar2) {
            C5415nu nuVar3 = nuVar;
            C5415nu nuVar4 = nuVar2;
            int compare = Integer.compare(nuVar4.f22644b, nuVar3.f22644b);
            return compare == 0 ? Float.compare(Math.abs(nuVar3.f22643a - this.f22653a), Math.abs(nuVar4.f22643a - this.f22653a)) : compare;
        }

        private C5417a(float f) {
            this.f22653a = f;
        }

        /* renamed from: a */
        private int m1753a(C5415nu nuVar, C5415nu nuVar2) {
            int compare = Integer.compare(nuVar2.f22644b, nuVar.f22644b);
            return compare == 0 ? Float.compare(Math.abs(nuVar.f22643a - this.f22653a), Math.abs(nuVar2.f22643a - this.f22653a)) : compare;
        }
    }
}
