package p110z1;

import com.tencent.smtt.sdk.TbsListener;
import java.util.Map;
import org.apache.commons.p105io.IOUtils;

/* renamed from: z1.li */
/* loaded from: classes3.dex */
public final class Code39Writer extends OneDimensionalCodeWriter {
    @Override // p110z1.OneDimensionalCodeWriter, p110z1.Writer
    /* renamed from: a */
    public final BitMatrix mo1618a(String str, BarcodeFormat fuVar, int i, int i2, Map<EncodeHintType, ?> map) throws WriterException {
        if (fuVar == BarcodeFormat.CODE_39) {
            return super.mo1618a(str, fuVar, i, i2, map);
        }
        throw new IllegalArgumentException("Can only encode CODE_39, but got ".concat(String.valueOf(fuVar)));
    }

    @Override // p110z1.OneDimensionalCodeWriter
    /* renamed from: a */
    public final boolean[] mo2086a(String str) {
        int length = str.length();
        if (length <= 80) {
            int i = 0;
            while (true) {
                if (i >= length) {
                    break;
                } else if ("0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ-. $/+%".indexOf(str.charAt(i)) < 0) {
                    str = m2112b(str);
                    length = str.length();
                    if (length > 80) {
                        throw new IllegalArgumentException("Requested contents should be less than 80 digits long, but got " + length + " (extended full ASCII mode)");
                    }
                } else {
                    i++;
                }
            }
            int[] iArr = new int[9];
            int i2 = length + 25;
            int i3 = 0;
            while (i3 < length) {
                m2113a(Code39Reader.f22347b["0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ-. $/+%".indexOf(str.charAt(i3))], iArr);
                int i4 = i2;
                for (int i5 = 0; i5 < 9; i5++) {
                    i4 += iArr[i5];
                }
                i3++;
                i2 = i4;
            }
            boolean[] zArr = new boolean[i2];
            m2113a(TbsListener.ErrorCode.NEEDDOWNLOAD_9, iArr);
            int a = m2084a(zArr, 0, iArr, true);
            int[] iArr2 = {1};
            int a2 = a + m2084a(zArr, a, iArr2, false);
            for (int i6 = 0; i6 < length; i6++) {
                m2113a(Code39Reader.f22347b["0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ-. $/+%".indexOf(str.charAt(i6))], iArr);
                int a3 = a2 + m2084a(zArr, a2, iArr, true);
                a2 = a3 + m2084a(zArr, a3, iArr2, false);
            }
            m2113a(TbsListener.ErrorCode.NEEDDOWNLOAD_9, iArr);
            m2084a(zArr, a2, iArr, true);
            return zArr;
        }
        throw new IllegalArgumentException("Requested contents should be less than 80 digits long, but got ".concat(String.valueOf(length)));
    }

    /* renamed from: a */
    private static void m2113a(int i, int[] iArr) {
        for (int i2 = 0; i2 < 9; i2++) {
            int i3 = 1;
            if (((1 << (8 - i2)) & i) != 0) {
                i3 = 2;
            }
            iArr[i2] = i3;
        }
    }

    /* renamed from: b */
    private static String m2112b(String str) {
        int length = str.length();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            char charAt = str.charAt(i);
            if (charAt != 0) {
                if (charAt != ' ') {
                    if (charAt == '@') {
                        sb.append("%V");
                    } else if (charAt != '`') {
                        switch (charAt) {
                            case '-':
                            case '.':
                                break;
                            default:
                                if (charAt <= 26) {
                                    sb.append(Typography.f21050b);
                                    sb.append((char) ((charAt - 1) + 65));
                                    break;
                                } else if (charAt < ' ') {
                                    sb.append('%');
                                    sb.append((char) ((charAt - 27) + 65));
                                    break;
                                } else if (charAt <= ',' || charAt == '/' || charAt == ':') {
                                    sb.append(IOUtils.DIR_SEPARATOR_UNIX);
                                    sb.append((char) ((charAt - '!') + 65));
                                    break;
                                } else if (charAt <= '9') {
                                    sb.append((char) ((charAt - '0') + 48));
                                    break;
                                } else if (charAt <= '?') {
                                    sb.append('%');
                                    sb.append((char) ((charAt - ';') + 70));
                                    break;
                                } else if (charAt <= 'Z') {
                                    sb.append((char) ((charAt - 'A') + 65));
                                    break;
                                } else if (charAt <= '_') {
                                    sb.append('%');
                                    sb.append((char) ((charAt - '[') + 75));
                                    break;
                                } else if (charAt <= 'z') {
                                    sb.append('+');
                                    sb.append((char) ((charAt - 'a') + 65));
                                    break;
                                } else if (charAt <= 127) {
                                    sb.append('%');
                                    sb.append((char) ((charAt - '{') + 80));
                                    break;
                                } else {
                                    throw new IllegalArgumentException("Requested content contains a non-encodable character: '" + str.charAt(i) + "'");
                                }
                        }
                    } else {
                        sb.append("%W");
                    }
                }
                sb.append(charAt);
            } else {
                sb.append("%U");
            }
        }
        return sb.toString();
    }
}
