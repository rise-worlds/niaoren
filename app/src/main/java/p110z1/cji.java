package p110z1;

import java.util.Arrays;
import org.apache.commons.p105io.FilenameUtils;
import org.apache.commons.p105io.IOUtils;

/* compiled from: Intrinsics.java */
/* renamed from: z1.cji */
/* loaded from: classes3.dex */
public class cji {
    /* renamed from: a */
    public static int m5193a(int i, int i2) {
        if (i < i2) {
            return -1;
        }
        return i == i2 ? 0 : 1;
    }

    /* renamed from: a */
    public static int m5190a(long j, long j2) {
        int i = (j > j2 ? 1 : (j == j2 ? 0 : -1));
        if (i < 0) {
            return -1;
        }
        return i == 0 ? 0 : 1;
    }

    private cji() {
    }

    /* renamed from: a */
    public static String m5180a(String str, Object obj) {
        return str + obj;
    }

    /* renamed from: a */
    public static void m5185a(Object obj) {
        if (obj == null) {
            m5196a();
        }
    }

    /* renamed from: a */
    public static void m5183a(Object obj, String str) {
        if (obj == null) {
            m5181a(str);
        }
    }

    /* renamed from: a */
    public static void m5196a() {
        throw ((KotlinNullPointerException) m5178a(new KotlinNullPointerException()));
    }

    /* renamed from: a */
    public static void m5181a(String str) {
        throw ((KotlinNullPointerException) m5178a(new KotlinNullPointerException(str)));
    }

    /* renamed from: b */
    public static void m5173b(String str) {
        throw ((UninitializedPropertyAccessException) m5178a(new UninitializedPropertyAccessException(str)));
    }

    /* renamed from: c */
    public static void m5170c(String str) {
        m5173b("lateinit property " + str + " has not been initialized");
    }

    /* renamed from: b */
    public static void m5176b() {
        throw ((AssertionError) m5178a(new AssertionError()));
    }

    /* renamed from: d */
    public static void m5167d(String str) {
        throw ((AssertionError) m5178a(new AssertionError(str)));
    }

    /* renamed from: c */
    public static void m5172c() {
        throw ((IllegalArgumentException) m5178a(new IllegalArgumentException()));
    }

    /* renamed from: e */
    public static void m5164e(String str) {
        throw ((IllegalArgumentException) m5178a(new IllegalArgumentException(str)));
    }

    /* renamed from: d */
    public static void m5169d() {
        throw ((IllegalStateException) m5178a(new IllegalStateException()));
    }

    /* renamed from: f */
    public static void m5161f(String str) {
        throw ((IllegalStateException) m5178a(new IllegalStateException(str)));
    }

    /* renamed from: b */
    public static void m5175b(Object obj, String str) {
        if (obj == null) {
            throw ((IllegalStateException) m5178a(new IllegalStateException(str + " must not be null")));
        }
    }

    /* renamed from: c */
    public static void m5171c(Object obj, String str) {
        if (obj == null) {
            throw ((IllegalStateException) m5178a(new IllegalStateException(str)));
        }
    }

    /* renamed from: a */
    public static void m5182a(Object obj, String str, String str2) {
        if (obj == null) {
            throw ((IllegalStateException) m5178a(new IllegalStateException("Method specified as non-null returned null: " + str + Consts.f23430h + str2)));
        }
    }

    /* renamed from: d */
    public static void m5168d(Object obj, String str) {
        if (obj == null) {
            throw ((IllegalStateException) m5178a(new IllegalStateException(str)));
        }
    }

    /* renamed from: b */
    public static void m5174b(Object obj, String str, String str2) {
        if (obj == null) {
            throw ((IllegalStateException) m5178a(new IllegalStateException("Field specified as non-null is null: " + str + Consts.f23430h + str2)));
        }
    }

    /* renamed from: e */
    public static void m5165e(Object obj, String str) {
        if (obj == null) {
            throw ((IllegalStateException) m5178a(new IllegalStateException(str)));
        }
    }

    /* renamed from: f */
    public static void m5162f(Object obj, String str) {
        if (obj == null) {
            m5156j(str);
        }
    }

    /* renamed from: g */
    public static void m5160g(Object obj, String str) {
        if (obj == null) {
            throw ((IllegalArgumentException) m5178a(new IllegalArgumentException(str)));
        }
    }

