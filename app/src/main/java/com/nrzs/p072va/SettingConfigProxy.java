package com.nrzs.p072va;

import com.lody.virtual.client.core.SettingConfig;

/* renamed from: com.nrzs.va.SettingConfigProxy */
/* loaded from: classes2.dex */
public abstract class SettingConfigProxy extends SettingConfig {
    @Override // com.lody.virtual.client.core.SettingConfig
    public abstract String get32bitEnginePackageName();

    @Override // com.lody.virtual.client.core.SettingConfig
    public abstract String get64bitEnginePackageName();

    public String getExtPackageName() {
        return null;
    }

    @Override // com.lody.virtual.client.core.SettingConfig
    public abstract String getHostPackageName();

    public String getMainPackageName() {
        return null;
    }

    @Override // com.lody.virtual.client.core.SettingConfig
    public boolean isAllowServiceStartForeground() {
        return false;
    }

    @Override // com.lody.virtual.client.core.SettingConfig
    public boolean isEnableAppFileSystemIsolation() {
        return false;
    }

    @Override // com.lody.virtual.client.core.SettingConfig
    public boolean isEnableIORedirect() {
        return true;
    }

    public String get32bitHelperAuthority() {
        return get32bitEnginePackageName() + ".virtual.service.32bit_helper";
    }

    @Override // com.lody.virtual.client.core.SettingConfig
    public String getProxyFileContentProviderAuthority() {
        return getHostPackageName() + ".virtual.fileprovider";
    }

    @Override // com.lody.virtual.client.core.SettingConfig
    public SettingConfig.AppLibConfig getAppLibConfig(String str) {
        return SettingConfig.AppLibConfig.UseRealLib;
    }
}
