package p110z1;

import com.cyjh.ddy.media.p035a.ResultTypeConstant;
import com.cyjh.mobileanjian.ipc.share.proto.IpcCommand;
import com.tencent.smtt.sdk.TbsListener;
import java.util.Arrays;
import java.util.Map;

/* renamed from: z1.lh */
/* loaded from: classes3.dex */
public final class Code39Reader extends OneDReader {

    /* renamed from: a */
    static final String f22346a = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ-. $/+%";

    /* renamed from: b */
    static final int[] f22347b = {52, 289, 97, 352, 49, 304, 112, 37, 292, 100, 265, 73, TbsListener.ErrorCode.THROWABLE_INITTESRUNTIMEENVIRONMENT, 25, 280, 88, 13, 268, 76, 28, IpcCommand.f8360aG, 67, TbsListener.ErrorCode.ERROR_TBSINSTALLER_ISTBSCORELEGAL_02, 19, ResultTypeConstant.f7202o, 82, 7, IpcCommand.f8363aJ, 70, 22, 385, 193, 448, TbsListener.ErrorCode.NEEDDOWNLOAD_6, 400, 208, 133, 388, 196, TbsListener.ErrorCode.STARTDOWNLOAD_9, TbsListener.ErrorCode.STARTDOWNLOAD_3, 138, 42};

    /* renamed from: c */
    static final int f22348c = 148;

    /* renamed from: d */
    private final boolean f22349d;

    /* renamed from: e */
    private final boolean f22350e;

    /* renamed from: f */
    private final StringBuilder f22351f;

    /* renamed from: g */
    private final int[] f22352g;

    public Code39Reader() {
        this(false);
    }

    public Code39Reader(boolean z) {
        this(z, (byte) 0);
    }

    private Code39Reader(boolean z, byte b) {
        this.f22349d = z;
        this.f22350e = false;
        this.f22351f = new StringBuilder(20);
        this.f22352g = new int[9];
    }

