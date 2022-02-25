package com.j256.ormlite.android.apptools;

import android.content.Context;
import android.content.res.Resources;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import p110z1.BaseDaoImpl;
import p110z1.C5570ui;
import p110z1.DaoManager;
import p110z1.LoggerFactory;

/* renamed from: com.j256.ormlite.android.apptools.a */
/* loaded from: classes.dex */
public class OpenHelperManager {

    /* renamed from: a */
    private static final String f9399a = "open_helper_classname";

    /* renamed from: b */
    private static C5570ui f9400b = LoggerFactory.m545a(OpenHelperManager.class);

    /* renamed from: c */
    private static Class<? extends OrmLiteSqliteOpenHelper> f9401c = null;

    /* renamed from: d */
    private static volatile OrmLiteSqliteOpenHelper f9402d = null;

    /* renamed from: e */
    private static boolean f9403e = false;

    /* renamed from: f */
    private static int f9404f = 0;

    /* renamed from: a */
    public static synchronized void m19842a(Class<? extends OrmLiteSqliteOpenHelper> cls) {
        synchronized (OpenHelperManager.class) {
            if (cls == null) {
                f9401c = null;
            } else {
                m19839b(cls);
            }
        }
    }

    /* renamed from: a */
    public static synchronized void m19843a(OrmLiteSqliteOpenHelper bVar) {
        synchronized (OpenHelperManager.class) {
            f9402d = bVar;
        }
    }

    /* renamed from: a */
    public static synchronized <T extends OrmLiteSqliteOpenHelper> T m19844a(Context context, Class<T> cls) {
        T t;
        synchronized (OpenHelperManager.class) {
            m19839b(cls);
            t = (T) m19840b(context, cls);
        }
        return t;
    }

    @Deprecated
    /* renamed from: a */
    public static synchronized OrmLiteSqliteOpenHelper m19845a(Context context) {
        OrmLiteSqliteOpenHelper b;
        synchronized (OpenHelperManager.class) {
            if (f9401c == null) {
                if (context != null) {
                    m19839b(m19837d(context.getApplicationContext(), context.getClass()));
                } else {
                    throw new IllegalArgumentException("context argument is null");
                }
            }
            b = m19840b(context, f9401c);
        }
        return b;
    }

    @Deprecated
    /* renamed from: a */
    public static void m19846a() {
        m19841b();
    }

    /* renamed from: b */
    public static synchronized void m19841b() {
        synchronized (OpenHelperManager.class) {
            f9404f--;
            f9400b.m618a("releasing helper {}, instance count = {}", f9402d, Integer.valueOf(f9404f));
            if (f9404f <= 0) {
                if (f9402d != null) {
                    f9400b.m619a("zero instances, closing helper {}", f9402d);
                    f9402d.close();
                    f9402d = null;
                    f9403e = true;
                }
                if (f9404f < 0) {
                    f9400b.m565e("too many calls to release helper, instance count = {}", Integer.valueOf(f9404f));
                }
            }
        }
    }

    /* renamed from: b */
    private static void m19839b(Class<? extends OrmLiteSqliteOpenHelper> cls) {
        Class<? extends OrmLiteSqliteOpenHelper> cls2 = f9401c;
        if (cls2 == null) {
            f9401c = cls;
        } else if (cls2 != cls) {
            throw new IllegalStateException("Helper class was " + f9401c + " but is trying to be reset to " + cls);
        }
    }

    /* renamed from: b */
    private static <T extends OrmLiteSqliteOpenHelper> T m19840b(Context context, Class<T> cls) {
        if (f9402d == null) {
            if (f9403e) {
                f9400b.m586c("helper was already closed and is being re-opened");
            }
            if (context != null) {
                f9402d = m19838c(context.getApplicationContext(), f9401c);
                f9400b.m619a("zero instances, created helper {}", f9402d);
                BaseDaoImpl.m1117o();
                DaoManager.m1024b();
                f9404f = 0;
            } else {
                throw new IllegalArgumentException("context argument is null");
            }
        }
        f9404f++;
        f9400b.m618a("returning helper {}, instance count = {} ", f9402d, Integer.valueOf(f9404f));
        return (T) f9402d;
    }

    /* renamed from: c */
    private static OrmLiteSqliteOpenHelper m19838c(Context context, Class<? extends OrmLiteSqliteOpenHelper> cls) {
        try {
            try {
                return (OrmLiteSqliteOpenHelper) cls.getConstructor(Context.class).newInstance(context);
            } catch (Exception e) {
                throw new IllegalStateException("Could not construct instance of helper class " + cls, e);
            }
        } catch (Exception e2) {
            throw new IllegalStateException("Could not find public constructor that has a single (Context) argument for helper class " + cls, e2);
        }
    }

    /* renamed from: d */
    private static Class<? extends OrmLiteSqliteOpenHelper> m19837d(Context context, Class<?> cls) {
        Type[] actualTypeArguments;
        Resources resources = context.getResources();
        int identifier = resources.getIdentifier(f9399a, "string", context.getPackageName());
        if (identifier != 0) {
            String string = resources.getString(identifier);
            try {
                return Class.forName(string);
            } catch (Exception e) {
                throw new IllegalStateException("Could not create helper instance for class " + string, e);
            }
        } else {
            for (Class<?> cls2 = cls; cls2 != null; cls2 = cls2.getSuperclass()) {
                Type genericSuperclass = cls2.getGenericSuperclass();
                if (!(genericSuperclass == null || !(genericSuperclass instanceof ParameterizedType) || (actualTypeArguments = ((ParameterizedType) genericSuperclass).getActualTypeArguments()) == null || actualTypeArguments.length == 0)) {
                    for (Type type : actualTypeArguments) {
                        if (type instanceof Class) {
                            Class<? extends OrmLiteSqliteOpenHelper> cls3 = (Class) type;
                            if (OrmLiteSqliteOpenHelper.class.isAssignableFrom(cls3)) {
                                return cls3;
                            }
                        }
                    }
                    continue;
                }
            }
            throw new IllegalStateException("Could not find OpenHelperClass because none of the generic parameters of class " + cls + " extends OrmLiteSqliteOpenHelper.  You should use getHelper(Context, Class) instead.");
        }
    }
}
