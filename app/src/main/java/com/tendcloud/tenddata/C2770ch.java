package com.tendcloud.tenddata;

import com.tendcloud.tenddata.AbstractC2765cf;

/* compiled from: td */
/* renamed from: com.tendcloud.tenddata.ch */
/* loaded from: classes2.dex */
public class C2770ch extends C2768cg {
    @Override // com.tendcloud.tenddata.C2768cg, com.tendcloud.tenddata.AbstractC2765cf
    /* renamed from: a */
    public AbstractC2765cf.EnumC2767b mo16156a(AbstractC2786cw cwVar) {
        if (m16165b(cwVar) == 13) {
            return AbstractC2765cf.EnumC2767b.MATCHED;
        }
        return AbstractC2765cf.EnumC2767b.NOT_MATCHED;
    }

    @Override // com.tendcloud.tenddata.C2768cg, com.tendcloud.tenddata.AbstractC2765cf
    /* renamed from: a */
    public AbstractC2787cx mo16153a(AbstractC2787cx cxVar) {
        super.mo16153a(cxVar);
        cxVar.mo16132a("Sec-WebSocket-Version", "13");
        return cxVar;
    }

    @Override // com.tendcloud.tenddata.C2768cg, com.tendcloud.tenddata.AbstractC2765cf
    /* renamed from: c */
    public AbstractC2765cf mo16149c() {
        return new C2770ch();
    }
}
