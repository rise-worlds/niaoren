package p110z1;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.InetAddress;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.Calendar;
import java.util.Currency;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicIntegerArray;
import org.slf4j.Marker;

/* renamed from: z1.qy */
/* loaded from: classes3.dex */
public final class TypeAdapters {

    /* renamed from: a */
    public static final TypeAdapter<Class> f22998a = new TypeAdapter<Class>() { // from class: z1.qy.1
        /* renamed from: a  reason: avoid collision after fix types in other method */
        public void mo1235a(JsonWriter rhVar, Class cls) throws IOException {
            throw new UnsupportedOperationException("Attempted to serialize java.lang.Class: " + cls.getName() + ". Forgot to register a type adapter?");
        }

        /* renamed from: a */
        public Class mo1234b(JsonReader reVar) throws IOException {
            throw new UnsupportedOperationException("Attempted to deserialize a java.lang.Class. Forgot to register a type adapter?");
        }
    }.m1438a();

    /* renamed from: b */
    public static final TypeAdapterFactory f22999b = m1300a(Class.class, f22998a);

    /* renamed from: c */
    public static final TypeAdapter<BitSet> f23000c = new TypeAdapter<BitSet>() { // from class: z1.qy.12
        /* renamed from: a */
        public BitSet mo1234b(JsonReader reVar) throws IOException {
            BitSet bitSet = new BitSet();
            reVar.mo1219a();
            JsonToken f = reVar.mo1205f();
            int i = 0;
            while (f != JsonToken.END_ARRAY) {
                boolean z = true;
                switch (C552430.f23046a[f.ordinal()]) {
                    case 1:
                        if (reVar.mo1198m() == 0) {
                            z = false;
                            break;
                        }
                        break;
                    case 2:
                        z = reVar.mo1202i();
                        break;
                    case 3:
                        String h = reVar.mo1203h();
                        try {
                            if (Integer.parseInt(h) == 0) {
                                z = false;
                                break;
                            }
                        } catch (NumberFormatException unused) {
                            throw new JsonSyntaxException("Error: Expecting: bitset number value (1, 0), Found: " + h);
                        }
                        break;
                    default:
                        throw new JsonSyntaxException("Invalid bitset value type: " + f);
                }
                if (z) {
                    bitSet.set(i);
                }
                i++;
                f = reVar.mo1205f();
            }
            reVar.mo1214b();
            return bitSet;
        }

        /* renamed from: a  reason: avoid collision after fix types in other method */
        public void mo1235a(JsonWriter rhVar, BitSet bitSet) throws IOException {
            rhVar.mo1173b();
            int length = bitSet.length();
            for (int i = 0; i < length; i++) {
                rhVar.mo1178a(bitSet.get(i) ? 1L : 0L);
            }
            rhVar.mo1169c();
        }
    }.m1438a();

    /* renamed from: d */
    public static final TypeAdapterFactory f23001d = m1300a(BitSet.class, f23000c);

    /* renamed from: e */
    public static final TypeAdapter<Boolean> f23002e = new TypeAdapter<Boolean>() { // from class: z1.qy.23
        /* renamed from: a */
        public Boolean mo1234b(JsonReader reVar) throws IOException {
            JsonToken f = reVar.mo1205f();
            if (f == JsonToken.NULL) {
                reVar.mo1201j();
                return null;
            } else if (f == JsonToken.STRING) {
                return Boolean.valueOf(Boolean.parseBoolean(reVar.mo1203h()));
            } else {
                return Boolean.valueOf(reVar.mo1202i());
            }
        }

        /* renamed from: a  reason: avoid collision after fix types in other method */
        public void mo1235a(JsonWriter rhVar, Boolean bool) throws IOException {
            rhVar.mo1177a(bool);
        }
    };

    /* renamed from: f */
    public static final TypeAdapter<Boolean> f23003f = new TypeAdapter<Boolean>() { // from class: z1.qy.31
        /* renamed from: a */
        public Boolean mo1234b(JsonReader reVar) throws IOException {
            if (reVar.mo1205f() != JsonToken.NULL) {
                return Boolean.valueOf(reVar.mo1203h());
            }
            reVar.mo1201j();
            return null;
        }

        /* renamed from: a  reason: avoid collision after fix types in other method */
        public void mo1235a(JsonWriter rhVar, Boolean bool) throws IOException {
            rhVar.mo1171b(bool == null ? "null" : bool.toString());
        }
    };

    /* renamed from: g */
    public static final TypeAdapterFactory f23004g = m1301a(Boolean.TYPE, Boolean.class, f23002e);

