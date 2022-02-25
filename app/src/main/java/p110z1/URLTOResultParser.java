package p110z1;

/* renamed from: z1.gn */
/* loaded from: classes3.dex */
public final class URLTOResultParser extends ResultParser {
    /* renamed from: c */
    private static URIParsedResult m2717c(C5422ol olVar) {
        int indexOf;
        String b = m2579b(olVar);
        String str = null;
        if ((!b.startsWith("urlto:") && !b.startsWith("URLTO:")) || (indexOf = b.indexOf(58, 6)) < 0) {
            return null;
        }
        if (indexOf > 6) {
            str = b.substring(6, indexOf);
        }
        return new URIParsedResult(b.substring(indexOf + 1), str);
    }

    @Override // p110z1.ResultParser
    /* renamed from: a */
    public final /* synthetic */ ParsedResult mo2567a(C5422ol olVar) {
        int indexOf;
        String b = m2579b(olVar);
        String str = null;
        if ((!b.startsWith("urlto:") && !b.startsWith("URLTO:")) || (indexOf = b.indexOf(58, 6)) < 0) {
            return null;
        }
        if (indexOf > 6) {
            str = b.substring(6, indexOf);
        }
        return new URIParsedResult(b.substring(indexOf + 1), str);
    }
}
