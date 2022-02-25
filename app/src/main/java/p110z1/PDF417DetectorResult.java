package p110z1;

import java.util.List;

/* renamed from: z1.mu */
/* loaded from: classes3.dex */
public final class PDF417DetectorResult {

    /* renamed from: a */
    public final BitMatrix f22507a;

    /* renamed from: b */
    public final List<ResultPoint[]> f22508b;

    public PDF417DetectorResult(BitMatrix hyVar, List<ResultPoint[]> list) {
        this.f22507a = hyVar;
        this.f22508b = list;
    }

    /* renamed from: a */
    private BitMatrix m1920a() {
        return this.f22507a;
    }

    /* renamed from: b */
    private List<ResultPoint[]> m1919b() {
        return this.f22508b;
    }
}
