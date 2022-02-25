package p110z1;

import java.util.concurrent.TimeUnit;

@bwy(m8750a = "1.3")
@Metadata(m8864a = 1, m8863b = {1, 1, 15}, m8862c = {1, 0, 3}, m8861d = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0000\bÇ\u0002\u0018\u00002\u00020\u00012\u00020\u0002B\u0007\b\u0002¢\u0006\u0002\u0010\u0003J\b\u0010\u0004\u001a\u00020\u0005H\u0014J\b\u0010\u0006\u001a\u00020\u0007H\u0016¨\u0006\b"}, m8860e = {"Lkotlin/time/MonoClock;", "Lkotlin/time/AbstractLongClock;", "Lkotlin/time/Clock;", "()V", "read", "", "toString", "", "kotlin-stdlib"})
@ExperimentalTime
/* renamed from: z1.cqq */
/* loaded from: classes3.dex */
public final class MonoClock extends cqc implements cqe {

    /* renamed from: a */
    public static final MonoClock f21094a = new MonoClock();

    @NotNull
    public String toString() {
        return "Clock(System.nanoTime())";
    }

    private MonoClock() {
        super(TimeUnit.NANOSECONDS);
    }

    @Override // p110z1.cqc
    /* renamed from: a */
    protected long mo3539a() {
        return System.nanoTime();
    }
}
