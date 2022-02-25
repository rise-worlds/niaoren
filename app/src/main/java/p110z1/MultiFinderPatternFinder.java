package p110z1;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/* renamed from: z1.ju */
/* loaded from: classes3.dex */
final class MultiFinderPatternFinder extends FinderPatternFinder {

    /* renamed from: e */
    private static final FinderPatternInfo[] f22172e = new FinderPatternInfo[0];

    /* renamed from: f */
    private static final float f22173f = 180.0f;

    /* renamed from: g */
    private static final float f22174g = 9.0f;

    /* renamed from: h */
    private static final float f22175h = 0.05f;

    /* renamed from: i */
    private static final float f22176i = 0.5f;

    /* compiled from: MultiFinderPatternFinder.java */
    /* renamed from: z1.ju$a */
    /* loaded from: classes3.dex */
    private static final class C5388a implements Serializable, Comparator<C5415nu> {
        private C5388a() {
        }

        /* synthetic */ C5388a(byte b) {
            this();
        }

        @Override // java.util.Comparator
        public final /* bridge */ /* synthetic */ int compare(C5415nu nuVar, C5415nu nuVar2) {
            double d = nuVar2.f22643a - nuVar.f22643a;
            if (d < 0.0d) {
                return -1;
            }
            return d > 0.0d ? 1 : 0;
        }

        /* renamed from: a */
        private static int m2248a(C5415nu nuVar, C5415nu nuVar2) {
            double d = nuVar2.f22643a - nuVar.f22643a;
            if (d < 0.0d) {
                return -1;
            }
            return d > 0.0d ? 1 : 0;
        }
    }

