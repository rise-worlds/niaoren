package com.lidroid.xutils.cache;

import java.util.concurrent.ConcurrentHashMap;

/* loaded from: classes.dex */
public class KeyExpiryMap<K, V> extends ConcurrentHashMap<K, Long> {
    private static final int DEFAULT_CONCURRENCY_LEVEL = 16;
    private static final long serialVersionUID = 1;

    /* JADX WARN: Multi-variable type inference failed */
    @Override // java.util.concurrent.ConcurrentHashMap, java.util.AbstractMap, java.util.Map
    public /* bridge */ /* synthetic */ Object put(Object obj, Object obj2) {
        return put((KeyExpiryMap<K, V>) obj, (Long) obj2);
    }

    public KeyExpiryMap(int i, float f, int i2) {
        super(i, f, i2);
    }

    public KeyExpiryMap(int i, float f) {
        super(i, f, 16);
    }

    public KeyExpiryMap(int i) {
        super(i);
    }

    public KeyExpiryMap() {
    }

    @Override // java.util.concurrent.ConcurrentHashMap, java.util.AbstractMap, java.util.Map
    public synchronized Long get(Object obj) {
        if (!containsKey(obj)) {
            return null;
        }
        return (Long) super.get(obj);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public synchronized Long put(K k, Long l) {
        if (containsKey(k)) {
            remove((Object) k);
        }
        return (Long) super.put((KeyExpiryMap<K, V>) k, (K) l);
    }

    @Override // java.util.concurrent.ConcurrentHashMap, java.util.AbstractMap, java.util.Map
    public synchronized boolean containsKey(Object obj) {
        boolean z;
        z = false;
        Long l = (Long) super.get(obj);
        if (l == null || System.currentTimeMillis() >= l.longValue()) {
            remove(obj);
        } else {
            z = true;
        }
        return z;
    }

    @Override // java.util.concurrent.ConcurrentHashMap, java.util.AbstractMap, java.util.Map
    public synchronized Long remove(Object obj) {
        return (Long) super.remove(obj);
    }

    @Override // java.util.concurrent.ConcurrentHashMap, java.util.AbstractMap, java.util.Map
    public synchronized void clear() {
        super.clear();
    }
}