    @Override // p110z1.OneDReader
    /* renamed from: a */
    public final C5422ol mo2072a(int i, BitArray huVar, Map<DecodeHintType, ?> map) throws NotFoundException, ChecksumException, FormatException {
        int[] iArr;
        char c;
        String str;
        char c2;
        int[] iArr2 = this.f22352g;
        Arrays.fill(iArr2, 0);
        StringBuilder sb = this.f22351f;
        sb.setLength(0);
        int i2 = huVar.f21908b;
        int c3 = huVar.m2541c(0);
        int length = iArr2.length;
        int i3 = c3;
        boolean z = false;
        int i4 = 0;
        while (c3 < i2) {
            if (huVar.m2551a(c3) != z) {
                iArr2[i4] = iArr2[i4] + 1;
            } else {
                if (i4 == length - 1) {
                    if (m2114a(iArr2) == 148 && huVar.m2550a(Math.max(0, i3 - ((c3 - i3) / 2)), i3)) {
                        int c4 = huVar.m2541c(new int[]{i3, c3}[1]);
                        int i5 = huVar.f21908b;
                        while (true) {
                            m2090a(huVar, c4, iArr2);
                            int a = m2114a(iArr2);
                            if (a >= 0) {
                                int i6 = 0;
                                while (true) {
                                    int[] iArr3 = f22347b;
                                    if (i6 < iArr3.length) {
                                        if (iArr3[i6] == a) {
                                            c = f22346a.charAt(i6);
                                            break;
                                        }
                                        i6++;
                                    } else if (a == 148) {
                                        c = '*';
                                    } else {
                                        throw NotFoundException.m1647a();
                                    }
                                }
                                sb.append(c);
                                int i7 = c4;
                                for (int i8 : iArr2) {
                                    i7 += i8;
                                }
                                int c5 = huVar.m2541c(i7);
                                if (c == '*') {
                                    sb.setLength(sb.length() - 1);
                                    int i9 = 0;
                                    for (int i10 : iArr2) {
                                        i9 += i10;
                                    }
                                    int i11 = (c5 - c4) - i9;
                                    if (c5 == i5 || (i11 << 1) >= i9) {
                                        char c6 = '+';
                                        if (this.f22349d) {
                                            int length2 = sb.length() - 1;
                                            int i12 = 0;
                                            for (int i13 = 0; i13 < length2; i13++) {
                                                i12 += f22346a.indexOf(this.f22351f.charAt(i13));
                                            }
                                            if (sb.charAt(length2) == f22346a.charAt(i12 % 43)) {
                                                sb.setLength(length2);
                                            } else {
                                                throw ChecksumException.m2421a();
                                            }
                                        }
                                        if (sb.length() != 0) {
                                            if (this.f22350e) {
                                                int length3 = sb.length();
                                                StringBuilder sb2 = new StringBuilder(length3);
                                                int i14 = 0;
                                                while (i14 < length3) {
                                                    char charAt = sb.charAt(i14);
                                                    if (charAt == c6 || charAt == '$' || charAt == '%' || charAt == '/') {
                                                        i14++;
                                                        char charAt2 = sb.charAt(i14);
                                                        if (charAt != c6) {
                                                            if (charAt != '/') {
                                                                switch (charAt) {
                                                                    case '$':
                                                                        if (charAt2 >= 'A' && charAt2 <= 'Z') {
                                                                            c2 = (char) (charAt2 - '@');
                                                                            break;
                                                                        } else {
                                                                            throw FormatException.m2059a();
                                                                        }
                                                                    case '%':
                                                                        if (charAt2 < 'A' || charAt2 > 'E') {
                                                                            if (charAt2 < 'F' || charAt2 > 'J') {
                                                                                if (charAt2 >= 'K' && charAt2 <= 'O') {
                                                                                    c2 = (char) (charAt2 + 16);
                                                                                    break;
                                                                                } else if (charAt2 >= 'P' && charAt2 <= 'T') {
                                                                                    c2 = (char) (charAt2 + '+');
                                                                                    break;
                                                                                } else if (charAt2 == 'U') {
                                                                                    c2 = 0;
                                                                                    break;
                                                                                } else if (charAt2 == 'V') {
                                                                                    c2 = '@';
                                                                                    break;
                                                                                } else if (charAt2 == 'W') {
                                                                                    c2 = '`';
                                                                                    break;
                                                                                } else if (charAt2 == 'X' || charAt2 == 'Y' || charAt2 == 'Z') {
                                                                                    c2 = 127;
                                                                                    break;
                                                                                } else {
                                                                                    throw FormatException.m2059a();
                                                                                }
                                                                            } else {
                                                                                c2 = (char) (charAt2 - 11);
                                                                                break;
                                                                            }
                                                                        } else {
                                                                            c2 = (char) (charAt2 - '&');
                                                                            break;
                                                                        }
                                                                        break;
                                                                    default:
                                                                        c2 = 0;
                                                                        break;
                                                                }
                                                            } else if (charAt2 >= 'A' && charAt2 <= 'O') {
                                                                c2 = (char) (charAt2 - ' ');
                                                            } else if (charAt2 == 'Z') {
                                                                c2 = ':';
                                                            } else {
                                                                throw FormatException.m2059a();
                                                            }
                                                        } else if (charAt2 < 'A' || charAt2 > 'Z') {
                                                            throw FormatException.m2059a();
                                                        } else {
                                                            c2 = (char) (charAt2 + ' ');
                                                        }
                                                        sb2.append(c2);
                                                    } else {
                                                        sb2.append(charAt);
                                                    }
                                                    i14++;
                                                    c6 = '+';
                                                }
                                                str = sb2.toString();
                                            } else {
                                                str = sb.toString();
                                            }
                                            float f = i;
                                            return new C5422ol(str, null, new ResultPoint[]{new ResultPoint((iArr[1] + iArr[0]) / 2.0f, f), new ResultPoint(c4 + (i9 / 2.0f), f)}, BarcodeFormat.CODE_39);
                                        }
                                        throw NotFoundException.m1647a();
                                    }
                                    throw NotFoundException.m1647a();
                                }
                                c4 = c5;
                            } else {
                                throw NotFoundException.m1647a();
                            }
                        }
                    }
                    i3 += iArr2[0] + iArr2[1];
                    int i15 = i4 - 1;
                    System.arraycopy(iArr2, 2, iArr2, 0, i15);
                    iArr2[i15] = 0;
                    iArr2[i4] = 0;
                    i4--;
                } else {
                    i4++;
                }
                iArr2[i4] = 1;
                z = !z;
            }
            c3++;
        }
        throw NotFoundException.m1647a();
    }

