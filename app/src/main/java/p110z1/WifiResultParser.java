package p110z1;

/* renamed from: z1.gt */
/* loaded from: classes3.dex */
public final class WifiResultParser extends ResultParser {
    /* renamed from: c */
    private static WifiParsedResult m2677c(C5422ol olVar) {
        String substring;
        String b;
        String b2 = m2579b(olVar);
        if (!b2.startsWith("WIFI:") || (b = m2580b("S:", (substring = b2.substring(5)), ';', false)) == null || b.isEmpty()) {
            return null;
        }
        String b3 = m2580b("P:", substring, ';', false);
        String b4 = m2580b("T:", substring, ';', false);
        return new WifiParsedResult(b4 == null ? "nopass" : b4, b, b3, Boolean.parseBoolean(m2580b("H:", substring, ';', false)), m2580b("I:", substring, ';', false), m2580b("A:", substring, ';', false), m2580b("E:", substring, ';', false), m2580b("H:", substring, ';', false));
    }

    @Override // p110z1.ResultParser
    /* renamed from: a */
    public final /* synthetic */ ParsedResult mo2567a(C5422ol olVar) {
        String substring;
        String b;
        String b2 = m2579b(olVar);
        if (!b2.startsWith("WIFI:") || (b = m2580b("S:", (substring = b2.substring(5)), ';', false)) == null || b.isEmpty()) {
            return null;
        }
        String b3 = m2580b("P:", substring, ';', false);
        String b4 = m2580b("T:", substring, ';', false);
        return new WifiParsedResult(b4 == null ? "nopass" : b4, b, b3, Boolean.parseBoolean(m2580b("H:", substring, ';', false)), m2580b("I:", substring, ';', false), m2580b("A:", substring, ';', false), m2580b("E:", substring, ';', false), m2580b("H:", substring, ';', false));
    }
}
