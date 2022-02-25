package p110z1;

@Metadata(m8864a = 2, m8863b = {1, 1, 15}, m8862c = {1, 0, 3}, m8861d = {"\u0000\u0014\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\u001a\u0017\u0010\u0000\u001a\u00020\u00012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H\u0086\b\u001a\u0017\u0010\u0005\u001a\u00020\u00012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H\u0086\b¨\u0006\u0006"}, m8860e = {"measureNanoTime", "", "block", "Lkotlin/Function0;", "", "measureTimeMillis", "kotlin-stdlib"})
@cgo(m5270a = "TimingKt")
/* renamed from: z1.cos */
/* loaded from: classes3.dex */
public final class Timing {
    /* renamed from: a */
    public static final long m4254a(@NotNull chc<Unit> chcVar) {
        cji.m5162f(chcVar, "block");
        long currentTimeMillis = System.currentTimeMillis();
        chcVar.invoke();
        return System.currentTimeMillis() - currentTimeMillis;
    }

    /* renamed from: b */
    public static final long m4253b(@NotNull chc<Unit> chcVar) {
        cji.m5162f(chcVar, "block");
        long nanoTime = System.nanoTime();
        chcVar.invoke();
        return System.nanoTime() - nanoTime;
    }
}
