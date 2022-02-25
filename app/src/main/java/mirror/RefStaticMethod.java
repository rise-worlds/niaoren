package mirror;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/* renamed from: mirror.k */
/* loaded from: classes2.dex */
public class RefStaticMethod<T> {

    /* renamed from: a */
    private Method f14698a;

    /* renamed from: b */
    private String f14699b;

    /* renamed from: c */
    private String f14700c;

    public RefStaticMethod(Class<?> cls, Field field) throws NoSuchMethodException {
        Class<?> cls2;
        this.f14700c = field.getName();
        this.f14699b = cls.getName();
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
                        this.f14698a = method;
                        this.f14698a.setAccessible(true);
                        break;
                    }
                    i++;
                }
            } else {
                String[] value = ((MethodReflectParams) field.getAnnotation(MethodReflectParams.class)).value();
                Class<?>[] clsArr = new Class[value.length];
                Class<?>[] clsArr2 = new Class[value.length];
                boolean z = false;
                while (i < value.length) {
                    Class<?> a = m14871a(value[i]);
                    if (a == null) {
                        try {
                            a = Class.forName(value[i]);
                        } catch (ClassNotFoundException e) {
                            e.printStackTrace();
                        }
                    }
                    clsArr[i] = a;
                    if ("java.util.HashSet".equals(value[i])) {
                        try {
                            cls2 = Class.forName("android.util.ArraySet");
                        } catch (ClassNotFoundException unused) {
                            cls2 = a;
                        }
                        if (cls2 != null) {
                            clsArr2[i] = cls2;
                        } else {
                            clsArr2[i] = a;
                        }
                        z = true;
                    } else {
                        clsArr2[i] = a;
                    }
                    i++;
                }
                try {
                    this.f14698a = cls.getDeclaredMethod(field.getName(), clsArr);
                } catch (Exception e2) {
                    e2.printStackTrace();
                    if (z) {
                        this.f14698a = cls.getDeclaredMethod(field.getName(), clsArr2);
                    }
                }
                this.f14698a.setAccessible(true);
            }
        } else {
            Class<?>[] value2 = ((MethodParams) field.getAnnotation(MethodParams.class)).value();
            while (i < value2.length) {
                Class<?> cls3 = value2[i];
                if (cls3.getClassLoader() == getClass().getClassLoader()) {
                    try {
                        Class.forName(cls3.getName());
                        value2[i] = (Class) cls3.getField("TYPE").get(null);
                    } catch (Throwable th) {
                        throw new RuntimeException(th);
                    }
                }
                i++;
            }
            this.f14698a = cls.getDeclaredMethod(field.getName(), value2);
            this.f14698a.setAccessible(true);
        }
        if (this.f14698a == null) {
            throw new NoSuchMethodException(field.getName());
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public static Class<?> m14871a(String str) {
        if (str.equals("int")) {
            return Integer.TYPE;
        }
        if (str.equals("long")) {
            return Long.TYPE;
        }
        if (str.equals("boolean")) {
            return Boolean.TYPE;
        }
        if (str.equals("byte")) {
            return Byte.TYPE;
        }
        if (str.equals("short")) {
            return Short.TYPE;
        }
        if (str.equals("char")) {
            return Character.TYPE;
        }
        if (str.equals("float")) {
            return Float.TYPE;
        }
        if (str.equals("double")) {
            return Double.TYPE;
        }
        if (str.equals("void")) {
            return Void.TYPE;
        }
        return null;
    }

    public T call(Object... objArr) {
        try {
            return (T) this.f14698a.invoke(null, objArr);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public T callWithException(Object... objArr) throws Throwable {
        try {
            return (T) this.f14698a.invoke(null, objArr);
        } catch (InvocationTargetException e) {
            if (e.getCause() != null) {
                throw e.getCause();
            }
            throw e;
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("RefStaticMethod{");
        sb.append(this.f14699b);
        sb.append("@");
        sb.append(this.f14700c);
        sb.append(" find=");
        sb.append(this.f14698a != null);
        sb.append('}');
        return sb.toString();
    }
}
