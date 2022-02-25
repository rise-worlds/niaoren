package com.cyjh.ddy.media.beaninner;

import android.os.Parcel;
import android.os.Parcelable;

/* renamed from: com.cyjh.ddy.media.beaninner.a */
/* loaded from: classes.dex */
public class MWYSdkBean implements Parcelable {

    /* renamed from: p */
    public static final Parcelable.Creator<MWYSdkBean> f7220p = new Parcelable.Creator<MWYSdkBean>() { // from class: com.cyjh.ddy.media.beaninner.MWYSdkBean$1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public MWYSdkBean createFromParcel(Parcel parcel) {
            return new MWYSdkBean(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public MWYSdkBean[] newArray(int i) {
            return new MWYSdkBean[i];
        }
    };

    /* renamed from: q */
    public static final int f7221q = 0;

    /* renamed from: r */
    public static final int f7222r = 1;

    /* renamed from: s */
    public static final int f7223s = 2;

    /* renamed from: t */
    public static final int f7224t = 3;

    /* renamed from: a */
    public String f7225a;

    /* renamed from: b */
    public String f7226b;

    /* renamed from: c */
    public String f7227c;

    /* renamed from: d */
    public String f7228d;

    /* renamed from: e */
    public String f7229e;

    /* renamed from: f */
    public String f7230f;

    /* renamed from: g */
    public long f7231g;

    /* renamed from: h */
    public int f7232h;

    /* renamed from: i */
    public String f7233i;

    /* renamed from: j */
    public boolean f7234j;

    /* renamed from: k */
    public boolean f7235k;

    /* renamed from: l */
    public int f7236l;

    /* renamed from: m */
    public int f7237m;

    /* renamed from: n */
    public String f7238n;

    /* renamed from: o */
    public boolean f7239o;

    /* renamed from: a */
    public int m21709a() {
        return 0;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public MWYSdkBean(Parcel parcel) {
        this.f7225a = parcel.readString();
        this.f7226b = parcel.readString();
        this.f7227c = parcel.readString();
        this.f7228d = parcel.readString();
        this.f7229e = parcel.readString();
        this.f7230f = parcel.readString();
        this.f7231g = parcel.readLong();
        this.f7232h = parcel.readInt();
        this.f7233i = parcel.readString();
        boolean z = true;
        this.f7234j = parcel.readByte() != 0;
        this.f7235k = parcel.readByte() != 0;
        this.f7236l = parcel.readInt();
        this.f7237m = parcel.readInt();
        this.f7238n = parcel.readString();
        this.f7239o = parcel.readByte() == 0 ? false : z;
    }

    /* renamed from: a */
    public void m21708a(Parcel parcel, int i) {
        parcel.writeString(this.f7225a);
        parcel.writeString(this.f7226b);
        parcel.writeString(this.f7227c);
        parcel.writeString(this.f7228d);
        parcel.writeString(this.f7229e);
        parcel.writeString(this.f7230f);
        parcel.writeLong(this.f7231g);
        parcel.writeInt(this.f7232h);
        parcel.writeString(this.f7233i);
        parcel.writeByte(this.f7234j ? (byte) 1 : (byte) 0);
        parcel.writeByte(this.f7235k ? (byte) 1 : (byte) 0);
        parcel.writeInt(this.f7236l);
        parcel.writeInt(this.f7237m);
        parcel.writeString(this.f7238n);
        parcel.writeByte(this.f7239o ? (byte) 1 : (byte) 0);
    }

    /* renamed from: a */
    public void m21707a(String str) {
        this.f7229e = str;
    }

    public MWYSdkBean(String str, String str2, String str3, String str4, String str5, String str6, long j, int i, String str7, boolean z, boolean z2, int i2, int i3, String str8, boolean z3) {
        this.f7225a = str;
        this.f7227c = str2;
        this.f7228d = str3;
        this.f7229e = str4;
        this.f7230f = str5;
        this.f7226b = str6;
        this.f7231g = j;
        this.f7232h = i;
        this.f7233i = str7;
        this.f7234j = z;
        this.f7235k = z2;
        this.f7238n = str8;
        this.f7237m = i2;
        this.f7236l = i3;
        this.f7239o = z3;
    }

    /* renamed from: b */
    public boolean m21706b() {
        int i = this.f7236l;
        return i == 1 || i == 3 || i == 2;
    }
}
