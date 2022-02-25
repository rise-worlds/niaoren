package com.stripe.android;

import android.support.annotation.Nullable;
import java.util.List;
import java.util.Map;

/* renamed from: com.stripe.android.u */
/* loaded from: classes2.dex */
class StripeResponse {

    /* renamed from: a */
    private String f12355a;

    /* renamed from: b */
    private int f12356b;

    /* renamed from: c */
    private Map<String, List<String>> f12357c;

    /* JADX INFO: Access modifiers changed from: package-private */
    public StripeResponse(int i, String str, @Nullable Map<String, List<String>> map) {
        this.f12356b = i;
        this.f12355a = str;
        this.f12357c = map;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public int m17467a() {
        return this.f12356b;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: b */
    public String m17466b() {
        return this.f12355a;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Nullable
    /* renamed from: c */
    public Map<String, List<String>> m17465c() {
        return this.f12357c;
    }
}
