package p110z1;

@Metadata(m8864a = 2, m8863b = {1, 1, 15}, m8862c = {1, 0, 3}, m8861d = {"\u0000 \n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\b\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\u001a*\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u00012\u0006\u0010\u0004\u001a\u00020\u0001H\u0002ø\u0001\u0000¢\u0006\u0004\b\u0005\u0010\u0006\u001a*\u0010\u0000\u001a\u00020\u00072\u0006\u0010\u0002\u001a\u00020\u00072\u0006\u0010\u0003\u001a\u00020\u00072\u0006\u0010\u0004\u001a\u00020\u0007H\u0002ø\u0001\u0000¢\u0006\u0004\b\b\u0010\t\u001a*\u0010\n\u001a\u00020\u00012\u0006\u0010\u000b\u001a\u00020\u00012\u0006\u0010\f\u001a\u00020\u00012\u0006\u0010\r\u001a\u00020\u000eH\u0001ø\u0001\u0000¢\u0006\u0004\b\u000f\u0010\u0006\u001a*\u0010\n\u001a\u00020\u00072\u0006\u0010\u000b\u001a\u00020\u00072\u0006\u0010\f\u001a\u00020\u00072\u0006\u0010\r\u001a\u00020\u0010H\u0001ø\u0001\u0000¢\u0006\u0004\b\u0011\u0010\t\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0012"}, m8860e = {"differenceModulo", "Lkotlin/UInt;", "a", "b", "c", "differenceModulo-WZ9TVnA", "(III)I", "Lkotlin/ULong;", "differenceModulo-sambcqE", "(JJJ)J", "getProgressionLastElement", "start", "end", "step", "", "getProgressionLastElement-Nkh28Cs", "", "getProgressionLastElement-7ftBX0g", "kotlin-stdlib"})
/* renamed from: z1.cfj */
/* loaded from: classes3.dex */
public final class UProgressionUtil {
    /* renamed from: b */
    private static final int m5456b(int i, int i2, int i3) {
        int c = UnsignedUtils.m8332c(i, i3);
        int c2 = UnsignedUtils.m8332c(i2, i3);
        return UInt.m8628b(UnsignedUtils.m8340a(c, c2) >= 0 ? c - c2 : UInt.m8628b(c - c2) + i3);
    }

    /* renamed from: b */
    private static final long m5455b(long j, long j2, long j3) {
        long c = UnsignedUtils.m8331c(j, j3);
        long c2 = UnsignedUtils.m8331c(j2, j3);
        return ULong.m8548b(UnsignedUtils.m8337a(c, c2) >= 0 ? c - c2 : ULong.m8548b(c - c2) + j3);
    }

    @bwy(m8750a = "1.3")
    @bwt
    /* renamed from: a */
    public static final int m5458a(int i, int i2, int i3) {
        if (i3 > 0) {
            return UnsignedUtils.m8340a(i, i2) >= 0 ? i2 : UInt.m8628b(i2 - m5456b(i2, i, UInt.m8628b(i3)));
        }
        if (i3 < 0) {
            return UnsignedUtils.m8340a(i, i2) <= 0 ? i2 : UInt.m8628b(i2 + m5456b(i, i2, UInt.m8628b(-i3)));
        }
        throw new IllegalArgumentException("Step is zero.");
    }

    @bwy(m8750a = "1.3")
    @bwt
    /* renamed from: a */
    public static final long m5457a(long j, long j2, long j3) {
        int i = (j3 > 0L ? 1 : (j3 == 0L ? 0 : -1));
        if (i > 0) {
            return UnsignedUtils.m8337a(j, j2) >= 0 ? j2 : ULong.m8548b(j2 - m5455b(j2, j, ULong.m8548b(j3)));
        }
        if (i < 0) {
            return UnsignedUtils.m8337a(j, j2) <= 0 ? j2 : ULong.m8548b(j2 + m5455b(j, j2, ULong.m8548b(-j3)));
        }
        throw new IllegalArgumentException("Step is zero.");
    }
}
