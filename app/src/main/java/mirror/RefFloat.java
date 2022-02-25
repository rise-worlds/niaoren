package mirror;

import java.lang.reflect.Field;

/* renamed from: mirror.e */
/* loaded from: classes2.dex */
public class RefFloat {

    /* renamed from: a */
    private Field f14692a;

    public RefFloat(Class cls, Field field) throws NoSuchFieldException {
        this.f14692a = cls.getDeclaredField(field.getName());
        this.f14692a.setAccessible(true);
    }

    public float get(Object obj) {
        try {
            return this.f14692a.getFloat(obj);
        } catch (Exception unused) {
            return 0.0f;
        }
    }

    public void set(Object obj, float f) {
        try {
            this.f14692a.setFloat(obj, f);
        } catch (Exception unused) {
        }
    }
}
