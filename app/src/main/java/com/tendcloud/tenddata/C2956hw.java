package com.tendcloud.tenddata;

/* compiled from: td */
/* renamed from: com.tendcloud.tenddata.hw */
/* loaded from: classes2.dex */
final class C2956hw {

    /* renamed from: a */
    static final int f14209a = 101;

    /* renamed from: b */
    static final int f14210b = 102;

    /* renamed from: c */
    static final int f14211c = 103;

    /* renamed from: d */
    private String f14212d;

    /* renamed from: e */
    private String f14213e;

    /* renamed from: f */
    private String f14214f;

    /* renamed from: g */
    private String f14215g;

    /* renamed from: h */
    private String f14216h;

    /* renamed from: i */
    private EnumC2957a f14217i;

    /* renamed from: j */
    private int f14218j;

    /* compiled from: td */
    /* renamed from: com.tendcloud.tenddata.hw$a */
    /* loaded from: classes2.dex */
    public enum EnumC2957a {
        ARRIVED(0),
        CLICK(1),
        SHOW(2),
        UNSHOWN(3),
        CANCEL(4),
        INAPP_SHOW(11),
        INAPP_DURATION(12),
        INAPP_UNSHOW(13);
        
        private final int index;

        EnumC2957a(int i) {
            this.index = i;
        }

        public int index() {
            return this.index;
        }
    }

    C2956hw() {
        this.f14212d = null;
        this.f14213e = null;
        this.f14214f = null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public C2956hw(String str, String str2, String str3) {
        this.f14212d = null;
        this.f14213e = null;
        this.f14214f = null;
        this.f14214f = str;
        this.f14213e = str2;
        this.f14212d = str3;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public C2956hw(String str, String str2, EnumC2957a aVar, int i) {
        this.f14212d = null;
        this.f14213e = null;
        this.f14214f = null;
        this.f14216h = str;
        this.f14217i = aVar;
        this.f14215g = str2;
        this.f14218j = i;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public String m15474a() {
        return this.f14213e;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: b */
    public String m15473b() {
        return this.f14214f;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: c */
    public String m15472c() {
        return this.f14212d;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: d */
    public String m15471d() {
        return this.f14216h;
    }

    /* renamed from: e */
    String m15470e() {
        return this.f14215g;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: f */
    public EnumC2957a m15469f() {
        return this.f14217i;
    }

    /* renamed from: g */
    int m15468g() {
        return this.f14218j;
    }
}
