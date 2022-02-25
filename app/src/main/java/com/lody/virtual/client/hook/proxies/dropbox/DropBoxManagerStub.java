package com.lody.virtual.client.hook.proxies.dropbox;

import com.lody.virtual.client.core.VirtualCore;
import com.lody.virtual.client.hook.base.BinderInvocationProxy;
import com.lody.virtual.client.hook.base.ResultStaticMethodProxy;
import p110z1.DropBoxManager;
import p110z1.IDropBoxManagerService;

/* loaded from: classes.dex */
public class DropBoxManagerStub extends BinderInvocationProxy {
    public DropBoxManagerStub() {
        super(IDropBoxManagerService.C5199a.asInterface, "dropbox");
    }

    @Override // com.lody.virtual.client.hook.base.BinderInvocationProxy, com.lody.virtual.client.hook.base.MethodInvocationProxy, com.lody.virtual.client.interfaces.IInjector
    public void inject() throws Throwable {
        super.inject();
        try {
            DropBoxManager.mService.set((android.os.DropBoxManager) VirtualCore.get().getContext().getSystemService("dropbox"), getInvocationStub().getProxyInterface());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.lody.virtual.client.hook.base.MethodInvocationProxy
    public void onBindMethods() {
        super.onBindMethods();
        addMethodProxy(new ResultStaticMethodProxy("getNextEntry", null));
    }
}
