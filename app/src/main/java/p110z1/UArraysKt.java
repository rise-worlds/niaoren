package p110z1;

import java.util.Arrays;
import java.util.NoSuchElementException;

@Metadata(m8864a = 1, m8863b = {1, 1, 15}, m8862c = {1, 0, 3}, m8861d = {"\u0000l\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\t\n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\b\t\bÇ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u001f\u0010\u0003\u001a\u00020\u0004*\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0005H\u0087\u0004ø\u0001\u0000¢\u0006\u0004\b\u0007\u0010\bJ\u001f\u0010\u0003\u001a\u00020\u0004*\u00020\t2\u0006\u0010\u0006\u001a\u00020\tH\u0087\u0004ø\u0001\u0000¢\u0006\u0004\b\n\u0010\u000bJ\u001f\u0010\u0003\u001a\u00020\u0004*\u00020\f2\u0006\u0010\u0006\u001a\u00020\fH\u0087\u0004ø\u0001\u0000¢\u0006\u0004\b\r\u0010\u000eJ\u001f\u0010\u0003\u001a\u00020\u0004*\u00020\u000f2\u0006\u0010\u0006\u001a\u00020\u000fH\u0087\u0004ø\u0001\u0000¢\u0006\u0004\b\u0010\u0010\u0011J\u0016\u0010\u0012\u001a\u00020\u0013*\u00020\u0005H\u0007ø\u0001\u0000¢\u0006\u0004\b\u0014\u0010\u0015J\u0016\u0010\u0012\u001a\u00020\u0013*\u00020\tH\u0007ø\u0001\u0000¢\u0006\u0004\b\u0016\u0010\u0017J\u0016\u0010\u0012\u001a\u00020\u0013*\u00020\fH\u0007ø\u0001\u0000¢\u0006\u0004\b\u0018\u0010\u0019J\u0016\u0010\u0012\u001a\u00020\u0013*\u00020\u000fH\u0007ø\u0001\u0000¢\u0006\u0004\b\u001a\u0010\u001bJ\u0016\u0010\u001c\u001a\u00020\u001d*\u00020\u0005H\u0007ø\u0001\u0000¢\u0006\u0004\b\u001e\u0010\u001fJ\u0016\u0010\u001c\u001a\u00020\u001d*\u00020\tH\u0007ø\u0001\u0000¢\u0006\u0004\b \u0010!J\u0016\u0010\u001c\u001a\u00020\u001d*\u00020\fH\u0007ø\u0001\u0000¢\u0006\u0004\b\"\u0010#J\u0016\u0010\u001c\u001a\u00020\u001d*\u00020\u000fH\u0007ø\u0001\u0000¢\u0006\u0004\b$\u0010%J\u001e\u0010&\u001a\u00020'*\u00020\u00052\u0006\u0010&\u001a\u00020(H\u0007ø\u0001\u0000¢\u0006\u0004\b)\u0010*J\u001e\u0010&\u001a\u00020+*\u00020\t2\u0006\u0010&\u001a\u00020(H\u0007ø\u0001\u0000¢\u0006\u0004\b,\u0010-J\u001e\u0010&\u001a\u00020.*\u00020\f2\u0006\u0010&\u001a\u00020(H\u0007ø\u0001\u0000¢\u0006\u0004\b/\u00100J\u001e\u0010&\u001a\u000201*\u00020\u000f2\u0006\u0010&\u001a\u00020(H\u0007ø\u0001\u0000¢\u0006\u0004\b2\u00103J\u001c\u00104\u001a\b\u0012\u0004\u0012\u00020'05*\u00020\u0005H\u0007ø\u0001\u0000¢\u0006\u0004\b6\u00107J\u001c\u00104\u001a\b\u0012\u0004\u0012\u00020+05*\u00020\tH\u0007ø\u0001\u0000¢\u0006\u0004\b8\u00109J\u001c\u00104\u001a\b\u0012\u0004\u0012\u00020.05*\u00020\fH\u0007ø\u0001\u0000¢\u0006\u0004\b:\u0010;J\u001c\u00104\u001a\b\u0012\u0004\u0012\u00020105*\u00020\u000fH\u0007ø\u0001\u0000¢\u0006\u0004\b<\u0010=\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006>"}, m8860e = {"Lkotlin/collections/UArraysKt;", "", "()V", "contentEquals", "", "Lkotlin/UByteArray;", "other", "contentEquals-kdPth3s", "([B[B)Z", "Lkotlin/UIntArray;", "contentEquals-ctEhBpI", "([I[I)Z", "Lkotlin/ULongArray;", "contentEquals-us8wMrg", "([J[J)Z", "Lkotlin/UShortArray;", "contentEquals-mazbYpA", "([S[S)Z", "contentHashCode", "", "contentHashCode-GBYM_sE", "([B)I", "contentHashCode--ajY-9A", "([I)I", "contentHashCode-QwZRm1k", "([J)I", "contentHashCode-rL5Bavg", "([S)I", "contentToString", "", "contentToString-GBYM_sE", "([B)Ljava/lang/String;", "contentToString--ajY-9A", "([I)Ljava/lang/String;", "contentToString-QwZRm1k", "([J)Ljava/lang/String;", "contentToString-rL5Bavg", "([S)Ljava/lang/String;", "random", "Lkotlin/UByte;", "Lkotlin/random/Random;", "random-oSF2wD8", "([BLkotlin/random/Random;)B", "Lkotlin/UInt;", "random-2D5oskM", "([ILkotlin/random/Random;)I", "Lkotlin/ULong;", "random-JzugnMA", "([JLkotlin/random/Random;)J", "Lkotlin/UShort;", "random-s5X_as8", "([SLkotlin/random/Random;)S", "toTypedArray", "", "toTypedArray-GBYM_sE", "([B)[Lkotlin/UByte;", "toTypedArray--ajY-9A", "([I)[Lkotlin/UInt;", "toTypedArray-QwZRm1k", "([J)[Lkotlin/ULong;", "toTypedArray-rL5Bavg", "([S)[Lkotlin/UShort;", "kotlin-stdlib"})
@Annotations(m8902a = "Provided for binary compatibility", m8900c = bvk.HIDDEN)
/* renamed from: z1.cbh */
/* loaded from: classes3.dex */
public final class UArraysKt {

