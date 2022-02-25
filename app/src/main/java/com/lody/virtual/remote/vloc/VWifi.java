package com.lody.virtual.remote.vloc;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes.dex */
public class VWifi implements Parcelable {
    public static final Parcelable.Creator<VWifi> CREATOR = new Parcelable.Creator<VWifi>() { // from class: com.lody.virtual.remote.vloc.VWifi.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public VWifi createFromParcel(Parcel parcel) {
            return new VWifi(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public VWifi[] newArray(int i) {
            return new VWifi[i];
        }
    };
    public String bssid;
    public String capabilities;
    public int frequency;
    public int level;
    public String ssid;
    public long timestamp;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.ssid);
        parcel.writeString(this.bssid);
        parcel.writeString(this.capabilities);
        parcel.writeInt(this.level);
        parcel.writeInt(this.frequency);
        parcel.writeLong(this.timestamp);
    }

    public VWifi() {
    }

    public VWifi(Parcel parcel) {
        this.ssid = parcel.readString();
        this.bssid = parcel.readString();
        this.capabilities = parcel.readString();
        this.level = parcel.readInt();
        this.frequency = parcel.readInt();
        this.timestamp = parcel.readLong();
    }
}
