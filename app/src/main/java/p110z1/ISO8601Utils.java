package p110z1;

import java.text.ParseException;
import java.text.ParsePosition;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.TimeZone;
import org.apache.commons.p105io.FilenameUtils;
import org.apache.tools.tar.TarConstants;

/* renamed from: z1.qz */
/* loaded from: classes3.dex */
public class ISO8601Utils {

    /* renamed from: a */
    private static final String f23049a = "UTC";

    /* renamed from: b */
    private static final TimeZone f23050b = TimeZone.getTimeZone(f23049a);

    /* renamed from: a */
    public static String m1228a(Date date) {
        return m1226a(date, false, f23050b);
    }

    /* renamed from: a */
    public static String m1227a(Date date, boolean z) {
        return m1226a(date, z, f23050b);
    }

    /* renamed from: a */
    public static String m1226a(Date date, boolean z, TimeZone timeZone) {
        GregorianCalendar gregorianCalendar = new GregorianCalendar(timeZone, Locale.US);
        gregorianCalendar.setTime(date);
        StringBuilder sb = new StringBuilder(19 + (z ? 4 : 0) + (timeZone.getRawOffset() == 0 ? 1 : 6));
        m1229a(sb, gregorianCalendar.get(1), 4);
        char c = '-';
        sb.append('-');
        m1229a(sb, gregorianCalendar.get(2) + 1, 2);
        sb.append('-');
        m1229a(sb, gregorianCalendar.get(5), 2);
        sb.append('T');
        m1229a(sb, gregorianCalendar.get(11), 2);
        sb.append(':');
        m1229a(sb, gregorianCalendar.get(12), 2);
        sb.append(':');
        m1229a(sb, gregorianCalendar.get(13), 2);
        if (z) {
            sb.append(FilenameUtils.EXTENSION_SEPARATOR);
            m1229a(sb, gregorianCalendar.get(14), 3);
        }
        int offset = timeZone.getOffset(gregorianCalendar.getTimeInMillis());
        if (offset != 0) {
            int i = offset / 60000;
            int abs = Math.abs(i / 60);
            int abs2 = Math.abs(i % 60);
            if (offset >= 0) {
                c = '+';
            }
            sb.append(c);
            m1229a(sb, abs, 2);
            sb.append(':');
            m1229a(sb, abs2, 2);
        } else {
            sb.append('Z');
        }
        return sb.toString();
    }

