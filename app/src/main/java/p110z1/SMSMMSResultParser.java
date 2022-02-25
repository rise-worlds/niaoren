package p110z1;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

/* renamed from: z1.ho */
/* loaded from: classes3.dex */
public final class SMSMMSResultParser extends ResultParser {
    /* renamed from: c */
    private static SMSParsedResult m2574c(C5422ol olVar) {
        String str;
        String str2;
        String b = m2579b(olVar);
        String str3 = null;
        if (!b.startsWith("sms:") && !b.startsWith("SMS:") && !b.startsWith("mms:") && !b.startsWith("MMS:")) {
            return null;
        }
        Map<String, String> c = m2578c(b);
        boolean z = false;
        if (c == null || c.isEmpty()) {
            str = null;
        } else {
            str3 = c.get("subject");
            str = c.get("body");
            z = true;
        }
        int indexOf = b.indexOf(63, 4);
        if (indexOf < 0 || !z) {
            str2 = b.substring(4);
        } else {
            str2 = b.substring(4, indexOf);
        }
        int i = -1;
        ArrayList arrayList = new ArrayList(1);
        ArrayList arrayList2 = new ArrayList(1);
        while (true) {
            int i2 = i + 1;
            int indexOf2 = str2.indexOf(44, i2);
            if (indexOf2 > i) {
                m2575a(arrayList, arrayList2, str2.substring(i2, indexOf2));
                i = indexOf2;
            } else {
                m2575a(arrayList, arrayList2, str2.substring(i2));
                return new SMSParsedResult((String[]) arrayList.toArray(new String[arrayList.size()]), (String[]) arrayList2.toArray(new String[arrayList2.size()]), str3, str);
            }
        }
    }

    /* renamed from: a */
    private static void m2575a(Collection<String> collection, Collection<String> collection2, String str) {
        int indexOf = str.indexOf(59);
        String str2 = null;
        if (indexOf < 0) {
            collection.add(str);
            collection2.add(null);
            return;
        }
        collection.add(str.substring(0, indexOf));
        String substring = str.substring(indexOf + 1);
        if (substring.startsWith("via=")) {
            str2 = substring.substring(4);
        }
        collection2.add(str2);
    }

    @Override // p110z1.ResultParser
    /* renamed from: a */
    public final /* synthetic */ ParsedResult mo2567a(C5422ol olVar) {
        String str;
        String str2;
        String b = m2579b(olVar);
        String str3 = null;
        if (!b.startsWith("sms:") && !b.startsWith("SMS:") && !b.startsWith("mms:") && !b.startsWith("MMS:")) {
            return null;
        }
        Map<String, String> c = m2578c(b);
        boolean z = false;
        if (c == null || c.isEmpty()) {
            str = null;
        } else {
            str3 = c.get("subject");
            str = c.get("body");
            z = true;
        }
        int indexOf = b.indexOf(63, 4);
        if (indexOf < 0 || !z) {
            str2 = b.substring(4);
        } else {
            str2 = b.substring(4, indexOf);
        }
        int i = -1;
        ArrayList arrayList = new ArrayList(1);
        ArrayList arrayList2 = new ArrayList(1);
        while (true) {
            int i2 = i + 1;
            int indexOf2 = str2.indexOf(44, i2);
            if (indexOf2 > i) {
                m2575a(arrayList, arrayList2, str2.substring(i2, indexOf2));
                i = indexOf2;
            } else {
                m2575a(arrayList, arrayList2, str2.substring(i2));
                return new SMSParsedResult((String[]) arrayList.toArray(new String[arrayList.size()]), (String[]) arrayList2.toArray(new String[arrayList2.size()]), str3, str);
            }
        }
    }
}
