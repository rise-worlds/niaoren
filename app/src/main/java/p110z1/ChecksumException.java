package p110z1;

/* renamed from: z1.io */
/* loaded from: classes3.dex */
public final class ChecksumException extends ReaderException {

    /* renamed from: c */
    private static final ChecksumException f22030c;

    static {
        ChecksumException ioVar = new ChecksumException();
        f22030c = ioVar;
        ioVar.setStackTrace(f22706b);
    }

    private ChecksumException() {
    }

    private ChecksumException(Throwable th) {
        super(th);
    }

    /* renamed from: a */
    public static ChecksumException m2421a() {
        return f22705a ? new ChecksumException() : f22030c;
    }

    /* renamed from: a */
    private static ChecksumException m2420a(Throwable th) {
        return f22705a ? new ChecksumException(th) : f22030c;
    }
}
