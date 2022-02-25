package p110z1;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

/* renamed from: z1.hn */
/* loaded from: classes3.dex */
public abstract class ResultParser {

    /* renamed from: a */
    private static final ResultParser[] f21893a = {new BookmarkDoCoMoResultParser(), new AddressBookDoCoMoResultParser(), new EmailDoCoMoResultParser(), new AddressBookAUResultParser(), new VCardResultParser(), new BizcardResultParser(), new VEventResultParser(), new EmailAddressResultParser(), new SMTPResultParser(), new TelResultParser(), new SMSMMSResultParser(), new SMSTOMMSTOResultParser(), new GeoResultParser(), new WifiResultParser(), new URLTOResultParser(), new URIResultParser(), new ISBNResultParser(), new ProductResultParser(), new ExpandedProductResultParser(), new VINResultParser()};

    /* renamed from: b */
    private static final Pattern f21894b = Pattern.compile("\\d+");

    /* renamed from: c */
    private static final Pattern f21895c = Pattern.compile(C4745bt.f20071b);

    /* renamed from: d */
    private static final Pattern f21896d = Pattern.compile(SimpleComparison.f23609c);

    /* renamed from: e */
    private static final String f21897e = "\ufeff";

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: a */
    public static int m2590a(char c) {
        if (c >= '0' && c <= '9') {
            return c - '0';
        }
        if (c >= 'a' && c <= 'f') {
            return (c - 'a') + 10;
        }
        if (c < 'A' || c > 'F') {
            return -1;
        }
        return (c - 'A') + 10;
    }

    /* renamed from: a */
    public abstract ParsedResult mo2567a(C5422ol olVar);

    /* renamed from: c */
    private static ParsedResult m2577c(C5422ol olVar) {
        for (ResultParser hnVar : f21893a) {
            ParsedResult a = hnVar.mo2567a(olVar);
            if (a != null) {
                return a;
            }
        }
        return new TextParsedResult(olVar.f22707a);
    }

    /* renamed from: a */
    private static void m2584a(String str, StringBuilder sb) {
        if (str != null) {
            sb.append('\n');
            sb.append(str);
        }
    }

    /* renamed from: a */
    private static void m2583a(String[] strArr, StringBuilder sb) {
        if (strArr != null) {
            for (String str : strArr) {
                sb.append('\n');
                sb.append(str);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: b */
    public static String[] m2581b(String str) {
        if (str == null) {
            return null;
        }
        return new String[]{str};
    }

    /* renamed from: a */
    private static String m2586a(String str) {
        int indexOf = str.indexOf(92);
        if (indexOf < 0) {
            return str;
        }
        int length = str.length();
        StringBuilder sb = new StringBuilder(length - 1);
        sb.append(str.toCharArray(), 0, indexOf);
        boolean z = false;
        while (indexOf < length) {
            char charAt = str.charAt(indexOf);
            if (z || charAt != '\\') {
                sb.append(charAt);
                z = false;
            } else {
                z = true;
            }
            indexOf++;
        }
        return sb.toString();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: a */
    public static boolean m2589a(CharSequence charSequence, int i) {
        return charSequence != null && i > 0 && i == charSequence.length() && f21894b.matcher(charSequence).matches();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: a */
    public static boolean m2588a(CharSequence charSequence, int i, int i2) {
        int i3;
        return charSequence != null && i2 > 0 && charSequence.length() >= (i3 = i2 + i) && f21894b.matcher(charSequence.subSequence(i, i3)).matches();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: c */
    public static Map<String, String> m2578c(String str) {
        int indexOf = str.indexOf(63);
        if (indexOf < 0) {
            return null;
        }
        HashMap hashMap = new HashMap(3);
        for (String str2 : f21895c.split(str.substring(indexOf + 1))) {
            String[] split = f21896d.split(str2, 2);
            if (split.length == 2) {
                try {
                    hashMap.put(split[0], m2576d(split[1]));
                } catch (IllegalArgumentException unused) {
                }
            }
        }
        return hashMap;
    }

    /* renamed from: a */
    private static void m2587a(CharSequence charSequence, Map<String, String> map) {
        String[] split = f21896d.split(charSequence, 2);
        if (split.length == 2) {
            try {
                map.put(split[0], m2576d(split[1]));
            } catch (IllegalArgumentException unused) {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: d */
    public static String m2576d(String str) {
        try {
            return URLDecoder.decode(str, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new IllegalStateException(e);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public static String[] m2585a(String str, String str2, char c, boolean z) {
        int length = str2.length();
        ArrayList arrayList = null;
        int i = 0;
        while (i < length) {
            int indexOf = str2.indexOf(str, i);
            if (indexOf < 0) {
                break;
            }
            int length2 = indexOf + str.length();
            boolean z2 = true;
            ArrayList arrayList2 = arrayList;
            int i2 = length2;
            while (z2) {
                int indexOf2 = str2.indexOf(c, i2);
                if (indexOf2 < 0) {
                    i2 = str2.length();
                    z2 = false;
                } else {
                    int i3 = 0;
                    for (int i4 = indexOf2 - 1; i4 >= 0 && str2.charAt(i4) == '\\'; i4--) {
                        i3++;
                    }
                    if (i3 % 2 != 0) {
                        i2 = indexOf2 + 1;
                    } else {
                        if (arrayList2 == null) {
                            arrayList2 = new ArrayList(3);
                        }
                        String a = m2586a(str2.substring(length2, indexOf2));
                        if (z) {
                            a = a.trim();
                        }
                        if (!a.isEmpty()) {
                            arrayList2.add(a);
                        }
                        i2 = indexOf2 + 1;
                        z2 = false;
                    }
                }
            }
            i = i2;
            arrayList = arrayList2;
        }
        if (arrayList == null || arrayList.isEmpty()) {
            return null;
        }
        return (String[]) arrayList.toArray(new String[arrayList.size()]);
    }

    /* renamed from: b */
    private static int m2582b(CharSequence charSequence, int i) {
        int i2 = 0;
        for (int i3 = i - 1; i3 >= 0 && charSequence.charAt(i3) == '\\'; i3--) {
            i2++;
        }
        return i2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: b */
    public static String m2580b(String str, String str2, char c, boolean z) {
        String[] a = m2585a(str, str2, c, z);
        if (a == null) {
            return null;
        }
        return a[0];
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: b */
    public static String m2579b(C5422ol olVar) {
        String str = olVar.f22707a;
        return str.startsWith(f21897e) ? str.substring(1) : str;
    }
}