    /* JADX WARN: Code restructure failed: missing block: B:18:0x0032, code lost:
        if (r1 >= r0) goto L_0x0044;
     */
    /* JADX WARN: Code restructure failed: missing block: B:19:0x0034, code lost:
        if (r3 <= 0) goto L_0x0044;
     */
    /* JADX WARN: Code restructure failed: missing block: B:20:0x0036, code lost:
        r2 = r10[r1];
     */
    /* JADX WARN: Code restructure failed: missing block: B:21:0x0038, code lost:
        if (r2 <= r5) goto L_0x0041;
     */
    /* JADX WARN: Code restructure failed: missing block: B:22:0x003a, code lost:
        r3 = r3 - 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:23:0x003e, code lost:
        if ((r2 << 1) < r6) goto L_0x0041;
     */
    /* JADX WARN: Code restructure failed: missing block: B:24:0x0040, code lost:
        return -1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:25:0x0041, code lost:
        r1 = r1 + 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x0044, code lost:
        return r4;
     */
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static int m2114a(int[] r10) {
        /*
            int r0 = r10.length
            r1 = 0
            r2 = 0
        L_0x0003:
            r3 = 2147483647(0x7fffffff, float:NaN)
            int r4 = r10.length
            r3 = 0
            r5 = 2147483647(0x7fffffff, float:NaN)
        L_0x000b:
            if (r3 >= r4) goto L_0x0017
            r6 = r10[r3]
            if (r6 >= r5) goto L_0x0014
            if (r6 <= r2) goto L_0x0014
            r5 = r6
        L_0x0014:
            int r3 = r3 + 1
            goto L_0x000b
        L_0x0017:
            r2 = 0
            r3 = 0
            r4 = 0
            r6 = 0
        L_0x001b:
            if (r2 >= r0) goto L_0x002e
            r7 = r10[r2]
            if (r7 <= r5) goto L_0x002b
            int r8 = r0 + (-1)
            int r8 = r8 - r2
            r9 = 1
            int r8 = r9 << r8
            r4 = r4 | r8
            int r3 = r3 + 1
            int r6 = r6 + r7
        L_0x002b:
            int r2 = r2 + 1
            goto L_0x001b
        L_0x002e:
            r2 = 3
            r7 = -1
            if (r3 != r2) goto L_0x0045
        L_0x0032:
            if (r1 >= r0) goto L_0x0044
            if (r3 <= 0) goto L_0x0044
            r2 = r10[r1]
            if (r2 <= r5) goto L_0x0041
            int r3 = r3 + (-1)
            int r2 = r2 << 1
            if (r2 < r6) goto L_0x0041
            return r7
        L_0x0041:
            int r1 = r1 + 1
            goto L_0x0032
        L_0x0044:
            return r4
        L_0x0045:
            if (r3 > r2) goto L_0x0048
            return r7
        L_0x0048:
            r2 = r5
            goto L_0x0003
        */
        throw new UnsupportedOperationException("Method not decompiled: p110z1.Code39Reader.m2114a(int[]):int");
    }

    /* renamed from: a */
    private static char m2117a(int i) throws NotFoundException {
        int i2 = 0;
        while (true) {
            int[] iArr = f22347b;
            if (i2 < iArr.length) {
                if (iArr[i2] == i) {
                    return f22346a.charAt(i2);
                }
                i2++;
            } else if (i == 148) {
                return '*';
            } else {
                throw NotFoundException.m1647a();
            }
        }
    }

    /* renamed from: a */
    private static String m2116a(CharSequence charSequence) throws FormatException {
        char c;
        int length = charSequence.length();
        StringBuilder sb = new StringBuilder(length);
        int i = 0;
        while (i < length) {
            char charAt = charSequence.charAt(i);
            if (charAt == '+' || charAt == '$' || charAt == '%' || charAt == '/') {
                i++;
                char charAt2 = charSequence.charAt(i);
                if (charAt != '+') {
                    if (charAt != '/') {
                        switch (charAt) {
                            case '$':
                                if (charAt2 >= 'A' && charAt2 <= 'Z') {
                                    c = (char) (charAt2 - '@');
                                    break;
                                } else {
                                    throw FormatException.m2059a();
                                }
                                break;
                            case '%':
                                if (charAt2 < 'A' || charAt2 > 'E') {
                                    if (charAt2 < 'F' || charAt2 > 'J') {
                                        if (charAt2 < 'K' || charAt2 > 'O') {
                                            if (charAt2 >= 'P' && charAt2 <= 'T') {
                                                c = (char) (charAt2 + '+');
                                                break;
                                            } else if (charAt2 == 'U') {
                                                c = 0;
                                                break;
                                            } else if (charAt2 == 'V') {
                                                c = '@';
                                                break;
                                            } else if (charAt2 == 'W') {
                                                c = '`';
                                                break;
                                            } else if (charAt2 == 'X' || charAt2 == 'Y' || charAt2 == 'Z') {
                                                c = 127;
                                                break;
                                            } else {
                                                throw FormatException.m2059a();
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
                            default:
                                c = 0;
                                break;
                        }
                    } else if (charAt2 >= 'A' && charAt2 <= 'O') {
                        c = (char) (charAt2 - ' ');
                    } else if (charAt2 == 'Z') {
                        c = ':';
                    } else {
                        throw FormatException.m2059a();
                    }
                } else if (charAt2 < 'A' || charAt2 > 'Z') {
                    throw FormatException.m2059a();
                } else {
                    c = (char) (charAt2 + ' ');
                }
                sb.append(c);
            } else {
                sb.append(charAt);
            }
            i++;
        }
        return sb.toString();
    }

    /* renamed from: a */
    private static int[] m2115a(BitArray huVar, int[] iArr) throws NotFoundException {
        int i = huVar.f21908b;
        int c = huVar.m2541c(0);
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
                } else if (m2114a(iArr) == 148 && huVar.m2550a(Math.max(0, i2 - ((c - i2) / 2)), i2)) {
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
