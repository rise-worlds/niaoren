package p110z1;

import java.util.NoSuchElementException;
import org.apache.commons.p105io.FilenameUtils;
import org.apache.tools.ant.types.selectors.SizeSelector;
import p110z1.UIntRange;
import p110z1.ULongRange;

/* compiled from: _URanges.kt */
@Metadata(m8864a = 5, m8863b = {1, 1, 15}, m8862c = {1, 0, 3}, m8861d = {"\u0000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\u0010\t\n\u0002\b\n\u001a\u001e\u0010\u0000\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0001H\u0007ø\u0001\u0000¢\u0006\u0004\b\u0003\u0010\u0004\u001a\u001e\u0010\u0000\u001a\u00020\u0005*\u00020\u00052\u0006\u0010\u0002\u001a\u00020\u0005H\u0007ø\u0001\u0000¢\u0006\u0004\b\u0006\u0010\u0007\u001a\u001e\u0010\u0000\u001a\u00020\b*\u00020\b2\u0006\u0010\u0002\u001a\u00020\bH\u0007ø\u0001\u0000¢\u0006\u0004\b\t\u0010\n\u001a\u001e\u0010\u0000\u001a\u00020\u000b*\u00020\u000b2\u0006\u0010\u0002\u001a\u00020\u000bH\u0007ø\u0001\u0000¢\u0006\u0004\b\f\u0010\r\u001a\u001e\u0010\u000e\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u000f\u001a\u00020\u0001H\u0007ø\u0001\u0000¢\u0006\u0004\b\u0010\u0010\u0004\u001a\u001e\u0010\u000e\u001a\u00020\u0005*\u00020\u00052\u0006\u0010\u000f\u001a\u00020\u0005H\u0007ø\u0001\u0000¢\u0006\u0004\b\u0011\u0010\u0007\u001a\u001e\u0010\u000e\u001a\u00020\b*\u00020\b2\u0006\u0010\u000f\u001a\u00020\bH\u0007ø\u0001\u0000¢\u0006\u0004\b\u0012\u0010\n\u001a\u001e\u0010\u000e\u001a\u00020\u000b*\u00020\u000b2\u0006\u0010\u000f\u001a\u00020\u000bH\u0007ø\u0001\u0000¢\u0006\u0004\b\u0013\u0010\r\u001a&\u0010\u0014\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00012\u0006\u0010\u000f\u001a\u00020\u0001H\u0007ø\u0001\u0000¢\u0006\u0004\b\u0015\u0010\u0016\u001a&\u0010\u0014\u001a\u00020\u0005*\u00020\u00052\u0006\u0010\u0002\u001a\u00020\u00052\u0006\u0010\u000f\u001a\u00020\u0005H\u0007ø\u0001\u0000¢\u0006\u0004\b\u0017\u0010\u0018\u001a$\u0010\u0014\u001a\u00020\u0005*\u00020\u00052\f\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u00050\u001aH\u0007ø\u0001\u0000¢\u0006\u0004\b\u001b\u0010\u001c\u001a&\u0010\u0014\u001a\u00020\b*\u00020\b2\u0006\u0010\u0002\u001a\u00020\b2\u0006\u0010\u000f\u001a\u00020\bH\u0007ø\u0001\u0000¢\u0006\u0004\b\u001d\u0010\u001e\u001a$\u0010\u0014\u001a\u00020\b*\u00020\b2\f\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\b0\u001aH\u0007ø\u0001\u0000¢\u0006\u0004\b\u001f\u0010 \u001a&\u0010\u0014\u001a\u00020\u000b*\u00020\u000b2\u0006\u0010\u0002\u001a\u00020\u000b2\u0006\u0010\u000f\u001a\u00020\u000bH\u0007ø\u0001\u0000¢\u0006\u0004\b!\u0010\"\u001a\u001f\u0010#\u001a\u00020$*\u00020%2\u0006\u0010&\u001a\u00020\u0001H\u0087\u0002ø\u0001\u0000¢\u0006\u0004\b'\u0010(\u001a\u001f\u0010#\u001a\u00020$*\u00020%2\b\u0010)\u001a\u0004\u0018\u00010\u0005H\u0087\nø\u0001\u0000¢\u0006\u0002\b*\u001a\u001f\u0010#\u001a\u00020$*\u00020%2\u0006\u0010&\u001a\u00020\bH\u0087\u0002ø\u0001\u0000¢\u0006\u0004\b+\u0010,\u001a\u001f\u0010#\u001a\u00020$*\u00020%2\u0006\u0010&\u001a\u00020\u000bH\u0087\u0002ø\u0001\u0000¢\u0006\u0004\b-\u0010.\u001a\u001f\u0010#\u001a\u00020$*\u00020/2\u0006\u0010&\u001a\u00020\u0001H\u0087\u0002ø\u0001\u0000¢\u0006\u0004\b0\u00101\u001a\u001f\u0010#\u001a\u00020$*\u00020/2\u0006\u0010&\u001a\u00020\u0005H\u0087\u0002ø\u0001\u0000¢\u0006\u0004\b2\u00103\u001a\u001f\u0010#\u001a\u00020$*\u00020/2\b\u0010)\u001a\u0004\u0018\u00010\bH\u0087\nø\u0001\u0000¢\u0006\u0002\b4\u001a\u001f\u0010#\u001a\u00020$*\u00020/2\u0006\u0010&\u001a\u00020\u000bH\u0087\u0002ø\u0001\u0000¢\u0006\u0004\b5\u00106\u001a\u001f\u00107\u001a\u000208*\u00020\u00012\u0006\u00109\u001a\u00020\u0001H\u0087\u0004ø\u0001\u0000¢\u0006\u0004\b:\u0010;\u001a\u001f\u00107\u001a\u000208*\u00020\u00052\u0006\u00109\u001a\u00020\u0005H\u0087\u0004ø\u0001\u0000¢\u0006\u0004\b<\u0010=\u001a\u001f\u00107\u001a\u00020>*\u00020\b2\u0006\u00109\u001a\u00020\bH\u0087\u0004ø\u0001\u0000¢\u0006\u0004\b?\u0010@\u001a\u001f\u00107\u001a\u000208*\u00020\u000b2\u0006\u00109\u001a\u00020\u000bH\u0087\u0004ø\u0001\u0000¢\u0006\u0004\bA\u0010B\u001a\u0015\u0010C\u001a\u00020\u0005*\u00020%H\u0087\bø\u0001\u0000¢\u0006\u0002\u0010D\u001a\u001c\u0010C\u001a\u00020\u0005*\u00020%2\u0006\u0010C\u001a\u00020EH\u0007ø\u0001\u0000¢\u0006\u0002\u0010F\u001a\u0015\u0010C\u001a\u00020\b*\u00020/H\u0087\bø\u0001\u0000¢\u0006\u0002\u0010G\u001a\u001c\u0010C\u001a\u00020\b*\u00020/2\u0006\u0010C\u001a\u00020EH\u0007ø\u0001\u0000¢\u0006\u0002\u0010H\u001a\f\u0010I\u001a\u000208*\u000208H\u0007\u001a\f\u0010I\u001a\u00020>*\u00020>H\u0007\u001a\u0015\u0010J\u001a\u000208*\u0002082\u0006\u0010J\u001a\u00020KH\u0087\u0004\u001a\u0015\u0010J\u001a\u00020>*\u00020>2\u0006\u0010J\u001a\u00020LH\u0087\u0004\u001a\u001f\u0010M\u001a\u00020%*\u00020\u00012\u0006\u00109\u001a\u00020\u0001H\u0087\u0004ø\u0001\u0000¢\u0006\u0004\bN\u0010O\u001a\u001f\u0010M\u001a\u00020%*\u00020\u00052\u0006\u00109\u001a\u00020\u0005H\u0087\u0004ø\u0001\u0000¢\u0006\u0004\bP\u0010Q\u001a\u001f\u0010M\u001a\u00020/*\u00020\b2\u0006\u00109\u001a\u00020\bH\u0087\u0004ø\u0001\u0000¢\u0006\u0004\bR\u0010S\u001a\u001f\u0010M\u001a\u00020%*\u00020\u000b2\u0006\u00109\u001a\u00020\u000bH\u0087\u0004ø\u0001\u0000¢\u0006\u0004\bT\u0010U\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006V"}, m8860e = {"coerceAtLeast", "Lkotlin/UByte;", "minimumValue", "coerceAtLeast-Kr8caGY", "(BB)B", "Lkotlin/UInt;", "coerceAtLeast-J1ME1BU", "(II)I", "Lkotlin/ULong;", "coerceAtLeast-eb3DHEI", "(JJ)J", "Lkotlin/UShort;", "coerceAtLeast-5PvTz6A", "(SS)S", "coerceAtMost", "maximumValue", "coerceAtMost-Kr8caGY", "coerceAtMost-J1ME1BU", "coerceAtMost-eb3DHEI", "coerceAtMost-5PvTz6A", "coerceIn", "coerceIn-b33U2AM", "(BBB)B", "coerceIn-WZ9TVnA", "(III)I", "range", "Lkotlin/ranges/ClosedRange;", "coerceIn-wuiCnnA", "(ILkotlin/ranges/ClosedRange;)I", "coerceIn-sambcqE", "(JJJ)J", "coerceIn-JPwROB0", "(JLkotlin/ranges/ClosedRange;)J", "coerceIn-VKSA0NQ", "(SSS)S", "contains", "", "Lkotlin/ranges/UIntRange;", SizeSelector.SIZE_KEY, "contains-68kG9v0", "(Lkotlin/ranges/UIntRange;B)Z", "element", "contains-biwQdVI", "contains-fz5IDCE", "(Lkotlin/ranges/UIntRange;J)Z", "contains-ZsK3CEQ", "(Lkotlin/ranges/UIntRange;S)Z", "Lkotlin/ranges/ULongRange;", "contains-ULb-yJY", "(Lkotlin/ranges/ULongRange;B)Z", "contains-Gab390E", "(Lkotlin/ranges/ULongRange;I)Z", "contains-GYNo2lE", "contains-uhHAxoY", "(Lkotlin/ranges/ULongRange;S)Z", "downTo", "Lkotlin/ranges/UIntProgression;", "to", "downTo-Kr8caGY", "(BB)Lkotlin/ranges/UIntProgression;", "downTo-J1ME1BU", "(II)Lkotlin/ranges/UIntProgression;", "Lkotlin/ranges/ULongProgression;", "downTo-eb3DHEI", "(JJ)Lkotlin/ranges/ULongProgression;", "downTo-5PvTz6A", "(SS)Lkotlin/ranges/UIntProgression;", "random", "(Lkotlin/ranges/UIntRange;)I", "Lkotlin/random/Random;", "(Lkotlin/ranges/UIntRange;Lkotlin/random/Random;)I", "(Lkotlin/ranges/ULongRange;)J", "(Lkotlin/ranges/ULongRange;Lkotlin/random/Random;)J", "reversed", "step", "", "", "until", "until-Kr8caGY", "(BB)Lkotlin/ranges/UIntRange;", "until-J1ME1BU", "(II)Lkotlin/ranges/UIntRange;", "until-eb3DHEI", "(JJ)Lkotlin/ranges/ULongRange;", "until-5PvTz6A", "(SS)Lkotlin/ranges/UIntRange;", "kotlin-stdlib"}, m8859f = "kotlin/ranges/URangesKt", m8857h = 1)
/* renamed from: z1.cmt */
/* loaded from: classes3.dex */
class cmt {
    @bwy(m8750a = "1.3")
    @Unsigned
    @cey
    /* renamed from: a */
    private static final int m4650a(@NotNull cmo cmoVar) {
        return _URanges.m4646a(cmoVar, Random.f20808b);
    }

