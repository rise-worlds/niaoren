package com.lody.virtual.server.content;

import android.accounts.Account;
import android.content.ComponentName;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.SystemClock;
import com.ctetin.expandabletextviewlibrary.ExpandableTextView;
import com.lody.virtual.helper.compat.ContentResolverCompat;
import com.lody.virtual.server.content.SyncStorageEngine;
import p110z1.C4963cj;
import p110z1.SimpleComparison;

/* loaded from: classes.dex */
public class SyncOperation implements Comparable {
    public static final int REASON_ACCOUNTS_UPDATED = -2;
    public static final int REASON_BACKGROUND_DATA_SETTINGS_CHANGED = -1;
    public static final int REASON_IS_SYNCABLE = -5;
    public static final int REASON_MASTER_SYNC_AUTO = -7;
    private static String[] REASON_NAMES = {"DataSettingsChanged", "AccountsUpdated", "ServiceChanged", "Periodic", "IsSyncable", "AutoSync", "MasterSyncAuto", "UserStart"};
    public static final int REASON_PERIODIC = -4;
    public static final int REASON_SERVICE_CHANGED = -3;
    public static final int REASON_SYNC_AUTO = -6;
    public static final int REASON_USER_START = -8;
    public final Account account;
    public final boolean allowParallelSyncs;
    public final String authority;
    public Long backoff;
    public long delayUntil;
    public long effectiveRunTime;
    public boolean expedited;
    public Bundle extras;
    public long flexTime;
    public final String key;
    public long latestRunTime;
    public SyncStorageEngine.PendingOperation pendingOperation;
    public final int reason;
    public final ComponentName service;
    public int syncSource;
    public final int userId;

    public SyncOperation(Account account, int i, int i2, int i3, String str, Bundle bundle, long j, long j2, long j3, long j4, boolean z) {
        this.service = null;
        this.account = account;
        this.authority = str;
        this.userId = i;
        this.reason = i2;
        this.syncSource = i3;
        this.allowParallelSyncs = z;
        this.extras = new Bundle(bundle);
        cleanBundle(this.extras);
        this.delayUntil = j4;
        this.backoff = Long.valueOf(j3);
        long elapsedRealtime = SystemClock.elapsedRealtime();
        if (j < 0 || isExpedited()) {
            this.expedited = true;
            this.latestRunTime = elapsedRealtime;
            this.flexTime = 0L;
        } else {
            this.expedited = false;
            this.latestRunTime = elapsedRealtime + j;
            this.flexTime = j2;
        }
        updateEffectiveRunTime();
        this.key = toKey();
    }

    private void cleanBundle(Bundle bundle) {
        removeFalseExtra(bundle, "upload");
        removeFalseExtra(bundle, "force");
        removeFalseExtra(bundle, "ignore_settings");
        removeFalseExtra(bundle, "ignore_backoff");
        removeFalseExtra(bundle, "do_not_retry");
        removeFalseExtra(bundle, "discard_deletions");
        removeFalseExtra(bundle, "expedited");
        removeFalseExtra(bundle, "deletions_override");
        removeFalseExtra(bundle, ContentResolverCompat.SYNC_EXTRAS_DISALLOW_METERED);
        bundle.remove(ContentResolverCompat.SYNC_EXTRAS_EXPECTED_UPLOAD);
        bundle.remove(ContentResolverCompat.SYNC_EXTRAS_EXPECTED_DOWNLOAD);
    }

