package p110z1;

import com.googlecode.tesseract.android.TessBaseAPI;

@Metadata(m8864a = 5, m8863b = {1, 1, 15}, m8862c = {1, 0, 3}, m8861d = {"\u0000(\n\u0002\b\u0002\n\u0002\u0010\u000f\n\u0002\b\u0005\n\u0002\u0010\u0005\n\u0002\u0010\u0006\n\u0002\u0010\u0007\n\u0002\u0010\b\n\u0002\u0010\t\n\u0002\u0010\n\n\u0002\b\u0002\u001a-\u0010\u0000\u001a\u0002H\u0001\"\u000e\b\u0000\u0010\u0001*\b\u0012\u0004\u0012\u0002H\u00010\u00022\u0006\u0010\u0003\u001a\u0002H\u00012\u0006\u0010\u0004\u001a\u0002H\u0001H\u0007¢\u0006\u0002\u0010\u0005\u001a5\u0010\u0000\u001a\u0002H\u0001\"\u000e\b\u0000\u0010\u0001*\b\u0012\u0004\u0012\u0002H\u00010\u00022\u0006\u0010\u0003\u001a\u0002H\u00012\u0006\u0010\u0004\u001a\u0002H\u00012\u0006\u0010\u0006\u001a\u0002H\u0001H\u0007¢\u0006\u0002\u0010\u0007\u001a\u0019\u0010\u0000\u001a\u00020\b2\u0006\u0010\u0003\u001a\u00020\b2\u0006\u0010\u0004\u001a\u00020\bH\u0087\b\u001a!\u0010\u0000\u001a\u00020\b2\u0006\u0010\u0003\u001a\u00020\b2\u0006\u0010\u0004\u001a\u00020\b2\u0006\u0010\u0006\u001a\u00020\bH\u0087\b\u001a\u0019\u0010\u0000\u001a\u00020\t2\u0006\u0010\u0003\u001a\u00020\t2\u0006\u0010\u0004\u001a\u00020\tH\u0087\b\u001a!\u0010\u0000\u001a\u00020\t2\u0006\u0010\u0003\u001a\u00020\t2\u0006\u0010\u0004\u001a\u00020\t2\u0006\u0010\u0006\u001a\u00020\tH\u0087\b\u001a\u0019\u0010\u0000\u001a\u00020\n2\u0006\u0010\u0003\u001a\u00020\n2\u0006\u0010\u0004\u001a\u00020\nH\u0087\b\u001a!\u0010\u0000\u001a\u00020\n2\u0006\u0010\u0003\u001a\u00020\n2\u0006\u0010\u0004\u001a\u00020\n2\u0006\u0010\u0006\u001a\u00020\nH\u0087\b\u001a\u0019\u0010\u0000\u001a\u00020\u000b2\u0006\u0010\u0003\u001a\u00020\u000b2\u0006\u0010\u0004\u001a\u00020\u000bH\u0087\b\u001a!\u0010\u0000\u001a\u00020\u000b2\u0006\u0010\u0003\u001a\u00020\u000b2\u0006\u0010\u0004\u001a\u00020\u000b2\u0006\u0010\u0006\u001a\u00020\u000bH\u0087\b\u001a\u0019\u0010\u0000\u001a\u00020\f2\u0006\u0010\u0003\u001a\u00020\f2\u0006\u0010\u0004\u001a\u00020\fH\u0087\b\u001a!\u0010\u0000\u001a\u00020\f2\u0006\u0010\u0003\u001a\u00020\f2\u0006\u0010\u0004\u001a\u00020\f2\u0006\u0010\u0006\u001a\u00020\fH\u0087\b\u001a\u0019\u0010\u0000\u001a\u00020\r2\u0006\u0010\u0003\u001a\u00020\r2\u0006\u0010\u0004\u001a\u00020\rH\u0087\b\u001a!\u0010\u0000\u001a\u00020\r2\u0006\u0010\u0003\u001a\u00020\r2\u0006\u0010\u0004\u001a\u00020\r2\u0006\u0010\u0006\u001a\u00020\rH\u0087\b\u001a-\u0010\u000e\u001a\u0002H\u0001\"\u000e\b\u0000\u0010\u0001*\b\u0012\u0004\u0012\u0002H\u00010\u00022\u0006\u0010\u0003\u001a\u0002H\u00012\u0006\u0010\u0004\u001a\u0002H\u0001H\u0007¢\u0006\u0002\u0010\u0005\u001a5\u0010\u000e\u001a\u0002H\u0001\"\u000e\b\u0000\u0010\u0001*\b\u0012\u0004\u0012\u0002H\u00010\u00022\u0006\u0010\u0003\u001a\u0002H\u00012\u0006\u0010\u0004\u001a\u0002H\u00012\u0006\u0010\u0006\u001a\u0002H\u0001H\u0007¢\u0006\u0002\u0010\u0007\u001a\u0019\u0010\u000e\u001a\u00020\b2\u0006\u0010\u0003\u001a\u00020\b2\u0006\u0010\u0004\u001a\u00020\bH\u0087\b\u001a!\u0010\u000e\u001a\u00020\b2\u0006\u0010\u0003\u001a\u00020\b2\u0006\u0010\u0004\u001a\u00020\b2\u0006\u0010\u0006\u001a\u00020\bH\u0087\b\u001a\u0019\u0010\u000e\u001a\u00020\t2\u0006\u0010\u0003\u001a\u00020\t2\u0006\u0010\u0004\u001a\u00020\tH\u0087\b\u001a!\u0010\u000e\u001a\u00020\t2\u0006\u0010\u0003\u001a\u00020\t2\u0006\u0010\u0004\u001a\u00020\t2\u0006\u0010\u0006\u001a\u00020\tH\u0087\b\u001a\u0019\u0010\u000e\u001a\u00020\n2\u0006\u0010\u0003\u001a\u00020\n2\u0006\u0010\u0004\u001a\u00020\nH\u0087\b\u001a!\u0010\u000e\u001a\u00020\n2\u0006\u0010\u0003\u001a\u00020\n2\u0006\u0010\u0004\u001a\u00020\n2\u0006\u0010\u0006\u001a\u00020\nH\u0087\b\u001a\u0019\u0010\u000e\u001a\u00020\u000b2\u0006\u0010\u0003\u001a\u00020\u000b2\u0006\u0010\u0004\u001a\u00020\u000bH\u0087\b\u001a!\u0010\u000e\u001a\u00020\u000b2\u0006\u0010\u0003\u001a\u00020\u000b2\u0006\u0010\u0004\u001a\u00020\u000b2\u0006\u0010\u0006\u001a\u00020\u000bH\u0087\b\u001a\u0019\u0010\u000e\u001a\u00020\f2\u0006\u0010\u0003\u001a\u00020\f2\u0006\u0010\u0004\u001a\u00020\fH\u0087\b\u001a!\u0010\u000e\u001a\u00020\f2\u0006\u0010\u0003\u001a\u00020\f2\u0006\u0010\u0004\u001a\u00020\f2\u0006\u0010\u0006\u001a\u00020\fH\u0087\b\u001a\u0019\u0010\u000e\u001a\u00020\r2\u0006\u0010\u0003\u001a\u00020\r2\u0006\u0010\u0004\u001a\u00020\rH\u0087\b\u001a!\u0010\u000e\u001a\u00020\r2\u0006\u0010\u0003\u001a\u00020\r2\u0006\u0010\u0004\u001a\u00020\r2\u0006\u0010\u0006\u001a\u00020\rH\u0087\b¨\u0006\u000f"}, m8860e = {"maxOf", TessBaseAPI.f9204e, "", "a", "b", "(Ljava/lang/Comparable;Ljava/lang/Comparable;)Ljava/lang/Comparable;", "c", "(Ljava/lang/Comparable;Ljava/lang/Comparable;Ljava/lang/Comparable;)Ljava/lang/Comparable;", "", "", "", "", "", "", "minOf", "kotlin-stdlib"}, m8859f = "kotlin/comparisons/ComparisonsKt", m8857h = 1)
/* renamed from: z1.cbt */
/* loaded from: classes3.dex */
class _ComparisonsJvm extends Comparisons {
    @bwy(m8750a = "1.1")
    @NotNull
    /* renamed from: b */
    public static final <T extends Comparable<? super T>> T m5710b(@NotNull T t, @NotNull T t2) {
        cji.m5162f(t, "a");
        cji.m5162f(t2, "b");
        return t.compareTo(t2) >= 0 ? t : t2;
    }

