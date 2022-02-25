package p110z1;

/* renamed from: z1.nm */
/* loaded from: classes3.dex */
final class FormatInformation {

    /* renamed from: c */
    private static final int f22602c = 21522;

    /* renamed from: d */
    private static final int[][] f22603d = {new int[]{f22602c, 0}, new int[]{20773, 1}, new int[]{24188, 2}, new int[]{23371, 3}, new int[]{17913, 4}, new int[]{16590, 5}, new int[]{20375, 6}, new int[]{19104, 7}, new int[]{30660, 8}, new int[]{29427, 9}, new int[]{32170, 10}, new int[]{30877, 11}, new int[]{26159, 12}, new int[]{25368, 13}, new int[]{27713, 14}, new int[]{26998, 15}, new int[]{5769, 16}, new int[]{5054, 17}, new int[]{7399, 18}, new int[]{6608, 19}, new int[]{1890, 20}, new int[]{597, 21}, new int[]{3340, 22}, new int[]{2107, 23}, new int[]{13663, 24}, new int[]{12392, 25}, new int[]{16177, 26}, new int[]{14854, 27}, new int[]{9396, 28}, new int[]{8579, 29}, new int[]{11994, 30}, new int[]{11245, 31}};

    /* renamed from: a */
    final ErrorCorrectionLevel f22604a;

    /* renamed from: b */
    final byte f22605b;

    private FormatInformation(int i) {
        this.f22604a = ErrorCorrectionLevel.m1822a((i >> 3) & 3);
        this.f22605b = (byte) (i & 7);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public static int m1820a(int i, int i2) {
        return Integer.bitCount(i ^ i2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: b */
    public static FormatInformation m1818b(int i, int i2) {
        FormatInformation c = m1817c(i, i2);
        return c != null ? c : m1817c(i ^ f22602c, i2 ^ f22602c);
    }

    /* renamed from: c */
    private static FormatInformation m1817c(int i, int i2) {
        int[][] iArr;
        int bitCount;
        int i3 = Integer.MAX_VALUE;
        int i4 = 0;
        for (int[] iArr2 : f22603d) {
            int i5 = iArr2[0];
            if (i5 == i || i5 == i2) {
                return new FormatInformation(iArr2[1]);
            }
            int bitCount2 = Integer.bitCount(i ^ i5);
            if (bitCount2 < i3) {
                i4 = iArr2[1];
                i3 = bitCount2;
            }
            if (i != i2 && (bitCount = Integer.bitCount(i5 ^ i2)) < i3) {
                i4 = iArr2[1];
                i3 = bitCount;
            }
        }
        if (i3 <= 3) {
            return new FormatInformation(i4);
        }
        return null;
    }

    /* renamed from: a */
    private ErrorCorrectionLevel m1821a() {
        return this.f22604a;
    }

    /* renamed from: b */
    private byte m1819b() {
        return this.f22605b;
    }

    public final int hashCode() {
        return (this.f22604a.ordinal() << 3) | this.f22605b;
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof FormatInformation)) {
            return false;
        }
        FormatInformation nmVar = (FormatInformation) obj;
        return this.f22604a == nmVar.f22604a && this.f22605b == nmVar.f22605b;
    }
}
