package p110z1;

@Metadata(m8864a = 2, m8863b = {1, 1, 15}, m8862c = {1, 0, 3}, m8861d = {"\u0000\u0012\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0006\u001a \u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u00012\u0006\u0010\u0004\u001a\u00020\u0001H\u0002\u001a \u0010\u0000\u001a\u00020\u00052\u0006\u0010\u0002\u001a\u00020\u00052\u0006\u0010\u0003\u001a\u00020\u00052\u0006\u0010\u0004\u001a\u00020\u0005H\u0002\u001a \u0010\u0006\u001a\u00020\u00012\u0006\u0010\u0007\u001a\u00020\u00012\u0006\u0010\b\u001a\u00020\u00012\u0006\u0010\t\u001a\u00020\u0001H\u0001\u001a \u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0007\u001a\u00020\u00052\u0006\u0010\b\u001a\u00020\u00052\u0006\u0010\t\u001a\u00020\u0005H\u0001\u001a\u0018\u0010\n\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u0001H\u0002\u001a\u0018\u0010\n\u001a\u00020\u00052\u0006\u0010\u0002\u001a\u00020\u00052\u0006\u0010\u0003\u001a\u00020\u0005H\u0002¨\u0006\u000b"}, m8860e = {"differenceModulo", "", "a", "b", "c", "", "getProgressionLastElement", "start", "end", "step", "mod", "kotlin-stdlib"})
/* renamed from: z1.cff */
/* loaded from: classes3.dex */
public final class progressionUtil {
    /* renamed from: a */
    private static final int m5469a(int i, int i2) {
        int i3 = i % i2;
        return i3 >= 0 ? i3 : i3 + i2;
    }

    /* renamed from: a */
    private static final long m5467a(long j, long j2) {
        long j3 = j % j2;
        return j3 >= 0 ? j3 : j3 + j2;
    }

    /* renamed from: b */
    private static final int m5465b(int i, int i2, int i3) {
        return m5469a(m5469a(i, i3) - m5469a(i2, i3), i3);
    }

    /* renamed from: b */
    private static final long m5464b(long j, long j2, long j3) {
        return m5467a(m5467a(j, j3) - m5467a(j2, j3), j3);
    }

    @bwt
    /* renamed from: a */
    public static final int m5468a(int i, int i2, int i3) {
        if (i3 > 0) {
            return i >= i2 ? i2 : i2 - m5465b(i2, i, i3);
        }
        if (i3 < 0) {
            return i <= i2 ? i2 : i2 + m5465b(i, i2, -i3);
        }
        throw new IllegalArgumentException("Step is zero.");
    }

    @bwt
    /* renamed from: a */
    public static final long m5466a(long j, long j2, long j3) {
        int i = (j3 > 0L ? 1 : (j3 == 0L ? 0 : -1));
        if (i > 0) {
            return j >= j2 ? j2 : j2 - m5464b(j2, j, j3);
        }
        if (i < 0) {
            return j <= j2 ? j2 : j2 + m5464b(j, j2, -j3);
        }
        throw new IllegalArgumentException("Step is zero.");
    }
}
