package p110z1;

import java.util.concurrent.TimeUnit;
import org.apache.commons.p105io.FilenameUtils;

/* compiled from: Clocks.kt */
@bwy(m8750a = "1.3")
@Metadata(m8864a = 1, m8863b = {1, 1, 15}, m8862c = {1, 0, 3}, m8861d = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0007\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u001a\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0002ø\u0001\u0000¢\u0006\u0004\b\t\u0010\nJ\u001b\u0010\u000b\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0086\u0002ø\u0001\u0000¢\u0006\u0004\b\f\u0010\nJ\b\u0010\r\u001a\u00020\u0004H\u0014R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u000e"}, m8860e = {"Lkotlin/time/TestClock;", "Lkotlin/time/AbstractLongClock;", "()V", "reading", "", "overflow", "", "duration", "Lkotlin/time/Duration;", "overflow-LRDsOJo", "(D)V", "plusAssign", "plusAssign-LRDsOJo", "read", "kotlin-stdlib"})
@ExperimentalTime
/* renamed from: z1.cqr */
/* loaded from: classes3.dex */
public final class cqr extends cqc {

    /* renamed from: a */
    private long f21095a;

    public cqr() {
        super(TimeUnit.NANOSECONDS);
    }

    @Override // p110z1.cqc
    /* renamed from: a */
    protected long mo3539a() {
        return this.f21095a;
    }

    /* renamed from: a */
    public final void m3538a(double d) {
        long j;
        double a = Duration.m3650a(d, m3666c());
        long j2 = (long) a;
        if (j2 == Long.MIN_VALUE || j2 == cjm.f20759b) {
            double d2 = this.f21095a + a;
            if (d2 > ((double) cjm.f20759b) || d2 < Long.MIN_VALUE) {
                m3537b(d);
            }
            j = (long) d2;
        } else {
            long j3 = this.f21095a;
            j = j3 + j2;
            if ((j2 ^ j3) >= 0 && (j3 ^ j) < 0) {
                m3537b(d);
            }
        }
        this.f21095a = j;
    }

    /* renamed from: b */
    private final void m3537b(double d) {
        throw new IllegalStateException("TestClock will overflow if its reading " + this.f21095a + "ns is advanced by " + Duration.m3608u(d) + FilenameUtils.EXTENSION_SEPARATOR);
    }
}
