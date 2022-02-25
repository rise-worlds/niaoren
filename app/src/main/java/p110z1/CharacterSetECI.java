package p110z1;

import com.tencent.smtt.sdk.TbsListener;
import java.util.HashMap;
import java.util.Map;

/* renamed from: z1.if */
/* loaded from: classes3.dex */
public enum CharacterSetECI {
    Cp437(new int[]{0, 2}, new String[0]),
    ISO8859_1(new int[]{1, 3}, "ISO-8859-1"),
    ISO8859_2(4, "ISO-8859-2"),
    ISO8859_3(5, "ISO-8859-3"),
    ISO8859_4(6, "ISO-8859-4"),
    ISO8859_5(7, "ISO-8859-5"),
    ISO8859_6(8, "ISO-8859-6"),
    ISO8859_7(9, "ISO-8859-7"),
    ISO8859_8(10, "ISO-8859-8"),
    ISO8859_9(11, "ISO-8859-9"),
    ISO8859_10(12, "ISO-8859-10"),
    ISO8859_11(13, "ISO-8859-11"),
    ISO8859_13(15, "ISO-8859-13"),
    ISO8859_14(16, "ISO-8859-14"),
    ISO8859_15(17, "ISO-8859-15"),
    ISO8859_16(18, "ISO-8859-16"),
    SJIS(20, "Shift_JIS"),
    Cp1250(21, "windows-1250"),
    Cp1251(22, "windows-1251"),
    Cp1252(23, "windows-1252"),
    Cp1256(24, "windows-1256"),
    UnicodeBigUnmarked(25, "UTF-16BE", "UnicodeBig"),
    UTF8(26, "UTF-8"),
    ASCII(new int[]{27, TbsListener.ErrorCode.NEEDDOWNLOAD_TRUE}, "US-ASCII"),
    Big5,
    GB18030(29, C5367in.f22024b, "EUC_CN", "GBK"),
    EUC_KR(30, "EUC-KR");
    

    /* renamed from: C */
    private static final Map<Integer, CharacterSetECI> f21958C = new HashMap();

    /* renamed from: D */
    private static final Map<String, CharacterSetECI> f21959D = new HashMap();

    /* renamed from: B */
    public final int[] f21987B;

    /* renamed from: E */
    private final String[] f21988E;

    static {
        CharacterSetECI[] values;
        for (CharacterSetECI ifVar : values()) {
            for (int i : ifVar.f21987B) {
                f21958C.put(Integer.valueOf(i), ifVar);
            }
            f21959D.put(ifVar.name(), ifVar);
            for (String str : ifVar.f21988E) {
                f21959D.put(str, ifVar);
            }
        }
    }

    /* JADX WARN: Incorrect types in method signature: (I)V */
    CharacterSetECI(String str) {
        this(new int[]{28}, new String[0]);
    }

    CharacterSetECI(int i, String... strArr) {
        this.f21987B = new int[]{i};
        this.f21988E = strArr;
    }

    CharacterSetECI(int[] iArr, String... strArr) {
        this.f21987B = iArr;
        this.f21988E = strArr;
    }

    /* renamed from: a */
    private int m2463a() {
        return this.f21987B[0];
    }

    /* renamed from: a */
    public static CharacterSetECI m2462a(int i) throws FormatException {
        if (i >= 0 && i < 900) {
            return f21958C.get(Integer.valueOf(i));
        }
        throw FormatException.m2059a();
    }

    /* renamed from: a */
    public static CharacterSetECI m2461a(String str) {
        return f21959D.get(str);
    }
}
