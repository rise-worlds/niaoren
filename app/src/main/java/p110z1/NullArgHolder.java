package p110z1;

/* renamed from: z1.uu */
/* loaded from: classes3.dex */
public class NullArgHolder implements ArgumentHolder {
    @Override // p110z1.ArgumentHolder
    /* renamed from: a */
    public String mo509a() {
        return "null-holder";
    }

    @Override // p110z1.ArgumentHolder
    /* renamed from: a */
    public void mo508a(String str) {
    }

    @Override // p110z1.ArgumentHolder
    /* renamed from: a */
    public void mo507a(String str, FieldType ssVar) {
    }

    @Override // p110z1.ArgumentHolder
    /* renamed from: a */
    public void mo506a(FieldType ssVar) {
    }

    @Override // p110z1.ArgumentHolder
    /* renamed from: b */
    public Object mo505b() {
        return null;
    }

    @Override // p110z1.ArgumentHolder
    /* renamed from: d */
    public FieldType mo503d() {
        return null;
    }

    @Override // p110z1.ArgumentHolder
    /* renamed from: a */
    public void mo387a(Object obj) {
        throw new UnsupportedOperationException("Cannot set null on " + getClass());
    }

    @Override // p110z1.ArgumentHolder
    /* renamed from: c */
    public SqlType mo504c() {
        return SqlType.STRING;
    }
}
