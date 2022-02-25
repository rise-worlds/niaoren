package p110z1;

import p110z1.cnh;

/* renamed from: z1.ckc */
/* loaded from: classes3.dex */
public abstract class PropertyReference1 extends PropertyReference implements cnh {
    public PropertyReference1() {
    }

    @bwy(m8750a = "1.1")
    public PropertyReference1(Object obj) {
        super(obj);
    }

    @Override // p110z1.CallableReference
    protected KCallable computeReflected() {
        return Reflection.m5112a(this);
    }

    @Override // p110z1.chd
    public Object invoke(Object obj) {
        return get(obj);
    }

    @Override // p110z1.cnf, p110z1.cng
    public cnh.AbstractC5009a getGetter() {
        return ((cnh) getReflected()).getGetter();
    }

    @Override // p110z1.cnh
    @bwy(m8750a = "1.1")
    public Object getDelegate(Object obj) {
        return ((cnh) getReflected()).getDelegate(obj);
    }
}
