package p110z1;

import com.tendcloud.tenddata.C2771ci;
import java.math.BigInteger;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import org.apache.tools.tar.TarConstants;

/* renamed from: z1.nc */
/* loaded from: classes3.dex */
public final class PDF417HighLevelEncoder {

    /* renamed from: a */
    private static final int f22550a = 0;

    /* renamed from: b */
    private static final int f22551b = 1;

    /* renamed from: c */
    private static final int f22552c = 2;

    /* renamed from: d */
    private static final int f22553d = 0;

    /* renamed from: e */
    private static final int f22554e = 1;

    /* renamed from: f */
    private static final int f22555f = 2;

    /* renamed from: g */
    private static final int f22556g = 3;

    /* renamed from: h */
    private static final int f22557h = 900;

    /* renamed from: i */
    private static final int f22558i = 901;

    /* renamed from: j */
    private static final int f22559j = 902;

    /* renamed from: k */
    private static final int f22560k = 913;

    /* renamed from: l */
    private static final int f22561l = 924;

    /* renamed from: m */
    private static final int f22562m = 925;

    /* renamed from: n */
    private static final int f22563n = 926;

    /* renamed from: o */
    private static final int f22564o = 927;

    /* renamed from: p */
    private static final byte[] f22565p = {TarConstants.LF_NORMAL, TarConstants.LF_LINK, TarConstants.LF_SYMLINK, TarConstants.LF_CHR, TarConstants.LF_BLK, TarConstants.LF_DIR, TarConstants.LF_FIFO, TarConstants.LF_CONTIG, 56, 57, 38, C2771ci.f13672f, 9, 44, 58, 35, 45, 46, 36, 47, 43, 37, 42, 61, 94, 0, 32, 0, 0, 0};

    /* renamed from: q */
    private static final byte[] f22566q = {59, 60, 62, 64, 91, 92, 93, 95, 96, 126, 33, C2771ci.f13672f, 9, 44, 58, 10, 45, 46, 36, 47, 34, 124, 42, 40, 41, 63, 123, 125, 39, 0};

    /* renamed from: r */
    private static final byte[] f22567r = new byte[128];

    /* renamed from: s */
    private static final byte[] f22568s = new byte[128];

    /* renamed from: t */
    private static final Charset f22569t = StandardCharsets.ISO_8859_1;

    /* renamed from: a */
    private static boolean m1869a(char c) {
        return c >= '0' && c <= '9';
    }

    /* renamed from: b */
    private static boolean m1861b(char c) {
        if (c != ' ') {
            return c >= 'A' && c <= 'Z';
        }
        return true;
    }

    /* renamed from: c */
    private static boolean m1859c(char c) {
        if (c != ' ') {
            return c >= 'a' && c <= 'z';
        }
        return true;
    }

    /* renamed from: f */
    private static boolean m1856f(char c) {
        if (c == '\t' || c == '\n' || c == '\r') {
            return true;
        }
        return c >= ' ' && c <= '~';
    }

    static {
        Arrays.fill(f22567r, (byte) -1);
        int i = 0;
        int i2 = 0;
        while (true) {
            byte[] bArr = f22565p;
            if (i2 >= bArr.length) {
                break;
            }
            byte b = bArr[i2];
            if (b > 0) {
                f22567r[b] = (byte) i2;
            }
            i2++;
        }
        Arrays.fill(f22568s, (byte) -1);
        while (true) {
            byte[] bArr2 = f22566q;
            if (i < bArr2.length) {
                byte b2 = bArr2[i];
                if (b2 > 0) {
                    f22568s[b2] = (byte) i;
                }
                i++;
            } else {
                return;
            }
        }
    }

    private PDF417HighLevelEncoder() {
    }

