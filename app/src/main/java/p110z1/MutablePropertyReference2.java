package p110z1;

import p110z1.cnd;
import p110z1.cni;

/* renamed from: z1.cjv */
/* loaded from: classes3.dex */
public abstract class MutablePropertyReference2 extends MutablePropertyReference implements cnd {
    @Override // p110z1.CallableReference
    protected KCallable computeReflected() {
        return Reflection.m5114a(this);
    }

    @Override // p110z1.cho
    public Object invoke(Object obj, Object obj2) {
        return get(obj, obj2);
    }

    /* JADX WARN: Type inference failed for: r0v2, types: [z1.cni$a] */
    @Override // p110z1.cnf, p110z1.cng
    public cni.AbstractC5010a getGetter() {
        return ((cnd) getReflected()).getGetter();
    }

    @Override // p110z1.KProperty, p110z1.cnb
    public cnd.AbstractC5002a getSetter() {
        return ((cnd) getReflected()).getSetter();
    }

    @Override // p110z1.cni
    @bwy(m8750a = "1.1")
    public Object getDelegate(Object obj, Object obj2) {
        return ((cnd) getReflected()).getDelegate(obj, obj2);
    }
}
