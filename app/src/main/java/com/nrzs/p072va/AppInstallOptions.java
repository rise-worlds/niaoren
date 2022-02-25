package com.nrzs.p072va;

import com.lody.virtual.remote.InstallOptions;

/* renamed from: com.nrzs.va.AppInstallOptions */
/* loaded from: classes2.dex */
public class AppInstallOptions {
    public InstallOptions mParams;

    public AppInstallOptions(InstallOptions installOptions) {
        this.mParams = installOptions;
    }

    public AppInstallOptions() {
        this.mParams = new InstallOptions();
    }

    public static AppInstallOptions makeOptions(boolean z, boolean z2) {
        return new AppInstallOptions();
    }

    public static AppInstallOptions makeOptions(boolean z) {
        return new AppInstallOptions();
    }
}
