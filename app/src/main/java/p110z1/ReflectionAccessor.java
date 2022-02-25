package p110z1;

import java.lang.reflect.AccessibleObject;

/* renamed from: z1.rb */
/* loaded from: classes3.dex */
public abstract class ReflectionAccessor {

    /* renamed from: a */
    private static final ReflectionAccessor f23056a;

    /* renamed from: a */
    public abstract void mo1223a(AccessibleObject accessibleObject);

    static {
        f23056a = JavaVersion.m1378a() < 9 ? new PreJava9ReflectionAccessor() : new UnsafeReflectionAccessor();
    }

    /* renamed from: a */
    public static ReflectionAccessor m1224a() {
        return f23056a;
    }
}
