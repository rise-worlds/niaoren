package com.lody.virtual;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import com.lody.virtual.client.core.VirtualCore;
import com.lody.virtual.helper.utils.VLog;
import com.lody.virtual.remote.InstallOptions;
import com.lody.virtual.remote.InstallResult;
import java.util.HashSet;
import java.util.Set;

/* loaded from: classes.dex */
public class GmsSupport {
    public static final String GMS_PKG = "com.google.android.gms";
    private static final HashSet<String> GOOGLE_APP = new HashSet<>();
    private static final HashSet<String> GOOGLE_SERVICE = new HashSet<>();
    public static final String GSF_PKG = "com.google.android.gsf";
    private static final String TAG = "GmsSupport";
    public static final String VENDING_PKG = "com.android.vending";

    static {
        GOOGLE_APP.add(VENDING_PKG);
        GOOGLE_APP.add("com.google.android.play.games");
        GOOGLE_APP.add("com.google.android.wearable.app");
        GOOGLE_APP.add("com.google.android.wearable.app.cn");
        GOOGLE_SERVICE.add(GMS_PKG);
        GOOGLE_SERVICE.add(GSF_PKG);
        GOOGLE_SERVICE.add("com.google.android.gsf.login");
        GOOGLE_SERVICE.add("com.google.android.backuptransport");
        GOOGLE_SERVICE.add("com.google.android.backup");
        GOOGLE_SERVICE.add("com.google.android.configupdater");
        GOOGLE_SERVICE.add("com.google.android.syncadapters.contacts");
        GOOGLE_SERVICE.add("com.google.android.feedback");
        GOOGLE_SERVICE.add("com.google.android.onetimeinitializer");
        GOOGLE_SERVICE.add("com.google.android.partnersetup");
        GOOGLE_SERVICE.add("com.google.android.setupwizard");
        GOOGLE_SERVICE.add("com.google.android.syncadapters.calendar");
    }

    public static boolean isGoogleFrameworkInstalled() {
        return VirtualCore.get().isAppInstalled(GMS_PKG);
    }

    public static boolean isGoogleService(String str) {
        return GOOGLE_SERVICE.contains(str);
    }

    public static boolean isGoogleAppOrService(String str) {
        return GOOGLE_APP.contains(str) || GOOGLE_SERVICE.contains(str);
    }

    public static boolean isOutsideGoogleFrameworkExist() {
        return VirtualCore.get().isOutsideInstalled(GMS_PKG);
    }

    private static void installPackages(Set<String> set, int i) {
        VirtualCore virtualCore = VirtualCore.get();
        for (String str : set) {
            if (!virtualCore.isAppInstalledAsUser(i, str)) {
                try {
                    ApplicationInfo applicationInfo = VirtualCore.get().getUnHookPackageManager().getApplicationInfo(str, 0);
                    if (i == 0) {
                        InstallResult installPackageSync = virtualCore.installPackageSync(applicationInfo.sourceDir, InstallOptions.makeOptions(true, false, InstallOptions.UpdateStrategy.FORCE_UPDATE));
                        if (installPackageSync.isSuccess) {
                            String str2 = TAG;
                            VLog.m18986w(str2, "install gms pkg success:" + applicationInfo.packageName, new Object[0]);
                        } else {
                            String str3 = TAG;
                            VLog.m18986w(str3, "install gms pkg fail:" + applicationInfo.packageName + ",error : " + installPackageSync.error, new Object[0]);
                        }
                    } else {
                        virtualCore.installPackageAsUser(i, str);
                    }
                } catch (PackageManager.NameNotFoundException unused) {
                }
            }
        }
    }

    public static void installGApps(int i) {
        installPackages(GOOGLE_SERVICE, i);
        installPackages(GOOGLE_APP, i);
    }

    public static void remove(String str) {
        GOOGLE_SERVICE.remove(str);
        GOOGLE_APP.remove(str);
    }

    public static boolean isInstalledGoogleService() {
        return VirtualCore.get().isAppInstalled(GMS_PKG);
    }
}
