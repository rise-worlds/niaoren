package p110z1;

/* compiled from: ErrorCorrection.java */
/* renamed from: z1.mg */
/* loaded from: classes3.dex */
public final class C5392mg {

    /* renamed from: a */
    public final ModulusGF f22419a = ModulusGF.f22420a;

    /* renamed from: a */
    private int m2046a(int[] iArr, int i, int[] iArr2) throws ChecksumException {
        int i2;
        ModulusPoly miVar;
        ModulusPoly miVar2 = new ModulusPoly(this.f22419a, iArr);
        int[] iArr3 = new int[i];
        int i3 = i;
        boolean z = false;
        while (true) {
            i2 = 1;
            if (i3 <= 0) {
                break;
            }
            int b = miVar2.m2030b(this.f22419a.f22421b[i3]);
            iArr3[i - i3] = b;
            if (b != 0) {
                z = true;
            }
            i3--;
        }
        if (!z) {
            return 0;
        }
        ModulusPoly miVar3 = this.f22419a.f22424e;
        if (iArr2 != null) {
            ModulusPoly miVar4 = miVar3;
            for (int i4 : iArr2) {
                int i5 = this.f22419a.f22421b[(iArr.length - 1) - i4];
                ModulusGF mhVar = this.f22419a;
                miVar4 = miVar4.m2026c(new ModulusPoly(mhVar, new int[]{mhVar.m2037c(0, i5), 1}));
            }
        }
        ModulusPoly miVar5 = new ModulusPoly(this.f22419a, iArr3);
        ModulusPoly a = this.f22419a.m2043a(i, 1);
        if (a.f22427b.length - 1 >= miVar5.f22427b.length - 1) {
            a = miVar5;
            miVar5 = a;
        }
        ModulusPoly miVar6 = this.f22419a.f22423d;
        ModulusPoly miVar7 = this.f22419a.f22424e;
        while (a.f22427b.length - i2 >= i / 2) {
            if (!a.m2035a()) {
                ModulusPoly miVar8 = this.f22419a.f22423d;
                int a2 = this.f22419a.m2044a(a.m2034a(a.f22427b.length - i2));
                while (miVar5.f22427b.length - i2 >= a.f22427b.length - i2 && !miVar5.m2035a()) {
                    int length = (miVar5.f22427b.length - i2) - (a.f22427b.length - i2);
                    int d = this.f22419a.m2036d(miVar5.m2034a(miVar5.f22427b.length - i2), a2);
                    miVar8 = miVar8.m2032a(this.f22419a.m2043a(length, d));
                    if (length >= 0) {
                        if (d == 0) {
                            miVar = a.f22426a.f22423d;
                        } else {
                            int length2 = a.f22427b.length;
                            int[] iArr4 = new int[length + length2];
                            for (int i6 = 0; i6 < length2; i6++) {
                                iArr4[i6] = a.f22426a.m2036d(a.f22427b[i6], d);
                            }
                            miVar = new ModulusPoly(a.f22426a, iArr4);
                        }
                        miVar5 = miVar5.m2029b(miVar);
                        i2 = 1;
                    } else {
                        throw new IllegalArgumentException();
                    }
                }
                miVar7 = miVar8.m2026c(miVar7).m2029b(miVar6).m2031b();
                miVar6 = miVar7;
                i2 = 1;
                miVar5 = a;
                a = miVar5;
            } else {
                throw ChecksumException.m2421a();
            }
        }
        int a3 = miVar7.m2034a(0);
        if (a3 != 0) {
            int a4 = this.f22419a.m2044a(a3);
            ModulusPoly[] miVarArr = {miVar7.m2027c(a4), a.m2027c(a4)};
            ModulusPoly miVar9 = miVarArr[0];
            ModulusPoly miVar10 = miVarArr[1];
            int[] a5 = m2049a(miVar9);
            int[] a6 = m2047a(miVar10, miVar9, a5);
            for (int i7 = 0; i7 < a5.length; i7++) {
                int length3 = iArr.length - 1;
                ModulusGF mhVar2 = this.f22419a;
                int i8 = a5[i7];
                if (i8 != 0) {
                    int i9 = length3 - mhVar2.f22422c[i8];
                    if (i9 >= 0) {
                        iArr[i9] = this.f22419a.m2037c(iArr[i9], a6[i7]);
                    } else {
                        throw ChecksumException.m2421a();
                    }
                } else {
                    throw new IllegalArgumentException();
                }
            }
            return a5.length;
        }
        throw ChecksumException.m2421a();
    }

