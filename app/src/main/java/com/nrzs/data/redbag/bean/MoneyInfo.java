package com.nrzs.data.redbag.bean;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.os.Parcel;
import android.os.Parcelable;

@Entity
/* loaded from: classes2.dex */
public class MoneyInfo implements Parcelable {
    public static final Parcelable.Creator<MoneyInfo> CREATOR = new Parcelable.Creator<MoneyInfo>() { // from class: com.nrzs.data.redbag.bean.MoneyInfo.1
        /* renamed from: a */
        public MoneyInfo createFromParcel(Parcel parcel) {
            return new MoneyInfo(parcel);
        }

        /* renamed from: a */
        public MoneyInfo[] newArray(int i) {
            return new MoneyInfo[i];
        }
    };

    /* renamed from: a */
    static final long f10650a = -1;
    @PrimaryKey(autoGenerate = true)

    /* renamed from: b */
    public long f10651b;
    @ColumnInfo

    /* renamed from: c */
    public String f10652c;
    @ColumnInfo

    /* renamed from: d */
    public String f10653d;
    @ColumnInfo

    /* renamed from: e */
    public String f10654e;
    @ColumnInfo

    /* renamed from: f */
    public String f10655f;
    @ColumnInfo

    /* renamed from: g */
    public String f10656g;
    @ColumnInfo

    /* renamed from: h */
    public String f10657h;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public MoneyInfo() {
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.f10652c);
        parcel.writeString(this.f10653d);
        parcel.writeString(this.f10654e);
        parcel.writeString(this.f10655f);
        parcel.writeString(this.f10656g);
        parcel.writeString(this.f10657h);
    }

    protected MoneyInfo(Parcel parcel) {
        this.f10652c = parcel.readString();
        this.f10654e = parcel.readString();
        this.f10655f = parcel.readString();
        this.f10653d = parcel.readString();
        this.f10656g = parcel.readString();
        this.f10657h = parcel.readString();
    }
}
