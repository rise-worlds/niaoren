package p110z1;

import java.io.IOException;
import java.io.Reader;
import java.util.Iterator;
import java.util.Map;
import org.apache.commons.p105io.FilenameUtils;

/* renamed from: z1.qp */
/* loaded from: classes3.dex */
public final class JsonTreeReader extends JsonReader {

    /* renamed from: b */
    private static final Reader f22915b = new Reader() { // from class: z1.qp.1
        @Override // java.io.Reader
        public int read(char[] cArr, int i, int i2) throws IOException {
            throw new AssertionError();
        }

        @Override // java.io.Reader, java.io.Closeable, java.lang.AutoCloseable
        public void close() throws IOException {
            throw new AssertionError();
        }
    };

    /* renamed from: c */
    private static final Object f22916c = new Object();

    /* renamed from: d */
    private Object[] f22917d = new Object[32];

    /* renamed from: e */
    private int f22918e = 0;

    /* renamed from: f */
    private String[] f22919f = new String[32];

    /* renamed from: g */
    private int[] f22920g = new int[32];

    public JsonTreeReader(JsonElement pdVar) {
        super(f22915b);
        m1334a(pdVar);
    }

    @Override // p110z1.JsonReader
    /* renamed from: a */
    public void mo1219a() throws IOException {
        m1333a(JsonToken.BEGIN_ARRAY);
        m1334a(((JsonArray) m1331t()).iterator());
        this.f22920g[this.f22918e - 1] = 0;
    }

    @Override // p110z1.JsonReader
    /* renamed from: b */
    public void mo1214b() throws IOException {
        m1333a(JsonToken.END_ARRAY);
        m1330u();
        m1330u();
        int i = this.f22918e;
        if (i > 0) {
            int[] iArr = this.f22920g;
            int i2 = i - 1;
            iArr[i2] = iArr[i2] + 1;
        }
    }

    @Override // p110z1.JsonReader
    /* renamed from: c */
    public void mo1209c() throws IOException {
        m1333a(JsonToken.BEGIN_OBJECT);
        m1334a(((JsonObject) m1331t()).m1472b().iterator());
    }

    @Override // p110z1.JsonReader
    /* renamed from: d */
    public void mo1207d() throws IOException {
        m1333a(JsonToken.END_OBJECT);
        m1330u();
        m1330u();
        int i = this.f22918e;
        if (i > 0) {
            int[] iArr = this.f22920g;
            int i2 = i - 1;
            iArr[i2] = iArr[i2] + 1;
        }
    }

    @Override // p110z1.JsonReader
    /* renamed from: e */
    public boolean mo1206e() throws IOException {
        JsonToken f = mo1205f();
        return (f == JsonToken.END_OBJECT || f == JsonToken.END_ARRAY) ? false : true;
    }

    @Override // p110z1.JsonReader
    /* renamed from: f */
    public JsonToken mo1205f() throws IOException {
        if (this.f22918e == 0) {
            return JsonToken.END_DOCUMENT;
        }
        Object t = m1331t();
        if (t instanceof Iterator) {
            boolean z = this.f22917d[this.f22918e - 2] instanceof JsonObject;
            Iterator it = (Iterator) t;
            if (!it.hasNext()) {
                return z ? JsonToken.END_OBJECT : JsonToken.END_ARRAY;
            }
            if (z) {
                return JsonToken.NAME;
            }
            m1334a(it.next());
            return mo1205f();
        } else if (t instanceof JsonObject) {
            return JsonToken.BEGIN_OBJECT;
        } else {
            if (t instanceof JsonArray) {
                return JsonToken.BEGIN_ARRAY;
            }
            if (t instanceof JsonPrimitive) {
                JsonPrimitive pjVar = (JsonPrimitive) t;
                if (pjVar.m1441z()) {
                    return JsonToken.STRING;
                }
                if (pjVar.m1458b()) {
                    return JsonToken.BOOLEAN;
                }
                if (pjVar.m1442y()) {
                    return JsonToken.NUMBER;
                }
                throw new AssertionError();
            } else if (t instanceof JsonNull) {
                return JsonToken.NULL;
            } else {
                if (t == f22916c) {
                    throw new IllegalStateException("JsonReader is closed");
                }
                throw new AssertionError();
            }
        }
    }

