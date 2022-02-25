package p110z1;

import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* renamed from: z1.go */
/* loaded from: classes3.dex */
public final class VCardResultParser extends ResultParser {

    /* renamed from: a */
    private static final Pattern f21788a = Pattern.compile("BEGIN:VCARD", 2);

    /* renamed from: b */
    private static final Pattern f21789b = Pattern.compile("\\d{4}-?\\d{2}-?\\d{2}");

    /* renamed from: c */
    private static final Pattern f21790c = Pattern.compile("\r\n[ \t]");

    /* renamed from: d */
    private static final Pattern f21791d = Pattern.compile("\\\\[nN]");

    /* renamed from: e */
    private static final Pattern f21792e = Pattern.compile("\\\\([,;\\\\])");

    /* renamed from: f */
    private static final Pattern f21793f = Pattern.compile(SimpleComparison.f23609c);

    /* renamed from: g */
    private static final Pattern f21794g = Pattern.compile(C4963cj.f20745b);

    /* renamed from: h */
    private static final Pattern f21795h = Pattern.compile("(?<!\\\\);+");

    /* renamed from: i */
    private static final Pattern f21796i = Pattern.compile(",");

    /* renamed from: j */
    private static final Pattern f21797j = Pattern.compile("[;,]");

    /* JADX WARN: Removed duplicated region for block: B:37:0x00fc  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x00fe  */
    /* renamed from: c */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static p110z1.AddressBookParsedResult m2706c(p110z1.C5422ol r24) {
        /*
            Method dump skipped, instructions count: 337
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: p110z1.VCardResultParser.m2706c(z1.ol):z1.gw");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public static List<List<String>> m2713a(CharSequence charSequence, String str, boolean z, boolean z2) {
        String str2;
        String str3;
        boolean z3;
        ArrayList arrayList;
        int indexOf;
        String str4;
        int i;
        int length = str.length();
        int i2 = 0;
        int i3 = 0;
        ArrayList arrayList2 = null;
        while (i3 < length) {
            int i4 = 2;
            Matcher matcher = Pattern.compile("(?:^|\n)" + ((Object) charSequence) + "(?:;([^:]*))?:", 2).matcher(str);
            if (i3 > 0) {
                i3--;
            }
            if (!matcher.find(i3)) {
                break;
            }
            int end = matcher.end(i2);
            String group = matcher.group(1);
            if (group != null) {
                String[] split = f21794g.split(group);
                int length2 = split.length;
                int i5 = 0;
                arrayList = null;
                z3 = false;
                str3 = null;
                str2 = null;
                while (i5 < length2) {
                    String str5 = split[i5];
                    if (arrayList == null) {
                        arrayList = new ArrayList(1);
                    }
                    arrayList.add(str5);
                    String[] split2 = f21793f.split(str5, i4);
                    if (split2.length > 1) {
                        String str6 = split2[0];
                        String str7 = split2[1];
                        if ("ENCODING".equalsIgnoreCase(str6) && "QUOTED-PRINTABLE".equalsIgnoreCase(str7)) {
                            z3 = true;
                        } else if ("CHARSET".equalsIgnoreCase(str6)) {
                            str3 = str7;
                        } else if ("VALUE".equalsIgnoreCase(str6)) {
                            str2 = str7;
                        }
                    }
                    i5++;
                    i4 = 2;
                }
            } else {
                arrayList = null;
                z3 = false;
                str3 = null;
                str2 = null;
            }
            int i6 = end;
            while (true) {
                indexOf = str.indexOf(10, i6);
                if (indexOf < 0) {
                    break;
                }
                if (indexOf < str.length() - 1) {
                    int i7 = indexOf + 1;
                    if (str.charAt(i7) == ' ' || str.charAt(i7) == '\t') {
                        i6 = indexOf + 2;
                    }
                }
                if (!z3) {
                    break;
                }
                if (indexOf <= 0) {
                    i = 2;
                } else if (str.charAt(indexOf - 1) != '=') {
                    i = 2;
                } else {
                    i6 = indexOf + 1;
                }
                if (indexOf < i) {
                    break;
                }
                if (str.charAt(indexOf - 2) != '=') {
                    break;
                }
                i6 = indexOf + 1;
            }
            if (indexOf < 0) {
                i3 = length;
                i2 = 0;
            } else if (indexOf > end) {
                if (arrayList2 == null) {
                    arrayList2 = new ArrayList(1);
                }
                if (indexOf > 0 && str.charAt(indexOf - 1) == '\r') {
                    indexOf--;
                }
                String substring = str.substring(end, indexOf);
                if (z) {
                    substring = substring.trim();
                }
                if (z3) {
                    str4 = m2714a(substring, str3);
                    if (z2) {
                        str4 = f21795h.matcher(str4).replaceAll("\n").trim();
                    }
                } else {
                    if (z2) {
                        substring = f21795h.matcher(substring).replaceAll("\n").trim();
                    }
                    str4 = f21792e.matcher(f21791d.matcher(f21790c.matcher(substring).replaceAll("")).replaceAll("\n")).replaceAll("$1");
                }
                if ("uri".equals(str2)) {
                    try {
                        str4 = URI.create(str4).getSchemeSpecificPart();
                    } catch (IllegalArgumentException unused) {
                    }
                }
                if (arrayList == null) {
                    ArrayList arrayList3 = new ArrayList(1);
                    arrayList3.add(str4);
                    arrayList2.add(arrayList3);
                } else {
                    arrayList.add(0, str4);
                    arrayList2.add(arrayList);
                }
                i3 = indexOf + 1;
                i2 = 0;
            } else {
                i3 = indexOf + 1;
                i2 = 0;
            }
        }
        return arrayList2;
    }

    /* renamed from: a */
    private static String m2714a(CharSequence charSequence, String str) {
        char charAt;
        int length = charSequence.length();
        StringBuilder sb = new StringBuilder(length);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        int i = 0;
        while (i < length) {
            char charAt2 = charSequence.charAt(i);
            if (!(charAt2 == '\n' || charAt2 == '\r')) {
                if (charAt2 != '=') {
                    m2716a(byteArrayOutputStream, str, sb);
                    sb.append(charAt2);
                } else if (!(i >= length - 2 || (charAt = charSequence.charAt(i + 1)) == '\r' || charAt == '\n')) {
                    i += 2;
                    char charAt3 = charSequence.charAt(i);
                    int a = m2590a(charAt);
                    int a2 = m2590a(charAt3);
                    if (a >= 0 && a2 >= 0) {
                        byteArrayOutputStream.write((a << 4) + a2);
                    }
                }
            }
            i++;
        }
        m2716a(byteArrayOutputStream, str, sb);
        return sb.toString();
    }

    /* renamed from: a */
    private static void m2716a(ByteArrayOutputStream byteArrayOutputStream, String str, StringBuilder sb) {
        String str2;
        if (byteArrayOutputStream.size() > 0) {
            byte[] byteArray = byteArrayOutputStream.toByteArray();
            if (str == null) {
                str2 = new String(byteArray, StandardCharsets.UTF_8);
            } else {
                try {
                    str2 = new String(byteArray, str);
                } catch (UnsupportedEncodingException unused) {
                    str2 = new String(byteArray, StandardCharsets.UTF_8);
                }
            }
            byteArrayOutputStream.reset();
            sb.append(str2);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: b */
    public static List<String> m2708b(CharSequence charSequence, String str, boolean z, boolean z2) {
        List<List<String>> a = m2713a(charSequence, str, z, z2);
        if (a == null || a.isEmpty()) {
            return null;
        }
        return a.get(0);
    }

    /* renamed from: a */
    private static String m2710a(List<String> list) {
        if (list == null || list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    /* renamed from: a */
    private static String[] m2711a(Collection<List<String>> collection) {
        if (collection == null || collection.isEmpty()) {
            return null;
        }
        ArrayList arrayList = new ArrayList(collection.size());
        for (List<String> list : collection) {
            String str = list.get(0);
            if (str != null && !str.isEmpty()) {
                arrayList.add(str);
            }
        }
        return (String[]) arrayList.toArray(new String[arrayList.size()]);
    }

    /* renamed from: b */
    private static String[] m2707b(Collection<List<String>> collection) {
        String str;
        if (collection == null || collection.isEmpty()) {
            return null;
        }
        ArrayList arrayList = new ArrayList(collection.size());
        for (List<String> list : collection) {
            String str2 = list.get(0);
            if (str2 != null && !str2.isEmpty()) {
                int i = 1;
                while (true) {
                    if (i >= list.size()) {
                        str = null;
                        break;
                    }
                    String str3 = list.get(i);
                    int indexOf = str3.indexOf(61);
                    if (indexOf < 0) {
                        str = str3;
                        break;
                    } else if ("TYPE".equalsIgnoreCase(str3.substring(0, indexOf))) {
                        str = str3.substring(indexOf + 1);
                        break;
                    } else {
                        i++;
                    }
                }
                arrayList.add(str);
            }
        }
        return (String[]) arrayList.toArray(new String[arrayList.size()]);
    }

    /* renamed from: a */
    private static boolean m2715a(CharSequence charSequence) {
        return charSequence == null || f21789b.matcher(charSequence).matches();
    }

    /* renamed from: a */
    private static void m2712a(Iterable<List<String>> iterable) {
        int indexOf;
        if (iterable != null) {
            for (List<String> list : iterable) {
                String str = list.get(0);
                String[] strArr = new String[5];
                int i = 0;
                int i2 = 0;
                while (i < 4 && (indexOf = str.indexOf(59, i2)) >= 0) {
                    strArr[i] = str.substring(i2, indexOf);
                    i++;
                    i2 = indexOf + 1;
                }
                strArr[i] = str.substring(i2);
                StringBuilder sb = new StringBuilder(100);
                m2709a(strArr, 3, sb);
                m2709a(strArr, 1, sb);
                m2709a(strArr, 2, sb);
                m2709a(strArr, 0, sb);
                m2709a(strArr, 4, sb);
                list.set(0, sb.toString().trim());
            }
        }
    }

    /* renamed from: a */
    private static void m2709a(String[] strArr, int i, StringBuilder sb) {
        if (strArr[i] != null && !strArr[i].isEmpty()) {
            if (sb.length() > 0) {
                sb.append(' ');
            }
            sb.append(strArr[i]);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:37:0x00fc  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x00fe  */
    @Override // p110z1.ResultParser
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final /* synthetic */ p110z1.ParsedResult mo2567a(p110z1.C5422ol r25) {
        /*
            Method dump skipped, instructions count: 337
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: p110z1.VCardResultParser.mo2567a(z1.ol):z1.hj");
    }
}
