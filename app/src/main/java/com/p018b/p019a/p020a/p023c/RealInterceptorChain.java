package com.p018b.p019a.p020a.p023c;

import com.p018b.p019a.AbstractC0899ag;
import com.p018b.p019a.Connection;
import com.p018b.p019a.HttpUrl;
import com.p018b.p019a.Interceptor;
import com.p018b.p019a.Request;
import com.p018b.p019a.Response;
import com.p018b.p019a.p020a.p022b.StreamAllocation;
import java.util.List;

/* renamed from: com.b.a.a.c.h */
/* loaded from: classes.dex */
public final class RealInterceptorChain implements AbstractC0899ag {

    /* renamed from: a */
    private final List<Interceptor> f5799a;

    /* renamed from: b */
    private final StreamAllocation f5800b;

    /* renamed from: c */
    private final HttpCodec f5801c;

    /* renamed from: d */
    private final Connection f5802d;

    /* renamed from: e */
    private final int f5803e;

    /* renamed from: f */
    private final Request f5804f;

    /* renamed from: g */
    private int f5805g;

    public RealInterceptorChain(List<Interceptor> list, StreamAllocation gVar, HttpCodec cVar, Connection mVar, int i, Request aoVar) {
        this.f5799a = list;
        this.f5802d = mVar;
        this.f5800b = gVar;
        this.f5801c = cVar;
        this.f5803e = i;
        this.f5804f = aoVar;
    }

    /* renamed from: b */
    public final StreamAllocation m24732b() {
        return this.f5800b;
    }

    /* renamed from: c */
    public final HttpCodec m24731c() {
        return this.f5801c;
    }

    @Override // com.p018b.p019a.AbstractC0899ag
    /* renamed from: a */
    public final Request mo24512a() {
        return this.f5804f;
    }

    @Override // com.p018b.p019a.AbstractC0899ag
    /* renamed from: a */
    public final Response mo24511a(Request aoVar) {
        return m24733a(aoVar, this.f5800b, this.f5801c, this.f5802d);
    }

    /* renamed from: a */
    public final Response m24733a(Request aoVar, StreamAllocation gVar, HttpCodec cVar, Connection mVar) {
        if (this.f5803e < this.f5799a.size()) {
            this.f5805g++;
            if (this.f5801c != null) {
                HttpUrl a = aoVar.m24471a();
                if (!(a.m24529f().equals(this.f5802d.mo24399a().m24424a().m24833a().m24529f()) && a.m24528g() == this.f5802d.mo24399a().m24424a().m24833a().m24528g())) {
                    throw new IllegalStateException("network interceptor " + this.f5799a.get(this.f5803e - 1) + " must retain the same host and port");
                }
            }
            if (this.f5801c == null || this.f5805g <= 1) {
                RealInterceptorChain hVar = new RealInterceptorChain(this.f5799a, gVar, cVar, mVar, this.f5803e + 1, aoVar);
                Interceptor afVar = this.f5799a.get(this.f5803e);
                Response a2 = afVar.mo24513a(hVar);
                if (cVar != null && this.f5803e + 1 < this.f5799a.size() && hVar.f5805g != 1) {
                    throw new IllegalStateException("network interceptor " + afVar + " must call proceed() exactly once");
                } else if (a2 != null) {
                    return a2;
                } else {
                    throw new NullPointerException("interceptor " + afVar + " returned null");
                }
            } else {
                throw new IllegalStateException("network interceptor " + this.f5799a.get(this.f5803e - 1) + " must call proceed() exactly once");
            }
        } else {
            throw new AssertionError();
        }
    }
}