    /* renamed from: j */
    private static void m5156j(String str) {
        StackTraceElement stackTraceElement = Thread.currentThread().getStackTrace()[3];
        String className = stackTraceElement.getClassName();
        String methodName = stackTraceElement.getMethodName();
        throw ((IllegalArgumentException) m5178a(new IllegalArgumentException("Parameter specified as non-null is null: method " + className + Consts.f23430h + methodName + ", parameter " + str)));
    }

    /* renamed from: a */
    public static boolean m5184a(Object obj, Object obj2) {
        if (obj == null) {
            return obj2 == null;
        }
        return obj.equals(obj2);
    }

    @bwy(m8750a = "1.1")
    /* renamed from: a */
    public static boolean m5188a(Double d, Double d2) {
        if (d == null) {
            if (d2 == null) {
                return true;
            }
        } else if (d2 != null && d.doubleValue() == d2.doubleValue()) {
            return true;
        }
        return false;
    }

    @bwy(m8750a = "1.1")
    /* renamed from: a */
    public static boolean m5189a(Double d, double d2) {
        return d != null && d.doubleValue() == d2;
    }

    @bwy(m8750a = "1.1")
    /* renamed from: a */
    public static boolean m5195a(double d, Double d2) {
        return d2 != null && d == d2.doubleValue();
    }

    @bwy(m8750a = "1.1")
    /* renamed from: a */
    public static boolean m5186a(Float f, Float f2) {
        if (f == null) {
            if (f2 == null) {
                return true;
            }
        } else if (f2 != null && f.floatValue() == f2.floatValue()) {
            return true;
        }
        return false;
    }

    @bwy(m8750a = "1.1")
    /* renamed from: a */
    public static boolean m5187a(Float f, float f2) {
        return f != null && f.floatValue() == f2;
    }

    @bwy(m8750a = "1.1")
    /* renamed from: a */
    public static boolean m5194a(float f, Float f2) {
        return f2 != null && f == f2.floatValue();
    }

    /* renamed from: e */
    public static void m5166e() {
        m5159g("This function has a reified type parameter and thus can only be inlined at compilation time, not called directly.");
    }

    /* renamed from: g */
    public static void m5159g(String str) {
        throw new UnsupportedOperationException(str);
    }

    /* renamed from: a */
    public static void m5192a(int i, String str) {
        m5166e();
    }

    /* renamed from: a */
    public static void m5191a(int i, String str, String str2) {
        m5159g(str2);
    }

    /* renamed from: f */
    public static void m5163f() {
        m5166e();
    }

    /* renamed from: h */
    public static void m5158h(String str) {
        m5159g(str);
    }

    /* renamed from: i */
    public static void m5157i(String str) throws ClassNotFoundException {
        String replace = str.replace(IOUtils.DIR_SEPARATOR_UNIX, FilenameUtils.EXTENSION_SEPARATOR);
        try {
            Class.forName(replace);
        } catch (ClassNotFoundException e) {
            throw ((ClassNotFoundException) m5178a(new ClassNotFoundException("Class " + replace + " is not found. Please update the Kotlin runtime to the latest version", e)));
        }
    }

    /* renamed from: a */
    public static void m5179a(String str, String str2) throws ClassNotFoundException {
        String replace = str.replace(IOUtils.DIR_SEPARATOR_UNIX, FilenameUtils.EXTENSION_SEPARATOR);
        try {
            Class.forName(replace);
        } catch (ClassNotFoundException e) {
            throw ((ClassNotFoundException) m5178a(new ClassNotFoundException("Class " + replace + " is not found: this code requires the Kotlin runtime of version at least " + str2, e)));
        }
    }

    /* renamed from: a */
    private static <T extends Throwable> T m5178a(T t) {
        return (T) m5177a((Throwable) t, cji.class.getName());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public static <T extends Throwable> T m5177a(T t, String str) {
        StackTraceElement[] stackTrace = t.getStackTrace();
        int length = stackTrace.length;
        int i = -1;
        for (int i2 = 0; i2 < length; i2++) {
            if (str.equals(stackTrace[i2].getClassName())) {
                i = i2;
            }
        }
        t.setStackTrace((StackTraceElement[]) Arrays.copyOfRange(stackTrace, i + 1, length));
        return t;
    }
}
