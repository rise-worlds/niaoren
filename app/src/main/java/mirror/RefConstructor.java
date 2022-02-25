package mirror;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;

/* renamed from: mirror.c */
/* loaded from: classes2.dex */
public class RefConstructor<T> {

    /* renamed from: a */
    private Constructor<?> f14690a;

    public RefConstructor(Class<?> cls, Field field) throws NoSuchMethodException {
        if (field.isAnnotationPresent(MethodParams.class)) {
            this.f14690a = cls.getDeclaredConstructor(((MethodParams) field.getAnnotation(MethodParams.class)).value());
        } else {
            int i = 0;
            if (field.isAnnotationPresent(MethodReflectParams.class)) {
                String[] value = ((MethodReflectParams) field.getAnnotation(MethodReflectParams.class)).value();
                Class<?>[] clsArr = new Class[value.length];
                while (i < value.length) {
                    try {
                        Class<?> a = RefStaticMethod.m14871a(value[i]);
                        clsArr[i] = a == null ? Class.forName(value[i]) : a;
                        i++;
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                this.f14690a = cls.getDeclaredConstructor(clsArr);
            } else {
                this.f14690a = cls.getDeclaredConstructor(new Class[0]);
            }
        }
        Constructor<?> constructor = this.f14690a;
        if (constructor != null && !constructor.isAccessible()) {
            this.f14690a.setAccessible(true);
        }
    }

    public T newInstance() {
        try {
            return (T) this.f14690a.newInstance(new Object[0]);
        } catch (Exception unused) {
            return null;
        }
    }

    public T newInstance(Object... objArr) {
        try {
            return (T) this.f14690a.newInstance(objArr);
        } catch (Exception unused) {
            return null;
        }
    }
}
