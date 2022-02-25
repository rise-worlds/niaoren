package com.lody.virtual.remote;

import android.app.PendingIntent;
import android.content.Intent;
import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes.dex */
public class IntentSenderData implements Parcelable {
    public static final Parcelable.Creator<IntentSenderData> CREATOR = new Parcelable.Creator<IntentSenderData>() { // from class: com.lody.virtual.remote.IntentSenderData.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public IntentSenderData createFromParcel(Parcel parcel) {
            return new IntentSenderData(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public IntentSenderData[] newArray(int i) {
            return new IntentSenderData[i];
        }
    };
    public String creator;
    public int flags;
    public Intent intent;
    public IBinder token;
    public int type;
    public int userId;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public IntentSenderData(String str, IBinder iBinder, Intent intent, int i, int i2, int i3) {
        this.creator = str;
        this.token = iBinder;
        this.intent = intent;
        this.flags = i;
        this.type = i2;
        this.userId = i3;
    }

    public PendingIntent getPendingIntent() {
        return readPendingIntent(this.token);
    }

    public static PendingIntent readPendingIntent(IBinder iBinder) {
        Parcel obtain = Parcel.obtain();
        obtain.writeStrongBinder(iBinder);
        obtain.setDataPosition(0);
        try {
            return PendingIntent.readPendingIntentOrNullFromParcel(obtain);
        } finally {
            obtain.recycle();
        }
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.creator);
        parcel.writeStrongBinder(this.token);
        parcel.writeParcelable(this.intent, i);
        parcel.writeInt(this.flags);
        parcel.writeInt(this.type);
        parcel.writeInt(this.userId);
    }

    protected IntentSenderData(Parcel parcel) {
        this.creator = parcel.readString();
        this.token = parcel.readStrongBinder();
        this.intent = (Intent) parcel.readParcelable(Intent.class.getClassLoader());
        this.flags = parcel.readInt();
        this.type = parcel.readInt();
        this.userId = parcel.readInt();
    }

    public void replace(IntentSenderData intentSenderData) {
        this.creator = intentSenderData.creator;
        this.token = intentSenderData.token;
        this.intent = intentSenderData.intent;
        this.flags = intentSenderData.flags;
        this.type = intentSenderData.type;
        this.userId = intentSenderData.userId;
    }
}