    /* renamed from: a */
    public static String m1863a(String str, Compaction myVar, Charset charset) throws WriterException {
        CharacterSetECI a;
        StringBuilder sb = new StringBuilder(str.length());
        if (charset == null) {
            charset = f22569t;
        } else if (!f22569t.equals(charset) && (a = CharacterSetECI.m2461a(charset.name())) != null) {
            int i = a.f21987B[0];
            if (i >= 0 && i < 900) {
                sb.append((char) 927);
                sb.append((char) i);
            } else if (i < 810900) {
                sb.append((char) 926);
                sb.append((char) ((i / 900) - 1));
                sb.append((char) (i % 900));
            } else if (i < 811800) {
                sb.append((char) 925);
                sb.append((char) (810900 - i));
            } else {
                throw new WriterException("ECI number not in valid range from 0..811799, but was ".concat(String.valueOf(i)));
            }
        }
        int length = str.length();
        switch (myVar) {
            case TEXT:
                m1866a(str, 0, length, sb, 0);
                break;
            case BYTE:
                byte[] bytes = str.getBytes(charset);
                m1862a(bytes, bytes.length, 1, sb);
                break;
            case NUMERIC:
                sb.append((char) 902);
                m1865a(str, 0, length, sb);
                break;
            default:
                int i2 = 0;
                int i3 = 0;
                int i4 = 0;
                while (i2 < length) {
                    int a2 = m1867a(str, i2);
                    if (a2 >= 13) {
                        sb.append((char) 902);
                        i3 = 2;
                        m1865a(str, i2, a2, sb);
                        i2 += a2;
                        i4 = 0;
                    } else {
                        int b = m1860b(str, i2);
                        if (b >= 5 || a2 == length) {
                            if (i3 != 0) {
                                sb.append((char) 900);
                                i3 = 0;
                                i4 = 0;
                            }
                            i4 = m1866a(str, i2, b, sb, i4);
                            i2 += b;
                        } else {
                            int a3 = m1864a(str, i2, charset);
                            if (a3 == 0) {
                                a3 = 1;
                            }
                            int i5 = a3 + i2;
                            byte[] bytes2 = str.substring(i2, i5).getBytes(charset);
                            if (bytes2.length == 1 && i3 == 0) {
                                m1862a(bytes2, 1, 0, sb);
                            } else {
                                m1862a(bytes2, bytes2.length, i3, sb);
                                i3 = 1;
                                i4 = 0;
                            }
                            i2 = i5;
                        }
                    }
                }
                break;
        }
        return sb.toString();
    }

    /* renamed from: a */
    private static int m1866a(CharSequence charSequence, int i, int i2, StringBuilder sb, int i3) {
        StringBuilder sb2 = new StringBuilder(i2);
        int i4 = i3;
        int i5 = 0;
        while (true) {
            int i6 = i + i5;
            char charAt = charSequence.charAt(i6);
            switch (i4) {
                case 0:
                    if (m1861b(charAt)) {
                        if (charAt == ' ') {
                            sb2.append((char) 26);
                            break;
                        } else {
                            sb2.append((char) (charAt - 'A'));
                            break;
                        }
                    } else if (m1859c(charAt)) {
                        sb2.append((char) 27);
                        i4 = 1;
                        continue;
                    } else if (!m1858d(charAt)) {
                        sb2.append((char) 29);
                        sb2.append((char) f22568s[charAt]);
                        break;
                    } else {
                        sb2.append((char) 28);
                        i4 = 2;
                    }
                case 1:
                    if (m1859c(charAt)) {
                        if (charAt == ' ') {
                            sb2.append((char) 26);
                            break;
                        } else {
                            sb2.append((char) (charAt - 'a'));
                            break;
                        }
                    } else if (m1861b(charAt)) {
                        sb2.append((char) 27);
                        sb2.append((char) (charAt - 'A'));
                        break;
                    } else if (!m1858d(charAt)) {
                        sb2.append((char) 29);
                        sb2.append((char) f22568s[charAt]);
                        break;
                    } else {
                        sb2.append((char) 28);
                        i4 = 2;
                        continue;
                    }
                case 2:
                    if (m1858d(charAt)) {
                        sb2.append((char) f22567r[charAt]);
                        break;
                    } else if (m1861b(charAt)) {
                        sb2.append((char) 28);
                        i4 = 0;
                        continue;
                    } else if (m1859c(charAt)) {
                        sb2.append((char) 27);
                        i4 = 1;
                    } else {
                        int i7 = i6 + 1;
                        if (i7 >= i2 || !m1857e(charSequence.charAt(i7))) {
                            sb2.append((char) 29);
                            sb2.append((char) f22568s[charAt]);
                            break;
                        } else {
                            i4 = 3;
                            sb2.append((char) 25);
                        }
                    }
                    break;
                default:
                    if (m1857e(charAt)) {
                        sb2.append((char) f22568s[charAt]);
                        break;
                    } else {
                        sb2.append((char) 29);
                        i4 = 0;
                        continue;
                    }
            }
            i5++;
            if (i5 >= i2) {
                int length = sb2.length();
                char c = 0;
                for (int i8 = 0; i8 < length; i8++) {
                    if (i8 % 2 != 0) {
                        c = (char) ((c * 30) + sb2.charAt(i8));
                        sb.append(c);
                    } else {
                        c = sb2.charAt(i8);
                    }
                }
                if (length % 2 != 0) {
                    sb.append((char) ((c * 30) + 29));
                }
                return i4;
            }
        }
    }

