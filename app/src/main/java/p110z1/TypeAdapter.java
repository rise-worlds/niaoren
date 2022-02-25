package p110z1;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;

/* renamed from: z1.pp */
/* loaded from: classes3.dex */
public abstract class TypeAdapter<T> {
    /* renamed from: a */
    public abstract void mo1235a(JsonWriter rhVar, T t) throws IOException;

    /* renamed from: b */
    public abstract T mo1234b(JsonReader reVar) throws IOException;

    /* renamed from: a */
    public final void m1436a(Writer writer, T t) throws IOException {
        mo1235a(new JsonWriter(writer), (JsonWriter) t);
    }

    /* renamed from: a */
    public final TypeAdapter<T> m1438a() {
        return new TypeAdapter<T>() { // from class: z1.pp.1
            @Override // p110z1.TypeAdapter
            /* renamed from: a */
            public void mo1235a(JsonWriter rhVar, T t) throws IOException {
                if (t == null) {
                    rhVar.mo1161f();
                } else {
                    TypeAdapter.this.mo1235a(rhVar, (JsonWriter) t);
                }
            }

            @Override // p110z1.TypeAdapter
            /* renamed from: b */
            public T mo1234b(JsonReader reVar) throws IOException {
                if (reVar.mo1205f() != JsonToken.NULL) {
                    return (T) TypeAdapter.this.mo1234b(reVar);
                }
                reVar.mo1201j();
                return null;
            }
        };
    }

    /* renamed from: a */
    public final String m1435a(T t) {
        StringWriter stringWriter = new StringWriter();
        try {
            m1436a((Writer) stringWriter, (StringWriter) t);
            return stringWriter.toString();
        } catch (IOException e) {
            throw new AssertionError(e);
        }
    }

    /* renamed from: b */
    public final JsonElement m1432b(T t) {
        try {
            JsonTreeWriter qqVar = new JsonTreeWriter();
            mo1235a((JsonWriter) qqVar, (JsonTreeWriter) t);
            return qqVar.m1328a();
        } catch (IOException e) {
            throw new JsonIOException(e);
        }
    }

    /* renamed from: a */
    public final T m1437a(Reader reader) throws IOException {
        return mo1234b(new JsonReader(reader));
    }

    /* renamed from: a */
    public final T m1434a(String str) throws IOException {
        return m1437a((Reader) new StringReader(str));
    }

    /* renamed from: a */
    public final T m1433a(JsonElement pdVar) {
        try {
            return mo1234b((JsonReader) new JsonTreeReader(pdVar));
        } catch (IOException e) {
            throw new JsonIOException(e);
        }
    }
}
