package p110z1;

import java.io.EOFException;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.util.Iterator;
import java.util.NoSuchElementException;

/* renamed from: z1.pm */
/* loaded from: classes3.dex */
public final class JsonStreamParser implements Iterator<JsonElement> {

    /* renamed from: a */
    private final JsonReader f22812a;

    /* renamed from: b */
    private final Object f22813b;

    public JsonStreamParser(String str) {
        this(new StringReader(str));
    }

    public JsonStreamParser(Reader reader) {
        this.f22812a = new JsonReader(reader);
        this.f22812a.m1215a(true);
        this.f22813b = new Object();
    }

    /* renamed from: a */
    public JsonElement next() throws JsonParseException {
        if (hasNext()) {
            try {
                return Streams.m1344a(this.f22812a);
            } catch (OutOfMemoryError e) {
                throw new JsonParseException("Failed parsing JSON source to Json", e);
            } catch (StackOverflowError e2) {
                throw new JsonParseException("Failed parsing JSON source to Json", e2);
            } catch (JsonParseException e3) {
                if (!(e3.getCause() instanceof EOFException)) {
                    throw e3;
                }
                throw new NoSuchElementException();
            }
        } else {
            throw new NoSuchElementException();
        }
    }

    @Override // java.util.Iterator
    public boolean hasNext() {
        boolean z;
        synchronized (this.f22813b) {
            try {
                try {
                    try {
                        z = this.f22812a.mo1205f() != JsonToken.END_DOCUMENT;
                    } catch (MalformedJsonException e) {
                        throw new JsonSyntaxException(e);
                    }
                } catch (IOException e2) {
                    throw new JsonIOException(e2);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
        return z;
    }

    @Override // java.util.Iterator
    public void remove() {
        throw new UnsupportedOperationException();
    }
}
