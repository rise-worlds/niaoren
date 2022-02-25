package com.tendcloud.tenddata;

import android.text.TextUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: td */
/* loaded from: classes2.dex */
public class ShoppingCart extends JSONArray {

    /* renamed from: a */
    private static final String f13425a = "id";

    /* renamed from: b */
    private static final String f13426b = "category";

    /* renamed from: c */
    private static final String f13427c = "name";

    /* renamed from: d */
    private static final String f13428d = "unitPrice";

    /* renamed from: e */
    private static final String f13429e = "count";

    private ShoppingCart() {
    }

    public static ShoppingCart createShoppingCart() {
        return new ShoppingCart();
    }

    public synchronized ShoppingCart addItem(String str, String str2, String str3, int i, int i2) {
        try {
            JSONObject jSONObject = new JSONObject();
            if (!TextUtils.isEmpty(str)) {
                jSONObject.put("id", str);
            }
            if (!TextUtils.isEmpty(str2)) {
                jSONObject.put(f13426b, str2);
            }
            if (!TextUtils.isEmpty(str3)) {
                jSONObject.put("name", str3);
            }
            jSONObject.put(f13428d, i);
            jSONObject.put(f13429e, i2);
            put(jSONObject);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return this;
    }
}
