package p110z1;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.Collection;

/* renamed from: z1.ou */
/* loaded from: classes3.dex */
public final class FieldAttributes {

    /* renamed from: a */
    private final Field f22731a;

    public FieldAttributes(Field field) {
        C$Gson$Preconditions.m1423a(field);
        this.f22731a = field;
    }

    /* renamed from: a */
    public Class<?> m1611a() {
        return this.f22731a.getDeclaringClass();
    }

    /* renamed from: b */
    public String m1607b() {
        return this.f22731a.getName();
    }

    /* renamed from: c */
    public Type m1606c() {
        return this.f22731a.getGenericType();
    }

    /* renamed from: d */
    public Class<?> m1605d() {
        return this.f22731a.getType();
    }

    /* renamed from: a */
    public <T extends Annotation> T m1609a(Class<T> cls) {
        return (T) this.f22731a.getAnnotation(cls);
    }

    /* renamed from: e */
    public Collection<Annotation> m1604e() {
        return Arrays.asList(this.f22731a.getAnnotations());
    }

    /* renamed from: a */
    public boolean m1610a(int i) {
        return (i & this.f22731a.getModifiers()) != 0;
    }

    /* renamed from: a */
    Object m1608a(Object obj) throws IllegalAccessException {
        return this.f22731a.get(obj);
    }

    /* renamed from: f */
    boolean m1603f() {
        return this.f22731a.isSynthetic();
    }
}
