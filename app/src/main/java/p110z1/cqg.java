package p110z1;

/* compiled from: Clock.kt */
@bwy(m8750a = "1.3")
@Metadata(m8864a = 1, m8863b = {1, 1, 15}, m8862c = {1, 0, 3}, m8861d = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\b\b'\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u0004H&ø\u0001\u0000¢\u0006\u0002\u0010\u0005J\u0006\u0010\u0006\u001a\u00020\u0007J\u0006\u0010\b\u001a\u00020\u0007J\u001b\u0010\t\u001a\u00020\u00002\u0006\u0010\n\u001a\u00020\u0004H\u0096\u0002ø\u0001\u0000¢\u0006\u0004\b\u000b\u0010\fJ\u001b\u0010\r\u001a\u00020\u00002\u0006\u0010\n\u001a\u00020\u0004H\u0096\u0002ø\u0001\u0000¢\u0006\u0004\b\u000e\u0010\f\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u000f"}, m8860e = {"Lkotlin/time/ClockMark;", "", "()V", "elapsedNow", "Lkotlin/time/Duration;", "()D", "hasNotPassedNow", "", "hasPassedNow", "minus", "duration", "minus-LRDsOJo", "(D)Lkotlin/time/ClockMark;", "plus", "plus-LRDsOJo", "kotlin-stdlib"})
@ExperimentalTime
/* renamed from: z1.cqg */
/* loaded from: classes3.dex */
public abstract class cqg {
    /* renamed from: a */
    public abstract double mo3660a();

    @NotNull
    /* renamed from: a */
    public cqg mo3659a(double d) {
        return new Clock(this, d, null);
    }

    @NotNull
    /* renamed from: b */
    public cqg m3658b(double d) {
        return mo3659a(Duration.m3642b(d));
    }

    /* renamed from: d */
    public final boolean m3657d() {
        return !Duration.m3637c(mo3660a());
    }

    /* renamed from: e */
    public final boolean m3656e() {
        return Duration.m3637c(mo3660a());
    }
}
