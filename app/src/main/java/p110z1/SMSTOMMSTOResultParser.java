package p110z1;

/* renamed from: z1.hq */
/* loaded from: classes3.dex */
public final class SMSTOMMSTOResultParser extends ResultParser {
    /* renamed from: c */
    private static SMSParsedResult m2568c(C5422ol olVar) {
        String b = m2579b(olVar);
        String str = null;
        if (!b.startsWith("smsto:") && !b.startsWith("SMSTO:") && !b.startsWith("mmsto:") && !b.startsWith("MMSTO:")) {
            return null;
        }
        String substring = b.substring(6);
        int indexOf = substring.indexOf(58);
        if (indexOf >= 0) {
            str = substring.substring(indexOf + 1);
            substring = substring.substring(0, indexOf);
        }
        return new SMSParsedResult(substring, str);
    }

    @Override // p110z1.ResultParser
    /* renamed from: a */
    public final /* synthetic */ ParsedResult mo2567a(C5422ol olVar) {
        String b = m2579b(olVar);
        String str = null;
        if (!b.startsWith("smsto:") && !b.startsWith("SMSTO:") && !b.startsWith("mmsto:") && !b.startsWith("MMSTO:")) {
            return null;
        }
        String substring = b.substring(6);
        int indexOf = substring.indexOf(58);
        if (indexOf >= 0) {
            str = substring.substring(indexOf + 1);
            substring = substring.substring(0, indexOf);
        }
        return new SMSParsedResult(substring, str);
    }
}
