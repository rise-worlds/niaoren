package p110z1;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.HashSet;
import java.util.Set;
import org.json.alipay.C3265a;

/* renamed from: z1.aj */
/* loaded from: classes3.dex */
public final class C3521aj implements AbstractC3469ah {
    @Override // p110z1.AbstractC3469ah
    /* renamed from: a */
    public final Object mo44a(Object obj, Type type) {
        if (!obj.getClass().equals(C3265a.class)) {
            return null;
        }
        C3265a aVar = (C3265a) obj;
        HashSet hashSet = new HashSet();
        Class cls = type instanceof ParameterizedType ? ((ParameterizedType) type).getActualTypeArguments()[0] : Object.class;
        for (int i = 0; i < aVar.m14761a(); i++) {
            hashSet.add(C3336ad.m14325a(aVar.m14760a(i), cls));
        }
        return hashSet;
    }

    @Override // p110z1.AbstractC3469ah, p110z1.AbstractC3490ai
    /* renamed from: a */
    public final boolean mo46a(Class<?> cls) {
        return Set.class.isAssignableFrom(cls);
    }
}