    @bwy(m8750a = "1.3")
    @Unsigned
    @cey
    /* renamed from: a */
    private static final long m4642a(@NotNull cmr cmrVar) {
        return _URanges.m4638a(cmrVar, Random.f20808b);
    }

    @bwy(m8750a = "1.3")
    @Unsigned
    /* renamed from: a */
    public static final int m4646a(@NotNull cmo cmoVar, @NotNull Random clqVar) {
        cji.m5162f(cmoVar, "$this$random");
        cji.m5162f(clqVar, "random");
        try {
            return URandom.m4870a(clqVar, cmoVar);
        } catch (IllegalArgumentException e) {
            throw new NoSuchElementException(e.getMessage());
        }
    }

    @bwy(m8750a = "1.3")
    @Unsigned
    /* renamed from: a */
    public static final long m4638a(@NotNull cmr cmrVar, @NotNull Random clqVar) {
        cji.m5162f(cmrVar, "$this$random");
        cji.m5162f(clqVar, "random");
        try {
            return URandom.m4869a(clqVar, cmrVar);
        } catch (IllegalArgumentException e) {
            throw new NoSuchElementException(e.getMessage());
        }
    }

    @bwy(m8750a = "1.3")
    @Unsigned
    @cey
    /* renamed from: a */
    private static final boolean m4647a(@NotNull cmo cmoVar, UInt bxoVar) {
        cji.m5162f(cmoVar, "$this$contains");
        return bxoVar != null && cmoVar.m4680a(bxoVar.m8629b());
    }

