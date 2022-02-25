package p110z1;

import com.tencent.smtt.sdk.TbsListener;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

/* compiled from: DecodedBitStreamParser.java */
/* renamed from: z1.nj */
/* loaded from: classes3.dex */
final class C5408nj {

    /* renamed from: a */
    private static final char[] f22591a = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ $%*+-./:".toCharArray();

    /* renamed from: b */
    private static final int f22592b = 1;

    private C5408nj() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public static DecoderResult m1832a(byte[] bArr, C5411np npVar, ErrorCorrectionLevel nlVar, Map<DecodeHintType, ?> map) throws FormatException {
        Mode nnVar;
        Mode nnVar2;
        int i;
        BitSource ieVar = new BitSource(bArr);
        StringBuilder sb = new StringBuilder(50);
        int i2 = 1;
        ArrayList arrayList = new ArrayList(1);
        CharacterSetECI ifVar = null;
        boolean z = false;
        int i3 = -1;
        int i4 = -1;
        while (true) {
            try {
                if (ieVar.m2467a() < 4) {
                    nnVar = Mode.TERMINATOR;
                } else {
                    nnVar = Mode.m1815a(ieVar.m2466a(4));
                }
                switch (nnVar) {
                    case TERMINATOR:
                        nnVar2 = nnVar;
                        break;
                    case FNC1_FIRST_POSITION:
                    case FNC1_SECOND_POSITION:
                        nnVar2 = nnVar;
                        z = true;
                        break;
                    case STRUCTURED_APPEND:
                        if (ieVar.m2467a() >= 16) {
                            i3 = ieVar.m2466a(8);
                            i4 = ieVar.m2466a(8);
                            nnVar2 = nnVar;
                            break;
                        } else {
                            throw FormatException.m2059a();
                        }
                    case ECI:
                        int a = ieVar.m2466a(8);
                        if ((a & 128) == 0) {
                            i = a & 127;
                        } else if ((a & 192) == 128) {
                            i = ((a & 63) << 8) | ieVar.m2466a(8);
                        } else if ((a & TbsListener.ErrorCode.EXCEED_INCR_UPDATE) == 192) {
                            i = ((a & 31) << 16) | ieVar.m2466a(16);
                        } else {
                            throw FormatException.m2059a();
                        }
                        ifVar = CharacterSetECI.m2462a(i);
                        if (ifVar != null) {
                            nnVar2 = nnVar;
                            break;
                        } else {
                            throw FormatException.m2059a();
                        }
                    case HANZI:
                        int a2 = ieVar.m2466a(4);
                        int a3 = ieVar.m2466a(nnVar.m1814a(npVar));
                        if (a2 != i2) {
                            nnVar2 = nnVar;
                            break;
                        } else {
                            m1835a(ieVar, sb, a3);
                            nnVar2 = nnVar;
                            break;
                        }
                    default:
                        int a4 = ieVar.m2466a(nnVar.m1814a(npVar));
                        switch (nnVar) {
                            case NUMERIC:
                                nnVar2 = nnVar;
                                m1830c(ieVar, sb, a4);
                                break;
                            case ALPHANUMERIC:
                                nnVar2 = nnVar;
                                m1833a(ieVar, sb, a4, z);
                                break;
                            case BYTE:
                                nnVar2 = nnVar;
                                m1834a(ieVar, sb, a4, ifVar, arrayList, map);
                                break;
                            case KANJI:
                                m1831b(ieVar, sb, a4);
                                nnVar2 = nnVar;
                                break;
                            default:
                                throw FormatException.m2059a();
                        }
                }
                if (nnVar2 == Mode.TERMINATOR) {
                    return new DecoderResult(bArr, sb.toString(), arrayList.isEmpty() ? null : arrayList, nlVar == null ? null : nlVar.toString(), i3, i4);
                }
                i2 = 1;
            } catch (IllegalArgumentException unused) {
                throw FormatException.m2059a();
            }
        }
    }

    /* renamed from: a */
    private static void m1835a(BitSource ieVar, StringBuilder sb, int i) throws FormatException {
        if (i * 13 <= ieVar.m2467a()) {
            byte[] bArr = new byte[i * 2];
            int i2 = 0;
            while (i > 0) {
                int a = ieVar.m2466a(13);
                int i3 = (a % 96) | ((a / 96) << 8);
                int i4 = i3 + (i3 < 959 ? 41377 : 42657);
                bArr[i2] = (byte) (i4 >> 8);
                bArr[i2 + 1] = (byte) i4;
                i2 += 2;
                i--;
            }
            try {
                sb.append(new String(bArr, C5367in.f22024b));
            } catch (UnsupportedEncodingException unused) {
                throw FormatException.m2059a();
            }
        } else {
            throw FormatException.m2059a();
        }
    }

