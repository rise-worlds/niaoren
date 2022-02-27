package com.stripe.android.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.Size;
import android.support.v4.media.MediaDescriptionCompat;
import java.util.Currency;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;

/* loaded from: classes2.dex */
public class ShippingMethod extends StripeJsonModel implements Parcelable {
    public static final Parcelable.Creator<ShippingMethod> CREATOR = new Parcelable.Creator<ShippingMethod>() { // from class: com.stripe.android.model.ShippingMethod.1
        /* renamed from: a */
        public ShippingMethod createFromParcel(Parcel parcel) {
            return new ShippingMethod(parcel);
        }

        /* renamed from: a */
        public ShippingMethod[] newArray(int i) {
            return new ShippingMethod[i];
        }
    };

    /* renamed from: a */
    private static final String f11944a = "amount";

    /* renamed from: b */
    private static final String f11945b = "currency_code";

    /* renamed from: c */
    private static final String f11946c = "detail";

    /* renamed from: d */
    private static final String f11947d = "identifier";

    /* renamed from: e */
    private static final String f11948e = "label";

    /* renamed from: f */
    private long f11949f;
    @Size(max = MediaDescriptionCompat.BT_FOLDER_TYPE_ARTISTS, min = 0)
    @NonNull

    /* renamed from: g */
    private String f11950g;
    @Nullable

    /* renamed from: h */
    private String f11951h;
    @NonNull

    /* renamed from: i */
    private String f11952i;
    @NonNull

    /* renamed from: j */
    private String f11953j;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public ShippingMethod(@NonNull String str, @NonNull String str2, long j, @Size(max = 3, min = 0) @NonNull String str3) {
        this(str, str2, null, j, str3);
    }

    public ShippingMethod(@NonNull String str, @NonNull String str2, @Nullable String str3, long j, @Size(max = 3, min = 0) @NonNull String str4) {
        this.f11953j = str;
        this.f11952i = str2;
        this.f11951h = str3;
        this.f11949f = j;
        this.f11950g = str4;
    }

    @NonNull
    /* renamed from: c */
    public Currency m17934c() {
        return Currency.getInstance(this.f11950g);
    }

    /* renamed from: d */
    public long m17933d() {
        return this.f11949f;
    }

    @NonNull
    /* renamed from: e */
    public String m17932e() {
        return this.f11953j;
    }

    @Nullable
    /* renamed from: f */
    public String m17931f() {
        return this.f11951h;
    }

    @NonNull
    /* renamed from: g */
    public String m17930g() {
        return this.f11952i;
    }

    @Override // com.stripe.android.model.StripeJsonModel
    @NonNull
    /* renamed from: a */
    public JSONObject mo17628a() {
        JSONObject jSONObject = new JSONObject();
        StripeJsonUtils.m17613a(jSONObject, f11944a, Long.valueOf(this.f11949f));
        StripeJsonUtils.m17612a(jSONObject, f11945b, this.f11950g);
        StripeJsonUtils.m17612a(jSONObject, f11946c, this.f11951h);
        StripeJsonUtils.m17612a(jSONObject, f11947d, this.f11952i);
        StripeJsonUtils.m17612a(jSONObject, f11948e, this.f11953j);
        return jSONObject;
    }

    @Override // com.stripe.android.model.StripeJsonModel
    @NonNull
    /* renamed from: b */
    public Map<String, Object> mo17623b() {
        HashMap hashMap = new HashMap();
        hashMap.put(f11944a, Long.valueOf(this.f11949f));
        hashMap.put(f11945b, this.f11950g);
        hashMap.put(f11946c, this.f11951h);
        hashMap.put(f11947d, this.f11952i);
        hashMap.put(f11948e, this.f11953j);
        return hashMap;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeLong(this.f11949f);
        parcel.writeString(this.f11950g);
        parcel.writeString(this.f11951h);
        parcel.writeString(this.f11952i);
        parcel.writeString(this.f11953j);
    }

    private ShippingMethod(Parcel parcel) {
        this.f11949f = parcel.readLong();
        this.f11950g = parcel.readString();
        this.f11951h = parcel.readString();
        this.f11952i = parcel.readString();
        this.f11953j = parcel.readString();
    }
}
