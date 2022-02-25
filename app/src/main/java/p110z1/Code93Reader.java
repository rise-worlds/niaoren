package p110z1;

import com.cyjh.ddy.media.p035a.ResultTypeConstant;
import com.cyjh.mobileanjian.ipc.share.proto.UiMessage;
import com.tencent.smtt.sdk.TbsListener;
import java.util.Arrays;
import org.apache.http.HttpStatus;

/* renamed from: z1.lj */
/* loaded from: classes3.dex */
public final class Code93Reader extends OneDReader {

    /* renamed from: b */
    static final int[] f22354b;

    /* renamed from: d */
    private static final int f22356d;

    /* renamed from: e */
    private final StringBuilder f22357e = new StringBuilder(20);

    /* renamed from: f */
    private final int[] f22358f = new int[6];

    /* renamed from: a */
    static final String f22353a = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ-. $/+%abcd*";

    /* renamed from: c */
    private static final char[] f22355c = f22353a.toCharArray();

    static {
        int[] iArr = {ResultTypeConstant.f7208u, TbsListener.ErrorCode.THROWABLE_INITTESRUNTIMEENVIRONMENT, 324, TbsListener.ErrorCode.ERROR_TBSINSTALLER_ISTBSCORELEGAL_02, 296, 292, 290, 336, ResultTypeConstant.f7202o, 266, HttpStatus.SC_FAILED_DEPENDENCY, 420, TbsListener.ErrorCode.INFO_CORE_EXIST_NOT_LOAD, 404, 402, 394, 360, 356, 354, 308, 282, 344, 332, TbsListener.ErrorCode.TEST_THROWABLE_IS_NULL, 300, 278, 436, 434, 428, HttpStatus.SC_UNPROCESSABLE_ENTITY, 406, 410, 364, 358, UiMessage.CommandToUi.Command_Type.SET_FW_TEXT_COLOR_VALUE, TbsListener.ErrorCode.ERROR_CANLOADVIDEO_RETURN_NULL, 302, 468, 466, 458, 366, 374, 430, 294, 474, 470, 306, 350};
        f22354b = iArr;
        f22356d = iArr[47];
    }

    /* renamed from: a */
    private static int m2107a(int[] iArr) {
        int i = 0;
        for (int i2 : iArr) {
            i += i2;
        }
        int length = iArr.length;
        int i3 = 0;
        for (int i4 = 0; i4 < length; i4++) {
            int round = Math.round((iArr[i4] * 9.0f) / i);
            if (round <= 0 || round > 4) {
                return -1;
            }
            if ((i4 & 1) == 0) {
                int i5 = i3;
                for (int i6 = 0; i6 < round; i6++) {
                    i5 = (i5 << 1) | 1;
                }
                i3 = i5;
            } else {
                i3 <<= round;
            }
        }
        return i3;
    }

    /* renamed from: a */
    private static char m2111a(int i) throws NotFoundException {
        int i2 = 0;
        while (true) {
            int[] iArr = f22354b;
            if (i2 >= iArr.length) {
                throw NotFoundException.m1647a();
            } else if (iArr[i2] == i) {
                return f22355c[i2];
            } else {
                i2++;
            }
        }
    }

