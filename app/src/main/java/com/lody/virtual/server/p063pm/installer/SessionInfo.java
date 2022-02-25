package com.lody.virtual.server.p063pm.installer;

import android.content.pm.PackageInstaller;
import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.Parcelable;
import p110z1.PackageInstaller;

/* renamed from: com.lody.virtual.server.pm.installer.SessionInfo */
/* loaded from: classes.dex */
public class SessionInfo implements Parcelable {
    public static final Parcelable.Creator<SessionInfo> CREATOR = new Parcelable.Creator<SessionInfo>() { // from class: com.lody.virtual.server.pm.installer.SessionInfo.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public SessionInfo createFromParcel(Parcel parcel) {
            return new SessionInfo(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public SessionInfo[] newArray(int i) {
            return new SessionInfo[i];
        }
    };
    public boolean active;
    public Bitmap appIcon;
    public CharSequence appLabel;
    public String appPackageName;
    public String installerPackageName;
    public int mode;
    public float progress;
    public String resolvedBaseCodePath;
    public boolean sealed;
    public int sessionId;
    public long sizeBytes;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public PackageInstaller.SessionInfo alloc() {
        PackageInstaller.SessionInfo newInstance = PackageInstaller.C5140a.ctor.newInstance();
        PackageInstaller.C5140a.sessionId.set(newInstance, this.sessionId);
        PackageInstaller.C5140a.installerPackageName.set(newInstance, this.installerPackageName);
        PackageInstaller.C5140a.resolvedBaseCodePath.set(newInstance, this.resolvedBaseCodePath);
        PackageInstaller.C5140a.progress.set(newInstance, this.progress);
        PackageInstaller.C5140a.sealed.set(newInstance, this.sealed);
        PackageInstaller.C5140a.active.set(newInstance, this.active);
        PackageInstaller.C5140a.mode.set(newInstance, this.mode);
        PackageInstaller.C5140a.sizeBytes.set(newInstance, this.sizeBytes);
        PackageInstaller.C5140a.appPackageName.set(newInstance, this.appPackageName);
        PackageInstaller.C5140a.appIcon.set(newInstance, this.appIcon);
        PackageInstaller.C5140a.appLabel.set(newInstance, this.appLabel);
        return newInstance;
    }

    public static SessionInfo realloc(PackageInstaller.SessionInfo sessionInfo) {
        SessionInfo sessionInfo2 = new SessionInfo();
        sessionInfo2.sessionId = PackageInstaller.C5140a.sessionId.get(sessionInfo);
        sessionInfo2.installerPackageName = PackageInstaller.C5140a.installerPackageName.get(sessionInfo);
        sessionInfo2.resolvedBaseCodePath = PackageInstaller.C5140a.resolvedBaseCodePath.get(sessionInfo);
        sessionInfo2.progress = PackageInstaller.C5140a.progress.get(sessionInfo);
        sessionInfo2.sealed = PackageInstaller.C5140a.sealed.get(sessionInfo);
        sessionInfo2.active = PackageInstaller.C5140a.active.get(sessionInfo);
        sessionInfo2.mode = PackageInstaller.C5140a.mode.get(sessionInfo);
        sessionInfo2.sizeBytes = PackageInstaller.C5140a.sizeBytes.get(sessionInfo);
        sessionInfo2.appPackageName = PackageInstaller.C5140a.appPackageName.get(sessionInfo);
        sessionInfo2.appIcon = PackageInstaller.C5140a.appIcon.get(sessionInfo);
        sessionInfo2.appLabel = PackageInstaller.C5140a.appLabel.get(sessionInfo);
        return sessionInfo2;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.sessionId);
        parcel.writeString(this.installerPackageName);
        parcel.writeString(this.resolvedBaseCodePath);
        parcel.writeFloat(this.progress);
        parcel.writeByte(this.sealed ? (byte) 1 : (byte) 0);
        parcel.writeByte(this.active ? (byte) 1 : (byte) 0);
        parcel.writeInt(this.mode);
        parcel.writeLong(this.sizeBytes);
        parcel.writeString(this.appPackageName);
        parcel.writeParcelable(this.appIcon, i);
        CharSequence charSequence = this.appLabel;
        if (charSequence != null) {
            parcel.writeString(charSequence.toString());
        }
    }

    public SessionInfo() {
    }

    protected SessionInfo(Parcel parcel) {
        this.sessionId = parcel.readInt();
        this.installerPackageName = parcel.readString();
        this.resolvedBaseCodePath = parcel.readString();
        this.progress = parcel.readFloat();
        boolean z = true;
        this.sealed = parcel.readByte() != 0;
        this.active = parcel.readByte() == 0 ? false : z;
        this.mode = parcel.readInt();
        this.sizeBytes = parcel.readLong();
        this.appPackageName = parcel.readString();
        this.appIcon = (Bitmap) parcel.readParcelable(Bitmap.class.getClassLoader());
        this.appLabel = parcel.readString();
    }
}
