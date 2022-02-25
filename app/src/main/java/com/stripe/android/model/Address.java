package com.stripe.android.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes2.dex */
public class Address extends StripeJsonModel implements Parcelable {
    public static final Parcelable.Creator<Address> CREATOR = new Parcelable.Creator<Address>() { // from class: com.stripe.android.model.Address.1
        /* renamed from: a */
        public Address createFromParcel(Parcel parcel) {
            return new Address(parcel);
        }

        /* renamed from: a */
        public Address[] newArray(int i) {
            return new Address[i];
        }
    };

    /* renamed from: a */
    private static final String f11917a = "city";

    /* renamed from: b */
    private static final String f11918b = "country";

    /* renamed from: c */
    private static final String f11919c = "line1";

    /* renamed from: d */
    private static final String f11920d = "line2";

    /* renamed from: e */
    private static final String f11921e = "postal_code";

    /* renamed from: f */
    private static final String f11922f = "state";
    @Nullable

    /* renamed from: g */
    private String f11923g;
    @Nullable

    /* renamed from: h */
    private String f11924h;
    @Nullable

    /* renamed from: i */
    private String f11925i;
    @Nullable

    /* renamed from: j */
    private String f11926j;
    @Nullable

    /* renamed from: k */
    private String f11927k;
    @Nullable

    /* renamed from: l */
    private String f11928l;

    @Retention(RetentionPolicy.SOURCE)
    /* renamed from: com.stripe.android.model.Address$b */
    /* loaded from: classes2.dex */
    public @interface AbstractC2387b {

        /* renamed from: a */
        public static final int f11935a = 0;

        /* renamed from: b */
        public static final int f11936b = 1;

        /* renamed from: c */
        public static final int f11937c = 2;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    Address(String str, String str2, String str3, String str4, String str5, String str6) {
        this.f11923g = str;
        this.f11924h = str2;
        this.f11925i = str3;
        this.f11926j = str4;
        this.f11927k = str5;
        this.f11928l = str6;
    }

    Address(C2386a aVar) {
        this.f11923g = aVar.f11929a;
        this.f11924h = aVar.f11930b;
        this.f11925i = aVar.f11931c;
        this.f11926j = aVar.f11932d;
        this.f11927k = aVar.f11933e;
        this.f11928l = aVar.f11934f;
    }

    @Nullable
    /* renamed from: c */
    public String m17966c() {
        return this.f11923g;
    }

    @Deprecated
    /* renamed from: a */
    public void m17969a(String str) {
        this.f11923g = str;
    }

    @Nullable
    /* renamed from: d */
    public String m17964d() {
        return this.f11924h;
    }

    @Deprecated
    /* renamed from: b */
    public void m17967b(String str) {
        this.f11924h = str;
    }

    @Nullable
    /* renamed from: e */
    public String m17962e() {
        return this.f11925i;
    }

    @Deprecated
    /* renamed from: c */
    public void m17965c(String str) {
        this.f11925i = str;
    }

    @Nullable
    /* renamed from: f */
    public String m17960f() {
        return this.f11926j;
    }

    @Deprecated
    /* renamed from: d */
    public void m17963d(String str) {
        this.f11926j = str;
    }

    @Nullable
    /* renamed from: g */
    public String m17958g() {
        return this.f11927k;
    }

    @Deprecated
    /* renamed from: e */
    public void m17961e(String str) {
        this.f11927k = str;
    }

    @Nullable
    /* renamed from: h */
    public String m17956h() {
        return this.f11928l;
    }

    @Deprecated
    /* renamed from: f */
    public void m17959f(String str) {
        this.f11928l = str;
    }

    @Override // com.stripe.android.model.StripeJsonModel
    @NonNull
    /* renamed from: b */
    public Map<String, Object> mo17623b() {
        HashMap hashMap = new HashMap();
        hashMap.put("city", this.f11923g);
        hashMap.put("country", this.f11924h);
        hashMap.put(f11919c, this.f11925i);
        hashMap.put(f11920d, this.f11926j);
        hashMap.put("postal_code", this.f11927k);
        hashMap.put("state", this.f11928l);
        return hashMap;
    }

    @Override // com.stripe.android.model.StripeJsonModel
    @NonNull
    /* renamed from: a */
    public JSONObject mo17628a() {
        JSONObject jSONObject = new JSONObject();
        StripeJsonUtils.m17612a(jSONObject, "city", this.f11923g);
        StripeJsonUtils.m17612a(jSONObject, "country", this.f11924h);
        StripeJsonUtils.m17612a(jSONObject, f11919c, this.f11925i);
        StripeJsonUtils.m17612a(jSONObject, f11920d, this.f11926j);
        StripeJsonUtils.m17612a(jSONObject, "postal_code", this.f11927k);
        StripeJsonUtils.m17612a(jSONObject, "state", this.f11928l);
        return jSONObject;
    }

    @Nullable
    /* renamed from: g */
    public static Address m17957g(@Nullable String str) {
        try {
            return m17968a(new JSONObject(str));
        } catch (JSONException unused) {
            return null;
        }
    }

    @Nullable
    /* renamed from: a */
    public static Address m17968a(@Nullable JSONObject jSONObject) {
        if (jSONObject == null) {
            return null;
        }
        return new Address(StripeJsonUtils.m17603e(jSONObject, "city"), StripeJsonUtils.m17603e(jSONObject, "country"), StripeJsonUtils.m17603e(jSONObject, f11919c), StripeJsonUtils.m17603e(jSONObject, f11920d), StripeJsonUtils.m17603e(jSONObject, "postal_code"), StripeJsonUtils.m17603e(jSONObject, "state"));
    }

    /* renamed from: com.stripe.android.model.Address$a */
    /* loaded from: classes2.dex */
    public static class C2386a {

        /* renamed from: a */
        private String f11929a;

        /* renamed from: b */
        private String f11930b;

        /* renamed from: c */
        private String f11931c;

        /* renamed from: d */
        private String f11932d;

        /* renamed from: e */
        private String f11933e;

        /* renamed from: f */
        private String f11934f;

        /* renamed from: a */
        public C2386a m17951a(String str) {
            this.f11929a = str;
            return this;
        }

        /* renamed from: b */
        public C2386a m17949b(@NonNull String str) {
            this.f11930b = str.toUpperCase();
            return this;
        }

        /* renamed from: c */
        public C2386a m17947c(String str) {
            this.f11931c = str;
            return this;
        }

        /* renamed from: d */
        public C2386a m17945d(String str) {
            this.f11932d = str;
            return this;
        }

        /* renamed from: e */
        public C2386a m17943e(String str) {
            this.f11933e = str;
            return this;
        }

        /* renamed from: f */
        public C2386a m17941f(String str) {
            this.f11934f = str;
            return this;
        }

        /* renamed from: a */
        public Address m17953a() {
            return new Address(this);
        }
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.f11923g);
        parcel.writeString(this.f11924h);
        parcel.writeString(this.f11925i);
        parcel.writeString(this.f11926j);
        parcel.writeString(this.f11927k);
        parcel.writeString(this.f11928l);
    }

    protected Address(Parcel parcel) {
        this.f11923g = parcel.readString();
        this.f11924h = parcel.readString();
        this.f11925i = parcel.readString();
        this.f11926j = parcel.readString();
        this.f11927k = parcel.readString();
        this.f11928l = parcel.readString();
    }
}
