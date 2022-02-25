package com.blankj.utilcode.util;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Member;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import p110z1.Consts;

/* renamed from: com.blankj.utilcode.util.am */
/* loaded from: classes.dex */
public final class ReflectUtils {

    /* renamed from: a */
    private final Class<?> f6697a;

    /* renamed from: b */
    private final Object f6698b;

    private ReflectUtils(Class<?> cls) {
        this(cls, cls);
    }

    private ReflectUtils(Class<?> cls, Object obj) {
        this.f6697a = cls;
        this.f6698b = obj;
    }

    /* renamed from: a */
    public static ReflectUtils m23518a(String str) throws C0981b {
        return m23520a(m23494e(str));
    }

    /* renamed from: a */
    public static ReflectUtils m23517a(String str, ClassLoader classLoader) throws C0981b {
        return m23520a(m23502b(str, classLoader));
    }

    /* renamed from: a */
    public static ReflectUtils m23520a(Class<?> cls) throws C0981b {
        return new ReflectUtils(cls);
    }

    /* renamed from: a */
    public static ReflectUtils m23519a(Object obj) throws C0981b {
        return new ReflectUtils(obj == null ? Object.class : obj.getClass(), obj);
    }

    /* renamed from: e */
    private static Class<?> m23494e(String str) {
        try {
            return Class.forName(str);
        } catch (ClassNotFoundException e) {
            throw new C0981b(e);
        }
    }

    /* renamed from: b */
    private static Class<?> m23502b(String str, ClassLoader classLoader) {
        try {
            return Class.forName(str, true, classLoader);
        } catch (ClassNotFoundException e) {
            throw new C0981b(e);
        }
    }

    /* renamed from: a */
    public ReflectUtils m23523a() {
        return m23507a(new Object[0]);
    }

    /* renamed from: a */
    public ReflectUtils m23507a(Object... objArr) {
        Constructor<?>[] declaredConstructors;
        Class<?>[] b = m23499b(objArr);
        try {
            return m23512a(m23498c().getDeclaredConstructor(b), objArr);
        } catch (NoSuchMethodException e) {
            ArrayList arrayList = new ArrayList();
            for (Constructor<?> constructor : m23498c().getDeclaredConstructors()) {
                if (m23508a(constructor.getParameterTypes(), b)) {
                    arrayList.add(constructor);
                }
            }
            if (!arrayList.isEmpty()) {
                m23509a((List<Constructor<?>>) arrayList);
                return m23512a(arrayList.get(0), objArr);
            }
            throw new C0981b(e);
        }
    }

    /* renamed from: b */
    private Class<?>[] m23499b(Object... objArr) {
        if (objArr == null) {
            return new Class[0];
        }
        Class<?>[] clsArr = new Class[objArr.length];
        for (int i = 0; i < objArr.length; i++) {
            Object obj = objArr[i];
            clsArr[i] = obj == null ? C0980a.class : obj.getClass();
        }
        return clsArr;
    }

    /* renamed from: a */
    private void m23509a(List<Constructor<?>> list) {
        Collections.sort(list, new Comparator<Constructor<?>>() { // from class: com.blankj.utilcode.util.am.1
            /* renamed from: a */
            public int compare(Constructor<?> constructor, Constructor<?> constructor2) {
                Class<?>[] parameterTypes = constructor.getParameterTypes();
                Class<?>[] parameterTypes2 = constructor2.getParameterTypes();
                int length = parameterTypes.length;
                for (int i = 0; i < length; i++) {
                    if (!parameterTypes[i].equals(parameterTypes2[i])) {
                        return ReflectUtils.this.m23497c(parameterTypes[i]).isAssignableFrom(ReflectUtils.this.m23497c(parameterTypes2[i])) ? 1 : -1;
                    }
                }
                return 0;
            }
        });
    }

    /* renamed from: a */
    private ReflectUtils m23512a(Constructor<?> constructor, Object... objArr) {
        try {
            return new ReflectUtils(constructor.getDeclaringClass(), ((Constructor) m23513a((ReflectUtils) constructor)).newInstance(objArr));
        } catch (Exception e) {
            throw new C0981b(e);
        }
    }

    /* renamed from: b */
    public ReflectUtils m23503b(String str) {
        try {
            Field f = m23493f(str);
            return new ReflectUtils(f.getType(), f.get(this.f6698b));
        } catch (IllegalAccessException e) {
            throw new C0981b(e);
        }
    }

    /* renamed from: a */
    public ReflectUtils m23516a(String str, Object obj) {
        try {
            m23493f(str).set(this.f6698b, m23504b(obj));
            return this;
        } catch (Exception e) {
            throw new C0981b(e);
        }
    }

