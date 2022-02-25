package com.lody.virtual.server.accounts;

import android.accounts.Account;
import android.os.Parcel;
import android.os.Parcelable;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes.dex */
public class VAccount implements Parcelable {
    public static final Parcelable.Creator<VAccount> CREATOR = new Parcelable.Creator<VAccount>() { // from class: com.lody.virtual.server.accounts.VAccount.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public VAccount createFromParcel(Parcel parcel) {
            return new VAccount(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public VAccount[] newArray(int i) {
            return new VAccount[i];
        }
    };
    public Map<String, String> authTokens;
    public long lastAuthenticatedTime;
    public String name;
    public String password;
    public String previousName;
    public String type;
    public Map<String, String> userDatas;
    public int userId;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public VAccount(int i, Account account) {
        this.userId = i;
        this.name = account.name;
        this.type = account.type;
        this.authTokens = new HashMap();
        this.userDatas = new HashMap();
    }

    public VAccount(Parcel parcel) {
        this.userId = parcel.readInt();
        this.name = parcel.readString();
        this.previousName = parcel.readString();
        this.type = parcel.readString();
        this.password = parcel.readString();
        this.lastAuthenticatedTime = parcel.readLong();
        int readInt = parcel.readInt();
        this.authTokens = new HashMap(readInt);
        for (int i = 0; i < readInt; i++) {
            this.authTokens.put(parcel.readString(), parcel.readString());
        }
        int readInt2 = parcel.readInt();
        this.userDatas = new HashMap(readInt2);
        for (int i2 = 0; i2 < readInt2; i2++) {
            this.userDatas.put(parcel.readString(), parcel.readString());
        }
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.userId);
        parcel.writeString(this.name);
        parcel.writeString(this.previousName);
        parcel.writeString(this.type);
        parcel.writeString(this.password);
        parcel.writeLong(this.lastAuthenticatedTime);
        parcel.writeInt(this.authTokens.size());
        for (Map.Entry<String, String> entry : this.authTokens.entrySet()) {
            parcel.writeString(entry.getKey());
            parcel.writeString(entry.getValue());
        }
        parcel.writeInt(this.userDatas.size());
        for (Map.Entry<String, String> entry2 : this.userDatas.entrySet()) {
            parcel.writeString(entry2.getKey());
            parcel.writeString(entry2.getValue());
        }
    }
}
