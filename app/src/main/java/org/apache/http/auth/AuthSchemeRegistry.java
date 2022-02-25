package org.apache.http.auth;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import org.apache.http.params.HttpParams;

@Deprecated
/* loaded from: assets/org.apache.http.legacy.boot */
public final class AuthSchemeRegistry {
    private final Map<String, AuthSchemeFactory> registeredSchemes = new LinkedHashMap();

    public synchronized void register(String name, AuthSchemeFactory factory) {
        if (name == null) {
            throw new IllegalArgumentException("Name may not be null");
        } else if (factory != null) {
            this.registeredSchemes.put(name.toLowerCase(Locale.ENGLISH), factory);
        } else {
            throw new IllegalArgumentException("Authentication scheme factory may not be null");
        }
    }

    public synchronized void unregister(String name) {
        if (name != null) {
            this.registeredSchemes.remove(name.toLowerCase(Locale.ENGLISH));
        } else {
            throw new IllegalArgumentException("Name may not be null");
        }
    }

    public synchronized AuthScheme getAuthScheme(String name, HttpParams params) throws IllegalStateException {
        AuthSchemeFactory factory;
        if (name != null) {
            factory = this.registeredSchemes.get(name.toLowerCase(Locale.ENGLISH));
            if (factory != null) {
            } else {
                throw new IllegalStateException("Unsupported authentication scheme: " + name);
            }
        } else {
            throw new IllegalArgumentException("Name may not be null");
        }
        return factory.newInstance(params);
    }

    public synchronized List<String> getSchemeNames() {
        return new ArrayList(this.registeredSchemes.keySet());
    }

    public synchronized void setItems(Map<String, AuthSchemeFactory> map) {
        if (map != null) {
            this.registeredSchemes.clear();
            this.registeredSchemes.putAll(map);
        }
    }
}
