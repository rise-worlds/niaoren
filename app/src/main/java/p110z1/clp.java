package p110z1;

import java.util.Random;

/* compiled from: PlatformRandom.kt */
@Metadata(m8864a = 2, m8863b = {1, 1, 15}, m8862c = {1, 0, 3}, m8861d = {"\u0000\u001e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0006\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\t\u0010\u0000\u001a\u00020\u0001H\u0081\b\u001a\u0018\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0005H\u0000\u001a\f\u0010\u0007\u001a\u00020\b*\u00020\u0001H\u0007\u001a\f\u0010\t\u001a\u00020\u0001*\u00020\bH\u0007¨\u0006\n"}, m8860e = {"defaultPlatformRandom", "Lkotlin/random/Random;", "doubleFromParts", "", "hi26", "", "low27", "asJavaRandom", "Ljava/util/Random;", "asKotlinRandom", "kotlin-stdlib"})
/* renamed from: z1.clp */
/* loaded from: classes3.dex */
public final class clp {
    /* renamed from: a */
    public static final double m4906a(int i, int i2) {
        return ((i << 27) + i2) / 9007199254740992L;
    }

    @bwy(m8750a = "1.3")
    @NotNull
    /* renamed from: a */
    public static final Random m4904a(@NotNull Random clqVar) {
        Random a;
        cji.m5162f(clqVar, "$this$asJavaRandom");
        PlatformRandom cllVar = (PlatformRandom) (!(clqVar instanceof PlatformRandom) ? null : clqVar);
        return (cllVar == null || (a = cllVar.mo4908a()) == null) ? new cln(clqVar) : a;
    }

    @bwy(m8750a = "1.3")
    @NotNull
    /* renamed from: a */
    public static final Random m4905a(@NotNull Random random) {
        Random impl;
        cji.m5162f(random, "$this$asKotlinRandom");
        cln clnVar = (cln) (!(random instanceof cln) ? null : random);
        return (clnVar == null || (impl = clnVar.getImpl()) == null) ? new clo(random) : impl;
    }

    @cey
    /* renamed from: a */
    private static final Random m4907a() {
        return cfe.f20646a.m5474a();
    }
}
