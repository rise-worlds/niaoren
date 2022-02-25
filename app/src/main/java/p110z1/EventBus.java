package p110z1;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.logging.Level;

/* renamed from: z1.czf */
/* loaded from: classes3.dex */
public class EventBus {

    /* renamed from: a */
    public static String f21113a = "EventBus";

    /* renamed from: b */
    static volatile EventBus f21114b;

    /* renamed from: c */
    private static final EventBusBuilder f21115c = new EventBusBuilder();

    /* renamed from: d */
    private static final Map<Class<?>, List<Class<?>>> f21116d = new HashMap();

    /* renamed from: e */
    private final Map<Class<?>, CopyOnWriteArrayList<Subscription>> f21117e;

    /* renamed from: f */
    private final Map<Object, List<Class<?>>> f21118f;

    /* renamed from: g */
    private final Map<Class<?>, Object> f21119g;

    /* renamed from: h */
    private final ThreadLocal<C5215b> f21120h;

    /* renamed from: i */
    private final MainThreadSupport f21121i;

    /* renamed from: j */
    private final Poster f21122j;

    /* renamed from: k */
    private final BackgroundPoster f21123k;

    /* renamed from: l */
    private final AsyncPoster f21124l;

    /* renamed from: m */
    private final SubscriberMethodFinder f21125m;

    /* renamed from: n */
    private final ExecutorService f21126n;

    /* renamed from: o */
    private final boolean f21127o;

    /* renamed from: p */
    private final boolean f21128p;

    /* renamed from: q */
    private final boolean f21129q;

    /* renamed from: r */
    private final boolean f21130r;

    /* renamed from: s */
    private final boolean f21131s;

    /* renamed from: t */
    private final boolean f21132t;

    /* renamed from: u */
    private final int f21133u;

    /* renamed from: v */
    private final Logger f21134v;

    /* compiled from: EventBus.java */
    /* renamed from: z1.czf$a */
    /* loaded from: classes3.dex */
    interface AbstractC5214a {
        /* renamed from: a */
        void m3419a(List<SubscriberExceptionEvent> list);
    }

    /* renamed from: a */
    public static EventBus m3448a() {
        if (f21114b == null) {
            synchronized (EventBus.class) {
                if (f21114b == null) {
                    f21114b = new EventBus();
                }
            }
        }
        return f21114b;
    }

    /* renamed from: b */
    public static EventBusBuilder m3436b() {
        return new EventBusBuilder();
    }

    /* renamed from: c */
    public static void m3432c() {
        SubscriberMethodFinder.m3385a();
        f21116d.clear();
    }

