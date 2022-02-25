package mirror;

import java.lang.reflect.Field;

/* renamed from: mirror.d */
/* loaded from: classes2.dex */
public class RefDouble {

    /* renamed from: a */
    private Field f14691a;

    public RefDouble(Class cls, Field field) throws NoSuchFieldException {
        this.f14691a = cls.getDeclaredField(field.getName());
        this.f14691a.setAccessible(true);
    }

    public double get(Object obj) {
        try {
            return this.f14691a.getDouble(obj);
        } catch (Exception unused) {
            return 0.0d;
        }
    }

    public void set(Object obj, double d) {
        try {
            this.f14691a.setDouble(obj, d);
        } catch (Exception unused) {
        }
    }
}
