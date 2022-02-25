package com.nrzs.data.p066ft.bean;

import android.os.Parcel;
import android.os.Parcelable;

/* renamed from: com.nrzs.data.ft.bean.ScriptPathInfo */
/* loaded from: classes2.dex */
public class ScriptPathInfo implements Parcelable {
    public static final Parcelable.Creator<ScriptPathInfo> CREATOR = new Parcelable.Creator<ScriptPathInfo>() { // from class: com.nrzs.data.ft.bean.ScriptPathInfo.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public ScriptPathInfo createFromParcel(Parcel parcel) {
            return new ScriptPathInfo(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public ScriptPathInfo[] newArray(int i) {
            return new ScriptPathInfo[i];
        }
    };
    public String atc_Path;
    public byte[] compiledContent;
    public String lc_path;
    public String rtd_path;
    public String uiCfgPath;
    public String uiPath;
    public String uipPath;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.lc_path);
        parcel.writeString(this.atc_Path);
        parcel.writeString(this.uiPath);
        parcel.writeString(this.uiCfgPath);
        parcel.writeString(this.rtd_path);
        parcel.writeString(this.uipPath);
        parcel.writeByteArray(this.compiledContent);
    }

    public ScriptPathInfo() {
        this.lc_path = "";
        this.atc_Path = "";
        this.uiPath = "";
        this.uiCfgPath = "";
        this.rtd_path = "";
        this.uipPath = "";
        this.compiledContent = null;
    }

    protected ScriptPathInfo(Parcel parcel) {
        this.lc_path = "";
        this.atc_Path = "";
        this.uiPath = "";
        this.uiCfgPath = "";
        this.rtd_path = "";
        this.uipPath = "";
        this.compiledContent = null;
        this.lc_path = parcel.readString();
        this.atc_Path = parcel.readString();
        this.uiPath = parcel.readString();
        this.uiCfgPath = parcel.readString();
        this.rtd_path = parcel.readString();
        this.uipPath = parcel.readString();
        this.compiledContent = parcel.createByteArray();
    }
}
