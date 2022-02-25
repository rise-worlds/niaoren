package p110z1;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/* renamed from: z1.al */
/* loaded from: classes3.dex */
public final class C3639al {
    /* renamed from: a */
    public static Class<?> m12656a(Type type) {
        while (!(type instanceof Class)) {
            if (type instanceof ParameterizedType) {
                type = ((ParameterizedType) type).getRawType();
            } else {
                throw new IllegalArgumentException("TODO");
            }
        }
        return (Class) type;
    }

    /* renamed from: a */
    public static boolean m12657a(Class<?> cls) {
        return cls.isPrimitive() || cls.equals(String.class) || cls.equals(Integer.class) || cls.equals(Long.class) || cls.equals(Double.class) || cls.equals(Float.class) || cls.equals(Boolean.class) || cls.equals(Short.class) || cls.equals(Character.class) || cls.equals(Byte.class) || cls.equals(Void.class);
    }
}
