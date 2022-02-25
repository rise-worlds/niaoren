package p110z1;

import com.tencent.smtt.sdk.TbsListener;
import java.util.ArrayList;
import java.util.Map;

/* renamed from: z1.lg */
/* loaded from: classes3.dex */
public final class Code128Writer extends OneDimensionalCodeWriter {

    /* renamed from: a */
    private static final int f22325a = 103;

    /* renamed from: b */
    private static final int f22326b = 104;

    /* renamed from: c */
    private static final int f22327c = 105;

    /* renamed from: d */
    private static final int f22328d = 101;

    /* renamed from: e */
    private static final int f22329e = 100;

    /* renamed from: f */
    private static final int f22330f = 99;

    /* renamed from: g */
    private static final int f22331g = 106;

    /* renamed from: h */
    private static final char f22332h = 241;

    /* renamed from: i */
    private static final char f22333i = 242;

    /* renamed from: j */
    private static final char f22334j = 243;

    /* renamed from: k */
    private static final char f22335k = 244;

    /* renamed from: l */
    private static final int f22336l = 102;

    /* renamed from: m */
    private static final int f22337m = 97;

    /* renamed from: n */
    private static final int f22338n = 96;

    /* renamed from: o */
    private static final int f22339o = 101;

    /* renamed from: p */
    private static final int f22340p = 100;

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Failed to restore enum class, 'enum' modifier removed */
    /* compiled from: Code128Writer.java */
    /* renamed from: z1.lg$a */
    /* loaded from: classes3.dex */
    public static final class EnumC5391a extends Enum<EnumC5391a> {

        /* renamed from: a */
        public static final int f22341a = 1;

        /* renamed from: b */
        public static final int f22342b = 2;

        /* renamed from: c */
        public static final int f22343c = 3;

        /* renamed from: d */
        public static final int f22344d = 4;

        /* renamed from: e */
        private static final /* synthetic */ int[] f22345e = {f22341a, f22342b, f22343c, f22344d};

        private EnumC5391a(String str, int i) {
        }

        /* renamed from: a */
        private static int[] m2118a() {
            return (int[]) f22345e.clone();
        }
    }

    @Override // p110z1.OneDimensionalCodeWriter, p110z1.Writer
    /* renamed from: a */
    public final BitMatrix mo1618a(String str, BarcodeFormat fuVar, int i, int i2, Map<EncodeHintType, ?> map) throws WriterException {
        if (fuVar == BarcodeFormat.CODE_128) {
            return super.mo1618a(str, fuVar, i, i2, map);
        }
        throw new IllegalArgumentException("Can only encode CODE_128, but got ".concat(String.valueOf(fuVar)));
    }

