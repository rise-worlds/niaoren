package p110z1;

/* renamed from: z1.gv */
/* loaded from: classes3.dex */
public final class AddressBookDoCoMoResultParser extends AbstractDoCoMoResultParser {
    /* renamed from: c */
    private static AddressBookParsedResult m2673c(C5422ol olVar) {
        String[] a;
        int indexOf;
        String b = m2579b(olVar);
        if (!b.startsWith("MECARD:") || (a = m2729a("N:", b)) == null) {
            return null;
        }
        String str = a[0];
        if (str.indexOf(44) >= 0) {
            str = str.substring(indexOf + 1) + ' ' + str.substring(0, indexOf);
        }
        String a2 = m2728a("SOUND:", b, true);
        String[] a3 = m2729a("TEL:", b);
        String[] a4 = m2729a("EMAIL:", b);
        String a5 = m2728a("NOTE:", b, false);
        String[] a6 = m2729a("ADR:", b);
        String a7 = m2728a("BDAY:", b, true);
        return new AddressBookParsedResult(m2581b(str), null, a2, a3, null, a4, null, null, a5, a6, null, m2728a("ORG:", b, true), !m2589a(a7, 8) ? null : a7, null, m2729a("URL:", b), null);
    }

    /* renamed from: a */
    private static String m2674a(String str) {
        int indexOf = str.indexOf(44);
        if (indexOf < 0) {
            return str;
        }
        return str.substring(indexOf + 1) + ' ' + str.substring(0, indexOf);
    }

    @Override // p110z1.ResultParser
    /* renamed from: a */
    public final /* synthetic */ ParsedResult mo2567a(C5422ol olVar) {
        String[] a;
        int indexOf;
        String b = m2579b(olVar);
        if (!b.startsWith("MECARD:") || (a = m2729a("N:", b)) == null) {
            return null;
        }
        String str = a[0];
        if (str.indexOf(44) >= 0) {
            str = str.substring(indexOf + 1) + ' ' + str.substring(0, indexOf);
        }
        String a2 = m2728a("SOUND:", b, true);
        String[] a3 = m2729a("TEL:", b);
        String[] a4 = m2729a("EMAIL:", b);
        String a5 = m2728a("NOTE:", b, false);
        String[] a6 = m2729a("ADR:", b);
        String a7 = m2728a("BDAY:", b, true);
        return new AddressBookParsedResult(m2581b(str), null, a2, a3, null, a4, null, null, a5, a6, null, m2728a("ORG:", b, true), !m2589a(a7, 8) ? null : a7, null, m2729a("URL:", b), null);
    }
}
