package p110z1;

import java.lang.ref.Reference;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* renamed from: z1.se */
/* loaded from: classes3.dex */
public class ReferenceObjectCache implements ObjectCache {

    /* renamed from: a */
    private final ConcurrentHashMap<Class<?>, Map<Object, Reference<Object>>> f23234a = new ConcurrentHashMap<>();

    /* renamed from: b */
    private final boolean f23235b;

    public ReferenceObjectCache(boolean z) {
        this.f23235b = z;
    }

    /* renamed from: c */
    public static ReferenceObjectCache m1005c() {
        return new ReferenceObjectCache(true);
    }

    /* renamed from: d */
    public static ReferenceObjectCache m1003d() {
        return new ReferenceObjectCache(false);
    }

    @Override // p110z1.ObjectCache
    /* renamed from: a */
    public synchronized <T> void mo1013a(Class<T> cls) {
        if (this.f23234a.get(cls) == null) {
            this.f23234a.put(cls, new ConcurrentHashMap());
        }
    }

    @Override // p110z1.ObjectCache
    /* renamed from: a */
    public <T, ID> T mo1012a(Class<T> cls, ID id) {
        Reference<Object> reference;
        Map<Object, Reference<Object>> e = m1000e(cls);
        if (e == null || (reference = e.get(id)) == null) {
            return null;
        }
        T t = (T) reference.get();
        if (t != null) {
            return t;
        }
        e.remove(id);
        return null;
    }

    @Override // p110z1.ObjectCache
    /* renamed from: a */
    public <T, ID> void mo1011a(Class<T> cls, ID id, T t) {
        Map<Object, Reference<Object>> e = m1000e(cls);
        if (e == null) {
            return;
        }
        if (this.f23235b) {
            e.put(id, new WeakReference(t));
        } else {
            e.put(id, new SoftReference(t));
        }
    }

    @Override // p110z1.ObjectCache
    /* renamed from: b */
    public <T> void mo1008b(Class<T> cls) {
        Map<Object, Reference<Object>> e = m1000e(cls);
        if (e != null) {
            e.clear();
        }
    }

    @Override // p110z1.ObjectCache
    /* renamed from: a */
    public void mo1014a() {
        for (Map<Object, Reference<Object>> map : this.f23234a.values()) {
            map.clear();
        }
    }

    @Override // p110z1.ObjectCache
    /* renamed from: b */
    public <T, ID> void mo1007b(Class<T> cls, ID id) {
        Map<Object, Reference<Object>> e = m1000e(cls);
        if (e != null) {
            e.remove(id);
        }
    }

    @Override // p110z1.ObjectCache
    /* renamed from: b */
    public <T, ID> T mo1006b(Class<T> cls, ID id, ID id2) {
        Reference<Object> remove;
        Map<Object, Reference<Object>> e = m1000e(cls);
        if (e == null || (remove = e.remove(id)) == null) {
            return null;
        }
        e.put(id2, remove);
        return (T) remove.get();
    }

    @Override // p110z1.ObjectCache
    /* renamed from: c */
    public <T> int mo1004c(Class<T> cls) {
        Map<Object, Reference<Object>> e = m1000e(cls);
        if (e == null) {
            return 0;
        }
        return e.size();
    }

    @Override // p110z1.ObjectCache
    /* renamed from: b */
    public int mo1009b() {
        int i = 0;
        for (Map<Object, Reference<Object>> map : this.f23234a.values()) {
            i += map.size();
        }
        return i;
    }

    /* renamed from: d */
    public <T> void m1002d(Class<T> cls) {
        Map<Object, Reference<Object>> e = m1000e(cls);
        if (e != null) {
            m1010a(e);
        }
    }

    /* renamed from: e */
    public <T> void m1001e() {
        for (Map<Object, Reference<Object>> map : this.f23234a.values()) {
            m1010a(map);
        }
    }

    /* renamed from: a */
    private void m1010a(Map<Object, Reference<Object>> map) {
        Iterator<Map.Entry<Object, Reference<Object>>> it = map.entrySet().iterator();
        while (it.hasNext()) {
            if (it.next().getValue().get() == null) {
                it.remove();
            }
        }
    }

    /* renamed from: e */
    private Map<Object, Reference<Object>> m1000e(Class<?> cls) {
        Map<Object, Reference<Object>> map = this.f23234a.get(cls);
        if (map == null) {
            return null;
        }
        return map;
    }
}
