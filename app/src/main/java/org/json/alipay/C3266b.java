package org.json.alipay;

import com.cyjh.ddy.media.p035a.ResultTypeConstant;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import p110z1.Consts;

/* renamed from: org.json.alipay.b */
/* loaded from: classes2.dex */
public class C3266b {

    /* renamed from: a */
    public static final Object f14854a = new C3267a((byte) 0);

    /* renamed from: b */
    private Map f14855b;

    /* renamed from: org.json.alipay.b$a */
    /* loaded from: classes2.dex */
    private static final class C3267a {
        private C3267a() {
        }

        /* synthetic */ C3267a(byte b) {
            this();
        }

        protected final Object clone() {
            return this;
        }

        public final boolean equals(Object obj) {
            return obj == null || obj == this;
        }

        public final String toString() {
            return "null";
        }
    }

    public C3266b() {
        this.f14855b = new HashMap();
    }

    public C3266b(String str) {
        this(new C3268c(str));
    }

    public C3266b(Map map) {
        this.f14855b = map == null ? new HashMap() : map;
    }

    public C3266b(C3268c cVar) {
        this();
        if (cVar.m14748c() == '{') {
            while (true) {
                char c = cVar.m14748c();
                if (c == 0) {
                    throw cVar.m14750a("A JSONObject text must end with '}'");
                } else if (c != '}') {
                    cVar.m14752a();
                    String obj = cVar.m14747d().toString();
                    char c2 = cVar.m14748c();
                    if (c2 == '=') {
                        if (cVar.m14749b() != '>') {
                            cVar.m14752a();
                        }
                    } else if (c2 != ':') {
                        throw cVar.m14750a("Expected a ':' after a key");
                    }
                    Object d = cVar.m14747d();
                    if (obj != null) {
                        if (d != null) {
                            m14755b(d);
                            this.f14855b.put(obj, d);
                        } else {
                            this.f14855b.remove(obj);
                        }
                        char c3 = cVar.m14748c();
                        if (c3 == ',' || c3 == ';') {
                            if (cVar.m14748c() != '}') {
                                cVar.m14752a();
                            } else {
                                return;
                            }
                        } else if (c3 != '}') {
                            throw cVar.m14750a("Expected a ',' or '}'");
                        } else {
                            return;
                        }
                    } else {
                        throw new JSONException("Null key.");
                    }
                } else {
                    return;
                }
            }
        } else {
            throw cVar.m14750a("A JSONObject text must begin with '{'");
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public static String m14757a(Object obj) {
        if (obj == null || obj.equals(null)) {
            return "null";
        }
        if (!(obj instanceof Number)) {
            return ((obj instanceof Boolean) || (obj instanceof C3266b) || (obj instanceof C3265a)) ? obj.toString() : obj instanceof Map ? new C3266b((Map) obj).toString() : obj instanceof Collection ? new C3265a((Collection) obj).toString() : obj.getClass().isArray() ? new C3265a(obj).toString() : m14753c(obj.toString());
        }
        Number number = (Number) obj;
        if (number != null) {
            m14755b(number);
            String obj2 = number.toString();
            if (obj2.indexOf(46) <= 0 || obj2.indexOf(101) >= 0 || obj2.indexOf(69) >= 0) {
                return obj2;
            }
            while (obj2.endsWith(ResultTypeConstant.f7213z)) {
                obj2 = obj2.substring(0, obj2.length() - 1);
            }
            return obj2.endsWith(Consts.f23430h) ? obj2.substring(0, obj2.length() - 1) : obj2;
        }
        throw new JSONException("Null pointer");
    }

    /* renamed from: b */
    private static void m14755b(Object obj) {
        if (obj == null) {
            return;
        }
        if (obj instanceof Double) {
            Double d = (Double) obj;
            if (d.isInfinite() || d.isNaN()) {
                throw new JSONException("JSON does not allow non-finite numbers.");
            }
        } else if (obj instanceof Float) {
            Float f = (Float) obj;
            if (f.isInfinite() || f.isNaN()) {
                throw new JSONException("JSON does not allow non-finite numbers.");
            }
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:32:0x0085, code lost:
        if (r4 == '<') goto L_0x0087;
     */
    /* renamed from: c */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.lang.String m14753c(java.lang.String r8) {
        /*
            if (r8 == 0) goto L_0x0099
            int r0 = r8.length()
            if (r0 != 0) goto L_0x000a
            goto L_0x0099
        L_0x000a:
            int r0 = r8.length()
            java.lang.StringBuffer r1 = new java.lang.StringBuffer
            int r2 = r0 + 4
            r1.<init>(r2)
            r2 = 34
            r1.append(r2)
            r3 = 0
            r4 = 0
        L_0x001c:
            if (r3 >= r0) goto L_0x0091
            char r5 = r8.charAt(r3)
            r6 = 92
            if (r5 == r2) goto L_0x0087
            r7 = 47
            if (r5 == r7) goto L_0x0083
            if (r5 == r6) goto L_0x0087
            switch(r5) {
                case 8: goto L_0x007d;
                case 9: goto L_0x007a;
                case 10: goto L_0x0077;
                default: goto L_0x002f;
            }
        L_0x002f:
            switch(r5) {
                case 12: goto L_0x0074;
                case 13: goto L_0x0071;
                default: goto L_0x0032;
            }
        L_0x0032:
            r4 = 32
            if (r5 < r4) goto L_0x0046
            r4 = 128(0x80, float:1.794E-43)
            if (r5 < r4) goto L_0x003e
            r4 = 160(0xa0, float:2.24E-43)
            if (r5 < r4) goto L_0x0046
        L_0x003e:
            r4 = 8192(0x2000, float:1.14794E-41)
            if (r5 < r4) goto L_0x008a
            r4 = 8448(0x2100, float:1.1838E-41)
            if (r5 >= r4) goto L_0x008a
        L_0x0046:
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            java.lang.String r6 = "000"
            r4.<init>(r6)
            java.lang.String r6 = java.lang.Integer.toHexString(r5)
            r4.append(r6)
            java.lang.String r4 = r4.toString()
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            java.lang.String r7 = "\\u"
            r6.<init>(r7)
            int r7 = r4.length()
            int r7 = r7 + (-4)
            java.lang.String r4 = r4.substring(r7)
            r6.append(r4)
            java.lang.String r4 = r6.toString()
            goto L_0x007f
        L_0x0071:
            java.lang.String r4 = "\\r"
            goto L_0x007f
        L_0x0074:
            java.lang.String r4 = "\\f"
            goto L_0x007f
        L_0x0077:
            java.lang.String r4 = "\\n"
            goto L_0x007f
        L_0x007a:
            java.lang.String r4 = "\\t"
            goto L_0x007f
        L_0x007d:
            java.lang.String r4 = "\\b"
        L_0x007f:
            r1.append(r4)
            goto L_0x008d
        L_0x0083:
            r7 = 60
            if (r4 != r7) goto L_0x008a
        L_0x0087:
            r1.append(r6)
        L_0x008a:
            r1.append(r5)
        L_0x008d:
            int r3 = r3 + 1
            r4 = r5
            goto L_0x001c
        L_0x0091:
            r1.append(r2)
            java.lang.String r8 = r1.toString()
            return r8
        L_0x0099:
            java.lang.String r8 = "\"\""
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: org.json.alipay.C3266b.m14753c(java.lang.String):java.lang.String");
    }

    /* renamed from: a */
    public final Object m14756a(String str) {
        Object obj = str == null ? null : this.f14855b.get(str);
        if (obj != null) {
            return obj;
        }
        throw new JSONException("JSONObject[" + m14753c(str) + "] not found.");
    }

    /* renamed from: a */
    public final Iterator m14758a() {
        return this.f14855b.keySet().iterator();
    }

    /* renamed from: b */
    public final boolean m14754b(String str) {
        return this.f14855b.containsKey(str);
    }

    public String toString() {
        try {
            Iterator a = m14758a();
            StringBuffer stringBuffer = new StringBuffer("{");
            while (a.hasNext()) {
                if (stringBuffer.length() > 1) {
                    stringBuffer.append(',');
                }
                Object next = a.next();
                stringBuffer.append(m14753c(next.toString()));
                stringBuffer.append(':');
                stringBuffer.append(m14757a(this.f14855b.get(next)));
            }
            stringBuffer.append('}');
            return stringBuffer.toString();
        } catch (Exception unused) {
            return null;
        }
    }
}
