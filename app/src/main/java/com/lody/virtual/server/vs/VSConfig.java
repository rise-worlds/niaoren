package com.lody.virtual.server.vs;

import android.os.Parcel;
import android.os.Parcelable;

/* renamed from: com.lody.virtual.server.vs.VSConfig */
/* loaded from: classes.dex */
public class VSConfig implements Parcelable {
    public static final Parcelable.Creator<VSConfig> CREATOR = new Parcelable.Creator<VSConfig>() { // from class: com.lody.virtual.server.vs.VSConfig.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public VSConfig createFromParcel(Parcel parcel) {
            return new VSConfig(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public VSConfig[] newArray(int i) {
            return new VSConfig[i];
        }
    };
    public boolean enable;
    public String vsPath;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeByte(this.enable ? (byte) 1 : (byte) 0);
        parcel.writeString(this.vsPath);
    }

    public VSConfig() {
    }

    protected VSConfig(Parcel parcel) {
        this.enable = parcel.readByte() != 0;
        this.vsPath = parcel.readString();
    }
}
