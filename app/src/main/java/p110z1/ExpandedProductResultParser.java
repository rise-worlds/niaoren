package p110z1;

/* renamed from: z1.he */
/* loaded from: classes3.dex */
public final class ExpandedProductResultParser extends ResultParser {
    /* renamed from: a */
    private static String m2608a(int i, String str) {
        if (str.charAt(i) != '(') {
            return null;
        }
        String substring = str.substring(i + 1);
        StringBuilder sb = new StringBuilder();
        for (int i2 = 0; i2 < substring.length(); i2++) {
            char charAt = substring.charAt(i2);
            if (charAt == ')') {
                return sb.toString();
            }
            if (charAt < '0' || charAt > '9') {
                return null;
            }
            sb.append(charAt);
        }
        return sb.toString();
    }

    /* renamed from: b */
    private static String m2607b(int i, String str) {
        StringBuilder sb = new StringBuilder();
        String substring = str.substring(i);
        for (int i2 = 0; i2 < substring.length(); i2++) {
            char charAt = substring.charAt(i2);
            if (charAt == '(') {
                if (m2608a(i2, substring) != null) {
                    break;
                }
                sb.append('(');
            } else {
                sb.append(charAt);
            }
        }
        return sb.toString();
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Removed duplicated region for block: B:147:0x0212 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:148:0x0230 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:149:0x023f A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:150:0x024d A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:151:0x025b A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:152:0x0263 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:153:0x026b A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:154:0x0273 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:155:0x027b A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:156:0x0283 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:157:0x028b A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:160:0x0206 A[SYNTHETIC] */
    /* renamed from: c */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static p110z1.ExpandedProductParsedResult m2606c(p110z1.C5422ol r23) {
        /*
            Method dump skipped, instructions count: 832
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: p110z1.ExpandedProductResultParser.m2606c(z1.ol):z1.hd");
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Removed duplicated region for block: B:147:0x0212 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:148:0x0230 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:149:0x023f A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:150:0x024d A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:151:0x025b A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:152:0x0263 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:153:0x026b A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:154:0x0273 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:155:0x027b A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:156:0x0283 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:157:0x028b A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:160:0x0206 A[SYNTHETIC] */
    @Override // p110z1.ResultParser
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final /* synthetic */ p110z1.ParsedResult mo2567a(p110z1.C5422ol r24) {
        /*
            Method dump skipped, instructions count: 832
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: p110z1.ExpandedProductResultParser.mo2567a(z1.ol):z1.hj");
    }
}
