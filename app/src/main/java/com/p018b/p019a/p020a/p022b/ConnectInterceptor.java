package com.p018b.p019a.p020a.p022b;

import com.p018b.p019a.AbstractC0899ag;
import com.p018b.p019a.Interceptor;
import com.p018b.p019a.OkHttpClient;
import com.p018b.p019a.Request;
import com.p018b.p019a.Response;
import com.p018b.p019a.p020a.p023c.RealInterceptorChain;

/* renamed from: com.b.a.a.b.a */
/* loaded from: classes.dex */
public final class ConnectInterceptor implements Interceptor {

    /* renamed from: a */
    public final OkHttpClient f5734a;

    public ConnectInterceptor(OkHttpClient aiVar) {
        this.f5734a = aiVar;
    }

    @Override // com.p018b.p019a.Interceptor
    /* renamed from: a */
    public final Response mo24513a(AbstractC0899ag agVar) {
        RealInterceptorChain hVar = (RealInterceptorChain) agVar;
        Request a = hVar.mo24512a();
        StreamAllocation b = hVar.m24732b();
        return hVar.m24733a(a, b, b.m24780a(this.f5734a, !a.m24469b().equals("GET")), b.m24776b());
    }
}
