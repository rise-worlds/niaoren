package com.lody.virtual.server;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import com.lody.virtual.client.core.VirtualCore;
import com.lody.virtual.client.ipc.ServiceManagerNative;
import com.lody.virtual.client.stub.KeepAliveService;
import com.lody.virtual.helper.compat.BundleCompat;
import com.lody.virtual.helper.compat.NotificationChannelCompat;
import com.lody.virtual.server.accounts.VAccountManagerService;
import com.lody.virtual.server.content.VContentService;
import com.lody.virtual.server.device.VDeviceManagerService;
import com.lody.virtual.server.interfaces.IServiceFetcher;
import com.lody.virtual.server.job.VJobSchedulerService;
import com.lody.virtual.server.location.VirtualLocationService;
import com.lody.virtual.server.notification.VNotificationManagerService;
import com.lody.virtual.server.am.VActivityManagerService;
import com.lody.virtual.server.pm.VAppManagerService;
import com.lody.virtual.server.pm.VPackageManagerService;
import com.lody.virtual.server.pm.VUserManagerService;
import com.lody.virtual.server.vs.VirtualStorageService;

/* loaded from: classes.dex */
public final class BinderProvider extends ContentProvider {
    private static boolean sInitialized = false;
    private final ServiceFetcher mServiceFetcher = new ServiceFetcher();

    @Override // android.content.ContentProvider
    public int delete(Uri uri, String str, String[] strArr) {
        return 0;
    }

    @Override // android.content.ContentProvider
    public String getType(Uri uri) {
        return null;
    }

    @Override // android.content.ContentProvider
    public Uri insert(Uri uri, ContentValues contentValues) {
        return null;
    }

    @Override // android.content.ContentProvider
    public Cursor query(Uri uri, String[] strArr, String str, String[] strArr2, String str2) {
        return null;
    }

    @Override // android.content.ContentProvider
    public int update(Uri uri, ContentValues contentValues, String str, String[] strArr) {
        return 0;
    }

    @Override // android.content.ContentProvider
    public boolean onCreate() {
        return init();
    }

    public static boolean isInitialized() {
        return sInitialized;
    }

    private boolean init() {
        if (sInitialized) {
            return false;
        }
        Context context = getContext();
        if (context != null) {
            if (Build.VERSION.SDK_INT >= 26) {
                NotificationChannelCompat.checkOrCreateChannel(context, NotificationChannelCompat.DAEMON_ID, "daemon");
                NotificationChannelCompat.checkOrCreateChannel(context, NotificationChannelCompat.DEFAULT_ID, "default");
            }
            try {
                context.startService(new Intent(context, KeepAliveService.class));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (!VirtualCore.get().isStartup()) {
            return false;
        }
        VPackageManagerService.systemReady();
        addService(ServiceManagerNative.PACKAGE, VPackageManagerService.get());
        addService(ServiceManagerNative.ACTIVITY, VActivityManagerService.get());
        addService(ServiceManagerNative.USER, VUserManagerService.get());
        VAppManagerService.systemReady();
        addService("app", VAppManagerService.get());
        if (Build.VERSION.SDK_INT >= 21) {
            addService(ServiceManagerNative.JOB, VJobSchedulerService.get());
        }
        VNotificationManagerService.systemReady(context);
        addService(ServiceManagerNative.NOTIFICATION, VNotificationManagerService.get());
        VAppManagerService.get().scanApps();
        VAccountManagerService.systemReady();
        VContentService.systemReady();
        addService("account", VAccountManagerService.get());
        addService(ServiceManagerNative.CONTENT, VContentService.get());
        addService(ServiceManagerNative.f10497VS, VirtualStorageService.get());
        addService("device", VDeviceManagerService.get());
        addService(ServiceManagerNative.VIRTUAL_LOC, VirtualLocationService.get());
        sInitialized = true;
        return true;
    }

    private void addService(String str, IBinder iBinder) {
        ServiceCache.addService(str, iBinder);
    }

    @Override // android.content.ContentProvider
    public Bundle call(String str, String str2, Bundle bundle) {
        if (!sInitialized) {
            init();
        }
        if (!"@".equals(str)) {
            return null;
        }
        Bundle bundle2 = new Bundle();
        BundleCompat.putBinder(bundle2, "_VA_|_binder_", this.mServiceFetcher);
        return bundle2;
    }

    /* loaded from: classes.dex */
    private class ServiceFetcher extends IServiceFetcher.Stub {
        private ServiceFetcher() {
        }

        @Override // com.lody.virtual.server.interfaces.IServiceFetcher
        public IBinder getService(String str) throws RemoteException {
            if (str != null) {
                return ServiceCache.getService(str);
            }
            return null;
        }

        @Override // com.lody.virtual.server.interfaces.IServiceFetcher
        public void addService(String str, IBinder iBinder) throws RemoteException {
            if (str != null && iBinder != null) {
                ServiceCache.addService(str, iBinder);
            }
        }

        @Override // com.lody.virtual.server.interfaces.IServiceFetcher
        public void removeService(String str) throws RemoteException {
            if (str != null) {
                ServiceCache.removeService(str);
            }
        }
    }
}
