package com.stripe.android;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.VisibleForTesting;
import com.stripe.android.model.StripeJsonModel;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public class EphemeralKey extends StripeJsonModel implements Parcelable {
    public static final Parcelable.Creator<EphemeralKey> CREATOR = new Parcelable.Creator<EphemeralKey>() { // from class: com.stripe.android.EphemeralKey.1
        /* renamed from: a */
        public EphemeralKey createFromParcel(Parcel parcel) {
            return new EphemeralKey(parcel);
        }

        /* renamed from: a */
        public EphemeralKey[] newArray(int i) {
            return new EphemeralKey[i];
        }
    };

    /* renamed from: a */
    static final String f11742a = "created";

    /* renamed from: b */
    static final String f11743b = "expires";

    /* renamed from: c */
    static final String f11744c = "secret";

    /* renamed from: d */
    static final String f11745d = "livemode";

    /* renamed from: e */
    static final String f11746e = "object";

    /* renamed from: f */
    static final String f11747f = "id";

    /* renamed from: g */
    static final String f11748g = "associated_objects";

    /* renamed from: h */
    static final String f11749h = "type";

    /* renamed from: i */
    static final String f11750i = "null";

    /* renamed from: j */
    private long f11751j;
    @NonNull

    /* renamed from: k */
    private String f11752k;

    /* renamed from: l */
    private long f11753l;
    @NonNull

    /* renamed from: m */
    private String f11754m;

    /* renamed from: n */
    private boolean f11755n;
    @NonNull

    /* renamed from: o */
    private String f11756o;
    @NonNull

    /* renamed from: p */
    private String f11757p;
    @NonNull

    /* renamed from: q */
    private String f11758q;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    private EphemeralKey(Parcel parcel) {
        this.f11751j = parcel.readLong();
        this.f11752k = parcel.readString();
        this.f11753l = parcel.readLong();
        this.f11754m = parcel.readString();
        this.f11755n = parcel.readInt() != 1 ? false : true;
        this.f11756o = parcel.readString();
        this.f11757p = parcel.readString();
        this.f11758q = parcel.readString();
    }

    private EphemeralKey(long j, @NonNull String str, long j2, @NonNull String str2, boolean z, @NonNull String str3, @NonNull String str4, @NonNull String str5) {
        this.f11751j = j;
        this.f11752k = str;
        this.f11753l = j2;
        this.f11754m = str2;
        this.f11755n = z;
        this.f11756o = str3;
        this.f11757p = str4;
        this.f11758q = str5;
    }

    @Override // com.stripe.android.model.StripeJsonModel
    @NonNull
    /* renamed from: a */
    public JSONObject mo17628a() {
        JSONObject jSONObject = new JSONObject();
        JSONArray jSONArray = new JSONArray();
        JSONObject jSONObject2 = new JSONObject();
        try {
            jSONObject.put(f11742a, this.f11751j);
            jSONObject.put("expires", this.f11753l);
            jSONObject.put(f11746e, this.f11756o);
            jSONObject.put("id", this.f11754m);
            jSONObject.put(f11744c, this.f11757p);
            jSONObject.put(f11745d, this.f11755n);
            jSONObject2.put("type", this.f11758q);
            jSONObject2.put("id", this.f11752k);
            jSONArray.put(jSONObject2);
            jSONObject.put(f11748g, jSONArray);
            return jSONObject;
        } catch (JSONException unused) {
            throw new IllegalArgumentException("JSONObject creation exception thrown.");
        }
    }

    @Override // com.stripe.android.model.StripeJsonModel
    @NonNull
    /* renamed from: b */
    public Map<String, Object> mo17623b() {
        HashMap hashMap = new HashMap();
        hashMap.put(f11742a, Long.valueOf(this.f11751j));
        hashMap.put("expires", Long.valueOf(this.f11753l));
        hashMap.put(f11746e, this.f11756o);
        hashMap.put("id", this.f11754m);
        hashMap.put(f11744c, this.f11757p);
        hashMap.put(f11745d, Boolean.valueOf(this.f11755n));
        ArrayList arrayList = new ArrayList();
        HashMap hashMap2 = new HashMap();
        hashMap2.put("id", this.f11752k);
        hashMap2.put("type", this.f11758q);
        arrayList.add(hashMap2);
        hashMap.put(f11748g, arrayList);
        return hashMap;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeLong(this.f11751j);
        parcel.writeString(this.f11752k);
        parcel.writeLong(this.f11753l);
        parcel.writeString(this.f11754m);
        parcel.writeInt(this.f11755n ? 1 : 0);
        parcel.writeString(this.f11756o);
        parcel.writeString(this.f11757p);
        parcel.writeString(this.f11758q);
    }

    /* renamed from: c */
    long m18122c() {
        return this.f11751j;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @NonNull
    /* renamed from: d */
    public String m18121d() {
        return this.f11752k;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: e */
    public long m18120e() {
        return this.f11753l;
    }

    @VisibleForTesting
    /* renamed from: a */
    void m18125a(long j) {
        this.f11753l = j;
    }

    @NonNull
    /* renamed from: f */
    String m18119f() {
        return this.f11754m;
    }

    /* renamed from: g */
    boolean m18118g() {
        return this.f11755n;
    }

    @NonNull
    /* renamed from: h */
    String m18117h() {
        return this.f11756o;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @NonNull
    /* renamed from: i */
    public String m18116i() {
        return this.f11757p;
    }

    @NonNull
    /* renamed from: j */
    String m18115j() {
        return this.f11758q;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Nullable
    /* renamed from: a */
    public static EphemeralKey m18124a(@Nullable String str) {
        if (str == null) {
            return null;
        }
        try {
            return m18123a(new JSONObject(str));
        } catch (JSONException unused) {
            return null;
        }
    }

    @Nullable
    /* renamed from: a */
    static EphemeralKey m18123a(@Nullable JSONObject jSONObject) {
        if (jSONObject == null) {
            return null;
        }
        try {
            long j = jSONObject.getLong(f11742a);
            long j2 = jSONObject.getLong("expires");
            String string = jSONObject.getString("id");
            boolean z = jSONObject.getBoolean(f11745d);
            String string2 = jSONObject.getString(f11746e);
            String string3 = jSONObject.getString(f11744c);
            JSONObject jSONObject2 = jSONObject.getJSONArray(f11748g).getJSONObject(0);
            return new EphemeralKey(j, jSONObject2.getString("id"), j2, string, z, string2, string3, jSONObject2.getString("type"));
        } catch (JSONException unused) {
            return null;
        }
    }
}
