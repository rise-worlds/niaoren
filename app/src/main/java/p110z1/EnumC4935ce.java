package p110z1;

/* renamed from: z1.ce */
/* loaded from: classes3.dex */
public enum EnumC4935ce {
    WIFI(0, "WIFI"),
    NETWORK_TYPE_1(1, "unicom2G"),
    NETWORK_TYPE_2(2, "mobile2G"),
    NETWORK_TYPE_4(4, "telecom2G"),
    NETWORK_TYPE_5(5, "telecom3G"),
    NETWORK_TYPE_6(6, "telecom3G"),
    NETWORK_TYPE_12(12, "telecom3G"),
    NETWORK_TYPE_8(8, "unicom3G"),
    NETWORK_TYPE_3(3, "unicom3G"),
    NETWORK_TYPE_13(13, "LTE"),
    NETWORK_TYPE_11(11, "IDEN"),
    NETWORK_TYPE_9(9, "HSUPA"),
    NETWORK_TYPE_10(10, "HSPA"),
    NETWORK_TYPE_15(15, "HSPAP"),
    NONE(-1, "none");
    

    /* renamed from: p */
    private int f20622p;

    /* renamed from: q */
    private String f20623q;

    EnumC4935ce(int i, String str) {
        this.f20622p = i;
        this.f20623q = str;
    }

    /* renamed from: a */
    public final int m5538a() {
        return this.f20622p;
    }

    /* renamed from: b */
    public final String m5536b() {
        return this.f20623q;
    }

    /* renamed from: a */
    public static EnumC4935ce m5537a(int i) {
        EnumC4935ce[] values;
        for (EnumC4935ce ceVar : values()) {
            if (ceVar.m5538a() == i) {
                return ceVar;
            }
        }
        return NONE;
    }
}
