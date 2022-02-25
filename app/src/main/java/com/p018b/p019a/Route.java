package com.p018b.p019a;

import java.net.InetSocketAddress;
import java.net.Proxy;
import p110z1.C4963cj;

/* renamed from: com.b.a.aw */
/* loaded from: classes.dex */
public final class Route {

    /* renamed from: a */
    final Address f6199a;

    /* renamed from: b */
    final Proxy f6200b;

    /* renamed from: c */
    final InetSocketAddress f6201c;

    public Route(Address aVar, Proxy proxy, InetSocketAddress inetSocketAddress) {
        if (aVar == null) {
            throw new NullPointerException("address == null");
        } else if (proxy == null) {
            throw new NullPointerException("proxy == null");
        } else if (inetSocketAddress != null) {
            this.f6199a = aVar;
            this.f6200b = proxy;
            this.f6201c = inetSocketAddress;
        } else {
            throw new NullPointerException("inetSocketAddress == null");
        }
    }

    /* renamed from: a */
    public final Address m24424a() {
        return this.f6199a;
    }

    /* renamed from: b */
    public final Proxy m24423b() {
        return this.f6200b;
    }

    /* renamed from: c */
    public final InetSocketAddress m24422c() {
        return this.f6201c;
    }

    /* renamed from: d */
    public final boolean m24421d() {
        return this.f6199a.f5686i != null && this.f6200b.type() == Proxy.Type.HTTP;
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof Route)) {
            return false;
        }
        Route awVar = (Route) obj;
        return this.f6199a.equals(awVar.f6199a) && this.f6200b.equals(awVar.f6200b) && this.f6201c.equals(awVar.f6201c);
    }

    public final int hashCode() {
        return ((((this.f6199a.hashCode() + 527) * 31) + this.f6200b.hashCode()) * 31) + this.f6201c.hashCode();
    }

    public final String toString() {
        return "Route{" + this.f6201c + C4963cj.f20747d;
    }
}
