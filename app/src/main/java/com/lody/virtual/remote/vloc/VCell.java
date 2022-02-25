package com.lody.virtual.remote.vloc;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes.dex */
public class VCell implements Parcelable {
    public static final Parcelable.Creator<VCell> CREATOR = new Parcelable.Creator<VCell>() { // from class: com.lody.virtual.remote.vloc.VCell.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public VCell createFromParcel(Parcel parcel) {
            return new VCell(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public VCell[] newArray(int i) {
            return new VCell[i];
        }
    };
    public int baseStationId;
    public int cid;
    public int lac;
    public int mcc;
    public int mnc;
    public int networkId;
    public int psc;
    public int systemId;
    public int type;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.type);
        parcel.writeInt(this.mcc);
        parcel.writeInt(this.mnc);
        parcel.writeInt(this.psc);
        parcel.writeInt(this.lac);
        parcel.writeInt(this.cid);
        parcel.writeInt(this.baseStationId);
        parcel.writeInt(this.systemId);
        parcel.writeInt(this.networkId);
    }

    public VCell() {
    }

    public VCell(Parcel parcel) {
        this.type = parcel.readInt();
        this.mcc = parcel.readInt();
        this.mnc = parcel.readInt();
        this.psc = parcel.readInt();
        this.lac = parcel.readInt();
        this.cid = parcel.readInt();
        this.baseStationId = parcel.readInt();
        this.systemId = parcel.readInt();
        this.networkId = parcel.readInt();
    }
}
