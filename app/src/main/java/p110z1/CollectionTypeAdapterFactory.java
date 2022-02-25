package p110z1;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Collection;

/* renamed from: z1.qm */
/* loaded from: classes3.dex */
public final class CollectionTypeAdapterFactory implements TypeAdapterFactory {

    /* renamed from: a */
    private final ConstructorConstructor f22909a;

    public CollectionTypeAdapterFactory(ConstructorConstructor pyVar) {
        this.f22909a = pyVar;
    }

    @Override // p110z1.TypeAdapterFactory
    /* renamed from: a */
    public <T> TypeAdapter<T> mo1264a(Gson oxVar, TypeToken<T> rdVar) {
        Type type = rdVar.getType();
        Class<? super T> rawType = rdVar.getRawType();
        if (!Collection.class.isAssignableFrom(rawType)) {
            return null;
        }
        Type a = C$Gson$Types.m1418a(type, (Class<?>) rawType);
        return new C5484a(oxVar, a, oxVar.m1579a((TypeToken) TypeToken.get(a)), this.f22909a.m1398a(rdVar));
    }

    /* compiled from: CollectionTypeAdapterFactory.java */
    /* renamed from: z1.qm$a */
    /* loaded from: classes3.dex */
    private static final class C5484a<E> extends TypeAdapter<Collection<E>> {

        /* renamed from: a */
        private final TypeAdapter<E> f22910a;

        /* renamed from: b */
        private final ObjectConstructor<? extends Collection<E>> f22911b;

        @Override // p110z1.TypeAdapter
        /* renamed from: a */
        public /* bridge */ /* synthetic */ void mo1235a(JsonWriter rhVar, Object obj) throws IOException {
            m1339a(rhVar, (Collection) ((Collection) obj));
        }

        public C5484a(Gson oxVar, Type type, TypeAdapter<E> ppVar, ObjectConstructor<? extends Collection<E>> qgVar) {
            this.f22910a = new TypeAdapterRuntimeTypeWrapper(oxVar, ppVar, type);
            this.f22911b = qgVar;
        }

        /* renamed from: a */
        public Collection<E> mo1234b(JsonReader reVar) throws IOException {
            if (reVar.mo1205f() == JsonToken.NULL) {
                reVar.mo1201j();
                return null;
            }
            Collection<E> collection = (Collection) this.f22911b.mo1357a();
            reVar.mo1219a();
            while (reVar.mo1206e()) {
                collection.add(this.f22910a.mo1234b(reVar));
            }
            reVar.mo1214b();
            return collection;
        }

        /* renamed from: a */
        public void m1339a(JsonWriter rhVar, Collection<E> collection) throws IOException {
            if (collection == null) {
                rhVar.mo1161f();
                return;
            }
            rhVar.mo1173b();
            for (E e : collection) {
                this.f22910a.mo1235a(rhVar, (JsonWriter) e);
            }
            rhVar.mo1169c();
        }
    }
}
