package p110z1;

import com.cyjh.mobileanjian.ipc.share.proto.IpcCommand;
import com.tencent.smtt.sdk.TbsListener;
import java.util.Arrays;

/* compiled from: HighLevelEncoder.java */
/* renamed from: z1.jg */
/* loaded from: classes3.dex */
public final class C5380jg {

    /* renamed from: a */
    static final char f22092a = 230;

    /* renamed from: b */
    static final char f22093b = 231;

    /* renamed from: c */
    static final char f22094c = 235;

    /* renamed from: d */
    static final char f22095d = 238;

    /* renamed from: e */
    static final char f22096e = 239;

    /* renamed from: f */
    static final char f22097f = 240;

    /* renamed from: g */
    static final char f22098g = 254;

    /* renamed from: h */
    static final char f22099h = 254;

    /* renamed from: i */
    static final int f22100i = 0;

    /* renamed from: j */
    static final int f22101j = 1;

    /* renamed from: k */
    static final int f22102k = 2;

    /* renamed from: l */
    static final int f22103l = 3;

    /* renamed from: m */
    static final int f22104m = 4;

    /* renamed from: n */
    static final int f22105n = 5;

    /* renamed from: o */
    private static final char f22106o = 129;

    /* renamed from: p */
    private static final char f22107p = 236;

    /* renamed from: q */
    private static final char f22108q = 237;

    /* renamed from: r */
    private static final String f22109r = "[)>\u001e05\u001d";

    /* renamed from: s */
    private static final String f22110s = "[)>\u001e06\u001d";

    /* renamed from: t */
    private static final String f22111t = "\u001e\u0004";

