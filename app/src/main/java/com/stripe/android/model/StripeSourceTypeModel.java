package com.stripe.android.model;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: Access modifiers changed from: package-private */
/* renamed from: com.stripe.android.model.r */
/* loaded from: classes2.dex */
public abstract class StripeSourceTypeModel extends StripeJsonModel {

    /* renamed from: a */
    private static final String f12280a = "null";

    /* renamed from: r */
    Set<String> f12282r = new HashSet();

    /* renamed from: q */
    Map<String, Object> f12281q = new HashMap();

    @NonNull
    /* renamed from: o */
    public Map<String, Object> mo17593o() {
        return this.f12281q;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public void m17594a(String... strArr) {
        Collections.addAll(this.f12282r, strArr);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public void m17598a(@NonNull Map<String, Object> map) {
        this.f12281q = map;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Nullable
    /* renamed from: a */
    public static Map<String, Object> m17595a(@Nullable JSONObject jSONObject, @Nullable Set<String> set) {
        if (jSONObject == null) {
            return null;
        }
        if (set == null) {
            set = new HashSet<>();
        }
        HashMap hashMap = new HashMap();
        Iterator<String> keys = jSONObject.keys();
        while (keys.hasNext()) {
            String next = keys.next();
            Object opt = jSONObject.opt(next);
            if (!f12280a.equals(opt) && opt != null && !set.contains(next)) {
                hashMap.put(next, opt);
            }
        }
        if (hashMap.isEmpty()) {
            return null;
        }
        return hashMap;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public static void m17596a(@Nullable JSONObject jSONObject, @Nullable Map<String, Object> map) {
        if (!(jSONObject == null || map == null || map.isEmpty())) {
            for (String str : map.keySet()) {
                try {
                    if (map.get(str) != null) {
                        jSONObject.put(str, map.get(str));
                    }
                } catch (JSONException unused) {
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public static void m17597a(@Nullable Map<String, Object> map, @Nullable Map<String, Object> map2) {
        if (!(map == null || map2 == null || map2.isEmpty())) {
            for (String str : map2.keySet()) {
                map.put(str, map2.get(str));
            }
        }
    }
}
