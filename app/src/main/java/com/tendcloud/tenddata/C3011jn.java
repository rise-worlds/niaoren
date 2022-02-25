package com.tendcloud.tenddata;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import org.apache.commons.p105io.IOUtils;
import org.apache.http.HttpHost;
import p110z1.Typography;

/* compiled from: td */
/* renamed from: com.tendcloud.tenddata.jn */
/* loaded from: classes2.dex */
public final class C3011jn {

    /* renamed from: a */
    private static final String f14331a = " ";

    /* renamed from: b */
    private static final int f14332b = 200;

    private C3011jn() {
    }

    /* renamed from: a */
    public static String m15251a(AbstractC3010jm jmVar) {
        if (jmVar == null) {
            return "";
        }
        StringBuffer stringBuffer = new StringBuffer();
        try {
            m15249a(null, jmVar, new StringBuffer(), stringBuffer);
            return stringBuffer.toString();
        } catch (Throwable th) {
            return "Error printing proto: " + th.getMessage();
        }
    }

    /* renamed from: a */
    private static void m15249a(String str, Object obj, StringBuffer stringBuffer, StringBuffer stringBuffer2) {
        Field[] fields;
        if (obj != null) {
            if (obj instanceof AbstractC3010jm) {
                int length = stringBuffer.length();
                if (str != null) {
                    stringBuffer2.append(stringBuffer);
                    stringBuffer2.append(m15250a(str));
                    stringBuffer2.append(" <\n");
                    stringBuffer.append(" ");
                }
                Class<?> cls = obj.getClass();
                for (Field field : cls.getFields()) {
                    int modifiers = field.getModifiers();
                    String name = field.getName();
                    if (!"cachedSize".equals(name) && (modifiers & 1) == 1 && (modifiers & 8) != 8 && !name.startsWith("_") && !name.endsWith("_")) {
                        Class<?> type = field.getType();
                        Object obj2 = field.get(obj);
                        if (!type.isArray()) {
                            m15249a(name, obj2, stringBuffer, stringBuffer2);
                        } else if (type.getComponentType() == Byte.TYPE) {
                            m15249a(name, obj2, stringBuffer, stringBuffer2);
                        } else {
                            int length2 = obj2 == null ? 0 : Array.getLength(obj2);
                            for (int i = 0; i < length2; i++) {
                                m15249a(name, Array.get(obj2, i), stringBuffer, stringBuffer2);
                            }
                        }
                    }
                }
                for (Method method : cls.getMethods()) {
                    String name2 = method.getName();
                    if (name2.startsWith("set")) {
                        String substring = name2.substring(3);
                        try {
                            if (((Boolean) cls.getMethod("has" + substring, new Class[0]).invoke(obj, new Object[0])).booleanValue()) {
                                m15249a(substring, cls.getMethod("get" + substring, new Class[0]).invoke(obj, new Object[0]), stringBuffer, stringBuffer2);
                            }
                        } catch (NoSuchMethodException unused) {
                        }
                    }
                }
                if (str != null) {
                    stringBuffer.setLength(length);
                    stringBuffer2.append(stringBuffer);
                    stringBuffer2.append(">\n");
                    return;
                }
                return;
            }
            String a = m15250a(str);
            stringBuffer2.append(stringBuffer);
            stringBuffer2.append(a);
            stringBuffer2.append(": ");
            if (obj instanceof String) {
                String b = m15247b((String) obj);
                stringBuffer2.append("\"");
                stringBuffer2.append(b);
                stringBuffer2.append("\"");
            } else if (obj instanceof byte[]) {
                m15248a((byte[]) obj, stringBuffer2);
            } else {
                stringBuffer2.append(obj);
            }
            stringBuffer2.append("\n");
        }
    }

    /* renamed from: a */
    private static String m15250a(String str) {
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < str.length(); i++) {
            char charAt = str.charAt(i);
            if (i == 0) {
                stringBuffer.append(Character.toLowerCase(charAt));
            } else if (Character.isUpperCase(charAt)) {
                stringBuffer.append('_');
                stringBuffer.append(Character.toLowerCase(charAt));
            } else {
                stringBuffer.append(charAt);
            }
        }
        return stringBuffer.toString();
    }

    /* renamed from: b */
    private static String m15247b(String str) {
        if (!str.startsWith(HttpHost.DEFAULT_SCHEME_NAME) && str.length() > 200) {
            str = str.substring(0, 200) + "[...]";
        }
        return m15246c(str);
    }

    /* renamed from: c */
    private static String m15246c(String str) {
        int length = str.length();
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            char charAt = str.charAt(i);
            if (charAt < ' ' || charAt > '~' || charAt == '\"' || charAt == '\'') {
                sb.append(String.format("\\u%04x", Integer.valueOf(charAt)));
            } else {
                sb.append(charAt);
            }
        }
        return sb.toString();
    }

    /* renamed from: a */
    private static void m15248a(byte[] bArr, StringBuffer stringBuffer) {
        if (bArr == null) {
            stringBuffer.append("\"\"");
            return;
        }
        stringBuffer.append(Typography.f21049a);
        for (byte b : bArr) {
            int i = b & 255;
            if (i == 92 || i == 34) {
                stringBuffer.append(IOUtils.DIR_SEPARATOR_WINDOWS);
                stringBuffer.append((char) i);
            } else if (i < 32 || i >= 127) {
                stringBuffer.append(String.format("\\%03o", Integer.valueOf(i)));
            } else {
                stringBuffer.append((char) i);
            }
        }
        stringBuffer.append(Typography.f21049a);
    }
}
