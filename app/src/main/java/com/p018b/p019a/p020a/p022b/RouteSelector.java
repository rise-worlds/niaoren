package com.p018b.p019a.p020a.p022b;

import com.p018b.p019a.Address;
import com.p018b.p019a.HttpUrl;
import com.p018b.p019a.Route;
import com.p018b.p019a.p020a.Util;
import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.SocketAddress;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;

/* renamed from: com.b.a.a.b.f */
/* loaded from: classes.dex */
public final class RouteSelector {

    /* renamed from: a */
    private final Address f5756a;

    /* renamed from: b */
    private final RouteDatabase f5757b;

    /* renamed from: c */
    private Proxy f5758c;

    /* renamed from: d */
    private InetSocketAddress f5759d;

    /* renamed from: e */
    private List<Proxy> f5760e;

    /* renamed from: f */
    private int f5761f;

    /* renamed from: h */
    private int f5763h;

    /* renamed from: g */
    private List<InetSocketAddress> f5762g = Collections.emptyList();

    /* renamed from: i */
    private final List<Route> f5764i = new ArrayList();

    public RouteSelector(Address aVar, RouteDatabase dVar) {
        List<Proxy> list;
        this.f5760e = Collections.emptyList();
        this.f5756a = aVar;
        this.f5757b = dVar;
        HttpUrl a = aVar.m24833a();
        Proxy h = aVar.m24826h();
        if (h != null) {
            list = Collections.singletonList(h);
        } else {
            List<Proxy> select = this.f5756a.m24827g().select(a.m24548a());
            if (select == null || select.isEmpty()) {
                list = Util.m24752a(Proxy.NO_PROXY);
            } else {
                list = Util.m24753a(select);
            }
        }
        this.f5760e = list;
        this.f5761f = 0;
    }

    /* renamed from: a */
    public final boolean m24791a() {
        return m24786d() || m24787c() || m24785e();
    }

    /* renamed from: b */
    public final Route m24788b() {
        while (true) {
            if (!m24786d()) {
                if (!m24787c()) {
                    if (m24785e()) {
                        return this.f5764i.remove(0);
                    }
                    throw new NoSuchElementException();
                } else if (m24787c()) {
                    List<Proxy> list = this.f5760e;
                    int i = this.f5761f;
                    this.f5761f = i + 1;
                    Proxy proxy = list.get(i);
                    m24789a(proxy);
                    this.f5758c = proxy;
                } else {
                    throw new SocketException("No route to " + this.f5756a.m24833a().m24529f() + "; exhausted proxy configurations: " + this.f5760e);
                }
            }
            if (m24786d()) {
                List<InetSocketAddress> list2 = this.f5762g;
                int i2 = this.f5763h;
                this.f5763h = i2 + 1;
                this.f5759d = list2.get(i2);
                Route awVar = new Route(this.f5756a, this.f5758c, this.f5759d);
                if (!this.f5757b.m24794c(awVar)) {
                    return awVar;
                }
                this.f5764i.add(awVar);
            } else {
                throw new SocketException("No route to " + this.f5756a.m24833a().m24529f() + "; exhausted inet socket addresses: " + this.f5762g);
            }
        }
    }

    /* renamed from: a */
    public final void m24790a(Route awVar, IOException iOException) {
        if (!(awVar.m24423b().type() == Proxy.Type.DIRECT || this.f5756a.m24827g() == null)) {
            this.f5756a.m24827g().connectFailed(this.f5756a.m24833a().m24548a(), awVar.m24423b().address(), iOException);
        }
        this.f5757b.m24796a(awVar);
    }

    /* renamed from: c */
    private boolean m24787c() {
        return this.f5761f < this.f5760e.size();
    }

    /* renamed from: a */
    private void m24789a(Proxy proxy) {
        String str;
        int i;
        this.f5762g = new ArrayList();
        if (proxy.type() == Proxy.Type.DIRECT || proxy.type() == Proxy.Type.SOCKS) {
            str = this.f5756a.m24833a().m24529f();
            i = this.f5756a.m24833a().m24528g();
        } else {
            SocketAddress address = proxy.address();
            if (address instanceof InetSocketAddress) {
                InetSocketAddress inetSocketAddress = (InetSocketAddress) address;
                InetAddress address2 = inetSocketAddress.getAddress();
                if (address2 == null) {
                    str = inetSocketAddress.getHostName();
                } else {
                    str = address2.getHostAddress();
                }
                i = inetSocketAddress.getPort();
            } else {
                throw new IllegalArgumentException("Proxy.address() is not an InetSocketAddress: " + address.getClass());
            }
        }
        if (i <= 0 || i > 65535) {
            throw new SocketException("No route to " + str + ":" + i + "; port is out of range");
        }
        if (proxy.type() == Proxy.Type.SOCKS) {
            this.f5762g.add(InetSocketAddress.createUnresolved(str, i));
        } else {
            List<InetAddress> a = this.f5756a.m24832b().mo24367a(str);
            int size = a.size();
            for (int i2 = 0; i2 < size; i2++) {
                this.f5762g.add(new InetSocketAddress(a.get(i2), i));
            }
        }
        this.f5763h = 0;
    }

    /* renamed from: d */
    private boolean m24786d() {
        return this.f5763h < this.f5762g.size();
    }

    /* renamed from: e */
    private boolean m24785e() {
        return !this.f5764i.isEmpty();
    }
}
