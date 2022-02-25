package p110z1;

import java.util.Random;

/* compiled from: PlatformRandom.kt */
@Metadata(m8864a = 1, m8863b = {1, 1, 15}, m8862c = {1, 0, 3}, m8861d = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0002\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, m8860e = {"Lkotlin/random/PlatformRandom;", "Lkotlin/random/AbstractPlatformRandom;", "impl", "Ljava/util/Random;", "(Ljava/util/Random;)V", "getImpl", "()Ljava/util/Random;", "kotlin-stdlib"})
/* renamed from: z1.clo */
/* loaded from: classes3.dex */
final class clo extends PlatformRandom {
    @NotNull

    /* renamed from: c */
    private final Random f20806c;

    public clo(@NotNull Random random) {
        cji.m5162f(random, "impl");
        this.f20806c = random;
    }

    @Override // p110z1.PlatformRandom
    @NotNull
    /* renamed from: a */
    public Random mo4908a() {
        return this.f20806c;
    }
}
