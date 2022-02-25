package p110z1;

import java.util.Iterator;

/* compiled from: _USequences.kt */
@Metadata(m8864a = 5, m8863b = {1, 1, 15}, m8862c = {1, 0, 3}, m8861d = {"\u0000\"\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\u001c\u0010\u0000\u001a\u00020\u0001*\b\u0012\u0004\u0012\u00020\u00030\u0002H\u0007ø\u0001\u0000¢\u0006\u0004\b\u0004\u0010\u0005\u001a\u001c\u0010\u0000\u001a\u00020\u0001*\b\u0012\u0004\u0012\u00020\u00010\u0002H\u0007ø\u0001\u0000¢\u0006\u0004\b\u0006\u0010\u0005\u001a\u001c\u0010\u0000\u001a\u00020\u0007*\b\u0012\u0004\u0012\u00020\u00070\u0002H\u0007ø\u0001\u0000¢\u0006\u0004\b\b\u0010\t\u001a\u001c\u0010\u0000\u001a\u00020\u0001*\b\u0012\u0004\u0012\u00020\n0\u0002H\u0007ø\u0001\u0000¢\u0006\u0004\b\u000b\u0010\u0005\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\f"}, m8860e = {"sum", "Lkotlin/UInt;", "Lkotlin/sequences/Sequence;", "Lkotlin/UByte;", "sumOfUByte", "(Lkotlin/sequences/Sequence;)I", "sumOfUInt", "Lkotlin/ULong;", "sumOfULong", "(Lkotlin/sequences/Sequence;)J", "Lkotlin/UShort;", "sumOfUShort", "kotlin-stdlib"}, m8859f = "kotlin/sequences/USequencesKt", m8857h = 1)
/* renamed from: z1.coq */
/* loaded from: classes3.dex */
class coq {
    @bwy(m8750a = "1.3")
    @Unsigned
    @cgo(m5270a = "sumOfUInt")
    /* renamed from: a */
    public static final int m4259a(@NotNull Sequence<UInt> cobVar) {
        cji.m5162f(cobVar, "$this$sum");
        Iterator<UInt> a = cobVar.mo3707a();
        int i = 0;
        while (a.hasNext()) {
            i = UInt.m8628b(i + a.next().m8629b());
        }
        return i;
    }

    @bwy(m8750a = "1.3")
    @Unsigned
    @cgo(m5270a = "sumOfULong")
    /* renamed from: b */
    public static final long m4258b(@NotNull Sequence<ULong> cobVar) {
        cji.m5162f(cobVar, "$this$sum");
        Iterator<ULong> a = cobVar.mo3707a();
        long j = 0;
        while (a.hasNext()) {
            j = ULong.m8548b(j + a.next().m8549b());
        }
        return j;
    }

    @bwy(m8750a = "1.3")
    @Unsigned
    @cgo(m5270a = "sumOfUByte")
    /* renamed from: c */
    public static final int m4257c(@NotNull Sequence<UByte> cobVar) {
        cji.m5162f(cobVar, "$this$sum");
        Iterator<UByte> a = cobVar.mo3707a();
        int i = 0;
        while (a.hasNext()) {
            i = UInt.m8628b(i + UInt.m8628b(a.next().m8705b() & 255));
        }
        return i;
    }

    @bwy(m8750a = "1.3")
    @Unsigned
    @cgo(m5270a = "sumOfUShort")
    /* renamed from: d */
    public static final int m4256d(@NotNull Sequence<UShort> cobVar) {
        cji.m5162f(cobVar, "$this$sum");
        Iterator<UShort> a = cobVar.mo3707a();
        int i = 0;
        while (a.hasNext()) {
            i = UInt.m8628b(i + UInt.m8628b(a.next().m8441b() & 65535));
        }
        return i;
    }
}
