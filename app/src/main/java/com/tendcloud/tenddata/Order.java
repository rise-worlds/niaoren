package com.tendcloud.tenddata;

import android.text.TextUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: td */
/* loaded from: classes2.dex */
public class Order extends JSONObject {

    /* renamed from: a */
    private static final String f13419a = "id";

    /* renamed from: b */
    private static final String f13420b = "category";

    /* renamed from: c */
    private static final String f13421c = "name";

    /* renamed from: d */
    private static final String f13422d = "unitPrice";

    /* renamed from: e */
    private static final String f13423e = "count";
    public static final String keyCurrencyType = "keyCurrencyType";
    public static final String keyOrderDetail = "keyOrderDetail";
    public static final String keyOrderId = "keyOrderId";
    public static final String keyTotalPrice = "keyTotalPrice";

    /* renamed from: f */
    private JSONArray f13424f = null;

    private Order(String str, int i, String str2) {
        try {
            put(keyOrderId, str);
            put(keyTotalPrice, i);
            put(keyCurrencyType, str2);
        } catch (JSONException unused) {
        }
    }

    private Order() {
    }

    public static Order createOrder(String str, int i, String str2) {
        try {
            C2811dq.iForDeveloper("createOrder called --> orderId: " + str + " ,totalPrice: " + i + " ,currencyType: " + str2);
            if (TextUtils.isEmpty(str)) {
                C2811dq.eForDeveloper("createOrder: orderId could not be null or empty");
            }
            if (TextUtils.isEmpty(str2) || str2.trim().length() != 3) {
                C2811dq.eForDeveloper("createOrder: currencyType length must be 3 ,likes CNY");
            }
        } catch (Throwable unused) {
        }
        return new Order(str, i, str2);
    }

    public synchronized Order addItem(String str, String str2, int i, int i2) {
        try {
            if (this.f13424f == null) {
                this.f13424f = new JSONArray();
                put(keyOrderDetail, this.f13424f);
            }
            JSONObject jSONObject = new JSONObject();
            if (!TextUtils.isEmpty(str)) {
                jSONObject.put(f13420b, str);
            }
            if (!TextUtils.isEmpty(str2)) {
                jSONObject.put("name", str2);
            }
            jSONObject.put(f13422d, i);
            jSONObject.put(f13423e, i2);
            this.f13424f.put(jSONObject);
        } catch (JSONException unused) {
        }
        return this;
    }

    public synchronized Order addItem(String str, String str2, String str3, int i, int i2) {
        try {
            if (this.f13424f == null) {
                this.f13424f = new JSONArray();
                put(keyOrderDetail, this.f13424f);
            }
            JSONObject jSONObject = new JSONObject();
            if (!TextUtils.isEmpty(str)) {
                jSONObject.put("id", str);
            }
            if (!TextUtils.isEmpty(str2)) {
                jSONObject.put(f13420b, str2);
            }
            if (!TextUtils.isEmpty(str3)) {
                jSONObject.put("name", str3);
            }
            jSONObject.put(f13422d, i);
            jSONObject.put(f13423e, i2);
            this.f13424f.put(jSONObject);
        } catch (JSONException unused) {
        }
        return this;
    }
}