    @bwy(m8750a = "1.1")
    @cey
    /* renamed from: a */
    private static final byte m5733a(byte b, byte b2) {
        return (byte) Math.max((int) b, (int) b2);
    }

    @bwy(m8750a = "1.1")
    @cey
    /* renamed from: a */
    private static final short m5722a(short s, short s2) {
        return (short) Math.max((int) s, (int) s2);
    }

    @bwy(m8750a = "1.1")
    @cey
    /* renamed from: a */
    private static final int m5727a(int i, int i2) {
        return Math.max(i, i2);
    }

    @bwy(m8750a = "1.1")
    @cey
    /* renamed from: a */
    private static final long m5725a(long j, long j2) {
        return Math.max(j, j2);
    }

    @bwy(m8750a = "1.1")
    @cey
    /* renamed from: a */
    private static final float m5729a(float f, float f2) {
        return Math.max(f, f2);
    }

    @bwy(m8750a = "1.1")
    @cey
    /* renamed from: a */
    private static final double m5731a(double d, double d2) {
        return Math.max(d, d2);
    }

    @bwy(m8750a = "1.1")
    @NotNull
    /* renamed from: a */
    public static final <T extends Comparable<? super T>> T m5723a(@NotNull T t, @NotNull T t2, @NotNull T t3) {
        cji.m5162f(t, "a");
        cji.m5162f(t2, "b");
        cji.m5162f(t3, "c");
        return (T) cbr.m5710b(t, cbr.m5710b(t2, t3));
    }

