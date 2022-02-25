package p110z1;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collection;
import java.util.EnumSet;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ConcurrentNavigableMap;
import java.util.concurrent.ConcurrentSkipListMap;

/* renamed from: z1.py */
/* loaded from: classes3.dex */
public final class ConstructorConstructor {

    /* renamed from: a */
    private final Map<Type, InstanceCreator<?>> f22816a;

    /* renamed from: b */
    private final ReflectionAccessor f22817b = ReflectionAccessor.m1224a();

    public ConstructorConstructor(Map<Type, InstanceCreator<?>> map) {
        this.f22816a = map;
    }

    /* renamed from: a */
    public <T> ObjectConstructor<T> m1398a(TypeToken<T> rdVar) {
        final Type type = rdVar.getType();
        Class<? super T> rawType = rdVar.getRawType();
        final InstanceCreator<?> ozVar = this.f22816a.get(type);
        if (ozVar != null) {
            return new ObjectConstructor<T>() { // from class: z1.py.1
                /* JADX WARN: Type inference failed for: r0v1, types: [T, java.lang.Object] */
                @Override // p110z1.ObjectConstructor
                /* renamed from: a */
                public T mo1357a() {
                    return ozVar.m1532a(type);
                }
            };
        }
        final InstanceCreator<?> ozVar2 = this.f22816a.get(rawType);
        if (ozVar2 != null) {
            return new ObjectConstructor<T>() { // from class: z1.py.7
                /* JADX WARN: Type inference failed for: r0v1, types: [T, java.lang.Object] */
                @Override // p110z1.ObjectConstructor
                /* renamed from: a */
                public T mo1357a() {
                    return ozVar2.m1532a(type);
                }
            };
        }
        ObjectConstructor<T> a = m1400a(rawType);
        if (a != null) {
            return a;
        }
        ObjectConstructor<T> a2 = m1399a(type, rawType);
        return a2 != null ? a2 : m1397b(type, rawType);
    }

    /* renamed from: a */
    private <T> ObjectConstructor<T> m1400a(Class<? super T> cls) {
        try {
            final Constructor<? super T> declaredConstructor = cls.getDeclaredConstructor(new Class[0]);
            if (!declaredConstructor.isAccessible()) {
                this.f22817b.mo1223a(declaredConstructor);
            }
            return new ObjectConstructor<T>() { // from class: z1.py.8
                /* JADX WARN: Type inference failed for: r0v5, types: [T, java.lang.Object] */
                @Override // p110z1.ObjectConstructor
                /* renamed from: a */
                public T mo1357a() {
                    try {
                        return declaredConstructor.newInstance(null);
                    } catch (IllegalAccessException e) {
                        throw new AssertionError(e);
                    } catch (InstantiationException e2) {
                        throw new RuntimeException("Failed to invoke " + declaredConstructor + " with no args", e2);
                    } catch (InvocationTargetException e3) {
                        throw new RuntimeException("Failed to invoke " + declaredConstructor + " with no args", e3.getTargetException());
                    }
                }
            };
        } catch (NoSuchMethodException unused) {
            return null;
        }
    }

