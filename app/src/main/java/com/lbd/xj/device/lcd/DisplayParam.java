package com.lbd.xj.device.lcd;

import android.os.Parcel;
import android.os.Parcelable;

/* renamed from: com.lbd.xj.device.lcd.DisplayParam */
/* loaded from: classes.dex */
public class DisplayParam implements Parcelable {
    public static final Parcelable.Creator<DisplayParam> CREATOR = new Parcelable.Creator<DisplayParam>() { // from class: com.lbd.xj.device.lcd.DisplayParam.1
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

    public void setDisplayWidth(int i) {
        this.displayWidth = String.valueOf(i);
    }

    public String getDisplayHeight() {
        return this.displayHeight;
    }

    public void setDisplayHeight(String str) {
        this.displayHeight = str;
    }

    public void setDisplayHeight(int i) {
        this.displayHeight = String.valueOf(i);
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

    public void setDisplayScaleW(int i) {
        this.displayScaleW = String.valueOf(i);
    }

    public String getDisplayScaleH() {
        return this.displayScaleH;
    }

    public void setDisplayScaleH(String str) {
        this.displayScaleH = str;
    }

    public void setDisplayScaleH(int i) {
        this.displayScaleH = String.valueOf(i);
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

    public void setDefaultScale() {
        this.scaleW = 1.0f;
        this.scaleH = 1.0f;
    }

    public String toString() {
        return "DisplayParam{displayWidth='" + this.displayWidth + "', displayHeight='" + this.displayHeight + "', scaleW=" + this.scaleW + ", scaleH=" + this.scaleH + ", displayScaleW='" + this.displayScaleW + "', displayScaleH='" + this.displayScaleH + "', isForceMode='" + this.isForceMode + "', port='" + this.port + "'}";
    }
}
