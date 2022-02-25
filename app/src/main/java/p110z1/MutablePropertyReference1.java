package p110z1;

import p110z1.cnc;
import p110z1.cnh;

/* renamed from: z1.cjt */
/* loaded from: classes3.dex */
public abstract class MutablePropertyReference1 extends MutablePropertyReference implements cnc {
    public MutablePropertyReference1() {
    }

    @bwy(m8750a = "1.1")
    public MutablePropertyReference1(Object obj) {
        super(obj);
    }

    @Override // p110z1.CallableReference
    protected KCallable computeReflected() {
        return Reflection.m5115a(this);
    }

    @Override // p110z1.chd
    public Object invoke(Object obj) {
        return get(obj);
    }

    /* JADX WARN: Type inference failed for: r0v2, types: [z1.cnh$a] */
    @Override // p110z1.cnf, p110z1.cng
    public cnh.AbstractC5009a getGetter() {
        return ((cnc) getReflected()).getGetter();
    }

    @Override // p110z1.KProperty, p110z1.cnb
    public cnc.AbstractC5001a getSetter() {
        return ((cnc) getReflected()).getSetter();
    }

    @Override // p110z1.cnh
    @bwy(m8750a = "1.1")
    public Object getDelegate(Object obj) {
        return ((cnc) getReflected()).getDelegate(obj);
    }
}
