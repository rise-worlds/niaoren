package p110z1;

/* renamed from: z1.md */
/* loaded from: classes3.dex */
public final class FormatException extends ReaderException {

    /* renamed from: c */
    private static final FormatException f22403c;

    static {
        FormatException mdVar = new FormatException();
        f22403c = mdVar;
        mdVar.setStackTrace(f22706b);
    }

    private FormatException() {
    }

    private FormatException(Throwable th) {
        super(th);
    }

    /* renamed from: a */
    public static FormatException m2059a() {
        return f22705a ? new FormatException() : f22403c;
    }

    /* renamed from: a */
    public static FormatException m2058a(Throwable th) {
        return f22705a ? new FormatException(th) : f22403c;
    }
}
