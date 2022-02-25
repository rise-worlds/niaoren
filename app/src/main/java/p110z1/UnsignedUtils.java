package p110z1;

@Metadata(m8864a = 2, m8863b = {1, 1, 15}, m8862c = {1, 0, 3}, m8861d = {"\u00000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\t\n\u0002\u0010\t\n\u0002\b\u0007\n\u0002\u0010\u000e\n\u0002\b\u0002\u001a\u0018\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u0001ø\u0001\u0000¢\u0006\u0002\u0010\u0004\u001a\u0018\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0002\u001a\u00020\u0003H\u0001ø\u0001\u0000¢\u0006\u0002\u0010\u0007\u001a\u0018\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\t2\u0006\u0010\u000b\u001a\u00020\tH\u0001\u001a\"\u0010\f\u001a\u00020\u00012\u0006\u0010\n\u001a\u00020\u00012\u0006\u0010\u000b\u001a\u00020\u0001H\u0001ø\u0001\u0000¢\u0006\u0004\b\r\u0010\u000e\u001a\"\u0010\u000f\u001a\u00020\u00012\u0006\u0010\n\u001a\u00020\u00012\u0006\u0010\u000b\u001a\u00020\u0001H\u0001ø\u0001\u0000¢\u0006\u0004\b\u0010\u0010\u000e\u001a\u0010\u0010\u0011\u001a\u00020\u00032\u0006\u0010\u0002\u001a\u00020\tH\u0001\u001a\u0018\u0010\u0012\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u00132\u0006\u0010\u000b\u001a\u00020\u0013H\u0001\u001a\"\u0010\u0014\u001a\u00020\u00062\u0006\u0010\n\u001a\u00020\u00062\u0006\u0010\u000b\u001a\u00020\u0006H\u0001ø\u0001\u0000¢\u0006\u0004\b\u0015\u0010\u0016\u001a\"\u0010\u0017\u001a\u00020\u00062\u0006\u0010\n\u001a\u00020\u00062\u0006\u0010\u000b\u001a\u00020\u0006H\u0001ø\u0001\u0000¢\u0006\u0004\b\u0018\u0010\u0016\u001a\u0010\u0010\u0019\u001a\u00020\u00032\u0006\u0010\u0002\u001a\u00020\u0013H\u0001\u001a\u0010\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u0002\u001a\u00020\u0013H\u0000\u001a\u0018\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u0002\u001a\u00020\u00132\u0006\u0010\u001c\u001a\u00020\tH\u0000\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u001d"}, m8860e = {"doubleToUInt", "Lkotlin/UInt;", "v", "", "(D)I", "doubleToULong", "Lkotlin/ULong;", "(D)J", "uintCompare", "", "v1", "v2", "uintDivide", "uintDivide-J1ME1BU", "(II)I", "uintRemainder", "uintRemainder-J1ME1BU", "uintToDouble", "ulongCompare", "", "ulongDivide", "ulongDivide-eb3DHEI", "(JJ)J", "ulongRemainder", "ulongRemainder-eb3DHEI", "ulongToDouble", "ulongToString", "", "base", "kotlin-stdlib"})
@cgo(m5270a = "UnsignedKt")
/* renamed from: z1.byg */
/* loaded from: classes3.dex */
public final class UnsignedUtils {
    @bwt
    /* renamed from: a */
    public static final double m8341a(int i) {
        return (Integer.MAX_VALUE & i) + (((i >>> 31) << 30) * 2);
    }

    @bwt
    /* renamed from: a */
    public static final double m8339a(long j) {
        return ((j >>> 11) * 2048) + (j & 2047);
    }

    @bwt
    /* renamed from: a */
    public static final int m8337a(long j, long j2) {
        return ((j ^ Long.MIN_VALUE) > (j2 ^ Long.MIN_VALUE) ? 1 : ((j ^ Long.MIN_VALUE) == (j2 ^ Long.MIN_VALUE) ? 0 : -1));
    }

