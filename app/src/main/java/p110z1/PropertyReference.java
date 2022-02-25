package p110z1;

/* renamed from: z1.cjz */
/* loaded from: classes3.dex */
public abstract class PropertyReference extends CallableReference implements cnf {
    public PropertyReference() {
    }

    @bwy(m8750a = "1.1")
    public PropertyReference(Object obj) {
        super(obj);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // p110z1.CallableReference
    @bwy(m8750a = "1.1")
    public cnf getReflected() {
        return (cnf) super.getReflected();
    }

    @Override // p110z1.cnf
    @bwy(m8750a = "1.1")
    public boolean isLateinit() {
        return getReflected().isLateinit();
    }

    @Override // p110z1.cnf
    @bwy(m8750a = "1.1")
    public boolean isConst() {
        return getReflected().isConst();
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof PropertyReference) {
            PropertyReference cjzVar = (PropertyReference) obj;
            return getOwner().equals(cjzVar.getOwner()) && getName().equals(cjzVar.getName()) && getSignature().equals(cjzVar.getSignature()) && cji.m5184a(getBoundReceiver(), cjzVar.getBoundReceiver());
        } else if (obj instanceof cnf) {
            return obj.equals(compute());
        } else {
            return false;
        }
    }

    public int hashCode() {
        return (((getOwner().hashCode() * 31) + getName().hashCode()) * 31) + getSignature().hashCode();
    }

    public String toString() {
        KCallable compute = compute();
        if (compute != this) {
            return compute.toString();
        }
        return "property " + getName() + " (Kotlin reflection is not available)";
    }
}
