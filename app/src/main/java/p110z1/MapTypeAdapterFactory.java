package p110z1;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Map;

/* renamed from: z1.qr */
/* loaded from: classes3.dex */
public final class MapTypeAdapterFactory implements TypeAdapterFactory {

    /* renamed from: a */
    final boolean f22926a;

    /* renamed from: b */
    private final ConstructorConstructor f22927b;

    public MapTypeAdapterFactory(ConstructorConstructor pyVar, boolean z) {
        this.f22927b = pyVar;
        this.f22926a = z;
    }

    @Override // p110z1.TypeAdapterFactory
    /* renamed from: a */
    public <T> TypeAdapter<T> mo1264a(Gson oxVar, TypeToken<T> rdVar) {
        Type type = rdVar.getType();
        if (!Map.class.isAssignableFrom(rdVar.getRawType())) {
            return null;
        }
        Type[] b = C$Gson$Types.m1408b(type, C$Gson$Types.m1404e(type));
        return new C5488a(oxVar, b[0], m1325a(oxVar, b[0]), b[1], oxVar.m1579a((TypeToken) TypeToken.get(b[1])), this.f22927b.m1398a(rdVar));
    }

    /* renamed from: a */
    private TypeAdapter<?> m1325a(Gson oxVar, Type type) {
        if (type == Boolean.TYPE || type == Boolean.class) {
            return TypeAdapters.f23003f;
        }
        return oxVar.m1579a((TypeToken) TypeToken.get(type));
    }

    /* compiled from: MapTypeAdapterFactory.java */
    /* renamed from: z1.qr$a */
    /* loaded from: classes3.dex */
    private final class C5488a<K, V> extends TypeAdapter<Map<K, V>> {

        /* renamed from: b */
        private final TypeAdapter<K> f22929b;

        /* renamed from: c */
        private final TypeAdapter<V> f22930c;

        /* renamed from: d */
        private final ObjectConstructor<? extends Map<K, V>> f22931d;

        @Override // p110z1.TypeAdapter
        /* renamed from: a */
        public /* bridge */ /* synthetic */ void mo1235a(JsonWriter rhVar, Object obj) throws IOException {
            m1323a(rhVar, (Map) ((Map) obj));
        }

        public C5488a(Gson oxVar, Type type, TypeAdapter<K> ppVar, Type type2, TypeAdapter<V> ppVar2, ObjectConstructor<? extends Map<K, V>> qgVar) {
            this.f22929b = new TypeAdapterRuntimeTypeWrapper(oxVar, ppVar, type);
            this.f22930c = new TypeAdapterRuntimeTypeWrapper(oxVar, ppVar2, type2);
            this.f22931d = qgVar;
        }

        /* renamed from: a */
        public Map<K, V> mo1234b(JsonReader reVar) throws IOException {
            JsonToken f = reVar.mo1205f();
            if (f == JsonToken.NULL) {
                reVar.mo1201j();
                return null;
            }
            Map<K, V> map = (Map) this.f22931d.mo1357a();
            if (f == JsonToken.BEGIN_ARRAY) {
                reVar.mo1219a();
                while (reVar.mo1206e()) {
                    reVar.mo1219a();
                    K b = this.f22929b.mo1234b(reVar);
                    if (map.put(b, this.f22930c.mo1234b(reVar)) == null) {
                        reVar.mo1214b();
                    } else {
                        throw new JsonSyntaxException("duplicate key: " + b);
                    }
                }
                reVar.mo1214b();
            } else {
                reVar.mo1209c();
                while (reVar.mo1206e()) {
                    JsonReaderInternalAccess.f22857a.mo1184a(reVar);
                    K b2 = this.f22929b.mo1234b(reVar);
                    if (map.put(b2, this.f22930c.mo1234b(reVar)) != null) {
                        throw new JsonSyntaxException("duplicate key: " + b2);
                    }
                }
                reVar.mo1207d();
            }
            return map;
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* renamed from: a */
        public void m1323a(JsonWriter rhVar, Map<K, V> map) throws IOException {
            if (map == null) {
                rhVar.mo1161f();
            } else if (!MapTypeAdapterFactory.this.f22926a) {
                rhVar.mo1166d();
                for (Map.Entry<K, V> entry : map.entrySet()) {
                    rhVar.mo1175a(String.valueOf(entry.getKey()));
                    this.f22930c.mo1235a(rhVar, (JsonWriter) entry.getValue());
                }
                rhVar.mo1163e();
            } else {
                ArrayList arrayList = new ArrayList(map.size());
                ArrayList arrayList2 = new ArrayList(map.size());
                int i = 0;
                boolean z = false;
                for (Map.Entry<K, V> entry2 : map.entrySet()) {
                    JsonElement b = this.f22929b.m1432b((TypeAdapter<K>) entry2.getKey());
                    arrayList.add(b);
                    arrayList2.add(entry2.getValue());
                    z |= b.m1489p() || b.m1488q();
                }
                if (z) {
                    rhVar.mo1173b();
                    int size = arrayList.size();
                    while (i < size) {
                        rhVar.mo1173b();
                        Streams.m1345a((JsonElement) arrayList.get(i), rhVar);
                        this.f22930c.mo1235a(rhVar, (JsonWriter) arrayList2.get(i));
                        rhVar.mo1169c();
                        i++;
                    }
                    rhVar.mo1169c();
                    return;
                }
                rhVar.mo1166d();
                int size2 = arrayList.size();
                while (i < size2) {
                    rhVar.mo1175a(m1322b((JsonElement) arrayList.get(i)));
                    this.f22930c.mo1235a(rhVar, (JsonWriter) arrayList2.get(i));
                    i++;
                }
                rhVar.mo1163e();
            }
        }

        /* renamed from: b */
        private String m1322b(JsonElement pdVar) {
            if (pdVar.m1487r()) {
                JsonPrimitive v = pdVar.m1483v();
                if (v.m1442y()) {
                    return String.valueOf(v.mo1456c());
                }
                if (v.m1458b()) {
                    return Boolean.toString(v.mo1445n());
                }
                if (v.m1441z()) {
                    return v.mo1455d();
                }
                throw new AssertionError();
            } else if (pdVar.m1486s()) {
                return "null";
            } else {
                throw new AssertionError();
            }
        }
    }
}
