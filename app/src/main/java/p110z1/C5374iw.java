package p110z1;

import java.io.Serializable;
import java.util.Comparator;
import java.util.Map;
import org.apache.commons.p105io.IOUtils;

/* compiled from: Detector.java */
/* renamed from: z1.iw */
/* loaded from: classes3.dex */
public final class C5374iw {

    /* renamed from: a */
    public final BitMatrix f22065a;

    /* renamed from: b */
    public final WhiteRectangleDetector f22066b;

    public C5374iw(BitMatrix hyVar) throws NotFoundException {
        this.f22065a = hyVar;
        this.f22066b = new WhiteRectangleDetector(hyVar);
    }

    /* JADX WARN: Removed duplicated region for block: B:45:0x0180  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x0197  */
    /* JADX WARN: Removed duplicated region for block: B:49:0x019c  */
    /* JADX WARN: Removed duplicated region for block: B:63:0x0250  */
    /* JADX WARN: Removed duplicated region for block: B:66:0x0262  */
    /* JADX WARN: Removed duplicated region for block: B:67:0x0267  */
    /* JADX WARN: Removed duplicated region for block: B:70:0x026d  */
    /* JADX WARN: Removed duplicated region for block: B:71:0x0272  */
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private p110z1.DetectorResult m2376a() throws p110z1.NotFoundException {
        /*
            Method dump skipped, instructions count: 667
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: p110z1.C5374iw.m2376a():z1.ii");
    }

    /* renamed from: a */
    private ResultPoint m2370a(ResultPoint onVar, ResultPoint onVar2, ResultPoint onVar3, ResultPoint onVar4, int i, int i2) {
        float a = m2372a(onVar, onVar2) / i;
        float a2 = m2372a(onVar3, onVar4);
        ResultPoint onVar5 = new ResultPoint(onVar4.f22726c + (((onVar4.f22726c - onVar3.f22726c) / a2) * a), onVar4.f22727d + (a * ((onVar4.f22727d - onVar3.f22727d) / a2)));
        float a3 = m2372a(onVar, onVar3) / i2;
        float a4 = m2372a(onVar2, onVar4);
        ResultPoint onVar6 = new ResultPoint(onVar4.f22726c + (((onVar4.f22726c - onVar2.f22726c) / a4) * a3), onVar4.f22727d + (a3 * ((onVar4.f22727d - onVar2.f22727d) / a4)));
        if (m2373a(onVar5)) {
            return (m2373a(onVar6) && Math.abs(i - m2369b(onVar3, onVar5).f22069c) + Math.abs(i2 - m2369b(onVar2, onVar5).f22069c) > Math.abs(i - m2369b(onVar3, onVar6).f22069c) + Math.abs(i2 - m2369b(onVar2, onVar6).f22069c)) ? onVar6 : onVar5;
        }
        if (m2373a(onVar6)) {
            return onVar6;
        }
        return null;
    }

    /* renamed from: a */
    private ResultPoint m2371a(ResultPoint onVar, ResultPoint onVar2, ResultPoint onVar3, ResultPoint onVar4, int i) {
        float f = i;
        float a = m2372a(onVar, onVar2) / f;
        float a2 = m2372a(onVar3, onVar4);
        ResultPoint onVar5 = new ResultPoint(onVar4.f22726c + (((onVar4.f22726c - onVar3.f22726c) / a2) * a), onVar4.f22727d + (a * ((onVar4.f22727d - onVar3.f22727d) / a2)));
        float a3 = m2372a(onVar, onVar3) / f;
        float a4 = m2372a(onVar2, onVar4);
        ResultPoint onVar6 = new ResultPoint(onVar4.f22726c + (((onVar4.f22726c - onVar2.f22726c) / a4) * a3), onVar4.f22727d + (a3 * ((onVar4.f22727d - onVar2.f22727d) / a4)));
        if (m2373a(onVar5)) {
            return (m2373a(onVar6) && Math.abs(m2369b(onVar3, onVar5).f22069c - m2369b(onVar2, onVar5).f22069c) > Math.abs(m2369b(onVar3, onVar6).f22069c - m2369b(onVar2, onVar6).f22069c)) ? onVar6 : onVar5;
        }
        if (m2373a(onVar6)) {
            return onVar6;
        }
        return null;
    }

    /* renamed from: a */
    public static int m2372a(ResultPoint onVar, ResultPoint onVar2) {
        return MathUtils.m2531a(ResultPoint.m1624a(onVar, onVar2));
    }

    /* renamed from: a */
    public static void m2375a(Map<ResultPoint, Integer> map, ResultPoint onVar) {
        Integer num = map.get(onVar);
        int i = 1;
        if (num != null) {
            i = 1 + num.intValue();
        }
        map.put(onVar, Integer.valueOf(i));
    }

    /* renamed from: a */
    public static BitMatrix m2374a(BitMatrix hyVar, ResultPoint onVar, ResultPoint onVar2, ResultPoint onVar3, ResultPoint onVar4, int i, int i2) throws NotFoundException {
        float f = i - 0.5f;
        float f2 = i2 - 0.5f;
        return GridSampler.m2440a().mo2439a(hyVar, i, i2, 0.5f, 0.5f, f, 0.5f, f, f2, 0.5f, f2, onVar.f22726c, onVar.f22727d, onVar4.f22726c, onVar4.f22727d, onVar3.f22726c, onVar3.f22727d, onVar2.f22726c, onVar2.f22727d);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: Detector.java */
    /* renamed from: z1.iw$a */
    /* loaded from: classes3.dex */
    public static final class C5376a {

        /* renamed from: a */
        public final ResultPoint f22067a;

        /* renamed from: b */
        public final ResultPoint f22068b;

        /* renamed from: c */
        public final int f22069c;

        /* synthetic */ C5376a(ResultPoint onVar, ResultPoint onVar2, int i, byte b) {
            this(onVar, onVar2, i);
        }

        private C5376a(ResultPoint onVar, ResultPoint onVar2, int i) {
            this.f22067a = onVar;
            this.f22068b = onVar2;
            this.f22069c = i;
        }

        /* renamed from: a */
        private ResultPoint m2368a() {
            return this.f22067a;
        }

        /* renamed from: b */
        private ResultPoint m2367b() {
            return this.f22068b;
        }

        /* renamed from: c */
        private int m2366c() {
            return this.f22069c;
        }

        public final String toString() {
            return this.f22067a + "/" + this.f22068b + IOUtils.DIR_SEPARATOR_UNIX + this.f22069c;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: Detector.java */
    /* renamed from: z1.iw$b */
    /* loaded from: classes3.dex */
    public static final class C5377b implements Serializable, Comparator<C5376a> {
        private C5377b() {
        }

        public /* synthetic */ C5377b(byte b) {
            this();
        }

        @Override // java.util.Comparator
        public final /* bridge */ /* synthetic */ int compare(C5376a aVar, C5376a aVar2) {
            return aVar.f22069c - aVar2.f22069c;
        }

        /* renamed from: a */
        private static int m2365a(C5376a aVar, C5376a aVar2) {
            return aVar.f22069c - aVar2.f22069c;
        }
    }

    /* renamed from: a */
    public final boolean m2373a(ResultPoint onVar) {
        return onVar.f22726c >= 0.0f && onVar.f22726c < ((float) this.f22065a.f21920a) && onVar.f22727d > 0.0f && onVar.f22727d < ((float) this.f22065a.f21921b);
    }

    /* renamed from: b */
    public final C5376a m2369b(ResultPoint onVar, ResultPoint onVar2) {
        int i;
        C5374iw iwVar = this;
        int i2 = (int) onVar.f22726c;
        int i3 = (int) onVar.f22727d;
        int i4 = (int) onVar2.f22726c;
        int i5 = (int) onVar2.f22727d;
        boolean z = Math.abs(i5 - i3) > Math.abs(i4 - i2);
        if (z) {
            i3 = i2;
            i2 = i3;
            i5 = i4;
            i4 = i5;
        }
        int abs = Math.abs(i4 - i2);
        int abs2 = Math.abs(i5 - i3);
        int i6 = (-abs) / 2;
        int i7 = -1;
        int i8 = i3 < i5 ? 1 : -1;
        if (i2 < i4) {
            i7 = 1;
        }
        boolean a = iwVar.f22065a.m2519a(z ? i3 : i2, z ? i2 : i3);
        int i9 = 0;
        while (i2 != i4) {
            BitMatrix hyVar = iwVar.f22065a;
            int i10 = z ? i3 : i2;
            if (z) {
                i4 = i4;
                i = i2;
            } else {
                i4 = i4;
                i = i3;
            }
            boolean a2 = hyVar.m2519a(i10, i);
            if (a2 != a) {
                i9++;
                a = a2;
            }
            i6 += abs2;
            if (i6 > 0) {
                if (i3 == i5) {
                    break;
                }
                i3 += i8;
                i6 -= abs;
            }
            i2 += i7;
            iwVar = this;
        }
        return new C5376a(onVar, onVar2, i9, (byte) 0);
    }
}
