package com.lody.virtual.server.p063pm.installer;

import android.annotation.TargetApi;
import android.content.pm.PackageInstaller;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;
import p110z1.PackageInstaller;

@TargetApi(21)
/* renamed from: com.lody.virtual.server.pm.installer.SessionParams */
/* loaded from: classes.dex */
public class SessionParams implements Parcelable {
    public static final Parcelable.Creator<SessionParams> CREATOR = new Parcelable.Creator<SessionParams>() { // from class: com.lody.virtual.server.pm.installer.SessionParams.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public SessionParams createFromParcel(Parcel parcel) {
            return new SessionParams(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public SessionParams[] newArray(int i) {
            return new SessionParams[i];
        }
    };
    public static final int MODE_FULL_INSTALL = 1;
    public static final int MODE_INHERIT_EXISTING = 2;
    public static final int MODE_INVALID = -1;
    public String abiOverride;
    public Bitmap appIcon;
    public long appIconLastModified;
    public String appLabel;
    public String appPackageName;
    public String[] grantedRuntimePermissions;
    public int installFlags;
    public int installLocation;
    public int mode;
    public Uri originatingUri;
    public Uri referrerUri;
    public long sizeBytes;
    public String volumeUuid;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public SessionParams(int i) {
        this.mode = -1;
        this.installLocation = 1;
        this.sizeBytes = -1L;
        this.appIconLastModified = -1L;
        this.mode = i;
    }

    public PackageInstaller.SessionParams build() {
        if (Build.VERSION.SDK_INT >= 23) {
            PackageInstaller.SessionParams sessionParams = new PackageInstaller.SessionParams(this.mode);
            PackageInstaller.C5142c.installFlags.set(sessionParams, this.installFlags);
            PackageInstaller.C5142c.installLocation.set(sessionParams, this.installLocation);
            PackageInstaller.C5142c.sizeBytes.set(sessionParams, this.sizeBytes);
            PackageInstaller.C5142c.appPackageName.set(sessionParams, this.appPackageName);
            PackageInstaller.C5142c.appIcon.set(sessionParams, this.appIcon);
            PackageInstaller.C5142c.appLabel.set(sessionParams, this.appLabel);
            PackageInstaller.C5142c.appIconLastModified.set(sessionParams, this.appIconLastModified);
            PackageInstaller.C5142c.originatingUri.set(sessionParams, this.originatingUri);
            PackageInstaller.C5142c.referrerUri.set(sessionParams, this.referrerUri);
            PackageInstaller.C5142c.abiOverride.set(sessionParams, this.abiOverride);
            PackageInstaller.C5142c.volumeUuid.set(sessionParams, this.volumeUuid);
            PackageInstaller.C5142c.grantedRuntimePermissions.set(sessionParams, this.grantedRuntimePermissions);
            return sessionParams;
        }
        PackageInstaller.SessionParams sessionParams2 = new PackageInstaller.SessionParams(this.mode);
        PackageInstaller.C5141b.installFlags.set(sessionParams2, this.installFlags);
        PackageInstaller.C5141b.installLocation.set(sessionParams2, this.installLocation);
        PackageInstaller.C5141b.sizeBytes.set(sessionParams2, this.sizeBytes);
        PackageInstaller.C5141b.appPackageName.set(sessionParams2, this.appPackageName);
        PackageInstaller.C5141b.appIcon.set(sessionParams2, this.appIcon);
        PackageInstaller.C5141b.appLabel.set(sessionParams2, this.appLabel);
        PackageInstaller.C5141b.appIconLastModified.set(sessionParams2, this.appIconLastModified);
        PackageInstaller.C5141b.originatingUri.set(sessionParams2, this.originatingUri);
        PackageInstaller.C5141b.referrerUri.set(sessionParams2, this.referrerUri);
        PackageInstaller.C5141b.abiOverride.set(sessionParams2, this.abiOverride);
        return sessionParams2;
    }

    public static SessionParams create(PackageInstaller.SessionParams sessionParams) {
        if (Build.VERSION.SDK_INT >= 23) {
            SessionParams sessionParams2 = new SessionParams(PackageInstaller.C5142c.mode.get(sessionParams));
            sessionParams2.installFlags = PackageInstaller.C5142c.installFlags.get(sessionParams);
            sessionParams2.installLocation = PackageInstaller.C5142c.installLocation.get(sessionParams);
            sessionParams2.sizeBytes = PackageInstaller.C5142c.sizeBytes.get(sessionParams);
            sessionParams2.appPackageName = PackageInstaller.C5142c.appPackageName.get(sessionParams);
            sessionParams2.appIcon = PackageInstaller.C5142c.appIcon.get(sessionParams);
            sessionParams2.appLabel = PackageInstaller.C5142c.appLabel.get(sessionParams);
            sessionParams2.appIconLastModified = PackageInstaller.C5142c.appIconLastModified.get(sessionParams);
            sessionParams2.originatingUri = PackageInstaller.C5142c.originatingUri.get(sessionParams);
            sessionParams2.referrerUri = PackageInstaller.C5142c.referrerUri.get(sessionParams);
            sessionParams2.abiOverride = PackageInstaller.C5142c.abiOverride.get(sessionParams);
            sessionParams2.volumeUuid = PackageInstaller.C5142c.volumeUuid.get(sessionParams);
            sessionParams2.grantedRuntimePermissions = PackageInstaller.C5142c.grantedRuntimePermissions.get(sessionParams);
            return sessionParams2;
        }
        SessionParams sessionParams3 = new SessionParams(PackageInstaller.C5141b.mode.get(sessionParams));
        sessionParams3.installFlags = PackageInstaller.C5141b.installFlags.get(sessionParams);
        sessionParams3.installLocation = PackageInstaller.C5141b.installLocation.get(sessionParams);
        sessionParams3.sizeBytes = PackageInstaller.C5141b.sizeBytes.get(sessionParams);
        sessionParams3.appPackageName = PackageInstaller.C5141b.appPackageName.get(sessionParams);
        sessionParams3.appIcon = PackageInstaller.C5141b.appIcon.get(sessionParams);
        sessionParams3.appLabel = PackageInstaller.C5141b.appLabel.get(sessionParams);
        sessionParams3.appIconLastModified = PackageInstaller.C5141b.appIconLastModified.get(sessionParams);
        sessionParams3.originatingUri = PackageInstaller.C5141b.originatingUri.get(sessionParams);
        sessionParams3.referrerUri = PackageInstaller.C5141b.referrerUri.get(sessionParams);
        sessionParams3.abiOverride = PackageInstaller.C5141b.abiOverride.get(sessionParams);
        return sessionParams3;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.mode);
        parcel.writeInt(this.installFlags);
        parcel.writeInt(this.installLocation);
        parcel.writeLong(this.sizeBytes);
        parcel.writeString(this.appPackageName);
        parcel.writeParcelable(this.appIcon, i);
        parcel.writeString(this.appLabel);
        parcel.writeLong(this.appIconLastModified);
        parcel.writeParcelable(this.originatingUri, i);
        parcel.writeParcelable(this.referrerUri, i);
        parcel.writeString(this.abiOverride);
        parcel.writeString(this.volumeUuid);
        parcel.writeStringArray(this.grantedRuntimePermissions);
    }

    protected SessionParams(Parcel parcel) {
        this.mode = -1;
        this.installLocation = 1;
        this.sizeBytes = -1L;
        this.appIconLastModified = -1L;
        this.mode = parcel.readInt();
        this.installFlags = parcel.readInt();
        this.installLocation = parcel.readInt();
        this.sizeBytes = parcel.readLong();
        this.appPackageName = parcel.readString();
        this.appIcon = (Bitmap) parcel.readParcelable(Bitmap.class.getClassLoader());
        this.appLabel = parcel.readString();
        this.appIconLastModified = parcel.readLong();
        this.originatingUri = (Uri) parcel.readParcelable(Uri.class.getClassLoader());
        this.referrerUri = (Uri) parcel.readParcelable(Uri.class.getClassLoader());
        this.abiOverride = parcel.readString();
        this.volumeUuid = parcel.readString();
        this.grantedRuntimePermissions = parcel.createStringArray();
    }
}
