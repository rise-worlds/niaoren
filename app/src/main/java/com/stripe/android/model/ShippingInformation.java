package com.stripe.android.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.stripe.android.StripeNetworkUtils;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;

/* loaded from: classes2.dex */
public class ShippingInformation extends StripeJsonModel implements Parcelable {
    public static final Parcelable.Creator<ShippingInformation> CREATOR = new Parcelable.Creator<ShippingInformation>() { // from class: com.stripe.android.model.ShippingInformation.1
        /* renamed from: a */
        public ShippingInformation createFromParcel(Parcel parcel) {
            return new ShippingInformation(parcel);
        }

        /* renamed from: a */
        public ShippingInformation[] newArray(int i) {
            return new ShippingInformation[i];
        }
    };

    /* renamed from: a */
    private static final String f11938a = "address";

    /* renamed from: b */
    private static final String f11939b = "name";

    /* renamed from: c */
    private static final String f11940c = "phone";
    @Nullable

    /* renamed from: d */
    private Address f11941d;
    @Nullable

    /* renamed from: e */
    private String f11942e;
    @Nullable

    /* renamed from: f */
    private String f11943f;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public ShippingInformation() {
    }

    public ShippingInformation(@Nullable Address address, @Nullable String str, @Nullable String str2) {
        this.f11941d = address;
        this.f11942e = str;
        this.f11943f = str2;
    }

    @Nullable
    /* renamed from: c */
    public Address m17939c() {
        return this.f11941d;
    }

    @Nullable
    /* renamed from: d */
    public String m17938d() {
        return this.f11942e;
    }

    @Nullable
    /* renamed from: e */
    public String m17937e() {
        return this.f11943f;
    }

    @Nullable
    /* renamed from: a */
    public static ShippingInformation m17940a(@Nullable JSONObject jSONObject) {
        if (jSONObject == null) {
            return null;
        }
        ShippingInformation shippingInformation = new ShippingInformation();
        shippingInformation.f11942e = StripeJsonUtils.m17603e(jSONObject, "name");
        shippingInformation.f11943f = StripeJsonUtils.m17603e(jSONObject, "phone");
        shippingInformation.f11941d = Address.m17968a(jSONObject.optJSONObject(f11938a));
        return shippingInformation;
    }

    @Override // com.stripe.android.model.StripeJsonModel
    @NonNull
    /* renamed from: a */
    public JSONObject mo17628a() {
        JSONObject jSONObject = new JSONObject();
        StripeJsonUtils.m17612a(jSONObject, "name", this.f11942e);
        StripeJsonUtils.m17612a(jSONObject, "phone", this.f11943f);
        m17625a(jSONObject, f11938a, this.f11941d);
        return jSONObject;
    }

    @Override // com.stripe.android.model.StripeJsonModel
    @NonNull
    /* renamed from: b */
    public Map<String, Object> mo17623b() {
        HashMap hashMap = new HashMap();
        hashMap.put("name", this.f11942e);
        hashMap.put("phone", this.f11943f);
        m17627a(hashMap, f11938a, this.f11941d);
        StripeNetworkUtils.m17470a(hashMap);
        return hashMap;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeParcelable(this.f11941d, i);
        parcel.writeString(this.f11942e);
        parcel.writeString(this.f11943f);
    }

    protected ShippingInformation(Parcel parcel) {
        this.f11941d = (Address) parcel.readParcelable(Address.class.getClassLoader());
        this.f11942e = parcel.readString();
        this.f11943f = parcel.readString();
    }
}
