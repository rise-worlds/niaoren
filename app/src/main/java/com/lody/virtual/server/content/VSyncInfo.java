package com.lody.virtual.server.content;

import android.accounts.Account;
import android.content.SyncInfo;
import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes.dex */
public class VSyncInfo implements Parcelable {
    public final Account account;
    public final String authority;
    public final int authorityId;
    public final long startTime;
    private static final Account REDACTED_ACCOUNT = new Account("*****", "*****");
    public static final Parcelable.Creator<VSyncInfo> CREATOR = new Parcelable.Creator<VSyncInfo>() { // from class: com.lody.virtual.server.content.VSyncInfo.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public VSyncInfo createFromParcel(Parcel parcel) {
            return new VSyncInfo(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public VSyncInfo[] newArray(int i) {
            return new VSyncInfo[i];
        }
    };

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public static VSyncInfo createAccountRedacted(int i, String str, long j) {
        return new VSyncInfo(i, REDACTED_ACCOUNT, str, j);
    }

    public VSyncInfo(int i, Account account, String str, long j) {
        this.authorityId = i;
        this.account = account;
        this.authority = str;
        this.startTime = j;
    }

    public VSyncInfo(VSyncInfo vSyncInfo) {
        this.authorityId = vSyncInfo.authorityId;
        this.account = new Account(vSyncInfo.account.name, vSyncInfo.account.type);
        this.authority = vSyncInfo.authority;
        this.startTime = vSyncInfo.startTime;
    }

    public SyncInfo toSyncInfo() {
        return p110z1.SyncInfo.ctor.newInstance(Integer.valueOf(this.authorityId), this.account, this.authority, Long.valueOf(this.startTime));
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.authorityId);
        parcel.writeParcelable(this.account, i);
        parcel.writeString(this.authority);
        parcel.writeLong(this.startTime);
    }

    VSyncInfo(Parcel parcel) {
        this.authorityId = parcel.readInt();
        this.account = (Account) parcel.readParcelable(Account.class.getClassLoader());
        this.authority = parcel.readString();
        this.startTime = parcel.readLong();
    }
}