    private MultiFinderPatternFinder(BitMatrix hyVar) {
        super(hyVar);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public MultiFinderPatternFinder(BitMatrix hyVar, byte b) {
        super(hyVar, null);
    }

    /* renamed from: b */
    private C5415nu[][] m2249b() throws NotFoundException {
        List<C5415nu> list = this.f22649d;
        int size = list.size();
        int i = 3;
        if (size < 3) {
            throw NotFoundException.m1647a();
        } else if (size == 3) {
            return new C5415nu[][]{new C5415nu[]{list.get(0), list.get(1), list.get(2)}};
        } else {
            Collections.sort(list, new C5388a((byte) 0));
            ArrayList arrayList = new ArrayList();
            int i2 = 0;
            while (i2 < size - 2) {
                C5415nu nuVar = list.get(i2);
                if (nuVar != null) {
                    int i3 = i2 + 1;
                    while (i3 < size - 1) {
                        C5415nu nuVar2 = list.get(i3);
                        if (nuVar2 != null) {
                            float min = (nuVar.f22643a - nuVar2.f22643a) / Math.min(nuVar.f22643a, nuVar2.f22643a);
                            float abs = Math.abs(nuVar.f22643a - nuVar2.f22643a);
                            float f = f22175h;
                            float f2 = f22176i;
                            if (abs <= f22176i || min < f22175h) {
                                int i4 = i3 + 1;
                                while (i4 < size) {
                                    C5415nu nuVar3 = list.get(i4);
                                    if (nuVar3 != null) {
                                        float min2 = (nuVar2.f22643a - nuVar3.f22643a) / Math.min(nuVar2.f22643a, nuVar3.f22643a);
                                        if (Math.abs(nuVar2.f22643a - nuVar3.f22643a) <= f2 || min2 < f) {
                                            C5415nu[] nuVarArr = new C5415nu[i];
                                            nuVarArr[0] = nuVar;
                                            nuVarArr[1] = nuVar2;
                                            nuVarArr[2] = nuVar3;
                                            ResultPoint.m1622a(nuVarArr);
                                            FinderPatternInfo nwVar = new FinderPatternInfo(nuVarArr);
                                            float a = ResultPoint.m1624a(nwVar.f22656b, nwVar.f22655a);
                                            float a2 = ResultPoint.m1624a(nwVar.f22657c, nwVar.f22655a);
                                            float a3 = ResultPoint.m1624a(nwVar.f22656b, nwVar.f22657c);
                                            float f3 = (a + a3) / (nuVar.f22643a * 2.0f);
                                            if (f3 <= f22173f && f3 >= f22174g && Math.abs((a - a3) / Math.min(a, a3)) < 0.1f) {
                                                float sqrt = (float) Math.sqrt((a * a) + (a3 * a3));
                                                if (Math.abs((a2 - sqrt) / Math.min(a2, sqrt)) < 0.1f) {
                                                    arrayList.add(nuVarArr);
                                                }
                                            }
                                        }
                                    }
                                    i4++;
                                    i = 3;
                                    f = f22175h;
                                    f2 = f22176i;
                                }
                            }
                        }
                        i3++;
                        i = 3;
                    }
                }
                i2++;
                i = 3;
            }
            if (!arrayList.isEmpty()) {
                return (C5415nu[][]) arrayList.toArray(new C5415nu[arrayList.size()]);
            }
            throw NotFoundException.m1647a();
        }
    }

    /* renamed from: a */
    public final FinderPatternInfo[] m2250a() throws NotFoundException {
        C5415nu[][] nuVarArr;
        BitMatrix hyVar = this.f22648c;
        int i = hyVar.f21921b;
        int i2 = hyVar.f21920a;
        int i3 = (i * 3) / 388;
        int i4 = 3;
        if (i3 < 3) {
            i3 = 3;
        }
        int[] iArr = new int[5];
        for (int i5 = i3 - 1; i5 < i; i5 += i3) {
            m1761b(iArr);
            int i6 = 0;
            for (int i7 = 0; i7 < i2; i7++) {
                if (hyVar.m2519a(i7, i5)) {
                    if ((i6 & 1) == 1) {
                        i6++;
                    }
                    iArr[i6] = iArr[i6] + 1;
                } else if ((i6 & 1) != 0) {
                    iArr[i6] = iArr[i6] + 1;
                } else if (i6 != 4) {
                    i6++;
                    iArr[i6] = iArr[i6] + 1;
                } else if (!m1766a(iArr) || !m1764a(iArr, i5, i7)) {
                    m1758c(iArr);
                    i6 = 3;
                } else {
                    m1761b(iArr);
                    i6 = 0;
                }
            }
            if (m1766a(iArr)) {
                m1764a(iArr, i5, i2);
            }
        }
        List<C5415nu> list = this.f22649d;
        int size = list.size();
        if (size >= 3) {
            if (size == 3) {
                nuVarArr = new C5415nu[][]{new C5415nu[]{list.get(0), list.get(1), list.get(2)}};
            } else {
                Collections.sort(list, new C5388a((byte) 0));
                ArrayList arrayList = new ArrayList();
                int i8 = 0;
                while (i8 < size - 2) {
                    C5415nu nuVar = list.get(i8);
                    if (nuVar != null) {
                        int i9 = i8 + 1;
                        while (i9 < size - 1) {
                            C5415nu nuVar2 = list.get(i9);
                            if (nuVar2 != null) {
                                float min = (nuVar.f22643a - nuVar2.f22643a) / Math.min(nuVar.f22643a, nuVar2.f22643a);
                                float abs = Math.abs(nuVar.f22643a - nuVar2.f22643a);
                                float f = f22175h;
                                float f2 = f22176i;
                                if (abs <= f22176i || min < f22175h) {
                                    int i10 = i9 + 1;
                                    while (i10 < size) {
                                        C5415nu nuVar3 = list.get(i10);
                                        if (nuVar3 != null) {
                                            float min2 = (nuVar2.f22643a - nuVar3.f22643a) / Math.min(nuVar2.f22643a, nuVar3.f22643a);
                                            if (Math.abs(nuVar2.f22643a - nuVar3.f22643a) <= f2 || min2 < f) {
                                                C5415nu[] nuVarArr2 = new C5415nu[i4];
                                                nuVarArr2[0] = nuVar;
                                                nuVarArr2[1] = nuVar2;
                                                nuVarArr2[2] = nuVar3;
                                                ResultPoint.m1622a(nuVarArr2);
                                                FinderPatternInfo nwVar = new FinderPatternInfo(nuVarArr2);
                                                float a = ResultPoint.m1624a(nwVar.f22656b, nwVar.f22655a);
                                                float a2 = ResultPoint.m1624a(nwVar.f22657c, nwVar.f22655a);
                                                float a3 = ResultPoint.m1624a(nwVar.f22656b, nwVar.f22657c);
                                                float f3 = (a + a3) / (nuVar.f22643a * 2.0f);
                                                if (f3 <= f22173f && f3 >= f22174g && Math.abs((a - a3) / Math.min(a, a3)) < 0.1f) {
                                                    float sqrt = (float) Math.sqrt((a * a) + (a3 * a3));
                                                    if (Math.abs((a2 - sqrt) / Math.min(a2, sqrt)) < 0.1f) {
                                                        arrayList.add(nuVarArr2);
                                                    }
                                                }
                                            }
                                        }
                                        i10++;
                                        i4 = 3;
                                        f = f22175h;
                                        f2 = f22176i;
                                    }
                                }
                            }
                            i9++;
                            i4 = 3;
                        }
                    }
                    i8++;
                    i4 = 3;
                }
                if (!arrayList.isEmpty()) {
                    nuVarArr = (C5415nu[][]) arrayList.toArray(new C5415nu[arrayList.size()]);
                } else {
                    throw NotFoundException.m1647a();
                }
            }
            ArrayList arrayList2 = new ArrayList();
            for (C5415nu[] nuVarArr3 : nuVarArr) {
                ResultPoint.m1622a(nuVarArr3);
                arrayList2.add(new FinderPatternInfo(nuVarArr3));
            }
            if (arrayList2.isEmpty()) {
                return f22172e;
            }
            return (FinderPatternInfo[]) arrayList2.toArray(new FinderPatternInfo[arrayList2.size()]);
        }
        throw NotFoundException.m1647a();
    }
}
