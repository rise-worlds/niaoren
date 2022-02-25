package p110z1;

import java.util.ArrayList;

/* renamed from: z1.jt */
/* loaded from: classes3.dex */
public final class MultiDetector extends C5414nt {

    /* renamed from: b */
    private static final DetectorResult[] f22171b = new DetectorResult[0];

    public MultiDetector(BitMatrix hyVar) {
        super(hyVar);
    }

    /* renamed from: a */
    public final DetectorResult[] m2251a() throws NotFoundException {
        FinderPatternInfo[] a = new MultiFinderPatternFinder(this.f22641a, (byte) 0).m2250a();
        if (a.length != 0) {
            ArrayList arrayList = new ArrayList();
            for (FinderPatternInfo nwVar : a) {
                try {
                    arrayList.add(m1782a(nwVar));
                } catch (ReaderException unused) {
                }
            }
            if (arrayList.isEmpty()) {
                return f22171b;
            }
            return (DetectorResult[]) arrayList.toArray(new DetectorResult[arrayList.size()]);
        }
        throw NotFoundException.m1647a();
    }
}
