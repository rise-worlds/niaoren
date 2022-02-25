package p110z1;

@Metadata(m8864a = 5, m8863b = {1, 1, 15}, m8862c = {1, 0, 3}, m8861d = {"\u0000*\n\u0000\n\u0002\u0010\b\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\n\u001a\r\u0010\u0000\u001a\u00020\u0001*\u00020\u0001H\u0087\b\u001a\r\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u0087\b\u001a\r\u0010\u0003\u001a\u00020\u0001*\u00020\u0001H\u0087\b\u001a\r\u0010\u0003\u001a\u00020\u0001*\u00020\u0002H\u0087\b\u001a\r\u0010\u0004\u001a\u00020\u0001*\u00020\u0001H\u0087\b\u001a\r\u0010\u0004\u001a\u00020\u0001*\u00020\u0002H\u0087\b\u001a\u0015\u0010\u0005\u001a\u00020\u0006*\u00020\u00072\u0006\u0010\b\u001a\u00020\u0002H\u0087\b\u001a\u0015\u0010\u0005\u001a\u00020\t*\u00020\n2\u0006\u0010\b\u001a\u00020\u0001H\u0087\b\u001a\r\u0010\u000b\u001a\u00020\f*\u00020\u0006H\u0087\b\u001a\r\u0010\u000b\u001a\u00020\f*\u00020\tH\u0087\b\u001a\r\u0010\r\u001a\u00020\f*\u00020\u0006H\u0087\b\u001a\r\u0010\r\u001a\u00020\f*\u00020\tH\u0087\b\u001a\r\u0010\u000e\u001a\u00020\f*\u00020\u0006H\u0087\b\u001a\r\u0010\u000e\u001a\u00020\f*\u00020\tH\u0087\b\u001a\u0015\u0010\u000f\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0010\u001a\u00020\u0001H\u0087\b\u001a\u0015\u0010\u000f\u001a\u00020\u0002*\u00020\u00022\u0006\u0010\u0010\u001a\u00020\u0001H\u0087\b\u001a\u0015\u0010\u0011\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0010\u001a\u00020\u0001H\u0087\b\u001a\u0015\u0010\u0011\u001a\u00020\u0002*\u00020\u00022\u0006\u0010\u0010\u001a\u00020\u0001H\u0087\b\u001a\r\u0010\u0012\u001a\u00020\u0001*\u00020\u0001H\u0087\b\u001a\r\u0010\u0012\u001a\u00020\u0002*\u00020\u0002H\u0087\b\u001a\r\u0010\u0013\u001a\u00020\u0001*\u00020\u0001H\u0087\b\u001a\r\u0010\u0013\u001a\u00020\u0002*\u00020\u0002H\u0087\b\u001a\r\u0010\u0014\u001a\u00020\u0002*\u00020\u0006H\u0087\b\u001a\r\u0010\u0014\u001a\u00020\u0001*\u00020\tH\u0087\b\u001a\r\u0010\u0015\u001a\u00020\u0002*\u00020\u0006H\u0087\b\u001a\r\u0010\u0015\u001a\u00020\u0001*\u00020\tH\u0087\b¨\u0006\u0016"}, m8860e = {"countLeadingZeroBits", "", "", "countOneBits", "countTrailingZeroBits", "fromBits", "", "Lkotlin/Double$Companion;", "bits", "", "Lkotlin/Float$Companion;", "isFinite", "", "isInfinite", "isNaN", "rotateLeft", "bitCount", "rotateRight", "takeHighestOneBit", "takeLowestOneBit", "toBits", "toRawBits", "kotlin-stdlib"}, m8859f = "kotlin/NumbersKt", m8857h = 1)
/* renamed from: z1.bwl */
/* loaded from: classes3.dex */
class NumbersJVM extends BigIntegers {
    @cey
    /* renamed from: a */
    private static final boolean m8820a(double d) {
        return Double.isNaN(d);
    }

    @cey
    /* renamed from: a */
    private static final boolean m8819a(float f) {
        return Float.isNaN(f);
    }

    @cey
    /* renamed from: b */
    private static final boolean m8812b(double d) {
        return Double.isInfinite(d);
    }

    @cey
    /* renamed from: b */
    private static final boolean m8811b(float f) {
        return Float.isInfinite(f);
    }

