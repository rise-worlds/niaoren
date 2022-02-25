package p110z1;

import java.io.IOException;
import java.lang.reflect.Array;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.Type;
import java.util.ArrayList;

/* renamed from: z1.ql */
/* loaded from: classes3.dex */
public final class ArrayTypeAdapter<E> extends TypeAdapter<Object> {

    /* renamed from: a */
    public static final TypeAdapterFactory f22906a = new TypeAdapterFactory() { // from class: z1.ql.1
        @Override // p110z1.TypeAdapterFactory
        /* renamed from: a */
        public <T> TypeAdapter<T> mo1264a(Gson oxVar, TypeToken<T> rdVar) {
            Type type = rdVar.getType();
            if (!(type instanceof GenericArrayType) && (!(type instanceof Class) || !((Class) type).isArray())) {
                return null;
            }
            Type g = C$Gson$Types.m1402g(type);
            return new ArrayTypeAdapter(oxVar, oxVar.m1579a((TypeToken) TypeToken.get(g)), C$Gson$Types.m1404e(g));
        }
    };

    /* renamed from: b */
    private final Class<E> f22907b;

    /* renamed from: c */
    private final TypeAdapter<E> f22908c;

    public ArrayTypeAdapter(Gson oxVar, TypeAdapter<E> ppVar, Class<E> cls) {
        this.f22908c = new TypeAdapterRuntimeTypeWrapper(oxVar, ppVar, cls);
        this.f22907b = cls;
    }

    @Override // p110z1.TypeAdapter
    /* renamed from: b */
    public Object mo1234b(JsonReader reVar) throws IOException {
        if (reVar.mo1205f() == JsonToken.NULL) {
            reVar.mo1201j();
            return null;
        }
        ArrayList arrayList = new ArrayList();
        reVar.mo1219a();
        while (reVar.mo1206e()) {
            arrayList.add(this.f22908c.mo1234b(reVar));
        }
        reVar.mo1214b();
        int size = arrayList.size();
        Object newInstance = Array.newInstance((Class<?>) this.f22907b, size);
        for (int i = 0; i < size; i++) {
            Array.set(newInstance, i, arrayList.get(i));
        }
        return newInstance;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // p110z1.TypeAdapter
    /* renamed from: a */
    public void mo1235a(JsonWriter rhVar, Object obj) throws IOException {
        if (obj == null) {
            rhVar.mo1161f();
            return;
        }
        rhVar.mo1173b();
        int length = Array.getLength(obj);
        for (int i = 0; i < length; i++) {
            this.f22908c.mo1235a(rhVar, (JsonWriter) Array.get(obj, i));
        }
        rhVar.mo1169c();
    }
}