    /* renamed from: t */
    private Object m1331t() {
        return this.f22917d[this.f22918e - 1];
    }

    /* renamed from: u */
    private Object m1330u() {
        Object[] objArr = this.f22917d;
        int i = this.f22918e - 1;
        this.f22918e = i;
        Object obj = objArr[i];
        objArr[this.f22918e] = null;
        return obj;
    }

    /* renamed from: a */
    private void m1333a(JsonToken rgVar) throws IOException {
        if (mo1205f() != rgVar) {
            throw new IllegalStateException("Expected " + rgVar + " but was " + mo1205f() + m1329v());
        }
    }

    @Override // p110z1.JsonReader
    /* renamed from: g */
    public String mo1204g() throws IOException {
        m1333a(JsonToken.NAME);
        Map.Entry entry = (Map.Entry) ((Iterator) m1331t()).next();
        String str = (String) entry.getKey();
        this.f22919f[this.f22918e - 1] = str;
        m1334a(entry.getValue());
        return str;
    }

    @Override // p110z1.JsonReader
    /* renamed from: h */
    public String mo1203h() throws IOException {
        JsonToken f = mo1205f();
        if (f == JsonToken.STRING || f == JsonToken.NUMBER) {
            String d = ((JsonPrimitive) m1330u()).mo1455d();
            int i = this.f22918e;
            if (i > 0) {
                int[] iArr = this.f22920g;
                int i2 = i - 1;
                iArr[i2] = iArr[i2] + 1;
            }
            return d;
        }
        throw new IllegalStateException("Expected " + JsonToken.STRING + " but was " + f + m1329v());
    }

    @Override // p110z1.JsonReader
    /* renamed from: i */
    public boolean mo1202i() throws IOException {
        m1333a(JsonToken.BOOLEAN);
        boolean n = ((JsonPrimitive) m1330u()).mo1445n();
        int i = this.f22918e;
        if (i > 0) {
            int[] iArr = this.f22920g;
            int i2 = i - 1;
            iArr[i2] = iArr[i2] + 1;
        }
        return n;
    }

    @Override // p110z1.JsonReader
    /* renamed from: j */
    public void mo1201j() throws IOException {
        m1333a(JsonToken.NULL);
        m1330u();
        int i = this.f22918e;
        if (i > 0) {
            int[] iArr = this.f22920g;
            int i2 = i - 1;
            iArr[i2] = iArr[i2] + 1;
        }
    }

    @Override // p110z1.JsonReader
    /* renamed from: k */
    public double mo1200k() throws IOException {
        JsonToken f = mo1205f();
        if (f == JsonToken.NUMBER || f == JsonToken.STRING) {
            double e = ((JsonPrimitive) m1331t()).mo1454e();
            if (m1194q() || (!Double.isNaN(e) && !Double.isInfinite(e))) {
                m1330u();
                int i = this.f22918e;
                if (i > 0) {
                    int[] iArr = this.f22920g;
                    int i2 = i - 1;
                    iArr[i2] = iArr[i2] + 1;
                }
                return e;
            }
            throw new NumberFormatException("JSON forbids NaN and infinities: " + e);
        }
        throw new IllegalStateException("Expected " + JsonToken.NUMBER + " but was " + f + m1329v());
    }

