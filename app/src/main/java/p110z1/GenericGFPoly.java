package p110z1;

/* JADX INFO: Access modifiers changed from: package-private */
/* renamed from: z1.ia */
/* loaded from: classes3.dex */
public final class GenericGFPoly {

    /* renamed from: a */
    final int[] f21949a;

    /* renamed from: b */
    private final GenericGF f21950b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public GenericGFPoly(GenericGF hzVar, int[] iArr) {
        if (iArr.length != 0) {
            this.f21950b = hzVar;
            int length = iArr.length;
            if (length <= 1 || iArr[0] != 0) {
                this.f21949a = iArr;
                return;
            }
            int i = 1;
            while (i < length && iArr[i] == 0) {
                i++;
            }
            if (i == length) {
                this.f21949a = new int[]{0};
                return;
            }
            this.f21949a = new int[length - i];
            int[] iArr2 = this.f21949a;
            System.arraycopy(iArr, i, iArr2, 0, iArr2.length);
            return;
        }
        throw new IllegalArgumentException();
    }

    /* renamed from: b */
    private int[] m2479b() {
        return this.f21949a;
    }

    /* renamed from: c */
    private int m2476c() {
        return this.f21949a.length - 1;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public final boolean m2483a() {
        return this.f21949a[0] == 0;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public final int m2482a(int i) {
        int[] iArr = this.f21949a;
        return iArr[(iArr.length - 1) - i];
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: b */
    public final int m2478b(int i) {
        if (i == 0) {
            return m2482a(0);
        }
        if (i == 1) {
            int i2 = 0;
            for (int i3 : this.f21949a) {
                i2 = GenericGF.m2493b(i2, i3);
            }
            return i2;
        }
        int[] iArr = this.f21949a;
        int i4 = iArr[0];
        int length = iArr.length;
        for (int i5 = 1; i5 < length; i5++) {
            i4 = GenericGF.m2493b(this.f21950b.m2490c(i, i4), this.f21949a[i5]);
        }
        return i4;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public final GenericGFPoly m2480a(GenericGFPoly iaVar) {
        if (!this.f21950b.equals(iaVar.f21950b)) {
            throw new IllegalArgumentException("GenericGFPolys do not have same GenericGF field");
        } else if (m2483a()) {
            return iaVar;
        } else {
            if (iaVar.m2483a()) {
                return this;
            }
            int[] iArr = this.f21949a;
            int[] iArr2 = iaVar.f21949a;
            if (iArr.length > iArr2.length) {
                iArr = iArr2;
                iArr2 = iArr;
            }
            int[] iArr3 = new int[iArr2.length];
            int length = iArr2.length - iArr.length;
            System.arraycopy(iArr2, 0, iArr3, 0, length);
            for (int i = length; i < iArr2.length; i++) {
                iArr3[i] = GenericGF.m2493b(iArr[i - length], iArr2[i]);
            }
            return new GenericGFPoly(this.f21950b, iArr3);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: b */
    public final GenericGFPoly m2477b(GenericGFPoly iaVar) {
        if (!this.f21950b.equals(iaVar.f21950b)) {
            throw new IllegalArgumentException("GenericGFPolys do not have same GenericGF field");
        } else if (m2483a() || iaVar.m2483a()) {
            return this.f21950b.f21933j;
        } else {
            int[] iArr = this.f21949a;
            int length = iArr.length;
            int[] iArr2 = iaVar.f21949a;
            int length2 = iArr2.length;
            int[] iArr3 = new int[(length + length2) - 1];
            for (int i = 0; i < length; i++) {
                int i2 = iArr[i];
                for (int i3 = 0; i3 < length2; i3++) {
                    int i4 = i + i3;
                    iArr3[i4] = GenericGF.m2493b(iArr3[i4], this.f21950b.m2490c(i2, iArr2[i3]));
                }
            }
            return new GenericGFPoly(this.f21950b, iArr3);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: c */
    public final GenericGFPoly m2475c(int i) {
        if (i == 0) {
            return this.f21950b.f21933j;
        }
        if (i == 1) {
            return this;
        }
        int length = this.f21949a.length;
        int[] iArr = new int[length];
        for (int i2 = 0; i2 < length; i2++) {
            iArr[i2] = this.f21950b.m2490c(this.f21949a[i2], i);
        }
        return new GenericGFPoly(this.f21950b, iArr);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public final GenericGFPoly m2481a(int i, int i2) {
        if (i < 0) {
            throw new IllegalArgumentException();
        } else if (i2 == 0) {
            return this.f21950b.f21933j;
        } else {
            int length = this.f21949a.length;
            int[] iArr = new int[i + length];
            for (int i3 = 0; i3 < length; i3++) {
                iArr[i3] = this.f21950b.m2490c(this.f21949a[i3], i2);
            }
            return new GenericGFPoly(this.f21950b, iArr);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: c */
    public final GenericGFPoly[] m2474c(GenericGFPoly iaVar) {
        if (!this.f21950b.equals(iaVar.f21950b)) {
            throw new IllegalArgumentException("GenericGFPolys do not have same GenericGF field");
        } else if (!iaVar.m2483a()) {
            GenericGFPoly iaVar2 = this.f21950b.f21933j;
            int b = this.f21950b.m2494b(iaVar.m2482a(iaVar.f21949a.length - 1));
            GenericGFPoly iaVar3 = iaVar2;
            GenericGFPoly iaVar4 = this;
            while (iaVar4.f21949a.length - 1 >= iaVar.f21949a.length - 1 && !iaVar4.m2483a()) {
                int[] iArr = iaVar4.f21949a;
                int length = (iArr.length - 1) - (iaVar.f21949a.length - 1);
                int c = this.f21950b.m2490c(iaVar4.m2482a(iArr.length - 1), b);
                GenericGFPoly a = iaVar.m2481a(length, c);
                iaVar3 = iaVar3.m2480a(this.f21950b.m2496a(length, c));
                iaVar4 = iaVar4.m2480a(a);
            }
            return new GenericGFPoly[]{iaVar3, iaVar4};
        } else {
            throw new IllegalArgumentException("Divide by 0");
        }
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder((this.f21949a.length - 1) * 8);
        for (int length = this.f21949a.length - 1; length >= 0; length--) {
            int a = m2482a(length);
            if (a != 0) {
                if (a < 0) {
                    sb.append(" - ");
                    a = -a;
                } else if (sb.length() > 0) {
                    sb.append(" + ");
                }
                if (length == 0 || a != 1) {
                    int a2 = this.f21950b.m2497a(a);
                    if (a2 == 0) {
                        sb.append('1');
                    } else if (a2 == 1) {
                        sb.append('a');
                    } else {
                        sb.append("a^");
                        sb.append(a2);
                    }
                }
                if (length != 0) {
                    if (length == 1) {
                        sb.append('x');
                    } else {
                        sb.append("x^");
                        sb.append(length);
                    }
                }
            }
        }
        return sb.toString();
    }
}
