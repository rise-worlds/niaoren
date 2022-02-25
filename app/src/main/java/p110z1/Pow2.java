package p110z1;

/* renamed from: z1.bte */
/* loaded from: classes3.dex */
public final class Pow2 {
    /* renamed from: b */
    public static boolean m9386b(int i) {
        return (i & (i + (-1))) == 0;
    }

    private Pow2() {
        throw new IllegalStateException("No instances!");
    }

    /* renamed from: a */
    public static int m9387a(int i) {
        return 1 << (32 - Integer.numberOfLeadingZeros(i - 1));
    }
}
