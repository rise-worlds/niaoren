package com.lody.virtual.client.ipc;

import android.accounts.Account;
import android.content.ISyncStatusObserver;
import android.content.PeriodicSync;
import android.content.SyncAdapterType;
import android.content.SyncInfo;
import android.content.SyncRequest;
import android.content.SyncStatusInfo;
import android.database.IContentObserver;
import android.net.Uri;
import android.os.Bundle;
import android.os.RemoteException;
import com.lody.virtual.helper.utils.IInterfaceUtils;
import com.lody.virtual.server.interfaces.IContentService;
import java.util.List;

/* loaded from: classes.dex */
public class VContentManager {
    private static final VContentManager sInstance = new VContentManager();
    private IContentService mService;

    public static VContentManager get() {
        return sInstance;
    }

    public IContentService getService() {
        if (!IInterfaceUtils.isAlive(this.mService)) {
            synchronized (this) {
                this.mService = (IContentService) LocalProxyUtils.genProxy(IContentService.class, getRemoteInterface());
            }
        }
        return this.mService;
    }

    private Object getRemoteInterface() {
        return IContentService.Stub.asInterface(ServiceManagerNative.getService(ServiceManagerNative.CONTENT));
    }

    public void unregisterContentObserver(IContentObserver iContentObserver) throws RemoteException {
        getService().unregisterContentObserver(iContentObserver);
    }

    public void registerContentObserver(Uri uri, boolean z, IContentObserver iContentObserver, int i) throws RemoteException {
        getService().registerContentObserver(uri, z, iContentObserver, i);
    }

    public void notifyChange(Uri uri, IContentObserver iContentObserver, boolean z, boolean z2, int i) throws RemoteException {
        getService().notifyChange(uri, iContentObserver, z, z2, i);
    }

    public void requestSync(Account account, String str, Bundle bundle) throws RemoteException {
        getService().requestSync(account, str, bundle);
    }

    public void sync(SyncRequest syncRequest) throws RemoteException {
        getService().sync(syncRequest);
    }

    public void cancelSync(Account account, String str) throws RemoteException {
        getService().cancelSync(account, str);
    }

    public boolean getSyncAutomatically(Account account, String str) throws RemoteException {
        return getService().getSyncAutomatically(account, str);
    }

    public void setSyncAutomatically(Account account, String str, boolean z) throws RemoteException {
        getService().setSyncAutomatically(account, str, z);
    }

    public List<PeriodicSync> getPeriodicSyncs(Account account, String str) throws RemoteException {
        return getService().getPeriodicSyncs(account, str);
    }

    public void addPeriodicSync(Account account, String str, Bundle bundle, long j) throws RemoteException {
        getService().addPeriodicSync(account, str, bundle, j);
    }

    public void removePeriodicSync(Account account, String str, Bundle bundle) throws RemoteException {
        getService().removePeriodicSync(account, str, bundle);
    }

    public int getIsSyncable(Account account, String str) throws RemoteException {
        return getService().getIsSyncable(account, str);
    }

    public void setIsSyncable(Account account, String str, int i) throws RemoteException {
        getService().setIsSyncable(account, str, i);
    }

    public void setMasterSyncAutomatically(boolean z) throws RemoteException {
        getService().setMasterSyncAutomatically(z);
    }

    public boolean getMasterSyncAutomatically() throws RemoteException {
        return getService().getMasterSyncAutomatically();
    }

    public boolean isSyncActive(Account account, String str) throws RemoteException {
        return getService().isSyncActive(account, str);
    }

    public List<SyncInfo> getCurrentSyncs() throws RemoteException {
        return getService().getCurrentSyncs();
    }

    public SyncAdapterType[] getSyncAdapterTypes() throws RemoteException {
        return getService().getSyncAdapterTypes();
    }

    public SyncStatusInfo getSyncStatus(Account account, String str) throws RemoteException {
        return getService().getSyncStatus(account, str);
    }

    public boolean isSyncPending(Account account, String str) throws RemoteException {
        return getService().isSyncPending(account, str);
    }

    public void addStatusChangeListener(int i, ISyncStatusObserver iSyncStatusObserver) throws RemoteException {
        getService().addStatusChangeListener(i, iSyncStatusObserver);
    }

    public void removeStatusChangeListener(ISyncStatusObserver iSyncStatusObserver) throws RemoteException {
        getService().removeStatusChangeListener(iSyncStatusObserver);
    }
}
