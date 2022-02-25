package p110z1;

/* renamed from: z1.cjd */
/* loaded from: classes3.dex */
public class FunctionReference extends CallableReference implements FunctionBase, KFunction {
    private final int arity;

    public FunctionReference(int i) {
        this.arity = i;
    }

    @bwy(m8750a = "1.1")
    public FunctionReference(int i, Object obj) {
        super(obj);
        this.arity = i;
    }

    @Override // p110z1.FunctionBase
    public int getArity() {
        return this.arity;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // p110z1.CallableReference
    @bwy(m8750a = "1.1")
    public KFunction getReflected() {
        return (KFunction) super.getReflected();
    }

    @Override // p110z1.CallableReference
    @bwy(m8750a = "1.1")
    protected KCallable computeReflected() {
        return Reflection.m5118a(this);
    }

    @Override // p110z1.KFunction
    @bwy(m8750a = "1.1")
    public boolean isInline() {
        return getReflected().isInline();
    }

    @Override // p110z1.KFunction
    @bwy(m8750a = "1.1")
    public boolean isExternal() {
        return getReflected().isExternal();
    }

    @Override // p110z1.KFunction
    @bwy(m8750a = "1.1")
    public boolean isOperator() {
        return getReflected().isOperator();
    }

    @Override // p110z1.KFunction
    @bwy(m8750a = "1.1")
    public boolean isInfix() {
        return getReflected().isInfix();
    }

    @Override // p110z1.CallableReference, p110z1.KCallable
    @bwy(m8750a = "1.1")
    public boolean isSuspend() {
        return getReflected().isSuspend();
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof FunctionReference) {
            FunctionReference cjdVar = (FunctionReference) obj;
            if (getOwner() != null ? getOwner().equals(cjdVar.getOwner()) : cjdVar.getOwner() == null) {
                if (getName().equals(cjdVar.getName()) && getSignature().equals(cjdVar.getSignature()) && cji.m5184a(getBoundReceiver(), cjdVar.getBoundReceiver())) {
                    return true;
                }
            }
            return false;
        } else if (obj instanceof KFunction) {
            return obj.equals(compute());
        } else {
            return false;
        }
    }

    public int hashCode() {
        return (((getOwner() == null ? 0 : getOwner().hashCode() * 31) + getName().hashCode()) * 31) + getSignature().hashCode();
    }

    public String toString() {
        KCallable compute = compute();
        if (compute != this) {
            return compute.toString();
        }
        if ("<init>".equals(getName())) {
            return "constructor (Kotlin reflection is not available)";
        }
        return "function " + getName() + " (Kotlin reflection is not available)";
    }
}
