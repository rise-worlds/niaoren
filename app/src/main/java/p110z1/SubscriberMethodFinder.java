package p110z1;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* JADX INFO: Access modifiers changed from: package-private */
/* renamed from: z1.czs */
/* loaded from: classes3.dex */
public class SubscriberMethodFinder {

    /* renamed from: a */
    private static final int f21183a = 64;

    /* renamed from: b */
    private static final int f21184b = 4096;

    /* renamed from: c */
    private static final int f21185c = 5192;

    /* renamed from: h */
    private static final int f21187h = 4;

    /* renamed from: e */
    private List<SubscriberInfoIndex> f21189e;

    /* renamed from: f */
    private final boolean f21190f;

    /* renamed from: g */
    private final boolean f21191g;

    /* renamed from: d */
    private static final Map<Class<?>, List<SubscriberMethod>> f21186d = new ConcurrentHashMap();

    /* renamed from: i */
    private static final C5220a[] f21188i = new C5220a[4];

    /* JADX INFO: Access modifiers changed from: package-private */
    public SubscriberMethodFinder(List<SubscriberInfoIndex> list, boolean z, boolean z2) {
        this.f21189e = list;
        this.f21190f = z;
        this.f21191g = z2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public List<SubscriberMethod> m3384a(Class<?> cls) {
        List<SubscriberMethod> list;
        List<SubscriberMethod> list2 = f21186d.get(cls);
        if (list2 != null) {
            return list2;
        }
        if (this.f21191g) {
            list = m3379c(cls);
        } else {
            list = m3381b(cls);
        }
        if (!list.isEmpty()) {
            f21186d.put(cls, list);
            return list;
        }
        throw new EventBusException("Subscriber " + cls + " and its super classes have no public methods with the @Subscribe annotation");
    }

    /* renamed from: b */
    private List<SubscriberMethod> m3381b(Class<?> cls) {
        SubscriberMethod[] d;
        C5220a b = m3382b();
        b.m3376a(cls);
        while (b.f21197f != null) {
            b.f21199h = m3380b(b);
            if (b.f21199h != null) {
                for (SubscriberMethod czrVar : b.f21199h.mo3366d()) {
                    if (b.m3375a(czrVar.f21177a, czrVar.f21179c)) {
                        b.f21192a.add(czrVar);
                    }
                }
            } else {
                m3378c(b);
            }
            b.m3374b();
        }
        return m3383a(b);
    }

    /* renamed from: a */
    private List<SubscriberMethod> m3383a(C5220a aVar) {
        ArrayList arrayList = new ArrayList(aVar.f21192a);
        aVar.m3377a();
        synchronized (f21188i) {
            int i = 0;
            while (true) {
                if (i >= 4) {
                    break;
                } else if (f21188i[i] == null) {
                    f21188i[i] = aVar;
                    break;
                } else {
                    i++;
                }
            }
        }
        return arrayList;
    }

    /* renamed from: b */
    private C5220a m3382b() {
        synchronized (f21188i) {
            for (int i = 0; i < 4; i++) {
                C5220a aVar = f21188i[i];
                if (aVar != null) {
                    f21188i[i] = null;
                    return aVar;
                }
            }
            return new C5220a();
        }
    }

    /* renamed from: b */
    private SubscriberInfo m3380b(C5220a aVar) {
        if (!(aVar.f21199h == null || aVar.f21199h.mo3368b() == null)) {
            SubscriberInfo b = aVar.f21199h.mo3368b();
            if (aVar.f21197f == b.mo3369a()) {
                return b;
            }
        }
        List<SubscriberInfoIndex> list = this.f21189e;
        if (list == null) {
            return null;
        }
        for (SubscriberInfoIndex czyVar : list) {
            SubscriberInfo a = czyVar.m3365a(aVar.f21197f);
            if (a != null) {
                return a;
            }
        }
        return null;
    }

    /* renamed from: c */
    private List<SubscriberMethod> m3379c(Class<?> cls) {
        C5220a b = m3382b();
        b.m3376a(cls);
        while (b.f21197f != null) {
            m3378c(b);
            b.m3374b();
        }
        return m3383a(b);
    }

    /* renamed from: c */
    private void m3378c(C5220a aVar) {
        Method[] methodArr;
        try {
            methodArr = aVar.f21197f.getDeclaredMethods();
        } catch (Throwable unused) {
            methodArr = aVar.f21197f.getMethods();
            aVar.f21198g = true;
        }
        for (Method method : methodArr) {
            int modifiers = method.getModifiers();
            if ((modifiers & 1) != 0 && (modifiers & f21185c) == 0) {
                Class<?>[] parameterTypes = method.getParameterTypes();
                if (parameterTypes.length == 1) {
                    Subscribe czpVar = (Subscribe) method.getAnnotation(Subscribe.class);
                    if (czpVar != null) {
                        Class<?> cls = parameterTypes[0];
                        if (aVar.m3375a(method, cls)) {
                            aVar.f21192a.add(new SubscriberMethod(method, cls, czpVar.m3389a(), czpVar.m3387c(), czpVar.m3388b()));
                        }
                    }
                } else if (this.f21190f && method.isAnnotationPresent(Subscribe.class)) {
                    throw new EventBusException("@Subscribe method " + (method.getDeclaringClass().getName() + Consts.f23430h + method.getName()) + "must have exactly 1 parameter but has " + parameterTypes.length);
                }
            } else if (this.f21190f && method.isAnnotationPresent(Subscribe.class)) {
                throw new EventBusException((method.getDeclaringClass().getName() + Consts.f23430h + method.getName()) + " is a illegal @Subscribe method: must be public, non-static, and non-abstract");
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public static void m3385a() {
        f21186d.clear();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: SubscriberMethodFinder.java */
    /* renamed from: z1.czs$a */
    /* loaded from: classes3.dex */
    public static class C5220a {

        /* renamed from: a */
        final List<SubscriberMethod> f21192a = new ArrayList();

        /* renamed from: b */
        final Map<Class, Object> f21193b = new HashMap();

        /* renamed from: c */
        final Map<String, Class> f21194c = new HashMap();

        /* renamed from: d */
        final StringBuilder f21195d = new StringBuilder(128);

        /* renamed from: e */
        Class<?> f21196e;

        /* renamed from: f */
        Class<?> f21197f;

        /* renamed from: g */
        boolean f21198g;

        /* renamed from: h */
        SubscriberInfo f21199h;

        C5220a() {
        }

        /* renamed from: a */
        void m3376a(Class<?> cls) {
            this.f21197f = cls;
            this.f21196e = cls;
            this.f21198g = false;
            this.f21199h = null;
        }

        /* renamed from: a */
        void m3377a() {
            this.f21192a.clear();
            this.f21193b.clear();
            this.f21194c.clear();
            this.f21195d.setLength(0);
            this.f21196e = null;
            this.f21197f = null;
            this.f21198g = false;
            this.f21199h = null;
        }

        /* renamed from: a */
        boolean m3375a(Method method, Class<?> cls) {
            Object put = this.f21193b.put(cls, method);
            if (put == null) {
                return true;
            }
            if (put instanceof Method) {
                if (m3373b((Method) put, cls)) {
                    this.f21193b.put(cls, this);
                } else {
                    throw new IllegalStateException();
                }
            }
            return m3373b(method, cls);
        }

        /* renamed from: b */
        private boolean m3373b(Method method, Class<?> cls) {
            this.f21195d.setLength(0);
            this.f21195d.append(method.getName());
            StringBuilder sb = this.f21195d;
            sb.append(Typography.f21053e);
            sb.append(cls.getName());
            String sb2 = this.f21195d.toString();
            Class<?> declaringClass = method.getDeclaringClass();
            Class put = this.f21194c.put(sb2, declaringClass);
            if (put == null || put.isAssignableFrom(declaringClass)) {
                return true;
            }
            this.f21194c.put(sb2, put);
            return false;
        }

        /* renamed from: b */
        void m3374b() {
            if (this.f21198g) {
                this.f21197f = null;
                return;
            }
            this.f21197f = this.f21197f.getSuperclass();
            String name = this.f21197f.getName();
            if (name.startsWith("java.") || name.startsWith("javax.") || name.startsWith("android.")) {
                this.f21197f = null;
            }
        }
    }
}