    /* renamed from: h */
    public static final TypeAdapter<Number> f23005h = new TypeAdapter<Number>() { // from class: z1.qy.32
        /* renamed from: a */
        public Number mo1234b(JsonReader reVar) throws IOException {
            if (reVar.mo1205f() == JsonToken.NULL) {
                reVar.mo1201j();
                return null;
            }
            try {
                return Byte.valueOf((byte) reVar.mo1198m());
            } catch (NumberFormatException e) {
                throw new JsonSyntaxException(e);
            }
        }

        /* renamed from: a  reason: avoid collision after fix types in other method */
        public void mo1235a(JsonWriter rhVar, Number number) throws IOException {
            rhVar.mo1176a(number);
        }
    };

    /* renamed from: i */
    public static final TypeAdapterFactory f23006i = m1301a(Byte.TYPE, Byte.class, f23005h);

    /* renamed from: j */
    public static final TypeAdapter<Number> f23007j = new TypeAdapter<Number>() { // from class: z1.qy.33
        /* renamed from: a */
        public Number mo1234b(JsonReader reVar) throws IOException {
            if (reVar.mo1205f() == JsonToken.NULL) {
                reVar.mo1201j();
                return null;
            }
            try {
                return Short.valueOf((short) reVar.mo1198m());
            } catch (NumberFormatException e) {
                throw new JsonSyntaxException(e);
            }
        }

        /* renamed from: a  reason: avoid collision after fix types in other method */
        public void mo1235a(JsonWriter rhVar, Number number) throws IOException {
            rhVar.mo1176a(number);
        }
    };

    /* renamed from: k */
    public static final TypeAdapterFactory f23008k = m1301a(Short.TYPE, Short.class, f23007j);

    /* renamed from: l */
    public static final TypeAdapter<Number> f23009l = new TypeAdapter<Number>() { // from class: z1.qy.34
        /* renamed from: a */
        public Number mo1234b(JsonReader reVar) throws IOException {
            if (reVar.mo1205f() == JsonToken.NULL) {
                reVar.mo1201j();
                return null;
            }
            try {
                return Integer.valueOf(reVar.mo1198m());
            } catch (NumberFormatException e) {
                throw new JsonSyntaxException(e);
            }
        }

        /* renamed from: a  reason: avoid collision after fix types in other method */
        public void mo1235a(JsonWriter rhVar, Number number) throws IOException {
            rhVar.mo1176a(number);
        }
    };

    /* renamed from: m */
    public static final TypeAdapterFactory f23010m = m1301a(Integer.TYPE, Integer.class, f23009l);

    /* renamed from: n */
    public static final TypeAdapter<AtomicInteger> f23011n = new TypeAdapter<AtomicInteger>() { // from class: z1.qy.35
        /* renamed from: a */
        public AtomicInteger mo1234b(JsonReader reVar) throws IOException {
            try {
                return new AtomicInteger(reVar.mo1198m());
            } catch (NumberFormatException e) {
                throw new JsonSyntaxException(e);
            }
        }

        /* renamed from: a  reason: avoid collision after fix types in other method */
        public void mo1235a(JsonWriter rhVar, AtomicInteger atomicInteger) throws IOException {
            rhVar.mo1178a(atomicInteger.get());
        }
    }.m1438a();

    /* renamed from: o */
    public static final TypeAdapterFactory f23012o = m1300a(AtomicInteger.class, f23011n);

    /* renamed from: p */
    public static final TypeAdapter<AtomicBoolean> f23013p = new TypeAdapter<AtomicBoolean>() { // from class: z1.qy.36
        /* renamed from: a */
        public AtomicBoolean mo1234b(JsonReader reVar) throws IOException {
            return new AtomicBoolean(reVar.mo1202i());
        }

        /* renamed from: a  reason: avoid collision after fix types in other method */
        public void mo1235a(JsonWriter rhVar, AtomicBoolean atomicBoolean) throws IOException {
            rhVar.mo1174a(atomicBoolean.get());
        }
    }.m1438a();

    /* renamed from: q */
    public static final TypeAdapterFactory f23014q = m1300a(AtomicBoolean.class, f23013p);

    /* renamed from: r */
    public static final TypeAdapter<AtomicIntegerArray> f23015r = new TypeAdapter<AtomicIntegerArray>() { // from class: z1.qy.2
        /* renamed from: a */
        public AtomicIntegerArray mo1234b(JsonReader reVar) throws IOException {
            ArrayList arrayList = new ArrayList();
            reVar.mo1219a();
            while (reVar.mo1206e()) {
                try {
                    arrayList.add(Integer.valueOf(reVar.mo1198m()));
                } catch (NumberFormatException e) {
                    throw new JsonSyntaxException(e);
                }
            }
            reVar.mo1214b();
            int size = arrayList.size();
            AtomicIntegerArray atomicIntegerArray = new AtomicIntegerArray(size);
            for (int i = 0; i < size; i++) {
                atomicIntegerArray.set(i, ((Integer) arrayList.get(i)).intValue());
            }
            return atomicIntegerArray;
        }

        /* renamed from: a  reason: avoid collision after fix types in other method */
        public void mo1235a(JsonWriter rhVar, AtomicIntegerArray atomicIntegerArray) throws IOException {
            rhVar.mo1173b();
            int length = atomicIntegerArray.length();
            for (int i = 0; i < length; i++) {
                rhVar.mo1178a(atomicIntegerArray.get(i));
            }
            rhVar.mo1169c();
        }
    }.m1438a();

