package com.lody.virtual.server.content;

import android.accounts.Account;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import com.lody.virtual.helper.compat.ContentResolverCompat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* loaded from: classes.dex */
public class VSyncRecord {
    public SyncRecordKey key;
    public int userId;
    public int syncable = -1;
    public boolean isPeriodic = false;
    public Map<SyncExtras, PeriodicSyncConfig> configs = new HashMap();
    public List<SyncExtras> extras = new ArrayList();

    public VSyncRecord(int i, Account account, String str) {
        this.userId = i;
        this.key = new SyncRecordKey(account, str);
    }

    /* loaded from: classes.dex */
    public static class SyncExtras implements Parcelable {
        public static final Parcelable.Creator<SyncExtras> CREATOR = new Parcelable.Creator<SyncExtras>() { // from class: com.lody.virtual.server.content.VSyncRecord.SyncExtras.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public SyncExtras createFromParcel(Parcel parcel) {
                return new SyncExtras(parcel);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public SyncExtras[] newArray(int i) {
                return new SyncExtras[i];
            }
        };
        Bundle extras;

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        public SyncExtras(Bundle bundle) {
            this.extras = bundle;
        }

        SyncExtras(Parcel parcel) {
            this.extras = parcel.readBundle(getClass().getClassLoader());
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeBundle(this.extras);
        }

        public boolean equals(Object obj) {
            return VSyncRecord.equals(this.extras, ((SyncExtras) obj).extras, false);
        }
    }

    /* loaded from: classes.dex */
    public static class SyncRecordKey implements Parcelable {
        public static final Parcelable.Creator<SyncRecordKey> CREATOR = new Parcelable.Creator<SyncRecordKey>() { // from class: com.lody.virtual.server.content.VSyncRecord.SyncRecordKey.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public SyncRecordKey createFromParcel(Parcel parcel) {
                return new SyncRecordKey(parcel);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public SyncRecordKey[] newArray(int i) {
                return new SyncRecordKey[i];
            }
        };
        Account account;
        String authority;

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        SyncRecordKey(Account account, String str) {
            this.account = account;
            this.authority = str;
        }

        SyncRecordKey(Parcel parcel) {
            this.account = (Account) parcel.readParcelable(Account.class.getClassLoader());
            this.authority = parcel.readString();
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeParcelable(this.account, i);
            parcel.writeString(this.authority);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            SyncRecordKey syncRecordKey = (SyncRecordKey) obj;
            Account account = this.account;
            if (account == null ? syncRecordKey.account != null : !account.equals(syncRecordKey.account)) {
                return false;
            }
            String str = this.authority;
            return str != null ? str.equals(syncRecordKey.authority) : syncRecordKey.authority == null;
        }
    }

    public static boolean equals(Bundle bundle, Bundle bundle2, boolean z) {
        if (bundle == bundle2) {
            return true;
        }
        if (z && bundle.size() != bundle2.size()) {
            return false;
        }
        if (bundle.size() <= bundle2.size()) {
            bundle2 = bundle;
            bundle = bundle2;
        }
        for (String str : bundle.keySet()) {
            if (z || !isIgnoredKey(str)) {
                if (!(bundle2.containsKey(str) && bundle.get(str).equals(bundle2.get(str)))) {
                    return false;
                }
            }
        }
        return true;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static class PeriodicSyncConfig implements Parcelable {
        public static final Parcelable.Creator<PeriodicSyncConfig> CREATOR = new Parcelable.Creator<PeriodicSyncConfig>() { // from class: com.lody.virtual.server.content.VSyncRecord.PeriodicSyncConfig.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public PeriodicSyncConfig createFromParcel(Parcel parcel) {
                return new PeriodicSyncConfig(parcel);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public PeriodicSyncConfig[] newArray(int i) {
                return new PeriodicSyncConfig[i];
            }
        };
        long syncRunTimeSecs;

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        public PeriodicSyncConfig(long j) {
            this.syncRunTimeSecs = j;
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeLong(this.syncRunTimeSecs);
        }

        PeriodicSyncConfig(Parcel parcel) {
            this.syncRunTimeSecs = parcel.readLong();
        }
    }

    private static boolean isIgnoredKey(String str) {
        return str.equals("expedited") || str.equals("ignore_settings") || str.equals("ignore_backoff") || str.equals("do_not_retry") || str.equals("force") || str.equals("upload") || str.equals("deletions_override") || str.equals("discard_deletions") || str.equals(ContentResolverCompat.SYNC_EXTRAS_EXPECTED_UPLOAD) || str.equals(ContentResolverCompat.SYNC_EXTRAS_EXPECTED_DOWNLOAD) || str.equals(ContentResolverCompat.SYNC_EXTRAS_PRIORITY) || str.equals(ContentResolverCompat.SYNC_EXTRAS_DISALLOW_METERED) || str.equals("initialize");
    }
}
