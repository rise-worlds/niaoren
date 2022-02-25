package com.lody.virtual.client.ipc;

import android.app.Notification;
import android.os.RemoteException;
import com.lody.virtual.client.core.VirtualCore;
import com.lody.virtual.helper.utils.IInterfaceUtils;
import com.lody.virtual.server.interfaces.INotificationManager;
import com.lody.virtual.server.notification.NotificationCompat;

/* loaded from: classes.dex */
public class VNotificationManager {
    private static final VNotificationManager sInstance = new VNotificationManager();
    private final NotificationCompat mNotificationCompat = NotificationCompat.create();
    private INotificationManager mService;

    public INotificationManager getService() {
        INotificationManager iNotificationManager = this.mService;
        if (iNotificationManager == null || !IInterfaceUtils.isAlive(iNotificationManager)) {
            synchronized (VNotificationManager.class) {
                this.mService = (INotificationManager) LocalProxyUtils.genProxy(INotificationManager.class, getRemoteInterface());
            }
        }
        return this.mService;
    }

    private Object getRemoteInterface() {
        return INotificationManager.Stub.asInterface(ServiceManagerNative.getService(ServiceManagerNative.NOTIFICATION));
    }

    private VNotificationManager() {
    }

    public static VNotificationManager get() {
        return sInstance;
    }

    public boolean dealNotification(int i, Notification notification, String str) {
        if (notification == null) {
            return false;
        }
        return VirtualCore.get().getHostPkg().equals(str) || this.mNotificationCompat.dealNotification(i, notification, str);
    }

    public int dealNotificationId(int i, String str, String str2, int i2) {
        try {
            return getService().dealNotificationId(i, str, str2, i2);
        } catch (RemoteException e) {
            e.printStackTrace();
            return i;
        }
    }

    public String dealNotificationTag(int i, String str, String str2, int i2) {
        try {
            return getService().dealNotificationTag(i, str, str2, i2);
        } catch (RemoteException e) {
            e.printStackTrace();
            return str2;
        }
    }

    public boolean areNotificationsEnabledForPackage(String str, int i) {
        try {
            return getService().areNotificationsEnabledForPackage(str, i);
        } catch (RemoteException e) {
            e.printStackTrace();
            return true;
        }
    }

    public void setNotificationsEnabledForPackage(String str, boolean z, int i) {
        try {
            getService().setNotificationsEnabledForPackage(str, z, i);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void addNotification(int i, String str, String str2, int i2) {
        try {
            getService().addNotification(i, str, str2, i2);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void cancelAllNotification(String str, int i) {
        try {
            getService().cancelAllNotification(str, i);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}
