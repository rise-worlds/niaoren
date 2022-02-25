package p110z1;

import java.io.EOFException;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicLongArray;

/* renamed from: z1.ox */
/* loaded from: classes3.dex */
public final class Gson {

    /* renamed from: a */
    static final boolean f22732a = false;

    /* renamed from: b */
    static final boolean f22733b = false;

    /* renamed from: c */
    static final boolean f22734c = false;

    /* renamed from: d */
    static final boolean f22735d = true;

    /* renamed from: e */
    static final boolean f22736e = false;

    /* renamed from: f */
    static final boolean f22737f = false;

    /* renamed from: g */
    static final boolean f22738g = false;

    /* renamed from: y */
    private static final TypeToken<?> f22739y = TypeToken.get(Object.class);

    /* renamed from: z */
    private static final String f22740z = ")]}'\n";

    /* renamed from: A */
    private final ThreadLocal<Map<TypeToken<?>, C5435a<?>>> f22741A;

    /* renamed from: B */
    private final Map<TypeToken<?>, TypeAdapter<?>> f22742B;

    /* renamed from: C */
    private final ConstructorConstructor f22743C;

    /* renamed from: D */
    private final JsonAdapterAnnotationTypeAdapterFactory f22744D;

    /* renamed from: h */
    final List<TypeAdapterFactory> f22745h;

    /* renamed from: i */
    final Excluder f22746i;

    /* renamed from: j */
    final FieldNamingStrategy f22747j;

    /* renamed from: k */
    final Map<Type, InstanceCreator<?>> f22748k;

    /* renamed from: l */
    final boolean f22749l;

    /* renamed from: m */
    final boolean f22750m;

    /* renamed from: n */
    final boolean f22751n;

    /* renamed from: o */
    final boolean f22752o;

    /* renamed from: p */
    final boolean f22753p;

    /* renamed from: q */
    final boolean f22754q;

    /* renamed from: r */
    final boolean f22755r;

    /* renamed from: s */
    final String f22756s;

    /* renamed from: t */
    final int f22757t;

    /* renamed from: u */
    final int f22758u;

    /* renamed from: v */
    final LongSerializationPolicy f22759v;

    /* renamed from: w */
    final List<TypeAdapterFactory> f22760w;

    /* renamed from: x */
    final List<TypeAdapterFactory> f22761x;

