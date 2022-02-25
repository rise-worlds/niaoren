package mirror;

import java.lang.reflect.Field;

/* renamed from: mirror.a */
/* loaded from: classes2.dex */
public class RefBoolean {

    /* renamed from: a */
    private Field f14688a;

    public RefBoolean(Class<?> cls, Field field) throws NoSuchFieldException {
        this.f14688a = cls.getDeclaredField(field.getName());
        this.f14688a.setAccessible(true);
    }

    public boolean get(Object obj) {
        try {
            return this.f14688a.getBoolean(obj);
        } catch (Exception unused) {
            return false;
        }
    }

    public void set(Object obj, boolean z) {
        try {
            this.f14688a.setBoolean(obj, z);
        } catch (Exception unused) {
        }
    }
}
