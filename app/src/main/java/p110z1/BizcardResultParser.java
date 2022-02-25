package p110z1;

import java.util.ArrayList;

/* renamed from: z1.gx */
/* loaded from: classes3.dex */
public final class BizcardResultParser extends AbstractDoCoMoResultParser {
    /* renamed from: c */
    private static AddressBookParsedResult m2654c(C5422ol olVar) {
        String b = m2579b(olVar);
        if (!b.startsWith("BIZCARD:")) {
            return null;
        }
        String a = m2728a("N:", b, true);
        String a2 = m2728a("X:", b, true);
        if (a == null) {
            a = a2;
        } else if (a2 != null) {
            a = a + ' ' + a2;
        }
        String a3 = m2728a("T:", b, true);
        String a4 = m2728a("C:", b, true);
        String[] a5 = m2729a("A:", b);
        String a6 = m2728a("B:", b, true);
        String a7 = m2728a("M:", b, true);
        String a8 = m2728a("F:", b, true);
        String a9 = m2728a("E:", b, true);
        String[] b2 = m2581b(a);
        ArrayList arrayList = new ArrayList(3);
        if (a6 != null) {
            arrayList.add(a6);
        }
        if (a7 != null) {
            arrayList.add(a7);
        }
        if (a8 != null) {
            arrayList.add(a8);
        }
        int size = arrayList.size();
        return new AddressBookParsedResult(b2, null, null, size == 0 ? null : (String[]) arrayList.toArray(new String[size]), null, m2581b(a9), null, null, null, a5, null, a4, null, a3, null, null);
    }

    /* renamed from: a */
    private static String[] m2656a(String str, String str2, String str3) {
        ArrayList arrayList = new ArrayList(3);
        if (str != null) {
            arrayList.add(str);
        }
        if (str2 != null) {
            arrayList.add(str2);
        }
        if (str3 != null) {
            arrayList.add(str3);
        }
        int size = arrayList.size();
        if (size == 0) {
            return null;
        }
        return (String[]) arrayList.toArray(new String[size]);
    }

    /* renamed from: b */
    private static String m2655b(String str, String str2) {
        if (str == null) {
            return str2;
        }
        if (str2 == null) {
            return str;
        }
        return str + ' ' + str2;
    }

    @Override // p110z1.ResultParser
    /* renamed from: a */
    public final /* synthetic */ ParsedResult mo2567a(C5422ol olVar) {
        String b = m2579b(olVar);
        if (!b.startsWith("BIZCARD:")) {
            return null;
        }
        String a = m2728a("N:", b, true);
        String a2 = m2728a("X:", b, true);
        if (a == null) {
            a = a2;
        } else if (a2 != null) {
            a = a + ' ' + a2;
        }
        String a3 = m2728a("T:", b, true);
        String a4 = m2728a("C:", b, true);
        String[] a5 = m2729a("A:", b);
        String a6 = m2728a("B:", b, true);
        String a7 = m2728a("M:", b, true);
        String a8 = m2728a("F:", b, true);
        String a9 = m2728a("E:", b, true);
        String[] b2 = m2581b(a);
        ArrayList arrayList = new ArrayList(3);
        if (a6 != null) {
            arrayList.add(a6);
        }
        if (a7 != null) {
            arrayList.add(a7);
        }
        if (a8 != null) {
            arrayList.add(a8);
        }
        int size = arrayList.size();
        return new AddressBookParsedResult(b2, null, null, size == 0 ? null : (String[]) arrayList.toArray(new String[size]), null, m2581b(a9), null, null, null, a5, null, a4, null, a3, null, null);
    }
}
