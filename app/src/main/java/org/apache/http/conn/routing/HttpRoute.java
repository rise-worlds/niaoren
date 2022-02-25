package org.apache.http.conn.routing;

import java.net.InetAddress;
import org.apache.http.HttpHost;
import org.apache.http.conn.routing.RouteInfo;

@Deprecated
/* loaded from: assets/org.apache.http.legacy.boot */
public final class HttpRoute implements RouteInfo, Cloneable {
    private final RouteInfo.LayerType layered;
    private final InetAddress localAddress;
    private final HttpHost[] proxyChain;
    private final boolean secure;
    private final HttpHost targetHost;
    private final RouteInfo.TunnelType tunnelled;

    private HttpRoute(InetAddress local, HttpHost target, HttpHost[] proxies, boolean secure, RouteInfo.TunnelType tunnelled, RouteInfo.LayerType layered) {
        if (target == null) {
            throw new IllegalArgumentException("Target host may not be null.");
        } else if (tunnelled == RouteInfo.TunnelType.TUNNELLED && proxies == null) {
            throw new IllegalArgumentException("Proxy required if tunnelled.");
        } else {
            tunnelled = tunnelled == null ? RouteInfo.TunnelType.PLAIN : tunnelled;
            layered = layered == null ? RouteInfo.LayerType.PLAIN : layered;
            this.targetHost = target;
            this.localAddress = local;
            this.proxyChain = proxies;
            this.secure = secure;
            this.tunnelled = tunnelled;
            this.layered = layered;
        }
    }

    public HttpRoute(HttpHost target, InetAddress local, HttpHost[] proxies, boolean secure, RouteInfo.TunnelType tunnelled, RouteInfo.LayerType layered) {
        this(local, target, toChain(proxies), secure, tunnelled, layered);
    }

    public HttpRoute(HttpHost target, InetAddress local, HttpHost proxy, boolean secure, RouteInfo.TunnelType tunnelled, RouteInfo.LayerType layered) {
        this(local, target, toChain(proxy), secure, tunnelled, layered);
    }

    public HttpRoute(HttpHost target, InetAddress local, boolean secure) {
        this(local, target, (HttpHost[]) null, secure, RouteInfo.TunnelType.PLAIN, RouteInfo.LayerType.PLAIN);
    }

    public HttpRoute(HttpHost target) {
        this((InetAddress) null, target, (HttpHost[]) null, false, RouteInfo.TunnelType.PLAIN, RouteInfo.LayerType.PLAIN);
    }

    public HttpRoute(HttpHost target, InetAddress local, HttpHost proxy, boolean secure) {
        this(local, target, toChain(proxy), secure, secure ? RouteInfo.TunnelType.TUNNELLED : RouteInfo.TunnelType.PLAIN, secure ? RouteInfo.LayerType.LAYERED : RouteInfo.LayerType.PLAIN);
        if (proxy == null) {
            throw new IllegalArgumentException("Proxy host may not be null.");
        }
    }

    private static HttpHost[] toChain(HttpHost proxy) {
        if (proxy == null) {
            return null;
        }
        return new HttpHost[]{proxy};
    }

    private static HttpHost[] toChain(HttpHost[] proxies) {
        if (proxies == null || proxies.length < 1) {
            return null;
        }
        for (HttpHost proxy : proxies) {
            if (proxy == null) {
                throw new IllegalArgumentException("Proxy chain may not contain null elements.");
            }
        }
        HttpHost[] result = new HttpHost[proxies.length];
        System.arraycopy(proxies, 0, result, 0, proxies.length);
        return result;
    }

    @Override // org.apache.http.conn.routing.RouteInfo
    public final HttpHost getTargetHost() {
        return this.targetHost;
    }

    @Override // org.apache.http.conn.routing.RouteInfo
    public final InetAddress getLocalAddress() {
        return this.localAddress;
    }

    @Override // org.apache.http.conn.routing.RouteInfo
    public final int getHopCount() {
        if (this.proxyChain == null) {
            return 1;
        }
        return 1 + this.proxyChain.length;
    }