    /* renamed from: f */
    private Field m23493f(String str) throws IllegalAccessException {
        Field g = m23492g(str);
        if ((g.getModifiers() & 16) == 16) {
            try {
                Field declaredField = Field.class.getDeclaredField("modifiers");
                declaredField.setAccessible(true);
                declaredField.setInt(g, g.getModifiers() & (-17));
            } catch (NoSuchFieldException unused) {
            }
        }
        return g;
    }

    /* renamed from: g */
    private Field m23492g(String str) {
        Class<?> c = m23498c();
        try {
            return (Field) m23513a((ReflectUtils) c.getField(str));
        } catch (NoSuchFieldException e) {
            do {
                try {
                    return (Field) m23513a((ReflectUtils) c.getDeclaredField(str));
                } catch (NoSuchFieldException unused) {
                    c = c.getSuperclass();
                    if (c != null) {
                        throw new C0981b(e);
                    }
                }
            } while (c != null);
            throw new C0981b(e);
        }
    }

    /* renamed from: b */
    private Object m23504b(Object obj) {
        return obj instanceof ReflectUtils ? ((ReflectUtils) obj).m23506b() : obj;
    }

    /* renamed from: c */
    public ReflectUtils m23496c(String str) throws C0981b {
        return m23514a(str, new Object[0]);
    }

    /* renamed from: a */
    public ReflectUtils m23514a(String str, Object... objArr) throws C0981b {
        Class<?>[] b = m23499b(objArr);
        try {
            try {
                return m23511a(m23515a(str, b), this.f6698b, objArr);
            } catch (NoSuchMethodException e) {
                throw new C0981b(e);
            }
        } catch (NoSuchMethodException unused) {
            return m23511a(m23501b(str, b), this.f6698b, objArr);
        }
    }

    /* renamed from: a */
    private ReflectUtils m23511a(Method method, Object obj, Object... objArr) {
        try {
            m23513a((ReflectUtils) method);
            if (method.getReturnType() != Void.TYPE) {
                return m23519a(method.invoke(obj, objArr));
            }
            method.invoke(obj, objArr);
            return m23519a(obj);
        } catch (Exception e) {
            throw new C0981b(e);
        }
    }

    /* renamed from: a */
    private Method m23515a(String str, Class<?>[] clsArr) throws NoSuchMethodException {
        Class<?> c = m23498c();
        try {
            return c.getMethod(str, clsArr);
        } catch (NoSuchMethodException unused) {
            do {
                try {
                    return c.getDeclaredMethod(str, clsArr);
                } catch (NoSuchMethodException unused2) {
                    c = c.getSuperclass();
                    if (c == null) {
                        throw new NoSuchMethodException();
                    }
                }
            } while (c == null);
            throw new NoSuchMethodException();
        }
    }

    /* renamed from: b */
    private Method m23501b(String str, Class<?>[] clsArr) throws NoSuchMethodException {
        Method[] methods;
        Method[] declaredMethods;
        Class<?> c = m23498c();
        ArrayList arrayList = new ArrayList();
        for (Method method : c.getMethods()) {
            if (m23510a(method, str, clsArr)) {
                arrayList.add(method);
            }
        }
        if (!arrayList.isEmpty()) {
            m23500b((List<Method>) arrayList);
            return arrayList.get(0);
        }
        do {
            for (Method method2 : c.getDeclaredMethods()) {
                if (m23510a(method2, str, clsArr)) {
                    arrayList.add(method2);
                }
            }
            if (!arrayList.isEmpty()) {
                m23500b((List<Method>) arrayList);
                return arrayList.get(0);
            }
            c = c.getSuperclass();
        } while (c != null);
        throw new NoSuchMethodException("No similar method " + str + " with params " + Arrays.toString(clsArr) + " could be found on type " + m23498c() + Consts.f23430h);
    }

    /* renamed from: b */
    private void m23500b(List<Method> list) {
        Collections.sort(list, new Comparator<Method>() { // from class: com.blankj.utilcode.util.am.2
            /* renamed from: a */
            public int compare(Method method, Method method2) {
                Class<?>[] parameterTypes = method.getParameterTypes();
                Class<?>[] parameterTypes2 = method2.getParameterTypes();
                int length = parameterTypes.length;
                for (int i = 0; i < length; i++) {
                    if (!parameterTypes[i].equals(parameterTypes2[i])) {
                        return ReflectUtils.this.m23497c(parameterTypes[i]).isAssignableFrom(ReflectUtils.this.m23497c(parameterTypes2[i])) ? 1 : -1;
                    }
                }
                return 0;
            }
        });
    }

