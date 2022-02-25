package p110z1;

import com.ctetin.expandabletextviewlibrary.ExpandableTextView;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* renamed from: z1.gm */
/* loaded from: classes3.dex */
public final class URIResultParser extends ResultParser {

    /* renamed from: a */
    private static final Pattern f21786a = Pattern.compile("[a-zA-Z][a-zA-Z0-9+-.]+:");

    /* renamed from: b */
    private static final Pattern f21787b = Pattern.compile("([a-zA-Z0-9\\-]+\\.){1,6}[a-zA-Z]{2,}(:\\d{1,5})?(/|\\?|$)");

    /* renamed from: c */
    private static URIParsedResult m2718c(C5422ol olVar) {
        String b = m2579b(olVar);
        if (b.startsWith("URL:") || b.startsWith("URI:")) {
            return new URIParsedResult(b.substring(4).trim(), null);
        }
        String trim = b.trim();
        if (m2719a(trim)) {
            return new URIParsedResult(trim, null);
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public static boolean m2719a(String str) {
        if (str.contains(ExpandableTextView.f6958c)) {
            return false;
        }
        Matcher matcher = f21786a.matcher(str);
        if (matcher.find() && matcher.start() == 0) {
            return true;
        }
        Matcher matcher2 = f21787b.matcher(str);
        return matcher2.find() && matcher2.start() == 0;
    }

    @Override // p110z1.ResultParser
    /* renamed from: a */
    public final /* synthetic */ ParsedResult mo2567a(C5422ol olVar) {
        String b = m2579b(olVar);
        if (b.startsWith("URL:") || b.startsWith("URI:")) {
            return new URIParsedResult(b.substring(4).trim(), null);
        }
        String trim = b.trim();
        if (m2719a(trim)) {
            return new URIParsedResult(trim, null);
        }
        return null;
    }
}
