package com.stripe.android.model;

import android.support.annotation.NonNull;
import com.stripe.android.StripeNetworkUtils;
import java.util.HashMap;
import java.util.Map;

/* renamed from: com.stripe.android.model.a */
/* loaded from: classes2.dex */
public class AccountParams {

    /* renamed from: a */
    static final String f11954a = "legal_entity";

    /* renamed from: b */
    static final String f11955b = "tos_shown_and_accepted";

    /* renamed from: c */
    private Boolean f11956c;

    /* renamed from: d */
    private Map<String, Object> f11957d;

    /* renamed from: a */
    public static AccountParams m17924a(boolean z, Map<String, Object> map) {
        return new AccountParams().m17925a(z).m17926a(map);
    }

    /* renamed from: a */
    public AccountParams m17925a(boolean z) {
        this.f11956c = Boolean.valueOf(z);
        return this;
    }

    /* renamed from: a */
    public AccountParams m17926a(Map<String, Object> map) {
        this.f11957d = map;
        return this;
    }

    @NonNull
    /* renamed from: a */
    public Map<String, Object> m17927a() {
        HashMap hashMap = new HashMap();
        HashMap hashMap2 = new HashMap();
        hashMap2.put(f11955b, this.f11956c);
        hashMap2.put(f11954a, this.f11957d);
        hashMap.put("account", hashMap2);
        StripeNetworkUtils.m17470a(hashMap);
        return hashMap;
    }
}
