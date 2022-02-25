package p110z1;

/* renamed from: z1.cje */
/* loaded from: classes3.dex */
public class FunctionReferenceImpl extends FunctionReference {
    private final String name;
    private final KDeclarationContainer owner;
    private final String signature;

    public FunctionReferenceImpl(int i, KDeclarationContainer cmyVar, String str, String str2) {
        super(i);
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
}
