package com.stripe.android;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.stripe.android.model.ShippingInformation;
import com.stripe.android.model.ShippingMethod;

/* loaded from: classes2.dex */
public class PaymentSessionData implements Parcelable {
    public static final Parcelable.Creator<PaymentSessionData> CREATOR = new Parcelable.Creator<PaymentSessionData>() { // from class: com.stripe.android.PaymentSessionData.1
        /* renamed from: a */
        public PaymentSessionData createFromParcel(Parcel parcel) {
            return new PaymentSessionData(parcel);
        }

        /* renamed from: a */
        public PaymentSessionData[] newArray(int i) {
            return new PaymentSessionData[i];
        }
    };

    /* renamed from: a */
    private static final String f11769a = "NO_PAYMENT";

    /* renamed from: b */
    private long f11770b;

    /* renamed from: c */
    private boolean f11771c;
    @NonNull

    /* renamed from: d */
    private String f11772d;

    /* renamed from: e */
    private long f11773e;
    @NonNull

    /* renamed from: f */
    private String f11774f;
    @Nullable

    /* renamed from: g */
    private ShippingInformation f11775g;
    @Nullable

    /* renamed from: h */
    private ShippingMethod f11776h;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public PaymentSessionData() {
        this.f11770b = 0L;
        this.f11772d = f11769a;
        this.f11773e = 0L;
        this.f11774f = PaymentResultListener.f11904d;
    }

    @Nullable
    /* renamed from: a */
    public String m18094a() {
        if (this.f11772d.equals(f11769a)) {
            return null;
        }
        return this.f11772d;
    }

    /* renamed from: b */
    public long m18088b() {
        return this.f11770b;
    }

    @NonNull
    /* renamed from: c */
    public String m18085c() {
        return this.f11774f;
    }

    /* renamed from: d */
    public boolean m18084d() {
        return this.f11771c;
    }

    /* renamed from: a */
    public void m18089a(boolean z) {
        this.f11771c = z;
    }

    /* renamed from: e */
    public long m18083e() {
        return this.f11773e;
    }

    @Nullable
    /* renamed from: f */
    public ShippingInformation m18082f() {
        return this.f11775g;
    }

    /* renamed from: a */
    public void m18092a(@Nullable ShippingInformation shippingInformation) {
        this.f11775g = shippingInformation;
    }

    @Nullable
    /* renamed from: g */
    public ShippingMethod m18081g() {
        return this.f11776h;
    }

    /* renamed from: a */
    public void m18091a(@Nullable ShippingMethod shippingMethod) {
        this.f11776h = shippingMethod;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public void m18093a(long j) {
        this.f11770b = j;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public void m18090a(@NonNull String str) {
        this.f11774f = str;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: b */
    public void m18086b(@Nullable String str) {
        if (str == null) {
            str = f11769a;
        }
        this.f11772d = str;
    }

    /* renamed from: b */
    void m18087b(long j) {
        this.f11773e = j;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        PaymentSessionData paymentSessionData = (PaymentSessionData) obj;
        if (this.f11770b != paymentSessionData.f11770b || this.f11771c != paymentSessionData.f11771c || this.f11773e != paymentSessionData.f11773e || !this.f11772d.equals(paymentSessionData.f11772d) || !this.f11774f.equals(paymentSessionData.f11774f)) {
            return false;
        }
        ShippingInformation shippingInformation = this.f11775g;
        if (shippingInformation == null ? paymentSessionData.f11775g != null : !shippingInformation.equals(paymentSessionData.f11775g)) {
            return false;
        }
        ShippingMethod shippingMethod = this.f11776h;
        if (shippingMethod != null) {
            return shippingMethod.equals(paymentSessionData.f11776h);
        }
        return paymentSessionData.f11776h == null;
    }

    public int hashCode() {
        long j = this.f11770b;
        long j2 = this.f11773e;
        int hashCode = ((((((((((int) (j ^ (j >>> 32))) * 31) + (this.f11771c ? 1 : 0)) * 31) + this.f11772d.hashCode()) * 31) + ((int) ((j2 >>> 32) ^ j2))) * 31) + this.f11774f.hashCode()) * 31;
        ShippingInformation shippingInformation = this.f11775g;
        int i = 0;
        int hashCode2 = (hashCode + (shippingInformation != null ? shippingInformation.hashCode() : 0)) * 31;
        ShippingMethod shippingMethod = this.f11776h;
        if (shippingMethod != null) {
            i = shippingMethod.hashCode();
        }
        return hashCode2 + i;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeLong(this.f11770b);
        parcel.writeInt(this.f11771c ? 1 : 0);
        parcel.writeString(this.f11774f);
        parcel.writeString(this.f11772d);
        parcel.writeParcelable(this.f11775g, i);
        parcel.writeParcelable(this.f11776h, i);
        parcel.writeLong(this.f11773e);
    }

    private PaymentSessionData(Parcel parcel) {
        this.f11770b = 0L;
        this.f11772d = f11769a;
        this.f11773e = 0L;
        this.f11774f = PaymentResultListener.f11904d;
        this.f11770b = parcel.readLong();
        this.f11771c = parcel.readInt() != 1 ? false : true;
        this.f11774f = PaymentSessionUtils.m17582a(parcel.readString());
        this.f11772d = parcel.readString();
        this.f11775g = (ShippingInformation) parcel.readParcelable(ShippingInformation.class.getClassLoader());
        this.f11776h = (ShippingMethod) parcel.readParcelable(ShippingMethod.class.getClassLoader());
        this.f11773e = parcel.readLong();
    }
}