    public Gson() {
        this(Excluder.f22841a, FieldNamingPolicy.IDENTITY, Collections.emptyMap(), false, false, false, true, false, false, false, LongSerializationPolicy.DEFAULT, null, 2, 2, Collections.emptyList(), Collections.emptyList(), Collections.emptyList());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Gson(Excluder pzVar, FieldNamingStrategy owVar, Map<Type, InstanceCreator<?>> map, boolean z, boolean z2, boolean z3, boolean z4, boolean z5, boolean z6, boolean z7, LongSerializationPolicy poVar, String str, int i, int i2, List<TypeAdapterFactory> list, List<TypeAdapterFactory> list2, List<TypeAdapterFactory> list3) {
        this.f22741A = new ThreadLocal<>();
        this.f22742B = new ConcurrentHashMap();
        this.f22746i = pzVar;
        this.f22747j = owVar;
        this.f22748k = map;
        this.f22743C = new ConstructorConstructor(map);
        this.f22749l = z;
        this.f22750m = z2;
        this.f22751n = z3;
        this.f22752o = z4;
        this.f22753p = z5;
        this.f22754q = z6;
        this.f22755r = z7;
        this.f22759v = poVar;
        this.f22756s = str;
        this.f22757t = i;
        this.f22758u = i2;
        this.f22760w = list;
        this.f22761x = list2;
        ArrayList arrayList = new ArrayList();
        arrayList.add(TypeAdapters.f22996Y);
        arrayList.add(ObjectTypeAdapter.f22932a);
        arrayList.add(pzVar);
        arrayList.addAll(list3);
        arrayList.add(TypeAdapters.f22975D);
        arrayList.add(TypeAdapters.f23010m);
        arrayList.add(TypeAdapters.f23004g);
        arrayList.add(TypeAdapters.f23006i);
        arrayList.add(TypeAdapters.f23008k);
        TypeAdapter<Number> a = m1582a(poVar);
        arrayList.add(TypeAdapters.m1301a(Long.TYPE, Long.class, a));
        arrayList.add(TypeAdapters.m1301a(Double.TYPE, Double.class, m1577a(z7)));
        arrayList.add(TypeAdapters.m1301a(Float.TYPE, Float.class, m1572b(z7)));
        arrayList.add(TypeAdapters.f23021x);
        arrayList.add(TypeAdapters.f23012o);
        arrayList.add(TypeAdapters.f23014q);
        arrayList.add(TypeAdapters.m1300a(AtomicLong.class, m1581a(a)));
        arrayList.add(TypeAdapters.m1300a(AtomicLongArray.class, m1573b(a)));
        arrayList.add(TypeAdapters.f23016s);
        arrayList.add(TypeAdapters.f23023z);
        arrayList.add(TypeAdapters.f22977F);
        arrayList.add(TypeAdapters.f22979H);
        arrayList.add(TypeAdapters.m1300a(BigDecimal.class, TypeAdapters.f22973B));
        arrayList.add(TypeAdapters.m1300a(BigInteger.class, TypeAdapters.f22974C));
        arrayList.add(TypeAdapters.f22981J);
        arrayList.add(TypeAdapters.f22983L);
        arrayList.add(TypeAdapters.f22987P);
        arrayList.add(TypeAdapters.f22989R);
        arrayList.add(TypeAdapters.f22994W);
        arrayList.add(TypeAdapters.f22985N);
        arrayList.add(TypeAdapters.f23001d);
        arrayList.add(DateTypeAdapter.f22912a);
        arrayList.add(TypeAdapters.f22992U);
        arrayList.add(TimeTypeAdapter.f22954a);
        arrayList.add(SqlDateTypeAdapter.f22952a);
        arrayList.add(TypeAdapters.f22990S);
        arrayList.add(ArrayTypeAdapter.f22906a);
        arrayList.add(TypeAdapters.f22999b);
        arrayList.add(new CollectionTypeAdapterFactory(this.f22743C));
        arrayList.add(new MapTypeAdapterFactory(this.f22743C, z2));
        this.f22744D = new JsonAdapterAnnotationTypeAdapterFactory(this.f22743C);
        arrayList.add(this.f22744D);
        arrayList.add(TypeAdapters.f22997Z);
        arrayList.add(new ReflectiveTypeAdapterFactory(this.f22743C, owVar, pzVar, this.f22744D));
        this.f22745h = Collections.unmodifiableList(arrayList);
    }

    /* renamed from: a */
    public GsonBuilder m1602a() {
        return new GsonBuilder(this);
    }

    /* renamed from: b */
    public Excluder m1576b() {
        return this.f22746i;
    }

    /* renamed from: c */
    public FieldNamingStrategy m1571c() {
        return this.f22747j;
    }

    /* renamed from: d */
    public boolean m1570d() {
        return this.f22749l;
    }

    /* renamed from: e */
    public boolean m1569e() {
        return this.f22752o;
    }

    /* renamed from: a */
    private TypeAdapter<Number> m1577a(boolean z) {
        if (z) {
            return TypeAdapters.f23019v;
        }
        return new TypeAdapter<Number>() { // from class: z1.ox.1
            /* renamed from: a */
            public Double mo1234b(JsonReader reVar) throws IOException {
                if (reVar.mo1205f() != JsonToken.NULL) {
                    return Double.valueOf(reVar.mo1200k());
                }
                reVar.mo1201j();
                return null;
            }

            /* renamed from: a  reason: avoid collision after fix types in other method */
            public void mo1235a(JsonWriter rhVar, Number number) throws IOException {
                if (number == null) {
                    rhVar.mo1161f();
                    return;
                }
                Gson.m1601a(number.doubleValue());
                rhVar.mo1176a(number);
            }
        };
    }

    /* renamed from: b */
    private TypeAdapter<Number> m1572b(boolean z) {
        if (z) {
            return TypeAdapters.f23018u;
        }
        return new TypeAdapter<Number>() { // from class: z1.ox.2
            /* renamed from: a */
            public Float mo1234b(JsonReader reVar) throws IOException {
                if (reVar.mo1205f() != JsonToken.NULL) {
                    return Float.valueOf((float) reVar.mo1200k());
                }
                reVar.mo1201j();
                return null;
            }

            /* renamed from: a  reason: avoid collision after fix types in other method */
            public void mo1235a(JsonWriter rhVar, Number number) throws IOException {
                if (number == null) {
                    rhVar.mo1161f();
                    return;
                }
                Gson.m1601a(number.floatValue());
                rhVar.mo1176a(number);
            }
        };
    }

    /* renamed from: a */
    static void m1601a(double d) {
        if (Double.isNaN(d) || Double.isInfinite(d)) {
            throw new IllegalArgumentException(d + " is not a valid double value as per JSON specification. To override this behavior, use GsonBuilder.serializeSpecialFloatingPointValues() method.");
        }
    }

    /* renamed from: a */
    private static TypeAdapter<Number> m1582a(LongSerializationPolicy poVar) {
        if (poVar == LongSerializationPolicy.DEFAULT) {
            return TypeAdapters.f23017t;
        }
        return new TypeAdapter<Number>() { // from class: z1.ox.3
            /* renamed from: a */
            public Number mo1234b(JsonReader reVar) throws IOException {
                if (reVar.mo1205f() != JsonToken.NULL) {
                    return Long.valueOf(reVar.mo1199l());
                }
                reVar.mo1201j();
                return null;
            }

            /* renamed from: a  reason: avoid collision after fix types in other method */
            public void mo1235a(JsonWriter rhVar, Number number) throws IOException {
                if (number == null) {
                    rhVar.mo1161f();
                } else {
                    rhVar.mo1171b(number.toString());
                }
            }
        };
    }

    /* renamed from: a */
    private static TypeAdapter<AtomicLong> m1581a(final TypeAdapter<Number> ppVar) {
        return new TypeAdapter<AtomicLong>() { // from class: z1.ox.4
            /* renamed from: a  reason: avoid collision after fix types in other method */
            public void mo1235a(JsonWriter rhVar, AtomicLong atomicLong) throws IOException {
                TypeAdapter.this.mo1235a(rhVar, (JsonWriter) Long.valueOf(atomicLong.get()));
            }

            /* renamed from: a */
            public AtomicLong mo1234b(JsonReader reVar) throws IOException {
                return new AtomicLong(((Number) TypeAdapter.this.mo1234b(reVar)).longValue());
            }
        }.m1438a();
    }

    /* renamed from: b */
    private static TypeAdapter<AtomicLongArray> m1573b(final TypeAdapter<Number> ppVar) {
        return new TypeAdapter<AtomicLongArray>() { // from class: z1.ox.5
            /* renamed from: a  reason: avoid collision after fix types in other method */
            public void mo1235a(JsonWriter rhVar, AtomicLongArray atomicLongArray) throws IOException {
                rhVar.mo1173b();
                int length = atomicLongArray.length();
                for (int i = 0; i < length; i++) {
                    TypeAdapter.this.mo1235a(rhVar, (JsonWriter) Long.valueOf(atomicLongArray.get(i)));
                }
                rhVar.mo1169c();
            }

            /* renamed from: a */
            public AtomicLongArray mo1234b(JsonReader reVar) throws IOException {
                ArrayList arrayList = new ArrayList();
                reVar.mo1219a();
                while (reVar.mo1206e()) {
                    arrayList.add(Long.valueOf(((Number) TypeAdapter.this.mo1234b(reVar)).longValue()));
                }
                reVar.mo1214b();
                int size = arrayList.size();
                AtomicLongArray atomicLongArray = new AtomicLongArray(size);
                for (int i = 0; i < size; i++) {
                    atomicLongArray.set(i, ((Long) arrayList.get(i)).longValue());
                }
                return atomicLongArray;
            }
        }.m1438a();
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* renamed from: a */
    public <T> TypeAdapter<T> m1579a(TypeToken<T> rdVar) {
        TypeAdapter<T> ppVar = (TypeAdapter<T>) this.f22742B.get(rdVar == null ? f22739y : rdVar);
        if (ppVar != null) {
            return ppVar;
        }
        Map<TypeToken<?>, C5435a<?>> map = this.f22741A.get();
        boolean z = false;
        if (map == null) {
            map = new HashMap<>();
            this.f22741A.set(map);
            z = true;
        }
        C5435a<?> aVar = map.get(rdVar);
        if (aVar != null) {
            return aVar;
        }
        try {
            C5435a<?> aVar2 = new C5435a<>();
            map.put(rdVar, aVar2);
            for (TypeAdapterFactory pqVar : this.f22745h) {
                TypeAdapter ppVar2 = (TypeAdapter<T>) pqVar.mo1264a(this, rdVar);
                if (ppVar2 != null) {
                    aVar2.m1558a((TypeAdapter<?>) ppVar2);
                    this.f22742B.put(rdVar, ppVar2);
                    return ppVar2;
                }
            }
            throw new IllegalArgumentException("GSON (2.8.5) cannot handle " + rdVar);
        } finally {
            map.remove(rdVar);
            if (z) {
                this.f22741A.remove();
            }
        }
    }

    /* renamed from: a */
    public <T> TypeAdapter<T> m1580a(TypeAdapterFactory pqVar, TypeToken<T> rdVar) {
        if (!this.f22745h.contains(pqVar)) {
            pqVar = this.f22744D;
        }
        boolean z = false;
        for (TypeAdapterFactory pqVar2 : this.f22745h) {
            if (z) {
                TypeAdapter<T> a = pqVar2.mo1264a(this, rdVar);
                if (a != null) {
                    return a;
                }
            } else if (pqVar2 == pqVar) {
                z = true;
            }
        }
        throw new IllegalArgumentException("GSON cannot serialize " + rdVar);
    }

    /* renamed from: a */
    public <T> TypeAdapter<T> m1596a(Class<T> cls) {
        return m1579a((TypeToken) TypeToken.get((Class) cls));
    }

    /* renamed from: a */
    public JsonElement m1595a(Object obj) {
        if (obj == null) {
            return JsonNull.f22808a;
        }
        return m1593a(obj, obj.getClass());
    }

    /* renamed from: a */
    public JsonElement m1593a(Object obj, Type type) {
        JsonTreeWriter qqVar = new JsonTreeWriter();
        m1591a(obj, type, qqVar);
        return qqVar.m1328a();
    }

    /* renamed from: b */
    public String m1575b(Object obj) {
        if (obj == null) {
            return m1587a((JsonElement) JsonNull.f22808a);
        }
        return m1574b(obj, obj.getClass());
    }

    /* renamed from: b */
    public String m1574b(Object obj, Type type) {
        StringWriter stringWriter = new StringWriter();
        m1592a(obj, type, stringWriter);
        return stringWriter.toString();
    }

    /* renamed from: a */
    public void m1594a(Object obj, Appendable appendable) throws JsonIOException {
        if (obj != null) {
            m1592a(obj, obj.getClass(), appendable);
        } else {
            m1586a((JsonElement) JsonNull.f22808a, appendable);
        }
    }

    /* renamed from: a */
    public void m1592a(Object obj, Type type, Appendable appendable) throws JsonIOException {
        try {
            m1591a(obj, type, m1597a(Streams.m1346a(appendable)));
        } catch (IOException e) {
            throw new JsonIOException(e);
        }
    }

    /* renamed from: a */
    public void m1591a(Object obj, Type type, JsonWriter rhVar) throws JsonIOException {
        TypeAdapter a = m1579a((TypeToken) TypeToken.get(type));
        boolean g = rhVar.m1160g();
        rhVar.m1170b(true);
        boolean h = rhVar.m1159h();
        rhVar.m1167c(this.f22752o);
        boolean i = rhVar.m1158i();
        rhVar.m1164d(this.f22749l);
        try {
            try {
                a.mo1235a(rhVar, (JsonWriter) obj);
            } catch (IOException e) {
                throw new JsonIOException(e);
            } catch (AssertionError e2) {
                throw new AssertionError("AssertionError (GSON 2.8.5): " + e2.getMessage(), e2);
            }
        } finally {
            rhVar.m1170b(g);
            rhVar.m1167c(h);
            rhVar.m1164d(i);
        }
    }

    /* renamed from: a */
    public String m1587a(JsonElement pdVar) {
        StringWriter stringWriter = new StringWriter();
        m1586a(pdVar, (Appendable) stringWriter);
        return stringWriter.toString();
    }

    /* renamed from: a */
    public void m1586a(JsonElement pdVar, Appendable appendable) throws JsonIOException {
        try {
            m1583a(pdVar, m1597a(Streams.m1346a(appendable)));
        } catch (IOException e) {
            throw new JsonIOException(e);
        }
    }

    /* renamed from: a */
    public JsonWriter m1597a(Writer writer) throws IOException {
        if (this.f22751n) {
            writer.write(f22740z);
        }
        JsonWriter rhVar = new JsonWriter(writer);
        if (this.f22753p) {
            rhVar.m1168c("  ");
        }
        rhVar.m1164d(this.f22749l);
        return rhVar;
    }

    /* renamed from: a */
    public JsonReader m1600a(Reader reader) {
        JsonReader reVar = new JsonReader(reader);
        reVar.m1215a(this.f22754q);
        return reVar;
    }

    /* renamed from: a */
    public void m1583a(JsonElement pdVar, JsonWriter rhVar) throws JsonIOException {
        boolean g = rhVar.m1160g();
        rhVar.m1170b(true);
        boolean h = rhVar.m1159h();
        rhVar.m1167c(this.f22752o);
        boolean i = rhVar.m1158i();
        rhVar.m1164d(this.f22749l);
        try {
            try {
                Streams.m1345a(pdVar, rhVar);
            } catch (IOException e) {
                throw new JsonIOException(e);
            } catch (AssertionError e2) {
                throw new AssertionError("AssertionError (GSON 2.8.5): " + e2.getMessage(), e2);
            }
        } finally {
            rhVar.m1170b(g);
            rhVar.m1167c(h);
            rhVar.m1164d(i);
        }
    }

    /* renamed from: a */
    public <T> T m1589a(String str, Class<T> cls) throws JsonSyntaxException {
        return (T) Primitives.m1351a((Class) cls).cast(m1588a(str, (Type) cls));
    }

    /* renamed from: a */
    public <T> T m1588a(String str, Type type) throws JsonSyntaxException {
        if (str == null) {
            return null;
        }
        return (T) m1598a((Reader) new StringReader(str), type);
    }

    /* renamed from: a */
    public <T> T m1599a(Reader reader, Class<T> cls) throws JsonSyntaxException, JsonIOException {
        JsonReader a = m1600a(reader);
        Object a2 = m1578a(a, (Type) cls);
        m1590a(a2, a);
        return (T) Primitives.m1351a((Class) cls).cast(a2);
    }

    /* renamed from: a */
    public <T> T m1598a(Reader reader, Type type) throws JsonIOException, JsonSyntaxException {
        JsonReader a = m1600a(reader);
        T t = (T) m1578a(a, type);
        m1590a(t, a);
        return t;
    }

    /* renamed from: a */
    private static void m1590a(Object obj, JsonReader reVar) {
        if (obj != null) {
            try {
                if (reVar.mo1205f() != JsonToken.END_DOCUMENT) {
                    throw new JsonIOException("JSON document was not fully consumed.");
                }
            } catch (MalformedJsonException e) {
                throw new JsonSyntaxException(e);
            } catch (IOException e2) {
                throw new JsonIOException(e2);
            }
        }
    }

    /* renamed from: a */
    public <T> T m1578a(JsonReader reVar, Type type) throws JsonIOException, JsonSyntaxException {
        boolean q = reVar.m1194q();
        boolean z = true;
        reVar.m1215a(true);
        try {
            try {
                try {
                    reVar.mo1205f();
                    z = false;
                    T b = m1579a((TypeToken) TypeToken.get(type)).mo1234b(reVar);
                    reVar.m1215a(q);
                    return b;
                } catch (IOException e) {
                    throw new JsonSyntaxException(e);
                } catch (IllegalStateException e2) {
                    throw new JsonSyntaxException(e2);
                }
            } catch (EOFException e3) {
                if (z) {
                    reVar.m1215a(q);
                    return null;
                }
                throw new JsonSyntaxException(e3);
            } catch (AssertionError e4) {
                throw new AssertionError("AssertionError (GSON 2.8.5): " + e4.getMessage(), e4);
            }
        } catch (Throwable th) {
            reVar.m1215a(q);
            throw th;
        }
    }

    /* renamed from: a */
    public <T> T m1585a(JsonElement pdVar, Class<T> cls) throws JsonSyntaxException {
        return (T) Primitives.m1351a((Class) cls).cast(m1584a(pdVar, (Type) cls));
    }

    /* renamed from: a */
    public <T> T m1584a(JsonElement pdVar, Type type) throws JsonSyntaxException {
        if (pdVar == null) {
            return null;
        }
        return (T) m1578a((JsonReader) new JsonTreeReader(pdVar), type);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: Gson.java */
    /* renamed from: z1.ox$a */
    /* loaded from: classes3.dex */
    public static class C5435a<T> extends TypeAdapter<T> {

        /* renamed from: a */
        private TypeAdapter<T> f22766a;

        C5435a() {
        }

        /* renamed from: a */
        public void m1558a(TypeAdapter<T> ppVar) {
            if (this.f22766a == null) {
                this.f22766a = ppVar;
                return;
            }
            throw new AssertionError();
        }

        @Override // p110z1.TypeAdapter
        /* renamed from: b */
        public T mo1234b(JsonReader reVar) throws IOException {
            TypeAdapter<T> ppVar = this.f22766a;
            if (ppVar != null) {
                return ppVar.mo1234b(reVar);
            }
            throw new IllegalStateException();
        }

        @Override // p110z1.TypeAdapter
        /* renamed from: a */
        public void mo1235a(JsonWriter rhVar, T t) throws IOException {
            TypeAdapter<T> ppVar = this.f22766a;
            if (ppVar != null) {
                ppVar.mo1235a(rhVar, (JsonWriter) t);
                return;
            }
            throw new IllegalStateException();
        }
    }

    public String toString() {
        return "{serializeNulls:" + this.f22749l + ",factories:" + this.f22745h + ",instanceCreators:" + this.f22743C + C4963cj.f20747d;
    }
}