    @bwy(m8750a = "1.3")
    @Unsigned
    @cey
    /* renamed from: a */
    private static final boolean m4639a(@NotNull cmr cmrVar, ULong bxsVar) {
        cji.m5162f(cmrVar, "$this$contains");
        return bxsVar != null && cmrVar.m4669a(bxsVar.m8549b());
    }

    @bwy(m8750a = "1.3")
    @Unsigned
    /* renamed from: a */
    public static final boolean m4649a(@NotNull cmo cmoVar, byte b) {
        cji.m5162f(cmoVar, "$this$contains");
        return cmoVar.m4680a(UInt.m8628b(b & 255));
    }

    @bwy(m8750a = "1.3")
    @Unsigned
    /* renamed from: a */
    public static final boolean m4641a(@NotNull cmr cmrVar, byte b) {
        cji.m5162f(cmrVar, "$this$contains");
        return cmrVar.m4669a(ULong.m8548b(b & 255));
    }

    @bwy(m8750a = "1.3")
    @Unsigned
    /* renamed from: a */
    public static final boolean m4640a(@NotNull cmr cmrVar, int i) {
        cji.m5162f(cmrVar, "$this$contains");
        return cmrVar.m4669a(ULong.m8548b(i & 4294967295L));
    }

    @bwy(m8750a = "1.3")
    @Unsigned
    /* renamed from: a */
    public static final boolean m4648a(@NotNull cmo cmoVar, long j) {
        cji.m5162f(cmoVar, "$this$contains");
        return ULong.m8548b(j >>> 32) == 0 && cmoVar.m4680a(UInt.m8628b((int) j));
    }

