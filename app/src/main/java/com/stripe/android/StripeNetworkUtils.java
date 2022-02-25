package com.stripe.android;

import android.content.Context;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.VisibleForTesting;
import android.text.TextUtils;
import com.stripe.android.model.BankAccount;
import com.stripe.android.model.Card;
import com.stripe.android.model.SourceCardData;
import com.stripe.android.model.Token;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;

/* renamed from: com.stripe.android.t */
/* loaded from: classes2.dex */
public class StripeNetworkUtils {

    /* renamed from: a */
    private static final String f12353a = "muid";

    /* renamed from: b */
    private static final String f12354b = "guid";

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: StripeNetworkUtils.java */
    @VisibleForTesting
    /* renamed from: com.stripe.android.t$a */
    /* loaded from: classes2.dex */
    public interface AbstractC2415a {
        /* renamed from: a */
        String m17469a();

        /* renamed from: b */
        String m17468b();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @NonNull
    /* renamed from: a */
    public static Map<String, Object> m17475a(@NonNull Context context, Card cVar) {
        return m17472a((AbstractC2415a) null, context, cVar);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @NonNull
    /* renamed from: a */
    public static Map<String, Object> m17474a(@NonNull Context context, @NonNull String str) {
        HashMap hashMap = new HashMap();
        hashMap.put("personal_id_number", str);
        HashMap hashMap2 = new HashMap();
        hashMap2.put(Token.f12285c, hashMap);
        return hashMap2;
    }

    @NonNull
    /* renamed from: a */
    private static Map<String, Object> m17472a(@Nullable AbstractC2415a aVar, @NonNull Context context, Card cVar) {
        HashMap hashMap = new HashMap();
        HashMap hashMap2 = new HashMap();
        hashMap2.put("number", StripeTextUtils.m17205a(cVar.m17886h()));
        hashMap2.put("cvc", StripeTextUtils.m17205a(cVar.m17882j()));
        hashMap2.put(SourceCardData.f12161k, cVar.m17880k());
        hashMap2.put(SourceCardData.f12162l, cVar.m17878l());
        hashMap2.put("name", StripeTextUtils.m17205a(cVar.m17876m()));
        hashMap2.put("currency", StripeTextUtils.m17205a(cVar.m17866t()));
        hashMap2.put("address_line1", StripeTextUtils.m17205a(cVar.m17874n()));
        hashMap2.put("address_line2", StripeTextUtils.m17205a(cVar.m17872o()));
        hashMap2.put("address_city", StripeTextUtils.m17205a(cVar.m17870p()));
        hashMap2.put("address_zip", StripeTextUtils.m17205a(cVar.m17869q()));
        hashMap2.put("address_state", StripeTextUtils.m17205a(cVar.m17868r()));
        hashMap2.put("address_country", StripeTextUtils.m17205a(cVar.m17867s()));
        m17470a(hashMap2);
        hashMap.put("product_usage", cVar.m17884i());
        hashMap.put("card", hashMap2);
        m17471a(aVar, context, hashMap);
        return hashMap;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @NonNull
    /* renamed from: a */
    public static Map<String, Object> m17476a(@NonNull Context context, @NonNull BankAccount bVar) {
        return m17473a((AbstractC2415a) null, context, bVar);
    }

    /* renamed from: a */
    public static void m17470a(@NonNull Map<String, Object> map) {
        Iterator it = new HashSet(map.keySet()).iterator();
        while (it.hasNext()) {
            String str = (String) it.next();
            if (map.get(str) == null) {
                map.remove(str);
            }
            if ((map.get(str) instanceof CharSequence) && TextUtils.isEmpty((CharSequence) map.get(str))) {
                map.remove(str);
            }
            if (map.get(str) instanceof Map) {
                m17470a((Map) map.get(str));
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public static void m17471a(@Nullable AbstractC2415a aVar, @NonNull Context context, @NonNull Map<String, Object> map) {
        String str;
        String str2;
        if (aVar == null) {
            str = Settings.Secure.getString(context.getContentResolver(), "android_id");
        } else {
            str = aVar.m17469a();
        }
        if (!StripeTextUtils.m17202b(str)) {
            String d = StripeTextUtils.m17200d(str);
            if (aVar == null) {
                str2 = context.getApplicationContext().getPackageName() + str;
            } else {
                str2 = aVar.m17468b() + str;
            }
            String d2 = StripeTextUtils.m17200d(str2);
            if (!StripeTextUtils.m17202b(d)) {
                map.put(f12354b, d);
            }
            if (!StripeTextUtils.m17202b(d2)) {
                map.put(f12353a, d2);
            }
        }
    }

    @NonNull
    /* renamed from: a */
    private static Map<String, Object> m17473a(@Nullable AbstractC2415a aVar, @NonNull Context context, @NonNull BankAccount bVar) {
        HashMap hashMap = new HashMap();
        HashMap hashMap2 = new HashMap();
        hashMap2.put(SourceCardData.f12158h, bVar.m17914e());
        hashMap2.put("currency", bVar.m17913f());
        hashMap2.put("account_number", bVar.m17923a());
        hashMap2.put("routing_number", StripeTextUtils.m17205a(bVar.m17910i()));
        hashMap2.put("account_holder_name", StripeTextUtils.m17205a(bVar.m17920b()));
        hashMap2.put("account_holder_type", StripeTextUtils.m17205a(bVar.m17918c()));
        m17470a(hashMap2);
        hashMap.put(Token.f12284b, hashMap2);
        m17471a(aVar, context, hashMap);
        return hashMap;
    }
}