    public EventBus() {
        this(f21115c);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public EventBus(EventBusBuilder czgVar) {
        this.f21120h = new ThreadLocal<C5215b>() { // from class: z1.czf.1
            /* JADX INFO: Access modifiers changed from: protected */
            /* renamed from: a */
            public C5215b initialValue() {
                return new C5215b();
            }
        };
        this.f21134v = czgVar.m3418a();
        this.f21117e = new HashMap();
        this.f21118f = new HashMap();
        this.f21119g = new ConcurrentHashMap();
        this.f21121i = czgVar.m3412b();
        MainThreadSupport czkVar = this.f21121i;
        this.f21122j = czkVar != null ? czkVar.mo3396a(this) : null;
        this.f21123k = new BackgroundPoster(this);
        this.f21124l = new AsyncPoster(this);
        this.f21133u = czgVar.f21154k != null ? czgVar.f21154k.size() : 0;
        this.f21125m = new SubscriberMethodFinder(czgVar.f21154k, czgVar.f21151h, czgVar.f21150g);
        this.f21128p = czgVar.f21144a;
        this.f21129q = czgVar.f21145b;
        this.f21130r = czgVar.f21146c;
        this.f21131s = czgVar.f21147d;
        this.f21127o = czgVar.f21148e;
        this.f21132t = czgVar.f21149f;
        this.f21126n = czgVar.f21152i;
    }

    /* renamed from: a */
    public void m3446a(Object obj) {
        List<SubscriberMethod> a = this.f21125m.m3384a(obj.getClass());
        synchronized (this) {
            for (SubscriberMethod czrVar : a) {
                m3442a(obj, czrVar);
            }
        }
    }

    /* renamed from: a */
    private void m3442a(Object obj, SubscriberMethod czrVar) {
        Class<?> cls = czrVar.f21179c;
        Subscription cztVar = new Subscription(obj, czrVar);
        CopyOnWriteArrayList<Subscription> copyOnWriteArrayList = this.f21117e.get(cls);
        if (copyOnWriteArrayList == null) {
            copyOnWriteArrayList = new CopyOnWriteArrayList<>();
            this.f21117e.put(cls, copyOnWriteArrayList);
        } else if (copyOnWriteArrayList.contains(cztVar)) {
            throw new EventBusException("Subscriber " + obj.getClass() + " already registered to event " + cls);
        }
        int size = copyOnWriteArrayList.size();
        for (int i = 0; i <= size; i++) {
            if (i == size || czrVar.f21180d > copyOnWriteArrayList.get(i).f21201b.f21180d) {
                copyOnWriteArrayList.add(i, cztVar);
                break;
            }
        }
        List<Class<?>> list = this.f21118f.get(obj);
        if (list == null) {
            list = new ArrayList<>();
            this.f21118f.put(obj, list);
        }
        list.add(cls);
        if (!czrVar.f21181e) {
            return;
        }
        if (this.f21132t) {
            for (Map.Entry<Class<?>, Object> entry : this.f21119g.entrySet()) {
                if (cls.isAssignableFrom(entry.getKey())) {
                    m3433b(cztVar, entry.getValue());
                }
            }
            return;
        }
        m3433b(cztVar, this.f21119g.get(cls));
    }

    /* renamed from: b */
    private void m3433b(Subscription cztVar, Object obj) {
        if (obj != null) {
            m3437a(cztVar, obj, m3422g());
        }
    }

    /* renamed from: g */
    private boolean m3422g() {
        MainThreadSupport czkVar = this.f21121i;
        if (czkVar != null) {
            return czkVar.mo3397a();
        }
        return true;
    }

    /* renamed from: b */
    public synchronized boolean m3434b(Object obj) {
        return this.f21118f.containsKey(obj);
    }

    /* renamed from: a */
    private void m3445a(Object obj, Class<?> cls) {
        CopyOnWriteArrayList<Subscription> copyOnWriteArrayList = this.f21117e.get(cls);
        if (copyOnWriteArrayList != null) {
            int size = copyOnWriteArrayList.size();
            int i = 0;
            while (i < size) {
                Subscription cztVar = copyOnWriteArrayList.get(i);
                if (cztVar.f21200a == obj) {
                    cztVar.f21202c = false;
                    copyOnWriteArrayList.remove(i);
                    i--;
                    size--;
                }
                i++;
            }
        }
    }

    /* renamed from: c */
    public synchronized void m3430c(Object obj) {
        List<Class<?>> list = this.f21118f.get(obj);
        if (list != null) {
            for (Class<?> cls : list) {
                m3445a(obj, cls);
            }
            this.f21118f.remove(obj);
        } else {
            Logger czjVar = this.f21134v;
            Level level = Level.WARNING;
            czjVar.mo3399a(level, "Subscriber to unregister was not registered before: " + obj.getClass());
        }
    }

    /* renamed from: d */
    public void m3427d(Object obj) {
        C5215b bVar = this.f21120h.get();
        List<Object> list = bVar.f21137a;
        list.add(obj);
        if (!bVar.f21138b) {
            bVar.f21139c = m3422g();
            bVar.f21138b = true;
            if (!bVar.f21142f) {
                while (true) {
                    try {
                        if (!list.isEmpty()) {
                            m3444a(list.remove(0), bVar);
                        } else {
                            return;
                        }
                    } finally {
                        bVar.f21138b = false;
                        bVar.f21139c = false;
                    }
                }
            } else {
                throw new EventBusException("Internal error. Abort state was not reset");
            }
        }
    }

    /* renamed from: e */
    public void m3425e(Object obj) {
        C5215b bVar = this.f21120h.get();
        if (!bVar.f21138b) {
            throw new EventBusException("This method may only be called from inside event handling methods on the posting thread");
        } else if (obj == null) {
            throw new EventBusException("Event may not be null");
        } else if (bVar.f21141e != obj) {
            throw new EventBusException("Only the currently handled event may be aborted");
        } else if (bVar.f21140d.f21201b.f21178b == ThreadMode.POSTING) {
            bVar.f21142f = true;
        } else {
            throw new EventBusException(" event handlers may only abort the incoming event");
        }
    }

    /* renamed from: f */
    public void m3423f(Object obj) {
        synchronized (this.f21119g) {
            this.f21119g.put(obj.getClass(), obj);
        }
        m3427d(obj);
    }

    /* renamed from: a */
    public <T> T m3447a(Class<T> cls) {
        T cast;
        synchronized (this.f21119g) {
            cast = cls.cast(this.f21119g.get(cls));
        }
        return cast;
    }

    /* renamed from: b */
    public <T> T m3435b(Class<T> cls) {
        T cast;
        synchronized (this.f21119g) {
            cast = cls.cast(this.f21119g.remove(cls));
        }
        return cast;
    }

    /* renamed from: g */
    public boolean m3421g(Object obj) {
        synchronized (this.f21119g) {
            Class<?> cls = obj.getClass();
            if (!obj.equals(this.f21119g.get(cls))) {
                return false;
            }
            this.f21119g.remove(cls);
            return true;
        }
    }

    /* renamed from: d */
    public void m3429d() {
        synchronized (this.f21119g) {
            this.f21119g.clear();
        }
    }

    /* renamed from: c */
    public boolean m3431c(Class<?> cls) {
        CopyOnWriteArrayList<Subscription> copyOnWriteArrayList;
        List<Class<?>> d = m3428d(cls);
        if (d != null) {
            int size = d.size();
            for (int i = 0; i < size; i++) {
                Class<?> cls2 = d.get(i);
                synchronized (this) {
                    copyOnWriteArrayList = this.f21117e.get(cls2);
                }
                if (!(copyOnWriteArrayList == null || copyOnWriteArrayList.isEmpty())) {
                    return true;
                }
            }
        }
        return false;
    }

    /* renamed from: a */
    private void m3444a(Object obj, C5215b bVar) throws Error {
        boolean z;
        Class<?> cls = obj.getClass();
        if (this.f21132t) {
            List<Class<?>> d = m3428d(cls);
            int size = d.size();
            z = false;
            for (int i = 0; i < size; i++) {
                z |= m3443a(obj, bVar, d.get(i));
            }
        } else {
            z = m3443a(obj, bVar, cls);
        }
        if (!z) {
            if (this.f21129q) {
                Logger czjVar = this.f21134v;
                Level level = Level.FINE;
                czjVar.mo3399a(level, "No subscribers registered for event " + cls);
            }
            if (!(!this.f21131s || cls == NoSubscriberEvent.class || cls == SubscriberExceptionEvent.class)) {
                m3427d(new NoSubscriberEvent(this, obj));
            }
        }
    }

    /* renamed from: a */
    private boolean m3443a(Object obj, C5215b bVar, Class<?> cls) {
        CopyOnWriteArrayList<Subscription> copyOnWriteArrayList;
        synchronized (this) {
            copyOnWriteArrayList = this.f21117e.get(cls);
        }
        if (copyOnWriteArrayList == null || copyOnWriteArrayList.isEmpty()) {
            return false;
        }
        Iterator<Subscription> it = copyOnWriteArrayList.iterator();
        while (it.hasNext()) {
            Subscription next = it.next();
            bVar.f21141e = obj;
            bVar.f21140d = next;
            try {
                m3437a(next, obj, bVar.f21139c);
                if (bVar.f21142f) {
                    return true;
                }
            } finally {
                bVar.f21141e = null;
                bVar.f21140d = null;
                bVar.f21142f = false;
            }
        }
        return true;
    }

    /* renamed from: a */
    private void m3437a(Subscription cztVar, Object obj, boolean z) {
        switch (cztVar.f21201b.f21178b) {
            case POSTING:
                m3439a(cztVar, obj);
                return;
            case MAIN:
                if (z) {
                    m3439a(cztVar, obj);
                    return;
                } else {
                    this.f21122j.mo3390a(cztVar, obj);
                    return;
                }
            case MAIN_ORDERED:
                Poster czoVar = this.f21122j;
                if (czoVar != null) {
                    czoVar.mo3390a(cztVar, obj);
                    return;
                } else {
                    m3439a(cztVar, obj);
                    return;
                }
            case BACKGROUND:
                if (z) {
                    this.f21123k.mo3390a(cztVar, obj);
                    return;
                } else {
                    m3439a(cztVar, obj);
                    return;
                }
            case ASYNC:
                this.f21124l.mo3390a(cztVar, obj);
                return;
            default:
                throw new IllegalStateException("Unknown thread mode: " + cztVar.f21201b.f21178b);
        }
    }

    /* renamed from: d */
    private static List<Class<?>> m3428d(Class<?> cls) {
        List<Class<?>> list;
        synchronized (f21116d) {
            list = f21116d.get(cls);
            if (list == null) {
                list = new ArrayList<>();
                for (Class<?> cls2 = cls; cls2 != null; cls2 = cls2.getSuperclass()) {
                    list.add(cls2);
                    m3441a(list, cls2.getInterfaces());
                }
                f21116d.put(cls, list);
            }
        }
        return list;
    }

    /* renamed from: a */
    static void m3441a(List<Class<?>> list, Class<?>[] clsArr) {
        for (Class<?> cls : clsArr) {
            if (!list.contains(cls)) {
                list.add(cls);
                m3441a(list, cls.getInterfaces());
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public void m3440a(PendingPost czmVar) {
        Object obj = czmVar.f21168a;
        Subscription cztVar = czmVar.f21169b;
        PendingPost.m3395a(czmVar);
        if (cztVar.f21202c) {
            m3439a(cztVar, obj);
        }
    }

    /* renamed from: a */
    void m3439a(Subscription cztVar, Object obj) {
        try {
            cztVar.f21201b.f21177a.invoke(cztVar.f21200a, obj);
        } catch (IllegalAccessException e) {
            throw new IllegalStateException("Unexpected exception", e);
        } catch (InvocationTargetException e2) {
            m3438a(cztVar, obj, e2.getCause());
        }
    }

    /* renamed from: a */
    private void m3438a(Subscription cztVar, Object obj, Throwable th) {
        if (obj instanceof SubscriberExceptionEvent) {
            if (this.f21128p) {
                Logger czjVar = this.f21134v;
                Level level = Level.SEVERE;
                czjVar.mo3398a(level, "SubscriberExceptionEvent subscriber " + cztVar.f21200a.getClass() + " threw an exception", th);
                SubscriberExceptionEvent czqVar = (SubscriberExceptionEvent) obj;
                Logger czjVar2 = this.f21134v;
                Level level2 = Level.SEVERE;
                czjVar2.mo3398a(level2, "Initial event " + czqVar.f21175c + " caused exception in " + czqVar.f21176d, czqVar.f21174b);
            }
        } else if (!this.f21127o) {
            if (this.f21128p) {
                Logger czjVar3 = this.f21134v;
                Level level3 = Level.SEVERE;
                czjVar3.mo3398a(level3, "Could not dispatch event: " + obj.getClass() + " to subscribing class " + cztVar.f21200a.getClass(), th);
            }
            if (this.f21130r) {
                m3427d(new SubscriberExceptionEvent(this, th, obj, cztVar.f21200a));
            }
        } else {
            throw new EventBusException("Invoking subscriber failed", th);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: EventBus.java */
    /* renamed from: z1.czf$b */
    /* loaded from: classes3.dex */
    public static final class C5215b {

        /* renamed from: a */
        final List<Object> f21137a = new ArrayList();

        /* renamed from: b */
        boolean f21138b;

        /* renamed from: c */
        boolean f21139c;

        /* renamed from: d */
        Subscription f21140d;

        /* renamed from: e */
        Object f21141e;

        /* renamed from: f */
        boolean f21142f;

        C5215b() {
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: e */
    public ExecutorService m3426e() {
        return this.f21126n;
    }

    /* renamed from: f */
    public Logger m3424f() {
        return this.f21134v;
    }

    public String toString() {
        return "EventBus[indexCount=" + this.f21133u + ", eventInheritance=" + this.f21132t + "]";
    }
}