    @bwy(m8750a = "1.3")
    @Unsigned
    /* renamed from: a */
    public static final boolean m4645a(@NotNull cmo cmoVar, short s) {
        cji.m5162f(cmoVar, "$this$contains");
        return cmoVar.m4680a(UInt.m8628b(s & 65535));
    }

    @bwy(m8750a = "1.3")
    @Unsigned
    /* renamed from: a */
    public static final boolean m4637a(@NotNull cmr cmrVar, short s) {
        cji.m5162f(cmrVar, "$this$contains");
        return cmrVar.m4669a(ULong.m8548b(s & 65535));
    }

    @bwy(m8750a = "1.3")
    @Unsigned
    @NotNull
    /* renamed from: a */
    public static final UIntRange m4660a(byte b, byte b2) {
        return UIntRange.f20856a.m4682a(UInt.m8628b(b & 255), UInt.m8628b(b2 & 255), -1);
    }

    @bwy(m8750a = "1.3")
    @Unsigned
    @NotNull
    /* renamed from: a */
    public static final UIntRange m4658a(int i, int i2) {
        return UIntRange.f20856a.m4682a(i, i2, -1);
    }

    @bwy(m8750a = "1.3")
    @Unsigned
    @NotNull
    /* renamed from: a */
    public static final ULongRange m4655a(long j, long j2) {
        return ULongRange.f20866a.m4671a(j, j2, -1L);
    }

    @bwy(m8750a = "1.3")
    @Unsigned
    @NotNull
    /* renamed from: a */
    public static final UIntRange m4636a(short s, short s2) {
        return UIntRange.f20856a.m4682a(UInt.m8628b(s & 65535), UInt.m8628b(s2 & 65535), -1);
    }

    @bwy(m8750a = "1.3")
    @Unsigned
    @NotNull
    /* renamed from: a */
    public static final UIntRange m4652a(@NotNull UIntRange cmmVar) {
        cji.m5162f(cmmVar, "$this$reversed");
        return UIntRange.f20856a.m4682a(cmmVar.m4685b(), cmmVar.m4686a(), -cmmVar.m4684c());
    }

    @bwy(m8750a = "1.3")
    @Unsigned
    @NotNull
    /* renamed from: a */
    public static final ULongRange m4644a(@NotNull ULongRange cmpVar) {
        cji.m5162f(cmpVar, "$this$reversed");
        return ULongRange.f20866a.m4671a(cmpVar.m4674b(), cmpVar.m4675a(), -cmpVar.m4673c());
    }

