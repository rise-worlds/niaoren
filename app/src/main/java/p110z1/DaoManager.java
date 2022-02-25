package p110z1;

import java.lang.reflect.Constructor;
import java.sql.SQLException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/* renamed from: z1.rw */
/* loaded from: classes3.dex */
public class DaoManager {

    /* renamed from: a */
    private static Map<Class<?>, DatabaseTableConfig<?>> f23216a;

    /* renamed from: b */
    private static Map<C5552a, Dao<?, ?>> f23217b;

    /* renamed from: c */
    private static Map<C5553b, Dao<?, ?>> f23218c;

    /* renamed from: d */
    private static C5570ui f23219d = LoggerFactory.m545a(DaoManager.class);

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v10, types: [z1.rv] */
    /* JADX WARN: Type inference failed for: r1v11, types: [z1.rv] */
    /* renamed from: a */
    public static synchronized <D extends Dao<T, ?>, T> D m1027a(ConnectionSource wmVar, Class<T> cls) throws SQLException {
        D d;
        synchronized (DaoManager.class) {
            if (wmVar != null) {
                D d2 = (D) m1031a(new C5552a(wmVar, cls));
                if (d2 != null) {
                    return d2;
                }
                D d3 = (D) ((Dao) m1019c(wmVar, cls));
                if (d3 != null) {
                    return d3;
                }
                DatabaseTable wqVar = (DatabaseTable) cls.getAnnotation(DatabaseTable.class);
                if (!(wqVar == null || wqVar.m199b() == Void.class || wqVar.m199b() == BaseDaoImpl.class)) {
                    Class<?> b = wqVar.m199b();
                    Object[] objArr = {wmVar, cls};
                    Constructor<?> a = m1033a(b, objArr);
                    if (a == null && (a = m1033a(b, (objArr = new Object[]{wmVar}))) == null) {
                        throw new SQLException("Could not find public constructor with ConnectionSource and optional Class parameters " + b + ".  Missing static on class?");
                    }
                    try {
                        d = (D) ((Dao) a.newInstance(objArr));
                        f23219d.m595b("created dao for class {} from constructor", cls);
                        m1026a(wmVar, (Dao<?, ?>) d);
                        return d;
                    } catch (Exception e) {
                        throw SqlExceptionUtil.m529a("Could not call the constructor in class " + b, e);
                    }
                }
                DatabaseTableConfig<T> a2 = wmVar.mo249e().mo877a(wmVar, cls);
                if (a2 == null) {
                    d = BaseDaoImpl.m1123a(wmVar, (Class) cls);
                } else {
                    d = BaseDaoImpl.m1122a(wmVar, (DatabaseTableConfig) a2);
                }
                f23219d.m595b("created dao for class {} with reflection", cls);
                m1026a(wmVar, (Dao<?, ?>) d);
                return d;
            }
            throw new IllegalArgumentException("connectionSource argument cannot be null");
        }
    }

    /* renamed from: b */
    public static synchronized <D extends Dao<T, ?>, T> D m1022b(ConnectionSource wmVar, Class<T> cls) {
        D d;
        synchronized (DaoManager.class) {
            if (wmVar != null) {
                d = (D) m1031a(new C5552a(wmVar, cls));
            } else {
                throw new IllegalArgumentException("connectionSource argument cannot be null");
            }
        }
        return d;
    }

    /* renamed from: a */
    public static synchronized <D extends Dao<T, ?>, T> D m1025a(ConnectionSource wmVar, DatabaseTableConfig<T> wrVar) throws SQLException {
        D d;
        synchronized (DaoManager.class) {
            if (wmVar != null) {
                d = (D) m1017c(wmVar, wrVar);
            } else {
                throw new IllegalArgumentException("connectionSource argument cannot be null");
            }
        }
        return d;
    }

    /* renamed from: b */
    public static synchronized <D extends Dao<T, ?>, T> D m1020b(ConnectionSource wmVar, DatabaseTableConfig<T> wrVar) {
        synchronized (DaoManager.class) {
            if (wmVar != null) {
                D d = (D) m1029a(new C5553b(wmVar, wrVar));
                if (d == null) {
                    return null;
                }
                return d;
            }
            throw new IllegalArgumentException("connectionSource argument cannot be null");
        }
    }

