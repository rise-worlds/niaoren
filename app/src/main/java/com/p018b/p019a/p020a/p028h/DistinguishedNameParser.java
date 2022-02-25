package com.p018b.p019a.p020a.p028h;

import javax.security.auth.x500.X500Principal;

/* renamed from: com.b.a.a.h.c */
/* loaded from: classes.dex */
final class DistinguishedNameParser {

    /* renamed from: a */
    private final String f6046a;

    /* renamed from: b */
    private final int f6047b;

    /* renamed from: c */
    private int f6048c;

    /* renamed from: d */
    private int f6049d;

    /* renamed from: e */
    private int f6050e;

    /* renamed from: f */
    private int f6051f;

    /* renamed from: g */
    private char[] f6052g;

    public DistinguishedNameParser(X500Principal x500Principal) {
        this.f6046a = x500Principal.getName("RFC2253");
        this.f6047b = this.f6046a.length();
    }

    /* renamed from: a */
    private String m24571a() {
        int i;
        while (true) {
            int i2 = this.f6048c;
            if (i2 >= this.f6047b || this.f6052g[i2] != ' ') {
                break;
            }
            this.f6048c = i2 + 1;
        }
        int i3 = this.f6048c;
        if (i3 == this.f6047b) {
            return null;
        }
        this.f6049d = i3;
        this.f6048c = i3 + 1;
        while (true) {
            int i4 = this.f6048c;
            if (i4 >= this.f6047b) {
                break;
            }
            char[] cArr = this.f6052g;
            if (cArr[i4] == '=' || cArr[i4] == ' ') {
                break;
            }
            this.f6048c = i4 + 1;
        }
        int i5 = this.f6048c;
        if (i5 < this.f6047b) {
            this.f6050e = i5;
            if (this.f6052g[i5] == ' ') {
                while (true) {
                    int i6 = this.f6048c;
                    if (i6 >= this.f6047b) {
                        break;
                    }
                    char[] cArr2 = this.f6052g;
                    if (cArr2[i6] == '=' || cArr2[i6] != ' ') {
                        break;
                    }
                    this.f6048c = i6 + 1;
                }
                char[] cArr3 = this.f6052g;
                int i7 = this.f6048c;
                if (cArr3[i7] != '=' || i7 == this.f6047b) {
                    throw new IllegalStateException("Unexpected end of DN: " + this.f6046a);
                }
            }
            do {
                this.f6048c++;
                i = this.f6048c;
                if (i >= this.f6047b) {
                    break;
                }
            } while (this.f6052g[i] == ' ');
            int i8 = this.f6050e;
            int i9 = this.f6049d;
            if (i8 - i9 > 4) {
                char[] cArr4 = this.f6052g;
                if (cArr4[i9 + 3] == '.' && (cArr4[i9] == 'O' || cArr4[i9] == 'o')) {
                    char[] cArr5 = this.f6052g;
                    int i10 = this.f6049d;
                    if (cArr5[i10 + 1] == 'I' || cArr5[i10 + 1] == 'i') {
                        char[] cArr6 = this.f6052g;
                        int i11 = this.f6049d;
                        if (cArr6[i11 + 2] == 'D' || cArr6[i11 + 2] == 'd') {
                            this.f6049d += 4;
                        }
                    }
                }
            }
            char[] cArr7 = this.f6052g;
            int i12 = this.f6049d;
            return new String(cArr7, i12, this.f6050e - i12);
        }
        throw new IllegalStateException("Unexpected end of DN: " + this.f6046a);
    }

    /* renamed from: b */
    private String m24568b() {
        int i = this.f6048c;
        if (i + 4 < this.f6047b) {
            this.f6049d = i;
            this.f6048c = i + 1;
            while (true) {
                int i2 = this.f6048c;
                if (i2 == this.f6047b) {
                    break;
                }
                char[] cArr = this.f6052g;
                if (cArr[i2] == '+' || cArr[i2] == ',' || cArr[i2] == ';') {
                    break;
                } else if (cArr[i2] == ' ') {
                    this.f6050e = i2;
                    this.f6048c = i2 + 1;
                    while (true) {
                        int i3 = this.f6048c;
                        if (i3 >= this.f6047b || this.f6052g[i3] != ' ') {
                            break;
                        }
                        this.f6048c = i3 + 1;
                    }
                } else {
                    if (cArr[i2] >= 'A' && cArr[i2] <= 'F') {
                        cArr[i2] = (char) (cArr[i2] + ' ');
                    }
                    this.f6048c++;
                }
            }
            this.f6050e = this.f6048c;
            int i4 = this.f6050e;
            int i5 = this.f6049d;
            int i6 = i4 - i5;
            if (i6 < 5 || (i6 & 1) == 0) {
                throw new IllegalStateException("Unexpected end of DN: " + this.f6046a);
            }
            byte[] bArr = new byte[i6 / 2];
            int i7 = i5 + 1;
            for (int i8 = 0; i8 < bArr.length; i8++) {
                bArr[i8] = (byte) m24570a(i7);
                i7 += 2;
            }
            return new String(this.f6052g, this.f6049d, i6);
        }
        throw new IllegalStateException("Unexpected end of DN: " + this.f6046a);
    }

