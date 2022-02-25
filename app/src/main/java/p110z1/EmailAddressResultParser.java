package p110z1;

import com.tencent.smtt.sdk.WebView;
import java.util.Map;
import java.util.regex.Pattern;

/* renamed from: z1.hb */
/* loaded from: classes3.dex */
public final class EmailAddressResultParser extends ResultParser {

    /* renamed from: a */
    private static final Pattern f21852a = Pattern.compile(",");

    @Override // p110z1.ResultParser
    /* renamed from: a */
    public final /* synthetic */ ParsedResult mo2567a(C5422ol olVar) {
        return m2628c(olVar);
    }

    /* renamed from: c */
    private static EmailAddressParsedResult m2628c(C5422ol olVar) {
        String str;
        String str2;
        String[] strArr;
        String str3;
        String b = m2579b(olVar);
        String[] strArr2 = null;
        if (b.startsWith(WebView.SCHEME_MAILTO) || b.startsWith("MAILTO:")) {
            String substring = b.substring(7);
            int indexOf = substring.indexOf(63);
            if (indexOf >= 0) {
                substring = substring.substring(0, indexOf);
            }
            try {
                String d = m2576d(substring);
                String[] split = !d.isEmpty() ? f21852a.split(d) : null;
                Map<String, String> c = m2578c(b);
                if (c != null) {
                    if (split == null && (str3 = c.get("to")) != null) {
                        split = f21852a.split(str3);
                    }
                    String str4 = c.get("cc");
                    strArr = str4 != null ? f21852a.split(str4) : null;
                    String str5 = c.get("bcc");
                    if (str5 != null) {
                        strArr2 = f21852a.split(str5);
                    }
                    str2 = c.get("subject");
                    str = c.get("body");
                } else {
                    split = split;
                    strArr = null;
                    strArr2 = null;
                    str2 = null;
                    str = null;
                }
                return new EmailAddressParsedResult(split, strArr, strArr2, str2, str);
            } catch (IllegalArgumentException unused) {
                return null;
            }
        } else if (!EmailDoCoMoResultParser.m2627a(b)) {
            return null;
        } else {
            return new EmailAddressParsedResult(b);
        }
    }
}
