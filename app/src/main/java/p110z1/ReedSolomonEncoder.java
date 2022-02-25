package p110z1;

import java.util.ArrayList;
import java.util.List;

/* renamed from: z1.ic */
/* loaded from: classes3.dex */
public final class ReedSolomonEncoder {

    /* renamed from: a */
    private final GenericGF f21952a;

    /* renamed from: b */
    private final List<GenericGFPoly> f21953b = new ArrayList();

    public ReedSolomonEncoder(GenericGF hzVar) {
        this.f21952a = hzVar;
        this.f21953b.add(new GenericGFPoly(hzVar, new int[]{1}));
    }

    /* renamed from: a */
    private GenericGFPoly m2469a(int i) {
        if (i >= this.f21953b.size()) {
            List<GenericGFPoly> list = this.f21953b;
            GenericGFPoly iaVar = list.get(list.size() - 1);
            for (int size = this.f21953b.size(); size <= i; size++) {
                GenericGF hzVar = this.f21952a;
                iaVar = iaVar.m2477b(new GenericGFPoly(hzVar, new int[]{1, hzVar.f21932i[(size - 1) + hzVar.f21936m]}));
                this.f21953b.add(iaVar);
            }
        }
        return this.f21953b.get(i);
    }

    /* renamed from: a */
    public final void m2468a(int[] iArr, int i) {
        if (i != 0) {
            int length = iArr.length - i;
            if (length > 0) {
                GenericGFPoly a = m2469a(i);
                int[] iArr2 = new int[length];
                System.arraycopy(iArr, 0, iArr2, 0, length);
                int[] iArr3 = new GenericGFPoly(this.f21952a, iArr2).m2481a(i, 1).m2474c(a)[1].f21949a;
                int length2 = i - iArr3.length;
                for (int i2 = 0; i2 < length2; i2++) {
                    iArr[length + i2] = 0;
                }
                System.arraycopy(iArr3, 0, iArr, length + length2, iArr3.length);
                return;
            }
            throw new IllegalArgumentException("No data bytes provided");
        }
        throw new IllegalArgumentException("No error correction bytes");
    }
}