    /* renamed from: b */
    private static void m1831b(BitSource ieVar, StringBuilder sb, int i) throws FormatException {
        if (i * 13 <= ieVar.m2467a()) {
            byte[] bArr = new byte[i * 2];
            int i2 = 0;
            while (i > 0) {
                int a = ieVar.m2466a(13);
                int i3 = (a % 192) | ((a / 192) << 8);
                int i4 = i3 + (i3 < 7936 ? 33088 : 49472);
                bArr[i2] = (byte) (i4 >> 8);
                bArr[i2 + 1] = (byte) i4;
                i2 += 2;
                i--;
            }
            try {
                sb.append(new String(bArr, C5367in.f22023a));
            } catch (UnsupportedEncodingException unused) {
                throw FormatException.m2059a();
            }
        } else {
            throw FormatException.m2059a();
        }
    }

    /* renamed from: a */
    private static void m1834a(BitSource ieVar, StringBuilder sb, int i, CharacterSetECI ifVar, Collection<byte[]> collection, Map<DecodeHintType, ?> map) throws FormatException {
        String str;
        if ((i << 3) <= ieVar.m2467a()) {
            byte[] bArr = new byte[i];
            for (int i2 = 0; i2 < i; i2++) {
                bArr[i2] = (byte) ieVar.m2466a(8);
            }
            if (ifVar == null) {
                str = C5367in.m2422a(bArr, map);
            } else {
                str = ifVar.name();
            }
            try {
                sb.append(new String(bArr, str));
                collection.add(bArr);
            } catch (UnsupportedEncodingException unused) {
                throw FormatException.m2059a();
            }
        } else {
            throw FormatException.m2059a();
        }
    }

    /* renamed from: a */
    private static char m1837a(int i) throws FormatException {
        char[] cArr = f22591a;
        if (i < cArr.length) {
            return cArr[i];
        }
        throw FormatException.m2059a();
    }

    /* renamed from: a */
    private static void m1833a(BitSource ieVar, StringBuilder sb, int i, boolean z) throws FormatException {
        while (i > 1) {
            if (ieVar.m2467a() >= 11) {
                int a = ieVar.m2466a(11);
                sb.append(m1837a(a / 45));
                sb.append(m1837a(a % 45));
                i -= 2;
            } else {
                throw FormatException.m2059a();
            }
        }
        if (i == 1) {
            if (ieVar.m2467a() >= 6) {
                sb.append(m1837a(ieVar.m2466a(6)));
            } else {
                throw FormatException.m2059a();
            }
        }
        if (z) {
            for (int length = sb.length(); length < sb.length(); length++) {
                if (sb.charAt(length) == '%') {
                    if (length < sb.length() - 1) {
                        int i2 = length + 1;
                        if (sb.charAt(i2) == '%') {
                            sb.deleteCharAt(i2);
                        }
                    }
                    sb.setCharAt(length, (char) 29);
                }
            }
        }
    }

    /* renamed from: c */
    private static void m1830c(BitSource ieVar, StringBuilder sb, int i) throws FormatException {
        while (i >= 3) {
            if (ieVar.m2467a() >= 10) {
                int a = ieVar.m2466a(10);
                if (a < 1000) {
                    sb.append(m1837a(a / 100));
                    sb.append(m1837a((a / 10) % 10));
                    sb.append(m1837a(a % 10));
                    i -= 3;
                } else {
                    throw FormatException.m2059a();
                }
            } else {
                throw FormatException.m2059a();
            }
        }
        if (i == 2) {
            if (ieVar.m2467a() >= 7) {
                int a2 = ieVar.m2466a(7);
                if (a2 < 100) {
                    sb.append(m1837a(a2 / 10));
                    sb.append(m1837a(a2 % 10));
                    return;
                }
                throw FormatException.m2059a();
            }
            throw FormatException.m2059a();
        } else if (i != 1) {
        } else {
            if (ieVar.m2467a() >= 4) {
                int a3 = ieVar.m2466a(4);
                if (a3 < 10) {
                    sb.append(m1837a(a3));
                    return;
                }
                throw FormatException.m2059a();
            }
            throw FormatException.m2059a();
        }
    }

    /* renamed from: a */
    private static int m1836a(BitSource ieVar) throws FormatException {
        int a = ieVar.m2466a(8);
        if ((a & 128) == 0) {
            return a & 127;
        }
        if ((a & 192) == 128) {
            return ieVar.m2466a(8) | ((a & 63) << 8);
        }
        if ((a & TbsListener.ErrorCode.EXCEED_INCR_UPDATE) == 192) {
            return ieVar.m2466a(16) | ((a & 31) << 16);
        }
        throw FormatException.m2059a();
    }
}
