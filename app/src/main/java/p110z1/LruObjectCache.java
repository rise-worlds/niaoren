package p110z1;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* renamed from: z1.sb */
/* loaded from: classes3.dex */
public class LruObjectCache implements ObjectCache {

    /* renamed from: a */
    private final int f23232a;

    /* renamed from: b */
    private final ConcurrentHashMap<Class<?>, Map<Object, Object>> f23233b = new ConcurrentHashMap<>();

    public LruObjectCache(int i) {
        this.f23232a = i;
    }

    @Override // p110z1.ObjectCache
    /* renamed from: a */
    public synchronized <T> void mo1013a(Class<T> cls) {
        if (this.f23233b.get(cls) == null) {
            this.f23233b.put(cls, Collections.synchronizedMap(new C5557a(this.f23232a)));
        }
    }

    @Override // p110z1.ObjectCache
    /* renamed from: a */
    public <T, ID> T mo1012a(Class<T> cls, ID id) {
        Map<Object, Object> d = m1015d(cls);
        if (d == null) {
            return null;
        }
        return (T) d.get(id);
    }

    @Override // p110z1.ObjectCache
    /* renamed from: a */
    public <T, ID> void mo1011a(Class<T> cls, ID id, T t) {
        Map<Object, Object> d = m1015d(cls);
        if (d != null) {
            d.put(id, t);
        }
    }

    @Override // p110z1.ObjectCache
    /* renamed from: b */
    public <T> void mo1008b(Class<T> cls) {
        Map<Object, Object> d = m1015d(cls);
        if (d != null) {
            d.clear();
        }
    }

    @Override // p110z1.ObjectCache
    /* renamed from: a */
    public void mo1014a() {
        for (Map<Object, Object> map : this.f23233b.values()) {
            map.clear();
        }
    }

    @Override // p110z1.ObjectCache
    /* renamed from: b */
    public <T, ID> void mo1007b(Class<T> cls, ID id) {
        Map<Object, Object> d = m1015d(cls);
        if (d != null) {
            d.remove(id);
        }
    }

    @Override // p110z1.ObjectCache
    /* renamed from: b */
    public <T, ID> T mo1006b(Class<T> cls, ID id, ID id2) {
        T t;
        Map<Object, Object> d = m1015d(cls);
        if (d == null || (t = (T) d.remove(id)) == null) {
            return null;
        }
        d.put(id2, t);
        return t;
    }

    @Override // p110z1.ObjectCache
    /* renamed from: c */
    public <T> int mo1004c(Class<T> cls) {
        Map<Object, Object> d = m1015d(cls);
        if (d == null) {
            return 0;
        }
        return d.size();
    }

    @Override // p110z1.ObjectCache
    /* renamed from: b */
    public int mo1009b() {
        int i = 0;
        for (Map<Object, Object> map : this.f23233b.values()) {
            i += map.size();
        }
        return i;
    }

    /* renamed from: d */
    private Map<Object, Object> m1015d(Class<?> cls) {
        Map<Object, Object> map = this.f23233b.get(cls);
        if (map == null) {
            return null;
        }
        return map;
    }

    /* compiled from: LruObjectCache.java */
    /* renamed from: z1.sb$a */
    /* loaded from: classes3.dex */
    private static class C5557a<K, V> extends LinkedHashMap<K, V> {
        private static final long serialVersionUID = -4566528080395573236L;
        private final int capacity;

        public C5557a(int i) {
            super(i, 0.75f, true);
            this.capacity = i;
        }

        @Override // java.util.LinkedHashMap
        protected boolean removeEldestEntry(Map.Entry<K, V> entry) {
            return size() > this.capacity;
        }
    }
}