    @Override // p110z1.OneDimensionalCodeWriter
    /* renamed from: a */
    public final boolean[] mo2086a(String str) {
        int length = str.length();
        if (length <= 0 || length > 80) {
            throw new IllegalArgumentException("Contents length should be between 1 and 80 characters, but got ".concat(String.valueOf(length)));
        }
        int i = 0;
        for (int i2 = 0; i2 < length; i2++) {
            char charAt = str.charAt(i2);
            switch (charAt) {
                case TbsListener.ErrorCode.TPATCH_BACKUP_NOT_VALID /* 241 */:
                case TbsListener.ErrorCode.TPATCH_ENABLE_EXCEPTION /* 242 */:
                case 243:
                case 244:
                    break;
                default:
                    if (charAt <= 127) {
                        break;
                    } else {
                        throw new IllegalArgumentException("Bad character in input: ".concat(String.valueOf(charAt)));
                    }
            }
        }
        ArrayList<int[]> arrayList = new ArrayList();
        int i3 = 0;
        int i4 = 0;
        int i5 = 0;
        int i6 = 1;
        while (true) {
            int i7 = 103;
            if (i3 < length) {
                int a = m2119a(str, i3, i5);
                if (a == i5) {
                    i7 = 101;
                    switch (str.charAt(i3)) {
                        case TbsListener.ErrorCode.TPATCH_BACKUP_NOT_VALID /* 241 */:
                            i7 = 102;
                            break;
                        case TbsListener.ErrorCode.TPATCH_ENABLE_EXCEPTION /* 242 */:
                            i7 = 97;
                            break;
                        case 243:
                            i7 = 96;
                            break;
                        case 244:
                            if (i5 != 101) {
                                i7 = 100;
                                break;
                            }
                            break;
                        default:
                            switch (i5) {
                                case 100:
                                    i7 = str.charAt(i3) - ' ';
                                    break;
                                case 101:
                                    i7 = str.charAt(i3) - ' ';
                                    if (i7 < 0) {
                                        i7 += 96;
                                        break;
                                    }
                                    break;
                                default:
                                    i7 = Integer.parseInt(str.substring(i3, i3 + 2));
                                    i3++;
                                    break;
                            }
                    }
                    i3++;
                } else {
                    if (i5 == 0) {
                        switch (a) {
                            case 100:
                                i7 = 104;
                                break;
                            case 101:
                                break;
                            default:
                                i7 = 105;
                                break;
                        }
                    } else {
                        i7 = a;
                    }
                    i5 = a;
                }
                arrayList.add(Code128Reader.f22309a[i7]);
                i4 += i7 * i6;
                if (i3 != 0) {
                    i6++;
                }
            } else {
                arrayList.add(Code128Reader.f22309a[i4 % 103]);
                arrayList.add(Code128Reader.f22309a[106]);
                int i8 = 0;
                for (int[] iArr : arrayList) {
                    int i9 = i8;
                    for (int i10 : iArr) {
                        i9 += i10;
                    }
                    i8 = i9;
                }
                boolean[] zArr = new boolean[i8];
                for (int[] iArr2 : arrayList) {
                    i += m2084a(zArr, i, iArr2, true);
                }
                return zArr;
            }
        }
    }

    /* renamed from: a */
    private static int m2120a(CharSequence charSequence, int i) {
        int length = charSequence.length();
        if (i >= length) {
            return EnumC5391a.f22341a;
        }
        char charAt = charSequence.charAt(i);
        if (charAt == 241) {
            return EnumC5391a.f22344d;
        }
        if (charAt < '0' || charAt > '9') {
            return EnumC5391a.f22341a;
        }
        int i2 = i + 1;
        if (i2 >= length) {
            return EnumC5391a.f22342b;
        }
        char charAt2 = charSequence.charAt(i2);
        if (charAt2 < '0' || charAt2 > '9') {
            return EnumC5391a.f22342b;
        }
        return EnumC5391a.f22343c;
    }

    /* renamed from: a */
    private static int m2119a(CharSequence charSequence, int i, int i2) {
        int a;
        int a2;
        char charAt;
        int a3 = m2120a(charSequence, i);
        if (a3 == EnumC5391a.f22342b) {
            return 100;
        }
        if (a3 == EnumC5391a.f22341a) {
            return (i >= charSequence.length() || ((charAt = charSequence.charAt(i)) >= ' ' && (i2 != 101 || charAt >= '`'))) ? 100 : 101;
        }
        if (i2 == 99) {
            return 99;
        }
        if (i2 != 100) {
            if (a3 == EnumC5391a.f22344d) {
                a3 = m2120a(charSequence, i + 1);
            }
            return a3 == EnumC5391a.f22343c ? 99 : 100;
        } else if (a3 == EnumC5391a.f22344d || (a = m2120a(charSequence, i + 2)) == EnumC5391a.f22341a || a == EnumC5391a.f22342b) {
            return 100;
        } else {
            if (a == EnumC5391a.f22344d) {
                return m2120a(charSequence, i + 3) == EnumC5391a.f22343c ? 99 : 100;
            }
            int i3 = i + 4;
            while (true) {
                a2 = m2120a(charSequence, i3);
                if (a2 != EnumC5391a.f22343c) {
                    break;
                }
                i3 += 2;
            }
            return a2 == EnumC5391a.f22342b ? 100 : 99;
        }
    }
}
