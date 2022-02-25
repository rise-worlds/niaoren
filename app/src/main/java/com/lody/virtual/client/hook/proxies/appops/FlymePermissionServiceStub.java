package com.lody.virtual.client.hook.proxies.appops;

import com.lody.virtual.client.hook.base.BinderInvocationProxy;
import com.lody.virtual.client.hook.base.ReplaceCallingPkgMethodProxy;
import p110z1.IFlymePermissionService;

/* loaded from: classes.dex */
public class FlymePermissionServiceStub extends BinderInvocationProxy {
    public FlymePermissionServiceStub() {
        super(IFlymePermissionService.C5211a.TYPE, "flyme_permission");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.lody.virtual.client.hook.base.MethodInvocationProxy
    public void onBindMethods() {
        super.onBindMethods();
        addMethodProxy(new ReplaceCallingPkgMethodProxy("noteIntentOperation"));
    }
}
