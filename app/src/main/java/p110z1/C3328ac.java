package p110z1;

import java.lang.reflect.Type;

/* renamed from: z1.ac */
/* loaded from: classes3.dex */
public final class C3328ac implements AbstractC3469ah, AbstractC3490ai {
    @Override // p110z1.AbstractC3490ai
    /* renamed from: a */
    public final Object mo45a(Object obj) {
        return ((Enum) obj).name();
    }

    @Override // p110z1.AbstractC3469ah
    /* renamed from: a */
    public final Object mo44a(Object obj, Type type) {
        return Enum.valueOf((Class) type, obj.toString());
    }

    @Override // p110z1.AbstractC3469ah, p110z1.AbstractC3490ai
    /* renamed from: a */
    public final boolean mo46a(Class<?> cls) {
        return Enum.class.isAssignableFrom(cls);
    }
}
