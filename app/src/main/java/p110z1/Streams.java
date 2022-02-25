package p110z1;

import java.io.EOFException;
import java.io.IOException;
import java.io.Writer;

/* renamed from: z1.qj */
/* loaded from: classes3.dex */
public final class Streams {
    private Streams() {
        throw new UnsupportedOperationException();
    }

    /* renamed from: a */
    public static JsonElement m1344a(JsonReader reVar) throws JsonParseException {
        EOFException e;
        boolean z;
        try {
            try {
                reVar.mo1205f();
                z = false;
            } catch (EOFException e2) {
                e = e2;
                z = true;
            }
            try {
                return TypeAdapters.f22995X.mo1234b(reVar);
            } catch (EOFException e3) {
                e = e3;
                if (z) {
                    return JsonNull.f22808a;
                }
                throw new JsonSyntaxException(e);
            }
        } catch (MalformedJsonException e4) {
            throw new JsonSyntaxException(e4);
        } catch (IOException e5) {
            throw new JsonIOException(e5);
        } catch (NumberFormatException e6) {
            throw new JsonSyntaxException(e6);
        }
    }

    /* renamed from: a */
    public static void m1345a(JsonElement pdVar, JsonWriter rhVar) throws IOException {
        TypeAdapters.f22995X.mo1235a(rhVar, (JsonWriter) pdVar);
    }

    /* renamed from: a */
    public static Writer m1346a(Appendable appendable) {
        return appendable instanceof Writer ? (Writer) appendable : new C5477a(appendable);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: Streams.java */
    /* renamed from: z1.qj$a */
    /* loaded from: classes3.dex */
    public static final class C5477a extends Writer {

        /* renamed from: a */
        private final Appendable f22898a;

        /* renamed from: b */
        private final C5478a f22899b = new C5478a();

        @Override // java.io.Writer, java.io.Closeable, java.lang.AutoCloseable
        public void close() {
        }

        @Override // java.io.Writer, java.io.Flushable
        public void flush() {
        }

        C5477a(Appendable appendable) {
            this.f22898a = appendable;
        }

        @Override // java.io.Writer
        public void write(char[] cArr, int i, int i2) throws IOException {
            C5478a aVar = this.f22899b;
            aVar.f22900a = cArr;
            this.f22898a.append(aVar, i, i2 + i);
        }

        @Override // java.io.Writer
        public void write(int i) throws IOException {
            this.f22898a.append((char) i);
        }

        /* compiled from: Streams.java */
        /* renamed from: z1.qj$a$a */
        /* loaded from: classes3.dex */
        static class C5478a implements CharSequence {

            /* renamed from: a */
            char[] f22900a;

            C5478a() {
            }

            @Override // java.lang.CharSequence
            public int length() {
                return this.f22900a.length;
            }

            @Override // java.lang.CharSequence
            public char charAt(int i) {
                return this.f22900a[i];
            }

            @Override // java.lang.CharSequence
            public CharSequence subSequence(int i, int i2) {
                return new String(this.f22900a, i, i2 - i);
            }
        }
    }
}