    @bwt
    /* renamed from: a */
    public static final int m8340a(int i, int i2) {
        return cji.m5193a(i ^ Integer.MIN_VALUE, i2 ^ Integer.MIN_VALUE);
    }

    @bwt
    /* renamed from: b */
    public static final int m8335b(int i, int i2) {
        return UInt.m8628b((int) ((i & 4294967295L) / (i2 & 4294967295L)));
    }

    @bwt
    /* renamed from: c */
    public static final int m8332c(int i, int i2) {
        return UInt.m8628b((int) ((i & 4294967295L) % (i2 & 4294967295L)));
    }

    @bwt
    /* renamed from: b */
    public static final long m8333b(long j, long j2) {
        if (j2 < 0) {
            return m8337a(j, j2) < 0 ? ULong.m8548b(0L) : ULong.m8548b(1L);
        }
        if (j >= 0) {
            return ULong.m8548b(j / j2);
        }
        int i = 1;
        long j3 = ((j >>> 1) / j2) << 1;
        if (m8337a(ULong.m8548b(j - (j3 * j2)), ULong.m8548b(j2)) < 0) {
            i = 0;
        }
        return ULong.m8548b(j3 + i);
    }

    @bwt
    /* renamed from: c */
    public static final long m8331c(long j, long j2) {
        if (j2 < 0) {
            return m8337a(j, j2) < 0 ? j : ULong.m8548b(j - j2);
        }
        if (j >= 0) {
            return ULong.m8548b(j % j2);
        }
        long j3 = j - ((((j >>> 1) / j2) << 1) * j2);
        if (m8337a(ULong.m8548b(j3), ULong.m8548b(j2)) < 0) {
            j2 = 0;
        }
        return ULong.m8548b(j3 - j2);
    }

    @bwt
    /* renamed from: a */
    public static final int m8342a(double d) {
        if (Double.isNaN(d) || d <= m8341a(0)) {
            return 0;
        }
        if (d >= m8341a(-1)) {
            return -1;
        }
        double d2 = Integer.MAX_VALUE;
        if (d <= d2) {
            return UInt.m8628b((int) d);
        }
        return UInt.m8628b(UInt.m8628b((int) (d - d2)) + UInt.m8628b(Integer.MAX_VALUE));
    }

    @bwt
    /* renamed from: b */
    public static final long m8336b(double d) {
        if (!Double.isNaN(d) && d > m8339a(0L)) {
            if (d >= m8339a(-1L)) {
                return -1L;
            }
            if (d < ((double) cjm.f20759b)) {
                return ULong.m8548b((long) d);
            }
            return ULong.m8548b(ULong.m8548b((long) (d - 9.223372036854776E18d)) - Long.MIN_VALUE);
        }
        return 0L;
    }

    @NotNull
    /* renamed from: b */
    public static final String m8334b(long j) {
        return m8338a(j, 10);
    }

    @NotNull
    /* renamed from: a */
    public static final String m8338a(long j, int i) {
        if (j >= 0) {
            String l = Long.toString(j, cov.m4246a(i));
            cji.m5175b(l, "java.lang.Long.toString(this, checkRadix(radix))");
            return l;
        }
        long j2 = i;
        long j3 = ((j >>> 1) / j2) << 1;
        long j4 = j - (j3 * j2);
        if (j4 >= j2) {
            j4 -= j2;
            j3++;
        }
        StringBuilder sb = new StringBuilder();
        String l2 = Long.toString(j3, cov.m4246a(i));
        cji.m5175b(l2, "java.lang.Long.toString(this, checkRadix(radix))");
        sb.append(l2);
        String l3 = Long.toString(j4, cov.m4246a(i));
        cji.m5175b(l3, "java.lang.Long.toString(this, checkRadix(radix))");
        sb.append(l3);
        return sb.toString();
    }
}
