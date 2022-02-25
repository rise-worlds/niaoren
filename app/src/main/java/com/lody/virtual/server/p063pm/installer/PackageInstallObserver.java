package com.lody.virtual.server.p063pm.installer;

import android.content.Intent;
import android.content.p001pm.IPackageInstallObserver2;
import android.os.Bundle;

/* renamed from: com.lody.virtual.server.pm.installer.PackageInstallObserver */
/* loaded from: classes.dex */
public class PackageInstallObserver {
    private final IPackageInstallObserver2.Stub mBinder = new IPackageInstallObserver2.Stub() { // from class: com.lody.virtual.server.pm.installer.PackageInstallObserver.1
        @Override // android.content.p001pm.IPackageInstallObserver2
        public void onUserActionRequired(Intent intent) {
            PackageInstallObserver.this.onUserActionRequired(intent);
        }

        @Override // android.content.p001pm.IPackageInstallObserver2
        public void onPackageInstalled(String str, int i, String str2, Bundle bundle) {
            PackageInstallObserver.this.onPackageInstalled(str, i, str2, bundle);
        }
    };

    public void onPackageInstalled(String str, int i, String str2, Bundle bundle) {
    }

    public void onUserActionRequired(Intent intent) {
    }

    public IPackageInstallObserver2 getBinder() {
        return this.mBinder;
    }
}
