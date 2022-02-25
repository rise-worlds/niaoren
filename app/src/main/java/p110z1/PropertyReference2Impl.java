package p110z1;

/* renamed from: z1.ckf */
/* loaded from: classes3.dex */
public class PropertyReference2Impl extends PropertyReference2 {
    private final String name;
    private final KDeclarationContainer owner;
    private final String signature;

    public PropertyReference2Impl(KDeclarationContainer cmyVar, String str, String str2) {
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

    @Override // p110z1.cni
    public Object get(Object obj, Object obj2) {
        return getGetter().call(obj, obj2);
    }
}
