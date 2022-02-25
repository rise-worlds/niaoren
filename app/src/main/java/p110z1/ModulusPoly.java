package p110z1;

/* renamed from: z1.mi */
/* loaded from: classes3.dex */
public final class ModulusPoly {

    /* renamed from: a */
    public final ModulusGF f22426a;

    /* renamed from: b */
    public final int[] f22427b;

    public ModulusPoly(ModulusGF mhVar, int[] iArr) {
        if (iArr.length != 0) {
            this.f22426a = mhVar;
            int length = iArr.length;
            if (length <= 1 || iArr[0] != 0) {
                this.f22427b = iArr;
                return;
            }
            int i = 1;
            while (i < length && iArr[i] == 0) {
                i++;
            }
            if (i == length) {
                this.f22427b = new int[]{0};
                return;
            }
            this.f22427b = new int[length - i];
            int[] iArr2 = this.f22427b;
            System.arraycopy(iArr, i, iArr2, 0, iArr2.length);
            return;
        }
        throw new IllegalArgumentException();
    }

    /* renamed from: c */
    private int[] m2028c() {
        return this.f22427b;
    }

    /* renamed from: d */
    private int m2025d() {
        return this.f22427b.length - 1;
    }

    /* renamed from: a */
    public final boolean m2035a() {
        return this.f22427b[0] == 0;
    }

    /* renamed from: a */
    public final int m2034a(int i) {
        int[] iArr = this.f22427b;
        return iArr[(iArr.length - 1) - i];
    }

    /* renamed from: b */
    public final int m2030b(int i) {
        if (i == 0) {
            return m2034a(0);
        }
        if (i == 1) {
            int i2 = 0;
            for (int i3 : this.f22427b) {
                i2 = this.f22426a.m2040b(i2, i3);
            }
            return i2;
        }
        int[] iArr = this.f22427b;
        int i4 = iArr[0];
        int length = iArr.length;
        for (int i5 = 1; i5 < length; i5++) {
            ModulusGF mhVar = this.f22426a;
            i4 = mhVar.m2040b(mhVar.m2036d(i, i4), this.f22427b[i5]);
        }
        return i4;
    }

    /* renamed from: a */
    public final ModulusPoly m2032a(ModulusPoly miVar) {
        if (!this.f22426a.equals(miVar.f22426a)) {
            throw new IllegalArgumentException("ModulusPolys do not have same ModulusGF field");
        } else if (m2035a()) {
            return miVar;
        } else {
            if (miVar.m2035a()) {
                return this;
            }
            int[] iArr = this.f22427b;
            int[] iArr2 = miVar.f22427b;
            if (iArr.length > iArr2.length) {
                iArr = iArr2;
                iArr2 = iArr;
            }
            int[] iArr3 = new int[iArr2.length];
            int length = iArr2.length - iArr.length;
            System.arraycopy(iArr2, 0, iArr3, 0, length);
            for (int i = length; i < iArr2.length; i++) {
                iArr3[i] = this.f22426a.m2040b(iArr[i - length], iArr2[i]);
            }
            return new ModulusPoly(this.f22426a, iArr3);
        }
    }

    /* renamed from: b */
    public final ModulusPoly m2029b(ModulusPoly miVar) {
        if (this.f22426a.equals(miVar.f22426a)) {
            return miVar.m2035a() ? this : m2032a(miVar.m2031b());
        }
        throw new IllegalArgumentException("ModulusPolys do not have same ModulusGF field");
    }

    /* renamed from: c */
    public final ModulusPoly m2026c(ModulusPoly miVar) {
        if (!this.f22426a.equals(miVar.f22426a)) {
            throw new IllegalArgumentException("ModulusPolys do not have same ModulusGF field");
        } else if (m2035a() || miVar.m2035a()) {
            return this.f22426a.f22423d;
        } else {
            int[] iArr = this.f22427b;
            int length = iArr.length;
            int[] iArr2 = miVar.f22427b;
            int length2 = iArr2.length;
            int[] iArr3 = new int[(length + length2) - 1];
            for (int i = 0; i < length; i++) {
                int i2 = iArr[i];
                for (int i3 = 0; i3 < length2; i3++) {
                    int i4 = i + i3;
                    ModulusGF mhVar = this.f22426a;
                    iArr3[i4] = mhVar.m2040b(iArr3[i4], mhVar.m2036d(i2, iArr2[i3]));
                }
            }
            return new ModulusPoly(this.f22426a, iArr3);
        }
    }

    /* renamed from: b */
    public final ModulusPoly m2031b() {
        int length = this.f22427b.length;
        int[] iArr = new int[length];
        for (int i = 0; i < length; i++) {
            iArr[i] = this.f22426a.m2037c(0, this.f22427b[i]);
        }
        return new ModulusPoly(this.f22426a, iArr);
    }

    /* renamed from: c */
    public final ModulusPoly m2027c(int i) {
        if (i == 0) {
            return this.f22426a.f22423d;
        }
        if (i == 1) {
            return this;
        }
        int length = this.f22427b.length;
        int[] iArr = new int[length];
        for (int i2 = 0; i2 < length; i2++) {
            iArr[i2] = this.f22426a.m2036d(this.f22427b[i2], i);
        }
        return new ModulusPoly(this.f22426a, iArr);
    }

    /* renamed from: a */
    private ModulusPoly m2033a(int i, int i2) {
        if (i < 0) {
            throw new IllegalArgumentException();
        } else if (i2 == 0) {
            return this.f22426a.f22423d;
        } else {
            int length = this.f22427b.length;
            int[] iArr = new int[i + length];
            for (int i3 = 0; i3 < length; i3++) {
                iArr[i3] = this.f22426a.m2036d(this.f22427b[i3], i2);
            }
            return new ModulusPoly(this.f22426a, iArr);
        }
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder((this.f22427b.length - 1) * 8);
        for (int length = this.f22427b.length - 1; length >= 0; length--) {
            int a = m2034a(length);
            if (a != 0) {
                if (a < 0) {
                    sb.append(" - ");
                    a = -a;
                } else if (sb.length() > 0) {
                    sb.append(" + ");
                }
                if (length == 0 || a != 1) {
                    sb.append(a);
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
