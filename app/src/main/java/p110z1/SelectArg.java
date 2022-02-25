package p110z1;

/* renamed from: z1.vc */
/* loaded from: classes3.dex */
public class SelectArg extends BaseArgumentHolder implements ArgumentHolder {

    /* renamed from: a */
    private boolean f23506a = false;

    /* renamed from: b */
    private Object f23507b = null;

    public SelectArg() {
    }

    public SelectArg(String str, Object obj) {
        super(str);
        mo387a(obj);
    }

    public SelectArg(SqlType suVar, Object obj) {
        super(suVar);
        mo387a(obj);
    }

    public SelectArg(SqlType suVar) {
        super(suVar);
    }

    public SelectArg(Object obj) {
        mo387a(obj);
    }

    @Override // p110z1.BaseArgumentHolder
    /* renamed from: e */
    protected Object mo386e() {
        return this.f23507b;
    }

    @Override // p110z1.BaseArgumentHolder, p110z1.ArgumentHolder
    /* renamed from: a */
    public void mo387a(Object obj) {
        this.f23506a = true;
        this.f23507b = obj;
    }

    @Override // p110z1.BaseArgumentHolder
    /* renamed from: f */
    protected boolean mo385f() {
        return this.f23506a;
    }
}