    /* renamed from: a */
    private static void m1862a(byte[] bArr, int i, int i2, StringBuilder sb) {
        int i3;
        if (i == 1 && i2 == 0) {
            sb.append((char) 913);
        } else if (i % 6 == 0) {
            sb.append((char) 924);
        } else {
            sb.append((char) 901);
        }
        if (i >= 6) {
            char[] cArr = new char[5];
            i3 = 0;
            while ((i + 0) - i3 >= 6) {
                long j = 0;
                for (int i4 = 0; i4 < 6; i4++) {
                    j = (j << 8) + (bArr[i3 + i4] & 255);
                }
                for (int i5 = 0; i5 < 5; i5++) {
                    cArr[i5] = (char) (j % 900);
                    j /= 900;
                }
                for (int i6 = 4; i6 >= 0; i6--) {
                    sb.append(cArr[i6]);
                }
                i3 += 6;
            }
        } else {
            i3 = 0;
        }
        while (i3 < i + 0) {
            sb.append((char) (bArr[i3] & 255));
            i3++;
        }
    }

    /* renamed from: a */
    private static void m1865a(String str, int i, int i2, StringBuilder sb) {
        StringBuilder sb2 = new StringBuilder((i2 / 3) + 1);
        BigInteger valueOf = BigInteger.valueOf(900L);
        BigInteger valueOf2 = BigInteger.valueOf(0L);
        int i3 = 0;
        while (i3 < i2) {
            sb2.setLength(0);
            int min = Math.min(44, i2 - i3);
            StringBuilder sb3 = new StringBuilder("1");
            int i4 = i + i3;
            sb3.append(str.substring(i4, i4 + min));
            BigInteger bigInteger = new BigInteger(sb3.toString());
            do {
                sb2.append((char) bigInteger.mod(valueOf).intValue());
                bigInteger = bigInteger.divide(valueOf);
            } while (!bigInteger.equals(valueOf2));
            for (int length = sb2.length() - 1; length >= 0; length--) {
                sb.append(sb2.charAt(length));
            }
            i3 += min;
        }
    }

    /* renamed from: d */
    private static boolean m1858d(char c) {
        return f22567r[c] != -1;
    }

    /* renamed from: e */
    private static boolean m1857e(char c) {
        return f22568s[c] != -1;
    }

    /* renamed from: a */
    private static int m1867a(CharSequence charSequence, int i) {
        int length = charSequence.length();
        int i2 = 0;
        if (i < length) {
            char charAt = charSequence.charAt(i);
            while (m1869a(charAt) && i < length) {
                i2++;
                i++;
                if (i < length) {
                    charAt = charSequence.charAt(i);
                }
            }
        }
        return i2;
    }

