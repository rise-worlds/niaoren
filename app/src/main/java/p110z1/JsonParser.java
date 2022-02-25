package p110z1;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;

/* renamed from: z1.pi */
/* loaded from: classes3.dex */
public final class JsonParser {
    /* renamed from: a */
    public JsonElement m1463a(String str) throws JsonSyntaxException {
        return m1464a(new StringReader(str));
    }

    /* renamed from: a */
    public JsonElement m1464a(Reader reader) throws JsonIOException, JsonSyntaxException {
        try {
            JsonReader reVar = new JsonReader(reader);
            JsonElement a = m1462a(reVar);
            if (!a.m1486s() && reVar.mo1205f() != JsonToken.END_DOCUMENT) {
                throw new JsonSyntaxException("Did not consume the entire document.");
            }
            return a;
        } catch (MalformedJsonException e) {
            throw new JsonSyntaxException(e);
        } catch (IOException e2) {
            throw new JsonIOException(e2);
        } catch (NumberFormatException e3) {
            throw new JsonSyntaxException(e3);
        }
    }

    /* renamed from: a */
    public JsonElement m1462a(JsonReader reVar) throws JsonIOException, JsonSyntaxException {
        boolean q = reVar.m1194q();
        q = true;
        try {
            try {
                try {
                    return Streams.m1344a(reVar);
                } catch (StackOverflowError e) {
                    throw new JsonParseException("Failed parsing JSON source: " + reVar + " to Json", e);
                }
            } catch (OutOfMemoryError e2) {
                throw new JsonParseException("Failed parsing JSON source: " + reVar + " to Json", e2);
            }
        } finally {
            reVar.m1215a(q);
        }
    }
}
