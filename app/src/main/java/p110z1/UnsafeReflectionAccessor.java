package p110z1;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Field;

/* renamed from: z1.rc */
/* loaded from: classes3.dex */
final class UnsafeReflectionAccessor extends ReflectionAccessor {

    /* renamed from: a */
    private static Class f23057a;

    /* renamed from: b */
    private final Object f23058b = m1222b();

    /* renamed from: c */
    private final Field f23059c = m1220c();

    @Override // p110z1.ReflectionAccessor
    /* renamed from: a */
    public void mo1223a(AccessibleObject accessibleObject) {
        if (!m1221b(accessibleObject)) {
            try {
                accessibleObject.setAccessible(true);
            } catch (SecurityException e) {
                throw new JsonIOException("Gson couldn't modify fields for " + accessibleObject + "\nand sun.misc.Unsafe not found.\nEither write a custom type adapter, or make fields accessible, or include sun.misc.Unsafe.", e);
            }
        }
    }

    /* renamed from: b */
    boolean m1221b(AccessibleObject accessibleObject) {
        if (!(this.f23058b == null || this.f23059c == null)) {
            try {
                f23057a.getMethod("putBoolean", Object.class, Long.TYPE, Boolean.TYPE).invoke(this.f23058b, accessibleObject, Long.valueOf(((Long) f23057a.getMethod("objectFieldOffset", Field.class).invoke(this.f23058b, this.f23059c)).longValue()), true);
                return true;
            } catch (Exception unused) {
            }
        }
        return false;
    }

    /* renamed from: b */
    private static Object m1222b() {
        try {
            f23057a = Class.forName("sun.misc.Unsafe");
            Field declaredField = f23057a.getDeclaredField("theUnsafe");
            declaredField.setAccessible(true);
            return declaredField.get(null);
        } catch (Exception unused) {
            return null;
        }
    }

    /* renamed from: c */
    private static Field m1220c() {
        try {
            return AccessibleObject.class.getDeclaredField("override");
        } catch (NoSuchFieldException unused) {
            return null;
        }
    }
}