    /* renamed from: a */
    private static String m2110a(CharSequence charSequence) throws FormatException {
        char c;
        int length = charSequence.length();
        StringBuilder sb = new StringBuilder(length);
        int i = 0;
        while (i < length) {
            char charAt = charSequence.charAt(i);
            if (charAt < 'a' || charAt > 'd') {
                sb.append(charAt);
            } else if (i < length - 1) {
                i++;
                char charAt2 = charSequence.charAt(i);
                switch (charAt) {
                    case 'a':
                        if (charAt2 >= 'A' && charAt2 <= 'Z') {
                            c = (char) (charAt2 - '@');
                            break;
                        } else {
                            throw FormatException.m2059a();
                        }
                        break;
                    case 'b':
                        if (charAt2 < 'A' || charAt2 > 'E') {
                            if (charAt2 < 'F' || charAt2 > 'J') {
                                if (charAt2 < 'K' || charAt2 > 'O') {
                                    if (charAt2 < 'P' || charAt2 > 'S') {
                                        if (charAt2 >= 'T' && charAt2 <= 'Z') {
                                            c = 127;
                                            break;
                                        } else {
                                            throw FormatException.m2059a();
                                        }
                                    } else {
                                        c = (char) (charAt2 + '+');
                                        break;
                                    }
                                } else {
                                    c = (char) (charAt2 + 16);
                                    break;
                                }
                            } else {
                                c = (char) (charAt2 - 11);
                                break;
                            }
                        } else {
                            c = (char) (charAt2 - '&');
                            break;
                        }
                        break;
                    case 'c':
                        if (charAt2 >= 'A' && charAt2 <= 'O') {
                            c = (char) (charAt2 - ' ');
                            break;
                        } else if (charAt2 == 'Z') {
                            c = ':';
                            break;
                        } else {
                            throw FormatException.m2059a();
                        }
                    case 'd':
                        if (charAt2 >= 'A' && charAt2 <= 'Z') {
                            c = (char) (charAt2 + ' ');
                            break;
                        } else {
                            throw FormatException.m2059a();
                        }
                    default:
                        c = 0;
                        break;
                }
                sb.append(c);
            } else {
                throw FormatException.m2059a();
            }
            i++;
        }
        return sb.toString();
    }

    /* renamed from: b */
    private static void m2106b(CharSequence charSequence) throws ChecksumException {
        int length = charSequence.length();
        m2109a(charSequence, length - 2, 20);
        m2109a(charSequence, length - 1, 15);
    }

