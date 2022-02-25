package p110z1;

import java.math.BigDecimal;
import java.math.BigInteger;

/* renamed from: z1.pj */
/* loaded from: classes3.dex */
public final class JsonPrimitive extends JsonElement {

    /* renamed from: a */
    private static final Class<?>[] f22810a = {Integer.TYPE, Long.TYPE, Short.TYPE, Float.TYPE, Double.TYPE, Byte.TYPE, Boolean.TYPE, Character.TYPE, Integer.class, Long.class, Short.class, Float.class, Double.class, Byte.class, Boolean.class, Character.class};

    /* renamed from: b */
    private Object f22811b;

    /* renamed from: a */
    public JsonPrimitive mo1444o() {
        return this;
    }

    public JsonPrimitive(Boolean bool) {
        m1460a(bool);
    }

    public JsonPrimitive(Number number) {
        m1460a(number);
    }

    public JsonPrimitive(String str) {
        m1460a(str);
    }

    public JsonPrimitive(Character ch) {
        m1460a(ch);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public JsonPrimitive(Object obj) {
        m1460a(obj);
    }

    /* renamed from: a */
    void m1460a(Object obj) {
        if (obj instanceof Character) {
            this.f22811b = String.valueOf(((Character) obj).charValue());
            return;
        }
        C$Gson$Preconditions.m1422a((obj instanceof Number) || m1457b(obj));
        this.f22811b = obj;
    }

    /* renamed from: b */
    public boolean m1458b() {
        return this.f22811b instanceof Boolean;
    }

    @Override // p110z1.JsonElement
    /* renamed from: x */
    Boolean mo1443x() {
        return (Boolean) this.f22811b;
    }

    @Override // p110z1.JsonElement
    /* renamed from: n */
    public boolean mo1445n() {
        if (m1458b()) {
            return mo1443x().booleanValue();
        }
        return Boolean.parseBoolean(mo1455d());
    }

    /* renamed from: y */
    public boolean m1442y() {
        return this.f22811b instanceof Number;
    }

    @Override // p110z1.JsonElement
    /* renamed from: c */
    public Number mo1456c() {
        Object obj = this.f22811b;
        return obj instanceof String ? new LazilyParsedNumber((String) obj) : (Number) obj;
    }

    /* renamed from: z */
    public boolean m1441z() {
        return this.f22811b instanceof String;
    }

    @Override // p110z1.JsonElement
    /* renamed from: d */
    public String mo1455d() {
        if (m1442y()) {
            return mo1456c().toString();
        }
        if (m1458b()) {
            return mo1443x().toString();
        }
        return (String) this.f22811b;
    }

    @Override // p110z1.JsonElement
    /* renamed from: e */
    public double mo1454e() {
        return m1442y() ? mo1456c().doubleValue() : Double.parseDouble(mo1455d());
    }

    @Override // p110z1.JsonElement
    /* renamed from: f */
    public BigDecimal mo1453f() {
        Object obj = this.f22811b;
        return obj instanceof BigDecimal ? (BigDecimal) obj : new BigDecimal(obj.toString());
    }

    @Override // p110z1.JsonElement
    /* renamed from: g */
    public BigInteger mo1452g() {
        Object obj = this.f22811b;
        return obj instanceof BigInteger ? (BigInteger) obj : new BigInteger(obj.toString());
    }

    @Override // p110z1.JsonElement
    /* renamed from: h */
    public float mo1451h() {
        return m1442y() ? mo1456c().floatValue() : Float.parseFloat(mo1455d());
    }

    @Override // p110z1.JsonElement
    /* renamed from: i */
    public long mo1450i() {
        return m1442y() ? mo1456c().longValue() : Long.parseLong(mo1455d());
    }

    @Override // p110z1.JsonElement
    /* renamed from: m */
    public short mo1446m() {
        return m1442y() ? mo1456c().shortValue() : Short.parseShort(mo1455d());
    }

    @Override // p110z1.JsonElement
    /* renamed from: j */
    public int mo1449j() {
        return m1442y() ? mo1456c().intValue() : Integer.parseInt(mo1455d());
    }

    @Override // p110z1.JsonElement
    /* renamed from: k */
    public byte mo1448k() {
        return m1442y() ? mo1456c().byteValue() : Byte.parseByte(mo1455d());
    }

    @Override // p110z1.JsonElement
    /* renamed from: l */
    public char mo1447l() {
        return mo1455d().charAt(0);
    }

    /* renamed from: b */
    private static boolean m1457b(Object obj) {
        if (obj instanceof String) {
            return true;
        }
        Class<?> cls = obj.getClass();
        for (Class<?> cls2 : f22810a) {
            if (cls2.isAssignableFrom(cls)) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        if (this.f22811b == null) {
            return 31;
        }
        if (m1459a(this)) {
            long longValue = mo1456c().longValue();
            return (int) ((longValue >>> 32) ^ longValue);
        }
        Object obj = this.f22811b;
        if (!(obj instanceof Number)) {
            return obj.hashCode();
        }
        long doubleToLongBits = Double.doubleToLongBits(mo1456c().doubleValue());
        return (int) ((doubleToLongBits >>> 32) ^ doubleToLongBits);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        JsonPrimitive pjVar = (JsonPrimitive) obj;
        if (this.f22811b == null) {
            return pjVar.f22811b == null;
        }
        if (m1459a(this) && m1459a(pjVar)) {
            return mo1456c().longValue() == pjVar.mo1456c().longValue();
        }
        if (!(this.f22811b instanceof Number) || !(pjVar.f22811b instanceof Number)) {
            return this.f22811b.equals(pjVar.f22811b);
        }
        double doubleValue = mo1456c().doubleValue();
        double doubleValue2 = pjVar.mo1456c().doubleValue();
        if (doubleValue != doubleValue2) {
            return Double.isNaN(doubleValue) && Double.isNaN(doubleValue2);
        }
        return true;
    }

    /* renamed from: a */
    private static boolean m1459a(JsonPrimitive pjVar) {
        Object obj = pjVar.f22811b;
        if (!(obj instanceof Number)) {
            return false;
        }
        Number number = (Number) obj;
        return (number instanceof BigInteger) || (number instanceof Long) || (number instanceof Integer) || (number instanceof Short) || (number instanceof Byte);
    }
}
