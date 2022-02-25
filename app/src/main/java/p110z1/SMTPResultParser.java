package p110z1;

/* renamed from: z1.hr */
/* loaded from: classes3.dex */
public final class SMTPResultParser extends ResultParser {
    /* renamed from: c */
    private static EmailAddressParsedResult m2566c(C5422ol olVar) {
        String str;
        String str2;
        String b = m2579b(olVar);
        if (!b.startsWith("smtp:") && !b.startsWith("SMTP:")) {
            return null;
        }
        String substring = b.substring(5);
        int indexOf = substring.indexOf(58);
        if (indexOf >= 0) {
            String substring2 = substring.substring(indexOf + 1);
            substring = substring.substring(0, indexOf);
            int indexOf2 = substring2.indexOf(58);
            if (indexOf2 >= 0) {
                String substring3 = substring2.substring(indexOf2 + 1);
                str2 = substring2.substring(0, indexOf2);
                str = substring3;
            } else {
                str = null;
                str2 = substring2;
            }
        } else {
            str2 = null;
            str = null;
        }
        return new EmailAddressParsedResult(new String[]{substring}, null, null, str2, str);
    }

    @Override // p110z1.ResultParser
    /* renamed from: a */
    public final /* synthetic */ ParsedResult mo2567a(C5422ol olVar) {
        String str;
        String str2;
        String b = m2579b(olVar);
        if (!b.startsWith("smtp:") && !b.startsWith("SMTP:")) {
            return null;
        }
        String substring = b.substring(5);
        int indexOf = substring.indexOf(58);
        if (indexOf >= 0) {
            String substring2 = substring.substring(indexOf + 1);
            substring = substring.substring(0, indexOf);
            int indexOf2 = substring2.indexOf(58);
            if (indexOf2 >= 0) {
                String substring3 = substring2.substring(indexOf2 + 1);
                str2 = substring2.substring(0, indexOf2);
                str = substring3;
            } else {
                str = null;
                str2 = substring2;
            }
        } else {
            str2 = null;
            str = null;
        }
        return new EmailAddressParsedResult(new String[]{substring}, null, null, str2, str);
    }
}
