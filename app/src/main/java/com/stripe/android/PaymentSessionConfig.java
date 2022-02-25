package com.stripe.android;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import com.stripe.android.model.Address;
import com.stripe.android.model.ShippingInformation;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* loaded from: classes2.dex */
public class PaymentSessionConfig implements Parcelable {
    public static final Parcelable.Creator<PaymentSessionConfig> CREATOR = new Parcelable.Creator<PaymentSessionConfig>() { // from class: com.stripe.android.PaymentSessionConfig.1
        /* renamed from: a */
        public PaymentSessionConfig createFromParcel(Parcel parcel) {
            return new PaymentSessionConfig(parcel);
        }

        /* renamed from: a */
        public PaymentSessionConfig[] newArray(int i) {
            return new PaymentSessionConfig[i];
        }
    };
    @NonNull

    /* renamed from: a */
    private List<String> f11759a;
    @NonNull

    /* renamed from: b */
    private List<String> f11760b;
    @NonNull

    /* renamed from: c */
    private ShippingInformation f11761c;

    /* renamed from: d */
    private boolean f11762d;

    /* renamed from: e */
    private boolean f11763e;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    /* renamed from: com.stripe.android.PaymentSessionConfig$a */
    /* loaded from: classes2.dex */
    public static class C2362a {

        /* renamed from: a */
        private boolean f11764a = true;

        /* renamed from: b */
        private boolean f11765b = true;
        @NonNull

        /* renamed from: c */
        private List<String> f11766c;
        @NonNull

        /* renamed from: d */
        private List<String> f11767d;
        @NonNull

        /* renamed from: e */
        private ShippingInformation f11768e;

        /* renamed from: a */
        public C2362a m18101a(String... strArr) {
            this.f11766c = Arrays.asList(strArr);
            return this;
        }

        /* renamed from: b */
        public C2362a m18098b(String... strArr) {
            this.f11767d = Arrays.asList(strArr);
            return this;
        }

        /* renamed from: a */
        public C2362a m18103a(ShippingInformation shippingInformation) {
            this.f11768e = shippingInformation;
            return this;
        }

        /* renamed from: a */
        public C2362a m18102a(boolean z) {
            this.f11764a = z;
            return this;
        }

        /* renamed from: b */
        public C2362a m18099b(boolean z) {
            this.f11765b = z;
            return this;
        }

        /* renamed from: a */
        public PaymentSessionConfig m18105a() {
            return new PaymentSessionConfig(this);
        }
    }

    PaymentSessionConfig(C2362a aVar) {
        this.f11759a = aVar.f11766c;
        this.f11760b = aVar.f11767d;
        this.f11761c = aVar.f11768e;
        this.f11762d = aVar.f11764a;
        this.f11763e = aVar.f11765b;
    }

    private PaymentSessionConfig(Parcel parcel) {
        this.f11759a = new ArrayList();
        parcel.readList(this.f11759a, String.class.getClassLoader());
        this.f11760b = new ArrayList();
        parcel.readList(this.f11760b, String.class.getClassLoader());
        this.f11761c = (ShippingInformation) parcel.readParcelable(Address.class.getClassLoader());
        boolean z = false;
        this.f11762d = parcel.readInt() == 1;
        this.f11763e = parcel.readInt() == 1 ? true : z;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        PaymentSessionConfig paymentSessionConfig = (PaymentSessionConfig) obj;
        if (m18109d() == paymentSessionConfig.m18109d() && m18108e() == paymentSessionConfig.m18108e() && m18112a().equals(paymentSessionConfig.m18112a()) && m18111b().equals(paymentSessionConfig.m18111b())) {
            return m18110c().equals(paymentSessionConfig.m18110c());
        }
        return false;
    }

    public int hashCode() {
        return (((((((m18112a().hashCode() * 31) + m18111b().hashCode()) * 31) + this.f11761c.hashCode()) * 31) + (m18109d() ? 1 : 0)) * 31) + (m18108e() ? 1 : 0);
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeList(this.f11759a);
        parcel.writeList(this.f11760b);
        parcel.writeParcelable(this.f11761c, i);
        parcel.writeInt(this.f11762d ? 1 : 0);
        parcel.writeInt(this.f11763e ? 1 : 0);
    }

    @NonNull
    /* renamed from: a */
    public List<String> m18112a() {
        return this.f11759a;
    }

    @NonNull
    /* renamed from: b */
    public List<String> m18111b() {
        return this.f11760b;
    }

    @NonNull
    /* renamed from: c */
    public ShippingInformation m18110c() {
        return this.f11761c;
    }

    /* renamed from: d */
    public boolean m18109d() {
        return this.f11762d;
    }

    /* renamed from: e */
    public boolean m18108e() {
        return this.f11763e;
    }
}
