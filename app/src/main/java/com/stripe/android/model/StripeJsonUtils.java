package com.stripe.android.model;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.Size;
import android.support.v4.media.MediaDescriptionCompat;
import com.stripe.android.StripeTextUtils;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: com.stripe.android.model.p */
/* loaded from: classes2.dex */
class StripeJsonUtils {

    /* renamed from: a */
    private static final String f12278a = "";

    /* renamed from: b */
    private static final String f12279b = "null";

    StripeJsonUtils() {
    }

    @Nullable
    /* renamed from: a */
    static String m17617a(@NonNull JSONObject jSONObject, @Size(min = 1) @NonNull String str) throws JSONException {
        return m17622a(jSONObject.getString(str));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Nullable
    /* renamed from: b */
    public static Boolean m17607b(@NonNull JSONObject jSONObject, @Size(min = 1) @NonNull String str) {
        if (!jSONObject.has(str)) {
            return null;
        }
        return Boolean.valueOf(jSONObject.optBoolean(str));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Nullable
    /* renamed from: c */
    public static Integer m17605c(@NonNull JSONObject jSONObject, @Size(min = 1) @NonNull String str) {
        if (!jSONObject.has(str)) {
            return null;
        }
        return Integer.valueOf(jSONObject.optInt(str));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Nullable
    /* renamed from: d */
    public static Long m17604d(@NonNull JSONObject jSONObject, @Size(min = 1) @NonNull String str) {
        if (!jSONObject.has(str)) {
            return null;
        }
        return Long.valueOf(jSONObject.optLong(str));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Nullable
    /* renamed from: e */
    public static String m17603e(@NonNull JSONObject jSONObject, @Size(min = 1) @NonNull String str) {
        return m17622a(jSONObject.optString(str));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Size(2)
    @Nullable
    /* renamed from: f */
    public static String m17602f(@NonNull JSONObject jSONObject, @Size(min = 1) @NonNull String str) {
        String a = m17622a(jSONObject.optString(str));
        if (a == null || a.length() != 2) {
            return null;
        }
        return a;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Size(MediaDescriptionCompat.BT_FOLDER_TYPE_ARTISTS)
    @Nullable
    /* renamed from: g */
    public static String m17601g(@NonNull JSONObject jSONObject, @Size(min = 1) @NonNull String str) {
        String a = m17622a(jSONObject.optString(str));
        if (a == null || a.length() != 3) {
            return null;
        }
        return a;
    }

    @Nullable
    /* renamed from: h */
    static Map<String, Object> m17600h(@NonNull JSONObject jSONObject, @Size(min = 1) @NonNull String str) {
        JSONObject optJSONObject = jSONObject.optJSONObject(str);
        if (optJSONObject == null) {
            return null;
        }
        return m17618a(optJSONObject);
    }

    @Nullable
    /* renamed from: i */
    static Map<String, String> m17599i(@NonNull JSONObject jSONObject, @Size(min = 1) @NonNull String str) {
        JSONObject optJSONObject = jSONObject.optJSONObject(str);
        if (optJSONObject == null) {
            return null;
        }
        return m17608b(optJSONObject);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Nullable
    /* renamed from: a */
    public static Map<String, Object> m17618a(@Nullable JSONObject jSONObject) {
        if (jSONObject == null) {
            return null;
        }
        HashMap hashMap = new HashMap();
        Iterator<String> keys = jSONObject.keys();
        while (keys.hasNext()) {
            String next = keys.next();
            Object opt = jSONObject.opt(next);
            if (!f12279b.equals(opt) && opt != null) {
                if (opt instanceof JSONObject) {
                    hashMap.put(next, m17618a((JSONObject) opt));
                } else if (opt instanceof JSONArray) {
                    hashMap.put(next, m17619a((JSONArray) opt));
                } else {
                    hashMap.put(next, opt);
                }
            }
        }
        return hashMap;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Nullable
    /* renamed from: b */
    public static Map<String, String> m17608b(@Nullable JSONObject jSONObject) {
        if (jSONObject == null) {
            return null;
        }
        HashMap hashMap = new HashMap();
        Iterator<String> keys = jSONObject.keys();
        while (keys.hasNext()) {
            String next = keys.next();
            Object opt = jSONObject.opt(next);
            if (!f12279b.equals(opt) && opt != null) {
                hashMap.put(next, opt.toString());
            }
        }
        return hashMap;
    }

    @Nullable
    /* renamed from: a */
    static List<Object> m17619a(@Nullable JSONArray jSONArray) {
        if (jSONArray == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < jSONArray.length(); i++) {
            try {
                Object obj = jSONArray.get(i);
                if (obj instanceof JSONArray) {
                    arrayList.add(m17619a((JSONArray) obj));
                } else if (obj instanceof JSONObject) {
                    Map<String, Object> a = m17618a((JSONObject) obj);
                    if (a != null) {
                        arrayList.add(a);
                    }
                } else if (!f12279b.equals(obj)) {
                    arrayList.add(obj);
                }
            } catch (JSONException unused) {
            }
        }
        return arrayList;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Nullable
    /* renamed from: a */
    public static JSONObject m17620a(@Nullable Map<String, ? extends Object> map) {
        if (map == null) {
            return null;
        }
        JSONObject jSONObject = new JSONObject();
        for (String str : map.keySet()) {
            Object obj = map.get(str);
            if (obj != null) {
                try {
                    if (obj instanceof Map) {
                        try {
                            jSONObject.put(str, m17620a((Map) obj));
                        } catch (ClassCastException unused) {
                        }
                    } else if (obj instanceof List) {
                        jSONObject.put(str, m17621a((List) obj));
                    } else {
                        if (!(obj instanceof Number) && !(obj instanceof Boolean)) {
                            jSONObject.put(str, obj.toString());
                        }
                        jSONObject.put(str, obj);
                    }
                } catch (JSONException unused2) {
                }
            }
        }
        return jSONObject;
    }

    @Nullable
    /* renamed from: b */
    static JSONObject m17609b(@Nullable Map<String, String> map) {
        if (map == null) {
            return null;
        }
        JSONObject jSONObject = new JSONObject();
        for (String str : map.keySet()) {
            try {
                jSONObject.put(str, map.get(str));
            } catch (JSONException unused) {
            }
        }
        return jSONObject;
    }

    @Nullable
    /* renamed from: a */
    static JSONArray m17621a(@Nullable List list) {
        if (list == null) {
            return null;
        }
        JSONArray jSONArray = new JSONArray();
        for (Object obj : list) {
            if (obj instanceof Map) {
                try {
                    jSONArray.put(m17620a((Map) obj));
                } catch (ClassCastException unused) {
                }
            } else if (obj instanceof List) {
                jSONArray.put(m17621a((List) obj));
            } else if ((obj instanceof Number) || (obj instanceof Boolean)) {
                jSONArray.put(obj);
            } else {
                jSONArray.put(obj.toString());
            }
        }
        return jSONArray;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public static void m17612a(@NonNull JSONObject jSONObject, @Size(min = 1) @NonNull String str, @Nullable String str2) {
        if (!StripeTextUtils.m17202b(str2)) {
            try {
                jSONObject.put(str, str2);
            } catch (JSONException unused) {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public static void m17614a(@NonNull JSONObject jSONObject, @Size(min = 1) @NonNull String str, @Nullable Integer num) {
        if (num != null) {
            try {
                jSONObject.put(str, num.intValue());
            } catch (JSONException unused) {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public static void m17613a(@NonNull JSONObject jSONObject, @Size(min = 1) @NonNull String str, @Nullable Long l) {
        if (l != null) {
            try {
                jSONObject.put(str, l.longValue());
            } catch (JSONException unused) {
            }
        }
    }

    /* renamed from: a */
    static void m17615a(@NonNull JSONObject jSONObject, @Size(min = 1) @NonNull String str, @Nullable Double d) {
        if (d != null) {
            try {
                jSONObject.put(str, d.doubleValue());
            } catch (JSONException unused) {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public static void m17616a(@NonNull JSONObject jSONObject, @Size(min = 1) @NonNull String str, @Nullable Boolean bool) {
        if (bool != null) {
            try {
                jSONObject.put(str, bool.booleanValue());
            } catch (JSONException unused) {
            }
        }
    }

    /* renamed from: a */
    static void m17611a(@NonNull JSONObject jSONObject, @Size(min = 1) @NonNull String str, @Nullable Map<String, String> map) {
        JSONObject b;
        if (map != null && (b = m17609b(map)) != null) {
            try {
                jSONObject.put(str, b);
            } catch (JSONException unused) {
            }
        }
    }

    /* renamed from: b */
    static void m17606b(@NonNull JSONObject jSONObject, @Size(min = 1) @NonNull String str, @Nullable Map<String, Object> map) {
        JSONObject a;
        if (map != null && (a = m17620a(map)) != null) {
            try {
                jSONObject.put(str, a);
            } catch (JSONException unused) {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public static void m17610a(@NonNull JSONObject jSONObject, @Size(min = 1) @NonNull String str, @Nullable JSONObject jSONObject2) {
        if (jSONObject2 != null) {
            try {
                jSONObject.put(str, jSONObject2);
            } catch (JSONException unused) {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Nullable
    /* renamed from: a */
    public static String m17622a(@Nullable String str) {
        if (f12279b.equals(str) || "".equals(str)) {
            return null;
        }
        return str;
    }
}
