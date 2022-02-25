package org.apache.http.params;

@Deprecated
/* loaded from: assets/org.apache.http.legacy.boot */
public final class DefaultedHttpParams extends AbstractHttpParams {
    private final HttpParams defaults;
    private final HttpParams local;

    public DefaultedHttpParams(HttpParams local, HttpParams defaults) {
        if (local != null) {
            this.local = local;
            this.defaults = defaults;
            return;
        }
        throw new IllegalArgumentException("HTTP parameters may not be null");
    }

    @Override // org.apache.http.params.HttpParams
    public HttpParams copy() {
        HttpParams clone = this.local.copy();
        return new DefaultedHttpParams(clone, this.defaults);
    }

    @Override // org.apache.http.params.HttpParams
    public Object getParameter(String name) {
        Object obj = this.local.getParameter(name);
        if (obj != null || this.defaults == null) {
            return obj;
        }
        return this.defaults.getParameter(name);
    }

    @Override // org.apache.http.params.HttpParams
    public boolean removeParameter(String name) {
        return this.local.removeParameter(name);
    }

    @Override // org.apache.http.params.HttpParams
    public HttpParams setParameter(String name, Object value) {
        return this.local.setParameter(name, value);
    }

    public HttpParams getDefaults() {
        return this.defaults;
    }
}
