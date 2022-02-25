package com.tendcloud.tenddata;

/* compiled from: td */
/* renamed from: com.tendcloud.tenddata.jf */
/* loaded from: classes2.dex */
public abstract class AbstractC3002jf extends AbstractC3010jm {

    /* renamed from: a */
    protected C3006ji f14319a;

    @Override // com.tendcloud.tenddata.AbstractC3010jm
    /* renamed from: a */
    protected int mo15260a() {
        if (this.f14319a == null) {
            return 0;
        }
        int i = 0;
        for (int i2 = 0; i2 < this.f14319a.m15288a(); i2++) {
            i += this.f14319a.m15287a(i2).m15279a();
        }
        return i;
    }

    @Override // com.tendcloud.tenddata.AbstractC3010jm
    public void writeTo(C3000je jeVar) {
        if (this.f14319a != null) {
            for (int i = 0; i < this.f14319a.m15288a(); i++) {
                this.f14319a.m15287a(i).m15278a(jeVar);
            }
        }
    }

    /* renamed from: b */
    public AbstractC3002jf clone() {
        AbstractC3002jf jfVar = (AbstractC3002jf) super.clone();
        C3008jk.m15275a(this, jfVar);
        return jfVar;
    }
}
