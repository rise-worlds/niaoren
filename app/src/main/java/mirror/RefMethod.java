package mirror;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/* renamed from: mirror.h */
/* loaded from: classes2.dex */
public class RefMethod<T> {

    /* renamed from: a */
    private Method f14695a;

    public RefMethod(Class<?> cls, Field field) throws NoSuchMethodException {
        int i = 0;
        if (!field.isAnnotationPresent(MethodParams.class)) {
            if (!field.isAnnotationPresent(MethodReflectParams.class)) {
                Method[] declaredMethods = cls.getDeclaredMethods();
                int length = declaredMethods.length;
                while (true) {
                    if (i >= length) {
                        break;
                    }
                    Method method = declaredMethods[i];
                    if (method.getName().equals(field.getName())) {
                        this.f14695a = method;
                        this.f14695a.setAccessible(true);
                        break;
                    }
                    i++;
                }
            } else {
                String[] value = ((MethodReflectParams) field.getAnnotation(MethodReflectParams.class)).value();
                Class<?>[] clsArr = new Class[value.length];
                while (i < value.length) {
                    Class<?> a = RefStaticMethod.m14871a(value[i]);
                    if (a == null) {
                        try {
                            a = Class.forName(value[i]);
                        } catch (ClassNotFoundException e) {
                            e.printStackTrace();
                        }
                    }
                    clsArr[i] = a;
                    i++;
                }
                this.f14695a = cls.getDeclaredMethod(field.getName(), clsArr);
                this.f14695a.setAccessible(true);
            }
        } else {
            Class<?>[] value2 = ((MethodParams) field.getAnnotation(MethodParams.class)).value();
            while (i < value2.length) {
                Class<?> cls2 = value2[i];
                if (cls2.getClassLoader() == getClass().getClassLoader()) {
                    try {
                        Class.forName(cls2.getName());
                        value2[i] = (Class) cls2.getField("TYPE").get(null);
                    } catch (Throwable th) {
                        throw new RuntimeException(th);
                    }
                }
                i++;
            }
            this.f14695a = cls.getDeclaredMethod(field.getName(), value2);
            this.f14695a.setAccessible(true);
        }
        if (this.f14695a == null) {
            throw new NoSuchMethodException(field.getName());
        }
    }

    public T call(Object obj, Object... objArr) {
        try {
            return (T) this.f14695a.invoke(obj, objArr);
        } catch (InvocationTargetException e) {
            if (e.getCause() != null) {
                e.getCause().printStackTrace();
                return null;
            }
            e.printStackTrace();
            return null;
        } catch (Throwable th) {
            th.printStackTrace();
            return null;
        }
    }

    public T callWithException(Object obj, Object... objArr) throws Throwable {
        try {
            return (T) this.f14695a.invoke(obj, objArr);
        } catch (InvocationTargetException e) {
            if (e.getCause() != null) {
                throw e.getCause();
            }
            throw e;
        }
    }

    public Class<?>[] paramList() {
        return this.f14695a.getParameterTypes();
    }
}
