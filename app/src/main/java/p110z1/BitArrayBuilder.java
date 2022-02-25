package p110z1;

import java.util.List;

/* renamed from: z1.ka */
/* loaded from: classes3.dex */
final class BitArrayBuilder {
    private BitArrayBuilder() {
    }

    /* renamed from: a */
    private static BitArray m2227a(List<ExpandedPair> list) {
        int size = (list.size() << 1) - 1;
        if (list.get(list.size() - 1).f22262b == null) {
            size--;
        }
        BitArray huVar = new BitArray(size * 12);
        int i = list.get(0).f22262b.f22285a;
        int i2 = 0;
        for (int i3 = 11; i3 >= 0; i3--) {
            if (((1 << i3) & i) != 0) {
                huVar.m2545b(i2);
            }
            i2++;
        }
        for (int i4 = 1; i4 < list.size(); i4++) {
            ExpandedPair kuVar = list.get(i4);
            int i5 = kuVar.f22261a.f22285a;
            for (int i6 = 11; i6 >= 0; i6--) {
                if (((1 << i6) & i5) != 0) {
                    huVar.m2545b(i2);
                }
                i2++;
            }
            if (kuVar.f22262b != null) {
                int i7 = kuVar.f22262b.f22285a;
                for (int i8 = 11; i8 >= 0; i8--) {
                    if (((1 << i8) & i7) != 0) {
                        huVar.m2545b(i2);
                    }
                    i2++;
                }
            }
        }
        return huVar;
    }
}
