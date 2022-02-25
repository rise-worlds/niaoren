package com.lody.virtual.client.hook.proxies.appops;

import android.annotation.TargetApi;
import com.lody.virtual.client.core.VirtualCore;
import com.lody.virtual.client.hook.annotations.Inject;
import com.lody.virtual.client.hook.base.BinderInvocationProxy;
import p110z1.AppOpsManager;
import p110z1.IAppOpsService;

@Inject(MethodProxies.class)
@TargetApi(19)
/* loaded from: classes.dex */
public class AppOpsManagerStub extends BinderInvocationProxy {
    public AppOpsManagerStub() {
        super(IAppOpsService.C5193a.asInterface, "appops");
    }

    @Override // com.lody.virtual.client.hook.base.BinderInvocationProxy, com.lody.virtual.client.hook.base.MethodInvocationProxy, com.lody.virtual.client.interfaces.IInjector
    public void inject() throws Throwable {
        super.inject();
        if (AppOpsManager.mService != null) {
            try {
                AppOpsManager.mService.set((android.app.AppOpsManager) VirtualCore.get().getContext().getSystemService("appops"), getInvocationStub().getProxyInterface());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.lody.virtual.client.hook.base.MethodInvocationProxy
    public void onBindMethods() {
        super.onBindMethods();
    }
}
