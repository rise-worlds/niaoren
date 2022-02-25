package com.blankj.utilcode.util;

import android.util.Log;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: com.blankj.utilcode.util.ac */
/* loaded from: classes.dex */
public final class JsonUtils {

    /* renamed from: a */
    private static final byte f6622a = 0;

    /* renamed from: b */
    private static final byte f6623b = 1;

    /* renamed from: c */
    private static final byte f6624c = 2;

    /* renamed from: d */
    private static final byte f6625d = 3;

    /* renamed from: e */
    private static final byte f6626e = 4;

    /* renamed from: f */
    private static final byte f6627f = 5;

    /* renamed from: g */
    private static final byte f6628g = 6;

    private JsonUtils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    /* renamed from: a */
    public static boolean m23810a(JSONObject jSONObject, String str) {
        return m23802a(jSONObject, str, false);
    }

    /* renamed from: a */
    public static boolean m23802a(JSONObject jSONObject, String str, boolean z) {
        return ((Boolean) m23806a(jSONObject, str, Boolean.valueOf(z), (byte) 0)).booleanValue();
    }

    /* renamed from: a */
    public static boolean m23819a(String str, String str2) {
        return m23811a(str, str2, false);
    }

    /* renamed from: a */
    public static boolean m23811a(String str, String str2, boolean z) {
        return ((Boolean) m23815a(str, str2, Boolean.valueOf(z), (byte) 0)).booleanValue();
    }

    /* renamed from: b */
    public static int m23800b(JSONObject jSONObject, String str) {
        return m23808a(jSONObject, str, -1);
    }

    /* renamed from: a */
    public static int m23808a(JSONObject jSONObject, String str, int i) {
        return ((Integer) m23806a(jSONObject, str, Integer.valueOf(i), (byte) 1)).intValue();
    }

    /* renamed from: b */
    public static int m23801b(String str, String str2) {
        return m23817a(str, str2, -1);
    }

    /* renamed from: a */
    public static int m23817a(String str, String str2, int i) {
        return ((Integer) m23815a(str, str2, Integer.valueOf(i), (byte) 1)).intValue();
    }

    /* renamed from: c */
    public static long m23798c(JSONObject jSONObject, String str) {
        return m23807a(jSONObject, str, -1L);
    }

    /* renamed from: a */
    public static long m23807a(JSONObject jSONObject, String str, long j) {
        return ((Long) m23806a(jSONObject, str, Long.valueOf(j), (byte) 2)).longValue();
    }

    /* renamed from: c */
    public static long m23799c(String str, String str2) {
        return m23816a(str, str2, -1L);
    }

    /* renamed from: a */
    public static long m23816a(String str, String str2, long j) {
        return ((Long) m23815a(str, str2, Long.valueOf(j), (byte) 2)).longValue();
    }

    /* renamed from: d */
    public static double m23796d(JSONObject jSONObject, String str) {
        return m23809a(jSONObject, str, -1.0d);
    }

    /* renamed from: a */
    public static double m23809a(JSONObject jSONObject, String str, double d) {
        return ((Double) m23806a(jSONObject, str, Double.valueOf(d), (byte) 3)).doubleValue();
    }

    /* renamed from: d */
    public static double m23797d(String str, String str2) {
        return m23818a(str, str2, -1.0d);
    }

    /* renamed from: a */
    public static double m23818a(String str, String str2, double d) {
        return ((Double) m23815a(str, str2, Double.valueOf(d), (byte) 3)).doubleValue();
    }

    /* renamed from: e */
    public static String m23794e(JSONObject jSONObject, String str) {
        return m23805a(jSONObject, str, "");
    }

    /* renamed from: a */
    public static String m23805a(JSONObject jSONObject, String str, String str2) {
        return (String) m23806a(jSONObject, str, str2, (byte) 4);
    }

    /* renamed from: e */
    public static String m23795e(String str, String str2) {
        return m23814a(str, str2, "");
    }

    /* renamed from: a */
    public static String m23814a(String str, String str2, String str3) {
        return (String) m23815a(str, str2, str3, (byte) 4);
    }

    /* renamed from: a */
    public static JSONObject m23803a(JSONObject jSONObject, String str, JSONObject jSONObject2) {
        return (JSONObject) m23806a(jSONObject, str, jSONObject2, (byte) 5);
    }

    /* renamed from: a */
    public static JSONObject m23812a(String str, String str2, JSONObject jSONObject) {
        return (JSONObject) m23815a(str, str2, jSONObject, (byte) 5);
    }

    /* renamed from: a */
    public static JSONArray m23804a(JSONObject jSONObject, String str, JSONArray jSONArray) {
        return (JSONArray) m23806a(jSONObject, str, jSONArray, (byte) 6);
    }

    /* renamed from: a */
    public static JSONArray m23813a(String str, String str2, JSONArray jSONArray) {
        return (JSONArray) m23815a(str, str2, jSONArray, (byte) 6);
    }

    /* renamed from: a */
    private static <T> T m23806a(JSONObject jSONObject, String str, T t, byte b) {
        if (jSONObject == null || str == null || str.length() == 0) {
            return t;
        }
        try {
            if (b == 0) {
                return (T) Boolean.valueOf(jSONObject.getBoolean(str));
            }
            if (b == 1) {
                return (T) Integer.valueOf(jSONObject.getInt(str));
            }
            if (b == 2) {
                return (T) Long.valueOf(jSONObject.getLong(str));
            }
            if (b == 3) {
                return (T) Double.valueOf(jSONObject.getDouble(str));
            }
            if (b == 4) {
                return (T) jSONObject.getString(str);
            }
            if (b == 5) {
                return (T) jSONObject.getJSONObject(str);
            }
            return b == 6 ? (T) jSONObject.getJSONArray(str) : t;
        } catch (JSONException e) {
            Log.e("JsonUtils", "getValueByType: ", e);
            return t;
        }
    }

    /* renamed from: a */
    private static <T> T m23815a(String str, String str2, T t, byte b) {
        if (str == null || str.length() == 0 || str2 == null || str2.length() == 0) {
            return t;
        }
        try {
            return (T) m23806a(new JSONObject(str), str2, t, b);
        } catch (JSONException e) {
            Log.e("JsonUtils", "getValueByType: ", e);
            return t;
        }
    }

    /* renamed from: a */
    public static String m23821a(String str) {
        return m23820a(str, 4);
    }

    /* renamed from: a */
    public static String m23820a(String str, int i) {
        try {
            int length = str.length();
            for (int i2 = 0; i2 < length; i2++) {
                char charAt = str.charAt(i2);
                if (charAt == '{') {
                    return new JSONObject(str).toString(i);
                }
                if (charAt == '[') {
                    return new JSONArray(str).toString(i);
                }
                if (!Character.isWhitespace(charAt)) {
                    return str;
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return str;
    }
}
