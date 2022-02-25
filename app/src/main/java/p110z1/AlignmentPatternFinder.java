package p110z1;

import java.util.ArrayList;
import java.util.List;

/* renamed from: z1.ns */
/* loaded from: classes3.dex */
final class AlignmentPatternFinder {

    /* renamed from: a */
    final BitMatrix f22632a;

    /* renamed from: c */
    final int f22634c;

    /* renamed from: d */
    final int f22635d;

    /* renamed from: e */
    final int f22636e;

    /* renamed from: f */
    final int f22637f;

    /* renamed from: g */
    private final float f22638g;

    /* renamed from: i */
    private final ResultPointCallback f22640i;

    /* renamed from: b */
    final List<AlignmentPattern> f22633b = new ArrayList(5);

    /* renamed from: h */
    private final int[] f22639h = new int[3];

    /* JADX INFO: Access modifiers changed from: package-private */
    public AlignmentPatternFinder(BitMatrix hyVar, int i, int i2, int i3, int i4, float f, ResultPointCallback ooVar) {
        this.f22632a = hyVar;
        this.f22634c = i;
        this.f22635d = i2;
        this.f22636e = i3;
        this.f22637f = i4;
        this.f22638g = f;
        this.f22640i = ooVar;
    }

    /* renamed from: a */
    private AlignmentPattern m1792a() throws NotFoundException {
        AlignmentPattern a;
        AlignmentPattern a2;
        int i = this.f22634c;
        int i2 = this.f22637f;
        int i3 = this.f22636e + i;
        int i4 = this.f22635d + (i2 / 2);
        int[] iArr = new int[3];
        for (int i5 = 0; i5 < i2; i5++) {
            int i6 = ((i5 & 1) == 0 ? (i5 + 1) / 2 : -((i5 + 1) / 2)) + i4;
            iArr[0] = 0;
            iArr[1] = 0;
            iArr[2] = 0;
            int i7 = i;
            while (i7 < i3 && !this.f22632a.m2519a(i7, i6)) {
                i7++;
            }
            int i8 = 0;
            while (i7 < i3) {
                if (!this.f22632a.m2519a(i7, i6)) {
                    if (i8 == 1) {
                        i8++;
                    }
                    iArr[i8] = iArr[i8] + 1;
                } else if (i8 == 1) {
                    iArr[1] = iArr[1] + 1;
                } else if (i8 != 2) {
                    i8++;
                    iArr[i8] = iArr[i8] + 1;
                } else if (m1790a(iArr) && (a2 = m1788a(iArr, i6, i7)) != null) {
                    return a2;
                } else {
                    iArr[0] = iArr[2];
                    iArr[1] = 1;
                    iArr[2] = 0;
                    i8 = 1;
                }
                i7++;
            }
            if (m1790a(iArr) && (a = m1788a(iArr, i6, i3)) != null) {
                return a;
            }
        }
        if (!this.f22633b.isEmpty()) {
            return this.f22633b.get(0);
        }
        throw NotFoundException.m1647a();
    }

