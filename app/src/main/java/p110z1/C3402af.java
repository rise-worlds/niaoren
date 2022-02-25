package p110z1;

import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.TreeMap;
import org.json.alipay.C3266b;

/* renamed from: z1.af */
/* loaded from: classes3.dex */
public final class C3402af implements AbstractC3469ah, AbstractC3490ai {
    @Override // p110z1.AbstractC3490ai
    /* renamed from: a */
    public final Object mo45a(Object obj) {
        TreeMap treeMap = new TreeMap();
        Class<?> cls = obj.getClass();
        while (true) {
            Field[] declaredFields = cls.getDeclaredFields();
            if (cls.equals(Object.class)) {
                return treeMap;
            }
            if (declaredFields != null && declaredFields.length > 0) {
                for (Field field : declaredFields) {
                    Object obj2 = null;
                    if (!(field == null || obj == null || "this$0".equals(field.getName()))) {
                        boolean isAccessible = field.isAccessible();
                        field.setAccessible(true);
                        Object obj3 = field.get(obj);
                        if (obj3 != null) {
                            field.setAccessible(isAccessible);
                            obj2 = C3382ae.m14204b(obj3);
                        }
                    }
                    if (obj2 != null) {
                        treeMap.put(field.getName(), obj2);
                    }
                }
            }
            cls = cls.getSuperclass();
        }
    }

    @Override // p110z1.AbstractC3469ah
    /* renamed from: a */
    public final Object mo44a(Object obj, Type type) {
        if (!obj.getClass().equals(C3266b.class)) {
            return null;
        }
        C3266b bVar = (C3266b) obj;
        Class cls = (Class) type;
        Object newInstance = cls.newInstance();
        while (!cls.equals(Object.class)) {
            Field[] declaredFields = cls.getDeclaredFields();
            if (declaredFields != null && declaredFields.length > 0) {
                for (Field field : declaredFields) {
                    String name = field.getName();
                    Type genericType = field.getGenericType();
                    if (bVar.m14754b(name)) {
                        field.setAccessible(true);
                        field.set(newInstance, C3336ad.m14325a(bVar.m14756a(name), genericType));
                    }
                }
            }
            cls = cls.getSuperclass();
        }
        return newInstance;
    }

    @Override // p110z1.AbstractC3469ah, p110z1.AbstractC3490ai
    /* renamed from: a */
    public final boolean mo46a(Class<?> cls) {
        return true;
    }
}