    @bwy(m8750a = "1.3")
    @Unsigned
    @NotNull
    /* renamed from: a */
    public static final UIntRange m4651a(@NotNull UIntRange cmmVar, int i) {
        cji.m5162f(cmmVar, "$this$step");
        cmi.m4805a(i > 0, Integer.valueOf(i));
        UIntRange.C4991a aVar = UIntRange.f20856a;
        int a = cmmVar.m4686a();
        int b = cmmVar.m4685b();
        if (cmmVar.m4684c() <= 0) {
            i = -i;
        }
        return aVar.m4682a(a, b, i);
    }

    @bwy(m8750a = "1.3")
    @Unsigned
    @NotNull
    /* renamed from: a */
    public static final ULongRange m4643a(@NotNull ULongRange cmpVar, long j) {
        cji.m5162f(cmpVar, "$this$step");
        cmi.m4805a(j > 0, Long.valueOf(j));
        ULongRange.C4993a aVar = ULongRange.f20866a;
        long a = cmpVar.m4675a();
        long b = cmpVar.m4674b();
        if (cmpVar.m4673c() <= 0) {
            j = -j;
        }
        return aVar.m4671a(a, b, j);
    }

    @bwy(m8750a = "1.3")
    @Unsigned
    @NotNull
    /* renamed from: b */
    public static final cmo m4634b(byte b, byte b2) {
        int i = b2 & 255;
        return cji.m5193a(i, 0) <= 0 ? cmo.f20864b.m4676a() : new cmo(UInt.m8628b(b & 255), UInt.m8628b(UInt.m8628b(i) - 1), null);
    }

    @bwy(m8750a = "1.3")
    @Unsigned
    @NotNull
    /* renamed from: b */
    public static final cmo m4633b(int i, int i2) {
        return UnsignedUtils.m8340a(i2, 0) <= 0 ? cmo.f20864b.m4676a() : new cmo(i, UInt.m8628b(i2 - 1), null);
    }

    @bwy(m8750a = "1.3")
    @Unsigned
    @NotNull
    /* renamed from: b */
    public static final cmr m4632b(long j, long j2) {
        return UnsignedUtils.m8337a(j2, 0L) <= 0 ? cmr.f20874b.m4661a() : new cmr(j, ULong.m8548b(j2 - ULong.m8548b(1 & 4294967295L)), null);
    }

    @bwy(m8750a = "1.3")
    @Unsigned
    @NotNull
    /* renamed from: b */
    public static final cmo m4631b(short s, short s2) {
        int i = s2 & 65535;
        return cji.m5193a(i, 0) <= 0 ? cmo.f20864b.m4676a() : new cmo(UInt.m8628b(s & 65535), UInt.m8628b(UInt.m8628b(i) - 1), null);
    }

    @bwy(m8750a = "1.3")
    @Unsigned
    /* renamed from: c */
    public static final int m4629c(int i, int i2) {
        return UnsignedUtils.m8340a(i, i2) < 0 ? i2 : i;
    }

    @bwy(m8750a = "1.3")
    @Unsigned
    /* renamed from: c */
    public static final long m4628c(long j, long j2) {
        return UnsignedUtils.m8337a(j, j2) < 0 ? j2 : j;
    }

    @bwy(m8750a = "1.3")
    @Unsigned
    /* renamed from: c */
    public static final byte m4630c(byte b, byte b2) {
        return cji.m5193a(b & 255, b2 & 255) < 0 ? b2 : b;
    }

    @bwy(m8750a = "1.3")
    @Unsigned
    /* renamed from: c */
    public static final short m4627c(short s, short s2) {
        return cji.m5193a(s & 65535, 65535 & s2) < 0 ? s2 : s;
    }

    @bwy(m8750a = "1.3")
    @Unsigned
    /* renamed from: d */
    public static final int m4625d(int i, int i2) {
        return UnsignedUtils.m8340a(i, i2) > 0 ? i2 : i;
    }

    @bwy(m8750a = "1.3")
    @Unsigned
    /* renamed from: d */
    public static final long m4624d(long j, long j2) {
        return UnsignedUtils.m8337a(j, j2) > 0 ? j2 : j;
    }

    @bwy(m8750a = "1.3")
    @Unsigned
    /* renamed from: d */
    public static final byte m4626d(byte b, byte b2) {
        return cji.m5193a(b & 255, b2 & 255) > 0 ? b2 : b;
    }

    @bwy(m8750a = "1.3")
    @Unsigned
    /* renamed from: d */
    public static final short m4623d(short s, short s2) {
        return cji.m5193a(s & 65535, 65535 & s2) > 0 ? s2 : s;
    }

