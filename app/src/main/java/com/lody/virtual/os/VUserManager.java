package com.lody.virtual.os;

import android.graphics.Bitmap;
import android.os.RemoteException;
import android.util.Log;
import com.lody.virtual.client.ipc.ServiceManagerNative;
import com.lody.virtual.server.interfaces.IUserManager;
import java.util.List;

/* renamed from: com.lody.virtual.os.VUserManager */
/* loaded from: classes.dex */
public class VUserManager {
    public static final String DISALLOW_CONFIG_BLUETOOTH = "no_config_bluetooth";
    public static final String DISALLOW_CONFIG_CREDENTIALS = "no_config_credentials";
    public static final String DISALLOW_CONFIG_WIFI = "no_config_wifi";
    public static final String DISALLOW_INSTALL_APPS = "no_install_apps";
    public static final String DISALLOW_INSTALL_UNKNOWN_SOURCES = "no_install_unknown_sources";
    public static final String DISALLOW_MODIFY_ACCOUNTS = "no_modify_accounts";
    public static final String DISALLOW_REMOVE_USER = "no_remove_user";
    public static final String DISALLOW_SHARE_LOCATION = "no_share_location";
    public static final String DISALLOW_UNINSTALL_APPS = "no_uninstall_apps";
    public static final String DISALLOW_USB_FILE_TRANSFER = "no_usb_file_transfer";
    private static String TAG = "VUserManager";
    private static VUserManager sInstance;
    private final IUserManager mService;

    public static int getMaxSupportedUsers() {
        return Integer.MAX_VALUE;
    }

    public boolean isUserAGoat() {
        return false;
    }

    public static synchronized VUserManager get() {
        VUserManager vUserManager;
        synchronized (VUserManager.class) {
            if (sInstance == null) {
                sInstance = new VUserManager(IUserManager.Stub.asInterface(ServiceManagerNative.getService(ServiceManagerNative.USER)));
            }
            vUserManager = sInstance;
        }
        return vUserManager;
    }

    public VUserManager(IUserManager iUserManager) {
        this.mService = iUserManager;
    }

    public static boolean supportsMultipleUsers() {
        return getMaxSupportedUsers() > 1;
    }

    public int getUserHandle() {
        return VUserHandle.myUserId();
    }

    public String getUserName() {
        try {
            return this.mService.getUserInfo(getUserHandle()).name;
        } catch (RemoteException e) {
            Log.w(TAG, "Could not get user name", e);
            return "";
        }
    }

    public VUserInfo getUserInfo(int i) {
        try {
            return this.mService.getUserInfo(i);
        } catch (RemoteException e) {
            Log.w(TAG, "Could not get user info", e);
            return null;
        }
    }

    public long getSerialNumberForUser(VUserHandle vUserHandle) {
        return getUserSerialNumber(vUserHandle.getIdentifier());
    }

    public VUserHandle getUserForSerialNumber(long j) {
        int userHandle = getUserHandle((int) j);
        if (userHandle >= 0) {
            return new VUserHandle(userHandle);
        }
        return null;
    }

    public VUserInfo createUser(String str, int i) {
        try {
            return this.mService.createUser(str, i);
        } catch (RemoteException e) {
            Log.w(TAG, "Could not create a user", e);
            return null;
        }
    }

    public int getUserCount() {
        List<VUserInfo> users = getUsers();
        if (users != null) {
            return users.size();
        }
        return 1;
    }

    public List<VUserInfo> getUsers() {
        try {
            return this.mService.getUsers(false);
        } catch (RemoteException e) {
            Log.w(TAG, "Could not get user list", e);
            return null;
        }
    }

    public List<VUserInfo> getUsers(boolean z) {
        try {
            return this.mService.getUsers(z);
        } catch (RemoteException e) {
            Log.w(TAG, "Could not get user list", e);
            return null;
        }
    }

    public boolean removeUser(int i) {
        try {
            return this.mService.removeUser(i);
        } catch (RemoteException e) {
            Log.w(TAG, "Could not remove user ", e);
            return false;
        }
    }

    public void setUserName(int i, String str) {
        try {
            this.mService.setUserName(i, str);
        } catch (RemoteException e) {
            Log.w(TAG, "Could not set the user name ", e);
        }
    }

    public void setUserIcon(int i, Bitmap bitmap) {
        try {
            this.mService.setUserIcon(i, bitmap);
        } catch (RemoteException e) {
            Log.w(TAG, "Could not set the user icon ", e);
        }
    }

    public Bitmap getUserIcon(int i) {
        try {
            return this.mService.getUserIcon(i);
        } catch (RemoteException e) {
            Log.w(TAG, "Could not get the user icon ", e);
            return null;
        }
    }

    public void setGuestEnabled(boolean z) {
        try {
            this.mService.setGuestEnabled(z);
        } catch (RemoteException unused) {
            String str = TAG;
            Log.w(str, "Could not change guest account availability to " + z);
        }
    }

    public boolean isGuestEnabled() {
        try {
            return this.mService.isGuestEnabled();
        } catch (RemoteException unused) {
            Log.w(TAG, "Could not retrieve guest enabled state");
            return false;
        }
    }

    public void wipeUser(int i) {
        try {
            this.mService.wipeUser(i);
        } catch (RemoteException unused) {
            String str = TAG;
            Log.w(str, "Could not wipe user " + i);
        }
    }

    public int getUserSerialNumber(int i) {
        try {
            return this.mService.getUserSerialNumber(i);
        } catch (RemoteException unused) {
            String str = TAG;
            Log.w(str, "Could not get serial number for user " + i);
            return -1;
        }
    }

    public int getUserHandle(int i) {
        try {
            return this.mService.getUserHandle(i);
        } catch (RemoteException unused) {
            String str = TAG;
            Log.w(str, "Could not get VUserHandle for user " + i);
            return -1;
        }
    }
}
