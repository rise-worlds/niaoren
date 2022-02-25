package p110z1;

/* renamed from: z1.ib */
/* loaded from: classes3.dex */
public final class ReedSolomonDecoder {

    /* renamed from: a */
    private final GenericGF f21951a;

    public ReedSolomonDecoder(GenericGF hzVar) {
        this.f21951a = hzVar;
    }

    /* renamed from: a */
    public final void m2470a(int[] iArr, int i) throws ReedSolomonException {
        GenericGFPoly iaVar = new GenericGFPoly(this.f21951a, iArr);
        int[] iArr2 = new int[i];
        boolean z = true;
        for (int i2 = 0; i2 < i; i2++) {
            GenericGF hzVar = this.f21951a;
            int b = iaVar.m2478b(hzVar.f21932i[hzVar.f21936m + i2]);
            iArr2[(i - 1) - i2] = b;
            if (b != 0) {
                z = false;
            }
        }
        if (!z) {
            GenericGFPoly iaVar2 = new GenericGFPoly(this.f21951a, iArr2);
            iaVar2 = this.f21951a.m2496a(i, 1);
            if (iaVar2.f21949a.length - 1 >= iaVar2.f21949a.length - 1) {
                iaVar2 = iaVar2;
                iaVar2 = iaVar2;
            }
            GenericGFPoly iaVar3 = this.f21951a.f21933j;
            iaVar3 = this.f21951a.f21934k;
            while (iaVar2.f21949a.length - 1 >= i / 2) {
                if (!iaVar2.m2483a()) {
                    GenericGFPoly iaVar4 = this.f21951a.f21933j;
                    int b2 = this.f21951a.m2494b(iaVar2.m2482a(iaVar2.f21949a.length - 1));
                    while (iaVar2.f21949a.length - 1 >= iaVar2.f21949a.length - 1 && !iaVar2.m2483a()) {
                        int length = (iaVar2.f21949a.length - 1) - (iaVar2.f21949a.length - 1);
                        int c = this.f21951a.m2490c(iaVar2.m2482a(iaVar2.f21949a.length - 1), b2);
                        iaVar4 = iaVar4.m2480a(this.f21951a.m2496a(length, c));
                        iaVar2 = iaVar2.m2480a(iaVar2.m2481a(length, c));
                    }
                    iaVar3 = iaVar4.m2477b(iaVar3).m2480a(iaVar3);
                    if (iaVar2.f21949a.length - 1 >= iaVar2.f21949a.length - 1) {
                        throw new IllegalStateException("Division algorithm failed to reduce polynomial?");
                    }
                } else {
                    throw new ReedSolomonException("r_{i-1} was zero");
                }
            }
            int a = iaVar3.m2482a(0);
            if (a != 0) {
                int b3 = this.f21951a.m2494b(a);
                GenericGFPoly[] iaVarArr = {iaVar3.m2475c(b3), iaVar2.m2475c(b3)};
                GenericGFPoly iaVar5 = iaVarArr[0];
                GenericGFPoly iaVar6 = iaVarArr[1];
                int[] a2 = m2473a(iaVar5);
                int[] a3 = m2471a(iaVar6, a2);
                for (int i3 = 0; i3 < a2.length; i3++) {
                    int length2 = (iArr.length - 1) - this.f21951a.m2497a(a2[i3]);
                    if (length2 >= 0) {
                        iArr[length2] = GenericGF.m2493b(iArr[length2], a3[i3]);
                    } else {
                        throw new ReedSolomonException("Bad error location");
                    }
                }
                return;
            }
            throw new ReedSolomonException("sigmaTilde(0) was zero");
        }
    }

    /* renamed from: a */
    private int[] m2471a(GenericGFPoly iaVar, int[] iArr) {
        int length = iArr.length;
        int[] iArr2 = new int[length];
        for (int i = 0; i < length; i++) {
            int b = this.f21951a.m2494b(iArr[i]);
            int i2 = 1;
            for (int i3 = 0; i3 < length; i3++) {
                if (i != i3) {
                    int c = this.f21951a.m2490c(iArr[i3], b);
                    i2 = this.f21951a.m2490c(i2, (c & 1) == 0 ? c | 1 : c & (-2));
                }
            }
            iArr2[i] = this.f21951a.m2490c(iaVar.m2478b(b), this.f21951a.m2494b(i2));
            if (this.f21951a.f21936m != 0) {
                iArr2[i] = this.f21951a.m2490c(iArr2[i], b);
            }
        }
        return iArr2;
    }

    /* renamed from: a */
    private GenericGFPoly[] m2472a(GenericGFPoly iaVar, GenericGFPoly iaVar2, int i) throws ReedSolomonException {
        if (iaVar2.f21949a.length - 1 < iaVar2.f21949a.length - 1) {
            iaVar2 = iaVar2;
            iaVar2 = iaVar2;
        }
        GenericGFPoly iaVar3 = this.f21951a.f21933j;
        iaVar3 = this.f21951a.f21934k;
        while (iaVar2.f21949a.length - 1 >= i / 2) {
            if (!iaVar2.m2483a()) {
                GenericGFPoly iaVar4 = this.f21951a.f21933j;
                int b = this.f21951a.m2494b(iaVar2.m2482a(iaVar2.f21949a.length - 1));
                while (iaVar2.f21949a.length - 1 >= iaVar2.f21949a.length - 1 && !iaVar2.m2483a()) {
                    int length = (iaVar2.f21949a.length - 1) - (iaVar2.f21949a.length - 1);
                    int c = this.f21951a.m2490c(iaVar2.m2482a(iaVar2.f21949a.length - 1), b);
                    iaVar4 = iaVar4.m2480a(this.f21951a.m2496a(length, c));
                    iaVar2 = iaVar2.m2480a(iaVar2.m2481a(length, c));
                }
                iaVar3 = iaVar4.m2477b(iaVar3).m2480a(iaVar3);
                if (iaVar2.f21949a.length - 1 >= iaVar2.f21949a.length - 1) {
                    throw new IllegalStateException("Division algorithm failed to reduce polynomial?");
                }
            } else {
                throw new ReedSolomonException("r_{i-1} was zero");
            }
        }
        int a = iaVar3.m2482a(0);
        if (a != 0) {
            int b2 = this.f21951a.m2494b(a);
            return new GenericGFPoly[]{iaVar3.m2475c(b2), iaVar2.m2475c(b2)};
        }
        throw new ReedSolomonException("sigmaTilde(0) was zero");
    }

    /* renamed from: a */
    private int[] m2473a(GenericGFPoly iaVar) throws ReedSolomonException {
        int length = iaVar.f21949a.length - 1;
        int i = 0;
        if (length == 1) {
            return new int[]{iaVar.m2482a(1)};
        }
        int[] iArr = new int[length];
        for (int i2 = 1; i2 < this.f21951a.f21935l && i < length; i2++) {
            if (iaVar.m2478b(i2) == 0) {
                iArr[i] = this.f21951a.m2494b(i2);
                i++;
            }
        }
        if (i == length) {
            return iArr;
        }
        throw new ReedSolomonException("Error locator degree does not match number of roots");
    }
}