    /* renamed from: a */
    private <T> ObjectConstructor<T> m1399a(final Type type, Class<? super T> cls) {
        if (Collection.class.isAssignableFrom(cls)) {
            if (SortedSet.class.isAssignableFrom(cls)) {
                return new ObjectConstructor<T>() { // from class: z1.py.9
                    /* JADX WARN: Type inference failed for: r0v0, types: [T, java.util.TreeSet] */
                    @Override // p110z1.ObjectConstructor
                    /* renamed from: a */
                    public T mo1357a() {
                        return new TreeSet();
                    }
                };
            }
            if (EnumSet.class.isAssignableFrom(cls)) {
                return new ObjectConstructor<T>() { // from class: z1.py.10
                    /* JADX WARN: Type inference failed for: r0v7, types: [T, java.util.EnumSet] */
                    @Override // p110z1.ObjectConstructor
                    /* renamed from: a */
                    public T mo1357a() {
                        Type type2 = type;
                        if (type2 instanceof ParameterizedType) {
                            Type type3 = ((ParameterizedType) type2).getActualTypeArguments()[0];
                            if (type3 instanceof Class) {
                                return EnumSet.noneOf((Class) type3);
                            }
                            throw new JsonIOException("Invalid EnumSet type: " + type.toString());
                        }
                        throw new JsonIOException("Invalid EnumSet type: " + type.toString());
                    }
                };
            }
            if (Set.class.isAssignableFrom(cls)) {
                return new ObjectConstructor<T>() { // from class: z1.py.11
                    /* JADX WARN: Type inference failed for: r0v0, types: [T, java.util.LinkedHashSet] */
                    @Override // p110z1.ObjectConstructor
                    /* renamed from: a */
                    public T mo1357a() {
                        return new LinkedHashSet();
                    }
                };
            }
            if (Queue.class.isAssignableFrom(cls)) {
                return new ObjectConstructor<T>() { // from class: z1.py.12
                    /* JADX WARN: Type inference failed for: r0v0, types: [T, java.util.ArrayDeque] */
                    @Override // p110z1.ObjectConstructor
                    /* renamed from: a */
                    public T mo1357a() {
                        return new ArrayDeque();
                    }
                };
            }
            return new ObjectConstructor<T>() { // from class: z1.py.13
                /* JADX WARN: Type inference failed for: r0v0, types: [T, java.util.ArrayList] */
                @Override // p110z1.ObjectConstructor
                /* renamed from: a */
                public T mo1357a() {
                    return new ArrayList();
                }
            };
        } else if (!Map.class.isAssignableFrom(cls)) {
            return null;
        } else {
            if (ConcurrentNavigableMap.class.isAssignableFrom(cls)) {
                return new ObjectConstructor<T>() { // from class: z1.py.14
                    /* JADX WARN: Type inference failed for: r0v0, types: [java.util.concurrent.ConcurrentSkipListMap, T] */
                    @Override // p110z1.ObjectConstructor
                    /* renamed from: a */
                    public T mo1357a() {
                        return new ConcurrentSkipListMap();
                    }
                };
            }
            if (ConcurrentMap.class.isAssignableFrom(cls)) {
                return new ObjectConstructor<T>() { // from class: z1.py.2
                    /* JADX WARN: Type inference failed for: r0v0, types: [T, java.util.concurrent.ConcurrentHashMap] */
                    @Override // p110z1.ObjectConstructor
                    /* renamed from: a */
                    public T mo1357a() {
                        return new ConcurrentHashMap();
                    }
                };
            }
            if (SortedMap.class.isAssignableFrom(cls)) {
                return new ObjectConstructor<T>() { // from class: z1.py.3
                    /* JADX WARN: Type inference failed for: r0v0, types: [java.util.TreeMap, T] */
                    @Override // p110z1.ObjectConstructor
                    /* renamed from: a */
                    public T mo1357a() {
                        return new TreeMap();
                    }
                };
            }
            if (!(type instanceof ParameterizedType) || String.class.isAssignableFrom(TypeToken.get(((ParameterizedType) type).getActualTypeArguments()[0]).getRawType())) {
                return new ObjectConstructor<T>() { // from class: z1.py.5
                    /* JADX WARN: Type inference failed for: r0v0, types: [z1.qf, T] */
                    @Override // p110z1.ObjectConstructor
                    /* renamed from: a */
                    public T mo1357a() {
                        return new LinkedTreeMap();
                    }
                };
            }
            return new ObjectConstructor<T>() { // from class: z1.py.4
                /* JADX WARN: Type inference failed for: r0v0, types: [java.util.LinkedHashMap, T] */
                @Override // p110z1.ObjectConstructor
                /* renamed from: a */
                public T mo1357a() {
                    return new LinkedHashMap();
                }
            };
        }
    }

    /* renamed from: b */
    private <T> ObjectConstructor<T> m1397b(final Type type, final Class<? super T> cls) {
        return new ObjectConstructor<T>() { // from class: z1.py.6

            /* renamed from: d */
            private final UnsafeAllocator f22834d = UnsafeAllocator.m1343a();

            /* JADX WARN: Type inference failed for: r0v2, types: [T, java.lang.Object] */
            @Override // p110z1.ObjectConstructor
            /* renamed from: a */
            public T mo1357a() {
                try {
                    return this.f22834d.mo1341a(cls);
                } catch (Exception e) {
                    throw new RuntimeException("Unable to invoke no-args constructor for " + type + ". Registering an InstanceCreator with Gson for this type may fix this problem.", e);
                }
            }
        };
    }

    public String toString() {
        return this.f22816a.toString();
    }
}
