package p110z1;

import com.tencent.smtt.sdk.WebView;

/* renamed from: z1.gj */
/* loaded from: classes3.dex */
public final class TelResultParser extends ResultParser {
    /* renamed from: c */
    private static TelParsedResult m2727c(C5422ol olVar) {
        String str;
        String b = m2579b(olVar);
        if (!b.startsWith(WebView.SCHEME_TEL) && !b.startsWith("TEL:")) {
            return null;
        }
        if (b.startsWith("TEL:")) {
            str = WebView.SCHEME_TEL + b.substring(4);
        } else {
            str = b;
        }
        int indexOf = b.indexOf(63, 4);
        return new TelParsedResult(indexOf < 0 ? b.substring(4) : b.substring(4, indexOf), str);
    }

    @Override // p110z1.ResultParser
    /* renamed from: a */
    public final /* synthetic */ ParsedResult mo2567a(C5422ol olVar) {
        String str;
        String b = m2579b(olVar);
        if (!b.startsWith(WebView.SCHEME_TEL) && !b.startsWith("TEL:")) {
            return null;
        }
        if (b.startsWith("TEL:")) {
            str = WebView.SCHEME_TEL + b.substring(4);
        } else {
            str = b;
        }
        int indexOf = b.indexOf(63, 4);
        return new TelParsedResult(indexOf < 0 ? b.substring(4) : b.substring(4, indexOf), str);
    }
}
