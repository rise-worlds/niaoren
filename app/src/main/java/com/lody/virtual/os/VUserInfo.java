package com.lody.virtual.os;

import android.os.Parcel;
import android.os.Parcelable;
import p110z1.C4963cj;

/* renamed from: com.lody.virtual.os.VUserInfo */
/* loaded from: classes.dex */
public class VUserInfo implements Parcelable {
    public static final Parcelable.Creator<VUserInfo> CREATOR = new Parcelable.Creator<VUserInfo>() { // from class: com.lody.virtual.os.VUserInfo.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public VUserInfo createFromParcel(Parcel parcel) {
            return new VUserInfo(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public VUserInfo[] newArray(int i) {
            return new VUserInfo[i];
        }
    };
    public static final int FLAG_ADMIN = 2;
    public static final int FLAG_DISABLED = 64;
    public static final int FLAG_GUEST = 4;
    public static final int FLAG_INITIALIZED = 16;
    public static final int FLAG_MANAGED_PROFILE = 32;
    public static final int FLAG_MASK_USER_TYPE = 255;
    public static final int FLAG_PRIMARY = 1;
    public static final int FLAG_RESTRICTED = 8;
    public static final int NO_PROFILE_GROUP_ID = -1;
    public long creationTime;
    public int flags;
    public boolean guestToRemove;
    public String iconPath;

    /* renamed from: id */
    public int f10500id;
    public long lastLoggedInTime;
    public String name;
    public boolean partial;
    public int profileGroupId;
    public int serialNumber;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public VUserInfo(int i, String str, int i2) {
        this(i, str, null, i2);
    }

    public VUserInfo(int i, String str, String str2, int i2) {
        this.f10500id = i;
        this.name = str;
        this.flags = i2;
        this.iconPath = str2;
        this.profileGroupId = -1;
    }

    public boolean isPrimary() {
        return (this.flags & 1) == 1;
    }

    public boolean isAdmin() {
        return (this.flags & 2) == 2;
    }

    public boolean isGuest() {
        return (this.flags & 4) == 4;
    }

    public boolean isRestricted() {
        return (this.flags & 8) == 8;
    }

    public boolean isManagedProfile() {
        return (this.flags & 32) == 32;
    }

    public boolean isEnabled() {
        return (this.flags & 64) != 64;
    }

    public VUserInfo() {
    }

    public VUserInfo(int i) {
        this.f10500id = i;
    }

    public VUserInfo(VUserInfo vUserInfo) {
        this.name = vUserInfo.name;
        this.iconPath = vUserInfo.iconPath;
        this.f10500id = vUserInfo.f10500id;
        this.flags = vUserInfo.flags;
        this.serialNumber = vUserInfo.serialNumber;
        this.creationTime = vUserInfo.creationTime;
        this.lastLoggedInTime = vUserInfo.lastLoggedInTime;
        this.partial = vUserInfo.partial;
        this.profileGroupId = vUserInfo.profileGroupId;
        this.guestToRemove = vUserInfo.guestToRemove;
    }

    public String toString() {
        return "UserInfo{" + this.f10500id + ":" + this.name + ":" + Integer.toHexString(this.flags) + C4963cj.f20747d;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.f10500id);
        parcel.writeString(this.name);
        parcel.writeString(this.iconPath);
        parcel.writeInt(this.flags);
        parcel.writeInt(this.serialNumber);
        parcel.writeLong(this.creationTime);
        parcel.writeLong(this.lastLoggedInTime);
        parcel.writeInt(this.partial ? 1 : 0);
        parcel.writeInt(this.profileGroupId);
        parcel.writeInt(this.guestToRemove ? 1 : 0);
    }

    private VUserInfo(Parcel parcel) {
        this.f10500id = parcel.readInt();
        this.name = parcel.readString();
        this.iconPath = parcel.readString();
        this.flags = parcel.readInt();
        this.serialNumber = parcel.readInt();
        this.creationTime = parcel.readLong();
        this.lastLoggedInTime = parcel.readLong();
        boolean z = true;
        this.partial = parcel.readInt() != 0;
        this.profileGroupId = parcel.readInt();
        this.guestToRemove = parcel.readInt() == 0 ? false : z;
    }
}