    /* renamed from: s */
    public static final TypeAdapterFactory f23016s = m1300a(AtomicIntegerArray.class, f23015r);

    /* renamed from: t */
    public static final TypeAdapter<Number> f23017t = new TypeAdapter<Number>() { // from class: z1.qy.3
        /* renamed from: a */
        public Number mo1234b(JsonReader reVar) throws IOException {
            if (reVar.mo1205f() == JsonToken.NULL) {
                reVar.mo1201j();
                return null;
            }
            try {
                return Long.valueOf(reVar.mo1199l());
            } catch (NumberFormatException e) {
                throw new JsonSyntaxException(e);
            }
        }

        /* renamed from: a  reason: avoid collision after fix types in other method */
        public void mo1235a(JsonWriter rhVar, Number number) throws IOException {
            rhVar.mo1176a(number);
        }
    };

    /* renamed from: u */
    public static final TypeAdapter<Number> f23018u = new TypeAdapter<Number>() { // from class: z1.qy.4
        /* renamed from: a */
        public Number mo1234b(JsonReader reVar) throws IOException {
            if (reVar.mo1205f() != JsonToken.NULL) {
                return Float.valueOf((float) reVar.mo1200k());
            }
            reVar.mo1201j();
            return null;
        }

        /* renamed from: a  reason: avoid collision after fix types in other method */
        public void mo1235a(JsonWriter rhVar, Number number) throws IOException {
            rhVar.mo1176a(number);
        }
    };

    /* renamed from: v */
    public static final TypeAdapter<Number> f23019v = new TypeAdapter<Number>() { // from class: z1.qy.5
        /* renamed from: a */
        public Number mo1234b(JsonReader reVar) throws IOException {
            if (reVar.mo1205f() != JsonToken.NULL) {
                return Double.valueOf(reVar.mo1200k());
            }
            reVar.mo1201j();
            return null;
        }

        /* renamed from: a  reason: avoid collision after fix types in other method */
        public void mo1235a(JsonWriter rhVar, Number number) throws IOException {
            rhVar.mo1176a(number);
        }
    };

    /* renamed from: w */
    public static final TypeAdapter<Number> f23020w = new TypeAdapter<Number>() { // from class: z1.qy.6
        /* renamed from: a */
        public Number mo1234b(JsonReader reVar) throws IOException {
            JsonToken f = reVar.mo1205f();
            int i = C552430.f23046a[f.ordinal()];
            if (i != 1) {
                switch (i) {
                    case 3:
                        break;
                    case 4:
                        reVar.mo1201j();
                        return null;
                    default:
                        throw new JsonSyntaxException("Expecting number, got: " + f);
                }
            }
            return new LazilyParsedNumber(reVar.mo1203h());
        }

        /* renamed from: a  reason: avoid collision after fix types in other method */
        public void mo1235a(JsonWriter rhVar, Number number) throws IOException {
            rhVar.mo1176a(number);
        }
    };

    /* renamed from: x */
    public static final TypeAdapterFactory f23021x = m1300a(Number.class, f23020w);

    /* renamed from: y */
    public static final TypeAdapter<Character> f23022y = new TypeAdapter<Character>() { // from class: z1.qy.7
        /* renamed from: a */
        public Character mo1234b(JsonReader reVar) throws IOException {
            if (reVar.mo1205f() == JsonToken.NULL) {
                reVar.mo1201j();
                return null;
            }
            String h = reVar.mo1203h();
            if (h.length() == 1) {
                return Character.valueOf(h.charAt(0));
            }
            throw new JsonSyntaxException("Expecting character, got: " + h);
        }

        /* renamed from: a  reason: avoid collision after fix types in other method */
        public void mo1235a(JsonWriter rhVar, Character ch) throws IOException {
            rhVar.mo1171b(ch == null ? null : String.valueOf(ch));
        }
    };

    /* renamed from: z */
    public static final TypeAdapterFactory f23023z = m1301a(Character.TYPE, Character.class, f23022y);

    /* renamed from: A */
    public static final TypeAdapter<String> f22972A = new TypeAdapter<String>() { // from class: z1.qy.8
        /* renamed from: a */
        public String mo1234b(JsonReader reVar) throws IOException {
            JsonToken f = reVar.mo1205f();
            if (f == JsonToken.NULL) {
                reVar.mo1201j();
                return null;
            } else if (f == JsonToken.BOOLEAN) {
                return Boolean.toString(reVar.mo1202i());
            } else {
                return reVar.mo1203h();
            }
        }

        /* renamed from: a  reason: avoid collision after fix types in other method */
        public void mo1235a(JsonWriter rhVar, String str) throws IOException {
            rhVar.mo1171b(str);
        }
    };

