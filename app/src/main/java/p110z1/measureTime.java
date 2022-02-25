package p110z1;

import com.googlecode.tesseract.android.TessBaseAPI;

@Metadata(m8864a = 2, m8863b = {1, 1, 15}, m8862c = {1, 0, 3}, m8861d = {"\u0000\"\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a,\u0010\u0000\u001a\u00020\u00012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H\u0087\bø\u0001\u0000\u0082\u0002\n\n\b\b\u0001\u0012\u0002\u0010\u0001 \u0001¢\u0006\u0002\u0010\u0005\u001a0\u0010\u0006\u001a\b\u0012\u0004\u0012\u0002H\b0\u0007\"\u0004\b\u0000\u0010\b2\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u0002H\b0\u0003H\u0087\b\u0082\u0002\n\n\b\b\u0001\u0012\u0002\u0010\u0001 \u0001\u001a0\u0010\u0000\u001a\u00020\u0001*\u00020\t2\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H\u0087\bø\u0001\u0000\u0082\u0002\n\n\b\b\u0001\u0012\u0002\u0010\u0001 \u0001¢\u0006\u0002\u0010\n\u001a4\u0010\u0006\u001a\b\u0012\u0004\u0012\u0002H\b0\u0007\"\u0004\b\u0000\u0010\b*\u00020\t2\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u0002H\b0\u0003H\u0087\b\u0082\u0002\n\n\b\b\u0001\u0012\u0002\u0010\u0001 \u0001\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u000b"}, m8860e = {"measureTime", "Lkotlin/time/Duration;", "block", "Lkotlin/Function0;", "", "(Lkotlin/jvm/functions/Function0;)D", "measureTimedValue", "Lkotlin/time/TimedValue;", TessBaseAPI.f9204e, "Lkotlin/time/Clock;", "(Lkotlin/time/Clock;Lkotlin/jvm/functions/Function0;)D", "kotlin-stdlib"})
/* renamed from: z1.cqp */
/* loaded from: classes3.dex */
public final class measureTime {
    @bwy(m8750a = "1.3")
    @ExperimentalTime
    /* renamed from: a */
    public static final double m3543a(@NotNull chc<Unit> chcVar) {
        cji.m5162f(chcVar, "block");
        cqg b = MonoClock.f21094a.mo3663b();
        chcVar.invoke();
        return b.mo3660a();
    }

    @bwy(m8750a = "1.3")
    @ExperimentalTime
    /* renamed from: a */
    public static final double m3542a(@NotNull cqe cqeVar, @NotNull chc<Unit> chcVar) {
        cji.m5162f(cqeVar, "$this$measureTime");
        cji.m5162f(chcVar, "block");
        cqg b = cqeVar.mo3663b();
        chcVar.invoke();
        return b.mo3660a();
    }

    @bwy(m8750a = "1.3")
    @ExperimentalTime
    @NotNull
    /* renamed from: b */
    public static final <T> cqs<T> m3541b(@NotNull chc<? extends T> chcVar) {
        cji.m5162f(chcVar, "block");
        return new cqs<>(chcVar.invoke(), MonoClock.f21094a.mo3663b().mo3660a(), null);
    }

    @bwy(m8750a = "1.3")
    @ExperimentalTime
    @NotNull
    /* renamed from: b */
    public static final <T> cqs<T> m3540b(@NotNull cqe cqeVar, @NotNull chc<? extends T> chcVar) {
        cji.m5162f(cqeVar, "$this$measureTimedValue");
        cji.m5162f(chcVar, "block");
        return new cqs<>(chcVar.invoke(), cqeVar.mo3663b().mo3660a(), null);
    }
}
