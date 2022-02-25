package mirror;

import java.lang.reflect.Field;

/* renamed from: mirror.l */
/* loaded from: classes2.dex */
public class RefStaticObject<T> {

    /* renamed from: a */
    private Field f14701a;

    public RefStaticObject(Class<?> cls, Field field) throws NoSuchFieldException {
        this.f14701a = cls.getDeclaredField(field.getName());
        this.f14701a.setAccessible(true);
    }

    public Class<?> type() {
        return this.f14701a.getType();
    }

    public T get() {
        try {
            return (T) this.f14701a.get(null);
        } catch (Exception unused) {
            return null;
        }
    }

    public void set(T t) {
        try {
            this.f14701a.set(null, t);
        } catch (Exception unused) {
        }
    }
}