    /* renamed from: B */
    public static final TypeAdapter<BigDecimal> f22973B = new TypeAdapter<BigDecimal>() { // from class: z1.qy.9
        /* renamed from: a */
        public BigDecimal mo1234b(JsonReader reVar) throws IOException {
            if (reVar.mo1205f() == JsonToken.NULL) {
                reVar.mo1201j();
                return null;
            }
            try {
                return new BigDecimal(reVar.mo1203h());
            } catch (NumberFormatException e) {
                throw new JsonSyntaxException(e);
            }
        }

        /* renamed from: a  reason: avoid collision after fix types in other method */
        public void mo1235a(JsonWriter rhVar, BigDecimal bigDecimal) throws IOException {
            rhVar.mo1176a(bigDecimal);
        }
    };

    /* renamed from: C */
    public static final TypeAdapter<BigInteger> f22974C = new TypeAdapter<BigInteger>() { // from class: z1.qy.10
        /* renamed from: a */
        public BigInteger mo1234b(JsonReader reVar) throws IOException {
            if (reVar.mo1205f() == JsonToken.NULL) {
                reVar.mo1201j();
                return null;
            }
            try {
                return new BigInteger(reVar.mo1203h());
            } catch (NumberFormatException e) {
                throw new JsonSyntaxException(e);
            }
        }

        /* renamed from: a  reason: avoid collision after fix types in other method */
        public void mo1235a(JsonWriter rhVar, BigInteger bigInteger) throws IOException {
            rhVar.mo1176a(bigInteger);
        }
    };

    /* renamed from: D */
    public static final TypeAdapterFactory f22975D = m1300a(String.class, f22972A);

    /* renamed from: E */
    public static final TypeAdapter<StringBuilder> f22976E = new TypeAdapter<StringBuilder>() { // from class: z1.qy.11
        /* renamed from: a */
        public StringBuilder mo1234b(JsonReader reVar) throws IOException {
            if (reVar.mo1205f() != JsonToken.NULL) {
                return new StringBuilder(reVar.mo1203h());
            }
            reVar.mo1201j();
            return null;
        }

        /* renamed from: a  reason: avoid collision after fix types in other method */
        public void mo1235a(JsonWriter rhVar, StringBuilder sb) throws IOException {
            rhVar.mo1171b(sb == null ? null : sb.toString());
        }
    };

    /* renamed from: F */
    public static final TypeAdapterFactory f22977F = m1300a(StringBuilder.class, f22976E);

    /* renamed from: G */
    public static final TypeAdapter<StringBuffer> f22978G = new TypeAdapter<StringBuffer>() { // from class: z1.qy.13
        /* renamed from: a */
        public StringBuffer mo1234b(JsonReader reVar) throws IOException {
            if (reVar.mo1205f() != JsonToken.NULL) {
                return new StringBuffer(reVar.mo1203h());
            }
            reVar.mo1201j();
            return null;
        }

        /* renamed from: a  reason: avoid collision after fix types in other method */
        public void mo1235a(JsonWriter rhVar, StringBuffer stringBuffer) throws IOException {
            rhVar.mo1171b(stringBuffer == null ? null : stringBuffer.toString());
        }
    };

    /* renamed from: H */
    public static final TypeAdapterFactory f22979H = m1300a(StringBuffer.class, f22978G);

    /* renamed from: I */
    public static final TypeAdapter<URL> f22980I = new TypeAdapter<URL>() { // from class: z1.qy.14
        /* renamed from: a */
        public URL mo1234b(JsonReader reVar) throws IOException {
            if (reVar.mo1205f() == JsonToken.NULL) {
                reVar.mo1201j();
                return null;
            }
            String h = reVar.mo1203h();
            if ("null".equals(h)) {
                return null;
            }
            return new URL(h);
        }

        /* renamed from: a  reason: avoid collision after fix types in other method */
        public void mo1235a(JsonWriter rhVar, URL url) throws IOException {
            rhVar.mo1171b(url == null ? null : url.toExternalForm());
        }
    };

    /* renamed from: J */
    public static final TypeAdapterFactory f22981J = m1300a(URL.class, f22980I);

    /* renamed from: K */
    public static final TypeAdapter<URI> f22982K = new TypeAdapter<URI>() { // from class: z1.qy.15
        /* renamed from: a */
        public URI mo1234b(JsonReader reVar) throws IOException {
            if (reVar.mo1205f() == JsonToken.NULL) {
                reVar.mo1201j();
                return null;
            }
            try {
                String h = reVar.mo1203h();
                if ("null".equals(h)) {
                    return null;
                }
                return new URI(h);
            } catch (URISyntaxException e) {
                throw new JsonIOException(e);
            }
        }

        /* renamed from: a  reason: avoid collision after fix types in other method */
        public void mo1235a(JsonWriter rhVar, URI uri) throws IOException {
            rhVar.mo1171b(uri == null ? null : uri.toASCIIString());
        }
    };

