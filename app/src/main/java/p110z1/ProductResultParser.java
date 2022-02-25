package p110z1;

/* renamed from: z1.hm */
/* loaded from: classes3.dex */
public final class ProductResultParser extends ResultParser {
    /* renamed from: c */
    private static ProductParsedResult m2591c(C5422ol olVar) {
        BarcodeFormat fuVar = olVar.f22711e;
        if (fuVar != BarcodeFormat.UPC_A && fuVar != BarcodeFormat.UPC_E && fuVar != BarcodeFormat.EAN_8 && fuVar != BarcodeFormat.EAN_13) {
            return null;
        }
        String b = m2579b(olVar);
        if (!m2589a(b, b.length())) {
            return null;
        }
        return new ProductParsedResult(b, (fuVar == BarcodeFormat.UPC_E && b.length() == 8) ? UPCEReader.m2123b(b) : b);
    }

    @Override // p110z1.ResultParser
    /* renamed from: a */
    public final /* synthetic */ ParsedResult mo2567a(C5422ol olVar) {
        BarcodeFormat fuVar = olVar.f22711e;
        if (fuVar != BarcodeFormat.UPC_A && fuVar != BarcodeFormat.UPC_E && fuVar != BarcodeFormat.EAN_8 && fuVar != BarcodeFormat.EAN_13) {
            return null;
        }
        String b = m2579b(olVar);
        if (!m2589a(b, b.length())) {
            return null;
        }
        return new ProductParsedResult(b, (fuVar == BarcodeFormat.UPC_E && b.length() == 8) ? UPCEReader.m2123b(b) : b);
    }
}
