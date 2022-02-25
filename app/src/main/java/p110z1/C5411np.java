package p110z1;

import com.tencent.smtt.sdk.TbsListener;

/* compiled from: Version.java */
/* renamed from: z1.np */
/* loaded from: classes3.dex */
public final class C5411np {

    /* renamed from: d */
    private static final int[] f22620d = {31892, 34236, 39577, 42195, 48118, 51042, 55367, 58893, 63784, 68472, 70749, 76311, 79154, 84390, 87683, 92361, 96236, 102084, 102881, 110507, 110734, 117786, 119615, 126325, 127568, 133589, 136944, 141498, 145311, 150283, 152622, 158308, 161089, 167017};

    /* renamed from: e */
    private static final C5411np[] f22621e = m1802f();

    /* renamed from: a */
    public final int f22622a;

    /* renamed from: b */
    public final int[] f22623b;

    /* renamed from: c */
    public final int f22624c;

    /* renamed from: f */
    private final C5413b[] f22625f;

    private C5411np(int i, int[] iArr, C5413b... bVarArr) {
        C5412a[] aVarArr;
        this.f22622a = i;
        this.f22623b = iArr;
        this.f22625f = bVarArr;
        int i2 = bVarArr[0].f22628a;
        int i3 = 0;
        for (C5412a aVar : bVarArr[0].f22629b) {
            i3 += aVar.f22626a * (aVar.f22627b + i2);
        }
        this.f22624c = i3;
    }

    /* renamed from: b */
    private int m1808b() {
        return this.f22622a;
    }

    /* renamed from: c */
    private int[] m1806c() {
        return this.f22623b;
    }

    /* renamed from: d */
    private int m1804d() {
        return this.f22624c;
    }

    /* renamed from: a */
    public final int m1811a() {
        return (this.f22622a * 4) + 17;
    }

    /* renamed from: a */
    public final C5413b m1809a(ErrorCorrectionLevel nlVar) {
        return this.f22625f[nlVar.ordinal()];
    }

    /* renamed from: a */
    public static C5411np m1810a(int i) throws FormatException {
        if (i % 4 == 1) {
            try {
                return m1807b((i - 17) / 4);
            } catch (IllegalArgumentException unused) {
                throw FormatException.m2059a();
            }
        } else {
            throw FormatException.m2059a();
        }
    }

