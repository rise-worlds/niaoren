package p110z1;

import com.googlecode.tesseract.android.TessBaseAPI;

@Metadata(m8864a = 2, m8863b = {1, 1, 15}, m8862c = {1, 0, 3}, m8861d = {"\u0000\u001c\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a\u0018\u0010\u0000\u001a\u00020\u0001*\u0004\u0018\u00010\u00022\b\u0010\u0003\u001a\u0004\u0018\u00010\u0004H\u0001\u001a;\u0010\u0005\u001a\u0002H\u0006\"\n\b\u0000\u0010\u0007*\u0004\u0018\u00010\u0002\"\u0004\b\u0001\u0010\u0006*\u0002H\u00072\u0012\u0010\b\u001a\u000e\u0012\u0004\u0012\u0002H\u0007\u0012\u0004\u0012\u0002H\u00060\tH\u0087\bø\u0001\u0000¢\u0006\u0002\u0010\u000b\u0082\u0002\b\n\u0006\b\u0011(\n0\u0001¨\u0006\f"}, m8860e = {"closeFinally", "", "Ljava/io/Closeable;", "cause", "", "use", "R", TessBaseAPI.f9204e, "block", "Lkotlin/Function1;", "Requires newer compiler version to be inlined correctly.", "(Ljava/io/Closeable;Lkotlin/jvm/functions/Function1;)Ljava/lang/Object;", "kotlin-stdlib"})
@cgo(m5270a = "CloseableKt")
/* renamed from: z1.cfn */
/* loaded from: classes3.dex */
public final class Closeable {
    @cey
    /* renamed from: a */
    private static final <T extends java.io.Closeable, R> R m5423a(T t, chd<? super T, ? extends R> chdVar) {
        Throwable th = null;
        try {
            R r = (R) chdVar.invoke(t);
            InlineMarker.m5201b(1);
            if (cfe.m5471a(1, 1, 0)) {
                m5424a(t, th);
            } else if (t != null) {
                t.close();
            }
            InlineMarker.m5200c(1);
            return r;
        } finally {
            try {
            } catch (Throwable th2) {
                throw th2;
            }
        }
    }

    @bwy(m8750a = "1.1")
    @bwt
    /* renamed from: a */
    public static final void m5424a(@dbs java.io.Closeable closeable, @dbs Throwable th) {
        if (closeable != null) {
            if (th == null) {
                closeable.close();
                return;
            }
            try {
                closeable.close();
            } catch (Throwable th2) {
                bvm.m8896a(th, th2);
            }
        }
    }
}