    @cey
    /* renamed from: c */
    private static final boolean m8806c(double d) {
        return !Double.isInfinite(d) && !Double.isNaN(d);
    }

    @cey
    /* renamed from: c */
    private static final boolean m8805c(float f) {
        return !Float.isInfinite(f) && !Float.isNaN(f);
    }

    @bwy(m8750a = "1.2")
    @cey
    /* renamed from: d */
    private static final long m8802d(double d) {
        return Double.doubleToLongBits(d);
    }

    @bwy(m8750a = "1.2")
    @cey
    /* renamed from: e */
    private static final long m8798e(double d) {
        return Double.doubleToRawLongBits(d);
    }

    @bwy(m8750a = "1.2")
    @cey
    /* renamed from: a */
    private static final double m8814a(@NotNull ciw ciwVar, long j) {
        return Double.longBitsToDouble(j);
    }

    @bwy(m8750a = "1.2")
    @cey
    /* renamed from: d */
    private static final int m8801d(float f) {
        return Float.floatToIntBits(f);
    }

    @bwy(m8750a = "1.2")
    @cey
    /* renamed from: e */
    private static final int m8797e(float f) {
        return Float.floatToRawIntBits(f);
    }

    @bwy(m8750a = "1.2")
    @cey
    /* renamed from: a */
    private static final float m8813a(@NotNull ciz cizVar, int i) {
        return Float.intBitsToFloat(i);
    }

    @bwy(m8750a = "1.3")
    @ExperimentalStdlibApi
    @cey
    /* renamed from: a */
    private static final int m8818a(int i) {
        return Integer.bitCount(i);
    }

    @bwy(m8750a = "1.3")
    @ExperimentalStdlibApi
    @cey
    /* renamed from: b */
    private static final int m8810b(int i) {
        return Integer.numberOfLeadingZeros(i);
    }

    @bwy(m8750a = "1.3")
    @ExperimentalStdlibApi
    @cey
    /* renamed from: c */
    private static final int m8804c(int i) {
        return Integer.numberOfTrailingZeros(i);
    }

    @bwy(m8750a = "1.3")
    @ExperimentalStdlibApi
    @cey
    /* renamed from: d */
    private static final int m8800d(int i) {
        return Integer.highestOneBit(i);
    }

    @bwy(m8750a = "1.3")
    @ExperimentalStdlibApi
    @cey
    /* renamed from: e */
    private static final int m8796e(int i) {
        return Integer.lowestOneBit(i);
    }

    @bwy(m8750a = "1.3")
    @ExperimentalStdlibApi
    @cey
    /* renamed from: a */
    private static final int m8817a(int i, int i2) {
        return Integer.rotateLeft(i, i2);
    }

    @bwy(m8750a = "1.3")
    @ExperimentalStdlibApi
    @cey
    /* renamed from: b */
    private static final int m8809b(int i, int i2) {
        return Integer.rotateRight(i, i2);
    }

    @bwy(m8750a = "1.3")
    @ExperimentalStdlibApi
    @cey
    /* renamed from: a */
    private static final int m8816a(long j) {
        return Long.bitCount(j);
    }

    @bwy(m8750a = "1.3")
    @ExperimentalStdlibApi
    @cey
    /* renamed from: b */
    private static final int m8808b(long j) {
        return Long.numberOfLeadingZeros(j);
    }

    @bwy(m8750a = "1.3")
    @ExperimentalStdlibApi
    @cey
    /* renamed from: c */
    private static final int m8803c(long j) {
        return Long.numberOfTrailingZeros(j);
    }

    @bwy(m8750a = "1.3")
    @ExperimentalStdlibApi
    @cey
    /* renamed from: d */
    private static final long m8799d(long j) {
        return Long.highestOneBit(j);
    }

    @bwy(m8750a = "1.3")
    @ExperimentalStdlibApi
    @cey
    /* renamed from: e */
    private static final long m8795e(long j) {
        return Long.lowestOneBit(j);
    }

    @bwy(m8750a = "1.3")
    @ExperimentalStdlibApi
    @cey
    /* renamed from: a */
    private static final long m8815a(long j, int i) {
        return Long.rotateLeft(j, i);
    }

    @bwy(m8750a = "1.3")
    @ExperimentalStdlibApi
    @cey
    /* renamed from: b */
    private static final long m8807b(long j, int i) {
        return Long.rotateRight(j, i);
    }
}
