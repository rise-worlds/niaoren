package com.lody.virtual.remote;

import android.content.BroadcastReceiver;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import p110z1.BroadcastReceiver;

/* loaded from: classes.dex */
public class PendingResultData implements Parcelable {
    public static final Parcelable.Creator<PendingResultData> CREATOR = new Parcelable.Creator<PendingResultData>() { // from class: com.lody.virtual.remote.PendingResultData.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public PendingResultData createFromParcel(Parcel parcel) {
            return new PendingResultData(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public PendingResultData[] newArray(int i) {
            return new PendingResultData[i];
        }
    };
    public boolean mAbortBroadcast;
    public boolean mFinished;
    public int mFlags;
    public boolean mInitialStickyHint;
    public boolean mOrderedHint;
    public int mResultCode;
    public String mResultData;
    public Bundle mResultExtras;
    public int mSendingUser;
    public IBinder mToken;
    public int mType;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public PendingResultData(BroadcastReceiver.PendingResult pendingResult) {
        if (BroadcastReceiver.C5134c.ctor != null) {
            this.mType = BroadcastReceiver.C5134c.mType.get(pendingResult);
            this.mOrderedHint = BroadcastReceiver.C5134c.mOrderedHint.get(pendingResult);
            this.mInitialStickyHint = BroadcastReceiver.C5134c.mInitialStickyHint.get(pendingResult);
            this.mToken = BroadcastReceiver.C5134c.mToken.get(pendingResult);
            this.mSendingUser = BroadcastReceiver.C5134c.mSendingUser.get(pendingResult);
            this.mFlags = BroadcastReceiver.C5134c.mFlags.get(pendingResult);
            this.mResultCode = BroadcastReceiver.C5134c.mResultCode.get(pendingResult);
            this.mResultData = BroadcastReceiver.C5134c.mResultData.get(pendingResult);
            this.mResultExtras = BroadcastReceiver.C5134c.mResultExtras.get(pendingResult);
            this.mAbortBroadcast = BroadcastReceiver.C5134c.mAbortBroadcast.get(pendingResult);
            this.mFinished = BroadcastReceiver.C5134c.mFinished.get(pendingResult);
        } else if (BroadcastReceiver.C5133b.ctor != null) {
            this.mType = BroadcastReceiver.C5133b.mType.get(pendingResult);
            this.mOrderedHint = BroadcastReceiver.C5133b.mOrderedHint.get(pendingResult);
            this.mInitialStickyHint = BroadcastReceiver.C5133b.mInitialStickyHint.get(pendingResult);
            this.mToken = BroadcastReceiver.C5133b.mToken.get(pendingResult);
            this.mSendingUser = BroadcastReceiver.C5133b.mSendingUser.get(pendingResult);
            this.mResultCode = BroadcastReceiver.C5133b.mResultCode.get(pendingResult);
            this.mResultData = BroadcastReceiver.C5133b.mResultData.get(pendingResult);
            this.mResultExtras = BroadcastReceiver.C5133b.mResultExtras.get(pendingResult);
            this.mAbortBroadcast = BroadcastReceiver.C5133b.mAbortBroadcast.get(pendingResult);
            this.mFinished = BroadcastReceiver.C5133b.mFinished.get(pendingResult);
        } else {
            this.mType = BroadcastReceiver.C5132a.mType.get(pendingResult);
            this.mOrderedHint = BroadcastReceiver.C5132a.mOrderedHint.get(pendingResult);
            this.mInitialStickyHint = BroadcastReceiver.C5132a.mInitialStickyHint.get(pendingResult);
            this.mToken = BroadcastReceiver.C5132a.mToken.get(pendingResult);
            this.mResultCode = BroadcastReceiver.C5132a.mResultCode.get(pendingResult);
            this.mResultData = BroadcastReceiver.C5132a.mResultData.get(pendingResult);
            this.mResultExtras = BroadcastReceiver.C5132a.mResultExtras.get(pendingResult);
            this.mAbortBroadcast = BroadcastReceiver.C5132a.mAbortBroadcast.get(pendingResult);
            this.mFinished = BroadcastReceiver.C5132a.mFinished.get(pendingResult);
        }
    }

    protected PendingResultData(Parcel parcel) {
        this.mType = parcel.readInt();
        boolean z = true;
        this.mOrderedHint = parcel.readByte() != 0;
        this.mInitialStickyHint = parcel.readByte() != 0;
        this.mToken = parcel.readStrongBinder();
        this.mSendingUser = parcel.readInt();
        this.mFlags = parcel.readInt();
        this.mResultCode = parcel.readInt();
        this.mResultData = parcel.readString();
        this.mResultExtras = parcel.readBundle();
        this.mAbortBroadcast = parcel.readByte() != 0;
        this.mFinished = parcel.readByte() == 0 ? false : z;
    }

    public BroadcastReceiver.PendingResult build() {
        return BroadcastReceiver.C5134c.ctor != null ? BroadcastReceiver.C5134c.ctor.newInstance(Integer.valueOf(this.mResultCode), this.mResultData, this.mResultExtras, Integer.valueOf(this.mType), Boolean.valueOf(this.mOrderedHint), Boolean.valueOf(this.mInitialStickyHint), this.mToken, Integer.valueOf(this.mSendingUser), Integer.valueOf(this.mFlags)) : BroadcastReceiver.C5133b.ctor != null ? BroadcastReceiver.C5133b.ctor.newInstance(Integer.valueOf(this.mResultCode), this.mResultData, this.mResultExtras, Integer.valueOf(this.mType), Boolean.valueOf(this.mOrderedHint), Boolean.valueOf(this.mInitialStickyHint), this.mToken, Integer.valueOf(this.mSendingUser)) : BroadcastReceiver.C5132a.ctor.newInstance(Integer.valueOf(this.mResultCode), this.mResultData, this.mResultExtras, Integer.valueOf(this.mType), Boolean.valueOf(this.mOrderedHint), Boolean.valueOf(this.mInitialStickyHint), this.mToken);
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.mType);
        parcel.writeByte(this.mOrderedHint ? (byte) 1 : (byte) 0);
        parcel.writeByte(this.mInitialStickyHint ? (byte) 1 : (byte) 0);
        parcel.writeStrongBinder(this.mToken);
        parcel.writeInt(this.mSendingUser);
        parcel.writeInt(this.mFlags);
        parcel.writeInt(this.mResultCode);
        parcel.writeString(this.mResultData);
        parcel.writeBundle(this.mResultExtras);
        parcel.writeByte(this.mAbortBroadcast ? (byte) 1 : (byte) 0);
        parcel.writeByte(this.mFinished ? (byte) 1 : (byte) 0);
    }

    public void finish() {
        try {
            build().finish();
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }
}
