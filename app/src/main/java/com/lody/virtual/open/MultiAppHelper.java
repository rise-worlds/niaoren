package com.lody.virtual.open;

import com.lody.virtual.client.core.VirtualCore;
import com.lody.virtual.p061os.VUserManager;
import com.lody.virtual.remote.InstalledAppInfo;

/* loaded from: classes.dex */
public class MultiAppHelper {
    public static int installExistedPackage(String str) throws IllegalStateException {
        return installExistedPackage(VirtualCore.get().getInstalledAppInfo(str, 0));
    }

    public static int installExistedPackage(InstalledAppInfo installedAppInfo) throws IllegalStateException {
        if (installedAppInfo != null) {
            int[] installedUsers = installedAppInfo.getInstalledUsers();
            int length = installedUsers.length;
            int i = 0;
            while (true) {
                if (i >= installedUsers.length) {
                    break;
                } else if (installedUsers[i] != i) {
                    length = i;
                    break;
                } else {
                    i++;
                }
            }
            if (VUserManager.get().getUserInfo(length) == null) {
                if (VUserManager.get().createUser("Space " + (length + 1), 2) == null) {
                    throw new IllegalStateException();
                }
            }
            if (VirtualCore.get().installPackageAsUser(length, installedAppInfo.packageName)) {
                return length;
            }
            throw new IllegalStateException("install fail");
        }
        throw new IllegalStateException("pkg must be installed.");
    }
}