    private void removeFalseExtra(Bundle bundle, String str) {
        if (!bundle.getBoolean(str, false)) {
            bundle.remove(str);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public SyncOperation(SyncOperation syncOperation) {
        this.service = syncOperation.service;
        this.account = syncOperation.account;
        this.authority = syncOperation.authority;
        this.userId = syncOperation.userId;
        this.reason = syncOperation.reason;
        this.syncSource = syncOperation.syncSource;
        this.extras = new Bundle(syncOperation.extras);
        this.expedited = syncOperation.expedited;
        this.latestRunTime = SystemClock.elapsedRealtime();
        this.flexTime = 0L;
        this.backoff = syncOperation.backoff;
        this.allowParallelSyncs = syncOperation.allowParallelSyncs;
        updateEffectiveRunTime();
        this.key = toKey();
    }

    public String toString() {
        return dump(null, true);
    }

    public String dump(PackageManager packageManager, boolean z) {
        StringBuilder sb = new StringBuilder();
        sb.append(this.account.name);
        sb.append(" u");
        sb.append(this.userId);
        sb.append(" (");
        sb.append(this.account.type);
        sb.append(")");
        sb.append(", ");
        sb.append(this.authority);
        sb.append(", ");
        sb.append(SyncStorageEngine.SOURCES[this.syncSource]);
        sb.append(", latestRunTime ");
        sb.append(this.latestRunTime);
        if (this.expedited) {
            sb.append(", EXPEDITED");
        }
        sb.append(", reason: ");
        sb.append(reasonToString(packageManager, this.reason));
        if (!z && !this.extras.keySet().isEmpty()) {
            sb.append("\n    ");
            extrasToStringBuilder(this.extras, sb);
        }
        return sb.toString();
    }

    public static String reasonToString(PackageManager packageManager, int i) {
        if (i < 0) {
            int i2 = (-i) - 1;
            String[] strArr = REASON_NAMES;
            if (i2 >= strArr.length) {
                return String.valueOf(i);
            }
            return strArr[i2];
        } else if (packageManager == null) {
            return String.valueOf(i);
        } else {
            String[] packagesForUid = packageManager.getPackagesForUid(i);
            if (packagesForUid != null && packagesForUid.length == 1) {
                return packagesForUid[0];
            }
            String nameForUid = packageManager.getNameForUid(i);
            return nameForUid != null ? nameForUid : String.valueOf(i);
        }
    }

    public boolean isMeteredDisallowed() {
        return this.extras.getBoolean(ContentResolverCompat.SYNC_EXTRAS_DISALLOW_METERED, false);
    }

    public boolean isInitialization() {
        return this.extras.getBoolean("initialize", false);
    }

    public boolean isExpedited() {
        return this.extras.getBoolean("expedited", false) || this.expedited;
    }

    public boolean ignoreBackoff() {
        return this.extras.getBoolean("ignore_backoff", false);
    }

    private String toKey() {
        StringBuilder sb = new StringBuilder();
        if (this.service == null) {
            sb.append("authority: ");
            sb.append(this.authority);
            sb.append(" account {name=" + this.account.name + ", user=" + this.userId + ", type=" + this.account.type + C4963cj.f20747d);
        } else {
            sb.append("service {package=");
            sb.append(this.service.getPackageName());
            sb.append(" user=");
            sb.append(this.userId);
            sb.append(", class=");
            sb.append(this.service.getClassName());
            sb.append(C4963cj.f20747d);
        }
        sb.append(" extras: ");
        extrasToStringBuilder(this.extras, sb);
        return sb.toString();
    }

    public static void extrasToStringBuilder(Bundle bundle, StringBuilder sb) {
        sb.append("[");
        for (String str : bundle.keySet()) {
            sb.append(str);
            sb.append(SimpleComparison.f23609c);
            sb.append(bundle.get(str));
            sb.append(ExpandableTextView.f6958c);
        }
        sb.append("]");
    }

    public void updateEffectiveRunTime() {
        this.effectiveRunTime = ignoreBackoff() ? this.latestRunTime : Math.max(Math.max(this.latestRunTime, this.delayUntil), this.backoff.longValue());
    }

    @Override // java.lang.Comparable
    public int compareTo(Object obj) {
        SyncOperation syncOperation = (SyncOperation) obj;
        boolean z = this.expedited;
        if (z != syncOperation.expedited) {
            return z ? -1 : 1;
        }
        long max = Math.max(this.effectiveRunTime - this.flexTime, 0L);
        long max2 = Math.max(syncOperation.effectiveRunTime - syncOperation.flexTime, 0L);
        if (max < max2) {
            return -1;
        }
        return max2 < max ? 1 : 0;
    }
}
