package p110z1;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/* renamed from: z1.qt */
/* loaded from: classes3.dex */
public final class ReflectiveTypeAdapterFactory implements TypeAdapterFactory {

    /* renamed from: a */
    private final ConstructorConstructor f22935a;

    /* renamed from: b */
    private final FieldNamingStrategy f22936b;

    /* renamed from: c */
    private final Excluder f22937c;

    /* renamed from: d */
    private final JsonAdapterAnnotationTypeAdapterFactory f22938d;

    /* renamed from: e */
    private final ReflectionAccessor f22939e = ReflectionAccessor.m1224a();

    public ReflectiveTypeAdapterFactory(ConstructorConstructor pyVar, FieldNamingStrategy owVar, Excluder pzVar, JsonAdapterAnnotationTypeAdapterFactory qoVar) {
        this.f22935a = pyVar;
        this.f22936b = owVar;
        this.f22937c = pzVar;
        this.f22938d = qoVar;
    }

    /* renamed from: a */
    public boolean m1320a(Field field, boolean z) {
        return m1319a(field, z, this.f22937c);
    }

    /* renamed from: a */
    static boolean m1319a(Field field, boolean z, Excluder pzVar) {
        return !pzVar.m1393a(field.getType(), z) && !pzVar.m1392a(field, z);
    }

    /* renamed from: a */
    private List<String> m1321a(Field field) {
        SerializedName ptVar = (SerializedName) field.getAnnotation(SerializedName.class);
        if (ptVar == null) {
            return Collections.singletonList(this.f22936b.translateName(field));
        }
        String a = ptVar.m1427a();
        String[] b = ptVar.m1426b();
        if (b.length == 0) {
            return Collections.singletonList(a);
        }
        ArrayList arrayList = new ArrayList(b.length + 1);
        arrayList.add(a);
        for (String str : b) {
            arrayList.add(str);
        }
        return arrayList;
    }

    @Override // p110z1.TypeAdapterFactory
    /* renamed from: a */
    public <T> TypeAdapter<T> mo1264a(Gson oxVar, TypeToken<T> rdVar) {
        Class<? super T> rawType = rdVar.getRawType();
        if (!Object.class.isAssignableFrom(rawType)) {
            return null;
        }
        return new C5492a(this.f22935a.m1398a(rdVar), m1317a(oxVar, (TypeToken<?>) rdVar, (Class<?>) rawType));
    }

    /* renamed from: a */
    private AbstractC5493b m1318a(final Gson oxVar, final Field field, String str, final TypeToken<?> rdVar, boolean z, boolean z2) {
        final boolean a = Primitives.m1350a((Type) rdVar.getRawType());
        JsonAdapter psVar = (JsonAdapter) field.getAnnotation(JsonAdapter.class);
        final TypeAdapter<?> a2 = psVar != null ? this.f22938d.m1335a(this.f22935a, oxVar, rdVar, psVar) : null;
        final boolean z3 = a2 != null;
        if (a2 == null) {
            a2 = oxVar.m1579a((TypeToken) rdVar);
        }
        return new AbstractC5493b(str, z, z2) { // from class: z1.qt.1
            @Override // p110z1.ReflectiveTypeAdapterFactory.AbstractC5493b
            /* renamed from: a */
            void mo1314a(JsonWriter rhVar, Object obj) throws IOException, IllegalAccessException {
                (z3 ? a2 : new TypeAdapterRuntimeTypeWrapper(oxVar, a2, rdVar.getType())).mo1235a(rhVar, (JsonWriter) field.get(obj));
            }

            @Override // p110z1.ReflectiveTypeAdapterFactory.AbstractC5493b
            /* renamed from: a */
            void mo1315a(JsonReader reVar, Object obj) throws IOException, IllegalAccessException {
                Object b = a2.mo1234b(reVar);
                if (b != null || !a) {
                    field.set(obj, b);
                }
            }

            @Override // p110z1.ReflectiveTypeAdapterFactory.AbstractC5493b
            /* renamed from: a */
            public boolean mo1316a(Object obj) throws IOException, IllegalAccessException {
                return this.f22950i && field.get(obj) != obj;
            }
        };
    }

