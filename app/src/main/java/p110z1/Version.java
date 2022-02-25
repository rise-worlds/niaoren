package p110z1;

import com.tencent.smtt.sdk.TbsListener;
import org.apache.tools.tar.TarConstants;

/* renamed from: z1.iu */
/* loaded from: classes3.dex */
public final class Version {

    /* renamed from: h */
    private static final Version[] f22053h = {new Version(1, 10, 10, 8, 8, new C5373b(5, new C5372a(1, 3, (byte) 0), (byte) 0)), new Version(2, 12, 12, 10, 10, new C5373b(7, new C5372a(1, 5, (byte) 0), (byte) 0)), new Version(3, 14, 14, 12, 12, new C5373b(10, new C5372a(1, 8, (byte) 0), (byte) 0)), new Version(4, 16, 16, 14, 14, new C5373b(12, new C5372a(1, 12, (byte) 0), (byte) 0)), new Version(5, 18, 18, 16, 16, new C5373b(14, new C5372a(1, 18, (byte) 0), (byte) 0)), new Version(6, 20, 20, 18, 18, new C5373b(18, new C5372a(1, 22, (byte) 0), (byte) 0)), new Version(7, 22, 22, 20, 20, new C5373b(20, new C5372a(1, 30, (byte) 0), (byte) 0)), new Version(8, 24, 24, 22, 22, new C5373b(24, new C5372a(1, 36, (byte) 0), (byte) 0)), new Version(9, 26, 26, 24, 24, new C5373b(28, new C5372a(1, 44, (byte) 0), (byte) 0)), new Version(10, 32, 32, 14, 14, new C5373b(36, new C5372a(1, 62, (byte) 0), (byte) 0)), new Version(11, 36, 36, 16, 16, new C5373b(42, new C5372a(1, 86, (byte) 0), (byte) 0)), new Version(12, 40, 40, 18, 18, new C5373b(48, new C5372a(1, 114, (byte) 0), (byte) 0)), new Version(13, 44, 44, 20, 20, new C5373b(56, new C5372a(1, TbsListener.ErrorCode.NEEDDOWNLOAD_5, (byte) 0), (byte) 0)), new Version(14, 48, 48, 22, 22, new C5373b(68, new C5372a(1, TbsListener.ErrorCode.NEEDDOWNLOAD_FALSE_4, (byte) 0), (byte) 0)), new Version(15, 52, 52, 24, 24, new C5373b(42, new C5372a(2, 102, (byte) 0), (byte) 0)), new Version(16, 64, 64, 14, 14, new C5373b(56, new C5372a(2, TbsListener.ErrorCode.NEEDDOWNLOAD_1, (byte) 0), (byte) 0)), new Version(17, 72, 72, 16, 16, new C5373b(36, new C5372a(4, 92, (byte) 0), (byte) 0)), new Version(18, 80, 80, 18, 18, new C5373b(48, new C5372a(4, 114, (byte) 0), (byte) 0)), new Version(19, 88, 88, 20, 20, new C5373b(56, new C5372a(4, TbsListener.ErrorCode.NEEDDOWNLOAD_5, (byte) 0), (byte) 0)), new Version(20, 96, 96, 22, 22, new C5373b(68, new C5372a(4, TbsListener.ErrorCode.NEEDDOWNLOAD_FALSE_4, (byte) 0), (byte) 0)), new Version(21, 104, 104, 24, 24, new C5373b(56, new C5372a(6, 136, (byte) 0), (byte) 0)), new Version(22, 120, 120, 18, 18, new C5373b(68, new C5372a(6, TbsListener.ErrorCode.NEEDDOWNLOAD_FALSE_5, (byte) 0), (byte) 0)), new Version(23, 132, 132, 20, 20, new C5373b(62, new C5372a(8, TbsListener.ErrorCode.STARTDOWNLOAD_4, (byte) 0), (byte) 0)), new Version(24, TbsListener.ErrorCode.NEEDDOWNLOAD_5, TbsListener.ErrorCode.NEEDDOWNLOAD_5, 22, 22, new C5373b(new C5372a(8, 156, (byte) 0), new C5372a(2, TarConstants.PREFIXLEN, (byte) 0), (byte) 0)), new Version(25, 8, 18, 6, 16, new C5373b(7, new C5372a(1, 5, (byte) 0), (byte) 0)), new Version(26, 8, 32, 6, 14, new C5373b(11, new C5372a(1, 10, (byte) 0), (byte) 0)), new Version(27, 12, 26, 10, 24, new C5373b(14, new C5372a(1, 16, (byte) 0), (byte) 0)), new Version(28, 12, 36, 10, 16, new C5373b(18, new C5372a(1, 22, (byte) 0), (byte) 0)), new Version(29, 16, 36, 14, 16, new C5373b(24, new C5372a(1, 32, (byte) 0), (byte) 0)), new Version(30, 16, 48, 14, 22, new C5373b(28, new C5372a(1, 49, (byte) 0), (byte) 0))};

