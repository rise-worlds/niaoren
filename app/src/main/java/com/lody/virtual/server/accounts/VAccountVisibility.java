package com.lody.virtual.server.accounts;

import android.accounts.Account;
import android.os.Parcel;
import android.os.Parcelable;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes.dex */
public class VAccountVisibility implements Parcelable {
    public static final Parcelable.Creator<VAccountVisibility> CREATOR = new Parcelable.Creator<VAccountVisibility>() { // from class: com.lody.virtual.server.accounts.VAccountVisibility.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public VAccountVisibility createFromParcel(Parcel parcel) {
            return new VAccountVisibility(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public VAccountVisibility[] newArray(int i) {
            return new VAccountVisibility[i];
        }
    };
    public String name;
    public String type;
    public int userId;
    public Map<String, Integer> visibility;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.name);
        parcel.writeString(this.type);
        parcel.writeInt(this.userId);
        parcel.writeInt(this.visibility.size());
        for (Map.Entry<String, Integer> entry : this.visibility.entrySet()) {
            parcel.writeString(entry.getKey());
            parcel.writeValue(entry.getValue());
        }
    }

    public VAccountVisibility() {
    }

    public VAccountVisibility(int i, Account account, Map<String, Integer> map) {
        this.userId = i;
        this.name = account.name;
        this.type = account.type;
        this.visibility = new HashMap();
        if (map != null) {
            this.visibility.putAll(map);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public VAccountVisibility(Parcel parcel) {
        this.name = parcel.readString();
        this.type = parcel.readString();
        this.userId = parcel.readInt();
        int readInt = parcel.readInt();
        this.visibility = new HashMap(readInt);
        for (int i = 0; i < readInt; i++) {
            this.visibility.put(parcel.readString(), (Integer) parcel.readValue(Integer.class.getClassLoader()));
        }
    }
}
