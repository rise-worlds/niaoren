package mirror;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.HashMap;

/* renamed from: mirror.b */
/* loaded from: classes2.dex */
public final class RefClass {

    /* renamed from: a */
    private static HashMap<Class<?>, Constructor<?>> f14689a = new HashMap<>();

    static {
        try {
            f14689a.put(RefObject.class, RefObject.class.getConstructor(Class.class, Field.class));
            f14689a.put(RefMethod.class, RefMethod.class.getConstructor(Class.class, Field.class));
            f14689a.put(RefInt.class, RefInt.class.getConstructor(Class.class, Field.class));
            f14689a.put(RefLong.class, RefLong.class.getConstructor(Class.class, Field.class));
            f14689a.put(RefFloat.class, RefFloat.class.getConstructor(Class.class, Field.class));
            f14689a.put(RefDouble.class, RefDouble.class.getConstructor(Class.class, Field.class));
            f14689a.put(RefBoolean.class, RefBoolean.class.getConstructor(Class.class, Field.class));
            f14689a.put(RefStaticObject.class, RefStaticObject.class.getConstructor(Class.class, Field.class));
            f14689a.put(RefStaticInt.class, RefStaticInt.class.getConstructor(Class.class, Field.class));
            f14689a.put(RefStaticMethod.class, RefStaticMethod.class.getConstructor(Class.class, Field.class));
            f14689a.put(RefConstructor.class, RefConstructor.class.getConstructor(Class.class, Field.class));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Class<?> load(Class<?> cls, String str) {
        try {
            return load(cls, Class.forName(str));
        } catch (Exception unused) {
            return null;
        }
    }

    public static Class load(Class cls, Class<?> cls2) {
        Field[] declaredFields;
        Constructor<?> constructor;
        for (Field field : cls.getDeclaredFields()) {
            try {
                if (Modifier.isStatic(field.getModifiers()) && (constructor = f14689a.get(field.getType())) != null) {
                    field.set(null, constructor.newInstance(cls2, field));
                }
            } catch (Exception unused) {
            }
        }
        return cls2;
    }
}
