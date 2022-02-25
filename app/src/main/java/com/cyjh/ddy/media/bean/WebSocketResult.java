package com.cyjh.ddy.media.bean;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes.dex */
public class WebSocketResult implements Parcelable {
    public static final Parcelable.Creator<WebSocketResult> CREATOR = new Parcelable.Creator<WebSocketResult>() { // from class: com.cyjh.ddy.media.bean.WebSocketResult.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public WebSocketResult createFromParcel(Parcel parcel) {
            return new WebSocketResult(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public WebSocketResult[] newArray(int i) {
            return new WebSocketResult[i];
        }
    };

    /* renamed from: h */
    public int f7218h;
    public int height;
    public String msg;
    public int rotate;
    public String state;
    public int status;
    public int type;

    /* renamed from: w */
    public int f7219w;
    public int width;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public WebSocketResult() {
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.type);
        parcel.writeInt(this.status);
        parcel.writeInt(this.rotate);
        parcel.writeInt(this.width);
        parcel.writeInt(this.height);
        parcel.writeInt(this.f7219w);
        parcel.writeInt(this.f7218h);
        parcel.writeString(this.msg);
        parcel.writeString(this.state);
    }

    protected WebSocketResult(Parcel parcel) {
        this.type = parcel.readInt();
        this.status = parcel.readInt();
        this.rotate = parcel.readInt();
        this.width = parcel.readInt();
        this.height = parcel.readInt();
        this.f7219w = parcel.readInt();
        this.f7218h = parcel.readInt();
        this.msg = parcel.readString();
        this.state = parcel.readString();
    }
}
