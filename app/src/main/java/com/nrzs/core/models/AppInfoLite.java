package com.nrzs.core.models;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes2.dex */
public class AppInfoLite implements Parcelable {
    public static final Parcelable.Creator<AppInfoLite> CREATOR = new Parcelable.Creator<AppInfoLite>() { // from class: com.nrzs.core.models.AppInfoLite.1
        /* renamed from: a */
        public AppInfoLite createFromParcel(Parcel parcel) {
            return new AppInfoLite(parcel);
        }

        /* renamed from: a */
        public AppInfoLite[] newArray(int i) {
            return new AppInfoLite[i];
        }
    };

    /* renamed from: a */
    public static final int f10530a = 1;

    /* renamed from: b */
    public static final int f10531b = 2;

    /* renamed from: c */
    public static final int f10532c = 3;

    /* renamed from: d */
    public String f10533d;

    /* renamed from: e */
    public String f10534e;

    /* renamed from: f */
    public boolean f10535f;

    /* renamed from: g */
    public GameInfo f10536g;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public AppInfoLite(String str, String str2, boolean z, GameInfo gameInfo) {
        this.f10533d = str;
        this.f10534e = str2;
        this.f10535f = z;
        this.f10536g = gameInfo;
    }

    public AppInfoLite(AppInfo bVar) {
        this(bVar.f10550a, bVar.f10551b, bVar.f10552c, bVar.f10556g);
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.f10533d);
        parcel.writeString(this.f10534e);
        parcel.writeByte(this.f10535f ? (byte) 1 : (byte) 0);
        parcel.writeParcelable(this.f10536g, i);
    }

    protected AppInfoLite(Parcel parcel) {
        this.f10533d = parcel.readString();
        this.f10534e = parcel.readString();
        this.f10535f = parcel.readByte() != 0;
        this.f10536g = (GameInfo) parcel.readParcelable(GameInfo.class.getClassLoader());
    }
}