    /* renamed from: L */
    public static final TypeAdapterFactory f22983L = m1300a(URI.class, f22982K);

    /* renamed from: M */
    public static final TypeAdapter<InetAddress> f22984M = new TypeAdapter<InetAddress>() { // from class: z1.qy.16
        /* renamed from: a */
        public InetAddress mo1234b(JsonReader reVar) throws IOException {
            if (reVar.mo1205f() != JsonToken.NULL) {
                return InetAddress.getByName(reVar.mo1203h());
            }
            reVar.mo1201j();
            return null;
        }

        /* renamed from: a  reason: avoid collision after fix types in other method */
        public void mo1235a(JsonWriter rhVar, InetAddress inetAddress) throws IOException {
            rhVar.mo1171b(inetAddress == null ? null : inetAddress.getHostAddress());
        }
    };

    /* renamed from: N */
    public static final TypeAdapterFactory f22985N = m1297b(InetAddress.class, f22984M);

    /* renamed from: O */
    public static final TypeAdapter<UUID> f22986O = new TypeAdapter<UUID>() { // from class: z1.qy.17
        /* renamed from: a */
        public UUID mo1234b(JsonReader reVar) throws IOException {
            if (reVar.mo1205f() != JsonToken.NULL) {
                return UUID.fromString(reVar.mo1203h());
            }
            reVar.mo1201j();
            return null;
        }

        /* renamed from: a  reason: avoid collision after fix types in other method */
        public void mo1235a(JsonWriter rhVar, UUID uuid) throws IOException {
            rhVar.mo1171b(uuid == null ? null : uuid.toString());
        }
    };

    /* renamed from: P */
    public static final TypeAdapterFactory f22987P = m1300a(UUID.class, f22986O);

    /* renamed from: Q */
    public static final TypeAdapter<Currency> f22988Q = new TypeAdapter<Currency>() { // from class: z1.qy.18
        /* renamed from: a */
        public Currency mo1234b(JsonReader reVar) throws IOException {
            return Currency.getInstance(reVar.mo1203h());
        }

        /* renamed from: a  reason: avoid collision after fix types in other method */
        public void mo1235a(JsonWriter rhVar, Currency currency) throws IOException {
            rhVar.mo1171b(currency.getCurrencyCode());
        }
    }.m1438a();

    /* renamed from: R */
    public static final TypeAdapterFactory f22989R = m1300a(Currency.class, f22988Q);

    /* renamed from: S */
    public static final TypeAdapterFactory f22990S = new TypeAdapterFactory() { // from class: z1.qy.19
        @Override // p110z1.TypeAdapterFactory
        /* renamed from: a */
        public <T> TypeAdapter<T> mo1264a(Gson oxVar, TypeToken<T> rdVar) {
            if (rdVar.getRawType() != Timestamp.class) {
                return null;
            }
            final TypeAdapter<T> a = oxVar.m1596a((Class) Date.class);
            return (TypeAdapter<T>) new TypeAdapter<Timestamp>() { // from class: z1.qy.19.1
                /* renamed from: a */
                public Timestamp mo1234b(JsonReader reVar) throws IOException {
                    Date date = (Date) a.mo1234b(reVar);
                    if (date != null) {
                        return new Timestamp(date.getTime());
                    }
                    return null;
                }

                /* renamed from: a  reason: avoid collision after fix types in other method */
                public void mo1235a(JsonWriter rhVar, Timestamp timestamp) throws IOException {
                    a.mo1235a(rhVar, (JsonWriter) timestamp);
                }
            };
        }
    };

    /* renamed from: T */
    public static final TypeAdapter<Calendar> f22991T = new TypeAdapter<Calendar>() { // from class: z1.qy.20

        /* renamed from: a */
        private static final String f23026a = "year";

        /* renamed from: b */
        private static final String f23027b = "month";

        /* renamed from: c */
        private static final String f23028c = "dayOfMonth";

        /* renamed from: d */
        private static final String f23029d = "hourOfDay";

        /* renamed from: e */
        private static final String f23030e = "minute";

        /* renamed from: f */
        private static final String f23031f = "second";

        /* renamed from: a */
        public Calendar mo1234b(JsonReader reVar) throws IOException {
            if (reVar.mo1205f() == JsonToken.NULL) {
                reVar.mo1201j();
                return null;
            }
            reVar.mo1209c();
            int i = 0;
            int i2 = 0;
            int i3 = 0;
            int i4 = 0;
            int i5 = 0;
            int i6 = 0;
            while (reVar.mo1205f() != JsonToken.END_OBJECT) {
                String g = reVar.mo1204g();
                int m = reVar.mo1198m();
                if (f23026a.equals(g)) {
                    i = m;
                } else if (f23027b.equals(g)) {
                    i2 = m;
                } else if (f23028c.equals(g)) {
                    i3 = m;
                } else if (f23029d.equals(g)) {
                    i4 = m;
                } else if ("minute".equals(g)) {
                    i5 = m;
                } else if ("second".equals(g)) {
                    i6 = m;
                }
            }
            reVar.mo1207d();
            return new GregorianCalendar(i, i2, i3, i4, i5, i6);
        }

        /* renamed from: a  reason: avoid collision after fix types in other method */
        public void mo1235a(JsonWriter rhVar, Calendar calendar) throws IOException {
            if (calendar == null) {
                rhVar.mo1161f();
                return;
            }
            rhVar.mo1166d();
            rhVar.mo1175a(f23026a);
            rhVar.mo1178a(calendar.get(1));
            rhVar.mo1175a(f23027b);
            rhVar.mo1178a(calendar.get(2));
            rhVar.mo1175a(f23028c);
            rhVar.mo1178a(calendar.get(5));
            rhVar.mo1175a(f23029d);
            rhVar.mo1178a(calendar.get(11));
            rhVar.mo1175a("minute");
            rhVar.mo1178a(calendar.get(12));
            rhVar.mo1175a("second");
            rhVar.mo1178a(calendar.get(13));
            rhVar.mo1163e();
        }
    };