    @Override // p110z1.JsonReader
    /* renamed from: l */
    public long mo1199l() throws IOException {
        JsonToken f = mo1205f();
        if (f == JsonToken.NUMBER || f == JsonToken.STRING) {
            long i = ((JsonPrimitive) m1331t()).mo1450i();
            m1330u();
            int i2 = this.f22918e;
            if (i2 > 0) {
                int[] iArr = this.f22920g;
                int i3 = i2 - 1;
                iArr[i3] = iArr[i3] + 1;
            }
            return i;
        }
        throw new IllegalStateException("Expected " + JsonToken.NUMBER + " but was " + f + m1329v());
    }

    @Override // p110z1.JsonReader
    /* renamed from: m */
    public int mo1198m() throws IOException {
        JsonToken f = mo1205f();
        if (f == JsonToken.NUMBER || f == JsonToken.STRING) {
            int j = ((JsonPrimitive) m1331t()).mo1449j();
            m1330u();
            int i = this.f22918e;
            if (i > 0) {
                int[] iArr = this.f22920g;
                int i2 = i - 1;
                iArr[i2] = iArr[i2] + 1;
            }
            return j;
        }
        throw new IllegalStateException("Expected " + JsonToken.NUMBER + " but was " + f + m1329v());
    }

    @Override // p110z1.JsonReader, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        this.f22917d = new Object[]{f22916c};
        this.f22918e = 1;
    }

    @Override // p110z1.JsonReader
    /* renamed from: n */
    public void mo1197n() throws IOException {
        if (mo1205f() == JsonToken.NAME) {
            mo1204g();
            this.f22919f[this.f22918e - 2] = "null";
        } else {
            m1330u();
            int i = this.f22918e;
            if (i > 0) {
                this.f22919f[i - 1] = "null";
            }
        }
        int i2 = this.f22918e;
        if (i2 > 0) {
            int[] iArr = this.f22920g;
            int i3 = i2 - 1;
            iArr[i3] = iArr[i3] + 1;
        }
    }

    @Override // p110z1.JsonReader
    public String toString() {
        return getClass().getSimpleName();
    }

    /* renamed from: o */
    public void m1332o() throws IOException {
        m1333a(JsonToken.NAME);
        Map.Entry entry = (Map.Entry) ((Iterator) m1331t()).next();
        m1334a(entry.getValue());
        m1334a(new JsonPrimitive((String) entry.getKey()));
    }

    /* renamed from: a */
    private void m1334a(Object obj) {
        int i = this.f22918e;
        Object[] objArr = this.f22917d;
        if (i == objArr.length) {
            Object[] objArr2 = new Object[i * 2];
            int[] iArr = new int[i * 2];
            String[] strArr = new String[i * 2];
            System.arraycopy(objArr, 0, objArr2, 0, i);
            System.arraycopy(this.f22920g, 0, iArr, 0, this.f22918e);
            System.arraycopy(this.f22919f, 0, strArr, 0, this.f22918e);
            this.f22917d = objArr2;
            this.f22920g = iArr;
            this.f22919f = strArr;
        }
        Object[] objArr3 = this.f22917d;
        int i2 = this.f22918e;
        this.f22918e = i2 + 1;
        objArr3[i2] = obj;
    }

    @Override // p110z1.JsonReader
    /* renamed from: p */
    public String mo1195p() {
        StringBuilder sb = new StringBuilder();
        sb.append(Typography.f21050b);
        int i = 0;
        while (i < this.f22918e) {
            Object[] objArr = this.f22917d;
            if (objArr[i] instanceof JsonArray) {
                i++;
                if (objArr[i] instanceof Iterator) {
                    sb.append('[');
                    sb.append(this.f22920g[i]);
                    sb.append(']');
                }
            } else if (objArr[i] instanceof JsonObject) {
                i++;
                if (objArr[i] instanceof Iterator) {
                    sb.append(FilenameUtils.EXTENSION_SEPARATOR);
                    String[] strArr = this.f22919f;
                    if (strArr[i] != null) {
                        sb.append(strArr[i]);
                    }
                }
            }
            i++;
        }
        return sb.toString();
    }

    /* renamed from: v */
    private String m1329v() {
        return " at path " + mo1195p();
    }
}
