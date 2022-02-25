package p110z1;

/* renamed from: z1.kp */
/* loaded from: classes3.dex */
final class DecodedInformation extends DecodedObject {

    /* renamed from: a */
    final String f22246a;

    /* renamed from: b */
    final int f22247b;

    /* renamed from: c */
    final boolean f22248c;

    /* JADX INFO: Access modifiers changed from: package-private */
    public DecodedInformation(int i, String str) {
        super(i);
        this.f22246a = str;
        this.f22248c = false;
        this.f22247b = 0;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public DecodedInformation(int i, String str, int i2) {
        super(i);
        this.f22248c = true;
        this.f22247b = i2;
        this.f22246a = str;
    }

    /* renamed from: a */
    private String m2201a() {
        return this.f22246a;
    }

    /* renamed from: b */
    private boolean m2200b() {
        return this.f22248c;
    }

    /* renamed from: c */
    private int m2199c() {
        return this.f22247b;
    }
}
