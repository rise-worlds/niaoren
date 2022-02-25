package p110z1;

import java.util.concurrent.TimeUnit;

/* compiled from: Clocks.kt */
@bwy(m8750a = "1.3")
@Metadata(m8864a = 1, m8863b = {1, 1, 15}, m8862c = {1, 0, 3}, m8861d = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\b'\u0018\u00002\u00020\u0001:\u0001\fB\u0011\u0012\n\u0010\u0002\u001a\u00060\u0003j\u0002`\u0004¢\u0006\u0002\u0010\u0005J\b\u0010\b\u001a\u00020\tH\u0016J\b\u0010\n\u001a\u00020\u000bH$R\u0018\u0010\u0002\u001a\u00060\u0003j\u0002`\u0004X\u0084\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\r"}, m8860e = {"Lkotlin/time/AbstractLongClock;", "Lkotlin/time/Clock;", "unit", "Ljava/util/concurrent/TimeUnit;", "Lkotlin/time/DurationUnit;", "(Ljava/util/concurrent/TimeUnit;)V", "getUnit", "()Ljava/util/concurrent/TimeUnit;", "markNow", "Lkotlin/time/ClockMark;", "read", "", "LongClockMark", "kotlin-stdlib"})
@ExperimentalTime
/* renamed from: z1.cqc */
/* loaded from: classes3.dex */
public abstract class cqc implements cqe {
    @NotNull

    /* renamed from: a */
    private final TimeUnit f21079a;

    /* renamed from: a */
    protected abstract long mo3539a();

    public cqc(@NotNull TimeUnit timeUnit) {
        cji.m5162f(timeUnit, "unit");
        this.f21079a = timeUnit;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @NotNull
    /* renamed from: c */
    public final TimeUnit m3666c() {
        return this.f21079a;
    }

    /* compiled from: Clocks.kt */
    @Metadata(m8864a = 1, m8863b = {1, 1, 15}, m8862c = {1, 0, 3}, m8861d = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\b\u0002\u0018\u00002\u00020\u0001B \u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007ø\u0001\u0000¢\u0006\u0002\u0010\bJ\u0010\u0010\n\u001a\u00020\u0007H\u0016ø\u0001\u0000¢\u0006\u0002\u0010\u000bJ\u001b\u0010\f\u001a\u00020\u00012\u0006\u0010\r\u001a\u00020\u0007H\u0096\u0002ø\u0001\u0000¢\u0006\u0004\b\u000e\u0010\u000fR\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0013\u0010\u0006\u001a\u00020\u0007X\u0082\u0004ø\u0001\u0000¢\u0006\u0004\n\u0002\u0010\tR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0010"}, m8860e = {"Lkotlin/time/AbstractLongClock$LongClockMark;", "Lkotlin/time/ClockMark;", "startedAt", "", "clock", "Lkotlin/time/AbstractLongClock;", "offset", "Lkotlin/time/Duration;", "(JLkotlin/time/AbstractLongClock;DLkotlin/jvm/internal/DefaultConstructorMarker;)V", "D", "elapsedNow", "()D", "plus", "duration", "plus-LRDsOJo", "(D)Lkotlin/time/ClockMark;", "kotlin-stdlib"})
    /* renamed from: z1.cqc$a */
    /* loaded from: classes3.dex */
    private static final class C5099a extends cqg {

        /* renamed from: a */
        private final long f21080a;

        /* renamed from: b */
        private final cqc f21081b;

        /* renamed from: c */
        private final double f21082c;

        private C5099a(long j, cqc cqcVar, double d) {
            this.f21080a = j;
            this.f21081b = cqcVar;
            this.f21082c = d;
        }

        public /* synthetic */ C5099a(long j, cqc cqcVar, double d, DefaultConstructorMarker civVar) {
            this(j, cqcVar, d);
        }

        @Override // p110z1.cqg
        /* renamed from: a */
        public double mo3660a() {
            return Duration.m3641b(cqi.m3592a(this.f21081b.mo3539a() - this.f21080a, this.f21081b.m3666c()), this.f21082c);
        }

        @Override // p110z1.cqg
        @NotNull
        /* renamed from: a */
        public cqg mo3659a(double d) {
            return new C5099a(this.f21080a, this.f21081b, Duration.m3653a(this.f21082c, d), null);
        }
    }

    @Override // p110z1.cqe
    @NotNull
    /* renamed from: b */
    public cqg mo3663b() {
        return new C5099a(mo3539a(), this, Duration.f21085a.m3603a(), null);
    }
}
