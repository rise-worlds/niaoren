package p110z1;

/* compiled from: ULongArray.kt */
@Metadata(m8864a = 2, m8863b = {1, 1, 15}, m8862c = {1, 0, 3}, m8861d = {"\u0000\u001a\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\u001a-\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0012\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00060\u0005H\u0087\bø\u0001\u0000¢\u0006\u0002\u0010\u0007\u001a\u001f\u0010\b\u001a\u00020\u00012\n\u0010\t\u001a\u00020\u0001\"\u00020\u0006H\u0087\bø\u0001\u0000¢\u0006\u0004\b\n\u0010\u000b\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\f"}, m8860e = {"ULongArray", "Lkotlin/ULongArray;", "size", "", "init", "Lkotlin/Function1;", "Lkotlin/ULong;", "(ILkotlin/jvm/functions/Function1;)[J", "ulongArrayOf", "elements", "ulongArrayOf-QwZRm1k", "([J)[J", "kotlin-stdlib"})
/* renamed from: z1.bxu */
/* loaded from: classes3.dex */
public final class bxu {
    @bwy(m8750a = "1.3")
    @Unsigned
    @cey
    /* renamed from: a */
    private static final long[] m8483a(long... jArr) {
        return jArr;
    }

    @bwy(m8750a = "1.3")
    @Unsigned
    @cey
    /* renamed from: a */
    private static final long[] m8484a(int i, chd<? super Integer, ULong> chdVar) {
        long[] jArr = new long[i];
        for (int i2 = 0; i2 < i; i2++) {
            jArr[i2] = chdVar.invoke(Integer.valueOf(i2)).m8549b();
        }
        return ULongArray.m8488d(jArr);
    }
}
