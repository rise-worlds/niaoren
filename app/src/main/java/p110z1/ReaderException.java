package p110z1;

/* renamed from: z1.ok */
/* loaded from: classes3.dex */
public abstract class ReaderException extends Exception {

    /* renamed from: a */
    protected static final boolean f22705a;

    /* renamed from: b */
    protected static final StackTraceElement[] f22706b;

    static {
        f22705a = System.getProperty("surefire.test.class.path") != null;
        f22706b = new StackTraceElement[0];
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ReaderException() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ReaderException(Throwable th) {
        super(th);
    }

    @Override // java.lang.Throwable
    public final synchronized Throwable fillInStackTrace() {
        return null;
    }
}