    /* renamed from: a */
    private boolean m23510a(Method method, String str, Class<?>[] clsArr) {
        return method.getName().equals(str) && m23508a(method.getParameterTypes(), clsArr);
    }

    /* renamed from: a */
    private boolean m23508a(Class<?>[] clsArr, Class<?>[] clsArr2) {
        if (clsArr.length != clsArr2.length) {
            return false;
        }
        for (int i = 0; i < clsArr2.length; i++) {
            if (!(clsArr2[i] == C0980a.class || m23497c(clsArr[i]).isAssignableFrom(m23497c(clsArr2[i])))) {
                return false;
            }
        }
        return true;
    }

    /* renamed from: a */
    private <T extends AccessibleObject> T m23513a(T t) {
        if (t == null) {
            return null;
        }
        if (t instanceof Member) {
            Member member = (Member) t;
            if (Modifier.isPublic(member.getModifiers()) && Modifier.isPublic(member.getDeclaringClass().getModifiers())) {
                return t;
            }
        }
        if (!t.isAccessible()) {
            t.setAccessible(true);
        }
        return t;
    }

    /* renamed from: b */
    public <P> P m23505b(Class<P> cls) {
        final boolean z = this.f6698b instanceof Map;
        return (P) Proxy.newProxyInstance(cls.getClassLoader(), new Class[]{cls}, new InvocationHandler() { // from class: com.blankj.utilcode.util.am.3
            @Override // java.lang.reflect.InvocationHandler
            public Object invoke(Object obj, Method method, Object[] objArr) {
                String name = method.getName();
                try {
                    return ReflectUtils.m23519a(ReflectUtils.this.f6698b).m23514a(name, objArr).m23506b();
                } catch (C0981b e) {
                    if (z) {
                        Map map = (Map) ReflectUtils.this.f6698b;
                        int length = objArr == null ? 0 : objArr.length;
                        if (length == 0 && name.startsWith("get")) {
                            return map.get(ReflectUtils.m23491h(name.substring(3)));
                        }
                        if (length == 0 && name.startsWith("is")) {
                            return map.get(ReflectUtils.m23491h(name.substring(2)));
                        }
                        if (length == 1 && name.startsWith("set")) {
                            map.put(ReflectUtils.m23491h(name.substring(3)), objArr[0]);
                            return null;
                        }
                    }
                    throw e;
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: h */
    public static String m23491h(String str) {
        int length = str.length();
        if (length == 0) {
            return "";
        }
        if (length == 1) {
            return str.toLowerCase();
        }
        return str.substring(0, 1).toLowerCase() + str.substring(1);
    }

    /* renamed from: c */
    private Class<?> m23498c() {
        return this.f6697a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: c */
    public Class<?> m23497c(Class<?> cls) {
        if (cls == null) {
            return null;
        }
        if (cls.isPrimitive()) {
            if (Boolean.TYPE == cls) {
                return Boolean.class;
            }
            if (Integer.TYPE == cls) {
                return Integer.class;
            }
            if (Long.TYPE == cls) {
                return Long.class;
            }
            if (Short.TYPE == cls) {
                return Short.class;
            }
            if (Byte.TYPE == cls) {
                return Byte.class;
            }
            if (Double.TYPE == cls) {
                return Double.class;
            }
            if (Float.TYPE == cls) {
                return Float.class;
            }
            if (Character.TYPE == cls) {
                return Character.class;
            }
            if (Void.TYPE == cls) {
                return Void.class;
            }
        }
        return cls;
    }

    /* renamed from: b */
    public <T> T m23506b() {
        return (T) this.f6698b;
    }

    public int hashCode() {
        return this.f6698b.hashCode();
    }

    public boolean equals(Object obj) {
        return (obj instanceof ReflectUtils) && this.f6698b.equals(((ReflectUtils) obj).m23506b());
    }

    public String toString() {
        return this.f6698b.toString();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: ReflectUtils.java */
    /* renamed from: com.blankj.utilcode.util.am$a */
    /* loaded from: classes.dex */
    public static class C0980a {
        private C0980a() {
        }
    }

    /* compiled from: ReflectUtils.java */
    /* renamed from: com.blankj.utilcode.util.am$b */
    /* loaded from: classes.dex */
    public static class C0981b extends RuntimeException {
        private static final long serialVersionUID = 858774075258496016L;

        public C0981b(String str) {
            super(str);
        }

        public C0981b(String str, Throwable th) {
            super(str, th);
        }

        public C0981b(Throwable th) {
            super(th);
        }
    }
}
