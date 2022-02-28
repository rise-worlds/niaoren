package com.nrzs.va;

import com.lody.virtual.remote.InstallResult;

/* renamed from: com.nrzs.va.AppInstallResult */
/* loaded from: classes2.dex */
public class AppInstallResult {
    public String error;
    public boolean isSuccess;
    public String packageName;

    public AppInstallResult(InstallResult installResult) {
        this.isSuccess = false;
        this.packageName = installResult.packageName;
        this.isSuccess = installResult.isSuccess;
        this.error = installResult.error;
    }

    public AppInstallResult(String str) {
        this.isSuccess = false;
        this.error = str;
        this.isSuccess = false;
    }

    public static AppInstallResult makeFailure(String str) {
        return new AppInstallResult(str);
    }
}
