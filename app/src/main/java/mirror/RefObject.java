package mirror;

import java.lang.reflect.Field;

/* renamed from: mirror.i */
/* loaded from: classes2.dex */
public class RefObject<T> {

    /* renamed from: a */
    private Field f14696a;

    public RefObject(Class<?> cls, Field field) throws NoSuchFieldException {
        this.f14696a = cls.getDeclaredField(field.getName());
        this.f14696a.setAccessible(true);
    }

    public T get(Object obj) {
        try {
            return (T) this.f14696a.get(obj);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean set(Object obj, T t) {
        try {
            this.f14696a.set(obj, t);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
