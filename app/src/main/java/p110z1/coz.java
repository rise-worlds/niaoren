package p110z1;

import com.github.kevinsawicki.http.HttpRequest;
import java.nio.charset.Charset;

/* compiled from: Charsets.kt */
@Metadata(m8864a = 2, m8863b = {1, 1, 15}, m8862c = {1, 0, 3}, m8861d = {"\u0000\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\u001a\u0011\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u0087\b¨\u0006\u0004"}, m8860e = {HttpRequest.PARAM_CHARSET, "Ljava/nio/charset/Charset;", "charsetName", "", "kotlin-stdlib"})
@cgo(m5270a = "CharsetsKt")
/* renamed from: z1.coz */
/* loaded from: classes3.dex */
public final class coz {
    @cey
    /* renamed from: a */
    private static final Charset m4220a(String str) {
        Charset forName = Charset.forName(str);
        cji.m5175b(forName, "Charset.forName(charsetName)");
        return forName;
    }
}
