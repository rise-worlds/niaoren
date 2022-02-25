package p110z1;

import java.util.ArrayList;

/* renamed from: z1.gu */
/* loaded from: classes3.dex */
public final class AddressBookAUResultParser extends ResultParser {
    /* renamed from: c */
    private static AddressBookParsedResult m2675c(C5422ol olVar) {
        String b = m2579b(olVar);
        String[] strArr = null;
        if (!b.contains("MEMORY") || !b.contains("\r\n")) {
            return null;
        }
        String b2 = m2580b("NAME1:", b, '\r', true);
        String b3 = m2580b("NAME2:", b, '\r', true);
        String[] a = m2676a("TEL", b);
        String[] a2 = m2676a("MAIL", b);
        String b4 = m2580b("MEMORY:", b, '\r', false);
        String b5 = m2580b("ADD:", b, '\r', true);
        if (b5 != null) {
            strArr = new String[]{b5};
        }
        return new AddressBookParsedResult(m2581b(b2), null, b3, a, null, a2, null, null, b4, strArr, null, null, null, null, null, null);
    }

    /* renamed from: a */
    private static String[] m2676a(String str, String str2) {
        ArrayList arrayList = null;
        for (int i = 1; i <= 3; i++) {
            String b = m2580b(str + i + ':', str2, '\r', true);
            if (b == null) {
                break;
            }
            if (arrayList == null) {
                arrayList = new ArrayList(3);
            }
            arrayList.add(b);
        }
        if (arrayList == null) {
            return null;
        }
        return (String[]) arrayList.toArray(new String[arrayList.size()]);
    }

    @Override // p110z1.ResultParser
    /* renamed from: a */
    public final /* synthetic */ ParsedResult mo2567a(C5422ol olVar) {
        String b = m2579b(olVar);
        String[] strArr = null;
        if (!b.contains("MEMORY") || !b.contains("\r\n")) {
            return null;
        }
        String b2 = m2580b("NAME1:", b, '\r', true);
        String b3 = m2580b("NAME2:", b, '\r', true);
        String[] a = m2676a("TEL", b);
        String[] a2 = m2676a("MAIL", b);
        String b4 = m2580b("MEMORY:", b, '\r', false);
        String b5 = m2580b("ADD:", b, '\r', true);
        if (b5 != null) {
            strArr = new String[]{b5};
        }
        return new AddressBookParsedResult(m2581b(b2), null, b3, a, null, a2, null, null, b4, strArr, null, null, null, null, null, null);
    }
}
