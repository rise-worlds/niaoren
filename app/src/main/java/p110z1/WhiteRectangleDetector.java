package p110z1;

/* renamed from: z1.hx */
/* loaded from: classes3.dex */
public final class WhiteRectangleDetector {

    /* renamed from: a */
    private static final int f21911a = 10;

    /* renamed from: b */
    private static final int f21912b = 1;

    /* renamed from: c */
    private final BitMatrix f21913c;

    /* renamed from: d */
    private final int f21914d;

    /* renamed from: e */
    private final int f21915e;

    /* renamed from: f */
    private final int f21916f;

    /* renamed from: g */
    private final int f21917g;

    /* renamed from: h */
    private final int f21918h;

    /* renamed from: i */
    private final int f21919i;

    public WhiteRectangleDetector(BitMatrix hyVar, int i, int i2, int i3) throws NotFoundException {
        this.f21913c = hyVar;
        this.f21914d = hyVar.f21921b;
        this.f21915e = hyVar.f21920a;
        int i4 = i / 2;
        this.f21916f = i2 - i4;
        this.f21917g = i2 + i4;
        this.f21919i = i3 - i4;
        this.f21918h = i3 + i4;
        if (this.f21919i < 0 || this.f21916f < 0 || this.f21918h >= this.f21914d || this.f21917g >= this.f21915e) {
            throw NotFoundException.m1647a();
        }
    }

    /* renamed from: a */
    public final ResultPoint[] m2524a() throws NotFoundException {
        boolean z;
        int i = this.f21916f;
        int i2 = this.f21917g;
        int i3 = this.f21919i;
        int i4 = this.f21918h;
        int i5 = i;
        boolean z2 = true;
        boolean z3 = false;
        boolean z4 = false;
        boolean z5 = false;
        boolean z6 = false;
        boolean z7 = false;
        while (true) {
            if (!z2) {
                z = false;
                break;
            }
            boolean z8 = true;
            boolean z9 = false;
            while (true) {
                if ((z8 || !z3) && i2 < this.f21915e) {
                    z8 = m2522a(i3, i4, i2, false);
                    if (z8) {
                        i2++;
                        z3 = true;
                        z9 = true;
                    } else if (!z3) {
                        i2++;
                    }
                }
            }
            if (i2 >= this.f21915e) {
                z = true;
                break;
            }
            boolean z10 = true;
            while (true) {
                if ((z10 || !z4) && i4 < this.f21914d) {
                    z10 = m2522a(i5, i2, i4, true);
                    if (z10) {
                        i4++;
                        z4 = true;
                        z9 = true;
                    } else if (!z4) {
                        i4++;
                    }
                }
            }
            if (i4 >= this.f21914d) {
                z = true;
                break;
            }
            boolean z11 = true;
            while (true) {
                if ((z11 || !z5) && i5 >= 0) {
                    z11 = m2522a(i3, i4, i5, false);
                    if (z11) {
                        i5--;
                        z5 = true;
                        z9 = true;
                    } else if (!z5) {
                        i5--;
                    }
                }
            }
            if (i5 < 0) {
                z = true;
                break;
            }
            boolean z12 = true;
            while (true) {
                if ((z12 || !z7) && i3 >= 0) {
                    z12 = m2522a(i5, i2, i3, true);
                    if (z12) {
                        i3--;
                        z7 = true;
                        z9 = true;
                    } else if (!z7) {
                        i3--;
                    }
                }
            }
            if (i3 < 0) {
                z = true;
                break;
            }
            if (z9) {
                z6 = true;
            }
            z2 = z9;
        }
        if (z || !z6) {
            throw NotFoundException.m1647a();
        }
        int i6 = i2 - i5;
        ResultPoint onVar = null;
        ResultPoint onVar2 = null;
        for (int i7 = 1; onVar2 == null && i7 < i6; i7++) {
            onVar2 = m2523a(i5, i4 - i7, i5 + i7, i4);
        }
        if (onVar2 != null) {
            ResultPoint onVar3 = null;
            for (int i8 = 1; onVar3 == null && i8 < i6; i8++) {
                onVar3 = m2523a(i5, i3 + i8, i5 + i8, i3);
            }
            if (onVar3 != null) {
                ResultPoint onVar4 = null;
                for (int i9 = 1; onVar4 == null && i9 < i6; i9++) {
                    onVar4 = m2523a(i2, i3 + i9, i2 - i9, i3);
                }
                if (onVar4 != null) {
                    for (int i10 = 1; onVar == null && i10 < i6; i10++) {
                        onVar = m2523a(i2, i4 - i10, i2 - i10, i4);
                    }
                    if (onVar != null) {
                        float f = onVar.f22726c;
                        float f2 = onVar.f22727d;
                        float f3 = onVar2.f22726c;
                        float f4 = onVar2.f22727d;
                        float f5 = onVar4.f22726c;
                        float f6 = onVar4.f22727d;
                        float f7 = onVar3.f22726c;
                        float f8 = onVar3.f22727d;
                        return f < ((float) this.f21915e) / 2.0f ? new ResultPoint[]{new ResultPoint(f7 - 1.0f, f8 + 1.0f), new ResultPoint(f3 + 1.0f, f4 + 1.0f), new ResultPoint(f5 - 1.0f, f6 - 1.0f), new ResultPoint(f + 1.0f, f2 - 1.0f)} : new ResultPoint[]{new ResultPoint(f7 + 1.0f, f8 + 1.0f), new ResultPoint(f3 + 1.0f, f4 - 1.0f), new ResultPoint(f5 - 1.0f, f6 + 1.0f), new ResultPoint(f - 1.0f, f2 - 1.0f)};
                    }
                    throw NotFoundException.m1647a();
                }
                throw NotFoundException.m1647a();
            }
            throw NotFoundException.m1647a();
        }
        throw NotFoundException.m1647a();
    }

