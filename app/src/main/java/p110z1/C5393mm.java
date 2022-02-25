package p110z1;

import java.io.ByteArrayOutputStream;
import java.math.BigInteger;
import java.nio.charset.Charset;
import java.util.Arrays;

/* compiled from: DecodedBitStreamParser.java */
/* renamed from: z1.mm */
/* loaded from: classes3.dex */
final class C5393mm {

    /* renamed from: A */
    private static final char[] f22444A = ";<>@[\\]_`~!\r\t,:\n-.$/\"|*()?{}'".toCharArray();

    /* renamed from: B */
    private static final char[] f22445B = "0123456789&\r\t,:#-.$/+%*=^".toCharArray();

    /* renamed from: C */
    private static final BigInteger[] f22446C;

    /* renamed from: D */
    private static final int f22447D = 2;

    /* renamed from: a */
    private static final int f22448a = 900;

    /* renamed from: b */
    private static final int f22449b = 901;

    /* renamed from: c */
    private static final int f22450c = 902;

    /* renamed from: d */
    private static final int f22451d = 924;

    /* renamed from: e */
    private static final int f22452e = 925;

    /* renamed from: f */
    private static final int f22453f = 926;

    /* renamed from: g */
    private static final int f22454g = 927;

    /* renamed from: h */
    private static final int f22455h = 928;

    /* renamed from: i */
    private static final int f22456i = 923;

    /* renamed from: j */
    private static final int f22457j = 922;

    /* renamed from: k */
    private static final int f22458k = 913;

    /* renamed from: l */
    private static final int f22459l = 15;

    /* renamed from: m */
    private static final int f22460m = 0;

    /* renamed from: n */
    private static final int f22461n = 1;

    /* renamed from: o */
    private static final int f22462o = 2;

    /* renamed from: p */
    private static final int f22463p = 3;

    /* renamed from: q */
    private static final int f22464q = 4;

    /* renamed from: r */
    private static final int f22465r = 5;

    /* renamed from: s */
    private static final int f22466s = 6;

    /* renamed from: t */
    private static final int f22467t = 25;

    /* renamed from: u */
    private static final int f22468u = 27;

    /* renamed from: v */
    private static final int f22469v = 27;

    /* renamed from: w */
    private static final int f22470w = 28;

    /* renamed from: x */
    private static final int f22471x = 28;

    /* renamed from: y */
    private static final int f22472y = 29;

    /* renamed from: z */
    private static final int f22473z = 29;

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Failed to restore enum class, 'enum' modifier removed */
    /* compiled from: DecodedBitStreamParser.java */
    /* renamed from: z1.mm$a */
    /* loaded from: classes3.dex */
    public static final class EnumC5395a extends Enum<EnumC5395a> {

        /* renamed from: a */
        public static final int f22475a = 1;

        /* renamed from: b */
        public static final int f22476b = 2;

        /* renamed from: c */
        public static final int f22477c = 3;

        /* renamed from: d */
        public static final int f22478d = 4;

        /* renamed from: e */
        public static final int f22479e = 5;

        /* renamed from: f */
        public static final int f22480f = 6;

        /* renamed from: g */
        private static final /* synthetic */ int[] f22481g = {f22475a, f22476b, f22477c, f22478d, f22479e, f22480f};

        private EnumC5395a(String str, int i) {
        }

        /* renamed from: a */
        public static int[] m1994a() {
            return (int[]) f22481g.clone();
        }
    }

    static {
        BigInteger[] bigIntegerArr = new BigInteger[16];
        f22446C = bigIntegerArr;
        bigIntegerArr[0] = BigInteger.ONE;
        BigInteger valueOf = BigInteger.valueOf(900L);
        f22446C[1] = valueOf;
        int i = 2;
        while (true) {
            BigInteger[] bigIntegerArr2 = f22446C;
            if (i < bigIntegerArr2.length) {
                bigIntegerArr2[i] = bigIntegerArr2[i - 1].multiply(valueOf);
                i++;
            } else {
                return;
            }
        }
    }

