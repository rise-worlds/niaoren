package p110z1;

@Metadata(m8864a = 5, m8863b = {1, 1, 15}, m8862c = {1, 0, 3}, m8861d = {"\u0000\u0014\n\u0000\n\u0002\u0010\u000b\n\u0002\u0010\f\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0000\u001a\u001c\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00022\b\b\u0002\u0010\u0004\u001a\u00020\u0001\u001a\n\u0010\u0005\u001a\u00020\u0001*\u00020\u0002\u001a\u0015\u0010\u0006\u001a\u00020\u0007*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0007H\u0087\n¨\u0006\b"}, m8860e = {"equals", "", "", "other", "ignoreCase", "isSurrogate", "plus", "", "kotlin-stdlib"}, m8859f = "kotlin/text/CharsKt", m8857h = 1)
/* renamed from: z1.cox */
/* loaded from: classes3.dex */
class Char extends CharJVM {
    /* renamed from: d */
    public static final boolean m4224d(char c) {
        return 55296 <= c && 57343 >= c;
    }

    @cey
    /* renamed from: a */
    private static final String m4225a(char c, String str) {
        return String.valueOf(c) + str;
    }

    /* renamed from: a */
    public static /* synthetic */ boolean m4226a(char c, char c2, boolean z, int i, Object obj) {
        if ((i & 2) != 0) {
            z = false;
        }
        return cov.m4227a(c, c2, z);
    }

    /* renamed from: a */
    public static final boolean m4227a(char c, char c2, boolean z) {
        if (c == c2) {
            return true;
        }
        if (!z) {
            return false;
        }
        return Character.toUpperCase(c) == Character.toUpperCase(c2) || Character.toLowerCase(c) == Character.toLowerCase(c2);
    }
}