    @bwy(m8750a = "1.3")
    @Unsigned
    /* renamed from: a */
    public static final int m4657a(int i, int i2, int i3) {
        if (UnsignedUtils.m8340a(i2, i3) <= 0) {
            return UnsignedUtils.m8340a(i, i2) < 0 ? i2 : UnsignedUtils.m8340a(i, i3) > 0 ? i3 : i;
        }
        throw new IllegalArgumentException("Cannot coerce value to an empty range: maximum " + UInt.m8635a(i3) + " is less than minimum " + UInt.m8635a(i2) + FilenameUtils.EXTENSION_SEPARATOR);
    }

    @bwy(m8750a = "1.3")
    @Unsigned
    /* renamed from: a */
    public static final long m4654a(long j, long j2, long j3) {
        if (UnsignedUtils.m8337a(j2, j3) <= 0) {
            return UnsignedUtils.m8337a(j, j2) < 0 ? j2 : UnsignedUtils.m8337a(j, j3) > 0 ? j3 : j;
        }
        throw new IllegalArgumentException("Cannot coerce value to an empty range: maximum " + ULong.m8555a(j3) + " is less than minimum " + ULong.m8555a(j2) + FilenameUtils.EXTENSION_SEPARATOR);
    }

    @bwy(m8750a = "1.3")
    @Unsigned
    /* renamed from: a */
    public static final byte m4659a(byte b, byte b2, byte b3) {
        int i = b2 & 255;
        int i2 = b3 & 255;
        if (cji.m5193a(i, i2) <= 0) {
            int i3 = b & 255;
            return cji.m5193a(i3, i) < 0 ? b2 : cji.m5193a(i3, i2) > 0 ? b3 : b;
        }
        throw new IllegalArgumentException("Cannot coerce value to an empty range: maximum " + UByte.m8711a(b3) + " is less than minimum " + UByte.m8711a(b2) + FilenameUtils.EXTENSION_SEPARATOR);
    }

    @bwy(m8750a = "1.3")
    @Unsigned
    /* renamed from: a */
    public static final short m4635a(short s, short s2, short s3) {
        int i = s2 & 65535;
        int i2 = s3 & 65535;
        if (cji.m5193a(i, i2) <= 0) {
            int i3 = 65535 & s;
            return cji.m5193a(i3, i) < 0 ? s2 : cji.m5193a(i3, i2) > 0 ? s3 : s;
        }
        throw new IllegalArgumentException("Cannot coerce value to an empty range: maximum " + UShort.m8447a(s3) + " is less than minimum " + UShort.m8447a(s2) + FilenameUtils.EXTENSION_SEPARATOR);
    }

    @bwy(m8750a = "1.3")
    @Unsigned
    /* renamed from: a */
    public static final int m4656a(int i, @NotNull Range<UInt> cmaVar) {
        cji.m5162f(cmaVar, "range");
        if (cmaVar instanceof clz) {
            return ((UInt) cmi.m4779a(UInt.m8623c(i), (clz<UInt>) cmaVar)).m8629b();
        }
        if (!cmaVar.mo4667e()) {
            return UnsignedUtils.m8340a(i, cmaVar.mo4665g().m8629b()) < 0 ? cmaVar.mo4665g().m8629b() : UnsignedUtils.m8340a(i, cmaVar.mo4663i().m8629b()) > 0 ? cmaVar.mo4663i().m8629b() : i;
        }
        throw new IllegalArgumentException("Cannot coerce value to an empty range: " + cmaVar + FilenameUtils.EXTENSION_SEPARATOR);
    }

    @bwy(m8750a = "1.3")
    @Unsigned
    /* renamed from: a */
    public static final long m4653a(long j, @NotNull Range<ULong> cmaVar) {
        cji.m5162f(cmaVar, "range");
        if (cmaVar instanceof clz) {
            return ((ULong) cmi.m4779a(ULong.m8543c(j), (clz<ULong>) cmaVar)).m8549b();
        }
        if (!cmaVar.mo4667e()) {
            return UnsignedUtils.m8337a(j, cmaVar.mo4665g().m8549b()) < 0 ? cmaVar.mo4665g().m8549b() : UnsignedUtils.m8337a(j, cmaVar.mo4663i().m8549b()) > 0 ? cmaVar.mo4663i().m8549b() : j;
        }
        throw new IllegalArgumentException("Cannot coerce value to an empty range: " + cmaVar + FilenameUtils.EXTENSION_SEPARATOR);
    }
}
