package p110z1;

import java.lang.reflect.Type;
import java.util.Date;

/* renamed from: z1.ab */
/* loaded from: classes3.dex */
public final class C3308ab implements AbstractC3469ah, AbstractC3490ai {
    @Override // p110z1.AbstractC3490ai
    /* renamed from: a */
    public final Object mo45a(Object obj) {
        return Long.valueOf(((Date) obj).getTime());
    }

    @Override // p110z1.AbstractC3469ah
    /* renamed from: a */
    public final Object mo44a(Object obj, Type type) {
        return new Date(((Long) obj).longValue());
    }

    @Override // p110z1.AbstractC3469ah, p110z1.AbstractC3490ai
    /* renamed from: a */
    public final boolean mo46a(Class<?> cls) {
        return Date.class.isAssignableFrom(cls);
    }
}