    /* renamed from: a */
    private static boolean m2309a() {
        return false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public static boolean m2308a(char c) {
        return c >= '0' && c <= '9';
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: b */
    public static boolean m2300b(char c) {
        return c >= 128 && c <= 255;
    }

    /* renamed from: d */
    private static boolean m2298d(char c) {
        if (c == ' ') {
            return true;
        }
        if (c < '0' || c > '9') {
            return c >= 'A' && c <= 'Z';
        }
        return true;
    }

    /* renamed from: e */
    private static boolean m2297e(char c) {
        if (c == ' ') {
            return true;
        }
        if (c < '0' || c > '9') {
            return c >= 'a' && c <= 'z';
        }
        return true;
    }

    /* renamed from: g */
    private static boolean m2295g(char c) {
        return c == '\r' || c == '*' || c == '>';
    }

    /* renamed from: h */
    private static boolean m2294h(char c) {
        return c >= ' ' && c <= '^';
    }

    private C5380jg() {
    }

    /* renamed from: a */
    private static char m2307a(int i) {
        int i2 = ((i * TbsListener.ErrorCode.NEEDDOWNLOAD_10) % 253) + 1 + IpcCommand.f8356aC;
        if (i2 > 254) {
            i2 -= 254;
        }
        return (char) i2;
    }

    /* renamed from: a */
    private static String m2304a(String str) {
        return m2303a(str, SymbolShapeHint.FORCE_NONE, (C5384jq) null, (C5384jq) null);
    }

    /* renamed from: a */
    public static String m2303a(String str, SymbolShapeHint jiVar, C5384jq jqVar, C5384jq jqVar2) {
        int i = 0;
        AbstractC5379jd[] jdVarArr = {new ASCIIEncoder(), new C40Encoder(), new TextEncoder(), new X12Encoder(), new EdifactEncoder(), new Base256Encoder()};
        EncoderContext jeVar = new EncoderContext(str);
        jeVar.f22079b = jiVar;
        jeVar.f22080c = jqVar;
        jeVar.f22081d = jqVar2;
        if (str.startsWith(f22109r) && str.endsWith(f22111t)) {
            jeVar.m2331a(f22107p);
            jeVar.f22086i = 2;
            jeVar.f22083f += 7;
        } else if (str.startsWith(f22110s) && str.endsWith(f22111t)) {
            jeVar.m2331a(f22108q);
            jeVar.f22086i = 2;
            jeVar.f22083f += 7;
        }
        while (jeVar.m2326b()) {
            jdVarArr[i].mo2273a(jeVar);
            if (jeVar.f22084g >= 0) {
                i = jeVar.f22084g;
                jeVar.f22084g = -1;
            }
        }
        int length = jeVar.f22082e.length();
        jeVar.m2323d();
        int i2 = jeVar.f22085h.f22114b;
        if (!(length >= i2 || i == 0 || i == 5 || i == 4)) {
            jeVar.m2331a((char) 254);
        }
        StringBuilder sb = jeVar.f22082e;
        if (sb.length() < i2) {
            sb.append(f22106o);
        }
        while (sb.length() < i2) {
            int length2 = (((sb.length() + 1) * TbsListener.ErrorCode.NEEDDOWNLOAD_10) % 253) + 1 + IpcCommand.f8356aC;
            if (length2 > 254) {
                length2 -= 254;
            }
            sb.append((char) length2);
        }
        return jeVar.f22082e.toString();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public static int m2305a(CharSequence charSequence, int i, int i2) {
        float[] fArr;
        char c;
        if (i >= charSequence.length()) {
            return i2;
        }
        if (i2 == 0) {
            fArr = new float[]{0.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.25f};
        } else {
            fArr = new float[]{1.0f, 2.0f, 2.0f, 2.0f, 2.0f, 2.25f};
            fArr[i2] = 0.0f;
        }
        int i3 = 0;
        while (true) {
            int i4 = i + i3;
            if (i4 == charSequence.length()) {
                byte[] bArr = new byte[6];
                int[] iArr = new int[6];
                int a = m2301a(fArr, iArr, Integer.MAX_VALUE, bArr);
                int a2 = m2302a(bArr);
                if (iArr[0] == a) {
                    return 0;
                }
                if (a2 == 1 && bArr[5] > 0) {
                    return 5;
                }
                if (a2 == 1 && bArr[4] > 0) {
                    return 4;
                }
                if (a2 != 1 || bArr[2] <= 0) {
                    return (a2 != 1 || bArr[3] <= 0) ? 1 : 3;
                }
                return 2;
            }
            char charAt = charSequence.charAt(i4);
            i3++;
            if (m2308a(charAt)) {
                fArr[0] = fArr[0] + 0.5f;
            } else if (m2300b(charAt)) {
                fArr[0] = (float) Math.ceil(fArr[0]);
                fArr[0] = fArr[0] + 2.0f;
            } else {
                fArr[0] = (float) Math.ceil(fArr[0]);
                fArr[0] = fArr[0] + 1.0f;
            }
            if (charAt == ' ' || (charAt >= '0' && charAt <= '9') || (charAt >= 'A' && charAt <= 'Z')) {
                fArr[1] = fArr[1] + 0.6666667f;
            } else if (m2300b(charAt)) {
                fArr[1] = fArr[1] + 2.6666667f;
            } else {
                fArr[1] = fArr[1] + 1.3333334f;
            }
            if (charAt == ' ' || (charAt >= '0' && charAt <= '9') || (charAt >= 'a' && charAt <= 'z')) {
                fArr[2] = fArr[2] + 0.6666667f;
            } else if (m2300b(charAt)) {
                fArr[2] = fArr[2] + 2.6666667f;
            } else {
                fArr[2] = fArr[2] + 1.3333334f;
            }
            if (m2296f(charAt)) {
                fArr[3] = fArr[3] + 0.6666667f;
            } else if (m2300b(charAt)) {
                fArr[3] = fArr[3] + 4.3333335f;
            } else {
                fArr[3] = fArr[3] + 3.3333333f;
            }
            if (charAt >= ' ' && charAt <= '^') {
                fArr[4] = fArr[4] + 0.75f;
                c = 5;
            } else if (m2300b(charAt)) {
                fArr[4] = fArr[4] + 4.25f;
                c = 5;
            } else {
                fArr[4] = fArr[4] + 3.25f;
                c = 5;
            }
            fArr[c] = fArr[c] + 1.0f;
            if (i3 >= 4) {
                int[] iArr2 = new int[6];
                byte[] bArr2 = new byte[6];
                m2301a(fArr, iArr2, Integer.MAX_VALUE, bArr2);
                int a3 = m2302a(bArr2);
                if (iArr2[0] < iArr2[c] && iArr2[0] < iArr2[1] && iArr2[0] < iArr2[2] && iArr2[0] < iArr2[3] && iArr2[0] < iArr2[4]) {
                    return 0;
                }
                if (iArr2[5] < iArr2[0] || bArr2[1] + bArr2[2] + bArr2[3] + bArr2[4] == 0) {
                    return 5;
                }
                if (a3 == 1 && bArr2[4] > 0) {
                    return 4;
                }
                if (a3 == 1 && bArr2[2] > 0) {
                    return 2;
                }
                if (a3 == 1 && bArr2[3] > 0) {
                    return 3;
                }
                if (iArr2[1] + 1 < iArr2[0] && iArr2[1] + 1 < iArr2[5] && iArr2[1] + 1 < iArr2[4] && iArr2[1] + 1 < iArr2[2]) {
                    if (iArr2[1] < iArr2[3]) {
                        return 1;
                    }
                    if (iArr2[1] == iArr2[3]) {
                        for (int i5 = i + i3 + 1; i5 < charSequence.length(); i5++) {
                            char charAt2 = charSequence.charAt(i5);
                            if (m2295g(charAt2)) {
                                return 3;
                            }
                            if (!m2296f(charAt2)) {
                                break;
                            }
                        }
                        return 1;
                    }
                }
            }
        }
    }

    /* renamed from: a */
    private static int m2301a(float[] fArr, int[] iArr, int i, byte[] bArr) {
        Arrays.fill(bArr, (byte) 0);
        int i2 = i;
        for (int i3 = 0; i3 < 6; i3++) {
            iArr[i3] = (int) Math.ceil(fArr[i3]);
            int i4 = iArr[i3];
            if (i2 > i4) {
                Arrays.fill(bArr, (byte) 0);
                i2 = i4;
            }
            if (i2 == i4) {
                bArr[i3] = (byte) (bArr[i3] + 1);
            }
        }
        return i2;
    }

    /* renamed from: a */
    private static int m2302a(byte[] bArr) {
        int i = 0;
        for (int i2 = 0; i2 < 6; i2++) {
            i += bArr[i2];
        }
        return i;
    }

    /* renamed from: f */
    private static boolean m2296f(char c) {
        if (m2295g(c) || c == ' ') {
            return true;
        }
        if (c < '0' || c > '9') {
            return c >= 'A' && c <= 'Z';
        }
        return true;
    }

    /* renamed from: a */
    private static int m2306a(CharSequence charSequence, int i) {
        int length = charSequence.length();
        int i2 = 0;
        if (i < length) {
            char charAt = charSequence.charAt(i);
            while (m2308a(charAt) && i < length) {
                i2++;
                i++;
                if (i < length) {
                    charAt = charSequence.charAt(i);
                }
            }
        }
        return i2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: c */
    public static void m2299c(char c) {
        String hexString;
        throw new IllegalArgumentException("Illegal character: " + c + " (0x" + ("0000".substring(0, 4 - hexString.length()) + Integer.toHexString(c)) + ')');
    }
}
