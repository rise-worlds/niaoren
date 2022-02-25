package p110z1;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.IdentityHashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Properties;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import org.json.alipay.C3266b;

/* renamed from: z1.ag */
/* loaded from: classes3.dex */
public final class C3435ag implements AbstractC3469ah, AbstractC3490ai {
    /* renamed from: a */
    private static Map<Object, Object> m13546a(Type type) {
        while (type != Properties.class) {
            if (type == Hashtable.class) {
                return new Hashtable();
            }
            if (type == IdentityHashMap.class) {
                return new IdentityHashMap();
            }
            if (type == SortedMap.class || type == TreeMap.class) {
                return new TreeMap();
            }
            if (type == ConcurrentMap.class || type == ConcurrentHashMap.class) {
                return new ConcurrentHashMap();
            }
            if (type == Map.class || type == HashMap.class) {
                return new HashMap();
            }
            if (type == LinkedHashMap.class) {
                return new LinkedHashMap();
            }
            if (type instanceof ParameterizedType) {
                type = ((ParameterizedType) type).getRawType();
            } else {
                Class cls = (Class) type;
                if (!cls.isInterface()) {
                    try {
                        return (Map) cls.newInstance();
                    } catch (Exception e) {
                        throw new IllegalArgumentException("unsupport type " + type, e);
                    }
                } else {
                    throw new IllegalArgumentException("unsupport type " + type);
                }
            }
        }
        return new Properties();
    }

    @Override // p110z1.AbstractC3490ai
    /* renamed from: a */
    public final Object mo45a(Object obj) {
        TreeMap treeMap = new TreeMap();
        for (Map.Entry entry : ((Map) obj).entrySet()) {
            if (entry.getKey() instanceof String) {
                treeMap.put((String) entry.getKey(), C3382ae.m14204b(entry.getValue()));
            } else {
                throw new IllegalArgumentException("Map key must be String!");
            }
        }
        return treeMap;
    }

    @Override // p110z1.AbstractC3469ah
    /* renamed from: a */
    public final Object mo44a(Object obj, Type type) {
        if (!obj.getClass().equals(C3266b.class)) {
            return null;
        }
        C3266b bVar = (C3266b) obj;
        Map<Object, Object> a = m13546a(type);
        if (type instanceof ParameterizedType) {
            ParameterizedType parameterizedType = (ParameterizedType) type;
            Type type2 = parameterizedType.getActualTypeArguments()[0];
            Type type3 = parameterizedType.getActualTypeArguments()[1];
            if (String.class == type2) {
                Iterator a2 = bVar.m14758a();
                while (a2.hasNext()) {
                    String str = (String) a2.next();
                    a.put(str, C3639al.m12657a((Class<?>) ((Class) type3)) ? bVar.m14756a(str) : C3336ad.m14325a(bVar.m14756a(str), type3));
                }
                return a;
            }
            throw new IllegalArgumentException("Deserialize Map Key must be String.class");
        }
        throw new IllegalArgumentException("Deserialize Map must be Generics!");
    }

    @Override // p110z1.AbstractC3469ah, p110z1.AbstractC3490ai
    /* renamed from: a */
    public final boolean mo46a(Class<?> cls) {
        return Map.class.isAssignableFrom(cls);
    }
}