    /* renamed from: a */
    public static synchronized void m1026a(ConnectionSource wmVar, Dao<?, ?> rvVar) {
        synchronized (DaoManager.class) {
            if (wmVar != null) {
                m1030a(new C5552a(wmVar, rvVar.mo1054i()), rvVar);
            } else {
                throw new IllegalArgumentException("connectionSource argument cannot be null");
            }
        }
    }

    /* renamed from: b */
    public static synchronized void m1021b(ConnectionSource wmVar, Dao<?, ?> rvVar) {
        synchronized (DaoManager.class) {
            if (wmVar != null) {
                m1023b(new C5552a(wmVar, rvVar.mo1054i()), rvVar);
            } else {
                throw new IllegalArgumentException("connectionSource argument cannot be null");
            }
        }
    }

    /* renamed from: c */
    public static synchronized void m1018c(ConnectionSource wmVar, Dao<?, ?> rvVar) {
        DatabaseTableConfig u;
        synchronized (DaoManager.class) {
            if (wmVar == null) {
                throw new IllegalArgumentException("connectionSource argument cannot be null");
            } else if (!(rvVar instanceof BaseDaoImpl) || (u = ((BaseDaoImpl) rvVar).m1115u()) == null) {
                m1030a(new C5552a(wmVar, rvVar.mo1054i()), rvVar);
            } else {
                m1028a(new C5553b(wmVar, u), rvVar);
            }
        }
    }

    /* renamed from: a */
    public static synchronized void m1034a() {
        synchronized (DaoManager.class) {
            if (f23216a != null) {
                f23216a.clear();
                f23216a = null;
            }
            m1024b();
        }
    }

    /* renamed from: b */
    public static synchronized void m1024b() {
        synchronized (DaoManager.class) {
            if (f23217b != null) {
                f23217b.clear();
                f23217b = null;
            }
            if (f23218c != null) {
                f23218c.clear();
                f23218c = null;
            }
        }
    }

    /* renamed from: a */
    public static synchronized void m1032a(Collection<DatabaseTableConfig<?>> collection) {
        HashMap hashMap;
        synchronized (DaoManager.class) {
            if (f23216a == null) {
                hashMap = new HashMap();
            } else {
                hashMap = new HashMap(f23216a);
            }
            for (DatabaseTableConfig<?> wrVar : collection) {
                hashMap.put(wrVar.m188b(), wrVar);
                f23219d.m585c("Loaded configuration for {}", wrVar.m188b());
            }
            f23216a = hashMap;
        }
    }

    /* renamed from: a */
    private static void m1030a(C5552a aVar, Dao<?, ?> rvVar) {
        if (f23217b == null) {
            f23217b = new HashMap();
        }
        f23217b.put(aVar, rvVar);
    }

    /* renamed from: b */
    private static void m1023b(C5552a aVar, Dao<?, ?> rvVar) {
        Map<C5552a, Dao<?, ?>> map = f23217b;
        if (map != null) {
            map.remove(aVar);
        }
    }

    /* renamed from: a */
    private static void m1028a(C5553b bVar, Dao<?, ?> rvVar) {
        if (f23218c == null) {
            f23218c = new HashMap();
        }
        f23218c.put(bVar, rvVar);
    }

    /* renamed from: a */
    private static <T> Dao<?, ?> m1031a(C5552a aVar) {
        if (f23217b == null) {
            f23217b = new HashMap();
        }
        Dao<?, ?> rvVar = f23217b.get(aVar);
        if (rvVar == null) {
            return null;
        }
        return rvVar;
    }

    /* renamed from: a */
    private static <T> Dao<?, ?> m1029a(C5553b bVar) {
        if (f23218c == null) {
            f23218c = new HashMap();
        }
        Dao<?, ?> rvVar = f23218c.get(bVar);
        if (rvVar == null) {
            return null;
        }
        return rvVar;
    }

