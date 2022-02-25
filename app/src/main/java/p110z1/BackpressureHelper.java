package p110z1;

import java.util.concurrent.atomic.AtomicLong;

/* renamed from: z1.bso */
/* loaded from: classes3.dex */
public final class BackpressureHelper {
    /* renamed from: a */
    public static long m9450a(long j, long j2) {
        long j3 = j + j2;
        return j3 < 0 ? cjm.f20759b : j3;
    }

    private BackpressureHelper() {
        throw new IllegalStateException("No instances!");
    }

    /* renamed from: b */
    public static long m9448b(long j, long j2) {
        long j3 = j * j2;
        return (((j | j2) >>> 31) == 0 || j3 / j == j2) ? j3 : cjm.f20759b;
    }

    /* renamed from: a */
    public static long m9449a(AtomicLong atomicLong, long j) {
        long j2;
        do {
            j2 = atomicLong.get();
            if (j2 == cjm.f20759b) {
                return cjm.f20759b;
            }
        } while (!atomicLong.compareAndSet(j2, m9450a(j2, j)));
        return j2;
    }

    /* renamed from: b */
    public static long m9447b(AtomicLong atomicLong, long j) {
        long j2;
        do {
            j2 = atomicLong.get();
            if (j2 == Long.MIN_VALUE) {
                return Long.MIN_VALUE;
            }
            if (j2 == cjm.f20759b) {
                return cjm.f20759b;
            }
        } while (!atomicLong.compareAndSet(j2, m9450a(j2, j)));
        return j2;
    }

    /* renamed from: c */
    public static long m9446c(AtomicLong atomicLong, long j) {
        long j2;
        long j3;
        do {
            j2 = atomicLong.get();
            if (j2 == cjm.f20759b) {
                return cjm.f20759b;
            }
            j3 = j2 - j;
            if (j3 < 0) {
                RxJavaPlugins.m9212a(new IllegalStateException("More produced than requested: " + j3));
                j3 = 0L;
            }
        } while (!atomicLong.compareAndSet(j2, j3));
        return j3;
    }

    /* renamed from: d */
    public static long m9445d(AtomicLong atomicLong, long j) {
        long j2;
        long j3;
        do {
            j2 = atomicLong.get();
            if (j2 == Long.MIN_VALUE) {
                return Long.MIN_VALUE;
            }
            if (j2 == cjm.f20759b) {
                return cjm.f20759b;
            }
            j3 = j2 - j;
            if (j3 < 0) {
                RxJavaPlugins.m9212a(new IllegalStateException("More produced than requested: " + j3));
                j3 = 0L;
            }
        } while (!atomicLong.compareAndSet(j2, j3));
        return j3;
    }
}
