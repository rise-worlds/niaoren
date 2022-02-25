package com.cyjh.ddy.media.bean;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes.dex */
public class CommandResponseInfo implements Parcelable {
    public static final Parcelable.Creator<CommandResponseInfo> CREATOR = new Parcelable.Creator<CommandResponseInfo>() { // from class: com.cyjh.ddy.media.bean.CommandResponseInfo.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public CommandResponseInfo createFromParcel(Parcel parcel) {
            return new CommandResponseInfo(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public CommandResponseInfo[] newArray(int i) {
            return new CommandResponseInfo[i];
        }
    };
    public int code;
    public String command;
    public String data;
    public String state;
    public long time;
    public String version;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeLong(this.time);
        parcel.writeString(this.command);
        parcel.writeString(this.data);
        parcel.writeString(this.state);
        parcel.writeString(this.version);
        parcel.writeInt(this.code);
    }

    public CommandResponseInfo() {
    }

    protected CommandResponseInfo(Parcel parcel) {
        this.time = parcel.readLong();
        this.command = parcel.readString();
        this.data = parcel.readString();
        this.state = parcel.readString();
        this.version = parcel.readString();
        this.code = parcel.readInt();
    }
}