    /* renamed from: a */
    private ResultPoint m2523a(float f, float f2, float f3, float f4) {
        int a = MathUtils.m2531a(MathUtils.m2530a(f, f2, f3, f4));
        float f5 = a;
        float f6 = (f3 - f) / f5;
        float f7 = (f4 - f2) / f5;
        for (int i = 0; i < a; i++) {
            float f8 = i;
            int a2 = MathUtils.m2531a((f8 * f6) + f);
            int a3 = MathUtils.m2531a((f8 * f7) + f2);
            if (this.f21913c.m2519a(a2, a3)) {
                return new ResultPoint(a2, a3);
            }
        }
        return null;
    }

    /* renamed from: a */
    private boolean m2522a(int i, int i2, int i3, boolean z) {
        if (z) {
            while (i <= i2) {
                if (this.f21913c.m2519a(i, i3)) {
                    return true;
                }
                i++;
            }
            return false;
        }
        while (i <= i2) {
            if (this.f21913c.m2519a(i3, i)) {
                return true;
            }
            i++;
        }
        return false;
    }

    public WhiteRectangleDetector(BitMatrix hyVar) throws NotFoundException {
        this(hyVar, 10, hyVar.f21920a / 2, hyVar.f21921b / 2);
    }

    /* renamed from: a */
    private ResultPoint[] m2521a(ResultPoint onVar, ResultPoint onVar2, ResultPoint onVar3, ResultPoint onVar4) {
        float f = onVar.f22726c;
        float f2 = onVar.f22727d;
        float f3 = onVar2.f22726c;
        float f4 = onVar2.f22727d;
        float f5 = onVar3.f22726c;
        float f6 = onVar3.f22727d;
        float f7 = onVar4.f22726c;
        float f8 = onVar4.f22727d;
        return f < ((float) this.f21915e) / 2.0f ? new ResultPoint[]{new ResultPoint(f7 - 1.0f, f8 + 1.0f), new ResultPoint(f3 + 1.0f, f4 + 1.0f), new ResultPoint(f5 - 1.0f, f6 - 1.0f), new ResultPoint(f + 1.0f, f2 - 1.0f)} : new ResultPoint[]{new ResultPoint(f7 + 1.0f, f8 + 1.0f), new ResultPoint(f3 + 1.0f, f4 - 1.0f), new ResultPoint(f5 - 1.0f, f6 + 1.0f), new ResultPoint(f - 1.0f, f2 - 1.0f)};
    }
}
