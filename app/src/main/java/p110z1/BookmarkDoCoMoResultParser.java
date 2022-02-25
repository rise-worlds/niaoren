package p110z1;

/* renamed from: z1.gy */
/* loaded from: classes3.dex */
public final class BookmarkDoCoMoResultParser extends AbstractDoCoMoResultParser {
    /* renamed from: c */
    private static URIParsedResult m2653c(C5422ol olVar) {
        String str = olVar.f22707a;
        if (!str.startsWith("MEBKM:")) {
            return null;
        }
        String a = m2728a("TITLE:", str, true);
        String[] a2 = m2729a("URL:", str);
        if (a2 == null) {
            return null;
        }
        String str2 = a2[0];
        if (URIResultParser.m2719a(str2)) {
            return new URIParsedResult(str2, a);
        }
        return null;
    }

    @Override // p110z1.ResultParser
    /* renamed from: a */
    public final /* synthetic */ ParsedResult mo2567a(C5422ol olVar) {
        String str = olVar.f22707a;
        if (!str.startsWith("MEBKM:")) {
            return null;
        }
        String a = m2728a("TITLE:", str, true);
        String[] a2 = m2729a("URL:", str);
        if (a2 == null) {
            return null;
        }
        String str2 = a2[0];
        if (URIResultParser.m2719a(str2)) {
            return new URIParsedResult(str2, a);
        }
        return null;
    }
}
