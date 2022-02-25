package p110z1;

import java.lang.reflect.Array;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import org.json.alipay.C3265a;

/* renamed from: z1.z */
/* loaded from: classes3.dex */
public final class C5617z implements AbstractC3469ah, AbstractC3490ai {
    @Override // p110z1.AbstractC3490ai
    /* renamed from: a */
    public final Object mo45a(Object obj) {
        ArrayList arrayList = new ArrayList();
        for (Object obj2 : (Object[]) obj) {
            arrayList.add(C3382ae.m14204b(obj2));
        }
        return arrayList;
    }

    @Override // p110z1.AbstractC3469ah
    /* renamed from: a */
    public final Object mo44a(Object obj, Type type) {
        if (!obj.getClass().equals(C3265a.class)) {
            return null;
        }
        C3265a aVar = (C3265a) obj;
        if (!(type instanceof GenericArrayType)) {
            Class<?> componentType = ((Class) type).getComponentType();
            int a = aVar.m14761a();
            Object newInstance = Array.newInstance(componentType, a);
            for (int i = 0; i < a; i++) {
                Array.set(newInstance, i, C3336ad.m14325a(aVar.m14760a(i), componentType));
            }
            return newInstance;
        }
        throw new IllegalArgumentException("Does not support generic array!");
    }

    @Override // p110z1.AbstractC3469ah, p110z1.AbstractC3490ai
    /* renamed from: a */
    public final boolean mo46a(Class<?> cls) {
        return cls.isArray();
    }
}
