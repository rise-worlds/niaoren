package p110z1;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: Token.java */
/* renamed from: z1.gg */
/* loaded from: classes3.dex */
public abstract class AbstractC5363gg {

    /* renamed from: a */
    static final AbstractC5363gg f21778a = new SimpleToken(null, 0, 0);

    /* renamed from: b */
    final AbstractC5363gg f21779b;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public abstract void mo2734a(BitArray huVar, byte[] bArr);

    /* JADX INFO: Access modifiers changed from: package-private */
    public AbstractC5363gg(AbstractC5363gg ggVar) {
        this.f21779b = ggVar;
    }

    /* renamed from: a */
    private AbstractC5363gg m2736a() {
        return this.f21779b;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public final AbstractC5363gg m2735a(int i, int i2) {
        return new SimpleToken(this, i, i2);
    }

    /* renamed from: b */
    private AbstractC5363gg m2733b(int i, int i2) {
        return new BinaryShiftToken(this, i, i2);
    }
}
