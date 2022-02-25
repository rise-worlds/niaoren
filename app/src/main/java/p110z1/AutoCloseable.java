package p110z1;

import com.googlecode.tesseract.android.TessBaseAPI;

@Metadata(m8864a = 2, m8863b = {1, 1, 15}, m8862c = {1, 0, 3}, m8861d = {"\u0000\u001c\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\u0018\u0010\u0000\u001a\u00020\u0001*\u0004\u0018\u00010\u00022\b\u0010\u0003\u001a\u0004\u0018\u00010\u0004H\u0001\u001a8\u0010\u0005\u001a\u0002H\u0006\"\n\b\u0000\u0010\u0007*\u0004\u0018\u00010\u0002\"\u0004\b\u0001\u0010\u0006*\u0002H\u00072\u0012\u0010\b\u001a\u000e\u0012\u0004\u0012\u0002H\u0007\u0012\u0004\u0012\u0002H\u00060\tH\u0087\b¢\u0006\u0002\u0010\n¨\u0006\u000b"}, m8860e = {"closeFinally", "", "Ljava/lang/AutoCloseable;", "cause", "", "use", "R", TessBaseAPI.f9204e, "block", "Lkotlin/Function1;", "(Ljava/lang/AutoCloseable;Lkotlin/jvm/functions/Function1;)Ljava/lang/Object;", "kotlin-stdlib-jdk7"}, m8858g = "kotlin")
@cgo(m5270a = "AutoCloseableKt")
/* renamed from: z1.cgi */
/* loaded from: classes3.dex */
public final class AutoCloseable {
    @bwy(m8750a = "1.2")
    @cey
    /* renamed from: a */
    private static final <T extends AutoCloseable, R> R m5282a(T t, chd<? super T, ? extends R> chdVar) {
        Throwable th = null;
        try {
            return (R) chdVar.invoke(t);
        } finally {
            try {
            } finally {
            }
        }
    }

    @bwy(m8750a = "1.2")
    @bwt
    /* renamed from: a */
    public static final void m5283a(@dbs AutoCloseable autoCloseable, @dbs Throwable th) {
        if (autoCloseable != null) {
            if (th == null) {
                autoCloseable.close();
                return;
            }
            try {
                autoCloseable.close();
            } catch (Throwable th2) {
                th.addSuppressed(th2);
            }
        }
    }
}
