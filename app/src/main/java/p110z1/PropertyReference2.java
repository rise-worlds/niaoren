package p110z1;

import p110z1.cni;

/* renamed from: z1.cke */
/* loaded from: classes3.dex */
public abstract class PropertyReference2 extends PropertyReference implements cni {
    @Override // p110z1.CallableReference
    protected KCallable computeReflected() {
        return Reflection.m5111a(this);
    }

    @Override // p110z1.cho
    public Object invoke(Object obj, Object obj2) {
        return get(obj, obj2);
    }

    @Override // p110z1.cnf, p110z1.cng
    public cni.AbstractC5010a getGetter() {
        return ((cni) getReflected()).getGetter();
    }

    @Override // p110z1.cni
    @bwy(m8750a = "1.1")
    public Object getDelegate(Object obj, Object obj2) {
        return ((cni) getReflected()).getDelegate(obj, obj2);
    }
}
