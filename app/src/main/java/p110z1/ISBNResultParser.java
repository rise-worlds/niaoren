package p110z1;

/* renamed from: z1.hi */
/* loaded from: classes3.dex */
public final class ISBNResultParser extends ResultParser {
    /* renamed from: c */
    private static ISBNParsedResult m2598c(C5422ol olVar) {
        if (olVar.f22711e != BarcodeFormat.EAN_13) {
            return null;
        }
        String b = m2579b(olVar);
        if (b.length() != 13) {
            return null;
        }
        if (b.startsWith("978") || b.startsWith("979")) {
            return new ISBNParsedResult(b);
        }
        return null;
    }

    @Override // p110z1.ResultParser
    /* renamed from: a */
    public final /* synthetic */ ParsedResult mo2567a(C5422ol olVar) {
        if (olVar.f22711e != BarcodeFormat.EAN_13) {
            return null;
        }
        String b = m2579b(olVar);
        if (b.length() != 13) {
            return null;
        }
        if (b.startsWith("978") || b.startsWith("979")) {
            return new ISBNParsedResult(b);
        }
        return null;
    }
}