    @Override // org.apache.http.conn.routing.RouteInfo
    public final HttpHost getHopTarget(int hop) {
        if (hop >= 0) {
            int hopcount = getHopCount();
            if (hop >= hopcount) {
                throw new IllegalArgumentException("Hop index " + hop + " exceeds route length " + hopcount);
            } else if (hop < hopcount - 1) {
                HttpHost result = this.proxyChain[hop];
                return result;
            } else {
                HttpHost result2 = this.targetHost;
                return result2;
            }
        } else {
            throw new IllegalArgumentException("Hop index must not be negative: " + hop);
        }
    }

    @Override // org.apache.http.conn.routing.RouteInfo
    public final HttpHost getProxyHost() {
        if (this.proxyChain == null) {
            return null;
        }
        return this.proxyChain[0];
    }

    @Override // org.apache.http.conn.routing.RouteInfo
    public final RouteInfo.TunnelType getTunnelType() {
        return this.tunnelled;
    }

    @Override // org.apache.http.conn.routing.RouteInfo
    public final boolean isTunnelled() {
        return this.tunnelled == RouteInfo.TunnelType.TUNNELLED;
    }

    @Override // org.apache.http.conn.routing.RouteInfo
    public final RouteInfo.LayerType getLayerType() {
        return this.layered;
    }

    @Override // org.apache.http.conn.routing.RouteInfo
    public final boolean isLayered() {
        return this.layered == RouteInfo.LayerType.LAYERED;
    }

    @Override // org.apache.http.conn.routing.RouteInfo
    public final boolean isSecure() {
        return this.secure;
    }

    public final boolean equals(Object o) {
        boolean z = true;
        if (o == this) {
            return true;
        }
        if (!(o instanceof HttpRoute)) {
            return false;
        }
        HttpRoute that = (HttpRoute) o;
        boolean equal = this.targetHost.equals(that.targetHost);
        boolean equal2 = equal & (this.localAddress == that.localAddress || (this.localAddress != null && this.localAddress.equals(that.localAddress))) & (this.proxyChain == that.proxyChain || !(this.proxyChain == null || that.proxyChain == null || this.proxyChain.length != that.proxyChain.length));
        if (!(this.secure == that.secure && this.tunnelled == that.tunnelled && this.layered == that.layered)) {
            z = false;
        }
        boolean equal3 = z & equal2;
        if (equal3 && this.proxyChain != null) {
            for (int i = 0; equal3 && i < this.proxyChain.length; i++) {
                equal3 = this.proxyChain[i].equals(that.proxyChain[i]);
            }
        }
        return equal3;
    }

    public final int hashCode() {
        HttpHost[] httpHostArr;
        int hc = this.targetHost.hashCode();
        if (this.localAddress != null) {
            hc ^= this.localAddress.hashCode();
        }
        if (this.proxyChain != null) {
            hc ^= this.proxyChain.length;
            for (HttpHost aProxyChain : this.proxyChain) {
                hc ^= aProxyChain.hashCode();
            }
        }
        if (this.secure) {
            hc ^= 286331153;
        }
        return (hc ^ this.tunnelled.hashCode()) ^ this.layered.hashCode();
    }

    public final String toString() {
        HttpHost[] httpHostArr;
        StringBuilder cab = new StringBuilder(50 + (getHopCount() * 30));
        cab.append("HttpRoute[");
        if (this.localAddress != null) {
            cab.append(this.localAddress);
            cab.append("->");
        }
        cab.append('{');
        if (this.tunnelled == RouteInfo.TunnelType.TUNNELLED) {
            cab.append('t');
        }
        if (this.layered == RouteInfo.LayerType.LAYERED) {
            cab.append('l');
        }
        if (this.secure) {
            cab.append('s');
        }
        cab.append("}->");
        if (this.proxyChain != null) {
            for (HttpHost aProxyChain : this.proxyChain) {
                cab.append(aProxyChain);
                cab.append("->");
            }
        }
        cab.append(this.targetHost);
        cab.append(']');
        return cab.toString();
    }

    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
