package com.lody.virtual.helper.utils;

import com.ctetin.expandabletextviewlibrary.ExpandableTextView;
import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Member;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Proxy;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;
import p110z1.Consts;

/* loaded from: classes.dex */
public class Reflect {
    private final boolean isClass = true;
    private final Object object;

    private Reflect(Class<?> cls) {
        this.object = cls;
    }

    private Reflect(Object obj) {
        this.object = obj;
    }

    /* renamed from: on */
    public static Reflect m18997on(String str) throws ReflectException {
        return m18999on(forName(str));
    }

    /* renamed from: on */
    public static Reflect m18996on(String str, ClassLoader classLoader) throws ReflectException {
        return m18999on(forName(str, classLoader));
    }

    /* renamed from: on */
    public static Reflect m18999on(Class<?> cls) {
        return new Reflect(cls);
    }

    /* renamed from: on */
    public static Reflect m18998on(Object obj) {
        return new Reflect(obj);
    }

    public static <T extends AccessibleObject> T accessible(T t) {
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

    /* JADX INFO: Access modifiers changed from: private */
    public static String property(String str) {
        int length = str.length();
        if (length == 0) {
            return "";
        }
        if (length == 1) {
            return str.toLowerCase();
        }
        return str.substring(0, 1).toLowerCase() + str.substring(1);
    }

    /* renamed from: on */
    private static Reflect m18995on(Constructor<?> constructor, Object... objArr) throws ReflectException {
        try {
            return m18998on(((Constructor) accessible(constructor)).newInstance(objArr));
        } catch (Exception e) {
            throw new ReflectException(e);
        }
    }

    /* renamed from: on */
    private static Reflect m18994on(Method method, Object obj, Object... objArr) throws ReflectException {
        try {
            accessible(method);
            if (method.getReturnType() != Void.TYPE) {
                return m18998on(method.invoke(obj, objArr));
            }
            method.invoke(obj, objArr);
            return m18998on(obj);
        } catch (Exception e) {
            throw new ReflectException(e);
        }
    }

    private static Object unwrap(Object obj) {
        return obj instanceof Reflect ? ((Reflect) obj).get() : obj;
    }

    private static Class<?>[] types(Object... objArr) {
        if (objArr == null) {
            return new Class[0];
        }
        Class<?>[] clsArr = new Class[objArr.length];
        for (int i = 0; i < objArr.length; i++) {
            Object obj = objArr[i];
            clsArr[i] = obj == null ? NULL.class : obj.getClass();
        }
        return clsArr;
    }

    private static Class<?> forName(String str) throws ReflectException {
        try {
            return Class.forName(str);
        } catch (Exception e) {
            throw new ReflectException(e);
        }
    }

    private static Class<?> forName(String str, ClassLoader classLoader) throws ReflectException {
        try {
            return Class.forName(str, true, classLoader);
        } catch (Exception e) {
            throw new ReflectException(e);
        }
    }

    public static Class<?> wrapper(Class<?> cls) {
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

    public static Object defaultValue(Class<?> cls) {
        Class<?> wrapper = wrapper(cls);
        if (wrapper != null && wrapper.isPrimitive()) {
            if (Boolean.class == wrapper) {
                return false;
            }
            if (Number.class.isAssignableFrom(wrapper)) {
                return 0;
            }
            if (Character.class == wrapper) {
                return (char) 0;
            }
            if (Void.class == wrapper) {
                return null;
            }
        }
        return null;
    }

    public <T> T get() {
        return (T) this.object;
    }

    public Reflect set(String str, Object obj) throws ReflectException {
        try {
            Field field0 = field0(str);
            field0.setAccessible(true);
            field0.set(this.object, unwrap(obj));
            return this;
        } catch (Exception e) {
            throw new ReflectException(e);
        }
    }

    public <T> T opt(String str) {
        try {
            return (T) field(str).get();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public <T> T get(String str) throws ReflectException {
        return (T) field(str).get();
    }

    public Reflect field(String str) throws ReflectException {
        try {
            return m18998on(field0(str).get(this.object));
        } catch (Exception e) {
            throw new ReflectException(this.object.getClass().getName(), e);
        }
    }

    private Field field0(String str) throws ReflectException {
        Class<?> type = type();
        try {
            return type.getField(str);
        } catch (NoSuchFieldException e) {
            do {
                try {
                    return (Field) accessible(type.getDeclaredField(str));
                } catch (NoSuchFieldException unused) {
                    type = type.getSuperclass();
                    if (type == null) {
                        throw new ReflectException(e);
                    }
                }
            } while (type == null);
            throw new ReflectException(e);
        }
    }

    public Map<String, Reflect> fields() {
        Field[] declaredFields;
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        Class<?> type = type();
        do {
            for (Field field : type.getDeclaredFields()) {
                if ((!this.isClass) ^ Modifier.isStatic(field.getModifiers())) {
                    String name = field.getName();
                    if (!linkedHashMap.containsKey(name)) {
                        linkedHashMap.put(name, field(name));
                    }
                }
            }
            type = type.getSuperclass();
        } while (type != null);
        return linkedHashMap;
    }

    public Reflect call(String str) throws ReflectException {
        return call(str, new Object[0]);
    }

    public Reflect call(String str, Object... objArr) throws ReflectException {
        Class<?>[] types = types(objArr);
        try {
            try {
                return m18994on(exactMethod(str, types), this.object, objArr);
            } catch (NoSuchMethodException e) {
                throw new ReflectException(e);
            }
        } catch (NoSuchMethodException unused) {
            return m18994on(similarMethod(str, types), this.object, objArr);
        }
    }

    public Method exactMethod(String str, Class<?>[] clsArr) throws NoSuchMethodException {
        Class<?> type = type();
        try {
            return type.getMethod(str, clsArr);
        } catch (NoSuchMethodException unused) {
            do {
                try {
                    return type.getDeclaredMethod(str, clsArr);
                } catch (NoSuchMethodException unused2) {
                    type = type.getSuperclass();
                    if (type == null) {
                        throw new NoSuchMethodException();
                    }
                }
            } while (type == null);
            throw new NoSuchMethodException();
        }
    }

    private Method similarMethod(String str, Class<?>[] clsArr) throws NoSuchMethodException {
        Method[] methods;
        Method[] declaredMethods;
        Class<?> type = type();
        for (Method method : type.getMethods()) {
            if (isSimilarSignature(method, str, clsArr)) {
                return method;
            }
        }
        do {
            for (Method method2 : type.getDeclaredMethods()) {
                if (isSimilarSignature(method2, str, clsArr)) {
                    return method2;
                }
            }
            type = type.getSuperclass();
        } while (type != null);
        throw new NoSuchMethodException("No similar method " + str + " with params " + Arrays.toString(clsArr) + " could be found on type " + type() + Consts.f23430h);
    }

    private boolean isSimilarSignature(Method method, String str, Class<?>[] clsArr) {
        return method.getName().equals(str) && match(method.getParameterTypes(), clsArr);
    }

    public Reflect create() throws ReflectException {
        return create(new Object[0]);
    }

    public Reflect create(Object... objArr) throws ReflectException {
        Constructor<?>[] declaredConstructors;
        Class<?>[] types = types(objArr);
        try {
            return m18995on(type().getDeclaredConstructor(types), objArr);
        } catch (NoSuchMethodException e) {
            for (Constructor<?> constructor : type().getDeclaredConstructors()) {
                if (match(constructor.getParameterTypes(), types)) {
                    return m18995on(constructor, objArr);
                }
            }
            throw new ReflectException(e);
        }
    }

    /* renamed from: as */
    public <P> P m19000as(Class<P> cls) {
        final boolean z = this.object instanceof Map;
        return (P) Proxy.newProxyInstance(cls.getClassLoader(), new Class[]{cls}, new InvocationHandler() { // from class: com.lody.virtual.helper.utils.Reflect.1
            @Override // java.lang.reflect.InvocationHandler
            public Object invoke(Object obj, Method method, Object[] objArr) throws Throwable {
                String name = method.getName();
                try {
                    return Reflect.m18998on(Reflect.this.object).call(name, objArr).get();
                } catch (ReflectException e) {
                    if (z) {
                        Map map = (Map) Reflect.this.object;
                        int length = objArr == null ? 0 : objArr.length;
                        if (length == 0 && name.startsWith("get")) {
                            return map.get(Reflect.property(name.substring(3)));
                        }
                        if (length == 0 && name.startsWith("is")) {
                            return map.get(Reflect.property(name.substring(2)));
                        }
                        if (length == 1 && name.startsWith("set")) {
                            map.put(Reflect.property(name.substring(3)), objArr[0]);
                            return null;
                        }
                    }
                    throw e;
                }
            }
        });
    }

    private boolean match(Class<?>[] clsArr, Class<?>[] clsArr2) {
        if (clsArr.length != clsArr2.length) {
            return false;
        }
        for (int i = 0; i < clsArr2.length; i++) {
            if (clsArr2[i] != NULL.class && !wrapper(clsArr[i]).isAssignableFrom(wrapper(clsArr2[i]))) {
                return false;
            }
        }
        return true;
    }

    public int hashCode() {
        return this.object.hashCode();
    }

    public boolean equals(Object obj) {
        return (obj instanceof Reflect) && this.object.equals(((Reflect) obj).get());
    }

    public String toString() {
        return this.object.toString();
    }

    public Class<?> type() {
        if (this.isClass) {
            return (Class) this.object;
        }
        return this.object.getClass();
    }

    public static String getMethodDetails(Method method) {
        StringBuilder sb = new StringBuilder(40);
        sb.append(Modifier.toString(method.getModifiers()));
        sb.append(ExpandableTextView.f6958c);
        sb.append(method.getReturnType().getName());
        sb.append(ExpandableTextView.f6958c);
        sb.append(method.getName());
        sb.append("(");
        Class<?>[] parameterTypes = method.getParameterTypes();
        for (Class<?> cls : parameterTypes) {
            sb.append(cls.getName());
            sb.append(", ");
        }
        if (parameterTypes.length > 0) {
            sb.delete(sb.length() - 2, sb.length());
        }
        sb.append(")");
        return sb.toString();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static class NULL {
        private NULL() {
        }
    }

    public Reflect callBest(String str, Object... objArr) throws ReflectException {
        Class<?>[] types = types(objArr);
        Method[] declaredMethods = type().getDeclaredMethods();
        int length = declaredMethods.length;
        Method method = null;
        int i = 0;
        char c = 0;
        while (true) {
            if (i >= length) {
                break;
            }
            Method method2 = declaredMethods[i];
            if (isSimilarSignature(method2, str, types)) {
                c = 2;
                method = method2;
                break;
            }
            if (matchObjectMethod(method2, str, types)) {
                method = method2;
                c = 1;
            } else if (method2.getName().equals(str) && method2.getParameterTypes().length == 0 && c == 0) {
                method = method2;
            }
            i++;
        }
        if (method != null) {
            if (c == 0) {
                objArr = new Object[0];
            }
            return m18994on(method, this.object, c == 1 ? new Object[]{objArr} : objArr);
        }
        throw new ReflectException("no method found for " + str, new NoSuchMethodException("No best method " + str + " with params " + Arrays.toString(types) + " could be found on type " + type() + Consts.f23430h));
    }

    public void printFields() {
        Map<String, Reflect> fields;
        if (!(this.object == null || (fields = fields()) == null)) {
            StringBuilder sb = new StringBuilder();
            for (Map.Entry<String, Reflect> entry : fields.entrySet()) {
                String key = entry.getKey();
                Object obj = entry.getValue().object;
                String obj2 = obj == null ? "null" : obj.toString();
                sb.append(key + " = " + obj2);
                sb.append('\n');
            }
            VLog.m18992e((this.isClass ? (Class) this.object : this.object.getClass()).getSimpleName(), sb.toString());
        }
    }

    private boolean matchObjectMethod(Method method, String str, Class<?>[] clsArr) {
        return method.getName().equals(str) && matchObject(method.getParameterTypes());
    }

    private boolean matchObject(Class<?>[] clsArr) {
        return clsArr.length > 0 && clsArr[0].isAssignableFrom(Object[].class);
    }
}
