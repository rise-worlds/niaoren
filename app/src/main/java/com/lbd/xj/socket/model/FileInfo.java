package com.lbd.xj.socket.model;

import android.os.Parcel;
import android.os.Parcelable;

/* renamed from: com.lbd.xj.socket.model.FileInfo */
/* loaded from: classes.dex */
public class FileInfo implements Parcelable {
    public static final Parcelable.Creator<FileInfo> CREATOR = new Parcelable.Creator<FileInfo>() { // from class: com.lbd.xj.socket.model.FileInfo.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public FileInfo createFromParcel(Parcel parcel) {
            return new FileInfo(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public FileInfo[] newArray(int i) {
            return new FileInfo[i];
        }
    };
    private static final long serialVersionUID = -4830812821556630987L;
    String fileName;
    String filePath;
    long fileSize;
    boolean isCheck;
    public boolean isDirectory;
    public boolean isPhoto;
    String suffix;
    String time;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public FileInfo() {
        this.isCheck = false;
        this.isPhoto = false;
    }

    protected FileInfo(Parcel parcel) {
        this.isCheck = false;
        this.isPhoto = false;
        this.fileName = parcel.readString();
        this.filePath = parcel.readString();
        this.fileSize = parcel.readLong();
        this.isDirectory = parcel.readByte() != 0;
        this.suffix = parcel.readString();
        this.time = parcel.readString();
        this.isCheck = parcel.readByte() != 0;
        if (parcel.readByte() != 0) {
            this.isPhoto = true;
        } else {
            this.isPhoto = false;
        }
    }

    public FileInfo(String str, String str2, long j, boolean z, String str3, String str4, boolean z2, boolean z3) {
        this.fileName = str;
        this.filePath = str2;
        this.fileSize = j;
        this.isDirectory = z;
        this.suffix = str3;
        this.time = str4;
        this.isCheck = z2;
        this.isPhoto = z3;
    }

    public String getFileName() {
        return this.fileName;
    }

    public String getFilePath() {
        return this.filePath;
    }

    public long getFileSize() {
        return this.fileSize;
    }

    public boolean getIsCheck() {
        return this.isCheck;
    }

    public boolean getIsDirectory() {
        return this.isDirectory;
    }

    public boolean getIsPhoto() {
        return this.isPhoto;
    }

    public String getSuffix() {
        return this.suffix;
    }

    public String getTime() {
        return this.time;
    }

    public boolean isDirectory() {
        return this.isDirectory;
    }

    public void setDirectory(boolean z) {
        this.isDirectory = z;
    }

    public void setFileName(String str) {
        this.fileName = str;
    }

    public void setFilePath(String str) {
        this.filePath = str;
    }

    public void setFileSize(long j) {
        this.fileSize = j;
    }

    public void setIsCheck(boolean z) {
        this.isCheck = z;
    }

    public void setIsDirectory(boolean z) {
        this.isDirectory = z;
    }

    public void setIsPhoto(boolean z) {
        this.isPhoto = z;
    }

    public void setSuffix(String str) {
        this.suffix = str;
    }

    public void setTime(String str) {
        this.time = str;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.fileName);
        parcel.writeString(this.filePath);
        parcel.writeLong(this.fileSize);
        parcel.writeByte(this.isDirectory ? (byte) 1 : (byte) 0);
        parcel.writeString(this.suffix);
        parcel.writeString(this.time);
        parcel.writeByte(this.isCheck ? (byte) 1 : (byte) 0);
        parcel.writeByte(this.isPhoto ? (byte) 1 : (byte) 0);
    }
}
