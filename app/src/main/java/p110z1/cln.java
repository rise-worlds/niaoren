package p110z1;

import java.util.Random;

/* compiled from: PlatformRandom.kt */
@Metadata(m8864a = 1, m8863b = {1, 1, 15}, m8862c = {1, 0, 3}, m8861d = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0012\n\u0000\n\u0002\u0010\u0006\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0003\b\u0002\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0010\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\nH\u0014J\b\u0010\f\u001a\u00020\bH\u0016J\u0010\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0010H\u0016J\b\u0010\u0011\u001a\u00020\u0012H\u0016J\b\u0010\u0013\u001a\u00020\u0014H\u0016J\b\u0010\u0015\u001a\u00020\nH\u0016J\u0010\u0010\u0015\u001a\u00020\n2\u0006\u0010\u0016\u001a\u00020\nH\u0016J\b\u0010\u0017\u001a\u00020\u0018H\u0016J\u0010\u0010\u0019\u001a\u00020\u000e2\u0006\u0010\u001a\u001a\u00020\u0018H\u0016R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u001b"}, m8860e = {"Lkotlin/random/KotlinRandom;", "Ljava/util/Random;", "impl", "Lkotlin/random/Random;", "(Lkotlin/random/Random;)V", "getImpl", "()Lkotlin/random/Random;", "seedInitialized", "", "next", "", "bits", "nextBoolean", "nextBytes", "", "bytes", "", "nextDouble", "", "nextFloat", "", "nextInt", "bound", "nextLong", "", "setSeed", "seed", "kotlin-stdlib"})
/* renamed from: z1.cln */
/* loaded from: classes3.dex */
final class cln extends Random {
    @NotNull
    private final Random impl;
    private boolean seedInitialized;

    public cln(@NotNull Random clqVar) {
        cji.m5162f(clqVar, "impl");
        this.impl = clqVar;
    }

    @NotNull
    public final Random getImpl() {
        return this.impl;
    }

    @Override // java.util.Random
    protected int next(int i) {
        return this.impl.mo4863a(i);
    }

    @Override // java.util.Random
    public int nextInt() {
        return this.impl.mo4862b();
    }

    @Override // java.util.Random
    public int nextInt(int i) {
        return this.impl.mo4893b(i);
    }

    @Override // java.util.Random
    public boolean nextBoolean() {
        return this.impl.mo4890d();
    }

    @Override // java.util.Random
    public long nextLong() {
        return this.impl.mo4892c();
    }

    @Override // java.util.Random
    public float nextFloat() {
        return this.impl.mo4888f();
    }

    @Override // java.util.Random
    public double nextDouble() {
        return this.impl.mo4889e();
    }

    @Override // java.util.Random
    public void nextBytes(@NotNull byte[] bArr) {
        cji.m5162f(bArr, "bytes");
        this.impl.mo4895a(bArr);
    }

    @Override // java.util.Random
    public void setSeed(long j) {
        if (!this.seedInitialized) {
            this.seedInitialized = true;
            return;
        }
        throw new UnsupportedOperationException("Setting seed is not supported.");
    }
}
