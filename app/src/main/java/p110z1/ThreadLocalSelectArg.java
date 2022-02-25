package p110z1;

/* renamed from: z1.vg */
/* loaded from: classes3.dex */
public class ThreadLocalSelectArg extends BaseArgumentHolder implements ArgumentHolder {

    /* renamed from: a */
    private ThreadLocal<C5582a> f23552a = new ThreadLocal<>();

    public ThreadLocalSelectArg() {
    }

    public ThreadLocalSelectArg(String str, Object obj) {
        super(str);
        mo387a(obj);
    }

    public ThreadLocalSelectArg(SqlType suVar, Object obj) {
        super(suVar);
        mo387a(obj);
    }

    public ThreadLocalSelectArg(Object obj) {
        mo387a(obj);
    }

    @Override // p110z1.BaseArgumentHolder
    /* renamed from: e */
    protected Object mo386e() {
        C5582a aVar = this.f23552a.get();
        if (aVar == null) {
            return null;
        }
        return aVar.f23553a;
    }

    @Override // p110z1.BaseArgumentHolder, p110z1.ArgumentHolder
    /* renamed from: a */
    public void mo387a(Object obj) {
        this.f23552a.set(new C5582a(obj));
    }

    @Override // p110z1.BaseArgumentHolder
    /* renamed from: f */
    protected boolean mo385f() {
        return this.f23552a.get() != null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: ThreadLocalSelectArg.java */
    /* renamed from: z1.vg$a */
    /* loaded from: classes3.dex */
    public static class C5582a {

        /* renamed from: a */
        Object f23553a;

        public C5582a(Object obj) {
            this.f23553a = obj;
        }
    }
}
