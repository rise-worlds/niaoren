package com.nrzs.core.models;

import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes2.dex */
public class GameInfo implements Parcelable, Cloneable {
    public static final Parcelable.Creator<GameInfo> CREATOR = new Parcelable.Creator<GameInfo>() { // from class: com.nrzs.core.models.GameInfo.1
        /* renamed from: a */
        public GameInfo createFromParcel(Parcel parcel) {
            return new GameInfo(parcel);
        }

        /* renamed from: a */
        public GameInfo[] newArray(int i) {
            return new GameInfo[i];
        }
    };

    /* renamed from: a */
    public long f10537a;

    /* renamed from: b */
    public String f10538b;

    /* renamed from: c */
    public String f10539c;

    /* renamed from: d */
    public String f10540d;

    /* renamed from: e */
    public String f10541e;

    /* renamed from: f */
    public String f10542f;

    /* renamed from: g */
    public String f10543g;

    /* renamed from: h */
    public String f10544h;

    /* renamed from: i */
    public String f10545i;

    /* renamed from: j */
    public String f10546j;

    /* renamed from: k */
    public int f10547k;

    /* renamed from: l */
    public Drawable f10548l;

    /* renamed from: m */
    public boolean f10549m;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeLong(this.f10537a);
        parcel.writeString(this.f10538b);
        parcel.writeString(this.f10539c);
        parcel.writeString(this.f10540d);
        parcel.writeString(this.f10541e);
        parcel.writeString(this.f10542f);
        parcel.writeString(this.f10543g);
        parcel.writeString(this.f10544h);
        parcel.writeString(this.f10545i);
        parcel.writeString(this.f10546j);
        parcel.writeInt(this.f10547k);
    }

    public GameInfo() {
    }

    protected GameInfo(Parcel parcel) {
        this.f10537a = parcel.readLong();
        this.f10538b = parcel.readString();
        this.f10539c = parcel.readString();
        this.f10540d = parcel.readString();
        this.f10541e = parcel.readString();
        this.f10542f = parcel.readString();
        this.f10543g = parcel.readString();
        this.f10544h = parcel.readString();
        this.f10545i = parcel.readString();
        this.f10546j = parcel.readString();
        this.f10547k = parcel.readInt();
    }
}
