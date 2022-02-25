package com.lody.virtual.client.hook.proxies.connectivity;

import com.lody.virtual.client.hook.base.BinderInvocationProxy;
import com.lody.virtual.client.hook.base.ResultStaticMethodProxy;
import p110z1.IConnectivityManager;

/* loaded from: classes.dex */
public class ConnectivityStub extends BinderInvocationProxy {
    public ConnectivityStub() {
        super(IConnectivityManager.C5168a.asInterface, "connectivity");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.lody.virtual.client.hook.base.MethodInvocationProxy
    public void onBindMethods() {
        super.onBindMethods();
        addMethodProxy(new ResultStaticMethodProxy("isTetheringSupported", true));
    }
}
