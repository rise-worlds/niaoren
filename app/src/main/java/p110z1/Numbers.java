package p110z1;

@Metadata(m8864a = 5, m8863b = {1, 1, 15}, m8862c = {1, 0, 3}, m8861d = {"\u0000\u0012\n\u0000\n\u0002\u0010\b\n\u0002\u0010\u0005\n\u0002\u0010\n\n\u0002\b\b\u001a\r\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u0087\b\u001a\r\u0010\u0000\u001a\u00020\u0001*\u00020\u0003H\u0087\b\u001a\r\u0010\u0004\u001a\u00020\u0001*\u00020\u0002H\u0087\b\u001a\r\u0010\u0004\u001a\u00020\u0001*\u00020\u0003H\u0087\b\u001a\r\u0010\u0005\u001a\u00020\u0001*\u00020\u0002H\u0087\b\u001a\r\u0010\u0005\u001a\u00020\u0001*\u00020\u0003H\u0087\b\u001a\u0014\u0010\u0006\u001a\u00020\u0002*\u00020\u00022\u0006\u0010\u0007\u001a\u00020\u0001H\u0007\u001a\u0014\u0010\u0006\u001a\u00020\u0003*\u00020\u00032\u0006\u0010\u0007\u001a\u00020\u0001H\u0007\u001a\u0014\u0010\b\u001a\u00020\u0002*\u00020\u00022\u0006\u0010\u0007\u001a\u00020\u0001H\u0007\u001a\u0014\u0010\b\u001a\u00020\u0003*\u00020\u00032\u0006\u0010\u0007\u001a\u00020\u0001H\u0007\u001a\r\u0010\t\u001a\u00020\u0002*\u00020\u0002H\u0087\b\u001a\r\u0010\t\u001a\u00020\u0003*\u00020\u0003H\u0087\b\u001a\r\u0010\n\u001a\u00020\u0002*\u00020\u0002H\u0087\b\u001a\r\u0010\n\u001a\u00020\u0003*\u00020\u0003H\u0087\b¨\u0006\u000b"}, m8860e = {"countLeadingZeroBits", "", "", "", "countOneBits", "countTrailingZeroBits", "rotateLeft", "bitCount", "rotateRight", "takeHighestOneBit", "takeLowestOneBit", "kotlin-stdlib"}, m8859f = "kotlin/NumbersKt", m8857h = 1)
/* renamed from: z1.bwm */
/* loaded from: classes3.dex */
class Numbers extends NumbersJVM {
    @bwy(m8750a = "1.3")
    @ExperimentalStdlibApi
    /* renamed from: a */
    public static final byte m8793a(byte b, int i) {
        int i2 = i & 7;
        return (byte) (((b & 255) >>> (8 - i2)) | (b << i2));
    }

    @bwy(m8750a = "1.3")
    @ExperimentalStdlibApi
    /* renamed from: a */
    public static final short m8791a(short s, int i) {
        int i2 = i & 15;
        return (short) (((s & 65535) >>> (16 - i2)) | (s << i2));
    }

    @bwy(m8750a = "1.3")
    @ExperimentalStdlibApi
    /* renamed from: b */
    public static final byte m8789b(byte b, int i) {
        int i2 = i & 7;
        return (byte) (((b & 255) >>> i2) | (b << (8 - i2)));
    }

    @bwy(m8750a = "1.3")
    @ExperimentalStdlibApi
    /* renamed from: b */
    public static final short m8787b(short s, int i) {
        int i2 = i & 15;
        return (short) (((s & 65535) >>> i2) | (s << (16 - i2)));
    }

    @bwy(m8750a = "1.3")
    @ExperimentalStdlibApi
    @cey
    /* renamed from: a */
    private static final int m8794a(byte b) {
        return Integer.bitCount(b & 255);
    }

    @bwy(m8750a = "1.3")
    @ExperimentalStdlibApi
    @cey
    /* renamed from: b */
    private static final int m8790b(byte b) {
        return Integer.numberOfLeadingZeros(b & 255) - 24;
    }

    @bwy(m8750a = "1.3")
    @ExperimentalStdlibApi
    @cey
    /* renamed from: c */
    private static final int m8786c(byte b) {
        return Integer.numberOfTrailingZeros(b | 256);
    }

    @bwy(m8750a = "1.3")
    @ExperimentalStdlibApi
    @cey
    /* renamed from: d */
    private static final byte m8784d(byte b) {
        return (byte) Integer.highestOneBit(b & 255);
    }

    @bwy(m8750a = "1.3")
    @ExperimentalStdlibApi
    @cey
    /* renamed from: e */
    private static final byte m8782e(byte b) {
        return (byte) Integer.lowestOneBit(b);
    }

    @bwy(m8750a = "1.3")
    @ExperimentalStdlibApi
    @cey
    /* renamed from: a */
    private static final int m8792a(short s) {
        return Integer.bitCount(s & 65535);
    }

    @bwy(m8750a = "1.3")
    @ExperimentalStdlibApi
    @cey
    /* renamed from: b */
    private static final int m8788b(short s) {
        return Integer.numberOfLeadingZeros(s & 65535) - 16;
    }

    @bwy(m8750a = "1.3")
    @ExperimentalStdlibApi
    @cey
    /* renamed from: c */
    private static final int m8785c(short s) {
        return Integer.numberOfTrailingZeros(s | 65536);
    }

    @bwy(m8750a = "1.3")
    @ExperimentalStdlibApi
    @cey
    /* renamed from: d */
    private static final short m8783d(short s) {
        return (short) Integer.highestOneBit(s & 65535);
    }

    @bwy(m8750a = "1.3")
    @ExperimentalStdlibApi
    @cey
    /* renamed from: e */
    private static final short m8781e(short s) {
        return (short) Integer.lowestOneBit(s);
    }
}
