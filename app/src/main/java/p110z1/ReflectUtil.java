package p110z1;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/* renamed from: z1.dcb */
/* loaded from: classes3.dex */
public class ReflectUtil {
    /* renamed from: a */
    public static final Object m3238a(String str, Object obj, String str2) {
        Field declaredField;
        if (obj == null) {
            return null;
        }
        try {
            Class<?> cls = Class.forName(str);
            if (cls == null || (declaredField = cls.getDeclaredField(str2)) == null) {
                return null;
            }
            declaredField.setAccessible(true);
            return declaredField.get(obj);
        } catch (Exception unused) {
            return null;
        }
    }

    /* renamed from: a */
    public static final Object m3239a(Object obj, String str) {
        if (obj == null) {
            return null;
        }
        for (Class<?> cls = obj.getClass(); cls != null; cls = cls.getSuperclass()) {
            try {
                Field declaredField = cls.getDeclaredField(str);
                if (declaredField == null) {
                    return null;
                }
                declaredField.setAccessible(true);
                return declaredField.get(obj);
            } catch (IllegalAccessException | NoSuchFieldException unused) {
            }
        }
        return null;
    }

    /* renamed from: a */
    public static final Object m3236a(String str, String str2) {
        Field field;
        try {
            Class<?> cls = Class.forName(str);
            if (cls == null || (field = cls.getField(str2)) == null) {
                return null;
            }
            return field.get("");
        } catch (Exception unused) {
            return null;
        }
    }

    /* renamed from: a */
    public static Object m3235a(String str, String str2, Object obj, Class[] clsArr, Object[] objArr) {
        Method method;
        if (obj == null) {
            return null;
        }
        try {
            Class<?> cls = Class.forName(str);
            if (cls == null || (method = cls.getMethod(str2, clsArr)) == null) {
                return null;
            }
            method.setAccessible(true);
            return method.invoke(obj, objArr);
        } catch (Exception unused) {
            return null;
        }
    }

    /* renamed from: a */
    public static Object m3240a(ClassLoader classLoader, String str, String str2, Object obj, Class[] clsArr, Object[] objArr) {
        Class<?> cls;
        Method method;
        if (obj == null) {
            return null;
        }
        try {
            cls = Class.forName(str, false, classLoader);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            cls = null;
        }
        if (cls == null) {
            return null;
        }
        try {
            method = cls.getMethod(str2, clsArr);
        } catch (NoSuchMethodException e2) {
            e2.printStackTrace();
            method = null;
        }
        if (method == null) {
            return null;
        }
        method.setAccessible(true);
        try {
            return method.invoke(obj, objArr);
        } catch (IllegalAccessException e3) {
            e3.printStackTrace();
            return null;
        } catch (InvocationTargetException e4) {
            e4.printStackTrace();
            return null;
        }
    }

    /* renamed from: a */
    public static Object m3234a(String str, String str2, Class[] clsArr, Object[] objArr) {
        Method method;
        try {
            Class<?> cls = Class.forName(str);
            if (cls == null || (method = cls.getMethod(str2, clsArr)) == null) {
                return null;
            }
            method.setAccessible(true);
            return method.invoke(null, objArr);
        } catch (Exception unused) {
            return null;
        }
    }

    /* renamed from: a */
    public static final Method m3241a(Class cls, String str, Class[] clsArr) {
        try {
            return cls.getMethod(str, clsArr);
        } catch (Exception unused) {
            return null;
        }
    }

    /* renamed from: a */
    public static final void m3237a(String str, Object obj, String str2, Object obj2) {
        Field declaredField;
        if (obj != null) {
            try {
                Class<?> cls = Class.forName(str);
                if (cls != null && (declaredField = cls.getDeclaredField(str2)) != null) {
                    declaredField.setAccessible(true);
                    declaredField.set(obj, obj2);
                }
            } catch (Exception unused) {
            }
        }
    }
}
