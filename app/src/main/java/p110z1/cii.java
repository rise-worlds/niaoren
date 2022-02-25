package p110z1;

/* compiled from: ArrayIterators.kt */
@Metadata(m8864a = 2, m8863b = {1, 1, 15}, m8862c = {1, 0, 3}, m8861d = {"\u0000F\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0019\n\u0002\u0018\u0002\n\u0002\u0010\u0013\n\u0002\u0018\u0002\n\u0002\u0010\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0015\n\u0002\u0018\u0002\n\u0002\u0010\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0017\n\u0000\u001a\u000e\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003\u001a\u000e\u0010\u0000\u001a\u00020\u00042\u0006\u0010\u0002\u001a\u00020\u0005\u001a\u000e\u0010\u0000\u001a\u00020\u00062\u0006\u0010\u0002\u001a\u00020\u0007\u001a\u000e\u0010\u0000\u001a\u00020\b2\u0006\u0010\u0002\u001a\u00020\t\u001a\u000e\u0010\u0000\u001a\u00020\n2\u0006\u0010\u0002\u001a\u00020\u000b\u001a\u000e\u0010\u0000\u001a\u00020\f2\u0006\u0010\u0002\u001a\u00020\r\u001a\u000e\u0010\u0000\u001a\u00020\u000e2\u0006\u0010\u0002\u001a\u00020\u000f\u001a\u000e\u0010\u0000\u001a\u00020\u00102\u0006\u0010\u0002\u001a\u00020\u0011¨\u0006\u0012"}, m8860e = {"iterator", "Lkotlin/collections/BooleanIterator;", "array", "", "Lkotlin/collections/ByteIterator;", "", "Lkotlin/collections/CharIterator;", "", "Lkotlin/collections/DoubleIterator;", "", "Lkotlin/collections/FloatIterator;", "", "Lkotlin/collections/IntIterator;", "", "Lkotlin/collections/LongIterator;", "", "Lkotlin/collections/ShortIterator;", "", "kotlin-stdlib"})
/* renamed from: z1.cii */
/* loaded from: classes3.dex */
public final class cii {
    @NotNull
    /* renamed from: a */
    public static final bzi m5257a(@NotNull byte[] bArr) {
        cji.m5162f(bArr, "array");
        return new cib(bArr);
    }

    @NotNull
    /* renamed from: a */
    public static final bzj m5256a(@NotNull char[] cArr) {
        cji.m5162f(cArr, "array");
        return new cic(cArr);
    }

    @NotNull
    /* renamed from: a */
    public static final cbc m5251a(@NotNull short[] sArr) {
        cji.m5162f(sArr, "array");
        return new cik(sArr);
    }

    @NotNull
    /* renamed from: a */
    public static final cai m5253a(@NotNull int[] iArr) {
        cji.m5162f(iArr, "array");
        return new cif(iArr);
    }

    @NotNull
    /* renamed from: a */
    public static final caj m5252a(@NotNull long[] jArr) {
        cji.m5162f(jArr, "array");
        return new cij(jArr);
    }

    @NotNull
    /* renamed from: a */
    public static final caa m5254a(@NotNull float[] fArr) {
        cji.m5162f(fArr, "array");
        return new cie(fArr);
    }

    @NotNull
    /* renamed from: a */
    public static final bzv m5255a(@NotNull double[] dArr) {
        cji.m5162f(dArr, "array");
        return new cid(dArr);
    }

    @NotNull
    /* renamed from: a */
    public static final Iterators m5250a(@NotNull boolean[] zArr) {
        cji.m5162f(zArr, "array");
        return new ArrayIterators(zArr);
    }
}