    /* renamed from: a */
    final int f22054a;

    /* renamed from: b */
    final int f22055b;

    /* renamed from: c */
    final int f22056c;

    /* renamed from: d */
    final int f22057d;

    /* renamed from: e */
    final int f22058e;

    /* renamed from: f */
    final C5373b f22059f;

    /* renamed from: g */
    final int f22060g;

    private Version(int i, int i2, int i3, int i4, int i5, C5373b bVar) {
        C5372a[] aVarArr;
        this.f22054a = i;
        this.f22055b = i2;
        this.f22056c = i3;
        this.f22057d = i4;
        this.f22058e = i5;
        this.f22059f = bVar;
        int i6 = bVar.f22063a;
        int i7 = 0;
        for (C5372a aVar : bVar.f22064b) {
            i7 += aVar.f22061a * (aVar.f22062b + i6);
        }
        this.f22060g = i7;
    }

    /* renamed from: a */
    private int m2391a() {
        return this.f22054a;
    }

    /* renamed from: b */
    private int m2389b() {
        return this.f22055b;
    }

    /* renamed from: c */
    private int m2388c() {
        return this.f22056c;
    }

    /* renamed from: d */
    private int m2387d() {
        return this.f22057d;
    }

    /* renamed from: e */
    private int m2386e() {
        return this.f22058e;
    }

    /* renamed from: f */
    private int m2385f() {
        return this.f22060g;
    }

    /* renamed from: g */
    private C5373b m2384g() {
        return this.f22059f;
    }

    /* renamed from: a */
    public static Version m2390a(int i, int i2) throws FormatException {
        Version[] iuVarArr;
        if ((i & 1) == 0 && (i2 & 1) == 0) {
            for (Version iuVar : f22053h) {
                if (iuVar.f22055b == i && iuVar.f22056c == i2) {
                    return iuVar;
                }
            }
            throw FormatException.m2059a();
        }
        throw FormatException.m2059a();
    }

    /* compiled from: Version.java */
    /* renamed from: z1.iu$b */
    /* loaded from: classes3.dex */
    static final class C5373b {

        /* renamed from: a */
        final int f22063a;

        /* renamed from: b */
        final C5372a[] f22064b;

        /* synthetic */ C5373b(int i, C5372a aVar, byte b) {
            this(i, aVar);
        }

        /* synthetic */ C5373b(C5372a aVar, C5372a aVar2, byte b) {
            this(aVar, aVar2);
        }

        private C5373b(int i, C5372a aVar) {
            this.f22063a = i;
            this.f22064b = new C5372a[]{aVar};
        }

        private C5373b(C5372a aVar, C5372a aVar2) {
            this.f22063a = 62;
            this.f22064b = new C5372a[]{aVar, aVar2};
        }

        /* renamed from: a */
        private int m2380a() {
            return this.f22063a;
        }

        /* renamed from: b */
        private C5372a[] m2379b() {
            return this.f22064b;
        }
    }

    /* compiled from: Version.java */
    /* renamed from: z1.iu$a */
    /* loaded from: classes3.dex */
    static final class C5372a {

        /* renamed from: a */
        final int f22061a;

        /* renamed from: b */
        final int f22062b;

        /* synthetic */ C5372a(int i, int i2, byte b) {
            this(i, i2);
        }

        private C5372a(int i, int i2) {
            this.f22061a = i;
            this.f22062b = i2;
        }

        /* renamed from: a */
        private int m2382a() {
            return this.f22061a;
        }

        /* renamed from: b */
        private int m2381b() {
            return this.f22062b;
        }
    }

    public final String toString() {
        return String.valueOf(this.f22054a);
    }