    /* renamed from: U */
    public static final TypeAdapterFactory f22992U = m1298b(Calendar.class, GregorianCalendar.class, f22991T);

    /* renamed from: V */
    public static final TypeAdapter<Locale> f22993V = new TypeAdapter<Locale>() { // from class: z1.qy.21
        /* renamed from: a */
        public Locale mo1234b(JsonReader reVar) throws IOException {
            String str = null;
            if (reVar.mo1205f() == JsonToken.NULL) {
                reVar.mo1201j();
                return null;
            }
            StringTokenizer stringTokenizer = new StringTokenizer(reVar.mo1203h(), "_");
            String nextToken = stringTokenizer.hasMoreElements() ? stringTokenizer.nextToken() : null;
            String nextToken2 = stringTokenizer.hasMoreElements() ? stringTokenizer.nextToken() : null;
            if (stringTokenizer.hasMoreElements()) {
                str = stringTokenizer.nextToken();
            }
            if (nextToken2 == null && str == null) {
                return new Locale(nextToken);
            }
            if (str == null) {
                return new Locale(nextToken, nextToken2);
            }
            return new Locale(nextToken, nextToken2, str);
        }

        /* renamed from: a  reason: avoid collision after fix types in other method */
        public void mo1235a(JsonWriter rhVar, Locale locale) throws IOException {
            rhVar.mo1171b(locale == null ? null : locale.toString());
        }
    };

    /* renamed from: W */
    public static final TypeAdapterFactory f22994W = m1300a(Locale.class, f22993V);

    /* renamed from: X */
    public static final TypeAdapter<JsonElement> f22995X = new TypeAdapter<JsonElement>() { // from class: z1.qy.22
        /* renamed from: a */
        public JsonElement mo1234b(JsonReader reVar) throws IOException {
            switch (C552430.f23046a[reVar.mo1205f().ordinal()]) {
                case 1:
                    return new JsonPrimitive((Number) new LazilyParsedNumber(reVar.mo1203h()));
                case 2:
                    return new JsonPrimitive(Boolean.valueOf(reVar.mo1202i()));
                case 3:
                    return new JsonPrimitive(reVar.mo1203h());
                case 4:
                    reVar.mo1201j();
                    return JsonNull.f22808a;
                case 5:
                    JsonArray paVar = new JsonArray();
                    reVar.mo1219a();
                    while (reVar.mo1206e()) {
                        paVar.m1495a(mo1234b(reVar));
                    }
                    reVar.mo1214b();
                    return paVar;
                case 6:
                    JsonObject pgVar = new JsonObject();
                    reVar.mo1209c();
                    while (reVar.mo1206e()) {
                        pgVar.m1473a(reVar.mo1204g(), mo1234b(reVar));
                    }
                    reVar.mo1207d();
                    return pgVar;
                default:
                    throw new IllegalArgumentException();
            }
        }

        /* renamed from: a  reason: avoid collision after fix types in other method */
        public void mo1235a(JsonWriter rhVar, JsonElement pdVar) throws IOException {
            if (pdVar == null || pdVar.m1486s()) {
                rhVar.mo1161f();
            } else if (pdVar.m1487r()) {
                JsonPrimitive v = pdVar.m1483v();
                if (v.m1442y()) {
                    rhVar.mo1176a(v.mo1456c());
                } else if (v.m1458b()) {
                    rhVar.mo1174a(v.mo1445n());
                } else {
                    rhVar.mo1171b(v.mo1455d());
                }
            } else if (pdVar.m1489p()) {
                rhVar.mo1173b();
                Iterator<JsonElement> it = pdVar.m1484u().iterator();
                while (it.hasNext()) {
                    mo1235a(rhVar, it.next());
                }
                rhVar.mo1169c();
            } else if (pdVar.m1488q()) {
                rhVar.mo1166d();
                for (Map.Entry<String, JsonElement> entry : pdVar.m1485t().m1472b()) {
                    rhVar.mo1175a(entry.getKey());
                    mo1235a(rhVar, entry.getValue());
                }
                rhVar.mo1163e();
            } else {
                throw new IllegalArgumentException("Couldn't write " + pdVar.getClass());
            }
        }
    };