    /* renamed from: a */
    public static final UArraysKt f20514a = new UArraysKt();

    private UArraysKt() {
    }

    @Unsigned
    @cgr
    /* renamed from: a */
    public static final int m6308a(@NotNull int[] iArr, @NotNull Random clqVar) {
        cji.m5162f(iArr, "$this$random");
        cji.m5162f(clqVar, "random");
        if (!UIntArray.m8570c(iArr)) {
            return UIntArray.m8581a(iArr, clqVar.mo4893b(UIntArray.m8582a(iArr)));
        }
        throw new NoSuchElementException("Array is empty.");
    }

    @Unsigned
    @cgr
    /* renamed from: a */
    public static final long m6305a(@NotNull long[] jArr, @NotNull Random clqVar) {
        cji.m5162f(jArr, "$this$random");
        cji.m5162f(clqVar, "random");
        if (!ULongArray.m8490c(jArr)) {
            return ULongArray.m8500a(jArr, clqVar.mo4893b(ULongArray.m8501a(jArr)));
        }
        throw new NoSuchElementException("Array is empty.");
    }

    @Unsigned
    @cgr
    /* renamed from: a */
    public static final byte m6311a(@NotNull byte[] bArr, @NotNull Random clqVar) {
        cji.m5162f(bArr, "$this$random");
        cji.m5162f(clqVar, "random");
        if (!UByteArray.m8648c(bArr)) {
            return UByteArray.m8657a(bArr, clqVar.mo4893b(UByteArray.m8659a(bArr)));
        }
        throw new NoSuchElementException("Array is empty.");
    }

    @Unsigned
    @cgr
    /* renamed from: a */
    public static final short m6302a(@NotNull short[] sArr, @NotNull Random clqVar) {
        cji.m5162f(sArr, "$this$random");
        cji.m5162f(clqVar, "random");
        if (!UShortArray.m8384c(sArr)) {
            return UShortArray.m8394a(sArr, clqVar.mo4893b(UShortArray.m8395a(sArr)));
        }
        throw new NoSuchElementException("Array is empty.");
    }

    @Unsigned
    @cgr
    /* renamed from: a */
    public static final boolean m6307a(@NotNull int[] iArr, @NotNull int[] iArr2) {
        cji.m5162f(iArr, "$this$contentEquals");
        cji.m5162f(iArr2, "other");
        return Arrays.equals(iArr, iArr2);
    }

    @Unsigned
    @cgr
    /* renamed from: a */
    public static final boolean m6304a(@NotNull long[] jArr, @NotNull long[] jArr2) {
        cji.m5162f(jArr, "$this$contentEquals");
        cji.m5162f(jArr2, "other");
        return Arrays.equals(jArr, jArr2);
    }

    @Unsigned
    @cgr
    /* renamed from: a */
    public static final boolean m6310a(@NotNull byte[] bArr, @NotNull byte[] bArr2) {
        cji.m5162f(bArr, "$this$contentEquals");
        cji.m5162f(bArr2, "other");
        return Arrays.equals(bArr, bArr2);
    }

