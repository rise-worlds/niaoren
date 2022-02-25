package p110z1;

@Metadata(m8864a = 2, m8863b = {1, 1, 15}, m8862c = {1, 0, 3}, m8861d = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0005\n\u0000\n\u0002\u0010\n\n\u0002\b\u0004\u001a\u0015\u0010\u0000\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0001H\u0087\f\u001a\u0015\u0010\u0000\u001a\u00020\u0003*\u00020\u00032\u0006\u0010\u0002\u001a\u00020\u0003H\u0087\f\u001a\r\u0010\u0004\u001a\u00020\u0001*\u00020\u0001H\u0087\b\u001a\r\u0010\u0004\u001a\u00020\u0003*\u00020\u0003H\u0087\b\u001a\u0015\u0010\u0005\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0001H\u0087\f\u001a\u0015\u0010\u0005\u001a\u00020\u0003*\u00020\u00032\u0006\u0010\u0002\u001a\u00020\u0003H\u0087\f\u001a\u0015\u0010\u0006\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0001H\u0087\f\u001a\u0015\u0010\u0006\u001a\u00020\u0003*\u00020\u00032\u0006\u0010\u0002\u001a\u00020\u0003H\u0087\f¨\u0006\u0007"}, m8860e = {"and", "", "other", "", "inv", "or", "xor", "kotlin-stdlib"})
/* renamed from: z1.cer */
/* loaded from: classes3.dex */
public final class bitwiseOperations {
    @bwy(m8750a = "1.1")
    @cey
    /* renamed from: a */
    private static final byte m5496a(byte b) {
        return (byte) (~b);
    }

    @bwy(m8750a = "1.1")
    @cey
    /* renamed from: a */
    private static final byte m5495a(byte b, byte b2) {
        return (byte) (b & b2);
    }

    @bwy(m8750a = "1.1")
    @cey
    /* renamed from: a */
    private static final short m5494a(short s) {
        return (short) (~s);
    }

    @bwy(m8750a = "1.1")
    @cey
    /* renamed from: a */
    private static final short m5493a(short s, short s2) {
        return (short) (s & s2);
    }

    @bwy(m8750a = "1.1")
    @cey
    /* renamed from: b */
    private static final byte m5492b(byte b, byte b2) {
        return (byte) (b | b2);
    }

    @bwy(m8750a = "1.1")
    @cey
    /* renamed from: b */
    private static final short m5491b(short s, short s2) {
        return (short) (s | s2);
    }

    @bwy(m8750a = "1.1")
    @cey
    /* renamed from: c */
    private static final byte m5490c(byte b, byte b2) {
        return (byte) (b ^ b2);
    }

    @bwy(m8750a = "1.1")
    @cey
    /* renamed from: c */
    private static final short m5489c(short s, short s2) {
        return (short) (s ^ s2);
    }
}
