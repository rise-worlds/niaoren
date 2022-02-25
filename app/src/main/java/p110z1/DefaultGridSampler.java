package p110z1;

/* renamed from: z1.ih */
/* loaded from: classes3.dex */
public final class DefaultGridSampler extends GridSampler {
    @Override // p110z1.GridSampler
    /* renamed from: a */
    public final BitMatrix mo2439a(BitMatrix hyVar, int i, int i2, float f, float f2, float f3, float f4, float f5, float f6, float f7, float f8, float f9, float f10, float f11, float f12, float f13, float f14, float f15, float f16) throws NotFoundException {
        return mo2438a(hyVar, i, i2, PerspectiveTransform.m2427a(f, f2, f3, f4, f5, f6, f7, f8, f9, f10, f11, f12, f13, f14, f15, f16));
    }

    @Override // p110z1.GridSampler
    /* renamed from: a */
    public final BitMatrix mo2438a(BitMatrix hyVar, int i, int i2, PerspectiveTransform imVar) throws NotFoundException {
        int i3 = i2;
        PerspectiveTransform imVar2 = imVar;
        if (i <= 0 || i3 <= 0) {
            throw NotFoundException.m1647a();
        }
        BitMatrix hyVar2 = new BitMatrix(i, i3);
        float[] fArr = new float[i * 2];
        int i4 = 0;
        while (i4 < i3) {
            int length = fArr.length;
            float f = i4 + 0.5f;
            for (int i5 = 0; i5 < length; i5 += 2) {
                fArr[i5] = (i5 / 2) + 0.5f;
                fArr[i5 + 1] = f;
            }
            int length2 = fArr.length;
            float f2 = imVar2.f22014a;
            float f3 = imVar2.f22015b;
            float f4 = imVar2.f22016c;
            float f5 = imVar2.f22017d;
            float f6 = imVar2.f22018e;
            float f7 = imVar2.f22019f;
            float f8 = imVar2.f22020g;
            float f9 = imVar2.f22021h;
            float f10 = imVar2.f22022i;
            for (int i6 = 0; i6 < length2; i6 += 2) {
                float f11 = fArr[i6];
                int i7 = i6 + 1;
                float f12 = fArr[i7];
                float f13 = (f4 * f11) + (f7 * f12) + f10;
                fArr[i6] = (((f2 * f11) + (f5 * f12)) + f8) / f13;
                fArr[i7] = (((f11 * f3) + (f12 * f6)) + f9) / f13;
            }
            int i8 = hyVar.f21920a;
            int i9 = hyVar.f21921b;
            boolean z = true;
            for (int i10 = 0; i10 < fArr.length && z; i10 += 2) {
                int i11 = (int) fArr[i10];
                int i12 = i10 + 1;
                int i13 = (int) fArr[i12];
                if (i11 < -1 || i11 > i8 || i13 < -1 || i13 > i9) {
                    throw NotFoundException.m1647a();
                }
                if (i11 == -1) {
                    fArr[i10] = 0.0f;
                    z = true;
                } else if (i11 == i8) {
                    fArr[i10] = i8 - 1;
                    z = true;
                } else {
                    z = false;
                }
                if (i13 == -1) {
                    fArr[i12] = 0.0f;
                    z = true;
                } else if (i13 == i9) {
                    fArr[i12] = i9 - 1;
                    z = true;
                }
            }
            boolean z2 = true;
            for (int length3 = fArr.length - 2; length3 >= 0 && z2; length3 -= 2) {
                int i14 = (int) fArr[length3];
                int i15 = length3 + 1;
                int i16 = (int) fArr[i15];
                if (i14 < -1 || i14 > i8 || i16 < -1 || i16 > i9) {
                    throw NotFoundException.m1647a();
                }
                if (i14 == -1) {
                    fArr[length3] = 0.0f;
                    z2 = true;
                } else if (i14 == i8) {
                    fArr[length3] = i8 - 1;
                    z2 = true;
                } else {
                    z2 = false;
                }
                if (i16 == -1) {
                    fArr[i15] = 0.0f;
                    z2 = true;
                } else if (i16 == i9) {
                    fArr[i15] = i9 - 1;
                    z2 = true;
                }
            }
            for (int i17 = 0; i17 < length; i17 += 2) {
                try {
                    if (hyVar.m2519a((int) fArr[i17], (int) fArr[i17 + 1])) {
                        hyVar2.m2511b(i17 / 2, i4);
                    }
                } catch (ArrayIndexOutOfBoundsException unused) {
                    throw NotFoundException.m1647a();
                }
            }
            i4++;
            i3 = i2;
            imVar2 = imVar;
        }
        return hyVar2;
    }
}
