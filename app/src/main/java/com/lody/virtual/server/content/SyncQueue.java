package com.lody.virtual.server.content;

import android.accounts.Account;
import android.util.Log;
import android.util.Pair;
import com.lody.virtual.server.content.SyncAdaptersCache;
import com.lody.virtual.server.content.SyncStorageEngine;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/* loaded from: classes.dex */
public class SyncQueue {
    private static final String TAG = "SyncManager";
    private final HashMap<String, SyncOperation> mOperationsMap = new HashMap<>();
    private final SyncAdaptersCache mSyncAdapters;
    private final SyncStorageEngine mSyncStorageEngine;

    public SyncQueue(SyncStorageEngine syncStorageEngine, SyncAdaptersCache syncAdaptersCache) {
        this.mSyncStorageEngine = syncStorageEngine;
        this.mSyncAdapters = syncAdaptersCache;
    }

    public void addPendingOperations(int i) {
        Iterator<SyncStorageEngine.PendingOperation> it = this.mSyncStorageEngine.getPendingOperations().iterator();
        while (it.hasNext()) {
            SyncStorageEngine.PendingOperation next = it.next();
            if (next.userId == i) {
                Pair<Long, Long> backoff = this.mSyncStorageEngine.getBackoff(next.account, next.userId, next.authority);
                SyncAdaptersCache.SyncAdapterInfo serviceInfo = this.mSyncAdapters.getServiceInfo(next.account, next.authority);
                if (serviceInfo == null) {
                    Log.w(TAG, "Missing sync adapter info for authority " + next.authority + ", userId " + next.userId);
                } else {
                    SyncOperation syncOperation = new SyncOperation(next.account, next.userId, next.reason, next.syncSource, next.authority, next.extras, 0L, 0L, backoff != null ? ((Long) backoff.first).longValue() : 0L, this.mSyncStorageEngine.getDelayUntilTime(next.account, next.userId, next.authority), serviceInfo.type.allowParallelSyncs());
                    syncOperation.expedited = next.expedited;
                    syncOperation.pendingOperation = next;
                    add(syncOperation, next);
                }
            }
        }
    }

    public boolean add(SyncOperation syncOperation) {
        return add(syncOperation, null);
    }

    private boolean add(SyncOperation syncOperation, SyncStorageEngine.PendingOperation pendingOperation) {
        String str = syncOperation.key;
        SyncOperation syncOperation2 = this.mOperationsMap.get(str);
        if (syncOperation2 == null) {
            syncOperation.pendingOperation = pendingOperation;
            if (syncOperation.pendingOperation == null) {
                SyncStorageEngine.PendingOperation insertIntoPending = this.mSyncStorageEngine.insertIntoPending(new SyncStorageEngine.PendingOperation(syncOperation.account, syncOperation.userId, syncOperation.reason, syncOperation.syncSource, syncOperation.authority, syncOperation.extras, syncOperation.expedited));
                if (insertIntoPending != null) {
                    syncOperation.pendingOperation = insertIntoPending;
                } else {
                    throw new IllegalStateException("error adding pending sync operation " + syncOperation);
                }
            }
            this.mOperationsMap.put(str, syncOperation);
            return true;
        } else if (syncOperation.compareTo(syncOperation2) > 0) {
            return false;
        } else {
            syncOperation2.expedited = syncOperation.expedited;
            syncOperation2.latestRunTime = Math.min(syncOperation2.latestRunTime, syncOperation.latestRunTime);
            syncOperation2.flexTime = syncOperation.flexTime;
            return true;
        }
    }

    public void removeUser(int i) {
        ArrayList arrayList = new ArrayList();
        for (SyncOperation syncOperation : this.mOperationsMap.values()) {
            if (syncOperation.userId == i) {
                arrayList.add(syncOperation);
            }
        }
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            remove((SyncOperation) it.next());
        }
    }

    public void remove(SyncOperation syncOperation) {
        SyncOperation remove = this.mOperationsMap.remove(syncOperation.key);
        if (remove != null && !this.mSyncStorageEngine.deleteFromPending(remove.pendingOperation)) {
            String str = "unable to find pending row for " + remove;
            Log.e(TAG, str, new IllegalStateException(str));
        }
    }

    public void onBackoffChanged(Account account, int i, String str, long j) {
        for (SyncOperation syncOperation : this.mOperationsMap.values()) {
            if (syncOperation.account.equals(account) && syncOperation.authority.equals(str) && syncOperation.userId == i) {
                syncOperation.backoff = Long.valueOf(j);
                syncOperation.updateEffectiveRunTime();
            }
        }
    }

    public void onDelayUntilTimeChanged(Account account, String str, long j) {
        for (SyncOperation syncOperation : this.mOperationsMap.values()) {
            if (syncOperation.account.equals(account) && syncOperation.authority.equals(str)) {
                syncOperation.delayUntil = j;
                syncOperation.updateEffectiveRunTime();
            }
        }
    }

    public void remove(Account account, int i, String str) {
        Iterator<Map.Entry<String, SyncOperation>> it = this.mOperationsMap.entrySet().iterator();
        while (it.hasNext()) {
            SyncOperation value = it.next().getValue();
            if (account == null || value.account.equals(account)) {
                if (str == null || value.authority.equals(str)) {
                    if (i == value.userId) {
                        it.remove();
                        if (!this.mSyncStorageEngine.deleteFromPending(value.pendingOperation)) {
                            String str2 = "unable to find pending row for " + value;
                            Log.e(TAG, str2, new IllegalStateException(str2));
                        }
                    }
                }
            }
        }
    }

    public Collection<SyncOperation> getOperations() {
        return this.mOperationsMap.values();
    }
}
