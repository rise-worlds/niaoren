package p110z1;

import java.util.Formatter;

/* renamed from: z1.mn */
/* loaded from: classes3.dex */
final class DetectionResult {

    /* renamed from: e */
    private static final int f22482e = 2;

    /* renamed from: a */
    final BarcodeMetadata f22483a;

    /* renamed from: b */
    final DetectionResultColumn[] f22484b;

    /* renamed from: c */
    BoundingBox f22485c;

    /* renamed from: d */
    final int f22486d;

    /* JADX INFO: Access modifiers changed from: package-private */
    public DetectionResult(BarcodeMetadata mfVar, BoundingBox mkVar) {
        this.f22483a = mfVar;
        this.f22486d = mfVar.f22414a;
        this.f22485c = mkVar;
        this.f22484b = new DetectionResultColumn[this.f22486d + 2];
    }

    /* renamed from: a */
    private DetectionResultColumn[] m1993a() {
        int i;
        int i2;
        boolean z;
        m1986a(this.f22484b[0]);
        m1986a(this.f22484b[this.f22486d + 1]);
        int i3 = PDF417Common.f22405b;
        while (true) {
            DetectionResultColumn[] moVarArr = this.f22484b;
            if (!(moVarArr[0] == null || moVarArr[this.f22486d + 1] == null)) {
                Codeword[] mlVarArr = moVarArr[0].f22489b;
                Codeword[] mlVarArr2 = this.f22484b[this.f22486d + 1].f22489b;
                for (int i4 = 0; i4 < mlVarArr.length; i4++) {
                    if (!(mlVarArr[i4] == null || mlVarArr2[i4] == null || mlVarArr[i4].f22443e != mlVarArr2[i4].f22443e)) {
                        for (int i5 = 1; i5 <= this.f22486d; i5++) {
                            Codeword mlVar = this.f22484b[i5].f22489b[i4];
                            if (mlVar != null) {
                                mlVar.f22443e = mlVarArr[i4].f22443e;
                                if (!mlVar.m2011a()) {
                                    this.f22484b[i5].f22489b[i4] = null;
                                }
                            }
                        }
                    }
                }
            }
            DetectionResultColumn[] moVarArr2 = this.f22484b;
            if (moVarArr2[0] == null) {
                i = 0;
            } else {
                Codeword[] mlVarArr3 = moVarArr2[0].f22489b;
                i = 0;
                for (int i6 = 0; i6 < mlVarArr3.length; i6++) {
                    if (mlVarArr3[i6] != null) {
                        int i7 = mlVarArr3[i6].f22443e;
                        int i8 = i;
                        int i9 = 0;
                        for (int i10 = 1; i10 < this.f22486d + 1 && i9 < 2; i10++) {
                            Codeword mlVar2 = this.f22484b[i10].f22489b[i6];
                            if (mlVar2 != null) {
                                i9 = m1991a(i7, i9, mlVar2);
                                if (!mlVar2.m2011a()) {
                                    i8++;
                                }
                            }
                        }
                        i = i8;
                    }
                }
            }
            DetectionResultColumn[] moVarArr3 = this.f22484b;
            int i11 = this.f22486d;
            if (moVarArr3[i11 + 1] == null) {
                i2 = 0;
            } else {
                Codeword[] mlVarArr4 = moVarArr3[i11 + 1].f22489b;
                i2 = 0;
                for (int i12 = 0; i12 < mlVarArr4.length; i12++) {
                    if (mlVarArr4[i12] != null) {
                        int i13 = mlVarArr4[i12].f22443e;
                        int i14 = i2;
                        int i15 = 0;
                        for (int i16 = this.f22486d + 1; i16 > 0 && i15 < 2; i16--) {
                            Codeword mlVar3 = this.f22484b[i16].f22489b[i12];
                            if (mlVar3 != null) {
                                i15 = m1991a(i13, i15, mlVar3);
                                if (!mlVar3.m2011a()) {
                                    i14++;
                                }
                            }
                        }
                        i2 = i14;
                    }
                }
            }
            int i17 = i + i2;
            if (i17 == 0) {
                i17 = 0;
            } else {
                for (int i18 = 1; i18 < this.f22486d + 1; i18++) {
                    Codeword[] mlVarArr5 = this.f22484b[i18].f22489b;
                    for (int i19 = 0; i19 < mlVarArr5.length; i19++) {
                        if (mlVarArr5[i19] != null && !mlVarArr5[i19].m2011a()) {
                            Codeword mlVar4 = mlVarArr5[i19];
                            Codeword[] mlVarArr6 = this.f22484b[i18 - 1].f22489b;
                            DetectionResultColumn[] moVarArr4 = this.f22484b;
                            int i20 = i18 + 1;
                            Codeword[] mlVarArr7 = moVarArr4[i20] != null ? moVarArr4[i20].f22489b : mlVarArr6;
                            Codeword[] mlVarArr8 = new Codeword[14];
                            mlVarArr8[2] = mlVarArr6[i19];
                            mlVarArr8[3] = mlVarArr7[i19];
                            if (i19 > 0) {
                                int i21 = i19 - 1;
                                mlVarArr8[0] = mlVarArr5[i21];
                                mlVarArr8[4] = mlVarArr6[i21];
                                mlVarArr8[5] = mlVarArr7[i21];
                            }
                            if (i19 > 1) {
                                int i22 = i19 - 2;
                                mlVarArr8[8] = mlVarArr5[i22];
                                mlVarArr8[10] = mlVarArr6[i22];
                                mlVarArr8[11] = mlVarArr7[i22];
                            }
                            if (i19 < mlVarArr5.length - 1) {
                                int i23 = i19 + 1;
                                mlVarArr8[1] = mlVarArr5[i23];
                                mlVarArr8[6] = mlVarArr6[i23];
                                mlVarArr8[7] = mlVarArr7[i23];
                            }
                            if (i19 < mlVarArr5.length - 2) {
                                int i24 = i19 + 2;
                                mlVarArr8[9] = mlVarArr5[i24];
                                mlVarArr8[12] = mlVarArr6[i24];
                                mlVarArr8[13] = mlVarArr7[i24];
                            }
                            for (int i25 = 0; i25 < 14; i25++) {
                                Codeword mlVar5 = mlVarArr8[i25];
                                if (mlVar5 == null || !mlVar5.m2011a() || mlVar5.f22441c != mlVar4.f22441c) {
                                    z = false;
                                } else {
                                    mlVar4.f22443e = mlVar5.f22443e;
                                    z = true;
                                }
                                if (!z) {
                                }
                            }
                        }
                    }
                }
            }
            if (i17 <= 0 || i17 >= i3) {
                break;
            }
            i3 = i17;
        }
        return this.f22484b;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public final void m1986a(DetectionResultColumn moVar) {
        Codeword[] mlVarArr;
        ResultPoint onVar;
        ResultPoint onVar2;
        if (moVar != null) {
            DetectionResultRowIndicatorColumn mpVar = (DetectionResultRowIndicatorColumn) moVar;
            BarcodeMetadata mfVar = this.f22483a;
            Codeword[] mlVarArr2 = mpVar.f22489b;
            for (Codeword mlVar : mpVar.f22489b) {
                if (mlVar != null) {
                    mlVar.m2009b();
                }
            }
            mpVar.m1968a(mlVarArr2, mfVar);
            BoundingBox mkVar = mpVar.f22488a;
            if (mpVar.f22490c) {
                onVar = mkVar.f22430b;
            } else {
                onVar = mkVar.f22432d;
            }
            if (mpVar.f22490c) {
                onVar2 = mkVar.f22431c;
            } else {
                onVar2 = mkVar.f22433e;
            }
            int b = mpVar.m1972b((int) onVar.f22727d);
            int b2 = mpVar.m1972b((int) onVar2.f22727d);
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
    }

    /* renamed from: d */
    private void m1983d() {
        DetectionResultColumn[] moVarArr = this.f22484b;
        if (!(moVarArr[0] == null || moVarArr[this.f22486d + 1] == null)) {
            Codeword[] mlVarArr = moVarArr[0].f22489b;
            Codeword[] mlVarArr2 = this.f22484b[this.f22486d + 1].f22489b;
            for (int i = 0; i < mlVarArr.length; i++) {
                if (!(mlVarArr[i] == null || mlVarArr2[i] == null || mlVarArr[i].f22443e != mlVarArr2[i].f22443e)) {
                    for (int i2 = 1; i2 <= this.f22486d; i2++) {
                        Codeword mlVar = this.f22484b[i2].f22489b[i];
                        if (mlVar != null) {
                            mlVar.f22443e = mlVarArr[i].f22443e;
                            if (!mlVar.m2011a()) {
                                this.f22484b[i2].f22489b[i] = null;
                            }
                        }
                    }
                }
            }
        }
    }

    /* renamed from: e */
    private int m1982e() {
        DetectionResultColumn[] moVarArr = this.f22484b;
        int i = this.f22486d;
        if (moVarArr[i + 1] == null) {
            return 0;
        }
        Codeword[] mlVarArr = moVarArr[i + 1].f22489b;
        int i2 = 0;
        for (int i3 = 0; i3 < mlVarArr.length; i3++) {
            if (mlVarArr[i3] != null) {
                int i4 = mlVarArr[i3].f22443e;
                int i5 = i2;
                int i6 = 0;
                for (int i7 = this.f22486d + 1; i7 > 0 && i6 < 2; i7--) {
                    Codeword mlVar = this.f22484b[i7].f22489b[i3];
                    if (mlVar != null) {
                        i6 = m1991a(i4, i6, mlVar);
                        if (!mlVar.m2011a()) {
                            i5++;
                        }
                    }
                }
                i2 = i5;
            }
        }
        return i2;
    }

    /* renamed from: f */
    private int m1981f() {
        DetectionResultColumn[] moVarArr = this.f22484b;
        if (moVarArr[0] == null) {
            return 0;
        }
        Codeword[] mlVarArr = moVarArr[0].f22489b;
        int i = 0;
        for (int i2 = 0; i2 < mlVarArr.length; i2++) {
            if (mlVarArr[i2] != null) {
                int i3 = mlVarArr[i2].f22443e;
                int i4 = i;
                int i5 = 0;
                for (int i6 = 1; i6 < this.f22486d + 1 && i5 < 2; i6++) {
                    Codeword mlVar = this.f22484b[i6].f22489b[i2];
                    if (mlVar != null) {
                        i5 = m1991a(i3, i5, mlVar);
                        if (!mlVar.m2011a()) {
                            i4++;
                        }
                    }
                }
                i = i4;
            }
        }
        return i;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public static int m1991a(int i, int i2, Codeword mlVar) {
        if (mlVar == null || mlVar.m2011a()) {
            return i2;
        }
        if (!mlVar.m2010a(i)) {
            return i2 + 1;
        }
        mlVar.f22443e = i;
        return 0;
    }

    /* renamed from: a */
    private void m1990a(int i, int i2, Codeword[] mlVarArr) {
        boolean z;
        Codeword mlVar = mlVarArr[i2];
        Codeword[] mlVarArr2 = this.f22484b[i - 1].f22489b;
        DetectionResultColumn[] moVarArr = this.f22484b;
        int i3 = i + 1;
        Codeword[] mlVarArr3 = moVarArr[i3] != null ? moVarArr[i3].f22489b : mlVarArr2;
        Codeword[] mlVarArr4 = new Codeword[14];
        mlVarArr4[2] = mlVarArr2[i2];
        mlVarArr4[3] = mlVarArr3[i2];
        if (i2 > 0) {
            int i4 = i2 - 1;
            mlVarArr4[0] = mlVarArr[i4];
            mlVarArr4[4] = mlVarArr2[i4];
            mlVarArr4[5] = mlVarArr3[i4];
        }
        if (i2 > 1) {
            int i5 = i2 - 2;
            mlVarArr4[8] = mlVarArr[i5];
            mlVarArr4[10] = mlVarArr2[i5];
            mlVarArr4[11] = mlVarArr3[i5];
        }
        if (i2 < mlVarArr.length - 1) {
            int i6 = i2 + 1;
            mlVarArr4[1] = mlVarArr[i6];
            mlVarArr4[6] = mlVarArr2[i6];
            mlVarArr4[7] = mlVarArr3[i6];
        }
        if (i2 < mlVarArr.length - 2) {
            int i7 = i2 + 2;
            mlVarArr4[9] = mlVarArr[i7];
            mlVarArr4[12] = mlVarArr2[i7];
            mlVarArr4[13] = mlVarArr3[i7];
        }
        for (int i8 = 0; i8 < 14; i8++) {
            Codeword mlVar2 = mlVarArr4[i8];
            if (mlVar2 == null || !mlVar2.m2011a() || mlVar2.f22441c != mlVar.f22441c) {
                z = false;
            } else {
                mlVar.f22443e = mlVar2.f22443e;
                z = true;
            }
            if (z) {
                return;
            }
        }
    }

    /* renamed from: a */
    private static boolean m1987a(Codeword mlVar, Codeword mlVar2) {
        if (mlVar2 == null || !mlVar2.m2011a() || mlVar2.f22441c != mlVar.f22441c) {
            return false;
        }
        mlVar.f22443e = mlVar2.f22443e;
        return true;
    }

    /* renamed from: g */
    private int m1980g() {
        return this.f22486d;
    }

    /* renamed from: h */
    private int m1979h() {
        return this.f22483a.f22418e;
    }

    /* renamed from: i */
    private int m1978i() {
        return this.f22483a.f22415b;
    }

    /* renamed from: a */
    private void m1988a(BoundingBox mkVar) {
        this.f22485c = mkVar;
    }

    /* renamed from: j */
    private BoundingBox m1977j() {
        return this.f22485c;
    }

    /* renamed from: a */
    private void m1989a(int i, DetectionResultColumn moVar) {
        this.f22484b[i] = moVar;
    }

    /* renamed from: a */
    private DetectionResultColumn m1992a(int i) {
        return this.f22484b[i];
    }

    public final String toString() {
        DetectionResultColumn[] moVarArr = this.f22484b;
        DetectionResultColumn moVar = moVarArr[0];
        if (moVar == null) {
            moVar = moVarArr[this.f22486d + 1];
        }
        Formatter formatter = new Formatter();
        for (int i = 0; i < moVar.f22489b.length; i++) {
            try {
                formatter.format("CW %3d:", Integer.valueOf(i));
                for (int i2 = 0; i2 < this.f22486d + 2; i2++) {
                    if (this.f22484b[i2] == null) {
                        formatter.format("    |   ", new Object[0]);
                    } else {
                        Codeword mlVar = this.f22484b[i2].f22489b[i];
                        if (mlVar == null) {
                            formatter.format("    |   ", new Object[0]);
                        } else {
                            formatter.format(" %3d|%3d", Integer.valueOf(mlVar.f22443e), Integer.valueOf(mlVar.f22442d));
                        }
                    }
                }
                formatter.format("%n", new Object[0]);
            } finally {
                try {
                } catch (Throwable th) {
                    throw th;
                }
            }
        }
        String formatter2 = formatter.toString();
        formatter.close();
        return formatter2;
    }

    /* renamed from: b */
    private int m1985b() {
        int i;
        int i2;
        boolean z;
        DetectionResultColumn[] moVarArr = this.f22484b;
        if (!(moVarArr[0] == null || moVarArr[this.f22486d + 1] == null)) {
            Codeword[] mlVarArr = moVarArr[0].f22489b;
            Codeword[] mlVarArr2 = this.f22484b[this.f22486d + 1].f22489b;
            for (int i3 = 0; i3 < mlVarArr.length; i3++) {
                if (!(mlVarArr[i3] == null || mlVarArr2[i3] == null || mlVarArr[i3].f22443e != mlVarArr2[i3].f22443e)) {
                    for (int i4 = 1; i4 <= this.f22486d; i4++) {
                        Codeword mlVar = this.f22484b[i4].f22489b[i3];
                        if (mlVar != null) {
                            mlVar.f22443e = mlVarArr[i3].f22443e;
                            if (!mlVar.m2011a()) {
                                this.f22484b[i4].f22489b[i3] = null;
                            }
                        }
                    }
                }
            }
        }
        DetectionResultColumn[] moVarArr2 = this.f22484b;
        if (moVarArr2[0] == null) {
            i = 0;
        } else {
            Codeword[] mlVarArr3 = moVarArr2[0].f22489b;
            i = 0;
            for (int i5 = 0; i5 < mlVarArr3.length; i5++) {
                if (mlVarArr3[i5] != null) {
                    int i6 = mlVarArr3[i5].f22443e;
                    int i7 = i;
                    int i8 = 0;
                    for (int i9 = 1; i9 < this.f22486d + 1 && i8 < 2; i9++) {
                        Codeword mlVar2 = this.f22484b[i9].f22489b[i5];
                        if (mlVar2 != null) {
                            i8 = m1991a(i6, i8, mlVar2);
                            if (!mlVar2.m2011a()) {
                                i7++;
                            }
                        }
                    }
                    i = i7;
                }
            }
        }
        DetectionResultColumn[] moVarArr3 = this.f22484b;
        int i10 = this.f22486d;
        if (moVarArr3[i10 + 1] == null) {
            i2 = 0;
        } else {
            Codeword[] mlVarArr4 = moVarArr3[i10 + 1].f22489b;
            i2 = 0;
            for (int i11 = 0; i11 < mlVarArr4.length; i11++) {
                if (mlVarArr4[i11] != null) {
                    int i12 = mlVarArr4[i11].f22443e;
                    int i13 = i2;
                    int i14 = 0;
                    for (int i15 = this.f22486d + 1; i15 > 0 && i14 < 2; i15--) {
                        Codeword mlVar3 = this.f22484b[i15].f22489b[i11];
                        if (mlVar3 != null) {
                            i14 = m1991a(i12, i14, mlVar3);
                            if (!mlVar3.m2011a()) {
                                i13++;
                            }
                        }
                    }
                    i2 = i13;
                }
            }
        }
        int i16 = i + i2;
        if (i16 == 0) {
            return 0;
        }
        for (int i17 = 1; i17 < this.f22486d + 1; i17++) {
            Codeword[] mlVarArr5 = this.f22484b[i17].f22489b;
            for (int i18 = 0; i18 < mlVarArr5.length; i18++) {
                if (mlVarArr5[i18] != null && !mlVarArr5[i18].m2011a()) {
                    Codeword mlVar4 = mlVarArr5[i18];
                    Codeword[] mlVarArr6 = this.f22484b[i17 - 1].f22489b;
                    DetectionResultColumn[] moVarArr4 = this.f22484b;
                    int i19 = i17 + 1;
                    Codeword[] mlVarArr7 = moVarArr4[i19] != null ? moVarArr4[i19].f22489b : mlVarArr6;
                    Codeword[] mlVarArr8 = new Codeword[14];
                    mlVarArr8[2] = mlVarArr6[i18];
                    mlVarArr8[3] = mlVarArr7[i18];
                    if (i18 > 0) {
                        int i20 = i18 - 1;
                        mlVarArr8[0] = mlVarArr5[i20];
                        mlVarArr8[4] = mlVarArr6[i20];
                        mlVarArr8[5] = mlVarArr7[i20];
                    }
                    if (i18 > 1) {
                        int i21 = i18 - 2;
                        mlVarArr8[8] = mlVarArr5[i21];
                        mlVarArr8[10] = mlVarArr6[i21];
                        mlVarArr8[11] = mlVarArr7[i21];
                    }
                    if (i18 < mlVarArr5.length - 1) {
                        int i22 = i18 + 1;
                        mlVarArr8[1] = mlVarArr5[i22];
                        mlVarArr8[6] = mlVarArr6[i22];
                        mlVarArr8[7] = mlVarArr7[i22];
                    }
                    if (i18 < mlVarArr5.length - 2) {
                        int i23 = i18 + 2;
                        mlVarArr8[9] = mlVarArr5[i23];
                        mlVarArr8[12] = mlVarArr6[i23];
                        mlVarArr8[13] = mlVarArr7[i23];
                    }
                    for (int i24 = 0; i24 < 14; i24++) {
                        Codeword mlVar5 = mlVarArr8[i24];
                        if (mlVar5 == null || !mlVar5.m2011a() || mlVar5.f22441c != mlVar4.f22441c) {
                            z = false;
                        } else {
                            mlVar4.f22443e = mlVar5.f22443e;
                            z = true;
                        }
                        if (!z) {
                        }
                    }
                }
            }
        }
        return i16;
    }

    /* renamed from: c */
    private int m1984c() {
        int i;
        DetectionResultColumn[] moVarArr = this.f22484b;
        int i2 = 0;
        if (!(moVarArr[0] == null || moVarArr[this.f22486d + 1] == null)) {
            Codeword[] mlVarArr = moVarArr[0].f22489b;
            Codeword[] mlVarArr2 = this.f22484b[this.f22486d + 1].f22489b;
            for (int i3 = 0; i3 < mlVarArr.length; i3++) {
                if (!(mlVarArr[i3] == null || mlVarArr2[i3] == null || mlVarArr[i3].f22443e != mlVarArr2[i3].f22443e)) {
                    for (int i4 = 1; i4 <= this.f22486d; i4++) {
                        Codeword mlVar = this.f22484b[i4].f22489b[i3];
                        if (mlVar != null) {
                            mlVar.f22443e = mlVarArr[i3].f22443e;
                            if (!mlVar.m2011a()) {
                                this.f22484b[i4].f22489b[i3] = null;
                            }
                        }
                    }
                }
            }
        }
        DetectionResultColumn[] moVarArr2 = this.f22484b;
        if (moVarArr2[0] == null) {
            i = 0;
        } else {
            Codeword[] mlVarArr3 = moVarArr2[0].f22489b;
            i = 0;
            for (int i5 = 0; i5 < mlVarArr3.length; i5++) {
                if (mlVarArr3[i5] != null) {
                    int i6 = mlVarArr3[i5].f22443e;
                    int i7 = i;
                    int i8 = 0;
                    for (int i9 = 1; i9 < this.f22486d + 1 && i8 < 2; i9++) {
                        Codeword mlVar2 = this.f22484b[i9].f22489b[i5];
                        if (mlVar2 != null) {
                            i8 = m1991a(i6, i8, mlVar2);
                            if (!mlVar2.m2011a()) {
                                i7++;
                            }
                        }
                    }
                    i = i7;
                }
            }
        }
        DetectionResultColumn[] moVarArr3 = this.f22484b;
        int i10 = this.f22486d;
        if (moVarArr3[i10 + 1] != null) {
            Codeword[] mlVarArr4 = moVarArr3[i10 + 1].f22489b;
            int i11 = 0;
            for (int i12 = 0; i12 < mlVarArr4.length; i12++) {
                if (mlVarArr4[i12] != null) {
                    int i13 = mlVarArr4[i12].f22443e;
                    int i14 = i11;
                    int i15 = 0;
                    for (int i16 = this.f22486d + 1; i16 > 0 && i15 < 2; i16--) {
                        Codeword mlVar3 = this.f22484b[i16].f22489b[i12];
                        if (mlVar3 != null) {
                            i15 = m1991a(i13, i15, mlVar3);
                            if (!mlVar3.m2011a()) {
                                i14++;
                            }
                        }
                    }
                    i11 = i14;
                }
            }
            i2 = i11;
        }
        return i + i2;
    }
}
