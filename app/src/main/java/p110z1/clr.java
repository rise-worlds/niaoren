package p110z1;

import org.apache.tools.ant.types.selectors.SizeSelector;

/* compiled from: Random.kt */
@Metadata(m8864a = 2, m8863b = {1, 1, 15}, m8862c = {1, 0, 3}, m8861d = {"\u0000:\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\u0010\u0006\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a\u0010\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u0007\u001a\u0010\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0004H\u0007\u001a\u0018\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\bH\u0000\u001a\u0018\u0010\n\u001a\u00020\u000b2\u0006\u0010\u0007\u001a\u00020\f2\u0006\u0010\t\u001a\u00020\fH\u0000\u001a\u0018\u0010\n\u001a\u00020\u000b2\u0006\u0010\u0007\u001a\u00020\u00032\u0006\u0010\t\u001a\u00020\u0003H\u0000\u001a\u0018\u0010\n\u001a\u00020\u000b2\u0006\u0010\u0007\u001a\u00020\u00042\u0006\u0010\t\u001a\u00020\u0004H\u0000\u001a\u0010\u0010\r\u001a\u00020\u00032\u0006\u0010\u000e\u001a\u00020\u0003H\u0000\u001a\u0014\u0010\u000f\u001a\u00020\u0003*\u00020\u00012\u0006\u0010\u0010\u001a\u00020\u0011H\u0007\u001a\u0014\u0010\u0012\u001a\u00020\u0004*\u00020\u00012\u0006\u0010\u0010\u001a\u00020\u0013H\u0007\u001a\u0014\u0010\u0014\u001a\u00020\u0003*\u00020\u00032\u0006\u0010\u0015\u001a\u00020\u0003H\u0000¨\u0006\u0016"}, m8860e = {"Random", "Lkotlin/random/Random;", "seed", "", "", "boundsErrorMessage", "", "from", "", "until", "checkRangeBounds", "", "", "fastLog2", SizeSelector.SIZE_KEY, "nextInt", "range", "Lkotlin/ranges/IntRange;", "nextLong", "Lkotlin/ranges/LongRange;", "takeUpperBits", "bitCount", "kotlin-stdlib"})
/* renamed from: z1.clr */
/* loaded from: classes3.dex */
public final class clr {
    /* renamed from: a */
    public static final int m4885a(int i, int i2) {
        return (i >>> (32 - i2)) & ((-i2) >> 31);
    }

    @bwy(m8750a = "1.3")
    @NotNull
    /* renamed from: a */
    public static final Random m4886a(int i) {
        return new XorWowRandom(i, i >> 31);
    }

    @bwy(m8750a = "1.3")
    @NotNull
    /* renamed from: a */
    public static final Random m4884a(long j) {
        return new XorWowRandom((int) j, (int) (j >> 32));
    }

    @bwy(m8750a = "1.3")
    /* renamed from: a */
    public static final int m4881a(@NotNull Random clqVar, @NotNull cme cmeVar) {
        cji.m5162f(clqVar, "$this$nextInt");
        cji.m5162f(cmeVar, "range");
        if (!cmeVar.mo4667e()) {
            return cmeVar.m4832b() < Integer.MAX_VALUE ? clqVar.mo4898a(cmeVar.m4833a(), cmeVar.m4832b() + 1) : cmeVar.m4833a() > Integer.MIN_VALUE ? clqVar.mo4898a(cmeVar.m4833a() - 1, cmeVar.m4832b()) + 1 : clqVar.mo4862b();
        }
        throw new IllegalArgumentException("Cannot get random in empty range: " + cmeVar);
    }

    @bwy(m8750a = "1.3")
    /* renamed from: a */
    public static final long m4880a(@NotNull Random clqVar, @NotNull cmh cmhVar) {
        cji.m5162f(clqVar, "$this$nextLong");
        cji.m5162f(cmhVar, "range");
        if (!cmhVar.mo4667e()) {
            return cmhVar.m4820b() < cjm.f20759b ? clqVar.mo4896a(cmhVar.mo4665g().longValue(), cmhVar.mo4663i().longValue() + 1) : cmhVar.mo4665g().longValue() > Long.MIN_VALUE ? clqVar.mo4896a(cmhVar.mo4665g().longValue() - 1, cmhVar.mo4663i().longValue()) + 1 : clqVar.mo4892c();
        }
        throw new IllegalArgumentException("Cannot get random in empty range: " + cmhVar);
    }

    /* renamed from: b */
    public static final int m4879b(int i) {
        return 31 - Integer.numberOfLeadingZeros(i);
    }

    /* renamed from: b */
    public static final void m4878b(int i, int i2) {
        if (!(i2 > i)) {
            throw new IllegalArgumentException(m4882a(Integer.valueOf(i), Integer.valueOf(i2)).toString());
        }
    }

    /* renamed from: a */
    public static final void m4883a(long j, long j2) {
        if (!(j2 > j)) {
            throw new IllegalArgumentException(m4882a(Long.valueOf(j), Long.valueOf(j2)).toString());
        }
    }

    /* renamed from: a */
    public static final void m4887a(double d, double d2) {
        if (!(d2 > d)) {
            throw new IllegalArgumentException(m4882a(Double.valueOf(d), Double.valueOf(d2)).toString());
        }
    }

    @NotNull
    /* renamed from: a */
    public static final String m4882a(@NotNull Object obj, @NotNull Object obj2) {
        cji.m5162f(obj, "from");
        cji.m5162f(obj2, "until");
        return "Random range is empty: [" + obj + ", " + obj2 + ").";
    }
}
