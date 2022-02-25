package com.lody.virtual.remote;

import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes.dex */
public class IntentSenderExtData implements Parcelable {
    public Intent fillIn;
    public int flagsMask;
    public int flagsValues;
    public Bundle options;
    public int requestCode;
    public IBinder resultTo;
    public String resultWho;
    public IBinder sender;
    public static final IntentSenderExtData EMPTY = new IntentSenderExtData(null, null, null, null, 0, null, 0, 0);
    public static final Parcelable.Creator<IntentSenderExtData> CREATOR = new Parcelable.Creator<IntentSenderExtData>() { // from class: com.lody.virtual.remote.IntentSenderExtData.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public IntentSenderExtData createFromParcel(Parcel parcel) {
            return new IntentSenderExtData(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public IntentSenderExtData[] newArray(int i) {
            return new IntentSenderExtData[i];
        }
    };

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public IntentSenderExtData(IBinder iBinder, Intent intent, IBinder iBinder2, String str, int i, Bundle bundle, int i2, int i3) {
        this.sender = iBinder;
        this.fillIn = intent;
        this.resultTo = iBinder2;
        this.resultWho = str;
        this.requestCode = i;
        this.options = bundle;
        this.flagsMask = i2;
        this.flagsValues = i3;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeStrongBinder(this.sender);
        parcel.writeParcelable(this.fillIn, i);
        parcel.writeStrongBinder(this.resultTo);
        parcel.writeString(this.resultWho);
        parcel.writeInt(this.requestCode);
        parcel.writeBundle(this.options);
        parcel.writeInt(this.flagsMask);
        parcel.writeInt(this.flagsValues);
    }

    protected IntentSenderExtData(Parcel parcel) {
        this.sender = parcel.readStrongBinder();
        this.fillIn = (Intent) parcel.readParcelable(Intent.class.getClassLoader());
        this.resultTo = parcel.readStrongBinder();
        this.resultWho = parcel.readString();
        this.requestCode = parcel.readInt();
        this.options = parcel.readBundle();
        this.flagsMask = parcel.readInt();
        this.flagsValues = parcel.readInt();
    }
}