    /* renamed from: b */
    public static C5411np m1807b(int i) {
        if (i > 0 && i <= 40) {
            return f22621e[i - 1];
        }
        throw new IllegalArgumentException();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: c */
    public static C5411np m1805c(int i) {
        int i2 = 0;
        int i3 = Integer.MAX_VALUE;
        int i4 = 0;
        while (true) {
            int[] iArr = f22620d;
            if (i2 < iArr.length) {
                int i5 = iArr[i2];
                if (i5 == i) {
                    return m1807b(i2 + 7);
                }
                int a = FormatInformation.m1820a(i, i5);
                if (a < i3) {
                    i4 = i2 + 7;
                    i3 = a;
                }
                i2++;
            } else if (i3 <= 3) {
                return m1807b(i4);
            } else {
                return null;
            }
        }
    }

    /* renamed from: e */
    private BitMatrix m1803e() {
        int a = m1811a();
        BitMatrix hyVar = new BitMatrix(a);
        hyVar.m2518a(0, 0, 9, 9);
        int i = a - 8;
        hyVar.m2518a(i, 0, 8, 9);
        hyVar.m2518a(0, i, 9, 8);
        int length = this.f22623b.length;
        for (int i2 = 0; i2 < length; i2++) {
            int i3 = this.f22623b[i2] - 2;
            for (int i4 = 0; i4 < length; i4++) {
                if (!((i2 == 0 && (i4 == 0 || i4 == length - 1)) || (i2 == length - 1 && i4 == 0))) {
                    hyVar.m2518a(this.f22623b[i4] - 2, i3, 5, 5);
                }
            }
        }
        int i5 = a - 17;
        hyVar.m2518a(6, 9, 1, i5);
        hyVar.m2518a(9, 6, i5, 1);
        if (this.f22622a > 6) {
            int i6 = a - 11;
            hyVar.m2518a(i6, 0, 3, 6);
            hyVar.m2518a(0, i6, 6, 3);
        }
        return hyVar;
    }

    /* compiled from: Version.java */
    /* renamed from: z1.np$b */
    /* loaded from: classes3.dex */
    public static final class C5413b {

        /* renamed from: a */
        final int f22628a;

        /* renamed from: b */
        final C5412a[] f22629b;

        C5413b(int i, C5412a... aVarArr) {
            this.f22628a = i;
            this.f22629b = aVarArr;
        }

        /* renamed from: c */
        private int m1797c() {
            return this.f22628a;
        }

        /* renamed from: a */
        public final int m1799a() {
            int i = 0;
            for (C5412a aVar : this.f22629b) {
                i += aVar.f22626a;
            }
            return i;
        }

        /* renamed from: b */
        public final int m1798b() {
            return this.f22628a * m1799a();
        }

        /* renamed from: d */
        private C5412a[] m1796d() {
            return this.f22629b;
        }
    }

    /* compiled from: Version.java */
    /* renamed from: z1.np$a */
    /* loaded from: classes3.dex */
    public static final class C5412a {

        /* renamed from: a */
        final int f22626a;

        /* renamed from: b */
        final int f22627b;

        C5412a(int i, int i2) {
            this.f22626a = i;
            this.f22627b = i2;
        }

        /* renamed from: a */
        private int m1801a() {
            return this.f22626a;
        }

        /* renamed from: b */
        private int m1800b() {
            return this.f22627b;
        }
    }

    public final String toString() {
        return String.valueOf(this.f22622a);
    }

    /* renamed from: f */
    private static C5411np[] m1802f() {
        return new C5411np[]{new C5411np(1, new int[0], new C5413b(7, new C5412a(1, 19)), new C5413b(10, new C5412a(1, 16)), new C5413b(13, new C5412a(1, 13)), new C5413b(17, new C5412a(1, 9))), new C5411np(2, new int[]{6, 18}, new C5413b(10, new C5412a(1, 34)), new C5413b(16, new C5412a(1, 28)), new C5413b(22, new C5412a(1, 22)), new C5413b(28, new C5412a(1, 16))), new C5411np(3, new int[]{6, 22}, new C5413b(15, new C5412a(1, 55)), new C5413b(26, new C5412a(1, 44)), new C5413b(18, new C5412a(2, 17)), new C5413b(22, new C5412a(2, 13))), new C5411np(4, new int[]{6, 26}, new C5413b(20, new C5412a(1, 80)), new C5413b(18, new C5412a(2, 32)), new C5413b(26, new C5412a(2, 24)), new C5413b(16, new C5412a(4, 9))), new C5411np(5, new int[]{6, 30}, new C5413b(26, new C5412a(1, 108)), new C5413b(24, new C5412a(2, 43)), new C5413b(18, new C5412a(2, 15), new C5412a(2, 16)), new C5413b(22, new C5412a(2, 11), new C5412a(2, 12))), new C5411np(6, new int[]{6, 34}, new C5413b(18, new C5412a(2, 68)), new C5413b(16, new C5412a(4, 27)), new C5413b(24, new C5412a(4, 19)), new C5413b(28, new C5412a(4, 15))), new C5411np(7, new int[]{6, 22, 38}, new C5413b(20, new C5412a(2, 78)), new C5413b(18, new C5412a(4, 31)), new C5413b(18, new C5412a(2, 14), new C5412a(4, 15)), new C5413b(26, new C5412a(4, 13), new C5412a(1, 14))), new C5411np(8, new int[]{6, 24, 42}, new C5413b(24, new C5412a(2, 97)), new C5413b(22, new C5412a(2, 38), new C5412a(2, 39)), new C5413b(22, new C5412a(4, 18), new C5412a(2, 19)), new C5413b(26, new C5412a(4, 14), new C5412a(2, 15))), new C5411np(9, new int[]{6, 26, 46}, new C5413b(30, new C5412a(2, 116)), new C5413b(22, new C5412a(3, 36), new C5412a(2, 37)), new C5413b(20, new C5412a(4, 16), new C5412a(4, 17)), new C5413b(24, new C5412a(4, 12), new C5412a(4, 13))), new C5411np(10, new int[]{6, 28, 50}, new C5413b(18, new C5412a(2, 68), new C5412a(2, 69)), new C5413b(26, new C5412a(4, 43), new C5412a(1, 44)), new C5413b(24, new C5412a(6, 19), new C5412a(2, 20)), new C5413b(28, new C5412a(6, 15), new C5412a(2, 16))), new C5411np(11, new int[]{6, 30, 54}, new C5413b(20, new C5412a(4, 81)), new C5413b(30, new C5412a(1, 50), new C5412a(4, 51)), new C5413b(28, new C5412a(4, 22), new C5412a(4, 23)), new C5413b(24, new C5412a(3, 12), new C5412a(8, 13))), new C5411np(12, new int[]{6, 32, 58}, new C5413b(24, new C5412a(2, 92), new C5412a(2, 93)), new C5413b(22, new C5412a(6, 36), new C5412a(2, 37)), new C5413b(26, new C5412a(4, 20), new C5412a(6, 21)), new C5413b(28, new C5412a(7, 14), new C5412a(4, 15))), new C5411np(13, new int[]{6, 34, 62}, new C5413b(26, new C5412a(4, 107)), new C5413b(22, new C5412a(8, 37), new C5412a(1, 38)), new C5413b(24, new C5412a(8, 20), new C5412a(4, 21)), new C5413b(22, new C5412a(12, 11), new C5412a(4, 12))), new C5411np(14, new int[]{6, 26, 46, 66}, new C5413b(30, new C5412a(3, 115), new C5412a(1, 116)), new C5413b(24, new C5412a(4, 40), new C5412a(5, 41)), new C5413b(20, new C5412a(11, 16), new C5412a(5, 17)), new C5413b(24, new C5412a(11, 12), new C5412a(5, 13))), new C5411np(15, new int[]{6, 26, 48, 70}, new C5413b(22, new C5412a(5, 87), new C5412a(1, 88)), new C5413b(24, new C5412a(5, 41), new C5412a(5, 42)), new C5413b(30, new C5412a(5, 24), new C5412a(7, 25)), new C5413b(24, new C5412a(11, 12), new C5412a(7, 13))), new C5411np(16, new int[]{6, 26, 50, 74}, new C5413b(24, new C5412a(5, 98), new C5412a(1, 99)), new C5413b(28, new C5412a(7, 45), new C5412a(3, 46)), new C5413b(24, new C5412a(15, 19), new C5412a(2, 20)), new C5413b(30, new C5412a(3, 15), new C5412a(13, 16))), new C5411np(17, new int[]{6, 30, 54, 78}, new C5413b(28, new C5412a(1, 107), new C5412a(5, 108)), new C5413b(28, new C5412a(10, 46), new C5412a(1, 47)), new C5413b(28, new C5412a(1, 22), new C5412a(15, 23)), new C5413b(28, new C5412a(2, 14), new C5412a(17, 15))), new C5411np(18, new int[]{6, 30, 56, 82}, new C5413b(30, new C5412a(5, 120), new C5412a(1, TbsListener.ErrorCode.THREAD_INIT_ERROR)), new C5413b(26, new C5412a(9, 43), new C5412a(4, 44)), new C5413b(28, new C5412a(17, 22), new C5412a(1, 23)), new C5413b(28, new C5412a(2, 14), new C5412a(19, 15))), new C5411np(19, new int[]{6, 30, 58, 86}, new C5413b(28, new C5412a(3, 113), new C5412a(4, 114)), new C5413b(26, new C5412a(3, 44), new C5412a(11, 45)), new C5413b(26, new C5412a(17, 21), new C5412a(4, 22)), new C5413b(26, new C5412a(9, 13), new C5412a(16, 14))), new C5411np(20, new int[]{6, 34, 62, 90}, new C5413b(28, new C5412a(3, 107), new C5412a(5, 108)), new C5413b(26, new C5412a(3, 41), new C5412a(13, 42)), new C5413b(30, new C5412a(15, 24), new C5412a(5, 25)), new C5413b(28, new C5412a(15, 15), new C5412a(10, 16))), new C5411np(21, new int[]{6, 28, 50, 72, 94}, new C5413b(28, new C5412a(4, 116), new C5412a(4, 117)), new C5413b(26, new C5412a(17, 42)), new C5413b(28, new C5412a(17, 22), new C5412a(6, 23)), new C5413b(30, new C5412a(19, 16), new C5412a(6, 17))), new C5411np(22, new int[]{6, 26, 50, 74, 98}, new C5413b(28, new C5412a(2, 111), new C5412a(7, 112)), new C5413b(28, new C5412a(17, 46)), new C5413b(30, new C5412a(7, 24), new C5412a(16, 25)), new C5413b(24, new C5412a(34, 13))), new C5411np(23, new int[]{6, 30, 54, 78, 102}, new C5413b(30, new C5412a(4, TbsListener.ErrorCode.THREAD_INIT_ERROR), new C5412a(5, TbsListener.ErrorCode.DOWNLOAD_HAS_COPY_TBS_ERROR)), new C5413b(28, new C5412a(4, 47), new C5412a(14, 48)), new C5413b(30, new C5412a(11, 24), new C5412a(14, 25)), new C5413b(30, new C5412a(16, 15), new C5412a(14, 16))), new C5411np(24, new int[]{6, 28, 54, 80, 106}, new C5413b(30, new C5412a(6, 117), new C5412a(4, 118)), new C5413b(28, new C5412a(6, 45), new C5412a(14, 46)), new C5413b(30, new C5412a(11, 24), new C5412a(16, 25)), new C5413b(30, new C5412a(30, 16), new C5412a(2, 17))), new C5411np(25, new int[]{6, 32, 58, 84, 110}, new C5413b(26, new C5412a(8, 106), new C5412a(4, 107)), new C5413b(28, new C5412a(8, 47), new C5412a(13, 48)), new C5413b(30, new C5412a(7, 24), new C5412a(22, 25)), new C5413b(30, new C5412a(22, 15), new C5412a(13, 16))), new C5411np(26, new int[]{6, 30, 58, 86, 114}, new C5413b(28, new C5412a(10, 114), new C5412a(2, 115)), new C5413b(28, new C5412a(19, 46), new C5412a(4, 47)), new C5413b(28, new C5412a(28, 22), new C5412a(6, 23)), new C5413b(30, new C5412a(33, 16), new C5412a(4, 17))), new C5411np(27, new int[]{6, 34, 62, 90, 118}, new C5413b(30, new C5412a(8, TbsListener.ErrorCode.DOWNLOAD_HAS_COPY_TBS_ERROR), new C5412a(4, TbsListener.ErrorCode.DOWNLOAD_RETRYTIMES302_EXCEED)), new C5413b(28, new C5412a(22, 45), new C5412a(3, 46)), new C5413b(30, new C5412a(8, 23), new C5412a(26, 24)), new C5413b(30, new C5412a(12, 15), new C5412a(28, 16))), new C5411np(28, new int[]{6, 26, 50, 74, 98, TbsListener.ErrorCode.DOWNLOAD_HAS_COPY_TBS_ERROR}, new C5413b(30, new C5412a(3, 117), new C5412a(10, 118)), new C5413b(28, new C5412a(3, 45), new C5412a(23, 46)), new C5413b(30, new C5412a(4, 24), new C5412a(31, 25)), new C5413b(30, new C5412a(11, 15), new C5412a(31, 16))), new C5411np(29, new int[]{6, 30, 54, 78, 102, TbsListener.ErrorCode.PV_UPLOAD_ERROR}, new C5413b(30, new C5412a(7, 116), new C5412a(7, 117)), new C5413b(28, new C5412a(21, 45), new C5412a(7, 46)), new C5413b(30, new C5412a(1, 23), new C5412a(37, 24)), new C5413b(30, new C5412a(19, 15), new C5412a(26, 16))), new C5411np(30, new int[]{6, 26, 52, 78, 104, 130}, new C5413b(30, new C5412a(5, 115), new C5412a(10, 116)), new C5413b(28, new C5412a(19, 47), new C5412a(10, 48)), new C5413b(30, new C5412a(15, 24), new C5412a(25, 25)), new C5413b(30, new C5412a(23, 15), new C5412a(25, 16))), new C5411np(31, new int[]{6, 30, 56, 82, 108, 134}, new C5413b(30, new C5412a(13, 115), new C5412a(3, 116)), new C5413b(28, new C5412a(2, 46), new C5412a(29, 47)), new C5413b(30, new C5412a(42, 24), new C5412a(1, 25)), new C5413b(30, new C5412a(23, 15), new C5412a(28, 16))), new C5411np(32, new int[]{6, 34, 60, 86, 112, 138}, new C5413b(30, new C5412a(17, 115)), new C5413b(28, new C5412a(10, 46), new C5412a(23, 47)), new C5413b(30, new C5412a(10, 24), new C5412a(35, 25)), new C5413b(30, new C5412a(19, 15), new C5412a(35, 16))), new C5411np(33, new int[]{6, 30, 58, 86, 114, TbsListener.ErrorCode.NEEDDOWNLOAD_3}, new C5413b(30, new C5412a(17, 115), new C5412a(1, 116)), new C5413b(28, new C5412a(14, 46), new C5412a(21, 47)), new C5413b(30, new C5412a(29, 24), new C5412a(19, 25)), new C5413b(30, new C5412a(11, 15), new C5412a(46, 16))), new C5411np(34, new int[]{6, 34, 62, 90, 118, TbsListener.ErrorCode.NEEDDOWNLOAD_7}, new C5413b(30, new C5412a(13, 115), new C5412a(6, 116)), new C5413b(28, new C5412a(14, 46), new C5412a(23, 47)), new C5413b(30, new C5412a(44, 24), new C5412a(7, 25)), new C5413b(30, new C5412a(59, 16), new C5412a(1, 17))), new C5411np(35, new int[]{6, 30, 54, 78, 102, TbsListener.ErrorCode.PV_UPLOAD_ERROR, 150}, new C5413b(30, new C5412a(12, TbsListener.ErrorCode.THREAD_INIT_ERROR), new C5412a(7, TbsListener.ErrorCode.DOWNLOAD_HAS_COPY_TBS_ERROR)), new C5413b(28, new C5412a(12, 47), new C5412a(26, 48)), new C5413b(30, new C5412a(39, 24), new C5412a(14, 25)), new C5413b(30, new C5412a(22, 15), new C5412a(41, 16))), new C5411np(36, new int[]{6, 24, 50, 76, 102, 128, 154}, new C5413b(30, new C5412a(6, TbsListener.ErrorCode.THREAD_INIT_ERROR), new C5412a(14, TbsListener.ErrorCode.DOWNLOAD_HAS_COPY_TBS_ERROR)), new C5413b(28, new C5412a(6, 47), new C5412a(34, 48)), new C5413b(30, new C5412a(46, 24), new C5412a(10, 25)), new C5413b(30, new C5412a(2, 15), new C5412a(64, 16))), new C5411np(37, new int[]{6, 28, 54, 80, 106, 132, 158}, new C5413b(30, new C5412a(17, TbsListener.ErrorCode.DOWNLOAD_HAS_COPY_TBS_ERROR), new C5412a(4, TbsListener.ErrorCode.DOWNLOAD_RETRYTIMES302_EXCEED)), new C5413b(28, new C5412a(29, 46), new C5412a(14, 47)), new C5413b(30, new C5412a(49, 24), new C5412a(10, 25)), new C5413b(30, new C5412a(24, 15), new C5412a(46, 16))), new C5411np(38, new int[]{6, 32, 58, 84, 110, 136, TbsListener.ErrorCode.STARTDOWNLOAD_3}, new C5413b(30, new C5412a(4, TbsListener.ErrorCode.DOWNLOAD_HAS_COPY_TBS_ERROR), new C5412a(18, TbsListener.ErrorCode.DOWNLOAD_RETRYTIMES302_EXCEED)), new C5413b(28, new C5412a(13, 46), new C5412a(32, 47)), new C5413b(30, new C5412a(48, 24), new C5412a(14, 25)), new C5413b(30, new C5412a(42, 15), new C5412a(32, 16))), new C5411np(39, new int[]{6, 26, 54, 82, 110, 138, TbsListener.ErrorCode.STARTDOWNLOAD_7}, new C5413b(30, new C5412a(20, 117), new C5412a(4, 118)), new C5413b(28, new C5412a(40, 47), new C5412a(7, 48)), new C5413b(30, new C5412a(43, 24), new C5412a(22, 25)), new C5413b(30, new C5412a(10, 15), new C5412a(67, 16))), new C5411np(40, new int[]{6, 30, 58, 86, 114, TbsListener.ErrorCode.NEEDDOWNLOAD_3, TbsListener.ErrorCode.NEEDDOWNLOAD_TRUE}, new C5413b(30, new C5412a(19, 118), new C5412a(6, 119)), new C5413b(28, new C5412a(18, 47), new C5412a(31, 48)), new C5413b(30, new C5412a(34, 24), new C5412a(34, 25)), new C5413b(30, new C5412a(20, 15), new C5412a(61, 16)))};
    }
}