    /* JADX WARN: Code restructure failed: missing block: B:32:0x00a6, code lost:
        return new java.lang.String(r1, r2, r6.f6051f - r2);
     */
    /* renamed from: c */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private java.lang.String m24567c() {
        /*
            r6 = this;
            int r0 = r6.f6048c
            r6.f6049d = r0
            r6.f6050e = r0
        L_0x0006:
            int r0 = r6.f6048c
            int r1 = r6.f6047b
            if (r0 < r1) goto L_0x0019
            java.lang.String r0 = new java.lang.String
            char[] r1 = r6.f6052g
            int r2 = r6.f6049d
            int r3 = r6.f6050e
            int r3 = r3 - r2
            r0.<init>(r1, r2, r3)
            return r0
        L_0x0019:
            char[] r1 = r6.f6052g
            char r2 = r1[r0]
            r3 = 59
            r4 = 32
            if (r2 == r4) goto L_0x005b
            if (r2 == r3) goto L_0x004e
            r3 = 92
            if (r2 == r3) goto L_0x003b
            switch(r2) {
                case 43: goto L_0x004e;
                case 44: goto L_0x004e;
                default: goto L_0x002c;
            }
        L_0x002c:
            int r2 = r6.f6050e
            int r3 = r2 + 1
            r6.f6050e = r3
            char r3 = r1[r0]
            r1[r2] = r3
            int r0 = r0 + 1
            r6.f6048c = r0
            goto L_0x0006
        L_0x003b:
            int r0 = r6.f6050e
            int r2 = r0 + 1
            r6.f6050e = r2
            char r2 = r6.m24566d()
            r1[r0] = r2
            int r0 = r6.f6048c
            int r0 = r0 + 1
            r6.f6048c = r0
            goto L_0x0006
        L_0x004e:
            java.lang.String r0 = new java.lang.String
            char[] r1 = r6.f6052g
            int r2 = r6.f6049d
            int r3 = r6.f6050e
            int r3 = r3 - r2
            r0.<init>(r1, r2, r3)
            return r0
        L_0x005b:
            int r2 = r6.f6050e
            r6.f6051f = r2
            int r0 = r0 + 1
            r6.f6048c = r0
            int r0 = r2 + 1
            r6.f6050e = r0
            r1[r2] = r4
        L_0x0069:
            int r0 = r6.f6048c
            int r1 = r6.f6047b
            if (r0 >= r1) goto L_0x0082
            char[] r1 = r6.f6052g
            char r2 = r1[r0]
            if (r2 != r4) goto L_0x0082
            int r2 = r6.f6050e
            int r5 = r2 + 1
            r6.f6050e = r5
            r1[r2] = r4
            int r0 = r0 + 1
            r6.f6048c = r0
            goto L_0x0069
        L_0x0082:
            int r0 = r6.f6048c
            int r1 = r6.f6047b
            if (r0 == r1) goto L_0x009a
            char[] r1 = r6.f6052g
            char r2 = r1[r0]
            r4 = 44
            if (r2 == r4) goto L_0x009a
            char r2 = r1[r0]
            r4 = 43
            if (r2 == r4) goto L_0x009a
            char r0 = r1[r0]
            if (r0 != r3) goto L_0x0006
        L_0x009a:
            java.lang.String r0 = new java.lang.String
            char[] r1 = r6.f6052g
            int r2 = r6.f6049d
            int r3 = r6.f6051f
            int r3 = r3 - r2
            r0.<init>(r1, r2, r3)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.p018b.p019a.p020a.p028h.DistinguishedNameParser.m24567c():java.lang.String");
    }

    /* renamed from: d */
    private char m24566d() {
        this.f6048c++;
        int i = this.f6048c;
        if (i != this.f6047b) {
            char c = this.f6052g[i];
            if (!(c == ' ' || c == '%' || c == '\\' || c == '_')) {
                switch (c) {
                    case '\"':
                    case '#':
                        break;
                    default:
                        switch (c) {
                            case '*':
                            case '+':
                            case ',':
                                break;
                            default:
                                switch (c) {
                                    case ';':
                                    case '<':
                                    case '=':
                                    case '>':
                                        break;
                                    default:
                                        return m24565e();
                                }
                        }
                }
            }
            return this.f6052g[this.f6048c];
        }
        throw new IllegalStateException("Unexpected end of DN: " + this.f6046a);
    }

    /* renamed from: e */
    private char m24565e() {
        int i;
        int i2;
        int a = m24570a(this.f6048c);
        this.f6048c++;
        if (a < 128) {
            return (char) a;
        }
        if (a < 192 || a > 247) {
            return '?';
        }
        if (a <= 223) {
            i2 = a & 31;
            i = 1;
        } else if (a <= 239) {
            i = 2;
            i2 = a & 15;
        } else {
            i = 3;
            i2 = a & 7;
        }
        for (int i3 = 0; i3 < i; i3++) {
            this.f6048c++;
            int i4 = this.f6048c;
            if (i4 == this.f6047b || this.f6052g[i4] != '\\') {
                return '?';
            }
            this.f6048c = i4 + 1;
            int a2 = m24570a(this.f6048c);
            this.f6048c++;
            if ((a2 & 192) != 128) {
                return '?';
            }
            i2 = (i2 << 6) + (a2 & 63);
        }
        return (char) i2;
    }

    /* renamed from: a */
    private int m24570a(int i) {
        int i2;
        int i3;
        int i4 = i + 1;
        if (i4 < this.f6047b) {
            char c = this.f6052g[i];
            if (c >= '0' && c <= '9') {
                i2 = c - '0';
            } else if (c >= 'a' && c <= 'f') {
                i2 = c - 'W';
            } else if (c < 'A' || c > 'F') {
                throw new IllegalStateException("Malformed DN: " + this.f6046a);
            } else {
                i2 = c - '7';
            }
            char c2 = this.f6052g[i4];
            if (c2 >= '0' && c2 <= '9') {
                i3 = c2 - '0';
            } else if (c2 >= 'a' && c2 <= 'f') {
                i3 = c2 - 'W';
            } else if (c2 < 'A' || c2 > 'F') {
                throw new IllegalStateException("Malformed DN: " + this.f6046a);
            } else {
                i3 = c2 - '7';
            }
            return (i2 << 4) + i3;
        }
        throw new IllegalStateException("Malformed DN: " + this.f6046a);
    }

    /* renamed from: a */
    public final String m24569a(String str) {
        this.f6048c = 0;
        this.f6049d = 0;
        this.f6050e = 0;
        this.f6051f = 0;
        this.f6052g = this.f6046a.toCharArray();
        String a = m24571a();
        if (a == null) {
            return null;
        }
        do {
            String str2 = "";
            int i = this.f6048c;
            if (i == this.f6047b) {
                return null;
            }
            switch (this.f6052g[i]) {
                case '\"':
                    this.f6048c = i + 1;
                    this.f6049d = this.f6048c;
                    this.f6050e = this.f6049d;
                    while (true) {
                        int i2 = this.f6048c;
                        if (i2 != this.f6047b) {
                            char[] cArr = this.f6052g;
                            if (cArr[i2] == '\"') {
                                this.f6048c = i2 + 1;
                                while (true) {
                                    int i3 = this.f6048c;
                                    if (i3 < this.f6047b && this.f6052g[i3] == ' ') {
                                        this.f6048c = i3 + 1;
                                    }
                                }
                                char[] cArr2 = this.f6052g;
                                int i4 = this.f6049d;
                                str2 = new String(cArr2, i4, this.f6050e - i4);
                                break;
                            } else {
                                if (cArr[i2] == '\\') {
                                    cArr[this.f6050e] = m24566d();
                                } else {
                                    cArr[this.f6050e] = cArr[i2];
                                }
                                this.f6048c++;
                                this.f6050e++;
                            }
                        } else {
                            throw new IllegalStateException("Unexpected end of DN: " + this.f6046a);
                        }
                    }
                    break;
                case '#':
                    str2 = m24568b();
                    break;
                case '+':
                case ',':
                case ';':
                    break;
                default:
                    str2 = m24567c();
                    break;
            }
            if (str.equalsIgnoreCase(a)) {
                return str2;
            }
            int i5 = this.f6048c;
            if (i5 >= this.f6047b) {
                return null;
            }
            char[] cArr3 = this.f6052g;
            if (cArr3[i5] == ',' || cArr3[i5] == ';' || cArr3[i5] == '+') {
                this.f6048c++;
                a = m24571a();
            } else {
                throw new IllegalStateException("Malformed DN: " + this.f6046a);
            }
        } while (a != null);
        throw new IllegalStateException("Malformed DN: " + this.f6046a);
    }
}
