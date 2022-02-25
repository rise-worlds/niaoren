package com.stripe.android.model;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.Size;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: com.stripe.android.model.o */
/* loaded from: classes2.dex */
public abstract class StripeJsonModel {
    @NonNull
    /* renamed from: a */
    public abstract JSONObject mo17628a();

    @NonNull
    /* renamed from: b */
    public abstract Map<String, Object> mo17623b();

    public String toString() {
        return mo17628a().toString();
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof StripeJsonModel)) {
            return false;
        }
        return toString().equals(((StripeJsonModel) obj).toString());
    }

    public int hashCode() {
        return toString().hashCode();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public static void m17627a(@NonNull Map<String, Object> map, @Size(min = 1) @NonNull String str, @Nullable StripeJsonModel oVar) {
        if (oVar != null) {
            map.put(str, oVar.mo17623b());
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public static void m17625a(@NonNull JSONObject jSONObject, @Size(min = 1) @NonNull String str, @Nullable StripeJsonModel oVar) {
        if (oVar != null) {
            try {
                jSONObject.put(str, oVar.mo17628a());
            } catch (JSONException unused) {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public static void m17626a(@NonNull Map<String, Object> map, @Size(min = 1) @NonNull String str, @Nullable List<? extends StripeJsonModel> list) {
        if (list != null) {
            ArrayList arrayList = new ArrayList();
            for (int i = 0; i < list.size(); i++) {
                arrayList.add(((StripeJsonModel) list.get(i)).mo17623b());
            }
            map.put(str, arrayList);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public static void m17624a(@NonNull JSONObject jSONObject, @Size(min = 1) @NonNull String str, @Nullable List<? extends StripeJsonModel> list) {
        if (list != null) {
            try {
                JSONArray jSONArray = new JSONArray();
                for (StripeJsonModel oVar : list) {
                    jSONArray.put(oVar.mo17628a());
                }
                jSONObject.put(str, jSONArray);
            } catch (JSONException unused) {
            }
        }
    }
}
