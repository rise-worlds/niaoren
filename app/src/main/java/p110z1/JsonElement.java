package p110z1;

import java.io.IOException;
import java.io.StringWriter;
import java.math.BigDecimal;
import java.math.BigInteger;

/* renamed from: z1.pd */
/* loaded from: classes3.dex */
public abstract class JsonElement {
    /* renamed from: o */
    public abstract JsonElement mo1444o();

    /* renamed from: p */
    public boolean m1489p() {
        return this instanceof JsonArray;
    }

    /* renamed from: q */
    public boolean m1488q() {
        return this instanceof JsonObject;
    }

    /* renamed from: r */
    public boolean m1487r() {
        return this instanceof JsonPrimitive;
    }

    /* renamed from: s */
    public boolean m1486s() {
        return this instanceof JsonNull;
    }

    /* renamed from: t */
    public JsonObject m1485t() {
        if (m1488q()) {
            return (JsonObject) this;
        }
        throw new IllegalStateException("Not a JSON Object: " + this);
    }

    /* renamed from: u */
    public JsonArray m1484u() {
        if (m1489p()) {
            return (JsonArray) this;
        }
        throw new IllegalStateException("Not a JSON Array: " + this);
    }

    /* renamed from: v */
    public JsonPrimitive m1483v() {
        if (m1487r()) {
            return (JsonPrimitive) this;
        }
        throw new IllegalStateException("Not a JSON Primitive: " + this);
    }

    /* renamed from: w */
    public JsonNull m1482w() {
        if (m1486s()) {
            return (JsonNull) this;
        }
        throw new IllegalStateException("Not a JSON Null: " + this);
    }

    /* renamed from: n */
    public boolean mo1445n() {
        throw new UnsupportedOperationException(getClass().getSimpleName());
    }

    /* renamed from: x */
    Boolean mo1443x() {
        throw new UnsupportedOperationException(getClass().getSimpleName());
    }

    /* renamed from: c */
    public Number mo1456c() {
        throw new UnsupportedOperationException(getClass().getSimpleName());
    }

    /* renamed from: d */
    public String mo1455d() {
        throw new UnsupportedOperationException(getClass().getSimpleName());
    }

    /* renamed from: e */
    public double mo1454e() {
        throw new UnsupportedOperationException(getClass().getSimpleName());
    }

    /* renamed from: h */
    public float mo1451h() {
        throw new UnsupportedOperationException(getClass().getSimpleName());
    }

    /* renamed from: i */
    public long mo1450i() {
        throw new UnsupportedOperationException(getClass().getSimpleName());
    }

    /* renamed from: j */
    public int mo1449j() {
        throw new UnsupportedOperationException(getClass().getSimpleName());
    }

    /* renamed from: k */
    public byte mo1448k() {
        throw new UnsupportedOperationException(getClass().getSimpleName());
    }

    /* renamed from: l */
    public char mo1447l() {
        throw new UnsupportedOperationException(getClass().getSimpleName());
    }

    /* renamed from: f */
    public BigDecimal mo1453f() {
        throw new UnsupportedOperationException(getClass().getSimpleName());
    }

    /* renamed from: g */
    public BigInteger mo1452g() {
        throw new UnsupportedOperationException(getClass().getSimpleName());
    }

    /* renamed from: m */
    public short mo1446m() {
        throw new UnsupportedOperationException(getClass().getSimpleName());
    }

    public String toString() {
        try {
            StringWriter stringWriter = new StringWriter();
            JsonWriter rhVar = new JsonWriter(stringWriter);
            rhVar.m1170b(true);
            Streams.m1345a(this, rhVar);
            return stringWriter.toString();
        } catch (IOException e) {
            throw new AssertionError(e);
        }
    }
}
