package p110z1;

import java.nio.charset.Charset;
import org.apache.http.protocol.HTTP;

@Metadata(m8864a = 1, m8863b = {1, 1, 15}, m8862c = {1, 0, 3}, m8861d = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0010\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0010\u0010\u0003\u001a\u00020\u00048\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u00020\u00048\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0006\u001a\u00020\u00048\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0007\u001a\u00020\u00048\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\b\u001a\u00020\u00048\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\t\u001a\u00020\u00048G¢\u0006\u0006\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\f\u001a\u00020\u00048G¢\u0006\u0006\u001a\u0004\b\r\u0010\u000bR\u0011\u0010\u000e\u001a\u00020\u00048G¢\u0006\u0006\u001a\u0004\b\u000f\u0010\u000bR\u0010\u0010\u0010\u001a\u00020\u00048\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0011\u001a\u0004\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0012\u001a\u0004\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0013\u001a\u0004\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0014"}, m8860e = {"Lkotlin/text/Charsets;", "", "()V", "ISO_8859_1", "Ljava/nio/charset/Charset;", "US_ASCII", "UTF_16", "UTF_16BE", "UTF_16LE", "UTF_32", "UTF32", "()Ljava/nio/charset/Charset;", "UTF_32BE", "UTF32_BE", "UTF_32LE", "UTF32_LE", "UTF_8", "utf_32", "utf_32be", "utf_32le", "kotlin-stdlib"})
/* renamed from: z1.coy */
/* loaded from: classes3.dex */
public final class Charsets {
    @JvmPlatformAnnotations
    @NotNull

    /* renamed from: a */
    public static final Charset f20995a;
    @JvmPlatformAnnotations
    @NotNull

    /* renamed from: b */
    public static final Charset f20996b;
    @JvmPlatformAnnotations
    @NotNull

    /* renamed from: c */
    public static final Charset f20997c;
    @JvmPlatformAnnotations
    @NotNull

    /* renamed from: d */
    public static final Charset f20998d;
    @JvmPlatformAnnotations
    @NotNull

    /* renamed from: e */
    public static final Charset f20999e;
    @JvmPlatformAnnotations
    @NotNull

    /* renamed from: f */
    public static final Charset f21000f;

    /* renamed from: g */
    public static final Charsets f21001g = new Charsets();

    /* renamed from: h */
    private static Charset f21002h;

    /* renamed from: i */
    private static Charset f21003i;

    /* renamed from: j */
    private static Charset f21004j;

    static {
        Charset forName = Charset.forName("UTF-8");
        cji.m5175b(forName, "Charset.forName(\"UTF-8\")");
        f20995a = forName;
        Charset forName2 = Charset.forName(HTTP.UTF_16);
        cji.m5175b(forName2, "Charset.forName(\"UTF-16\")");
        f20996b = forName2;
        Charset forName3 = Charset.forName("UTF-16BE");
        cji.m5175b(forName3, "Charset.forName(\"UTF-16BE\")");
        f20997c = forName3;
        Charset forName4 = Charset.forName("UTF-16LE");
        cji.m5175b(forName4, "Charset.forName(\"UTF-16LE\")");
        f20998d = forName4;
        Charset forName5 = Charset.forName("US-ASCII");
        cji.m5175b(forName5, "Charset.forName(\"US-ASCII\")");
        f20999e = forName5;
        Charset forName6 = Charset.forName("ISO-8859-1");
        cji.m5175b(forName6, "Charset.forName(\"ISO-8859-1\")");
        f21000f = forName6;
    }

    private Charsets() {
    }

    @cgo(m5270a = "UTF32")
    @NotNull
    /* renamed from: a */
    public final Charset m4223a() {
        Charset charset = f21002h;
        if (charset != null) {
            return charset;
        }
        Charset forName = Charset.forName("UTF-32");
        cji.m5175b(forName, "Charset.forName(\"UTF-32\")");
        f21002h = forName;
        return forName;
    }

    @cgo(m5270a = "UTF32_LE")
    @NotNull
    /* renamed from: b */
    public final Charset m4222b() {
        Charset charset = f21003i;
        if (charset != null) {
            return charset;
        }
        Charset forName = Charset.forName("UTF-32LE");
        cji.m5175b(forName, "Charset.forName(\"UTF-32LE\")");
        f21003i = forName;
        return forName;
    }

    @cgo(m5270a = "UTF32_BE")
    @NotNull
    /* renamed from: c */
    public final Charset m4221c() {
        Charset charset = f21004j;
        if (charset != null) {
            return charset;
        }
        Charset forName = Charset.forName("UTF-32BE");
        cji.m5175b(forName, "Charset.forName(\"UTF-32BE\")");
        f21004j = forName;
        return forName;
    }
}
