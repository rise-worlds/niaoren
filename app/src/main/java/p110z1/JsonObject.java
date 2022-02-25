package p110z1;

import java.util.Map;
import java.util.Set;

/* renamed from: z1.pg */
/* loaded from: classes3.dex */
public final class JsonObject extends JsonElement {

    /* renamed from: a */
    private final LinkedTreeMap<String, JsonElement> f22809a = new LinkedTreeMap<>();

    /* renamed from: a */
    public JsonObject mo1444o() {
        JsonObject pgVar = new JsonObject();
        for (Map.Entry<String, JsonElement> entry : this.f22809a.entrySet()) {
            pgVar.m1473a(entry.getKey(), entry.getValue().mo1444o());
        }
        return pgVar;
    }

    /* renamed from: a */
    public void m1473a(String str, JsonElement pdVar) {
        if (pdVar == null) {
            pdVar = JsonNull.f22808a;
        }
        this.f22809a.put(str, pdVar);
    }

    /* renamed from: a */
    public JsonElement m1478a(String str) {
        return this.f22809a.remove(str);
    }

    /* renamed from: a */
    public void m1474a(String str, String str2) {
        m1473a(str, m1479a((Object) str2));
    }

    /* renamed from: a */
    public void m1475a(String str, Number number) {
        m1473a(str, m1479a(number));
    }

    /* renamed from: a */
    public void m1477a(String str, Boolean bool) {
        m1473a(str, m1479a(bool));
    }

    /* renamed from: a */
    public void m1476a(String str, Character ch) {
        m1473a(str, m1479a(ch));
    }

    /* renamed from: a */
    private JsonElement m1479a(Object obj) {
        return obj == null ? JsonNull.f22808a : new JsonPrimitive(obj);
    }

    /* renamed from: b */
    public Set<Map.Entry<String, JsonElement>> m1472b() {
        return this.f22809a.entrySet();
    }

    /* renamed from: y */
    public Set<String> m1466y() {
        return this.f22809a.keySet();
    }

    /* renamed from: z */
    public int m1465z() {
        return this.f22809a.size();
    }

    /* renamed from: b */
    public boolean m1471b(String str) {
        return this.f22809a.containsKey(str);
    }

    /* renamed from: c */
    public JsonElement m1470c(String str) {
        return this.f22809a.get(str);
    }

    /* renamed from: d */
    public JsonPrimitive m1469d(String str) {
        return (JsonPrimitive) this.f22809a.get(str);
    }

    /* renamed from: e */
    public JsonArray m1468e(String str) {
        return (JsonArray) this.f22809a.get(str);
    }

    /* renamed from: f */
    public JsonObject m1467f(String str) {
        return (JsonObject) this.f22809a.get(str);
    }

    public boolean equals(Object obj) {
        return obj == this || ((obj instanceof JsonObject) && ((JsonObject) obj).f22809a.equals(this.f22809a));
    }

    public int hashCode() {
        return this.f22809a.hashCode();
    }
}
