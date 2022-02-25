package mirror;

import java.lang.reflect.Field;

/* renamed from: mirror.f */
/* loaded from: classes2.dex */
public class RefInt {

    /* renamed from: a */
    private Field f14693a;

    public RefInt(Class cls, Field field) throws NoSuchFieldException {
        this.f14693a = cls.getDeclaredField(field.getName());
        this.f14693a.setAccessible(true);
    }

    public int get(Object obj) {
        try {
            return this.f14693a.getInt(obj);
        } catch (Exception unused) {
            return 0;
        }
    }

    public void set(Object obj, int i) {
        try {
            this.f14693a.setInt(obj, i);
        } catch (Exception unused) {
        }
    }
}
