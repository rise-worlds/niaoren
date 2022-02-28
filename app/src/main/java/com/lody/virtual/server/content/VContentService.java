package com.lody.virtual.server.content;

import android.accounts.Account;
import android.content.ContentResolver;
import android.content.Context;
import android.content.ISyncStatusObserver;
import android.content.PeriodicSync;
import android.content.SyncAdapterType;
import android.content.SyncInfo;
import android.content.SyncRequest;
import android.content.SyncStatusInfo;
import android.database.IContentObserver;
import android.database.sqlite.SQLiteException;
import android.net.Uri;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import android.text.TextUtils;
import android.util.Log;
import com.lody.virtual.client.core.VirtualCore;
import com.lody.virtual.helper.utils.VLog;
import com.lody.virtual.os.VBinder;
import com.lody.virtual.os.VUserHandle;
import com.lody.virtual.server.interfaces.IContentService;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes.dex */
public final class VContentService extends IContentService.Stub {
    private static final String TAG = "ContentService";
    private static final VContentService sInstance = new VContentService();
    private final ObserverNode mRootNode = new ObserverNode("");
    private SyncManager mSyncManager = null;
    private final Object mSyncManagerLock = new Object();
    private Context mContext = VirtualCore.get().getContext();

    public static VContentService get() {
        return sInstance;
    }

    private SyncManager getSyncManager() {
        SyncManager syncManager;
        synchronized (this.mSyncManagerLock) {
            try {
                if (this.mSyncManager == null) {
                    this.mSyncManager = new SyncManager(this.mContext);
                }
            } catch (SQLiteException e) {
                Log.e(TAG, "Can't create SyncManager", e);
            }
            syncManager = this.mSyncManager;
        }
        return syncManager;
    }

