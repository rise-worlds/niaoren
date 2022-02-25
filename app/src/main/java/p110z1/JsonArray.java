package p110z1;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* renamed from: z1.pa */
/* loaded from: classes3.dex */
public final class JsonArray extends JsonElement implements Iterable<JsonElement> {

    /* renamed from: a */
    private final List<JsonElement> f22807a;

    public JsonArray() {
        this.f22807a = new ArrayList();
    }

    public JsonArray(int i) {
        this.f22807a = new ArrayList(i);
    }

    /* renamed from: a */
    public JsonArray mo1444o() {
        if (this.f22807a.isEmpty()) {
            return new JsonArray();
        }
        JsonArray paVar = new JsonArray(this.f22807a.size());
        for (JsonElement pdVar : this.f22807a) {
            paVar.m1495a(pdVar.mo1444o());
        }
        return paVar;
    }

    /* renamed from: a */
    public void m1500a(Boolean bool) {
        this.f22807a.add(bool == null ? JsonNull.f22808a : new JsonPrimitive(bool));
    }

    /* renamed from: a */
    public void m1499a(Character ch) {
        this.f22807a.add(ch == null ? JsonNull.f22808a : new JsonPrimitive(ch));
    }

    /* renamed from: a */
    public void m1498a(Number number) {
        this.f22807a.add(number == null ? JsonNull.f22808a : new JsonPrimitive(number));
    }

    /* renamed from: a */
    public void m1497a(String str) {
        this.f22807a.add(str == null ? JsonNull.f22808a : new JsonPrimitive(str));
    }

    /* renamed from: a */
    public void m1495a(JsonElement pdVar) {
        if (pdVar == null) {
            pdVar = JsonNull.f22808a;
        }
        this.f22807a.add(pdVar);
    }

    /* renamed from: a */
    public void m1496a(JsonArray paVar) {
        this.f22807a.addAll(paVar.f22807a);
    }

    /* renamed from: a */
    public JsonElement m1501a(int i, JsonElement pdVar) {
        return this.f22807a.set(i, pdVar);
    }

    /* renamed from: b */
    public boolean m1492b(JsonElement pdVar) {
        return this.f22807a.remove(pdVar);
    }

    /* renamed from: a */
    public JsonElement m1502a(int i) {
        return this.f22807a.remove(i);
    }

    /* renamed from: c */
    public boolean m1491c(JsonElement pdVar) {
        return this.f22807a.contains(pdVar);
    }

    /* renamed from: b */
    public int m1494b() {
        return this.f22807a.size();
    }

    @Override // java.lang.Iterable
    public Iterator<JsonElement> iterator() {
        return this.f22807a.iterator();
    }

    /* renamed from: b */
    public JsonElement m1493b(int i) {
        return this.f22807a.get(i);
    }

    @Override // p110z1.JsonElement
    /* renamed from: c */
    public Number mo1456c() {
        if (this.f22807a.size() == 1) {
            return this.f22807a.get(0).mo1456c();
        }
        throw new IllegalStateException();
    }

    @Override // p110z1.JsonElement
    /* renamed from: d */
    public String mo1455d() {
        if (this.f22807a.size() == 1) {
            return this.f22807a.get(0).mo1455d();
        }
        throw new IllegalStateException();
    }

    @Override // p110z1.JsonElement
    /* renamed from: e */
    public double mo1454e() {
        if (this.f22807a.size() == 1) {
            return this.f22807a.get(0).mo1454e();
        }
        throw new IllegalStateException();
    }

    @Override // p110z1.JsonElement
    /* renamed from: f */
    public BigDecimal mo1453f() {
        if (this.f22807a.size() == 1) {
            return this.f22807a.get(0).mo1453f();
        }
        throw new IllegalStateException();
    }

    @Override // p110z1.JsonElement
    /* renamed from: g */
    public BigInteger mo1452g() {
        if (this.f22807a.size() == 1) {
            return this.f22807a.get(0).mo1452g();
        }
        throw new IllegalStateException();
    }

    @Override // p110z1.JsonElement
    /* renamed from: h */
    public float mo1451h() {
        if (this.f22807a.size() == 1) {
            return this.f22807a.get(0).mo1451h();
        }
        throw new IllegalStateException();
    }

    @Override // p110z1.JsonElement
    /* renamed from: i */
    public long mo1450i() {
        if (this.f22807a.size() == 1) {
            return this.f22807a.get(0).mo1450i();
        }
        throw new IllegalStateException();
    }

    @Override // p110z1.JsonElement
    /* renamed from: j */
    public int mo1449j() {
        if (this.f22807a.size() == 1) {
            return this.f22807a.get(0).mo1449j();
        }
        throw new IllegalStateException();
    }

    @Override // p110z1.JsonElement
    /* renamed from: k */
    public byte mo1448k() {
        if (this.f22807a.size() == 1) {
            return this.f22807a.get(0).mo1448k();
        }
        throw new IllegalStateException();
    }

    @Override // p110z1.JsonElement
    /* renamed from: l */
    public char mo1447l() {
        if (this.f22807a.size() == 1) {
            return this.f22807a.get(0).mo1447l();
        }
        throw new IllegalStateException();
    }

    @Override // p110z1.JsonElement
    /* renamed from: m */
    public short mo1446m() {
        if (this.f22807a.size() == 1) {
            return this.f22807a.get(0).mo1446m();
        }
        throw new IllegalStateException();
    }

    @Override // p110z1.JsonElement
    /* renamed from: n */
    public boolean mo1445n() {
        if (this.f22807a.size() == 1) {
            return this.f22807a.get(0).mo1445n();
        }
        throw new IllegalStateException();
    }

    public boolean equals(Object obj) {
        return obj == this || ((obj instanceof JsonArray) && ((JsonArray) obj).f22807a.equals(this.f22807a));
    }

    public int hashCode() {
        return this.f22807a.hashCode();
    }
}
