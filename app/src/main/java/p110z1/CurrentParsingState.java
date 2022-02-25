package p110z1;

/* renamed from: z1.kn */
/* loaded from: classes3.dex */
final class CurrentParsingState {

    /* renamed from: a */
    int f22238a = 0;

    /* renamed from: b */
    int f22239b = EnumC5389a.f22240a;

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Failed to restore enum class, 'enum' modifier removed */
    /* compiled from: CurrentParsingState.java */
    /* renamed from: z1.kn$a */
    /* loaded from: classes3.dex */
    public static final class EnumC5389a extends Enum<EnumC5389a> {

        /* renamed from: a */
        public static final int f22240a = 1;

        /* renamed from: b */
        public static final int f22241b = 2;

        /* renamed from: c */
        public static final int f22242c = 3;

        /* renamed from: d */
        private static final /* synthetic */ int[] f22243d = {f22240a, f22241b, f22242c};

        private EnumC5389a(String str, int i) {
        }

        /* renamed from: a */
        private static int[] m2204a() {
            return (int[]) f22243d.clone();
        }
    }

    /* renamed from: a */
    private int m2213a() {
        return this.f22238a;
    }

    /* renamed from: b */
    private void m2210b(int i) {
        this.f22238a = i;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public final void m2212a(int i) {
        this.f22238a += i;
    }

    /* renamed from: b */
    private boolean m2211b() {
        return this.f22239b == EnumC5389a.f22241b;
    }

    /* renamed from: c */
    private boolean m2209c() {
        return this.f22239b == EnumC5389a.f22240a;
    }

    /* renamed from: d */
    private boolean m2208d() {
        return this.f22239b == EnumC5389a.f22242c;
    }

    /* renamed from: e */
    private void m2207e() {
        this.f22239b = EnumC5389a.f22240a;
    }

    /* renamed from: f */
    private void m2206f() {
        this.f22239b = EnumC5389a.f22241b;
    }

    /* renamed from: g */
    private void m2205g() {
        this.f22239b = EnumC5389a.f22242c;
    }
}