    /* renamed from: a */
    private static void m2109a(CharSequence charSequence, int i, int i2) throws ChecksumException {
        int i3 = 0;
        int i4 = 1;
        for (int i5 = i - 1; i5 >= 0; i5--) {
            i3 += f22353a.indexOf(charSequence.charAt(i5)) * i4;
            i4++;
            if (i4 > i2) {
                i4 = 1;
            }
        }
        if (charSequence.charAt(i) != f22355c[i3 % 47]) {
            throw ChecksumException.m2421a();
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:19:0x0060, code lost:
        r8 = p110z1.Code93Reader.f22355c[r9];
        r7.append(r8);
        r9 = r6.length;
        r12 = r4;
        r10 = 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:20:0x006a, code lost:
        if (r10 >= r9) goto L_0x0072;
     */
    /* JADX WARN: Code restructure failed: missing block: B:21:0x006c, code lost:
        r12 = r12 + r6[r10];
        r10 = r10 + 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:22:0x0072, code lost:
        r9 = r18.m2541c(r12);
     */
    /* JADX WARN: Code restructure failed: missing block: B:23:0x0078, code lost:
        if (r8 != '*') goto L_0x018f;
     */
    /* JADX WARN: Code restructure failed: missing block: B:24:0x007a, code lost:
        r7.deleteCharAt(r7.length() - 1);
        r8 = r6.length;
        r10 = 0;
        r12 = 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:25:0x0085, code lost:
        if (r10 >= r8) goto L_0x008d;
     */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x0087, code lost:
        r12 = r12 + r6[r10];
        r10 = r10 + 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:27:0x008d, code lost:
        if (r9 == r5) goto L_0x018a;
     */
    /* JADX WARN: Code restructure failed: missing block: B:29:0x0093, code lost:
        if (r18.m2551a(r9) == false) goto L_0x018a;
     */
    /* JADX WARN: Code restructure failed: missing block: B:31:0x0099, code lost:
        if (r7.length() < 2) goto L_0x0185;
     */
    /* JADX WARN: Code restructure failed: missing block: B:32:0x009b, code lost:
        r1 = r7.length();
        m2109a(r7, r1 - 2, 20);
        m2109a(r7, r1 - 1, 15);
        r7.setLength(r7.length() - 2);
        r1 = r7.length();
        r5 = new java.lang.StringBuilder(r1);
        r6 = 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:33:0x00be, code lost:
        if (r6 >= r1) goto L_0x0158;
     */
    /* JADX WARN: Code restructure failed: missing block: B:34:0x00c0, code lost:
        r8 = r7.charAt(r6);
     */
    /* JADX WARN: Code restructure failed: missing block: B:35:0x00c6, code lost:
        if (r8 < 'a') goto L_0x0152;
     */
    /* JADX WARN: Code restructure failed: missing block: B:37:0x00ca, code lost:
        if (r8 > 'd') goto L_0x0152;
     */
    /* JADX WARN: Code restructure failed: missing block: B:39:0x00ce, code lost:
        if (r6 >= (r1 - 1)) goto L_0x014d;
     */
    /* JADX WARN: Code restructure failed: missing block: B:40:0x00d0, code lost:
        r6 = r6 + 1;
        r9 = r7.charAt(r6);
     */
    /* JADX WARN: Code restructure failed: missing block: B:41:0x00dc, code lost:
        switch(r8) {
            case 97: goto L_0x013c;
            case 98: goto L_0x0102;
            case 99: goto L_0x00f0;
            case 100: goto L_0x00e2;
            default: goto L_0x00df;
        };
     */
    /* JADX WARN: Code restructure failed: missing block: B:42:0x00df, code lost:
        r8 = 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:43:0x00e2, code lost:
        if (r9 < 'A') goto L_0x00eb;
     */
    /* JADX WARN: Code restructure failed: missing block: B:44:0x00e4, code lost:
        if (r9 > 'Z') goto L_0x00eb;
     */
    /* JADX WARN: Code restructure failed: missing block: B:45:0x00e6, code lost:
        r8 = (char) (r9 + ' ');
     */
    /* JADX WARN: Code restructure failed: missing block: B:47:0x00ef, code lost:
        throw p110z1.FormatException.m2059a();
     */
    /* JADX WARN: Code restructure failed: missing block: B:48:0x00f0, code lost:
        if (r9 < 'A') goto L_0x00f8;
     */
    /* JADX WARN: Code restructure failed: missing block: B:49:0x00f2, code lost:
        if (r9 > 'O') goto L_0x00f8;
     */
    /* JADX WARN: Code restructure failed: missing block: B:50:0x00f4, code lost:
        r8 = (char) (r9 - ' ');
     */
    /* JADX WARN: Code restructure failed: missing block: B:51:0x00f8, code lost:
        if (r9 != 'Z') goto L_0x00fd;
     */
    /* JADX WARN: Code restructure failed: missing block: B:52:0x00fa, code lost:
        r8 = ':';
     */
    /* JADX WARN: Code restructure failed: missing block: B:54:0x0101, code lost:
        throw p110z1.FormatException.m2059a();
     */
    /* JADX WARN: Code restructure failed: missing block: B:55:0x0102, code lost:
        if (r9 < 'A') goto L_0x010c;
     */
    /* JADX WARN: Code restructure failed: missing block: B:57:0x0106, code lost:
        if (r9 > 'E') goto L_0x010c;
     */
    /* JADX WARN: Code restructure failed: missing block: B:58:0x0108, code lost:
        r8 = (char) (r9 - '&');
     */
    /* JADX WARN: Code restructure failed: missing block: B:60:0x010e, code lost:
        if (r9 < 'F') goto L_0x0118;
     */
    /* JADX WARN: Code restructure failed: missing block: B:62:0x0112, code lost:
        if (r9 > 'J') goto L_0x0118;
     */
    /* JADX WARN: Code restructure failed: missing block: B:63:0x0114, code lost:
        r8 = (char) (r9 - 11);
     */
    /* JADX WARN: Code restructure failed: missing block: B:65:0x011a, code lost:
        if (r9 < 'K') goto L_0x0122;
     */
    /* JADX WARN: Code restructure failed: missing block: B:66:0x011c, code lost:
        if (r9 > 'O') goto L_0x0122;
     */
    /* JADX WARN: Code restructure failed: missing block: B:67:0x011e, code lost:
        r8 = (char) (r9 + 16);
     */
    /* JADX WARN: Code restructure failed: missing block: B:69:0x0124, code lost:
        if (r9 < 'P') goto L_0x012e;
     */
    /* JADX WARN: Code restructure failed: missing block: B:71:0x0128, code lost:
        if (r9 > 'S') goto L_0x012e;
     */
    /* JADX WARN: Code restructure failed: missing block: B:72:0x012a, code lost:
        r8 = (char) (r9 + '+');
     */
    /* JADX WARN: Code restructure failed: missing block: B:74:0x0130, code lost:
        if (r9 < 'T') goto L_0x0137;
     */
    /* JADX WARN: Code restructure failed: missing block: B:75:0x0132, code lost:
        if (r9 > 'Z') goto L_0x0137;
     */
    /* JADX WARN: Code restructure failed: missing block: B:76:0x0134, code lost:
        r8 = 127;
     */
    /* JADX WARN: Code restructure failed: missing block: B:78:0x013b, code lost:
        throw p110z1.FormatException.m2059a();
     */
    /* JADX WARN: Code restructure failed: missing block: B:79:0x013c, code lost:
        if (r9 < 'A') goto L_0x0144;
     */
    /* JADX WARN: Code restructure failed: missing block: B:80:0x013e, code lost:
        if (r9 > 'Z') goto L_0x0144;
     */
    /* JADX WARN: Code restructure failed: missing block: B:81:0x0140, code lost:
        r8 = (char) (r9 - '@');
     */
    /* JADX WARN: Code restructure failed: missing block: B:83:0x0148, code lost:
        throw p110z1.FormatException.m2059a();
     */
    /* JADX WARN: Code restructure failed: missing block: B:84:0x0149, code lost:
        r5.append(r8);
     */
    /* JADX WARN: Code restructure failed: missing block: B:86:0x0151, code lost:
        throw p110z1.FormatException.m2059a();
     */
    /* JADX WARN: Code restructure failed: missing block: B:87:0x0152, code lost:
        r5.append(r8);
     */
    /* JADX WARN: Code restructure failed: missing block: B:88:0x0155, code lost:
        r6 = r6 + 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:89:0x0158, code lost:
        r9 = r17;
     */
    /* JADX WARN: Code restructure failed: missing block: B:90:0x0184, code lost:
        return new p110z1.C5422ol(r5.toString(), null, new p110z1.ResultPoint[]{new p110z1.ResultPoint((r2[1] + r2[0]) / 2.0f, r9), new p110z1.ResultPoint(r4 + (r12 / 2.0f), r9)}, p110z1.BarcodeFormat.CODE_93);
     */
    /* JADX WARN: Code restructure failed: missing block: B:92:0x0189, code lost:
        throw p110z1.NotFoundException.m1647a();
     */
    /* JADX WARN: Code restructure failed: missing block: B:94:0x018e, code lost:
        throw p110z1.NotFoundException.m1647a();
     */
    @Override // p110z1.OneDReader
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final p110z1.C5422ol mo2072a(int r17, p110z1.BitArray r18, java.util.Map<p110z1.DecodeHintType, ?> r19) throws p110z1.NotFoundException, p110z1.ChecksumException, p110z1.FormatException {
        /*
            Method dump skipped, instructions count: 470
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: p110z1.Code93Reader.mo2072a(int, z1.hu, java.util.Map):z1.ol");
    }

    /* renamed from: a */
    private int[] m2108a(BitArray huVar) throws NotFoundException {
        int i = huVar.f21908b;
        int c = huVar.m2541c(0);
        Arrays.fill(this.f22358f, 0);
        int[] iArr = this.f22358f;
        int length = iArr.length;
        int i2 = c;
        boolean z = false;
        int i3 = 0;
        while (c < i) {
            if (huVar.m2551a(c) != z) {
                iArr[i3] = iArr[i3] + 1;
            } else {
                if (i3 != length - 1) {
                    i3++;
                } else if (m2107a(iArr) == f22356d) {
                    return new int[]{i2, c};
                } else {
                    i2 += iArr[0] + iArr[1];
                    int i4 = i3 - 1;
                    System.arraycopy(iArr, 2, iArr, 0, i4);
                    iArr[i4] = 0;
                    iArr[i3] = 0;
                    i3--;
                }
                iArr[i3] = 1;
                z = !z;
            }
            c++;
        }
        throw NotFoundException.m1647a();
    }
}