    /* renamed from: a */
    public static Date m1230a(String str, ParsePosition parsePosition) throws ParseException {
        String str2;
        int i;
        int i2;
        int i3;
        int i4;
        int i5;
        int i6;
        TimeZone timeZone;
        char charAt;
        try {
            int index = parsePosition.getIndex();
            int i7 = index + 4;
            int a = m1231a(str, index, i7);
            if (m1232a(str, i7, '-')) {
                i7++;
            }
            int i8 = i7 + 2;
            int a2 = m1231a(str, i7, i8);
            if (m1232a(str, i8, '-')) {
                i8++;
            }
            int i9 = i8 + 2;
            int a3 = m1231a(str, i8, i9);
            boolean a4 = m1232a(str, i9, 'T');
            if (a4 || str.length() > i9) {
                if (a4) {
                    int i10 = i9 + 1;
                    int i11 = i10 + 2;
                    i5 = m1231a(str, i10, i11);
                    if (m1232a(str, i11, ':')) {
                        i11++;
                    }
                    i2 = i11 + 2;
                    i4 = m1231a(str, i11, i2);
                    if (m1232a(str, i2, ':')) {
                        i2++;
                    }
                    if (str.length() <= i2 || (charAt = str.charAt(i2)) == 'Z' || charAt == '+' || charAt == '-') {
                        i3 = 0;
                        i = 0;
                    } else {
                        int i12 = i2 + 2;
                        int a5 = m1231a(str, i2, i12);
                        i = 59;
                        if (a5 <= 59 || a5 >= 63) {
                            i = a5;
                        }
                        if (m1232a(str, i12, (char) FilenameUtils.EXTENSION_SEPARATOR)) {
                            int i13 = i12 + 1;
                            i2 = m1233a(str, i13 + 1);
                            int min = Math.min(i2, i13 + 3);
                            i3 = m1231a(str, i13, min);
                            switch (min - i13) {
                                case 1:
                                    i3 *= 100;
                                    break;
                                case 2:
                                    i3 *= 10;
                                    break;
                            }
                        } else {
                            i2 = i12;
                            i3 = 0;
                        }
                    }
                } else {
                    i2 = i9;
                    i5 = 0;
                    i4 = 0;
                    i3 = 0;
                    i = 0;
                }
                if (str.length() > i2) {
                    char charAt2 = str.charAt(i2);
                    if (charAt2 == 'Z') {
                        timeZone = f23050b;
                        i6 = i2 + 1;
                    } else {
                        if (!(charAt2 == '+' || charAt2 == '-')) {
                            throw new IndexOutOfBoundsException("Invalid time zone indicator '" + charAt2 + "'");
                        }
                        String substring = str.substring(i2);
                        if (substring.length() < 5) {
                            substring = substring + TarConstants.VERSION_POSIX;
                        }
                        i6 = i2 + substring.length();
                        if (!"+0000".equals(substring) && !"+00:00".equals(substring)) {
                            String str3 = "GMT" + substring;
                            TimeZone timeZone2 = TimeZone.getTimeZone(str3);
                            String id = timeZone2.getID();
                            if (!id.equals(str3) && !id.replace(":", "").equals(str3)) {
                                throw new IndexOutOfBoundsException("Mismatching time zone indicator: " + str3 + " given, resolves to " + timeZone2.getID());
                            }
                            timeZone = timeZone2;
                        }
                        timeZone = f23050b;
                    }
                    GregorianCalendar gregorianCalendar = new GregorianCalendar(timeZone);
                    gregorianCalendar.setLenient(false);
                    gregorianCalendar.set(1, a);
                    gregorianCalendar.set(2, a2 - 1);
                    gregorianCalendar.set(5, a3);
                    gregorianCalendar.set(11, i5);
                    gregorianCalendar.set(12, i4);
                    gregorianCalendar.set(13, i);
                    gregorianCalendar.set(14, i3);
                    parsePosition.setIndex(i6);
                    return gregorianCalendar.getTime();
                }
                throw new IllegalArgumentException("No time zone indicator");
            }
            GregorianCalendar gregorianCalendar2 = new GregorianCalendar(a, a2 - 1, a3);
            parsePosition.setIndex(i9);
            return gregorianCalendar2.getTime();
        } catch (IndexOutOfBoundsException | NumberFormatException | IllegalArgumentException e) {
            if (str == null) {
                str2 = null;
            } else {
                str2 = Typography.f21049a + str + Typography.f21049a;
            }
            String message = e.getMessage();
            if (message == null || message.isEmpty()) {
                message = "(" + e.getClass().getName() + ")";
            }
            ParseException parseException = new ParseException("Failed to parse date [" + str2 + "]: " + message, parsePosition.getIndex());
            parseException.initCause(e);
            throw parseException;
        }
    }

    /* renamed from: a */
    private static boolean m1232a(String str, int i, char c) {
        return i < str.length() && str.charAt(i) == c;
    }

    /* renamed from: a */
    private static int m1231a(String str, int i, int i2) throws NumberFormatException {
        int i3;
        int i4;
        if (i < 0 || i2 > str.length() || i > i2) {
            throw new NumberFormatException(str);
        }
        if (i < i2) {
            i4 = i + 1;
            int digit = Character.digit(str.charAt(i), 10);
            if (digit >= 0) {
                i3 = -digit;
            } else {
                throw new NumberFormatException("Invalid number: " + str.substring(i, i2));
            }
        } else {
            i4 = i;
            i3 = 0;
        }
        while (i4 < i2) {
            i4++;
            int digit2 = Character.digit(str.charAt(i4), 10);
            if (digit2 >= 0) {
                i3 = (i3 * 10) - digit2;
            } else {
                throw new NumberFormatException("Invalid number: " + str.substring(i, i2));
            }
        }
        return -i3;
    }

    /* renamed from: a */
    private static void m1229a(StringBuilder sb, int i, int i2) {
        String num = Integer.toString(i);
        for (int length = i2 - num.length(); length > 0; length--) {
            sb.append('0');
        }
        sb.append(num);
    }

    /* renamed from: a */
    private static int m1233a(String str, int i) {
        while (i < str.length()) {
            char charAt = str.charAt(i);
            if (charAt < '0' || charAt > '9') {
                return i;
            }
            i++;
        }
        return str.length();
    }
}
