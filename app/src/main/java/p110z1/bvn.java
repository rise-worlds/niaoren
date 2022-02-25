package p110z1;

import com.stripe.android.CustomerSession;
import java.io.PrintStream;
import java.io.PrintWriter;

/* compiled from: Exceptions.kt */
@Metadata(m8864a = 5, m8863b = {1, 1, 15}, m8862c = {1, 0, 3}, m8861d = {"\u0000&\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\u0010\u0003\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a\u0012\u0010\b\u001a\u00020\t*\u00020\u00032\u0006\u0010\n\u001a\u00020\u0003\u001a\r\u0010\u000b\u001a\u00020\t*\u00020\u0003H\u0087\b\u001a\u0015\u0010\u000b\u001a\u00020\t*\u00020\u00032\u0006\u0010\f\u001a\u00020\rH\u0087\b\u001a\u0015\u0010\u000b\u001a\u00020\t*\u00020\u00032\u0006\u0010\u000e\u001a\u00020\u000fH\u0087\b\"!\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001*\u00020\u00038F¢\u0006\f\u0012\u0004\b\u0004\u0010\u0005\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0010"}, m8860e = {"stackTrace", "", "Ljava/lang/StackTraceElement;", "", "stackTrace$annotations", "(Ljava/lang/Throwable;)V", "getStackTrace", "(Ljava/lang/Throwable;)[Ljava/lang/StackTraceElement;", "addSuppressed", "", CustomerSession.f11798b, "printStackTrace", "stream", "Ljava/io/PrintStream;", "writer", "Ljava/io/PrintWriter;", "kotlin-stdlib"}, m8859f = "kotlin/ExceptionsKt", m8857h = 1)
/* renamed from: z1.bvn */
/* loaded from: classes3.dex */
class bvn {
    /* renamed from: a */
    public static /* synthetic */ void m8899a(Throwable th) {
    }

    @cey
    /* renamed from: c */
    private static final void m8894c(@NotNull Throwable th) {
        if (th != null) {
            th.printStackTrace();
            return;
        }
        throw new TypeCastException("null cannot be cast to non-null type java.lang.Throwable");
    }

    @cey
    /* renamed from: a */
    private static final void m8897a(@NotNull Throwable th, PrintWriter printWriter) {
        if (th != null) {
            th.printStackTrace(printWriter);
            return;
        }
        throw new TypeCastException("null cannot be cast to non-null type java.lang.Throwable");
    }

    @cey
    /* renamed from: a */
    private static final void m8898a(@NotNull Throwable th, PrintStream printStream) {
        if (th != null) {
            th.printStackTrace(printStream);
            return;
        }
        throw new TypeCastException("null cannot be cast to non-null type java.lang.Throwable");
    }

    @NotNull
    /* renamed from: b */
    public static final StackTraceElement[] m8895b(@NotNull Throwable th) {
        cji.m5162f(th, "$this$stackTrace");
        StackTraceElement[] stackTrace = th.getStackTrace();
        if (stackTrace == null) {
            cji.m5196a();
        }
        return stackTrace;
    }

    /* renamed from: a */
    public static final void m8896a(@NotNull Throwable th, @NotNull Throwable th2) {
        cji.m5162f(th, "$this$addSuppressed");
        cji.m5162f(th2, CustomerSession.f11798b);
        cfe.f20646a.mo5454a(th, th2);
    }
}
