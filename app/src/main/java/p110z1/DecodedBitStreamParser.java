package p110z1;

import android.support.p006v7.widget.helper.ItemTouchHelper;
import com.tencent.smtt.sdk.TbsListener;
import java.io.UnsupportedEncodingException;
import java.util.Collection;
import org.apache.commons.p105io.FilenameUtils;
import org.apache.commons.p105io.IOUtils;

/* renamed from: z1.is */
/* loaded from: classes3.dex */
final class DecodedBitStreamParser {

    /* renamed from: a */
    private static final char[] f22038a = {'*', '*', '*', ' ', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};

    /* renamed from: b */
    private static final char[] f22039b = {'!', Typography.f21049a, '#', Typography.f21050b, '%', Typography.f21051c, '\'', '(', ')', '*', '+', ',', '-', FilenameUtils.EXTENSION_SEPARATOR, IOUtils.DIR_SEPARATOR_UNIX, ':', ';', Typography.f21052d, '=', Typography.f21053e, '?', '@', '[', IOUtils.DIR_SEPARATOR_WINDOWS, ']', '^', '_'};

    /* renamed from: c */
    private static final char[] f22040c = {'*', '*', '*', ' ', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};

    /* renamed from: d */
    private static final char[] f22041d = f22039b;

    /* renamed from: e */
    private static final char[] f22042e = {'`', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', '{', '|', '}', '~', 127};

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Failed to restore enum class, 'enum' modifier removed */
    /* compiled from: DecodedBitStreamParser.java */
    /* renamed from: z1.is$a */
    /* loaded from: classes3.dex */
    public static final class EnumC5369a extends Enum<EnumC5369a> {

        /* renamed from: a */
        public static final int f22044a = 1;

        /* renamed from: b */
        public static final int f22045b = 2;

        /* renamed from: c */
        public static final int f22046c = 3;

        /* renamed from: d */
        public static final int f22047d = 4;

        /* renamed from: e */
        public static final int f22048e = 5;

        /* renamed from: f */
        public static final int f22049f = 6;

        /* renamed from: g */
        public static final int f22050g = 7;

        /* renamed from: h */
        private static final /* synthetic */ int[] f22051h = {f22044a, f22045b, f22046c, f22047d, f22048e, f22049f, f22050g};

        private EnumC5369a(String str, int i) {
        }

        /* renamed from: a */
        public static int[] m2395a() {
            return (int[]) f22051h.clone();
        }
    }

    private DecodedBitStreamParser() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Removed duplicated region for block: B:22:0x0056  */
    /* JADX WARN: Removed duplicated region for block: B:25:0x0066  */
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static p110z1.DecoderResult m2399a(byte[] r6) throws p110z1.FormatException {
        /*
            z1.ie r0 = new z1.ie
            r0.<init>(r6)
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r2 = 100
            r1.<init>(r2)
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r3 = 0
            r2.<init>(r3)
            java.util.ArrayList r3 = new java.util.ArrayList
            r4 = 1
            r3.<init>(r4)
            int r4 = p110z1.DecodedBitStreamParser.EnumC5369a.f22045b
        L_0x001a:
            int r5 = p110z1.DecodedBitStreamParser.EnumC5369a.f22045b
            if (r4 != r5) goto L_0x0023
            int r4 = m2401a(r0, r1, r2)
            goto L_0x0046
        L_0x0023:
            int[] r5 = p110z1.DecodedBitStreamParser.C53681.f22043a
            int r4 = r4 + (-1)
            r4 = r5[r4]
            switch(r4) {
                case 1: goto L_0x0041;
                case 2: goto L_0x003d;
                case 3: goto L_0x0039;
                case 4: goto L_0x0035;
                case 5: goto L_0x0031;
                default: goto L_0x002c;
            }
        L_0x002c:
            z1.md r6 = p110z1.FormatException.m2059a()
            throw r6
        L_0x0031:
            m2400a(r0, r1, r3)
            goto L_0x0044
        L_0x0035:
            m2396d(r0, r1)
            goto L_0x0044
        L_0x0039:
            m2397c(r0, r1)
            goto L_0x0044
        L_0x003d:
            m2398b(r0, r1)
            goto L_0x0044
        L_0x0041:
            m2402a(r0, r1)
        L_0x0044:
            int r4 = p110z1.DecodedBitStreamParser.EnumC5369a.f22045b
        L_0x0046:
            int r5 = p110z1.DecodedBitStreamParser.EnumC5369a.f22044a
            if (r4 == r5) goto L_0x0050
            int r5 = r0.m2467a()
            if (r5 > 0) goto L_0x001a
        L_0x0050:
            int r0 = r2.length()
            if (r0 <= 0) goto L_0x0059
            r1.append(r2)
        L_0x0059:
            z1.ig r0 = new z1.ig
            java.lang.String r1 = r1.toString()
            boolean r2 = r3.isEmpty()
            r4 = 0
            if (r2 == 0) goto L_0x0067
            r3 = r4
        L_0x0067:
            r0.<init>(r6, r1, r3, r4)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: p110z1.DecodedBitStreamParser.m2399a(byte[]):z1.ig");
    }

    /* compiled from: DecodedBitStreamParser.java */
    /* renamed from: z1.is$1 */
    /* loaded from: classes3.dex */
    static /* synthetic */ class C53681 {

        /* renamed from: a */
        static final /* synthetic */ int[] f22043a = new int[EnumC5369a.m2395a().length];

        static {
            try {
                f22043a[EnumC5369a.f22046c - 1] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f22043a[EnumC5369a.f22047d - 1] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f22043a[EnumC5369a.f22048e - 1] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f22043a[EnumC5369a.f22049f - 1] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f22043a[EnumC5369a.f22050g - 1] = 5;
            } catch (NoSuchFieldError unused5) {
            }
        }
    }

    /* renamed from: a */
    private static int m2401a(BitSource ieVar, StringBuilder sb, StringBuilder sb2) throws FormatException {
        boolean z = false;
        do {
            int a = ieVar.m2466a(8);
            if (a != 0) {
                if (a > 128) {
                    if (a != 129) {
                        if (a > 229) {
                            switch (a) {
                                case TbsListener.ErrorCode.RENAME_SUCCESS /* 230 */:
                                    return EnumC5369a.f22046c;
                                case TbsListener.ErrorCode.RENAME_FAIL /* 231 */:
                                    return EnumC5369a.f22050g;
                                case TbsListener.ErrorCode.INSTALL_SUCCESS_AND_RELEASE_LOCK /* 232 */:
                                    sb.append((char) 29);
                                    break;
                                case TbsListener.ErrorCode.DECOUPLE_INSTLL_SUCCESS /* 233 */:
                                case TbsListener.ErrorCode.DECOUPLE_INCURUPDATE_SUCCESS /* 234 */:
                                case TbsListener.ErrorCode.TPATCH_BACKUP_NOT_VALID /* 241 */:
                                    break;
                                case TbsListener.ErrorCode.DECOUPLE_INCURUPDATE_FAIL /* 235 */:
                                    z = true;
                                    break;
                                case TbsListener.ErrorCode.TPATCH_INSTALL_SUCCESS /* 236 */:
                                    sb.append("[)>\u001e05\u001d");
                                    sb2.insert(0, "\u001e\u0004");
                                    break;
                                case TbsListener.ErrorCode.DECOUPLE_TPATCH_INSTALL_SUCCESS /* 237 */:
                                    sb.append("[)>\u001e06\u001d");
                                    sb2.insert(0, "\u001e\u0004");
                                    break;
                                case TbsListener.ErrorCode.TPATCH_FAIL /* 238 */:
                                    return EnumC5369a.f22048e;
                                case TbsListener.ErrorCode.DECOUPLE_TPATCH_FAIL /* 239 */:
                                    return EnumC5369a.f22047d;
                                case TbsListener.ErrorCode.TPATCH_VERSION_FAILED /* 240 */:
                                    return EnumC5369a.f22049f;
                                default:
                                    if (!(a == 254 && ieVar.m2467a() == 0)) {
                                        throw FormatException.m2059a();
                                    }
                                    break;
                            }
                        } else {
                            int i = a - 130;
                            if (i < 10) {
                                sb.append('0');
                            }
                            sb.append(i);
                        }
                    } else {
                        return EnumC5369a.f22044a;
                    }
                } else {
                    if (z) {
                        a += 128;
                    }
                    sb.append((char) (a - 1));
                    return EnumC5369a.f22045b;
                }
            } else {
                throw FormatException.m2059a();
            }
        } while (ieVar.m2467a() > 0);
        return EnumC5369a.f22045b;
    }

    /* renamed from: a */
    private static void m2402a(BitSource ieVar, StringBuilder sb) throws FormatException {
        int a;
        int[] iArr = new int[3];
        boolean z = false;
        int i = 0;
        while (ieVar.m2467a() != 8 && (a = ieVar.m2466a(8)) != 254) {
            m2403a(a, ieVar.m2466a(8), iArr);
            for (int i2 = 0; i2 < 3; i2++) {
                int i3 = iArr[i2];
                switch (i) {
                    case 0:
                        if (i3 < 3) {
                            i = i3 + 1;
                            break;
                        } else {
                            char[] cArr = f22038a;
                            if (i3 < cArr.length) {
                                char c = cArr[i3];
                                if (z) {
                                    sb.append((char) (c + 128));
                                    z = false;
                                    break;
                                } else {
                                    sb.append(c);
                                    break;
                                }
                            } else {
                                throw FormatException.m2059a();
                            }
                        }
                    case 1:
                        if (z) {
                            sb.append((char) (i3 + 128));
                            z = false;
                        } else {
                            sb.append((char) i3);
                        }
                        i = 0;
                        break;
                    case 2:
                        char[] cArr2 = f22039b;
                        if (i3 < cArr2.length) {
                            char c2 = cArr2[i3];
                            if (z) {
                                sb.append((char) (c2 + 128));
                                z = false;
                                i = 0;
                                break;
                            } else {
                                sb.append(c2);
                                z = z;
                                i = 0;
                            }
                        } else if (i3 == 27) {
                            sb.append((char) 29);
                            z = z;
                            i = 0;
                        } else if (i3 == 30) {
                            z = true;
                            i = 0;
                        } else {
                            throw FormatException.m2059a();
                        }
                    case 3:
                        if (z) {
                            sb.append((char) (i3 + TbsListener.ErrorCode.EXCEED_INCR_UPDATE));
                            z = false;
                        } else {
                            sb.append((char) (i3 + 96));
                        }
                        i = 0;
                        break;
                    default:
                        throw FormatException.m2059a();
                }
            }
            if (ieVar.m2467a() > 0) {
                z = z;
            } else {
                return;
            }
        }
    }

    /* renamed from: b */
    private static void m2398b(BitSource ieVar, StringBuilder sb) throws FormatException {
        int a;
        int[] iArr = new int[3];
        boolean z = false;
        int i = 0;
        while (ieVar.m2467a() != 8 && (a = ieVar.m2466a(8)) != 254) {
            m2403a(a, ieVar.m2466a(8), iArr);
            for (int i2 = 0; i2 < 3; i2++) {
                int i3 = iArr[i2];
                switch (i) {
                    case 0:
                        if (i3 < 3) {
                            i = i3 + 1;
                            break;
                        } else {
                            char[] cArr = f22040c;
                            if (i3 < cArr.length) {
                                char c = cArr[i3];
                                if (z) {
                                    sb.append((char) (c + 128));
                                    z = false;
                                    break;
                                } else {
                                    sb.append(c);
                                    break;
                                }
                            } else {
                                throw FormatException.m2059a();
                            }
                        }
                    case 1:
                        if (z) {
                            sb.append((char) (i3 + 128));
                            z = false;
                        } else {
                            sb.append((char) i3);
                        }
                        i = 0;
                        break;
                    case 2:
                        char[] cArr2 = f22041d;
                        if (i3 < cArr2.length) {
                            char c2 = cArr2[i3];
                            if (z) {
                                sb.append((char) (c2 + 128));
                                z = false;
                                i = 0;
                                break;
                            } else {
                                sb.append(c2);
                                z = z;
                                i = 0;
                            }
                        } else if (i3 == 27) {
                            sb.append((char) 29);
                            z = z;
                            i = 0;
                        } else if (i3 == 30) {
                            z = true;
                            i = 0;
                        } else {
                            throw FormatException.m2059a();
                        }
                    case 3:
                        char[] cArr3 = f22042e;
                        if (i3 < cArr3.length) {
                            char c3 = cArr3[i3];
                            if (z) {
                                sb.append((char) (c3 + 128));
                                z = false;
                            } else {
                                sb.append(c3);
                            }
                            i = 0;
                            break;
                        } else {
                            throw FormatException.m2059a();
                        }
                    default:
                        throw FormatException.m2059a();
                }
            }
            if (ieVar.m2467a() > 0) {
                z = z;
            } else {
                return;
            }
        }
    }

    /* renamed from: c */
    private static void m2397c(BitSource ieVar, StringBuilder sb) throws FormatException {
        int a;
        int[] iArr = new int[3];
        while (ieVar.m2467a() != 8 && (a = ieVar.m2466a(8)) != 254) {
            m2403a(a, ieVar.m2466a(8), iArr);
            for (int i = 0; i < 3; i++) {
                int i2 = iArr[i];
                switch (i2) {
                    case 0:
                        sb.append('\r');
                        break;
                    case 1:
                        sb.append('*');
                        break;
                    case 2:
                        sb.append(Typography.f21053e);
                        break;
                    case 3:
                        sb.append(' ');
                        break;
                    default:
                        if (i2 < 14) {
                            sb.append((char) (i2 + 44));
                            break;
                        } else if (i2 < 40) {
                            sb.append((char) (i2 + 51));
                            break;
                        } else {
                            throw FormatException.m2059a();
                        }
                }
            }
            if (ieVar.m2467a() <= 0) {
                return;
            }
        }
    }

    /* renamed from: a */
    private static void m2403a(int i, int i2, int[] iArr) {
        int i3 = ((i << 8) + i2) - 1;
        int i4 = i3 / 1600;
        iArr[0] = i4;
        int i5 = i3 - (i4 * 1600);
        int i6 = i5 / 40;
        iArr[1] = i6;
        iArr[2] = i5 - (i6 * 40);
    }

    /* renamed from: d */
    private static void m2396d(BitSource ieVar, StringBuilder sb) {
        while (ieVar.m2467a() > 16) {
            for (int i = 0; i < 4; i++) {
                int a = ieVar.m2466a(6);
                if (a == 31) {
                    int i2 = 8 - ieVar.f21955b;
                    if (i2 != 8) {
                        ieVar.m2466a(i2);
                        return;
                    }
                    return;
                }
                if ((a & 32) == 0) {
                    a |= 64;
                }
                sb.append((char) a);
            }
            if (ieVar.m2467a() <= 0) {
                return;
            }
        }
    }

    /* renamed from: a */
    private static int m2404a(int i, int i2) {
        int i3 = i - (((i2 * TbsListener.ErrorCode.NEEDDOWNLOAD_10) % 255) + 1);
        return i3 >= 0 ? i3 : i3 + 256;
    }

    /* renamed from: a */
    private static void m2400a(BitSource ieVar, StringBuilder sb, Collection<byte[]> collection) throws FormatException {
        int i = ieVar.f21954a + 1;
        int i2 = i + 1;
        int a = m2404a(ieVar.m2466a(8), i);
        if (a == 0) {
            a = ieVar.m2467a() / 8;
        } else if (a >= 250) {
            i2++;
            a = ((a - 249) * ItemTouchHelper.Callback.DEFAULT_SWIPE_ANIMATION_DURATION) + m2404a(ieVar.m2466a(8), i2);
        }
        if (a >= 0) {
            byte[] bArr = new byte[a];
            for (int i3 = 0; i3 < a; i3++) {
                if (ieVar.m2467a() >= 8) {
                    i2++;
                    bArr[i3] = (byte) m2404a(ieVar.m2466a(8), i2);
                } else {
                    throw FormatException.m2059a();
                }
            }
            collection.add(bArr);
            try {
                sb.append(new String(bArr, "ISO8859_1"));
            } catch (UnsupportedEncodingException e) {
                throw new IllegalStateException("Platform does not support required encoding: ".concat(String.valueOf(e)));
            }
        } else {
            throw FormatException.m2059a();
        }
    }
}