    private C5393mm() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Removed duplicated region for block: B:17:0x004e  */
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static p110z1.DecoderResult m1997a(int[] r6, java.lang.String r7) throws p110z1.FormatException {
        /*
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            int r1 = r6.length
            r2 = 1
            int r1 = r1 << r2
            r0.<init>(r1)
            java.nio.charset.Charset r1 = java.nio.charset.StandardCharsets.ISO_8859_1
            r2 = r6[r2]
            z1.mv r3 = new z1.mv
            r3.<init>()
            r4 = 2
        L_0x0012:
            r5 = 0
            r5 = r6[r5]
            if (r4 >= r5) goto L_0x006d
            r5 = 913(0x391, float:1.28E-42)
            if (r2 == r5) goto L_0x0058
            switch(r2) {
                case 900: goto L_0x0053;
                case 901: goto L_0x004e;
                case 902: goto L_0x0049;
                default: goto L_0x001e;
            }
        L_0x001e:
            switch(r2) {
                case 922: goto L_0x0044;
                case 923: goto L_0x0044;
                case 924: goto L_0x004e;
                case 925: goto L_0x0041;
                case 926: goto L_0x003e;
                case 927: goto L_0x002d;
                case 928: goto L_0x0028;
                default: goto L_0x0021;
            }
        L_0x0021:
            int r4 = r4 + (-1)
            int r2 = m1999a(r6, r4, r0)
            goto L_0x0060
        L_0x0028:
            int r2 = m1998a(r6, r4, r3)
            goto L_0x0060
        L_0x002d:
            int r2 = r4 + 1
            r1 = r6[r4]
            z1.if r1 = p110z1.CharacterSetECI.m2462a(r1)
            java.lang.String r1 = r1.name()
            java.nio.charset.Charset r1 = java.nio.charset.Charset.forName(r1)
            goto L_0x0060
        L_0x003e:
            int r2 = r4 + 2
            goto L_0x0060
        L_0x0041:
            int r2 = r4 + 1
            goto L_0x0060
        L_0x0044:
            z1.md r6 = p110z1.FormatException.m2059a()
            throw r6
        L_0x0049:
            int r2 = m1995b(r6, r4, r0)
            goto L_0x0060
        L_0x004e:
            int r2 = m2001a(r2, r6, r1, r4, r0)
            goto L_0x0060
        L_0x0053:
            int r2 = m1999a(r6, r4, r0)
            goto L_0x0060
        L_0x0058:
            int r2 = r4 + 1
            r4 = r6[r4]
            char r4 = (char) r4
            r0.append(r4)
        L_0x0060:
            int r4 = r6.length
            if (r2 >= r4) goto L_0x0068
            int r4 = r2 + 1
            r2 = r6[r2]
            goto L_0x0012
        L_0x0068:
            z1.md r6 = p110z1.FormatException.m2059a()
            throw r6
        L_0x006d:
            int r6 = r0.length()
            if (r6 == 0) goto L_0x0080
            z1.ig r6 = new z1.ig
            java.lang.String r0 = r0.toString()
            r1 = 0
            r6.<init>(r1, r0, r1, r7)
            r6.f21996h = r3
            return r6
        L_0x0080:
            z1.md r6 = p110z1.FormatException.m2059a()
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: p110z1.C5393mm.m1997a(int[], java.lang.String):z1.ig");
    }

    /* renamed from: a */
    private static int m1998a(int[] iArr, int i, PDF417ResultMetadata mvVar) throws FormatException {
        if (i + 2 <= iArr[0]) {
            int[] iArr2 = new int[2];
            int i2 = i;
            int i3 = 0;
            while (i3 < 2) {
                iArr2[i3] = iArr[i2];
                i3++;
                i2++;
            }
            mvVar.f22509a = Integer.parseInt(m2000a(iArr2, 2));
            StringBuilder sb = new StringBuilder();
            int a = m1999a(iArr, i2, sb);
            mvVar.f22510b = sb.toString();
            int i4 = iArr[a] == f22456i ? a + 1 : -1;
            while (a < iArr[0]) {
                switch (iArr[a]) {
                    case f22457j /* 922 */:
                        a++;
                        mvVar.f22511c = true;
                        break;
                    case f22456i /* 923 */:
                        int i5 = a + 1;
                        switch (iArr[i5]) {
                            case 0:
                                StringBuilder sb2 = new StringBuilder();
                                a = m1999a(iArr, i5 + 1, sb2);
                                mvVar.f22515g = sb2.toString();
                                continue;
                            case 1:
                                StringBuilder sb3 = new StringBuilder();
                                a = m1995b(iArr, i5 + 1, sb3);
                                mvVar.f22512d = Integer.parseInt(sb3.toString());
                                continue;
                            case 2:
                                StringBuilder sb4 = new StringBuilder();
                                a = m1995b(iArr, i5 + 1, sb4);
                                mvVar.f22517i = Long.parseLong(sb4.toString());
                                continue;
                            case 3:
                                StringBuilder sb5 = new StringBuilder();
                                a = m1999a(iArr, i5 + 1, sb5);
                                mvVar.f22513e = sb5.toString();
                                continue;
                            case 4:
                                StringBuilder sb6 = new StringBuilder();
                                a = m1999a(iArr, i5 + 1, sb6);
                                mvVar.f22514f = sb6.toString();
                                continue;
                            case 5:
                                StringBuilder sb7 = new StringBuilder();
                                a = m1995b(iArr, i5 + 1, sb7);
                                mvVar.f22516h = Long.parseLong(sb7.toString());
                                continue;
                            case 6:
                                StringBuilder sb8 = new StringBuilder();
                                a = m1995b(iArr, i5 + 1, sb8);
                                mvVar.f22518j = Integer.parseInt(sb8.toString());
                                continue;
                            default:
                                throw FormatException.m2059a();
                        }
                    default:
                        throw FormatException.m2059a();
                }
            }
            if (i4 != -1) {
                int i6 = a - i4;
                if (mvVar.f22511c) {
                    i6--;
                }
                mvVar.f22519k = Arrays.copyOfRange(iArr, i4, i6 + i4);
            }
            return a;
        }
        throw FormatException.m2059a();
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* renamed from: a */
    private static int m1999a(int[] iArr, int i, StringBuilder sb) {
        int[] iArr2 = new int[(iArr[0] - i) << 1];
        int[] iArr3 = new int[(iArr[0] - i) << 1];
        int i2 = i;
        boolean z = false;
        int i3 = 0;
        while (i2 < iArr[0] && !z) {
            int i4 = i2 + 1;
            int i5 = iArr[i2];
            if (i5 < 900) {
                iArr2[i3] = i5 / 30;
                iArr2[i3 + 1] = i5 % 30;
                i3 += 2;
                i2 = i4;
            } else if (i5 != f22458k) {
                if (i5 != 928) {
                    switch (i5) {
                        case 900:
                            i3++;
                            iArr2[i3] = 900;
                            i2 = i4;
                            break;
                        case 901:
                        case 902:
                            break;
                        default:
                            switch (i5) {
                                case f22457j /* 922 */:
                                case f22456i /* 923 */:
                                case f22451d /* 924 */:
                                    break;
                                default:
                                    i2 = i4;
                                    break;
                            }
                    }
                }
                i2 = i4 - 1;
                z = true;
            } else {
                iArr2[i3] = f22458k;
                i2 = i4 + 1;
                iArr3[i3] = iArr[i4];
                i3++;
            }
        }
        int i6 = EnumC5395a.f22475a;
        int i7 = EnumC5395a.f22475a;
        int i8 = i6;
        for (int i9 = 0; i9 < i3; i9++) {
            int i10 = iArr2[i9];
            char c = ' ';
            switch (C53941.f22474a[i8 - 1]) {
                case 1:
                    if (i10 < 26) {
                        c = (char) (i10 + 65);
                        break;
                    } else {
                        if (i10 != 900) {
                            if (i10 != f22458k) {
                                switch (i10) {
                                    case 27:
                                        i8 = EnumC5395a.f22476b;
                                        c = 0;
                                        break;
                                    case 28:
                                        i8 = EnumC5395a.f22477c;
                                        c = 0;
                                        break;
                                    case 29:
                                        c = 0;
                                        i7 = i8;
                                        i8 = EnumC5395a.f22480f;
                                        break;
                                }
                            } else {
                                sb.append((char) iArr3[i9]);
                                c = 0;
                                break;
                            }
                        } else {
                            i8 = EnumC5395a.f22475a;
                        }
                        c = 0;
                        break;
                    }
                    break;
                case 2:
                    if (i10 < 26) {
                        c = (char) (i10 + 97);
                        break;
                    } else {
                        if (i10 != 900) {
                            if (i10 != f22458k) {
                                switch (i10) {
                                    case 27:
                                        c = 0;
                                        i7 = i8;
                                        i8 = EnumC5395a.f22479e;
                                        break;
                                    case 28:
                                        i8 = EnumC5395a.f22477c;
                                        c = 0;
                                        break;
                                    case 29:
                                        c = 0;
                                        i7 = i8;
                                        i8 = EnumC5395a.f22480f;
                                        break;
                                }
                            } else {
                                sb.append((char) iArr3[i9]);
                                c = 0;
                                break;
                            }
                        } else {
                            i8 = EnumC5395a.f22475a;
                        }
                        c = 0;
                        break;
                    }
                    break;
                case 3:
                    if (i10 < 25) {
                        c = f22445B[i10];
                        break;
                    } else {
                        if (i10 != 900) {
                            if (i10 != f22458k) {
                                switch (i10) {
                                    case 25:
                                        i8 = EnumC5395a.f22478d;
                                        c = 0;
                                        break;
                                    case 27:
                                        i8 = EnumC5395a.f22476b;
                                        c = 0;
                                        break;
                                    case 28:
                                        i8 = EnumC5395a.f22475a;
                                        c = 0;
                                        break;
                                    case 29:
                                        c = 0;
                                        i7 = i8;
                                        i8 = EnumC5395a.f22480f;
                                        break;
                                }
                            } else {
                                sb.append((char) iArr3[i9]);
                                c = 0;
                                break;
                            }
                        } else {
                            i8 = EnumC5395a.f22475a;
                        }
                        c = 0;
                        break;
                    }
                    break;
                case 4:
                    if (i10 >= 29) {
                        if (i10 != 29) {
                            if (i10 != 900) {
                                if (i10 == f22458k) {
                                    sb.append((char) iArr3[i9]);
                                    c = 0;
                                    break;
                                }
                            } else {
                                i8 = EnumC5395a.f22475a;
                            }
                            c = 0;
                            break;
                        } else {
                            i8 = EnumC5395a.f22475a;
                            c = 0;
                            break;
                        }
                    } else {
                        c = f22444A[i10];
                        break;
                    }
                case 5:
                    if (i10 >= 26) {
                        if (i10 != 26) {
                            i8 = i10 != 900 ? i7 : EnumC5395a.f22475a;
                            c = 0;
                            break;
                        } else {
                            i8 = i7;
                            break;
                        }
                    } else {
                        c = (char) (i10 + 65);
                        i8 = i7;
                        break;
                    }
                case 6:
                    if (i10 >= 29) {
                        if (i10 != 29) {
                            if (i10 != 900) {
                                if (i10 == f22458k) {
                                    sb.append((char) iArr3[i9]);
                                }
                                i8 = i7;
                                c = 0;
                                break;
                            } else {
                                i8 = EnumC5395a.f22475a;
                                c = 0;
                                break;
                            }
                        } else {
                            i8 = EnumC5395a.f22475a;
                            c = 0;
                            break;
                        }
                    } else {
                        c = f22444A[i10];
                        i8 = i7;
                        break;
                    }
                default:
                    c = 0;
                    break;
            }
            if (c != 0) {
                sb.append(c);
            }
        }
        return i2;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* renamed from: a */
    private static void m1996a(int[] iArr, int[] iArr2, int i, StringBuilder sb) {
        char c;
        int i2 = EnumC5395a.f22475a;
        int i3 = EnumC5395a.f22475a;
        int i4 = i2;
        for (int i5 = 0; i5 < i; i5++) {
            int i6 = iArr[i5];
            switch (C53941.f22474a[i4 - 1]) {
                case 1:
                    if (i6 < 26) {
                        c = (char) (i6 + 65);
                        break;
                    } else {
                        if (i6 != 900) {
                            if (i6 != f22458k) {
                                switch (i6) {
                                    case 26:
                                        c = ' ';
                                        break;
                                    case 27:
                                        i4 = EnumC5395a.f22476b;
                                        c = 0;
                                        break;
                                    case 28:
                                        i4 = EnumC5395a.f22477c;
                                        c = 0;
                                        break;
                                    case 29:
                                        c = 0;
                                        i3 = i4;
                                        i4 = EnumC5395a.f22480f;
                                        break;
                                }
                            } else {
                                sb.append((char) iArr2[i5]);
                                c = 0;
                                break;
                            }
                        } else {
                            i4 = EnumC5395a.f22475a;
                        }
                        c = 0;
                        break;
                    }
                case 2:
                    if (i6 < 26) {
                        c = (char) (i6 + 97);
                        break;
                    } else {
                        if (i6 != 900) {
                            if (i6 != f22458k) {
                                switch (i6) {
                                    case 26:
                                        c = ' ';
                                        break;
                                    case 27:
                                        c = 0;
                                        i3 = i4;
                                        i4 = EnumC5395a.f22479e;
                                        break;
                                    case 28:
                                        i4 = EnumC5395a.f22477c;
                                        c = 0;
                                        break;
                                    case 29:
                                        c = 0;
                                        i3 = i4;
                                        i4 = EnumC5395a.f22480f;
                                        break;
                                }
                            } else {
                                sb.append((char) iArr2[i5]);
                                c = 0;
                                break;
                            }
                        } else {
                            i4 = EnumC5395a.f22475a;
                        }
                        c = 0;
                        break;
                    }
                case 3:
                    if (i6 < 25) {
                        c = f22445B[i6];
                        break;
                    } else {
                        if (i6 != 900) {
                            if (i6 != f22458k) {
                                switch (i6) {
                                    case 25:
                                        i4 = EnumC5395a.f22478d;
                                        c = 0;
                                        break;
                                    case 26:
                                        c = ' ';
                                        break;
                                    case 27:
                                        i4 = EnumC5395a.f22476b;
                                        c = 0;
                                        break;
                                    case 28:
                                        i4 = EnumC5395a.f22475a;
                                        c = 0;
                                        break;
                                    case 29:
                                        c = 0;
                                        i3 = i4;
                                        i4 = EnumC5395a.f22480f;
                                        break;
                                }
                            } else {
                                sb.append((char) iArr2[i5]);
                                c = 0;
                                break;
                            }
                        } else {
                            i4 = EnumC5395a.f22475a;
                        }
                        c = 0;
                        break;
                    }
                case 4:
                    if (i6 >= 29) {
                        if (i6 != 29) {
                            if (i6 != 900) {
                                if (i6 == f22458k) {
                                    sb.append((char) iArr2[i5]);
                                    c = 0;
                                    break;
                                }
                            } else {
                                i4 = EnumC5395a.f22475a;
                            }
                            c = 0;
                            break;
                        } else {
                            i4 = EnumC5395a.f22475a;
                            c = 0;
                            break;
                        }
                    } else {
                        c = f22444A[i6];
                        break;
                    }
                case 5:
                    if (i6 >= 26) {
                        if (i6 != 26) {
                            i4 = i6 != 900 ? i3 : EnumC5395a.f22475a;
                            c = 0;
                            break;
                        } else {
                            i4 = i3;
                            c = ' ';
                            break;
                        }
                    } else {
                        c = (char) (i6 + 65);
                        i4 = i3;
                        break;
                    }
                case 6:
                    if (i6 >= 29) {
                        if (i6 != 29) {
                            if (i6 != 900) {
                                if (i6 == f22458k) {
                                    sb.append((char) iArr2[i5]);
                                }
                                i4 = i3;
                                c = 0;
                                break;
                            } else {
                                i4 = EnumC5395a.f22475a;
                                c = 0;
                                break;
                            }
                        } else {
                            i4 = EnumC5395a.f22475a;
                            c = 0;
                            break;
                        }
                    } else {
                        c = f22444A[i6];
                        i4 = i3;
                        break;
                    }
                default:
                    c = 0;
                    break;
            }
            if (c != 0) {
                sb.append(c);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: DecodedBitStreamParser.java */
    /* renamed from: z1.mm$1 */
    /* loaded from: classes3.dex */
    public static /* synthetic */ class C53941 {

        /* renamed from: a */
        static final /* synthetic */ int[] f22474a = new int[EnumC5395a.m1994a().length];

        static {
            try {
                f22474a[EnumC5395a.f22475a - 1] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f22474a[EnumC5395a.f22476b - 1] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f22474a[EnumC5395a.f22477c - 1] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f22474a[EnumC5395a.f22478d - 1] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f22474a[EnumC5395a.f22479e - 1] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                f22474a[EnumC5395a.f22480f - 1] = 6;
            } catch (NoSuchFieldError unused6) {
            }
        }
    }

    /* renamed from: a */
    private static int m2001a(int i, int[] iArr, Charset charset, int i2, StringBuilder sb) {
        int i3;
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        long j = 900;
        int i4 = 6;
        if (i == 901) {
            int[] iArr2 = new int[6];
            int i5 = i2 + 1;
            int i6 = iArr[i2];
            boolean z = false;
            int i7 = 0;
            long j2 = 0;
            while (i5 < iArr[0] && !z) {
                int i8 = i7 + 1;
                iArr2[i7] = i6;
                j2 = (j2 * j) + i6;
                int i9 = i5 + 1;
                i6 = iArr[i5];
                if (i6 != 928) {
                    switch (i6) {
                        case 900:
                        case 901:
                        case 902:
                            break;
                        default:
                            switch (i6) {
                                case f22457j /* 922 */:
                                case f22456i /* 923 */:
                                case f22451d /* 924 */:
                                    break;
                                default:
                                    if (i8 % 5 != 0 || i8 <= 0) {
                                        z = z;
                                        i5 = i9;
                                        i7 = i8;
                                        j = 900;
                                        i4 = 6;
                                    } else {
                                        int i10 = 0;
                                        while (i10 < i4) {
                                            byteArrayOutputStream.write((byte) (j2 >> ((5 - i10) * 8)));
                                            i10++;
                                            i4 = 6;
                                            z = z;
                                        }
                                        i5 = i9;
                                        j = 900;
                                        i7 = 0;
                                        j2 = 0;
                                        continue;
                                        continue;
                                    }
                                    break;
                            }
                    }
                }
                i5 = i9 - 1;
                i7 = i8;
                j = 900;
                i4 = 6;
                z = true;
            }
            if (i5 == iArr[0] && i6 < 900) {
                i7++;
                iArr2[i7] = i6;
            }
            for (int i11 = 0; i11 < i7; i11++) {
                byteArrayOutputStream.write((byte) iArr2[i11]);
            }
            i3 = i5;
        } else if (i != f22451d) {
            i3 = i2;
        } else {
            i3 = i2;
            boolean z2 = false;
            int i12 = 0;
            long j3 = 0;
            while (i3 < iArr[0] && !z2) {
                int i13 = i3 + 1;
                int i14 = iArr[i3];
                if (i14 < 900) {
                    i12++;
                    j3 = (j3 * 900) + i14;
                    i3 = i13;
                } else {
                    if (i14 != 928) {
                        switch (i14) {
                            default:
                                switch (i14) {
                                    case f22457j /* 922 */:
                                    case f22456i /* 923 */:
                                    case f22451d /* 924 */:
                                        break;
                                    default:
                                        i3 = i13;
                                        break;
                                }
                            case 900:
                            case 901:
                            case 902:
                                i3 = i13 - 1;
                                z2 = true;
                                break;
                        }
                    }
                    i3 = i13 - 1;
                    z2 = true;
                }
                if (i12 % 5 == 0 && i12 > 0) {
                    for (int i15 = 0; i15 < 6; i15++) {
                        byteArrayOutputStream.write((byte) (j3 >> ((5 - i15) * 8)));
                    }
                    i12 = 0;
                    j3 = 0;
                }
            }
        }
        sb.append(new String(byteArrayOutputStream.toByteArray(), charset));
        return i3;
    }

    /* renamed from: b */
    private static int m1995b(int[] iArr, int i, StringBuilder sb) throws FormatException {
        int[] iArr2 = new int[15];
        boolean z = false;
        int i2 = 0;
        while (i < iArr[0] && !z) {
            i++;
            int i3 = iArr[i];
            if (i == iArr[0]) {
                z = true;
            }
            if (i3 < 900) {
                iArr2[i2] = i3;
                i2++;
            } else {
                if (i3 != 928) {
                    switch (i3) {
                        default:
                            switch (i3) {
                            }
                        case 900:
                        case 901:
                            i--;
                            z = true;
                            break;
                    }
                }
                i--;
                z = true;
            }
            if ((i2 % 15 == 0 || i3 == 902 || z) && i2 > 0) {
                sb.append(m2000a(iArr2, i2));
                i2 = 0;
            }
        }
        return i;
    }

    /* renamed from: a */
    private static String m2000a(int[] iArr, int i) throws FormatException {
        BigInteger bigInteger = BigInteger.ZERO;
        for (int i2 = 0; i2 < i; i2++) {
            bigInteger = bigInteger.add(f22446C[(i - i2) - 1].multiply(BigInteger.valueOf(iArr[i2])));
        }
        String bigInteger2 = bigInteger.toString();
        if (bigInteger2.charAt(0) == '1') {
            return bigInteger2.substring(1);
        }
        throw FormatException.m2059a();
    }
}
