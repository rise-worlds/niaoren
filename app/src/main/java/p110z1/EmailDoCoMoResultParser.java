package p110z1;

import java.util.regex.Pattern;

/* renamed from: z1.hc */
/* loaded from: classes3.dex */
public final class EmailDoCoMoResultParser extends AbstractDoCoMoResultParser {

    /* renamed from: a */
    private static final Pattern f21853a = Pattern.compile("[a-zA-Z0-9@.!#$%&'*+\\-/=?^_`{|}~]+");

    /* renamed from: c */
    private static EmailAddressParsedResult m2626c(C5422ol olVar) {
        String[] a;
        String b = m2579b(olVar);
        if (!b.startsWith("MATMSG:") || (a = m2729a("TO:", b)) == null) {
            return null;
        }
        for (String str : a) {
            if (!m2627a(str)) {
                return null;
            }
        }
        return new EmailAddressParsedResult(a, null, null, m2728a("SUB:", b, false), m2728a("BODY:", b, false));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public static boolean m2627a(String str) {
        return str != null && f21853a.matcher(str).matches() && str.indexOf(64) >= 0;
    }

    @Override // p110z1.ResultParser
    /* renamed from: a */
    public final /* synthetic */ ParsedResult mo2567a(C5422ol olVar) {
        String[] a;
        String b = m2579b(olVar);
        if (!b.startsWith("MATMSG:") || (a = m2729a("TO:", b)) == null) {
            return null;
        }
        for (String str : a) {
            if (!m2627a(str)) {
                return null;
            }
        }
        return new EmailAddressParsedResult(a, null, null, m2728a("SUB:", b, false), m2728a("BODY:", b, false));
    }
}
