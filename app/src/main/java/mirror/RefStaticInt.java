package mirror;

import java.lang.reflect.Field;

/* renamed from: mirror.j */
/* loaded from: classes2.dex */
public class RefStaticInt {

    /* renamed from: a */
    private Field f14697a;

    public RefStaticInt(Class<?> cls, Field field) throws NoSuchFieldException {
        this.f14697a = cls.getDeclaredField(field.getName());
        this.f14697a.setAccessible(true);
    }

    public int get() {
        try {
            return this.f14697a.getInt(null);
        } catch (Exception unused) {
            return 0;
        }
    }

    public void set(int i) {
        try {
            this.f14697a.setInt(null, i);
        } catch (Exception unused) {
        }
    }
}
