package p110z1;

/* renamed from: z1.avh */
/* loaded from: classes3.dex */
public final class ObjectHelper {

    /* renamed from: a */
    static final BiPredicate<Object, Object> f17596a = new C3936a();

    /* renamed from: a */
    public static int m9879a(int i, int i2) {
        if (i < i2) {
            return -1;
        }
        return i > i2 ? 1 : 0;
    }

    /* renamed from: a */
    public static int m9877a(long j, long j2) {
        int i = (j > j2 ? 1 : (j == j2 ? 0 : -1));
        if (i < 0) {
            return -1;
        }
        return i > 0 ? 1 : 0;
    }

    private ObjectHelper() {
        throw new IllegalStateException("No instances!");
    }

    /* renamed from: a */
    public static <T> T m9873a(T t, String str) {
        if (t != null) {
            return t;
        }
        throw new NullPointerException(str);
    }

    /* renamed from: a */
    public static boolean m9874a(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    /* renamed from: a */
    public static int m9875a(Object obj) {
        if (obj != null) {
            return obj.hashCode();
        }
        return 0;
    }

    /* renamed from: a */
    public static <T> BiPredicate<T, T> m9880a() {
        return (BiPredicate<T, T>) f17596a;
    }

    /* renamed from: a */
    public static int m9878a(int i, String str) {
        if (i > 0) {
            return i;
        }
        throw new IllegalArgumentException(str + " > 0 required but it was " + i);
    }

    /* renamed from: a */
    public static long m9876a(long j, String str) {
        if (j > 0) {
            return j;
        }
        throw new IllegalArgumentException(str + " > 0 required but it was " + j);
    }

    /* compiled from: ObjectHelper.java */
    /* renamed from: z1.avh$a */
    /* loaded from: classes3.dex */
    static final class C3936a implements BiPredicate<Object, Object> {
        C3936a() {
        }

        @Override // p110z1.BiPredicate
        /* renamed from: a */
        public boolean mo9871a(Object obj, Object obj2) {
            return ObjectHelper.m9874a(obj, obj2);
        }
    }

    @Deprecated
    /* renamed from: b */
    public static long m9872b(long j, String str) {
        throw new InternalError("Null check on a primitive: " + str);
    }
}