    /* renamed from: h */
    private static Version[] m2383h() {
        return new Version[]{new Version(1, 10, 10, 8, 8, new C5373b(5, new C5372a(1, 3, (byte) 0), (byte) 0)), new Version(2, 12, 12, 10, 10, new C5373b(7, new C5372a(1, 5, (byte) 0), (byte) 0)), new Version(3, 14, 14, 12, 12, new C5373b(10, new C5372a(1, 8, (byte) 0), (byte) 0)), new Version(4, 16, 16, 14, 14, new C5373b(12, new C5372a(1, 12, (byte) 0), (byte) 0)), new Version(5, 18, 18, 16, 16, new C5373b(14, new C5372a(1, 18, (byte) 0), (byte) 0)), new Version(6, 20, 20, 18, 18, new C5373b(18, new C5372a(1, 22, (byte) 0), (byte) 0)), new Version(7, 22, 22, 20, 20, new C5373b(20, new C5372a(1, 30, (byte) 0), (byte) 0)), new Version(8, 24, 24, 22, 22, new C5373b(24, new C5372a(1, 36, (byte) 0), (byte) 0)), new Version(9, 26, 26, 24, 24, new C5373b(28, new C5372a(1, 44, (byte) 0), (byte) 0)), new Version(10, 32, 32, 14, 14, new C5373b(36, new C5372a(1, 62, (byte) 0), (byte) 0)), new Version(11, 36, 36, 16, 16, new C5373b(42, new C5372a(1, 86, (byte) 0), (byte) 0)), new Version(12, 40, 40, 18, 18, new C5373b(48, new C5372a(1, 114, (byte) 0), (byte) 0)), new Version(13, 44, 44, 20, 20, new C5373b(56, new C5372a(1, TbsListener.ErrorCode.NEEDDOWNLOAD_5, (byte) 0), (byte) 0)), new Version(14, 48, 48, 22, 22, new C5373b(68, new C5372a(1, TbsListener.ErrorCode.NEEDDOWNLOAD_FALSE_4, (byte) 0), (byte) 0)), new Version(15, 52, 52, 24, 24, new C5373b(42, new C5372a(2, 102, (byte) 0), (byte) 0)), new Version(16, 64, 64, 14, 14, new C5373b(56, new C5372a(2, TbsListener.ErrorCode.NEEDDOWNLOAD_1, (byte) 0), (byte) 0)), new Version(17, 72, 72, 16, 16, new C5373b(36, new C5372a(4, 92, (byte) 0), (byte) 0)), new Version(18, 80, 80, 18, 18, new C5373b(48, new C5372a(4, 114, (byte) 0), (byte) 0)), new Version(19, 88, 88, 20, 20, new C5373b(56, new C5372a(4, TbsListener.ErrorCode.NEEDDOWNLOAD_5, (byte) 0), (byte) 0)), new Version(20, 96, 96, 22, 22, new C5373b(68, new C5372a(4, TbsListener.ErrorCode.NEEDDOWNLOAD_FALSE_4, (byte) 0), (byte) 0)), new Version(21, 104, 104, 24, 24, new C5373b(56, new C5372a(6, 136, (byte) 0), (byte) 0)), new Version(22, 120, 120, 18, 18, new C5373b(68, new C5372a(6, TbsListener.ErrorCode.NEEDDOWNLOAD_FALSE_5, (byte) 0), (byte) 0)), new Version(23, 132, 132, 20, 20, new C5373b(62, new C5372a(8, TbsListener.ErrorCode.STARTDOWNLOAD_4, (byte) 0), (byte) 0)), new Version(24, TbsListener.ErrorCode.NEEDDOWNLOAD_5, TbsListener.ErrorCode.NEEDDOWNLOAD_5, 22, 22, new C5373b(new C5372a(8, 156, (byte) 0), new C5372a(2, TarConstants.PREFIXLEN, (byte) 0), (byte) 0)), new Version(25, 8, 18, 6, 16, new C5373b(7, new C5372a(1, 5, (byte) 0), (byte) 0)), new Version(26, 8, 32, 6, 14, new C5373b(11, new C5372a(1, 10, (byte) 0), (byte) 0)), new Version(27, 12, 26, 10, 24, new C5373b(14, new C5372a(1, 16, (byte) 0), (byte) 0)), new Version(28, 12, 36, 10, 16, new C5373b(18, new C5372a(1, 22, (byte) 0), (byte) 0)), new Version(29, 16, 36, 14, 16, new C5373b(24, new C5372a(1, 32, (byte) 0), (byte) 0)), new Version(30, 16, 48, 14, 22, new C5373b(28, new C5372a(1, 49, (byte) 0), (byte) 0))};
    }
}
