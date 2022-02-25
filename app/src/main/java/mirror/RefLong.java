package mirror;

import java.lang.reflect.Field;

/* renamed from: mirror.g */
/* loaded from: classes2.dex */
public class RefLong {

    /* renamed from: a */
    private Field f14694a;

    public RefLong(Class cls, Field field) throws NoSuchFieldException {
        this.f14694a = cls.getDeclaredField(field.getName());
        this.f14694a.setAccessible(true);
    }

    public long get(Object obj) {
        try {
            return this.f14694a.getLong(obj);
        } catch (Exception unused) {
            return 0L;
        }
    }

    public void set(Object obj, long j) {
        try {
            this.f14694a.setLong(obj, j);
        } catch (Exception unused) {
        }
    }
}
