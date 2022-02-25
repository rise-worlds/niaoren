package com.stripe.android.model;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.stripe.android.RequestOptions;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: com.stripe.android.model.e */
/* loaded from: classes2.dex */
public class CustomerSource extends StripeJsonModel implements StripePaymentSource {

    /* renamed from: a */
    private StripePaymentSource f12091a;

    private CustomerSource(StripePaymentSource qVar) {
        this.f12091a = qVar;
    }

    @Nullable
    /* renamed from: c */
    public StripePaymentSource m17804c() {
        return this.f12091a;
    }

    @Override // com.stripe.android.model.StripePaymentSource
    @Nullable
    /* renamed from: A */
    public String mo17592A() {
        StripePaymentSource qVar = this.f12091a;
        if (qVar == null) {
            return null;
        }
        return qVar.mo17592A();
    }

    @Nullable
    /* renamed from: d */
    public C2395g m17803d() {
        StripePaymentSource qVar = this.f12091a;
        if (qVar instanceof C2395g) {
            return (C2395g) qVar;
        }
        return null;
    }

    @Nullable
    /* renamed from: e */
    public String m17802e() {
        C2395g d = m17803d();
        Card f = m17801f();
        if (d != null && d.m17757q().equals("card")) {
            SourceCardData hVar = (SourceCardData) d.m17758p();
            if (hVar != null) {
                return hVar.m17728n();
            }
            return null;
        } else if (f != null) {
            return f.m17905F();
        } else {
            return null;
        }
    }

    @Nullable
    /* renamed from: f */
    public Card m17801f() {
        StripePaymentSource qVar = this.f12091a;
        if (qVar instanceof Card) {
            return (Card) qVar;
        }
        return null;
    }

    @NonNull
    /* renamed from: g */
    public String m17800g() {
        StripePaymentSource qVar = this.f12091a;
        return qVar instanceof Card ? "card" : qVar instanceof C2395g ? ((C2395g) qVar).m17757q() : "unknown";
    }

    @Nullable
    /* renamed from: a */
    public static CustomerSource m17806a(@Nullable String str) {
        try {
            return m17805a(new JSONObject(str));
        } catch (JSONException unused) {
            return null;
        }
    }

    @Nullable
    /* renamed from: a */
    public static CustomerSource m17805a(@Nullable JSONObject jSONObject) {
        StripePaymentSource qVar;
        if (jSONObject == null) {
            return null;
        }
        String e = StripeJsonUtils.m17603e(jSONObject, "object");
        if ("card".equals(e)) {
            qVar = Card.m17901a(jSONObject);
        } else if (RequestOptions.f12301a.equals(e)) {
            qVar = C2395g.m17788a(jSONObject);
        } else {
            qVar = null;
        }
        if (qVar == null) {
            return null;
        }
        return new CustomerSource(qVar);
    }

    @Override // com.stripe.android.model.StripeJsonModel
    @NonNull
    /* renamed from: b */
    public Map<String, Object> mo17623b() {
        StripePaymentSource qVar = this.f12091a;
        if (qVar instanceof C2395g) {
            return ((C2395g) qVar).mo17623b();
        }
        if (qVar instanceof Card) {
            return ((Card) qVar).mo17623b();
        }
        return new HashMap();
    }

    @Override // com.stripe.android.model.StripeJsonModel
    @NonNull
    /* renamed from: a */
    public JSONObject mo17628a() {
        StripePaymentSource qVar = this.f12091a;
        if (qVar instanceof C2395g) {
            return ((C2395g) qVar).mo17628a();
        }
        if (qVar instanceof Card) {
            return ((Card) qVar).mo17628a();
        }
        return new JSONObject();
    }
}
