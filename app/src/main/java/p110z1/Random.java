package p110z1;

@bwy(m8750a = "1.3")
@Metadata(m8864a = 1, m8863b = {1, 1, 15}, m8862c = {1, 0, 3}, m8861d = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0012\n\u0002\b\u0005\n\u0002\u0010\u0006\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0003\b'\u0018\u0000 \u00182\u00020\u0001:\u0002\u0017\u0018B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0004H&J\b\u0010\u0006\u001a\u00020\u0007H\u0016J\u0010\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\tH\u0016J$\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\t2\b\b\u0002\u0010\u000b\u001a\u00020\u00042\b\b\u0002\u0010\f\u001a\u00020\u0004H\u0016J\u0010\u0010\b\u001a\u00020\t2\u0006\u0010\r\u001a\u00020\u0004H\u0016J\b\u0010\u000e\u001a\u00020\u000fH\u0016J\u0010\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u000fH\u0016J\u0018\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0011\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u000fH\u0016J\b\u0010\u0012\u001a\u00020\u0013H\u0016J\b\u0010\u0014\u001a\u00020\u0004H\u0016J\u0010\u0010\u0014\u001a\u00020\u00042\u0006\u0010\u0010\u001a\u00020\u0004H\u0016J\u0018\u0010\u0014\u001a\u00020\u00042\u0006\u0010\u0011\u001a\u00020\u00042\u0006\u0010\u0010\u001a\u00020\u0004H\u0016J\b\u0010\u0015\u001a\u00020\u0016H\u0016J\u0010\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0010\u001a\u00020\u0016H\u0016J\u0018\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0011\u001a\u00020\u00162\u0006\u0010\u0010\u001a\u00020\u0016H\u0016¨\u0006\u0019"}, m8860e = {"Lkotlin/random/Random;", "", "()V", "nextBits", "", "bitCount", "nextBoolean", "", "nextBytes", "", "array", "fromIndex", "toIndex", "size", "nextDouble", "", "until", "from", "nextFloat", "", "nextInt", "nextLong", "", "Companion", "Default", "kotlin-stdlib"})
/* renamed from: z1.clq */
/* loaded from: classes3.dex */
public abstract class Random {

    /* renamed from: b */
    public static final C4981b f20808b = new C4981b(null);

    /* renamed from: c */
    private static final Random f20809c = cfe.f20646a.m5474a();
    @JvmPlatformAnnotations
    @NotNull

    /* renamed from: a */
    public static final C4980a f20807a = C4980a.f20810c;

    /* renamed from: a */
    public abstract int mo4863a(int i);

    /* renamed from: b */
    public int mo4862b() {
        return mo4863a(32);
    }

    /* renamed from: b */
    public int mo4893b(int i) {
        return mo4898a(0, i);
    }

    /* renamed from: a */
    public int mo4898a(int i, int i2) {
        int i3;
        int b;
        int i4;
        clr.m4878b(i, i2);
        int i5 = i2 - i;
        if (i5 > 0 || i5 == Integer.MIN_VALUE) {
            if (((-i5) & i5) == i5) {
                i3 = mo4863a(clr.m4879b(i5));
            } else {
                do {
                    b = mo4862b() >>> 1;
                    i4 = b % i5;
                } while ((b - i4) + (i5 - 1) < 0);
                i3 = i4;
            }
            return i + i3;
        }
        while (true) {
            int b2 = mo4862b();
            if (i <= b2 && i2 > b2) {
                return b2;
            }
        }
    }

    /* renamed from: c */
    public long mo4892c() {
        return (mo4862b() << 32) + mo4862b();
    }

    /* renamed from: a */
    public long mo4897a(long j) {
        return mo4896a(0L, j);
    }

    /* renamed from: a */
    public long mo4896a(long j, long j2) {
        long j3;
        long c;
        long j4;
        clr.m4883a(j, j2);
        long j5 = j2 - j;
        if (j5 > 0) {
            if (((-j5) & j5) == j5) {
                int i = (int) j5;
                int i2 = (int) (j5 >>> 32);
                if (i != 0) {
                    j3 = mo4863a(clr.m4879b(i)) & 4294967295L;
                } else if (i2 == 1) {
                    j3 = mo4862b() & 4294967295L;
                } else {
                    j3 = (mo4863a(clr.m4879b(i2)) << 32) + mo4862b();
                }
            } else {
                do {
                    c = mo4892c() >>> 1;
                    j4 = c % j5;
                } while ((c - j4) + (j5 - 1) < 0);
                j3 = j4;
            }
            return j + j3;
        }
        while (true) {
            long c2 = mo4892c();
            if (j <= c2 && j2 > c2) {
                return c2;
            }
        }
    }

    /* renamed from: d */
    public boolean mo4890d() {
        return mo4863a(1) != 0;
    }

    /* renamed from: e */
    public double mo4889e() {
        return clp.m4906a(mo4863a(26), mo4863a(27));
    }

    /* renamed from: a */
    public double mo4900a(double d) {
        return mo4899a(0.0d, d);
    }

    /* JADX WARN: Removed duplicated region for block: B:21:0x0049  */
    /* JADX WARN: Removed duplicated region for block: B:23:? A[RETURN, SYNTHETIC] */
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public double mo4899a(double r7, double r9) {
        /*
            r6 = this;
            p110z1.clr.m4887a(r7, r9)
            double r0 = r9 - r7
            boolean r2 = java.lang.Double.isInfinite(r0)
            if (r2 == 0) goto L_0x003e
            boolean r2 = java.lang.Double.isInfinite(r7)
            r3 = 1
            r4 = 0
            if (r2 != 0) goto L_0x001b
            boolean r2 = java.lang.Double.isNaN(r7)
            if (r2 != 0) goto L_0x001b
            r2 = 1
            goto L_0x001c
        L_0x001b:
            r2 = 0
        L_0x001c:
            if (r2 == 0) goto L_0x003e
            boolean r2 = java.lang.Double.isInfinite(r9)
            if (r2 != 0) goto L_0x002b
            boolean r2 = java.lang.Double.isNaN(r9)
            if (r2 != 0) goto L_0x002b
            goto L_0x002c
        L_0x002b:
            r3 = 0
        L_0x002c:
            if (r3 == 0) goto L_0x003e
            double r0 = r6.mo4889e()
            r2 = 2
            double r2 = (double) r2
            double r4 = r9 / r2
            double r2 = r7 / r2
            double r4 = r4 - r2
            double r0 = r0 * r4
            double r7 = r7 + r0
            double r7 = r7 + r0
            goto L_0x0045
        L_0x003e:
            double r2 = r6.mo4889e()
            double r2 = r2 * r0
            double r7 = r7 + r2
        L_0x0045:
            int r0 = (r7 > r9 ? 1 : (r7 == r9 ? 0 : -1))
            if (r0 < 0) goto L_0x0053
            z1.ciw r7 = p110z1.ciw.f20730a
            double r7 = r7.m5221d()
            double r7 = java.lang.Math.nextAfter(r9, r7)
        L_0x0053:
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: p110z1.Random.mo4899a(double, double):double");
    }

    /* renamed from: f */
    public float mo4888f() {
        return mo4863a(24) / 16777216;
    }

    /* renamed from: a */
    public static /* synthetic */ byte[] m4903a(Random clqVar, byte[] bArr, int i, int i2, int i3, Object obj) {
        if (obj == null) {
            if ((i3 & 2) != 0) {
                i = 0;
            }
            if ((i3 & 4) != 0) {
                i2 = bArr.length;
            }
            return clqVar.mo4894a(bArr, i, i2);
        }
        throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: nextBytes");
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x0018  */
    /* JADX WARN: Removed duplicated region for block: B:23:0x0087  */
    @p110z1.NotNull
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public byte[] mo4894a(@p110z1.NotNull byte[] r7, int r8, int r9) {
        /*
            r6 = this;
            java.lang.String r0 = "array"
            p110z1.cji.m5162f(r7, r0)
            int r0 = r7.length
            r1 = 0
            r2 = 1
            if (r8 >= 0) goto L_0x000b
            goto L_0x0015
        L_0x000b:
            if (r0 < r8) goto L_0x0015
            int r0 = r7.length
            if (r9 >= 0) goto L_0x0011
            goto L_0x0015
        L_0x0011:
            if (r0 < r9) goto L_0x0015
            r0 = 1
            goto L_0x0016
        L_0x0015:
            r0 = 0
        L_0x0016:
            if (r0 == 0) goto L_0x0087
            if (r8 > r9) goto L_0x001b
            goto L_0x001c
        L_0x001b:
            r2 = 0
        L_0x001c:
            if (r2 == 0) goto L_0x005d
            int r0 = r9 - r8
            int r0 = r0 / 4
            r2 = r8
            r8 = 0
        L_0x0024:
            if (r8 >= r0) goto L_0x0047
            int r3 = r6.mo4862b()
            byte r4 = (byte) r3
            r7[r2] = r4
            int r4 = r2 + 1
            int r5 = r3 >>> 8
            byte r5 = (byte) r5
            r7[r4] = r5
            int r4 = r2 + 2
            int r5 = r3 >>> 16
            byte r5 = (byte) r5
            r7[r4] = r5
            int r4 = r2 + 3
            int r3 = r3 >>> 24
            byte r3 = (byte) r3
            r7[r4] = r3
            int r2 = r2 + 4
            int r8 = r8 + 1
            goto L_0x0024
        L_0x0047:
            int r9 = r9 - r2
            int r8 = r9 * 8
            int r8 = r6.mo4863a(r8)
        L_0x004e:
            if (r1 >= r9) goto L_0x005c
            int r0 = r2 + r1
            int r3 = r1 * 8
            int r3 = r8 >>> r3
            byte r3 = (byte) r3
            r7[r0] = r3
            int r1 = r1 + 1
            goto L_0x004e
        L_0x005c:
            return r7
        L_0x005d:
            java.lang.StringBuilder r7 = new java.lang.StringBuilder
            r7.<init>()
            java.lang.String r0 = "fromIndex ("
            r7.append(r0)
            r7.append(r8)
            java.lang.String r8 = ") must be not greater than toIndex ("
            r7.append(r8)
            r7.append(r9)
            java.lang.String r8 = ")."
            r7.append(r8)
            java.lang.String r7 = r7.toString()
            java.lang.IllegalArgumentException r8 = new java.lang.IllegalArgumentException
            java.lang.String r7 = r7.toString()
            r8.<init>(r7)
            java.lang.Throwable r8 = (java.lang.Throwable) r8
            throw r8
        L_0x0087:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = "fromIndex ("
            r0.append(r1)
            r0.append(r8)
            java.lang.String r8 = ") or toIndex ("
            r0.append(r8)
            r0.append(r9)
            java.lang.String r8 = ") are out of range: 0.."
            r0.append(r8)
            int r7 = r7.length
            r0.append(r7)
            r7 = 46
            r0.append(r7)
            java.lang.String r7 = r0.toString()
            java.lang.IllegalArgumentException r8 = new java.lang.IllegalArgumentException
            java.lang.String r7 = r7.toString()
            r8.<init>(r7)
            java.lang.Throwable r8 = (java.lang.Throwable) r8
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: p110z1.Random.mo4894a(byte[], int, int):byte[]");
    }

    @NotNull
    /* renamed from: a */
    public byte[] mo4895a(@NotNull byte[] bArr) {
        cji.m5162f(bArr, "array");
        return mo4894a(bArr, 0, bArr.length);
    }

    @NotNull
    /* renamed from: c */
    public byte[] mo4891c(int i) {
        return mo4895a(new byte[i]);
    }

    /* compiled from: Random.kt */
    @Metadata(m8864a = 1, m8863b = {1, 1, 15}, m8862c = {1, 0, 3}, m8861d = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0012\n\u0002\b\u0005\n\u0002\u0010\u0006\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\bH\u0016J\b\u0010\n\u001a\u00020\u000bH\u0016J\u0010\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\rH\u0016J \u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\r2\u0006\u0010\u000f\u001a\u00020\b2\u0006\u0010\u0010\u001a\u00020\bH\u0016J\u0010\u0010\f\u001a\u00020\r2\u0006\u0010\u0011\u001a\u00020\bH\u0016J\b\u0010\u0012\u001a\u00020\u0013H\u0016J\u0010\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0013H\u0016J\u0018\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0015\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0013H\u0016J\b\u0010\u0016\u001a\u00020\u0017H\u0016J\b\u0010\u0018\u001a\u00020\bH\u0016J\u0010\u0010\u0018\u001a\u00020\b2\u0006\u0010\u0014\u001a\u00020\bH\u0016J\u0018\u0010\u0018\u001a\u00020\b2\u0006\u0010\u0015\u001a\u00020\b2\u0006\u0010\u0014\u001a\u00020\bH\u0016J\b\u0010\u0019\u001a\u00020\u001aH\u0016J\u0010\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u0014\u001a\u00020\u001aH\u0016J\u0018\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u0015\u001a\u00020\u001a2\u0006\u0010\u0014\u001a\u00020\u001aH\u0016R\u0016\u0010\u0003\u001a\u00020\u00048\u0006X\u0087\u0004¢\u0006\b\n\u0000\u0012\u0004\b\u0005\u0010\u0002R\u000e\u0010\u0006\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001b"}, m8860e = {"Lkotlin/random/Random$Default;", "Lkotlin/random/Random;", "()V", "Companion", "Lkotlin/random/Random$Companion;", "Companion$annotations", "defaultRandom", "nextBits", "", "bitCount", "nextBoolean", "", "nextBytes", "", "array", "fromIndex", "toIndex", "size", "nextDouble", "", "until", "from", "nextFloat", "", "nextInt", "nextLong", "", "kotlin-stdlib"})
    /* renamed from: z1.clq$b */
    /* loaded from: classes3.dex */
    public static final class C4981b extends Random {
        @Annotations(m8902a = "Use Default companion object instead", m8900c = bvk.HIDDEN)
        /* renamed from: a */
        public static /* synthetic */ void m4901a() {
        }

        private C4981b() {
        }

        public /* synthetic */ C4981b(DefaultConstructorMarker civVar) {
            this();
        }

        @Override // p110z1.Random
        /* renamed from: a */
        public int mo4863a(int i) {
            return Random.f20809c.mo4863a(i);
        }

        @Override // p110z1.Random
        /* renamed from: b */
        public int mo4862b() {
            return Random.f20809c.mo4862b();
        }

        @Override // p110z1.Random
        /* renamed from: b */
        public int mo4893b(int i) {
            return Random.f20809c.mo4893b(i);
        }

        @Override // p110z1.Random
        /* renamed from: a */
        public int mo4898a(int i, int i2) {
            return Random.f20809c.mo4898a(i, i2);
        }

        @Override // p110z1.Random
        /* renamed from: c */
        public long mo4892c() {
            return Random.f20809c.mo4892c();
        }

        @Override // p110z1.Random
        /* renamed from: a */
        public long mo4897a(long j) {
            return Random.f20809c.mo4897a(j);
        }

        @Override // p110z1.Random
        /* renamed from: a */
        public long mo4896a(long j, long j2) {
            return Random.f20809c.mo4896a(j, j2);
        }

        @Override // p110z1.Random
        /* renamed from: d */
        public boolean mo4890d() {
            return Random.f20809c.mo4890d();
        }

        @Override // p110z1.Random
        /* renamed from: e */
        public double mo4889e() {
            return Random.f20809c.mo4889e();
        }

        @Override // p110z1.Random
        /* renamed from: a */
        public double mo4900a(double d) {
            return Random.f20809c.mo4900a(d);
        }

        @Override // p110z1.Random
        /* renamed from: a */
        public double mo4899a(double d, double d2) {
            return Random.f20809c.mo4899a(d, d2);
        }

        @Override // p110z1.Random
        /* renamed from: f */
        public float mo4888f() {
            return Random.f20809c.mo4888f();
        }

        @Override // p110z1.Random
        @NotNull
        /* renamed from: a */
        public byte[] mo4895a(@NotNull byte[] bArr) {
            cji.m5162f(bArr, "array");
            return Random.f20809c.mo4895a(bArr);
        }

        @Override // p110z1.Random
        @NotNull
        /* renamed from: c */
        public byte[] mo4891c(int i) {
            return Random.f20809c.mo4891c(i);
        }

        @Override // p110z1.Random
        @NotNull
        /* renamed from: a */
        public byte[] mo4894a(@NotNull byte[] bArr, int i, int i2) {
            cji.m5162f(bArr, "array");
            return Random.f20809c.mo4894a(bArr, i, i2);
        }
    }

    /* compiled from: Random.kt */
    @Metadata(m8864a = 1, m8863b = {1, 1, 15}, m8862c = {1, 0, 3}, m8861d = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\bÇ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0004H\u0016¨\u0006\u0006"}, m8860e = {"Lkotlin/random/Random$Companion;", "Lkotlin/random/Random;", "()V", "nextBits", "", "bitCount", "kotlin-stdlib"})
    @Annotations(m8902a = "Use Default companion object instead", m8900c = bvk.HIDDEN)
    /* renamed from: z1.clq$a */
    /* loaded from: classes3.dex */
    public static final class C4980a extends Random {

        /* renamed from: c */
        public static final C4980a f20810c = new C4980a();

        private C4980a() {
        }

        @Override // p110z1.Random
        /* renamed from: a */
        public int mo4863a(int i) {
            return Random.f20808b.mo4863a(i);
        }
    }
}
