package com.nrzs.data.game.bean;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes2.dex */
public class GameScreenInfo implements Parcelable {
    public static final Parcelable.Creator<GameScreenInfo> CREATOR = new Parcelable.Creator<GameScreenInfo>() { // from class: com.nrzs.data.game.bean.GameScreenInfo.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public GameScreenInfo createFromParcel(Parcel parcel) {
            return new GameScreenInfo(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public GameScreenInfo[] newArray(int i) {
            return new GameScreenInfo[i];
        }
    };
    public boolean IsBangsScreen;
    public String Resolution;
    public boolean UseFullScreen;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeByte(this.UseFullScreen ? (byte) 1 : (byte) 0);
        parcel.writeString(this.Resolution);
        parcel.writeByte(this.IsBangsScreen ? (byte) 1 : (byte) 0);
    }

    public GameScreenInfo() {
    }

    protected GameScreenInfo(Parcel parcel) {
        boolean z = true;
        this.UseFullScreen = parcel.readByte() != 0;
        this.Resolution = parcel.readString();
        this.IsBangsScreen = parcel.readByte() == 0 ? false : z;
    }
}