    @Unsigned
    @cgr
    /* renamed from: a */
    public static final boolean m6301a(@NotNull short[] sArr, @NotNull short[] sArr2) {
        cji.m5162f(sArr, "$this$contentEquals");
        cji.m5162f(sArr2, "other");
        return Arrays.equals(sArr, sArr2);
    }

    @Unsigned
    @cgr
    /* renamed from: a */
    public static final int m6309a(@NotNull int[] iArr) {
        cji.m5162f(iArr, "$this$contentHashCode");
        return Arrays.hashCode(iArr);
    }

    @Unsigned
    @cgr
    /* renamed from: a */
    public static final int m6306a(@NotNull long[] jArr) {
        cji.m5162f(jArr, "$this$contentHashCode");
        return Arrays.hashCode(jArr);
    }

    @Unsigned
    @cgr
    /* renamed from: a */
    public static final int m6312a(@NotNull byte[] bArr) {
        cji.m5162f(bArr, "$this$contentHashCode");
        return Arrays.hashCode(bArr);
    }

    @Unsigned
    @cgr
    /* renamed from: a */
    public static final int m6303a(@NotNull short[] sArr) {
        cji.m5162f(sArr, "$this$contentHashCode");
        return Arrays.hashCode(sArr);
    }

    @Unsigned
    @cgr
    @NotNull
    /* renamed from: b */
    public static final String m6299b(@NotNull int[] iArr) {
        cji.m5162f(iArr, "$this$contentToString");
        return bzk.m6679a(cji.m5162f(iArr, "v"), ", ", "[", "]", 0, null, null, 56, null);
    }

    @Unsigned
    @cgr
    @NotNull
    /* renamed from: b */
    public static final String m6298b(@NotNull long[] jArr) {
        cji.m5162f(jArr, "$this$contentToString");
        return bzk.m6679a(cji.m5162f(jArr, "v"), ", ", "[", "]", 0, null, null, 56, null);
    }

    @Unsigned
    @cgr
    @NotNull
    /* renamed from: b */
    public static final String m6300b(@NotNull byte[] bArr) {
        cji.m5162f(bArr, "$this$contentToString");
        return bzk.m6679a(cji.m5162f(bArr, "v"), ", ", "[", "]", 0, null, null, 56, null);
    }

    @Unsigned
    @cgr
    @NotNull
    /* renamed from: b */
    public static final String m6297b(@NotNull short[] sArr) {
        cji.m5162f(sArr, "$this$contentToString");
        return bzk.m6679a(cji.m5162f(sArr, "v"), ", ", "[", "]", 0, null, null, 56, null);
    }

    @Unsigned
    @cgr
    @NotNull
    /* renamed from: c */
    public static final UInt[] m6295c(@NotNull int[] iArr) {
        cji.m5162f(iArr, "$this$toTypedArray");
        int a = UIntArray.m8582a(iArr);
        UInt[] bxoVarArr = new UInt[a];
        for (int i = 0; i < a; i++) {
            bxoVarArr[i] = UInt.m8623c(UIntArray.m8581a(iArr, i));
        }
        return bxoVarArr;
    }

    @Unsigned
    @cgr
    @NotNull
    /* renamed from: c */
    public static final ULong[] m6294c(@NotNull long[] jArr) {
        cji.m5162f(jArr, "$this$toTypedArray");
        int a = ULongArray.m8501a(jArr);
        ULong[] bxsVarArr = new ULong[a];
        for (int i = 0; i < a; i++) {
            bxsVarArr[i] = ULong.m8543c(ULongArray.m8500a(jArr, i));
        }
        return bxsVarArr;
    }

    @Unsigned
    @cgr
    @NotNull
    /* renamed from: c */
    public static final UByte[] m6296c(@NotNull byte[] bArr) {
        cji.m5162f(bArr, "$this$toTypedArray");
        int a = UByteArray.m8659a(bArr);
        UByte[] bxkVarArr = new UByte[a];
        for (int i = 0; i < a; i++) {
            bxkVarArr[i] = UByte.m8699c(UByteArray.m8657a(bArr, i));
        }
        return bxkVarArr;
    }

    @Unsigned
    @cgr
    @NotNull
    /* renamed from: c */
    public static final UShort[] m6293c(@NotNull short[] sArr) {
        cji.m5162f(sArr, "$this$toTypedArray");
        int a = UShortArray.m8395a(sArr);
        UShort[] bxyVarArr = new UShort[a];
        for (int i = 0; i < a; i++) {
            bxyVarArr[i] = UShort.m8435c(UShortArray.m8394a(sArr, i));
        }
        return bxyVarArr;
    }
}
