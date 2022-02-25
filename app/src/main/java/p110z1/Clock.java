package p110z1;

@Metadata(m8864a = 1, m8863b = {1, 1, 15}, m8862c = {1, 0, 3}, m8861d = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\f\b\u0003\u0018\u00002\u00020\u0001B\u0018\u0012\u0006\u0010\u0002\u001a\u00020\u0001\u0012\u0006\u0010\u0003\u001a\u00020\u0004ø\u0001\u0000¢\u0006\u0002\u0010\u0005J\u0010\u0010\u000b\u001a\u00020\u0004H\u0016ø\u0001\u0000¢\u0006\u0002\u0010\u0007J\u001b\u0010\f\u001a\u00020\u00012\u0006\u0010\r\u001a\u00020\u0004H\u0096\u0002ø\u0001\u0000¢\u0006\u0004\b\u000e\u0010\u000fR\u0016\u0010\u0003\u001a\u00020\u0004ø\u0001\u0000¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b\u0006\u0010\u0007R\u0011\u0010\u0002\u001a\u00020\u0001¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0010"}, m8860e = {"Lkotlin/time/AdjustedClockMark;", "Lkotlin/time/ClockMark;", "mark", "adjustment", "Lkotlin/time/Duration;", "(Lkotlin/time/ClockMark;DLkotlin/jvm/internal/DefaultConstructorMarker;)V", "getAdjustment", "()D", "D", "getMark", "()Lkotlin/time/ClockMark;", "elapsedNow", "plus", "duration", "plus-LRDsOJo", "(D)Lkotlin/time/ClockMark;", "kotlin-stdlib"})
@ExperimentalTime
/* renamed from: z1.cqd */
/* loaded from: classes3.dex */
final class Clock extends cqg {
    @NotNull

    /* renamed from: a */
    private final cqg f21083a;

    /* renamed from: b */
    private final double f21084b;

    private Clock(cqg cqgVar, double d) {
        this.f21083a = cqgVar;
        this.f21084b = d;
    }

    public /* synthetic */ Clock(cqg cqgVar, double d, DefaultConstructorMarker civVar) {
        this(cqgVar, d);
    }

    @NotNull
    /* renamed from: b */
    public final cqg m3665b() {
        return this.f21083a;
    }

    /* renamed from: c */
    public final double m3664c() {
        return this.f21084b;
    }

    @Override // p110z1.cqg
    /* renamed from: a */
    public double mo3660a() {
        return Duration.m3641b(this.f21083a.mo3660a(), this.f21084b);
    }

    @Override // p110z1.cqg
    @NotNull
    /* renamed from: a */
    public cqg mo3659a(double d) {
        return new Clock(this.f21083a, Duration.m3653a(this.f21084b, d), null);
    }
}
