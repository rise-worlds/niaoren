package com.lody.virtual.client.core;

import android.content.Intent;

/* loaded from: classes.dex */
public abstract class SettingConfig {

    /* loaded from: classes.dex */
    public enum AppLibConfig {
        UseRealLib,
        UseOwnLib
    }

    public abstract String get32bitEnginePackageName();

    public abstract String get64bitEnginePackageName();

    public FakeWifiStatus getFakeWifiStatus() {
        return null;
    }

    public abstract String getHostPackageName();

    public boolean isAllowCreateShortcut() {
        return true;
    }

    public boolean isAllowServiceStartForeground() {
        return false;
    }

    public boolean isDisableDrawOverlays(String str) {
        return false;
    }

    public boolean isEnableAppFileSystemIsolation() {
        return false;
    }

    public boolean isEnableIORedirect() {
        return true;
    }

    public boolean isHideForegroundNotification() {
        return false;
    }

    public boolean isHostIntent(Intent intent) {
        return false;
    }

    public boolean isUseRealDataDir(String str) {
        return false;
    }

    public boolean isUseRealLibDir(String str) {
        return false;
    }

    public Intent onHandleLauncherIntent(Intent intent) {
        return null;
    }

    public String getBinderProviderAuthority() {
        return getHostPackageName() + ".virtual.service.BinderProvider";
    }

    public String getHelperAuthority() {
        return get64bitEnginePackageName() + ".virtual.service.64bit_helper";
    }

    public String getProxyFileContentProviderAuthority() {
        return getHostPackageName() + ".virtual.fileprovider";
    }

    public AppLibConfig getAppLibConfig(String str) {
        return AppLibConfig.UseRealLib;
    }

    /* loaded from: classes.dex */
    public static class FakeWifiStatus {
        public static String DEFAULT_BSSID = "66:55:44:33:22:11";
        public static String DEFAULT_MAC = "11:22:33:44:55:66";
        public static String DEFAULT_SSID = "VA_SSID";

        public String getSSID() {
            return DEFAULT_SSID;
        }

        public String getBSSID() {
            return DEFAULT_BSSID;
        }

        public String getMAC() {
            return DEFAULT_MAC;
        }
    }
}