    /* renamed from: a */
    private static float m1789a(int[] iArr, int i) {
        return (i - iArr[2]) - (iArr[1] / 2.0f);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public final boolean m1790a(int[] iArr) {
        float f = this.f22638g;
        float f2 = f / 2.0f;
        for (int i = 0; i < 3; i++) {
            if (Math.abs(f - iArr[i]) >= f2) {
                return false;
            }
        }
        return true;
    }

    /* JADX WARN: Code restructure failed: missing block: B:31:0x0060, code lost:
        if (r2[1] <= r12) goto L_0x0063;
     */
    /* JADX WARN: Code restructure failed: missing block: B:32:0x0063, code lost:
        if (r10 >= r1) goto L_0x0077;
     */
    /* JADX WARN: Code restructure failed: missing block: B:34:0x0069, code lost:
        if (r0.m2519a(r11, r10) != false) goto L_0x0077;
     */
    /* JADX WARN: Code restructure failed: missing block: B:36:0x006d, code lost:
        if (r2[2] > r12) goto L_0x0077;
     */
    /* JADX WARN: Code restructure failed: missing block: B:37:0x006f, code lost:
        r2[2] = r2[2] + 1;
        r10 = r10 + 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:39:0x0079, code lost:
        if (r2[2] <= r12) goto L_0x007c;
     */
    /* JADX WARN: Code restructure failed: missing block: B:40:0x007b, code lost:
        return Float.NaN;
     */
    /* JADX WARN: Code restructure failed: missing block: B:42:0x008d, code lost:
        if ((java.lang.Math.abs(((r2[0] + r2[1]) + r2[2]) - r13) * 5) < (r13 * 2)) goto L_0x0090;
     */
    /* JADX WARN: Code restructure failed: missing block: B:43:0x008f, code lost:
        return Float.NaN;
     */
    /* JADX WARN: Code restructure failed: missing block: B:45:0x0094, code lost:
        if (m1790a(r2) == false) goto L_0x009b;
     */
    /* JADX WARN: Code restructure failed: missing block: B:47:0x009a, code lost:
        return m1789a(r2, r10);
     */
    /* JADX WARN: Code restructure failed: missing block: B:48:0x009b, code lost:
        return Float.NaN;
     */
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private float m1791a(int r10, int r11, int r12, int r13) {
        /*
            r9 = this;
            z1.hy r0 = r9.f22632a
            int r1 = r0.f21921b
            int[] r2 = r9.f22639h
            r3 = 0
            r2[r3] = r3
            r4 = 1
            r2[r4] = r3
            r5 = 2
            r2[r5] = r3
            r6 = r10
        L_0x0010:
            if (r6 < 0) goto L_0x0024
            boolean r7 = r0.m2519a(r11, r6)
            if (r7 == 0) goto L_0x0024
            r7 = r2[r4]
            if (r7 > r12) goto L_0x0024
            r7 = r2[r4]
            int r7 = r7 + r4
            r2[r4] = r7
            int r6 = r6 + (-1)
            goto L_0x0010
        L_0x0024:
            r7 = 2143289344(0x7fc00000, float:NaN)
            if (r6 < 0) goto L_0x009d
            r8 = r2[r4]
            if (r8 <= r12) goto L_0x002e
            goto L_0x009d
        L_0x002e:
            if (r6 < 0) goto L_0x0042
            boolean r8 = r0.m2519a(r11, r6)
            if (r8 != 0) goto L_0x0042
            r8 = r2[r3]
            if (r8 > r12) goto L_0x0042
            r8 = r2[r3]
            int r8 = r8 + r4
            r2[r3] = r8
            int r6 = r6 + (-1)
            goto L_0x002e
        L_0x0042:
            r6 = r2[r3]
            if (r6 <= r12) goto L_0x0047
            return r7
        L_0x0047:
            int r10 = r10 + r4
        L_0x0048:
            if (r10 >= r1) goto L_0x005c
            boolean r6 = r0.m2519a(r11, r10)
            if (r6 == 0) goto L_0x005c
            r6 = r2[r4]
            if (r6 > r12) goto L_0x005c
            r6 = r2[r4]
            int r6 = r6 + r4
            r2[r4] = r6
            int r10 = r10 + 1
            goto L_0x0048
        L_0x005c:
            if (r10 == r1) goto L_0x009c
            r6 = r2[r4]
            if (r6 <= r12) goto L_0x0063
            goto L_0x009c
        L_0x0063:
            if (r10 >= r1) goto L_0x0077
            boolean r6 = r0.m2519a(r11, r10)
            if (r6 != 0) goto L_0x0077
            r6 = r2[r5]
            if (r6 > r12) goto L_0x0077
            r6 = r2[r5]
            int r6 = r6 + r4
            r2[r5] = r6
            int r10 = r10 + 1
            goto L_0x0063
        L_0x0077:
            r11 = r2[r5]
            if (r11 <= r12) goto L_0x007c
            return r7
        L_0x007c:
            r11 = r2[r3]
            r12 = r2[r4]
            int r11 = r11 + r12
            r12 = r2[r5]
            int r11 = r11 + r12
            int r11 = r11 - r13
            int r11 = java.lang.Math.abs(r11)
            int r11 = r11 * 5
            int r13 = r13 * 2
            if (r11 < r13) goto L_0x0090
            return r7
        L_0x0090:
            boolean r11 = r9.m1790a(r2)
            if (r11 == 0) goto L_0x009b
            float r10 = m1789a(r2, r10)
            return r10
        L_0x009b:
            return r7
        L_0x009c:
            return r7
        L_0x009d:
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: p110z1.AlignmentPatternFinder.m1791a(int, int, int, int):float");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Code restructure failed: missing block: B:30:0x0071, code lost:
        if (r8[1] <= r5) goto L_0x0074;
     */
    /* JADX WARN: Code restructure failed: missing block: B:31:0x0074, code lost:
        if (r14 >= r7) goto L_0x0088;
     */
    /* JADX WARN: Code restructure failed: missing block: B:33:0x007a, code lost:
        if (r6.m2519a(r4, r14) != false) goto L_0x0088;
     */
    /* JADX WARN: Code restructure failed: missing block: B:35:0x007e, code lost:
        if (r8[2] > r5) goto L_0x0088;
     */
    /* JADX WARN: Code restructure failed: missing block: B:36:0x0080, code lost:
        r8[2] = r8[2] + 1;
        r14 = r14 + 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:38:0x008a, code lost:
        if (r8[2] <= r5) goto L_0x008d;
     */
    /* JADX WARN: Code restructure failed: missing block: B:40:0x009e, code lost:
        if ((java.lang.Math.abs(((r8[0] + r8[1]) + r8[2]) - r1) * 5) < (r1 * 2)) goto L_0x00a1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:42:0x00a5, code lost:
        if (m1790a(r8) == false) goto L_0x00ab;
     */
    /* JADX WARN: Code restructure failed: missing block: B:43:0x00a7, code lost:
        r10 = m1789a(r8, r14);
     */
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final p110z1.AlignmentPattern m1788a(int[] r13, int r14, int r15) {
        /*
            Method dump skipped, instructions count: 291
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: p110z1.AlignmentPatternFinder.m1788a(int[], int, int):z1.nr");
    }
}
