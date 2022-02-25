package p110z1;

import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

/* renamed from: z1.qq */
/* loaded from: classes3.dex */
public final class JsonTreeWriter extends JsonWriter {

    /* renamed from: a */
    private static final Writer f22921a = new Writer() { // from class: z1.qq.1
        @Override // java.io.Writer
        public void write(char[] cArr, int i, int i2) {
            throw new AssertionError();
        }

        @Override // java.io.Writer, java.io.Flushable
        public void flush() throws IOException {
            throw new AssertionError();
        }

        @Override // java.io.Writer, java.io.Closeable, java.lang.AutoCloseable
        public void close() throws IOException {
            throw new AssertionError();
        }
    };

    /* renamed from: b */
    private static final JsonPrimitive f22922b = new JsonPrimitive("closed");

    /* renamed from: d */
    private String f22924d;

    /* renamed from: c */
    private final List<JsonElement> f22923c = new ArrayList();

    /* renamed from: e */
    private JsonElement f22925e = JsonNull.f22808a;

    @Override // p110z1.JsonWriter, java.io.Flushable
    public void flush() throws IOException {
    }

    public JsonTreeWriter() {
        super(f22921a);
    }

    /* renamed from: a */
    public JsonElement m1328a() {
        if (this.f22923c.isEmpty()) {
            return this.f22925e;
        }
        throw new IllegalStateException("Expected one JSON element but was " + this.f22923c);
    }

    /* renamed from: j */
    private JsonElement m1326j() {
        List<JsonElement> list = this.f22923c;
        return list.get(list.size() - 1);
    }

    /* renamed from: a */
    private void m1327a(JsonElement pdVar) {
        if (this.f22924d != null) {
            if (!pdVar.m1486s() || m1158i()) {
                ((JsonObject) m1326j()).m1473a(this.f22924d, pdVar);
            }
            this.f22924d = null;
        } else if (this.f22923c.isEmpty()) {
            this.f22925e = pdVar;
        } else {
            JsonElement j = m1326j();
            if (j instanceof JsonArray) {
                ((JsonArray) j).m1495a(pdVar);
                return;
            }
            throw new IllegalStateException();
        }
    }

    @Override // p110z1.JsonWriter
    /* renamed from: b */
    public JsonWriter mo1173b() throws IOException {
        JsonArray paVar = new JsonArray();
        m1327a(paVar);
        this.f22923c.add(paVar);
        return this;
    }

    @Override // p110z1.JsonWriter
    /* renamed from: c */
    public JsonWriter mo1169c() throws IOException {
        if (this.f22923c.isEmpty() || this.f22924d != null) {
            throw new IllegalStateException();
        } else if (m1326j() instanceof JsonArray) {
            List<JsonElement> list = this.f22923c;
            list.remove(list.size() - 1);
            return this;
        } else {
            throw new IllegalStateException();
        }
    }

    @Override // p110z1.JsonWriter
    /* renamed from: d */
    public JsonWriter mo1166d() throws IOException {
        JsonObject pgVar = new JsonObject();
        m1327a(pgVar);
        this.f22923c.add(pgVar);
        return this;
    }

    @Override // p110z1.JsonWriter
    /* renamed from: e */
    public JsonWriter mo1163e() throws IOException {
        if (this.f22923c.isEmpty() || this.f22924d != null) {
            throw new IllegalStateException();
        } else if (m1326j() instanceof JsonObject) {
            List<JsonElement> list = this.f22923c;
            list.remove(list.size() - 1);
            return this;
        } else {
            throw new IllegalStateException();
        }
    }

    @Override // p110z1.JsonWriter
    /* renamed from: a */
    public JsonWriter mo1175a(String str) throws IOException {
        if (this.f22923c.isEmpty() || this.f22924d != null) {
            throw new IllegalStateException();
        } else if (m1326j() instanceof JsonObject) {
            this.f22924d = str;
            return this;
        } else {
            throw new IllegalStateException();
        }
    }

    @Override // p110z1.JsonWriter
    /* renamed from: b */
    public JsonWriter mo1171b(String str) throws IOException {
        if (str == null) {
            return mo1161f();
        }
        m1327a(new JsonPrimitive(str));
        return this;
    }

    @Override // p110z1.JsonWriter
    /* renamed from: f */
    public JsonWriter mo1161f() throws IOException {
        m1327a(JsonNull.f22808a);
        return this;
    }

    @Override // p110z1.JsonWriter
    /* renamed from: a */
    public JsonWriter mo1174a(boolean z) throws IOException {
        m1327a(new JsonPrimitive(Boolean.valueOf(z)));
        return this;
    }

    @Override // p110z1.JsonWriter
    /* renamed from: a */
    public JsonWriter mo1177a(Boolean bool) throws IOException {
        if (bool == null) {
            return mo1161f();
        }
        m1327a(new JsonPrimitive(bool));
        return this;
    }

    @Override // p110z1.JsonWriter
    /* renamed from: a */
    public JsonWriter mo1182a(double d) throws IOException {
        if (m1160g() || (!Double.isNaN(d) && !Double.isInfinite(d))) {
            m1327a(new JsonPrimitive((Number) Double.valueOf(d)));
            return this;
        }
        throw new IllegalArgumentException("JSON forbids NaN and infinities: " + d);
    }

    @Override // p110z1.JsonWriter
    /* renamed from: a */
    public JsonWriter mo1178a(long j) throws IOException {
        m1327a(new JsonPrimitive((Number) Long.valueOf(j)));
        return this;
    }

    @Override // p110z1.JsonWriter
    /* renamed from: a */
    public JsonWriter mo1176a(Number number) throws IOException {
        if (number == null) {
            return mo1161f();
        }
        if (!m1160g()) {
            double doubleValue = number.doubleValue();
            if (Double.isNaN(doubleValue) || Double.isInfinite(doubleValue)) {
                throw new IllegalArgumentException("JSON forbids NaN and infinities: " + number);
            }
        }
        m1327a(new JsonPrimitive(number));
        return this;
    }

    @Override // p110z1.JsonWriter, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        if (this.f22923c.isEmpty()) {
            this.f22923c.add(f22922b);
            return;
        }
        throw new IOException("Incomplete document");
    }
}
