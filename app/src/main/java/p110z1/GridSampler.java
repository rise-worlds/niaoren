package p110z1;

/* renamed from: z1.ik */
/* loaded from: classes3.dex */
public abstract class GridSampler {

    /* renamed from: a */
    private static GridSampler f22007a = new DefaultGridSampler();

    /* renamed from: a */
    public abstract BitMatrix mo2439a(BitMatrix hyVar, int i, int i2, float f, float f2, float f3, float f4, float f5, float f6, float f7, float f8, float f9, float f10, float f11, float f12, float f13, float f14, float f15, float f16) throws NotFoundException;

    /* renamed from: a */
    public abstract BitMatrix mo2438a(BitMatrix hyVar, int i, int i2, PerspectiveTransform imVar) throws NotFoundException;

    /* renamed from: a */
    private static void m2436a(GridSampler ikVar) {
        f22007a = ikVar;
    }

    /* renamed from: a */
    public static GridSampler m2440a() {
        return f22007a;
    }

    /* renamed from: a */
    private static void m2437a(BitMatrix hyVar, float[] fArr) throws NotFoundException {
        int i = hyVar.f21920a;
        int i2 = hyVar.f21921b;
        boolean z = true;
        for (int i3 = 0; i3 < fArr.length && z; i3 += 2) {
            int i4 = (int) fArr[i3];
            int i5 = i3 + 1;
            int i6 = (int) fArr[i5];
            if (i4 < -1 || i4 > i || i6 < -1 || i6 > i2) {
                throw NotFoundException.m1647a();
            }
            if (i4 == -1) {
                fArr[i3] = 0.0f;
                z = true;
            } else if (i4 == i) {
                fArr[i3] = i - 1;
                z = true;
            } else {
                z = false;
            }
            if (i6 == -1) {
                fArr[i5] = 0.0f;
                z = true;
            } else if (i6 == i2) {
                fArr[i5] = i2 - 1;
                z = true;
            }
        }
        boolean z2 = true;
        for (int length = fArr.length - 2; length >= 0 && z2; length -= 2) {
            int i7 = (int) fArr[length];
            int i8 = length + 1;
            int i9 = (int) fArr[i8];
            if (i7 < -1 || i7 > i || i9 < -1 || i9 > i2) {
                throw NotFoundException.m1647a();
            }
            if (i7 == -1) {
                fArr[length] = 0.0f;
                z2 = true;
            } else if (i7 == i) {
                fArr[length] = i - 1;
                z2 = true;
            } else {
                z2 = false;
            }
            if (i9 == -1) {
                fArr[i8] = 0.0f;
                z2 = true;
            } else if (i9 == i2) {
                fArr[i8] = i2 - 1;
                z2 = true;
            }
        }
    }
}
