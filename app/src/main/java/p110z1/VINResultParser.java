package p110z1;

import com.cyjh.ddy.media.media.ActionCode;
import java.util.regex.Pattern;

/* renamed from: z1.gr */
/* loaded from: classes3.dex */
public final class VINResultParser extends ResultParser {

    /* renamed from: a */
    private static final Pattern f21807a = Pattern.compile("[IOQ]");

    /* renamed from: b */
    private static final Pattern f21808b = Pattern.compile("[A-Z0-9]{17}");

    @Override // p110z1.ResultParser
    /* renamed from: a */
    public final /* synthetic */ ParsedResult mo2567a(C5422ol olVar) {
        return m2686c(olVar);
    }

    /* renamed from: a */
    private static boolean m2691a(CharSequence charSequence) {
        char c;
        int i;
        int i2;
        int i3 = 0;
        int i4 = 0;
        while (i3 < charSequence.length()) {
            int i5 = i3 + 1;
            if (i5 > 0 && i5 <= 7) {
                i = 9 - i5;
            } else if (i5 == 8) {
                i = 10;
            } else if (i5 == 9) {
                i = 0;
            } else if (i5 < 10 || i5 > 17) {
                throw new IllegalArgumentException();
            } else {
                i = 19 - i5;
            }
            char charAt = charSequence.charAt(i3);
            if (charAt >= 'A' && charAt <= 'I') {
                i2 = (charAt - 'A') + 1;
            } else if (charAt >= 'J' && charAt <= 'R') {
                i2 = (charAt - 'J') + 1;
            } else if (charAt >= 'S' && charAt <= 'Z') {
                i2 = (charAt - 'S') + 2;
            } else if (charAt < '0' || charAt > '9') {
                throw new IllegalArgumentException();
            } else {
                i2 = charAt - '0';
            }
            i4 += i * i2;
            i3 = i5;
        }
        char charAt2 = charSequence.charAt(8);
        int i6 = i4 % 11;
        if (i6 < 10) {
            c = (char) (i6 + 48);
        } else if (i6 == 10) {
            c = 'X';
        } else {
            throw new IllegalArgumentException();
        }
        return charAt2 == c;
    }

    /* renamed from: b */
    private static int m2690b(char c) {
        if (c >= 'A' && c <= 'I') {
            return (c - 'A') + 1;
        }
        if (c >= 'J' && c <= 'R') {
            return (c - 'J') + 1;
        }
        if (c >= 'S' && c <= 'Z') {
            return (c - 'S') + 2;
        }
        if (c >= '0' && c <= '9') {
            return c - '0';
        }
        throw new IllegalArgumentException();
    }

    /* renamed from: a */
    private static int m2692a(int i) {
        if (i > 0 && i <= 7) {
            return 9 - i;
        }
        if (i == 8) {
            return 10;
        }
        if (i == 9) {
            return 0;
        }
        if (i >= 10 && i <= 17) {
            return 19 - i;
        }
        throw new IllegalArgumentException();
    }

    /* renamed from: b */
    private static char m2689b(int i) {
        if (i < 10) {
            return (char) (i + 48);
        }
        if (i == 10) {
            return 'X';
        }
        throw new IllegalArgumentException();
    }

    /* renamed from: c */
    private static int m2687c(char c) {
        if (c >= 'E' && c <= 'H') {
            return (c - 'E') + 1984;
        }
        if (c >= 'J' && c <= 'N') {
            return (c - 'J') + 1988;
        }
        if (c == 'P') {
            return 1993;
        }
        if (c >= 'R' && c <= 'T') {
            return (c - 'R') + 1994;
        }
        if (c >= 'V' && c <= 'Y') {
            return (c - 'V') + 1997;
        }
        if (c >= '1' && c <= '9') {
            return (c - '1') + ActionCode.CtrlConnectRefuse_2001;
        }
        if (c >= 'A' && c <= 'D') {
            return (c - 'A') + 2010;
        }
        throw new IllegalArgumentException();
    }

    /* renamed from: b */
    private static String m2688b(CharSequence charSequence) {
        char charAt = charSequence.charAt(0);
        char charAt2 = charSequence.charAt(1);
        if (charAt != '9') {
            if (charAt != 'S') {
                if (charAt != 'Z') {
                    switch (charAt) {
                        case '1':
                        case '4':
                        case '5':
                            return "US";
                        case '2':
                            return "CA";
                        case '3':
                            if (charAt2 < 'A' || charAt2 > 'W') {
                                return null;
                            }
                            return "MX";
                        default:
                            switch (charAt) {
                                case 'J':
                                    if (charAt2 < 'A' || charAt2 > 'T') {
                                        return null;
                                    }
                                    return "JP";
                                case 'K':
                                    if (charAt2 < 'L' || charAt2 > 'R') {
                                        return null;
                                    }
                                    return "KO";
                                case 'L':
                                    return "CN";
                                case 'M':
                                    if (charAt2 < 'A' || charAt2 > 'E') {
                                        return null;
                                    }
                                    return "IN";
                                default:
                                    switch (charAt) {
                                        case 'V':
                                            if (charAt2 >= 'F' && charAt2 <= 'R') {
                                                return "FR";
                                            }
                                            if (charAt2 < 'S' || charAt2 > 'W') {
                                                return null;
                                            }
                                            return "ES";
                                        case 'W':
                                            return "DE";
                                        case 'X':
                                            if (charAt2 == '0') {
                                                return "RU";
                                            }
                                            if (charAt2 < '3' || charAt2 > '9') {
                                                return null;
                                            }
                                            return "RU";
                                        default:
                                            return null;
                                    }
                            }
                    }
                } else if (charAt2 < 'A' || charAt2 > 'R') {
                    return null;
                } else {
                    return "IT";
                }
            } else if (charAt2 >= 'A' && charAt2 <= 'M') {
                return "UK";
            } else {
                if (charAt2 < 'N' || charAt2 > 'T') {
                    return null;
                }
                return "DE";
            }
        } else if (charAt2 >= 'A' && charAt2 <= 'E') {
            return "BR";
        } else {
            if (charAt2 < '3' || charAt2 > '9') {
                return null;
            }
            return "BR";
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Removed duplicated region for block: B:114:0x016e  */
    /* JADX WARN: Removed duplicated region for block: B:118:0x017b  */
    /* JADX WARN: Removed duplicated region for block: B:123:0x018a  */
    /* JADX WARN: Removed duplicated region for block: B:124:0x0191  */
    /* renamed from: c */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static p110z1.VINParsedResult m2686c(p110z1.C5422ol r19) {
        /*
            Method dump skipped, instructions count: 528
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: p110z1.VINResultParser.m2686c(z1.ol):z1.gq");
    }
}
