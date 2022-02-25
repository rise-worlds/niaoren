package p110z1;

/* compiled from: Clock.kt */
@Metadata(m8864a = 2, m8863b = {1, 1, 15}, m8862c = {1, 0, 3}, m8861d = {"\u0000\u0016\n\u0000\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\u0015\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0002H\u0087\n\u001a\u001d\u0010\u0004\u001a\u00020\u0005*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0002H\u0087\nø\u0001\u0000¢\u0006\u0002\u0010\u0006\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0007"}, m8860e = {"compareTo", "", "Lkotlin/time/ClockMark;", "other", "minus", "Lkotlin/time/Duration;", "(Lkotlin/time/ClockMark;Lkotlin/time/ClockMark;)D", "kotlin-stdlib"})
/* renamed from: z1.cqf */
/* loaded from: classes3.dex */
public final class cqf {
    @bwy(m8750a = "1.3")
    @Annotations(m8902a = "Subtracting one ClockMark from another is not a well defined operation because these clock marks could have been obtained from the different clocks.", m8900c = bvk.ERROR)
    @cey
    @ExperimentalTime
    /* renamed from: a */
    private static final double m3662a(@NotNull cqg cqgVar, cqg cqgVar2) {
        cji.m5162f(cqgVar, "$this$minus");
        throw new Error("Operation is disallowed.");
    }

    @bwy(m8750a = "1.3")
    @Annotations(m8902a = "Comparing one ClockMark to another is not a well defined operation because these clock marks could have been obtained from the different clocks.", m8900c = bvk.ERROR)
    @cey
    @ExperimentalTime
    /* renamed from: b */
    private static final int m3661b(@NotNull cqg cqgVar, cqg cqgVar2) {
        cji.m5162f(cqgVar, "$this$compareTo");
        throw new Error("Operation is disallowed.");
    }
}
