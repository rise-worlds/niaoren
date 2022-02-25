package p110z1;

import p110z1.cnb;
import p110z1.cng;

/* renamed from: z1.cjr */
/* loaded from: classes3.dex */
public abstract class MutablePropertyReference0 extends MutablePropertyReference implements cnb {
    public MutablePropertyReference0() {
    }

    @bwy(m8750a = "1.1")
    public MutablePropertyReference0(Object obj) {
        super(obj);
    }

    @Override // p110z1.CallableReference
    protected KCallable computeReflected() {
        return Reflection.m5116a(this);
    }

    @Override // p110z1.chc
    public Object invoke() {
        return get();
    }

    /* JADX WARN: Type inference failed for: r0v2, types: [z1.cng$a] */
    @Override // p110z1.cnf, p110z1.cng
    public cng.AbstractC5008a getGetter() {
        return ((cnb) getReflected()).getGetter();
    }

    @Override // p110z1.KProperty, p110z1.cnb
    public cnb.AbstractC5000a getSetter() {
        return ((cnb) getReflected()).getSetter();
    }

    @Override // p110z1.cng
    @bwy(m8750a = "1.1")
    public Object getDelegate() {
        return ((cnb) getReflected()).getDelegate();
    }
}