    /* renamed from: a */
    private static Constructor<?> m1033a(Class<?> cls, Object[] objArr) {
        Constructor<?>[] constructors;
        boolean z;
        for (Constructor<?> constructor : cls.getConstructors()) {
            Class<?>[] parameterTypes = constructor.getParameterTypes();
            if (parameterTypes.length == objArr.length) {
                int i = 0;
                while (true) {
                    if (i >= parameterTypes.length) {
                        z = true;
                        break;
                    } else if (!parameterTypes[i].isAssignableFrom(objArr[i].getClass())) {
                        z = false;
                        break;
                    } else {
                        i++;
                    }
                }
                if (z) {
                    return constructor;
                }
            }
        }
        return null;
    }

    /* renamed from: c */
    private static <D, T> D m1019c(ConnectionSource wmVar, Class<T> cls) throws SQLException {
        DatabaseTableConfig<?> wrVar;
        Map<Class<?>, DatabaseTableConfig<?>> map = f23216a;
        if (map == null || (wrVar = map.get(cls)) == null) {
            return null;
        }
        return (D) m1017c(wmVar, wrVar);
    }

    /* renamed from: c */
    private static <D extends Dao<T, ?>, T> D m1017c(ConnectionSource wmVar, DatabaseTableConfig<T> wrVar) throws SQLException {
        D d;
        C5553b bVar = new C5553b(wmVar, wrVar);
        D d2 = (D) m1029a(bVar);
        if (d2 != null) {
            return d2;
        }
        Class<T> b = wrVar.m188b();
        C5552a aVar = new C5552a(wmVar, b);
        D d3 = (D) m1031a(aVar);
        if (d3 != null) {
            m1028a(bVar, (Dao<?, ?>) d3);
            return d3;
        }
        DatabaseTable wqVar = (DatabaseTable) wrVar.m188b().getAnnotation(DatabaseTable.class);
        if (wqVar == null || wqVar.m199b() == Void.class || wqVar.m199b() == BaseDaoImpl.class) {
            d = (D) BaseDaoImpl.m1122a(wmVar, (DatabaseTableConfig) wrVar);
        } else {
            Class<?> b2 = wqVar.m199b();
            Object[] objArr = {wmVar, wrVar};
            Constructor<?> a = m1033a(b2, objArr);
            if (a != null) {
                try {
                    d = (D) ((Dao) a.newInstance(objArr));
                } catch (Exception e) {
                    throw SqlExceptionUtil.m529a("Could not call the constructor in class " + b2, e);
                }
            } else {
                throw new SQLException("Could not find public constructor with ConnectionSource, DatabaseTableConfig parameters in class " + b2);
            }
        }
        m1028a(bVar, (Dao<?, ?>) d);
        f23219d.m595b("created dao for class {} from table config", b);
        if (m1031a(aVar) == null) {
            m1030a(aVar, (Dao<?, ?>) d);
        }
        return d;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: DaoManager.java */
    /* renamed from: z1.rw$a */
    /* loaded from: classes3.dex */
    public static class C5552a {

        /* renamed from: a */
        ConnectionSource f23220a;

        /* renamed from: b */
        Class<?> f23221b;

        public C5552a(ConnectionSource wmVar, Class<?> cls) {
            this.f23220a = wmVar;
            this.f23221b = cls;
        }

        public int hashCode() {
            return ((this.f23221b.hashCode() + 31) * 31) + this.f23220a.hashCode();
        }

        public boolean equals(Object obj) {
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            C5552a aVar = (C5552a) obj;
            return this.f23221b.equals(aVar.f23221b) && this.f23220a.equals(aVar.f23220a);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: DaoManager.java */
    /* renamed from: z1.rw$b */
    /* loaded from: classes3.dex */
    public static class C5553b {

        /* renamed from: a */
        ConnectionSource f23222a;

        /* renamed from: b */
        DatabaseTableConfig<?> f23223b;

        public C5553b(ConnectionSource wmVar, DatabaseTableConfig<?> wrVar) {
            this.f23222a = wmVar;
            this.f23223b = wrVar;
        }

        public int hashCode() {
            return ((this.f23223b.hashCode() + 31) * 31) + this.f23222a.hashCode();
        }

        public boolean equals(Object obj) {
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            C5553b bVar = (C5553b) obj;
            return this.f23223b.equals(bVar.f23223b) && this.f23222a.equals(bVar.f23222a);
        }
    }
}
