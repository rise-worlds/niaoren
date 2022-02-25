package p110z1;

import java.io.IOException;
import java.lang.reflect.Type;

/* renamed from: z1.qw */
/* loaded from: classes3.dex */
public final class TreeTypeAdapter<T> extends TypeAdapter<T> {

    /* renamed from: a */
    final Gson f22956a;

    /* renamed from: b */
    private final JsonSerializer<T> f22957b;

    /* renamed from: c */
    private final JsonDeserializer<T> f22958c;

    /* renamed from: d */
    private final TypeToken<T> f22959d;

    /* renamed from: e */
    private final TypeAdapterFactory f22960e;

    /* renamed from: f */
    private final TreeTypeAdapter<T>.C5497a f22961f = new C5497a();

    /* renamed from: g */
    private TypeAdapter<T> f22962g;

    public TreeTypeAdapter(JsonSerializer<T> plVar, JsonDeserializer<T> pcVar, Gson oxVar, TypeToken<T> rdVar, TypeAdapterFactory pqVar) {
        this.f22957b = plVar;
        this.f22958c = pcVar;
        this.f22956a = oxVar;
        this.f22959d = rdVar;
        this.f22960e = pqVar;
    }

    @Override // p110z1.TypeAdapter
    /* renamed from: b */
    public T mo1234b(JsonReader reVar) throws IOException {
        if (this.f22958c == null) {
            return m1307b().mo1234b(reVar);
        }
        JsonElement a = Streams.m1344a(reVar);
        if (a.m1486s()) {
            return null;
        }
        return this.f22958c.m1490a(a, this.f22959d.getType(), this.f22961f);
    }

    @Override // p110z1.TypeAdapter
    /* renamed from: a */
    public void mo1235a(JsonWriter rhVar, T t) throws IOException {
        JsonSerializer<T> plVar = this.f22957b;
        if (plVar == null) {
            m1307b().mo1235a(rhVar, (JsonWriter) t);
        } else if (t == null) {
            rhVar.mo1161f();
        } else {
            Streams.m1345a(plVar.m1440a(t, this.f22959d.getType(), this.f22961f), rhVar);
        }
    }

    /* renamed from: b */
    private TypeAdapter<T> m1307b() {
        TypeAdapter<T> ppVar = this.f22962g;
        if (ppVar != null) {
            return ppVar;
        }
        TypeAdapter<T> a = this.f22956a.m1580a(this.f22960e, this.f22959d);
        this.f22962g = a;
        return a;
    }

    /* renamed from: a */
    public static TypeAdapterFactory m1308a(TypeToken<?> rdVar, Object obj) {
        return new C5498b(obj, rdVar, false, null);
    }

    /* renamed from: b */
    public static TypeAdapterFactory m1306b(TypeToken<?> rdVar, Object obj) {
        return new C5498b(obj, rdVar, rdVar.getType() == rdVar.getRawType(), null);
    }

    /* renamed from: a */
    public static TypeAdapterFactory m1309a(Class<?> cls, Object obj) {
        return new C5498b(obj, null, false, cls);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: TreeTypeAdapter.java */
    /* renamed from: z1.qw$b */
    /* loaded from: classes3.dex */
    public static final class C5498b implements TypeAdapterFactory {

        /* renamed from: a */
        private final TypeToken<?> f22964a;

        /* renamed from: b */
        private final boolean f22965b;

        /* renamed from: c */
        private final Class<?> f22966c;

        /* renamed from: d */
        private final JsonSerializer<?> f22967d;

        /* renamed from: e */
        private final JsonDeserializer<?> f22968e;

        C5498b(Object obj, TypeToken<?> rdVar, boolean z, Class<?> cls) {
            JsonDeserializer<?> pcVar = null;
            this.f22967d = obj instanceof JsonSerializer ? (JsonSerializer) obj : null;
            this.f22968e = obj instanceof JsonDeserializer ? (JsonDeserializer) obj : pcVar;
            C$Gson$Preconditions.m1422a((this.f22967d == null && this.f22968e == null) ? false : true);
            this.f22964a = rdVar;
            this.f22965b = z;
            this.f22966c = cls;
        }

        @Override // p110z1.TypeAdapterFactory
        /* renamed from: a */
        public <T> TypeAdapter<T> mo1264a(Gson oxVar, TypeToken<T> rdVar) {
            boolean z;
            TypeToken<?> rdVar2 = this.f22964a;
            if (rdVar2 != null) {
                z = rdVar2.equals(rdVar) || (this.f22965b && this.f22964a.getType() == rdVar.getRawType());
            } else {
                z = this.f22966c.isAssignableFrom(rdVar.getRawType());
            }
            if (z) {
                return new TreeTypeAdapter(this.f22967d, this.f22968e, oxVar, rdVar, this);
            }
            return null;
        }
    }

    /* compiled from: TreeTypeAdapter.java */
    /* renamed from: z1.qw$a */
    /* loaded from: classes3.dex */
    private final class C5497a implements JsonDeserializationContext, JsonSerializationContext {
        private C5497a() {
        }

        @Override // p110z1.JsonSerializationContext
        /* renamed from: a */
        public JsonElement mo1305a(Object obj) {
            return TreeTypeAdapter.this.f22956a.m1595a(obj);
        }

        @Override // p110z1.JsonSerializationContext
        /* renamed from: a */
        public JsonElement mo1304a(Object obj, Type type) {
            return TreeTypeAdapter.this.f22956a.m1593a(obj, type);
        }

        @Override // p110z1.JsonDeserializationContext
        /* renamed from: a */
        public <R> R mo1303a(JsonElement pdVar, Type type) throws JsonParseException {
            return (R) TreeTypeAdapter.this.f22956a.m1584a(pdVar, type);
        }
    }
}
