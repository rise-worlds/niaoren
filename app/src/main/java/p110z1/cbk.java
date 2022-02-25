package p110z1;

import java.util.Collection;

/* compiled from: _UCollections.kt */
@Metadata(m8864a = 5, m8863b = {1, 1, 15}, m8862c = {1, 0, 3}, m8861d = {"\u0000F\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u001c\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u001e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\u001c\u0010\u0000\u001a\u00020\u0001*\b\u0012\u0004\u0012\u00020\u00030\u0002H\u0007ø\u0001\u0000¢\u0006\u0004\b\u0004\u0010\u0005\u001a\u001c\u0010\u0000\u001a\u00020\u0001*\b\u0012\u0004\u0012\u00020\u00010\u0002H\u0007ø\u0001\u0000¢\u0006\u0004\b\u0006\u0010\u0005\u001a\u001c\u0010\u0000\u001a\u00020\u0007*\b\u0012\u0004\u0012\u00020\u00070\u0002H\u0007ø\u0001\u0000¢\u0006\u0004\b\b\u0010\t\u001a\u001c\u0010\u0000\u001a\u00020\u0001*\b\u0012\u0004\u0012\u00020\n0\u0002H\u0007ø\u0001\u0000¢\u0006\u0004\b\u000b\u0010\u0005\u001a\u001a\u0010\f\u001a\u00020\r*\b\u0012\u0004\u0012\u00020\u00030\u000eH\u0007ø\u0001\u0000¢\u0006\u0002\u0010\u000f\u001a\u001a\u0010\u0010\u001a\u00020\u0011*\b\u0012\u0004\u0012\u00020\u00010\u000eH\u0007ø\u0001\u0000¢\u0006\u0002\u0010\u0012\u001a\u001a\u0010\u0013\u001a\u00020\u0014*\b\u0012\u0004\u0012\u00020\u00070\u000eH\u0007ø\u0001\u0000¢\u0006\u0002\u0010\u0015\u001a\u001a\u0010\u0016\u001a\u00020\u0017*\b\u0012\u0004\u0012\u00020\n0\u000eH\u0007ø\u0001\u0000¢\u0006\u0002\u0010\u0018\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0019"}, m8860e = {"sum", "Lkotlin/UInt;", "", "Lkotlin/UByte;", "sumOfUByte", "(Ljava/lang/Iterable;)I", "sumOfUInt", "Lkotlin/ULong;", "sumOfULong", "(Ljava/lang/Iterable;)J", "Lkotlin/UShort;", "sumOfUShort", "toUByteArray", "Lkotlin/UByteArray;", "", "(Ljava/util/Collection;)[B", "toUIntArray", "Lkotlin/UIntArray;", "(Ljava/util/Collection;)[I", "toULongArray", "Lkotlin/ULongArray;", "(Ljava/util/Collection;)[J", "toUShortArray", "Lkotlin/UShortArray;", "(Ljava/util/Collection;)[S", "kotlin-stdlib"}, m8859f = "kotlin/collections/UCollectionsKt", m8857h = 1)
/* renamed from: z1.cbk */
/* loaded from: classes3.dex */
class cbk {
    @bwy(m8750a = "1.3")
    @Unsigned
    @NotNull
    /* renamed from: a */
    public static final byte[] m6289a(@NotNull Collection<UByte> collection) {
        cji.m5162f(collection, "$this$toUByteArray");
        byte[] a = UByteArray.m8660a(collection.size());
        int i = 0;
        for (UByte bxkVar : collection) {
            i++;
            UByteArray.m8656a(a, i, bxkVar.m8705b());
        }
        return a;
    }

    @bwy(m8750a = "1.3")
    @Unsigned
    @NotNull
    /* renamed from: b */
    public static final int[] m6287b(@NotNull Collection<UInt> collection) {
        cji.m5162f(collection, "$this$toUIntArray");
        int[] b = UIntArray.m8575b(collection.size());
        int i = 0;
        for (UInt bxoVar : collection) {
            i++;
            UIntArray.m8580a(b, i, bxoVar.m8629b());
        }
        return b;
    }

    @bwy(m8750a = "1.3")
    @Unsigned
    @NotNull
    /* renamed from: c */
    public static final long[] m6285c(@NotNull Collection<ULong> collection) {
        cji.m5162f(collection, "$this$toULongArray");
        long[] a = ULongArray.m8503a(collection.size());
        int i = 0;
        for (ULong bxsVar : collection) {
            i++;
            ULongArray.m8499a(a, i, bxsVar.m8549b());
        }
        return a;
    }

    @bwy(m8750a = "1.3")
    @Unsigned
    @NotNull
    /* renamed from: d */
    public static final short[] m6283d(@NotNull Collection<UShort> collection) {
        cji.m5162f(collection, "$this$toUShortArray");
        short[] a = UShortArray.m8397a(collection.size());
        int i = 0;
        for (UShort bxyVar : collection) {
            i++;
            UShortArray.m8393a(a, i, bxyVar.m8441b());
        }
        return a;
    }

    @bwy(m8750a = "1.3")
    @Unsigned
    @cgo(m5270a = "sumOfUInt")
    /* renamed from: a */
    public static final int m6290a(@NotNull Iterable<UInt> iterable) {
        cji.m5162f(iterable, "$this$sum");
        int i = 0;
        for (UInt bxoVar : iterable) {
            i = UInt.m8628b(i + bxoVar.m8629b());
        }
        return i;
    }

    @bwy(m8750a = "1.3")
    @Unsigned
    @cgo(m5270a = "sumOfULong")
    /* renamed from: b */
    public static final long m6288b(@NotNull Iterable<ULong> iterable) {
        cji.m5162f(iterable, "$this$sum");
        long j = 0;
        for (ULong bxsVar : iterable) {
            j = ULong.m8548b(j + bxsVar.m8549b());
        }
        return j;
    }

    @bwy(m8750a = "1.3")
    @Unsigned
    @cgo(m5270a = "sumOfUByte")
    /* renamed from: c */
    public static final int m6286c(@NotNull Iterable<UByte> iterable) {
        cji.m5162f(iterable, "$this$sum");
        int i = 0;
        for (UByte bxkVar : iterable) {
            i = UInt.m8628b(i + UInt.m8628b(bxkVar.m8705b() & 255));
        }
        return i;
    }

    @bwy(m8750a = "1.3")
    @Unsigned
    @cgo(m5270a = "sumOfUShort")
    /* renamed from: d */
    public static final int m6284d(@NotNull Iterable<UShort> iterable) {
        cji.m5162f(iterable, "$this$sum");
        int i = 0;
        for (UShort bxyVar : iterable) {
            i = UInt.m8628b(i + UInt.m8628b(bxyVar.m8441b() & 65535));
        }
        return i;
    }
}
