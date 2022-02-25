package p110z1;

/* renamed from: z1.pw  reason: invalid class name */
/* loaded from: classes3.dex */
public final class C$Gson$Preconditions {
    private C$Gson$Preconditions() {
        throw new UnsupportedOperationException();
    }

    /* renamed from: a */
    public static <T> T m1423a(T t) {
        if (t != null) {
            return t;
        }
        throw new NullPointerException();
    }

    /* renamed from: a */
    public static void m1422a(boolean z) {
        if (!z) {
            throw new IllegalArgumentException();
        }
    }
}
