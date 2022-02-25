package com.tendcloud.tenddata;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.CopyOnWriteArraySet;

/* compiled from: td */
/* renamed from: com.tendcloud.tenddata.ev */
/* loaded from: classes2.dex */
public final class C2858ev {

    /* renamed from: a */
    private static volatile C2858ev f13925a;

    /* renamed from: b */
    private final ConcurrentMap f13926b = new ConcurrentHashMap();

    /* renamed from: c */
    private final ThreadLocal f13927c = new C2860ew(this);

    /* renamed from: d */
    private final ThreadLocal f13928d = new C2861ex(this);

    /* renamed from: e */
    private final Map f13929e = new HashMap();

    /* renamed from: a */
    public static C2858ev m15778a() {
        if (f13925a == null) {
            synchronized (C2858ev.class) {
                if (f13925a == null) {
                    f13925a = new C2858ev();
                }
            }
        }
        return f13925a;
    }

    private C2858ev() {
    }

    public void register(Object obj) {
        if (obj != null) {
            try {
                Map a = C2862ey.m15770a(obj);
                for (Class cls : a.keySet()) {
                    Set set = (Set) this.f13926b.get(cls);
                    if (set == null) {
                        set = new CopyOnWriteArraySet();
                        Set set2 = (Set) this.f13926b.putIfAbsent(cls, set);
                        if (set2 != null) {
                            set = set2;
                        }
                    }
                    if (!set.addAll((Set) a.get(cls))) {
                        return;
                    }
                }
            } catch (Throwable th) {
                C2933hb.postSDKError(th);
            }
        }
    }

    public void unregister(Object obj) {
        if (obj != null) {
            try {
                for (Map.Entry entry : C2862ey.m15770a(obj).entrySet()) {
                    Set<C2863ez> a = m15777a((Class) entry.getKey());
                    Collection<?> collection = (Collection) entry.getValue();
                    if (a != null && a.containsAll(collection)) {
                        for (C2863ez ezVar : a) {
                            if (collection.contains(ezVar)) {
                                ezVar.m15768b();
                            }
                        }
                        a.removeAll(collection);
                    }
                    return;
                }
            } catch (Throwable th) {
                C2933hb.postSDKError(th);
            }
        }
    }

    public void post(Object obj) {
        if (obj != null) {
            try {
                boolean z = false;
                for (Class cls : m15774b(obj.getClass())) {
                    Set<C2863ez> a = m15777a(cls);
                    if (a != null && !a.isEmpty()) {
                        z = true;
                        for (C2863ez ezVar : a) {
                            m15776a(obj, ezVar);
                        }
                    }
                }
                if (!z && !(obj instanceof C2865fa)) {
                    post(new C2865fa(this, obj));
                }
                m15775b();
            } catch (Throwable th) {
                C2933hb.postSDKError(th);
            }
        }
    }

    /* renamed from: a */
    protected void m15776a(Object obj, C2863ez ezVar) {
        try {
            ((ConcurrentLinkedQueue) this.f13927c.get()).offer(new C2859a(obj, ezVar));
        } catch (Throwable th) {
            C2933hb.postSDKError(th);
        }
    }

    /* renamed from: b */
    protected void m15775b() {
        try {
            if (!((Boolean) this.f13928d.get()).booleanValue()) {
                this.f13928d.set(true);
                while (true) {
                    C2859a aVar = (C2859a) ((ConcurrentLinkedQueue) this.f13927c.get()).poll();
                    if (aVar != null) {
                        if (aVar.handler.m15769a()) {
                            m15773b(aVar.event, aVar.handler);
                        }
                    } else {
                        return;
                    }
                }
            }
        } finally {
            this.f13928d.set(false);
        }
    }

    /* renamed from: b */
    protected void m15773b(Object obj, C2863ez ezVar) {
        try {
            ezVar.handleEvent(obj);
        } catch (Throwable th) {
            C2933hb.postSDKError(th);
        }
    }

    /* renamed from: a */
    Set m15777a(Class cls) {
        try {
            return (Set) this.f13926b.get(cls);
        } catch (Throwable th) {
            C2933hb.postSDKError(th);
            return null;
        }
    }

    /* renamed from: b */
    Set m15774b(Class cls) {
        try {
            Set set = (Set) this.f13929e.get(cls);
            if (set != null) {
                return set;
            }
            Set c = m15772c(cls);
            this.f13929e.put(cls, c);
            return c;
        } catch (Throwable th) {
            C2933hb.postSDKError(th);
            return null;
        }
    }

    /* renamed from: c */
    private Set m15772c(Class cls) {
        try {
            LinkedList linkedList = new LinkedList();
            HashSet hashSet = new HashSet();
            linkedList.add(cls);
            while (!linkedList.isEmpty()) {
                Class cls2 = (Class) linkedList.remove(0);
                hashSet.add(cls2);
                Class superclass = cls2.getSuperclass();
                if (superclass != null) {
                    linkedList.add(superclass);
                }
            }
            return hashSet;
        } catch (Throwable th) {
            C2933hb.postSDKError(th);
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: td */
    /* renamed from: com.tendcloud.tenddata.ev$a */
    /* loaded from: classes2.dex */
    public static class C2859a {
        final Object event;
        final C2863ez handler;

        public C2859a(Object obj, C2863ez ezVar) {
            this.event = obj;
            this.handler = ezVar;
        }
    }
}
