package com.nrzs.va;

import android.content.pm.PackageManager;
import com.lody.virtual.client.core.VirtualCore;
import com.lody.virtual.client.ipc.VPackageManager;
import com.lody.virtual.remote.InstallResult;
import java.io.IOException;
import java.util.concurrent.Callable;
import p110z1.DoneCallback;
import p110z1.FailCallback;
import p110z1.VUiKit;

/* renamed from: com.nrzs.va.VirtualCoreProxy */
/* loaded from: classes2.dex */
public class VirtualCoreProxy {
    public static void preOpt(String str) throws IOException {
    }

    public static void installPackage(final String str, final AppInstallOptions appInstallOptions, final AppInstallCallback appInstallCallback) {
        VUiKit.m11713a().mo3332a(new Callable<InstallResult>() { // from class: com.nrzs.va.VirtualCoreProxy.3
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // java.util.concurrent.Callable
            public InstallResult call() {
                return VirtualCore.get().installPackage(str, appInstallOptions.mParams);
            }
        }).mo3285a(new FailCallback<Throwable>() { // from class: com.nrzs.va.VirtualCoreProxy.2
            public void onFail(Throwable th) {
                AppInstallCallback.this.onFinish(null);
            }
        }).mo3282b(new DoneCallback<InstallResult>() { // from class: com.nrzs.va.VirtualCoreProxy.1
            public void onDone(InstallResult installResult) {
                AppInstallCallback.this.onFinish(new AppInstallResult(installResult));
            }
        });
    }

    public static AppInstallResult installPackageSync(String str, AppInstallOptions appInstallOptions) {
        return new AppInstallResult(VirtualCore.get().installPackage(str, appInstallOptions.mParams));
    }

    public static boolean isEngineInstalled() {
        return com.lody.virtual.client.core.VirtualCoreProxy.isEngineInstalled();
    }

    public static PackageManager getUnHookPackageManager() {
        return VirtualCore.get().getUnHookPackageManager();
    }

    public static boolean isRunProcess(String str) {
        return com.lody.virtual.client.core.VirtualCoreProxy.isRunProcess(str);
    }

    public static String[] getDangrousPermissions(String str) {
        return VPackageManager.get().getDangrousPermissions(str);
    }
}
