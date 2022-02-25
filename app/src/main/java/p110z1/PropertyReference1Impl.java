package p110z1;

/* renamed from: z1.ckd */
/* loaded from: classes3.dex */
public class PropertyReference1Impl extends PropertyReference1 {
    private final String name;
    private final KDeclarationContainer owner;
    private final String signature;

    public PropertyReference1Impl(KDeclarationContainer cmyVar, String str, String str2) {
        this.owner = cmyVar;
        this.name = str;
        this.signature = str2;
    }

    @Override // p110z1.CallableReference
    public KDeclarationContainer getOwner() {
        return this.owner;
    }

    @Override // p110z1.CallableReference, p110z1.KCallable
    public String getName() {
        return this.name;
    }

    @Override // p110z1.CallableReference
    public String getSignature() {
        return this.signature;
    }

    @Override // p110z1.cnh
    public Object get(Object obj) {
        return getGetter().call(obj);
    }
}
