package p110z1;

import p110z1.cng;

/* renamed from: z1.cka */
/* loaded from: classes3.dex */
public abstract class PropertyReference0 extends PropertyReference implements cng {
    public PropertyReference0() {
    }

    @bwy(m8750a = "1.1")
    public PropertyReference0(Object obj) {
        super(obj);
    }

    @Override // p110z1.CallableReference
    protected KCallable computeReflected() {
        return Reflection.m5113a(this);
    }

    @Override // p110z1.chc
    public Object invoke() {
        return get();
    }

    @Override // p110z1.cnf, p110z1.cng
    public cng.AbstractC5008a getGetter() {
        return ((cng) getReflected()).getGetter();
    }

    @Override // p110z1.cng
    @bwy(m8750a = "1.1")
    public Object getDelegate() {
        return ((cng) getReflected()).getDelegate();
    }
}
