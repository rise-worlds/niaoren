package p110z1;

/* renamed from: z1.mp */
/* loaded from: classes3.dex */
final class DetectionResultRowIndicatorColumn extends DetectionResultColumn {

    /* renamed from: c */
    final boolean f22490c;

    /* JADX INFO: Access modifiers changed from: package-private */
    public DetectionResultRowIndicatorColumn(BoundingBox mkVar, boolean z) {
        super(mkVar);
        this.f22490c = z;
    }

    /* renamed from: c */
    private int[] m1965c() {
        ResultPoint onVar;
        ResultPoint onVar2;
        Codeword[] mlVarArr;
        int i;
        BarcodeMetadata a = m1970a();
        if (a == null) {
            return null;
        }
        BoundingBox mkVar = this.f22488a;
        if (this.f22490c) {
            onVar = mkVar.f22430b;
        } else {
            onVar = mkVar.f22432d;
        }
        if (this.f22490c) {
            onVar2 = mkVar.f22431c;
        } else {
            onVar2 = mkVar.f22433e;
        }
        int b = m1972b((int) onVar2.f22727d);
        Codeword[] mlVarArr2 = this.f22489b;
        int i2 = -1;
        int i3 = 0;
        int i4 = 1;
        for (int b2 = m1972b((int) onVar.f22727d); b2 < b; b2++) {
            if (mlVarArr2[b2] != null) {
                Codeword mlVar = mlVarArr2[b2];
                mlVar.m2009b();
                int i5 = mlVar.f22443e - i2;
                if (i5 == 0) {
                    i3++;
                } else if (i5 == 1) {
                    i4 = Math.max(i4, i3);
                    i2 = mlVar.f22443e;
                    i3 = 1;
                } else if (mlVar.f22443e >= a.f22418e) {
                    mlVarArr2[b2] = null;
                } else {
                    i2 = mlVar.f22443e;
                    i3 = 1;
                }
            }
        }
        int[] iArr = new int[a.f22418e];
        for (Codeword mlVar2 : this.f22489b) {
            if (mlVar2 != null && (i = mlVar2.f22443e) < iArr.length) {
                iArr[i] = iArr[i] + 1;
            }
        }
        return iArr;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public final void m1968a(Codeword[] mlVarArr, BarcodeMetadata mfVar) {
        for (int i = 0; i < mlVarArr.length; i++) {
            Codeword mlVar = mlVarArr[i];
            if (mlVarArr[i] != null) {
                int i2 = mlVar.f22442d % 30;
                int i3 = mlVar.f22443e;
                if (i3 > mfVar.f22418e) {
                    mlVarArr[i] = null;
                } else {
                    if (!this.f22490c) {
                        i3 += 2;
                    }
                    switch (i3 % 3) {
                        case 0:
                            if ((i2 * 3) + 1 == mfVar.f22416c) {
                                break;
                            } else {
                                mlVarArr[i] = null;
                                continue;
                            }
                        case 1:
                            if (i2 / 3 != mfVar.f22415b || i2 % 3 != mfVar.f22417d) {
                                mlVarArr[i] = null;
                                break;
                            } else {
                                continue;
                            }
                            break;
                        case 2:
                            if (i2 + 1 != mfVar.f22414a) {
                                mlVarArr[i] = null;
                                break;
                            } else {
                                continue;
                            }
                    }
                }
            }
        }
    }

    /* renamed from: d */
    private boolean m1964d() {
        return this.f22490c;
    }

    @Override // p110z1.DetectionResultColumn
    public final String toString() {
        return "IsLeft: " + this.f22490c + '\n' + super.toString();
    }

    /* renamed from: b */
    private void m1967b() {
        Codeword[] mlVarArr;
        for (Codeword mlVar : this.f22489b) {
            if (mlVar != null) {
                mlVar.m2009b();
            }
        }
    }

    /* renamed from: a */
    private void m1969a(BarcodeMetadata mfVar) {
        Codeword[] mlVarArr;
        ResultPoint onVar;
        ResultPoint onVar2;
        Codeword[] mlVarArr2 = this.f22489b;
        for (Codeword mlVar : this.f22489b) {
            if (mlVar != null) {
                mlVar.m2009b();
            }
        }
        m1968a(mlVarArr2, mfVar);
        BoundingBox mkVar = this.f22488a;
        if (this.f22490c) {
            onVar = mkVar.f22430b;
        } else {
            onVar = mkVar.f22432d;
        }
        if (this.f22490c) {
            onVar2 = mkVar.f22431c;
        } else {
            onVar2 = mkVar.f22433e;
        }
        int b = m1972b((int) onVar.f22727d);
        int b2 = m1972b((int) onVar2.f22727d);
        int i = -1;
        int i2 = 0;
        int i3 = 1;
        while (b < b2) {
            if (mlVarArr2[b] != null) {
                Codeword mlVar2 = mlVarArr2[b];
                int i4 = mlVar2.f22443e - i;
                if (i4 == 0) {
                    i2++;
                } else if (i4 == 1) {
                    i3 = Math.max(i3, i2);
                    i = mlVar2.f22443e;
                    i2 = 1;
                } else if (i4 < 0 || mlVar2.f22443e >= mfVar.f22418e || i4 > b) {
                    mlVarArr2[b] = null;
                } else {
                    if (i3 > 2) {
                        i4 *= i3 - 2;
                    }
                    boolean z = i4 >= b;
                    for (int i5 = 1; i5 <= i4 && !z; i5++) {
                        z = mlVarArr2[b - i5] != null;
                    }
                    if (z) {
                        mlVarArr2[b] = null;
                    } else {
                        i = mlVar2.f22443e;
                        i2 = 1;
                    }
                }
            }
            b++;
        }
    }

    /* renamed from: b */
    private void m1966b(BarcodeMetadata mfVar) {
        ResultPoint onVar;
        ResultPoint onVar2;
        BoundingBox mkVar = this.f22488a;
        if (this.f22490c) {
            onVar = mkVar.f22430b;
        } else {
            onVar = mkVar.f22432d;
        }
        if (this.f22490c) {
            onVar2 = mkVar.f22431c;
        } else {
            onVar2 = mkVar.f22433e;
        }
        int b = m1972b((int) onVar2.f22727d);
        Codeword[] mlVarArr = this.f22489b;
        int i = -1;
        int i2 = 0;
        int i3 = 1;
        for (int b2 = m1972b((int) onVar.f22727d); b2 < b; b2++) {
            if (mlVarArr[b2] != null) {
                Codeword mlVar = mlVarArr[b2];
                mlVar.m2009b();
                int i4 = mlVar.f22443e - i;
                if (i4 == 0) {
                    i2++;
                } else if (i4 == 1) {
                    i3 = Math.max(i3, i2);
                    i = mlVar.f22443e;
                    i2 = 1;
                } else if (mlVar.f22443e >= mfVar.f22418e) {
                    mlVarArr[b2] = null;
                } else {
                    i = mlVar.f22443e;
                    i2 = 1;
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public final BarcodeMetadata m1970a() {
        Codeword[] mlVarArr = this.f22489b;
        BarcodeValue mjVar = new BarcodeValue();
        BarcodeValue mjVar2 = new BarcodeValue();
        BarcodeValue mjVar3 = new BarcodeValue();
        BarcodeValue mjVar4 = new BarcodeValue();
        for (Codeword mlVar : mlVarArr) {
            if (mlVar != null) {
                mlVar.m2009b();
                int i = mlVar.f22442d % 30;
                int i2 = mlVar.f22443e;
                if (!this.f22490c) {
                    i2 += 2;
                }
                switch (i2 % 3) {
                    case 0:
                        mjVar2.m2023a((i * 3) + 1);
                        continue;
                    case 1:
                        mjVar4.m2023a(i / 3);
                        mjVar3.m2023a(i % 3);
                        continue;
                    case 2:
                        mjVar.m2023a(i + 1);
                        continue;
                }
            }
        }
        if (mjVar.m2024a().length == 0 || mjVar2.m2024a().length == 0 || mjVar3.m2024a().length == 0 || mjVar4.m2024a().length == 0 || mjVar.m2024a()[0] <= 0 || mjVar2.m2024a()[0] + mjVar3.m2024a()[0] < 3 || mjVar2.m2024a()[0] + mjVar3.m2024a()[0] > 90) {
            return null;
        }
        BarcodeMetadata mfVar = new BarcodeMetadata(mjVar.m2024a()[0], mjVar2.m2024a()[0], mjVar3.m2024a()[0], mjVar4.m2024a()[0]);
        m1968a(mlVarArr, mfVar);
        return mfVar;
    }
}
