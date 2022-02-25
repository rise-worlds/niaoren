package com.lbd.p054xj.device;

import android.os.Parcel;
import android.os.Parcelable;

/* renamed from: com.lbd.xj.device.DisplayParam */
/* loaded from: classes.dex */
public class DisplayParam implements Parcelable {
    public static final Parcelable.Creator<DisplayParam> CREATOR = new Parcelable.Creator<DisplayParam>() { // from class: com.lbd.xj.device.DisplayParam.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public DisplayParam createFromParcel(Parcel parcel) {
            return new DisplayParam(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public DisplayParam[] newArray(int i) {
            return new DisplayParam[i];
        }
    };
    private String displayHeight;
    private String displayScaleH;
    private String displayScaleW;
    private String displayWidth;
    private String isForceMode;
    private String port;
    private float scaleH;
    private float scaleW;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public DisplayParam() {
    }

    protected DisplayParam(Parcel parcel) {
        this.displayWidth = parcel.readString();
        this.displayHeight = parcel.readString();
        this.scaleW = parcel.readFloat();
        this.scaleH = parcel.readFloat();
        this.displayScaleW = parcel.readString();
        this.displayScaleH = parcel.readString();
        this.isForceMode = parcel.readString();
        this.port = parcel.readString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.displayWidth);
        parcel.writeString(this.displayHeight);
        parcel.writeFloat(this.scaleW);
        parcel.writeFloat(this.scaleH);
        parcel.writeString(this.displayScaleW);
        parcel.writeString(this.displayScaleH);
        parcel.writeString(this.isForceMode);
        parcel.writeString(this.port);
    }

    public String getDisplayWidth() {
        return this.displayWidth;
    }

    public void setDisplayWidth(String str) {
        this.displayWidth = str;
    }

    public String getDisplayHeight() {
        return this.displayHeight;
    }

    public void setDisplayHeight(String str) {
        this.displayHeight = str;
    }

    public float getScaleW() {
        return this.scaleW;
    }

    public void setScaleW(float f) {
        this.scaleW = f;
    }

    public float getScaleH() {
        return this.scaleH;
    }

    public void setScaleH(float f) {
        this.scaleH = f;
    }

    public String getDisplayScaleW() {
        return this.displayScaleW;
    }

    public void setDisplayScaleW(String str) {
        this.displayScaleW = str;
    }

    public String getDisplayScaleH() {
        return this.displayScaleH;
    }

    public void setDisplayScaleH(String str) {
        this.displayScaleH = str;
    }

    public String getIsForceMode() {
        return this.isForceMode;
    }

    public void setIsForceMode(String str) {
        this.isForceMode = str;
    }

    public String getPort() {
        return this.port;
    }

    public void setPort(String str) {
        this.port = str;
    }
}
