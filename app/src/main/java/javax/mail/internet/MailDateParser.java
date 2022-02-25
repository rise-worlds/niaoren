package javax.mail.internet;

import java.text.ParseException;

/* compiled from: MailDateFormat.java */
/* loaded from: classes2.dex */
class MailDateParser {
    int index = 0;
    char[] orig;

    public MailDateParser(char[] cArr) {
        this.orig = null;
        this.orig = cArr;
    }

    public void skipUntilNumber() throws ParseException {
        while (true) {
            try {
                switch (this.orig[this.index]) {
                    case '0':
                    case '1':
                    case '2':
                    case '3':
                    case '4':
                    case '5':
                    case '6':
                    case '7':
                    case '8':
                    case '9':
                        return;
                    default:
                        this.index++;
                }
            } catch (ArrayIndexOutOfBoundsException unused) {
                throw new ParseException("No Number Found", this.index);
            }
        }
    }

    public void skipWhiteSpace() {
        int length = this.orig.length;
        while (true) {
            int i = this.index;
            if (i < length) {
                char c = this.orig[i];
                if (!(c == '\r' || c == ' ')) {
                    switch (c) {
                        case '\t':
                        case '\n':
                            break;
                        default:
                            return;
                    }
                }
                this.index++;
            } else {
                return;
            }
        }
    }

    public int peekChar() throws ParseException {
        int i = this.index;
        char[] cArr = this.orig;
        if (i < cArr.length) {
            return cArr[i];
        }
        throw new ParseException("No more characters", i);
    }

    public void skipChar(char c) throws ParseException {
        int i = this.index;
        char[] cArr = this.orig;
        if (i >= cArr.length) {
            throw new ParseException("No more characters", i);
        } else if (cArr[i] == c) {
            this.index = i + 1;
        } else {
            throw new ParseException("Wrong char", i);
        }
    }

    public boolean skipIfChar(char c) throws ParseException {
        int i = this.index;
        char[] cArr = this.orig;
        if (i >= cArr.length) {
            throw new ParseException("No more characters", i);
        } else if (cArr[i] != c) {
            return false;
        } else {
            this.index = i + 1;
            return true;
        }
    }

    public int parseNumber() throws ParseException {
        int length = this.orig.length;
        boolean z = false;
        int i = 0;
        while (true) {
            int i2 = this.index;
            if (i2 < length) {
                switch (this.orig[i2]) {
                    case '0':
                        i *= 10;
                        break;
                    case '1':
                        i = (i * 10) + 1;
                        break;
                    case '2':
                        i = (i * 10) + 2;
                        break;
                    case '3':
                        i = (i * 10) + 3;
                        break;
                    case '4':
                        i = (i * 10) + 4;
                        break;
                    case '5':
                        i = (i * 10) + 5;
                        break;
                    case '6':
                        i = (i * 10) + 6;
                        break;
                    case '7':
                        i = (i * 10) + 7;
                        break;
                    case '8':
                        i = (i * 10) + 8;
                        break;
                    case '9':
                        i = (i * 10) + 9;
                        break;
                    default:
                        if (z) {
                            return i;
                        }
                        throw new ParseException("No Number found", i2);
                }
                this.index++;
                z = true;
            } else if (z) {
                return i;
            } else {
                throw new ParseException("No Number found", i2);
            }
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:46:0x00cd, code lost:
        if (r0 == 'u') goto L_0x00cf;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public int parseMonth() throws java.text.ParseException {
        /*
            Method dump skipped, instructions count: 446
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: javax.mail.internet.MailDateParser.parseMonth():int");
    }

    public int parseTimeZone() throws ParseException {
        int i = this.index;
        char[] cArr = this.orig;
        if (i < cArr.length) {
            char c = cArr[i];
            if (c == '+' || c == '-') {
                return parseNumericTimeZone();
            }
            return parseAlphaTimeZone();
        }
        throw new ParseException("No more characters", i);
    }

    public int parseNumericTimeZone() throws ParseException {
        boolean z;
        char[] cArr = this.orig;
        int i = this.index;
        this.index = i + 1;
        char c = cArr[i];
        if (c == '+') {
            z = true;
        } else if (c == '-') {
            z = false;
        } else {
            throw new ParseException("Bad Numeric TimeZone", this.index);
        }
        int parseNumber = parseNumber();
        int i2 = ((parseNumber / 100) * 60) + (parseNumber % 100);
        return z ? -i2 : i2;
    }

    public int parseAlphaTimeZone() throws ParseException {
        try {
            char[] cArr = this.orig;
            int i = this.index;
            this.index = i + 1;
            char c = cArr[i];
            int i2 = 0;
            boolean z = true;
            switch (c) {
                case 'C':
                case 'c':
                    i2 = 360;
                    break;
                case 'E':
                case 'e':
                    i2 = 300;
                    break;
                case 'G':
                case 'g':
                    char[] cArr2 = this.orig;
                    int i3 = this.index;
                    this.index = i3 + 1;
                    char c2 = cArr2[i3];
                    if (c2 == 'M' || c2 == 'm') {
                        char[] cArr3 = this.orig;
                        int i4 = this.index;
                        this.index = i4 + 1;
                        char c3 = cArr3[i4];
                        if (c3 != 'T') {
                            if (c3 == 't') {
                            }
                        }
                        z = false;
                        break;
                    }
                    throw new ParseException("Bad Alpha TimeZone", this.index);
                case 'M':
                case 'm':
                    i2 = 420;
                    break;
                case 'P':
                case 'p':
                    i2 = 480;
                    break;
                case 'U':
                case 'u':
                    char[] cArr4 = this.orig;
                    int i5 = this.index;
                    this.index = i5 + 1;
                    char c4 = cArr4[i5];
                    if (!(c4 == 'T' || c4 == 't')) {
                        throw new ParseException("Bad Alpha TimeZone", this.index);
                    }
                    z = false;
                default:
                    throw new ParseException("Bad Alpha TimeZone", this.index);
            }
            if (!z) {
                return i2;
            }
            char[] cArr5 = this.orig;
            int i6 = this.index;
            this.index = i6 + 1;
            char c5 = cArr5[i6];
            if (c5 == 'S' || c5 == 's') {
                char[] cArr6 = this.orig;
                int i7 = this.index;
                this.index = i7 + 1;
                char c6 = cArr6[i7];
                if (c6 == 'T' || c6 == 't') {
                    return i2;
                }
                throw new ParseException("Bad Alpha TimeZone", this.index);
            } else if (c5 != 'D' && c5 != 'd') {
                return i2;
            } else {
                char[] cArr7 = this.orig;
                int i8 = this.index;
                this.index = i8 + 1;
                char c7 = cArr7[i8];
                if (c7 == 'T' || c7 != 't') {
                    return i2 - 60;
                }
                throw new ParseException("Bad Alpha TimeZone", this.index);
            }
        } catch (ArrayIndexOutOfBoundsException unused) {
            throw new ParseException("Bad Alpha TimeZone", this.index);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int getIndex() {
        return this.index;
    }
}