    @Override // com.lody.virtual.server.interfaces.IContentService.Stub, android.os.Binder
    public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        try {
            return super.onTransact(i, parcel, parcel2, i2);
        } catch (RuntimeException e) {
            if (!(e instanceof SecurityException)) {
                e.printStackTrace();
            }
            throw e;
        }
    }

    private VContentService() {
    }

    public static void systemReady() {
        get().getSyncManager();
    }

    @Override // com.lody.virtual.server.interfaces.IContentService
    public void registerContentObserver(Uri uri, boolean z, IContentObserver iContentObserver, int i) {
        if (iContentObserver == null || uri == null) {
            throw new IllegalArgumentException("You must pass a valid uri and observer");
        }
        synchronized (this.mRootNode) {
            this.mRootNode.addObserverLocked(uri, iContentObserver, z, this.mRootNode, VBinder.getCallingUid(), VBinder.getCallingPid(), i);
        }
    }

    public void registerContentObserver(Uri uri, boolean z, IContentObserver iContentObserver) {
        registerContentObserver(uri, z, iContentObserver, VUserHandle.getCallingUserId());
    }

    @Override // com.lody.virtual.server.interfaces.IContentService
    public void unregisterContentObserver(IContentObserver iContentObserver) {
        if (iContentObserver != null) {
            synchronized (this.mRootNode) {
                this.mRootNode.removeObserverLocked(iContentObserver);
            }
            return;
        }
        throw new IllegalArgumentException("You must pass a valid observer");
    }

    @Override // com.lody.virtual.server.interfaces.IContentService
    public void notifyChange(Uri uri, IContentObserver iContentObserver, boolean z, boolean z2, int i) {
        Throwable th;
        SyncManager syncManager;
        int i2 = 2;
        if (Log.isLoggable(TAG, 2)) {
            Log.v(TAG, "Notifying update of " + uri + " for user " + i + " from observer " + iContentObserver + ", syncToNetwork " + z2);
        }
        int callingUid = VBinder.getCallingUid();
        long clearCallingIdentity = clearCallingIdentity();
        try {
            ArrayList<ObserverCall> arrayList = new ArrayList<>();
            try {
                synchronized (this.mRootNode) {
                    try {
                        this.mRootNode.collectObserversLocked(uri, 0, iContentObserver, z, i, arrayList);
                        int size = arrayList.size();
                        int i3 = 0;
                        while (i3 < size) {
                            ObserverCall observerCall = arrayList.get(i3);
                            try {
                                observerCall.mObserver.onChange(observerCall.mSelfChange, uri, i);
                                if (Log.isLoggable(TAG, i2)) {
                                    Log.v(TAG, "Notified " + observerCall.mObserver + " of update at " + uri);
                                }
                            } catch (RemoteException unused) {
                                synchronized (this.mRootNode) {
                                    Log.w(TAG, "Found dead observer, removing");
                                    IBinder asBinder = observerCall.mObserver.asBinder();
                                    ArrayList arrayList2 = observerCall.mNode.mObservers;
                                    int size2 = arrayList2.size();
                                    int i4 = 0;
                                    while (i4 < size2) {
                                        if (((ObserverNode.ObserverEntry) arrayList2.get(i4)).observer.asBinder() == asBinder) {
                                            arrayList2.remove(i4);
                                            i4--;
                                            size2--;
                                        }
                                        i4++;
                                    }
                                }
                            }
                            i3++;
                            i2 = 2;
                        }
                        if (z2 && (syncManager = getSyncManager()) != null) {
                            syncManager.scheduleLocalSync(null, i, callingUid, uri.getAuthority());
                        }
                    } catch (Throwable th2) {
                        th = th2;
                        throw th;
                    }
                }
            } catch (Throwable th3) {
                th = th3;
            }
        } finally {
            restoreCallingIdentity(clearCallingIdentity);
        }
    }

    public void notifyChange(Uri uri, IContentObserver iContentObserver, boolean z, boolean z2) {
        notifyChange(uri, iContentObserver, z, z2, VUserHandle.getCallingUserId());
    }

    /* loaded from: classes.dex */
    public static final class ObserverCall {
        final ObserverNode mNode;
        final IContentObserver mObserver;
        final boolean mSelfChange;

        ObserverCall(ObserverNode observerNode, IContentObserver iContentObserver, boolean z) {
            this.mNode = observerNode;
            this.mObserver = iContentObserver;
            this.mSelfChange = z;
        }
    }

    @Override // com.lody.virtual.server.interfaces.IContentService
    public void requestSync(Account account, String str, Bundle bundle) {
        ContentResolver.validateSyncExtrasBundle(bundle);
        int callingUserId = VUserHandle.getCallingUserId();
        int callingUid = VBinder.getCallingUid();
        long clearCallingIdentity = clearCallingIdentity();
        try {
            SyncManager syncManager = getSyncManager();
            if (syncManager != null) {
                syncManager.scheduleSync(account, callingUserId, callingUid, str, bundle, 0L, 0L, false);
            }
        } finally {
            restoreCallingIdentity(clearCallingIdentity);
        }
    }

    @Override // com.lody.virtual.server.interfaces.IContentService
    public void sync(SyncRequest syncRequest) {
        Bundle bundle = p110z1.SyncRequest.mExtras.get(syncRequest);
        long j = p110z1.SyncRequest.mSyncFlexTimeSecs.get(syncRequest);
        long j2 = p110z1.SyncRequest.mSyncRunTimeSecs.get(syncRequest);
        int callingUserId = VUserHandle.getCallingUserId();
        int callingUid = VBinder.getCallingUid();
        long clearCallingIdentity = clearCallingIdentity();
        try {
            SyncManager syncManager = getSyncManager();
            if (syncManager != null) {
                Account account = p110z1.SyncRequest.mAccountToSync.get(syncRequest);
                String str = p110z1.SyncRequest.mAuthority.get(syncRequest);
                if (p110z1.SyncRequest.mIsPeriodic.get(syncRequest)) {
                    long j3 = 60;
                    if (j2 < 60) {
                        VLog.m18986w(TAG, "Requested poll frequency of " + j2 + " seconds being rounded up to 60 seconds.", new Object[0]);
                    } else {
                        j3 = j2;
                    }
                    PeriodicSync periodicSync = new PeriodicSync(account, str, bundle, j3);
                    p110z1.PeriodicSync.flexTime.set(periodicSync, j);
                    getSyncManager().getSyncStorageEngine().addPeriodicSync(periodicSync, callingUserId);
                } else {
                    syncManager.scheduleSync(account, callingUserId, callingUid, str, bundle, j * 1000, 1000 * j2, false);
                }
            }
        } finally {
            restoreCallingIdentity(clearCallingIdentity);
        }
    }

    @Override // com.lody.virtual.server.interfaces.IContentService
    public void cancelSync(Account account, String str) {
        if (str == null || str.length() != 0) {
            int callingUserId = VUserHandle.getCallingUserId();
            long clearCallingIdentity = clearCallingIdentity();
            try {
                SyncManager syncManager = getSyncManager();
                if (syncManager != null) {
                    syncManager.clearScheduledSyncOperations(account, callingUserId, str);
                    syncManager.cancelActiveSync(account, callingUserId, str);
                }
            } finally {
                restoreCallingIdentity(clearCallingIdentity);
            }
        } else {
            throw new IllegalArgumentException("Authority must be non-empty");
        }
    }

    @Override // com.lody.virtual.server.interfaces.IContentService
    public SyncAdapterType[] getSyncAdapterTypes() {
        VUserHandle.getCallingUserId();
        long clearCallingIdentity = clearCallingIdentity();
        try {
            return getSyncManager().getSyncAdapterTypes();
        } finally {
            restoreCallingIdentity(clearCallingIdentity);
        }
    }

    @Override // com.lody.virtual.server.interfaces.IContentService
    public boolean getSyncAutomatically(Account account, String str) {
        int callingUserId = VUserHandle.getCallingUserId();
        long clearCallingIdentity = clearCallingIdentity();
        try {
            SyncManager syncManager = getSyncManager();
            if (syncManager != null) {
                return syncManager.getSyncStorageEngine().getSyncAutomatically(account, callingUserId, str);
            }
            restoreCallingIdentity(clearCallingIdentity);
            return false;
        } finally {
            restoreCallingIdentity(clearCallingIdentity);
        }
    }

    @Override // com.lody.virtual.server.interfaces.IContentService
    public void setSyncAutomatically(Account account, String str, boolean z) {
        if (!TextUtils.isEmpty(str)) {
            int callingUserId = VUserHandle.getCallingUserId();
            long clearCallingIdentity = clearCallingIdentity();
            try {
                SyncManager syncManager = getSyncManager();
                if (syncManager != null) {
                    syncManager.getSyncStorageEngine().setSyncAutomatically(account, callingUserId, str, z);
                }
            } finally {
                restoreCallingIdentity(clearCallingIdentity);
            }
        } else {
            throw new IllegalArgumentException("Authority must be non-empty");
        }
    }

    @Override // com.lody.virtual.server.interfaces.IContentService
    public void addPeriodicSync(Account account, String str, Bundle bundle, long j) {
        if (account == null) {
            throw new IllegalArgumentException("Account must not be null");
        } else if (!TextUtils.isEmpty(str)) {
            int callingUserId = VUserHandle.getCallingUserId();
            if (j < 60) {
                VLog.m18986w(TAG, "Requested poll frequency of " + j + " seconds being rounded up to 60 seconds.", new Object[0]);
                j = 60L;
            }
            long clearCallingIdentity = clearCallingIdentity();
            try {
                PeriodicSync periodicSync = new PeriodicSync(account, str, bundle, j);
                p110z1.PeriodicSync.flexTime.set(periodicSync, SyncStorageEngine.calculateDefaultFlexTime(j));
                getSyncManager().getSyncStorageEngine().addPeriodicSync(periodicSync, callingUserId);
            } finally {
                restoreCallingIdentity(clearCallingIdentity);
            }
        } else {
            throw new IllegalArgumentException("Authority must not be empty.");
        }
    }

    @Override // com.lody.virtual.server.interfaces.IContentService
    public void removePeriodicSync(Account account, String str, Bundle bundle) {
        if (account == null) {
            throw new IllegalArgumentException("Account must not be null");
        } else if (!TextUtils.isEmpty(str)) {
            int callingUserId = VUserHandle.getCallingUserId();
            long clearCallingIdentity = clearCallingIdentity();
            try {
                getSyncManager().getSyncStorageEngine().removePeriodicSync(new PeriodicSync(account, str, bundle, 0L), callingUserId);
            } finally {
                restoreCallingIdentity(clearCallingIdentity);
            }
        } else {
            throw new IllegalArgumentException("Authority must not be empty");
        }
    }

    @Override // com.lody.virtual.server.interfaces.IContentService
    public List<PeriodicSync> getPeriodicSyncs(Account account, String str) {
        if (account == null) {
            throw new IllegalArgumentException("Account must not be null");
        } else if (!TextUtils.isEmpty(str)) {
            int callingUserId = VUserHandle.getCallingUserId();
            long clearCallingIdentity = clearCallingIdentity();
            try {
                return getSyncManager().getSyncStorageEngine().getPeriodicSyncs(account, callingUserId, str);
            } finally {
                restoreCallingIdentity(clearCallingIdentity);
            }
        } else {
            throw new IllegalArgumentException("Authority must not be empty");
        }
    }

    @Override // com.lody.virtual.server.interfaces.IContentService
    public int getIsSyncable(Account account, String str) {
        int callingUserId = VUserHandle.getCallingUserId();
        long clearCallingIdentity = clearCallingIdentity();
        try {
            SyncManager syncManager = getSyncManager();
            if (syncManager != null) {
                return syncManager.getIsSyncable(account, callingUserId, str);
            }
            restoreCallingIdentity(clearCallingIdentity);
            return -1;
        } finally {
            restoreCallingIdentity(clearCallingIdentity);
        }
    }

    @Override // com.lody.virtual.server.interfaces.IContentService
    public void setIsSyncable(Account account, String str, int i) {
        if (!TextUtils.isEmpty(str)) {
            int callingUserId = VUserHandle.getCallingUserId();
            long clearCallingIdentity = clearCallingIdentity();
            try {
                SyncManager syncManager = getSyncManager();
                if (syncManager != null) {
                    syncManager.getSyncStorageEngine().setIsSyncable(account, callingUserId, str, i);
                }
            } finally {
                restoreCallingIdentity(clearCallingIdentity);
            }
        } else {
            throw new IllegalArgumentException("Authority must not be empty");
        }
    }

    @Override // com.lody.virtual.server.interfaces.IContentService
    public boolean getMasterSyncAutomatically() {
        int callingUserId = VUserHandle.getCallingUserId();
        long clearCallingIdentity = clearCallingIdentity();
        try {
            SyncManager syncManager = getSyncManager();
            if (syncManager != null) {
                return syncManager.getSyncStorageEngine().getMasterSyncAutomatically(callingUserId);
            }
            restoreCallingIdentity(clearCallingIdentity);
            return false;
        } finally {
            restoreCallingIdentity(clearCallingIdentity);
        }
    }

    @Override // com.lody.virtual.server.interfaces.IContentService
    public void setMasterSyncAutomatically(boolean z) {
        int callingUserId = VUserHandle.getCallingUserId();
        long clearCallingIdentity = clearCallingIdentity();
        try {
            SyncManager syncManager = getSyncManager();
            if (syncManager != null) {
                syncManager.getSyncStorageEngine().setMasterSyncAutomatically(z, callingUserId);
            }
        } finally {
            restoreCallingIdentity(clearCallingIdentity);
        }
    }

    @Override // com.lody.virtual.server.interfaces.IContentService
    public boolean isSyncActive(Account account, String str) {
        int callingUserId = VUserHandle.getCallingUserId();
        long clearCallingIdentity = clearCallingIdentity();
        try {
            SyncManager syncManager = getSyncManager();
            if (syncManager != null) {
                return syncManager.getSyncStorageEngine().isSyncActive(account, callingUserId, str);
            }
            restoreCallingIdentity(clearCallingIdentity);
            return false;
        } finally {
            restoreCallingIdentity(clearCallingIdentity);
        }
    }

    @Override // com.lody.virtual.server.interfaces.IContentService
    public List<SyncInfo> getCurrentSyncs() {
        int callingUserId = VUserHandle.getCallingUserId();
        long clearCallingIdentity = clearCallingIdentity();
        try {
            List<VSyncInfo> currentSyncsCopy = getSyncManager().getSyncStorageEngine().getCurrentSyncsCopy(callingUserId);
            ArrayList arrayList = new ArrayList(currentSyncsCopy.size());
            for (VSyncInfo vSyncInfo : currentSyncsCopy) {
                arrayList.add(vSyncInfo.toSyncInfo());
            }
            return arrayList;
        } finally {
            restoreCallingIdentity(clearCallingIdentity);
        }
    }

    @Override // com.lody.virtual.server.interfaces.IContentService
    public SyncStatusInfo getSyncStatus(Account account, String str) {
        if (!TextUtils.isEmpty(str)) {
            int callingUserId = VUserHandle.getCallingUserId();
            long clearCallingIdentity = clearCallingIdentity();
            try {
                SyncManager syncManager = getSyncManager();
                if (syncManager != null) {
                    return syncManager.getSyncStorageEngine().getStatusByAccountAndAuthority(account, callingUserId, str);
                }
                restoreCallingIdentity(clearCallingIdentity);
                return null;
            } finally {
                restoreCallingIdentity(clearCallingIdentity);
            }
        } else {
            throw new IllegalArgumentException("Authority must not be empty");
        }
    }

    @Override // com.lody.virtual.server.interfaces.IContentService
    public boolean isSyncPending(Account account, String str) {
        int callingUserId = VUserHandle.getCallingUserId();
        long clearCallingIdentity = clearCallingIdentity();
        try {
            SyncManager syncManager = getSyncManager();
            if (syncManager != null) {
                return syncManager.getSyncStorageEngine().isSyncPending(account, callingUserId, str);
            }
            restoreCallingIdentity(clearCallingIdentity);
            return false;
        } finally {
            restoreCallingIdentity(clearCallingIdentity);
        }
    }

    @Override // com.lody.virtual.server.interfaces.IContentService
    public void addStatusChangeListener(int i, ISyncStatusObserver iSyncStatusObserver) {
        long clearCallingIdentity = clearCallingIdentity();
        try {
            SyncManager syncManager = getSyncManager();
            if (!(syncManager == null || iSyncStatusObserver == null)) {
                syncManager.getSyncStorageEngine().addStatusChangeListener(i, iSyncStatusObserver);
            }
        } finally {
            restoreCallingIdentity(clearCallingIdentity);
        }
    }

    @Override // com.lody.virtual.server.interfaces.IContentService
    public void removeStatusChangeListener(ISyncStatusObserver iSyncStatusObserver) {
        long clearCallingIdentity = clearCallingIdentity();
        try {
            SyncManager syncManager = getSyncManager();
            if (!(syncManager == null || iSyncStatusObserver == null)) {
                syncManager.getSyncStorageEngine().removeStatusChangeListener(iSyncStatusObserver);
            }
        } finally {
            restoreCallingIdentity(clearCallingIdentity);
        }
    }

    /* loaded from: classes.dex */
    public static final class ObserverNode {
        public static final int DELETE_TYPE = 2;
        public static final int INSERT_TYPE = 0;
        public static final int UPDATE_TYPE = 1;
        private String mName;
        private ArrayList<ObserverNode> mChildren = new ArrayList<>();
        private ArrayList<ObserverEntry> mObservers = new ArrayList<>();

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes.dex */
        public class ObserverEntry implements IBinder.DeathRecipient {
            public final boolean notifyForDescendants;
            public final IContentObserver observer;
            private final Object observersLock;
            public final int pid;
            public final int uid;
            private final int userHandle;

            public ObserverEntry(IContentObserver iContentObserver, boolean z, Object obj, int i, int i2, int i3) {
                this.observersLock = obj;
                this.observer = iContentObserver;
                this.uid = i;
                this.pid = i2;
                this.userHandle = i3;
                this.notifyForDescendants = z;
                try {
                    this.observer.asBinder().linkToDeath(this, 0);
                } catch (RemoteException unused) {
                    binderDied();
                }
            }

            @Override // android.os.IBinder.DeathRecipient
            public void binderDied() {
                synchronized (this.observersLock) {
                    ObserverNode.this.removeObserverLocked(this.observer);
                }
            }
        }

        public ObserverNode(String str) {
            this.mName = str;
        }

        private String getUriSegment(Uri uri, int i) {
            if (uri == null) {
                return null;
            }
            if (i == 0) {
                return uri.getAuthority();
            }
            return uri.getPathSegments().get(i - 1);
        }

        private int countUriSegments(Uri uri) {
            if (uri == null) {
                return 0;
            }
            return uri.getPathSegments().size() + 1;
        }

        public void addObserverLocked(Uri uri, IContentObserver iContentObserver, boolean z, Object obj, int i, int i2, int i3) {
            addObserverLocked(uri, 0, iContentObserver, z, obj, i, i2, i3);
        }

        private void addObserverLocked(Uri uri, int i, IContentObserver iContentObserver, boolean z, Object obj, int i2, int i3, int i4) {
            if (i == countUriSegments(uri)) {
                this.mObservers.add(new ObserverEntry(iContentObserver, z, obj, i2, i3, i4));
                return;
            }
            String uriSegment = getUriSegment(uri, i);
            if (uriSegment != null) {
                int size = this.mChildren.size();
                for (int i5 = 0; i5 < size; i5++) {
                    ObserverNode observerNode = this.mChildren.get(i5);
                    if (observerNode.mName.equals(uriSegment)) {
                        observerNode.addObserverLocked(uri, i + 1, iContentObserver, z, obj, i2, i3, i4);
                        return;
                    }
                }
                ObserverNode observerNode2 = new ObserverNode(uriSegment);
                this.mChildren.add(observerNode2);
                observerNode2.addObserverLocked(uri, i + 1, iContentObserver, z, obj, i2, i3, i4);
                return;
            }
            throw new IllegalArgumentException("Invalid Uri (" + uri + ") used for observer");
        }

        public boolean removeObserverLocked(IContentObserver iContentObserver) {
            int size = this.mChildren.size();
            int i = 0;
            while (i < size) {
                if (this.mChildren.get(i).removeObserverLocked(iContentObserver)) {
                    this.mChildren.remove(i);
                    i--;
                    size--;
                }
                i++;
            }
            IBinder asBinder = iContentObserver.asBinder();
            int size2 = this.mObservers.size();
            int i2 = 0;
            while (true) {
                if (i2 >= size2) {
                    break;
                }
                ObserverEntry observerEntry = this.mObservers.get(i2);
                if (observerEntry.observer.asBinder() == asBinder) {
                    this.mObservers.remove(i2);
                    asBinder.unlinkToDeath(observerEntry, 0);
                    break;
                }
                i2++;
            }
            return this.mChildren.size() == 0 && this.mObservers.size() == 0;
        }

        private void collectMyObserversLocked(boolean z, IContentObserver iContentObserver, boolean z2, int i, ArrayList<ObserverCall> arrayList) {
            int size = this.mObservers.size();
            IBinder asBinder = iContentObserver == null ? null : iContentObserver.asBinder();
            for (int i2 = 0; i2 < size; i2++) {
                ObserverEntry observerEntry = this.mObservers.get(i2);
                boolean z3 = observerEntry.observer.asBinder() == asBinder;
                if ((!z3 || z2) && ((i == -1 || observerEntry.userHandle == -1 || i == observerEntry.userHandle) && (z || observerEntry.notifyForDescendants))) {
                    arrayList.add(new ObserverCall(this, observerEntry.observer, z3));
                }
            }
        }

        public void collectObserversLocked(Uri uri, int i, IContentObserver iContentObserver, boolean z, int i2, ArrayList<ObserverCall> arrayList) {
            String str;
            if (i >= countUriSegments(uri)) {
                collectMyObserversLocked(true, iContentObserver, z, i2, arrayList);
                str = null;
            } else {
                String uriSegment = getUriSegment(uri, i);
                collectMyObserversLocked(false, iContentObserver, z, i2, arrayList);
                str = uriSegment;
            }
            int size = this.mChildren.size();
            for (int i3 = 0; i3 < size; i3++) {
                ObserverNode observerNode = this.mChildren.get(i3);
                if (str == null || observerNode.mName.equals(str)) {
                    observerNode.collectObserversLocked(uri, i + 1, iContentObserver, z, i2, arrayList);
                    if (str != null) {
                        return;
                    }
                }
            }
        }
    }
}
