package p110z1;

/* renamed from: z1.nn */
/* loaded from: classes3.dex */
public enum Mode {
    TERMINATOR(new int[]{0, 0, 0}, 0),
    NUMERIC(new int[]{10, 12, 14}, 1),
    ALPHANUMERIC(new int[]{9, 11, 13}, 2),
    STRUCTURED_APPEND(new int[]{0, 0, 0}, 3),
    BYTE(new int[]{8, 16, 16}, 4),
    ECI(new int[]{0, 0, 0}, 7),
    KANJI(new int[]{8, 10, 12}, 8),
    FNC1_FIRST_POSITION(new int[]{0, 0, 0}, 5),
    FNC1_SECOND_POSITION(new int[]{0, 0, 0}, 9),
    HANZI(new int[]{8, 10, 12}, 13);
    

    /* renamed from: k */
    public final int f22617k;

    /* renamed from: l */
    private final int[] f22618l;

    Mode(int[] iArr, int i) {
        this.f22618l = iArr;
        this.f22617k = i;
    }

    /* renamed from: a */
    public static Mode m1815a(int i) {
        switch (i) {
            case 0:
                return TERMINATOR;
            case 1:
                return NUMERIC;
            case 2:
                return ALPHANUMERIC;
            case 3:
                return STRUCTURED_APPEND;
            case 4:
                return BYTE;
            case 5:
                return FNC1_FIRST_POSITION;
            case 6:
            case 10:
            case 11:
            case 12:
            default:
                throw new IllegalArgumentException();
            case 7:
                return ECI;
            case 8:
                return KANJI;
            case 9:
                return FNC1_SECOND_POSITION;
            case 13:
                return HANZI;
        }
    }

    /* renamed from: a */
    private int m1816a() {
        return this.f22617k;
    }

    /* renamed from: a */
    public final int m1814a(C5411np npVar) {
        int i = npVar.f22622a;
        return this.f22618l[i <= 9 ? (char) 0 : i <= 26 ? (char) 1 : (char) 2];
    }
}
