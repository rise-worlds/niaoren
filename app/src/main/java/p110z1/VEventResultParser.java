package p110z1;

import com.tencent.smtt.sdk.WebView;
import java.util.List;

/* renamed from: z1.gp */
/* loaded from: classes3.dex */
public final class VEventResultParser extends ResultParser {
    @Override // p110z1.ResultParser
    /* renamed from: a */
    public final /* synthetic */ ParsedResult mo2567a(C5422ol olVar) {
        return m2702c(olVar);
    }

    /* renamed from: c */
    private static CalendarParsedResult m2702c(C5422ol olVar) {
        String[] strArr;
        double d;
        String b = m2579b(olVar);
        if (b.indexOf("BEGIN:VEVENT") < 0) {
            return null;
        }
        String a = m2705a("SUMMARY", b);
        String a2 = m2705a("DTSTART", b);
        if (a2 == null) {
            return null;
        }
        String a3 = m2705a("DTEND", b);
        String a4 = m2705a("DURATION", b);
        String a5 = m2705a("LOCATION", b);
        String a6 = m2704a(m2705a("ORGANIZER", b));
        List<List<String>> a7 = VCardResultParser.m2713a((CharSequence) "ATTENDEE", b, true, false);
        if (a7 == null || a7.isEmpty()) {
            strArr = null;
        } else {
            int size = a7.size();
            strArr = new String[size];
            for (int i = 0; i < size; i++) {
                strArr[i] = a7.get(i).get(0);
            }
        }
        if (strArr != null) {
            for (int i2 = 0; i2 < strArr.length; i2++) {
                strArr[i2] = m2704a(strArr[i2]);
            }
        }
        String a8 = m2705a("DESCRIPTION", b);
        String a9 = m2705a("GEO", b);
        double d2 = Double.NaN;
        if (a9 == null) {
            d = Double.NaN;
        } else {
            int indexOf = a9.indexOf(59);
            if (indexOf < 0) {
                return null;
            }
            try {
                d2 = Double.parseDouble(a9.substring(0, indexOf));
                d = Double.parseDouble(a9.substring(indexOf + 1));
            } catch (NumberFormatException unused) {
                return null;
            }
        }
        try {
            return new CalendarParsedResult(a, a2, a3, a4, a5, a6, strArr, a8, d2, d);
        } catch (IllegalArgumentException unused2) {
            return null;
        }
    }

    /* renamed from: a */
    private static String m2705a(CharSequence charSequence, String str) {
        List<String> b = VCardResultParser.m2708b(charSequence, str, true, false);
        if (b == null || b.isEmpty()) {
            return null;
        }
        return b.get(0);
    }

    /* renamed from: b */
    private static String[] m2703b(CharSequence charSequence, String str) {
        List<List<String>> a = VCardResultParser.m2713a(charSequence, str, true, false);
        if (a == null || a.isEmpty()) {
            return null;
        }
        int size = a.size();
        String[] strArr = new String[size];
        for (int i = 0; i < size; i++) {
            strArr[i] = a.get(i).get(0);
        }
        return strArr;
    }

    /* renamed from: a */
    private static String m2704a(String str) {
        return str != null ? (str.startsWith(WebView.SCHEME_MAILTO) || str.startsWith("MAILTO:")) ? str.substring(7) : str : str;
    }
}
