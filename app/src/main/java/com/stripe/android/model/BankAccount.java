package com.stripe.android.model;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.Size;
import android.support.p003v4.media.MediaDescriptionCompat;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: com.stripe.android.model.b */
/* loaded from: classes2.dex */
public class BankAccount {

    /* renamed from: a */
    public static final String f11958a = "company";

    /* renamed from: b */
    public static final String f11959b = "individual";

    /* renamed from: c */
    private static final String f11960c = "account_holder_name";

    /* renamed from: d */
    private static final String f11961d = "account_holder_type";

    /* renamed from: e */
    private static final String f11962e = "bank_name";

    /* renamed from: f */
    private static final String f11963f = "country";

    /* renamed from: g */
    private static final String f11964g = "currency";

    /* renamed from: h */
    private static final String f11965h = "fingerprint";

    /* renamed from: i */
    private static final String f11966i = "last4";

    /* renamed from: j */
    private static final String f11967j = "routing_number";
    @Nullable

    /* renamed from: k */
    private String f11968k;
    @Nullable

    /* renamed from: l */
    private String f11969l;
    @Nullable

    /* renamed from: m */
    private String f11970m;
    @Nullable

    /* renamed from: n */
    private String f11971n;
    @Size(2)
    @Nullable

    /* renamed from: o */
    private String f11972o;
    @Size(MediaDescriptionCompat.BT_FOLDER_TYPE_ARTISTS)
    @Nullable

    /* renamed from: p */
    private String f11973p;
    @Nullable

    /* renamed from: q */
    private String f11974q;
    @Nullable

    /* renamed from: r */
    private String f11975r;
    @Nullable

    /* renamed from: s */
    private String f11976s;

    /* compiled from: BankAccount.java */
    @Retention(RetentionPolicy.SOURCE)
    /* renamed from: com.stripe.android.model.b$a */
    /* loaded from: classes2.dex */
    public @interface AbstractC2390a {
    }

    public BankAccount(@NonNull String str, @Size(2) @NonNull String str2, @Size(3) @NonNull String str3, @NonNull String str4) {
        this.f11970m = str;
        this.f11972o = str2;
        this.f11973p = str3;
        this.f11976s = str4;
    }

    public BankAccount(@Nullable String str, @Nullable String str2, @Nullable String str3, @Size(2) @Nullable String str4, @Size(3) @Nullable String str5, @Nullable String str6, @Nullable String str7, @Nullable String str8) {
        this.f11968k = str;
        this.f11969l = str2;
        this.f11971n = str3;
        this.f11972o = str4;
        this.f11973p = str5;
        this.f11974q = str6;
        this.f11975r = str7;
        this.f11976s = str8;
    }

    @Nullable
    /* renamed from: a */
    public String m17923a() {
        return this.f11970m;
    }

    @Nullable
    /* renamed from: b */
    public String m17920b() {
        return this.f11968k;
    }

    @NonNull
    /* renamed from: a */
    public BankAccount m17922a(String str) {
        this.f11968k = str;
        return this;
    }

    @Nullable
    /* renamed from: c */
    public String m17918c() {
        return this.f11969l;
    }

    @NonNull
    /* renamed from: b */
    public BankAccount m17919b(String str) {
        this.f11969l = str;
        return this;
    }

    @Nullable
    /* renamed from: d */
    public String m17916d() {
        return this.f11971n;
    }

    @Size(2)
    @Nullable
    /* renamed from: e */
    public String m17914e() {
        return this.f11972o;
    }

    @Size(MediaDescriptionCompat.BT_FOLDER_TYPE_ARTISTS)
    @Nullable
    /* renamed from: f */
    public String m17913f() {
        return this.f11973p;
    }

    @Nullable
    /* renamed from: g */
    public String m17912g() {
        return this.f11974q;
    }

    @Nullable
    /* renamed from: h */
    public String m17911h() {
        return this.f11975r;
    }

    @Nullable
    /* renamed from: i */
    public String m17910i() {
        return this.f11976s;
    }

    @Nullable
    /* renamed from: c */
    public static String m17917c(@Nullable String str) {
        if (f11958a.equals(str)) {
            return f11958a;
        }
        if (f11959b.equals(str)) {
            return f11959b;
        }
        return null;
    }

    @Nullable
    /* renamed from: d */
    public static BankAccount m17915d(@Nullable String str) {
        try {
            return m17921a(new JSONObject(str));
        } catch (JSONException unused) {
            return null;
        }
    }

    @Nullable
    /* renamed from: a */
    public static BankAccount m17921a(@Nullable JSONObject jSONObject) {
        if (jSONObject == null) {
            return null;
        }
        return new BankAccount(StripeJsonUtils.m17603e(jSONObject, f11960c), m17917c(StripeJsonUtils.m17603e(jSONObject, f11961d)), StripeJsonUtils.m17603e(jSONObject, f11962e), StripeJsonUtils.m17602f(jSONObject, "country"), StripeJsonUtils.m17601g(jSONObject, f11964g), StripeJsonUtils.m17603e(jSONObject, f11965h), StripeJsonUtils.m17603e(jSONObject, "last4"), StripeJsonUtils.m17603e(jSONObject, f11967j));
    }
}