    /* renamed from: a */
    private ModulusPoly[] m2048a(ModulusPoly miVar, ModulusPoly miVar2, int i) throws ChecksumException {
        ModulusPoly miVar3;
        ModulusPoly miVar4 = miVar;
        miVar4 = miVar2;
        if (miVar4.f22427b.length - 1 < miVar4.f22427b.length - 1) {
            miVar4 = miVar4;
            miVar4 = miVar4;
        }
        ModulusPoly miVar5 = this.f22419a.f22423d;
        miVar5 = this.f22419a.f22424e;
        while (miVar4.f22427b.length - 1 >= i / 2) {
            if (!miVar4.m2035a()) {
                ModulusPoly miVar6 = this.f22419a.f22423d;
                int a = this.f22419a.m2044a(miVar4.m2034a(miVar4.f22427b.length - 1));
                while (miVar4.f22427b.length - 1 >= miVar4.f22427b.length - 1 && !miVar4.m2035a()) {
                    int length = (miVar4.f22427b.length - 1) - (miVar4.f22427b.length - 1);
                    int d = this.f22419a.m2036d(miVar4.m2034a(miVar4.f22427b.length - 1), a);
                    miVar6 = miVar6.m2032a(this.f22419a.m2043a(length, d));
                    if (length >= 0) {
                        if (d == 0) {
                            miVar3 = miVar4.f22426a.f22423d;
                        } else {
                            int length2 = miVar4.f22427b.length;
                            int[] iArr = new int[length + length2];
                            for (int i2 = 0; i2 < length2; i2++) {
                                iArr[i2] = miVar4.f22426a.m2036d(miVar4.f22427b[i2], d);
                            }
                            miVar3 = new ModulusPoly(miVar4.f22426a, iArr);
                        }
                        miVar4 = miVar4.m2029b(miVar3);
                    } else {
                        throw new IllegalArgumentException();
                    }
                }
                miVar5 = miVar6.m2026c(miVar5).m2029b(miVar5).m2031b();
            } else {
                throw ChecksumException.m2421a();
            }
        }
        int a2 = miVar5.m2034a(0);
        if (a2 != 0) {
            int a3 = this.f22419a.m2044a(a2);
            return new ModulusPoly[]{miVar5.m2027c(a3), miVar4.m2027c(a3)};
        }
        throw ChecksumException.m2421a();
    }

    /* renamed from: a */
    public final int[] m2049a(ModulusPoly miVar) throws ChecksumException {
        int length = miVar.f22427b.length - 1;
        int[] iArr = new int[length];
        int i = 0;
        for (int i2 = 1; i2 < this.f22419a.f22425f && i < length; i2++) {
            if (miVar.m2030b(i2) == 0) {
                iArr[i] = this.f22419a.m2044a(i2);
                i++;
            }
        }
        if (i == length) {
            return iArr;
        }
        throw ChecksumException.m2421a();
    }

    /* renamed from: a */
    public final int[] m2047a(ModulusPoly miVar, ModulusPoly miVar2, int[] iArr) {
        int length = miVar2.f22427b.length - 1;
        int[] iArr2 = new int[length];
        for (int i = 1; i <= length; i++) {
            iArr2[length - i] = this.f22419a.m2036d(i, miVar2.m2034a(i));
        }
        ModulusPoly miVar3 = new ModulusPoly(this.f22419a, iArr2);
        int length2 = iArr.length;
        int[] iArr3 = new int[length2];
        for (int i2 = 0; i2 < length2; i2++) {
            int a = this.f22419a.m2044a(iArr[i2]);
            iArr3[i2] = this.f22419a.m2036d(this.f22419a.m2037c(0, miVar.m2030b(a)), this.f22419a.m2044a(miVar3.m2030b(a)));
        }
        return iArr3;
    }
}