    /* renamed from: Y */
    public static final TypeAdapterFactory f22996Y = m1297b(JsonElement.class, f22995X);

    /* renamed from: Z */
    public static final TypeAdapterFactory f22997Z = new TypeAdapterFactory() { // from class: z1.qy.24
        @Override // p110z1.TypeAdapterFactory
        /* renamed from: a */
        public <T> TypeAdapter<T> mo1264a(Gson oxVar, TypeToken<T> rdVar) {
            Class rawType = rdVar.getRawType();
            if (!Enum.class.isAssignableFrom(rawType) || rawType == Enum.class) {
                return null;
            }
            if (!rawType.isEnum()) {
                rawType = (Class<? super Object>) rawType.getSuperclass();
            }
            return new C5537a(rawType);
        }
    };

    private TypeAdapters() {
        throw new UnsupportedOperationException();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: TypeAdapters.java */
    /* renamed from: z1.qy$30 */
    /* loaded from: classes3.dex */
    public static /* synthetic */ class C552430 {

        /* renamed from: a */
        static final /* synthetic */ int[] f23046a = new int[JsonToken.values().length];

        static {
            try {
                f23046a[JsonToken.NUMBER.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f23046a[JsonToken.BOOLEAN.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f23046a[JsonToken.STRING.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f23046a[JsonToken.NULL.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f23046a[JsonToken.BEGIN_ARRAY.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                f23046a[JsonToken.BEGIN_OBJECT.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                f23046a[JsonToken.END_DOCUMENT.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                f23046a[JsonToken.NAME.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                f23046a[JsonToken.END_OBJECT.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                f23046a[JsonToken.END_ARRAY.ordinal()] = 10;
            } catch (NoSuchFieldError unused10) {
            }
        }
    }

    /* compiled from: TypeAdapters.java */
    /* renamed from: z1.qy$a */
    /* loaded from: classes3.dex */
    private static final class C5537a<T extends Enum<T>> extends TypeAdapter<T> {

        /* renamed from: a */
        private final Map<String, T> f23047a = new HashMap();

        /* renamed from: b */
        private final Map<T, String> f23048b = new HashMap();

        /* JADX WARN: Multi-variable type inference failed */
        @Override // p110z1.TypeAdapter
        /* renamed from: a */
        public /* bridge */ /* synthetic */ void mo1235a(JsonWriter rhVar, Object obj) throws IOException {
            m1236a(rhVar, (JsonWriter) ((Enum) obj));
        }

        public C5537a(Class<T> cls) {
            T[] enumConstants;
            try {
                for (T t : cls.getEnumConstants()) {
                    String name = t.name();
                    SerializedName ptVar = (SerializedName) cls.getField(name).getAnnotation(SerializedName.class);
                    if (ptVar != null) {
                        name = ptVar.m1427a();
                        for (String str : ptVar.m1426b()) {
                            this.f23047a.put(str, t);
                        }
                    }
                    this.f23047a.put(name, t);
                    this.f23048b.put(t, name);
                }
            } catch (NoSuchFieldException e) {
                throw new AssertionError(e);
            }
        }

        /* renamed from: a */
        public T mo1234b(JsonReader reVar) throws IOException {
            if (reVar.mo1205f() != JsonToken.NULL) {
                return this.f23047a.get(reVar.mo1203h());
            }
            reVar.mo1201j();
            return null;
        }

        /* renamed from: a */
        public void m1236a(JsonWriter rhVar, T t) throws IOException {
            rhVar.mo1171b(t == null ? null : this.f23048b.get(t));
        }
    }

    /* renamed from: a */
    public static <TT> TypeAdapterFactory m1299a(final TypeToken<TT> rdVar, final TypeAdapter<TT> ppVar) {
        return new TypeAdapterFactory() { // from class: z1.qy.25
            @Override // p110z1.TypeAdapterFactory
            /* renamed from: a */
            public <T> TypeAdapter<T> mo1264a(Gson oxVar, TypeToken<T> rdVar2) {
                if (rdVar2.equals(TypeToken.this)) {
                    return ppVar;
                }
                return null;
            }
        };
    }

    /* renamed from: a */
    public static <TT> TypeAdapterFactory m1300a(final Class<TT> cls, final TypeAdapter<TT> ppVar) {
        return new TypeAdapterFactory() { // from class: z1.qy.26
            @Override // p110z1.TypeAdapterFactory
            /* renamed from: a */
            public <T> TypeAdapter<T> mo1264a(Gson oxVar, TypeToken<T> rdVar) {
                if (rdVar.getRawType() == cls) {
                    return ppVar;
                }
                return null;
            }

            public String toString() {
                return "Factory[type=" + cls.getName() + ",adapter=" + ppVar + "]";
            }
        };
    }

    /* renamed from: a */
    public static <TT> TypeAdapterFactory m1301a(final Class<TT> cls, final Class<TT> cls2, final TypeAdapter<? super TT> ppVar) {
        return new TypeAdapterFactory() { // from class: z1.qy.27
            @Override // p110z1.TypeAdapterFactory
            /* renamed from: a */
            public <T> TypeAdapter<T> mo1264a(Gson oxVar, TypeToken<T> rdVar) {
                Class<? super T> rawType = rdVar.getRawType();
                if (rawType == cls || rawType == cls2) {
                    return ppVar;
                }
                return null;
            }

            public String toString() {
                return "Factory[type=" + cls2.getName() + Marker.ANY_NON_NULL_MARKER + cls.getName() + ",adapter=" + ppVar + "]";
            }
        };
    }

    /* renamed from: b */
    public static <TT> TypeAdapterFactory m1298b(final Class<TT> cls, final Class<? extends TT> cls2, final TypeAdapter<? super TT> ppVar) {
        return new TypeAdapterFactory() { // from class: z1.qy.28
            @Override // p110z1.TypeAdapterFactory
            /* renamed from: a */
            public <T> TypeAdapter<T> mo1264a(Gson oxVar, TypeToken<T> rdVar) {
                Class<? super T> rawType = rdVar.getRawType();
                if (rawType == cls || rawType == cls2) {
                    return ppVar;
                }
                return null;
            }

            public String toString() {
                return "Factory[type=" + cls.getName() + Marker.ANY_NON_NULL_MARKER + cls2.getName() + ",adapter=" + ppVar + "]";
            }
        };
    }

    /* renamed from: b */
    public static <T1> TypeAdapterFactory m1297b(final Class<T1> cls, final TypeAdapter<T1> ppVar) {
        return new TypeAdapterFactory() { // from class: z1.qy.29
            @Override // p110z1.TypeAdapterFactory
            /* renamed from: a */
            public <T2> TypeAdapter<T2> mo1264a(Gson oxVar, TypeToken<T2> rdVar) {
                final Class<? super T2> rawType = rdVar.getRawType();
                if (!cls.isAssignableFrom(rawType)) {
                    return null;
                }
                return (TypeAdapter<T2>) new TypeAdapter<T1>() { // from class: z1.qy.29.1
                    @Override // p110z1.TypeAdapter
                    /* renamed from: a */
                    public void mo1235a(JsonWriter rhVar, T1 t1) throws IOException {
                        ppVar.mo1235a(rhVar, (JsonWriter) t1);
                    }

                    /* JADX WARN: Multi-variable type inference failed */
                    /* JADX WARN: Type inference failed for: r4v1, types: [java.lang.Object, T1] */
                    /* JADX WARN: Unknown variable types count: 1 */
                    @Override // p110z1.TypeAdapter
                    /* renamed from: b */
                    /*
                        Code decompiled incorrectly, please refer to instructions dump.
                        To view partially-correct code enable 'Show inconsistent code' option in preferences
                    */
                    public T1 mo1234b(p110z1.JsonReader r4) throws java.io.IOException {
                        /*
                            r3 = this;
                            z1.qy$29 r0 = p110z1.TypeAdapters.C552129.this
                            z1.pp r0 = r2
                            java.lang.Object r4 = r0.mo1234b(r4)
                            if (r4 == 0) goto L_0x0040
                            java.lang.Class r0 = r2
                            boolean r0 = r0.isInstance(r4)
                            if (r0 == 0) goto L_0x0013
                            goto L_0x0040
                        L_0x0013:
                            z1.pn r0 = new z1.pn
                            java.lang.StringBuilder r1 = new java.lang.StringBuilder
                            r1.<init>()
                            java.lang.String r2 = "Expected a "
                            r1.append(r2)
                            java.lang.Class r2 = r2
                            java.lang.String r2 = r2.getName()
                            r1.append(r2)
                            java.lang.String r2 = " but was "
                            r1.append(r2)
                            java.lang.Class r4 = r4.getClass()
                            java.lang.String r4 = r4.getName()
                            r1.append(r4)
                            java.lang.String r4 = r1.toString()
                            r0.<init>(r4)
                            throw r0
                        L_0x0040:
                            return r4
                        */
                        throw new UnsupportedOperationException("Method not decompiled: p110z1.TypeAdapters.C552129.C55221.mo1234b(z1.re):java.lang.Object");
                    }
                };
            }

            public String toString() {
                return "Factory[typeHierarchy=" + cls.getName() + ",adapter=" + ppVar + "]";
            }
        };
    }
}
