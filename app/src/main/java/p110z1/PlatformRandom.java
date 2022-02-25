package p110z1;

import java.util.Random;

@Metadata(m8864a = 1, m8863b = {1, 1, 15}, m8862c = {1, 0, 3}, m8861d = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0012\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0003\n\u0002\u0010\t\n\u0000\b \u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\bH\u0016J\b\u0010\n\u001a\u00020\u000bH\u0016J\u0010\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\rH\u0016J\b\u0010\u000f\u001a\u00020\u0010H\u0016J\b\u0010\u0011\u001a\u00020\u0012H\u0016J\b\u0010\u0013\u001a\u00020\bH\u0016J\u0010\u0010\u0013\u001a\u00020\b2\u0006\u0010\u0014\u001a\u00020\bH\u0016J\b\u0010\u0015\u001a\u00020\u0016H\u0016R\u0012\u0010\u0003\u001a\u00020\u0004X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0017"}, m8860e = {"Lkotlin/random/AbstractPlatformRandom;", "Lkotlin/random/Random;", "()V", "impl", "Ljava/util/Random;", "getImpl", "()Ljava/util/Random;", "nextBits", "", "bitCount", "nextBoolean", "", "nextBytes", "", "array", "nextDouble", "", "nextFloat", "", "nextInt", "until", "nextLong", "", "kotlin-stdlib"})
/* renamed from: z1.cll */
/* loaded from: classes3.dex */
public abstract class PlatformRandom extends Random {
    @NotNull
    /* renamed from: a */
    public abstract Random mo4908a();

    @Override // p110z1.Random
    /* renamed from: a */
    public int mo4863a(int i) {
        return clr.m4885a(mo4908a().nextInt(), i);
    }

    @Override // p110z1.Random
    /* renamed from: b */
    public int mo4862b() {
        return mo4908a().nextInt();
    }

    @Override // p110z1.Random
    /* renamed from: b */
    public int mo4893b(int i) {
        return mo4908a().nextInt(i);
    }

    @Override // p110z1.Random
    /* renamed from: c */
    public long mo4892c() {
        return mo4908a().nextLong();
    }

    @Override // p110z1.Random
    /* renamed from: d */
    public boolean mo4890d() {
        return mo4908a().nextBoolean();
    }

    @Override // p110z1.Random
    /* renamed from: e */
    public double mo4889e() {
        return mo4908a().nextDouble();
    }

    @Override // p110z1.Random
    /* renamed from: f */
    public float mo4888f() {
        return mo4908a().nextFloat();
    }

    @Override // p110z1.Random
    @NotNull
    /* renamed from: a */
    public byte[] mo4895a(@NotNull byte[] bArr) {
        cji.m5162f(bArr, "array");
        mo4908a().nextBytes(bArr);
        return bArr;
    }
}