    @bwy(m8750a = "1.1")
    @cey
    /* renamed from: a */
    private static final byte m5732a(byte b, byte b2, byte b3) {
        return (byte) Math.max((int) b, Math.max((int) b2, (int) b3));
    }

    @bwy(m8750a = "1.1")
    @cey
    /* renamed from: a */
    private static final short m5721a(short s, short s2, short s3) {
        return (short) Math.max((int) s, Math.max((int) s2, (int) s3));
    }

    @bwy(m8750a = "1.1")
    @cey
    /* renamed from: a */
    private static final int m5726a(int i, int i2, int i3) {
        return Math.max(i, Math.max(i2, i3));
    }

    @bwy(m8750a = "1.1")
    @cey
    /* renamed from: a */
    private static final long m5724a(long j, long j2, long j3) {
        return Math.max(j, Math.max(j2, j3));
    }

    @bwy(m8750a = "1.1")
    @cey
    /* renamed from: a */
    private static final float m5728a(float f, float f2, float f3) {
        return Math.max(f, Math.max(f2, f3));
    }

    @bwy(m8750a = "1.1")
    @cey
    /* renamed from: a */
    private static final double m5730a(double d, double d2, double d3) {
        return Math.max(d, Math.max(d2, d3));
    }

    @bwy(m8750a = "1.1")
    @NotNull
    /* renamed from: c */
    public static final <T extends Comparable<? super T>> T m5706c(@NotNull T t, @NotNull T t2) {
        cji.m5162f(t, "a");
        cji.m5162f(t2, "b");
        return t.compareTo(t2) <= 0 ? t : t2;
    }

    @bwy(m8750a = "1.1")
    @cey
    /* renamed from: b */
    private static final byte m5720b(byte b, byte b2) {
        return (byte) Math.min((int) b, (int) b2);
    }

    @bwy(m8750a = "1.1")
    @cey
    /* renamed from: b */
    private static final short m5708b(short s, short s2) {
        return (short) Math.min((int) s, (int) s2);
    }

    @bwy(m8750a = "1.1")
    @cey
    /* renamed from: b */
    private static final int m5714b(int i, int i2) {
        return Math.min(i, i2);
    }

    @bwy(m8750a = "1.1")
    @cey
    /* renamed from: b */
    private static final long m5712b(long j, long j2) {
        return Math.min(j, j2);
    }

    @bwy(m8750a = "1.1")
    @cey
    /* renamed from: b */
    private static final float m5716b(float f, float f2) {
        return Math.min(f, f2);
    }

    @bwy(m8750a = "1.1")
    @cey
    /* renamed from: b */
    private static final double m5718b(double d, double d2) {
        return Math.min(d, d2);
    }

    @bwy(m8750a = "1.1")
    @NotNull
    /* renamed from: b */
    public static final <T extends Comparable<? super T>> T m5709b(@NotNull T t, @NotNull T t2, @NotNull T t3) {
        cji.m5162f(t, "a");
        cji.m5162f(t2, "b");
        cji.m5162f(t3, "c");
        return (T) cbr.m5706c(t, cbr.m5706c(t2, t3));
    }

    @bwy(m8750a = "1.1")
    @cey
    /* renamed from: b */
    private static final byte m5719b(byte b, byte b2, byte b3) {
        return (byte) Math.min((int) b, Math.min((int) b2, (int) b3));
    }

    @bwy(m8750a = "1.1")
    @cey
    /* renamed from: b */
    private static final short m5707b(short s, short s2, short s3) {
        return (short) Math.min((int) s, Math.min((int) s2, (int) s3));
    }

    @bwy(m8750a = "1.1")
    @cey
    /* renamed from: b */
    private static final int m5713b(int i, int i2, int i3) {
        return Math.min(i, Math.min(i2, i3));
    }

    @bwy(m8750a = "1.1")
    @cey
    /* renamed from: b */
    private static final long m5711b(long j, long j2, long j3) {
        return Math.min(j, Math.min(j2, j3));
    }

    @bwy(m8750a = "1.1")
    @cey
    /* renamed from: b */
    private static final float m5715b(float f, float f2, float f3) {
        return Math.min(f, Math.min(f2, f3));
    }

    @bwy(m8750a = "1.1")
    @cey
    /* renamed from: b */
    private static final double m5717b(double d, double d2, double d3) {
        return Math.min(d, Math.min(d2, d3));
    }
}