    /* JADX WARN: Code restructure failed: missing block: B:15:0x0029, code lost:
        return (r4 - r8) - r1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:32:0x004d, code lost:
        return r1 - r8;
     */
    /* renamed from: b */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static int m1860b(java.lang.CharSequence r7, int r8) {
        /*
            int r0 = r7.length()
            r1 = r8
        L_0x0005:
            if (r1 >= r0) goto L_0x004c
            char r2 = r7.charAt(r1)
            r3 = 0
            r4 = r1
            r1 = 0
        L_0x000e:
            r5 = 13
            if (r1 >= r5) goto L_0x0025
            boolean r6 = m1869a(r2)
            if (r6 == 0) goto L_0x0025
            if (r4 >= r0) goto L_0x0025
            int r1 = r1 + 1
            int r4 = r4 + 1
            if (r4 >= r0) goto L_0x000e
            char r2 = r7.charAt(r4)
            goto L_0x000e
        L_0x0025:
            if (r1 < r5) goto L_0x002a
            int r4 = r4 - r8
            int r4 = r4 - r1
            return r4
        L_0x002a:
            if (r1 > 0) goto L_0x004a
            char r1 = r7.charAt(r4)
            r2 = 9
            if (r1 == r2) goto L_0x0042
            r2 = 10
            if (r1 == r2) goto L_0x0042
            if (r1 == r5) goto L_0x0042
            r2 = 32
            if (r1 < r2) goto L_0x0043
            r2 = 126(0x7e, float:1.77E-43)
            if (r1 > r2) goto L_0x0043
        L_0x0042:
            r3 = 1
        L_0x0043:
            if (r3 == 0) goto L_0x0048
            int r1 = r4 + 1
            goto L_0x0005
        L_0x0048:
            r1 = r4
            goto L_0x004c
        L_0x004a:
            r1 = r4
            goto L_0x0005
        L_0x004c:
            int r1 = r1 - r8
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: p110z1.PDF417HighLevelEncoder.m1860b(java.lang.CharSequence, int):int");
    }

    /* JADX WARN: Code restructure failed: missing block: B:14:0x0028, code lost:
        return r1 - r6;
     */
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static int m1864a(java.lang.String r5, int r6, java.nio.charset.Charset r7) throws p110z1.WriterException {
        /*
            java.nio.charset.CharsetEncoder r7 = r7.newEncoder()
            int r0 = r5.length()
            r1 = r6
        L_0x0009:
            if (r1 >= r0) goto L_0x0057
            char r2 = r5.charAt(r1)
            r3 = 0
        L_0x0010:
            r4 = 13
            if (r3 >= r4) goto L_0x0025
            boolean r2 = m1869a(r2)
            if (r2 == 0) goto L_0x0025
            int r3 = r3 + 1
            int r2 = r1 + r3
            if (r2 >= r0) goto L_0x0025
            char r2 = r5.charAt(r2)
            goto L_0x0010
        L_0x0025:
            if (r3 < r4) goto L_0x0029
            int r1 = r1 - r6
            return r1
        L_0x0029:
            char r2 = r5.charAt(r1)
            boolean r3 = r7.canEncode(r2)
            if (r3 == 0) goto L_0x0036
            int r1 = r1 + 1
            goto L_0x0009
        L_0x0036:
            z1.oq r5 = new z1.oq
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            java.lang.String r7 = "Non-encodable character detected: "
            r6.<init>(r7)
            r6.append(r2)
            java.lang.String r7 = " (Unicode: "
            r6.append(r7)
            r6.append(r2)
            r7 = 41
            r6.append(r7)
            java.lang.String r6 = r6.toString()
            r5.<init>(r6)
            throw r5
        L_0x0057:
            int r1 = r1 - r6
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: p110z1.PDF417HighLevelEncoder.m1864a(java.lang.String, int, java.nio.charset.Charset):int");
    }

    /* renamed from: a */
    private static void m1868a(int i, StringBuilder sb) throws WriterException {
        if (i >= 0 && i < 900) {
            sb.append((char) 927);
            sb.append((char) i);
        } else if (i < 810900) {
            sb.append((char) 926);
            sb.append((char) ((i / 900) - 1));
            sb.append((char) (i % 900));
        } else if (i < 811800) {
            sb.append((char) 925);
            sb.append((char) (810900 - i));
        } else {
            throw new WriterException("ECI number not in valid range from 0..811799, but was ".concat(String.valueOf(i)));
        }
    }
}
