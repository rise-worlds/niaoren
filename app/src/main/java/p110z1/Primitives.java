package p110z1;

import java.lang.reflect.Type;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/* renamed from: z1.qi */
/* loaded from: classes3.dex */
public final class Primitives {

    /* renamed from: a */
    private static final Map<Class<?>, Class<?>> f22896a;

    /* renamed from: b */
    private static final Map<Class<?>, Class<?>> f22897b;

    private Primitives() {
        throw new UnsupportedOperationException();
    }

    static {
        HashMap hashMap = new HashMap(16);
        HashMap hashMap2 = new HashMap(16);
        m1349a(hashMap, hashMap2, Boolean.TYPE, Boolean.class);
        m1349a(hashMap, hashMap2, Byte.TYPE, Byte.class);
        m1349a(hashMap, hashMap2, Character.TYPE, Character.class);
        m1349a(hashMap, hashMap2, Double.TYPE, Double.class);
        m1349a(hashMap, hashMap2, Float.TYPE, Float.class);
        m1349a(hashMap, hashMap2, Integer.TYPE, Integer.class);
        m1349a(hashMap, hashMap2, Long.TYPE, Long.class);
        m1349a(hashMap, hashMap2, Short.TYPE, Short.class);
        m1349a(hashMap, hashMap2, Void.TYPE, Void.class);
        f22896a = Collections.unmodifiableMap(hashMap);
        f22897b = Collections.unmodifiableMap(hashMap2);
    }

    /* renamed from: a */
    private static void m1349a(Map<Class<?>, Class<?>> map, Map<Class<?>, Class<?>> map2, Class<?> cls, Class<?> cls2) {
        map.put(cls, cls2);
        map2.put(cls2, cls);
    }

    /* renamed from: a */
    public static boolean m1350a(Type type) {
        return f22896a.containsKey(type);
    }

    /* renamed from: b */
    public static boolean m1347b(Type type) {
        return f22897b.containsKey(C$Gson$Preconditions.m1423a(type));
    }

    /* renamed from: a */
    public static <T> Class<T> m1351a(Class<T> cls) {
        Class<T> cls2 = (Class<T>) f22896a.get(C$Gson$Preconditions.m1423a(cls));
        return cls2 == null ? cls : cls2;
    }

    /* renamed from: b */
    public static <T> Class<T> m1348b(Class<T> cls) {
        Class<T> cls2 = (Class<T>) f22897b.get(C$Gson$Preconditions.m1423a(cls));
        return cls2 == null ? cls : cls2;
    }
}