    /* renamed from: a */
    private Map<String, AbstractC5493b> m1317a(Gson oxVar, TypeToken<?> rdVar, Class<?> cls) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        if (cls.isInterface()) {
            return linkedHashMap;
        }
        Type type = rdVar.getType();
        TypeToken<?> rdVar2 = rdVar;
        Class<?> cls2 = cls;
        while (cls2 != Object.class) {
            Field[] declaredFields = cls2.getDeclaredFields();
            int length = declaredFields.length;
            boolean z = false;
            int i = 0;
            while (i < length) {
                Field field = declaredFields[i];
                boolean a = m1320a(field, true);
                boolean a2 = m1320a(field, z);
                if (a || a2) {
                    this.f22939e.mo1223a(field);
                    Type a3 = C$Gson$Types.m1416a(rdVar2.getType(), cls2, field.getGenericType());
                    List<String> a4 = m1321a(field);
                    AbstractC5493b bVar = null;
                    int size = a4.size();
                    int i2 = 0;
                    while (i2 < size) {
                        String str = a4.get(i2);
                        if (i2 != 0) {
                            a = false;
                        }
                        bVar = (AbstractC5493b) linkedHashMap.put(str, m1318a(oxVar, field, str, TypeToken.get(a3), a, a2));
                        if (bVar != null) {
                            bVar = bVar;
                        }
                        i2++;
                        a = a;
                        a4 = a4;
                        size = size;
                        field = field;
                    }
                    if (bVar != null) {
                        throw new IllegalArgumentException(type + " declares multiple JSON fields named " + bVar.f22949h);
                    }
                }
                i++;
                z = false;
            }
            rdVar2 = TypeToken.get(C$Gson$Types.m1416a(rdVar2.getType(), cls2, cls2.getGenericSuperclass()));
            cls2 = rdVar2.getRawType();
        }
        return linkedHashMap;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: ReflectiveTypeAdapterFactory.java */
    /* renamed from: z1.qt$b */
    /* loaded from: classes3.dex */
    public static abstract class AbstractC5493b {

        /* renamed from: h */
        final String f22949h;

        /* renamed from: i */
        final boolean f22950i;

        /* renamed from: j */
        final boolean f22951j;

        /* renamed from: a */
        abstract void mo1315a(JsonReader reVar, Object obj) throws IOException, IllegalAccessException;

        /* renamed from: a */
        abstract void mo1314a(JsonWriter rhVar, Object obj) throws IOException, IllegalAccessException;

        /* renamed from: a */
        abstract boolean mo1316a(Object obj) throws IOException, IllegalAccessException;

        protected AbstractC5493b(String str, boolean z, boolean z2) {
            this.f22949h = str;
            this.f22950i = z;
            this.f22951j = z2;
        }
    }

    /* compiled from: ReflectiveTypeAdapterFactory.java */
    /* renamed from: z1.qt$a */
    /* loaded from: classes3.dex */
    public static final class C5492a<T> extends TypeAdapter<T> {

        /* renamed from: a */
        private final ObjectConstructor<T> f22947a;

        /* renamed from: b */
        private final Map<String, AbstractC5493b> f22948b;

        C5492a(ObjectConstructor<T> qgVar, Map<String, AbstractC5493b> map) {
            this.f22947a = qgVar;
            this.f22948b = map;
        }

        @Override // p110z1.TypeAdapter
        /* renamed from: b */
        public T mo1234b(JsonReader reVar) throws IOException {
            if (reVar.mo1205f() == JsonToken.NULL) {
                reVar.mo1201j();
                return null;
            }
            T a = this.f22947a.mo1357a();
            try {
                reVar.mo1209c();
                while (reVar.mo1206e()) {
                    AbstractC5493b bVar = this.f22948b.get(reVar.mo1204g());
                    if (bVar != null && bVar.f22951j) {
                        bVar.mo1315a(reVar, a);
                    }
                    reVar.mo1197n();
                }
                reVar.mo1207d();
                return a;
            } catch (IllegalAccessException e) {
                throw new AssertionError(e);
            } catch (IllegalStateException e2) {
                throw new JsonSyntaxException(e2);
            }
        }

        @Override // p110z1.TypeAdapter
        /* renamed from: a */
        public void mo1235a(JsonWriter rhVar, T t) throws IOException {
            if (t == null) {
                rhVar.mo1161f();
                return;
            }
            rhVar.mo1166d();
            try {
                for (AbstractC5493b bVar : this.f22948b.values()) {
                    if (bVar.mo1316a(t)) {
                        rhVar.mo1175a(bVar.f22949h);
                        bVar.mo1314a(rhVar, t);
                    }
                }
                rhVar.mo1163e();
            } catch (IllegalAccessException e) {
                throw new AssertionError(e);
            }
        }
    }
}
