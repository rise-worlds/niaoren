package com.stripe.android;

import android.support.annotation.NonNull;
import android.support.annotation.VisibleForTesting;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: com.stripe.android.g */
/* loaded from: classes2.dex */
class ErrorParser {
    @VisibleForTesting

    /* renamed from: a */
    static final String f11856a = "An improperly formatted error response was found.";

    /* renamed from: b */
    private static final String f11857b = "charge";

    /* renamed from: c */
    private static final String f11858c = "code";

    /* renamed from: d */
    private static final String f11859d = "decline_code";

    /* renamed from: e */
    private static final String f11860e = "error";

    /* renamed from: f */
    private static final String f11861f = "message";

    /* renamed from: g */
    private static final String f11862g = "param";

    /* renamed from: h */
    private static final String f11863h = "type";

    ErrorParser() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @NonNull
    /* renamed from: a */
    public static C2380a m18008a(String str) {
        C2380a aVar = new C2380a();
        try {
            JSONObject jSONObject = new JSONObject(str).getJSONObject("error");
            aVar.f11869f = jSONObject.optString(f11857b);
            aVar.f11866c = jSONObject.optString(f11858c);
            aVar.f11868e = jSONObject.optString(f11859d);
            aVar.f11865b = jSONObject.optString(f11861f);
            aVar.f11867d = jSONObject.optString(f11862g);
            aVar.f11864a = jSONObject.optString("type");
        } catch (JSONException unused) {
            aVar.f11865b = f11856a;
        }
        return aVar;
    }

    /* compiled from: ErrorParser.java */
    /* renamed from: com.stripe.android.g$a */
    /* loaded from: classes2.dex */
    static class C2380a {

        /* renamed from: a */
        public String f11864a;

        /* renamed from: b */
        public String f11865b;

        /* renamed from: c */
        public String f11866c;

        /* renamed from: d */
        public String f11867d;

        /* renamed from: e */
        public String f11868e;

        /* renamed from: f */
        public String f11869f;

        C2380a() {
        }
    }
}
