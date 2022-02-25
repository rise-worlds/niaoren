package org.apache.http.cookie;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import org.apache.http.params.HttpParams;

@Deprecated
/* loaded from: assets/org.apache.http.legacy.boot */
public final class CookieSpecRegistry {
    private final Map<String, CookieSpecFactory> registeredSpecs = new LinkedHashMap();

    public synchronized void register(String name, CookieSpecFactory factory) {
        if (name == null) {
            throw new IllegalArgumentException("Name may not be null");
        } else if (factory != null) {
            this.registeredSpecs.put(name.toLowerCase(Locale.ENGLISH), factory);
        } else {
            throw new IllegalArgumentException("Cookie spec factory may not be null");
        }
    }

    public synchronized void unregister(String id) {
        if (id != null) {
            this.registeredSpecs.remove(id.toLowerCase(Locale.ENGLISH));
        } else {
            throw new IllegalArgumentException("Id may not be null");
        }
    }

    public synchronized CookieSpec getCookieSpec(String name, HttpParams params) throws IllegalStateException {
        CookieSpecFactory factory;
        if (name != null) {
            factory = this.registeredSpecs.get(name.toLowerCase(Locale.ENGLISH));
            if (factory != null) {
            } else {
                throw new IllegalStateException("Unsupported cookie spec: " + name);
            }
        } else {
            throw new IllegalArgumentException("Name may not be null");
        }
        return factory.newInstance(params);
    }

    public synchronized CookieSpec getCookieSpec(String name) throws IllegalStateException {
        return getCookieSpec(name, null);
    }

    public synchronized List<String> getSpecNames() {
        return new ArrayList(this.registeredSpecs.keySet());
    }

    public synchronized void setItems(Map<String, CookieSpecFactory> map) {
        if (map != null) {
            this.registeredSpecs.clear();
            this.registeredSpecs.putAll(map);
        }
    }
}
